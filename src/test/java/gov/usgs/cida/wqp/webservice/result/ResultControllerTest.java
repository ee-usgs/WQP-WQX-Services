package gov.usgs.cida.wqp.webservice.result;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.ResultDelimitedTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerTest;

public class ResultControllerTest extends BaseSpringTest {

	protected ResultController controller;

	@Before
	public void setup() {
		controller = new ResultController(null, null, null, null, null, null, null);
	}

	@After
	public void teardown() {
		//Need to manually clear out this thread local
		ResultController.setCounts(null);
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getRawCounts());
		assertEquals(HttpConstants.HEADER_TOTAL_RESULT_COUNT, countHeader);
		assertEquals(BaseControllerTest.TEST_NWIS_STATION_COUNT, response.getHeaderValue(HEADER_NWIS_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_STATION_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_NWIS_ACTIVITY_COUNT, response.getHeaderValue(HEADER_NWIS_ACTIVITY_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_ACTIVITY_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT));
		assertEquals(BaseControllerTest.TEST_NWIS_RESULT_COUNT, response.getHeaderValue(HEADER_NWIS_RESULT_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_RESULT_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_RESULT_COUNT));
	}

	@Test
	public void getMappingTest() {
		ResultDelimitedTest.assertBiologicalProfile(controller.getMapping(Profile.BIOLOGICAL));
		ResultDelimitedTest.assertPcResultProfile(controller.getMapping(Profile.PC_RESULT));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.PC_RESULT, controller.determineProfile(null));

		Map<String, Object> pm = new HashMap<>();
		pm.put(Parameters.DATA_PROFILE.toString(), new String[]{"biological"});
		assertEquals(Profile.BIOLOGICAL, controller.determineProfile(pm));
	}

}
