package gov.usgs.wma.wqp.webservice;

import static gov.usgs.wma.wqp.openapi.model.ProjectMonitoringLocationWeightingCountJson.HEADER_NWIS_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.ProjectMLWeightingDelimitedTest;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.util.HttpConstants;

public class ProjectMLWeightingControllerTest {

	protected ProjectMonitoringLocationWeightingController controller;

	@BeforeEach
	public void setup() {
		controller = new ProjectMonitoringLocationWeightingController(null, null, null, null, null, null, null);
	}

	@AfterEach
	public void tearDown() {
		// Need to manually clear out this thread local
		ProjectMonitoringLocationWeightingController.setCounts(null);
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getNwisRawCounts());
		assertEquals(HttpConstants.HEADER_TOTAL_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, countHeader);
		assertEquals(BaseControllerTest.TEST_NWIS_PROJECT_ML_WEIGHTING_COUNT, response.getHeaderValue(HEADER_NWIS_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_PROJECT_ML_WEIGHTING_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT));
	}

	@Test
	public void getMappingTest() {
		ProjectMLWeightingDelimitedTest.assertProjectMLWeightingProfile(controller.getMapping(Profile.PROJECT_MONITORING_LOCATION_WEIGHTING));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.PROJECT_MONITORING_LOCATION_WEIGHTING, controller.determineProfile(null));

		FilterParameters filter = new FilterParameters();
		filter.setDataProfile("biological");
		assertEquals(Profile.BIOLOGICAL, controller.determineProfile(filter));
	}
}
