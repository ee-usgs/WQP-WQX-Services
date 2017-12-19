package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.exception.WqpExceptionId;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;

public class LookupValidatorTest extends BaseSpringTest {

	@Mock
	protected ConstraintValidatorContext context;
	@Mock
	protected CodesService codesService;
	@Mock
	protected ConstraintViolationBuilder cvb;

	protected LookupValidator validator;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		validator = new LookupValidator();
		Whitebox.setInternalState(validator, "codesService", codesService);
	}

	@Test
	public void testNullValue() throws WqpException {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		assertTrue(validator.isValid(null, context));
		verify(codesService).validate(any(Parameters.class), anyString());
	}

	@Test
	public void testEmptyStringValue() throws WqpException {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		assertTrue(validator.isValid("", context));
		verify(codesService).validate(any(Parameters.class), anyString());
	}

	@Test
	public void testNotFoundValue() throws WqpException {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(false);
		assertFalse(validator.isValid("ABC", context));
		verify(codesService).validate(any(Parameters.class), anyString());
	}

	@Test
	public void testOkValue() throws WqpException {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		assertTrue(validator.isValid("ABC", context));
		verify(codesService).validate(any(Parameters.class), anyString());
	}

	@Test
	public void testWqpException() throws WqpException {
		when(codesService.validate(any(Parameters.class), anyString())).thenThrow(new WqpException(WqpExceptionId.URL_PARSING_EXCEPTION, null, null, "oops", null));
		when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(cvb);
		assertFalse(validator.isValid("ABC", context));
		verify(codesService).validate(any(Parameters.class), anyString());
		verify(cvb).addConstraintViolation();
	}

}