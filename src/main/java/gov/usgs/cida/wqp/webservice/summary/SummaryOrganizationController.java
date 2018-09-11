package gov.usgs.cida.wqp.webservice.summary;

import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.xml.IXmlMapping;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.ConfigurationService;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.swagger.SwaggerConfig;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseController;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={SwaggerConfig.SUMMARY_ORGANIZATION_TAG_NAME})
@RestController
@RequestMapping(value=HttpConstants.SUMMARY_ORGANIZATION_ENDPOINT,
	produces={HttpConstants.MIME_TYPE_GEOJSON})
public class SummaryOrganizationController extends BaseController {

    public SummaryOrganizationController(IStreamingDao inStreamingDao, ICountDao inCountDao, ILogService inLogService, ContentNegotiationStrategy inContentStrategy, Validator inValidator, ConfigurationService configurationService) {
	super(inStreamingDao, inCountDao, inLogService, inContentStrategy, inValidator, configurationService);
    }

    @Override
    protected String addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Profile determineProfile(FilterParameters filter) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Map<String, String> getMapping(Profile profile) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected IXmlMapping getXmlMapping() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected IXmlMapping getKmlMapping() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
