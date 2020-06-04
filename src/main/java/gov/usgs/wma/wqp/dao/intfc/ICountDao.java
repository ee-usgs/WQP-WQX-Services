package gov.usgs.wma.wqp.dao.intfc;

import java.util.List;
import java.util.Map;

import gov.usgs.wma.wqp.dao.NameSpace;
import gov.usgs.wma.wqp.parameter.FilterParameters;

public interface ICountDao {

	/** 
	 * This Dao will grab all of the record counts for the given type and parameters.
	 * @param nameSpace - the type of counts we are looking for.
	 * @param filter - the query/post parameters from the http request
	 * @return - the list of counts.
	 */
	List<Map<String, Object>> getCounts(NameSpace nameSpace, FilterParameters filter);

}