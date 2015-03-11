package gov.usgs.cida.wqp.webservice;

import gov.usgs.cida.wqp.count.IRowCountDao;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.parameter.transform.ITransformer;
import gov.usgs.cida.wqp.parameter.transform.ParameterTransformer;
import gov.usgs.cida.wqp.parameter.transform.SplitAndRegexGroupTransformer;
import gov.usgs.cida.wqp.parameter.transform.SplitTransformer;
import gov.usgs.cida.wqp.station.dao.IStationDao;
import gov.usgs.cida.wqp.station.dao.MapResultHandler;
import gov.usgs.cida.wqp.util.CharacterSeparatedValue;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.util.SchemaRoot;
import gov.usgs.cida.wqp.util.WarningHeader;
import gov.usgs.cida.wqp.validation.AbstractValidator;
import gov.usgs.cida.wqp.validation.BoundedFloatingPointValidator;
import gov.usgs.cida.wqp.validation.DateFormatValidator;
import gov.usgs.cida.wqp.validation.LatLonBoundingBoxValidator;
import gov.usgs.cida.wqp.validation.LatitudeValidator;
import gov.usgs.cida.wqp.validation.LongitudeValidator;
import gov.usgs.cida.wqp.validation.LookupValidator;
import gov.usgs.cida.wqp.validation.ParameterValidation;
import gov.usgs.cida.wqp.validation.RegexValidator;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StationController implements HttpConstants, MybatisConstants, ValidationConstants {
	private final Logger log = Logger.getLogger(getClass());

	static final Map<Parameters, AbstractValidator> VALIDATOR_MAP = new HashMap<Parameters, AbstractValidator>();

	static {
		// one float value
		VALIDATOR_MAP.put(Parameters.LATITUDE, new LatitudeValidator(Parameters.LATITUDE));
		// one float value
		VALIDATOR_MAP.put(Parameters.LONGITUDE, new LongitudeValidator(Parameters.LONGITUDE));
		// comma list of four float values
		VALIDATOR_MAP.put(Parameters.BBOX, new LatLonBoundingBoxValidator(Parameters.BBOX));
		// one float value
		VALIDATOR_MAP.put(Parameters.WITHIN, // TODO seems silly to require a string
				new BoundedFloatingPointValidator(Parameters.WITHIN,""+DEFAULT_MIN_OCCURS,""+UNBOUNDED));

//		VALIDATOR_MAP.put(Parameters.ANALYTICAL_METHOD,
//				new RegexValidator(REGEX_ANALYTICAL_METHOD).maxOccurs(IN_CLAUSE_LIMIT));
		
		// one short country code string
		VALIDATOR_MAP.put(Parameters.COUNTRY, new RegexValidator(Parameters.COUNTRY,REGEX_FIPS_COUNTRY));
		// country:state code string
		VALIDATOR_MAP.put(Parameters.STATE, new RegexValidator(Parameters.STATE,REGEX_FIPS_STATE));
		// country:state:county code string
		VALIDATOR_MAP.put(Parameters.COUNTY, new RegexValidator(Parameters.COUNTY,REGEX_FIPS_COUNTY));
		// semicolon list of 8digit HUC codes
		VALIDATOR_MAP.put(Parameters.HUC, new RegexValidator(Parameters.HUC,REGEX_HUC));
		// semicolon list of 5digit pCodes
		VALIDATOR_MAP.put(Parameters.PCODE, new RegexValidator(Parameters.PCODE,REGEX_PCODE));
		// agency-site string
		VALIDATOR_MAP.put(Parameters.SITEID,
				new RegexValidator(Parameters.SITEID,DEFAULT_MIN_OCCURS, UNBOUNDED, DEFAULT_DELIMITER, REGEX_SITEID));
		// one string 'yes' or omitted
		VALIDATOR_MAP.put(Parameters.ZIP, new RegexValidator(Parameters.ZIP,1, 1, null, "zip"));
		// one string 'yes' or omitted
		VALIDATOR_MAP.put(Parameters.MIMETYPE, new RegexValidator(Parameters.MIMETYPE,1, 1, null,REGEX_MIMETYPES));
		// semicolon list of string activity IDs
		VALIDATOR_MAP.put(Parameters.ACTIVITY_ID, new RegexValidator(Parameters.ACTIVITY_ID,REGEX_ACTIVITY_ID));
		// semicolon (or pipe) list of databases to include
		VALIDATOR_MAP.put(Parameters.PROVIDERS, new RegexValidator(Parameters.PROVIDERS,REGEX_PROVIDERS));
		// semicolon (or pipe) list of databases to exclude as 'command.avoid'
		VALIDATOR_MAP.put(Parameters.AVOID, new RegexValidator(Parameters.AVOID,REGEX_PROVIDERS));
		

		// semicolon list of string characteristic types 
		VALIDATOR_MAP.put(Parameters.CHARACTERISTIC_TYPE, new LookupValidator(Parameters.CHARACTERISTIC_TYPE));
		// semicolon list of string characteristic name
		VALIDATOR_MAP.put(Parameters.CHARACTERISTIC_NAME, new LookupValidator(Parameters.CHARACTERISTIC_NAME));
		// one string ORG name
		VALIDATOR_MAP.put(Parameters.ORGANIZATION, new LookupValidator(Parameters.ORGANIZATION));
		// semicolon list of string media type names
		VALIDATOR_MAP.put(Parameters.SAMPLE_MEDIA, new LookupValidator(Parameters.SAMPLE_MEDIA));
		// one string site type name
		VALIDATOR_MAP.put(Parameters.SITE_TYPE, new LookupValidator(Parameters.SITE_TYPE));
		
		// one string date MM-DD-YYYY
		VALIDATOR_MAP.put(Parameters.START_DATE_LO, new DateFormatValidator(Parameters.START_DATE_LO, FORMAT_DATE));
		// one string date MM-DD-YYYY
		VALIDATOR_MAP.put(Parameters.START_DATE_HI, new DateFormatValidator(Parameters.START_DATE_HI, FORMAT_DATE));
		
    	HashMapParameterHandler.setValidatorMap(VALIDATOR_MAP);
    	
    	
    	// TODO this is redundant - above defines what should be used - this is not a comprehensive list
    	Map<Parameters, ITransformer> transMap = new HashMap<Parameters, ITransformer>();
    	ITransformer bboxTransformer   = new SplitTransformer(",");
    	transMap.put(Parameters.BBOX, bboxTransformer);
    	ITransformer stateTransformer  = new SplitAndRegexGroupTransformer(DEFAULT_DELIMITER,REGEX_FIPS_STATE);
    	transMap.put(Parameters.STATE, stateTransformer);
    	ITransformer countyTransformer = new SplitAndRegexGroupTransformer(DEFAULT_DELIMITER,REGEX_FIPS_COUNTY);
    	transMap.put(Parameters.COUNTY, countyTransformer);
    	ITransformer hucTransformer    = new SplitAndRegexGroupTransformer(DEFAULT_DELIMITER,"\\*|%");
    	transMap.put(Parameters.HUC, hucTransformer);
    	ParameterTransformer.setTransformerMap(transMap);
	}
	
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
    protected IRowCountDao rowCountDao;
    public void setRowCountDao(final IRowCountDao inRowCountDao) {
        rowCountDao = inRowCountDao;
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
    	log.trace(getClass());
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

        if (pm.isValid()) {
            addCountHeader(pm, response);
        } else {
            writeWarningHeaders(response, pm.getValidationMessages(), SchemaRoot.WQX.getEmptyDocument());
        }
        log.info("Processing Head complete:" + request.getQueryString());
    }
	
    /**
     * station search request
     */
	@RequestMapping(value=STATION_SEARCH_ENPOINT, method=RequestMethod.GET, produces=MIME_TYPE_TEXT_CSV)
    @ResponseBody
    public void stationGetRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("");        
        log.info("Processing Get:" + request.getQueryString()); // TODO use SLF4J to avoid string concatenation inline       

        response.setCharacterEncoding(DEFAULT_ENCODING);

        ParameterMap pm = new ParameterValidation().preProcess(request, parameterHandler);
        log.debug("pm.isValid:" + pm.isValid());

        if (pm.isValid()) {
        	// TODO pass the next two lines on to CDAT
            response.addHeader(HEADER_CONTENT_TYPE, MIME_TYPE_TEXT_CSV); // TODO from request (pm)
            int count = addCountHeader(pm, response);
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
                    handleException(response, e, SchemaRoot.WQX.getEmptyDocument());
                }
            } else {
                writeEmptyDocument(response, SchemaRoot.WQX.getEmptyDocument());
            }
        } else {
            writeWarningHeaders(response, pm.getValidationMessages(), SchemaRoot.WQX.getEmptyDocument());
        }
        log.info("Processing Get complete:" + request.getQueryString());
    }
	
	
	
	
	
	
	/*
	 * HELPER METHODS THAT COULD LIVE IN HELPER CLASSES
	 */
	
	
    // TODO this could be generalized as it was in SWSF
    protected int addCountHeader(ParameterMap pm, HttpServletResponse response) {
//        String count = rowCountDao.retrieveRowCount(sqlmapName, pm.getQueryParameters());
    	Integer count = stationDao.getCount(pm.getQueryParameters());
        log.trace("station count : " + count);
        response.addHeader(HEADER_SITE_COUNT, count.toString());
        return count;
    }
    
    protected void handleException(HttpServletResponse response, final Exception e, final String emptyXmlDoc) {
        int refNbr = e.hashCode();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        writeEmptyDocument(response, emptyXmlDoc);
        response.addHeader(HEADER_WARNING, new WarningHeader(null, "Unexpected Error:" + refNbr, null).toString());
        log.info(refNbr + e.getMessage());
    }

    protected void writeWarningHeaders(final HttpServletResponse response, final Map<String, List<String>> validationMessages,
            final String emptyXmlDoc) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
//        writeEmptyDocument(response, emptyXmlDoc);
        for (List<String> msgs : validationMessages.values()) {
            for (String msg : msgs) {
                response.addHeader(HEADER_WARNING, new WarningHeader(null, msg, null).toString());
            }
        }
    }
    
    protected void writeEmptyDocument(final HttpServletResponse response, final String emptyXmlDoc) {
        try {
            response.getWriter().write(emptyXmlDoc);
        } catch (Exception e) {
            log.info("Tried to write empty document but couldn't", e);
        }
    }
}
