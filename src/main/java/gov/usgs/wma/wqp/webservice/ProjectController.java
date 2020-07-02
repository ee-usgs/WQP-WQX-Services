package gov.usgs.wma.wqp.webservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.wma.wqp.dao.intfc.ICountDao;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.ProjectDelimited;
import gov.usgs.wma.wqp.mapping.xml.IXmlMapping;
import gov.usgs.wma.wqp.openapi.ConfigOpenApi;
import gov.usgs.wma.wqp.openapi.annotation.FormUrlPostOperation;
import gov.usgs.wma.wqp.openapi.annotation.GetOperation;
import gov.usgs.wma.wqp.openapi.annotation.HeadOperation;
import gov.usgs.wma.wqp.openapi.annotation.PostCountOperation;
import gov.usgs.wma.wqp.openapi.annotation.PostOperation;
import gov.usgs.wma.wqp.openapi.annotation.query.FullParameterList;
import gov.usgs.wma.wqp.openapi.model.ProjectCountJson;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.service.ConfigurationService;
import gov.usgs.wma.wqp.service.ILogService;
import gov.usgs.wma.wqp.util.HttpConstants;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=ConfigOpenApi.PROJECT_TAG_NAME, description=ConfigOpenApi.TAG_DESCRIPTION)
@RestController
@RequestMapping(value=HttpConstants.PROJECT_SEARCH_ENDPOINT,
produces={HttpConstants.MIME_TYPE_TSV,
		HttpConstants.MIME_TYPE_CSV,
		HttpConstants.MIME_TYPE_XLSX,
		HttpConstants.MIME_TYPE_XML})
public class ProjectController extends BaseController {

	protected final IXmlMapping xmlMapping;

	@Autowired
	public ProjectController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			@Qualifier("projectWqx") IXmlMapping inXmlMapping,
			ContentNegotiationStrategy inContentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, inContentStrategy, validator, configurationService);
		xmlMapping = inXmlMapping;
	}

	@HeadOperation
	@FullParameterList
	public void projectHeadRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) FilterParameters filter
			) {
		doHeadRequest(request, response, filter);
	}

	@GetOperation
	@FullParameterList
	public void projectGetRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) FilterParameters filter
			) {
		doDataRequest(request, response, filter);
	}

	@PostOperation
	public void projectJsonPostRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip,
			@Parameter(hidden=true) @RequestBody FilterParameters filter
			) {
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@FormUrlPostOperation
	@PostMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void activityFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	@PostCountOperation
	@ApiResponse(
			responseCode="200",
			description="OK",
			content=@Content(schema=@Schema(implementation=ProjectCountJson.class)))
	public Map<String, String> projectPostCountRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip,
			@Parameter(hidden=true) @RequestBody FilterParameters filter
			) {
		return doPostCountRequest(request, response, filter, mimeType, zip);
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addProjectHeaders(response, counts);
		return HttpConstants.HEADER_TOTAL_PROJECT_COUNT;
	}

	@Override
	protected Profile determineProfile(FilterParameters filter) {
		return determineProfile(Profile.PROJECT, filter);
	}

	@Override
	protected Map<String, String> getMapping(Profile profile) {
		return ProjectDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return null;
	}

	@Override
	protected void addCustomRequestParams() {
		getFilter().setSiteUrlBase(configurationService.getMyUrlBase());
	}
}
