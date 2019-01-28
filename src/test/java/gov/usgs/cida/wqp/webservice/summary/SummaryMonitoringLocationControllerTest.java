package gov.usgs.cida.wqp.webservice.summary;

import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import static gov.usgs.cida.wqp.swagger.model.ActivityCountJson.HEADER_NWIS_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResDetectQntLmtCountJson.HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResultCountJson.HEADER_NWIS_RESULT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;


public class SummaryMonitoringLocationControllerTest {
	protected SummaryMonitoringLocationController controller;

	@Before
	public void setup() {
		controller = new SummaryMonitoringLocationController(null, null, null, null, null, null, null, null);
		SummaryMonitoringLocationController.remove();
	}

	@Test
	public void addCountHeaderTest() {
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
	public void determineProfileTest() {
		assertEquals(Profile.SUMMARY_MONITORING_LOCATION, controller.determineProfile(null));

		FilterParameters filter = new FilterParameters();
		filter.setDataProfile("summarymonitoringlocation");
		assertEquals(Profile.SUMMARY_MONITORING_LOCATION, controller.determineProfile(filter));
		
		filter.setDataProfile("periodOfRecord");
		assertEquals(Profile.PERIOD_OF_RECORD, controller.determineProfile(filter));
	}
}
