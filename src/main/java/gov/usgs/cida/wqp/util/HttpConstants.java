package gov.usgs.cida.wqp.util;
public interface HttpConstants extends XmlConstants {
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final int    HEADER_WARNING_DEFAULT_CODE = 299;
	public static final String HEADER_WARNING = "Warning";
	public static final String HEADER_DELIMITER = "-";
	public static final String HEADER_COUNT = "Count";
	public static final String HEADER_SITE = "Site";
	public static final String HEADER_TOTAL = "Total";
	public static final String HEADER_SITE_COUNT = HEADER_SITE + HEADER_DELIMITER + HEADER_COUNT;
	public static final String HEADER_TOTAL_SITE_COUNT = HEADER_TOTAL + HEADER_DELIMITER + HEADER_SITE_COUNT;
	public static final String HEADER_CORS = "Access-Control-Allow-Origin";
	public static final String HEADER_CORS_VALUE = "*";
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_CONTENT_DISPOSITION = "Content-disposition";
	public static final String MIME_TYPE_FI = "fi";
	public static final String MIME_TYPE_APPLICATION_FI = "application/fastinfoset";
	public static final String MIME_TYPE_TEXT = "application/text";
	public static final String MIME_TYPE_XML = "xml";
	public static final String MIME_TYPE_APPLICATION_XML = "application/xml";
	public static final String MIME_TYPE_APPLICATION_JSON = "application/json";
	public static final String MIME_TYPE_APPLICATION_ZIP = "application/zip";
	public static final String MIME_TYPE_TEXT_CSV = "text/csv";
	public static final String ENDPOINT_RESULT = "Result";
	public static final String ENDPOINT_STATION = "Station";
	public static final String ENDPOINT_SIMPLE_STATION = "simplestation";
	public static final String ENDPOINT_BIOLOGICAL_RESULT = "biologicalresult";
	public static final String SEARCH_ENPOINT = "/search";
	// must do it here rather than in the EndPoint constructor because annotations require compile time values
	public static final String RESULT_SEARCH_ENPOINT = ENDPOINT_RESULT + SEARCH_ENPOINT;
	public static final String STATION_SEARCH_ENPOINT = ENDPOINT_STATION + SEARCH_ENPOINT;
	public static final String SIMPLE_STATION_ENDPOINT = ENDPOINT_SIMPLE_STATION + SEARCH_ENPOINT;
	public static final String BIOLOGICAL_RESULT_ENPOINT = ENDPOINT_BIOLOGICAL_RESULT + SEARCH_ENPOINT;
	public static final String HEADER_RESULT = ENDPOINT_RESULT;
}