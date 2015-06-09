package gov.usgs.cida.wqp.dao;

import gov.usgs.cida.wqp.transform.intfc.ITransformer;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamingResultHandler implements ResultHandler {

	private static final Logger LOG = LoggerFactory.getLogger(StreamingResultHandler.class);
	private final ITransformer transformer;
	
	
	public StreamingResultHandler(ITransformer transformer) {
		LOG.trace("streaming handler constructed");
		this.transformer = transformer;
	}

	@Override
	public void handleResult(ResultContext context) {
		LOG.trace("streaming handle result : {}", (context==null ?"null" :"context"));
		if (null != context) {
			transformer.write(context.getResultObject());
		}
	}
}