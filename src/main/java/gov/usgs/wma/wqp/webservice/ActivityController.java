package gov.usgs.wma.wqp.webservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.wma.wqp.dao.intfc.ICountDao;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.ActivityDelimited;
import gov.usgs.wma.wqp.mapping.xml.IXmlMapping;
import gov.usgs.wma.wqp.openapi.ConfigOpenApi;
import gov.usgs.wma.wqp.openapi.annotation.FormUrlPostOperation;
import gov.usgs.wma.wqp.openapi.annotation.GetOperation;
import gov.usgs.wma.wqp.openapi.annotation.HeadOperation;
import gov.usgs.wma.wqp.openapi.annotation.PostCountOperation;
import gov.usgs.wma.wqp.openapi.annotation.PostOperation;
import gov.usgs.wma.wqp.openapi.annotation.query.DataProfileActivity;
import gov.usgs.wma.wqp.openapi.annotation.query.FullParameterList;
import gov.usgs.wma.wqp.openapi.model.ActivityCountJson;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.service.ConfigurationService;
import gov.usgs.wma.wqp.service.ILogService;
import gov.usgs.wma.wqp.util.HttpConstants;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=ConfigOpenApi.ACTIVITY_TAG_NAME, description=ConfigOpenApi.TAG_DESCRIPTION)
@RestController
@RequestMapping(value=HttpConstants.ACTIVITY_SEARCH_ENDPOINT,
	produces={HttpConstants.MIME_TYPE_TSV,
			HttpConstants.MIME_TYPE_CSV,
			HttpConstants.MIME_TYPE_XLSX,
			HttpConstants.MIME_TYPE_XML})
public class ActivityController extends BaseController {

	protected final IXmlMapping xmlMapping;

	@Autowired
	public ActivityController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			@Qualifier("activityWqx") IXmlMapping inXmlMapping,
			ContentNegotiationStrategy contentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, contentStrategy, validator, configurationService);
		xmlMapping = inXmlMapping;
	}

	@HeadOperation
	@DataProfileActivity
	@FullParameterList
	public void activityHeadRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) FilterParameters filter
			) {
		doHeadRequest(request, response, filter);
	}

	@GetOperation
	@DataProfileActivity
	@FullParameterList
	public void activityGetRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) FilterParameters filter
			) {
		doDataRequest(request, response, filter);
	}

	@PostOperation
	public void activityJsonPostRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip,
			@Parameter(hidden=true) @RequestBody FilterParameters filter
			) {
		doDataRequest(request, response, filter, mimeType, zip);
	}

	@FormUrlPostOperation
	public void activityFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	@PostCountOperation
	@ApiResponse(
			responseCode="200",
			description="OK",
			content=@Content(schema=@Schema(implementation=ActivityCountJson.class)))
	public Map<String, String> activityPostCountRequest(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip,
			@Parameter(hidden=true) @RequestBody FilterParameters filter
			) {
		return doPostCountRequest(request, response, filter, mimeType, zip);
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		addActivityHeaders(response, counts);
		return HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT;
	}

	@Override
	protected Map<String, String> getMapping(Profile profile) {
		return ActivityDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

	@Override
	protected Profile determineProfile(FilterParameters filter) {
		return determineProfile(Profile.ACTIVITY, filter);
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
