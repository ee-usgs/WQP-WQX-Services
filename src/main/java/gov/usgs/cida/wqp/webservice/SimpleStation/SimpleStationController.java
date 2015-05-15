package gov.usgs.cida.wqp.webservice.SimpleStation;

import static gov.usgs.cida.wqp.mapping.BaseWqx.WQX_PROVIDER;
import gov.cida.cdat.control.SCManager;
import gov.cida.cdat.io.Closer;
import gov.cida.cdat.io.TransformOutputStream;
import gov.cida.cdat.io.container.SimpleStreamContainer;
import gov.cida.cdat.io.container.StreamContainer;
import gov.cida.cdat.transform.IXmlMapping;
import gov.cida.cdat.transform.MapToJsonTransformer;
import gov.cida.cdat.transform.MapToXmlTransformer;
import gov.cida.cdat.transform.Transformer;
import gov.usgs.cida.wqp.dao.ICountDao;
import gov.usgs.cida.wqp.dao.IDao;
import gov.usgs.cida.wqp.dao.IStreamingDao;
import gov.usgs.cida.wqp.mapping.SimpleStationWqxOutbound;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.util.MimeType;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.validation.ParameterValidation;
import gov.usgs.cida.wqp.validation.ValidationConstants;
import gov.usgs.cida.wqp.webservice.AsyncUtils;
import gov.usgs.cida.wqp.webservice.BaseController;
import gov.usgs.cida.wqp.webservice.HeaderWorker;
import gov.usgs.cida.wqp.webservice.ObjectTransformStream;
import gov.usgs.cida.wqp.webservice.StationWorker;

import java.io.OutputStream;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value=HttpConstants.SIMPLE_STATION_ENDPOINT, produces={HttpConstants.MIME_TYPE_XML, HttpConstants.MIME_TYPE_JSON})
public class SimpleStationController extends BaseController implements HttpConstants, MybatisConstants, ValidationConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());

	protected IStreamingDao streamingDao;
	protected ICountDao countDao;
	protected IParameterHandler parameterHandler;
	protected ILogService logService;
	protected ParameterMap pm;

	
	@Autowired
	public SimpleStationController(IStreamingDao streamingDao, ICountDao countDao, 
			IParameterHandler parameterHandler, ILogService logService) {
		
		log.trace(getClass().getName());
		
		this.streamingDao     = streamingDao;
		this.parameterHandler = parameterHandler;
		this.countDao         = countDao;
		this.logService       = logService;
	}
	

	/**
	 * SimpleStation HEAD request
	 */
	@RequestMapping(method=RequestMethod.HEAD)
	@Async
	public void simpleStationHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		log.info("Processing Head: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		SCManager session = null;
		
		try {
			session = doHeader(request, response, logId);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
			Closer.close(session);
			log.info("Processing Head complete: {}", request.getQueryString());
		}
	}
	
	
	/**
	 * Shared header helper method share for both the HEAD and GET requests
	 * @param request
	 * @param response
	 * @return cDAT session opened here for use on the GET request - bit kluggy but DRY'er code
	 */
	private SCManager doHeader(HttpServletRequest request, HttpServletResponse response, BigDecimal logId) {
		response.setCharacterEncoding(DEFAULT_ENCODING);
		pm = new ParameterValidation().preProcess(request, parameterHandler);
		if ( ! pm.isValid() ) {
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.writeWarningHeaders(response, pm.getValidationMessages());
			log.info("Processing Head invalid params end:{}", request.getQueryString());
			return null;
		}
		SCManager   session = SCManager.open().setAutoStart(true);
		HeaderWorker header = new HeaderWorker(response, ICountDao.SIMPLE_STATION_NAMESPACE, pm, countDao, MimeType.xml);
		header.setFilename(ICountDao.SIMPLE_STATION_NAMESPACE.toLowerCase());
		String stationCount = session.addWorker("SimpleStationCount", header);

		AsyncUtils.waitForComplete(session, stationCount);

		if (header.hasError()) {
			//TODO We can't just eat these.
			throw new RuntimeException(header.getCurrentError());
		}
		logService.logHeadComplete(response, logId);
		return session;
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
		
		SCManager session = null;
		try {
			session = doHeader(request, response, logId);
			if (session != null) {
				
				Transformer transformer; 
				OutputStream responseStream = response.getOutputStream();
				String mimeTypeParam = pm.getParameter(Parameters.MIMETYPE);
				MimeType mimeType = MimeType.xml.fromString(mimeTypeParam);
				
				switch (mimeType) {
					case json:
						String header = "{\"type\":\"FeatureCollection\",\"features\":[";
						String footer = "]}";
						transformer = new MapToJsonTransformer(header, footer);
						transformer = new SimpleStationMapReformater(transformer);
						break;
					case xml:
					default:
						IXmlMapping mapping = new SimpleStationWqxOutbound();
						transformer = new MapToXmlTransformer(mapping, WQX_PROVIDER);
						break;
				}
				TransformOutputStream transformStream = new ObjectTransformStream(responseStream, logService, logId, transformer);
				StreamContainer<TransformOutputStream> transformProvider = new SimpleStreamContainer<TransformOutputStream>(transformStream);
				
				StationWorker worker = new StationWorker(IDao.SIMPLE_STATION_NAMESPACE, pm, streamingDao, transformProvider);
				String stationName = session.addWorker("SimpleStation", worker);

				AsyncUtils.waitForComplete(session, stationName, true);
			}
		} catch (Exception e) {
			//TODO We can't just eat these.
			log.error("Error openging outputstream",e);
			throw new RuntimeException(e);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
			log.info("Processing Get complete: {}", request.getQueryString());
		}
	}

}
