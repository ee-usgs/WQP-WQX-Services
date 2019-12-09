package gov.usgs.cida.wqp.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.dao.LogDao;
import gov.usgs.cida.wqp.dao.LogDaoIT;
import gov.usgs.cida.wqp.dao.intfc.ILogDao;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerTest;
import gov.usgs.cida.wqp.webservice.TestBaseController;

public class LogServiceTest {

	@Mock
	ILogDao logDao;
	LogService service;
	Integer FIFTY_FIVE = 55;
	@SuppressWarnings("rawtypes")
	ArgumentCaptor<Map> valueCapture = ArgumentCaptor.forClass(Map.class);
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	FilterParameters filter;

	public static final int LOG_REQUEST_PARAMETER_COUNT = 6;

	@BeforeEach
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
		assertEquals(LOG_REQUEST_PARAMETER_COUNT, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals("Direct Call", valueCapture.getValue().get(LogDao.ORIGIN));
		assertEquals("", valueCapture.getValue().get(LogDao.CALL_TYPE));
		assertEquals("", valueCapture.getValue().get(LogDao.ENDPOINT));
		assertEquals("{}", valueCapture.getValue().get(LogDao.POST_DATA));
		assertNull(valueCapture.getValue().get(LogDao.USER_AGENT));
		verify(logDao).addLog(anyMap());

		assertEquals(FIFTY_FIVE, service.logRequest(request, response));
		assertEquals(LOG_REQUEST_PARAMETER_COUNT, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals("Direct Call", valueCapture.getValue().get(LogDao.ORIGIN));
		assertEquals("", valueCapture.getValue().get(LogDao.CALL_TYPE));
		assertEquals("", valueCapture.getValue().get(LogDao.ENDPOINT));
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
		assertEquals(LOG_REQUEST_PARAMETER_COUNT, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals("WQP Site", valueCapture.getValue().get(LogDao.ORIGIN));
		assertEquals("GET", valueCapture.getValue().get(LogDao.CALL_TYPE));
		assertEquals(HttpConstants.RESULT_SEARCH_ENDPOINT, valueCapture.getValue().get(LogDao.ENDPOINT));
		assertEquals("{\"activity\":\"act\"}", valueCapture.getValue().get(LogDao.POST_DATA));
		assertEquals("myBrowserType", valueCapture.getValue().get(LogDao.USER_AGENT));
		verify(logDao).addLog(anyMap());

		assertEquals(FIFTY_FIVE, service.logRequest(request, response));
		assertEquals(LOG_REQUEST_PARAMETER_COUNT, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals("WQP Site", valueCapture.getValue().get(LogDao.ORIGIN));
		assertEquals("GET", valueCapture.getValue().get(LogDao.CALL_TYPE));
		assertEquals(HttpConstants.RESULT_SEARCH_ENDPOINT, valueCapture.getValue().get(LogDao.ENDPOINT));
		assertEquals("{}", valueCapture.getValue().get(LogDao.POST_DATA));
		assertEquals("myBrowserType", valueCapture.getValue().get(LogDao.USER_AGENT));
		verify(logDao, times(2)).addLog(anyMap());
	}

	@Test
	public void processEmptyCountsTest() {
		Map<String, Object> pc = service.processCounts(new ArrayList<>(), HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT);
		assertEquals(2, pc.size());
		assertEquals("{\"counts\":[]}", pc.get(LogDao.DATA_STORE_COUNTS));
		assertEquals("0", pc.get(LogDao.TOTAL_ROWS_EXPECTED).toString());
	}

	@Test
	public void processCountsTest() {
		List<Map<String, Object>> rawCounts = BaseControllerTest.getNwisRawCounts();
		rawCounts.add(BaseControllerTest.getStoretCounts());
		Map<String, Object> pc = service.processCounts(rawCounts, HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT);
		assertEquals(2, pc.size());
		assertEquals("{\"counts\":[{\"NWIS\":{\"project\":106,\"organization\":1,\"activitymetric\":32,\"activity\":113,\"projectmonitoringlocationweighting\":1,\"site\":12,\"resultdetectionquantitationlimit\":432,\"result\":359}},{\"STORET\":{\"project\":10,\"organization\":2,\"activitymetric\":2,\"activity\":13,\"projectmonitoringlocationweighting\":2,\"site\":2,\"resultdetectionquantitationlimit\":32,\"result\":59}}]}", pc.get(LogDao.DATA_STORE_COUNTS));
		assertEquals("4321", pc.get(LogDao.TOTAL_ROWS_EXPECTED).toString());
	}

	@Test
	public void logHeadCompleteNullTest() {
		service.logHeadComplete(null, null, null);
		assertEquals(1, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));

		service.logHeadComplete(null, null, FIFTY_FIVE);
		assertEquals(1, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));

		service.logHeadComplete(null, HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT, FIFTY_FIVE);
		assertEquals(1, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));

		service.logHeadComplete(BaseControllerTest.getNwisRawCounts(), null, FIFTY_FIVE);
		assertEquals(3, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));
		assertEquals("0", valueCapture.getValue().get(LogDao.TOTAL_ROWS_EXPECTED).toString());
		assertEquals(LogDaoIT.DATA_COUNTS_SERVICE, valueCapture.getValue().get(LogDao.DATA_STORE_COUNTS));

