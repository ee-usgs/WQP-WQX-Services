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
 * @author duselmann
 */
public class SpringInitializer implements WebApplicationInitializer {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 *  gets invoked automatically when application context loads
	 */
	public void onStartup(ServletContext servletContext) throws ServletException {		
		log.error("Logging Enabled");
		log.warn("Logging Enabled");
		log.info("Logging Enabled");
		log.debug("Logging Enabled");
		log.trace("Logging Enabled");
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(SpringConfig.class);
//		ctx.register(MyBatisConfig.class) // TODO it would be nice to have individual configurations
		FilterRegistration corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
		corsFilter.addMappingForUrlPatterns(null, true, "/*");
		Dynamic servlet = servletContext.addServlet("springDispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setAsyncSupported(true);
		servlet.setLoadOnStartup(1);
	}
}