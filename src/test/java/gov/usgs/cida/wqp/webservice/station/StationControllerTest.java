package gov.usgs.cida.wqp.webservice.station;

import static gov.usgs.cida.wqp.swagger.model.ActivityCountJson.HEADER_NWIS_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResDetectQntLmtCountJson.HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResultCountJson.HEADER_NWIS_RESULT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.StationDelimitedTest;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerTest;

public class StationControllerTest extends BaseSpringTest {

	protected StationController controller;

	@Before
	public void setup() {
		controller = new StationController(null, null, null, null, null, null, null, null, null);
	}

	@After
	public void teardown() {
		//Need to manually clear out this thread local
		StationController.setCounts(null);
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getRawCounts());
		assertEquals(HttpConstants.HEADER_TOTAL_SITE_COUNT, countHeader);
		assertEquals(BaseControllerTest.TEST_NWIS_STATION_COUNT, response.getHeaderValue(HEADER_NWIS_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_STATION_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_SITE_COUNT));
		assertFalse(response.containsHeader(HEADER_NWIS_ACTIVITY_COUNT));
		assertFalse(response.containsHeader(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT));
		assertFalse(response.containsHeader(HEADER_NWIS_RESULT_COUNT));
		assertFalse(response.containsHeader(HttpConstants.HEADER_TOTAL_RESULT_COUNT));
		assertFalse(response.containsHeader(HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT));
		assertFalse(response.containsHeader(HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT));
	}

	@Test
	public void getMappingTest() {
		StationDelimitedTest.assertStationProfile(controller.getMapping(Profile.STATION));
		StationDelimitedTest.assertSimpleStationProfile(controller.getMapping(Profile.SIMPLE_STATION));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.STATION, controller.determineProfile(null));

		FilterParameters filter = new FilterParameters();
		filter.setDataProfile("simplestation");
		assertEquals(Profile.SIMPLE_STATION, controller.determineProfile(filter));
	}

}
