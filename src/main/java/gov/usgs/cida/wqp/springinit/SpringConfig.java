package gov.usgs.cida.wqp.springinit;

import static gov.usgs.cida.wqp.util.MimeType.csv;
import static gov.usgs.cida.wqp.util.MimeType.geojson;
import static gov.usgs.cida.wqp.util.MimeType.json;
import static gov.usgs.cida.wqp.util.MimeType.kml;
import static gov.usgs.cida.wqp.util.MimeType.kmz;
import static gov.usgs.cida.wqp.util.MimeType.text;
import static gov.usgs.cida.wqp.util.MimeType.tsv;
import static gov.usgs.cida.wqp.util.MimeType.xlsx;
import static gov.usgs.cida.wqp.util.MimeType.xml;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import gov.usgs.cida.wqp.parameter.CustomBoundingBoxConverter;
import gov.usgs.cida.wqp.parameter.CustomStringArrayToListConverter;
import gov.usgs.cida.wqp.parameter.CustomStringConverter;
import gov.usgs.cida.wqp.parameter.CustomStringToArrayConverter;
import gov.usgs.cida.wqp.parameter.CustomStringToListConverter;

@Configuration
public class SpringConfig implements WebMvcConfigurer {
	private static final Logger LOG = LoggerFactory.getLogger(SpringConfig.class);

	public SpringConfig() {
		LOG.trace(getClass().getName());
	}

	@Autowired
	CustomStringToArrayConverter customStringToArrayConverter;

	@Autowired
	CustomStringToListConverter customStringToListConverter;

	@Autowired
	CustomStringArrayToListConverter customStringArrayToListConverter;

	@Autowired
	CustomStringConverter customStringConverter;

	@Autowired
	CustomBoundingBoxConverter customBoundingBoxConverter;

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
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST"));
		// setAllowCredentials(true) is important, otherwise:
		// The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
		configuration.setAllowCredentials(true);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
