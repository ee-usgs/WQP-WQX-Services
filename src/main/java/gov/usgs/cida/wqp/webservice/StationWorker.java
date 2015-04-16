package gov.usgs.cida.wqp.webservice;

import gov.cida.cdat.control.Message;
import gov.cida.cdat.control.Worker;
import gov.cida.cdat.exception.CdatException;
import gov.cida.cdat.exception.StreamInitException;
import gov.usgs.cida.wqp.dao.IStreamingDao;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.station.dao.MapResultHandler;
import gov.usgs.cida.wqp.util.CharacterSeparatedValue;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StationWorker extends Worker implements HttpConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final String nameSpace;
	private final HttpServletResponse response;
	private final ParameterMap parameters;
	private final HttpUtils httpUtils = new HttpUtils();
	
	private OutputStream out;
	
	protected IStreamingDao streamingDao;
	
	public StationWorker(HttpServletResponse response, String nameSpace, ParameterMap parameters, 
			IStreamingDao streamingDao) {
		this.response = response;
		this.nameSpace = nameSpace;
		this.parameters = parameters;
		this.streamingDao = streamingDao;
	}
	
	@Override
	public Message onReceive(Message msg) {
		return super.onReceive(msg);
	}
	
	@Override
	public void begin() throws CdatException {
		super.begin();
		try {
			this.out = new StationColumnMapper( response.getOutputStream() );
		} catch (Exception e) {
			log.warn(e.getMessage());
			try {
				httpUtils.handleException(response, WQX_EMPTY_DOC, e);
				throw new StreamInitException("Failed to open Station request stream", e);
			} catch (Exception ee) {
				throw new StreamInitException("Failed to open Station request stream and handle exception", e);
			}
		}
	}
	
	@Override
	public boolean process() throws CdatException {
		log.trace("fetching station data with streaming handler - started");

		String mimeType    = (String) parameters.getQueryParameters().get(Parameters.MIMETYPE.toString());
		CharacterSeparatedValue contentTransformer = CharacterSeparatedValue.CSV;
		if ("tsv".equals(mimeType)) {
			contentTransformer = CharacterSeparatedValue.TSV;
		}
		
		ResultHandler handler = new MapResultHandler(out, contentTransformer);
		streamingDao.stream(nameSpace, parameters.getQueryParameters(), handler);
		log.trace("fetching station data with streaming handler - finished");
		return false;
	}
	
	@Override
	public void end() {
		super.end();
	}
}