package gov.usgs.cida.wqp.parameter.transform;

import gov.usgs.cida.wqp.validation.ValidationResult;

/**
 *
 * @author duselman
 */
public class NoopTransformer implements Transformer<String> {

    public String transform(String value) {
    	return value;
    }

    @Override
    public ValidationResult<String> getValdiationResult() {
    	return new ValidationResult<String>();
    }
}
