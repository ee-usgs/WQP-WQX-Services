package gov.usgs.cida.wqp.validation;
import gov.usgs.cida.wqp.parameter.Parameters;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author duselman
 */
public class RegexValidator<T> extends AbstractValidator<T> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public static final String ERROR_MESSAGE = "must match the format ";
	private String regex;
	private Pattern validatePattern;
	
	public RegexValidator(Parameters inParameter, String inRegex)  {
		this(inParameter, DEFAULT_MIN_OCCURS, IN_CLAUSE_LIMIT, DEFAULT_DELIMITER, inRegex);
	}
	
	public RegexValidator(Parameters inParameter, int minOccurs, int maxOccurs, String delimiter, String inRegex)  {
		super(inParameter, minOccurs, maxOccurs, delimiter);
		log.trace(getClass().getName());
		if (null == inRegex || 0 == inRegex.length()) {
			throw new IllegalArgumentException("The REGEX must be provided.");
		}
		regex = inRegex;
		StringBuilder validateRegExBuilder = new StringBuilder();
		if (delimiter != null) {
			// Modify the regular expression to deal with the delimiter.
			//NOTE that special regex characters are not escaped and will cause bad results if used!!!!
			validateRegExBuilder.append("(?:");
			validateRegExBuilder.append("(?:^|(?<!^)").append(delimiter).append(")");
			validateRegExBuilder.append("(?:").append(regex).append(")");
			validateRegExBuilder.append("(?=(?<!").append(delimiter).append(")$|").append(delimiter).append("(?!$))");
			validateRegExBuilder.append(")");
			// Add further constraints on the regular expression corresponding to max- and minOccurs
			validateRegExBuilder.append("{").append(minOccurs);
			if (maxOccurs != minOccurs) {
				validateRegExBuilder.append(",");
				if (maxOccurs != UNBOUNDED) {
					validateRegExBuilder.append(maxOccurs);
				}
			}
			validateRegExBuilder.append("}");
		} else {
			// If no delimiter, make sure the regular expression matches
			// ONLY the regex, nothing more, nothing less.
			validateRegExBuilder.append("^").append(regex).append("$");
		}
		validatePattern = Pattern.compile(validateRegExBuilder.toString());
	}
	
	public String getValidatePattern() {
		return validatePattern.toString();
	}
	
	@Override
	public ValidationResult<T> validate(String value) {
		ValidationResult<T> vr = new ValidationResult<T>();
		if (value != null && value.length() > 0) {
			if ( ! validatePattern.matcher(value).matches() ) {
				vr.setValid(false);
				vr.getValidationMessages().add(getErrorMessage(value, ERROR_MESSAGE + regex));
			}
		} else {
			if (0 != minOccurs) {
				vr.setValid(false);
				vr.getValidationMessages().add(getErrorMessage(value, ERROR_MESSAGE + regex));
			}
		}
		if ( vr.isValid() ) {
			vr.setTransformedValue( transformer.transform(value) );
		} else {
			vr.setRawValue(new String[]{value});
		}
		return vr;
	}
}