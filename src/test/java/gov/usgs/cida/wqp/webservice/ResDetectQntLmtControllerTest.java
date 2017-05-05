package gov.usgs.cida.wqp.webservice;

import static gov.usgs.cida.wqp.swagger.model.ActivityCountJson.HEADER_NWIS_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResDetectQntLmtCountJson.HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResultCountJson.HEADER_NWIS_RESULT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.ResDetectQntLmtDelimitedTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;

public class ResDetectQntLmtControllerTest extends BaseSpringTest {

	protected ResDetectQntLmtController controller;

	@Before
	public void setup() {
		controller = new ResDetectQntLmtController(null, null, null, null, null, null, null, null);
	}

	@After
	public void teardown() {
		//Need to manually clear out this thread local
		ActivityController.setCounts(null);
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getRawCounts());
		assertEquals(HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT, countHeader);
		assertEquals(BaseControllerTest.TEST_NWIS_STATION_COUNT, response.getHeaderValue(HEADER_NWIS_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_STATION_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_NWIS_ACTIVITY_COUNT, response.getHeaderValue(HEADER_NWIS_ACTIVITY_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_ACTIVITY_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT));
		assertEquals(BaseControllerTest.TEST_NWIS_RESULT_COUNT, response.getHeaderValue(HEADER_NWIS_RESULT_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_RESULT_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_RESULT_COUNT));
		assertEquals(BaseControllerTest.TEST_NWIS_RES_DETECT_QNT_LMT_COUNT, response.getHeaderValue(HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_RES_DETECT_QNT_LMT_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT));
	}

	@Test
	public void getMappingTest() {
		ResDetectQntLmtDelimitedTest.assertResDetectQntLmtProfile(controller.getMapping(Profile.RES_DETECT_QNT_LMT));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.RES_DETECT_QNT_LMT, controller.determineProfile(null));

		Map<String, Object> pm = new HashMap<>();
		pm.put(Parameters.DATA_PROFILE.toString(), new String[]{"biological"});
		assertEquals(Profile.BIOLOGICAL, controller.determineProfile(pm));
	}

}
