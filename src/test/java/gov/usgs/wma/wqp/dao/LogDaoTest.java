package gov.usgs.wma.wqp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;

import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.read.ListAppender;
import gov.usgs.wma.wqp.BaseTest;
import gov.usgs.wma.wqp.webservice.BaseControllerTest;

@SpringBootTest(webEnvironment=WebEnvironment.MOCK,
		classes = {LogDao.class, ObjectMapper.class})
public class LogDaoTest extends BaseTest {
	// number of microseconds in a second
	protected long MICROS_IN_SEC = TimeUnit.SECONDS.toMicros(1);

	public static final String DATA_COUNTS_SERVICE = "{\"counts\":[{\"NWIS\":{\"project\":106,\"organization\":1,\"activitymetric\":32,\"activity\":113,\"projectmonitoringlocationweighting\":1,\"site\":12,\"resultdetectionquantitationlimit\":432,\"result\":359}}]}";
	public static final String DATA_COUNTS_IN_DB = "{\"counts\": [{\"NWIS\": {\"site\": 12, \"result\": 359, \"project\": 106, \"activity\": 113, \"organization\": 1, \"activitymetric\": 32, \"resultdetectionquantitationlimit\": 432, \"projectmonitoringlocationweighting\": 1}}]}";
	public static final String DOWNLOAD_DETAILS = "{\"SILLY_ORG\": 17, \"WIDNR_WQX\": 6}";

	@Autowired
	private LogDao logDao;

	private Map<String, Object> parameterMap;
	private ListAppender<ILoggingEvent> appender;

	Instant testTimestamp;

	@BeforeEach
	public void setUp() {
		parameterMap = new HashMap<>();
		if (LogDao.LOG instanceof ch.qos.logback.classic.Logger) {
			ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LogDao.LOG;
			appender = new ListAppender<>();
			appender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
			appender.start();
			logger.setLevel(Level.INFO);
			logger.addAppender(appender);
		}

		testTimestamp = Instant.now();
	}

	@AfterEach
	public void cleanup() {
		if (LogDao.LOG instanceof ch.qos.logback.classic.Logger) {
			if (appender != null) {
				ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LogDao.LOG;
				logger.detachAppender(appender);
			}
		}
	}

	@Test
	public void requestTest() throws IOException, JSONException {
		assertTrue(LogDao.LOG instanceof ch.qos.logback.classic.Logger,
				"LogDao loggger type is not expected ch.qos.logback.classic.Logger");
		addLog();
		headComplete();
		firstRow();
		requestComplete();
		assertTrue(appender.list.size() == 1, "Expected only 1 log entry, got " + appender.list.size());
		JSONObject logJson = assertLogMessage("complete");
		logJson.remove("id"); // id field was verified above
		assertJsonTimestamps(logJson);
		// now that time stamps have been verified, removed them from json and compare the other fields
		removeJsonTimestamps(logJson);
		String expectedJson = getCompareFile("webServiceLog/afterRequestComplete.json");
		assertThat(logJson, sameJSONObjectAs(new JSONObject(expectedJson)));
	}

	@Test
	public void cleanupTest() throws IOException, JSONException {
		assertTrue(LogDao.LOG instanceof ch.qos.logback.classic.Logger,
				"LogDao loggger type is not expected ch.qos.logback.classic.Logger");
		addLog();
		headComplete();
		firstRow();
		assertTrue(appender.list.size() == 0,
				"Did not expect logging until request complete. Log entries: " + appender.list);
		logDao.cleanup();
		assertFalse(appender.list.size() == 0,
				"LogDao cleanup() did not log.");
		assertFalse(appender.list.size() > 1,
				"Expected LogDao cleanup() to only log once. Log entries: " + appender.list);
		assertLogMessage("firstRow");
	}

