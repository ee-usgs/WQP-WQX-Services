package gov.usgs.cida.wqp.mapping;

public abstract class BaseColumn {

	//ResultSet Keys
	public static final String KEY_DATA_SOURCE_ID = "DATA_SOURCE_ID";
	public static final String KEY_DATA_SOURCE = "data_source";
	public static final String KEY_ORGANIZATION = "ORGANIZATION";
	public static final String KEY_ORGANIZATION_NAME = "ORGANIZATION_NAME";
	public static final String KEY_STATION_ID = "STATION_ID";
	public static final String KEY_SITE_ID = "SITE_ID";
	public static final String KEY_HUC = "HUC";
	public static final String KEY_GOVERNMENTAL_UNIT_CODE = "GOVERNMENTAL_UNIT_CODE";
	public static final String KEY_ACTIVITY_COUNT = "ACTIVITY_COUNT";
	public static final String KEY_RESULT_COUNT = "RESULT_COUNT";
	public static final String KEY_LAST_UPDATED = "LAST_UPDATED";
	public static final String KEY_LAST_RESULT = "LAST_RESULT";
	public static final String KEY_SITE_COUNT = "SITE_COUNT";	

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
