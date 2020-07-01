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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.wma.wqp.dao.intfc.ICountDao;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.ActivityMetricDelimited;
import gov.usgs.wma.wqp.mapping.xml.IXmlMapping;
import gov.usgs.wma.wqp.openapi.ConfigOpenApi;
import gov.usgs.wma.wqp.openapi.annotation.GetOperation;
import gov.usgs.wma.wqp.openapi.annotation.HeadOperation;
import gov.usgs.wma.wqp.openapi.annotation.PostCountOperation;
import gov.usgs.wma.wqp.openapi.annotation.PostOperation;
import gov.usgs.wma.wqp.openapi.annotation.path.Activity;
import gov.usgs.wma.wqp.openapi.annotation.path.Organization;
import gov.usgs.wma.wqp.openapi.annotation.path.Provider;
import gov.usgs.wma.wqp.openapi.annotation.query.FullParameterList;
import gov.usgs.wma.wqp.openapi.annotation.query.MimeTypeJson;
import gov.usgs.wma.wqp.openapi.annotation.query.MimeTypeStd;
import gov.usgs.wma.wqp.openapi.annotation.query.Zip;
import gov.usgs.wma.wqp.openapi.model.ActivityMetricCountJson;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.service.ConfigurationService;
import gov.usgs.wma.wqp.service.ILogService;
import gov.usgs.wma.wqp.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=ConfigOpenApi.ACTIVITY_METRIC_TAG_NAME, description=ConfigOpenApi.TAG_DESCRIPTION)
@RestController
@RequestMapping(produces={
		HttpConstants.MIME_TYPE_TSV,
		HttpConstants.MIME_TYPE_CSV,
		HttpConstants.MIME_TYPE_XLSX,
		HttpConstants.MIME_TYPE_XML})
public class ActivityMetricController extends BaseController {

	protected final IXmlMapping xmlMapping;

	@Autowired
	public ActivityMetricController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			@Qualifier("activityMetricWqx") IXmlMapping inXmlMapping,
			ContentNegotiationStrategy contentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, contentStrategy, validator, configurationService);
		xmlMapping = inXmlMapping;
	}

	@HeadOperation
	@FullParameterList
	@MimeTypeStd
	@RequestMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENDPOINT, method=RequestMethod.HEAD)
	public void activityMetricHeadRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) FilterParameters filter
			) {
		doHeadRequest(request, response, filter);
	}

	@HeadOperation
	@Provider
	@Organization
	@Activity
	@MimeTypeStd
	@Zip
	@RequestMapping(value=HttpConstants.ACTIVITY_METRIC_REST_ENDPOINT, method=RequestMethod.HEAD)
	public void activityMetricRestHeadRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @PathVariable("activity") String activity,
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip
			) {
		FilterParameters filter = new FilterParameters();
		filter.setActivity(activity);
		doHeadRequest(request, response, filter, mimeType, zip);
	}

	@GetOperation
	@FullParameterList
	@MimeTypeStd
	@GetMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENDPOINT)
	public void activityMetricGetRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) FilterParameters filter
			) {
		doDataRequest(request, response, filter);
	}

	@GetOperation
	@Provider
	@Organization
	@Activity
	@MimeTypeStd
	@Zip
	@GetMapping(value=HttpConstants.ACTIVITY_METRIC_REST_ENDPOINT)
	public void activityMetricGetRestRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @PathVariable("activity") String activity,
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip
			) {
		FilterParameters filter = new FilterParameters();
		filter.setActivity(activity);
		//Small dataset, so sorting shouldn't affect response time, but does make testing repeatable/possible.
		filter.setSorted("yes");
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@PostOperation
	@MimeTypeStd
	@Zip
	@PostMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENDPOINT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void activityMetricJsonPostRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip,
			@RequestBody FilterParameters filter
			) {
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@Operation(description="Same as the JSON consumer, but hidden from swagger", hidden=true)
	@PostMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENDPOINT, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void activityMetricFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	@PostCountOperation
	@MimeTypeJson
	@Zip
	@ApiResponse(
			responseCode="200",
			description="OK",
			content=@Content(schema=@Schema(implementation=ActivityMetricCountJson.class))
			)
	@PostMapping(value=HttpConstants.ACTIVITY_METRIC_SEARCH_ENDPOINT + "/count", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> activityMetricPostCountRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip,
			@RequestBody FilterParameters filter
			) {
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
