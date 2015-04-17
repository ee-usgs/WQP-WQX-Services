package gov.usgs.cida.wqp.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogDao extends BaseDao implements ILogDao {

	@Autowired
	public LogDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	@Override
	public BigDecimal addLog(Map<String, Object> parameterMap) {
		getSqlSession().selectOne(LOG_NAMESPACE + QUERY_INSERT_ID, parameterMap);
		BigDecimal xxx = (BigDecimal) parameterMap.get(ID);
		return xxx;
	}

	@Override
	public void setHeadComplete(Map<String, Object> parameterMap) {
		getSqlSession().selectOne(LOG_NAMESPACE + QUERY_HEAD_ID, parameterMap);
	}

	@Override
	public void setFirstRow(Map<String, Object> parameterMap) {
		getSqlSession().selectOne(LOG_NAMESPACE + QUERY_FIRST_ROW_ID, parameterMap);
	}

	@Override
	public void setRequestComplete(Map<String, Object> parameterMap) {
		getSqlSession().selectOne(LOG_NAMESPACE + QUERY_COMPLETE_ID, parameterMap);
	}

}
