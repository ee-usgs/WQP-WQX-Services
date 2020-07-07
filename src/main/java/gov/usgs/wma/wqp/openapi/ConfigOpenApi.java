package gov.usgs.wma.wqp.openapi;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.usgs.wma.wqp.service.ConfigurationService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ConfigOpenApi {

	public static final String ORGANIZATION_TAG_NAME = "Organization";
	public static final String ACTIVITY_TAG_NAME = "Activity";
	public static final String ACTIVITY_METRIC_TAG_NAME = "Activity Metric";
	public static final String ACTIVITY_ALL_TAG_NAME = "Activity All";
	public static final String BIOLOGICAL_METRIC_TAG_NAME = "Biological Metric";	
	public static final String PERIOD_OF_RECORD_TAG_NAME = "Period of Record";
	public static final String RES_DETECT_QNT_LMT_TAG_NAME = "Result Detection Quantitation Limit";
	public static final String RESULT_TAG_NAME = "Result";
	public static final String SIMPLE_STATION_TAG_NAME = "Simple Station (deprecated)";
	public static final String STATION_TAG_NAME = "Station";
	public static final String SUMMARY_ORGANIZATION_TAG_NAME = "Summary Organizaton";
	public static final String SUMMARY_MONITORING_LOCATION_TAG_NAME = "Summary Monitoring Location";
	public static final String VERSION_TAG_NAME = "Application Version";
	public static final String VERSION_TAG_DESCRIPTION = "Display Application Version";
	public static final String PROJECT_TAG_NAME = "Project";
	public static final String PROJECT_MONITORING_LOCATION_WEIGHTING_TAG_NAME = "Project Monitoring Location Weighting";
	public static final String TAG_DESCRIPTION = "Data Download";
	public static final String FILE_DOWNLOAD_TAG_NAME = "File Download";

	@Autowired
	private ConfigurationService configurationService;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.addServersItem(new Server().url(configurationService.getMyUrlBase()))
				.components(new Components())
				.info(new Info()
						.title(configurationService.getDeployName() + " API")
						.description("Documentation for the " + configurationService.getDeployName() + " API")
						.version(configurationService.getAppVersion())
						);
	}

	@Bean
	public OpenApiCustomiser sortTagsAlphabetically() {
		return openApi -> openApi.setTags(openApi.getTags()
				.stream()
				.sorted(Comparator.comparing(tag -> StringUtils.stripAccents(tag.getName())))
				.collect(Collectors.toList())
				);
	}

}
