package gov.usgs.wma.wqp.webservice.station;

import static gov.usgs.wma.wqp.openapi.model.ActivityCountJson.HEADER_NWIS_ACTIVITY_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ResDetectQntLmtCountJson.HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ResultCountJson.HEADER_NWIS_RESULT_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.StationDelimitedTest;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.util.HttpConstants;
import gov.usgs.wma.wqp.webservice.BaseControllerTest;

public class StationControllerTest {

	protected StationController controller;

	@BeforeEach
	public void setup() {
		controller = new StationController(null, null, null, null, null, null, null, null);
		StationController.remove();
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
