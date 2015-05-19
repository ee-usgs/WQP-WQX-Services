package gov.usgs.cida.wqp.parameter.transform;

import gov.usgs.cida.wqp.validation.ValidationResult;

/**
 *
 * @author duselman
 */
public interface ParameterTransformer<T> {
	ValidationResult<T> getValdiationResult();
	T transform(String value);
}