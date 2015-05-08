package gov.usgs.cida.wqp.webservice;
import gov.cida.cdat.control.Message;
import gov.cida.cdat.control.Worker;
import gov.cida.cdat.exception.CdatException;
import gov.usgs.cida.wqp.dao.ICountDao;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.util.MimeType;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class HeaderWorker extends Worker implements HttpConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final HttpServletResponse response;
	private final String countQueryId;
	private final ParameterMap parameters;
	private final HttpUtils httpUtils = new HttpUtils();
	
	private List<Map<String, Object>> counts;
	
	private ICountDao countDao;

	private MimeType mimeType;
	
	private String filename = "data";
	
	public HeaderWorker(HttpServletResponse response, String countQueryId, ParameterMap parameters, ICountDao countDao, MimeType mimeType) {
		this.response = response;
		this.countQueryId = countQueryId;
		this.parameters = parameters;
		this.countDao = countDao;
		
		String mimeTypeParam = (String) parameters.getQueryParameters().get(Parameters.MIMETYPE.toString());
		this.mimeType = mimeType.fromString(mimeTypeParam);
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilename() {
		return filename;
	}
	
	@Override
	public Message onReceive(Message msg) {
		return super.onReceive(msg);
	}
	
	@Override
	public void begin() throws CdatException {
	}
	
	@Override
	public boolean process() throws CdatException {
		log.trace("station count");
		counts = countDao.getCounts(countQueryId, parameters.getQueryParameters());
		return false;
	}
	
	@Override
	public void end() {
		log.trace("station header start");
		response.setCharacterEncoding(DEFAULT_ENCODING);
		response.addHeader(HEADER_CONTENT_TYPE, mimeType.getMimeType());
		httpUtils.addCountHeader(response, counts);
		response.setHeader("Content-Disposition","attachment; filename="+filename+"."+mimeType);
		//response.setContentLength(byteLength); // TODO this would be nice if possible to determine
		log.trace("station header finish");
	}
}