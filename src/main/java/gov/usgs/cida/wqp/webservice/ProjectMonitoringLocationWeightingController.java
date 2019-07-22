package gov.usgs.cida.wqp.webservice;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.ProjectMonitoringLocationWeightingDelimited;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.ConfigurationService;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.SwaggerParameters;
import gov.usgs.cida.wqp.swagger.annotation.FullParameterList;
import gov.usgs.cida.wqp.swagger.model.ProjectMonitoringLocationWeightingCountJson;
import gov.usgs.cida.wqp.util.HttpConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import java.io.IOException;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags= {SwaggerConfig.PROJECT_MONITORING_LOCATION_WEIGHTING_TAG_NAME})
@RestController
@RequestMapping(produces={HttpConstants.MIME_TYPE_TSV,
						  HttpConstants.MIME_TYPE_CSV,
						  HttpConstants.MIME_TYPE_XLSX,
						  HttpConstants.MIME_TYPE_XML})
public class ProjectMonitoringLocationWeightingController extends BaseController {
	
	protected final IXmlMapping xmlMapping;
	
	@Autowired
	public ProjectMonitoringLocationWeightingController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			@Qualifier("projectMonitoringLocationWeightingWqx") IXmlMapping inXmlMapping,
			ContentNegotiationStrategy inContentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, inContentStrategy, validator, configurationService);
		xmlMapping = inXmlMapping;
	}

	@ApiOperation(value="Return the project monitoring location weightings associated with the specified project and organization.")
	@GetMapping(value=HttpConstants.PROJECT_MONITORING_LOCATION_WEIGHTING_REST_ENDPOINT)
	public void projectMonitoringLocationWeightingRestGet(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("provider") @ApiParam(value=SwaggerParameters.PROVIDER_DESCRIPTION) String provider,
			@PathVariable("organization") String organization,
			@PathVariable("projectIdentifier") String projectIdentifier,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) throws IOException {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(Arrays.asList(provider));
		filter.setOrganization(Arrays.asList(organization));
		filter.setProject(Arrays.asList(projectIdentifier));
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@ApiOperation(value="Return appropriate request headers (including anticipated record counts) for the project monitoring location weightings associated with the specified project and organization.")
	@RequestMapping(value=HttpConstants.PROJECT_MONITORING_LOCATION_WEIGHTING_REST_ENDPOINT, method=RequestMethod.HEAD)
	public void projectMonitoringLocationWeightingRestHead(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("provider") @ApiParam(value=SwaggerParameters.PROVIDER_DESCRIPTION) String provider,
			@PathVariable("organization") String organization, 
			@PathVariable("projectIdentifier") String projectIdentifier, 
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) throws IOException {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(Arrays.asList(provider));
		filter.setOrganization(Arrays.asList(organization));
		filter.setProject(Arrays.asList(projectIdentifier));
		doHeadRequest(request, response, filter, mimeType, zip);
	}

	@ApiOperation(value="Return appropriate request headers (including anticipated record counts).")
	@FullParameterList
	@RequestMapping(value=HttpConstants.PROJECT_MONITORING_LOCATION_WEIGHTING_SEARCH_ENDPOINT, method=RequestMethod.HEAD)
	public void projectMonitoringLocationWeightingHeadRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
		doHeadRequest(request, response, filter);
	}
	
	@ApiOperation(value="Return requested data.", produces="")
	@FullParameterList
	@GetMapping(value=HttpConstants.PROJECT_MONITORING_LOCATION_WEIGHTING_SEARCH_ENDPOINT)
	public void projectMonitoringLocationWeightingGetRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
		doDataRequest(request, response, filter);
	}
	
	@ApiOperation(value="Return requested data. Use when list of parameters is too long for a query string.")
	@PostMapping(value=HttpConstants.PROJECT_MONITORING_LOCATION_WEIGHTING_SEARCH_ENDPOINT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void projectMonitoringLocationWeightingJsonPostRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody @ApiIgnore FilterParameters filter) {
		doDataRequest(request, response, filter, mimeType, zip);
	}
	
	@ApiOperation(value="Same as the JSON consumer, but hidden from swagger", hidden=true)
	@PostMapping(value=HttpConstants.PROJECT_MONITORING_LOCATION_WEIGHTING_SEARCH_ENDPOINT, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void projectMonitoringLocationWeightingFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
		doDataRequest(request, response, filter);
	}
	
	@ApiOperation(value="Return anticipated record counts.")
	@ApiResponses(value= {@ApiResponse(code=200, message="OK", response=ProjectMonitoringLocationWeightingCountJson.class)})
	@PostMapping(value=HttpConstants.PROJECT_MONITORING_LOCATION_WEIGHTING_SEARCH_ENDPOINT + "/count", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String>  projectMonitoringLocationWeightingPostCountRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody @ApiIgnore FilterParameters filter) {
		return doPostCountRequest(request, response, filter, mimeType, zip);
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addProjectMonitoringLocationWeightingHeaders(response, counts);
		return HttpConstants.HEADER_TOTAL_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT;
	}
	
	@Override
	protected Profile determineProfile(FilterParameters filter) {
		return determineProfile(Profile.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
	}

	@Override
	protected Map<String, String> getMapping(Profile profile) {
		return ProjectMonitoringLocationWeightingDelimited.getMapping(profile);
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
