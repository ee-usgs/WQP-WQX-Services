package gov.usgs.cida.wqp.webservice.result;

import gov.usgs.cida.wqp.dao.StreamingResultHandler;
import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ResultColumn;
import gov.usgs.cida.wqp.mapping.ResultWqx;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.transform.MapToDelimitedTransformer;
import gov.usgs.cida.wqp.transform.MapToXlsxTransformer;
import gov.usgs.cida.wqp.transform.MapToXmlTransformer;
import gov.usgs.cida.wqp.transform.Transformer;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.util.MimeType;
import gov.usgs.cida.wqp.validation.ParameterValidation;
import gov.usgs.cida.wqp.webservice.BaseController;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value=HttpConstants.RESULT_SEARCH_ENPOINT,
	produces={HttpConstants.MIME_TYPE_TSV, HttpConstants.MIME_TYPE_CSV, HttpConstants.MIME_TYPE_XLSX, HttpConstants.MIME_TYPE_XML})
public class ResultController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	protected IStreamingDao streamingDao;
	protected ICountDao countDao;
	protected IParameterHandler parameterHandler;
	protected ILogService logService;
	protected ParameterMap pm;

	
	@Autowired
	public ResultController(IStreamingDao streamingDao, ICountDao countDao, 
			IParameterHandler parameterHandler, ILogService logService) {
		
		log.trace(getClass().getName());
		
		this.streamingDao     = streamingDao;
		this.parameterHandler = parameterHandler;
		this.countDao         = countDao;
		this.logService       = logService;
	}
	

	/**
	 * Result HEAD request
	 */
	@RequestMapping(method=RequestMethod.HEAD)
	public void resultHeadRequest(HttpServletRequest request, HttpServletResponse response) {
		log.info("Processing Head: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		
		try {
			doHeader(request, response, logId);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
			log.info("Processing Head complete: {}", request.getQueryString());
		}
	}
	
	
	/**
	 * Shared header helper method share for both the HEAD and GET requests
	 * @param request
	 * @param response
	 * @return boolean indication of valid parameters.
	 */
	private boolean doHeader(HttpServletRequest request, HttpServletResponse response, BigDecimal logId) {
		response.setCharacterEncoding(DEFAULT_ENCODING);
		pm = new ParameterValidation().preProcess(request, parameterHandler);
		if ( ! pm.isValid() ) {
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.writeWarningHeaders(response, pm.getValidationMessages());
			log.info("Processing Head invalid params end:{}", request.getQueryString());
			return false;
		}
		
		String mimeTypeParam = (String) pm.getQueryParameters().get(Parameters.MIMETYPE.toString());
		MimeType mimeType = MimeType.xml.fromString(mimeTypeParam);
		HttpUtils httpUtils = new HttpUtils();

		List<Map<String, Object>> counts = countDao.getCounts(IDao.RESULT_NAMESPACE, pm.getQueryParameters());

		response.setCharacterEncoding(DEFAULT_ENCODING);
		response.addHeader(HEADER_CONTENT_TYPE, mimeType.getMimeType());
		httpUtils.addPcResultHeaders(response, counts);
		response.setHeader("Content-Disposition","attachment; filename="+ENDPOINT_RESULT.toLowerCase()+"."+mimeType);

		logService.logHeadComplete(response, logId);
		return true;
	}
	
	/**
	 * result search request
	 */
	@RequestMapping(method=RequestMethod.GET)
	public void stationGetRequest(HttpServletRequest request, HttpServletResponse response) {
		log.trace(""); // blank line during trace
		log.info("Processing Get: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		
		if (doHeader(request, response, logId)) {
			try {
					
				Transformer transformer; 
				OutputStream responseStream = response.getOutputStream();
				String mimeTypeParam = pm.getParameter(Parameters.MIMETYPE);
				MimeType mimeType = MimeType.xml.fromString(mimeTypeParam);
				
				switch (mimeType) {
					case xlsx:
						transformer = new MapToXlsxTransformer(responseStream, ResultColumn.mappings, logService, logId);
						break;
					case tsv:
						transformer = new MapToDelimitedTransformer(responseStream, ResultColumn.mappings, logService, logId, MapToDelimitedTransformer.TAB);
						break;
					case xml:
						transformer = new MapToXmlTransformer(responseStream, new ResultWqx(), logService, logId);
						break;
					case csv:
					default:
						transformer = new MapToDelimitedTransformer(responseStream, ResultColumn.mappings, logService, logId, MapToDelimitedTransformer.COMMA);
				}
//				TransformOutputStream transformStream = new ObjectTransformStream(responseStream, logService, logId, transformer);
				
				ResultHandler handler = new StreamingResultHandler(transformer);
				streamingDao.stream(IDao.RESULT_NAMESPACE, pm.getQueryParameters(), handler);
	
				transformer.end();
			} catch (Exception e) {
				//TODO We can't just eat these.
				log.error("Error openging outputstream",e);
				throw new RuntimeException(e);
			} finally {
				logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
				log.info("Processing Get complete: {}", request.getQueryString());
			}
		} else {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
		}
	}

}
