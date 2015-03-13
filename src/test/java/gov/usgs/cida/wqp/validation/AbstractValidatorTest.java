package gov.usgs.cida.wqp.validation;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.Parameters;

import org.junit.Test;

public class AbstractValidatorTest extends BaseSpringTest implements ValidationConstants {
    
    private class TestValidator extends AbstractValidator<String[]> {

        protected TestValidator(Parameters inParameter) {
            super(inParameter);
        }

        protected TestValidator(Parameters inParameter, int minOccurs, int maxOccurs, String delimiter) {
            super(inParameter, minOccurs, maxOccurs, delimiter);
        }

        @Override
        public ValidationResult<String[]> validate(String value) {
            return null;
        }

    }
    
    @Test
    public void testConstructor() {
        try {
            new TestValidator(null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Parameter being validated must be provided.", e.getMessage());
        }
        
        try {
            new TestValidator(null, -1, 0, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Parameter being validated must be provided.", e.getMessage());
        }
        
        try {
            new TestValidator(Parameters.COUNTRY, -1, 0, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("minOccurs must be >= 0.", e.getMessage());
        }
        
        try {
            new TestValidator(Parameters.COUNTRY, 0, 0, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("maxOccurs must be > 0.", e.getMessage());
        }
        
        try {
            new TestValidator(Parameters.COUNTRY, 9, 1, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("maxOccurs must be > minOccurs.", e.getMessage());
        }
        
        try {
            new TestValidator(Parameters.COUNTRY, 1, 9, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("delimiter must be defined if maxOccurs > minOccurs.", e.getMessage());
        }
        
        try {
            new TestValidator(Parameters.COUNTRY, 1, 9, "");
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("delimiter must be defined if maxOccurs > minOccurs.", e.getMessage());
        }
        
        TestValidator rv = new TestValidator(Parameters.COUNTRY);
        assertEquals(DEFAULT_MIN_OCCURS, rv.getMinOccurs());
        assertEquals(DEFAULT_MAX_OCCURS, rv.getMaxOccurs());
        assertEquals(DEFAULT_DELIMITER, rv.getDelimiter());

        assertEquals("The value of countrycode=value is invalid", rv.getErrorMessage("value", "is invalid"));
    }


}
