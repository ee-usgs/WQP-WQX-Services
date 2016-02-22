package gov.usgs.cida.wqp.service;

import gov.usgs.cida.wqp.util.HttpConstants;

import java.util.HashMap;
import java.util.Map;

public enum EndPoint {
	RESULT  (HttpConstants.ENDPOINT_RESULT, HttpConstants.RESULT_SEARCH_ENPOINT),
	STATION (HttpConstants.ENDPOINT_STATION, HttpConstants.STATION_SEARCH_ENPOINT),
	SIMPLESTATION    (HttpConstants.ENDPOINT_SIMPLE_STATION, HttpConstants.SIMPLE_STATION_ENDPOINT);
	
	private static Map<String, EndPoint> codeMap = new HashMap<String, EndPoint>();
	
	static {
		for (EndPoint endpoint : EndPoint.values()) {
			codeMap.put("/" + endpoint.uri.toUpperCase(), endpoint);
		}
	}
	
	private final String name;
	private final String uri;
	
	private EndPoint (String endpointName, String endpointUri) {
		name = endpointName;
		uri  = endpointUri;
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

	public String getName() {
		return name;
	}

}