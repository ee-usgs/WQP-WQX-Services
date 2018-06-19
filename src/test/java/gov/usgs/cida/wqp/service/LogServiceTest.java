package gov.usgs.cida.wqp.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.dao.LogDao;
import gov.usgs.cida.wqp.dao.intfc.ILogDao;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerTest;
import gov.usgs.cida.wqp.webservice.TestBaseController;

public class LogServiceTest {

	@Mock
	ILogDao logDao;
	LogService service;
	BigDecimal FIFTY_FIVE = BigDecimal.valueOf(55);
	@SuppressWarnings("rawtypes")
	ArgumentCaptor<Map> valueCapture = ArgumentCaptor.forClass(Map.class);
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	FilterParameters filter;
	String dataCounts = "<counts><nwis><station>12</station></nwis><nwis><activity>113</activity></nwis><nwis><activitymetric>32</activitymetric></nwis><nwis><result>359</result></nwis><nwis><resultdetectionquantitationlimit>432</resultdetectionquantitationlimit></nwis></counts>";

	@Before
	@SuppressWarnings("unchecked")
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service = new LogService(logDao);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		filter = new FilterParameters();
		when(logDao.addLog(valueCapture.capture())).thenReturn(FIFTY_FIVE);
		doNothing().when(logDao).setHeadComplete(valueCapture.capture());
		doNothing().when(logDao).setFirstRow(valueCapture.capture());
		doNothing().when(logDao).setRequestComplete(valueCapture.capture());
	}

	@Test
	public void getNodeNameTest() {
		assertEquals("activity", LogService.getNodeName(HttpConstants.ENDPOINT_ACTIVITY));
		assertEquals("activitymetric", LogService.getNodeName(HttpConstants.ENDPOINT_ACTIVITY_METRIC));
		assertEquals("result", LogService.getNodeName(HttpConstants.ENDPOINT_RESULT));
		assertEquals("station", LogService.getNodeName(HttpConstants.HEADER_SITE));
		assertEquals("project", LogService.getNodeName(HttpConstants.ENDPOINT_PROJECT));
		assertEquals("resultdetectionquantitationlimit", LogService.getNodeName(HttpConstants.ENDPOINT_RES_DETECT_QNT_LMT));
	}

	@Test
	public void legRequestNullTest() {
		assertEquals(FIFTY_FIVE, service.logRequest(null, null, filter));
		assertEquals(1, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		verify(logDao).addLog(anyMap());

		assertEquals(FIFTY_FIVE, service.logRequest(null, null));
		assertEquals(1, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		verify(logDao, times(2)).addLog(anyMap());
	}

	@Test
	public void logRequestEmptyTest() {
		assertEquals(FIFTY_FIVE, service.logRequest(request, response, filter));
		assertEquals(7, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals("Direct Call", valueCapture.getValue().get(LogDao.ORIGIN));
		assertEquals("", valueCapture.getValue().get(LogDao.CALL_TYPE));
		assertEquals("", valueCapture.getValue().get(LogDao.END_POINT));
		assertEquals("All filter data is now in the POST_DATA", valueCapture.getValue().get(LogDao.QUERY_STRING));
		assertEquals("{}", valueCapture.getValue().get(LogDao.POST_DATA));
		assertNull(valueCapture.getValue().get(LogDao.USER_AGENT));
		verify(logDao).addLog(anyMap());

		assertEquals(FIFTY_FIVE, service.logRequest(request, response));
		assertEquals(7, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals("Direct Call", valueCapture.getValue().get(LogDao.ORIGIN));
		assertEquals("", valueCapture.getValue().get(LogDao.CALL_TYPE));
		assertEquals("", valueCapture.getValue().get(LogDao.END_POINT));
		assertEquals("All filter data is now in the POST_DATA", valueCapture.getValue().get(LogDao.QUERY_STRING));
		assertEquals("{}", valueCapture.getValue().get(LogDao.POST_DATA));
		assertNull(valueCapture.getValue().get(LogDao.USER_AGENT));
		verify(logDao, times(2)).addLog(anyMap());
	}

	@Test
	public void logRequestDataTest() {
		request.addHeader("referer", "ui");
		request.addHeader("user-agent", "myBrowserType");
		request.setMethod("GET");
		request.setRequestURI(HttpConstants.RESULT_SEARCH_ENDPOINT);
		filter.setActivity("act");
		assertEquals(FIFTY_FIVE, service.logRequest(request, response, filter));
		assertEquals(7, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals("WQP Site", valueCapture.getValue().get(LogDao.ORIGIN));
		assertEquals("GET", valueCapture.getValue().get(LogDao.CALL_TYPE));
		assertEquals(HttpConstants.RESULT_SEARCH_ENDPOINT, valueCapture.getValue().get(LogDao.END_POINT));
		assertEquals("All filter data is now in the POST_DATA", valueCapture.getValue().get(LogDao.QUERY_STRING));
		assertEquals("{\"activity\":\"act\"}", valueCapture.getValue().get(LogDao.POST_DATA));
		assertEquals("myBrowserType", valueCapture.getValue().get(LogDao.USER_AGENT));
		verify(logDao).addLog(anyMap());

		assertEquals(FIFTY_FIVE, service.logRequest(request, response));
		assertEquals(7, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals("WQP Site", valueCapture.getValue().get(LogDao.ORIGIN));
		assertEquals("GET", valueCapture.getValue().get(LogDao.CALL_TYPE));
		assertEquals(HttpConstants.RESULT_SEARCH_ENDPOINT, valueCapture.getValue().get(LogDao.END_POINT));
		assertEquals("All filter data is now in the POST_DATA", valueCapture.getValue().get(LogDao.QUERY_STRING));
		assertEquals("{}", valueCapture.getValue().get(LogDao.POST_DATA));
		assertEquals("myBrowserType", valueCapture.getValue().get(LogDao.USER_AGENT));
		verify(logDao, times(2)).addLog(anyMap());
	}

	@Test
	public void logHeadCompleteNullTest() {
		service.logHeadComplete(null, FIFTY_FIVE);
		verify(logDao, never()).setHeadComplete(anyMap());
	}

	@Test
	public void logHeadCompleteTest() {
		response = setCountHeaders(response);
		service.logHeadComplete(response, FIFTY_FIVE);
		assertEquals(3, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));
		assertEquals(BaseControllerTest.TEST_TOTAL_RES_DETECT_QNT_LMT_COUNT, valueCapture.getValue().get(LogDao.TOTAL_ROWS_EXPECTED).toString());
		assertEquals(dataCounts, valueCapture.getValue().get(LogDao.DATA_STORE_COUNTS));
		verify(logDao).setHeadComplete(anyMap());
	}

	@Test
	public void logFirstRowCompleteTest() {
		service.logFirstRowComplete(FIFTY_FIVE);
		assertEquals(1, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));
		verify(logDao).setFirstRow(anyMap());
	}

	@Test
	public void logRequestCompleteNullTest() throws JSONException {
		service.logRequestComplete(FIFTY_FIVE, null, null);
		assertEquals(3, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));
		assertNull(valueCapture.getValue().get(LogDao.HTTP_STATUS_CODE));
		assertThat(new JSONObject(valueCapture.getValue().get(LogDao.DOWNLOAD_DETAILS).toString()),
				sameJSONObjectAs(new JSONObject("{}")));
		verify(logDao).setRequestComplete(anyMap());

		service.logRequestComplete(FIFTY_FIVE, "200", new HashMap<String, Integer>());
		assertEquals(3, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));
		assertEquals("200", valueCapture.getValue().get(LogDao.HTTP_STATUS_CODE));
		assertThat(new JSONObject(valueCapture.getValue().get(LogDao.DOWNLOAD_DETAILS).toString()),
				sameJSONObjectAs(new JSONObject("{}")));
		verify(logDao, times(2)).setRequestComplete(anyMap());
	}

	@Test
	public void logRequestCompleteTest() throws JSONException {
		service.logRequestComplete(FIFTY_FIVE, "200", getDownloadDetails());
		assertEquals(3, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));
		assertEquals("200", valueCapture.getValue().get(LogDao.HTTP_STATUS_CODE));
		assertThat(new JSONObject(valueCapture.getValue().get(LogDao.DOWNLOAD_DETAILS).toString()),
				sameJSONObjectAs(new JSONObject("{\"abc\":123,\"xyz\":242526}")));
		verify(logDao).setRequestComplete(anyMap());
	}

	protected MockHttpServletResponse setCountHeaders(MockHttpServletResponse response) {
		TestBaseController testController = new TestBaseController(null, null, null, null, null, null);
		testController.addSiteHeaders(response, BaseControllerTest.getRawCounts());
		testController.addActivityHeaders(response, BaseControllerTest.getRawCounts());
		testController.addActivityMetricHeaders(response, BaseControllerTest.getRawCounts());
		testController.addResultHeaders(response, BaseControllerTest.getRawCounts());
		testController.addResDetectQntLmtHeaders(response, BaseControllerTest.getRawCounts());
		response.addHeader("abc-def", "not a count");
		response.addHeader("abc-def-ghi", "also not a count");
		return response;
	}

	public static Map<String, Integer> getDownloadDetails() {
		Map<String, Integer> rtn = new HashMap<>();
		rtn.put("abc", 123);
		rtn.put("xyz", 242526);
		return rtn;
	}

	public static Map<String, Integer> getBadDownLoadDetails() {
		Map<String, Integer> rtn = new HashMap<>();
		rtn.put("abc", 123);
		rtn.put("xyz", 242526);
		return rtn;
	}

}
