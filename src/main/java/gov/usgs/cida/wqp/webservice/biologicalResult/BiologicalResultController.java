package gov.usgs.cida.wqp.webservice.biologicalResult;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.BioResultColumn;
import gov.usgs.cida.wqp.mapping.IXmlMapping;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.webservice.BaseController;

@Controller
@RequestMapping(value=HttpConstants.RESULT_SEARCH_ENPOINT, params="dataProfile=biological",
	produces={HttpConstants.MIME_TYPE_TSV, HttpConstants.MIME_TYPE_CSV, HttpConstants.MIME_TYPE_XLSX, HttpConstants.MIME_TYPE_XML})
public class BiologicalResultController extends BaseController {

	protected final IXmlMapping xmlMapping;
	
	@Autowired
	public BiologicalResultController(IStreamingDao inStreamingDao, ICountDao inCountDao, 
			IParameterHandler inParameterHandler, ILogService inLogService,
			@Qualifier("maxResultRows") Integer inMaxResultRows,
			@Qualifier("bioResultWqx") IXmlMapping inXmlMapping,
			@Qualifier("siteUrlBase") String inSiteUrlBase) {
		super(inStreamingDao, inCountDao, inParameterHandler, inLogService, inMaxResultRows, inSiteUrlBase);
		xmlMapping = inXmlMapping;
	}
	
	/**
	 * Result HEAD request
	 */
	@RequestMapping(method=RequestMethod.HEAD)
	public void resultHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		doHeadRequest(request, response, IDao.BIOLOGICAL_RESULT_NAMESPACE, ENDPOINT_BIOLOGICAL_RESULT);
	}

	/**
	 * Result GET request
	 */
	@RequestMapping(method=RequestMethod.GET)
	public void resultGetRequest(HttpServletRequest request, HttpServletResponse response) {
		doGetRequest(request, response, IDao.BIOLOGICAL_RESULT_NAMESPACE, ENDPOINT_BIOLOGICAL_RESULT);
	}

	/**
	 * Result POST request with json
	 */
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void resultJsonPostRequest(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> postParms) {
		doPostRequest(request, response, IDao.BIOLOGICAL_RESULT_NAMESPACE, ENDPOINT_BIOLOGICAL_RESULT, postParms);
	}
	
	/**
	 * Result POST request with form urlencoded
	 */
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void resultFormUrlencodedPostRequest(HttpServletRequest request, HttpServletResponse response) {
		doPostRequest(request, response, IDao.BIOLOGICAL_RESULT_NAMESPACE, ENDPOINT_BIOLOGICAL_RESULT, null);
	}

	/**
	 * Result POST count request
	 */
	@RequestMapping(value="count", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> resultPostCountRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Map<String, Object> postParms) {
		return doPostCountRequest(request, response, IDao.BIOLOGICAL_RESULT_NAMESPACE, ENDPOINT_BIOLOGICAL_RESULT, postParms);
	}
	
	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		addCountHeaders(response, counts, HEADER_TOTAL_RESULT_COUNT, HEADER_RESULT_COUNT, MybatisConstants.RESULT_COUNT);
		return HEADER_TOTAL_RESULT_COUNT;
	}

	@Override
	protected Map<String, String> getMapping() {
		return BioResultColumn.mappings;
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

}
