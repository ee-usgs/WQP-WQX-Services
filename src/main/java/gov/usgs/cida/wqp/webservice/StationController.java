package gov.usgs.cida.wqp.webservice;

import gov.cida.cdat.control.Control;
import gov.cida.cdat.control.SCManager;
import gov.cida.cdat.control.Time;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.station.dao.ICountDao;
import gov.usgs.cida.wqp.station.dao.IStationDao;
import gov.usgs.cida.wqp.station.dao.StationDao;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.validation.ParameterValidation;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.context.request.async.DeferredResult;
//import org.springframework.web.servlet.ModelAndView;

@Controller
public class StationController implements HttpConstants, MybatisConstants, ValidationConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());

		
	/* ========================================================================
	 * Beans		===========================================================
	 * ========================================================================
	 */
	@Autowired
	private Environment environment;
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
    @Autowired
    protected IStationDao stationDao;
    public void setStationDao(IStationDao stationDao) {
		this.stationDao = stationDao;
	}
    	
    protected IParameterHandler parameterHandler = new HashMapParameterHandler();
    public void setParameterHandler(IParameterHandler inParameterHandler) {
        parameterHandler = inParameterHandler;
    }
    
    
    public StationController() {
    	log.trace(getClass().getName());
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
        log.info("Processing Head:" + request.getQueryString());
        
        SCManager session = null;
		try {
			session = doHeader(request, response);
		} finally {
			if (session != null) {
				session.close();
			}
		    log.info("Processing Head complete:" + request.getQueryString());
		}

    }
	
    
    private SCManager doHeader(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(DEFAULT_ENCODING);
        ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);

        if ( ! pm.isValid() ) {
            HttpUtils httpUtils = new HttpUtils();
        	httpUtils.writeWarningHeaders(response, pm.getValidationMessages(), WQX_EMPTY_DOC);
	        log.info("Processing Head invalid params end:" + request.getQueryString());
        	return null;
        }
        
		SCManager session = SCManager.open();
		HeaderWorker header = new HeaderWorker(response, StationDao.COUNT_QUERY_ID, pm);
		header.setCountDao((ICountDao)stationDao);
		String stationCount = session.addWorker("StationCount", header);
		session.send(stationCount, Control.Start);

		session.waitForComplete(stationCount, Time.SECOND.asMS());
		return session;
	}
    
    
    /**
     * station search request
     */
	@RequestMapping(value=STATION_SEARCH_ENPOINT, method=RequestMethod.GET, produces=MIME_TYPE_TEXT_CSV)
    @Async
    public void stationGetRequest(HttpServletRequest request, HttpServletResponse response) {
        log.trace(""); // blank line during trace       
        log.info("Processing Get:" + request.getQueryString()); // TODO use SLF4J to avoid string concatenation inline       

        ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);
        
        SCManager session = null;
		try {
			session = doHeader(request, response);
			
			if (session != null) {
				StationWorker station = new StationWorker(response, pm);
				station.setStationDao(stationDao);
				String stationName = session.addWorker("Station", station);				
				session.send(stationName, Control.Start);

				session.waitForComplete(stationName, Time.SECOND.asMS());				
			}
		} catch (Exception e) {
			log.error("Error openging outputstream",e);
		} finally {
			if (session != null) {
				session.close();
			}
	        log.info("Processing Get complete:" + request.getQueryString());
		}
    }

}
