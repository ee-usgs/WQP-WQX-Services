package gov.usgs.wma.wqp.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.wma.wqp.util.HttpConstants;

public class ResultCountJson extends ActivityCountJson {

	public static final String HEADER_NWIS_RESULT_COUNT = NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RESULT_COUNT;
	public static final String HEADER_STEWARDS_RESULT_COUNT = STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RESULT_COUNT;
	public static final String HEADER_STORET_RESULT_COUNT = STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RESULT_COUNT;
	public static final String HEADER_BIODATA_RESULT_COUNT = BIODATA + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_RESULT_COUNT;

	@JsonProperty(HttpConstants.HEADER_TOTAL_RESULT_COUNT)
	String totalResultCount;
	@JsonProperty(HEADER_NWIS_RESULT_COUNT)
	String nwisResultCount;
	@JsonProperty(HEADER_STEWARDS_RESULT_COUNT)
	String stewardsResultCount;
	@JsonProperty(HEADER_STORET_RESULT_COUNT)
	String storetResultCount;
	@JsonProperty(HEADER_BIODATA_RESULT_COUNT)
	String biodataResultCount;

}
