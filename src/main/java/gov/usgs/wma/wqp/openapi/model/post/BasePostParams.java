package gov.usgs.wma.wqp.openapi.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BasePostParams {

	@JsonProperty("mimeType")
	String mimeType;
	@JsonProperty("zip")
	String zip;

}
