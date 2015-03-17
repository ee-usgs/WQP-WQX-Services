package gov.usgs.cida.wqp.station.dao;

import java.util.List;
import java.util.Map;

public interface ICountDao {
	List<Map<String, Object>> getCounts(String queryId, Map<String, Object> parameterMap);
}
