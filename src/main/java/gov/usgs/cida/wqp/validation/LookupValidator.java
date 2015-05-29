package gov.usgs.cida.wqp.validation;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author duselmann
 */
public class LookupValidator extends AbstractValidator<String[]> {
	private static final Logger LOG = LoggerFactory.getLogger(LookupValidator.class);
	
	private CodesService codesService;
	private Parameters parameter;
	
	public LookupValidator(CodesService inCodesService, Parameters inParameter)  {
		this(inCodesService, inParameter, DEFAULT_MIN_OCCURS, IN_CLAUSE_LIMIT, DEFAULT_DELIMITER);
	}
	
	public LookupValidator(CodesService inCodesService, Parameters parameter, int minOccurs, int maxOccurs, String delimiter)  {
		super(parameter, minOccurs, maxOccurs, delimiter);
		this.parameter=parameter;
		codesService = inCodesService;
		LOG.trace(getClass().getName());
	}
	
	@Override
	public ValidationResult<String[]> validate(final String value) {
		ValidationResult<String[]> vr = new ValidationResult<String[]>();
		String[] strings = transformer.transform(value);
		if (strings.length < getMinOccurs() || strings.length > getMaxOccurs()) {
			vr.setValid(false);
			vr.getValidationMessages().add(getErrorMessage(value, IS_NOT_BETWEEN + getMinOccurs() + " and " + getMaxOccurs() + " occurences."));
		}
		for (String code : strings) {
			try {
				if ( ! codesService.validate(parameter, code) ) {
					vr.setValid(false);
					vr.getValidationMessages().add(getErrorMessage(code, "is not in the list of enumerated values"));
				}
			} catch (WqpException e) {
				vr.setValid(false);
				vr.getValidationMessages().add(getErrorMessage(code, "Server error: We cannot access the list of enumerated values to validate your parameter."));
			}
		}
		vr.setTransformedValue(strings);
		return vr;
	}
	
}