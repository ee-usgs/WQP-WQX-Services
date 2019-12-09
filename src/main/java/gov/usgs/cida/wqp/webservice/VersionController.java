package gov.usgs.cida.wqp.webservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.annotation.NoQueryParametersList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={SwaggerConfig.VERSION_TAG_NAME})
@RestController
public class VersionController {

	@Value("${site.url.base}")
	private String serverUrl;

	@ApiOperation(value="Return the web service version information.")
	@NoQueryParametersList
	@GetMapping(value="version", produces=MediaType.APPLICATION_JSON_VALUE)
	public RedirectView getVersion() {
		return new RedirectView(serverUrl + "/about/info", true, false);
	}

}