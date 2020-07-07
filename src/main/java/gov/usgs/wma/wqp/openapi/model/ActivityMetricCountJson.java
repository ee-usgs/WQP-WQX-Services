package gov.usgs.wma.wqp.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.wma.wqp.util.HttpConstants;

public class ActivityMetricCountJson extends ActivityCountJson {

	public static final String HEADER_NWIS_ACTIVITY_METRIC_COUNT = NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ACTIVITY_METRIC_COUNT;
	public static final String HEADER_STEWARDS_ACTIVITY_METRIC_COUNT = STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ACTIVITY_METRIC_COUNT;
	public static final String HEADER_STORET_ACTIVITY_METRIC_COUNT = STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_ACTIVITY_METRIC_COUNT;

	@JsonProperty(HttpConstants.HEADER_TOTAL_ACTIVITY_METRIC_COUNT)
	public String totalActivityMetricCount;
	@JsonProperty(HEADER_NWIS_ACTIVITY_METRIC_COUNT)
	public String nwisActivityMetricCount;
	@JsonProperty(HEADER_STEWARDS_ACTIVITY_METRIC_COUNT)
	public String stewardsActivityMetricCount;
	@JsonProperty(HEADER_STORET_ACTIVITY_METRIC_COUNT)
	public String storetActivityMetricCount;

}
