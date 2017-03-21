package gov.usgs.cida.wqp.webservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.ResultDelimited;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;

@Controller
@RequestMapping(produces={HttpConstants.MIME_TYPE_TSV, HttpConstants.MIME_TYPE_CSV, HttpConstants.MIME_TYPE_XLSX, HttpConstants.MIME_TYPE_XML})
public class ResDetectQntLmtController extends BaseController {

	protected final IXmlMapping xmlMapping;

	@Autowired
	public ResDetectQntLmtController(IStreamingDao inStreamingDao, ICountDao inCountDao, 
			IParameterHandler inParameterHandler, ILogService inLogService,
			@Qualifier("maxResultRows") Integer inMaxResultRows,
			@Qualifier("resDetectQntLmtWqx") IXmlMapping inXmlMapping,
			@Qualifier("siteUrlBase") String inSiteUrlBase) {
		super(inStreamingDao, inCountDao, inParameterHandler, inLogService, inMaxResultRows, inSiteUrlBase);
		xmlMapping = inXmlMapping;
	}

	@RequestMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENPOINT, method=RequestMethod.HEAD)
	public void resDetectQntLmtHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		doHeadRequest(request, response);
	}

	@RequestMapping(value=HttpConstants.RES_DETECT_QNT_LMT_REST_ENPOINT, method=RequestMethod.HEAD)
	public void resDetectQntLmtHeadRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("activity") String activity,
			@PathVariable("result") String result,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) {
		doHeadRequest(request, response);
	}

	@GetMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENPOINT)
	public void resDetectQntLmtGetRequest(HttpServletRequest request, HttpServletResponse response) {
		doGetRequest(request, response);
	}

	@GetMapping(value=HttpConstants.RES_DETECT_QNT_LMT_REST_ENPOINT)
	public void resDetectQntLmtGetRestRequest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("activity") String activity,
			@PathVariable("result") String result,
			@RequestParam(value="mimeType", required=false) String mimeType,
			@RequestParam(value="zip", required=false) String zip) {
		doGetRequest(request, response);
	}

	@PostMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENPOINT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void resDetectQntLmtJsonPostRequest(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> postParms) {
		doPostRequest(request, response, postParms);
	}

	@PostMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENPOINT, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void resDetectQntLmtFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response) {
		doPostRequest(request, response, null);
	}

	@PostMapping(value=HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENPOINT + "/count", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> resDetectQntLmtPostCountRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Map<String, Object> postParms) {
		return doPostCountRequest(request, response, postParms);
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		addActivityHeaders(response, counts);
		addResultHeaders(response, counts);
		addResDetectQntLmtHeaders(response, counts);
		return HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT;
	}

	@Override
	protected Map<String, String> getMapping(String profile) {
		return ResultDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

	@Override
	protected String determineProfile(Map<String, Object> pm) {
		return determineProfile(Profile.RES_DETECT_QNT_LMT, pm);
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return null;
	}

}
