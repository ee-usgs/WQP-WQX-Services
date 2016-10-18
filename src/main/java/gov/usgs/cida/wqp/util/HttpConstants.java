package gov.usgs.cida.wqp.util;

import gov.usgs.cida.wqp.transform.XmlConstants;

import org.springframework.http.MediaType;

public interface HttpConstants extends XmlConstants {
	String DEFAULT_ENCODING = "UTF-8";
	String HEADER_FATAL_ERROR = "FATAL-ERROR";
	int HEADER_WARNING_DEFAULT_CODE = 299;
	String HEADER_WARNING = "Warning";
	String HEADER_DELIMITER = "-";
	String HEADER_COUNT = "Count";
	String HEADER_SITE = "Site";
	String HEADER_TOTAL = "Total";
	String HEADER_SITE_COUNT = HEADER_SITE + HEADER_DELIMITER + HEADER_COUNT;
	String HEADER_TOTAL_SITE_COUNT = HEADER_TOTAL + HEADER_DELIMITER + HEADER_SITE_COUNT;

	String HEADER_CONTENT_TYPE = "Content-Type";
	String HEADER_CONTENT_DISPOSITION = "Content-disposition";
	String ENDPOINT_RESULT = "Result";
	String ENDPOINT_STATION = "Station";
	String ENDPOINT_SIMPLE_STATION = "simplestation";
	String SEARCH_ENPOINT = "/search";
	// must do it here rather than in the EndPoint constructor because annotations require compile time values
	String RESULT_SEARCH_ENPOINT = ENDPOINT_RESULT + SEARCH_ENPOINT;
	String STATION_SEARCH_ENPOINT = ENDPOINT_STATION + SEARCH_ENPOINT;
	String SIMPLE_STATION_ENDPOINT = ENDPOINT_SIMPLE_STATION + SEARCH_ENPOINT;
	String HEADER_RESULT = ENDPOINT_RESULT;
	String HEADER_RESULT_COUNT = HEADER_RESULT + HEADER_DELIMITER + HEADER_COUNT;
	String HEADER_TOTAL_RESULT_COUNT = HEADER_TOTAL + HEADER_DELIMITER + HEADER_RESULT_COUNT;

	String MIME_TYPE_ZIP  = "application/zip";
	String MIME_TYPE_CSV  = "text/csv";
	String MIME_TYPE_TSV  = "text/tab-separated-values";
	String MIME_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	String MIME_TYPE_XML  = MediaType.APPLICATION_XML_VALUE;
	String MIME_TYPE_JSON = MediaType.APPLICATION_JSON_VALUE;
	String MIME_TYPE_KML = "application/vnd.google-earth.kml+xml";
	String MIME_TYPE_KMZ = "application/vnd.google-earth.kmz";
	String MIME_TYPE_GEOJSON = "application/vnd.geo+json";

}