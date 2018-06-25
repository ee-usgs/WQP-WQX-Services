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
import gov.usgs.cida.wqp.mapping.delimited.ResultDelimited;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.parameter.ResultIdentifier;
import gov.usgs.cida.wqp.service.ConfigurationService;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.SwaggerParameters;
import gov.usgs.cida.wqp.swagger.annotation.FullParameterList;
import gov.usgs.cida.wqp.swagger.model.ResDetectQntLmtCountJson;
import gov.usgs.cida.wqp.util.HttpConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags={SwaggerConfig.RES_DETECT_QNT_LMT_TAG_NAME})
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

	@ApiOperation(value="Return appropriate request headers (including anticipated record counts).")
	@FullParameterList
	@RequestMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT, method=RequestMethod.HEAD)
	public void resDetectQntLmtHeadRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
		doHeadRequest(request, response, filter);
	}

	@ApiOperation(value="Return appropriate request headers (including anticipated record counts) for the specified result.")
	@RequestMapping(value=HttpConstants.RES_DETECT_QNT_LMT_REST_ENDPOINT, method=RequestMethod.HEAD)
	public void resDetectQntLmtHeadRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("activity") @ApiParam(value=SwaggerParameters.ACTIVITY_DESCRIPTION) String activity,
			@PathVariable("result") @ApiParam(value=SwaggerParameters.RESULT_DESCRIPTION) String result,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) {
		FilterParameters filter = new FilterParameters();
		filter.setActivity(activity);
		filter.setResult(new ResultIdentifier(result));
		doHeadRequest(request, response, filter, mimeType, zip);
	}

	@ApiOperation(value="Return requested data.")
	@FullParameterList
	@GetMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT)
	public void resDetectQntLmtGetRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	@ApiOperation(value="Return result detection quantitative limit information for the specified result.")
	@GetMapping(value=HttpConstants.RES_DETECT_QNT_LMT_REST_ENDPOINT)
	public void resDetectQntLmtGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("activity") @ApiParam(value=SwaggerParameters.ACTIVITY_DESCRIPTION) String activity,
			@PathVariable("result") @ApiParam(value=SwaggerParameters.RESULT_DESCRIPTION) String result,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) {
		FilterParameters filter = new FilterParameters();
		filter.setActivity(activity);
		filter.setResult(new ResultIdentifier(result));
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@ApiOperation(value="Return requested data. Use when the list of parameter values is too long for a query string.")
	@PostMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void resDetectQntLmtJsonPostRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody @ApiIgnore FilterParameters filter) {
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@ApiOperation(value="Same as the JSON consumer, but hidden from swagger", hidden=true)
	@PostMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void resDetectQntLmtFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	@ApiOperation(value="Return anticipated record counts.")
	@ApiResponses(value={@ApiResponse(code=200, message="OK", response=ResDetectQntLmtCountJson.class)})
	@PostMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENDPOINT + "/count", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> resDetectQntLmtPostCountRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip,
			@RequestBody @ApiIgnore FilterParameters filter) {
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
