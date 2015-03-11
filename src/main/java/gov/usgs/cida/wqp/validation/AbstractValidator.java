package gov.usgs.cida.wqp.validation;


import gov.usgs.cida.wqp.parameter.Parameters;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 *
 * @author tkunicki
 */
public abstract class AbstractValidator implements ValidationConstants {
	private final Logger log = Logger.getLogger(getClass());

	public static final String BASE_ERROR_MESSAGE_FORMAT= "The value of %s=%s %s";
	public static final String IS_NOT_BETWEEN = "is not between ";
	public static final String AND = " and ";
	public static final String MAXBOUND_BETWEEN = "maxBound must be between ";
    public static final String MINBOUND_BETWEEN = "minBound must be between ";
    public static final String INCLUSIVE = " (inclusive)";
    
    protected final Parameters parameter;
    protected final int minOccurs;
    protected final int maxOccurs;
    protected final String delimiter;
    protected final Pattern splitPattern;

    protected AbstractValidator(Parameters inParameter) {
        this(inParameter, DEFAULT_MIN_OCCURS, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER);
    }

    protected AbstractValidator(Parameters inParameter, int inMinOccurs, int inMaxOccurs, String inDelimiter) {
        log.trace(getClass());

        parameter = inParameter;
        minOccurs = inMinOccurs;
        maxOccurs = inMaxOccurs;
        delimiter = inDelimiter;

        if (null == parameter) {
            throw new IllegalArgumentException("The Parameter being validated must be provided.");
        }
        if (minOccurs < 0) {
            throw new IllegalArgumentException("minOccurs must be >= 0.");
        }
        if (maxOccurs < 1) {
            throw new IllegalArgumentException("maxOccurs must be > 0.");
        }
        if (maxOccurs < minOccurs) {
            throw new IllegalArgumentException("maxOccurs must be > minOccurs.");
        }
        if (maxOccurs > 1 && (delimiter == null || 0 == delimiter.length())) {
            throw new IllegalArgumentException("delimiter must be defined if maxOccurs > minOccurs.");
        }

        splitPattern = delimiter == null || delimiter.length() == 0 ? null : Pattern.compile(delimiter);
    }

    public int getMinOccurs() {
        return minOccurs;
    }

    public int getMaxOccurs() {
        return maxOccurs;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String[] split(String value) {
        String[] rtn = null;
        if (value == null) {
            rtn = new String[0];
        } else if (splitPattern == null) {
            rtn = new String[]{value};
        } else {
            rtn =  splitPattern.split(pruneDelimiters(value), -1);
        }
        return rtn;
    }

    protected String getErrorMessage(String parameterValue, String msg) {
        return String.format(BASE_ERROR_MESSAGE_FORMAT, parameter.toString(), parameterValue, msg);
    }
    
    /**
     * Removes consecutive occurrences of the delimiter as well as the leading and trailing delimiter, 
     * if the parameter takes delimiters.
     * 
     * @param parameterValue
     * @return pruned parameterValue 
     */
    public String pruneDelimiters(final String parameterValue) {
        String param = parameterValue;
        if (getDelimiter() != null && getDelimiter().length() > 0) {
            // replace consecutive occurrences of delimiter
            param = param.replaceAll(getDelimiter() + "+",  getDelimiter());
            // replace leading and trailing occurrence of delimiter
            param = param.replaceAll("^" + getDelimiter() + "|" + getDelimiter() + "$", "");
        }
        return param;
    }
    
    
    public abstract ValidationResult validate(String value);

}
