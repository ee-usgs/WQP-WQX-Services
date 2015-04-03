package gov.usgs.cida.wqp.webservice.SimpleStation;

import gov.usgs.cida.wqp.station.dao.ICountDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleStationDao extends SqlSessionDaoSupport implements ICountDao {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public SimpleStationDao(SqlSessionFactory sqlSessionFactory) {
		log.trace(getClass().getName());
		setSqlSessionFactory(sqlSessionFactory);
	}
	
	public void stream(Map<String, Object> parameterMap, ResultHandler handler) {
		try {
			getSqlSession().select("simpleStation.select", parameterMap, handler);
		} catch (Exception e) {
			log.error("SimpleStation ResultHandler error", e);
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> getCounts(Map<String, Object> parameterMap) {
		return getSqlSession().selectList("simpleStation.count", parameterMap);
	}

	@Override
	//TODO - clean up this indirection
	public List<Map<String, Object>> getCounts(String queryId, Map<String, Object> parameterMap) {
		return getCounts(parameterMap);
	}

}
