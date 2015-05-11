package gov.usgs.cida.wqp.webservice;

import gov.cida.cdat.control.Callback;
import gov.cida.cdat.control.Control;
import gov.cida.cdat.control.Message;
import gov.cida.cdat.control.SCManager;
import gov.cida.cdat.control.Time;
import gov.cida.cdat.io.Closer;
import gov.usgs.cida.wqp.util.WqpEnv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncUtils {
	private static final Logger log = LoggerFactory.getLogger(AsyncUtils.class);
	private static final String QueryTimeMax = "query.time.max";
	
	
	static {
		try {
			String sec = WqpEnv.get(QueryTimeMax);
			long seconds = Long.parseLong(sec);
			setDefaultWaitTime(seconds);
		} catch (Exception e) {
			queryWaitTime = Time.HOUR;
		}
	}
	
	
	private static Time queryWaitTime = Time.HOUR;
	public static void setDefaultWaitTime(long sec) {
		Time.CUSTOM.setDuration(sec, "sec");
		queryWaitTime = Time.CUSTOM;
	}
	
	/**
	 * Asynchronous/Non-blocking implementation. This way many works can be issued simultaneously.
	 * @param session
	 * @param workerName
	 * @param deferral
	 */
	public static void listenForComplete(SCManager session, String workerName) {
		listenForComplete(session, workerName, false);
	}
	public static void listenForComplete(final SCManager session, String workerName, final boolean closeSession) {
		session.send(workerName, Control.onComplete, queryWaitTime, new Callback(){
			@Override
			public void onComplete(Throwable error, Message signal) {
				applyResult(error, signal);
				
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
	public static void waitForComplete(SCManager session, String workerName) {
		waitForComplete(session, workerName, false);
	}
	public static void waitForComplete(SCManager session, String workerName, final boolean closeSession) {
		final Throwable[] problem = new Throwable[1];
		final Message[]  response = new Message[1];
		

		session.send(workerName, Control.onComplete, queryWaitTime, new Callback(){
			@Override
			public void onComplete(Throwable error, Message signal) {
				problem[0] = error;
				// need to set the message onComplete for the code to continue but need to mark it failed if signal is null
				response[0] = signal==null ?Message.create("onComplete", "failure") :signal;
			}
		});
		
		Time.waitForResponse(response, Time.SECOND, queryWaitTime);
		
		applyResult(problem[0], response[0]);
		
		if (closeSession) {
			Closer.close(session);
		}
	}

	
	static final String APPLY_DeferredResult_MSG = "Applying DeferredResult from worker run.";
	
	static void applyResult(Throwable error, Message signal) {
		log.trace("{} signal:{}",APPLY_DeferredResult_MSG,signal);
		if (error != null) {
			log.warn("{} ERROR:{}",APPLY_DeferredResult_MSG,error.getMessage());
			log.error("{}",error);
		}
	}
}
