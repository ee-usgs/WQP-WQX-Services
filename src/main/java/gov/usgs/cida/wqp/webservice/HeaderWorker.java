package gov.usgs.cida.wqp.webservice;

import gov.cida.cdat.control.Message;
import gov.cida.cdat.control.Worker;
import gov.cida.cdat.exception.CdatException;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.station.dao.ICountDao;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HeaderWorker extends Worker implements HttpConstants {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final HttpServletResponse response;
	private final String countQueryId;
	private final ParameterMap parameters;
    private final HttpUtils httpUtils = new HttpUtils();

	private List<Map<String, Object>> counts;
	
	@Autowired
	private ICountDao countDao;
	public void setCountDao(ICountDao countDao) {
		this.countDao = countDao;
	}
	
	public HeaderWorker(HttpServletResponse response, String countQueryId, ParameterMap parameters) {
		this.response = response;
		this.countQueryId = countQueryId;
		this.parameters = parameters;
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
        response.addHeader(HEADER_CONTENT_TYPE, MIME_TYPE_TEXT_CSV); // TODO from request (pm)
        httpUtils.addCountHeader(response, counts);
        response.setHeader("Content-Disposition","attachment; filename=station.csv"); // TODO this will be based on request format
//        response.setContentLength(11*1024); // TODO this would be nice if possible to determine
		log.trace("station header finish");
	}
}