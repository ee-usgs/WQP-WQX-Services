package gov.usgs.wma.wqp.webservice;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.wma.wqp.dao.intfc.ICountDao;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.xml.IXmlMapping;
import gov.usgs.wma.wqp.openapi.annotation.GetOperation;
import gov.usgs.wma.wqp.openapi.annotation.HeadOperation;
import gov.usgs.wma.wqp.openapi.annotation.path.Organization;
import gov.usgs.wma.wqp.openapi.annotation.path.ProjectIdentifier;
import gov.usgs.wma.wqp.openapi.annotation.path.Provider;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.service.ConfigurationService;
import gov.usgs.wma.wqp.service.ILogService;
import gov.usgs.wma.wqp.util.HttpConstants;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(value=HttpConstants.PROJECT_MONITORING_LOCATION_WEIGHTING_REST_ENDPOINT,
	produces={HttpConstants.MIME_TYPE_TSV,
			HttpConstants.MIME_TYPE_CSV,
			HttpConstants.MIME_TYPE_XLSX,
			HttpConstants.MIME_TYPE_XML})
public class ProjectMonitoringLocationWeightingRestController extends ProjectMonitoringLocationWeightingBaseController {

	@Autowired
	public ProjectMonitoringLocationWeightingRestController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService,
			@Qualifier("projectMonitoringLocationWeightingWqx") IXmlMapping inXmlMapping,
			ContentNegotiationStrategy inContentStrategy,
			Validator validator, ConfigurationService configurationService) {
		super(inStreamingDao, inCountDao, inLogService, inXmlMapping, inContentStrategy, validator, configurationService);
	}

	@HeadOperation
	@Provider
	@Organization
	@ProjectIdentifier
	public void projectMonitoringLocationWeightingRestHead(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @PathVariable("provider") String provider,
			@Parameter(hidden=true) @PathVariable("organization") String organization, 
			@Parameter(hidden=true) @PathVariable("projectIdentifier") String projectIdentifier, 
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip
			) throws IOException {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(Arrays.asList(provider));
		filter.setOrganization(Arrays.asList(organization));
		filter.setProject(Arrays.asList(projectIdentifier));
		doHeadRequest(request, response, filter, mimeType, zip);
	}

	@GetOperation
	@Provider
	@Organization
	@ProjectIdentifier
	public void projectMonitoringLocationWeightingRestGet(
			HttpServletRequest request,
			HttpServletResponse response,
			@Parameter(hidden=true) @PathVariable("provider") String provider,
			@Parameter(hidden=true) @PathVariable("organization") String organization,
			@Parameter(hidden=true) @PathVariable("projectIdentifier") String projectIdentifier,
			@Parameter(hidden=true) @RequestParam(value="mimeType", required=false) String mimeType,
			@Parameter(hidden=true) @RequestParam(value="zip", required=false) String zip
			) throws IOException {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(Arrays.asList(provider));
		filter.setOrganization(Arrays.asList(organization));
		filter.setProject(Arrays.asList(projectIdentifier));
		doDataRequest(request, response, filter, mimeType, zip);
	}
}
