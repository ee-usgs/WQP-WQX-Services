package gov.usgs.cida.wqp.validation;

public interface ValidationConstants { // TODO these should be defaults with WqpConfig and wqpgateway.properties overrides
	
//	int UNBOUNDED          = Integer.MAX_VALUE;
	int IN_CLAUSE_LIMIT    = 1000;
	int DEFAULT_MIN_OCCURS = 0;
	int DEFAULT_MAX_OCCURS = 1;
	
	double MAX_LATITUDE	=   90;
	double MIN_LATITUDE	=  -90;
	double MAX_LONGITUDE=  180;
	double MIN_LONGITUDE= -180;
	
	String DEFAULT_DELIMITER    = ";"; // parameter delimiter
	String FORMAT_DATE			= "MM-dd-yyyy";
	String REGEX_FIPS_COUNTRY	= "[A-Z]{2}";
	String REGEX_FIPS_STATE		= "(?:([A-Z]{2}):)?([0-9]{1,2})";
	String REGEX_FIPS_COUNTY	= "(?:([A-Z]{2}):)?([0-9]{1,2}):([0-9]{3}|N/A)";
	String REGEX_SITEID			= "[\\w]+\\-.+\\S";
	String REGEX_HUC			= "(?:[0-9]{8})|(?:(?:[0-9]{2}){1,3}\\*?)";
	String REGEX_PCODE			= "[0-9]{5}";
	String REGEX_MIMETYPES		= "csv|tsv|tab|xlsx|xml|kml|kmz|json"; // TODO refine
	String REGEX_AVOID			= "NWIS|STORET";
	String REGEX_ZIP			= "yes|no";
	String REGEX_ANALYTICAL_METHOD	= ".+";
	String REGEX_HUC_WILDCARD_IN	= "\\*";
	String REGEX_HUC_WILDCARD_OUT	= "";
}
