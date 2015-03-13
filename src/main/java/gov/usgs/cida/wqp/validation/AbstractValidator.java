package gov.usgs.cida.wqp.validation;


import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.parameter.transform.NoopTransformer;
import gov.usgs.cida.wqp.parameter.transform.SplitTransformer;
import gov.usgs.cida.wqp.parameter.transform.Transformer;

import org.apache.log4j.Logger;

/**
 *
 * @author tkunicki
 */
public abstract class AbstractValidator<T> implements ValidationConstants {
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

    protected Transformer<T> transformer;
    
    public void setTransformer(Transformer<T> transformer) {
		this.transformer = transformer;
	}
    
    
    protected AbstractValidator(Parameters inParameter) {
        this(inParameter, DEFAULT_MIN_OCCURS, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER);
    }

    @SuppressWarnings("unchecked")
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

        if (delimiter == null || delimiter.length() == 0) {
        	setTransformer( (Transformer<T>) new NoopTransformer() ); // default noop transformer
        } else {
        	setTransformer( (Transformer<T>) new SplitTransformer(delimiter)); // default splitting transformer
        }
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


    protected String getErrorMessage(String parameterValue, String msg) {
        return String.format(BASE_ERROR_MESSAGE_FORMAT, parameter.toString(), parameterValue, msg);
    }
        
    
    public abstract ValidationResult<T> validate(String value);

}
