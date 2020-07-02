package gov.usgs.wma.wqp.openapi.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoringLocationPost extends OrganizationPost {

	@JsonProperty("bBox")
	String bBox;
	@JsonProperty("countrycode")
	String[] countrycode;
	@JsonProperty("countycode")
	String[] countycode;
	@JsonProperty("dataProfile")
	String dataProfile;
	@JsonProperty("huc")
	String[] huc;
	@JsonProperty("lat")
	String lat;
	@JsonProperty("long")
	String longitude;
	@JsonProperty("nldiurl")
	String nldiurl;
	@JsonProperty("providers")
	String[] providers;
	@JsonProperty("siteid")
	String[] siteid;
	@JsonProperty("siteType")
	String[] siteType;
	@JsonProperty("statecode")
	String[] statecode;
	@JsonProperty("within")
	String within;

}
