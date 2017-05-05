package gov.usgs.cida.wqp.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.cida.wqp.util.HttpConstants;

public class StationCountJson {

	public static final String NWIS = "NWIS";
	public static final String STORET = "STORET";
	public static final String STEWARDS = "STEWARDS";
	public static final String BIODATA = "BIODATA";
	public static final String HEADER_NWIS_SITE_COUNT = NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_SITE_COUNT;
	public static final String HEADER_STEWARDS_SITE_COUNT = STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_SITE_COUNT;
	public static final String HEADER_STORET_SITE_COUNT = STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_SITE_COUNT;
	public static final String HEADER_BIODATA_SITE_COUNT = BIODATA + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_SITE_COUNT;

	@JsonProperty(HttpConstants.HEADER_TOTAL_SITE_COUNT)
	String totalSiteCount;
	@JsonProperty(HEADER_NWIS_SITE_COUNT)
	String nwisSiteCount;
	@JsonProperty(HEADER_STEWARDS_SITE_COUNT)
	String stewardsSiteCount;
	@JsonProperty(HEADER_STORET_SITE_COUNT)
	String storetSiteCount;
	@JsonProperty(HEADER_BIODATA_SITE_COUNT)
	String biodataSiteCount;

}
