package gov.usgs.cida.wqp.webservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.annotation.NoQueryParametersList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={SwaggerConfig.VERSION_TAG_NAME})
@Controller
public class VersionController {

	@ApiOperation(value="Return the web service version information.")
	@NoQueryParametersList
	@RequestMapping(value="version", produces=MediaType.TEXT_PLAIN_VALUE)
	public String getVersion() {
		return "redirect:/actuator/info";
	}

}