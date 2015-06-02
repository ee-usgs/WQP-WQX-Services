package gov.usgs.cida.wqp.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {

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
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, HttpConstants.HEADER_TOTAL_SITE_COUNT);
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "NWIS-Site-Count");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "STEWARDS-Site-Count");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "STORET-Site-Count");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "Total-Result-Count");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "NWIS-Result-Count");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "STEWARDS-Result-Count");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "STORET-Result-Count");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "NWIS-Warning");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "STEWARDS-Warning");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, "STORET-Warning");
		resp.addHeader(HttpConstants.HEADER_CORS_EXPOSE_HEADERS, HttpConstants.HEADER_FATAL_ERROR);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// nothing needed here
	}

}
