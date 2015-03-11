package gov.usgs.cida.wqp.station.dao;

import gov.usgs.cida.wqp.station.SimpleStation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;

public interface IStationDao {

    List<SimpleStation> get(Map<String, Object> parameterMap);
    List<Map<String,Object>> getMapList(Map<String, Object> parameterMap);
    void stream(Map<String, Object> parameterMap, ResultHandler handler);
	int getCount(Map<String, Object> parameterMap);
}
