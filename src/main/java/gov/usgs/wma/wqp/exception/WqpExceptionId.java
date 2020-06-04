package gov.usgs.wma.wqp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WqpExceptionId {

	private static final Logger LOG = LoggerFactory.getLogger(WqpExceptionId.class);

	private final Long exceptionId;
	private String name;

	//-----------------------------------------
	// EXCEPTION DEFINITIONS
	//-----------------------------------------
	public static final WqpExceptionId URL_PARSING_EXCEPTION =
			new WqpExceptionId("WQPGatewayException Gateway Exception: Syntax error parsing server URL."
					, 0x03003);
	public static final WqpExceptionId METHOD_PARAM_NULL =
			new WqpExceptionId("WQPGatewayException WQPUtil Exception: Method Parameter must not be 'null'."
					, 0x06001);
	public static final WqpExceptionId METHOD_PARAM_EMPTY =
			new WqpExceptionId("WQPGatewayException WQPUtil Exception: Method Parameter must not be an empty String."
					, 0x06002);
	public static final WqpExceptionId METHOD_PARAM_BOUNDS =
			new WqpExceptionId("WQPGatewayException WQPUtil Exception: Method Parameter is out of bounds."
					, 0x06003);

	private WqpExceptionId( String name, int id ) {
		LOG.trace(getClass().getName());
		this.name = name;
		this.exceptionId = Long.valueOf(id);
	}

	public String toString() {
		return this.name;
	}
	public Long value() {
		return this.exceptionId;
	}
}

