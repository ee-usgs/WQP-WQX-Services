package gov.usgs.cida.wqp.mapping;

public abstract class BaseColumn {

	//ResultSet Keys
	public static final String KEY_DATA_SOURCE_ID = "data_source_id";
	public static final String KEY_DATA_SOURCE = "data_source";
	public static final String KEY_ORGANIZATION = "organization";
	public static final String KEY_ORGANIZATION_NAME = "organization_name";
	public static final String KEY_STATION_ID = "station_id";
	public static final String KEY_SITE_ID = "site_id";
	public static final String KEY_HUC = "huc";
	public static final String KEY_GOVERNMENTAL_UNIT_CODE = "governmental_unit_code";
	public static final String KEY_ACTIVITY_COUNT = "activity_count";
	public static final String KEY_RESULT_COUNT = "result_count";
	public static final String KEY_LAST_UPDATED = "last_updated";
	public static final String KEY_LAST_RESULT = "last_result";
	public static final String KEY_SITE_COUNT = "site_count";	

	//Profile Mapping of the Keys
	public static final ColumnProfile DATA_SOURCE = new ColumnProfile(KEY_DATA_SOURCE, Profile.STATION, Profile.PC_RESULT, Profile.BIOLOGICAL, Profile.SIMPLE_STATION, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.ACTIVITY_METRIC, Profile.RES_DETECT_QNT_LMT, Profile.NARROW_RESULT, Profile.PROJECT, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING, Profile.ORGANIZATION, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD, Profile.PERIOD_OF_RECORD);

	public static final ColumnProfile ORGANIZATION = new ColumnProfile(KEY_ORGANIZATION, Profile.STATION, Profile.PC_RESULT, Profile.BIOLOGICAL, Profile.SIMPLE_STATION, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.ACTIVITY_METRIC, Profile.RES_DETECT_QNT_LMT, Profile.NARROW_RESULT, Profile.PROJECT, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING, Profile.ORGANIZATION, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD, Profile.PERIOD_OF_RECORD);

	public static final ColumnProfile ORGANIZATION_NAME = new ColumnProfile(KEY_ORGANIZATION_NAME, Profile.STATION, Profile.PC_RESULT, Profile.BIOLOGICAL, Profile.SIMPLE_STATION, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.ACTIVITY_METRIC, Profile.RES_DETECT_QNT_LMT, Profile.NARROW_RESULT, Profile.PROJECT, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING, Profile.ORGANIZATION, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD, Profile.PERIOD_OF_RECORD);

	public static final ColumnProfile SITE_ID = new ColumnProfile(KEY_SITE_ID, Profile.STATION, Profile.PC_RESULT, Profile.BIOLOGICAL, Profile.SIMPLE_STATION, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.ACTIVITY_METRIC, Profile.RES_DETECT_QNT_LMT, Profile.NARROW_RESULT, Profile.PROJECT_MONITORING_LOCATION_WEIGHTING, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD, Profile.PERIOD_OF_RECORD);

	public static final ColumnProfile RESULT_COUNT = new ColumnProfile(KEY_RESULT_COUNT, Profile.ACTIVITY_ALL);

	public static final ColumnProfile LAST_UPDATED = new ColumnProfile(KEY_LAST_UPDATED, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

}
