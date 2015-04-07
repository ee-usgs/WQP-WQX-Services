package gov.usgs.cida.wqp.station.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StationDao extends SqlSessionDaoSupport implements IStationDao, ICountDao {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public static final String COUNT_QUERY_ID = "selectStationsCount";
	
	public StationDao(SqlSessionFactory sqlSessionFactory) {
		log.trace(getClass().getName());
		setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public List<Map<String,Object>> getMapList(Map<String, Object> parameterMap) {
		return getSqlSession().selectList("selectStationsMapList", parameterMap);
	}
	
	@Override
	public void stream(Map<String, Object> parameterMap, ResultHandler handler) {
		try {
			getSqlSession().select("dataMapper.selectStationsMapList", parameterMap, handler);
		} catch (Exception e) {
			log.error("Station ResultHandler error", e);
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Map<String, Object>> getCounts(Map<String, Object> parameterMap) {
		return getCounts(COUNT_QUERY_ID, parameterMap);
	}
	
	@Override
	public List<Map<String, Object>> getCounts(String queryId, Map<String, Object> parameterMap) {
		return getSqlSession().selectList(queryId, parameterMap);
	}
}