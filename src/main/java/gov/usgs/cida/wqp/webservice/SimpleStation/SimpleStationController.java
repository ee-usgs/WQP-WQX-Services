package gov.usgs.cida.wqp.webservice.SimpleStation;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.swagger.annotation.FullParameterList;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Endpoints in this controller have been deprecated. Please use those in the StationController.
 *
 */
@Api(tags={SwaggerConfig.SIMPLE_STATION_TAG_NAME})
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
	public SimpleStationController(IStreamingDao inStreamingDao, ICountDao inCountDao, 
			IParameterHandler inParameterHandler, ILogService inLogService,
			@Qualifier("maxResultRows") Integer inMaxResultRows,
			@Qualifier("simpleStationWqxOutbound") IXmlMapping inXmlMapping,
			@Qualifier("siteUrlBase") String inSiteUrlBase,
			ContentNegotiationStrategy contentStrategy) {
		super(inStreamingDao, inCountDao, inParameterHandler, inLogService, inMaxResultRows, inSiteUrlBase, contentStrategy);
		xmlMapping = inXmlMapping;
		
		LOG.trace(getClass().getName());
	}

	@ApiOperation(value="Return appropriate request headers (including anticipated record counts).")
	@FullParameterList
	@RequestMapping(method=RequestMethod.HEAD)
	public void simpleStationHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		doHeadRequest(request, response);
	}

	@ApiOperation(value="Return requested data.")
	@FullParameterList
	@GetMapping()
	public void simpleStationGetRequest(HttpServletRequest request, HttpServletResponse response) {
		doGetRequest(request, response);
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
	protected Profile determineProfile(Map<String, Object> pm) {
		return determineProfile(Profile.SIMPLE_STATION, pm);
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return null;
	}
}
