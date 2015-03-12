package gov.usgs.cida.wqp.springinit;


import gov.usgs.cida.wqp.station.dao.IStationDao;
import gov.usgs.cida.wqp.station.dao.StationDao;
import gov.usgs.cida.wqp.util.JndiUtils;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * This class takes the place of the old Spring servlet.xml configuration that
 * used to reside in /WEB-INF. 
 */

@Configuration
@ComponentScan(basePackages= {"gov.usgs.cida.wqp"})
@EnableWebMvc
@PropertySource(value = {"file:${catalina.base}/conf/wqpgateway.properties"})		// Unfortunately this is Tomcat specific.  For us its ok
public class SpringConfig extends WebMvcConfigurerAdapter {
	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	private Environment environment;
	
	public SpringConfig() {
        log.trace(getClass());
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean mybatis = new SqlSessionFactoryBean();
		Resource mybatisConfig = new ClassPathResource("/mybatis/mybatisConfig.xml"); // TODO string
		mybatis.setConfigLocation(mybatisConfig);

		DataSource ds = JndiUtils.jndiDataSource("jdbc/WQPQW"); // TODO string
		mybatis.setDataSource(ds);
		
		return mybatis;
	}
   
	@Bean
	public IStationDao stationDao() throws Exception {
		return new StationDao(sqlSessionFactory().getObject());
	}
	
	/**
	 * Expose the resources (properties defined above) as an Environment to all
	 * classes.  Must declare a class variable with:
	 * 
	 * 		@Autowired
	 *		private Environment environment; 
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/**
	 * Our resources
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/schema/**").addResourceLocations("/WEB-INF/classes/schema/");
    }
	
	/**
	 * The caveat of mapping DispatcherServlet to "/" is that by default it breaks the ability to serve
	 * static resources like images and CSS files. To remedy this, I need to configure Spring MVC to
	 * enable defaultServletHandling.
	 * 
	 * 		equivalent for <mvc:default-servlet-handler/> tag
	 * 
	 * To do that, my WebappConfig needs to extend WebMvcConfigurerAdapter and override the following method:
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix("");		// Making this empty so we can explicitly call each view we require (i.e. .jsp and .xml)

		return resolver;
	}
}
