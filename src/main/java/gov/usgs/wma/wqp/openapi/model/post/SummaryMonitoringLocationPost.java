package gov.usgs.wma.wqp.openapi.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryMonitoringLocationPost extends MonitoringLocationPost {
	@JsonProperty("summaryYears")
	public String summaryYears;
}
