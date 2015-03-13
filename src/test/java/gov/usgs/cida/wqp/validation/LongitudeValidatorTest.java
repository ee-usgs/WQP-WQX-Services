package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.Parameters;

import org.junit.Test;

public class LongitudeValidatorTest extends BaseSpringTest implements ValidationConstants {
    
    @Test
    public void testConstructor_minTooLarge() {
        try {
            new LongitudeValidator(Parameters.LONGITUDE, "500", "-80");
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            //Used to be "minBound must be between -180.0 and 180.0 (inclusive)", but the BoundedFloatingPointValidator was changed to 
            //compare the minBound to maxBound...
            assertEquals("minBound must be less than maxBound.", e.getMessage());
        }
    }
    @Test
    public void testConstructor_minTooSmall() {
        try {
            new LongitudeValidator(Parameters.LONGITUDE, "-500", "-80");
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            assertEquals("minBound must be between -180.0 and 180.0 (inclusive)", e.getMessage());
        }
    }
    @Test
    public void testConstructor_maxTooLarge() {
        try {
            new LongitudeValidator(Parameters.LONGITUDE, "-80", "500");
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            assertEquals("maxBound must be between -180.0 and 180.0 (inclusive)", e.getMessage());
        }
    }
    @Test
    public void testConstructor_maxTooSmall() {
        try {
            new LongitudeValidator(Parameters.LONGITUDE, "-80", "-500");
        } catch (Exception e) {
            assertEquals(e.getClass(), IllegalArgumentException.class);
            //Used to be "maxBound must be between -180.0 and 180.0 (inclusive)", but the BoundedFloatingPointValidator was changed to 
            //compare the minBound to maxBound...
            assertEquals("minBound must be less than maxBound.", e.getMessage());
        }
    }

    @Test
    public void testLat_defualts() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.LONGITUDE);
        assertNotNull(validator);
        assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
        assertEquals(DEFAULT_MAX_OCCURS, validator.getMaxOccurs());
        assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
    }        
    @Test
    public void testLat_validPoisitveValue() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.LONGITUDE);
        ValidationResult<?> vr = validator.validate("88");
        assertTrue(vr.isValid());
        assertEquals(0, vr.getValidationMessages().size());
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = (double[])vr.getTransformedValue();
        assertEquals(1, val.length);
        assertEquals(88.0, val[0], .01);

    }        
    @Test
    public void testLat_validNegativeValue() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.LONGITUDE);
        ValidationResult<?> vr = validator.validate("-88");
        assertTrue(vr.isValid());
        assertEquals(0, vr.getValidationMessages().size());
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = (double[])vr.getTransformedValue();
        assertEquals(1, val.length);
        assertEquals(-88.0, val[0], .01);

    }        
    @Test
    public void testLat_valueTooSmall() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.LONGITUDE);
        ValidationResult<?> vr = validator.validate("-200");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of long=-200.0 is not between -180.0 and 180.0", vr.getValidationMessages().get(0));
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = (double[])vr.getTransformedValue();
        assertEquals(1, val.length);
        assertEquals(-200.0, val[0], .01);

    }        
    @Test
    public void testLat_valueTooLarge() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.LONGITUDE);
        ValidationResult<?> vr = validator.validate("200");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of long=200.0 is not between -180.0 and 180.0", vr.getValidationMessages().get(0));
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = (double[])vr.getTransformedValue();
        assertEquals(1, val.length);
        assertEquals(200.0, val[0], .01);

    }        
    @Test
    public void testLat_tooManyValues() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.LONGITUDE);
        ValidationResult<?> vr = validator.validate("-88;CV");
        assertFalse(vr.isValid());
        assertEquals(2, vr.getValidationMessages().size());
        assertEquals("The value of long=-88;CV is not between 0 and 1 occurances.", vr.getValidationMessages().get(0));
        assertEquals("The value of long=CV is not a valid number.", vr.getValidationMessages().get(1));
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = (double[])vr.getTransformedValue();
        assertEquals(2, val.length);
        assertEquals(-88.0, val[0], .01);
        assertEquals(Double.NaN, val[1], .01);

    }        
    @Test
    public void testLat_NaN() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.LONGITUDE);
        ValidationResult<?> vr = validator.validate("CV");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of long=CV is not a valid number.", vr.getValidationMessages().get(0));
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = (double[])vr.getTransformedValue();
        assertEquals(1, val.length);
        assertEquals(Double.NaN, val[0], .01);
    }

}
