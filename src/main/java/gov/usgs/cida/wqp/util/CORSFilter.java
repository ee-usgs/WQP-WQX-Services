package gov.usgs.cida.wqp.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {
	public static final String BIODATA = "BIODATA";
	public static final String NWIS = "NWIS";
	public static final String STEWARDS = "STEWARDS";
	public static final String STORET = "STORET";
	
	public static final List<String> ACCESS_CONTROL_HEADERS = new ArrayList<>(
			Arrays.asList(HttpConstants.HEADER_TOTAL_SITE_COUNT,
					BIODATA + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_SITE_COUNT,
					NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_SITE_COUNT,
					STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_SITE_COUNT,
					STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_SITE_COUNT,
					HttpConstants.HEADER_TOTAL_RESULT_COUNT,
					BIODATA + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RESULT_COUNT,
					NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RESULT_COUNT,
					STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RESULT_COUNT,
					STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RESULT_COUNT,
					HttpConstants.HEADER_WARNING,
					HttpConstants.HEADER_FATAL_ERROR));

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// nothing needed here
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader(HttpConstants.HEADER_CORS, HttpConstants.HEADER_CORS_VALUE);
		resp.setHeader(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE);
		resp.setHeader(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE);
		resp.setHeader(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE);
//		for (String header : ACCESS_CONTROL_HEADERS) {
//			resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, header);
//		}
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, StringUtils.join(ACCESS_CONTROL_HEADERS, ", "));
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// nothing needed here
	}

}
