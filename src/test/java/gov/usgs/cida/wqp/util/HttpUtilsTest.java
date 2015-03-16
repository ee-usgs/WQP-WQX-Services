package gov.usgs.cida.wqp.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
	
    @Test
    public void test_addCountHeader() {
    	MockHttpServletResponse response = new MockHttpServletResponse();
        
    	utils.addCountHeader(response, 10);
        
        assertEquals(1, response.getHeaderNames().size());
        assertTrue(response.containsHeader(HEADER_SITE_COUNT));
        assertEquals("10", response.getHeader(HEADER_SITE_COUNT));
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
