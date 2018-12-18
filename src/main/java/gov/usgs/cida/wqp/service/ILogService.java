package gov.usgs.cida.wqp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.usgs.cida.wqp.parameter.FilterParameters;

public interface ILogService {
	Integer logRequest(HttpServletRequest request, HttpServletResponse response);
	Integer logRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter);
	void logHeadComplete(List<Map<String, Object>> counts, String totalHeader, Integer logId);
	void logFirstRowComplete(Integer logId);
	void logRequestComplete(Integer logId, String httpStatusCode, Map<String, Integer> downloadDetails);
}