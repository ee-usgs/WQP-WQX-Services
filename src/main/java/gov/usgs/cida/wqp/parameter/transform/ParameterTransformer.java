package gov.usgs.cida.wqp.parameter.transform;

import gov.usgs.cida.wqp.validation.ValidationResult;

public interface ParameterTransformer<T> {
	ValidationResult<T> getValdiationResult();
	T transform(String value);
}