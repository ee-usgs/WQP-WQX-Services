package gov.usgs.cida.wqp.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import gov.usgs.cida.wqp.Version;
import gov.usgs.cida.wqp.service.ConfigurationService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.annotation.NoQueryParametersList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=SwaggerConfig.VERSION_TAG_NAME, description=SwaggerConfig.VERSION_TAG_DESCRIPTION)
@RestController
public class VersionController {

	protected final ConfigurationService configurationService;

	@Autowired
	public VersionController(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@Operation(description="Return the web service version information.",
			responses= {
					@ApiResponse(content=@Content(schema=@Schema(implementation=Version.class)))
			})
	@NoQueryParametersList
	@GetMapping(value="version", produces=MediaType.APPLICATION_JSON_VALUE)
	public RedirectView getVersion() {
		return new RedirectView(configurationService.getMyUrlBase() + "/about/info", true, false);
	}

}