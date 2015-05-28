package gov.usgs.cida.wqp.webservice;

import gov.usgs.cida.wqp.dao.StreamingResultHandler;
import gov.usgs.cida.wqp.dao.intfc.ICountDao;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.IXmlMapping;
import gov.usgs.cida.wqp.parameter.IParameterHandler;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.transform.MapToDelimitedTransformer;
import gov.usgs.cida.wqp.transform.MapToJsonTransformer;
import gov.usgs.cida.wqp.transform.MapToKmlTransformer;
import gov.usgs.cida.wqp.transform.MapToXlsxTransformer;
import gov.usgs.cida.wqp.transform.MapToXmlTransformer;
import gov.usgs.cida.wqp.transform.Transformer;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.util.MimeType;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

public abstract class BaseController implements HttpConstants, ValidationConstants {

	private final Logger log = LoggerFactory.getLogger(getClass());
	protected final IStreamingDao streamingDao;
	protected final ICountDao countDao;
	protected final IParameterHandler parameterHandler;
	protected final ILogService logService;

	protected ParameterMap pm;
	protected MimeType mimeType;
	protected boolean zipped = false;
	
	public BaseController(IStreamingDao inStreamingDao, ICountDao inCountDao,
			IParameterHandler inParameterHandler, ILogService inLogService) {
		
		log.trace(getClass().getName());
		
		streamingDao     = inStreamingDao;
		parameterHandler = inParameterHandler;
		countDao         = inCountDao;
		logService       = inLogService;
	}
	
