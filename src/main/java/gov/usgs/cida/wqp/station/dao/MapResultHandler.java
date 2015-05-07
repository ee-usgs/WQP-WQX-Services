package gov.usgs.cida.wqp.station.dao;

import gov.cida.cdat.io.Closer;
import gov.cida.cdat.transform.CharacterSeparatedValue;
import gov.usgs.cida.wqp.service.ILogService;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapResultHandler implements ResultHandler, Closeable {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final CharacterSeparatedValue transformer; // TODO need to generalize
	private final OutputStream stream;  // TODO will use StreamContainer later
	private final ILogService logService;
	private final BigDecimal logId;
	
	private boolean doHeaders = true;
	
	public MapResultHandler(OutputStream consumer, CharacterSeparatedValue transformer, ILogService logService, BigDecimal logId) {
		log.trace("streaming handler constructed");
		this.transformer = transformer;
		this.stream = consumer;
		this.logService = logService;
		this.logId = logId;
	}
	
	@Override
	public void handleResult(ResultContext context) {
		log.trace("streaming handle result : {}", (context==null ?"null" :"context"));
		try {
			//context.getResultCount();
			@SuppressWarnings("unchecked")
			Map<String,Object> entry = (Map<String,Object>) context.getResultObject();
			log.trace("streaming handle result : {}", (entry==null ?"null" :"entry"));
			if (doHeaders) {
				log.trace("streaming handle result : preheader");
				doHeader(entry);
				log.trace("streaming handle result : postheader");
				logService.logFirstRowComplete(logId);
			}
			write(entry);
		} catch (RuntimeException e) {
			log.warn("Error MapResultHandler", e);
			throw e;
		}
	}
	
	private void write(Map<String, Object> entry) {
		log.trace("need entry: {}", entry);
		try {
			String sep = "";
			for (Map.Entry<String,Object> column : entry.entrySet()) {
				stream.write(sep.getBytes());
				String initialValue = "";
				if ( null != column.getValue() ) {
					initialValue = column.getValue().toString();
				}
				String writeValue = transformer.encode(initialValue);
				stream.write( writeValue.getBytes() );
				sep = transformer.getValueSeparator();
			}
			stream.write( transformer.getEntrySeparator().getBytes() );
		} catch (IOException e) {
			throw new RuntimeException("Failed writing header",e);
		}
	}
	
	private void doHeader(Map<String,Object> headerEntry) {
		doHeaders = false;
		// this makes the Java DRY
		Map<String,	Object> headerMap = new LinkedHashMap<String, Object>();
		for (Map.Entry<String,Object> column : headerEntry.entrySet()) {
			headerMap.put(column.getKey(), column.getKey());
		}
		write(headerMap);
	}
	
	
	@Override
	public void close() throws IOException {
		Closer.close(stream);
	}
}