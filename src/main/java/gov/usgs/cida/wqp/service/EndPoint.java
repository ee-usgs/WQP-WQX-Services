package gov.usgs.cida.wqp.service;

import static gov.usgs.cida.wqp.util.HttpConstants.ENDPOINT_RESULT;
import static gov.usgs.cida.wqp.util.HttpConstants.ENDPOINT_SIMPLE_STATION;
import static gov.usgs.cida.wqp.util.HttpConstants.ENDPOINT_STATION;
import static gov.usgs.cida.wqp.util.HttpConstants.HEADER_RESULT;
import static gov.usgs.cida.wqp.util.HttpConstants.HEADER_SITE;
import static gov.usgs.cida.wqp.util.HttpConstants.RESULT_SEARCH_ENPOINT;
import static gov.usgs.cida.wqp.util.HttpConstants.SIMPLE_STATION_ENDPOINT;
import static gov.usgs.cida.wqp.util.HttpConstants.STATION_SEARCH_ENPOINT;

import java.util.HashMap;
import java.util.Map;

public enum EndPoint {
	RESULT  (ENDPOINT_RESULT, RESULT_SEARCH_ENPOINT, HEADER_RESULT),
	STATION (ENDPOINT_STATION, STATION_SEARCH_ENPOINT,  HEADER_SITE),
	SIMPLESTATION    (ENDPOINT_SIMPLE_STATION, SIMPLE_STATION_ENDPOINT, HEADER_SITE);
	
	private static Map<String, EndPoint> codeMap = new HashMap<String, EndPoint>();
	
	static {
		for (EndPoint endpoint : EndPoint.values()) {
			codeMap.put("/" + endpoint.URI.toUpperCase(), endpoint);
		}
	}
	
	public final String COUNT_HEADER_NAME;
	public final String NAME;
	public final String URI;
	
	private EndPoint (String endpointName, String endpointUri, String headerName) {
		NAME = endpointName;
		URI  = endpointUri;
		COUNT_HEADER_NAME = headerName;
	}
	
	public static EndPoint getEnumByCode(String code) {
		if (code == null || code.isEmpty()) {
			return null;
		}
		try {
			String endpoint = code.substring(code.indexOf('/', 2), code.length()).toUpperCase();
			return codeMap.get(endpoint);
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}
}