	@ExceptionHandler(Exception.class)
    public @ResponseBody String handleUncaughtException(Exception ex, WebRequest request, HttpServletResponse response) throws IOException {
		if (ex instanceof AccessDeniedException) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return "You are not authorized to perform this action.";
        } else if (ex instanceof MissingServletRequestParameterException
        		|| ex instanceof HttpMediaTypeNotSupportedException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ex.getLocalizedMessage();
        } else if (ex instanceof HttpMessageNotReadableException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            //This exception's message contains implementation details after the new line, so only take up to that.
            return ex.getLocalizedMessage().substring(0, ex.getLocalizedMessage().indexOf("\n"));
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            int hashValue = response.hashCode();
            //Note: we are giving the user a generic message.  
            //Server logs can be used to troubleshoot problems.
            String msgText = "Something bad happened. Contact us with Reference Number: " + hashValue;
            log.error(msgText, ex);
            return msgText;
        }
    }

	protected boolean isZipped(String zipParm, String mimeType) {
		if ("yes".equalsIgnoreCase(zipParm) || MimeType.kmz.toString().equalsIgnoreCase(mimeType)) {
			return true;
		} else {
			return false;
		}
	}

	protected OutputStream getOutputStream(HttpServletResponse response, boolean zipped, String fileName) throws IOException {
		if (zipped) {
			ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
			zipOut.putNextEntry(new ZipEntry(fileName));
			return (OutputStream) zipOut;
		} else {
			return response.getOutputStream();
		}
	}

	protected MimeType adjustMimeType(MimeType mimeType, boolean zipped) {
		if (zipped && MimeType.kml.equals(mimeType)) {
			return MimeType.kmz;
		} else {
			return mimeType;
		}
	}

	protected void closeZip(OutputStream out) throws IOException {
		((ZipOutputStream) out).closeEntry();
		((ZipOutputStream) out).finish();
	}

	protected String getContentHeader(boolean zipped, MimeType mimeType) {
		if (zipped) {
			switch (mimeType) {
			case kml:
			case kmz:
				return MimeType.kmz.getMimeType();
			default:
				return MIME_TYPE_ZIP;
			}
		} else {
			return mimeType.getMimeType();
		}
	}
	
	protected String getAttachementFileName(boolean zipped, MimeType mimeType, String endpoint) {
		StringBuilder ret = new StringBuilder(endpoint.toLowerCase()).append(".");
		if (zipped) {
			switch (mimeType) {
			case kml:
			case kmz:
				ret.append(MimeType.kmz);
				break;
			default:
				ret.append("zip");
			}
		} else {
			ret.append(mimeType);
		}
		return ret.toString();
	}
	
	protected void doHeadRequest(HttpServletRequest request, HttpServletResponse response, String mybatisNamespace, String endpoint) {
		log.info("Processing Head: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		
		try {
			doHeader(request, response, logId, mybatisNamespace, endpoint);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
			log.info("Processing Head complete: {}", request.getQueryString());
		}
	}

	protected boolean doHeader(HttpServletRequest request, HttpServletResponse response, BigDecimal logId,
			String mybatisNamespace, String endpoint) {
		response.setCharacterEncoding(DEFAULT_ENCODING);
		pm = processParameters(request);
		if ( ! pm.isValid() ) {
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.writeWarningHeaders(response, pm.getValidationMessages());
			log.info("Processing Head invalid params end:{}", request.getQueryString());
			return false;
		}

		String mimeTypeParam = (String) pm.getQueryParameters().get(Parameters.MIMETYPE.toString());
		String zipParam = (String) pm.getQueryParameters().get(Parameters.ZIP.toString());
		zipped = isZipped(zipParam, mimeTypeParam);
		mimeType = adjustMimeType(MimeType.csv.fromString(mimeTypeParam), zipped);

		List<Map<String, Object>> counts = countDao.getCounts(mybatisNamespace, pm.getQueryParameters());

		response.setCharacterEncoding(DEFAULT_ENCODING);
		response.addHeader(HEADER_CONTENT_TYPE, getContentHeader(zipped, mimeType));
		addCountHeaders(response, counts);
		response.setHeader(HEADER_CONTENT_DISPOSITION,"attachment; filename=" + getAttachementFileName(zipped, mimeType, endpoint));
		
		logService.logHeadComplete(response, logId);
		return true;
	}
	
	protected ParameterMap processParameters(HttpServletRequest request) {
		ParameterMap pm = new ParameterMap();
		if (request.getParameterMap().isEmpty()) {
			log.debug("No parameters");
			pm.setValid(false);
		} else {
			log.trace("got parameters");
			Map<String, String[]> requestParams = new HashMap<String, String[]>(request.getParameterMap());
			log.debug("requestParams: {}", requestParams);
			pm = parameterHandler.validateAndTransform(requestParams);
			log.debug("pm: {}", pm);
			log.debug("queryParms: {}", pm.getQueryParameters());
		}
		return pm;
	}
	
	protected abstract void addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts);
	
	protected String doGetRequest(HttpServletRequest request, HttpServletResponse response, String mybatisNamespace, String endpoint) {
		log.info("Processing Get: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
	
		try {
			if (doHeader(request, response, logId, mybatisNamespace, endpoint)) {
				String namespace = getNamespace(mybatisNamespace, mimeType);
				OutputStream responseStream = getOutputStream(response, zipped, endpoint.toLowerCase()+"."+mimeType);
				Transformer transformer = getTransformer(mimeType, responseStream, logId);
					
				ResultHandler handler = new StreamingResultHandler(transformer);
				streamingDao.stream(namespace, pm.getQueryParameters(), handler);
		
				transformer.end();
					
				if (zipped) {
					closeZip(responseStream);
				}
			}		
		} catch (Exception e) {
			log.error("Error processing get " + e.getLocalizedMessage());
			throw new RuntimeException(e);
		} finally {
			log.info("Processing Get complete: {}", request.getQueryString());
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
		}
		return "";
	}

	protected String getNamespace(String mybatisNamespace, MimeType mimeType) {
		if (MimeType.kml.equals(mimeType) || MimeType.kmz.equals(mimeType)) {
			return IDao.STATION_KML_NAMESPACE;
		} else {
			return mybatisNamespace;
		}
	}
	
	protected Transformer getTransformer(MimeType mimeType, OutputStream responseStream, BigDecimal logId) {
		Transformer transformer;
		switch (mimeType) {
		case json:
			transformer = new MapToJsonTransformer(responseStream, null, logService, logId);
			break;
		case xlsx:
			transformer = new MapToXlsxTransformer(responseStream, getMapping(), logService, logId);
			break;
		case xml:
			transformer = new MapToXmlTransformer(responseStream, getXmlMapping(), logService, logId);
			break;
		case kml:
		case kmz:
			transformer = new MapToKmlTransformer(responseStream, getKmlMapping(), logService, logId);
			break;
		case tsv:
			transformer = new MapToDelimitedTransformer(responseStream, getMapping(), logService, logId, MapToDelimitedTransformer.TAB);
			break;
		case csv:
		default:
			transformer = new MapToDelimitedTransformer(responseStream, getMapping(), logService, logId, MapToDelimitedTransformer.COMMA);
		}
		return transformer;
	}

	protected abstract Map<String, String> getMapping();
	
	protected abstract IXmlMapping getXmlMapping();
	
	protected abstract IXmlMapping getKmlMapping();
	
}
