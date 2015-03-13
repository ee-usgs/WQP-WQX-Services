package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.Parameters;

import org.junit.Test;

public class BoundedFloatingPointValidatorTest extends BaseSpringTest implements ValidationConstants {

    @Test
    public void testConstructors() {
        try {
            new BoundedFloatingPointValidator(null, null, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Parameter being validated must be provided.", e.getMessage());
        }

        try {
            new BoundedFloatingPointValidator(Parameters.WITHIN, null, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("minBound is not a valid number.", e.getMessage());
        }

        try {
            new BoundedFloatingPointValidator(Parameters.WITHIN, "10.5", null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("maxBound is not a valid number.", e.getMessage());
        }

        try {
            new BoundedFloatingPointValidator(Parameters.WITHIN, "10.5", "8.2");
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("minBound must be less than maxBound.", e.getMessage());
        }

        BoundedFloatingPointValidator validator = new BoundedFloatingPointValidator(Parameters.WITHIN);
        assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs()); 
        assertEquals(DEFAULT_MAX_OCCURS, validator.getMaxOccurs()); 
        assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
        assertEquals(-Double.MAX_VALUE, validator.minBound, .1);
        assertEquals(Double.MAX_VALUE, validator.maxBound, .1);

        validator = new BoundedFloatingPointValidator(Parameters.WITHIN, "3", "12");
        assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs()); 
        assertEquals(DEFAULT_MAX_OCCURS, validator.getMaxOccurs()); 
        assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
        assertEquals(Double.valueOf("3"), validator.minBound, .1);
        assertEquals(Double.valueOf("12"), validator.maxBound, .1);

        validator = new BoundedFloatingPointValidator(Parameters.WITHIN, 1, 2, "x", "3", "4");
        assertEquals(1, validator.getMinOccurs()); 
        assertEquals(2, validator.getMaxOccurs()); 
        assertEquals("x", validator.getDelimiter());
        assertEquals(Double.valueOf("3"), validator.minBound, .1);
        assertEquals(Double.valueOf("4"), validator.maxBound, .1);
    }
    
    @Test
    public void testNullValue() {
    	BoundedFloatingPointValidator validator = new BoundedFloatingPointValidator(Parameters.WITHIN, 1, 2, ";", "3", "4");

        ValidationResult<double[]> vr = validator.validate(null);
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of within=null is not between 1 and 2 occurances.", vr.getValidationMessages().get(0));
        assertTrue(vr.getTransformedValue() instanceof double[]);
        assertEquals(0, vr.getTransformedValue().length);
    }        
    @Test
    public void testEmptyStringValue() {
    	BoundedFloatingPointValidator validator = new BoundedFloatingPointValidator(Parameters.WITHIN, 1, 2, ";", "3", "4");

        ValidationResult<double[]> vr = validator.validate("");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of within= is not between 1 and 2 occurances.", vr.getValidationMessages().get(0));
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = vr.getTransformedValue();
        assertEquals(0, val.length);
        //assertEquals(Double.NaN, val[0], .01);
    }
    @Test
    public void testThreeValues() {
    	BoundedFloatingPointValidator validator = new BoundedFloatingPointValidator(Parameters.WITHIN, 1, 2, ";", "3", "4");

        ValidationResult<double[]> vr = validator.validate("1;2;3.5");
        assertFalse(vr.isValid());
        assertEquals(3, vr.getValidationMessages().size());
        assertEquals("The value of within=1;2;3.5 is not between 1 and 2 occurances.", vr.getValidationMessages().get(0));
        assertEquals("The value of within=1.0 is not between 3.0 and 4.0", vr.getValidationMessages().get(1));
        assertEquals("The value of within=2.0 is not between 3.0 and 4.0", vr.getValidationMessages().get(2));
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = vr.getTransformedValue();
        assertEquals(3, val.length);
        assertEquals(1.0, val[0], .01);
        assertEquals(2.0, val[1], .01);
        assertEquals(3.5, val[2], .01);
    }

    @Test
    public void testIntegersValues() {
    	BoundedFloatingPointValidator validator = new BoundedFloatingPointValidator(Parameters.WITHIN, 1, 2, ";", "3", "4");

        ValidationResult<double[]> vr = validator.validate("5;6");
        assertFalse(vr.isValid());
        assertEquals(2, vr.getValidationMessages().size());
        assertEquals("The value of within=5.0 is not between 3.0 and 4.0", vr.getValidationMessages().get(0));
        assertEquals("The value of within=6.0 is not between 3.0 and 4.0", vr.getValidationMessages().get(1));
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = vr.getTransformedValue();
        assertEquals(2, val.length);
        assertEquals(5.0, val[0], .01);
        assertEquals(6.0, val[1], .01);
    }
    

    @Test
    public void testWithinDefinition() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.WITHIN);
        
        assertNotNull(validator);
        assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
        assertEquals(DEFAULT_MAX_OCCURS, validator.getMaxOccurs());
        assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
    }
    @Test
    public void testWithinPositiveValue() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.WITHIN);
        ValidationResult<?> vr = validator.validate("123");
        assertTrue(vr.isValid());
        assertEquals(0, vr.getValidationMessages().size());
        assertTrue(vr.getTransformedValue() instanceof double[]);
        double[] val = (double[])vr.getTransformedValue();
        assertEquals(1, val.length);
        assertEquals(123.0, val[0], .01);
    }
    @Test
    public void testWithinNegativeValue() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.WITHIN);
        ValidationResult<?> vr = validator.validate("-2");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of within=-2.0 is not between 0.0 and 2.147483647E9", vr.getValidationMessages().get(0));
        double[]val = (double[])vr.getTransformedValue();
        assertEquals(1, val.length);
        assertEquals(-2.0, val[0], .01);
    }
    @Test
    public void testWithinMixValues() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.WITHIN);
    	ValidationResult<?> vr = validator.validate("2013;CV; 567 ");
        assertFalse(vr.isValid());
        assertEquals(3, vr.getValidationMessages().size());
        assertEquals("The value of within=2013;CV; 567  is not between 0 and 1 occurances.", vr.getValidationMessages().get(0));
        assertEquals("The value of within=CV is not a valid number.", vr.getValidationMessages().get(1));
        assertEquals("The value of within= 567  has leading or trailing whitespace.", vr.getValidationMessages().get(2));
        double[]val = (double[])vr.getTransformedValue();
        assertEquals(3, val.length);
        assertEquals(2013.0, val[0], .01);
        assertEquals(Double.NaN, val[1], .01);
        assertEquals(Double.NaN, val[2], .01);
    }

}
