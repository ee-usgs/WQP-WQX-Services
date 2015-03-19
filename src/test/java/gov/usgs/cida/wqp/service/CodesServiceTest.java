package gov.usgs.cida.wqp.service;

import static gov.usgs.cida.wqp.exception.WqpGatewayExceptionId.*;
import static org.junit.Assert.*;
import gov.cida.cdat.exception.StreamInitException;
import gov.cida.cdat.exception.producer.FileNotFoundException;
import gov.cida.cdat.io.container.UrlStreamContainer;
import gov.usgs.cida.wqp.TestUtils;
import gov.usgs.cida.wqp.exception.WqpGatewayException;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.WqpConfig;
import gov.usgs.cida.wqp.util.WqpConfigConstants;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodesServiceTest implements WqpConfigConstants {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	@Test
	public void testMakeUrl_nullParam() throws Exception {
		try {
			new CodesService().makeCodesUrl(null, "provider");
			fail("should have thrown exception on null parameter");
		} catch (WqpGatewayException e) {
			assertEquals("Expect param exception", METHOD_PARAM_NULL, e.getExceptionid());
		}
	}
	@Test
	public void testMakeUrl_nullCode() throws Exception {
		try {
			new CodesService().makeCodesUrl(Parameters.PROVIDERS, null);
			fail("should have thrown exception on null code");
		} catch (WqpGatewayException e) {
			assertEquals("Expect param exception", METHOD_PARAM_NULL, e.getExceptionid());
		}
	}
	@Test
	public void testMakeUrl_emptyCode() throws Exception {
		try {
			new CodesService().makeCodesUrl(Parameters.PROVIDERS, "");
			fail("should have thrown exception on empty string");
		} catch (WqpGatewayException e) {
			assertEquals("Expect param exception", METHOD_PARAM_EMPTY, e.getExceptionid());
		}
	}
	
	@Test
	public void testMakeUrl_thowsExceptionWhenNoUrl() {
		WqpConfig.set(CODES_URL, "");
		try {
			new CodesService().makeCodesUrl(Parameters.PROVIDERS, "provider");
			fail("should have thrown exception when no codes url");
		} catch (WqpGatewayException e) {
			assertTrue("Expect config exception", e.getExceptionid() == UNDEFINED_WQP_CONFIG_PARAM);
		}
	}
	
	@Test
	public void testMakeUrl_default() throws Exception {
		String baseUrl = "https://wqp.codes.usgs.gov/codes/";
		WqpConfig.set(CODES_URL, baseUrl);
		
		URL actualUrl = new CodesService().makeCodesUrl(Parameters.PROVIDERS, "provider");
		
		String expectedUrl = baseUrl +"/"+ Parameters.PROVIDERS +"/provider?mimetype=json";
		
		assertEquals(expectedUrl, actualUrl.toString());
	}
	
	
	@Test
	public void testMakeUrl_customMimeType() throws Exception {
		String baseUrl = "https://wqp.codes.usgs.gov/codes/";
		WqpConfig.set(CODES_URL, baseUrl);
		
		String mimeType = "xml";
		try {
			WqpConfig.set(CODES_MIME_TYPE, mimeType);
			
			URL actualUrl = new CodesService().makeCodesUrl(Parameters.PROVIDERS, "provider");
			
			String expectedUrl = baseUrl +"/"+ Parameters.PROVIDERS +"/provider?mimetype="+mimeType;
			
			assertEquals(expectedUrl, actualUrl.toString());
			
		} finally {
			WqpConfig.set(CODES_MIME_TYPE, null);
		}
	}

	
	@Test
	public void testMakeUrl_badURL() throws Exception {
//		TODO note that URL does not see this as a bad URL https://wqp.codes.usgs.gov***/& bad?? \t URL&";
//		TODO do we want more comprehensive validation?
		String baseUrl = "ht//tps://wqp.codes.usgs.gov/bad/URL/";
		WqpConfig.set(CODES_URL, baseUrl);
		
		try {
			URL actualUrl = new CodesService().makeCodesUrl(Parameters.PROVIDERS, "provider");
			System.out.println(actualUrl.toString());
			fail("should have thrown exception on bad URL");
		} catch (WqpGatewayException e) {
			assertEquals("Expect URL exception", URL_PARSING_EXCEPTION, e.getExceptionid());
		}
	}

	
	@Test
	public void testValidation_nullParam() throws Exception {
		try {
			new CodesService().validate(null, "provider");
			fail("should have thrown exception on null parameter");
		} catch (WqpGatewayException e) {
			assertTrue("Expect param exception", e.getExceptionid() == METHOD_PARAM_NULL);
		}
	}
	@Test
	public void testValidation_nullCode() throws Exception {
		try {
			new CodesService().validate(Parameters.PROVIDERS, null);
			fail("should have thrown exception on null code");
		} catch (WqpGatewayException e) {
			assertEquals("Expect param exception", METHOD_PARAM_NULL, e.getExceptionid());
		}
	}
	@Test
	public void testValidation_emptyCode() throws Exception {
		boolean actual = new CodesService().validate(Parameters.PROVIDERS, "");
		assertFalse("Empty string code is always invalid", actual);
	}
	
	
	@Test
	public void testValidation_validCode() throws Exception {
		// mock the fetcher
		boolean actual = new CodesService(){
			@Override
			public String fetch(Parameters codeType, String code) throws WqpGatewayException {
				return "provider";
			}
		}.validate(Parameters.PROVIDERS, "provider");
		
		assertTrue("Returned code is always valid", actual);
	}

	@Test
	public void testValidation_invalidCode() throws Exception {
		// mock the fetcher
		boolean actual = new CodesService(){
			@Override
			public String fetch(Parameters codeType, String code) throws WqpGatewayException {
				return "someOtherCode";
			}
		}.validate(Parameters.PROVIDERS, "invalidCode");
		
		assertFalse("Returned code is invalid if it does not contian the code", actual);
	}
	
	
	@Test
	public void testFetch_throwsExceptionWhenNoUrl() {
		WqpConfig.set(CODES_URL, "");
		try {
			new CodesService().fetch(Parameters.PROVIDERS, "b");
			fail("should have thrown exception when no codes url");
		} catch (WqpGatewayException e) {
			assertEquals("Expect config exception", UNDEFINED_WQP_CONFIG_PARAM, e.getExceptionid());
		}
	}

	@Test
	public void testFetch_testDataPipe() throws Exception {
		final String baseStr = "a byte stream";
		
		// mock the url stream
		String actualStr = new CodesService(){
			protected UrlStreamContainer makeProvider(Parameters codeType, String code) throws WqpGatewayException {
				UrlStreamContainer urlContainer = Mockito.mock(UrlStreamContainer.class);
				ByteArrayInputStream bais = new ByteArrayInputStream(baseStr.getBytes());
				try {
					TestUtils.refectSetValue(urlContainer, "logger", logger);
					Mockito.when(urlContainer.getName()).thenReturn("TestStream");
					Mockito.when(urlContainer.open()).thenReturn(bais);
				} catch (StreamInitException e) {
					fail("Failed to mock url container open()");
				}
				
				return urlContainer;
			};
		}.fetch(Parameters.PROVIDERS, "b");
		
		assertEquals("Expect stream string returned", baseStr, actualStr);
	}

	@Test
	public void testFetch_testServerNotAvailable() throws Exception {
		try {
			// mock the url stream
			new CodesService(){
				protected UrlStreamContainer makeProvider(Parameters codeType, String code) throws WqpGatewayException {
					UrlStreamContainer urlContainer = Mockito.mock(UrlStreamContainer.class);
					InputStream out = new InputStream() {
						@Override
						public int read() throws IOException {
							throw new RuntimeException();
						}
					};
					try {
						TestUtils.refectSetValue(urlContainer, "logger", logger);
						Mockito.when(urlContainer.getName()).thenReturn("TestStream");
						Mockito.when(urlContainer.open()).thenReturn(out);
					} catch (StreamInitException e) {
						fail("Failed to mock url container open()");
					}
					
					return urlContainer;
				};
			}.fetch(Parameters.PROVIDERS, "b");
			
			fail("should throw connection exception");
		} catch (WqpGatewayException e) {
			assertEquals("Expecting server request error", SERVER_REQUEST_IO_ERROR, e.getExceptionid());
		}
	}

	
	@Test
	public void testFetch_testServer404() throws Exception {
		// mock the url stream
		String actual = new CodesService(){
			@SuppressWarnings("unchecked")
			protected UrlStreamContainer makeProvider(Parameters codeType, String code) throws WqpGatewayException {
				UrlStreamContainer urlContainer = Mockito.mock(UrlStreamContainer.class);
				try {
					TestUtils.refectSetValue(urlContainer, "logger", logger);
					Mockito.when(urlContainer.getName()).thenReturn("TestStream");
					Mockito.when(urlContainer.open()).thenThrow(FileNotFoundException.class); // this is unchecked, why?
				} catch (StreamInitException e) {
					fail("Failed to mock url container open()");
				}
				
				return urlContainer;
			};
		}.fetch(Parameters.PROVIDERS, "b");
		
		String expect = "";
		assertEquals(expect,actual);
	}
	
	@Test
	public void testMakeProvider() throws Exception {
		String baseUrl = "https://wqp.codes.usgs.gov/codes/";
		WqpConfig.set(CODES_URL, baseUrl);
		UrlStreamContainer urlContainer = new CodesService().makeProvider(Parameters.PROVIDERS, "provider");
		assertNotNull("expect container instance",urlContainer);
		
		Object value = TestUtils.reflectValue(urlContainer, "url");
		assertNotNull("expect url instance", value);
		
		URL actualUrl = (URL)value;
		String expectedUrl = baseUrl +"/"+ Parameters.PROVIDERS +"/provider?mimetype=json";
		assertEquals(expectedUrl, actualUrl.toString());
	}
	
	
	// integration test
	public static void main(String[] args) throws Exception {
		WqpConfig.set(CODES_URL, "http://cida-eros-wqpdev.er.usgs.gov:8082/qw_portal_services/codes/");
		
		String value = new CodesService().fetch(Parameters.PROVIDERS, "NWIS");
		System.out.println(value);
		
		boolean valid = new CodesService().validate(Parameters.PROVIDERS, "NWIS");
		System.out.println(valid);

		boolean invalid = new CodesService().validate(Parameters.PROVIDERS, "SWIN");
		System.out.println(invalid);
	}
	
	
}
