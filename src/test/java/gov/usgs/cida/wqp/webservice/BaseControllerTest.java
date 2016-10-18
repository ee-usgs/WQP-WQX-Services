package gov.usgs.cida.wqp.webservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
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
		testController = new TestBaseController(streamingDao, countDao, parameterHandler, logService, 100, "http://test-url.usgs.gov");
	}

	@Test
	@SuppressWarnings("unchecked")
	public void processParametersTest_empty() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		testController.processParameters(request, null);
		assertFalse(TestBaseController.getPm().isValid());
		verify(parameterHandler, never()).validateAndTransform(anyMap(), anyMap());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void processParametersTest_invalid() {
		ParameterMap p = new ParameterMap();
		p.setValid(false);
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenReturn(p);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		testController.processParameters(request, null);
		assertEquals(p, TestBaseController.getPm());
		assertFalse(TestBaseController.getPm().isValid());
		verify(parameterHandler).validateAndTransform(anyMap(), anyMap());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void processParametersTest_valid() {
		ParameterMap p = new ParameterMap();
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenReturn(p);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		testController.processParameters(request, null);
		assertEquals(p, TestBaseController.getPm());
		assertTrue(TestBaseController.getPm().isValid());
		verify(parameterHandler).validateAndTransform(anyMap(), anyMap());
	}

	@Test
	public void determineZippedTest() {
		assertFalse(testController.determineZipped(null, null));
		assertFalse(testController.determineZipped("xxx", null));
		assertFalse(testController.determineZipped("xxx", MimeType.csv.toString()));
		assertFalse(testController.determineZipped("no", null));
		assertFalse(testController.determineZipped("no", MimeType.kml.toString()));
		assertTrue(testController.determineZipped("no", MimeType.kmz.toString()));
		assertTrue(testController.determineZipped("yes", MimeType.kmz.toString()));
		assertTrue(testController.determineZipped("yes", MimeType.json.toString()));
		assertTrue(testController.determineZipped("yes", MimeType.csv.toString()));
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
	public void determineMimeTypeTest() {
		assertEquals(MimeType.csv, testController.determineMimeType(MimeType.csv, false));
		assertEquals(MimeType.csv, testController.determineMimeType(MimeType.csv, true));

		assertEquals(MimeType.kml, testController.determineMimeType(MimeType.kml, false));
		assertEquals(MimeType.kmz, testController.determineMimeType(MimeType.kml, true));
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
		TestBaseController.setMimeType(MimeType.csv);
		TestBaseController.setZipped(false);
		assertEquals(HttpConstants.MIME_TYPE_CSV, testController.getContentHeader());

		TestBaseController.setMimeType(MimeType.csv);
		TestBaseController.setZipped(true);
		assertEquals(HttpConstants.MIME_TYPE_ZIP, testController.getContentHeader());

		TestBaseController.setMimeType(MimeType.kml);
		TestBaseController.setZipped(false);
		assertEquals(HttpConstants.MIME_TYPE_KML, testController.getContentHeader());

		TestBaseController.setMimeType(MimeType.kml);
		TestBaseController.setZipped(true);
		assertEquals(HttpConstants.MIME_TYPE_KMZ, testController.getContentHeader());

		TestBaseController.setMimeType(MimeType.kmz);
		TestBaseController.setZipped(false);
		assertEquals(HttpConstants.MIME_TYPE_KMZ, testController.getContentHeader());

		TestBaseController.setMimeType(MimeType.kmz);
		TestBaseController.setZipped(false);
		assertEquals(HttpConstants.MIME_TYPE_KMZ, testController.getContentHeader());
	}

	@Test
	public void determinBaseFileNameTest() {
		TestBaseController.setProfile(Profile.STATION);
		assertEquals("station", testController.determineBaseFileName());
		
		TestBaseController.setProfile(Profile.PC_RESULT);
		assertEquals("result", testController.determineBaseFileName());

		TestBaseController.setProfile(Profile.BIOLOGICAL);
		assertEquals("biologicalresult", testController.determineBaseFileName());
	}

	@Test
	public void getAttachementFileNameTest() {
		TestBaseController.setMimeType(MimeType.csv);
		TestBaseController.setZipped(false);
		TestBaseController.setProfile(Profile.STATION);
		assertEquals("station.csv", testController.getAttachementFileName());

		TestBaseController.setZipped(true);
		assertEquals("station.zip", testController.getAttachementFileName());

		TestBaseController.setMimeType(MimeType.kml);
		assertEquals("station.kmz", testController.getAttachementFileName());

		TestBaseController.setMimeType(MimeType.kmz);
		assertEquals("station.kmz", testController.getAttachementFileName());

		TestBaseController.setMimeType(MimeType.tsv);
		TestBaseController.setZipped(false);
		TestBaseController.setProfile(Profile.PC_RESULT);
		assertEquals("result.tsv", testController.getAttachementFileName());

		TestBaseController.setMimeType(MimeType.xml);
		TestBaseController.setProfile(Profile.BIOLOGICAL);
		assertEquals("biologicalresult.xml", testController.getAttachementFileName());
	}

	@Test
	public void determineZipEntryNameTest() {
		TestBaseController.setProfile(Profile.STATION);
		TestBaseController.setMimeType(MimeType.csv);
		assertEquals("station.csv", testController.determineZipEntryName());
		TestBaseController.setMimeType(MimeType.kml);
		assertEquals("station.kml", testController.determineZipEntryName());
		TestBaseController.setMimeType(MimeType.kmz);
		assertEquals("station.kml", testController.determineZipEntryName());
		TestBaseController.setProfile(Profile.PC_RESULT);
		TestBaseController.setMimeType(MimeType.xml);
		assertEquals("result.xml", testController.determineZipEntryName());
	}

	@Test
	public void determineNamespaceTest() {
		TestBaseController.setMimeType(MimeType.kml);
		assertEquals(IDao.STATION_KML_NAMESPACE, testController.determineNamespace());

		TestBaseController.setMimeType(MimeType.kmz);
		assertEquals(IDao.STATION_KML_NAMESPACE, testController.determineNamespace());

		TestBaseController.setMimeType(MimeType.geojson);
		assertEquals(IDao.SIMPLE_STATION_NAMESPACE, testController.determineNamespace());

		TestBaseController.setMimeType(MimeType.csv);
		TestBaseController.setProfile(Profile.BIOLOGICAL);
		assertEquals(IDao.BIOLOGICAL_RESULT_NAMESPACE, testController.determineNamespace());

		TestBaseController.setProfile(Profile.PC_RESULT);
		assertEquals(IDao.RESULT_NAMESPACE, testController.determineNamespace());

		TestBaseController.setProfile(Profile.SIMPLE_STATION);
		assertEquals(IDao.SIMPLE_STATION_NAMESPACE, testController.determineNamespace());

		TestBaseController.setProfile(Profile.STATION);
		assertEquals(IDao.STATION_NAMESPACE, testController.determineNamespace());

		TestBaseController.setProfile("");
		assertEquals("", testController.determineNamespace());
	}

	@Test
	public void getTransformerTest() {
		TestBaseController.setMimeType(MimeType.json);
		assertTrue(testController.getTransformer(null, null) instanceof MapToJsonTransformer);
		TestBaseController.setMimeType(MimeType.geojson);
		assertTrue(testController.getTransformer(null, null) instanceof MapToJsonTransformer);
		TestBaseController.setMimeType(MimeType.xlsx);
		assertTrue(testController.getTransformer(null, null) instanceof MapToXlsxTransformer);
		TestBaseController.setMimeType(MimeType.xml);
		assertTrue(testController.getTransformer(null, null) instanceof MapToXmlTransformer);
		TestBaseController.setMimeType(MimeType.kml);
		assertTrue(testController.getTransformer(null, null) instanceof MapToKmlTransformer);
		TestBaseController.setMimeType(MimeType.kmz);
		assertTrue(testController.getTransformer(null, null) instanceof MapToKmlTransformer);

		TestBaseController.setMimeType(MimeType.tsv);
		Transformer t = testController.getTransformer(null, null);
		assertTrue(t instanceof MapToDelimitedTransformer);
		assertEquals(MapToDelimitedTransformer.TAB, ((MapToDelimitedTransformer) t).getDelimiter());

		TestBaseController.setMimeType(MimeType.csv);
		t = testController.getTransformer(null, null);
		assertTrue(t instanceof MapToDelimitedTransformer);
		assertEquals(MapToDelimitedTransformer.COMMA, ((MapToDelimitedTransformer) t).getDelimiter());
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.STATION, testController.determineProfile(Profile.STATION, null));

		Map<String, Object> pm = new HashMap<>();
		pm.put(Parameters.DATA_PROFILE.toString(), null);
		assertEquals(Profile.STATION, testController.determineProfile(Profile.STATION, pm));

		pm.put(Parameters.DATA_PROFILE.toString(), null);
		assertEquals(Profile.STATION, testController.determineProfile(Profile.STATION, pm));

		pm.put(Parameters.DATA_PROFILE.toString(), "abc");
		assertEquals(Profile.STATION, testController.determineProfile(Profile.STATION, pm));

		pm.put(Parameters.DATA_PROFILE.toString(), new String[0]);
		assertEquals(Profile.STATION, testController.determineProfile(Profile.STATION, pm));

		pm.put(Parameters.DATA_PROFILE.toString(), new String[]{"biological"});
		assertEquals(Profile.BIOLOGICAL, testController.determineProfile(Profile.STATION, pm));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void doCommonSetupTest_NoParms() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		BigDecimal logId = BigDecimal.ONE;
		TestBaseController.setLogId(logId);

		assertFalse(testController.doCommonSetup(request, response, null));
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		verify(parameterHandler, never()).validateAndTransform(anyMap(), anyMap());
		verify(logService, never()).logHeadComplete(response, logId);
		verify(countDao, never()).getCounts(anyString(), anyMap());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void doCommonSetupTest_InvalidParms() {
		ParameterMap p = new ParameterMap();
		p.merge("countrycode", Arrays.asList("not cool"));
		p.setValid(false);
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenReturn(p);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		MockHttpServletResponse response = new MockHttpServletResponse();
		BigDecimal logId = BigDecimal.ONE;
		TestBaseController.setLogId(logId);

		assertFalse(testController.doCommonSetup(request, response, null));
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertTrue(response.getHeader(HttpConstants.HEADER_WARNING).startsWith("299 WQP \"not cool\""));
		verify(parameterHandler).validateAndTransform(anyMap(), anyMap());
		verify(logService, never()).logHeadComplete(response, logId);
		verify(countDao, never()).getCounts(anyString(), anyMap());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void doCommonSetupTest() {
		Map<String, Object> q = new HashMap<>();
		q.put("mimeType", "kml");
		q.put("zip", "yes");
		q.put(Parameters.DATA_PROFILE.toString(), Profile.STATION);
		ParameterMap p = new ParameterMap();
		p.setQueryParameters(q);
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenReturn(p);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		MockHttpServletResponse response = new MockHttpServletResponse();
		BigDecimal logId = BigDecimal.ONE;
		TestBaseController.setLogId(logId);

		assertTrue(testController.doCommonSetup(request, response, null));
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		assertEquals("application/vnd.google-earth.kmz;charset=UTF-8", response.getHeader(HttpConstants.HEADER_CONTENT_TYPE));
		assertEquals("attachment; filename=station.kmz", response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("12", response.getHeader(TestBaseController.TEST_COUNT));
		verify(parameterHandler).validateAndTransform(anyMap(), anyMap());
		verify(logService).logHeadComplete(response, logId);
		verify(countDao).getCounts(anyString(), anyMap());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void doCommonSetupPostCountTest() {
		Map<String, Object> q = new HashMap<>();
		q.put(Parameters.MIMETYPE.toString(), "json");
		q.put(Parameters.DATA_PROFILE.toString(), Profile.STATION);
		ParameterMap p = new ParameterMap();
		p.setQueryParameters(q);
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenReturn(p);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter(Parameters.COUNTRY.toString(), "US");
		request.setMethod("POST");
		MockHttpServletResponse response = new MockHttpServletResponse();
		BigDecimal logId = BigDecimal.ONE;
		TestBaseController.setLogId(logId);

		assertTrue(testController.doCommonSetup(request, response, null));
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		assertEquals("application/json;charset=UTF-8", response.getHeader(HttpConstants.HEADER_CONTENT_TYPE));
		assertEquals("attachment; filename=station.json", response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("12", response.getHeader(TestBaseController.TEST_COUNT));
		verify(parameterHandler).validateAndTransform(anyMap(), anyMap());
		verify(logService).logHeadComplete(response, logId);
		verify(countDao).getCounts(anyString(), anyMap());

		request.setRequestURI("endpoint/count");
		response = new MockHttpServletResponse();
		assertTrue(testController.doCommonSetup(request, response, null));
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		assertEquals("application/json;charset=UTF-8", response.getHeader(HttpConstants.HEADER_CONTENT_TYPE));
		assertNull(response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("12", response.getHeader(TestBaseController.TEST_COUNT));
		verify(parameterHandler, times(2)).validateAndTransform(anyMap(), anyMap());
		//logService is only one because we created a new response
		verify(logService, times(1)).logHeadComplete(response, logId);
		verify(countDao, times(2)).getCounts(anyString(), anyMap());

		request.setMethod("GET");
		response = new MockHttpServletResponse();
		assertTrue(testController.doCommonSetup(request, response, null));
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		assertEquals("application/json;charset=UTF-8", response.getHeader(HttpConstants.HEADER_CONTENT_TYPE));
		assertEquals("attachment; filename=station.json", response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("12", response.getHeader(TestBaseController.TEST_COUNT));
		verify(parameterHandler, times(3)).validateAndTransform(anyMap(), anyMap());
		//logService is only one because we created a new response
		verify(logService, times(1)).logHeadComplete(response, logId);
		verify(countDao, times(3)).getCounts(anyString(), anyMap());	
	}

	@Test
	public void checkMaxRowsTest() {
		TestBaseController small = new TestBaseController(null, null, null, null, 10, "http://test-url.usgs.gov");
		TestBaseController.setPm(new ParameterMap());

		//xml formats ok when less than max & always sorted
		TestBaseController.setMimeType(MimeType.xml);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "yes", false);

		TestBaseController.setMimeType(MimeType.kml);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "yes", false);

		TestBaseController.setMimeType(MimeType.kmz);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "yes", false);

		//xml formats not ok when greater than max
		TestBaseController.setMimeType(MimeType.xml);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowFalseAsserts(small, "15");
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowFalseAsserts(small, "15");
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowFalseAsserts(small, "15");

		TestBaseController.setMimeType(MimeType.kml);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowFalseAsserts(small, "15");
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowFalseAsserts(small, "15");
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowFalseAsserts(small, "15");

		TestBaseController.setMimeType(MimeType.kmz);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowFalseAsserts(small, "15");
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowFalseAsserts(small, "15");
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowFalseAsserts(small, "15");

		//other formats less than max always ok & sorting based on given (or lack of) sorted query parameter
		TestBaseController.setMimeType(MimeType.csv);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "no", false);

		TestBaseController.setMimeType(MimeType.tsv);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "no", false);

		TestBaseController.setMimeType(MimeType.xlsx);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "5", "yes", false);
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "5", "no", false);

		//other formats are ok, but not sorted when greater than max - and warning header given
		TestBaseController.setMimeType(MimeType.csv);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "15", "no", true);

		TestBaseController.setMimeType(MimeType.tsv);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "15", "no", true);

		TestBaseController.setMimeType(MimeType.xlsx);
		//No query parm
		TestBaseController.getPm().getQueryParameters().clear();
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a yes
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "yes");
		doMaxRowTrueAsserts(small, "15", "no", true);
		//now for a no
		TestBaseController.getPm().getQueryParameters().put(Parameters.SORTED.toString(), "no");
		doMaxRowTrueAsserts(small, "15", "no", true);
	}

	public void doMaxRowTrueAsserts(TestBaseController small, String rows, String expectedSort, boolean isHeaderexpected) {
		HttpServletResponse response = new MockHttpServletResponse();
		assertTrue(small.checkMaxRows(response, rows));
		assertTrue(TestBaseController.getPm().getQueryParameters().containsKey(Parameters.SORTED.toString()));
		assertEquals(expectedSort, TestBaseController.getPm().getQueryParameters().get(Parameters.SORTED.toString()));
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
	@SuppressWarnings("unchecked")
	public void doHeadRequestTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		testController.doHeadRequest(request, response);

		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(Map.class));
		verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void doHeadRequestTest_error() {
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenThrow(new RuntimeException("test"));
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		MockHttpServletResponse response = new MockHttpServletResponse();

		try {
			testController.doHeadRequest(request, response);
		} catch (RuntimeException e) {
			if (!"test".equalsIgnoreCase(e.getMessage())) {
				fail("not the exception we were expecting");
			}
		}

		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
//		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(Map.class));
		verify(logService).logRequestComplete(any(BigDecimal.class), anyString());

	}

	@Test
	@SuppressWarnings("unchecked")
	public void doGetRequestTest_NoParms() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		testController.doGetRequest(request, response);
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(Map.class));
		verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
		verify(streamingDao, never()).stream(anyString(), anyMap(), any(ResultHandler.class));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void doGetRequestTest_error() {
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenThrow(new RuntimeException("test"));
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		MockHttpServletResponse response = new MockHttpServletResponse();

		try {
			testController.doGetRequest(request, response);
		} catch (RuntimeException e) {
			if (!"java.lang.RuntimeException: test".equalsIgnoreCase(e.getMessage())) {
				e.printStackTrace();
				fail("not the exception we were expecting: " + e.getMessage());
			}
		}

		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
//		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(Map.class));
		verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
		verify(streamingDao, never()).stream(anyString(), anyMap(), any(ResultHandler.class));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void doGetRequestTest() {
		Map<String, Object> q = new HashMap<>();
		q.put("mimeType", "kml");
		q.put("zip", "yes");
		q.put(Parameters.DATA_PROFILE.toString(), Profile.STATION);
		ParameterMap p = new ParameterMap();
		p.setQueryParameters(q);
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenReturn(p);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		MockHttpServletResponse response = new MockHttpServletResponse();

		testController.doGetRequest(request, response);
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		assertEquals("application/vnd.google-earth.kmz;charset=UTF-8", response.getHeader(HttpConstants.HEADER_CONTENT_TYPE));
		assertEquals("attachment; filename=station.kmz", response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("12", response.getHeader(TestBaseController.TEST_COUNT));

		ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(response.getContentAsByteArray()));
		try {
			ZipEntry entry = in.getNextEntry();
			assertEquals("station.kml", entry.getName());
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}

		verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(Map.class));
		verify(parameterHandler).validateAndTransform(anyMap(), anyMap());
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

	@Test
	@SuppressWarnings("unchecked")
	public void doPostRequestTest() {
		Map<String, Object> q = new HashMap<>();
		q.put("mimeType", "kml");
		q.put("zip", "yes");
		Map<String, Object> json = new HashMap<>();
		json.put("siteid", Arrays.asList("11NPSWRD-BICA_MFG_B","WIDNR_WQX-10030952"));
		ParameterMap p = new ParameterMap();
		p.setQueryParameters(q);
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenReturn(p);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("countrycode", "US");
		MockHttpServletResponse response = new MockHttpServletResponse();

		testController.doPostRequest(request, response, json);
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		assertEquals("application/vnd.google-earth.kmz;charset=UTF-8", response.getHeader(HttpConstants.HEADER_CONTENT_TYPE));
		assertEquals("attachment; filename=station.kmz", response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("12", response.getHeader(TestBaseController.TEST_COUNT));

		ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(response.getContentAsByteArray()));
		try {
			ZipEntry entry = in.getNextEntry();
			assertTrue(entry.getName().contentEquals("station.kml"));
		} catch (IOException e) {
			fail("Should not get exception but did:" + e.getLocalizedMessage());
		}

		verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(Map.class));
		verify(parameterHandler).validateAndTransform(anyMap(), anyMap());
		verify(countDao).getCounts(anyString(), anyMap());
		verify(logService).logHeadComplete(any(HttpServletResponse.class), any(BigDecimal.class));
		verify(streamingDao).stream(anyString(), anyMap(), any(ResultHandler.class));
		verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void doPostCountRequestTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		Map<String, Object> q = new HashMap<>();
		q.put("mimeType", "kml");
		q.put("zip", "yes");
		Map<String, Object> json = new HashMap<>();
		json.put("siteid", Arrays.asList("11NPSWRD-BICA_MFG_B","WIDNR_WQX-10030952"));
		ParameterMap p = new ParameterMap();
		p.setQueryParameters(q);
		when(parameterHandler.validateAndTransform(anyMap(), anyMap())).thenReturn(p);
		List<Map<String, Object>> rawCounts = new ArrayList<>();
		Map<String, Object> countRow = new HashMap<>();
		countRow.put("DATA_SOURCE", "NWIS");
		countRow.put("STATION_COUNT", 12);
		rawCounts.add(countRow);
		when(countDao.getCounts(anyString(), anyMap())).thenReturn(rawCounts);

		Map<String, String> result = testController.doPostCountRequest(request, response, json);

		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.containsKey("NWIS-Site-Count"));
		assertEquals("12", result.get("NWIS-Site-Count"));
		assertTrue(result.containsKey("Total-Site-Count"));
		assertEquals("0", result.get("Total-Site-Count"));
		assertEquals(HttpConstants.DEFAULT_ENCODING, response.getCharacterEncoding());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNull(response.getHeader(HttpConstants.HEADER_WARNING));
		verify(logService).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(Map.class));
		verify(logService).logRequestComplete(any(BigDecimal.class), anyString());
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> rawCounts = new ArrayList<>();
		Map<String, Object> countRow = new HashMap<>();
		countRow.put("DATA_SOURCE", "NWIS");
		countRow.put("STATION_COUNT", 12);
		countRow.put("RESULT_COUNT", 359);
		rawCounts.add(countRow);

		testController.addCountHeaders(response, rawCounts, HttpConstants.HEADER_TOTAL_SITE_COUNT, HttpConstants.HEADER_SITE_COUNT, MybatisConstants.STATION_COUNT);

		Object nwis = response.getHeaderValue("NWIS-Site-Count");
		assertNotNull(nwis);
		assertEquals("12", nwis);
		Object total = response.getHeaderValue("Total-Site-Count");
		assertNotNull(total);
		assertEquals("0", total);

		Map<String, String> counts = TestBaseController.getCounts();
		assertNotNull(counts);
		assertEquals(2, counts.size());
		assertTrue(counts.containsKey("NWIS-Site-Count"));
		assertEquals("12", counts.get("NWIS-Site-Count"));
		assertTrue(counts.containsKey("Total-Site-Count"));
		assertEquals("0", counts.get("Total-Site-Count"));

		testController.addCountHeaders(response, rawCounts, HttpConstants.HEADER_TOTAL_RESULT_COUNT, HttpConstants.HEADER_RESULT_COUNT, MybatisConstants.RESULT_COUNT);

		Object nwisSite = response.getHeaderValue("NWIS-Site-Count");
		assertNotNull(nwisSite);
		assertEquals("12", nwisSite);
		Object totalSite = response.getHeaderValue("Total-Site-Count");
		assertNotNull(totalSite);
		assertEquals("0", totalSite);
		Object nwisResult = response.getHeaderValue("NWIS-Result-Count");
		assertNotNull(nwisResult);
		assertEquals("359", nwisResult);
		Object totalResult = response.getHeaderValue("Total-Result-Count");
		assertNotNull(totalResult);
		assertEquals("0", totalResult);

		counts = TestBaseController.getCounts();
		assertNotNull(counts);
		assertEquals(4, counts.size());
		assertTrue(counts.containsKey("NWIS-Site-Count"));
		assertEquals("12", counts.get("NWIS-Site-Count"));
		assertTrue(counts.containsKey("Total-Site-Count"));
		assertEquals("0", counts.get("Total-Site-Count"));
		assertTrue(counts.containsKey("NWIS-Result-Count"));
		assertEquals("359", counts.get("NWIS-Result-Count"));
		assertTrue(counts.containsKey("Total-Result-Count"));
		assertEquals("0", counts.get("Total-Result-Count"));
	}

	@Test
	public void addSiteHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> rawCounts = new ArrayList<>();
		Map<String, Object> countRow = new HashMap<>();
		countRow.put("DATA_SOURCE", "NWIS");
		countRow.put("STATION_COUNT", 12);
		countRow.put("RESULT_COUNT", 359);
		rawCounts.add(countRow);

		testController.addSiteHeaders(response, rawCounts);

		Object nwis = response.getHeaderValue("NWIS-Site-Count");
		assertNotNull(nwis);
		assertEquals("12", nwis);
		Object total = response.getHeaderValue("Total-Site-Count");
		assertNotNull(total);
		assertEquals("0", total);

		Map<String, String> counts = TestBaseController.getCounts();
		assertNotNull(counts);
		assertEquals(2, counts.size());
		assertTrue(counts.containsKey("NWIS-Site-Count"));
		assertEquals("12", counts.get("NWIS-Site-Count"));
		assertTrue(counts.containsKey("Total-Site-Count"));
		assertEquals("0", counts.get("Total-Site-Count"));
	}

}
