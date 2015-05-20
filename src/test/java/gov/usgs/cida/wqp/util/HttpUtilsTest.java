package gov.usgs.cida.wqp.util;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
public class HttpUtilsTest implements HttpConstants, MybatisConstants {
	HttpUtils utils = new HttpUtils();
	@Test
	public void test_quoteString() {
		String text = "text";
		String expect = "\"text\"";
		assertEquals(expect, utils.quote(text));
	}
	@Test
	public void test_warningHeader_defaultsCodeAndError() {
		String expect = "299 WQP \"Unknown error\" date";
		assertEquals(expect, utils.warningHeader(null, null, "date"));
	}
	@Test
	public void test_warningHeader_codeAndErrorText() {
		String expect = "505 WQP \"Param error\" date";
		assertEquals(expect, utils.warningHeader(505, "Param error", "date"));
	}
	@Test
	public void test_warningHeader() {
		String expect = "505 WQP \"Param error\" " + new Date().toString();
		assertEquals(expect, utils.warningHeader(505, "Param error", null));
		System.out.println(expect);
	}
	@Test
	public void test_writeWarningHeaders() throws UnsupportedEncodingException {
		MockHttpServletResponse response = new MockHttpServletResponse();
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("abc", Arrays.asList("warning1", "warning2"));
		map.put("def", Arrays.asList("warning3", "warning4"));
		utils.writeWarningHeaders(response, map);
		assertTrue( response.containsHeader("Warning") );
		assertEquals("", response.getContentAsString());
		assertEquals( 4, response.getHeaderValues("Warning").size() );
		String warning = response.getHeaderValues("Warning").toString();
		assertTrue( warning.contains("299 WQP \"warning1\"") );
		assertTrue( warning.contains("299 WQP \"warning2\"") );
		assertTrue( warning.contains("299 WQP \"warning3\"") );
		assertTrue( warning.contains("299 WQP \"warning4\"") );
	}
	private void addEntryStation(String ds, Integer en, List<Map<String, Object>>  list) {
		Map<String, Object> count = new HashMap<String, Object>();
		count.put(DATA_SOURCE,ds);
		count.put(STATION_COUNT,en);
		list.add(count);
	}
	private void addEntryResult(String ds, Integer en, List<Map<String, Object>>  list) {
		Map<String, Object> count = new HashMap<String, Object>();
		count.put(DATA_SOURCE,ds);
		count.put(STATION_COUNT,en);
		count.put(RESULT_COUNT,en*3);
		list.add(count);
	}
	@Test
	public void test_addCountHeader_proper() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryStation("NWIS", 7, counts);
		addEntryStation("STEW", 5, counts);
		addEntryStation(null, 12, counts);
		utils.addSiteHeaders(response, counts);
		assertEquals(3, response.getHeaderNames().size());
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
	public void test_addCountHeader_noTotal() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryStation("NWIS", 7, counts);
		addEntryStation("STEW", 5, counts);
		utils.addSiteHeaders(response, counts);
		assertEquals(3, response.getHeaderNames().size());
		String nwis = "NWIS"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(nwis));
		String stew = "STEW"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(stew));
		assertTrue(response.containsHeader(HEADER_TOTAL_SITE_COUNT));
		assertEquals("7", response.getHeader(nwis));
		assertEquals("5", response.getHeader(stew));
		assertEquals("0", response.getHeader(HEADER_TOTAL_SITE_COUNT));
	}
	@Test
	public void test_handleException() throws UnsupportedEncodingException {
		MockHttpServletResponse response = new MockHttpServletResponse();
		utils.handleException(response, "abc", new Exception("testing me"));
		assertEquals(400, response.getStatus());
		assertEquals( 1, response.getHeaderValues("Warning").size() );
		assertEquals( "abc" , response.getContentAsString());
		String warning = response.getHeaderValues("Warning").toString();
		System.out.println(warning);
		assertTrue( warning.contains("299 WQP \"Unexpected Error:") );
	}
	
	@Test
	public void test_addPcResultHeaders_proper() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryResult("NWIS", 7, counts);
		addEntryResult("STEW", 5, counts);
		addEntryResult("Stor", 5, counts);
		addEntryResult(null, 12, counts);
		utils.addPcResultHeaders(response, counts);
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
	public void test_addPcResultHeaders_noTotal() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryResult("NWIS", 7, counts);
		addEntryResult("STEW", 5, counts);
		utils.addPcResultHeaders(response, counts);
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

	@Test
	public void determineHeaderNameTest() {
		Map<String, Object> count = new LinkedHashMap<>();
		assertEquals("Total-", utils.determineHeaderName(null, null));
		assertEquals("Total-", utils.determineHeaderName(count, null));
		assertEquals("Total-abc", utils.determineHeaderName(null, "abc"));
		count.put("abc", "123");
		count.put("def", "456");
		count.put(MybatisConstants.DATA_SOURCE, null);
		assertEquals("Total-xyz", utils.determineHeaderName(count, "xyz"));
		count.put(MybatisConstants.DATA_SOURCE, "dude");
		assertEquals("dude-xyz", utils.determineHeaderName(count, "xyz"));
	}

	@Test
	public void determineHeaderValueTest() {
		Map<String, Object> count = new LinkedHashMap<>();
		assertEquals("0", utils.determineHeaderValue(null, null));
		assertEquals("0", utils.determineHeaderValue(count, null));
		assertEquals("0", utils.determineHeaderValue(null, "abc"));
		count.put("abc", "123");
		count.put("def", "456");
		count.put("ghi", null);
		assertEquals("0", utils.determineHeaderValue(count, "xyz"));
		assertEquals("123", utils.determineHeaderValue(count, "abc"));
		assertEquals("456", utils.determineHeaderValue(count, "def"));
		assertEquals("0", utils.determineHeaderValue(count, "ghi"));
	}

}