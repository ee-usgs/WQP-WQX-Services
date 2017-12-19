package gov.usgs.cida.wqp.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class WqpDateValidator implements ConstraintValidator<WqpDate, String> {

	private String formatString;

	@Override
	public void initialize(WqpDate constraintAnnotation) {
		this.formatString = constraintAnnotation.formatString();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
		if (StringUtils.isBlank(value)) {
			return true;
		}
		// SimpleDateFormat is *NOT* thread safe, do not attempt to optimize by
		// pulling out to instance scope.
		DateFormat dateFormat = new SimpleDateFormat(formatString);
		try {
			Date date = dateFormat.parse(value);
			//parse will roll over a date like 09-31-2011 to be 10-01-2011,
			//lets make sure the roundtrip matches (by formatting the parsed date)
			//otherwise this will fail hard in ORACLE anyhow.
			if (dateFormat.format(date).equals(value)){
				return true;
			}
		} catch (ParseException e) {
			//Nothing to see here, just return not valid.
		}
		return false;
	}

}
