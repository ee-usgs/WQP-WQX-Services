package gov.usgs.cida.wqp.springinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.usgs.cida.wqp.service.ConfigurationService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Autowired
	ConfigurationService configurationService;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.addServersItem(new Server().url(configurationService.getMyUrlBase()))
				.components(new Components())
				.info(new Info().title("Water Quality Portal Data API").description(
						"Documentation for the Water Quality Portal Data Download API"));

	}

}
