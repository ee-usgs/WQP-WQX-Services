package gov.usgs.wma.wqp.dao;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.dbunit.dataset.ReplacementDataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.springtestdbunit.dataset.ReplacementDataSetModifier;

import gov.usgs.wma.wqp.BaseIT;
import gov.usgs.wma.wqp.springinit.DBTestConfig;
import gov.usgs.wma.wqp.webservice.BaseControllerTest;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
classes={DBTestConfig.class, LogDao.class})
public class LogDaoIT extends BaseIT {

	public static final String DATA_COUNTS_SERVICE = "{\"counts\":[{\"NWIS\":{\"project\":106,\"organization\":1,\"activitymetric\":32,\"activity\":113,\"projectmonitoringlocationweighting\":1,\"site\":12,\"resultdetectionquantitationlimit\":432,\"result\":359}}]}";
	public static final String DATA_COUNTS_IN_DB = "{\"counts\": [{\"NWIS\": {\"site\": 12, \"result\": 359, \"project\": 106, \"activity\": 113, \"organization\": 1, \"activitymetric\": 32, \"resultdetectionquantitationlimit\": 432, \"projectmonitoringlocationweighting\": 1}}]}";
	public static final String DOWNLOAD_DETAILS = "{\"SILLY_ORG\": 17, \"WIDNR_WQX\": 6}";

	@Autowired
	LogDao logDao;

	Map<String, Object> parameterMap;

	Integer id;
	String testTimestamp;

	protected class LogModifier extends ReplacementDataSetModifier {
		@Override
		protected void addReplacements(ReplacementDataSet dataSet) {
			dataSet.addReplacementSubstring("[id]", id.toString());
			dataSet.addReplacementSubstring("[testTimestamp]", testTimestamp);
			dataSet.addReplacementSubstring("[dataStoreCounts]", DATA_COUNTS_IN_DB);
			dataSet.addReplacementSubstring("[downloadDetails]", DOWNLOAD_DETAILS);
		}
	}

	@BeforeEach
	public void setup() {
		parameterMap = new HashMap<>();

		id = 1;
		testTimestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now()).substring(0, 16);
	}

	@Test
	@DatabaseSetup(
			connection=DBTestConfig.DBUNIT_CONNECTION_WSL,
			value="classpath:/testData/webServiceLog/empty.xml"
			)
	@ExpectedDatabase(
			connection=DBTestConfig.DBUNIT_CONNECTION_WSL,
			value="classpath:/testResult/webServiceLog/afterInsert.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			modifiers=LogModifier.class,
			table="web_service_log",
			query="select web_service_log_id, origin, call_type, endpoint, user_agent, to_char(request_timestamp_utc, 'yyyy-mm-ddThh24:mi') request_timestamp_utc from web_service_log"
			)
	public void insertTest() {
		parameterMap.put(LogDao.ORIGIN, "WQP Site");
		parameterMap.put(LogDao.CALL_TYPE, "GET");
		parameterMap.put(LogDao.ENDPOINT, "/wqp/summary/monitoringlocation/search");
		parameterMap.put(LogDao.POST_DATA, "{\"huc\":[\"010700061301\"],\"mimeType\":\"geojson\",\"zip\":\"no\"}");
		parameterMap.put(LogDao.USER_AGENT, "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/98 Safari/537.4 (StatusCake)");
		id = logDao.addLog(parameterMap);
	}

	@Test
	@DatabaseSetup(
			connection=DBTestConfig.DBUNIT_CONNECTION_WSL,
			value="classpath:/testData/webServiceLog/beforeHead.xml"
			)
	@ExpectedDatabase(
			connection=DBTestConfig.DBUNIT_CONNECTION_WSL,
			value="classpath:/testResult/webServiceLog/afterHead.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			modifiers=LogModifier.class,
			table="web_service_log",
			query="select web_service_log_id, origin, call_type, endpoint, user_agent, request_timestamp_utc, to_char(head_sent_utc, 'yyyy-mm-ddThh24:mi') head_sent_utc, total_rows_expected, data_store_counts from web_service_log"
			)
	public void setHeadCompleteTest() {
		parameterMap.put(LogDao.ID, id);
		parameterMap.put(LogDao.TOTAL_ROWS_EXPECTED, BaseControllerTest.TEST_TOTAL_RES_DETECT_QNT_LMT_COUNT);
		parameterMap.put(LogDao.DATA_STORE_COUNTS, DATA_COUNTS_IN_DB);
		logDao.setHeadComplete(parameterMap);
	}

	@Test
	@DatabaseSetup(
			connection=DBTestConfig.DBUNIT_CONNECTION_WSL,
			value="classpath:/testData/webServiceLog/beforeFirstRow.xml"
			)
	@ExpectedDatabase(
			connection=DBTestConfig.DBUNIT_CONNECTION_WSL,
			value="classpath:/testResult/webServiceLog/afterFirstRow.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			modifiers=LogModifier.class,
			table="web_service_log",
			query="select web_service_log_id, origin, call_type, endpoint, user_agent, request_timestamp_utc, head_sent_utc, total_rows_expected, data_store_counts, to_char(first_row_sent_utc, 'yyyy-mm-ddThh24:mi') first_row_sent_utc from web_service_log"
			)
	public void setFirstRowTest() {
		parameterMap.put(LogDao.ID, id);
		logDao.setFirstRow(parameterMap);
	}

	@Test
	@DatabaseSetup(
			connection=DBTestConfig.DBUNIT_CONNECTION_WSL,
			value="classpath:/testData/webServiceLog/beforeRequestComplete.xml"
			)
	@ExpectedDatabase(
			connection=DBTestConfig.DBUNIT_CONNECTION_WSL,
			value="classpath:/testResult/webServiceLog/afterRequestComplete.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			modifiers=LogModifier.class,
			table="web_service_log",
			query="select web_service_log_id, origin, call_type, endpoint, user_agent, request_timestamp_utc, head_sent_utc, total_rows_expected, data_store_counts, first_row_sent_utc, to_char(request_completed_utc, 'yyyy-mm-ddThh24:mi') request_completed_utc, http_status_code, download_details from web_service_log"
			)
	public void setRequestCompleteTest() {
		parameterMap.put(LogDao.ID, id);
		parameterMap.put(LogDao.HTTP_STATUS_CODE, "200");
		parameterMap.put(LogDao.DOWNLOAD_DETAILS, DOWNLOAD_DETAILS);
		logDao.setRequestComplete(parameterMap);
	}
}
