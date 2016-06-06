package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.FetchService;
import gov.usgs.cida.wqp.service.FetchServiceTest;

public class FetchValidatorTest extends BaseSpringTest implements ValidationConstants {

	protected FetchValidator validator;

	@Mock
	protected FetchService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		validator = new FetchValidator(NLDI_WQP_FEATURE_IDENTIFIER, service, Parameters.NLDIURL);
	}

	@Test
	public void tooManyOccursTest() {
		ValidationResult<String[]> vr = validator.validate("http://test.usgs.gov;http://test.usgs.gov");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of nldiurl=http://test.usgs.gov;http://test.usgs.gov is not between 0 and 1 occurences.",
				vr.getValidationMessages().get(0));
		assertNull(vr.getTransformedValue());
	}

	@Test
	public void zeroOccursTest() {
		ValidationResult<String[]> vr = validator.validate("");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertEquals(0, vr.getTransformedValue().length);
	}

	@Test
	public void badUrlTest() {
		ValidationResult<String[]> vr = validator.validate("httpx://test.usgs.gov");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of nldiurl=httpx://test.usgs.gov is not a valid URL.",
				vr.getValidationMessages().get(0));
		assertEquals(0, vr.getTransformedValue().length);
	}

	@Test
	public void cannotContactUrlTest() throws IOException {
		when(service.fetch(anyString(), any(URL.class))).thenThrow(new IOException());
		
		ValidationResult<String[]> vr = validator.validate("http://test.usgs.gov");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of nldiurl=http://test.usgs.gov cannot be accessed to validate your request.",
				vr.getValidationMessages().get(0));
		assertEquals(0, vr.getTransformedValue().length);
	}

	@Test
	public void noValuesTest() throws IOException {
		when(service.fetch(anyString(), any(URL.class))).thenReturn(new HashSet<String>());
		
		ValidationResult<String[]> vr = validator.validate("http://test.usgs.gov");
		assertFalse(vr.isValid());
		assertEquals(1, vr.getValidationMessages().size());
		assertEquals("The value of nldiurl=http://test.usgs.gov no values were found.",
				vr.getValidationMessages().get(0));
		assertEquals(0, vr.getTransformedValue().length);
	}

	@Test
	public void happyPathTest() throws IOException {
		when(service.fetch(anyString(), any(URL.class))).thenReturn(FetchServiceTest.NLDI_IDENTIFIERS);
		
		ValidationResult<String[]> vr = validator.validate("http://test.usgs.gov");
		assertTrue(vr.isValid());
		assertEquals(0, vr.getValidationMessages().size());
		assertEquals(12, vr.getTransformedValue().length);
		assertArrayEquals(FetchServiceTest.NLDI_IDENTIFIERS.toArray(new String[0]), vr.getTransformedValue());
	}

}
