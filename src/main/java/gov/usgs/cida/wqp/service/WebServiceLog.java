package gov.usgs.cida.wqp.service;

import gov.usgs.cida.wqp.util.HttpConstants;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WebServiceLog implements IWebServiceLogService, HttpConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IWebServiceLogDao dao;
	
	public WebServiceLog() {
		log.trace(getClass().getName());
	}
	
	@Override
	public void logRequest(HttpServletRequest request, final HttpServletResponse response) {
		if (dao == null) {
			throw new NullPointerException("No WebSeriveLogDao set");
		}
		Map<String, Object> counts = getLog(request);
		counts.putAll(getCounts(request, response));
		dao.add(counts);
	}
	
	protected Map<String, Object> getLog(HttpServletRequest request) {
		Map<String, Object> webServiceLog = new HashMap<String, Object>();
		
		if (null != request) {
			String origin = (null==request.getHeader("referer")) ?"Direct Call" :"WQP Site";
			webServiceLog.put(WebServiceLogDao.ORIGIN, origin);
			
			String callType = request.getMethod();
			webServiceLog.put(WebServiceLogDao.CALL_TYPE, callType);
			
			EndPoint endpoint = EndPoint.getEnumByCode(request.getRequestURI());
			String endpt = (null==endpoint) ?request.getRequestURI() :endpoint.NAME;
			webServiceLog.put(WebServiceLogDao.ENDPOINT, endpt);
			
			String queryString = request.getQueryString();
			webServiceLog.put(WebServiceLogDao.QUERY_STRING, queryString);
		}
		return webServiceLog;
	}
	
	protected Map<String, Object> getCounts(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> counts = new HashMap<String, Object>();
		if (request==null || response==null) {
			return counts;
		}
		
		EndPoint endpoint = EndPoint.getEnumByCode(request.getRequestURI());
		if (endpoint == null) {
			return counts;
		}
		
		Integer totalRowsExpected = 0;
		
		StringBuilder endpointCounts = new StringBuilder("<counts>");
		for (String headerName : response.getHeaderNames()) {
			if (headerName == null) {
				continue;
			}
			String[] parts = headerName.split(HEADER_DELIMITER);
			// boundary condition checks - the only test that matters is the length test 
			// - you cannot have the two nulls if the split was successful
			// null == parts  || null == parts[2] 
			if (3 != parts.length) { 
				continue; 
			}
			// we only care about Count headers
			if ( HEADER_COUNT.contentEquals(parts[2]) ) {
				// the total header count for the given endpoint
				if ( HEADER_TOTAL.contentEquals(parts[0]) ) {
					if ( endpoint.COUNT_HEADER_NAME.contentEquals(parts[1]) ) {
						totalRowsExpected = Integer.valueOf(response.getHeader(headerName));
					}
				} else { // all endpoints counts here
					endpointCounts
						.append("<"+ parts[0].toLowerCase() +">")
						.append("<"+ getNodeName(parts[1]) +">")
						.append(response.getHeader(headerName))
						.append("</"+ getNodeName(parts[1]) +">")
						.append("</"+ parts[0].toLowerCase() +">");
				}
			}
		}
		endpointCounts.append("</counts>");
		
		counts.put(WebServiceLogDao.TOTAL_ROWS_EXPECTED, totalRowsExpected);
		counts.put(WebServiceLogDao.DATA_STORE_COUNTS, endpointCounts.toString());
		
		return counts;
	}
	
	protected static String getNodeName(String headerName) {
		String rtn = ENDPOINT_RESULT.toLowerCase();
		if (headerName.equalsIgnoreCase(HEADER_SITE)) {
			rtn = ENDPOINT_STATION.toLowerCase();
		}
		return rtn;
	}
	
	public void setWebServiceLogDao(final IWebServiceLogDao inDao) {
		dao = inDao;
	}
}