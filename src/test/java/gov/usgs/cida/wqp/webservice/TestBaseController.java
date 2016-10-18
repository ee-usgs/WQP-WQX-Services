package gov.usgs.cida.wqp.webservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.StationDelimited;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.mapping.xml.StationKml;
import gov.usgs.cida.wqp.mapping.xml.StationWqx;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.service.ILogService;

public class TestBaseController extends BaseController {

	public static final String TEST_COUNT = "TEST-COUNT";
	
	public TestBaseController(IStreamingDao inStreamingDao,
			ICountDao inCountDao, IParameterHandler inParameterHandler,
			ILogService inLogService, Integer inMaxRows, String inSiteUrlBase) {
		super(inStreamingDao, inCountDao, inParameterHandler, inLogService, inMaxRows, inSiteUrlBase);
	}

	@Override
	protected String addCountHeaders(HttpServletResponse response,
			List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		response.setHeader(TEST_COUNT, "12");
		return TEST_COUNT;
	}

	@Override
	protected Map<String, String> getMapping(String profile) {
		return StationDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return new StationWqx();
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return new StationKml("");
	}

	@Override
	protected String determineProfile(Map<String, Object> pm) {
		return Profile.STATION;
	}

}
