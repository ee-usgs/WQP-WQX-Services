package gov.usgs.cida.wqp.webservice.SimpleStation;
import gov.cida.cdat.io.TransformOutputStream;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamingResultHandler implements ResultHandler {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final TransformOutputStream stream;  // TODO will use StreamContainer later
	
	
	public StreamingResultHandler(TransformOutputStream consumer) {
		log.trace("streaming handler constructed");
		this.stream = consumer;
	}
	
	
	@Override
	public void handleResult(ResultContext context) {
		log.trace("streaming handle result : {}", (context==null ?"null" :"context"));
		try {
			Object object = context.getResultObject();
			stream.write(object);
		} catch (Exception e) {
			log.warn("Error MapResultHandler", e);
			throw new RuntimeException("Error MapResultHandler", e);
		}
	}
}