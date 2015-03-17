package gov.usgs.cida.wqp.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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

        utils.writeWarningHeaders(response, map, KML_EMPTY_DOC);
        
        assertTrue( response.containsHeader("Warning") );
        
        assertEquals( KML_EMPTY_DOC , response.getContentAsString());
        assertEquals( 4, response.getHeaderValues("Warning").size() );
        
        String warning = response.getHeaderValues("Warning").toString();
        
        assertTrue( warning.contains("299 WQP \"warning1\"") );
        assertTrue( warning.contains("299 WQP \"warning2\"") );
        assertTrue( warning.contains("299 WQP \"warning3\"") );
        assertTrue( warning.contains("299 WQP \"warning4\"") );
	}

	
	private void addEntry(String ds, Integer en, List<Map<String, Object>>  list) {
    	Map<String, Object> count = new HashMap<String, Object>();
    	count.put(DATA_SOURCE,ds);
    	count.put(ENTRIES,en);
		list.add(count);
	}
	
    @Test
    public void test_addCountHeader_proper() {
    	MockHttpServletResponse response = new MockHttpServletResponse();
        
    	List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
    	addEntry("NWIS", 7, counts);
    	addEntry("STEW", 5, counts);
    	addEntry(null, 12, counts);
    	
    	int total = utils.addCountHeader(response, counts);
        
        assertEquals(3, response.getHeaderNames().size());
        
        String nwis = "NWIS"+HEADER_DELIMITER+HEADER_SITE_COUNT;
        assertTrue(response.containsHeader(nwis));

        String stew = "STEW"+HEADER_DELIMITER+HEADER_SITE_COUNT;
        assertTrue(response.containsHeader(stew));
        
        assertTrue(response.containsHeader(HEADER_TOTAL_SITE_COUNT));
        
        assertEquals("7", response.getHeader(nwis));
        assertEquals("5", response.getHeader(stew));

        
        assertEquals("12", response.getHeader(HEADER_TOTAL_SITE_COUNT));
        assertEquals(12, total);
    }	

    @Test
    public void test_addCountHeader_noTotal() {
    	MockHttpServletResponse response = new MockHttpServletResponse();
        
    	List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
    	addEntry("NWIS", 7, counts);
    	addEntry("STEW", 5, counts);
    	
    	int total = utils.addCountHeader(response, counts);
        
        assertEquals(3, response.getHeaderNames().size());
        
        String nwis = "NWIS"+HEADER_DELIMITER+HEADER_SITE_COUNT;
        assertTrue(response.containsHeader(nwis));

        String stew = "STEW"+HEADER_DELIMITER+HEADER_SITE_COUNT;
        assertTrue(response.containsHeader(stew));
        
        assertTrue(response.containsHeader(HEADER_TOTAL_SITE_COUNT));
        
        assertEquals("7", response.getHeader(nwis));
        assertEquals("5", response.getHeader(stew));

        assertEquals("0", response.getHeader(HEADER_TOTAL_SITE_COUNT));
        assertEquals(0, total);
    }

    @Test
    public void test_handleException() throws UnsupportedEncodingException {
    	MockHttpServletResponse response = new MockHttpServletResponse();

        utils.handleException(response, KML_EMPTY_DOC, new Exception("testing me"));
        

        assertEquals(400, response.getStatus());
        assertEquals( 1, response.getHeaderValues("Warning").size() );
        assertEquals( KML_EMPTY_DOC , response.getContentAsString());
        
        String warning = response.getHeaderValues("Warning").toString();
        System.out.println(warning);
        assertTrue( warning.contains("299 WQP \"Unexpected Error:") );
    }

}
