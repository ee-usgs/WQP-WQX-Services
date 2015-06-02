package gov.usgs.cida.wqp.springinit;

import gov.usgs.cida.wqp.util.CORSFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * Create ApplicationContext using the AnnotationConfigWebApplicationContext to avoid using beans XML files.
 *
 * @author drsteini
 */
public class SpringInitializer implements WebApplicationInitializer {
	private static final Logger LOG = LoggerFactory.getLogger(SpringInitializer.class);
	
	/**
	 *  gets invoked automatically when application context loads
	 */
	public void onStartup(ServletContext servletContext) throws ServletException {		
		LOG.error("Logging Enabled");
		LOG.warn("Logging Enabled");
		LOG.info("Logging Enabled");
		LOG.debug("Logging Enabled");
		LOG.trace("Logging Enabled");
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(SpringConfig.class);

		FilterRegistration corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
		corsFilter.addMappingForUrlPatterns(null, true, "/*");
		
		Dynamic servlet = servletContext.addServlet("springDispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setAsyncSupported(false);
		servlet.setLoadOnStartup(1);
	}
}