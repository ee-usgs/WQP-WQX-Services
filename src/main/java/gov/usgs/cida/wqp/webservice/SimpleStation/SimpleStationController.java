package gov.usgs.cida.wqp.webservice.SimpleStation;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.StationDelimited;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.ConfigurationService;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.annotation.FullParameterList;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Endpoints in this controller have been deprecated. Please use those in the StationController.
 *
 */
@Tag(name=SwaggerConfig.SIMPLE_STATION_TAG_NAME, description=SwaggerConfig.TAG_DESCRIPTION)
@RestController
@RequestMapping(value=HttpConstants.SIMPLE_STATION_ENDPOINT,
	produces={HttpConstants.MIME_TYPE_XML,
			HttpConstants.MIME_TYPE_JSON,
			HttpConstants.MIME_TYPE_GEOJSON})
@Deprecated
public class SimpleStationController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleStationController.class);

	protected final IXmlMapping xmlMapping;

	@Autowired
	public SimpleStationController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			@Qualifier("simpleStationWqxOutbound") IXmlMapping inXmlMapping,
			ContentNegotiationStrategy contentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, contentStrategy, validator, configurationService);
		xmlMapping = inXmlMapping;

		LOG.trace(getClass().getName());
	}

	@Operation(description="Return appropriate request headers (including anticipated record counts).")
	@FullParameterList
	@RequestMapping(method=RequestMethod.HEAD)
	public void simpleStationHeadRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doHeadRequest(request, response, filter);
	}

	@Operation(description="Return requested data.")
	@FullParameterList
	@GetMapping()
	public void simpleStationGetRequest(HttpServletRequest request, HttpServletResponse response, FilterParameters filter) {
		doDataRequest(request, response, filter);
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		return HttpConstants.HEADER_TOTAL_SITE_COUNT;
	}

	@Override
	protected Map<String, String> getMapping(Profile profile) {
		return StationDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

	@Override
	protected Profile determineProfile(FilterParameters filter) {
		return determineProfile(Profile.SIMPLE_STATION, filter);
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return null;
	}

}
