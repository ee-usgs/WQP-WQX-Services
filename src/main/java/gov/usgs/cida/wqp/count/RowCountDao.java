package gov.usgs.cida.wqp.count;

import gov.usgs.cida.wqp.util.SqlSessionContextObjectFactory;


/**
 * @author drsteini
 *
 */
//    /** 
//     * {@inheritDoc}
//     * @see gov.usgs.cida.qw.dao.intfc.IRowCountDao#retrieveRowCount(java.lang.String, java.util.Map)
//     */
//    @Override
//    public String retrieveRowCount(String sqlMap, Map<String, Object> queryParams) {
//        return String.valueOf(getSqlSession().selectOne(sqlMap, queryParams));
//    }
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RowCountDao implements IRowCountDao {

	public static class RowCounts {
		List<Integer> counts = new ArrayList<Integer>();
		public void setValue(Integer value) {
			counts.add(value);
		}
		public List<Integer> getCounts() {
			return counts;
		}
		
	}

	@Autowired
	private SqlSessionFactory mybatisSessionFactory;
//	@Autowired
//	private SqlSessionContextObjectFactory sqlSessionContextObjectFactory;
	
	public RowCountDao(SqlSessionFactory sqlSessionFactory) {
		this.mybatisSessionFactory = sqlSessionFactory;
	}	
//	public RowCountDao(SqlSessionFactory sqlSessionFactory) {
//		this.sqlSessionFactory = sqlSessionFactory;
//		
//		Configuration configuration = sqlSessionFactory.getConfiguration();
//		configuration.setAutoMappingBehavior(AutoMappingBehavior.NONE);
//		ObjectFactory objectFactory = configuration.getObjectFactory();
//		if (objectFactory instanceof SqlSessionContextObjectFactory) {
//			sqlSessionContextObjectFactory = (SqlSessionContextObjectFactory)objectFactory;
//		} else {
//			sqlSessionContextObjectFactory = new SqlSessionContextObjectFactory(objectFactory);	
//			configuration.setObjectFactory(sqlSessionContextObjectFactory);
//		}
//	}

	@Override
	public List<Integer> retrieveRowCount(String selectId, Map<String, Object> queryParams) {
		SqlSession sqlSession = null;
		try {
			sqlSession = mybatisSessionFactory.openSession();
			return ((RowCounts) sqlSession.selectOne(selectId, queryParams)).getCounts();
		} catch(Exception e){
			throw new RuntimeException("issue getting the row count", e);
		} finally {
//			sqlSessionContextObjectFactory.cleanupContext();
			if (sqlSession != null) {
				try {
					sqlSession.close();
				} catch (Exception e) { /* clean up, don't care */ }
			}
		}
	}
	
	
	@Override
	public List<RowCounts> retrieveCounts(String selectId, Map<String, Object> queryParams) {
		SqlSession sqlSession = null;
		try {
			sqlSession = mybatisSessionFactory.openSession();
			return  sqlSession.selectList(selectId, queryParams);
		} catch(Exception e){
			throw new RuntimeException("issue getting counts", e);
		} finally {
//			sqlSessionContextObjectFactory.cleanupContext();
			if (sqlSession != null) {
				try {
					sqlSession.close();
				} catch (Exception e) { /* clean up, don't care */ }
			}
		}
	}
	
	@Override
	public String retrieveRowCountStr(String sqlMap, Map<String, Object> queryParams) {
		SqlSession sqlSession = null;
		try {
			sqlSession = mybatisSessionFactory.openSession();
			return String.valueOf(sqlSession.selectOne(sqlMap, queryParams));
		} catch (Exception e) {
			return "";
		}
	}
}
