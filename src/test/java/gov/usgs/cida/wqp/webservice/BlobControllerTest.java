package gov.usgs.cida.wqp.webservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.dao.BlobDao;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.service.LogServiceTest;
import gov.usgs.cida.wqp.util.HttpConstants;

public class BlobControllerTest extends BaseTest {

	@Mock
	BlobDao blobDao;
	@Mock
	ILogService logService;
	BlobController controller;
	Integer FIFTY_FIVE = 55;
	MockHttpServletRequest request;
	MockHttpServletResponse response;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		controller = new BlobController(blobDao, logService);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		when(logService.logRequest(request, response)).thenReturn(FIFTY_FIVE);
	}

	@Test
	public void setupResponseTest() throws IOException {
		controller.setupResponse(request, response, BlobController.PROJECT_FILE);
		assertTrue(response.containsHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("attachment; filename=" + BlobController.PROJECT_FILE, response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals(FIFTY_FIVE, BlobController.getLogId());
		assertEquals(response.getOutputStream(), BlobController.getOutputStream());
		assertNotNull(BlobController.getZipOutputStream());
		verify(logService).logRequest(request, response);
	}

	@Test
	public void finishResponseTest() throws IOException {
		BlobController.setLogId(FIFTY_FIVE);
		BlobController.setOutputStream(response.getOutputStream());
		ZipOutputStream zip = new ZipOutputStream(response.getOutputStream());
		zip.putNextEntry(new ZipEntry("what"));
		zip.write("something".getBytes());
		BlobController.setZipOutputStream(zip);
		controller.finishResponse(response, "test", LogServiceTest.getDownloadDetails());
		verify(logService).logRequestComplete(any(), anyString(), anyMap());
		assertNull(BlobController.getLogId());
		assertNull(BlobController.getOutputStream());
		assertNull(BlobController.getZipOutputStream());
		assertEquals("something", extractZipContent(response.getContentAsByteArray(), "what"));
	}

	@Test
	public void projectTest() throws IOException {
		controller.projectBlobFilesGetRestRequest(request, response, "provider", "organization", "projectIdentifier");
		assertTrue(response.containsHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("attachment; filename=" + BlobController.PROJECT_FILE, response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals(0, response.getContentLength());
		verify(logService).logRequest(request, response);
		verify(logService).logRequestComplete(any(), anyString(), anyMap());
		verify(blobDao).getProjectFiles(any(ZipOutputStream.class), anyString(), anyString(), anyString());
	}

	@Test
	public void stationTest() throws IOException {
		controller.monitoringLocationBlobFilesGetRestRequest(request, response, "provider", "organization", "projectIdentifier");
		assertTrue(response.containsHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("attachment; filename=" + BlobController.MONITORING_LOCATION_FILE, response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals(0, response.getContentLength());
		verify(logService).logRequest(request, response);
		verify(logService).logRequestComplete(any(), anyString(), anyMap());
		verify(blobDao).getMonitoringLocationFiles(any(ZipOutputStream.class), anyString(), anyString(), anyString());
	}

	@Test
	public void activityTest() throws IOException {
		controller.activityBlobFilesGetRestRequest(request, response, "provider", "organization", "activity");
		assertTrue(response.containsHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("attachment; filename=" + BlobController.ACTIVITY_FILE, response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals(0, response.getContentLength());
		verify(logService).logRequest(request, response);
		verify(logService).logRequestComplete(any(), anyString(), anyMap());
		verify(blobDao).getActivityFiles(any(ZipOutputStream.class), anyString(), anyString(), anyString());
	}

	@Test
	public void resultTest() throws IOException {
		controller.resultBlobFilesGetRestRequest(request, response, "provider", "organization", "activity", "result");
		assertTrue(response.containsHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals("attachment; filename=" + BlobController.RESULT_FILE, response.getHeader(HttpConstants.HEADER_CONTENT_DISPOSITION));
		assertEquals(0, response.getContentLength());
		verify(logService).logRequest(request, response);
		verify(logService).logRequestComplete(any(), anyString(), anyMap());
		verify(blobDao).getResultFiles(any(ZipOutputStream.class), anyString(), anyString(), anyString(), anyString());
	}

}
