package gov.usgs.wma.wqp.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.wma.wqp.util.HttpConstants;

public class ActivityCountJson extends StationCountJson {

	public static final String HEADER_NWIS_ACTIVITY_COUNT = NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ACTIVITY_COUNT;
	public static final String HEADER_STEWARDS_ACTIVITY_COUNT = STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ACTIVITY_COUNT;
	public static final String HEADER_STORET_ACTIVITY_COUNT = STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ACTIVITY_COUNT;
	public static final String HEADER_BIODATA_ACTIVITY_COUNT = BIODATA + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ACTIVITY_COUNT;

	@JsonProperty(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT)
	String totalActivityCount;
	@JsonProperty(HEADER_NWIS_ACTIVITY_COUNT)
	String nwisActivityCount;
	@JsonProperty(HEADER_STEWARDS_ACTIVITY_COUNT)
	String stewardsActivityCount;
	@JsonProperty(HEADER_STORET_ACTIVITY_COUNT)
	String storetActivityCount;
	@JsonProperty(HEADER_BIODATA_ACTIVITY_COUNT)
	String biodataActivityCount;

}
