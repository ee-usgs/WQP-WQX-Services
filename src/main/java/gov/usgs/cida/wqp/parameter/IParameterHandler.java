package gov.usgs.cida.wqp.parameter;

import java.util.Map;
import java.util.Set;

public interface IParameterHandler {
	ParameterMap validateAndTransform(Map<String, String[]> inRequestParameters, Map<String, Object> postParms, Object pathVariables);
	ParameterMap validateParameterNamesAndGroups(Set<String> inParameterNames);
}