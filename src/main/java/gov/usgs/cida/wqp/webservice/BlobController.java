package gov.usgs.cida.wqp.webservice;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.cida.wqp.dao.BlobDao;
import gov.usgs.cida.wqp.parameter.ResultIdentifier;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.SwaggerParameters;
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
	public static final String ACTIVITY_FILE = "activity.zip";

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
			@PathVariable("organization") @ApiParam(value=SwaggerParameters.ORGANIZATION_DESCRIPTION) String organization,
			@PathVariable("monitoringLocation") @ApiParam(value=SwaggerParameters.MONITORING_LOCATION_DESCRIPTION) String monitoringLocation) throws IOException {
		setupResponse(request, response, MONITORING_LOCATION_FILE);
		Map<String, Integer> downloadDetails = new HashMap<>();
		downloadDetails.put(organization, blobDao.getMonitoringLocationFiles(getZipOutputStream(), organization, monitoringLocation));
		finishResponse(response, organization, downloadDetails);
	}

	@ApiOperation(value="Return zipped artifact with all files available for the specified organization/project.")
	@GetMapping(value=HttpConstants.PROJECT_FILE_REST_ENDPOINT)
	@NoQueryParametersList
	public void projectBlobFilesGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("organization") @ApiParam(value=SwaggerParameters.ORGANIZATION_DESCRIPTION) String organization,
			@PathVariable("project") @ApiParam(value=SwaggerParameters.PROJECT_IDENTIFIER_DESCRIPTION) String project) throws IOException {
		setupResponse(request, response, PROJECT_FILE);
		Map<String, Integer> downloadDetails = new HashMap<>();
		downloadDetails.put(organization, blobDao.getProjectFiles(getZipOutputStream(), organization, project));
		finishResponse(response, organization, downloadDetails);
	}

	@ApiOperation(value="Return zipped artifact with all files available for the specified organization/activity/result.")
	@GetMapping(value=HttpConstants.RESULT_FILE_REST_ENDPOINT)
	@NoQueryParametersList
	public void resultBlobFilesGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("organization") @ApiParam(value=SwaggerParameters.ORGANIZATION_DESCRIPTION) String organization,
			@PathVariable("activity") @ApiParam(value=SwaggerParameters.ACTIVITY_DESCRIPTION) String activity,
			@PathVariable("result") @ApiParam(value=SwaggerParameters.RESULT_DESCRIPTION) String result) throws IOException {
		setupResponse(request, response, RESULT_FILE);
		Map<String, Integer> downloadDetails = new HashMap<>();
		downloadDetails.put(organization, blobDao.getResultFiles(getZipOutputStream(), organization, activity, new ResultIdentifier(result)));
		finishResponse(response, organization, downloadDetails);
	}

	@ApiOperation(value="Return zipped artifact with all files available for the specified organization/activity.")
	@GetMapping(value=HttpConstants.ACTIVITY_FILE_REST_ENDPOINT)
	@NoQueryParametersList
	public void activityBlobFilesGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("organization") @ApiParam(value=SwaggerParameters.ORGANIZATION_DESCRIPTION) String organization,
			@PathVariable("activity") @ApiParam(value=SwaggerParameters.ACTIVITY_DESCRIPTION) String activity) throws IOException {
		setupResponse(request, response, ACTIVITY_FILE);
		Map<String, Integer> downloadDetails = new HashMap<>();
		downloadDetails.put(organization, blobDao.getActivityFiles(getZipOutputStream(), organization, activity));
		finishResponse(response, organization, downloadDetails);
	}

	protected void setupResponse(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
		response.setHeader(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		setLogId(logService.logRequest(request, response));
		setOutputStream(response.getOutputStream());
		setZipOutputStream(new ZipOutputStream(getOutputStream()));
	}

	protected void finishResponse(HttpServletResponse response, String organization, Map<String, Integer> downloadDetails) throws IOException {
		Integer rowCnt = downloadDetails.get(organization);
		if (null == rowCnt || 0 == rowCnt) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		getZipOutputStream().finish();
		getZipOutputStream().close();
		getOutputStream().flush();
		logService.logRequestComplete(getLogId(), String.valueOf(response.getStatus()), downloadDetails);
		remove();
	}

}