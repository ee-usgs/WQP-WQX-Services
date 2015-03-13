package gov.usgs.cida.wqp.parameter;

import gov.usgs.cida.wqp.validation.ValidationResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class ParameterMap {
	private final Logger log = Logger.getLogger(getClass());

    private boolean valid = true;

    public ParameterMap() {
        log.trace(getClass());
	}
    
    private Map<String, List<String>> validationMessages = new HashMap<String, List<String>>();

    private Map<String, Object> queryParameters = new HashMap<String, Object>();

    public boolean isValid() {
        return valid;
    }

    public void setValid(final boolean inValid) {
        valid = inValid;
    }

    public Map<String, List<String>> getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(final Map<String, List<String>> inValidationMessages) {
        validationMessages = inValidationMessages;
    }

    public Map<String, Object> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(final Map<String, Object> inQueryParameters) {
        queryParameters = inQueryParameters;
    }

    public void merge(final String inParameterName, final ValidationResult<?> inValidationResult) {
        if (!validationMessages.containsKey(inParameterName)) {
            validationMessages.put(inParameterName, inValidationResult.getValidationMessages());
        } else {
            validationMessages.get(inParameterName).addAll(inValidationResult.getValidationMessages());
        }
        if (null != inValidationResult.getTransformedValue()) {
            queryParameters.put(inParameterName, inValidationResult.getTransformedValue());
        }
        if (!inValidationResult.isValid()) {
            setValid(false);
        }
    }
    
    public void merge(final String inParameterName, final List<String> inValidationMessage) {
        if (!validationMessages.containsKey(inParameterName)) {
            validationMessages.put(inParameterName, inValidationMessage);
        } else {
            validationMessages.get(inParameterName).addAll(inValidationMessage);
        }
    }
    
    public void merge(final String inParameterName, final Object inQueryParameter) {
        if (null != inQueryParameter) {
            queryParameters.put(inParameterName, inQueryParameter);
        }
    }
    
    public void merge(final ParameterMap inParameterMap) {
        if (null != inParameterMap.getValidationMessages() && !inParameterMap.getValidationMessages().isEmpty()) {
            for (Entry<String, List<String>> entry : inParameterMap.getValidationMessages().entrySet()) {
                merge(entry.getKey(), entry.getValue());
            }
        }
        if (null != inParameterMap.getQueryParameters() && !inParameterMap.getQueryParameters().isEmpty()) {
            for (Entry<String, Object> entry : inParameterMap.getQueryParameters().entrySet()) {
                merge(entry.getKey(), entry.getValue());
            }
        }
        if (!inParameterMap.isValid()) {
            setValid(false);
        }
    }
    
}
