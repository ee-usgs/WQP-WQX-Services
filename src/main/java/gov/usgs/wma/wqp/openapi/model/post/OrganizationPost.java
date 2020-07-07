package gov.usgs.wma.wqp.openapi.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrganizationPost extends BasePostParams {
	@JsonProperty("organization")
	public String[] organization;
}
