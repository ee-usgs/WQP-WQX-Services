package gov.usgs.cida.wqp.webservice.SimpleStation;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.IXmlMapping;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value=HttpConstants.SIMPLE_STATION_ENDPOINT, produces={HttpConstants.MIME_TYPE_XML, HttpConstants.MIME_TYPE_JSON})
public class SimpleStationController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleStationController.class);

	protected final IXmlMapping xmlMapping;
	
	@Autowired
	public SimpleStationController(IStreamingDao inStreamingDao, ICountDao inCountDao, 
			IParameterHandler inParameterHandler, ILogService inLogService,
			@Qualifier("maxResultRows") Integer inMaxResultRows,
			@Qualifier("simpleStationWqxOutbound") IXmlMapping inXmlMapping) {
		super(inStreamingDao, inCountDao, inParameterHandler, inLogService, inMaxResultRows);
		xmlMapping = inXmlMapping;
		
		LOG.trace(getClass().getName());
	}

	/**
	 * SimpleStation HEAD request
	 */
	@RequestMapping(method=RequestMethod.HEAD)
	public void simpleStationHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		doHeadRequest(request, response, IDao.SIMPLE_STATION_NAMESPACE, ENDPOINT_SIMPLE_STATION);
	}
	
	/**
	 * SimpleStation GET request
	 */
	@RequestMapping(method=RequestMethod.GET)
	public void simpleStationGetRequest(HttpServletRequest request, HttpServletResponse response) {
		doGetRequest(request, response, IDao.SIMPLE_STATION_NAMESPACE, ENDPOINT_SIMPLE_STATION);
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		return HEADER_TOTAL_SITE_COUNT;
	}

	@Override
	protected Map<String, String> getMapping() {
		return StationColumn.mappings;
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

}
