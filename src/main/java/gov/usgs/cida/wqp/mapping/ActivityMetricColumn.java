package gov.usgs.cida.wqp.mapping;

public class ActivityMetricColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_METRIC_TYPE_IDENTIFIER = "METRIC_TYPE_IDENTIFIER";
	public static final String KEY_METRIC_TYPE_CONTEXT = "METRIC_TYPE_CONTEXT";
	public static final String KEY_METRIC_TYPE_NAME = "METRIC_TYPE_NAME";
	public static final String KEY_METRIC_CITATION_TITLE = "METRIC_CITATION_TITLE";
	public static final String KEY_METRIC_CITATION_CREATOR = "METRIC_CITATION_CREATOR";
	public static final String KEY_METRIC_CITATION_SUBJECT = "METRIC_CITATION_SUBJECT";
	public static final String KEY_METRIC_CITATION_PUBLISHER = "METRIC_CITATION_PUBLISHER";
	public static final String KEY_METRIC_CITATION_DATE = "METRIC_CITATION_DATE";
	public static final String KEY_METRIC_CITATION_ID = "METRIC_CITATION_ID";
	public static final String KEY_METRIC_TYPE_SCALE = "METRIC_TYPE_SCALE";
	public static final String KEY_FORMULA_DESCRIPTION = "FORMULA_DESCRIPTION";
	public static final String KEY_ACTIVITY_METRIC_VALUE = "ACTIVITY_METRIC_VALUE";
	public static final String KEY_ACTIVITY_METRIC_UNIT = "ACTIVITY_METRIC_UNIT";
	public static final String KEY_ACTIVITY_METRIC_SCORE = "ACTIVITY_METRIC_SCORE";
	public static final String KEY_ACTIVITY_METRIC_COMMENT = "ACTIVITY_METRIC_COMMENT";
	public static final String KEY_INDEX_IDENTIFIER = "INDEX_IDENTIFIER";

	//Profile Mapping of the Keys
	public static final ColumnProfile METRIC_TYPE_IDENTIFIER = new ColumnProfile(KEY_METRIC_TYPE_IDENTIFIER);
	public static final ColumnProfile METRIC_TYPE_CONTEXT = new ColumnProfile(KEY_METRIC_TYPE_CONTEXT);
	public static final ColumnProfile METRIC_TYPE_NAME = new ColumnProfile(KEY_METRIC_TYPE_NAME);
	public static final ColumnProfile METRIC_CITATION_TITLE = new ColumnProfile(KEY_METRIC_CITATION_TITLE);
	public static final ColumnProfile METRIC_CITATION_CREATOR = new ColumnProfile(KEY_METRIC_CITATION_CREATOR);
	public static final ColumnProfile METRIC_CITATION_SUBJECT = new ColumnProfile(KEY_METRIC_CITATION_SUBJECT);
	public static final ColumnProfile METRIC_CITATION_PUBLISHER = new ColumnProfile(KEY_METRIC_CITATION_PUBLISHER);
	public static final ColumnProfile METRIC_CITATION_DATE = new ColumnProfile(KEY_METRIC_CITATION_DATE);
	public static final ColumnProfile METRIC_CITATION_ID = new ColumnProfile(KEY_METRIC_CITATION_ID);
	public static final ColumnProfile METRIC_TYPE_SCALE = new ColumnProfile(KEY_METRIC_TYPE_SCALE);
	public static final ColumnProfile FORMULA_DESCRIPTION = new ColumnProfile(KEY_FORMULA_DESCRIPTION);
	public static final ColumnProfile ACTIVITY_METRIC_VALUE = new ColumnProfile(KEY_ACTIVITY_METRIC_VALUE);
	public static final ColumnProfile ACTIVITY_METRIC_UNIT = new ColumnProfile(KEY_ACTIVITY_METRIC_UNIT);
	public static final ColumnProfile ACTIVITY_METRIC_SCORE = new ColumnProfile(KEY_ACTIVITY_METRIC_SCORE);
	public static final ColumnProfile ACTIVITY_METRIC_COMMENT = new ColumnProfile(KEY_ACTIVITY_METRIC_COMMENT);
	public static final ColumnProfile INDEX_IDENTIFIER = new ColumnProfile(KEY_INDEX_IDENTIFIER);

	private ActivityMetricColumn() {
	}

}
