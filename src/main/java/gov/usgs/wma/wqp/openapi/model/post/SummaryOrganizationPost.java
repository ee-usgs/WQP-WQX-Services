package gov.usgs.wma.wqp.openapi.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryOrganizationPost extends OrganizationPost {
	@JsonProperty("summaryYears")
	public String summaryYears;
}
