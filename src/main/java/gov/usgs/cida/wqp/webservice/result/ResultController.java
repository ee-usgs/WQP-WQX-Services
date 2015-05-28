package gov.usgs.cida.wqp.webservice.result;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.IXmlMapping;
import gov.usgs.cida.wqp.mapping.ResultColumn;
import gov.usgs.cida.wqp.mapping.ResultWqx;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.webservice.BaseController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value=HttpConstants.RESULT_SEARCH_ENPOINT,
	produces={HttpConstants.MIME_TYPE_TSV, HttpConstants.MIME_TYPE_CSV, HttpConstants.MIME_TYPE_XLSX, HttpConstants.MIME_TYPE_XML})
public class ResultController extends BaseController {

	@Autowired
	public ResultController(IStreamingDao inStreamingDao, ICountDao inCountDao, 
			IParameterHandler inParameterHandler, ILogService inLogService) {
		super(inStreamingDao, inCountDao, inParameterHandler, inLogService);
	}
	
	/**
	 * Result HEAD request
	 */
	@RequestMapping(method=RequestMethod.HEAD)
	public void resultHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		doHeadRequest(request, response, IDao.RESULT_NAMESPACE, ENDPOINT_RESULT);
	}

	/**
	 * Result GET request
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String resultGetRequest(HttpServletRequest request, HttpServletResponse response) {
		return doGetRequest(request, response, IDao.RESULT_NAMESPACE, ENDPOINT_RESULT);
	}

	protected void addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.addPcResultHeaders(response, counts);
	}

	@Override
	protected Map<String, String> getMapping() {
		return ResultColumn.mappings;
	}


	@Override
	protected IXmlMapping getXmlMapping() {
		return new ResultWqx();
	}


	@Override
	protected IXmlMapping getKmlMapping() {
		// Result has no KML
		return null;
	}

}
