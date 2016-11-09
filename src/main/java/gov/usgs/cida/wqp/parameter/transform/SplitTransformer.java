package gov.usgs.cida.wqp.parameter.transform;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.usgs.cida.wqp.validation.AbstractValidator;
import gov.usgs.cida.wqp.validation.ValidationResult;

public class SplitTransformer implements ParameterTransformer<String[]> {
	private static final Logger LOG = LoggerFactory.getLogger(SplitTransformer.class);

	private final Pattern splitPattern;
	private final String delimiter;

	public SplitTransformer() {
		this(AbstractValidator.DEFAULT_DELIMITER);
	}

	public SplitTransformer(String delimiter) {
		LOG.trace(getClass().getName());
		this.delimiter = delimiter;
		if (null == delimiter || 0 == delimiter.length()) {
			throw new IllegalArgumentException("A Delimiter must be provided.");
		} else {
			splitPattern = Pattern.compile(delimiter);
		}
	}

	@Override
	public String[] transform(String value) {
		return split(value);
	}

	public String[] split(String value) {
		String[] rtn = null;
		if (value == null || 0 == value.length()) {
			rtn = new String[0];
		} else if (splitPattern == null) {
			rtn = new String[]{value};
		} else {
			rtn =  splitPattern.split(pruneDelimiters(value), -1);
		}
		return rtn;
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
		if (delimiter != null && delimiter.length() > 0) {
			// replace consecutive occurrences of delimiter
			param = param.replaceAll(delimiter + "+",  delimiter);
			// replace leading and trailing occurrence of delimiter
			param = param.replaceAll("^" + delimiter + "|" + delimiter + "$", "");
		}
		return param;
	}

	@Override
	public ValidationResult<String[]> getValdiationResult() {
		return new ValidationResult<String[]>();
	}
}