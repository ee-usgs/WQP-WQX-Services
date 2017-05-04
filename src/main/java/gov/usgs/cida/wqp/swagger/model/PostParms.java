package gov.usgs.cida.wqp.swagger.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostParms {

	@JsonProperty("analyticalmethod")
	List<String> analyticalmethod;
	@JsonProperty("bBox")
	String bBox;
	@JsonProperty("assemblage")
	List<String> assemblage;
	@JsonProperty("characteristicName")
	List<String> characteristicName;
	@JsonProperty("characteristicType")
	List<String> characteristicType;
	@JsonProperty("countrycode")
	List<String> countrycode;
	@JsonProperty("countycode")
	List<String> countycode;
	@JsonProperty("dataProfile")
	String dataProfile;
	@JsonProperty("huc")
	List<String> huc;
	@JsonProperty("lat")
	String lat;
	@JsonProperty("long")
	String longitude;
	@JsonProperty("minactivities")
	String minactivities;
	@JsonProperty("minresults")
	String minresults;
	@JsonProperty("organization")
	List<String> organization;
	@JsonProperty("pCode")
	List<String> pCode;
	@JsonProperty("project")
	List<String> project;
	@JsonProperty("sampleMedia")
	List<String> sampleMedia;
	@JsonProperty("providers")
	List<String> providers;
	@JsonProperty("siteid")
	List<String> siteid;
	@JsonProperty("siteType")
	List<String> siteType;
	@JsonProperty("statecode")
	List<String> statecode;
	@JsonProperty("startDateHi")
	String startDateHi;
	@JsonProperty("startDateLo")
	String startDateLo;
	@JsonProperty("subjectTaxonomicName")
	List<String> subjectTaxonomicName;
	@JsonProperty("nldiurl")
	String nldiurl;
	@JsonProperty("within")
	String within;

}
