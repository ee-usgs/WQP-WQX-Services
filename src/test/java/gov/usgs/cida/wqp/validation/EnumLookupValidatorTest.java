package gov.usgs.cida.wqp.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.parameter.Parameters;

public class EnumLookupValidatorTest extends BaseTest {

	@Mock
	protected ConstraintValidatorContext context;
	@Mock
	protected EnumLookup enumLookup;

	protected EnumLookupValidator validator;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		validator = new EnumLookupValidator();
	}

	@Test
	public void testNullValue() throws WqpException {
		when(enumLookup.parameter()).thenReturn(Parameters.DATA_PROFILE);
		validator.initialize(enumLookup);
		assertTrue(validator.isValid(null, context));
	}

	@Test
	public void testEmptyStringValue() throws WqpException {
		when(enumLookup.parameter()).thenReturn(Parameters.DATA_PROFILE);
		validator.initialize(enumLookup);
		assertTrue(validator.isValid("", context));
	}

	@Test
	public void testNotFoundValue() throws WqpException {
		when(enumLookup.parameter()).thenReturn(Parameters.DATA_PROFILE);
		validator.initialize(enumLookup);
		assertFalse(validator.isValid("ABC", context));
	}

	@Test
	public void testOkValue() throws WqpException {
		when(enumLookup.parameter()).thenReturn(Parameters.DATA_PROFILE);
		validator.initialize(enumLookup);
		assertTrue(validator.isValid("station", context));
	}

	@Test
	public void testNotFoundParameter() throws WqpException {
		when(enumLookup.parameter()).thenReturn(Parameters.ZIP);
		validator.initialize(enumLookup);
		assertFalse(validator.isValid("station", context));
	}
}
