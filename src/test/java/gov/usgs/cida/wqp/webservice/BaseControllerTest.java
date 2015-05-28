package gov.usgs.cida.wqp.webservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.transform.MapToDelimitedTransformer;
import gov.usgs.cida.wqp.transform.MapToJsonTransformer;
import gov.usgs.cida.wqp.transform.MapToKmlTransformer;
import gov.usgs.cida.wqp.transform.MapToXlsxTransformer;
import gov.usgs.cida.wqp.transform.MapToXmlTransformer;
import gov.usgs.cida.wqp.transform.Transformer;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.MimeType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class BaseControllerTest {

	@Mock
	private IStreamingDao streamingDao;
	@Mock
	private IParameterHandler parameterHandler;
	@Mock
	private ICountDao countDao;
	@Mock
	private ILogService logService;

	private TestBaseController testController;
	
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	testController = new TestBaseController(streamingDao, countDao, parameterHandler, logService);
    }

	@Test
	public void processParametersTest_empty() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ParameterMap pm = testController.processParameters(request);
		assertFalse(pm.isValid());
        verify(parameterHandler, never()).validateAndTransform(anyMap());
	}
	
	@Test
	public void processParametersTest_invalid() {
		ParameterMap p = new ParameterMap();
		p.setValid(false);
        when(parameterHandler.validateAndTransform(anyMap())).thenReturn(p);

        MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		ParameterMap pm = testController.processParameters(request);
		assertEquals(p, pm);
		assertFalse(pm.isValid());
        verify(parameterHandler).validateAndTransform(anyMap());
	}

	@Test
	public void processParametersTest_valid() {
		ParameterMap p = new ParameterMap();
        when(parameterHandler.validateAndTransform(anyMap())).thenReturn(p);

        MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		ParameterMap pm = testController.processParameters(request);
		assertEquals(p, pm);
		assertTrue(pm.isValid());
        verify(parameterHandler).validateAndTransform(anyMap());
	}

	@Test
	public void getZippedTest() {
		assertFalse(testController.isZipped(null, null));
		assertFalse(testController.isZipped("xxx", null));
		assertFalse(testController.isZipped("xxx", MimeType.csv.toString()));
		assertFalse(testController.isZipped("no", null));
		assertFalse(testController.isZipped("no", MimeType.kml.toString()));
		assertTrue(testController.isZipped("no", MimeType.kmz.toString()));
		assertTrue(testController.isZipped("yes", MimeType.kmz.toString()));
		assertTrue(testController.isZipped("yes", MimeType.json.toString()));
		assertTrue(testController.isZipped("yes", MimeType.csv.toString()));
	}

	@Test
	public void getOutputStreamTest() {
		HttpServletResponse response = new MockHttpServletResponse();
		try {
			OutputStream out = testController.getOutputStream(response, false, "abc");
			assertTrue(out instanceof ServletOutputStream);
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}

		try {
			OutputStream out = testController.getOutputStream(response, true, "abc");
			assertTrue(out instanceof ZipOutputStream);
			out.write("xyz".getBytes());
			((ZipOutputStream) out).closeEntry();
			((ZipOutputStream) out).finish();
			out.close();
			ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(((MockHttpServletResponse)response).getContentAsByteArray()));
	        ZipEntry entry = in.getNextEntry();
	        assertTrue(entry.getName().contentEquals("abc"));
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	@Test
	public void adjustMimeTypeTest() {
		assertEquals(MimeType.csv, testController.adjustMimeType(MimeType.csv, false));
		assertEquals(MimeType.csv, testController.adjustMimeType(MimeType.csv, true));
		
		assertEquals(MimeType.kml, testController.adjustMimeType(MimeType.kml, false));
		assertEquals(MimeType.kmz, testController.adjustMimeType(MimeType.kml, true));
	}
	
	@Test
	public void closeZipTest() {
		try {
			ByteArrayOutputStream response = new ByteArrayOutputStream();
			ZipOutputStream zipOut = new ZipOutputStream(response);

			zipOut.putNextEntry(new ZipEntry("abc"));
			zipOut.write("xyz".getBytes());
			testController.closeZip(zipOut);
			
			ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(response.toByteArray()));
			ZipEntry entry = in.getNextEntry();
			assertTrue(entry.getName().contentEquals("abc"));
			
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        int len;
	        byte[] buffer = new byte[1024];
	        while ((len = in.read(buffer)) > 0) {
	        	os.write(buffer, 0, len);
	        }
	        assertEquals("xyz", os.toString());
	        
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
	}

	@Test
	public void getContentHeaderTest() {
		assertEquals(HttpConstants.MIME_TYPE_CSV, testController.getContentHeader(false, MimeType.csv));
		assertEquals(HttpConstants.MIME_TYPE_ZIP, testController.getContentHeader(true, MimeType.csv));
		assertEquals(HttpConstants.MIME_TYPE_KML, testController.getContentHeader(false, MimeType.kml));
		assertEquals(HttpConstants.MIME_TYPE_KMZ, testController.getContentHeader(true, MimeType.kml));
		assertEquals(HttpConstants.MIME_TYPE_KMZ, testController.getContentHeader(false, MimeType.kmz));
		assertEquals(HttpConstants.MIME_TYPE_KMZ, testController.getContentHeader(false, MimeType.kmz));
	}

	@Test
	public void getAttachementFileNameTest() {
		assertEquals("abc.csv", testController.getAttachementFileName(false, MimeType.csv, "ABC"));
		assertEquals("abc.csv", testController.getAttachementFileName(false, MimeType.csv, "AbC"));
		assertEquals("abc.zip", testController.getAttachementFileName(true, MimeType.csv, "ABC"));
		assertEquals("abc.kml", testController.getAttachementFileName(false, MimeType.kml, "ABC"));
		assertEquals("abc.kmz", testController.getAttachementFileName(true, MimeType.kml, "ABC"));
		assertEquals("abc.kmz", testController.getAttachementFileName(false, MimeType.kmz, "ABC"));
		assertEquals("abc.kmz", testController.getAttachementFileName(false, MimeType.kmz, "ABC"));
	}

	@Test
	public void getNamespaceTest() {
		assertEquals("abc", testController.getNamespace("abc", MimeType.csv));
		assertEquals(IDao.STATION_KML_NAMESPACE, testController.getNamespace("abc", MimeType.kmz));
		assertEquals("abc", testController.getNamespace("abc", MimeType.xml));
		assertEquals(IDao.STATION_KML_NAMESPACE, testController.getNamespace("abc", MimeType.kml));
		assertEquals("abc", testController.getNamespace("abc", MimeType.json));
	}

	@Test
	public void getTransformer() {
		assertTrue(testController.getTransformer(MimeType.json, null, null) instanceof MapToJsonTransformer);
		assertTrue(testController.getTransformer(MimeType.xlsx, null, null) instanceof MapToXlsxTransformer);
		assertTrue(testController.getTransformer(MimeType.xml, null, null) instanceof MapToXmlTransformer);
		assertTrue(testController.getTransformer(MimeType.kml, null, null) instanceof MapToKmlTransformer);
		assertTrue(testController.getTransformer(MimeType.kmz, null, null) instanceof MapToKmlTransformer);

		Transformer t = testController.getTransformer(MimeType.tsv, null, null);
		assertTrue(t instanceof MapToDelimitedTransformer);
		assertEquals(MapToDelimitedTransformer.TAB, ((MapToDelimitedTransformer) t).getDelimiter());
		
		t = testController.getTransformer(MimeType.csv, null, null);
		assertTrue(t instanceof MapToDelimitedTransformer);
		assertEquals(MapToDelimitedTransformer.COMMA, ((MapToDelimitedTransformer) t).getDelimiter());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doHeaderTest_NoParms() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        BigDecimal logId = BigDecimal.ONE;
        String mybatisNamespace = "namepspace"; 
        String endpoint = "endpoint";
		
        assertFalse(testController.doHeader(request, response, logId, mybatisNamespace, endpoint));
        assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
        verify(parameterHandler, never()).validateAndTransform(anyMap());
        verify(logService, never()).logHeadComplete(response, logId);
        verify(countDao, never()).getCounts(anyString(), anyMap());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doHeaderTest_InvalidParms() {
		ParameterMap p = new ParameterMap();
		p.merge("countrycode", Arrays.asList("not cool"));
		p.setValid(false);
        when(parameterHandler.validateAndTransform(anyMap())).thenReturn(p);

        MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
        MockHttpServletResponse response = new MockHttpServletResponse();
        BigDecimal logId = BigDecimal.ONE;
        String mybatisNamespace = "namepspace"; 
        String endpoint = "endpoint";
		
        assertFalse(testController.doHeader(request, response, logId, mybatisNamespace, endpoint));
        assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(response.getHeader(HttpConstants.HEADER_WARNING).startsWith("299 WQP \"not cool\""));
        verify(parameterHandler).validateAndTransform(anyMap());
        verify(logService, never()).logHeadComplete(response, logId);
        verify(countDao, never()).getCounts(anyString(), anyMap());
	}


	@SuppressWarnings("unchecked")
	@Test
	public void doHeaderTest() {
		Map<String, Object> q = new HashMap<>();
		q.put("mimeType", "kml");
		q.put("zip", "yes");
		ParameterMap p = new ParameterMap();
		p.setQueryParameters(q);
        when(parameterHandler.validateAndTransform(anyMap())).thenReturn(p);

        MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
        MockHttpServletResponse response = new MockHttpServletResponse();
        BigDecimal logId = BigDecimal.ONE;
        String mybatisNamespace = "namepspace"; 
        String endpoint = "endpoint";
		
        assertTrue(testController.doHeader(request, response, logId, mybatisNamespace, endpoint));
        assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
        assertEquals("application/vnd.google-earth.kmz;charset=UTF-8", response.getHeader(HttpConstants.HEADER_CONTENT_TYPE));
        assertEquals("attachment; filename=endpoint.kmz", response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
        assertEquals("12", response.getHeader("TEST-COUNT"));
        verify(parameterHandler).validateAndTransform(anyMap());
        verify(logService).logHeadComplete(response, logId);
        verify(countDao).getCounts(anyString(), anyMap());
	}

	@Test
	public void doHeadRequestTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        String mybatisNamespace = "namepspace"; 
        String endpoint = "endpoint";
		
        testController.doHeadRequest(request, response, mybatisNamespace, endpoint);
        
        assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
        verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class));
        verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
	}

	@Test
	public void doHeadRequestTest_error() {
        when(parameterHandler.validateAndTransform(anyMap())).thenThrow(new RuntimeException("test"));
        MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
        MockHttpServletResponse response = new MockHttpServletResponse();
        String mybatisNamespace = "namepspace"; 
        String endpoint = "endpoint";
		
        try {
        	testController.doHeadRequest(request, response, mybatisNamespace, endpoint);
        } catch (RuntimeException e) {
        	if (!"test".equalsIgnoreCase(e.getMessage())) {
        		fail("not the exception we were expecting");
        	}
        }
        
        assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
//        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
        verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class));
        verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
       
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doGetRequestTest_NoParms() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        String mybatisNamespace = "namepspace"; 
        String endpoint = "endpoint";
		
        testController.doGetRequest(request, response, mybatisNamespace, endpoint);
        assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
        verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class));
        verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
        verify(streamingDao, never()).stream(anyString(), anyMap(), any(ResultHandler.class));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doGetRequestTest_error() {
        when(parameterHandler.validateAndTransform(anyMap())).thenThrow(new RuntimeException("test"));
        MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
        MockHttpServletResponse response = new MockHttpServletResponse();
        String mybatisNamespace = "namepspace"; 
        String endpoint = "endpoint";
        
        try {
        	testController.doGetRequest(request, response, mybatisNamespace, endpoint);
        } catch (RuntimeException e) {
        	if (!"java.lang.RuntimeException: test".equalsIgnoreCase(e.getMessage())) {
        		fail("not the exception we were expecting");
        	}
        }
        
        assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
//        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
        verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class));
        verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
        verify(streamingDao, never()).stream(anyString(), anyMap(), any(ResultHandler.class));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void doGetRequestTest() {
		Map<String, Object> q = new HashMap<>();
		q.put("mimeType", "kml");
		q.put("zip", "yes");
		ParameterMap p = new ParameterMap();
		p.setQueryParameters(q);
        when(parameterHandler.validateAndTransform(anyMap())).thenReturn(p);

        MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
        MockHttpServletResponse response = new MockHttpServletResponse();
        String mybatisNamespace = "namepspace"; 
        String endpoint = "endpoint";
		
        testController.doGetRequest(request, response, mybatisNamespace, endpoint);
        assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
        assertEquals("application/vnd.google-earth.kmz;charset=UTF-8", response.getHeader(HttpConstants.HEADER_CONTENT_TYPE));
        assertEquals("attachment; filename=endpoint.kmz", response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
        assertEquals("12", response.getHeader("TEST-COUNT"));

		ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(response.getContentAsByteArray()));
		try {
			ZipEntry entry = in.getNextEntry();
			assertTrue(entry.getName().contentEquals("endpoint.kmz"));
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}
      
        verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class));
        verify(parameterHandler).validateAndTransform(anyMap());
        verify(countDao).getCounts(anyString(), anyMap());
        verify(logService).logHeadComplete(any(HttpServletResponse.class), any(BigDecimal.class));
        verify(streamingDao).stream(anyString(), anyMap(), any(ResultHandler.class));
        verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
	}

}
