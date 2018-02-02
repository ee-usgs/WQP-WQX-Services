package gov.usgs.cida.wqp.service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.usgs.cida.wqp.parameter.FilterParameters;

public interface ILogService {
	BigDecimal logRequest(final HttpServletRequest request, final HttpServletResponse response);
	BigDecimal logRequest(final HttpServletRequest request, final HttpServletResponse response, FilterParameters filter);
	void logHeadComplete(HttpServletResponse response, BigDecimal logId);
	void logFirstRowComplete(BigDecimal logId);
	void logRequestComplete(BigDecimal logId, String httpStatusCode);
}