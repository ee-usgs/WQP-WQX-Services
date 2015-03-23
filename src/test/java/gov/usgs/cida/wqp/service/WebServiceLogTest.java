package gov.usgs.cida.wqp.service;

import static gov.usgs.cida.wqp.service.WebServiceLogDao.*;
import static org.junit.Assert.*;
import gov.usgs.cida.wqp.TestUtils;
import gov.usgs.cida.wqp.util.HttpConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;


public class WebServiceLogTest implements HttpConstants {


	@Test
	public void testLog_nullRequest() {
		HttpServletRequest  request  = null;
		
		Map<String, Object> log = new WebServiceLog().getLog(request);
		
		assertNotNull(log);
		assertTrue( log.isEmpty() );
	}

	@Test
	public void testLog_referer() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);
		
		Mockito.when(request.getHeader("referer")).thenReturn("referer"); // WQP Site
		
		Mockito.when(request.getMethod()).thenReturn("GET");
		Mockito.when(request.getRequestURI()).thenReturn("wqp/"+EndPoint.STATION.URI);
		Mockito.when(request.getQueryString()).thenReturn("?this=that&that=this");
		
		Map<String, Object> log = new WebServiceLog().getLog(request);
	
		assertEquals("WQP Site",log.get(ORIGIN));
		assertEquals("GET",log.get(CALL_TYPE));
		assertEquals(EndPoint.STATION.NAME,log.get(ENDPOINT));
		assertEquals("?this=that&that=this",log.get(QUERY_STRING));
		
	}
	
	@Test
	public void testLog_unknownEndpoint() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);

		Mockito.when(request.getRequestURI()).thenReturn("unknown/endpoint/uri");
		
		Mockito.when(request.getHeader("referer")).thenReturn(null); // direct call
		Mockito.when(request.getMethod()).thenReturn("GET");
		Mockito.when(request.getQueryString()).thenReturn("?this=that&that=this");
		
		Map<String, Object> log = new WebServiceLog().getLog(request);
	
		assertEquals("Direct Call",log.get(ORIGIN));
		assertEquals("GET",log.get(CALL_TYPE));
		assertEquals("unknown/endpoint/uri",log.get(ENDPOINT));
		assertEquals("?this=that&that=this",log.get(QUERY_STRING));
	}
	
	@Test
	public void testLog() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);
		
		Mockito.when(request.getHeader("referer")).thenReturn(null); // direct call
		Mockito.when(request.getMethod()).thenReturn("GET");
		Mockito.when(request.getRequestURI()).thenReturn("wqp/"+EndPoint.STATION.URI);
		Mockito.when(request.getQueryString()).thenReturn("?this=that&that=this");
		
		Map<String, Object> log = new WebServiceLog().getLog(request);
	
		assertEquals("Direct Call",log.get(ORIGIN));
		assertEquals("GET",log.get(CALL_TYPE));
		assertEquals(EndPoint.STATION.NAME,log.get(ENDPOINT));
		assertEquals("?this=that&that=this",log.get(QUERY_STRING));
	}

	
	
	@Test
	public void testCounts_nullRequest() {
		HttpServletRequest  request  = null;
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		
		Map<String, Object> counts = new WebServiceLog().getCounts(request, response);
		
		assertNotNull(counts);
		assertTrue( counts.isEmpty() );
	}

	@Test
	public void testCounts_nullResponse() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = null;
		
		Map<String, Object> counts = new WebServiceLog().getCounts(request, response);
		
		assertNotNull(counts);
		assertTrue( counts.isEmpty() );
	}
	
	
	@Test
	public void testCounts_unknownEndpoint() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

		Mockito.when(request.getRequestURI()).thenReturn("unknown/endpoint/uri");
		
		Map<String, Object> counts = new WebServiceLog().getCounts(request, response);
	
		assertNotNull(counts);
		assertTrue( counts.isEmpty() );
	}
	
	@Test
	public void testCounts_zero_noHeaders() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		
		Mockito.when(request.getRequestURI()).thenReturn("wqp/"+EndPoint.STATION.URI);
		
		Map<String, Object> counts = new WebServiceLog().getCounts(request, response);
	
		TestUtils.log(counts);
		
		assertEquals(0, counts.get(TOTAL_ROWS_EXPECTED));
		assertEquals("<counts></counts>", counts.get(DATA_STORE_COUNTS));
	}
	
	@Test
	public void testCounts_zero_noCountsHeaders() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		
		Mockito.when(request.getRequestURI()).thenReturn("wqp/"+EndPoint.STATION.URI);
		
		Collection<String> headers = new ArrayList<String>();
		headers.add(null);
		headers.add("");
		headers.add("FirstHeader");
		headers.add("Second"+HEADER_DELIMITER+"Header");
		headers.add("Second"+HEADER_DELIMITER+"Header"+HEADER_DELIMITER+"Other");
		headers.add(HEADER_TOTAL+HEADER_DELIMITER+"Header"+HEADER_DELIMITER+HEADER_COUNT);
		Mockito.when(response.getHeaderNames()).thenReturn(headers);
		
		Map<String, Object> counts = new WebServiceLog().getCounts(request, response);
	
		TestUtils.log(counts);
		
		assertEquals(0, counts.get(TOTAL_ROWS_EXPECTED));
		assertEquals("<counts></counts>", counts.get(DATA_STORE_COUNTS));
	}
	
	@Test
	public void testCounts_totalCount() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		
		Mockito.when(request.getRequestURI()).thenReturn("wqp/"+EndPoint.STATION.URI);
		
		String headerName = HEADER_TOTAL+HEADER_DELIMITER+EndPoint.STATION.COUNT_HEADER_NAME+HEADER_DELIMITER+HEADER_COUNT;
		Collection<String> headers = new ArrayList<String>();
		headers.add(headerName);
		Mockito.when(response.getHeaderNames()).thenReturn(headers);
		Mockito.when(response.getHeader(headerName)).thenReturn("123");
		
		Map<String, Object> counts = new WebServiceLog().getCounts(request, response);
	
		TestUtils.log(counts);
		
		assertEquals(123, counts.get(TOTAL_ROWS_EXPECTED));
		assertEquals("<counts></counts>", counts.get(DATA_STORE_COUNTS));
	}
	
	@Test
	public void testCounts_individualCount_site() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		
		Mockito.when(request.getRequestURI()).thenReturn("wqp/"+EndPoint.STATION.URI);
		
		String headerName = "asdf"+HEADER_DELIMITER+HEADER_SITE+HEADER_DELIMITER+HEADER_COUNT;
		Collection<String> headers = new ArrayList<String>();
		headers.add(headerName);
		Mockito.when(response.getHeaderNames()).thenReturn(headers);
		Mockito.when(response.getHeader(headerName)).thenReturn("123");
		
		Map<String, Object> counts = new WebServiceLog().getCounts(request, response);
	
		TestUtils.log(counts);
		
		assertEquals(0, counts.get(TOTAL_ROWS_EXPECTED));
		assertEquals("<counts><asdf><station>123</station></asdf></counts>", counts.get(DATA_STORE_COUNTS));
	}
	
	@Test
	public void testCounts_individualCount_results() {
		HttpServletRequest  request  = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		
		Mockito.when(request.getRequestURI()).thenReturn("wqp/"+EndPoint.STATION.URI);
		
		String headerName = "asdf"+HEADER_DELIMITER+"Test"+HEADER_DELIMITER+HEADER_COUNT;
		Collection<String> headers = new ArrayList<String>();
		headers.add(headerName);
		Mockito.when(response.getHeaderNames()).thenReturn(headers);
		Mockito.when(response.getHeader(headerName)).thenReturn("123");
		
		Map<String, Object> counts = new WebServiceLog().getCounts(request, response);
	
		TestUtils.log(counts);
		
		assertEquals(0, counts.get(TOTAL_ROWS_EXPECTED));
		assertEquals("<counts><asdf><result>123</result></asdf></counts>", counts.get(DATA_STORE_COUNTS));
	}
	
	
	@Test
	public void testSetDao() {
		WebServiceLogDao dao = Mockito.mock(WebServiceLogDao.class);
		
		WebServiceLog log = new WebServiceLog();
		log.setWebServiceLogDao(dao);
		assertEquals(dao, TestUtils.reflectValue(log, "dao"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testLogRequest() {
		WebServiceLogDao dao = Mockito.mock(WebServiceLogDao.class);
		
		WebServiceLog    log = Mockito.spy(new WebServiceLog());
		log.setWebServiceLogDao(dao);

		log.logRequest(null, null);
		
		Mockito.verify(log, Mockito.atLeastOnce()).getLog(Mockito.any(HttpServletRequest.class));
		Mockito.verify(log, Mockito.atLeastOnce()).getCounts(Mockito.any(HttpServletRequest.class), Mockito.any(HttpServletResponse.class));
		Mockito.verify(dao, Mockito.atLeastOnce()).add(Mockito.anyMap());
	}
	
	
	@Test
	public void testLogRequest_nullDao() {
		try {
			new WebServiceLog().logRequest(null, null);
			fail("should throw NPE when DAO not set");
		} catch (NullPointerException e) {
			assertEquals("No WebSeriveLogDao set", e.getMessage());
		}
	}	
}
