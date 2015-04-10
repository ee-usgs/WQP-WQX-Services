package gov.usgs.cida.wqp.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseDao extends SqlSessionDaoSupport {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	public BaseDao(SqlSessionFactory sqlSessionFactory) {
		log.trace(getClass().getName());
		setSqlSessionFactory(sqlSessionFactory);
	}

}
