package gov.usgs.cida.wqp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.parameter.Parameters;

@Component
public class CountDao extends BaseDao implements ICountDao {
	private static final Logger LOG = LoggerFactory.getLogger(CountDao.class);

	public static final String QUERY_SELECT_ID = ".count";

	@Autowired
	public CountDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	@Override
	public List<Map<String, Object>> getCounts(String nameSpace, Map<String, Object> parameterMap) {
		LOG.trace("Getting counts for: {}", nameSpace);
		return getSqlSession().selectList(nameSpace + QUERY_SELECT_ID, adjustMinCount(nameSpace, parameterMap));
	}

	protected Map<String, Object> adjustMinCount(String nameSpace, Map<String, Object> parameterMap) {
		//We need to set a minimum record count so sites without activities/results are excluded from counts for those end points.
		Map<String, Object> adjustedParameterMap = new HashMap<>();
		if (null != parameterMap) {
			adjustedParameterMap.putAll(parameterMap);
		}
		switch (nameSpace) {
		case BaseDao.ACTIVITY_NAMESPACE:
			if (!adjustedParameterMap.containsKey(Parameters.MIN_ACTIVITIES.toString())) {
				adjustedParameterMap.put(Parameters.MIN_ACTIVITIES.toString(), new String[]{"1"});
			}
			break;
		case BaseDao.BIOLOGICAL_RESULT_NAMESPACE:
		case BaseDao.RESULT_NAMESPACE:
			if (!adjustedParameterMap.containsKey(Parameters.MIN_RESULTS.toString())) {
				adjustedParameterMap.put(Parameters.MIN_RESULTS.toString(), new String[]{"1"});
			}
			break;
		default:
			break;
		}
		return adjustedParameterMap;
	}
}
