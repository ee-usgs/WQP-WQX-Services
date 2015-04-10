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
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		resp.addHeader("Access-Control-Expose-Headers", "Total-Site-Count");
		resp.addHeader("Access-Control-Expose-Headers", "NWIS-Site-Count");
		resp.addHeader("Access-Control-Expose-Headers", "STEWARDS-Site-Count");
		resp.addHeader("Access-Control-Expose-Headers", "STORET-Site-Count");
		resp.addHeader("Access-Control-Expose-Headers", "Total-Result-Count");
		resp.addHeader("Access-Control-Expose-Headers", "NWIS-Result-Count");
		resp.addHeader("Access-Control-Expose-Headers", "STEWARDS-Result-Count");
		resp.addHeader("Access-Control-Expose-Headers", "STORET-Result-Count");
		resp.addHeader("Access-Control-Expose-Headers", "NWIS-Warning");
		resp.addHeader("Access-Control-Expose-Headers", "STEWARDS-Warning");
		resp.addHeader("Access-Control-Expose-Headers", "STORET-Warning");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// nothing needed here
	}

}
