package gov.usgs.wma.wqp.webservice;

import static gov.usgs.wma.wqp.openapi.model.OrganizationCountJson.HEADER_NWIS_ORGANIZATION_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.OrganizationDelimitedTest;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.util.HttpConstants;

public class OrganizationControllerTest {

	protected OrganizationController controller;

	@BeforeEach
	public void setup() {
		controller = new OrganizationController(null, null, null, null, null, null, null);
	}

	@AfterEach
	public void tearDown() {
		// Need to manually clear out this thread local
		OrganizationController.setCounts(null);
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getNwisRawCounts());
		assertEquals(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT, countHeader);
		assertEquals(BaseControllerTest.TEST_NWIS_ORGANIZATION_COUNT, response.getHeaderValue(HEADER_NWIS_ORGANIZATION_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_ORGANIZATION_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT));
	}

	@Test
	public void getMappingTest() {
		OrganizationDelimitedTest.assertOrganizationProfile(controller.getMapping(Profile.ORGANIZATION));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.ORGANIZATION, controller.determineProfile(null));

		FilterParameters filter = new FilterParameters();
		filter.setDataProfile("biological");
		assertEquals(Profile.BIOLOGICAL, controller.determineProfile(filter));
	}

}
