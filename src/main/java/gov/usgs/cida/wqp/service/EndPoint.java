package gov.usgs.cida.wqp.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import static gov.usgs.cida.wqp.util.HttpConstants.*;

public enum EndPoint {

    RESULT  (ENDPOINT_RESULT, RESULT_SEARCH_ENPOINT, HEADER_RESULT),
    STATION (ENDPOINT_STATION, STATION_SEARCH_ENPOINT,  HEADER_SITE),
    SIMPLESTATION    (ENDPOINT_SIMPLE_STATION, SIMPLE_STATION_ENDPOINT, HEADER_SITE),
    BIOLOGICALRESULT (ENDPOINT_BIOLOGICAL_RESULT, BIOLOGICAL_RESULT_ENPOINT, HEADER_RESULT);

    private static Map<String, EndPoint> codeMap = new HashMap<String, EndPoint>();
    static {
        for (EndPoint endpoint : EndPoint.values()) {
            codeMap.put("/" + endpoint.URI.toUpperCase(), endpoint);
        }
    }
    
	private final Logger log = Logger.getLogger(getClass());
    
    public final String COUNT_HEADER_NAME;
    public final String NAME;
    public final String URI;
    
    
    private EndPoint (final String endpointName, final String endpointUri, final String headerName) {
        log.trace(getClass());
        
        NAME = endpointName;
        URI  = endpointUri;
        COUNT_HEADER_NAME = headerName;
    }
    

    public static EndPoint getEnumByCode(final String code) {
        return (code == null || code.isEmpty()) ?null :codeMap.get(code.substring(code.indexOf('/', 2), code.length()).toUpperCase());
    }

}
