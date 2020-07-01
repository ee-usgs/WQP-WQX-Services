package gov.usgs.wma.wqp.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.wma.wqp.util.HttpConstants;

public class ProjectMonitoringLocationWeightingCountJson {

	public static final String HEADER_NWIS_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT = StationCountJson.NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT;
	public static final String HEADER_STEWARDS_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT = StationCountJson.STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT;
	public static final String HEADER_STORET_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT = StationCountJson.STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT;

	@JsonProperty(HttpConstants.HEADER_TOTAL_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT)
	public String totalProjectCount;
	@JsonProperty(HEADER_NWIS_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT)
	public String nwisProjectCount;
	@JsonProperty(HEADER_STEWARDS_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT)
	public String stewardsProjectCount;
	@JsonProperty(HEADER_STORET_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT)
	public String storetProjectCount;

}
