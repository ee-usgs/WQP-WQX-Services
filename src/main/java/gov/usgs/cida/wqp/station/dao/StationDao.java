package gov.usgs.cida.wqp.station.dao;

import gov.usgs.cida.wqp.station.SimpleStation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StationDao extends SqlSessionDaoSupport implements IStationDao {
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


    public StationDao(SqlSessionFactory sqlSessionFactory) {
        log.trace(getClass().getName());
		setSqlSessionFactory(sqlSessionFactory);
	}
    
    
    @Override
    public List<SimpleStation> get(Map<String, Object> parameterMap) {
        return getSqlSession().selectList("simpleStationsSelect", parameterMap);
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
    public int getCount(Map<String, Object> parameterMap) {
        return getSqlSession().selectOne("selectStationsCount", parameterMap);
    }
}
