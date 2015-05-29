package gov.usgs.cida.wqp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WqpExceptionId {
	private static final Logger LOG = LoggerFactory.getLogger(WqpExceptionId.class);
	
	private final Long exceptionId;
	private String name;
	
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
	
	//-----------------------------------------
	// EXCEPTION DEFINITIONS
	//-----------------------------------------
	// BASIC EXCEPTIONS
	public static final WqpExceptionId INITIALIZATION_NOT_PERFORMED =
			new WqpExceptionId("WQPGatewayException Initialization Exception: " +
					"Initialization of class not performed.", 0x00000);
	// ------------------
	// CACHE EXCEPTIONS
	// ------------------
	public static final WqpExceptionId CACHE_REFRESH_TIME_EXCEEDED =
		new WqpExceptionId("WQPGatewayException Cache Exception: Cache refresh " +
				"time exceeded accepted limits.", 0x01000);
	public static final WqpExceptionId CACHE_CREATION_FAILED =
			new WqpExceptionId("WQPGatewayException Cache Exception: Cache creation " +
					"was unsuccessful.", 0x01001);
	// ------------------
	// PROPERTIES EXCEPTIONS
	// ------------------
	public static final WqpExceptionId INVALID_PROPERTIES_FILE_REQUESTED =
		new WqpExceptionId("WQPGatewayException Properties Exception: Invalid properties file requested."
				, 0x02000);
	// ------------------
	// STATION EXCEPTIONS
	// ------------------
	public static final WqpExceptionId UNSUPPORTED_REQUEST_METHOD =
		new WqpExceptionId("WQPGatewayException Gateway Exception: Unsupported http request method."
				, 0x03000);
	public static final WqpExceptionId ERROR_READING_CLIENT_REQUEST_BODY =
			new WqpExceptionId("WQPGatewayException Gateway Exception: Error reading client request body."
					, 0x03001);
	public static final WqpExceptionId UNSUPPORTED_CONTENT_FOR_REQUEST_METHOD =
			new WqpExceptionId("WQPGatewayException Gateway Exception: Content in request body unsupported for client request method."
					, 0x03002);
	public static final WqpExceptionId URL_PARSING_EXCEPTION =
			new WqpExceptionId("WQPGatewayException Gateway Exception: Syntax error parsing server URL."
					, 0x03003);
	public static final WqpExceptionId CLIENT_PROTOCOL_ERROR =
			new WqpExceptionId("WQPGatewayException Gateway Exception: Client protocol error."
					, 0x03004);
	public static final WqpExceptionId SERVER_REQUEST_IO_ERROR =
			new WqpExceptionId("WQPGatewayException Gateway Exception: I/O error on server request."
					, 0x03005);
	public static final WqpExceptionId SERVER_RESPONSE_INPUT_STREAM_ERROR =
			new WqpExceptionId("WQPGatewayException Gateway Exception: Error obtaining input stream for server response."
					, 0x03006);
	public static final WqpExceptionId CLIENT_RESPONSE_OUTPUT_STREAM_ERROR =
			new WqpExceptionId("WQPGatewayException Gateway Exception: Error obtaining output stream for client response."
					, 0x03007);
	public static final WqpExceptionId SERVER_TO_CLIENT_RESPONSE_ERROR =
			new WqpExceptionId("WQPGatewayException Gateway Exception: Error copying server response to client."
					, 0x03008);
	// ------------------
	// UTIL EXCEPTIONS
	// ------------------
	public static final WqpExceptionId UTIL_GZIP_COMPRESSION_ERROR =
			new WqpExceptionId("WQPGatewayException Util Exception: Gzip compression error."
					, 0x04000);
	public static final WqpExceptionId GZIP_ERROR =
			new WqpExceptionId("WQPGatewayException Util Exception: Error uncompressing server gzip content."
					, 0x04001);
	public static final WqpExceptionId GZIP_NOT_UTF8 =
			new WqpExceptionId("WQPGatewayException Util Exception: Server gzip content is not UTF-8."
					, 0x04002);
	// ------------------
	// WQPUTIL EXCEPTIONS
	// ------------------
	public static final WqpExceptionId INVALID_SERVER_RESPONSE_ENCODING =
			new WqpExceptionId("WQPGatewayException WQPUtil Exception: Invalid content encoding."
					, 0x05000);
	public static final WqpExceptionId INVALID_SERVER_RESPONSE_CODE =
			new WqpExceptionId("WQPGatewayException WQPUtil Exception: Invalid response code."
					, 0x05001);

	public static final WqpExceptionId UNDEFINED_WQP_CONFIG_PARAM =
			new WqpExceptionId("WQPGatewayException WQPUtil Exception: Undefined WQP Configureation Parameter."
					, 0x06000);
	public static final WqpExceptionId METHOD_PARAM_NULL =
			new WqpExceptionId("WQPGatewayException WQPUtil Exception: Method Parameter must not be 'null'."
					, 0x06001);
	public static final WqpExceptionId METHOD_PARAM_EMPTY =
			new WqpExceptionId("WQPGatewayException WQPUtil Exception: Method Parameter must not be an empty String."
					, 0x06002);
	public static final WqpExceptionId METHOD_PARAM_BOUNDS =
			new WqpExceptionId("WQPGatewayException WQPUtil Exception: Method Parameter is out of bounds."
					, 0x06003);
}

