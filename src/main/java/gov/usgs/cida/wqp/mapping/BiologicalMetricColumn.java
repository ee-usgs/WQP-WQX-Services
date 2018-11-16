package gov.usgs.cida.wqp.mapping;

public class BiologicalMetricColumn extends BaseColumn {
	
	

	// NOTE all the database columns are listed here only some are needed.
	// not sure on which columns match these headers
	//BiologicalHabitatIndex
	//IndexType
	//IndexTypeCitation
	
	
	
		// in base column DATA_SOURCE_ID
		// in base column DATA_SOURCE	
		// not needed STATION_ID
	// used but are in BaseColumn public static final String KEY_SITE_ID = "SITE_ID";  MonitoringLocationIdentifier
	// used but are in BaseColumn public static final String KEY_ORGANIZATION = "ORGANIZATION"; OrganizationIdentifier
		// not needed SITE_TYPE
		// not needed HUC
		// not neededGOVERNMENTAL_UNIT_CODE		
	public static final String KEY_INDEX_IDENTIFIER = "INDEX_IDENTIFIER"; //IndexIdentifier
	public static final String KEY_INDEX_TYPE_IDENTIFIER = "TYPE_IDENTIFIER"; //IndexTypeIdentifier
	public static final String KEY_INDEX_TYPE_CONTEXT = "TYPE_CONTEXT"; //IndexTypeIdentifierContext
	public static final String KEY_INDEX_TYPE_NAME = "INDEX_TYPE_NAME"; //IndexTypeName
	public static final String KEY_RESOURCE_TITLE_NAME = "RESOURCE_TITLE_NAME"; //ResourceTitleName
	public static final String KEY_RESOURCE_CREATOR_NAME = "RESOURCE_CREATOR_NAME"; //ResourceCreatorName
	public static final String KEY_RESOURCE_SUBJECT_TEXT = "RESOURCE_SUBJECT_TEXT"; //ResourceSubjectText
	public static final String KEY_RESOURCE_PUBLISHER_NAME = "RESOURCE_PUBLISHER_NAME"; //ResourcePublisherName
	public static final String KEY_RESOURCE_DATE = "RESOURCE_DATE"; //ResourceDate
	public static final String KEY_RESOURCE_IDENTIFIER = "RESOURCE_IDENTIFIER"; //ResourceIdentifier
	public static final String KEY_INDEX_TYPE_SCALE_TEXT = "INDEX_TYPE_SCALE_TEXT"; //IndexTypeScaleText
	public static final String KEY_INDEX_SCORE_NUMERIC = "INDEX_SCORE_NUMERIC"; //IndexScoreNumeric
	public static final String KEY_INDEX_QUALIFIER_CODE = "INDEX_QUALIFIER_CODE"; //IndexQualifierCode
	public static final String KEY_INDEX_COMMENT = "INDEX_COMMENT"; //IndexCommentText		
	public static final String KEY_INDEX_CALCULATED_DATE = "INDEX_CALCULATED_DATE"; //IndexCalculatedDate
	// not needed HUC_2
	// not needed HUC_4
	// not needed HUC_6
	// not needed HUC_8		
	// not needed HUC_10
	// not needed HUC_12
	// not needed COUNTRY_CODE
	// not needed STATE_CODE
	// not needed COUNTY_CODE
	
	//Profile Mapping of the Keys
	public static final ColumnProfile INDEX_IDENTIFIER = new ColumnProfile(KEY_INDEX_IDENTIFIER, Profile.BIOLOGICAL_METRIC);
	public static final ColumnProfile INDEX_TYPE_IDENTIFIER = new ColumnProfile(KEY_INDEX_TYPE_IDENTIFIER, Profile.BIOLOGICAL_METRIC);
	public static final ColumnProfile INDEX_TYPE_CONTEXT = new ColumnProfile(KEY_INDEX_TYPE_CONTEXT, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile INDEX_TYPE_NAME = new ColumnProfile(KEY_INDEX_TYPE_NAME, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile RESOURCE_TITLE_NAME = new ColumnProfile(KEY_RESOURCE_TITLE_NAME, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile RESOURCE_CREATOR_NAME = new ColumnProfile(KEY_RESOURCE_CREATOR_NAME, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile RESOURCE_SUBJECT_TEXT = new ColumnProfile(KEY_RESOURCE_SUBJECT_TEXT, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile RESOURCE_PUBLISHER_NAME = new ColumnProfile(KEY_RESOURCE_PUBLISHER_NAME, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile RESOURCE_DATE = new ColumnProfile(KEY_RESOURCE_DATE, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile RESOURCE_IDENTIFIER = new ColumnProfile(KEY_RESOURCE_IDENTIFIER, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile INDEX_TYPE_SCALE_TEXT = new ColumnProfile(KEY_INDEX_TYPE_SCALE_TEXT, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile INDEX_SCORE_NUMERIC = new ColumnProfile(KEY_INDEX_SCORE_NUMERIC, Profile.BIOLOGICAL_METRIC);			
	public static final ColumnProfile INDEX_QUALIFIER_CODE = new ColumnProfile(KEY_INDEX_QUALIFIER_CODE, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile INDEX_COMMENT = new ColumnProfile(KEY_INDEX_COMMENT, Profile.BIOLOGICAL_METRIC);		
	public static final ColumnProfile INDEX_CALCULATED_DATE = new ColumnProfile(KEY_INDEX_CALCULATED_DATE, Profile.BIOLOGICAL_METRIC);			
			
}
