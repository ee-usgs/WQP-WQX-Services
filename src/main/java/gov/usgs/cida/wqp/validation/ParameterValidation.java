package gov.usgs.cida.wqp.validation;

import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterValidation {
	private final Logger log = LoggerFactory.getLogger(getClass());

	public ParameterValidation() {
    	log.trace(getClass().getName());
	}
	
    public ParameterMap preProcess(HttpServletRequest request, IParameterHandler parameterHandler) {
    	Logger log = LoggerFactory.getLogger(getClass());
    	
        ParameterMap pm = new ParameterMap();
        if (request.getParameterMap().isEmpty()) {
            log.debug("No parameters");
            pm.setValid(false);
        } else {
            log.trace("got parameters");

            Map<String, String[]> requestParams = new HashMap<String, String[]>(request.getParameterMap());
            log.debug("requestParams: {}", requestParams);
            pm = parameterHandler.validateAndTransform(requestParams);
            log.debug("pm: {}", pm);
            log.debug("queryParms: {}", pm.getQueryParameters());
        }
        return pm;
    }

}
