package gov.usgs.wma.wqp.mapping;

public class ProjectMonitoringLocationWeightingColumn extends BaseColumn {
	
	//ResultSet Keys
	public static final String KEY_PRJMLW_ID = "prjmlw_id";
	public static final String KEY_PRJMLW_VALUE = "measure_value";
	public static final String KEY_PRJMLW_UNIT = "unit_code";
	public static final String KEY_PRJMLW_COMMENT = "comment_text";
	public static final String KEY_PRJMLW_STATISTICAL_STRATUM = "statistical_stratum";
	public static final String KEY_PRJMLW_LOCATION_CATEGORY = "location_category";
	public static final String KEY_PRJMLW_LOCATION_STATUS = "location_status";
	public static final String KEY_PRJMLW_REFERENCE_LOCATION_TYPE_CODE = "ref_location_type_code";
	public static final String KEY_PRJMLW_REFERENCE_LOCATION_START_DATE = "ref_location_start_date";
	public static final String KEY_PRJMLW_REFERENCE_LOCATION_END_DATE = "ref_location_end_date";
	public static final String KEY_REFERENCE_LOCATION_CITATION_TITLE = "resource_title";
	public static final String KEY_REFERENCE_LOCATION_CITATION_CREATOR = "resource_creator";
	public static final String KEY_REFERENCE_LOCATION_CITATION_SUBJECT = "resource_subject";
	public static final String KEY_REFERENCE_LOCATION_CITATION_PUBLISHER = "resource_publisher";
	public static final String KEY_REFERENCE_LOCATION_CITATION_DATE = "resource_date";
	public static final String KEY_REFERENCE_LOCATION_CITATION_IDENTIFIER = "resource_identifier";
	
	//Profile Mapping of the Keys
	public static final ColumnProfile PRJMLW_ID = new ColumnProfile(KEY_PRJMLW_ID, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PRJMLW_VALUE = new ColumnProfile(KEY_PRJMLW_VALUE, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PRJMLW_UNIT = new ColumnProfile(KEY_PRJMLW_UNIT, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PRJMLW_COMMENT = new ColumnProfile(KEY_PRJMLW_COMMENT, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PRJMLW_STATISTICAL_STRATUM = new ColumnProfile(KEY_PRJMLW_STATISTICAL_STRATUM, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PRJMLW_LOCATION_CATEGORY = new ColumnProfile(KEY_PRJMLW_LOCATION_CATEGORY, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PRJMLW_LOCATION_STATUS = new ColumnProfile(KEY_PRJMLW_LOCATION_STATUS, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PRJMLW_REFERENCE_LOCATION_TYPE_CODE = new ColumnProfile(KEY_PRJMLW_REFERENCE_LOCATION_TYPE_CODE, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PRJMLW_REFERENCE_LOCATION_START_DATE = new ColumnProfile(KEY_PRJMLW_REFERENCE_LOCATION_START_DATE, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PRJMLW_REFERENCE_LOCATION_END_DATE = new ColumnProfile(KEY_PRJMLW_REFERENCE_LOCATION_END_DATE, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile REFERENCE_LOCATION_CITATION_TITLE = new ColumnProfile(KEY_REFERENCE_LOCATION_CITATION_TITLE, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile REFERENCE_LOCATION_CITATION_CREATOR = new ColumnProfile(KEY_REFERENCE_LOCATION_CITATION_CREATOR, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile REFERENCE_LOCATION_CITATION_SUBJECT = new ColumnProfile(KEY_REFERENCE_LOCATION_CITATION_SUBJECT, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile REFERENCE_LOCATION_CITATION_PUBLISHER = new ColumnProfile(KEY_REFERENCE_LOCATION_CITATION_PUBLISHER, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile REFERENCE_LOCATION_CITATION_DATE = new ColumnProfile(KEY_REFERENCE_LOCATION_CITATION_DATE, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile REFERENCE_LOCATION_CITATION_IDENTIFIER = new ColumnProfile(KEY_REFERENCE_LOCATION_CITATION_IDENTIFIER, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	
	private ProjectMonitoringLocationWeightingColumn() {
	}
}
