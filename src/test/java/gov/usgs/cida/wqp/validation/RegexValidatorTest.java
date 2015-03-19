package gov.usgs.cida.wqp.validation;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.parameter.transform.SplitTransformer;
import org.junit.Test;
public class RegexValidatorTest extends BaseSpringTest implements ValidationConstants {
	@Test
	public void testConstructors_nullParameter() {
		try {
			new RegexValidator<Object>(null, null);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("The Parameter being validated must be provided.", e.getMessage());
		}
	}
	@Test
	public void testConstructors_nullPattern() {
		try {
			new RegexValidator<Object>(Parameters.ACTIVITY_ID, null);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("The REGEX must be provided.", e.getMessage());
		}
	}
	@Test
	public void testConstructors_defaults() {
		RegexValidator<?> validator = new RegexValidator<Object>(Parameters.ACTIVITY_ID, "abc");
		assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(IN_CLAUSE_LIMIT, validator.getMaxOccurs());
		assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
		assertEquals("(?:(?:^|(?<!^);)(?:abc)(?=(?<!;)$|;(?!$))){0,1000}", validator.getValidatePattern());
	}
	@Test
	public void testConstructors_customValues() {
		RegexValidator<?> validator = new RegexValidator<Object>(Parameters.ACTIVITY_ID, 5, 12, "!", "[0-9]+|abc");
		assertEquals(5, validator.getMinOccurs());
		assertEquals(12, validator.getMaxOccurs());
		assertEquals("!", validator.getDelimiter());
		assertEquals("(?:(?:^|(?<!^)!)(?:[0-9]+|abc)(?=(?<!!)$|!(?!$))){5,12}", validator.getValidatePattern());
	}
	@Test
	public void testConstructors_nullDelimiter() {
		RegexValidator<?> validator = new RegexValidator<Object>(Parameters.ACTIVITY_ID, 1, 1, null, "[0-9]+|abc");
		assertEquals(1, validator.getMinOccurs());
		assertEquals(1, validator.getMaxOccurs());
		assertNull(validator.getDelimiter());
		assertEquals("^[0-9]+|abc$", validator.getValidatePattern());
	}
	@Test
	public void testValidate_SimpleLiteral_match() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, "abc");
		ValidationResult<String[]> vr = validator.validate("abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_SimpleLiteral_mismatch() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, "abc");
		ValidationResult<String[]> vr = validator.validate("def");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of activityId=def must match the format abc", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"def"}, vr.getRawValue());
	}
	@Test
	public void testValidate_SimpleNumeric_match() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, "[0-9]+");
		ValidationResult<String[]> vr = validator.validate("123");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"123"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_SimpleNumeric_mismatch() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, "[0-9]+");
		ValidationResult<String[]> vr = validator.validate("345.678");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of activityId=345.678 must match the format [0-9]+", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"345.678"}, vr.getRawValue());
	}
	@Test
	public void testValidate_SucceedsOnEmptyString() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[0], vr.getTransformedValue());
	}
	@Test
	public void testValidate_SucceedsOnNull() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, 0, 1, null, "[0-9]+|abc");
		validator.setTransformer( new SplitTransformer(DEFAULT_DELIMITER));
		ValidationResult<String[]> vr = validator.validate(null);
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[0], vr.getTransformedValue());
	}
	@Test
	public void testValidate_MinOccursOnce_emptyStringValue() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, 1, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of activityId= must match the format [0-9]+|abc", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{""}, vr.getRawValue());
	}
	@Test
	public void testValidate_MinOccursOnce_matchingValue() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, 1, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_MinOccursOnce_mismatch() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, 1, DEFAULT_MAX_OCCURS, DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abcabc");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of activityId=abcabc must match the format [0-9]+|abc", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"abcabc"}, vr.getRawValue());
	}
	String delim = DEFAULT_DELIMITER;
	@Test
	public void testValidate_MaxOccursThrice() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, DEFAULT_MIN_OCCURS, 3, DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_MaxOccursThrice_2match() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, DEFAULT_MIN_OCCURS, 3, DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc" + delim + "abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc", "abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_MaxOccursThrice_3match() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, DEFAULT_MIN_OCCURS, 3, DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc" + delim + "abc" + delim + "abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc", "abc", "abc"}, vr.getTransformedValue());
	}
	@Test
	public void testValidate_MaxOccursThrice_tooMany() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, DEFAULT_MIN_OCCURS, 3, DEFAULT_DELIMITER, "[0-9]+|abc");
		ValidationResult<String[]> vr = validator.validate("abc" + delim + "abc" + delim + "abc" + delim + "abc");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of activityId=abc;abc;abc;abc must match the format [0-9]+|abc", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"abc;abc;abc;abc"}, vr.getRawValue());
	}
	@Test
	public void testValidate_MaxOccursUnbounded() {
		AbstractValidator<String[]> validator = new RegexValidator<String[]>(Parameters.ACTIVITY_ID, "[0-9]+|abc");
		String delim = validator.getDelimiter();
		ValidationResult<String[]> vr = validator.validate("abc" + delim + "abc" + delim + "abc" + delim + "abc");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"abc", "abc", "abc", "abc"}, vr.getTransformedValue());
	}
	@Test
	public void testCounty_defaults() throws Exception {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.COUNTY);
		assertNotNull(validator);
		assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(IN_CLAUSE_LIMIT, validator.getMaxOccurs());
		assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
	}
	@Test
	public void testCounty_match() throws Exception {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.COUNTY);
		ValidationResult<?> vr = validator.validate("US:55:027");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[][]{new String[]{"US","55","027"}}, (String[][])vr.getTransformedValue());
	}
	@Test
	public void testCounty_mismatch() throws Exception {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.COUNTY);
		ValidationResult<?> vr = validator.validate("US:55:027;CV");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of countycode=US:55:027;CV must match the format (?:([A-Z]{2}):)?([0-9]{1,2}):([0-9]{3}|N/A)", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"US:55:027;CV"}, (String[])vr.getRawValue());
	}
	@Test
	public void testHuc_defaults() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.HUC);
		assertNotNull(validator);
		assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(IN_CLAUSE_LIMIT, validator.getMaxOccurs());
		assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
	}
	@Test
	public void testHuc_match() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.HUC);
		ValidationResult<?> vr = validator.validate("07090002");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"07090002"}, (String[])vr.getTransformedValue());
	}
	@Test
	public void testHuc_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.HUC);
		ValidationResult<?> vr = validator.validate("07090002;CV");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of huc=07090002;CV must match the format (?:[0-9]{8})|(?:[0-9]{2,7}\\*)", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"07090002;CV"}, (String[])vr.getRawValue());
	}
	@Test
	public void testSiteId_defaults() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.SITEID);
		assertNotNull(validator);
		assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(UNBOUNDED, validator.getMaxOccurs());
		assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
	}
	@Test
	public void testSiteId_match() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.SITEID);
		ValidationResult<?> vr = validator.validate("IASF-IATC201");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[]{"IASF-IATC201"}, (String[])vr.getTransformedValue());
	}
	@Test
	public void testSiteId_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.SITEID);
		ValidationResult<?> vr = validator.validate("CV;IASF-IATC201");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of siteid=CV;IASF-IATC201 must match the format [\\w]+\\-.+\\S", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"CV;IASF-IATC201"}, (String[])vr.getRawValue());
	}
	@Test
	public void testState_defaults() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.STATE);
		assertNotNull(validator);
		assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
		assertEquals(IN_CLAUSE_LIMIT, validator.getMaxOccurs());
		assertEquals(DEFAULT_DELIMITER, validator.getDelimiter());
	}
	@Test
	public void testState_match() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.STATE);
		ValidationResult<?> vr = validator.validate("US:55");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertArrayEquals(new String[][]{new String[]{"US","55"}}, (String[][])vr.getTransformedValue());
	}
	@Test
	public void testState_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.STATE);
		ValidationResult<?> vr = validator.validate("US:55;CV");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of statecode=US:55;CV must match the format (?:([A-Z]{2}):)?([0-9]{1,2})", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"US:55;CV"}, (String[])vr.getRawValue());
	}
	@Test
	public void testMimeType() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.MIMETYPE);
		ValidationResult<?> vr = validator.validate("csv");
		assertTrue(vr.isValid());
	}
	@Test
	public void testMimeType_mismatch() {
		AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.MIMETYPE);
		ValidationResult<?> vr = validator.validate("cvs"); // intentional typo
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of mimeType=cvs must match the format csv|tsv|tab|xlsx|xml|kml|kmz", vr.getValidationMessages().get(0));
		assertArrayEquals(new String[]{"cvs"}, (String[])vr.getRawValue());
	}
}