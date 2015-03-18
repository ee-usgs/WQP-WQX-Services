package gov.usgs.cida.wqp.service;
import java.util.Map;
public interface IWebServiceLogDao {
	/**
	 * Add a new log entry to the database
	 * @param object a map containing the values to insert.
	 */
	void add(Map<String, Object> object);
}