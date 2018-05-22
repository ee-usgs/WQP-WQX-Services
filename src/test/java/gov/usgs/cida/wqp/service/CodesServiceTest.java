package gov.usgs.cida.wqp.service;

import static gov.usgs.cida.wqp.exception.WqpExceptionId.METHOD_PARAM_EMPTY;
import static gov.usgs.cida.wqp.exception.WqpExceptionId.METHOD_PARAM_NULL;
import static gov.usgs.cida.wqp.exception.WqpExceptionId.URL_PARSING_EXCEPTION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.parameter.Parameters;

public class CodesServiceTest extends BaseTest {

	private ConfigurationService configurationService;

	@Before
	public void setup() {
		configurationService = new ConfigurationService();
	}

	@Test
	public void testMakeUrl_nullParam() throws Exception {
		try {
			new CodesService(configurationService).makeCodesUrl(null, "provider");
			fail("should have thrown exception on null parameter");
		} catch (WqpException e) {
			assertEquals("Expect param exception", METHOD_PARAM_NULL, e.getExceptionid());
		}
	}

	@Test
	public void testMakeUrl_nullCode() throws Exception {
		try {
			new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, null);
			fail("should have thrown exception on null code");
		} catch (WqpException e) {
			assertEquals("Expect param exception", METHOD_PARAM_NULL, e.getExceptionid());
		}
	}

	@Test
	public void testMakeUrl_emptyCode() throws Exception {
		try {
			new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, "");
			fail("should have thrown exception on empty string");
		} catch (WqpException e) {
			assertEquals("Expect param exception", METHOD_PARAM_EMPTY, e.getExceptionid());
		}
	}

	@Test
	public void testMakeUrl_default() throws Exception {
		String baseUrl = "https://wqp.codes.usgs.gov/codes/";
		Whitebox.setInternalState(configurationService, "codesUrl", baseUrl);
		Whitebox.setInternalState(configurationService, "codesMimeType", JSON);
		URL actualUrl = new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, "pro/vider");
		String expectedUrl = baseUrl +"/"+ Parameters.PROVIDERS +"/validate?value=pro%2Fvider&mimeType=json";
		assertEquals(expectedUrl, actualUrl.toString());
	}

	@Test
	public void testMakeUrl_customMimeType() throws Exception {
		String baseUrl = "https://wqp.codes.usgs.gov/codes/";
		String mimeType = XML;
		Whitebox.setInternalState(configurationService, "codesUrl", baseUrl);
		Whitebox.setInternalState(configurationService, "codesMimeType", mimeType);
		URL actualUrl = new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, "provider");
		String expectedUrl = baseUrl +"/"+ Parameters.PROVIDERS +"/validate?value=provider&mimeType="+mimeType;
		assertEquals(expectedUrl, actualUrl.toString());
	}

	@Test
	public void testMakeUrl_badURL() throws Exception {
		String baseUrl = "ht//tps://wqp.codes.usgs.gov/bad/URL/";
		Whitebox.setInternalState(configurationService, "codesUrl", baseUrl);
		try {
			URL actualUrl = new CodesService(configurationService).makeCodesUrl(Parameters.PROVIDERS, "provider");
			System.out.println(actualUrl.toString());
			fail("should have thrown exception on bad URL");
		} catch (WqpException e) {
			assertEquals("Expect URL exception", URL_PARSING_EXCEPTION, e.getExceptionid());
		}
	}

	@Test
	public void testValidation_nullParam() throws Exception {
		try {
			new CodesService(configurationService).validate(null, "provider");
			fail("should have thrown exception on null parameter");
		} catch (WqpException e) {
			assertTrue("Expect param exception", e.getExceptionid() == METHOD_PARAM_NULL);
		}
	}

	@Test
	public void testValidation_nullCode() throws Exception {
		try {
			new CodesService(configurationService).validate(Parameters.PROVIDERS, null);
			fail("should have thrown exception on null code");
		} catch (WqpException e) {
			assertEquals("Expect param exception", METHOD_PARAM_NULL, e.getExceptionid());
		}
	}

	@Test
	public void testValidation_emptyCode() throws Exception {
		boolean actual = new CodesService(configurationService).validate(Parameters.PROVIDERS, "");
		assertFalse("Empty string code is always invalid", actual);
	}

}
