package gov.usgs.wma.wqp.openapi.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryOrganizationPost extends BasePostParams {
	@JsonProperty("organization")
	public String[] organization;
	@JsonProperty("summaryYears")
	public String summaryYears;
}
