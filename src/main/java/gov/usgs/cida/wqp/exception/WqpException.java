package gov.usgs.cida.wqp.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WqpException extends Exception {
	
	private static final Logger LOG = LoggerFactory.getLogger(WqpException.class);
	private static final long serialVersionUID = 126269528002852327L;
	private final WqpExceptionId exceptionid;    // uniquely generated id for this exception
	private final String classname;                     // the name of the class that threw the exception
	private final String method;                        // the name of the method that threw the exception
	private final String message;                       // a detailed message
	private WqpException previous = null;        // the exception which was caught
	private String delimeter = "\n";                    // line separator
	
	public WqpException(final WqpExceptionId id, final Class<?> clazz, final String method, final String message) {
		this(id, clazz.getName(), method, message, null);
	}
	
	public WqpException(final WqpExceptionId id, final String classname, final String method, final String message) {
		this(id, classname, method, message, null);
	}
	
	public WqpException(final WqpExceptionId id, final String classname, final String method, final String message, final WqpException previous) {
		LOG.trace(getClass().getName());
		this.exceptionid = id;
		this.classname   = classname;
		this.method      = method;
		this.message     = message;
		this.previous    = previous;
	}
	
	public String traceBack() {
		return traceBack("\n");
	}
	
	public String traceBack(final String sep) {
		int level = 0;
		WqpException e = this;
		final StringBuffer text = new StringBuffer(line("WQPGatewayException Trace: Calling sequence (top to bottom)"));
		while (e != null) {
			level++;
			text.append(sep);
			text.append(line("--level " + level + "--------------------------------------"));
			text.append(line("Class/Method: " + e.classname + "/" + e.method));
			text.append(line("Id          : " + e.exceptionid));
			text.append(line("Message     : " + e.message));
			e = e.previous;
		}
		return text.toString();
	}
	
	private String line(final String s) {
		return s + this.delimeter;
	}
	@Override
	public String getMessage() {
		return this.traceBack();
	}
	@Override
	public String toString() {
		return this.traceBack();
	}
	public WqpExceptionId getExceptionid() {
		return this.exceptionid;
	}
	public String getClassname() {
		return this.classname;
	}
	public String getMethod() {
		return this.method;
	}
	public WqpException getPrevious() {
		return this.previous;
	}
	public String getMessageOnly() {
		return this.message;
	}
}