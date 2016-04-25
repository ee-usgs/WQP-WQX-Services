package gov.usgs.cida.wqp.webservice.station;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.IXmlMapping;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseController;

@Controller
@RequestMapping(value=HttpConstants.STATION_SEARCH_ENPOINT,
	produces={HttpConstants.MIME_TYPE_XLSX,
			  HttpConstants.MIME_TYPE_CSV,
			  HttpConstants.MIME_TYPE_TSV,
			  HttpConstants.MIME_TYPE_XML,
			  HttpConstants.MIME_TYPE_KML,
			  HttpConstants.MIME_TYPE_KMZ,
			  HttpConstants.MIME_TYPE_GEOJSON})
public class StationController extends BaseController {

	protected final IXmlMapping xmlMapping;
	protected final IXmlMapping kmlMapping;
	
	@Autowired
	public StationController(IStreamingDao inStreamingDao, ICountDao inCountDao,
			IParameterHandler inParameterHandler, ILogService inLogService,
			@Qualifier("maxResultRows") Integer inMaxResultRows,
			@Qualifier("stationWqx") IXmlMapping inXmlMapping,
			@Qualifier("stationKml") IXmlMapping inKmlMapping) {
		super(inStreamingDao, inCountDao, inParameterHandler, inLogService, inMaxResultRows);
		xmlMapping = inXmlMapping;
		kmlMapping = inKmlMapping;
	}

	/**
	 * Station HEAD request
	 */
	@RequestMapping(method=RequestMethod.HEAD)
	public void stationHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		doHeadRequest(request, response, IDao.STATION_NAMESPACE, ENDPOINT_STATION);
	}
	
	/**
	 * Station GET request
	 */
	@RequestMapping(method=RequestMethod.GET)
	public void stationGetRequest(HttpServletRequest request, HttpServletResponse response) {
		doGetRequest(request, response, IDao.STATION_NAMESPACE, ENDPOINT_STATION);
	}
	
	/**
	 * Station POST request
	 */
	@RequestMapping(method=RequestMethod.POST, consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public void stationPostRequest(HttpServletRequest request, HttpServletResponse response, @ModelAttribute HashMap<String, Object> postParms) {
		doPostRequest(request, response, IDao.STATION_NAMESPACE, ENDPOINT_STATION, postParms);
	}
	
	/**
	 * Station POST count request
	 */
	@RequestMapping(value="count", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> stationPostCountRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Map<String, Object> postParms) {
		return doPostCountRequest(request, response, IDao.STATION_NAMESPACE, ENDPOINT_STATION, postParms);
	}
	
	@Override
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

	@Override
	protected IXmlMapping getKmlMapping() {
		return kmlMapping;
	}

}