package gov.usgs.cida.wqp.webservice;

import gov.cida.cdat.io.TransformOutputStream;
import gov.cida.cdat.transform.Transformer;
import gov.usgs.cida.wqp.service.ILogService;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ObjectTransformStream extends TransformOutputStream {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private ILogService logService;
	private BigDecimal logId;
	
	boolean first = true;
	
	
	public ObjectTransformStream(OutputStream target, ILogService logService, BigDecimal logId, Transformer transform) {
		super(target, transform);
		this.logService = logService;
		this.logId = logId;
	}


	private void doFirst() {
		if (first) {
			logService.logFirstRowComplete(logId);
			first = false;
		}
	}
	

	@Override
	public void write(final byte[] bytes, final int off, final int len) throws IOException {
		Object obj = extractObject(bytes,off,len);
		if (null == obj) {
			log.warn("Cannot write null.");
			return;
		}
		log.warn("Should call write(Object)");
		write(obj);
	}
	

	@Override
	public void write(Object obj) throws IOException {
		doFirst();
		super.write(obj);
	}
}
