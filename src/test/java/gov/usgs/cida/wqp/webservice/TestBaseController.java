package gov.usgs.cida.wqp.webservice;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.IXmlMapping;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.mapping.StationKml;
import gov.usgs.cida.wqp.mapping.StationWqx;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.service.ILogService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class TestBaseController extends BaseController {

	public static final String TEST_COUNT = "TEST-COUNT";
	
	public TestBaseController(IStreamingDao inStreamingDao,
			ICountDao inCountDao, IParameterHandler inParameterHandler,
			ILogService inLogService, Integer inMaxRows) {
		super(inStreamingDao, inCountDao, inParameterHandler, inLogService, inMaxRows);
	}

	@Override
	protected String addCountHeaders(HttpServletResponse response,
			List<Map<String, Object>> counts) {
		response.setHeader(TEST_COUNT, "12");
		return TEST_COUNT;
	}

	@Override
	protected Map<String, String> getMapping() {
		return StationColumn.mappings;
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return new StationWqx();
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return new StationKml("");
	}

}
