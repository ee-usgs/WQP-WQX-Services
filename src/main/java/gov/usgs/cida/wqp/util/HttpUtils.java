package gov.usgs.cida.wqp.util;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
/**
 *
 */
public class HttpUtils implements HttpConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public String quote(String text) {
		return '"' + text + '"';
	}
	
	public String warningHeader(Integer code, String text, String date) {
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
		return code + " WQP " + quote(text) +" "+ date;
	}
	
	public void addSiteHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		log.trace("station counts : {}", counts);
		response.addHeader(HEADER_TOTAL_SITE_COUNT, "0");
		
		for (Map<String, Object> count : counts) {
			log.trace("result count : {}", count);
			response.setHeader(determineHeaderName(count, HEADER_SITE_COUNT), determineHeaderValue(count, MybatisConstants.STATION_COUNT));
		}
	}

	public void addPcResultHeaders(HttpServletResponse response, List<Map<String, Object>> counts) {
		log.trace("result counts : {}", counts);
		response.addHeader(HEADER_TOTAL_SITE_COUNT, "0");
		response.addHeader(HEADER_TOTAL_RESULT_COUNT, "0");
		
		for (Map<String, Object> count : counts) {
			log.trace("result count : {}", count);
			response.setHeader(determineHeaderName(count, HEADER_SITE_COUNT), determineHeaderValue(count, MybatisConstants.STATION_COUNT));
			response.setHeader(determineHeaderName(count, HEADER_RESULT_COUNT), determineHeaderValue(count, MybatisConstants.RESULT_COUNT));
		}
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
			log.trace("determine count header : {}", provider.toString());
	
			return provider.toString();
		}
	}
	
	protected String determineHeaderValue(Map<String, Object> count, String key) {
		if (null == count || count.isEmpty() || null == key || !count.containsKey(key)) {
			return "0";
		} else {
			Object results = count.get(key);
			results = results==null ?"0" :results;
			log.trace("determine count value : {}", results.toString());
			return results.toString();
		}
	}
	
	public void handleException(HttpServletResponse response, String document, Exception e) {
		int refNbr = e.hashCode();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		writeDocument(response, document);
		response.addHeader(HEADER_WARNING, warningHeader(null, "Unexpected Error:" + refNbr, null));
		log.error("{} {}", refNbr, e.getMessage());
	}
	
	public void writeWarningHeaders(HttpServletResponse response, Map<String, List<String>> validationMessages) {
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		for (List<String> msgs : validationMessages.values()) {
			for (String msg : msgs) {
				response.addHeader(HEADER_WARNING, warningHeader(null, msg, null));
			}
		}
	}
	
	public void writeDocument(HttpServletResponse response, String document) {
		try {
			response.getWriter().write(document);
		} catch (Exception e) {
			log.error("Tried to write empty document but couldn't", e);
		}
	}
}