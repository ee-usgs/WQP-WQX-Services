package gov.usgs.wma.wqp.dao;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.parameter.FilterParameters;

@Component
public class StreamingDao extends BaseDao implements IStreamingDao {

	public static final String QUERY_SELECT_ID = ".select";

	@Autowired
	public StreamingDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	@Override
	public void stream(NameSpace nameSpace, FilterParameters filter, ResultHandler<?> handler) {
		if (null == handler) {
			throw new IllegalArgumentException("A ResultHandler is required for the StreamingDao.stream");
		}
		getSqlSession().select(nameSpace + QUERY_SELECT_ID, filter, handler);
	}

}