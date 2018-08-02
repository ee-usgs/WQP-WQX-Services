package gov.usgs.cida.wqp.webservice.stationSummary;

import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={SwaggerConfig.SUMMARY_STATION_TAG_NAME})
@RestController
@RequestMapping(value=HttpConstants.SUMMARY_STATION_ENDPOINT,
	produces={HttpConstants.MIME_TYPE_GEOJSON})
public class SummaryStationController extends BaseController {
	
}
