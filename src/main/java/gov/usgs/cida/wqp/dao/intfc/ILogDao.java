package gov.usgs.cida.wqp.dao.intfc;

import java.math.BigDecimal;
import java.util.Map;

public interface ILogDao extends IDao {

	static String QUERY_INSERT_ID = ".insert";
	static String QUERY_HEAD_ID = ".head";
	static String QUERY_FIRST_ROW_ID = ".firstRow";
	static String QUERY_COMPLETE_ID = ".complete";

	static String ID = "id";
	static String ORIGIN = "origin";
	static String CALL_TYPE = "callType";
	static String END_POINT = "endPoint";
	static String QUERY_STRING = "queryString";
	static String TOTAL_ROWS_EXPECTED = "totalRowsExpected";
	static String DATA_STORE_COUNTS = "dataStoreCounts";
	static String HTTP_STATUS_CODE = "httpStatusCode";
	
	BigDecimal addLog(Map<String, Object> parameterMap);

	void setHeadComplete(Map<String, Object> parameterMap);

	void setFirstRow(Map<String, Object> parameterMap);

	void setRequestComplete(Map<String, Object> parameterMap);
}
