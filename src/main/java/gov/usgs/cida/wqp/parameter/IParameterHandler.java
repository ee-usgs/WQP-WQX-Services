package gov.usgs.cida.wqp.parameter;

import java.util.Set;

public interface IParameterHandler {
	ParameterMap validateAndTransform(Object inParameters);
	ParameterMap validateParameterNamesAndGroups(Set<String> inParameterNames);
}