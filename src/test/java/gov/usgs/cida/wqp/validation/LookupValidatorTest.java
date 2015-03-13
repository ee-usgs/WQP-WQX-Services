package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;

import org.junit.Test;

public class LookupValidatorTest extends BaseSpringTest implements ValidationConstants {

    // Each individual end point should do it's own unit/integration test against the database.

    private class TestCodeServiceMatch extends CodesService {
    	public String fetch(Parameters codeType, String code) {
    		return code;
    	}
    }
    private class TestCodeServiceMismatch extends CodesService {
    	public String fetch(Parameters codeType, String code) {
    		return "";
    	}
    }
    
    @Test
    public void testConstructors() {
        try {
            new LookupValidator(null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Parameter being validated must be provided.", e.getMessage());
        }
        
        try {
            new LookupValidator(null, -1, 0, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Parameter being validated must be provided.", e.getMessage());
        }
        
        AbstractValidator<?> validator = new LookupValidator(Parameters.COUNTRY);
        assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
        assertEquals(IN_CLAUSE_LIMIT, validator.getMaxOccurs());
        assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
    }
    
    @Test
    public void testNullValue() {
    	LookupValidator validator = new LookupValidator(Parameters.COUNTRY, 1, 2, ";");
        validator.setCodesService(new TestCodeServiceMatch());
        
        ValidationResult<String[]> vr = validator.validate(null);
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of countrycode=null is not between 1 and 2 occurences.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{}, (String[])vr.getTransformedValue());
    }
    
    @Test
    public void testEmptyStringValue() {
    	LookupValidator validator = new LookupValidator(Parameters.COUNTRY, 1, 2, ";");
        validator.setCodesService(new TestCodeServiceMismatch());
        
        ValidationResult<String[]> vr = validator.validate("asdf");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of countrycode=asdf is not in the list of enumerated values", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"asdf"}, vr.getTransformedValue());
    }
    
    @Test
    public void testTooManyInvalidStringValue() {
    	LookupValidator validator = new LookupValidator(Parameters.COUNTRY, 1, 2, ";");
        validator.setCodesService(new TestCodeServiceMismatch());
        
        ValidationResult<String[]> vr = validator.validate("AA;BB;CC");
        assertFalse(vr.isValid());
        assertEquals(4, vr.getValidationMessages().size());
        assertEquals("The value of countrycode=AA;BB;CC is not between 1 and 2 occurences.", vr.getValidationMessages().get(0));
        assertEquals("The value of countrycode=AA is not in the list of enumerated values", vr.getValidationMessages().get(1));
        assertEquals("The value of countrycode=BB is not in the list of enumerated values", vr.getValidationMessages().get(2));
        assertEquals("The value of countrycode=CC is not in the list of enumerated values", vr.getValidationMessages().get(3));
        assertArrayEquals(new String[]{"AA","BB","CC"}, (String[])vr.getTransformedValue());
    }

    @Test
    public void testNotFoundValue() {
    	LookupValidator validator = new LookupValidator(Parameters.COUNTRY, 1, 2, "-");
        validator.setCodesService(new TestCodeServiceMismatch());
        
        ValidationResult<String[]> vr = validator.validate("AA;BB;CC");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of countrycode=AA;BB;CC is not in the list of enumerated values", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"AA;BB;CC"}, (String[])vr.getTransformedValue());
    }
    
}
