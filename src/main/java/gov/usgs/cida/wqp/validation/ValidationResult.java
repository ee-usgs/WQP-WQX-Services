package gov.usgs.cida.wqp.validation;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ValidationResult<T> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	public static final String ERROR_MESSAGE = "must match the format ";
	private boolean valid = true;
	private T transformedValue;
	private Object[] rawValue;
	private List<String> validationMessages = new ArrayList<String>();
	public ValidationResult() {
		log.trace(getClass().getName());
	}
	public ValidationResult(boolean isValid, T transformedValue, List<String> inMessages) {
		valid = isValid;
		this.transformedValue = transformedValue;
		validationMessages = inMessages;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(final boolean inValid) {
		valid = inValid;
	}
	public T getTransformedValue() {
		return transformedValue;
	}
	public void setTransformedValue(final T transformedValue) {
		this.transformedValue = transformedValue;
	}
	public void setRawValue(Object[] rawValue) {
		this.rawValue = rawValue;
	}
	public Object[] getRawValue() {
		return rawValue;
	}
	public List<String> getValidationMessages() {
		return validationMessages;
	}
	public void setValidationMessages(final List<String> inValidationMessages) {
		validationMessages = inValidationMessages;
	}
	public ValidationResult<T> merge(final ValidationResult<T> validationResult) {
		if ( ! validationResult.isValid() ) {
			setValid(false);
		}
		if (null != validationResult.getTransformedValue()) {
			setTransformedValue(validationResult.getTransformedValue());
		}
		if (null != validationResult.getValidationMessages()) {
			validationMessages.addAll(validationResult.getValidationMessages());
		}
		return this;
	}
}