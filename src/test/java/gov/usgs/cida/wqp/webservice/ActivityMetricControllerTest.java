package gov.usgs.cida.wqp.webservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.ActivityMetricDelimitedTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;

public class ActivityMetricControllerTest extends BaseSpringTest {

	protected ActivityMetricController controller;

	@Before
	public void setup() {
		controller = new ActivityMetricController(null, null, null, null, null, null, null);
	}

	@After
	public void teardown() {
		//Need to manually clear out this thread local
		ActivityMetricController.setCounts(null);
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getRawCounts());
		assertEquals(HttpConstants.HEADER_TOTAL_ACTIVITY_METRIC_COUNT, countHeader);
		assertEquals(BaseControllerTest.TEST_NWIS_STATION_COUNT, response.getHeaderValue(HEADER_NWIS_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_STATION_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_NWIS_ACTIVITY_COUNT, response.getHeaderValue(HEADER_NWIS_ACTIVITY_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_ACTIVITY_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT));
		assertEquals(BaseControllerTest.TEST_NWIS_ACTIVITY_METRIC_COUNT, response.getHeaderValue(HEADER_NWIS_ACTIVITY_METRIC_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_ACTIVITY_METRIC_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_ACTIVITY_METRIC_COUNT));
		assertFalse(response.containsHeader(HEADER_NWIS_RESULT_COUNT));
		assertFalse(response.containsHeader(HttpConstants.HEADER_TOTAL_RESULT_COUNT));
	}

	@Test
	public void getMappingTest() {
		ActivityMetricDelimitedTest.assertActivityMetricProfile(controller.getMapping(Profile.ACTIVITY_METRIC));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.ACTIVITY_METRIC, controller.determineProfile(null));

		Map<String, Object> pm = new HashMap<>();
		pm.put(Parameters.DATA_PROFILE.toString(), new String[]{"biological"});
		assertEquals(Profile.BIOLOGICAL, controller.determineProfile(pm));
	}

}
