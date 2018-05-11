package gov.usgs.cida.wqp.swagger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.classmate.TypeResolver;

import gov.usgs.cida.wqp.service.ConfigurationService;
import gov.usgs.cida.wqp.swagger.model.ActivityCountJson;
import gov.usgs.cida.wqp.swagger.model.ActivityMetricCountJson;
import gov.usgs.cida.wqp.swagger.model.PostParms;
import gov.usgs.cida.wqp.swagger.model.ProjectCountJson;
import gov.usgs.cida.wqp.swagger.model.ResDetectQntLmtCountJson;
import gov.usgs.cida.wqp.swagger.model.ResultCountJson;
import gov.usgs.cida.wqp.swagger.model.StationCountJson;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("swagger")
public class SwaggerConfig {
	public static final String ACTIVITY_TAG_NAME = "Activity";
	public static final String ACTIVITY_METRIC_TAG_NAME = "Activity Metric";
	public static final String RES_DETECT_QNT_LMT_TAG_NAME = "Result Detection Quantitation Limit";
	public static final String RESULT_TAG_NAME = "Result";
	public static final String SIMPLE_STATION_TAG_NAME = "Simple Station (deprecated)";
	public static final String STATION_TAG_NAME = "Station";
	public static final String VERSION_TAG_NAME = "Application Version";
	public static final String VERSION_TAG_DESCRIPTION = "Display Application Version";
	public static final String PROJECT_TAG_NAME = "Project";
	public static final String PROJECT_MONITORING_LOCATION_WEIGHTING_TAG_NAME = "Project Monitoring Location Weighting";
	public static final String TAG_DESCRIPTION = "Data Download";
	public static final String FILE_DOWNLOAD_TAG_NAME = "File Download";

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private TypeResolver typeResolver;

	@Bean
	public SwaggerServices swaggerServices() {
		SwaggerServices props = new SwaggerServices();
		Yaml yaml = new Yaml();  
		try( InputStream in = configurationService.getSwaggerServicesConfigFile().getInputStream()) {
			props = yaml.loadAs(in, SwaggerServices.class );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	@Bean
	public Docket qwPortalServicesApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.protocols(new HashSet<>(Arrays.asList("https")))
				.host(configurationService.getSwaggerDisplayHost())
				.pathProvider(pathProvider())
				.useDefaultResponseMessages(false)
				.additionalModels(typeResolver.resolve(PostParms.class),
						typeResolver.resolve(StationCountJson.class),
						typeResolver.resolve(ActivityCountJson.class),
						typeResolver.resolve(ActivityMetricCountJson.class),
						typeResolver.resolve(ResultCountJson.class),
						typeResolver.resolve(ResDetectQntLmtCountJson.class),
						typeResolver.resolve(ProjectCountJson.class))
				.tags(new Tag(ACTIVITY_TAG_NAME, TAG_DESCRIPTION),
						new Tag(ACTIVITY_METRIC_TAG_NAME, TAG_DESCRIPTION),
						new Tag(RES_DETECT_QNT_LMT_TAG_NAME, TAG_DESCRIPTION),
						new Tag(RESULT_TAG_NAME, TAG_DESCRIPTION),
						new Tag(SIMPLE_STATION_TAG_NAME, TAG_DESCRIPTION),
						new Tag(STATION_TAG_NAME, TAG_DESCRIPTION),
						new Tag(VERSION_TAG_NAME, VERSION_TAG_DESCRIPTION),
						new Tag(FILE_DOWNLOAD_TAG_NAME, FILE_DOWNLOAD_TAG_NAME)
					)
				.select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("gov.usgs.cida.qw"))
				.build()
		;
	}

	@Bean
	public PathProvider pathProvider() {
		PathProvider rtn = new ProxyPathProvider();
		return rtn;
	}

	public class ProxyPathProvider extends AbstractPathProvider {
		@Override
		protected String applicationPath() {
			return configurationService.getSwaggerDisplayPath();
		}
	
		@Override
		protected String getDocumentationPath() {
			return configurationService.getSwaggerDisplayPath();
		}
	}

	@Bean
	public UiConfiguration uiConfig() {
		//This is needed in swagger 2 for the "try it" button on head requests - should not be needed with swagger 3
		//It is needed in all WQP projects!!!
		return new UiConfiguration(null, "none", "alpha", "schema", new String[] { "get", "post", "head" }, false, true, null);
	}

}
