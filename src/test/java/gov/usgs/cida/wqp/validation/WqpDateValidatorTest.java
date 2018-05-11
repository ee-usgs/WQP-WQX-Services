package gov.usgs.cida.wqp.validation;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public class WqpDateValidatorTest extends BaseTest {

	@Mock
	protected ConstraintValidatorContext context;

	protected WqpDateValidator validator;

	@Before
	public void setup() {
		validator = new WqpDateValidator();
		Whitebox.setInternalState(validator, "formatString", FilterParameters.FORMAT_DATE);
	}

	@Test
	public void nullValue() {
		assertTrue(validator.isValid(null, context));
	}

	@Test
	public void emptyString() {
		assertTrue(validator.isValid("", context));
	}

	@Test
	public void wrongDayOfMonth() {
		assertFalse(validator.isValid("09-31-2011", context));
	}

	@Test
	public void wrongMonthOfYear() {
		assertFalse(validator.isValid("13-31-2011", context));
	}

	@Test
	public void leapYear() {
		assertTrue(validator.isValid("02-29-2004", context));
	}

	@Test
	public void nonLeapYear() {
		assertFalse(validator.isValid("02-29-2001", context));
	}

	@Test
	public void valid() {
		assertTrue(validator.isValid("05-30-2013", context));
	}

	@Test
	public void notADate() {
		assertFalse(validator.isValid("CV", context));
	}

}