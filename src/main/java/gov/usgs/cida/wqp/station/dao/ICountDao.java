package gov.usgs.cida.wqp.station.dao;

import java.util.Map;

public interface ICountDao {
	int getCount(String queryId, Map<String, Object> parameterMap);
}
