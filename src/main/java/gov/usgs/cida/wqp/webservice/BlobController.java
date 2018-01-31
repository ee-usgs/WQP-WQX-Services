package gov.usgs.cida.wqp.webservice;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.cida.wqp.dao.BlobDao;
import gov.usgs.cida.wqp.parameter.ResultIdentifier;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.annotation.NoQueryParametersList;
import gov.usgs.cida.wqp.util.HttpConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags={SwaggerConfig.FILE_DOWNLOAD_TAG_NAME})
@RestController
public class BlobController {

	protected final BlobDao blobDao;
	protected final ILogService logService;

	private static ThreadLocal<BigDecimal> logId = new ThreadLocal<>();
	private static ThreadLocal<OutputStream> outputStream = new ThreadLocal<>();
	private static ThreadLocal<ZipOutputStream> zipOutputStream = new ThreadLocal<>();

	public static final String MONITORING_LOCATION_FILE = "monitoringLocation.zip";
	public static final String PROJECT_FILE = "project.zip";
	public static final String RESULT_FILE = "result.zip";
	public static final String MONITORING_LOCATION_DESCRIPTION = "Case-sensitive Monitoring Location (Site ID).";
	public static final String ORGANIZATION_DESCRIPTION = "Case-sensitive Organization Identifier.";
	public static final String PROJECT_IDENTIFIER_DESCRIPTION = "Case-sensitive Project Identifier.";
	public static final String ACTIVITY_DESCRIPTION = "Case-sensitive Activity Identifier.";
	public static final String RESULT_DESCRIPTION = "Case-sensitive Result Identifier.";

	@Autowired
	public BlobController(BlobDao blobDao, ILogService inLogService) {
		this.blobDao = blobDao;
		logService = inLogService;
	}

	protected static BigDecimal getLogId() {
		return logId.get();
	}
	protected static void setLogId(BigDecimal inLogId) {
		logId.set(inLogId);
	}
	protected static OutputStream getOutputStream() {
		return outputStream.get();
	}
	protected static void setOutputStream(OutputStream inOutputStream) {
		outputStream.set(inOutputStream);
	}
	protected static ZipOutputStream getZipOutputStream() {
		return zipOutputStream.get();
	}
	protected static void setZipOutputStream(ZipOutputStream inZipOutputStream) {
		zipOutputStream.set(inZipOutputStream);
	}
	protected static void remove() {
		logId.remove();
		outputStream.remove();
		zipOutputStream.remove();
	}


	@ApiOperation(value="Return zipped artifact with all files available for the specified organization/monitoringLocation.")
	@GetMapping(value=HttpConstants.MONITORING_LOCATION_FILE_REST_ENDPOINT)
	@NoQueryParametersList
	public void monitoringLocationBlobFilesGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("organization") @ApiParam(value=ORGANIZATION_DESCRIPTION) String organization,
			@PathVariable("monitoringLocation") @ApiParam(value=MONITORING_LOCATION_DESCRIPTION) String monitoringLocation) throws IOException {
		setupResponse(request, response, MONITORING_LOCATION_FILE);
		blobDao.getMonitoringLocationFiles(getZipOutputStream(), organization, monitoringLocation);
		finishResponse(response);
	}

	@ApiOperation(value="Return zipped artifact with all files available for the specified organization/project.")
	@GetMapping(value=HttpConstants.PROJECT_FILE_REST_ENDPOINT)
	@NoQueryParametersList
	public void projectBlobFilesGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("organization") @ApiParam(value=ORGANIZATION_DESCRIPTION) String organization,
			@PathVariable("project") @ApiParam(value=PROJECT_IDENTIFIER_DESCRIPTION) String project) throws IOException {
		setupResponse(request, response, PROJECT_FILE);
		blobDao.getProjectFiles(getZipOutputStream(), organization, project);
		finishResponse(response);
	}

	@ApiOperation(value="Return zipped artifact with all files available for the specified organization/activity/result.")
	@GetMapping(value=HttpConstants.RESULT_FILE_REST_ENDPOINT)
	@NoQueryParametersList
	public void resultBlobFilesGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("organization") @ApiParam(value=ORGANIZATION_DESCRIPTION) String organization,
			@PathVariable("activity") @ApiParam(value=ACTIVITY_DESCRIPTION) String activity,
			@PathVariable("result") @ApiParam(value=RESULT_DESCRIPTION) String result) throws IOException {
		setupResponse(request, response, RESULT_FILE);
		blobDao.getResultFiles(getZipOutputStream(), organization, activity, new ResultIdentifier(result));
		finishResponse(response);
	}

	protected void setupResponse(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
		response.setHeader(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		setLogId(logService.logRequest(request, response));
		setOutputStream(response.getOutputStream());
		setZipOutputStream(new ZipOutputStream(getOutputStream()));
	}

	protected void finishResponse(HttpServletResponse response) throws IOException {
		getZipOutputStream().finish();
		getZipOutputStream().close();
		getOutputStream().flush();
		logService.logRequestComplete(getLogId(), String.valueOf(response.getStatus()));
		remove();
	}

}