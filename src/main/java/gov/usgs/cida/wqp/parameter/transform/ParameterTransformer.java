package gov.usgs.cida.wqp.parameter.transform;
import gov.usgs.cida.wqp.validation.ValidationResult;
/**
 *
 * @author duselman
 */
public interface Transformer<T> {
	T transform(String value);
	ValidationResult<T> getValdiationResult();
}