	// verify the log message and return the json logged as a JSONObject
	private JSONObject assertLogMessage(String expectedStage) throws JSONException {
		String message = appender.list.get(0).getMessage();
		assertTrue(message != null && message.trim().length() > 0, "Logging message is empty.");
		String[] fields = message.split("\\|");
		String assertMess = String.format("Expected log message to have 4 | separated fields. Got %d, message=%s",
				fields.length, message);
		assertTrue(fields.length == 4, assertMess);
		assertEquals("Web Request", fields[0]);
		String id = fields[1];
		assertTrue(id != null && !id.trim().isEmpty(), "id was blank in log message");
		UUID uuid = UUID.fromString(id);
		assertNotNull(uuid);
		assertEquals(expectedStage, fields[2]);
		JSONObject logJson = new JSONObject(fields[3]);
		assertEquals(id, logJson.getString("id"));
		assertEquals(expectedStage, logJson.getString("stage"));
		return logJson;
	}

	private void addLog() {
		parameterMap.put(LogDao.ORIGIN, "WQP Site");
		parameterMap.put(LogDao.CALL_TYPE, "GET");
		parameterMap.put(LogDao.ENDPOINT, "/wqp/summary/monitoringlocation/search");
		parameterMap.put(LogDao.POST_DATA, "{\"huc\":[\"010700061301\"],\"mimeType\":\"geojson\",\"zip\":\"no\"}");
		parameterMap.put(LogDao.USER_AGENT, "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/98 Safari/537.4 (StatusCake)");
		logDao.addLog(parameterMap);
	}

	private void headComplete() {
		parameterMap.put(LogDao.TOTAL_ROWS_EXPECTED, BaseControllerTest.TEST_TOTAL_RES_DETECT_QNT_LMT_COUNT);
		parameterMap.put(LogDao.DATA_STORE_COUNTS, DATA_COUNTS_IN_DB);
		logDao.setHeadComplete(parameterMap);
	}

	private void firstRow() {
		logDao.setFirstRow(parameterMap);
	}

	private void requestComplete() {
		parameterMap.put(LogDao.HTTP_STATUS_CODE, "200");
		parameterMap.put(LogDao.DOWNLOAD_DETAILS, DOWNLOAD_DETAILS);
		logDao.setRequestComplete(parameterMap);
	}

	private void removeJsonTimestamps(JSONObject json) throws JSONException {
		json.remove("start");
		json.remove("headComplete");
		json.remove("firstRow");
		json.remove("complete");
	}

	private void assertJsonTimestamps(JSONObject json) throws JSONException {
		assertJsonTimestamps(json, "start", "headComplete");
		assertJsonTimestamps(json, "headComplete", "firstRow");
		assertJsonTimestamps(json, "firstRow", "complete");
		assertJsonTimestamps(json, "start", "complete");
	}

	// verify the specified time fields in the json
	private void assertJsonTimestamps(JSONObject json, String time1, String laterTime) throws JSONException {
		assertNotNull(json);
		String firstTimeStr = json.getString(time1);
		assertNotNull(firstTimeStr);
		Instant time1Intance = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(firstTimeStr));
		// The logging of json from beginning to end should happen very fast, should
		// easily be under a second even on a lap top.
		boolean notBeforeStart = time1Intance.equals(testTimestamp) || time1Intance.isAfter(testTimestamp);
		assertTrue(notBeforeStart, String.format("%s timestamp in log json is not after test start", time1));
		long duration = testTimestamp.until(time1Intance, ChronoUnit.MICROS);
		assertTrue(duration <= MICROS_IN_SEC, String
				.format("duration from test started until '%s' timestamp is more than expected: " + duration, time1));

		// verify complete time stamp
		String laterTimeStr = json.getString(laterTime);
		assertNotNull(laterTimeStr);
		Instant laterTimeIntance = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(laterTimeStr));
		boolean notBeforeTime1 = laterTimeIntance.equals(time1Intance) || laterTimeIntance.isAfter(time1Intance);
		assertTrue(notBeforeTime1,
				String.format("%s timestamp in log json is not after %s timestamp", time1, laterTime));
		duration = time1Intance.until(laterTimeIntance, ChronoUnit.SECONDS);
		assertTrue(duration <= 1, String.format(
				"duration from test started until '%s' timestamp is more than expected: " + duration, laterTime));
	}

}
