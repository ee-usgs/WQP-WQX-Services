package gov.usgs.cida.wqp.springinit;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.parameter.transform.ParameterTransformer;
import gov.usgs.cida.wqp.parameter.transform.SplitAndRegexGroupTransformer;
import gov.usgs.cida.wqp.parameter.transform.SplitAndReplaceTransformer;
import gov.usgs.cida.wqp.station.dao.ICountDao;
import gov.usgs.cida.wqp.station.dao.IStationDao;
import gov.usgs.cida.wqp.station.dao.StationDao;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.JndiUtils;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.validation.AbstractValidator;
import gov.usgs.cida.wqp.validation.BoundedFloatingPointValidator;
import gov.usgs.cida.wqp.validation.DateFormatValidator;
import gov.usgs.cida.wqp.validation.LatLonBoundingBoxValidator;
import gov.usgs.cida.wqp.validation.LatitudeValidator;
import gov.usgs.cida.wqp.validation.LongitudeValidator;
import gov.usgs.cida.wqp.validation.LookupValidator;
import gov.usgs.cida.wqp.validation.RegexValidator;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@PropertySource(value = {"file:${catalina.base}/conf/wqpgateway.properties"})
public class SpringConfig extends WebMvcConfigurerAdapter implements HttpConstants, MybatisConstants, ValidationConstants  {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	static final Map<Parameters, AbstractValidator<?>> VALIDATOR_MAP = new HashMap<Parameters, AbstractValidator<?>>();

	static {
		// one float value
		VALIDATOR_MAP.put(Parameters.LATITUDE, new LatitudeValidator(Parameters.LATITUDE));
		// one float value
		VALIDATOR_MAP.put(Parameters.LONGITUDE, new LongitudeValidator(Parameters.LONGITUDE));
		// comma list of four float values
		VALIDATOR_MAP.put(Parameters.BBOX, new LatLonBoundingBoxValidator(Parameters.BBOX));
		// one float value
		 // TODO seems silly to require a string for numerical values
		AbstractValidator<double[]> floatValidator = new BoundedFloatingPointValidator(Parameters.WITHIN,""+DEFAULT_MIN_OCCURS,""+UNBOUNDED);
		VALIDATOR_MAP.put(Parameters.WITHIN, floatValidator);

		VALIDATOR_MAP.put(Parameters.ANALYTICAL_METHOD, new RegexValidator<String>(Parameters.ANALYTICAL_METHOD, REGEX_ANALYTICAL_METHOD));
		
		// one short country code string
		VALIDATOR_MAP.put(Parameters.COUNTRY, new RegexValidator<String[]>(Parameters.COUNTRY,REGEX_FIPS_COUNTRY));
		// country:state code string
		AbstractValidator<String[][]> stateValidator = new RegexValidator<String[][]>(Parameters.STATE,REGEX_FIPS_STATE);
		ParameterTransformer<String[][]> stateTransformer  = new SplitAndRegexGroupTransformer(DEFAULT_DELIMITER,REGEX_FIPS_STATE);
		stateValidator.setTransformer(stateTransformer);
		VALIDATOR_MAP.put(Parameters.STATE, stateValidator);
		// country:state:county code string
		AbstractValidator<String[][]> countyValidator = new RegexValidator<String[][]>(Parameters.COUNTY,REGEX_FIPS_COUNTY);
		ParameterTransformer<String[][]> countyTransformer = new SplitAndRegexGroupTransformer(DEFAULT_DELIMITER,REGEX_FIPS_COUNTY);
		countyValidator.setTransformer(countyTransformer);
		VALIDATOR_MAP.put(Parameters.COUNTY, countyValidator);
		// semicolon list of 8digit HUC codes
		AbstractValidator<String[]> hucValidator = new RegexValidator<String[]>(Parameters.HUC,REGEX_HUC);
		ParameterTransformer<String[]> hucTransformer = new SplitAndReplaceTransformer(DEFAULT_DELIMITER, REGEX_HUC_WILDCARD_IN, REGEX_HUC_WILDCARD_OUT);
		hucValidator.setTransformer(hucTransformer);
		VALIDATOR_MAP.put(Parameters.HUC, hucValidator);
		// semicolon list of 5digit pCodes
		VALIDATOR_MAP.put(Parameters.PCODE, new RegexValidator<String[]>(Parameters.PCODE,REGEX_PCODE));
		// agency-site string
		VALIDATOR_MAP.put(Parameters.SITEID,
				new RegexValidator<String[]>(Parameters.SITEID,DEFAULT_MIN_OCCURS, UNBOUNDED, DEFAULT_DELIMITER, REGEX_SITEID));
		// one string 'yes' or omitted
		VALIDATOR_MAP.put(Parameters.ZIP, new RegexValidator<String[]>(Parameters.ZIP,1, 1, null, "zip"));
		// one string 'yes' or omitted
		VALIDATOR_MAP.put(Parameters.MIMETYPE, new RegexValidator<String[]>(Parameters.MIMETYPE,1, 1, null,REGEX_MIMETYPES));
		// semicolon list of string activity IDs
		VALIDATOR_MAP.put(Parameters.ACTIVITY_ID, new RegexValidator<String[]>(Parameters.ACTIVITY_ID,REGEX_ACTIVITY_ID));
		// semicolon (or pipe) list of databases to include
		VALIDATOR_MAP.put(Parameters.PROVIDERS, new RegexValidator<String[]>(Parameters.PROVIDERS,REGEX_PROVIDERS));
		// semicolon (or pipe) list of databases to exclude as 'command.avoid'
		VALIDATOR_MAP.put(Parameters.AVOID, new RegexValidator<String[]>(Parameters.AVOID,REGEX_PROVIDERS));
		// semicolon list of string characteristic types
		VALIDATOR_MAP.put(Parameters.CHARACTERISTIC_TYPE, new LookupValidator(Parameters.CHARACTERISTIC_TYPE));
		// semicolon list of string characteristic name
		VALIDATOR_MAP.put(Parameters.CHARACTERISTIC_NAME, new LookupValidator(Parameters.CHARACTERISTIC_NAME));
		// one string ORG name
		VALIDATOR_MAP.put(Parameters.ORGANIZATION, new LookupValidator(Parameters.ORGANIZATION));
		// semicolon list of string media type names
		VALIDATOR_MAP.put(Parameters.SAMPLE_MEDIA, new LookupValidator(Parameters.SAMPLE_MEDIA));
		// one string site type name
		VALIDATOR_MAP.put(Parameters.SITE_TYPE, new LookupValidator(Parameters.SITE_TYPE));
		// one string date MM-DD-YYYY
		VALIDATOR_MAP.put(Parameters.START_DATE_LO, new DateFormatValidator(Parameters.START_DATE_LO, FORMAT_DATE));
		// one string date MM-DD-YYYY
		VALIDATOR_MAP.put(Parameters.START_DATE_HI, new DateFormatValidator(Parameters.START_DATE_HI, FORMAT_DATE));
		HashMapParameterHandler.setValidatorMap(VALIDATOR_MAP);
	}
	
	@Autowired
	private Environment environment;
	public SpringConfig() {
		log.trace(getClass().getName());
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
	
	@Bean
	public ICountDao countDao() throws Exception {
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