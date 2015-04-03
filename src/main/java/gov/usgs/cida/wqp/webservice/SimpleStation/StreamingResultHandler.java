package gov.usgs.cida.wqp.webservice.SimpleStation;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamingResultHandler implements ResultHandler, Closeable {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final OutputStream stream;  // TODO will use StreamContainer later
	
	public StreamingResultHandler(OutputStream consumer) {
		log.trace("streaming handler constructed");
		this.stream = consumer;
	}
	
	@Override
	public void handleResult(ResultContext context) {
		log.trace("streaming handle result : {}", (context==null ?"null" :"context"));
		try {
			// Serialize object to byte array to write to OutputStream
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(context.getResultObject());
			oos.flush();
			byte[] bytes = baos.toByteArray();
			stream.write(bytes, 0, bytes.length);
		} catch (Exception e) {
			log.warn("Error MapResultHandler", e);
			throw new RuntimeException("Error MapResultHandler", e);
		}
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