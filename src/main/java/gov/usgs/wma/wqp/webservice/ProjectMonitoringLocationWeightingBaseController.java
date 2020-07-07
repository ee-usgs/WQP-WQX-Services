package gov.usgs.wma.wqp.webservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.springframework.web.accept.ContentNegotiationStrategy;

import gov.usgs.wma.wqp.dao.intfc.ICountDao;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.ProjectMonitoringLocationWeightingDelimited;
import gov.usgs.wma.wqp.mapping.xml.IXmlMapping;
import gov.usgs.wma.wqp.openapi.ConfigOpenApi;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.service.ConfigurationService;
import gov.usgs.wma.wqp.service.ILogService;
import gov.usgs.wma.wqp.util.HttpConstants;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=ConfigOpenApi.PROJECT_MONITORING_LOCATION_WEIGHTING_TAG_NAME, description=ConfigOpenApi.TAG_DESCRIPTION)
public abstract class ProjectMonitoringLocationWeightingBaseController extends BaseController {

	protected final IXmlMapping xmlMapping;

	public ProjectMonitoringLocationWeightingBaseController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			IXmlMapping inXmlMapping,
			ContentNegotiationStrategy inContentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, inContentStrategy, validator, configurationService);
		xmlMapping = inXmlMapping;
	}

	protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addProjectMonitoringLocationWeightingHeaders(response, counts);
		return HttpConstants.HEADER_TOTAL_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT;
	}

	@Override
	protected Profile determineProfile(FilterParameters filter) {
		return determineProfile(Profile.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
	}

	@Override
	protected Map<String, String> getMapping(Profile profile) {
		return ProjectMonitoringLocationWeightingDelimited.getMapping(profile);
	}

	@Override
	protected IXmlMapping getXmlMapping() {
		return xmlMapping;
	}

	@Override
	protected IXmlMapping getKmlMapping() {
		return null;
	}

	@Override
	protected void addCustomRequestParams() {
		getFilter().setSiteUrlBase(configurationService.getMyUrlBase());
	}
}
