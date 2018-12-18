package gov.usgs.cida.wqp.mapping;

public class ActivityMetricColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_METRIC_TYPE_IDENTIFIER = "type_identifier";
	public static final String KEY_METRIC_TYPE_CONTEXT = "identifier_context";
	public static final String KEY_METRIC_TYPE_NAME = "type_name";
	public static final String KEY_METRIC_CITATION_TITLE = "resource_title";
	public static final String KEY_METRIC_CITATION_CREATOR = "resource_creator";
	public static final String KEY_METRIC_CITATION_SUBJECT = "resource_subject";
	public static final String KEY_METRIC_CITATION_PUBLISHER = "resource_publisher";
	public static final String KEY_METRIC_CITATION_DATE = "resource_date";
	public static final String KEY_METRIC_CITATION_ID = "resource_identifier";
	public static final String KEY_METRIC_TYPE_SCALE = "type_scale";
	public static final String KEY_FORMULA_DESCRIPTION = "formula_description";
	public static final String KEY_ACTIVITY_METRIC_VALUE = "measure_value";
	public static final String KEY_ACTIVITY_METRIC_UNIT = "unit_code";
	public static final String KEY_ACTIVITY_METRIC_SCORE = "score";
	public static final String KEY_ACTIVITY_METRIC_COMMENT = "comment_text";
	public static final String KEY_INDEX_IDENTIFIER = "index_identifier";

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
