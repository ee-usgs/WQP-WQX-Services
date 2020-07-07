package gov.usgs.wma.wqp.webservice;

import static gov.usgs.wma.wqp.openapi.model.ProjectCountJson.HEADER_NWIS_PROJECT_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.ProjectDelimitedTest;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.util.HttpConstants;

public class ProjectControllerTest {

	protected ProjectController controller;

	@BeforeEach
	public void setUp() {
		controller = new ProjectController(null, null, null, null, null, null, null);
	}

	@AfterEach
	public void tearDown() {
		// Need to manually clear out this thread local
		ProjectController.setCounts(null);
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getNwisRawCounts());
		assertEquals(HttpConstants.HEADER_TOTAL_PROJECT_COUNT, countHeader);
		assertEquals(BaseControllerTest.TEST_NWIS_PROJECT_COUNT, response.getHeaderValue(HEADER_NWIS_PROJECT_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_PROJECT_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_PROJECT_COUNT));
	}

	@Test
	public void getMappingTest() {
		ProjectDelimitedTest.assertProjectProfile(controller.getMapping(Profile.PROJECT));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.PROJECT, controller.determineProfile(null));

		FilterParameters filter = new FilterParameters();
		filter.setDataProfile("biological");
		assertEquals(Profile.BIOLOGICAL, controller.determineProfile(filter));
	}

}
