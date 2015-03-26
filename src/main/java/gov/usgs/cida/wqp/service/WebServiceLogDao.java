package gov.usgs.cida.wqp.service;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.transaction.annotation.Transactional;
public class WebServiceLogDao extends SqlSessionDaoSupport implements IWebServiceLogDao {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public static final String ORIGIN = "origin";
	public static final String CALL_TYPE = "callType";
	public static final String ENDPOINT = "endpoint";
	public static final String QUERY_STRING = "queryString";
	public static final String TOTAL_ROWS_EXPECTED = "totalRowsExpected";
	public static final String DATA_STORE_COUNTS = "dataStoreCounts";

	public WebServiceLogDao() {
		log.trace(getClass().getName());
	}
	
	@Transactional
	public void add(final Map<String, Object> object) {
		getSqlSession().insert("addWebServiceLog", object);
	}
}