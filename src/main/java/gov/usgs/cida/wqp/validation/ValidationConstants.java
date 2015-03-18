package gov.usgs.cida.wqp.validation;
public interface ValidationConstants {
	public static final String DEFAULT_DELIMITER = ";";
	public static final int UNBOUNDED = Integer.MAX_VALUE;
	public static final int IN_CLAUSE_LIMIT = 1000;
	public static final int DEFAULT_MIN_OCCURS = 0;
	public static final int DEFAULT_MAX_OCCURS = 1;
	public static final double MAX_LATITUDE	=   90;
	public static final double MIN_LATITUDE	=  -90;
	public static final double MAX_LONGITUDE=  180;
	public static final double MIN_LONGITUDE= -180;
	public static final String REGEX_FIPS_COUNTRY	= "[A-Z]{2}";
	public static final String REGEX_FIPS_STATE		= "(?:([A-Z]{2}):)?([0-9]{1,2})";
	public static final String REGEX_FIPS_COUNTY	= "(?:([A-Z]{2}):)?([0-9]{1,2}):([0-9]{3}|N/A)";
	public static final String REGEX_SITEID			= "[\\w]+\\-.+\\S";
	public static final String REGEX_HUC			= "(?:[0-9]{8})|(?:[0-9]{2,7}\\*)";
	public static final String REGEX_PCODE			= "[0-9]{5}";
	public static final String REGEX_MIMETYPES		= "csv|tsv|xlsx|xml|kml"; // TODO refine
	public static final String REGEX_PROVIDERS		= "NWIS|STORAT|STEWARDS"; // TODO refine
	public static final String FORMAT_DATE			= "MM-dd-yyyy";
	public static final String REGEX_ACTIVITY_ID	= ".+";
	public static final String REGEX_ANALYTICAL_METHOD	= ".+";
	public static final String REGEX_HUC_WILDCARD_IN	= "\\*";
	public static final String REGEX_HUC_WILDCARD_OUT	= "%";
}