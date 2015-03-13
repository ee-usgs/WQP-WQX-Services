package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.Parameters;

import org.junit.Test;

public class DateFormatValidatorTest extends BaseSpringTest implements ValidationConstants {

    @Test
    public void testConstructors_nullParameter() {
        try {
            new DateFormatValidator(null, "format string");
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Parameter being validated must be provided.", e.getMessage());
        }
    }        
    @Test
    public void testConstructors_nullParameter2() {
        try {
            new DateFormatValidator(null, -1, 0, null, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Parameter being validated must be provided.", e.getMessage());
        }
    }
    @Test
    public void testConstructors_nullFormat() {
        try {
            new DateFormatValidator(Parameters.START_DATE_HI, -0, 1, null, null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Format String must be provided.", e.getMessage());
        }
    }        
    @Test
    public void testConstructors_emptyStringFormat() {
        try {
            new DateFormatValidator(Parameters.START_DATE_HI, 0, 1, null, "");
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("The Format String must be provided.", e.getMessage());
        }
    }
    @Test
    public void testConstructors_defaults() {
        AbstractValidator<?> validator = new DateFormatValidator(Parameters.START_DATE_HI, FORMAT_DATE);
        assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
        assertEquals(DEFAULT_MAX_OCCURS, validator.getMaxOccurs());
        assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
    }
    
    @Test
    public void testValidate_SimpleDateFormat_nullValue() {
        AbstractValidator<String[]> validator = new DateFormatValidator(Parameters.START_DATE_HI, 1, 2, ";", FORMAT_DATE);
        
        ValidationResult<String[]> vr = validator.validate(null);
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of startDateHi=null is not between 1 and 2 occurances.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{}, vr.getTransformedValue());
    }
    @Test
    public void testValidate_SimpleDateFormat_wrongOccurs() {
        AbstractValidator<String[]> validator = new DateFormatValidator(Parameters.START_DATE_HI, 1, 2, ";", FORMAT_DATE);
        
        ValidationResult<String[]> vr = validator.validate("12-31-2011;09-30-2011;01-31-2011");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of startDateHi=12-31-2011;09-30-2011;01-31-2011 is not between 1 and 2 occurances.",
                vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"12-31-2011","09-30-2011","01-31-2011"}, vr.getTransformedValue());
    }
    @Test
    public void testValidate_SimpleDateFormat_wrongDayOfMonth() {
        AbstractValidator<String[]> validator = new DateFormatValidator(Parameters.START_DATE_HI, 1, 2, ";", FORMAT_DATE);
        
        ValidationResult<String[]> vr = validator.validate("09-31-2011");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of startDateHi=09-31-2011 must be a valid date in the format MM-dd-yyyy", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"09-31-2011"}, vr.getTransformedValue());
    }
    @Test
    public void testValidate_SimpleDateFormat_wrongMonthOfYear() {
        AbstractValidator<String[]> validator = new DateFormatValidator(Parameters.START_DATE_HI, 1, 2, ";", FORMAT_DATE);
        
        ValidationResult<String[]> vr = validator.validate("13-31-2011");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of startDateHi=13-31-2011 must be a valid date in the format MM-dd-yyyy", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"13-31-2011"}, vr.getTransformedValue());
    }
    @Test
    public void testValidate_SimpleDateFormat_leapYear() {
        AbstractValidator<String[]> validator = new DateFormatValidator(Parameters.START_DATE_HI, 1, 2, ";", FORMAT_DATE);
        
        ValidationResult<String[]> vr = validator.validate("02-29-2004");
        assertTrue(vr.isValid());
    }
    @Test
    public void testValidate_SimpleDateFormat_nonLeapYear() {
        AbstractValidator<String[]> validator = new DateFormatValidator(Parameters.START_DATE_HI, 1, 2, ";", FORMAT_DATE);
        
        ValidationResult<String[]> vr = validator.validate("02-29-2001");
        assertFalse(vr.isValid());
    }
    @Test
    public void testValidate_SimpleDateFormat_manyErrors() {
        AbstractValidator<String[]> validator = new DateFormatValidator(Parameters.START_DATE_HI, 1, 2, ";", FORMAT_DATE);
        
        ValidationResult<String[]> vr = validator.validate("12-31-2011;abc;09-31-2011;13-31-2011;12-32-2011");
        assertFalse(vr.isValid());
        assertEquals(5, vr.getValidationMessages().size());
        assertEquals("The value of startDateHi=12-31-2011;abc;09-31-2011;13-31-2011;12-32-2011 is not between 1 and 2 occurances.",
                vr.getValidationMessages().get(0));
        assertEquals("The value of startDateHi=abc must be a valid date in the format MM-dd-yyyy", vr.getValidationMessages().get(1));
        assertEquals("The value of startDateHi=09-31-2011 must be a valid date in the format MM-dd-yyyy", vr.getValidationMessages().get(2));
        assertEquals("The value of startDateHi=13-31-2011 must be a valid date in the format MM-dd-yyyy", vr.getValidationMessages().get(3));
        assertEquals("The value of startDateHi=12-32-2011 must be a valid date in the format MM-dd-yyyy", vr.getValidationMessages().get(4));
        assertArrayEquals(new String[]{"12-31-2011","abc","09-31-2011","13-31-2011","12-32-2011"}, vr.getTransformedValue());
    }

    @Test
    public void testStartDateHi_defaults() {
        AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.START_DATE_HI);
        assertNotNull(validator);
        assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
        assertEquals(DEFAULT_MAX_OCCURS, validator.getMaxOccurs());
        assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
    }        
    @Test
    public void testStartDateHi_valid() {
        AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.START_DATE_HI);
        ValidationResult<?> vr = validator.validate("05-30-2013");
        assertTrue(vr.isValid());
        assertEquals(0, vr.getValidationMessages().size());
        assertArrayEquals(new String[]{"05-30-2013"}, (String[])vr.getTransformedValue());
    }
    @Test
    public void testStartDateHi_invalid() {
        AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.START_DATE_HI);
        ValidationResult<?> vr = validator.validate("05-30-2013;CV");
        assertFalse(vr.isValid());
        assertEquals(2, vr.getValidationMessages().size());
        assertEquals("The value of startDateHi=05-30-2013;CV is not between 0 and 1 occurances.", vr.getValidationMessages().get(0));
        assertEquals("The value of startDateHi=CV must be a valid date in the format MM-dd-yyyy", vr.getValidationMessages().get(1));
        assertArrayEquals(new String[]{"05-30-2013","CV"}, (String[])vr.getTransformedValue());
    }

    @Test
    public void testStartDateLo_defaults() {
        AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.START_DATE_LO);
        assertNotNull(validator);
        assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
        assertEquals(DEFAULT_MAX_OCCURS, validator.getMaxOccurs());
        assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
    }        
    @Test
    public void testStartDateLo_valid() {
        AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.START_DATE_LO);
        ValidationResult<?> vr = validator.validate("05-30-2013");
        assertTrue(vr.isValid());
        assertEquals(0, vr.getValidationMessages().size());
        assertArrayEquals(new String[]{"05-30-2013"}, (String[])vr.getTransformedValue());
    }
    @Test
    public void testStartDateLo_invalid() {
        AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.START_DATE_LO);
        ValidationResult<?> vr = validator.validate("05-30-2013;CV");
        assertFalse(vr.isValid());
        assertEquals(2, vr.getValidationMessages().size());
        assertEquals("The value of startDateLo=05-30-2013;CV is not between 0 and 1 occurances.", vr.getValidationMessages().get(0));
        assertEquals("The value of startDateLo=CV must be a valid date in the format MM-dd-yyyy", vr.getValidationMessages().get(1));
        assertArrayEquals(new String[]{"05-30-2013","CV"}, (String[])vr.getTransformedValue());
    }

}
