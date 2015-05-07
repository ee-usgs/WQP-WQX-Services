package gov.usgs.cida.wqp.webservice;

import gov.cida.cdat.control.Callback;
import gov.cida.cdat.control.Control;
import gov.cida.cdat.control.Message;
import gov.cida.cdat.control.SCManager;
import gov.cida.cdat.control.Time;
import gov.cida.cdat.io.Closer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;

public class AsyncUtils {
	private static final Logger log = LoggerFactory.getLogger(AsyncUtils.class);

	
	/**
	 * Asynchronous/Non-blocking implementation. This way many works can be issued simultaneously.
	 * @param session
	 * @param workerName
	 * @param deferral
	 */
	public static void listenForComplete(SCManager session, String workerName, final DeferredResult<String> deferral) {
		listenForComplete(session, workerName, deferral, false);
	}
	public static void listenForComplete(final SCManager session, String workerName, final DeferredResult<String> deferral, final boolean closeSession) {
		session.send(workerName, Control.onComplete, Time.HOUR, new Callback(){
			@Override
			public void onComplete(Throwable error, Message signal) {
				applyResult(deferral, error, signal);
				
				if (closeSession) {
					Closer.close(session);
				}
			}
		});
	}
	/**
	 * Synchronous/blocking implementation. This way dependent work can be chained to trigger when one completes.
	 * @param session
	 * @param workerName
	 * @param deferral
	 */
	public static void waitForComplete(SCManager session, String workerName, final DeferredResult<String> deferral) {
		waitForComplete(session, workerName, deferral, false);
	}
	public static void waitForComplete(SCManager session, String workerName, final DeferredResult<String> deferral, final boolean closeSession) {
		final Throwable[] problem = new Throwable[1];
		final Message[]  response = new Message[1];
		

		session.send(workerName, Control.onComplete, Time.HOUR, new Callback(){
			@Override
			public void onComplete(Throwable error, Message signal) {
				problem[0] = error;
				// need to set the message onComplete for the code to continue but need to mark it failed if signal is null
				response[0] = signal==null ?Message.create("onComplete", "failure") :signal;
			}
		});
		
		Time.waitForResponse(response, Time.SECOND, Time.HOUR);
		
		applyResult(deferral, problem[0], response[0]);
		
		if (closeSession) {
			Closer.close(session);
		}
	}

	
	static final String APPLY_DeferredResult_MSG = "Applying DeferredResult from worker run.";
	
	static void applyResult(DeferredResult<String> deferral, Throwable error, Message signal) {
		log.trace("{} signal:{}",APPLY_DeferredResult_MSG,signal);
		if (error != null) {
			log.warn("{} ERROR:{}",APPLY_DeferredResult_MSG,error.getMessage());
			log.error("{}",error);
			deferral.setErrorResult(error);
		} else if (signal != null  &&  "done".equalsIgnoreCase( signal.get(Control.onComplete) ) ) {
			deferral.setResult("success");
		} else {
			deferral.setResult("failure");
		}
	}
}
