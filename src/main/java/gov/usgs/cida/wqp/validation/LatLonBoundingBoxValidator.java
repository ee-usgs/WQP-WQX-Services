package gov.usgs.cida.wqp.validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.usgs.cida.wqp.parameter.Parameters;
/**
 *
 * @author tkunicki
 */
public class LatLonBoundingBoxValidator extends AbstractValidator<String[]> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public static final String ERROR_MESSAGE = "is not a valid bounding box.";
	
	public LatLonBoundingBoxValidator(Parameters inParameter) {
		super(inParameter, 0, 1, ",");
		log.trace(getClass().getName());
	}
	
	@Override
	public ValidationResult<String[]> validate(String value) {
		ValidationResult<String[]> vr = new ValidationResult<String[]>();
		if (value == null || value.length() == 0) {
			if (getMinOccurs() != 0) {
				//This won't happen unless the hard-coded constructor is changed...
				vr.setValid(false);
				vr.getValidationMessages().add(getErrorMessage(value, IS_NOT_BETWEEN + getMinOccurs() + AND + getMaxOccurs() + " occurances."));
				vr.setTransformedValue(new String[]{value});
			}
		} else {
			String[] strings = transformer.transform(value);
			if (!isValid(strings)) {
				vr.setValid(false);
				vr.getValidationMessages().add(getErrorMessage(value, ERROR_MESSAGE));
			}
			vr.setTransformedValue(strings);
		}
		return vr;
	}
	
	protected boolean isValid(String[] strings) {
		double[] doubles = new double[4];
		boolean valid = true;
		if (strings.length == 4) {
			// convert strings to doubles
			for (int i = 0; i < 4 && valid; ++i) {
				if (strings[i].equals(strings[i].trim())) {
					try {
						doubles[i] = Double.parseDouble(strings[i]);
						valid = !Double.isInfinite(doubles[i]) && !Double.isNaN(doubles[i]);
					} catch (NumberFormatException e) {
						valid = false;
					}
				} else {
					valid = false;
				}
			}
		} else {
			valid = false;
		}
		// check range on doubles, assume (lon, lat)
		if (valid) {
			boolean latsValid = doubles[1] >= MIN_LATITUDE
					&& doubles[1] <= MAX_LATITUDE
					&& doubles[3] >= MIN_LATITUDE
					&& doubles[3] <= MAX_LATITUDE;
			boolean longsValid = doubles[0] >= MIN_LONGITUDE
					&& doubles[0] <= MAX_LONGITUDE
					&& doubles[2] >= MIN_LONGITUDE
					&& doubles[2] <= MAX_LONGITUDE;
			boolean crossCheckValid = doubles[0] <= doubles[2]
					&& doubles[1] <= doubles[3];
			valid = latsValid && longsValid && crossCheckValid;
		}
		return valid;
	}
}