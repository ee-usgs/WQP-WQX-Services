package gov.usgs.cida.wqp.webservice;

import java.io.IOException;

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
import gov.usgs.cida.wqp.util.PutSomeWhereElse;
import gov.usgs.cida.wqp.validation.ParameterValidation;
import gov.usgs.cida.wqp.validation.ValidationConstants;
import gov.usgs.cida.wqp.webservice.SimpleStation.SimpleStationWorker;
import gov.usgs.cida.wqp.webservice.SimpleStation.TransformOutputStream;
import gov.usgs.cida.wqp.webservice.SimpleStation.XXXStationColumnMapper;
import gov.usgs.cida.wqp.webservice.SimpleStation.XlsxTransformer;

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
public class StationController implements HttpConstants, ValidationConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());

	/* ========================================================================
	 * Beans		===========================================================
	 * ========================================================================
	 */
	protected IStreamingDao streamingDao;
	protected ICountDao countDao;

	protected IParameterHandler parameterHandler;

	@Autowired
	public StationController(
			IStreamingDao inStreamingDao,
			ICountDao inCountDao,
			IParameterHandler inParameterHandler) {
		log.trace(getClass().getName());
		streamingDao = inStreamingDao;
		parameterHandler = inParameterHandler;
		countDao = inCountDao;
	}

	/* ========================================================================
	 * Actions		===========================================================
	 * ========================================================================
	 */
	/**
	 * Station HEAD request
	 */
	@RequestMapping(value=STATION_SEARCH_ENPOINT, method=RequestMethod.HEAD)
	@Async
	public void stationHeadRequest(HttpServletRequest request, HttpServletResponse response) {
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
		HeaderWorker header = new HeaderWorker(response, IDao.STATION_NAMESPACE, pm, countDao, MEDIA_TYPE_CSV);
		String stationCount = session.addWorker("StationCount", header);
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
	@RequestMapping(value=STATION_SEARCH_ENPOINT, method=RequestMethod.GET)
	@Async
	public void stationGetRequest(HttpServletRequest request, HttpServletResponse response) {
		log.trace(""); // blank line during trace
		log.info("Processing Get: {}", request.getQueryString()); // TODO use SLF4J to avoid string concatenation inline
		ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);
		SCManager session = null;
		try {
			session = doHeader(request, response);
			if (session != null) {
				//TODO refactor to allow WQX&xlsx&json to all behave nicely
				String stationName;
				String mimeType = PutSomeWhereElse.getMimeType(pm, MEDIA_TYPE_CSV);
				if (MEDIA_TYPE_XLSX.equalsIgnoreCase(mimeType)) {
					stationName = doUglyStuff(session, response, pm);
				} else {
				StationWorker station = new StationWorker(response, IDao.STATION_NAMESPACE, pm, streamingDao);
				stationName = session.addWorker("Station", station);
				}
				session.send(stationName, Control.Start);
				session.waitForComplete(stationName, Time.SECOND.asMS());
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
	
	private String doUglyStuff(SCManager session, HttpServletResponse response, ParameterMap pm) throws IOException {
		TransformOutputStream transformer = new XlsxTransformer(response.getOutputStream(), XXXStationColumnMapper.getMappings());
		SimpleStationWorker worker = new SimpleStationWorker(response, IDao.STATION_NAMESPACE, pm, streamingDao, transformer);
		return session.addWorker("Station", worker);
	}
}