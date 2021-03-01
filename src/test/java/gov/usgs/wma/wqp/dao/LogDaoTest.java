package gov.usgs.wma.wqp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
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

	Integer id;
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

		id = 1;
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
	public void requestTest() throws IOException, JSONException { assertTrue(LogDao.LOG instanceof ch.qos.logback.classic.Logger,
				"LogDao loggger type is not expected ch.qos.logback.classic.Logger");
		assertTrue(LogDao.counter.get() == 1, "Expected id to start at 1.");
		addLog();
		headComplete();
		firstRow();
		requestComplete();
		assertTrue(appender.list.size() == 1, "Expected only 1 log entry, got " + appender.list.size());
		String message = appender.list.get(0).getMessage();
		assertTrue(message != null && message.trim().length() > 0, "Logging message is empty.");
		String[] fields = message.split("\\|");
		String assertMess = String.format("Expected log message to have 4 | separated fields. Got %d, message=%s",
				fields.length, message);
		assertTrue(fields.length == 4, assertMess);
		assertEquals("Web Request", fields[0]);
		assertEquals("1", fields[1]);
		assertEquals("complete", fields[2]);
		JSONObject logJson = new JSONObject(fields[3]);
		assertJsonTimestamps(logJson);
		// now that "started" and "complete" times have been verified, removed them from json and compare the other fields
		logJson.remove("started");
		logJson.remove("complete");
		String expectedJson = getCompareFile("webServiceLog/afterRequestComplete.json");
		assertThat(logJson, sameJSONObjectAs(new JSONObject(expectedJson)));
		assertTrue(LogDao.counter.get() == 2, "Expected next id to start at 2.");
	}

	private void addLog() {
		parameterMap.put(LogDao.ORIGIN, "WQP Site");
		parameterMap.put(LogDao.CALL_TYPE, "GET");
		parameterMap.put(LogDao.ENDPOINT, "/wqp/summary/monitoringlocation/search");
		parameterMap.put(LogDao.POST_DATA, "{\"huc\":[\"010700061301\"],\"mimeType\":\"geojson\",\"zip\":\"no\"}");
		parameterMap.put(LogDao.USER_AGENT, "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/98 Safari/537.4 (StatusCake)");
		id = logDao.addLog(parameterMap);
	}

	private void headComplete() {
		parameterMap.put(LogDao.ID, id);
		parameterMap.put(LogDao.TOTAL_ROWS_EXPECTED, BaseControllerTest.TEST_TOTAL_RES_DETECT_QNT_LMT_COUNT);
		parameterMap.put(LogDao.DATA_STORE_COUNTS, DATA_COUNTS_IN_DB);
		logDao.setHeadComplete(parameterMap);
	}

	private void firstRow() {
		parameterMap.put(LogDao.ID, id);
		logDao.setFirstRow(parameterMap);
	}

	private void requestComplete() {
		parameterMap.put(LogDao.ID, id);
		parameterMap.put(LogDao.HTTP_STATUS_CODE, "200");
		parameterMap.put(LogDao.DOWNLOAD_DETAILS, DOWNLOAD_DETAILS);
		logDao.setRequestComplete(parameterMap);
	}

	// verify "started" and "complete" times in json
	private void assertJsonTimestamps(JSONObject json) throws JSONException {
		assertNotNull(json);
		String startedTimeStr = json.getString("started");
		assertNotNull(startedTimeStr);
		Instant jsonStartedIntance = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(startedTimeStr));
		// The logging of json from beginning to end should happen very fast, should easily be under a second even
		// on a lap top.
		assertTrue(jsonStartedIntance.isAfter(testTimestamp), "started timestamp in log json is not after test start");
		long duration = testTimestamp.until(jsonStartedIntance, ChronoUnit.MICROS);
		assertTrue(duration <= MICROS_IN_SEC, "duration from test started until 'started' timestamp is more than expected: " + duration);

		// verify complete time stamp
		String completedTimeStr = json.getString("complete");
		assertNotNull(completedTimeStr);
		Instant jsonCompleteIntance = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(completedTimeStr));
		assertTrue(jsonCompleteIntance.isAfter(jsonStartedIntance),
				"complete timestamp in log json is not after started timestamp");
		duration = jsonStartedIntance.until(jsonCompleteIntance, ChronoUnit.SECONDS);
		assertTrue(duration <= 1, "duration from 'started' until 'complete' timestamp is more than expected: " + duration);
	}

}
