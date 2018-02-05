package gov.usgs.cida.wqp.dao;

import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.transform.intfc.ITransformer;
import gov.usgs.cida.wqp.webservice.BaseController;

import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamingResultHandler implements ResultHandler<Object> {

	private static final Logger LOG = LoggerFactory.getLogger(StreamingResultHandler.class);
	private final ITransformer transformer;

	public StreamingResultHandler(ITransformer transformer) {
		LOG.trace("streaming handler constructed");
		this.transformer = transformer;
	}

	@Override
	public void handleResult(ResultContext<?> context) {
		LOG.trace("streaming handle result : {}", context == null ? "null" : "context");
		if (null != context) {
			summarize(context.getResultObject());
			transformer.write(context.getResultObject());
		}
	}

	protected void summarize(Object rawResult) {
		if (rawResult instanceof Map && ((Map<?, ?>)rawResult).containsKey(BaseColumn.KEY_ORGANIZATION)) {
			String key = ((Map<?, ?>)rawResult).get(BaseColumn.KEY_ORGANIZATION).toString();
			Map<String, Integer> details = BaseController.getDownloadDetails();
			if (details.containsKey(key)) {
				details.put(key, details.get(key) + 1);
			} else {
				details.put(key, 1);
			}
			BaseController.setDownloadDetails(details);
		}
	}

}