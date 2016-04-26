package gov.usgs.cida.wqp.springinit;

import static gov.usgs.cida.wqp.util.MimeType.csv;
import static gov.usgs.cida.wqp.util.MimeType.geojson;
import static gov.usgs.cida.wqp.util.MimeType.json;
import static gov.usgs.cida.wqp.util.MimeType.kml;
import static gov.usgs.cida.wqp.util.MimeType.kmz;
import static gov.usgs.cida.wqp.util.MimeType.tsv;
import static gov.usgs.cida.wqp.util.MimeType.xlsx;
import static gov.usgs.cida.wqp.util.MimeType.xml;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.JndiUtils;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.util.WqpEnv;
import gov.usgs.cida.wqp.util.WqpEnvProperties;

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
@Import(ParameterValidationConfig.class)
public class SpringConfig extends WebMvcConfigurerAdapter implements EnvironmentAware,
		HttpConstants, MybatisConstants, WqpEnvProperties  {
	private static final Logger LOG = LoggerFactory.getLogger(SpringConfig.class);
	
	public SpringConfig() {
		LOG.trace(getClass().getName());
	}
	
	@Override
	public void setEnvironment(Environment env) {
		LOG.trace("setting evnironment");
		WqpEnv.setEnv(env);
	}
	
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
        	.favorPathExtension(false)
        	.favorParameter(true)
        	.parameterName("mimeType")
        	.mediaType(csv.getExtension(), csv.mediaType)
        	.mediaType(tsv.getExtension(), tsv.mediaType)
        	.mediaType(xml.getExtension(), xml.mediaType)
        	.mediaType(json.getExtension(), json.mediaType)
        	.mediaType(xlsx.getExtension(), xlsx.mediaType)
        	.mediaType(kml.getExtension(), kml.mediaType)
        	.mediaType(kmz.getExtension(), kmz.mediaType)
        	.mediaType(geojson.getExtension(), geojson.mediaType)
        	.ignoreAcceptHeader(true);
    }

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean mybatis = new SqlSessionFactoryBean();
		Resource mybatisConfig = new ClassPathResource(MYBATIS_CONF);
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

	@Bean
	public String kmlStyleUrl() {
		return WqpEnv.get(KML_STYLE_URL);
	}

	@Bean
	public Integer maxResultRows() {
		return Integer.valueOf(WqpEnv.get(MAX_RESULT_ROWS));
	}
	
	@Bean
	public String siteUrlBase() {
		return WqpEnv.get(SITE_URL_BASE);
	}
	
}
