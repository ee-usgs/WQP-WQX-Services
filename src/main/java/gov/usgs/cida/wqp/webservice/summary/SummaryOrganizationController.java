package gov.usgs.cida.wqp.webservice.summary;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.ConfigurationService;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.annotation.ProfileParameterSummaryOrganization;
import gov.usgs.cida.wqp.swagger.model.StationCountJson;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags={SwaggerConfig.SUMMARY_ORGANIZATION_TAG_NAME})
@RestController
@RequestMapping(value=HttpConstants.SUMMARY_ORGANIZATION_ENDPOINT,
	produces={HttpConstants.MIME_TYPE_GEOJSON})
public class SummaryOrganizationController extends BaseController {

    public SummaryOrganizationController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService, ContentNegotiationStrategy inContentStrategy, Validator inValidator, ConfigurationService configurationService) {
	super(inStreamingDao, inCountDao, inLogService, inContentStrategy, inValidator, configurationService);
    }
    
    @ApiOperation(value="Return appropriate request headers (including anticipated record counts).")
    @ProfileParameterSummaryOrganization
    @RequestMapping(method=RequestMethod.HEAD)
    public void summaryOrganizationRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
	    doHeadRequest(request, response, filter);
    }
    
    @ApiOperation(value="Return requested data.")
    @ProfileParameterSummaryOrganization
    @GetMapping()
    public void summaryOrganizationGetRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
	    doDataRequest(request, response, filter);
    }
    
    @ApiOperation(value="Return requested data. Use when the list of parameter values is too long for a query string.")
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void summaryOrganizationJsonPostRequest(HttpServletRequest request, HttpServletResponse response,
		    @RequestParam(value="mimeType", required=false) String mimeType,
		    @RequestParam(value="zip", required=false) String zip,
		    @RequestBody @ApiIgnore FilterParameters filter) {
	    doDataRequest(request, response, filter, mimeType, zip);
    }
    
    @ApiOperation(value="Same as the JSON consumer, but hidden from swagger", hidden=true)
    @PostMapping(consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void summaryOrganizationFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter) {
	    doDataRequest(request, response, filter);
    }
    
    @ApiOperation(value="Return anticipated record counts.")
    @ApiResponses(value={@ApiResponse(code=200, message="OK", response=StationCountJson.class)})
    @PostMapping(value="count", produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> summaryOrganizationPostCountRequest(HttpServletRequest request, HttpServletResponse response,
		    @RequestParam(value="mimeType", required=false) String mimeType,
		    @RequestParam(value="zip", required=false) String zip,
		    @RequestBody @ApiIgnore FilterParameters filter) {
	    return doPostCountRequest(request, response, filter, mimeType, zip);
    }
    
    

    @Override  
    protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
	addSiteHeaders(response, counts);
	return HttpConstants.HEADER_TOTAL_SITE_COUNT;
    }

    @Override 
    protected Profile determineProfile(FilterParameters filter) {
	return determineProfile(Profile.SUMMARY_ORGANIZATION, filter);
    }

    @Override
    protected Map<String, String> getMapping(Profile profile) {
	return null;
    }

    @Override
    protected IXmlMapping getXmlMapping() {
	return null;
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
