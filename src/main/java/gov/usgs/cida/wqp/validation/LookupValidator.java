package gov.usgs.cida.wqp.validation;


import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;

import org.apache.log4j.Logger;


/**
 *
 * @author duselmann
 */
public class LookupValidator extends AbstractValidator<String[]> {
	private final Logger log = Logger.getLogger(getClass());

	private CodesService codesService = new CodesService();
	private Parameters parameter;

    public LookupValidator(Parameters inParameter)  {
        this(inParameter, DEFAULT_MIN_OCCURS, IN_CLAUSE_LIMIT, DEFAULT_DELIMITER);
    }
    
    public LookupValidator(Parameters parameter, int minOccurs, int maxOccurs, String delimiter)  {
        super(parameter, minOccurs, maxOccurs, delimiter);
        this.parameter=parameter;
    	log.trace(getClass());
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
        	String valid = codesService.fetch(parameter, code);
            if ( ! code.equalsIgnoreCase(valid) ) {
                vr.setValid(false);
                vr.getValidationMessages().add(getErrorMessage(code, "is not in the list of enumerated values"));
            }
        }
        vr.setTransformedValue(strings);
        return vr;
    }

    
	public void setCodesService(CodesService codesService) {
		this.codesService = codesService;
	}

}
