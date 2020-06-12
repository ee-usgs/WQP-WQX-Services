package gov.usgs.wma.wqp.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import gov.usgs.wma.wqp.util.HttpConstants;

public class BiologicalMetricCountJson extends StationCountJson {

	public static final String HEADER_NWIS_BIOLOGICAL_METRIC_COUNT = NWIS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_BIOLOGICAL_METRIC_COUNT;
	public static final String HEADER_STEWARDS_BIOLOGICAL_METRIC_COUNT = STEWARDS + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_BIOLOGICAL_METRIC_COUNT;
	public static final String HEADER_STORET_BIOLOGICAL_METRIC_COUNT = STORET + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_BIOLOGICAL_METRIC_COUNT;
	public static final String HEADER_BIODATA_BIOLOGICAL_METRIC_COUNT = BIODATA + HttpConstants.HEADER_DELIMITER + HttpConstants.HEADER_BIOLOGICAL_METRIC_COUNT;

	@JsonProperty(HttpConstants.HEADER_TOTAL_BIOLOGICAL_METRIC_COUNT)
	String totalBiologicalMetricCount;
	@JsonProperty(HEADER_NWIS_BIOLOGICAL_METRIC_COUNT)
	String nwisBiologicalMetricCount;
	@JsonProperty(HEADER_STEWARDS_BIOLOGICAL_METRIC_COUNT)
	String stewardsBiologicalMetricCount;
	@JsonProperty(HEADER_STORET_BIOLOGICAL_METRIC_COUNT)
	String storetBiologicalMetricCount;
	@JsonProperty(HEADER_BIODATA_BIOLOGICAL_METRIC_COUNT)
	String biodataBiologicalMetricCount;

}
