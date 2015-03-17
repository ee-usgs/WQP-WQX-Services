package gov.usgs.cida.wqp.service;


import gov.usgs.cida.wqp.util.HttpConstants;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

public class WebServiceLogService implements IWebServiceLogService, HttpConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWebServiceLogDao dao;

	public WebServiceLogService() {
        log.trace(getClass().getName());
	}
    
    
    @Override
    public void logRequest(HttpServletRequest request, final HttpServletResponse response) {
        Map<String, Object> counts = getWebServiceLog(request, response);
        dao.add(counts);
    }

    protected Map<String, Object> getWebServiceLog(final HttpServletRequest request, final HttpServletResponse response) {
        Map<String, Object> webServiceLog = new HashMap<String, Object>();
        if (null != request && null != response) {
            String origin = null == request.getHeader("referer") ? "Direct Call" : "WQP Site";
            String callType = request.getMethod();
            EndPoint endpoint = EndPoint.getEnumByCode(request.getRequestURI());
            String queryString = request.getQueryString();

            webServiceLog.put(WebServiceLogDao.ORIGIN, origin);
            webServiceLog.put(WebServiceLogDao.CALL_TYPE, callType);
            webServiceLog.put(WebServiceLogDao.ENDPOINT, null != endpoint ? endpoint.NAME : request.getRequestURI());
            webServiceLog.put(WebServiceLogDao.QUERY_STRING, queryString);
            webServiceLog.putAll(getCounts(endpoint, response));
        }
        return webServiceLog;
    }

    protected Map<String, Object> getCounts(final EndPoint endpoint, final HttpServletResponse response) {
        Map<String, Object> counts = new HashMap<String, Object>();
        if (null != endpoint && null != response) {
            Integer totalRowsExpected = 0;
            StringBuilder endpointCounts = new StringBuilder("<counts>");

            for (String headerName : response.getHeaderNames()) {
                String[] bits = headerName.split(HEADER_DELIMITER);
                //Is this a count header?
                if (null != bits && 3 == bits.length && null != bits[2] && HEADER_COUNT.contentEquals(bits[2])) {
                    //Is this a total count header?
                    if (null != bits[0] && HEADER_TOTAL.contentEquals(bits[0])) {
                        //Is this the total count header for the endpoint we are accessing?
                        if (null != bits[1] && endpoint.COUNT_HEADER_NAME.contentEquals(bits[1])) {
                            totalRowsExpected = Integer.valueOf(response.getHeader(headerName));
                        }
                    //Or an endpoint count header? 
                    } else {
                        endpointCounts.append("<" + bits[0].toLowerCase() + ">")
                            .append("<" + getNodeName(bits[1]) + ">")
                            .append(response.getHeader(headerName))
                            .append("</" + getNodeName(bits[1]) + ">")
                            .append("</" + bits[0].toLowerCase() + ">");
                    }
                }
            }

            counts.put(WebServiceLogDao.TOTAL_ROWS_EXPECTED, totalRowsExpected);
            counts.put(WebServiceLogDao.DATA_STORE_COUNTS, endpointCounts.append("</counts>").toString());
        }
        return counts;
    }

    protected static String getNodeName(final String headerName) {
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