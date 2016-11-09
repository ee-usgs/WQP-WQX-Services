package gov.usgs.cida.wqp.dao.intfc;

import java.math.BigDecimal;
import java.util.Map;

public interface ILogDao {

	BigDecimal addLog(Map<String, Object> parameterMap);

	void setHeadComplete(Map<String, Object> parameterMap);

	void setFirstRow(Map<String, Object> parameterMap);

	void setRequestComplete(Map<String, Object> parameterMap);

}
