package gov.usgs.cida.wqp.webservice;

import static gov.usgs.cida.wqp.swagger.model.ProjectMonitoringLocationWeightingCountJson.HEADER_NWIS_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.ProjectMLWeightingDelimitedTest;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.util.HttpConstants;

public class ProjectMLWeightingControllerTest extends BaseSpringTest {

	protected ProjectMonitoringLocationWeightingController controller;

	@Before
	public void setup() {
		controller = new ProjectMonitoringLocationWeightingController(null, null, null, null, null, null, null, null);
	}

	@After
	public void tearDown() {
		// Need to manually clear out this thread local
		ProjectMonitoringLocationWeightingController.setCounts(null);
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getRawCounts());
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
