package gov.usgs.cida.wqp.webservice;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

public class BaseController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	
//	@ExceptionHandler(IllegalStateException.class)
//    public void handleIllegalStateException(IllegalStateException ex, WebRequest request, HttpServletResponse response) throws Exception {
//    	if (ex.getMessage().equals("Cannot forward after response has been committed")) {
//    		LOG.warn("DefferedResult is the likely cause for this state when we independantely write to the the response.", ex.getMessage());
//    		return;
//    	}
//        throw new Exception(ex);
//    }
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
            LOG.error(msgText, ex);
            return msgText;
        }
    }
}
