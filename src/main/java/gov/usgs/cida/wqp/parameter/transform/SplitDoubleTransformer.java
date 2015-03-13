package gov.usgs.cida.wqp.parameter.transform;


import gov.usgs.cida.wqp.validation.ValidationConstants;
import gov.usgs.cida.wqp.validation.ValidationResult;

import org.apache.log4j.Logger;



/**
 *
 * @author tkunicki
 */
public class SplitDoubleTransformer implements Transformer<double[]>, ValidationConstants {
	private final Logger log = Logger.getLogger(getClass());

	private SplitTransformer splitter;
	private ValidationResult<double[]> vr;


    public SplitDoubleTransformer() {
    	this(DEFAULT_DELIMITER);
    }
    
    public SplitDoubleTransformer(String delimiter) {
        log.trace(getClass());
        
        if (null == delimiter || 0 == delimiter.length()) {
            throw new IllegalArgumentException("A Delimiter must be provided.");
        }
        splitter = new SplitTransformer(delimiter);
    }

    @Override
    public double[] transform(String value) {
        return stringAsDoubles(value);
    }

    
    protected double[] stringAsDoubles(String value) {
        String[] strings = splitter.transform(value);
        if (strings == null) {
            //This will not happen unless split(value) is changed. 
            return new double[0];
        }
        vr = new ValidationResult<double[]>();
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
    
    protected String getErrorMessage(String parameterValue, String msg) {
        return String.format("The value of PARAM=%s %s", parameterValue, msg);
    }

    
    @Override
    public ValidationResult<double[]> getValdiationResult() {
    	return vr;
    }
}
