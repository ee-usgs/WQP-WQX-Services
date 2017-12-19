package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.parameter.Parameters;

public class EnumLookupValidatorTest extends BaseSpringTest {

	@Mock
	protected ConstraintValidatorContext context;

	protected EnumLookupValidator validator;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		validator = new EnumLookupValidator();
	}

	@Test
	public void testNullValue() throws WqpException {
		Whitebox.setInternalState(validator, "parameter", Parameters.DATA_PROFILE);
		assertTrue(validator.isValid(null, context));
	}

	@Test
	public void testEmptyStringValue() throws WqpException {
		Whitebox.setInternalState(validator, "parameter", Parameters.DATA_PROFILE);
		assertTrue(validator.isValid("", context));
	}

	@Test
	public void testNotFoundValue() throws WqpException {
		Whitebox.setInternalState(validator, "parameter", Parameters.DATA_PROFILE);
		assertFalse(validator.isValid("ABC", context));
	}

	@Test
	public void testOkValue() throws WqpException {
		Whitebox.setInternalState(validator, "parameter", Parameters.DATA_PROFILE);
		assertTrue(validator.isValid("station", context));
	}

	@Test
	public void testNotFoundParameter() throws WqpException {
		Whitebox.setInternalState(validator, "parameter", Parameters.ZIP);
		assertFalse(validator.isValid("station", context));
	}

}
