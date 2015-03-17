package gov.usgs.cida.wqp.station.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;

public interface IStationDao {

    List<Map<String,Object>> getMapList(Map<String, Object> parameterMap);
    void stream(Map<String, Object> parameterMap, ResultHandler handler);
    List<Map<String, Object>> getCounts(Map<String, Object> parameterMap);
}
