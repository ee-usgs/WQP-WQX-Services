package gov.usgs.cida.wqp.webservice.SimpleStation;

import static gov.usgs.cida.wqp.swagger.model.ActivityCountJson.HEADER_NWIS_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResDetectQntLmtCountJson.HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResultCountJson.HEADER_NWIS_RESULT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.StationDelimitedTest;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerTest;

public class SimpleStationControllerTest {

	protected SimpleStationController controller;

	@BeforeEach
	public void setup() {
		controller = new SimpleStationController(null, null, null, null, null, null, null);
		SimpleStationController.remove();
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getNwisRawCounts());
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
		StationDelimitedTest.assertSimpleStationProfile(controller.getMapping(Profile.SIMPLE_STATION));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.SIMPLE_STATION, controller.determineProfile(null));

		FilterParameters filter = new FilterParameters();
		filter.setDataProfile("station");
		assertEquals(Profile.STATION, controller.determineProfile(filter));
	}

}
