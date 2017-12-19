package gov.usgs.cida.wqp.dao.intfc;

import org.apache.ibatis.session.ResultHandler;

import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public interface IStreamingDao {

	/** 
	 * This Dao will stream all of the data for the given type and parameters.
	 * @param nameSpace - the type of data we are looking for.
	 * @param filter - the query/post parameters from the http request
	 * @param handler - the row handler to use for streaming data
	 */
	void stream(NameSpace nameSpace, FilterParameters filter, ResultHandler<?> handler);

}