package gov.usgs.cida.wqp.parameter;

import gov.usgs.cida.wqp.validation.ValidationResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterMap {
	private static final Logger LOG = LoggerFactory.getLogger(ParameterMap.class);
	
	private boolean valid = true;
	private Map<String, List<String>> validationMessages = new HashMap<String, List<String>>();
	private Map<String, Object> queryParameters = new HashMap<String, Object>();
	
	public ParameterMap() {
		LOG.trace(getClass().getName());
	}
	
	public void merge(final String parameterName, final ValidationResult<?> validationResult) {
		if (!validationMessages.containsKey(parameterName)) {
			validationMessages.put(parameterName, validationResult.getValidationMessages());
		} else {
			validationMessages.get(parameterName).addAll(validationResult.getValidationMessages());
		}
		if (null != validationResult.getTransformedValue()) {
			//We need to convert the actual parameter name sent to MyBatis because it tries to interpret
			//"command.avoid" as the avoid property of the command object. This parameter should be considered 
			//deprecated and is being replaced with the providers parameter.  
			queryParameters.put(parameterName.replace(".", ""), validationResult.getTransformedValue());
		}
		if (!validationResult.isValid()) {
			setValid(false);
		}
	}
	
	public void merge(final String parameterName, final List<String> validationMessage) {
		if (!validationMessages.containsKey(parameterName)) {
			validationMessages.put(parameterName, validationMessage);
		} else {
			validationMessages.get(parameterName).addAll(validationMessage);
		}
	}
	
	public void merge(final String parameterName, final Object queryParameter) {
		if (null != queryParameter) {
			queryParameters.put(parameterName, queryParameter);
		}
	}
	
	public void merge(final ParameterMap parameterMap) {
		if (null != parameterMap.getValidationMessages() && !parameterMap.getValidationMessages().isEmpty()) {
			for (Entry<String, List<String>> entry : parameterMap.getValidationMessages().entrySet()) {
				merge(entry.getKey(), entry.getValue());
			}
		}
		if (null != parameterMap.getQueryParameters() && !parameterMap.getQueryParameters().isEmpty()) {
			for (Entry<String, Object> entry : parameterMap.getQueryParameters().entrySet()) {
				merge(entry.getKey(), entry.getValue());
			}
		}
		if (!parameterMap.isValid()) {
			setValid(false);
		}
	}

	public boolean isValid() {
		return valid;
	}
	public void setValid(final boolean inValid) {
		valid = inValid;
	}
	public Map<String, List<String>> getValidationMessages() {
		return validationMessages;
	}
	public void setValidationMessages(final Map<String, List<String>> validationMessages) {
		this.validationMessages = validationMessages;
	}
	public Map<String, Object> getQueryParameters() {
		return queryParameters;
	}
	public void setQueryParameters(final Map<String, Object> queryParameters) {
		this.queryParameters = queryParameters;
	}
	
	public String getParameter(Parameters param) {
		if (param == null) {
			return null; // TODO should this be the empty string?
		}
		return (String) getQueryParameters().get(param.toString());		
	}
	
}