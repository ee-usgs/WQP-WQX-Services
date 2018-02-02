package gov.usgs.cida.wqp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.usgs.cida.wqp.dao.LogDao;
import gov.usgs.cida.wqp.dao.intfc.ILogDao;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.util.HttpConstants;

@Component
public class LogService implements ILogService {
	private static final Logger LOG = LoggerFactory.getLogger(LogService.class);

	private ILogDao logDao;

	@Autowired
	public LogService(ILogDao inLogDao) {
		logDao = inLogDao;
		LOG.trace(getClass().getName());
	}

	@Override
	public BigDecimal logRequest(HttpServletRequest request, final HttpServletResponse response) {
		return logRequest(request, response, new FilterParameters());
	}

	@Override
	public BigDecimal logRequest(HttpServletRequest request, final HttpServletResponse response, FilterParameters filter) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(LogDao.ID, null);
		if (null != request) {
			String origin = (null==request.getHeader("referer")) ? "Direct Call" : "WQP Site";
			parameterMap.put(LogDao.ORIGIN, origin);

			String callType = request.getMethod();
			parameterMap.put(LogDao.CALL_TYPE, callType);

			parameterMap.put(LogDao.END_POINT, request.getRequestURI());

			String queryString = "All filter data is now in the POST_DATA";
			parameterMap.put(LogDao.QUERY_STRING, queryString);

			parameterMap.put(LogDao.POST_DATA, filter.toJson());
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
				String[] parts = headerName.split(HttpConstants.HEADER_DELIMITER);
				// boundary condition checks - the only test that matters is the length test 
				// - you cannot have the two nulls if the split was successful
				// null == parts  || null == parts[2] 
				if (3 != parts.length) {
					continue;
				}
				// we only care about Count headers
				if ( HttpConstants.HEADER_COUNT.contentEquals(parts[2]) ) {
					// the total header count for the given endpoint
					if ( HttpConstants.HEADER_TOTAL.contentEquals(parts[0]) ) {
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

			parameterMap.put(LogDao.ID, logId);
			parameterMap.put(LogDao.TOTAL_ROWS_EXPECTED, totalRowsExpected);
			parameterMap.put(LogDao.DATA_STORE_COUNTS, endpointCounts.toString());

			logDao.setHeadComplete(parameterMap);
		}
	}

	protected static String getNodeName(String headerName) {
		switch (headerName) {
		case HttpConstants.HEADER_SITE:
			return HttpConstants.ENDPOINT_STATION.toLowerCase();
		default:
			return headerName.toLowerCase();
		}
	}

	@Override
	public void logFirstRowComplete(BigDecimal logId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(LogDao.ID, logId);
		logDao.setFirstRow(parameterMap);
	}

	@Override
	public void logRequestComplete(BigDecimal logId, String httpStatusCode) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(LogDao.ID, logId);
		parameterMap.put(LogDao.HTTP_STATUS_CODE, httpStatusCode);
		logDao.setRequestComplete(parameterMap);
	}

}