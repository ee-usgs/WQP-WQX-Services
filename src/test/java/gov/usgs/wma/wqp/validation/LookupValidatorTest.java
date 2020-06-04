package gov.usgs.wma.wqp.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import gov.usgs.wma.wqp.BaseTest;
import gov.usgs.wma.wqp.exception.WqpException;
import gov.usgs.wma.wqp.exception.WqpExceptionId;
import gov.usgs.wma.wqp.service.CodesService;

@SpringBootTest(classes={LookupValidator.class})
public class LookupValidatorTest extends BaseTest {

	@MockBean
	protected ConstraintValidatorContext context;
	@MockBean
	protected CodesService codesService;
	@MockBean
	protected ConstraintViolationBuilder cvb;

	@Autowired
	protected LookupValidator validator;

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