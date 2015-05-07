package gov.usgs.cida.wqp.webservice.SimpleStation;
import gov.cida.cdat.control.Message;
import gov.cida.cdat.control.Worker;
import gov.cida.cdat.exception.CdatException;
import gov.cida.cdat.io.Closer;
import gov.cida.cdat.io.TransformOutputStream;
import gov.cida.cdat.io.container.StreamContainer;
import gov.usgs.cida.wqp.dao.IStreamingDao;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.util.HttpConstants;

import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleStationWorker extends Worker implements HttpConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final String nameSpace;
	private final ParameterMap parameters;
	private final IStreamingDao streamingDao;
	private final StreamContainer<? extends TransformOutputStream> transform;
	
	private ResultHandler handler;
	
	
	
	public SimpleStationWorker(String nameSpace, ParameterMap parameters, 
			IStreamingDao streamingDao, StreamContainer<? extends TransformOutputStream> transformProvider) {
		this.nameSpace    = nameSpace;
		this.parameters   = parameters;
		this.streamingDao = streamingDao;
		this.transform    = transformProvider;
	}
	
	
	@Override
	public Message onReceive(Message msg) {
		return super.onReceive(msg);
	}
	
	
	@Override
	public void begin() throws CdatException {
		log.trace("fetching simple station data with streaming handler - started");
		TransformOutputStream target = transform.open();
		handler = new StreamingResultHandler(target);
	}
	
	
	@Override
	public boolean process() throws CdatException {
		streamingDao.stream(nameSpace, parameters.getQueryParameters(), handler);
		return false; // false means there is not more data - that no more calls are necessary
	}
	
	
	@Override
	public void end() {
		Closer.close(transform); // note this StreamContainer calls flush
		log.trace("fetching simple station data with streaming handler - finished");
	}
}