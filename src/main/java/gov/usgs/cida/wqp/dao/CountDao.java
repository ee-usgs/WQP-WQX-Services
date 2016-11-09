package gov.usgs.cida.wqp.dao;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		return getSqlSession().selectList(nameSpace + QUERY_SELECT_ID, parameterMap);
	}

}
