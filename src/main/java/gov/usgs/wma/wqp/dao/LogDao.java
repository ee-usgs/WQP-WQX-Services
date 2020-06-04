package gov.usgs.wma.wqp.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.usgs.wma.wqp.dao.intfc.ILogDao;

@Component
public class LogDao extends BaseDao implements ILogDao {
	public static final String QUERY_INSERT_ID = ".insert";
	public static final String QUERY_HEAD_ID = ".head";
	public static final String QUERY_FIRST_ROW_ID = ".firstRow";
	public static final String QUERY_COMPLETE_ID = ".complete";

	public static final String ID = "id";
	public static final String ORIGIN = "origin";
	public static final String CALL_TYPE = "callType";
	public static final String ENDPOINT = "endpoint";
	public static final String TOTAL_ROWS_EXPECTED = "totalRowsExpected";
	public static final String DATA_STORE_COUNTS = "dataStoreCounts";
	public static final String HTTP_STATUS_CODE = "httpStatusCode";
	public static final String POST_DATA = "postData";
	public static final String USER_AGENT = "userAgent";
	public static final String DOWNLOAD_DETAILS = "downloadDetails";

	@Autowired
	public LogDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Integer addLog(Map<String, Object> parameterMap) {
		getSqlSession().insert(LOG_NAMESPACE + QUERY_INSERT_ID, parameterMap);
		return (Integer) parameterMap.get(ID);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void setHeadComplete(Map<String, Object> parameterMap) {
		getSqlSession().selectOne(LOG_NAMESPACE + QUERY_HEAD_ID, parameterMap);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void setFirstRow(Map<String, Object> parameterMap) {
		getSqlSession().selectOne(LOG_NAMESPACE + QUERY_FIRST_ROW_ID, parameterMap);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void setRequestComplete(Map<String, Object> parameterMap) {
		getSqlSession().selectOne(LOG_NAMESPACE + QUERY_COMPLETE_ID, parameterMap);
	}

}
