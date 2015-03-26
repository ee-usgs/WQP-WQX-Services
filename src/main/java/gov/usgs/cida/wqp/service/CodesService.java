package gov.usgs.cida.wqp.service;

import static gov.usgs.cida.wqp.exception.WqpExceptionId.*;
import gov.cida.cdat.exception.producer.FileNotFoundException;
import gov.cida.cdat.io.Closer;
import gov.cida.cdat.io.container.DataPipe;
import gov.cida.cdat.io.container.SimpleStreamContainer;
import gov.cida.cdat.io.container.UrlStreamContainer;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.WqpEnv;
import gov.usgs.cida.wqp.util.WqpEnvProperties;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CodesService implements WqpEnvProperties {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public static final String DEFAULT_MIME_TYPE = "json";
	
	public String fetch(Parameters codeType, String code) throws WqpException {
		UrlStreamContainer producer = makeProvider(codeType,code);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		SimpleStreamContainer<OutputStream> consumer = new SimpleStreamContainer<OutputStream>(baos);

		DataPipe pipe = new DataPipe(producer, consumer);
		try {
			pipe.open();
			pipe.processAll();
		} catch (FileNotFoundException e) {
			// if the server responds with no file then the parameter returned no matches
			return "";
		} catch (Exception e) {
			log.error("Cannot contact Codes Webservice at {}, exception to follow", WqpEnv.get(CODES_URL));
			log.error("Cannot contact Codes Webservice: exception", e);
			throw new WqpException(SERVER_REQUEST_IO_ERROR, getClass(), "fetch", "Cannot reach codes service");
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
	 * @throws WqpException
	 */
	protected UrlStreamContainer makeProvider(Parameters codeType, String code) throws WqpException {
		URL url = makeCodesUrl(codeType, code);
		return  new UrlStreamContainer(url);
	}

	public boolean validate(Parameters codeType, String code) throws WqpException {
		log.trace("validating {}={}",codeType, code);
		String response = null;
		
		try {
			response = fetch(codeType, code);
		} catch (WqpException e) {
			// the empty sting will be a considered false validation
			if (e.getExceptionid() != METHOD_PARAM_EMPTY) {
				throw e;
			}
			
		}
		
		return ! StringUtils.isEmpty(response) && response.contains(code);
	}
	
	protected URL makeCodesUrl(Parameters codeType, String code) throws WqpException {
		log.trace("making codes url");
		
		if (codeType == null) {
			throw new WqpException(METHOD_PARAM_NULL, getClass(), "mokeCodesUrl", "codeType");
		}
		if (code == null) {
			throw new WqpException(METHOD_PARAM_NULL, getClass(), "mokeCodesUrl", "code");
		}
		if ( StringUtils.isEmpty(code) ) {
			throw new WqpException(METHOD_PARAM_EMPTY, getClass(), "mokeCodesUrl", "code");
		}
		
		URL url = null;
		try {
			
			String codesUrl = WqpEnv.get(CODES_URL);
			if ( StringUtils.isEmpty(codesUrl) ) {
				throw new WqpException(UNDEFINED_WQP_CONFIG_PARAM, getClass(), "mokeCodesUrl", CODES_URL);
			}
			String mimeType = WqpEnv.get(CODES_MIME_TYPE, DEFAULT_MIME_TYPE);
			String urlStr =  codesUrl +"/"+ codeType +"/"+ code + "?mimetype="+ mimeType;
			log.trace("making codes url : {}", urlStr);
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			throw new WqpException(URL_PARSING_EXCEPTION, getClass(), "mokeCodesUrl",
					"Invalid Code Lookup URL. Ensure that the wqpgateway.properties has a properly formated URL for " + CODES_URL);
		}
		return url;
	}
}
