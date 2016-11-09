package gov.usgs.cida.wqp.validation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.Parameters;
public class AbstractValidatorTest extends BaseSpringTest {
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
	public void testConstructor_nullParameter() {
		try {
			new TestValidator(null);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("The Parameter being validated must be provided.", e.getMessage());
		}
	}
	@Test
	public void testConstructor_nullParameter2() {
		try {
			new TestValidator(null, 1, 0, AbstractValidator.DEFAULT_DELIMITER);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("The Parameter being validated must be provided.", e.getMessage());
		}
	}
	@Test
	public void testConstructor_minOccurTooSmall() {
		try {
			new TestValidator(Parameters.COUNTRY, -1, 0, AbstractValidator.DEFAULT_DELIMITER);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("minOccurs must be >= 0.", e.getMessage());
		}
	}
	@Test
	public void testConstructor_maxOccurZero() {
		try {
			new TestValidator(Parameters.COUNTRY, 0, 0, null);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("maxOccurs must be > 0.", e.getMessage());
		}
	}
	@Test
	public void testConstructor_maxOccurTooSmall() {
		try {
			new TestValidator(Parameters.COUNTRY, 9, 1, null);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("maxOccurs must be > minOccurs.", e.getMessage());
		}
	}
	@Test
	public void testConstructor_nullDelimiter() {
		try {
			new TestValidator(Parameters.COUNTRY, 1, 9, null);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("delimiter must be defined if maxOccurs > minOccurs.", e.getMessage());
		}
	}
	@Test
	public void testConstructor_emptyStringDelimiter() {
		try {
			new TestValidator(Parameters.COUNTRY, 1, 9, "");
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("delimiter must be defined if maxOccurs > minOccurs.", e.getMessage());
		}
	}
	@Test
	public void testConstructor_defaults() {
		TestValidator rv = new TestValidator(Parameters.COUNTRY);
		assertEquals(AbstractValidator.DEFAULT_MIN_OCCURS, rv.getMinOccurs());
		assertEquals(AbstractValidator.DEFAULT_MAX_OCCURS, rv.getMaxOccurs());
		assertEquals(AbstractValidator.DEFAULT_DELIMITER, rv.getDelimiter());
		assertEquals("The value of countrycode=value is invalid", rv.getErrorMessage("value", "is invalid"));
	}
}