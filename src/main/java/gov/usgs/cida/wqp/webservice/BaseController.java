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
import gov.usgs.cida.wqp.util.MimeType;
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
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

public abstract class BaseController implements HttpConstants, ValidationConstants {

	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	protected final IStreamingDao streamingDao;
	protected final ICountDao countDao;
	protected final IParameterHandler parameterHandler;
	protected final ILogService logService;

	protected ParameterMap pm;
	protected MimeType mimeType;
	protected boolean zipped = false;
	
	public BaseController(IStreamingDao inStreamingDao, ICountDao inCountDao,
			IParameterHandler inParameterHandler, ILogService inLogService) {
		
		LOG.trace(getClass().getName());
		
		streamingDao     = inStreamingDao;
		parameterHandler = inParameterHandler;
		countDao         = inCountDao;
		logService       = inLogService;
	}
	
	protected boolean isZipped(String zipParm, String mimeType) {
		if ("yes".equalsIgnoreCase(zipParm) || MimeType.kmz.toString().equalsIgnoreCase(mimeType)) {
			return true;
		} else {
			return false;
		}
	}

	protected OutputStream getOutputStream(HttpServletResponse response, boolean zipped, String fileName) {
		try {
			OutputStream buffered = new BufferedOutputStream(response.getOutputStream());
			if (zipped) {
				ZipOutputStream zipOut = new ZipOutputStream(buffered);
				zipOut.putNextEntry(new ZipEntry(fileName));
				return (OutputStream) zipOut;
			} else {
				return buffered;
			}
		} catch (IOException e) {
			throw new RuntimeException("Error getting OutputStream", e);
		}
	}

	protected MimeType adjustMimeType(MimeType mimeType, boolean zipped) {
		if (zipped && MimeType.kml.equals(mimeType)) {
			return MimeType.kmz;
		} else {
			return mimeType;
		}
	}

