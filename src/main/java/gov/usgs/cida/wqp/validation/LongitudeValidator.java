package gov.usgs.cida.wqp.validation;

import org.apache.log4j.Logger;

import gov.usgs.cida.wqp.parameter.Parameters;

public class LongitudeValidator extends BoundedFloatingPointValidator {
	private final Logger log = Logger.getLogger(getClass());

    public LongitudeValidator(Parameters inParameter)  {
        this(inParameter, String.valueOf(MIN_LONGITUDE), String.valueOf(MAX_LONGITUDE));
    }

    public LongitudeValidator(Parameters inParameter, String inMinBound, String inMaxBound)  {
        this(inParameter, DEFAULT_MIN_OCCURS, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER, inMinBound, inMaxBound);
    }
    
    public LongitudeValidator(Parameters inParameter, int minOccurs, int maxOccurs, String delimiter, String inMinBound, String inMaxBound)  {
        super(inParameter, minOccurs, maxOccurs, delimiter, inMinBound, inMaxBound);
    	log.trace(getClass());
        if (maxBound < MIN_LONGITUDE || maxBound > MAX_LONGITUDE) {
            throw new IllegalArgumentException(MAXBOUND_BETWEEN + MIN_LONGITUDE + AND
                    + MAX_LONGITUDE + INCLUSIVE);
        }

        if (minBound < MIN_LONGITUDE || minBound > MAX_LONGITUDE) {
            throw new IllegalArgumentException(MINBOUND_BETWEEN + MIN_LONGITUDE + AND 
                    + MAX_LONGITUDE + INCLUSIVE);
        }

    }

}
