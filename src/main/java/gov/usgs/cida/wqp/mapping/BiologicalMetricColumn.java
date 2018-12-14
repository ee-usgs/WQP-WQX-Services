package gov.usgs.cida.wqp.mapping;

public class BiologicalMetricColumn extends BaseColumn {
	
	public static final String KEY_INDEX_IDENTIFIER = "index_identifier";
	public static final String KEY_INDEX_TYPE_IDENTIFIER = "index_type_identifier";
	public static final String KEY_INDEX_TYPE_CONTEXT = "index_type_context";
	public static final String KEY_INDEX_TYPE_NAME = "index_type_name";
	public static final String KEY_RESOURCE_TITLE_NAME = "resource_title_name";
	public static final String KEY_RESOURCE_CREATOR_NAME = "resource_creator_name";
	public static final String KEY_RESOURCE_SUBJECT_TEXT = "resource_subject_text";
	public static final String KEY_RESOURCE_PUBLISHER_NAME = "resource_publisher_name";
	public static final String KEY_RESOURCE_DATE = "resource_date";
	public static final String KEY_RESOURCE_IDENTIFIER = "resource_identifier";
	public static final String KEY_INDEX_TYPE_SCALE_TEXT = "index_type_scale_text";
	public static final String KEY_INDEX_SCORE_NUMERIC = "index_score_numeric";
	public static final String KEY_INDEX_QUALIFIER_CODE = "index_qualifier_code";
	public static final String KEY_INDEX_COMMENT = "index_comment";		
	public static final String KEY_INDEX_CALCULATED_DATE = "index_calculated_date";
	
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
