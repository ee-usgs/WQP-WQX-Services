package gov.usgs.cida.wqp.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class WQPGatewayException extends Exception {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 126269528002852327L;
	private final WQPGatewayExceptionID exceptionid;    // uniquely generated id for this exception
	private final String classname;                     // the name of the class that threw the exception
	private final String method;                        // the name of the method that threw the exception
	private final String message;                       // a detailed message
	private WQPGatewayException previous = null;        // the exception which was caught
	private String delimeter = "\n";                    // line separator
	public WQPGatewayException(final WQPGatewayExceptionID id, final String classname, final String method, final String message) {
		this(id, classname, method, message, null);
	}
	public WQPGatewayException(final WQPGatewayExceptionID id, final String classname, final String method, final String message, final WQPGatewayException previous) {
		log.trace(getClass().getName());
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
		this.delimeter = sep;
		int level = 0;
		WQPGatewayException e = this;
		final StringBuffer text = new StringBuffer(line("WQPGatewayException Trace: Calling sequence (top to bottom)"));
		while (e != null) {
			level++;
			text.append(this.delimeter);
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
	public WQPGatewayExceptionID getExceptionid() {
		return this.exceptionid;
	}
	public String getClassname() {
		return this.classname;
	}
	public String getMethod() {
		return this.method;
	}
	public WQPGatewayException getPrevious() {
		return this.previous;
	}
	public String getMessageOnly() {
		return this.message;
	}
}