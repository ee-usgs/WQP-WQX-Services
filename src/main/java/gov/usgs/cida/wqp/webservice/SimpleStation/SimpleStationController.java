package gov.usgs.cida.wqp.webservice.SimpleStation;

import gov.cida.cdat.control.Control;
import gov.cida.cdat.control.SCManager;
import gov.cida.cdat.control.Time;
import gov.usgs.cida.wqp.dao.ICountDao;
import gov.usgs.cida.wqp.dao.IDao;
import gov.usgs.cida.wqp.dao.IStreamingDao;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.util.PutSomeWhereElse;
import gov.usgs.cida.wqp.validation.ParameterValidation;
import gov.usgs.cida.wqp.validation.ValidationConstants;
import gov.usgs.cida.wqp.webservice.HeaderWorker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleStationController implements HttpConstants, MybatisConstants, ValidationConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());

	protected IStreamingDao streamingDao;
	protected ICountDao countDao;

	protected IParameterHandler parameterHandler;

	@Autowired
	public SimpleStationController(
			IStreamingDao inStreamingDao,
			ICountDao inCountDao,
			IParameterHandler inParameterHandler) {
		log.trace(getClass().getName());
		streamingDao = inStreamingDao;
		parameterHandler = inParameterHandler;
		countDao = inCountDao;
	}

	/**
	 * SimpleStation HEAD request
	 */
	@RequestMapping(value=SIMPLE_STATION_ENDPOINT, method=RequestMethod.HEAD, produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@Async
	public void simpleStationHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		log.info("Processing Head: {}", request.getQueryString());
		SCManager session = null;
		try {
			session = doHeader(request, response);
		} finally {
			if (session != null) {
				session.close();
			}
			log.info("Processing Head complete: {}", request.getQueryString());
		}
	}
	
	/**
	 * Shared header helper method share for both the HEAD and GET requests
	 * @param request
	 * @param response
	 * @return cDAT session opened here for use on the GET request - bit kluggy but DRY'er code
	 */
	private SCManager doHeader(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding(DEFAULT_ENCODING);
		ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);
		if ( ! pm.isValid() ) {
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.writeWarningHeaders(response, pm.getValidationMessages(), WQX_EMPTY_DOC);
			log.info("Processing Head invalid params end:{}", request.getQueryString());
			return null;
		}
		SCManager session = SCManager.open();
		HeaderWorker header = new HeaderWorker(response, ICountDao.STATION_NAMESPACE, pm, countDao, MEDIA_TYPE_XML);
		String stationCount = session.addWorker("SimpleStationCount", header);
		session.send(stationCount, Control.Start);
		session.waitForComplete(stationCount, Time.SECOND.asMS());
		if (header.hasError()) {
			//TODO We can't just eat these.
			throw new RuntimeException(header.getCurrentError());
		}
		return session;
	}
	
	/**
	 * station search request
	 */
	@RequestMapping(value=SIMPLE_STATION_ENDPOINT, method=RequestMethod.GET, produces={MIME_TYPE_XLSX, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@Async
	public void stationGetRequest(HttpServletRequest request, HttpServletResponse response) {
		log.trace(""); // blank line during trace
		log.info("Processing Get: {}", request.getQueryString()); // TODO use SLF4J to avoid string concatenation inline
		
		ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);
		SCManager session = null;
		try {
			session = doHeader(request, response);
			if (session != null) {
				TransformOutputStream transformer;
				String mimeType = PutSomeWhereElse.getMimeType(pm, MEDIA_TYPE_XML);
				switch (mimeType) {
				case MEDIA_TYPE_JSON:
					transformer = new SimpleStationJsonTransformer( response.getOutputStream());
					break;
				//TODO here only for demo purposes needs to be removed before going to production.	
				case MEDIA_TYPE_XLSX:
					transformer = new XlsxTransformer( response.getOutputStream(), XXXStationColumnMapper.getMappings());
					break;
				default:
					transformer = new XmlTransformer( response.getOutputStream(), new SimpleStationXmlMapping());
					break;
				}
					
				SimpleStationWorker worker = new SimpleStationWorker(response, IDao.SIMPLE_STATION_NAMESPACE, pm, streamingDao, transformer);
				String stationName = session.addWorker("SimpleStation", worker);
				session.send(stationName, Control.Start);
				session.waitForComplete(stationName, Time.SECOND.asMS());
				if (worker.hasError()) {
					//TODO We can't just eat these.
					throw new RuntimeException(worker.getCurrentError());
				}
			}
		} catch (Exception e) {
			//TODO We can't just eat these.
			log.error("Error openging outputstream",e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				session.close();
			}
			log.info("Processing Get complete: {}", request.getQueryString());
		}
	}

}
