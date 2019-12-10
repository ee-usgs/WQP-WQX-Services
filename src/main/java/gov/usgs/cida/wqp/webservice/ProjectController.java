package gov.usgs.cida.wqp.webservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.ProjectDelimited;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.ConfigurationService;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.annotation.FullParameterList;
import gov.usgs.cida.wqp.swagger.model.ProjectCountJson;
import gov.usgs.cida.wqp.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=SwaggerConfig.PROJECT_TAG_NAME, description=SwaggerConfig.TAG_DESCRIPTION)
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
	
	@Operation(description="Return appropriate request headers (including anticipated record counts).")
	@FullParameterList
	@RequestMapping(method=RequestMethod.HEAD)
	public void projectHeadRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doHeadRequest(request, response, filter);
	}
	
	@Operation(description="Return requested data.")
	@FullParameterList
	@GetMapping()
	public void projectGetRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doDataRequest(request, response, filter);
	}
	
	@Operation(description="Return requested data. Use when list of parameters is too long for a query string.")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public void projectJsonPostRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody FilterParameters filter) {
		doDataRequest(request, response, filter, mimeType, zip);
	}
	
	@Operation(description="Same as the JSON consumer, but hidden from swagger", hidden=true)
	@PostMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void activityFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doDataRequest(request, response, filter);
	}
	
	@Operation(description="Return anticipated record counts.",
			responses={
					@ApiResponse(
									responseCode="200",
									description="OK",
									content=@Content(schema=@Schema(implementation=ProjectCountJson.class)))
					})
	@PostMapping(value="count", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> projectPostCountRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody FilterParameters filter) {
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
		getFilter().setSiteUrlBase(configurationService.getSiteUrlBase());
	}
}


