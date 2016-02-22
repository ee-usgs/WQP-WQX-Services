package gov.usgs.cida.wqp.webservice;

import gov.usgs.cida.wqp.util.HttpConstants;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionController {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public void handleHttpMediaTypeNotAcceptableException(Exception ex, HttpServletResponse response) {
		response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		printException(response, ex.getLocalizedMessage());
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public void handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpServletResponse response) {
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		printException(response, ex.getLocalizedMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingServletRequestParameterException(Exception ex, HttpServletResponse response) {
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		printException(response, ex.getLocalizedMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	//This exception's message contains implementation details after the new line, so only take up to that.
    public void httpMessageNotReadableException(Exception ex, WebRequest request, HttpServletResponse response) {
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		printException(response, ex.getLocalizedMessage().substring(0, ex.getLocalizedMessage().indexOf("\n")));
	}

	@ExceptionHandler(Exception.class)
	public void handleUncaughtException(Exception ex, WebRequest request, HttpServletResponse response) {
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		int hashValue = response.hashCode();
		//Note: we are giving the user a generic message.  
		//Server logs can be used to troubleshoot problems.
		String msgText = "Something bad happened. Contact us with Reference Number: " + hashValue;
		LOG.error("logId: {}", BaseController.getLogId());
		LOG.error("status: {}", HttpStatus.INTERNAL_SERVER_ERROR.value());
		LOG.error(msgText, ex);
		response.addHeader(HttpConstants.HEADER_FATAL_ERROR, msgText);
		printException(response, msgText);
    }

	protected void printException(HttpServletResponse response, String msg) {
		try {
			response.getOutputStream().print(msg);
			response.getOutputStream().flush();
		} catch (IOException e) {
			LOG.error("Error in handleUncaughtException:", e);
		}
	}

}
