package gov.usgs.cida.wqp.dao;

import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreamingDao extends BaseDao implements IStreamingDao {
	
	@Autowired
	public StreamingDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}
	
	@Override
	public void stream(String nameSpace, Map<String, Object> parameterMap, ResultHandler handler) {
		getSqlSession().select(nameSpace + QUERY_SELECT_ID, parameterMap, handler);
	}

}