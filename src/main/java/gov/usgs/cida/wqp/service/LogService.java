package gov.usgs.cida.wqp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.usgs.cida.wqp.dao.LogDao;
import gov.usgs.cida.wqp.dao.intfc.ILogDao;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Component
public class LogService implements ILogService {
	private static final Logger LOG = LoggerFactory.getLogger(LogService.class);

	public static final String COUNTS_START = "{\"counts\":[";

	private ILogDao logDao;

	@Autowired
	public LogService(ILogDao inLogDao) {
		logDao = inLogDao;
		LOG.trace(getClass().getName());
	}

	@Override
	public Integer logRequest(HttpServletRequest request, HttpServletResponse response) {
		return logRequest(request, response, new FilterParameters());
	}

	@Override
	public Integer logRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(LogDao.ID, null);
		if (null != request) {
			String origin = (null==request.getHeader("referer")) ? "Direct Call" : "WQP Site";
			parameterMap.put(LogDao.ORIGIN, origin);

			String callType = request.getMethod();
			parameterMap.put(LogDao.CALL_TYPE, callType);

			parameterMap.put(LogDao.ENDPOINT, request.getRequestURI());

			parameterMap.put(LogDao.POST_DATA, filter.toJson());

			parameterMap.put(LogDao.USER_AGENT, request.getHeader("user-agent"));
		}

		return logDao.addLog(parameterMap);
	}

	@Override
	public void logHeadComplete(List<Map<String, Object>> counts, String totalHeader, Integer logId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(LogDao.ID, logId);

		if (null != counts) {
			parameterMap.putAll(processCounts(counts, totalHeader));
		}

		logDao.setHeadComplete(parameterMap);
	}

	protected Map<String, Object> processCounts(List<Map<String, Object>> counts, String totalHeader) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Integer totalRowsExpected = 0;

		StringBuilder endpointCounts = new StringBuilder(COUNTS_START);

		for (Map<String, Object> countLine : counts) {
			if (countLine.get(BaseColumn.KEY_DATA_SOURCE) == null) {
				totalRowsExpected = getTotalRowsExpected(countLine, totalHeader);
			} else {
				endpointCounts.append("{\"").append(countLine.get(BaseColumn.KEY_DATA_SOURCE)).append("\":").append("{");
				for (Entry<String, Object> entry : countLine.entrySet()) {
					if (!entry.getKey().equalsIgnoreCase(BaseColumn.KEY_DATA_SOURCE)) {
						endpointCounts.append("\"").append(entry.getKey().replaceAll("_count", "")).append("\":")
							.append(entry.getValue()).append(",");
					}
				}
				endpointCounts.deleteCharAt(endpointCounts.length()-1).append("}},");
			}
		}

		if (endpointCounts.length() != COUNTS_START.length()) {
			endpointCounts.deleteCharAt(endpointCounts.length()-1);
		}
		endpointCounts.append("]}");

		parameterMap.put(LogDao.TOTAL_ROWS_EXPECTED, totalRowsExpected);
		parameterMap.put(LogDao.DATA_STORE_COUNTS, endpointCounts.toString());

		return parameterMap;
	}

	protected Integer getTotalRowsExpected(Map<String, Object> countLine, String totalHeader) {
		Integer totalRowsExpected = 0;
		if (totalHeader != null) {
			String key = totalHeader.substring(6).toLowerCase().replaceAll("-", "_");
			if (countLine.containsKey(key)
					&& countLine.get(key) != null) {
				totalRowsExpected = NumberUtils.toInt(countLine.get(key).toString(), 0);
			}
		}
		return totalRowsExpected;
	}

	@Override
	public void logFirstRowComplete(Integer logId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(LogDao.ID, logId);
		logDao.setFirstRow(parameterMap);
	}

	@Override
	public void logRequestComplete(Integer logId, String httpStatusCode, Map<String, Integer> downloadDetails) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = null == downloadDetails ? "{}" : mapper.writeValueAsString(downloadDetails);
		} catch (JsonProcessingException e) {
			//Not sure how this can happen, but is part of the API
			json = "{\"Error serializing downloadDetails\"}";
			LOG.info(json, e);
		}
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(LogDao.ID, logId);
		parameterMap.put(LogDao.HTTP_STATUS_CODE, httpStatusCode);
		parameterMap.put(LogDao.DOWNLOAD_DETAILS, json);
		logDao.setRequestComplete(parameterMap);
	}

}