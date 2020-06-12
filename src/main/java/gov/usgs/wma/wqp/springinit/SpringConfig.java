package gov.usgs.wma.wqp.springinit;

import static gov.usgs.wma.wqp.util.MimeType.csv;
import static gov.usgs.wma.wqp.util.MimeType.geojson;
import static gov.usgs.wma.wqp.util.MimeType.json;
import static gov.usgs.wma.wqp.util.MimeType.kml;
import static gov.usgs.wma.wqp.util.MimeType.kmz;
import static gov.usgs.wma.wqp.util.MimeType.text;
import static gov.usgs.wma.wqp.util.MimeType.tsv;
import static gov.usgs.wma.wqp.util.MimeType.xlsx;
import static gov.usgs.wma.wqp.util.MimeType.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import gov.usgs.wma.wqp.parameter.CustomBoundingBoxConverter;
import gov.usgs.wma.wqp.parameter.CustomStringArrayToListConverter;
import gov.usgs.wma.wqp.parameter.CustomStringConverter;
import gov.usgs.wma.wqp.parameter.CustomStringToArrayConverter;
import gov.usgs.wma.wqp.parameter.CustomStringToListConverter;

@Configuration
public class SpringConfig implements WebMvcConfigurer {
	private static final Logger LOG = LoggerFactory.getLogger(SpringConfig.class);

	public SpringConfig() {
		LOG.trace(getClass().getName());
	}

	@Autowired
	private CustomStringToArrayConverter customStringToArrayConverter;

	@Autowired
	private CustomStringToListConverter customStringToListConverter;

	@Autowired
	private CustomStringArrayToListConverter customStringArrayToListConverter;

	@Autowired
	private CustomStringConverter customStringConverter;

	@Autowired
	private CustomBoundingBoxConverter customBoundingBoxConverter;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(customStringToArrayConverter);
		registry.addConverter(customStringToListConverter);
		registry.addConverter(customStringArrayToListConverter);
		registry.addConverter(customStringConverter);
		registry.addConverter(customBoundingBoxConverter);
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
			.mediaType(text.getExtension(), text.mediaType)
		;
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		//This should make the url case insensitive
		AntPathMatcher matcher = new AntPathMatcher();
		matcher.setCaseSensitive(false);
		configurer.setPathMatcher(matcher);
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		config.setAllowCredentials(false);
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		bean.setOrder(0);

		return bean;
	}

}
