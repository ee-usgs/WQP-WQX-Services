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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.ActivityMetricDelimited;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.SwaggerParameters;
import gov.usgs.cida.wqp.swagger.annotation.FullParameterList;
import gov.usgs.cida.wqp.swagger.model.ActivityMetricCountJson;
import gov.usgs.cida.wqp.util.HttpConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags={SwaggerConfig.ACTIVITY_METRIC_TAG_NAME})
@RestController
@RequestMapping(produces={HttpConstants.MIME_TYPE_TSV, HttpConstants.MIME_TYPE_CSV, HttpConstants.MIME_TYPE_XLSX, HttpConstants.MIME_TYPE_XML})
public class ActivityMetricController extends BaseController {

	protected final IXmlMapping xmlMapping;

	@Autowired
	public ActivityMetricController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			@Qualifier("maxResultRows") Integer inMaxResultRows,
			@Qualifier("activityMetricWqx") IXmlMapping inXmlMapping,
			@Qualifier("siteUrlBase") String inSiteUrlBase,
			ContentNegotiationStrategy contentStrategy,
			Validator validator) {
		super(inStreamingDao, inCountDao, inLogService, inMaxResultRows, inSiteUrlBase, contentStrategy, validator);
		xmlMapping = inXmlMapping;
	}

	@ApiOperation(value="Return appropriate request headers (including anticipated record counts).")
	@FullParameterList
	@RequestMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENPOINT, method=RequestMethod.HEAD)
	public void activityMetricHeadRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
		doHeadRequest(request, response, filter);
	}

	@ApiOperation(value="Return appropriate request headers (including anticipated record counts) for the specified activity.")
	@RequestMapping(value=HttpConstants.ACTIVITY_METRIC_REST_ENPOINT, method=RequestMethod.HEAD)
	public void activityMetricRestHeadRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("activity") @ApiParam(value=SwaggerParameters.ACTIVITY_DESCRIPTION) String activity,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) {
		FilterParameters filter = new FilterParameters();
		filter.setActivity(activity);
		doHeadRequest(request, response, filter, mimeType, zip);
	}

	@ApiOperation(value="Return requested data.")
	@FullParameterList
	@GetMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENPOINT)
	public void activityMetricGetRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	@ApiOperation(value="Return activity metric information for the specified activity.")
	@GetMapping(value=HttpConstants.ACTIVITY_METRIC_REST_ENPOINT)
	public void activityMetricGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("activity") @ApiParam(value=SwaggerParameters.ACTIVITY_DESCRIPTION) String activity,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) {
		FilterParameters filter = new FilterParameters();
		filter.setActivity(activity);
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@ApiOperation(value="Return requested data. Use when the list of parameter values is too long for a query string.")
	@PostMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENPOINT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void activityMetricJsonPostRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody @ApiIgnore FilterParameters filter) {
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@ApiOperation(value="Same as the JSON consumer, but hidden from swagger", hidden=true)
	@PostMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENPOINT, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void activityMetricFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	@ApiOperation(value="Return anticipated record counts.")
	@ApiResponses(value={@ApiResponse(code=200, message="OK", response=ActivityMetricCountJson.class)})
	@PostMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENPOINT + "/count", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> activityMetricPostCountRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody @ApiIgnore FilterParameters filter) {
		return doPostCountRequest(request, response, filter, mimeType, zip);
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		addActivityHeaders(response, counts);
		addActivityMetricHeaders(response, counts);
		return HttpConstants.HEADER_TOTAL_ACTIVITY_METRIC_COUNT;
	}

	@Override
	protected Map<String, String> getMapping(Profile profile) {
		return ActivityMetricDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

	@Override
	protected Profile determineProfile(FilterParameters filter) {
		return determineProfile(Profile.ACTIVITY_METRIC, filter);
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return null;
	}
}
