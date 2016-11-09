package gov.usgs.cida.wqp.dao.intfc;

import java.util.Map;

import org.apache.ibatis.session.ResultHandler;

public interface IStreamingDao {

	/** 
	 * This Dao will stream all of the data for the given type and parameters.
	 * @param nameSpace - the type of data we are looking for.
	 * @param parameterMap - the map of query parameters from the http request
	 * @param handler - the row handler to use for streaming data
	 */
	void stream(String nameSpace, Map<String, Object> parameterMap, ResultHandler<?> handler);

}