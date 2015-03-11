package gov.usgs.cida.wqp.count;

import gov.usgs.cida.wqp.count.RowCountDao.RowCounts;

import java.util.List;
import java.util.Map;

/**
 * @author drsteini
 * originally in swsf-ibatis, wqp-core
 */
public interface IRowCountDao {

    String retrieveRowCountStr(String sqlMap, Map<String, Object> queryParams);
    
	List<Integer> retrieveRowCount(String selectId, Map<String, Object> queryParams);
	
	List<RowCounts> retrieveCounts(String selectId, Map<String, Object> queryParams);
    
}
