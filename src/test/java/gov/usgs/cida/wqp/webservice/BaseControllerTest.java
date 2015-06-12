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
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.transform.MapToDelimitedTransformer;
import gov.usgs.cida.wqp.transform.MapToJsonTransformer;
import gov.usgs.cida.wqp.transform.MapToKmlTransformer;
import gov.usgs.cida.wqp.transform.MapToXlsxTransformer;
import gov.usgs.cida.wqp.transform.MapToXmlTransformer;
import gov.usgs.cida.wqp.transform.Transformer;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.MimeType;
import gov.usgs.cida.wqp.util.MybatisConstants;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
    	testController = new TestBaseController(streamingDao, countDao, parameterHandler, logService, 100);
    }

	@Test
	public void processParametersTest_empty() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		testController.processParameters(request);
		assertFalse(testController.pm.isValid());
        verify(parameterHandler, never()).validateAndTransform(anyMap());
	}
	
	@Test
	public void processParametersTest_invalid() {
		ParameterMap p = new ParameterMap();
		p.setValid(false);
        when(parameterHandler.validateAndTransform(anyMap())).thenReturn(p);

        MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		testController.processParameters(request);
		assertEquals(p, testController.pm);
		assertFalse(testController.pm.isValid());
        verify(parameterHandler).validateAndTransform(anyMap());
	}

	@Test
	public void processParametersTest_valid() {
		ParameterMap p = new ParameterMap();
        when(parameterHandler.validateAndTransform(anyMap())).thenReturn(p);

        MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		testController.processParameters(request);
		assertEquals(p, testController.pm);
		assertTrue(testController.pm.isValid());
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
			assertTrue(out instanceof BufferedOutputStream);
		} catch (RuntimeException e) {
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
	public void getZipEntryNameTest() {
		assertEquals("abc.csv", testController.getZipEntryName("abc", MimeType.csv));
		assertEquals("abc.csv", testController.getZipEntryName("AbC", MimeType.csv));
		assertEquals("abc.csv", testController.getZipEntryName("ABC", MimeType.csv));
		assertEquals("abc.kml", testController.getZipEntryName("ABC", MimeType.kml));
		assertEquals("abc.kml", testController.getZipEntryName("ABC", MimeType.kml));
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
        assertEquals("12", response.getHeader(TestBaseController.TEST_COUNT));
        verify(parameterHandler).validateAndTransform(anyMap());
        verify(logService).logHeadComplete(response, logId);
        verify(countDao).getCounts(anyString(), anyMap());
	}

	@Test
	public void checkMaxRowsTest() {
		TestBaseController small = new TestBaseController(null, null, null, null, 10);
		small.pm = new ParameterMap();

		//xml formats ok when less than max & always sorted
        small.mimeType = MimeType.xml;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "yes", false);

        small.mimeType = MimeType.kml;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "yes", false);

        small.mimeType = MimeType.kmz;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "yes", false);

		//xml formats not ok when greater than max
        small.mimeType = MimeType.xml;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowFalseAsserts(small, "15");
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowFalseAsserts(small, "15");
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowFalseAsserts(small, "15");

        small.mimeType = MimeType.kml;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowFalseAsserts(small, "15");
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowFalseAsserts(small, "15");
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowFalseAsserts(small, "15");

        small.mimeType = MimeType.kmz;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowFalseAsserts(small, "15");
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowFalseAsserts(small, "15");
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowFalseAsserts(small, "15");

		//other formats less than max always ok & sorting based on given (or lack of) sorted query parameter
        small.mimeType = MimeType.csv;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "no", false);

        small.mimeType = MimeType.tsv;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "no", false);

		small.mimeType = MimeType.xlsx;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "no", false);
        
        //other formats are ok, but not sorted when greater than max - and warning header given
        small.mimeType = MimeType.csv;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "15", "no", true);

        small.mimeType = MimeType.tsv;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "15", "no", true);

		small.mimeType = MimeType.xlsx;
		//No query parm
		small.pm.getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a yes
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a no
		small.pm.getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "15", "no", true);
	}

	public void doMaxRowTrueAsserts(TestBaseController small, String rows, String expectedSort, boolean isHeaderexpected) {
        HttpServletResponse response = new MockHttpServletResponse();
        assertTrue(small.checkMaxRows(response, rows));
        assertTrue(small.pm.getQueryParameters().containsKey(Parameters.SORTED.toString()));
        assertEquals(expectedSort, small.pm.getQueryParameters().get(Parameters.SORTED.toString()));
        if (isHeaderexpected) {
            assertEquals(1, response.getHeaderNames().size());
            assertTrue(response.getHeaderNames().contains(HttpConstants.HEADER_WARNING));
            assertEquals("This query will return in excess of 10 results, the data will not be sorted.",
            		response.getHeader(HttpConstants.HEADER_WARNING));
        } else {
            assertEquals(0, response.getHeaderNames().size());
        }
	}

	public void doMaxRowFalseAsserts(TestBaseController small, String rows) {
        HttpServletResponse response = new MockHttpServletResponse();
        assertFalse(small.checkMaxRows(response, rows));
        assertEquals(1, response.getHeaderNames().size());
        assertTrue(response.getHeaderNames().contains(HttpConstants.HEADER_WARNING));
        assertEquals("This query will return in excess of 10 results, please refine your query.",
            		response.getHeader(HttpConstants.HEADER_WARNING));
        assertEquals(400, response.getStatus());
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
        		e.printStackTrace();
        		fail("not the exception we were expecting: " + e.getMessage());
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
        assertEquals("12", response.getHeader(TestBaseController.TEST_COUNT));

		ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(response.getContentAsByteArray()));
		try {
			ZipEntry entry = in.getNextEntry();
			assertTrue(entry.getName().contentEquals("endpoint.kml"));
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

	@Test
	public void test_warningHeader_defaultsCodeAndError() {
		String expect = "299 WQP \"Unknown error\" date";
		assertEquals(expect, testController.warningHeader(null, null, "date"));
	}

	@Test
	public void test_warningHeader_codeAndErrorText() {
		String expect = "505 WQP \"Param error\" date";
		assertEquals(expect, testController.warningHeader(505, "Param error", "date"));
	}

	@Test
	public void test_warningHeader() {
		String expect = "505 WQP \"Param error\" " + new Date().toString();
		assertEquals(expect, testController.warningHeader(505, "Param error", null));
		System.out.println(expect);
	}

	@Test
	public void test_writeWarningHeaders() throws UnsupportedEncodingException {
		MockHttpServletResponse response = new MockHttpServletResponse();
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("abc", Arrays.asList("warning1", "warning2"));
		map.put("def", Arrays.asList("warning3", "warning4"));
		testController.writeWarningHeaders(response, map);
		assertTrue( response.containsHeader("Warning") );
		assertEquals("", response.getContentAsString());
		assertEquals( 4, response.getHeaderValues("Warning").size() );
		String warning = response.getHeaderValues("Warning").toString();
		assertTrue( warning.contains("299 WQP \"warning1\"") );
		assertTrue( warning.contains("299 WQP \"warning2\"") );
		assertTrue( warning.contains("299 WQP \"warning3\"") );
		assertTrue( warning.contains("299 WQP \"warning4\"") );
	}
	
	private void addEntryStation(String ds, Integer en, List<Map<String, Object>>  list) {
		Map<String, Object> count = new HashMap<String, Object>();
		count.put(MybatisConstants.DATA_SOURCE,ds);
		count.put(MybatisConstants.STATION_COUNT,en);
		list.add(count);
	}

	@Test
	public void test_addSiteCountHeader_proper() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryStation("NWIS", 7, counts);
		addEntryStation("STEW", 5, counts);
		addEntryStation(null, 12, counts);
		testController.addSiteHeaders(response, counts);
		assertEquals(3, response.getHeaderNames().size());
		String nwis = "NWIS"+HttpConstants.HEADER_DELIMITER+HttpConstants.HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(nwis));
		String stew = "STEW"+HttpConstants.HEADER_DELIMITER+HttpConstants.HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(stew));
		assertTrue(response.containsHeader(HttpConstants.HEADER_TOTAL_SITE_COUNT));
		assertEquals("7", response.getHeader(nwis));
		assertEquals("5", response.getHeader(stew));
		assertEquals("12", response.getHeader(HttpConstants.HEADER_TOTAL_SITE_COUNT));
	}

	@Test
	public void test_addSiteCountHeader_noTotal() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryStation("NWIS", 7, counts);
		addEntryStation("STEW", 5, counts);
		testController.addSiteHeaders(response, counts);
		assertEquals(3, response.getHeaderNames().size());
		String nwis = "NWIS"+HttpConstants.HEADER_DELIMITER+HttpConstants.HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(nwis));
		String stew = "STEW"+HttpConstants.HEADER_DELIMITER+HttpConstants.HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(stew));
		assertTrue(response.containsHeader(HttpConstants.HEADER_TOTAL_SITE_COUNT));
		assertEquals("7", response.getHeader(nwis));
		assertEquals("5", response.getHeader(stew));
		assertEquals("0", response.getHeader(HttpConstants.HEADER_TOTAL_SITE_COUNT));
	}

	@Test
	public void determineHeaderNameTest() {
		Map<String, Object> count = new LinkedHashMap<>();
		assertEquals("Total-", testController.determineHeaderName(null, null));
		assertEquals("Total-", testController.determineHeaderName(count, null));
		assertEquals("Total-abc", testController.determineHeaderName(null, "abc"));
		count.put("abc", "123");
		count.put("def", "456");
		count.put(MybatisConstants.DATA_SOURCE, null);
		assertEquals("Total-xyz", testController.determineHeaderName(count, "xyz"));
		count.put(MybatisConstants.DATA_SOURCE, "dude");
		assertEquals("dude-xyz", testController.determineHeaderName(count, "xyz"));
	}

	@Test
	public void determineHeaderValueTest() {
		Map<String, Object> count = new LinkedHashMap<>();
		assertEquals("0", testController.determineHeaderValue(null, null));
		assertEquals("0", testController.determineHeaderValue(count, null));
		assertEquals("0", testController.determineHeaderValue(null, "abc"));
		count.put("abc", "123");
		count.put("def", "456");
		count.put("ghi", null);
		assertEquals("0", testController.determineHeaderValue(count, "xyz"));
		assertEquals("123", testController.determineHeaderValue(count, "abc"));
		assertEquals("456", testController.determineHeaderValue(count, "def"));
		assertEquals("0", testController.determineHeaderValue(count, "ghi"));
	}
}
