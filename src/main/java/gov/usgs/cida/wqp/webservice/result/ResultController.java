package gov.usgs.cida.wqp.webservice.result;

import gov.cida.cdat.control.SCManager;
import gov.cida.cdat.io.Closer;
import gov.cida.cdat.io.TransformOutputStream;
import gov.cida.cdat.io.container.SimpleStreamContainer;
import gov.cida.cdat.io.container.StreamContainer;
import gov.cida.cdat.transform.CharacterSeparatedValue;
import gov.cida.cdat.transform.IXmlMapping;
import gov.cida.cdat.transform.MapToJsonTransformer;
import gov.cida.cdat.transform.MapToXlsxTransformer;
import gov.cida.cdat.transform.MapToXmlTransformer;
import gov.cida.cdat.transform.Transformer;
import gov.usgs.cida.wqp.dao.ICountDao;
import gov.usgs.cida.wqp.dao.IDao;
import gov.usgs.cida.wqp.dao.IStreamingDao;
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
import gov.usgs.cida.wqp.webservice.StationColumnMapping;
import gov.usgs.cida.wqp.webservice.StationWorker;
import gov.usgs.cida.wqp.webservice.StreamingResultHandler;
import gov.usgs.cida.wqp.webservice.simpleStation.SimpleStationMapReformater;
import gov.usgs.cida.wqp.webservice.simpleStation.SimpleStationXmlMapping;

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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResultController extends BaseController implements HttpConstants, MybatisConstants, ValidationConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());

	protected IStreamingDao streamingDao;
	protected ICountDao countDao;
	protected IParameterHandler parameterHandler;
	protected ILogService logService;
	protected ParameterMap pm;

	
	@Autowired
	public ResultController(IStreamingDao streamingDao, ICountDao countDao, 
			IParameterHandler parameterHandler, ILogService logService) {
		
		log.trace(getClass().getName());
		
		this.streamingDao     = streamingDao;
		this.parameterHandler = parameterHandler;
		this.countDao         = countDao;
		this.logService       = logService;
	}
	

	@RequestMapping(value="xxx", method=RequestMethod.HEAD)
	public void simpleStationHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		log.info("Processing Head: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		
		try {
			doHeaderXXX(request, response, logId);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
			log.info("Processing Head complete: {}", request.getQueryString());
		}
	}
	private void doHeaderXXX(HttpServletRequest request, HttpServletResponse response, BigDecimal logId) {
		response.setCharacterEncoding(DEFAULT_ENCODING);
		pm = new ParameterValidation().preProcess(request, parameterHandler);
		if ( ! pm.isValid() ) {
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.writeWarningHeaders(response, pm.getValidationMessages(), WQX_EMPTY_DOC);
			log.info("Processing Head invalid params end:{}", request.getQueryString());
		}
		String mimeTypeParam = (String) pm.getQueryParameters().get(Parameters.MIMETYPE.toString());
		MimeType mimeType = MimeType.xml.fromString(mimeTypeParam);
		HttpUtils httpUtils = new HttpUtils();

		List<Map<String, Object>> counts = countDao.getCounts(ICountDao.SIMPLE_STATION_NAMESPACE, pm.getQueryParameters());

		response.setCharacterEncoding(DEFAULT_ENCODING);
		response.addHeader(HEADER_CONTENT_TYPE, mimeType.getMimeType());
		httpUtils.addCountHeader(response, counts);
		response.setHeader("Content-Disposition","attachment; filename="+ICountDao.SIMPLE_STATION_NAMESPACE.toLowerCase()+"."+mimeType);

		logService.logHeadComplete(response, logId);
	}
	@RequestMapping(value="xxx", method=RequestMethod.GET, produces={MIME_TYPE_XLSX, MIME_TYPE_XML, MIME_TYPE_JSON})
	public void simplestationGetRequest(HttpServletRequest request, HttpServletResponse response) {
		log.trace(""); // blank line during trace
		log.info("Processing Get: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		
		SCManager session = null;
		try {
			doHeaderXXX(request, response, logId);
			
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
					case xlsx:
						transformer = new MapToXlsxTransformer(responseStream, StationColumnMapping.mappings);
						break;
					case xml:
					default:
						IXmlMapping mapping = new SimpleStationXmlMapping();
						String xmlRootNode  = "<" + mapping.getRoot() + " " + mapping.getRootNamespace() + ">";
						transformer = new MapToXmlTransformer(mapping, xmlRootNode, StationColumnMapping.VALUE_PROVIDER);
						break;
				}
				TransformOutputStream transformStream = new ObjectTransformStream(responseStream, logService, logId, transformer);
				
				ResultHandler handler = new StreamingResultHandler(transformStream);
				streamingDao.stream(IDao.SIMPLE_STATION_NAMESPACE, pm.getQueryParameters(), handler);


		} catch (Exception e) {
			//TODO We can't just eat these.
			log.error("Error openging outputstream",e);
			throw new RuntimeException(e);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
			log.info("Processing Get complete: {}", request.getQueryString());
		}
	}
	
	
	
	
	/**
	 * Result HEAD request
	 */
	@RequestMapping(value=RESULT_SEARCH_ENPOINT, method=RequestMethod.HEAD, produces={MIME_TYPE_TSV, MIME_TYPE_CSV, MIME_TYPE_XLSX})
