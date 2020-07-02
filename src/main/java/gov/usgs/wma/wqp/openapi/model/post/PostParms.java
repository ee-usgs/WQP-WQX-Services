package gov.usgs.wma.wqp.openapi.model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostParms extends MonitoringLocationPost {

	@JsonProperty("analyticalmethod")
	String[] analyticalmethod;
	@JsonProperty("assemblage")
	String[] assemblage;
	@JsonProperty("characteristicName")
	String[] characteristicName;
	@JsonProperty("characteristicType")
	String[] characteristicType;
	@JsonProperty("minactivities")
	String minactivities;
	@JsonProperty("minresults")
	String minresults;
	@JsonProperty("pCode")
	String[] pCode;
	@JsonProperty("project")
	String[] project;
	@JsonProperty("sampleMedia")
	String[] sampleMedia;
	@JsonProperty("startDateHi")
	String startDateHi;
	@JsonProperty("startDateLo")
	String startDateLo;
	@JsonProperty("subjectTaxonomicName")
	String[] subjectTaxonomicName;

}
