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
	public int addCountHeader(HttpServletResponse response, List<Map<String, Object>> counts) {
		if (counts == null || counts.isEmpty()) {
			response.addHeader(HEADER_TOTAL_SITE_COUNT, "0");
			return 0;
		}
		log.trace("station counts : {}", counts);
		int total = -1;
		for (Map<String, Object> count : counts) {
			log.trace("station count : {}", count);
			Object entries  = count.get(MybatisConstants.ENTRIES);
			entries = entries==null ?"0" :entries;
			Object site = count.get(MybatisConstants.DATA_SOURCE);
			if (site==null) {
				site = HEADER_TOTAL_SITE_COUNT;
				try {
					total = Integer.parseInt(entries.toString());
				} catch(Exception e) {
					total = 0;
				}
			} else {
				site = site+HEADER_DELIMITER+HEADER_SITE_COUNT;
			}
			log.trace("station count header : {},{}", site,entries);
			response.addHeader(site.toString(), entries.toString());
		}
		if (total == -1) {
			response.addHeader(HEADER_TOTAL_SITE_COUNT, "0");
			total = 0;
		}
		return total;
	}
	public void handleException(HttpServletResponse response, String document, Exception e) {
		int refNbr = e.hashCode();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		writeDocument(response, document);
		response.addHeader(HEADER_WARNING, warningHeader(null, "Unexpected Error:" + refNbr, null));
		log.error("{} {}", refNbr, e.getMessage());
	}
	public void writeWarningHeaders(HttpServletResponse response, Map<String, List<String>> validationMessages, String document) {
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		writeDocument(response, document);
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