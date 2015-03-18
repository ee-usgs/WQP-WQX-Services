package gov.usgs.cida.wqp.parameter.transform;
import gov.usgs.cida.wqp.util.Transformer;
import gov.usgs.cida.wqp.validation.ValidationResult;
/**
 *
 * @author duselman
 */
public interface ParameterTransformer<T> extends Transformer<T> {
	ValidationResult<T> getValdiationResult();
}