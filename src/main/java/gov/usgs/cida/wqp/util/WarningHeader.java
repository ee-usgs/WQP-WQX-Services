package gov.usgs.cida.wqp.util;

import org.apache.log4j.Logger;


/**
 * Warning header per http 1.1 www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.46
 * @author ilinkuo
 *
 */
public class WarningHeader implements HttpConstants {
	private final Logger log = Logger.getLogger(getClass());

    private final int code;
    private final String text;
    private final String date;

    
    
    public WarningHeader(final Integer inCode, final String inText, final String inDate) {
        log.trace(getClass());
        
        if (null == inCode) {
            code = HEADER_WARNING_DEFAULT_CODE;
        } else {
            code = inCode;
        }

        if (null == inText || 0 == inText.length()) {
            text = "Unknown error";
        } else {
            text = inText.replace('\n', ' ').replace('"', '\'');
        }

        if (null == inDate || 0 == inDate.length()) {
            date = null;
        } else {
            date = inDate;
        }
    }

    public String getQuotedText() {
        return '"' + this.text + '"';
    }

    @Override
    public String toString() {
        //NOTE that the old swsf would add "detail" in [] ahead of the text. see TrafficController.toWarningHeaderMessage. 
        return code + "WQP " + getQuotedText() + (date == null ? "" : " " + date);
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

}
