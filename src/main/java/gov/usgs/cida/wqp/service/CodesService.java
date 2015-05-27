package gov.usgs.cida.wqp.service;

import static gov.usgs.cida.wqp.exception.WqpExceptionId.METHOD_PARAM_EMPTY;
import static gov.usgs.cida.wqp.exception.WqpExceptionId.METHOD_PARAM_NULL;
import static gov.usgs.cida.wqp.exception.WqpExceptionId.URL_PARSING_EXCEPTION;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.WqpEnvProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CodesService implements WqpEnvProperties {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final String codesUrl;
	private final String mimeType;

	public CodesService(String codesUrl, String mimeType) {
		this.codesUrl = codesUrl;
		this.mimeType = mimeType;
	}
	
	public boolean validate(Parameters codeType, String code) throws WqpException {
		log.trace("validating {}={}",codeType, code);
		boolean response = false;
		
		try {
			fetch(codeType, code);
			response = true;
		} catch (FileNotFoundException e) {
			//invalid parameter value
		} catch (IOException e) {
			// TODO better error handling? might be some exceptions we want to bubble up to top?
			e.printStackTrace();
		} catch (WqpException e) {
			// the empty sting will be a considered false validation
			if (e.getExceptionid() != METHOD_PARAM_EMPTY) {
				throw e;
			}
			
		}
		
		return response;
	}

	protected void fetch(Parameters codeType, String code) throws IOException, WqpException {
		URL url = makeCodesUrl(codeType, code);
		url.getContent();
	}

	protected URL makeCodesUrl(Parameters codeType, String code) throws WqpException {
		log.trace("making codes url");
		
		if (codeType == null) {
			throw new WqpException(METHOD_PARAM_NULL, getClass(), "makeCodesUrl", "codeType");
		}
		if (code == null) {
			throw new WqpException(METHOD_PARAM_NULL, getClass(), "makeCodesUrl", "code");
		}
		if ( StringUtils.isEmpty(code) ) {
			throw new WqpException(METHOD_PARAM_EMPTY, getClass(), "makeCodesUrl", "code");
		}
		
		URL url = null;
		try {
			String urlStr =  codesUrl +"/"+ codeType +"?value="+ URLEncoder.encode(code, HttpConstants.DEFAULT_ENCODING) + "&mimeType="+ mimeType;
			log.trace("making codes url : {}", urlStr);
			url = new URL(urlStr);
		} catch (MalformedURLException | UnsupportedEncodingException e) {
			throw new WqpException(URL_PARSING_EXCEPTION, getClass(), "makeCodesUrl",
					"Invalid Code Lookup URL. Ensure that the wqpgateway.properties has a properly formated URL for " + CODES_URL);
		}
		return url;
	}
}
