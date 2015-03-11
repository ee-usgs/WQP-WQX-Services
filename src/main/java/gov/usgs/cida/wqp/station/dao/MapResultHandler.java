package gov.usgs.cida.wqp.station.dao;

import gov.usgs.cida.wqp.util.CharacterSeparatedValue;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.log4j.Logger;


public class MapResultHandler implements ResultHandler, Closeable {
	private final Logger log = Logger.getLogger(getClass());

	private final CharacterSeparatedValue transformer; // TODO need to generalize
	private final OutputStream stream;  // TODO will use StreamContainer later
	private final ThreadLocal<List<String>> headers;
	
	
	public MapResultHandler(OutputStream consumer, CharacterSeparatedValue transformer) {
		log.trace("streaming handler constructed");
		
		this.transformer = transformer;
		this.stream = consumer;
		this.headers = new ThreadLocal<List<String>>();
	}
	
	
	@Override
	public void handleResult(ResultContext context) {
		log.trace("streaming handle result : " + (context==null ?"null" :"context"));
		
		try {
			//context.getResultCount();
			@SuppressWarnings("unchecked")
			Map<String,Object> entry = (Map<String,Object>) context.getResultObject();
			log.trace("streaming handle result : " + (entry==null ?"null" :"entry"));
			
			if ( ! hasHeader() ) {
				log.trace("streaming handle result : preheader");
				doHeader( entry.keySet() );
				log.trace("streaming handle result : postheader");
			}
			
			write(entry);
		} catch (RuntimeException e) {
			log.warn(e);
			throw e;
		} 
	}
	
	
	private void write(Map<String, ? extends Object> values) {
		log.trace("need entry: " + values);

		try {
			String sep = "";
			for (String column : headers.get()) {
				stream.write(sep.getBytes());
				String initialValue = "";
				if ( null != values.get(column) ) {
					initialValue = values.get(column).toString();
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
	
	
	private void doHeader(Collection<String> columns) {
		headers.set( new ArrayList<String>(columns) );
		log.trace("need headers: " + headers.get());
		
		// this makes the Java DRY
		Map<String,	String> headerMap = new HashMap<String, String>();
		for (String column : headers.get()) {
			headerMap.put(column, column);
		}
		
		write(headerMap);
	}

	
	private boolean hasHeader() {
		return headers.get() != null;
	}

	
	@Override
	public void close() throws IOException {
		try {
			stream.close();
		} catch (Exception e) {
			// TODO will use Closer later
		}
	}

}
