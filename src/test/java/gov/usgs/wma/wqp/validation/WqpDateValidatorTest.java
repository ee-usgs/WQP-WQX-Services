package gov.usgs.wma.wqp.validation;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gov.usgs.wma.wqp.BaseTest;
import gov.usgs.wma.wqp.parameter.FilterParameters;

public class WqpDateValidatorTest extends BaseTest {

	@Mock
	protected ConstraintValidatorContext context;
	@Mock
	protected WqpDate wqpDate;

	protected WqpDateValidator validator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(wqpDate.formatString()).thenReturn(FilterParameters.FORMAT_DATE);
		validator = new WqpDateValidator();
		validator.initialize(wqpDate);
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