package gov.usgs.cida.wqp.transform;


import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.transform.intfc.ITransformer;
import gov.usgs.cida.wqp.util.HttpConstants;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Transformer extends OutputStream implements ITransformer {

	protected OutputStream target;
	protected Map<String, String> mapping;
	protected final ILogService logService;
	protected final BigDecimal logId;

	/** Is this the first write to the stream. */
	private boolean first = true;

	public Transformer(OutputStream target, Map<String, String> mapping, ILogService logService, BigDecimal logId) {
		this.target = target;
		this.mapping = mapping;
		this.logService = logService;
		this.logId = logId;
	}
	
	protected String getMappedName(Entry<?, ?> entry) {
		return mapping.get(entry.getKey());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void write(Object result) throws IOException {
		if (null == result) {
			return;
		}
		
		if (result instanceof Map) {
			Map<String, Object> resultMap = (Map<String, Object>) result;
			if (first) {
				logService.logFirstRowComplete(logId);
				init();
				writeHeader();
				first = false;
			}
			writeData(resultMap);
		}
		target.flush();
	}
	
	protected abstract void init() throws IOException;
	
	protected abstract void writeHeader() throws IOException;

	protected abstract void writeData(Map<String, Object> resultMap) throws IOException;

	@Override
	public void write(int b) throws IOException {
		//Nothing to do here, but we need to override because we are extending OutpuStream.
		throw new RuntimeException("Writing a single byte is not supported");
	}

	/** 
	 * Converts a string to a byte array and stream it.
	 * @param in the string to be streamed.
	 * @throws IOException when issues with the streaming.
	 */
	protected void writeToStream(final String in) throws IOException {
		if (null != in) {
			target.write(in.getBytes(HttpConstants.DEFAULT_ENCODING));
		}
	}

	public void end() throws IOException {
		target.flush();
	}
}