	protected void closeZip(OutputStream out) {
		try {
			((ZipOutputStream) out).closeEntry();
			((ZipOutputStream) out).finish();
		} catch (IOException e) {
			throw new RuntimeException("Error closing ZipOutputStream", e);
		}
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
				break;
			}
		} else {
			ret.append(mimeType);
		}
		return ret.toString();
	}
	
	protected void doHeadRequest(HttpServletRequest request, HttpServletResponse response, String mybatisNamespace, String endpoint) {
		LOG.info("Processing Head: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		
		try {
			doHeader(request, response, logId, mybatisNamespace, endpoint);
		} finally {
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
			LOG.info("Processing Head complete: {}", request.getQueryString());
		}
	}

	protected boolean doHeader(HttpServletRequest request, HttpServletResponse response, BigDecimal logId,
			String mybatisNamespace, String endpoint) {
		response.setCharacterEncoding(DEFAULT_ENCODING);
		processParameters(request);
		if ( ! pm.isValid() ) {
			writeWarningHeaders(response, pm.getValidationMessages());
			LOG.info("Processing Head invalid params end:{}", request.getQueryString());
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
	
	protected void processParameters(HttpServletRequest request) {
		pm = new ParameterMap();
		if (request.getParameterMap().isEmpty()) {
			LOG.debug("No parameters");
			pm.setValid(false);
		} else {
			LOG.trace("got parameters");
			Map<String, String[]> requestParams = new HashMap<String, String[]>(request.getParameterMap());
			LOG.debug("requestParams: {}", requestParams);
			pm = parameterHandler.validateAndTransform(requestParams);
			LOG.debug("pm: {}", pm);
			LOG.debug("queryParms: {}", pm.getQueryParameters());
		}
	}
	
	protected abstract void addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts);
	
	protected void doGetRequest(HttpServletRequest request, HttpServletResponse response, String mybatisNamespace, String endpoint) {
		LOG.info("Processing Get: {}", request.getQueryString());
		BigDecimal logId = logService.logRequest(request, response);
		OutputStream responseStream = null;
		
		try {
			if (doHeader(request, response, logId, mybatisNamespace, endpoint)) {
				String namespace = getNamespace(mybatisNamespace, mimeType);
				responseStream = getOutputStream(response, zipped, getZipEntryName(endpoint, mimeType));
				Transformer transformer = getTransformer(mimeType, responseStream, logId);
					
				ResultHandler handler = new StreamingResultHandler(transformer);
				streamingDao.stream(namespace, pm.getQueryParameters(), handler);
		
				transformer.end();
					
			}		
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			int hashValue = response.hashCode();
			//Note: we are giving the user a generic message.  
			//Server logs can be used to troubleshoot problems.
			String msgText = "Something bad happened. Contact us with Reference Number: " + hashValue;
			LOG.error(msgText, e);
			response.addHeader(HEADER_FATAL_ERROR, msgText);
			if (null != responseStream) {
				try {
					responseStream.write(msgText.getBytes(DEFAULT_ENCODING));
				} catch (IOException e2) {
					//Just log, cause we obviously can't tell the client
					LOG.error("Error telling client about exception", e2);
				}
			}
		} finally {
			if (null != responseStream) {
				try {
					if (zipped) {
						closeZip(responseStream);
					}
				} catch (Exception e) {
					//Just log, cause we obviously can't tell the client
					LOG.error("Error closing zip", e);
				}
				try {
					responseStream.flush();
				} catch (IOException e) {
					//Just log, cause we obviously can't tell the client
					LOG.error("Error flushing response stream", e);
				}
				try {
					responseStream.close();
				} catch (IOException e) {
					//Just log, cause we obviously can't tell the client
					LOG.error("Error closing response stream", e);
				}
			}
			LOG.info("Processing Get complete: {}", request.getQueryString());
			logService.logRequestComplete(logId, String.valueOf(response.getStatus()));
		}
	}
	
	protected String getZipEntryName(String endpoint, MimeType mimeType) {
		if (MimeType.kml.equals(mimeType) || MimeType.kmz.equals(mimeType)) {
			return endpoint.toLowerCase() + "." + MimeType.kml;
		} else {
			return endpoint.toLowerCase() + "." + mimeType;
		}
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
			break;
		}
		return transformer;
	}

	protected abstract Map<String, String> getMapping();
	
	protected abstract IXmlMapping getXmlMapping();
	
	protected IXmlMapping getKmlMapping() {
		return null;
	}
	
	public void writeWarningHeaders(HttpServletResponse response, Map<String, List<String>> validationMessages) {
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		for (List<String> msgs : validationMessages.values()) {
			for (String msg : msgs) {
				response.addHeader(HEADER_WARNING, warningHeader(null, msg, null));
			}
		}
	}

	protected String warningHeader(Integer code, String text, String date) {
		 if (null == code) {
			 code = HEADER_WARNING_DEFAULT_CODE;
		 }
		 if (null == text || 0 == text.length()) {
			 text = "Unknown error";
		 }
		 if (null == date || 0 == date.length()) {
			 date = new Date().toString();
		 }
		//NOTE that the old swsf would add "detail" in [] ahead of the text. see TrafficController.toWarningHeaderMessage.
		return code + " WQP " + '"' + text + '"' +" "+ date;
	}

	protected String determineHeaderName(Map<String, Object> count, String suffix) {
		String mySuffix = suffix==null ?"" :suffix;
		if (null == count) {
			return HEADER_TOTAL + HEADER_DELIMITER + mySuffix;
		} else {
			Object provider = count.get(MybatisConstants.DATA_SOURCE);
			
			if (provider==null) {
				provider = HEADER_TOTAL + HEADER_DELIMITER + mySuffix;
			} else {
				provider = provider + HEADER_DELIMITER + mySuffix;
			}
			LOG.trace("determine count header : {}", provider.toString());
	
			return provider.toString();
		}
	}
	
	protected String determineHeaderValue(Map<String, Object> count, String key) {
		if (null == count || count.isEmpty() || null == key || !count.containsKey(key)) {
			return "0";
		} else {
			Object results = count.get(key);
			results = results==null ?"0" :results;
			LOG.trace("determine count value : {}", results.toString());
			return results.toString();
		}
	}
	
	public void addCountHeaders(HttpServletResponse response, List<Map<String, Object>> counts,
			String totalHeader, String valueHeader, String valueField) {
		LOG.trace("{} counts : {}", valueHeader, counts);
		response.addHeader(totalHeader, "0");
		
		for (Map<String, Object> count : counts) {
			LOG.trace("result count : {}", count);
			response.setHeader(determineHeaderName(count, valueHeader), determineHeaderValue(count, valueField));
		}
	}

	public void addSiteHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		addCountHeaders(response, counts, HEADER_TOTAL_SITE_COUNT, HEADER_SITE_COUNT, MybatisConstants.STATION_COUNT);
	}

}
