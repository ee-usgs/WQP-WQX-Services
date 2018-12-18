package gov.usgs.cida.wqp.dao.intfc;

import java.util.Map;

public interface ILogDao {

	Integer addLog(Map<String, Object> parameterMap);

	void setHeadComplete(Map<String, Object> parameterMap);

	void setFirstRow(Map<String, Object> parameterMap);

	void setRequestComplete(Map<String, Object> parameterMap);

}
