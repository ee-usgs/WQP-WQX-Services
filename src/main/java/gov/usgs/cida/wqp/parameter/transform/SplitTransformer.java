package gov.usgs.cida.wqp.parameter.transform;
import gov.usgs.cida.wqp.validation.ValidationConstants;
import gov.usgs.cida.wqp.validation.ValidationResult;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author tkunicki
 */
public class SplitTransformer implements ParameterTransformer<String[]>, ValidationConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final Pattern splitPattern;
	private final String delimiter;
	public SplitTransformer() {
		this(DEFAULT_DELIMITER);
	}
	public SplitTransformer(String delimiter) {
		log.trace(getClass().getName());
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
//	protected String[] split(String value) {
//		if (null == value || 0 == value.length()) {
//			return new String[0];
//		} else {
//			return splitPattern.split(value, -1);
//		}
//	}
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