//	@Async
	public void resultHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		log.info("Processing Head: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
//		SCManager session = null;
		
		try {
//			session = doHeader(request, response, logId);
			doHeader(request, response, logId);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
//			Closer.close(session);
			log.info("Processing Head complete: {}", request.getQueryString());
		}
	}
	
	
	/**
	 * Shared header helper method share for both the HEAD and GET requests
	 * @param request
	 * @param response
	 * @return cDAT session opened here for use on the GET request - bit kluggy but DRY'er code
	 */
//	private SCManager doHeader(HttpServletRequest request, HttpServletResponse response, BigDecimal logId) {
	private void doHeader(HttpServletRequest request, HttpServletResponse response, BigDecimal logId) {
		response.setCharacterEncoding(DEFAULT_ENCODING);
		pm = new ParameterValidation().preProcess(request, parameterHandler);
		if ( ! pm.isValid() ) {
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.writeWarningHeaders(response, pm.getValidationMessages(), WQX_EMPTY_DOC);
			log.info("Processing Head invalid params end:{}", request.getQueryString());
//			return null;
		}
//		SCManager   session = SCManager.open().setAutoStart(true);
//		HeaderWorker header = new HeaderWorker(response, IDao.RESULT_NAMESPACE, pm, countDao, MimeType.xml);
//		header.setFilename(ICountDao.SIMPLE_STATION_NAMESPACE.toLowerCase());
//		String stationCount = session.addWorker("SimpleStationCount", header);

//		AsyncUtils.waitForComplete(session, stationCount);

//		if (header.hasError()) {
			//TODO We can't just eat these.
//			throw new RuntimeException(header.getCurrentError());
//		}
		
		String mimeTypeParam = (String) pm.getQueryParameters().get(Parameters.MIMETYPE.toString());
		MimeType mimeType = MimeType.xml.fromString(mimeTypeParam);
		HttpUtils httpUtils = new HttpUtils();

		List<Map<String, Object>> counts = countDao.getCounts(IDao.RESULT_NAMESPACE, pm.getQueryParameters());

		response.setCharacterEncoding(DEFAULT_ENCODING);
		response.addHeader(HEADER_CONTENT_TYPE, mimeType.getMimeType());
		httpUtils.addCountHeader(response, counts);
		response.setHeader("Content-Disposition","attachment; filename="+IDao.RESULT_NAMESPACE.toLowerCase()+"."+mimeType);

		logService.logHeadComplete(response, logId);
//		return session;
	}
	
	
	/**
	 * result search request
	 */
	@RequestMapping(value=RESULT_SEARCH_ENPOINT, method=RequestMethod.GET, produces=MIME_TYPE_XLSX) //{MIME_TYPE_TSV, MIME_TYPE_CSV, MIME_TYPE_XLSX})
//	@Async
	public void stationGetRequest(HttpServletRequest request, HttpServletResponse response) {
		log.trace(""); // blank line during trace
		log.info("Processing Get: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		
		SCManager session = null;
		try {
//			session = doHeader(request, response, logId);
			doHeader(request, response, logId);
//			if (session != null) {
				
				Transformer transformer; 
				OutputStream responseStream = response.getOutputStream();
				String mimeTypeParam = pm.getParameter(Parameters.MIMETYPE);
				MimeType mimeType = MimeType.xml.fromString(mimeTypeParam);
				
				switch (mimeType) {
					case xlsx:
						transformer = new MapToXlsxTransformer(responseStream, ResultColumnMapping.mappings);
						break;
					case tsv:
						transformer = CharacterSeparatedValue.TSV;
						responseStream = new ResultColumnMapping(responseStream); // column rename
						break;
					case csv:
					default:
						transformer = CharacterSeparatedValue.CSV;
						responseStream = new ResultColumnMapping(responseStream); // column rename
				}
				TransformOutputStream transformStream = new ObjectTransformStream(responseStream, logService, logId, transformer);
//				StreamContainer<TransformOutputStream> transformProvider = new SimpleStreamContainer<TransformOutputStream>(transformStream);
				
				ResultHandler handler = new StreamingResultHandler(transformStream);
				streamingDao.stream(IDao.RESULT_NAMESPACE, pm.getQueryParameters(), handler);


//				StationWorker worker = new StationWorker(IDao.RESULT_NAMESPACE, pm, streamingDao, transformProvider);
//				String stationName = session.addWorker("SimpleStation", worker);

//				AsyncUtils.waitForComplete(session, stationName, true);
//			}
		} catch (Exception e) {
			//TODO We can't just eat these.
			log.error("Error openging outputstream",e);
			throw new RuntimeException(e);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
//			Closer.close(session); // handled in the AsyncUtils
			log.info("Processing Get complete: {}", request.getQueryString());
		}
	}


}
