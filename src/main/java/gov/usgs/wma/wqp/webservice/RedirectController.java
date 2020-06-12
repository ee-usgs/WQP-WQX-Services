package gov.usgs.wma.wqp.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import gov.usgs.wma.wqp.openapi.ConfigOpenApi;
import gov.usgs.wma.wqp.openapi.model.Version;
import gov.usgs.wma.wqp.service.ConfigurationService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=ConfigOpenApi.VERSION_TAG_NAME, description=ConfigOpenApi.VERSION_TAG_DESCRIPTION)
@RestController
public class RedirectController {

	protected final ConfigurationService configurationService;

	@Autowired
	public RedirectController(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@Operation(description="Return the web service version information.",
			responses= {
					@ApiResponse(content=@Content(schema=@Schema(implementation=Version.class)))
			})
	@GetMapping(value="/version")
	public RedirectView getVersion() {
		return new RedirectView(configurationService.getSiteUrlBase() + "about/info", true, false);
	}

	@GetMapping(value="/swagger")
	@Hidden
	public RedirectView getSwagger() {
		return new RedirectView(configurationService.getSiteUrlBase() + "swagger-ui/index.html?url="
									+ configurationService.getSwaggerApiDocsUrl(), true, true);
	}
}