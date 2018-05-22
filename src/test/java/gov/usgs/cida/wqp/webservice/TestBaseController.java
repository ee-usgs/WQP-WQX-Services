package gov.usgs.cida.wqp.webservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.springframework.web.accept.ContentNegotiationStrategy;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.StationDelimited;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.mapping.xml.StationKml;
import gov.usgs.cida.wqp.mapping.xml.StationWqx;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.ConfigurationService;
import gov.usgs.cida.wqp.service.ILogService;

public class TestBaseController extends BaseController {

	public static final String TEST_COUNT = "TEST-COUNT";

	public TestBaseController(IStreamingDao inStreamingDao,
			ICountDao inCountDao,
			ILogService inLogService,
			ContentNegotiationStrategy contentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, contentStrategy, validator, configurationService);
	}

	@Override
	protected String addCountHeaders(HttpServletResponse response,
			List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		response.setHeader(TEST_COUNT, "12");
		return TEST_COUNT;
	}

	@Override
	protected Map<String, String> getMapping(Profile profile) {
		return StationDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return new StationWqx();
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return new StationKml(configurationService);
	}

	@Override
	protected Profile determineProfile(FilterParameters filter) {
		return Profile.STATION;
	}

}
