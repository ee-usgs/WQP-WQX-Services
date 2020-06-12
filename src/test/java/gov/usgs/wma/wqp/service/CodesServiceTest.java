package gov.usgs.wma.wqp.service;

import static gov.usgs.wma.wqp.exception.WqpExceptionId.METHOD_PARAM_EMPTY;
import static gov.usgs.wma.wqp.exception.WqpExceptionId.METHOD_PARAM_NULL;
import static gov.usgs.wma.wqp.exception.WqpExceptionId.URL_PARSING_EXCEPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gov.usgs.wma.wqp.BaseTest;
import gov.usgs.wma.wqp.exception.WqpException;
import gov.usgs.wma.wqp.parameter.Parameters;

public class CodesServiceTest extends BaseTest {

	@Mock
	private ConfigurationService configurationService;
	private static final String BASE_URL = "https://wqp.codes.usgs.gov/codes/";

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMakeUrl_nullParam() throws Exception {
		try {
			new CodesService(configurationService).makeCodesUrl(null, "provider");
			fail("should have thrown exception on null parameter");
		} catch (WqpException e) {
			assertEquals(METHOD_PARAM_NULL, e.getExceptionid(), "Expect param exception");
		}
	}

	@Test
	public void testMakeUrl_nullCode() throws Exception {
		try {
			new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, null);
			fail("should have thrown exception on null code");
		} catch (WqpException e) {
			assertEquals(METHOD_PARAM_NULL, e.getExceptionid(), "Expect param exception");
		}
	}

	@Test
	public void testMakeUrl_emptyCode() throws Exception {
		try {
			new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, "");
			fail("should have thrown exception on empty string");
		} catch (WqpException e) {
			assertEquals(METHOD_PARAM_EMPTY, e.getExceptionid(), "Expect param exception");
		}
	}

	@Test
	public void testMakeUrl_default() throws Exception {
		when(configurationService.getCodesUrl()).thenReturn(BASE_URL);
		when(configurationService.getCodesMimeType()).thenReturn(JSON);
		URL actualUrl = new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, "pro/vider");
		String expectedUrl = BASE_URL +"/"+ Parameters.PROVIDERS +"/validate?value=pro%2Fvider&mimeType=json";
		assertEquals(expectedUrl, actualUrl.toString());
	}

	@Test
	public void testMakeUrl_customMimeType() throws Exception {
		when(configurationService.getCodesUrl()).thenReturn(BASE_URL);
		when(configurationService.getCodesMimeType()).thenReturn(XML);
		URL actualUrl = new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, "provider");
		String expectedUrl = BASE_URL +"/"+ Parameters.PROVIDERS +"/validate?value=provider&mimeType="+XML;
		assertEquals(expectedUrl, actualUrl.toString());
	}

	@Test
	public void testMakeUrl_badURL() throws Exception {
		when(configurationService.getCodesUrl()).thenReturn("ht//tps://wqp.codes.usgs.gov/bad/URL/");
		try {
			URL actualUrl = new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, "provider");
			System.out.println(actualUrl.toString());
			fail("should have thrown exception on bad URL");
		} catch (WqpException e) {
			assertEquals(URL_PARSING_EXCEPTION, e.getExceptionid(), "Expect URL exception");
		}
	}

	@Test
	public void testValidation_nullParam() throws Exception {
		try {
			new CodesService(configurationService).validate(null, "provider");
			fail("should have thrown exception on null parameter");
		} catch (WqpException e) {
			assertTrue(e.getExceptionid() == METHOD_PARAM_NULL, "Expect param exception");
		}
	}

	@Test
	public void testValidation_nullCode() throws Exception {
		try {
			new CodesService(configurationService).validate(Parameters.PROVIDERS, null);
			fail("should have thrown exception on null code");
		} catch (WqpException e) {
			assertEquals(METHOD_PARAM_NULL, e.getExceptionid(), "Expect param exception");
		}
	}

	@Test
	public void testValidation_emptyCode() throws Exception {
		boolean actual = new CodesService(configurationService).validate(Parameters.PROVIDERS, "");
		assertFalse(actual, "Empty string code is always invalid");
	}

}
