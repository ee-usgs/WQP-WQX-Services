package gov.usgs.wma.wqp.webservice;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;
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
public class GlobalDefaultExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public void handleUncaughtException(Exception ex, WebRequest request, HttpServletResponse response) throws IOException {
		if (ex instanceof HttpMediaTypeNotAcceptableException) {

			sendError(response, HttpStatus.NOT_ACCEPTABLE, null);

		} else if (ex instanceof HttpMessageNotReadableException
				|| ex instanceof MissingServletRequestParameterException
				|| ex instanceof HttpMediaTypeNotSupportedException) {

			int x = ex.getLocalizedMessage().indexOf("\n");
			sendError(response, HttpStatus.BAD_REQUEST, (x > 0 ? ex.getLocalizedMessage().substring(0, x) : ex.getLocalizedMessage()));

		} else if (ex instanceof ClientAbortException) {

			LOG.info("Client abort - This is normal and likely means the client dropped their connection", ex);

		} else {

			//Generic message + hash code allows us to look up the err in the logs
			int hashValue = response.hashCode();
			String msgText = "Something bad happened. Contact us with Reference Number: " + hashValue;

			LOG.error(msgText, ex);
			sendError(response, HttpStatus.INTERNAL_SERVER_ERROR, msgText);
		}
	}

	/**
	 * Send an error message to the user, but only if the response is not already committed.
	 * @param response
	 * @param httpStatus
	 * @param message
	 */
	private void sendError(HttpServletResponse response, HttpStatus httpStatus, String message) {
		if (! response.isCommitted()) {
			try {
				if (message != null) {
					response.sendError(httpStatus.value(), message);
				} else {
					response.sendError(httpStatus.value());
				}

			} catch (IOException e) {
				//Ignore - nothing we can do
			}
		}
	}

}
