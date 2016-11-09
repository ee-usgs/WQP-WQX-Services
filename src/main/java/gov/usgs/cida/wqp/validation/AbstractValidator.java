package gov.usgs.cida.wqp.validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.parameter.transform.NoopTransformer;
import gov.usgs.cida.wqp.parameter.transform.ParameterTransformer;
import gov.usgs.cida.wqp.parameter.transform.SplitTransformer;

public abstract class AbstractValidator<T> {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractValidator.class);
	
	public static final int UNBOUNDED = Integer.MAX_VALUE;
	public static final int IN_CLAUSE_LIMIT = 1000;
	public static final String DEFAULT_DELIMITER = ";"; // parameter delimiter
	public static final int DEFAULT_MIN_OCCURS = 0;
	public static final int DEFAULT_MAX_OCCURS = 1;

	public static final double MAX_LATITUDE = 90;
	public static final double MIN_LATITUDE = -90;
	public static final double MAX_LONGITUDE = 180;
	public static final double MIN_LONGITUDE = -180;

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
	protected ParameterTransformer<T> transformer;

	public void setTransformer(ParameterTransformer<T> transformer) {
		this.transformer = transformer;
	}

	protected AbstractValidator(Parameters inParameter) {
		this(inParameter, DEFAULT_MIN_OCCURS, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER);
	}

	@SuppressWarnings("unchecked")
	protected AbstractValidator(Parameters inParameter, int inMinOccurs, int inMaxOccurs, String inDelimiter) {
		LOG.trace(getClass().getName());
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
			 // default noop transformer
			setTransformer( (ParameterTransformer<T>) new NoopTransformer() );
		} else {
			 // default splitting transformer
			setTransformer( (ParameterTransformer<T>) new SplitTransformer(delimiter));
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