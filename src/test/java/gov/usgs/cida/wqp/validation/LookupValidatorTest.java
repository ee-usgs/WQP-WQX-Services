package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.exception.WqpExceptionId;
import gov.usgs.cida.wqp.service.CodesService;

public class LookupValidatorTest extends BaseTest {

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
		when(codesService.validate(isNull(), isNull())).thenReturn(true);
		assertTrue(validator.isValid(null, context));
		verify(codesService).validate(isNull(), isNull());
	}

	@Test
	public void testEmptyStringValue() throws WqpException {
		when(codesService.validate(isNull(), anyString())).thenReturn(true);
		assertTrue(validator.isValid("", context));
		verify(codesService).validate(isNull(), anyString());
	}

	@Test
	public void testNotFoundValue() throws WqpException {
		when(codesService.validate(isNull(), anyString())).thenReturn(false);
		assertFalse(validator.isValid("ABC", context));
		verify(codesService).validate(isNull(), anyString());
	}

	@Test
	public void testOkValue() throws WqpException {
		when(codesService.validate(isNull(), anyString())).thenReturn(true);
		assertTrue(validator.isValid("ABC", context));
		verify(codesService).validate(isNull(), anyString());
	}

	@Test
	public void testWqpException() throws WqpException {
		when(codesService.validate(isNull(), anyString())).thenThrow(new WqpException(WqpExceptionId.URL_PARSING_EXCEPTION, null, null, "oops", null));
		when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(cvb);
		assertFalse(validator.isValid("ABC", context));
		verify(codesService).validate(isNull(), anyString());
		verify(cvb).addConstraintViolation();
	}

}