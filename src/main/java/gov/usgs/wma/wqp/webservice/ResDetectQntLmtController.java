package gov.usgs.wma.wqp.webservice;

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

import gov.usgs.wma.wqp.dao.intfc.ICountDao;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.ResultDelimited;
import gov.usgs.wma.wqp.mapping.xml.IXmlMapping;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.service.ConfigurationService;
import gov.usgs.wma.wqp.service.ILogService;
import gov.usgs.wma.wqp.openapi.ConfigOpenApi;
import gov.usgs.wma.wqp.openapi.SwaggerParameters;
import gov.usgs.wma.wqp.openapi.annotation.FullParameterList;
import gov.usgs.wma.wqp.openapi.model.ResDetectQntLmtCountJson;
import gov.usgs.wma.wqp.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=ConfigOpenApi.RES_DETECT_QNT_LMT_TAG_NAME, description=ConfigOpenApi.TAG_DESCRIPTION)
@RestController
@RequestMapping(produces={HttpConstants.MIME_TYPE_TSV, HttpConstants.MIME_TYPE_CSV, HttpConstants.MIME_TYPE_XLSX, HttpConstants.MIME_TYPE_XML})
public class ResDetectQntLmtController extends BaseController {

	protected final IXmlMapping xmlMapping;

	@Autowired
	public ResDetectQntLmtController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			@Qualifier("resDetectQntLmtWqx") IXmlMapping inXmlMapping,
			ContentNegotiationStrategy contentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, contentStrategy, validator, configurationService);
		xmlMapping = inXmlMapping;
	}

	@Operation(description="Return appropriate request headers (including anticipated record counts).")
	@FullParameterList
	@RequestMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT, method=RequestMethod.HEAD)
	public void resDetectQntLmtHeadRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doHeadRequest(request, response, filter);
	}

	@Operation(description="Return appropriate request headers (including anticipated record counts) for the specified result.")
	@RequestMapping(value=HttpConstants.RES_DETECT_QNT_LMT_REST_ENDPOINT, method=RequestMethod.HEAD)
	public void resDetectQntLmtHeadRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("provider") @Parameter(description=SwaggerParameters.PROVIDER_DESCRIPTION) String provider,
			@PathVariable("organization") @Parameter(description=SwaggerParameters.ORGANIZATION_DESCRIPTION) String organization,
			@PathVariable("activity") @Parameter(description=SwaggerParameters.ACTIVITY_DESCRIPTION) String activity,
			@PathVariable("result") @Parameter(description=SwaggerParameters.RESULT_DESCRIPTION) String resultId,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(Arrays.asList(provider));
		filter.setOrganization(Arrays.asList(organization));
		filter.setActivity(activity);
		filter.setResultId(resultId);
		doHeadRequest(request, response, filter, mimeType, zip);
	}

	@Operation(description="Return requested data.")
	@FullParameterList
	@GetMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT)
	public void resDetectQntLmtGetRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	@Operation(description="Return result detection quantitative limit information for the specified result.")
	@GetMapping(value=HttpConstants.RES_DETECT_QNT_LMT_REST_ENDPOINT)
	public void resDetectQntLmtGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("provider") @Parameter(description=SwaggerParameters.PROVIDER_DESCRIPTION) String provider,
			@PathVariable("organization") @Parameter(description=SwaggerParameters.ORGANIZATION_DESCRIPTION) String organization,
			@PathVariable("activity") @Parameter(description=SwaggerParameters.ACTIVITY_DESCRIPTION) String activity,
			@PathVariable("result") @Parameter(description=SwaggerParameters.RESULT_DESCRIPTION) String resultId,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(Arrays.asList(provider));
		filter.setOrganization(Arrays.asList(organization));
		filter.setActivity(activity);
		filter.setResultId(resultId);
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@Operation(description="Return requested data. Use when the list of parameter values is too long for a query string.")
	@PostMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void resDetectQntLmtJsonPostRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody FilterParameters filter) {
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@Operation(description="Same as the JSON consumer, but hidden from swagger", hidden=true)
	@PostMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void resDetectQntLmtFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	@Operation(description="Return anticipated record counts.",
			responses={
					@ApiResponse(
									responseCode="200",
									description="OK",
									content=@Content(schema=@Schema(implementation=ResDetectQntLmtCountJson.class)))
					})
	@PostMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT + "/count", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> resDetectQntLmtPostCountRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody FilterParameters filter) {
		return doPostCountRequest(request, response, filter, mimeType, zip);
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		addActivityHeaders(response, counts);
		addResultHeaders(response, counts);
		addResDetectQntLmtHeaders(response, counts);
		return HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT;
	}

	@Override
	protected Map<String, String> getMapping(Profile profile) {
		return ResultDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

	@Override
	protected Profile determineProfile(FilterParameters filter) {
		return determineProfile(Profile.RES_DETECT_QNT_LMT, filter);
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return null;
	}

}
