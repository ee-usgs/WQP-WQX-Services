package gov.usgs.cida.wqp.webservice.SimpleStation;

import static gov.usgs.cida.wqp.swagger.model.ActivityCountJson.HEADER_NWIS_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResDetectQntLmtCountJson.HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResultCountJson.HEADER_NWIS_RESULT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.FullIntegrationTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.StationDelimitedTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerTest;
import gov.usgs.cida.wqp.webservice.station.StationController;

@Category(FullIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/simpleStation.xml")
})
public class SimpleStationControllerTest extends BaseSpringTest {

	protected SimpleStationController controller;

	@Before
	public void setup() {
		controller = new SimpleStationController(null, null, null, null, null, null, null, null);
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
		StationDelimitedTest.assertSimpleStationProfile(controller.getMapping(Profile.SIMPLE_STATION));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.SIMPLE_STATION, controller.determineProfile(null));

		Map<String, Object> pm = new HashMap<>();
		pm.put(Parameters.DATA_PROFILE.toString(), new String[]{"station"});
		assertEquals(Profile.STATION, controller.determineProfile(pm));
	}

}
