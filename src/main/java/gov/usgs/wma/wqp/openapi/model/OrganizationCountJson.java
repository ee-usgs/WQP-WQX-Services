package gov.usgs.wma.wqp.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.wma.wqp.util.HttpConstants;

public class OrganizationCountJson {

	public static final String HEADER_NWIS_ORGANIZATION_COUNT = StationCountJson.NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ORGANIZATION_COUNT;
	public static final String HEADER_STEWARDS_ORGANIZATION_COUNT = StationCountJson.STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ORGANIZATION_COUNT;
	public static final String HEADER_STORET_ORGANIZATION_COUNT = StationCountJson.STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ORGANIZATION_COUNT;

	@JsonProperty(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT)
	public String totalProjectCount;
	@JsonProperty(HEADER_NWIS_ORGANIZATION_COUNT)
	public String nwisProjectCount;
	@JsonProperty(HEADER_STEWARDS_ORGANIZATION_COUNT)
	public String stewardsProjectCount;
	@JsonProperty(HEADER_STORET_ORGANIZATION_COUNT)
	public String storetProjectCount;

}
