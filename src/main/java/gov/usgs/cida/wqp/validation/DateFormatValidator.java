package gov.usgs.cida.wqp.validation;
import gov.usgs.cida.wqp.parameter.Parameters;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author tkunicki
 */
public class DateFormatValidator extends AbstractValidator<String[]> {
	private static final Logger LOG = LoggerFactory.getLogger(DateFormatValidator.class);
	
	protected final String formatString;
	protected static final String MUST_BE_VALID = "must be a valid date in the format ";
	
	public DateFormatValidator(Parameters inParameter, String inFormatString)  {
		this(inParameter, DEFAULT_MIN_OCCURS, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER, inFormatString);
	}
	
	public DateFormatValidator(Parameters inParameter, int minOccurs, int maxOccurs, String delimiter, String inFormatString)  {
		super(inParameter, minOccurs, maxOccurs, delimiter);
		LOG.trace(getClass().getName());
		if (null == inFormatString || 0 == inFormatString.length()) {
			throw new IllegalArgumentException("The Format String must be provided.");
		}
		formatString = inFormatString;
	}
	
	@Override
	public ValidationResult<String[]> validate(String value) {
		ValidationResult<String[]> vr = new ValidationResult<String[]>();
		String[] strings = transformer.transform(value);
		if (strings.length < getMinOccurs() || strings.length > getMaxOccurs()) {
			vr.setValid(false);
			vr.getValidationMessages().add(getErrorMessage(value, IS_NOT_BETWEEN + getMinOccurs() + AND + getMaxOccurs() + " occurances."));
		}
		// SimpleDateFormat is *NOT* thread safe, do not attempt to optimize by
		// pulling out to instance scope.
		DateFormat dateFormat = new SimpleDateFormat(formatString);
		for (String s : strings) {
			try {
				Date date = dateFormat.parse(s);
				//parse will roll over a date like 09-31-2011 to be 10-01-2011,
				//lets make sure the roundtrip matches (by formatting the parsed date)
				//otherwise this will fail hard in ORACLE anyhow.
				if (!dateFormat.format(date).equals(s)){
					vr.setValid(false);
					vr.getValidationMessages().add(getErrorMessage(s, MUST_BE_VALID + formatString));
				}
			} catch (ParseException e) {
				vr.setValid(false);
				vr.getValidationMessages().add(getErrorMessage(s, MUST_BE_VALID + formatString));
			}
		}
		vr.setTransformedValue(strings);
		return vr;
	}
}