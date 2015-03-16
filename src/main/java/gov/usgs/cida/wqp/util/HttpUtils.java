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

    
    public int addCountHeader(HttpServletResponse response, Integer count) {
    	String countStr = count==null ?"none" :count.toString();
        log.trace("station count : {}", count);
        response.addHeader(HEADER_SITE_COUNT, countStr);
        return count;
    }
    
    public void handleException(HttpServletResponse response, String document, Exception e) {
        int refNbr = e.hashCode();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        writeDocument(response, document);
        response.addHeader(HEADER_WARNING, warningHeader(null, "Unexpected Error:" + refNbr, null));
        log.info("{} {}", refNbr, e.getMessage());
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
            log.info("Tried to write empty document but couldn't", e);
        }
    }
    
}
