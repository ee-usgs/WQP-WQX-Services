package gov.usgs.cida.wqp.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.usgs.cida.wqp.parameter.Parameters;


/**
 *
 * @author tkunicki
 */
public class LatitudeValidator extends BoundedFloatingPointValidator {
	private final Logger log = LoggerFactory.getLogger(getClass());

    public LatitudeValidator(Parameters inParameter)  {
        this(inParameter, String.valueOf(MIN_LATITUDE), String.valueOf(MAX_LATITUDE));
    }

    public LatitudeValidator(Parameters inParameter, String inMinBound, String inMaxBound)  {
        this(inParameter, DEFAULT_MIN_OCCURS, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER, inMinBound, inMaxBound);
    }
    
    public LatitudeValidator(Parameters inParameter, int minOccurs, int maxOccurs, String delimiter, String inMinBound, String inMaxBound)  {
        super(inParameter, minOccurs, maxOccurs, delimiter, inMinBound, inMaxBound);
    	log.trace(getClass().getName());
        if (maxBound < MIN_LATITUDE || maxBound > MAX_LATITUDE) {
            throw new IllegalArgumentException(MAXBOUND_BETWEEN + MIN_LATITUDE + AND
                    + MAX_LATITUDE + INCLUSIVE);
        }

        if (minBound < MIN_LATITUDE || minBound > MAX_LATITUDE) {
            throw new IllegalArgumentException(MINBOUND_BETWEEN + MIN_LATITUDE + AND 
                    + MAX_LATITUDE + INCLUSIVE);
        }

    }

}
