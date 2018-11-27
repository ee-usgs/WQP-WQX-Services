package gov.usgs.cida.wqp.mapping;

public class BiologicalMetricColumn extends BaseColumn {
	
	public static final String KEY_INDEX_IDENTIFIER = "INDEX_IDENTIFIER";
	public static final String KEY_INDEX_TYPE_IDENTIFIER = "INDEX_TYPE_IDENTIFIER";
	public static final String KEY_INDEX_TYPE_CONTEXT = "INDEX_TYPE_CONTEXT";
	public static final String KEY_INDEX_TYPE_NAME = "INDEX_TYPE_NAME";
	public static final String KEY_RESOURCE_TITLE_NAME = "RESOURCE_TITLE_NAME";
	public static final String KEY_RESOURCE_CREATOR_NAME = "RESOURCE_CREATOR_NAME";
	public static final String KEY_RESOURCE_SUBJECT_TEXT = "RESOURCE_SUBJECT_TEXT";
	public static final String KEY_RESOURCE_PUBLISHER_NAME = "RESOURCE_PUBLISHER_NAME";
	public static final String KEY_RESOURCE_DATE = "RESOURCE_DATE";
	public static final String KEY_RESOURCE_IDENTIFIER = "RESOURCE_IDENTIFIER";
	public static final String KEY_INDEX_TYPE_SCALE_TEXT = "INDEX_TYPE_SCALE_TEXT";
	public static final String KEY_INDEX_SCORE_NUMERIC = "INDEX_SCORE_NUMERIC";
	public static final String KEY_INDEX_QUALIFIER_CODE = "INDEX_QUALIFIER_CODE";
	public static final String KEY_INDEX_COMMENT = "INDEX_COMMENT";		
	public static final String KEY_INDEX_CALCULATED_DATE = "INDEX_CALCULATED_DATE";
	
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
