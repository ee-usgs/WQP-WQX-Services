package gov.usgs.cida.wqp.mapping;

public class StationColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_STATION_NAME = "station_name";
	public static final String KEY_MONITORING_LOCATION_TYPE = "station_type_name";
	public static final String KEY_MONITORING_LOCATION_DESCRIPTION = "description_text";
	public static final String KEY_HUC_8 = "huc_8";
	public static final String KEY_HUC_12 = "huc_12";
	public static final String KEY_CONTRIB_DRAIN_AREA_VALUE = "contrib_drain_area_value";
	public static final String KEY_CONTRIB_DRAIN_AREA_UNIT = "contrib_drain_area_unit";
	public static final String KEY_DRAIN_AREA_VALUE = "drain_area_value";
	public static final String KEY_DRAIN_AREA_UNIT = "drain_area_unit";
	public static final String KEY_LATITUDE  = "latitude";
	public static final String KEY_LONGITUDE = "longitude";
	public static final String KEY_SITE_TYPE = "site_type";
	public static final String KEY_SOURCE_MAP_SCALE = "map_scale";
	public static final String KEY_HORIZONTAL_ACCY_VALUE = "geoposition_accy_value";
	public static final String KEY_HORIZONTAL_ACCY_UNIT = "geoposition_accy_unit";
	public static final String KEY_HORIZONTAL_COLLECTION_METHOD = "geopositioning_method";
	public static final String KEY_HORIZONTAL_DATUM = "hdatum_id_code";
	public static final String KEY_VERTICAL_MEASURE_VALUE = "elevation_value";
	public static final String KEY_VERTICAL_MEASURE_UNIT = "elevation_unit";
	public static final String KEY_VERTICAL_ACCY_VALUE = "vertical_accuracy_value";
	public static final String KEY_VERTICAL_ACCY_UNIT = "vertical_accuracy_unit";
	public static final String KEY_VERTICAL_COLLECTION_METHOD = "elevation_method";
	public static final String KEY_VERTICAL_DATUM = "vdatum_id_code";
	public static final String KEY_COUNTRY_CODE = "country_code";
	public static final String KEY_STATE_CODE = "state_fips_code";
	public static final String KEY_COUNTY_CODE = "county_fips_code";
	public static final String KEY_NAT_AQFR_NAME = "nat_aqfr_name";
	public static final String KEY_AQFR_NAME = "aqfr_name";
	public static final String KEY_AQFR_TYPE_NAME = "aqfr_type_name";
	public static final String KEY_CONSTRUCTION_DATE = "construction_date";
	public static final String KEY_WELL_DEPTH_VALUE = "well_depth_value";
	public static final String KEY_WELL_DEPTH_UNIT = "well_depth_unit";
	public static final String KEY_HOLE_DEPTH_VALUE = "hole_depth_value";
	public static final String KEY_HOLE_DEPTH_UNIT = "hole_depth_unit";
	public static final String KEY_STATE_NAME = "state_name";
	public static final String KEY_COUNTY_NAME = "county_name";
	public static final String KEY_MONITORING_LOCATION_SUMMARY = "monitoring_location_summary";
	public static final String KEY_MONITORING_LOCATION_URL = "monitoring_location_url";
	public static final String KEY_LAST_SUBMITTED_DATE = "last_result";
	public static final String KEY_TOTAL_ACTIVITIES = "total_activities";
	public static final String KEY_PERIOD_OF_RECORD = "period_of_record";	
	public static final String KEY_THE_YEAR = "the_year";
	public static final String KEY_CHARACTERISTIC_TYPE = "characteristic_type";
	public static final String KEY_CHARACTERISTIC_NAME = "characteristic_name";
	public static final String KEY_TOTAL_RESULTS = "total_results";
	public static final String KEY_LAST_RESULT_DATE = "last_result_date";
	public static final String KEY_MONITORING_LOCATION_TYPE_NAME = "station_type_name";
	

	//Profile Mapping of the Keys
	public static final ColumnProfile STATION_NAME = new ColumnProfile(KEY_STATION_NAME, Profile.STATION, Profile.SIMPLE_STATION, Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile MONITORING_LOCATION_TYPE = new ColumnProfile(KEY_MONITORING_LOCATION_TYPE, Profile.STATION, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile MONITORING_LOCATION_DESCRIPTION = new ColumnProfile(KEY_MONITORING_LOCATION_DESCRIPTION, Profile.STATION);
	public static final ColumnProfile MONITORING_LOCATION_URL = new ColumnProfile(KEY_MONITORING_LOCATION_URL, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile SITE_TYPE = new ColumnProfile(KEY_SITE_TYPE, Profile.SIMPLE_STATION, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile HUC_8 = new ColumnProfile(KEY_HUC_8, Profile.STATION, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile HUC_12 = new ColumnProfile(KEY_HUC_12);
	public static final ColumnProfile DRAIN_AREA_VALUE = new ColumnProfile(KEY_DRAIN_AREA_VALUE, Profile.STATION);
	public static final ColumnProfile DRAIN_AREA_UNIT = new ColumnProfile(KEY_DRAIN_AREA_UNIT, Profile.STATION);
	public static final ColumnProfile CONTRIB_DRAIN_AREA_VALUE = new ColumnProfile(KEY_CONTRIB_DRAIN_AREA_VALUE, Profile.STATION);
	public static final ColumnProfile CONTRIB_DRAIN_AREA_UNIT = new ColumnProfile(KEY_CONTRIB_DRAIN_AREA_UNIT, Profile.STATION);
	public static final ColumnProfile LATITUDE = new ColumnProfile(KEY_LATITUDE, Profile.STATION, Profile.SIMPLE_STATION, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile LONGITUDE = new ColumnProfile(KEY_LONGITUDE, Profile.STATION, Profile.SIMPLE_STATION, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile SOURCE_MAP_SCALE = new ColumnProfile(KEY_SOURCE_MAP_SCALE, Profile.STATION);
	public static final ColumnProfile HORIZONTAL_ACCY_VALUE = new ColumnProfile(KEY_HORIZONTAL_ACCY_VALUE, Profile.STATION);
	public static final ColumnProfile HORIZONTAL_ACCY_UNIT = new ColumnProfile(KEY_HORIZONTAL_ACCY_UNIT, Profile.STATION);
	public static final ColumnProfile HORIZONTAL_COLLECTION_METHOD = new ColumnProfile(KEY_HORIZONTAL_COLLECTION_METHOD, Profile.STATION);
	public static final ColumnProfile HORIZONTAL_DATUM = new ColumnProfile(KEY_HORIZONTAL_DATUM, Profile.STATION);
	public static final ColumnProfile VERTICAL_MEASURE_VALUE = new ColumnProfile(KEY_VERTICAL_MEASURE_VALUE, Profile.STATION);
	public static final ColumnProfile VERTICAL_MEASURE_UNIT = new ColumnProfile(KEY_VERTICAL_MEASURE_UNIT, Profile.STATION);
	public static final ColumnProfile VERTICAL_ACCY_VALUE = new ColumnProfile(KEY_VERTICAL_ACCY_VALUE, Profile.STATION);
	public static final ColumnProfile VERTICAL_ACCY_UNIT = new ColumnProfile(KEY_VERTICAL_ACCY_UNIT, Profile.STATION);
	public static final ColumnProfile VERTICAL_COLLECTION_METHOD = new ColumnProfile(KEY_VERTICAL_COLLECTION_METHOD, Profile.STATION);
	public static final ColumnProfile VERTICAL_DATUM = new ColumnProfile(KEY_VERTICAL_DATUM, Profile.STATION);
	public static final ColumnProfile COUNTRY_CODE = new ColumnProfile(KEY_COUNTRY_CODE, Profile.STATION);
	public static final ColumnProfile STATE_CODE = new ColumnProfile(KEY_STATE_CODE, Profile.STATION);
	public static final ColumnProfile COUNTY_CODE = new ColumnProfile(KEY_COUNTY_CODE, Profile.STATION);
	public static final ColumnProfile NAT_AQFR_NAME = new ColumnProfile(KEY_NAT_AQFR_NAME, Profile.STATION);
	public static final ColumnProfile AQFR_NAME = new ColumnProfile(KEY_AQFR_NAME, Profile.STATION);
	public static final ColumnProfile AQFR_TYPE_NAME = new ColumnProfile(KEY_AQFR_TYPE_NAME, Profile.STATION);
	public static final ColumnProfile CONSTRUCTION_DATE = new ColumnProfile(KEY_CONSTRUCTION_DATE, Profile.STATION);
	public static final ColumnProfile WELL_DEPTH_VALUE = new ColumnProfile(KEY_WELL_DEPTH_VALUE, Profile.STATION);
	public static final ColumnProfile WELL_DEPTH_UNIT = new ColumnProfile(KEY_WELL_DEPTH_UNIT, Profile.STATION);
	public static final ColumnProfile HOLE_DEPTH_VALUE = new ColumnProfile(KEY_HOLE_DEPTH_VALUE, Profile.STATION);
	public static final ColumnProfile HOLE_DEPTH_UNIT = new ColumnProfile(KEY_HOLE_DEPTH_UNIT, Profile.STATION);
	public static final ColumnProfile STATE_NAME = new ColumnProfile(KEY_STATE_NAME, Profile.SIMPLE_STATION, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile COUNTY_NAME = new ColumnProfile(KEY_COUNTY_NAME, Profile.SIMPLE_STATION, Profile.PERIOD_OF_RECORD);	
	public static final ColumnProfile MONITORING_LOCATION_SUMMARY=  new ColumnProfile(KEY_MONITORING_LOCATION_SUMMARY, Profile.SUMMARY_MONITORING_LOCATION);
	public static final ColumnProfile LAST_SUBMITTED_DATE = new ColumnProfile(KEY_LAST_SUBMITTED_DATE, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile TOTAL_ACTIVITIES = new ColumnProfile(KEY_TOTAL_ACTIVITIES, Profile.PERIOD_OF_RECORD);	
//	public static final ColumnProfile MONITORING_LOCATION_TYPE_NAME = new ColumnProfile(KEY_MONITORING_LOCATION_TYPE, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile STATION_ID = new ColumnProfile(KEY_STATION_ID, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile THE_YEAR = new ColumnProfile(KEY_THE_YEAR, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile CHARACTERISTIC_TYPE = new ColumnProfile(KEY_CHARACTERISTIC_TYPE, Profile.PERIOD_OF_RECORD);
	public static final ColumnProfile CHARACTERISTIC_NAME = new ColumnProfile(KEY_CHARACTERISTIC_NAME, Profile.PERIOD_OF_RECORD);	
	public static final ColumnProfile TOTAL_RESULTS = new ColumnProfile(KEY_TOTAL_RESULTS, Profile.PERIOD_OF_RECORD);	
	public static final ColumnProfile LAST_RESULT_DATE= new ColumnProfile(KEY_LAST_RESULT_DATE, Profile.PERIOD_OF_RECORD);	
	
	private StationColumn() {
	}
}
