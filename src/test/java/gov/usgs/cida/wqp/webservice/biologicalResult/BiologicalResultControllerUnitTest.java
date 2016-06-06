package gov.usgs.cida.wqp.webservice.biologicalResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.MybatisConstants;

public class BiologicalResultControllerUnitTest extends BaseSpringTest implements HttpConstants {

	private void addEntryStation(String ds, Integer en, List<Map<String, Object>>  list) {
		Map<String, Object> count = new HashMap<String, Object>();
		count.put(MybatisConstants.DATA_SOURCE,ds);
		count.put(MybatisConstants.STATION_COUNT,en);
		list.add(count);
	}
	
	private void addEntryResult(String ds, Integer en, List<Map<String, Object>>  list) {
		Map<String, Object> count = new HashMap<String, Object>();
		count.put(MybatisConstants.DATA_SOURCE,ds);
		count.put(MybatisConstants.STATION_COUNT,en);
		count.put(MybatisConstants.RESULT_COUNT,en*3);
		list.add(count);
	}

	@Test
	public void test_addResultHeaders_proper() {
		BiologicalResultController testController = new BiologicalResultController(null, null, null, null, 12, null, null);
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryStation("NWIS", 7, counts);
		addEntryStation("STEW", 5, counts);
		addEntryStation(null, 12, counts);
		addEntryResult("NWIS", 7, counts);
		addEntryResult("STEW", 5, counts);
		addEntryResult("Stor", 5, counts);
		addEntryResult(null, 12, counts);
		assertEquals(HEADER_TOTAL_RESULT_COUNT, testController.addCountHeaders(response, counts));
		assertEquals(8, response.getHeaderNames().size());
		String nwis = "NWIS"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(nwis));
		String stew = "STEW"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(stew));
		assertTrue(response.containsHeader(HEADER_TOTAL_SITE_COUNT));
		assertEquals("7", response.getHeader(nwis));
		assertEquals("5", response.getHeader(stew));
		assertEquals("12", response.getHeader(HEADER_TOTAL_SITE_COUNT));
	}

	@Test
	public void test_addResultHeaders_noTotal() {
		BiologicalResultController testController = new BiologicalResultController(null, null, null, null, 12, null, null);
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryResult("NWIS", 7, counts);
		addEntryResult("STEW", 5, counts);
		addEntryStation("NWIS", 7, counts);
		addEntryStation("STEW", 5, counts);
		assertEquals(HEADER_TOTAL_RESULT_COUNT, testController.addCountHeaders(response, counts));
		assertEquals(6, response.getHeaderNames().size());
		String nwis = "NWIS"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(nwis));
		String stew = "STEW"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(stew));
		assertTrue(response.containsHeader(HEADER_TOTAL_SITE_COUNT));
		assertEquals("7", response.getHeader(nwis));
		assertEquals("5", response.getHeader(stew));
		assertEquals("0", response.getHeader(HEADER_TOTAL_SITE_COUNT));
	}

}
