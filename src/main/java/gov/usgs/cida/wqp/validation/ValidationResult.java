package gov.usgs.cida.wqp.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ValidationResult {
	private final Logger log = Logger.getLogger(getClass());

	public static final String ERROR_MESSAGE = "must match the format ";

    private boolean valid = true;
    private Object transformedValue;
    private List<String> validationMessages = new ArrayList<String>();

    
    public ValidationResult() {
        log.trace(getClass());
    }
    
    public ValidationResult(boolean isValid, Object inTransformedValue, List<String> inMessages) {
        valid = isValid;
        transformedValue = inTransformedValue;
        validationMessages = inMessages;
    }
    
    public boolean isValid() {
        return valid;
    }

    public void setValid(final boolean inValid) {
        valid = inValid;
    }

    public Object getTransformedValue() {
        return transformedValue;
    }

    public void setTransformedValue(final Object inTransformedValue) {
        transformedValue = inTransformedValue;
    }

    public List<String> getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(final List<String> inValidationMessages) {
        validationMessages = inValidationMessages;
    }
    
    public ValidationResult merge(final ValidationResult inValidationResult) {
        if (!inValidationResult.isValid()) {
            setValid(false);
        }
        if (null != inValidationResult.getTransformedValue()) {
            setTransformedValue(inValidationResult.getTransformedValue());
        }
        if (null != inValidationResult.getValidationMessages()) {
            validationMessages.addAll(inValidationResult.getValidationMessages());
        }
        return this;
    }
        
}
