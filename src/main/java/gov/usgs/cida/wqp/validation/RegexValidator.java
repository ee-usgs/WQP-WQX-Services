package gov.usgs.cida.wqp.validation;


import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.parameter.transform.ITransformer;
import gov.usgs.cida.wqp.parameter.transform.ParameterTransformer;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 *
 * @author tkunicki
 */
public class RegexValidator extends AbstractValidator {
	private final Logger log = Logger.getLogger(getClass());

    public static final String ERROR_MESSAGE = "must match the format ";

    private String regex;
    private Pattern validatePattern;

    
    public RegexValidator(Parameters inParameter, String inRegex)  {
        this(inParameter, DEFAULT_MIN_OCCURS, IN_CLAUSE_LIMIT, DEFAULT_DELIMITER, inRegex);
    }

    public RegexValidator(Parameters inParameter, int minOccurs, int maxOccurs, String delimiter, String inRegex)  {
        super(inParameter, minOccurs, maxOccurs, delimiter);
    	log.trace(getClass());

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
    public ValidationResult validate(String value) {
        ValidationResult vr = new ValidationResult();
        if (value != null && value.length() > 0) {
            if (!validatePattern.matcher(value).matches()) {
                vr.setValid(false);
                vr.getValidationMessages().add(getErrorMessage(value, ERROR_MESSAGE + regex));
            }
        } else {
            if (0 != minOccurs) {
                vr.setValid(false);
                vr.getValidationMessages().add(getErrorMessage(value, ERROR_MESSAGE + regex));
            }
        }
        if (vr.isValid()) {
            ITransformer transformer = ParameterTransformer.getTransformer(parameter);
            if (null == transformer) {
                transformer = ParameterTransformer.getDefaultTransformer();
            }
            vr.setTransformedValue(transformer.transform(value));
        } else {
            vr.setTransformedValue(new String[]{value});
        }
        return vr;
    }

}
