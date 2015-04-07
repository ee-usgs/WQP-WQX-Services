package gov.usgs.cida.wqp.webservice;
import gov.cida.cdat.control.Message;
import gov.cida.cdat.control.Worker;
import gov.cida.cdat.exception.CdatException;
import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.station.dao.ICountDao;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.HttpUtils;
import gov.usgs.cida.wqp.util.PutSomeWhereElse;

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
	
	private String defaultMimetype;
	
	public HeaderWorker(HttpServletResponse response, String countQueryId, ParameterMap parameters, ICountDao countDao, String defaultMimetype) {
		this.response = response;
		this.countQueryId = countQueryId;
		this.parameters = parameters;
		this.countDao = countDao;
		this.defaultMimetype = defaultMimetype;
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
		
		String contentType = PutSomeWhereElse.getContentType(parameters, defaultMimetype);
		response.addHeader(HEADER_CONTENT_TYPE, contentType);

		httpUtils.addCountHeader(response, counts);
		
		if (contentType.contentEquals(MIME_TYPE_TEXT_CSV) || contentType.contentEquals(MIME_TYPE_TEXT_TSV)) {
			response.setHeader("Content-Disposition","attachment; filename=station."+PutSomeWhereElse.getMimeType(parameters, defaultMimetype));
		}
//		response.setContentLength(11*1024); // TODO this would be nice if possible to determine
		log.trace("station header finish");
	}
}