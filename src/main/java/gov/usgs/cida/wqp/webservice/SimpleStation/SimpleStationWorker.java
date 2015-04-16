package gov.usgs.cida.wqp.webservice.SimpleStation;
import gov.cida.cdat.control.Message;
import gov.cida.cdat.control.Worker;
import gov.cida.cdat.exception.CdatException;
import gov.cida.cdat.exception.StreamInitException;
import gov.usgs.cida.wqp.dao.IStreamingDao;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SimpleStationWorker extends Worker implements HttpConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final String nameSpace;
	private final HttpServletResponse response;
	private final ParameterMap parameters;
	private final HttpUtils httpUtils = new HttpUtils();
	private OutputStream out;

	private final TransformOutputStream transformer;
	
	protected IStreamingDao streamingDao;
	
	public SimpleStationWorker(HttpServletResponse response, String nameSpace, ParameterMap parameters, 
			IStreamingDao streamingDao, TransformOutputStream transformer) {
		this.response = response;
		this.nameSpace = nameSpace;
		this.parameters = parameters;
		this.streamingDao = streamingDao;
		this.transformer = transformer;
	}
	
	@Override
	public Message onReceive(Message msg) {
		return super.onReceive(msg);
	}
	
	@Override
	public void begin() throws CdatException {
		super.begin();
		try {
			this.out = transformer;
		} catch (Exception e) {
			log.warn(e.getMessage());
			try {
				httpUtils.handleException(response, WQX_EMPTY_DOC, e);
				throw new StreamInitException("Failed to open Simple Station request stream", e);
			} catch (Exception ee) {
				throw new StreamInitException("Failed to open Simple Station request stream and handle exception", e);
			}
		}
	}
	
	@Override
	public boolean process() throws CdatException {
		log.trace("fetching simple station data with streaming handler - started");
		ResultHandler handler = new StreamingResultHandler(out);
		streamingDao.stream(nameSpace, parameters.getQueryParameters(), handler);
		log.trace("fetching simple station data with streaming handler - finished");
		return false;
	}
	
	@Override
	public void end() {
		transformer.end();
		super.end();
	}

}