		service.logHeadComplete(BaseControllerTest.getNwisRawCounts(), HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT, null);
		assertEquals(3, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals(BaseControllerTest.TEST_TOTAL_RES_DETECT_QNT_LMT_COUNT, valueCapture.getValue().get(LogDao.TOTAL_ROWS_EXPECTED).toString());
		assertEquals(LogDaoIT.DATA_COUNTS_SERVICE, valueCapture.getValue().get(LogDao.DATA_STORE_COUNTS));

		service.logHeadComplete(BaseControllerTest.getNwisRawCounts(), null, null);
		assertEquals(3, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
		assertEquals("0", valueCapture.getValue().get(LogDao.TOTAL_ROWS_EXPECTED).toString());
		assertEquals(LogDaoIT.DATA_COUNTS_SERVICE, valueCapture.getValue().get(LogDao.DATA_STORE_COUNTS));

		service.logHeadComplete(null, HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT, null);
		assertEquals(1, valueCapture.getValue().size());
		assertNull(valueCapture.getValue().get(LogDao.ID));
	}

	@Test
	public void logHeadCompleteEmptyTest() {
		response = setCountHeaders(response);
		service.logHeadComplete(new ArrayList<>(), HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT, FIFTY_FIVE);
		assertEquals(3, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));
		assertEquals("0", valueCapture.getValue().get(LogDao.TOTAL_ROWS_EXPECTED).toString());
		assertEquals("{\"counts\":[]}", valueCapture.getValue().get(LogDao.DATA_STORE_COUNTS));
		verify(logDao).setHeadComplete(anyMap());
	}

	@Test
	public void logHeadCompleteTest() {
		response = setCountHeaders(response);
		service.logHeadComplete(BaseControllerTest.getNwisRawCounts(), HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT, FIFTY_FIVE);
		assertEquals(3, valueCapture.getValue().size());
		assertEquals(FIFTY_FIVE, valueCapture.getValue().get(LogDao.ID));
		assertEquals(BaseControllerTest.TEST_TOTAL_RES_DETECT_QNT_LMT_COUNT, valueCapture.getValue().get(LogDao.TOTAL_ROWS_EXPECTED).toString());
		assertEquals(LogDaoIT.DATA_COUNTS_SERVICE, valueCapture.getValue().get(LogDao.DATA_STORE_COUNTS));
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

	@Test
	public void getTotalRowsExpectedNullTest() {
		assertEquals(NumberUtils.INTEGER_ZERO, service.getTotalRowsExpected(getcountRow(), null));
	}

	@Test
	public void getTotalRowsExpectedNotContainsTest() {
		assertEquals(NumberUtils.INTEGER_ZERO, service.getTotalRowsExpected(getcountRow(), "total-a-b-c"));
	}

	@Test
	public void getTotalRowsExpectedBadValueTest() {
		assertEquals(NumberUtils.INTEGER_ZERO, service.getTotalRowsExpected(getcountRow(), "total-duh-count"));
		assertEquals(NumberUtils.INTEGER_ZERO, service.getTotalRowsExpected(getcountRow(), "total-wow-count"));
	}

	@Test
	public void getTotalRowsExpectedTest() {
		assertEquals(Integer.valueOf("123"), service.getTotalRowsExpected(getcountRow(), "total-abc-count"));
		assertEquals(Integer.valueOf("242526"), service.getTotalRowsExpected(getcountRow(), "total-xyz-count"));
	}

	protected MockHttpServletResponse setCountHeaders(MockHttpServletResponse response) {
		TestBaseController testController = new TestBaseController(null, null, null, null, null, null);
		testController.addSiteHeaders(response, BaseControllerTest.getNwisRawCounts());
		testController.addActivityHeaders(response, BaseControllerTest.getNwisRawCounts());
		testController.addActivityMetricHeaders(response, BaseControllerTest.getNwisRawCounts());
		testController.addResultHeaders(response, BaseControllerTest.getNwisRawCounts());
		testController.addResDetectQntLmtHeaders(response, BaseControllerTest.getNwisRawCounts());
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

	public static  Map<String, Object> getcountRow() {
		Map<String, Object> rtn = new HashMap<>();
		rtn.put("abc_count", 123);
		rtn.put("xyz_count", 242526);
		rtn.put("duh_count", "alpha");
		rtn.put("wow_count", null);
		return rtn;
	}
}
