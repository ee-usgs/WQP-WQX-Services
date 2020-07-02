package gov.usgs.wma.wqp.webservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.springframework.web.accept.ContentNegotiationStrategy;

import gov.usgs.wma.wqp.dao.intfc.ICountDao;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.ResultDelimited;
import gov.usgs.wma.wqp.mapping.xml.IXmlMapping;
import gov.usgs.wma.wqp.openapi.ConfigOpenApi;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.service.ConfigurationService;
import gov.usgs.wma.wqp.service.ILogService;
import gov.usgs.wma.wqp.util.HttpConstants;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=ConfigOpenApi.RES_DETECT_QNT_LMT_TAG_NAME, description=ConfigOpenApi.TAG_DESCRIPTION)
public abstract class ResDetectQntLmtBaseController extends BaseController {

	protected final IXmlMapping xmlMapping;

	public ResDetectQntLmtBaseController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			IXmlMapping inXmlMapping,
			ContentNegotiationStrategy contentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, contentStrategy, validator, configurationService);
		xmlMapping = inXmlMapping;
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addSiteHeaders(response, counts);
		addActivityHeaders(response, counts);
		addResultHeaders(response, counts);
		addResDetectQntLmtHeaders(response, counts);
		return HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT;
	}

	@Override
	protected Map<String, String> getMapping(Profile profile) {
		return ResultDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

	@Override
	protected Profile determineProfile(FilterParameters filter) {
		return determineProfile(Profile.RES_DETECT_QNT_LMT, filter);
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return null;
	}
}
