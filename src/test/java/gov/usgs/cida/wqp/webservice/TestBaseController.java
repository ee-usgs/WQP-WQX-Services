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

	public TestBaseController(IStreamingDao inStreamingDao,
			ICountDao inCountDao, IParameterHandler inParameterHandler,
			ILogService inLogService) {
		super(inStreamingDao, inCountDao, inParameterHandler, inLogService);
	}

	@Override
	protected void addCountHeaders(HttpServletResponse response,
			List<Map<String, Object>> counts) {
		response.setHeader("TEST-COUNT", "12");
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
