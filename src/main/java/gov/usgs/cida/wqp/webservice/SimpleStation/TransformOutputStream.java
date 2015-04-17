package gov.usgs.cida.wqp.webservice.SimpleStation;

import gov.cida.cdat.io.Closer;
import gov.cida.cdat.transform.Transformer;
import gov.usgs.cida.wqp.service.ILogService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;

public abstract class TransformOutputStream extends OutputStream {

	protected OutputStream target;
	protected ILogService webServiceLogService;
	protected BigDecimal logId;
	private Transformer transform;
	protected Map<String, Object> result;
	
	public TransformOutputStream(OutputStream target, ILogService webServiceLogService, BigDecimal logId, Transformer transform) {
		this.target = target;
		this.webServiceLogService = webServiceLogService;
		this.logId = logId;
		this.transform = transform;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void write(byte[] bytes, int off, int len) throws IOException {
//		byte[] newBytes = transform.transform(bytes, off, len);
//		target.write(newBytes);
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object obj = ois.readObject();
			result = (Map<String, Object>) obj;

		} catch (Exception e) {
			// If there is a problem, just write to the underlying outputStream.
			//LOG.error("Writing to underlying OutputStream.", e);
			result = null;
			target.write(bytes, off, len);
			
		}
	}
	
	@Override
	public void write(int b) throws IOException {
		throw new RuntimeException("Writing a single byte is not supported");
	}
	
//	public void write(Object obj) throws IOException {
//		byte[] bytes = transform.transform(obj);
//		target.write(bytes);
//	}
//	
//	@Override
//	public void flush() throws IOException {
//		target.write( transform.getRemaining() );
//		super.flush();
//	}
	
	@Override
	public void close() throws IOException {
		Closer.close(target);
		super.close();
	}
	
	public abstract void end();
	
}
