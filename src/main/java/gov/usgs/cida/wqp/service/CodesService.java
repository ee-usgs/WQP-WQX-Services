package gov.usgs.cida.wqp.service;

import static gov.usgs.cida.wqp.exception.WqpGatewayExceptionId.*;
import gov.cida.cdat.io.Closer;
import gov.cida.cdat.io.container.DataPipe;
import gov.cida.cdat.io.container.SimpleStreamContainer;
import gov.cida.cdat.io.container.UrlStreamContainer;
import gov.usgs.cida.wqp.exception.WqpGatewayException;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.WqpConfig;
import gov.usgs.cida.wqp.util.WqpConfigConstants;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CodesService implements WqpConfigConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public static final String DEFAULT_MIME_TYPE = "json";
	
	public String fetch(Parameters codeType, String code) throws WqpGatewayException {
		UrlStreamContainer producer = makeProvider(codeType,code);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		SimpleStreamContainer<OutputStream> consumer = new SimpleStreamContainer<OutputStream>(baos);

		DataPipe pipe = new DataPipe(producer, consumer);
		try {
			pipe.open();
			pipe.processAll();
		} catch (Exception e) {
			log.error("Cannot contact Codes Webservice at {}, exception to follow", WqpConfig.get(CODES_URL));
			log.error("Cannot contact Codes Webservice: exception", e);
			throw new WqpGatewayException(SERVER_REQUEST_IO_ERROR, getClass(), "fetch", "Cannot reach codes service");
		} finally {
			Closer.close(pipe);
		}
		String codes = new String( baos.toByteArray() );
		
		return codes;
	}
	
	/**
	 * Helper method so that we can test the fetch method by providing a mock implementation.
	 * @param codeType
	 * @param code
	 * @return
	 * @throws WqpGatewayException
	 */
	protected UrlStreamContainer makeProvider(Parameters codeType, String code) throws WqpGatewayException {
		URL url = makeCodesUrl(codeType, code);
		return  new UrlStreamContainer(url);
	}

	public boolean validate(Parameters codeType, String code) throws WqpGatewayException {
		String response = null;
		
		try {
			response = fetch(codeType, code);
		} catch (WqpGatewayException e) {
			// the empty sting will be a considered false validation
			if (e.getExceptionid() != METHOD_PARAM_EMPTY) {
				throw e;
			}
			
		}
		
		return ! StringUtils.isEmpty(response) && response.contains(code);
	}
	
	protected URL makeCodesUrl(Parameters codeType, String code) throws WqpGatewayException {
		if (codeType == null) {
			throw new WqpGatewayException(METHOD_PARAM_NULL, getClass(), "mokeCodesUrl", "codeType");
		}
		if (code == null) {
			throw new WqpGatewayException(METHOD_PARAM_NULL, getClass(), "mokeCodesUrl", "code");
		}
		if ( StringUtils.isEmpty(code) ) {
			throw new WqpGatewayException(METHOD_PARAM_EMPTY, getClass(), "mokeCodesUrl", "code");
		}
		
		URL url = null;
		try {
			
			String codesUrl = WqpConfig.get(CODES_URL);
			if ( StringUtils.isEmpty(codesUrl) ) {
				throw new WqpGatewayException(UNDEFINED_WQP_CONFIG_PARAM, getClass(), "mokeCodesUrl", CODES_URL);
			}
			String mimeType = WqpConfig.get(CODES_MIME_TYPE, DEFAULT_MIME_TYPE);
			String urlStr =  codesUrl +"/"+ codeType +"/"+ code + "?mimeType="+ mimeType;
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			throw new WqpGatewayException(URL_PARSING_EXCEPTION, getClass(), "mokeCodesUrl",
					"Invalid Code Lookup URL. Ensure that the wqpgateway.properties has a properly formated URL for " + CODES_URL);
		}
		return url;
	}
}
