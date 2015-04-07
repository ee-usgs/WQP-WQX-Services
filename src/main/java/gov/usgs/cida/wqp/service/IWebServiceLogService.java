package gov.usgs.cida.wqp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IWebServiceLogService {
	void logRequest(final HttpServletRequest request, final HttpServletResponse response);
}