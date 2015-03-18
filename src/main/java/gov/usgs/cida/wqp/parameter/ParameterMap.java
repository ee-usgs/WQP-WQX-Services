package gov.usgs.cida.wqp.parameter;
import gov.usgs.cida.wqp.validation.ValidationResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ParameterMap {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private boolean valid = true;
	public ParameterMap() {
		log.trace(getClass().getName());
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
	public void setValidationMessages(final Map<String, List<String>> validationMessages) {
		this.validationMessages = validationMessages;
	}
	public Map<String, Object> getQueryParameters() {
		return queryParameters;
	}
	public void setQueryParameters(final Map<String, Object> queryParameters) {
		this.queryParameters = queryParameters;
	}
	public void merge(final String parameterName, final ValidationResult<?> validationResult) {
		if (!validationMessages.containsKey(parameterName)) {
			validationMessages.put(parameterName, validationResult.getValidationMessages());
		} else {
			validationMessages.get(parameterName).addAll(validationResult.getValidationMessages());
		}
		if (null != validationResult.getTransformedValue()) {
			queryParameters.put(parameterName, validationResult.getTransformedValue());
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
}