package gov.usgs.cida.wqp.mapping;

public class ActivityColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_ACTIVITY_ID = "activity_id";
	public static final String KEY_ACTIVITY = "activity";
	public static final String KEY_ACTIVITY_TYPE_CODE = "activity_type_code";
	public static final String KEY_SAMPLE_MEDIA = "sample_media";
	public static final String KEY_ACTIVITY_MEDIA_SUBDIV_NAME = "activity_media_subdiv_name";
	public static final String KEY_EVENT_DATE = "event_date";
	public static final String KEY_ACTIVITY_START_TIME = "activity_start_time";
	public static final String KEY_ACT_START_TIME_ZONE = "act_start_time_zone";
	public static final String KEY_ACTIVITY_STOP_DATE = "activity_stop_date";
	public static final String KEY_ACTIVITY_STOP_TIME = "activity_stop_time";
	public static final String KEY_ACT_STOP_TIME_ZONE = "act_stop_time_zone";
	public static final String KEY_ACTIVITY_RELATIVE_DEPTH = "activity_relative_depth_name";
	public static final String KEY_ACTIVITY_DEPTH = "activity_depth";
	public static final String KEY_ACTIVITY_DEPTH_UNIT = "activity_depth_unit";
	public static final String KEY_ACTIVITY_DEPTH_REF_POINT = "activity_depth_ref_point";
	public static final String KEY_ACTIVITY_UPPER_DEPTH = "activity_upper_depth";
	public static final String KEY_ACTIVITY_UPPER_DEPTH_UNIT = "activity_upper_depth_unit";
	public static final String KEY_ACTIVITY_LOWER_DEPTH = "activity_lower_depth";
	public static final String KEY_ACTIVITY_LOWER_DEPTH_UNIT = "activity_lower_depth_unit";
	public static final String KEY_ACTIVITY_PROJECTS = "activity_projects";
	public static final String KEY_ACTIVITY_CONDUCTING_ORG = "activity_conducting_org";
	public static final String KEY_ACTIVITY_COMMENT = "activity_comment";
	public static final String KEY_SAMPLE_AQFR_NAME = "sample_aqfr_name";
	public static final String KEY_HYDROLOGIC_CONDITION_NAME = "hydrologic_condition_name";
	public static final String KEY_HYDROLOGIC_EVENT_NAME = "hydrologic_event_name";
	public static final String KEY_ACTIVITY_LATITUDE = "activity_latitude";
	public static final String KEY_ACTIVITY_LONGITUDE = "activity_longitude";
	public static final String KEY_ACTIVITY_SOURCE_MAP_SCALE = "activity_source_map_scale";
	public static final String KEY_ACT_HORIZONTAL_ACCURACY = "act_horizontal_accuracy";
	public static final String KEY_ACT_HORIZONTAL_ACCURACY_UNIT = "act_horizontal_accuracy_unit";
	public static final String KEY_ACT_HORIZONTAL_COLLECT_METHOD = "act_horizontal_collect_method";
	public static final String KEY_ACT_HORIZONTAL_DATUM_NAME = "act_horizontal_datum_name";
	public static final String KEY_ASSEMBLAGE_SAMPLED_NAME = "assemblage_sampled_name";
	public static final String KEY_ACT_COLLECTION_DURATION = "act_collection_duration";
	public static final String KEY_ACT_COLLECTION_DURATION_UNIT = "act_collection_duration_unit";
	public static final String KEY_ACT_SAM_COMPNT_NAME = "act_sam_compnt_name";
	public static final String KEY_ACT_SAM_COMPNT_PLACE_IN_SERIES = "act_sam_compnt_place_in_series";
	public static final String KEY_ACT_REACH_LENGTH = "act_reach_length";
	public static final String KEY_ACT_REACH_LENGTH_UNIT = "act_reach_length_unit";
	public static final String KEY_ACT_REACH_WIDTH = "act_reach_width";
	public static final String KEY_ACT_REACH_WIDTH_UNIT = "act_reach_width_unit";
	public static final String KEY_ACT_PASS_COUNT = "act_pass_count";
	public static final String KEY_NET_TYPE_NAME = "net_type_name";
	public static final String KEY_ACT_NET_SURFACE_AREA = "act_net_surface_area";
	public static final String KEY_ACT_NET_SURFACE_AREA_UNIT = "act_net_surface_area_unit";
	public static final String KEY_ACT_NET_MESH_SIZE = "act_net_mesh_size";
	public static final String KEY_ACT_NET_MESH_SIZE_UNIT = "act_net_mesh_size_unit";
	public static final String KEY_ACT_BOAT_SPEED = "act_boat_speed";
	public static final String KEY_ACT_BOAT_SPEED_UNIT = "act_boat_speed_unit";
	public static final String KEY_ACT_CURRENT_SPEED = "act_current_speed";
	public static final String KEY_ACT_CURRENT_SPEED_UNIT = "act_current_speed_unit";
	public static final String KEY_TOXICITY_TEST_TYPE_NAME = "toxicity_test_type_name";
	public static final String KEY_SAMPLE_COLLECT_METHOD_ID = "sample_collect_method_id";
	public static final String KEY_SAMPLE_COLLECT_METHOD_CTX = "sample_collect_method_ctx";
	public static final String KEY_SAMPLE_COLLECT_METHOD_NAME = "sample_collect_method_name";
	public static final String KEY_ACT_SAM_COLLECT_METH_QUAL_TYPE = "act_sam_collect_meth_qual_type";
	public static final String KEY_ACT_SAM_COLLECT_METH_DESC = "act_sam_collect_meth_desc";
	public static final String KEY_SAMPLE_COLLECT_EQUIP_NAME = "sample_collect_equip_name";
	public static final String KEY_ACT_SAM_COLLECT_EQUIP_COMMENTS = "act_sam_collect_equip_comments";
	public static final String KEY_ACT_SAM_PREP_METH_ID = "act_sam_prep_meth_id";
	public static final String KEY_ACT_SAM_PREP_METH_CONTEXT = "act_sam_prep_meth_context";
	public static final String KEY_ACT_SAM_PREP_METH_NAME = "act_sam_prep_meth_name";
	public static final String KEY_ACT_SAM_PREP_METH_QUAL_TYPE = "act_sam_prep_meth_qual_type";
	public static final String KEY_ACT_SAM_PREP_METH_DESC = "act_sam_prep_meth_desc";
	public static final String KEY_SAMPLE_CONTAINER_TYPE = "sample_container_type";
	public static final String KEY_SAMPLE_CONTAINER_COLOR = "sample_container_color";
	public static final String KEY_ACT_SAM_CHEMICAL_PRESERVATIVE = "act_sam_chemical_preservative";
	public static final String KEY_THERMAL_PRESERVATIVE_NAME = "thermal_preservative_name";
	public static final String KEY_ACT_SAM_TRANSPORT_STORAGE_DESC = "act_sam_transport_storage_desc";
	public static final String KEY_ACTIVITY_OBJECT_NAME = "activity_object_name";
	public static final String KEY_ACTIVITY_OBJECT_TYPE = "activity_object_type";
	public static final String KEY_ACTIVITY_FILE_URL = "activity_file_url";
	public static final String KEY_ACTIVITY_METRIC_URL = "activity_metric_url";
	public static final String KEY_ACTIVITY_GROUP_URL = "activity_group_url";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Profile Mapping of the Keys
	public static final ColumnProfile ACTIVITY = new ColumnProfile(KEY_ACTIVITY, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.ACTIVITY_METRIC, Profile.RES_DETECT_QNT_LMT,
			Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_TYPE_CODE = new ColumnProfile(KEY_ACTIVITY_TYPE_CODE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_BROAD);
	public static final ColumnProfile SAMPLE_MEDIA = new ColumnProfile(KEY_SAMPLE_MEDIA, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_MEDIA_SUBDIV_NAME = new ColumnProfile(KEY_ACTIVITY_MEDIA_SUBDIV_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile EVENT_DATE = new ColumnProfile(KEY_EVENT_DATE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_START_TIME = new ColumnProfile(KEY_ACTIVITY_START_TIME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_START_TIME_ZONE = new ColumnProfile(KEY_ACT_START_TIME_ZONE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_STOP_DATE = new ColumnProfile(KEY_ACTIVITY_STOP_DATE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_STOP_TIME = new ColumnProfile(KEY_ACTIVITY_STOP_TIME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_STOP_TIME_ZONE = new ColumnProfile(KEY_ACT_STOP_TIME_ZONE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_RELATIVE_DEPTH = new ColumnProfile(KEY_ACTIVITY_RELATIVE_DEPTH, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_DEPTH = new ColumnProfile(KEY_ACTIVITY_DEPTH, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_DEPTH_UNIT = new ColumnProfile(KEY_ACTIVITY_DEPTH_UNIT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_UPPER_DEPTH = new ColumnProfile(KEY_ACTIVITY_UPPER_DEPTH, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_UPPER_DEPTH_UNIT = new ColumnProfile(KEY_ACTIVITY_UPPER_DEPTH_UNIT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_LOWER_DEPTH = new ColumnProfile(KEY_ACTIVITY_LOWER_DEPTH, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_LOWER_DEPTH_UNIT = new ColumnProfile(KEY_ACTIVITY_LOWER_DEPTH_UNIT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_DEPTH_REF_POINT = new ColumnProfile(KEY_ACTIVITY_DEPTH_REF_POINT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_PROJECTS = new ColumnProfile(KEY_ACTIVITY_PROJECTS, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_CONDUCTING_ORG = new ColumnProfile(KEY_ACTIVITY_CONDUCTING_ORG, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_COMMENT = new ColumnProfile(KEY_ACTIVITY_COMMENT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile SAMPLE_AQFR_NAME = new ColumnProfile(KEY_SAMPLE_AQFR_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile HYDROLOGIC_CONDITION_NAME = new ColumnProfile(KEY_HYDROLOGIC_CONDITION_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile HYDROLOGIC_EVENT_NAME = new ColumnProfile(KEY_HYDROLOGIC_EVENT_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_LATITUDE = new ColumnProfile(KEY_ACTIVITY_LATITUDE, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_LONGITUDE = new ColumnProfile(KEY_ACTIVITY_LONGITUDE, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_SOURCE_MAP_SCALE = new ColumnProfile(KEY_ACTIVITY_SOURCE_MAP_SCALE, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_HORIZONTAL_ACCURACY = new ColumnProfile(KEY_ACT_HORIZONTAL_ACCURACY, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_HORIZONTAL_ACCURACY_UNIT = new ColumnProfile(KEY_ACT_HORIZONTAL_ACCURACY_UNIT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_HORIZONTAL_COLLECT_METHOD = new ColumnProfile(KEY_ACT_HORIZONTAL_COLLECT_METHOD, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_HORIZONTAL_DATUM_NAME = new ColumnProfile(KEY_ACT_HORIZONTAL_DATUM_NAME, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ASSEMBLAGE_SAMPLED_NAME = new ColumnProfile(KEY_ASSEMBLAGE_SAMPLED_NAME, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_COLLECTION_DURATION = new ColumnProfile(KEY_ACT_COLLECTION_DURATION, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_COLLECTION_DURATION_UNIT = new ColumnProfile(KEY_ACT_COLLECTION_DURATION_UNIT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_COMPNT_NAME = new ColumnProfile(KEY_ACT_SAM_COMPNT_NAME, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_COMPNT_PLACE_IN_SERIES = new ColumnProfile(KEY_ACT_SAM_COMPNT_PLACE_IN_SERIES, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_REACH_LENGTH = new ColumnProfile(KEY_ACT_REACH_LENGTH, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_REACH_LENGTH_UNIT = new ColumnProfile(KEY_ACT_REACH_LENGTH_UNIT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_REACH_WIDTH = new ColumnProfile(KEY_ACT_REACH_WIDTH, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_REACH_WIDTH_UNIT = new ColumnProfile(KEY_ACT_REACH_WIDTH_UNIT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_PASS_COUNT = new ColumnProfile(KEY_ACT_PASS_COUNT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile NET_TYPE_NAME = new ColumnProfile(KEY_NET_TYPE_NAME, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_NET_SURFACE_AREA = new ColumnProfile(KEY_ACT_NET_SURFACE_AREA, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_NET_SURFACE_AREA_UNIT = new ColumnProfile(KEY_ACT_NET_SURFACE_AREA_UNIT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_NET_MESH_SIZE = new ColumnProfile(KEY_ACT_NET_MESH_SIZE, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_NET_MESH_SIZE_UNIT = new ColumnProfile(KEY_ACT_NET_MESH_SIZE_UNIT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_BOAT_SPEED = new ColumnProfile(KEY_ACT_BOAT_SPEED, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_BOAT_SPEED_UNIT = new ColumnProfile(KEY_ACT_BOAT_SPEED_UNIT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_CURRENT_SPEED = new ColumnProfile(KEY_ACT_CURRENT_SPEED, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_CURRENT_SPEED_UNIT = new ColumnProfile(KEY_ACT_CURRENT_SPEED_UNIT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile TOXICITY_TEST_TYPE_NAME = new ColumnProfile(KEY_TOXICITY_TEST_TYPE_NAME, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile SAMPLE_COLLECT_METHOD_ID = new ColumnProfile(KEY_SAMPLE_COLLECT_METHOD_ID, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile SAMPLE_COLLECT_METHOD_CTX = new ColumnProfile(KEY_SAMPLE_COLLECT_METHOD_CTX, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile SAMPLE_COLLECT_METHOD_NAME = new ColumnProfile(KEY_SAMPLE_COLLECT_METHOD_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_COLLECT_METH_QUAL_TYPE = new ColumnProfile(KEY_ACT_SAM_COLLECT_METH_QUAL_TYPE, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);

	public static final ColumnProfile ACT_SAM_COLLECT_METH_DESC = new ColumnProfile(KEY_ACT_SAM_COLLECT_METH_DESC, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);

	public static final ColumnProfile SAMPLE_COLLECT_EQUIP_NAME = new ColumnProfile(KEY_SAMPLE_COLLECT_EQUIP_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.ACTIVITY, Profile.ACTIVITY_ALL,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_COLLECT_EQUIP_COMMENTS = new ColumnProfile(KEY_ACT_SAM_COLLECT_EQUIP_COMMENTS, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_PREP_METH_ID = new ColumnProfile(KEY_ACT_SAM_PREP_METH_ID, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_PREP_METH_CONTEXT = new ColumnProfile(KEY_ACT_SAM_PREP_METH_CONTEXT, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_PREP_METH_NAME = new ColumnProfile(KEY_ACT_SAM_PREP_METH_NAME, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_PREP_METH_QUAL_TYPE = new ColumnProfile(KEY_ACT_SAM_PREP_METH_QUAL_TYPE, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);

	public static final ColumnProfile ACT_SAM_PREP_METH_DESC = new ColumnProfile(KEY_ACT_SAM_PREP_METH_DESC, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);

	public static final ColumnProfile SAMPLE_CONTAINER_TYPE = new ColumnProfile(KEY_SAMPLE_CONTAINER_TYPE, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile SAMPLE_CONTAINER_COLOR = new ColumnProfile(KEY_SAMPLE_CONTAINER_COLOR, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_CHEMICAL_PRESERVATIVE = new ColumnProfile(KEY_ACT_SAM_CHEMICAL_PRESERVATIVE, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile THERMAL_PRESERVATIVE_NAME = new ColumnProfile(KEY_THERMAL_PRESERVATIVE_NAME, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACT_SAM_TRANSPORT_STORAGE_DESC = new ColumnProfile(KEY_ACT_SAM_TRANSPORT_STORAGE_DESC, Profile.BIOLOGICAL, Profile.ACTIVITY, Profile.ACTIVITY_ALL, Profile.RESULT_BROAD);
	public static final ColumnProfile ACTIVITY_OBJECT_NAME = new ColumnProfile(KEY_ACTIVITY_OBJECT_NAME, Profile.ACTIVITY_ALL);
	public static final ColumnProfile ACTIVITY_OBJECT_TYPE = new ColumnProfile(KEY_ACTIVITY_OBJECT_TYPE, Profile.ACTIVITY_ALL);
	public static final ColumnProfile ACTIVITY_FILE_URL = new ColumnProfile(KEY_ACTIVITY_FILE_URL, Profile.ACTIVITY_ALL);
	public static final ColumnProfile ACTIVITY_METRIC_URL = new ColumnProfile(KEY_ACTIVITY_METRIC_URL, Profile.ACTIVITY, Profile.ACTIVITY_ALL);
	public static final ColumnProfile ACTIVITY_GROUP_URL = new ColumnProfile(KEY_ACTIVITY_GROUP_URL, Profile.ACTIVITY_ALL);

	private ActivityColumn() {
	}
}
