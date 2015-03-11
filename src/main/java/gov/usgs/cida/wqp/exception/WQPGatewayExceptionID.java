package gov.usgs.cida.wqp.exception;

import org.apache.log4j.Logger;

public class WQPGatewayExceptionID {
	private final Logger log = Logger.getLogger(getClass());
	
	private final Long exceptionId;
    private String name;

    private WQPGatewayExceptionID( String name, int id ) {
        log.trace(getClass());
        
    	this.name = name;
    	this.exceptionId = Long.valueOf(id);
    }
    
    public String toString() { return this.name; }
    public Long value() { return this.exceptionId; }
    
    //-----------------------------------------
    // EXCEPTION DEFINITIONS
    //-----------------------------------------
    
    // BASIC EXCEPTIONS
    public static final WQPGatewayExceptionID INITIALIZATION_NOT_PERFORMED = 
        	new WQPGatewayExceptionID("WQPGatewayException Initialization Exception: " +
        			"Initialization of class not performed.", 0x00000);
    
    // ------------------
    // CACHE EXCEPTIONS
    // ------------------
    public static final WQPGatewayExceptionID CACHE_REFRESH_TIME_EXCEEDED = 
    	new WQPGatewayExceptionID("WQPGatewayException Cache Exception: Cache refresh " +
    			"time exceeded accepted limits.", 0x01000);
    
    public static final WQPGatewayExceptionID CACHE_CREATION_FAILED = 
        	new WQPGatewayExceptionID("WQPGatewayException Cache Exception: Cache creation " +
        			"was unsuccessful.", 0x01001);

    // ------------------
    // PROPERTIES EXCEPTIONS
    // ------------------
    public static final WQPGatewayExceptionID INVALID_PROPERTIES_FILE_REQUESTED = 
    	new WQPGatewayExceptionID("WQPGatewayException Properties Exception: Invalid properties file requested."
    			, 0x02000);
    
    // ------------------
    // STATION EXCEPTIONS
    // ------------------
    public static final WQPGatewayExceptionID UNSUPPORTED_REQUEST_METHOD = 
    	new WQPGatewayExceptionID("WQPGatewayException Gateway Exception: Unsupported http request method."
    			, 0x03000);
    public static final WQPGatewayExceptionID ERROR_READING_CLIENT_REQUEST_BODY = 
        	new WQPGatewayExceptionID("WQPGatewayException Gateway Exception: Error reading client request body."
        			, 0x03001);
    public static final WQPGatewayExceptionID UNSUPPORTED_CONTENT_FOR_REQUEST_METHOD = 
        	new WQPGatewayExceptionID("WQPGatewayException Gateway Exception: Content in request body unsupported for client request method."
        			, 0x03002);
    public static final WQPGatewayExceptionID URL_PARSING_EXCEPTION = 
        	new WQPGatewayExceptionID("WQPGatewayException Gateway Exception: Syntax error parsing server URL."
        			, 0x03003);
    public static final WQPGatewayExceptionID CLIENT_PROTOCOL_ERROR = 
        	new WQPGatewayExceptionID("WQPGatewayException Gateway Exception: Client protocol error."
        			, 0x03004);
    public static final WQPGatewayExceptionID SERVER_REQUEST_IO_ERROR = 
        	new WQPGatewayExceptionID("WQPGatewayException Gateway Exception: I/O error on server request."
        			, 0x03005);
    public static final WQPGatewayExceptionID SERVER_RESPONSE_INPUT_STREAM_ERROR = 
        	new WQPGatewayExceptionID("WQPGatewayException Gateway Exception: Error obtaining input stream for server response."
        			, 0x03006);
    public static final WQPGatewayExceptionID CLIENT_RESPONSE_OUTPUT_STREAM_ERROR = 
        	new WQPGatewayExceptionID("WQPGatewayException Gateway Exception: Error obtaining output stream for client response."
        			, 0x03007);
    public static final WQPGatewayExceptionID SERVER_TO_CLIENT_RESPONSE_ERROR = 
        	new WQPGatewayExceptionID("WQPGatewayException Gateway Exception: Error copying server response to client."
        			, 0x03008);
    
    // ------------------
    // UTIL EXCEPTIONS
    // ------------------
    public static final WQPGatewayExceptionID UTIL_GZIP_COMPRESSION_ERROR = 
        	new WQPGatewayExceptionID("WQPGatewayException Util Exception: Gzip compression error."
        			, 0x04000);
    public static final WQPGatewayExceptionID GZIP_ERROR = 
        	new WQPGatewayExceptionID("WQPGatewayException Util Exception: Error uncompressing server gzip content."
        			, 0x04001);
    public static final WQPGatewayExceptionID GZIP_NOT_UTF8 = 
        	new WQPGatewayExceptionID("WQPGatewayException Util Exception: Server gzip content is not UTF-8."
        			, 0x04002);
    
    // ------------------
    // WQPUTIL EXCEPTIONS
    // ------------------
    public static final WQPGatewayExceptionID INVALID_SERVER_RESPONSE_ENCODING = 
        	new WQPGatewayExceptionID("WQPGatewayException WQPUtil Exception: Invalid content encoding."
        			, 0x05000);
    
    public static final WQPGatewayExceptionID INVALID_SERVER_RESPONSE_CODE = 
        	new WQPGatewayExceptionID("WQPGatewayException WQPUtil Exception: Invalid response code."
        			, 0x05001);
    
}
