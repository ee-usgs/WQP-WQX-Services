package gov.usgs.cida.wqp.springinit;


import static gov.usgs.cida.wqp.util.MimeType.*;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.parameter.transform.ParameterTransformer;
import gov.usgs.cida.wqp.parameter.transform.SplitAndReplaceTransformer;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.JndiUtils;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.util.WqpEnv;
import gov.usgs.cida.wqp.util.WqpEnvProperties;
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
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
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
@ComponentScan(basePackages= {WqpEnv.BASE_PACKAGE})
@EnableWebMvc
@PropertySources({
	//This will get the defaults
	@PropertySource(value = "classpath:" + WqpEnv.PROPERTIES_FILE),
	//This will override with values from the containers file if the file can be found
	@PropertySource(value = WqpEnv.CONTAINER_PROPERTIES_FILE, ignoreResourceNotFound = true)
})
public class SpringConfig extends WebMvcConfigurerAdapter implements EnvironmentAware,
		HttpConstants, MybatisConstants, ValidationConstants, WqpEnvProperties  {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public SpringConfig() {
		log.trace(getClass().getName());
	}
	
	@Override
	public void setEnvironment(Environment env) {
		log.trace("setting evnironment");
		WqpEnv.setEnv(env);
	}
	
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
        	.favorPathExtension(false)
        	.favorParameter(true)
        	.parameterName("mimeType")
        	.mediaType(csv.getExtension(),  csv.mediaType)
        	.mediaType(tsv.getExtension(),  tsv.mediaType)
        	.mediaType(xml.getExtension(),  xml.mediaType)
        	.mediaType(json.getExtension(), json.mediaType)
        	.mediaType(xlsx.getExtension(), xlsx.mediaType)
        	.ignoreAcceptHeader(true);
    }
    
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean mybatis = new SqlSessionFactoryBean();
		Resource mybatisConfig = new ClassPathResource(WqpEnv.get(MYBATIS_CONF));
		mybatis.setConfigLocation(mybatisConfig);
		DataSource ds = JndiUtils.jndiDataSource(WqpEnv.get(JNDI_DATASOURCE));
		mybatis.setDataSource(ds);
		return mybatis;
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
		resolver.setPrefix(WqpEnv.get(JSP_VIEWS));
		resolver.setSuffix("");		// Making this empty so we can explicitly call each view we require (i.e. .jsp and .xml)
		return resolver;
	}
	
	@Bean
	public CodesService codesService() {
		return new CodesService();
	};

	@Bean
	public RegexValidator<String> analyticalMethodValidator() {
		return new RegexValidator<String>(Parameters.ANALYTICAL_METHOD, REGEX_ANALYTICAL_METHOD);
	}
	
	@Bean
	public RegexValidator<String[]> avoidValidator(){
		// semicolon list of databases to exclude as 'command.avoid'
		return new RegexValidator<String[]>(Parameters.AVOID,REGEX_AVOID);
	}
	
	@Bean
	public LatLonBoundingBoxValidator bboxValidator() {
		// comma list of four float values
		return new LatLonBoundingBoxValidator(Parameters.BBOX);
	}
	
	@Bean
	public LookupValidator characteristicNameValidator() {
		// semicolon list of string characteristic names
		return new LookupValidator(codesService(), Parameters.CHARACTERISTIC_NAME);
	}
	
	@Bean
	public LookupValidator characteristicTypeValidator() {
		// semicolon list of string characteristic types
		return new LookupValidator(codesService(), Parameters.CHARACTERISTIC_TYPE);
	}
	
	@Bean
	public RegexValidator<String[]> countryValidator() {
		// semicolon list of string country codes
		return new RegexValidator<String[]>(Parameters.COUNTRY, REGEX_FIPS_COUNTRY);
	}
	
	@Bean
	public RegexValidator<String[]> countyValidator() {
		// semicolon list of country:state:county code strings
		return new RegexValidator<String[]>(Parameters.COUNTY, REGEX_FIPS_COUNTY);
	}
	
	@Bean
	public AbstractValidator<String[]> hucValidator() {
		// semicolon list of HUC codes
		AbstractValidator<String[]> hucValidator = new RegexValidator<String[]>(Parameters.HUC, REGEX_HUC);
		ParameterTransformer<String[]> hucTransformer = new SplitAndReplaceTransformer(DEFAULT_DELIMITER, REGEX_HUC_WILDCARD_IN, REGEX_HUC_WILDCARD_OUT);
		hucValidator.setTransformer(hucTransformer);
		return hucValidator;
	}
	
	@Bean
	public LatitudeValidator latitudeValidator() {
		// one float value
		return new LatitudeValidator(Parameters.LATITUDE);
	}
	
	@Bean
	public LongitudeValidator longitudeValidator() {
		// one float value
		return new LongitudeValidator(Parameters.LONGITUDE);
	}
	
	@Bean
	public RegexValidator<String[]> mimeTypeValidator() {
		// one string mimetype
		return new RegexValidator<String[]>(Parameters.MIMETYPE, 1, 1, null, REGEX_MIMETYPES);
	}
	
	@Bean
	public LookupValidator organizationValidator() {
		// semicolon list of string ORG names
		return new LookupValidator(codesService(), Parameters.ORGANIZATION);
	}
	
	@Bean
	public RegexValidator<String[]> parameterCodeValidator() {
		// semicolon list of 5digit pCodes
		return new RegexValidator<String[]>(Parameters.PCODE,REGEX_PCODE);
	}
	
	@Bean
	public LookupValidator projectValidator() {
		// semicolon list of string project ids
		return new LookupValidator(codesService(), Parameters.PROJECT);
	}
	
	@Bean
	public LookupValidator providerValidator() {
		// semicolon list of databases to include
		return new LookupValidator(codesService(), Parameters.PROVIDERS);
	}
	
	@Bean
	public LookupValidator sampleMediaValidator() {
		// semicolon list of string media type names
		return new LookupValidator(codesService(), Parameters.SAMPLE_MEDIA);
	}
	
	@Bean
	public LookupValidator siteTypeValidator() {
		// semicolon list of string site type names
		return new LookupValidator(codesService(), Parameters.SITE_TYPE);
	}
	
	@Bean
	public RegexValidator<String[]> siteIdValidator() {
		// agency-site string
		return new RegexValidator<String[]>(Parameters.SITEID, REGEX_SITEID);
	}
	
	@Bean
	public DateFormatValidator startDateHiValidator() {
		// one string date MM-DD-YYYY
		return new DateFormatValidator(Parameters.START_DATE_HI, FORMAT_DATE);
	}
	
	@Bean
	public DateFormatValidator startDateLoValidator() {
		// one string date MM-DD-YYYY
		return new DateFormatValidator(Parameters.START_DATE_LO, FORMAT_DATE);
	}
	
	@Bean
	public RegexValidator<String[]> stateValidator() {
		// country:state code string
		return new RegexValidator<String[]>(Parameters.STATE, REGEX_FIPS_STATE);
	}
	
	@Bean
	public BoundedFloatingPointValidator withinValidator() {
		// one float value
		return new BoundedFloatingPointValidator(Parameters.WITHIN, 0, Double.MAX_VALUE);
	}

	@Bean
	public RegexValidator<String[]> zipValidator() {
		// one string 'yes' or omitted
		return new RegexValidator<String[]>(Parameters.ZIP, 0, 1, null, REGEX_ZIP);
	}
	
	@Bean
	public Map<Parameters, AbstractValidator<?>> validatorMap() {
		Map<Parameters, AbstractValidator<?>> validatorMap = new HashMap<Parameters, AbstractValidator<?>>();
		validatorMap.put(Parameters.ANALYTICAL_METHOD, analyticalMethodValidator());
		validatorMap.put(Parameters.AVOID, avoidValidator());
		validatorMap.put(Parameters.BBOX, bboxValidator());
		validatorMap.put(Parameters.CHARACTERISTIC_NAME, characteristicNameValidator());
		validatorMap.put(Parameters.CHARACTERISTIC_TYPE, characteristicTypeValidator());
		validatorMap.put(Parameters.COUNTRY, countryValidator());
		validatorMap.put(Parameters.COUNTY, countyValidator());
		validatorMap.put(Parameters.HUC, hucValidator());
		validatorMap.put(Parameters.LATITUDE, latitudeValidator());
		validatorMap.put(Parameters.LONGITUDE, longitudeValidator());
		validatorMap.put(Parameters.MIMETYPE, mimeTypeValidator());
		validatorMap.put(Parameters.ORGANIZATION, organizationValidator());
		validatorMap.put(Parameters.PCODE, parameterCodeValidator());
		validatorMap.put(Parameters.PROJECT, projectValidator());
		validatorMap.put(Parameters.PROVIDERS, providerValidator());
		validatorMap.put(Parameters.SAMPLE_MEDIA, sampleMediaValidator());
		validatorMap.put(Parameters.SITE_TYPE, siteTypeValidator());
		validatorMap.put(Parameters.SITEID, siteIdValidator());
		validatorMap.put(Parameters.START_DATE_HI, startDateHiValidator());
		validatorMap.put(Parameters.START_DATE_LO, startDateLoValidator());
		validatorMap.put(Parameters.STATE, stateValidator());
		validatorMap.put(Parameters.WITHIN, withinValidator());
		validatorMap.put(Parameters.ZIP, zipValidator());
        return validatorMap;
	}

	@Bean
	public IParameterHandler hashMapParameterHandler() throws Exception {
		return new HashMapParameterHandler(validatorMap());
	}
	
}
