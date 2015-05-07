package gov.usgs.cida.wqp.webservice;

import gov.cida.cdat.control.Message;
import gov.cida.cdat.control.Worker;
import gov.cida.cdat.exception.CdatException;
import gov.cida.cdat.io.Closer;
import gov.cida.cdat.io.TransformOutputStream;
import gov.cida.cdat.io.container.StreamContainer;
import gov.usgs.cida.wqp.dao.IStreamingDao;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.SimpleStation.StreamingResultHandler;

import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StationWorker extends Worker implements HttpConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final String nameSpace;
	private final ParameterMap parameters;
	private final IStreamingDao streamingDao;
	private final StreamContainer<? extends TransformOutputStream> transform;
	
	private ResultHandler handler;
	
//	private final HttpUtils httpUtils = new HttpUtils();
	
	public StationWorker(String nameSpace, ParameterMap parameters, 
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
//		try {
			log.trace("fetching station data with streaming handler - started");
			TransformOutputStream target = transform.open();
			handler = new StreamingResultHandler(target);
//			ResultHandler handler = new MapResultHandler(out, contentTransformer, logService, logId);
			
//		} catch (Exception e) {
//			log.warn(e.getMessage());
//			try {
//				httpUtils.handleException(response, WQX_EMPTY_DOC, e);
//				throw new StreamInitException("Failed to open Station request stream", e);
//			} catch (Exception ee) {
//				throw new StreamInitException("Failed to open Station request stream and handle exception", e);
//			}
//		}
	}
	
	@Override
	public boolean process() throws CdatException {
		streamingDao.stream(nameSpace, parameters.getQueryParameters(), handler);
		return false;
	}
	
	@Override
	public void end() {
		Closer.close(transform); // note this StreamContainer calls flush
		log.trace("fetching station data with streaming handler - finished");
	}
}