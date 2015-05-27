package gov.usgs.cida.wqp.webservice.station;

import gov.usgs.cida.wqp.dao.StreamingResultHandler;
import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.mapping.StationKml;
import gov.usgs.cida.wqp.mapping.StationWqx;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.transform.MapToDelimitedTransformer;
import gov.usgs.cida.wqp.transform.MapToKmlTransformer;
import gov.usgs.cida.wqp.transform.MapToXlsxTransformer;
import gov.usgs.cida.wqp.transform.MapToXmlTransformer;
import gov.usgs.cida.wqp.transform.Transformer;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.util.MimeType;
import gov.usgs.cida.wqp.validation.ParameterValidation;
import gov.usgs.cida.wqp.webservice.BaseController;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value=HttpConstants.STATION_SEARCH_ENPOINT,
	produces={HttpConstants.MIME_TYPE_XLSX, HttpConstants.MIME_TYPE_CSV, HttpConstants.MIME_TYPE_TSV, HttpConstants.MIME_TYPE_XML, HttpConstants.MIME_TYPE_KML}) //, HttpConstants.MIME_TYPE_JSON
public class StationController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(StationController.class);

	/* ========================================================================
	 * Beans		===========================================================
	 * ========================================================================
	 */
	protected final IStreamingDao streamingDao;
	protected final ICountDao countDao;
	protected final IParameterHandler parameterHandler;
	protected final ILogService logService;
	protected final String kmlStyleUrl;
	protected ParameterMap pm;

	
	@Autowired
	public StationController(IStreamingDao inStreamingDao, ICountDao inCountDao,
			IParameterHandler inParameterHandler, ILogService inLogService,
			@Qualifier("kmlStyleUrl")
			String inKmlStyleUrl) {
		
		log.trace(getClass().getName());
		
		streamingDao     = inStreamingDao;
		parameterHandler = inParameterHandler;
		countDao         = inCountDao;
		logService       = inLogService;
		kmlStyleUrl      = inKmlStyleUrl;
	}

	/* ========================================================================
	 * Actions		===========================================================
	 * ========================================================================
	 */
	/**
	 * Station HEAD request
	 */
	@RequestMapping(method=RequestMethod.HEAD)
	public void stationHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		log.info("Processing Head: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		
		try {
			doHeader(request, response, logId);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
			log.info("Processing Head complete: {}", request.getQueryString());
		}
	}
	
	
	/**
	 * Shared header helper method share for both the HEAD and GET requests
	 * @param request
	 * @param response
	 */
	private boolean doHeader(HttpServletRequest request, HttpServletResponse response, BigDecimal logId) {
		response.setCharacterEncoding(DEFAULT_ENCODING);
		pm = new ParameterValidation().preProcess(request, parameterHandler);
		if ( ! pm.isValid() ) {
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.writeWarningHeaders(response, pm.getValidationMessages());
			log.info("Processing Head invalid params end:{}", request.getQueryString());
			return false;
		}

		String mimeTypeParam = (String) pm.getQueryParameters().get(Parameters.MIMETYPE.toString());
		MimeType mimeType = MimeType.xml.fromString(mimeTypeParam);
		HttpUtils httpUtils = new HttpUtils();

		List<Map<String, Object>> counts = countDao.getCounts(IDao.STATION_NAMESPACE, pm.getQueryParameters());

		response.setCharacterEncoding(DEFAULT_ENCODING);
		response.addHeader(HEADER_CONTENT_TYPE, mimeType.getMimeType());
		httpUtils.addPcResultHeaders(response, counts);
		response.setHeader("Content-Disposition","attachment; filename="+ENDPOINT_STATION.toLowerCase()+"."+mimeType);
		
		logService.logHeadComplete(response, logId);
		return true;
	}
	

	/**
	 * station search request
	 */
	@RequestMapping(method=RequestMethod.GET)
	@Async
	public void stationGetRequest(HttpServletRequest request, HttpServletResponse response) {
		log.trace(""); // blank line during trace
		log.info("Processing Get: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);

		if (doHeader(request, response, logId)) {
			try {
				Transformer transformer;
				OutputStream responseStream = response.getOutputStream();
				String mimeTypeParam = pm.getParameter(Parameters.MIMETYPE);
				MimeType mimeType = MimeType.csv.fromString(mimeTypeParam);
				String namespace = IDao.STATION_NAMESPACE;
				
				switch (mimeType) {
	//				case json:
	//					String header = "{\"type\":\"FeatureCollection\",\"features\":[";
	//					String footer = "]}";
	//					transformer = new MapToJsonTransformer(header, footer);
	//					transformer = new SimpleStationMapReformater(transformer); // TODO need to enhance the LinkedHashMap to include station fields
	//					break;
					case xlsx:
						transformer = new MapToXlsxTransformer(responseStream, StationColumn.mappings, logService, logId);
						break;
					case xml:
						transformer = new MapToXmlTransformer(responseStream, new StationWqx(), logService, logId);
						break;
					case kml:
						transformer = new MapToKmlTransformer(responseStream, new StationKml(kmlStyleUrl), logService, logId);
						namespace = IDao.STATION_KML_NAMESPACE;
						break;
					case tsv:
						transformer = new MapToDelimitedTransformer(responseStream, StationColumn.mappings, logService, logId, MapToDelimitedTransformer.TAB);
						break;
					case csv:
					default:
						transformer = new MapToDelimitedTransformer(responseStream, StationColumn.mappings, logService, logId, MapToDelimitedTransformer.COMMA);
				}
				
				ResultHandler handler = new StreamingResultHandler(transformer);
				streamingDao.stream(namespace, pm.getQueryParameters(), handler);
	
				transformer.end();

			} catch (Exception e) {
				//TODO We can't just eat these.
				log.error("Error openging outputstream",e);
				throw new RuntimeException(e);
			} finally {
				log.info("Processing Get complete: {}", request.getQueryString());
			}
		} else {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
		}
	}
}