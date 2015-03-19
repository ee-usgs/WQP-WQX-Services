package gov.usgs.cida.wqp.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class WqpGatewayExceptionId {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final Long exceptionId;
	private String name;
	private WqpGatewayExceptionId( String name, int id ) {
		log.trace(getClass().getName());
		this.name = name;
		this.exceptionId = Long.valueOf(id);
	}
	public String toString() { return this.name; }
	public Long value() { return this.exceptionId; }
	//-----------------------------------------
	// EXCEPTION DEFINITIONS
	//-----------------------------------------
	// BASIC EXCEPTIONS
	public static final WqpGatewayExceptionId INITIALIZATION_NOT_PERFORMED =
			new WqpGatewayExceptionId("WQPGatewayException Initialization Exception: " +
					"Initialization of class not performed.", 0x00000);
	// ------------------
	// CACHE EXCEPTIONS
	// ------------------
	public static final WqpGatewayExceptionId CACHE_REFRESH_TIME_EXCEEDED =
		new WqpGatewayExceptionId("WQPGatewayException Cache Exception: Cache refresh " +
				"time exceeded accepted limits.", 0x01000);
	public static final WqpGatewayExceptionId CACHE_CREATION_FAILED =
			new WqpGatewayExceptionId("WQPGatewayException Cache Exception: Cache creation " +
					"was unsuccessful.", 0x01001);
	// ------------------
	// PROPERTIES EXCEPTIONS
	// ------------------
	public static final WqpGatewayExceptionId INVALID_PROPERTIES_FILE_REQUESTED =
		new WqpGatewayExceptionId("WQPGatewayException Properties Exception: Invalid properties file requested."
				, 0x02000);
	// ------------------
	// STATION EXCEPTIONS
	// ------------------
	public static final WqpGatewayExceptionId UNSUPPORTED_REQUEST_METHOD =
		new WqpGatewayExceptionId("WQPGatewayException Gateway Exception: Unsupported http request method."
				, 0x03000);
	public static final WqpGatewayExceptionId ERROR_READING_CLIENT_REQUEST_BODY =
			new WqpGatewayExceptionId("WQPGatewayException Gateway Exception: Error reading client request body."
					, 0x03001);
	public static final WqpGatewayExceptionId UNSUPPORTED_CONTENT_FOR_REQUEST_METHOD =
			new WqpGatewayExceptionId("WQPGatewayException Gateway Exception: Content in request body unsupported for client request method."
					, 0x03002);
	public static final WqpGatewayExceptionId URL_PARSING_EXCEPTION =
			new WqpGatewayExceptionId("WQPGatewayException Gateway Exception: Syntax error parsing server URL."
					, 0x03003);
	public static final WqpGatewayExceptionId CLIENT_PROTOCOL_ERROR =
			new WqpGatewayExceptionId("WQPGatewayException Gateway Exception: Client protocol error."
					, 0x03004);
	public static final WqpGatewayExceptionId SERVER_REQUEST_IO_ERROR =
			new WqpGatewayExceptionId("WQPGatewayException Gateway Exception: I/O error on server request."
					, 0x03005);
	public static final WqpGatewayExceptionId SERVER_RESPONSE_INPUT_STREAM_ERROR =
			new WqpGatewayExceptionId("WQPGatewayException Gateway Exception: Error obtaining input stream for server response."
					, 0x03006);
	public static final WqpGatewayExceptionId CLIENT_RESPONSE_OUTPUT_STREAM_ERROR =
			new WqpGatewayExceptionId("WQPGatewayException Gateway Exception: Error obtaining output stream for client response."
					, 0x03007);
	public static final WqpGatewayExceptionId SERVER_TO_CLIENT_RESPONSE_ERROR =
			new WqpGatewayExceptionId("WQPGatewayException Gateway Exception: Error copying server response to client."
					, 0x03008);
	// ------------------
	// UTIL EXCEPTIONS
	// ------------------
	public static final WqpGatewayExceptionId UTIL_GZIP_COMPRESSION_ERROR =
			new WqpGatewayExceptionId("WQPGatewayException Util Exception: Gzip compression error."
					, 0x04000);
	public static final WqpGatewayExceptionId GZIP_ERROR =
			new WqpGatewayExceptionId("WQPGatewayException Util Exception: Error uncompressing server gzip content."
					, 0x04001);
	public static final WqpGatewayExceptionId GZIP_NOT_UTF8 =
			new WqpGatewayExceptionId("WQPGatewayException Util Exception: Server gzip content is not UTF-8."
					, 0x04002);
	// ------------------
	// WQPUTIL EXCEPTIONS
	// ------------------
	public static final WqpGatewayExceptionId INVALID_SERVER_RESPONSE_ENCODING =
			new WqpGatewayExceptionId("WQPGatewayException WQPUtil Exception: Invalid content encoding."
					, 0x05000);
	public static final WqpGatewayExceptionId INVALID_SERVER_RESPONSE_CODE =
			new WqpGatewayExceptionId("WQPGatewayException WQPUtil Exception: Invalid response code."
					, 0x05001);

	public static final WqpGatewayExceptionId UNDEFINED_WQP_CONFIG_PARAM =
			new WqpGatewayExceptionId("WQPGatewayException WQPUtil Exception: Undefined WQP Configureation Parameter."
					, 0x06000);
	public static final WqpGatewayExceptionId METHOD_PARAM_NULL =
			new WqpGatewayExceptionId("WQPGatewayException WQPUtil Exception: Method Parameter must not be 'null'."
					, 0x06001);
	public static final WqpGatewayExceptionId METHOD_PARAM_EMPTY =
			new WqpGatewayExceptionId("WQPGatewayException WQPUtil Exception: Method Parameter must not be an empty String."
					, 0x06002);
	public static final WqpGatewayExceptionId METHOD_PARAM_BOUNDS =
			new WqpGatewayExceptionId("WQPGatewayException WQPUtil Exception: Method Parameter is out of bounds."
					, 0x06003);
}

