package gov.usgs.cida.wqp.service;

import gov.usgs.cida.wqp.dao.intfc.ILogDao;
import gov.usgs.cida.wqp.util.HttpConstants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogService implements ILogService, HttpConstants {
	private static final Logger LOG = LoggerFactory.getLogger(LogService.class);

	private ILogDao logDao;
	
	@Autowired
	public LogService(ILogDao inLogDao) {
		logDao = inLogDao;
		LOG.trace(getClass().getName());
	}
	
	@Override
	public BigDecimal logRequest(HttpServletRequest request, final HttpServletResponse response) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(ILogDao.ID, null);
		if (null != request) {
			String origin = (null==request.getHeader("referer")) ?"Direct Call" :"WQP Site";
			parameterMap.put(ILogDao.ORIGIN, origin);
			
			String callType = request.getMethod();
			parameterMap.put(ILogDao.CALL_TYPE, callType);
			
			EndPoint endpoint = EndPoint.getEnumByCode(request.getRequestURI());
			String endpt = (null == endpoint) ? request.getRequestURI() : endpoint.getName();
			parameterMap.put(ILogDao.END_POINT, endpt);
			
			String queryString = null==request.getQueryString() ? "No Query String Provided" : request.getQueryString();
			parameterMap.put(ILogDao.QUERY_STRING, queryString);
		}

		return logDao.addLog(parameterMap);
	}
	
	@Override
	public void logHeadComplete(HttpServletResponse response, BigDecimal logId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		if (null != response) {
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
						totalRowsExpected = Integer.valueOf(response.getHeader(headerName));
					} else {
						// all endpoints counts here
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
		
			parameterMap.put(ILogDao.ID, logId);
			parameterMap.put(ILogDao.TOTAL_ROWS_EXPECTED, totalRowsExpected);
			parameterMap.put(ILogDao.DATA_STORE_COUNTS, endpointCounts.toString());
		
			logDao.setHeadComplete(parameterMap);
		}
	}
	
	protected static String getNodeName(String headerName) {
		String rtn = ENDPOINT_RESULT.toLowerCase();
		if (headerName.equalsIgnoreCase(HEADER_SITE)) {
			rtn = ENDPOINT_STATION.toLowerCase();
		}
		return rtn;
	}
	
	@Override
	public void logFirstRowComplete(BigDecimal logId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(ILogDao.ID, logId);
		logDao.setFirstRow(parameterMap);
	}

	@Override
	public void logRequestComplete(BigDecimal logId, String httpStatusCode) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(ILogDao.ID, logId);
		parameterMap.put(ILogDao.HTTP_STATUS_CODE, httpStatusCode);
		logDao.setRequestComplete(parameterMap);
	}

}