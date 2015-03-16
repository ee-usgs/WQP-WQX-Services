package gov.usgs.cida.wqp.webservice;

import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.station.dao.IStationDao;
import gov.usgs.cida.wqp.station.dao.MapResultHandler;
import gov.usgs.cida.wqp.util.CharacterSeparatedValue;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.validation.ParameterValidation;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping(value="foo", method=RequestMethod.GET)
    public DeferredResult<ModelAndView> stationSearch() {
		log.info("stationSearch called");
		
		ModelAndView mv = new ModelAndView("index.jsp");
		mv.addObject("version", environment.getProperty("app.version"));

		DeferredResult<ModelAndView> finalResult = new DeferredResult<ModelAndView>();
		finalResult.setResult(mv);
		return finalResult;
    }
	
	/**
	 * Station HEAD request 
	 */
    @RequestMapping(value=STATION_SEARCH_ENPOINT, method=RequestMethod.HEAD)
    @ResponseBody
    public void stationHeadRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("Processing Head:" + request.getQueryString());
        
        response.setCharacterEncoding(DEFAULT_ENCODING);

        ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);

        HttpUtils httpUtils = new HttpUtils();
        
        if (pm.isValid()) {
        	httpUtils.addCountHeader(pm, response, stationDao);
        } else {
        	httpUtils.writeWarningHeaders(response, pm.getValidationMessages(), WQX_EMPTY_DOC);
        }
        log.info("Processing Head complete:" + request.getQueryString());
    }
	
    /**
     * station search request
     */
	@RequestMapping(value=STATION_SEARCH_ENPOINT, method=RequestMethod.GET, produces=MIME_TYPE_TEXT_CSV)
    @ResponseBody
    public void stationGetRequest(HttpServletRequest request, HttpServletResponse response) {
        log.trace(""); // blank line during trace       
        log.info("Processing Get:" + request.getQueryString()); // TODO use SLF4J to avoid string concatenation inline       

        response.setCharacterEncoding(DEFAULT_ENCODING);

        ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);
        log.debug("pm.isValid:" + pm.isValid());

        HttpUtils httpUtils = new HttpUtils();

        if (pm.isValid()) {
        	
        	// TODO pass the next two lines on to CDAT
            response.addHeader(HEADER_CONTENT_TYPE, MIME_TYPE_TEXT_CSV); // TODO from request (pm)
            int count = httpUtils.addCountHeader(pm, response, stationDao);
            response.setHeader("Content-Disposition","attachment; filename=stations.csv");
//            response.setContentLength(11*1024);

            if (count > 0 && count<1000) { // Only grab the data if there is some. TODO eventually parallel cDAT workers
                try {
                	log.trace("fetching station data with streaming handler - started");
                	// TODO pass the next two lines on to CDAT
                    ResultHandler handler = new MapResultHandler(response.getOutputStream(), CharacterSeparatedValue.CSV);
                    stationDao.stream(pm.getQueryParameters(), handler);
                    
                	log.trace("fetching station data with streaming handler - finished");
                    
                } catch (Exception e) {
                    log.warn(e.getMessage());
                    httpUtils.handleException(response, WQX_EMPTY_DOC, e);
                }
            } else {
            	httpUtils.writeDocument(response, WQX_EMPTY_DOC);
            }
        } else {
        	httpUtils.writeWarningHeaders(response, pm.getValidationMessages(), WQX_EMPTY_DOC);
        }
        log.info("Processing Get complete:" + request.getQueryString());
    }

}
