package gov.usgs.cida.wqp.mapping;

public class ProjectColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_PROJECT_ID = "project_id";
	public static final String KEY_PROJECT_IDENTIFIER = "project_identifier";
	public static final String KEY_PROJECT_NAME = "project_name";
	public static final String KEY_PROJECT_DESCRIPTION = "description";
	public static final String KEY_SAMPLING_DESIGN_TYPE_CODE = "sampling_design_type_code";
	public static final String KEY_QAPP_APPROVED_INDICATOR = "qapp_approved_indicator";
	public static final String KEY_QAPP_APPROVAL_AGENCY_NAME = "qapp_approval_agency_name";
	public static final String KEY_PROJECT_FILE_URL = "project_file_url";
	public static final String KEY_MONITORING_LOCATION_WEIGHT_URL = "monitoring_location_weight_url";

	//Profile Mapping of the Keys
	public static final ColumnProfile PROJECT_ID = new ColumnProfile(KEY_PROJECT_ID, Profile.PROJECT, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PROJECT_IDENTIFIER = new ColumnProfile(KEY_PROJECT_IDENTIFIER, Profile.PROJECT, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING);
	public static final ColumnProfile PROJECT_NAME = new ColumnProfile(KEY_PROJECT_NAME, Profile.PROJECT, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile PROJECT_DESCRIPTION = new ColumnProfile(KEY_PROJECT_DESCRIPTION, Profile.PROJECT);
	public static final ColumnProfile SAMPLING_DESIGN_TYPE_CODE = new ColumnProfile(KEY_SAMPLING_DESIGN_TYPE_CODE, Profile.PROJECT);
	public static final ColumnProfile QAPP_APPROVED_INDICATOR = new ColumnProfile(KEY_QAPP_APPROVED_INDICATOR, Profile.PROJECT);
	public static final ColumnProfile QAPP_APPROVAL_AGENCY_NAME = new ColumnProfile(KEY_QAPP_APPROVAL_AGENCY_NAME, Profile.PROJECT);
	public static final ColumnProfile PROJECT_FILE_URL = new ColumnProfile(KEY_PROJECT_FILE_URL, Profile.PROJECT);
	public static final ColumnProfile MONITORING_LOCATION_WEIGHT_URL = new ColumnProfile(KEY_MONITORING_LOCATION_WEIGHT_URL, Profile.PROJECT);

	private ProjectColumn () {
	}
}
