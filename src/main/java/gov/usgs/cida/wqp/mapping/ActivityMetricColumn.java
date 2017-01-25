package gov.usgs.cida.wqp.mapping;

public class ActivityMetricColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_METRIC_TYPE_IDENTIFIER = "TYPE_IDENTIFIER";
	public static final String KEY_METRIC_TYPE_CONTEXT = "IDENTIFIER_CONTEXT";
	public static final String KEY_METRIC_TYPE_NAME = "TYPE_NAME";
	public static final String KEY_METRIC_CITATION_TITLE = "RESOURCE_TITLE";
	public static final String KEY_METRIC_CITATION_CREATOR = "RESOURCE_CREATOR";
	public static final String KEY_METRIC_CITATION_SUBJECT = "RESOURCE_SUBJECT";
	public static final String KEY_METRIC_CITATION_PUBLISHER = "RESOURCE_PUBLISHER";
	public static final String KEY_METRIC_CITATION_DATE = "RESOURCE_DATE";
	public static final String KEY_METRIC_CITATION_ID = "RESOURCE_IDENTIFIER";
	public static final String KEY_METRIC_TYPE_SCALE = "TYPE_SCALE";
	public static final String KEY_FORMULA_DESCRIPTION = "FORMULA_DESCRIPTION";
	public static final String KEY_ACTIVITY_METRIC_VALUE = "MEASURE_VALUE";
	public static final String KEY_ACTIVITY_METRIC_UNIT = "UNIT_CODE";
	public static final String KEY_ACTIVITY_METRIC_SCORE = "SCORE";
	public static final String KEY_ACTIVITY_METRIC_COMMENT = "COMMENT_TEXT";
	public static final String KEY_INDEX_IDENTIFIER = "INDEX_IDENTIFIER";

	//Profile Mapping of the Keys
	public static final ColumnProfile METRIC_TYPE_IDENTIFIER = new ColumnProfile(KEY_METRIC_TYPE_IDENTIFIER, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile METRIC_TYPE_CONTEXT = new ColumnProfile(KEY_METRIC_TYPE_CONTEXT, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile METRIC_TYPE_NAME = new ColumnProfile(KEY_METRIC_TYPE_NAME, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile METRIC_CITATION_TITLE = new ColumnProfile(KEY_METRIC_CITATION_TITLE, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile METRIC_CITATION_CREATOR = new ColumnProfile(KEY_METRIC_CITATION_CREATOR, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile METRIC_CITATION_SUBJECT = new ColumnProfile(KEY_METRIC_CITATION_SUBJECT, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile METRIC_CITATION_PUBLISHER = new ColumnProfile(KEY_METRIC_CITATION_PUBLISHER, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile METRIC_CITATION_DATE = new ColumnProfile(KEY_METRIC_CITATION_DATE, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile METRIC_CITATION_ID = new ColumnProfile(KEY_METRIC_CITATION_ID, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile METRIC_TYPE_SCALE = new ColumnProfile(KEY_METRIC_TYPE_SCALE, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile FORMULA_DESCRIPTION = new ColumnProfile(KEY_FORMULA_DESCRIPTION, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile ACTIVITY_METRIC_VALUE = new ColumnProfile(KEY_ACTIVITY_METRIC_VALUE, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile ACTIVITY_METRIC_UNIT = new ColumnProfile(KEY_ACTIVITY_METRIC_UNIT, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile ACTIVITY_METRIC_SCORE = new ColumnProfile(KEY_ACTIVITY_METRIC_SCORE, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile ACTIVITY_METRIC_COMMENT = new ColumnProfile(KEY_ACTIVITY_METRIC_COMMENT, Profile.ACTIVITY_METRIC);
	public static final ColumnProfile INDEX_IDENTIFIER = new ColumnProfile(KEY_INDEX_IDENTIFIER, Profile.ACTIVITY_METRIC);

	private ActivityMetricColumn() {
	}

}
