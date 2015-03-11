package gov.usgs.cida.wqp.validation;

import org.apache.log4j.Logger;

import gov.usgs.cida.wqp.parameter.Parameters;


/**
 *
 * @author tkunicki
 */
public class BoundedFloatingPointValidator extends AbstractValidator {
	private final Logger log = Logger.getLogger(getClass());

    protected final double minBound;
    protected final double maxBound;
    protected ValidationResult vr = new ValidationResult();

    public BoundedFloatingPointValidator(Parameters inParameter)  {
        super(inParameter);
    	log.trace(getClass());
        minBound = -Double.MAX_VALUE;
        maxBound = Double.MAX_VALUE;
    }
    
    public BoundedFloatingPointValidator(Parameters inParameter, String inMinBound, String inMaxBound)  {
        this(inParameter, DEFAULT_MIN_OCCURS, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER, inMinBound, inMaxBound);
    }
    
    public BoundedFloatingPointValidator(Parameters inParameter, int minOccurs, int maxOccurs, String delimiter, String inMinBound, String inMaxBound)  {
        super(inParameter, minOccurs, maxOccurs, delimiter);
    	log.trace(getClass());
        try {
            minBound = Double.parseDouble(inMinBound);
        } catch (Exception e) {
            throw new IllegalArgumentException("minBound is not a valid number.", e);
        }
        try {
            maxBound = Double.parseDouble(inMaxBound);
        } catch (Exception e) {
            throw new IllegalArgumentException("maxBound is not a valid number.", e);
        }
        if (maxBound < minBound) {
            throw new IllegalArgumentException("minBound must be less than maxBound.");
        }
    }

    @Override
    public ValidationResult validate(String value) {
        vr = new ValidationResult();
        double[] doubles = stringAsDoubles(value);
        if (doubles.length < getMinOccurs() || doubles.length > getMaxOccurs()) {
            vr.setValid(false);
            vr.getValidationMessages().add(getErrorMessage(value, IS_NOT_BETWEEN + getMinOccurs() + AND + getMaxOccurs() + " occurances."));
        }
        for (double d : doubles) {
            if (d < minBound || d > maxBound) {
                vr.setValid(false);
                vr.getValidationMessages().add(getErrorMessage(String.valueOf(d), IS_NOT_BETWEEN + minBound + AND + maxBound));
            }
        }
        vr.setTransformedValue(doubles);
        return vr;
    }

    protected double[] stringAsDoubles(String value) {
        String[] strings = split(value);
        if (strings == null) {
            //This will not happen unless split(value) is changed. 
            return new double[0];
        }
        double[] doubles = new double[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            if (strings[i].equals(strings[i].trim())) {
                try {
                    doubles[i] = Double.parseDouble(strings[i]);
                } catch (NumberFormatException e) {
                    vr.setValid(false);
                    vr.getValidationMessages().add(getErrorMessage(strings[i], "is not a valid number."));
                    doubles[i] = Double.NaN;
                }
            } else {
                vr.setValid(false);
                vr.getValidationMessages().add(getErrorMessage(strings[i], "has leading or trailing whitespace."));
                doubles[i] = Double.NaN;
            }
        }
        return doubles;
    }

}
