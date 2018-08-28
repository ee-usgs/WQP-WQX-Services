package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.ActivityColumn.*;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_SITE_TYPE;
import static gov.usgs.cida.wqp.mapping.TestStationMap.BASE_STATION;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestActivityMap {

	public static final Map<String, Object> BASE_LOCATION_COLUMNS;
	static {
		BASE_LOCATION_COLUMNS = new LinkedHashMap<String, Object>();
		BASE_LOCATION_COLUMNS.put(KEY_ACTIVITY_LATITUDE, BigDecimal.valueOf(43.333));
		BASE_LOCATION_COLUMNS.put(KEY_ACTIVITY_LONGITUDE, BigDecimal.valueOf(-89.8989));
	}

	public static final Map<String, Object> EXTENDED_LOCATION_COLUMNS;
	static {
		EXTENDED_LOCATION_COLUMNS = new LinkedHashMap<String, Object>();
		EXTENDED_LOCATION_COLUMNS.putAll(BASE_LOCATION_COLUMNS);
		EXTENDED_LOCATION_COLUMNS.put(KEY_ACTIVITY_SOURCE_MAP_SCALE, BigDecimal.valueOf(2222222));
	}

	public static final Map<String, Object> BASE_ACTIVITY;
	static {
		BASE_ACTIVITY = new LinkedHashMap<String, Object>();
		BASE_ACTIVITY.putAll(BASE_STATION);
		BASE_ACTIVITY.put(KEY_ACTIVITY, "activity");
		BASE_ACTIVITY.put(KEY_SITE_TYPE, "siteType");
		BASE_ACTIVITY.put(KEY_HUC, "0000");
		BASE_ACTIVITY.put(KEY_GOVERNMENTAL_UNIT_CODE, "XX:44:555");
		BASE_ACTIVITY.put(KEY_ORGANIZATION_NAME, "organizationName");
		BASE_ACTIVITY.put(KEY_ACTIVITY_ID, BigDecimal.valueOf(11));
		BASE_ACTIVITY.put(KEY_ACTIVITY_TYPE_CODE, "activityTypeCode");
		BASE_ACTIVITY.put(KEY_SAMPLE_MEDIA, "sampleMedia");
		BASE_ACTIVITY.put(KEY_ACTIVITY_MEDIA_SUBDIV_NAME, "activityMediaSubdivName");
		BASE_ACTIVITY.put(KEY_EVENT_DATE, "1999-12-28");
		BASE_ACTIVITY.put(KEY_ACTIVITY_START_TIME, "activityStartTime");
		BASE_ACTIVITY.put(KEY_ACT_START_TIME_ZONE, "actStartTimeZone");
		BASE_ACTIVITY.put(KEY_ACTIVITY_STOP_DATE, "activityStopDate");
		BASE_ACTIVITY.put(KEY_ACTIVITY_STOP_TIME, "activityStopTime");
		BASE_ACTIVITY.put(KEY_ACT_STOP_TIME_ZONE, "actStopTimeZone");
		BASE_ACTIVITY.put(KEY_ACTIVITY_RELATIVE_DEPTH, "activityRelativeDepthName");
		BASE_ACTIVITY.put(KEY_ACTIVITY_DEPTH, "activityDepth");
		BASE_ACTIVITY.put(KEY_ACTIVITY_DEPTH_UNIT, "activityDepthUnit");
		BASE_ACTIVITY.put(KEY_ACTIVITY_DEPTH_REF_POINT, "activityDepthRefPoint");
		BASE_ACTIVITY.put(KEY_ACTIVITY_UPPER_DEPTH, "activityUpperDepth");
		BASE_ACTIVITY.put(KEY_ACTIVITY_UPPER_DEPTH_UNIT, "activityUpperDepthUnit");
		BASE_ACTIVITY.put(KEY_ACTIVITY_LOWER_DEPTH, "activityLowerDepth");
		BASE_ACTIVITY.put(KEY_ACTIVITY_LOWER_DEPTH_UNIT, "activityLowerDepthUnit");
		BASE_ACTIVITY.put(KEY_ACTIVITY_PROJECTS, "projectId");
		BASE_ACTIVITY.put(KEY_ACTIVITY_CONDUCTING_ORG, "activityConductingOrg");
		BASE_ACTIVITY.put(KEY_ACTIVITY_COMMENT, "activityComment");
		BASE_ACTIVITY.put(KEY_SAMPLE_AQFR_NAME, "sampleAqfrName");
		BASE_ACTIVITY.put(KEY_HYDROLOGIC_CONDITION_NAME, "hydrologicConditionName");
		BASE_ACTIVITY.put(KEY_HYDROLOGIC_EVENT_NAME, "hydrologicEventName");
		BASE_ACTIVITY.put(KEY_SAMPLE_COLLECT_METHOD_ID, "sampleCollectMethodId");
		BASE_ACTIVITY.put(KEY_SAMPLE_COLLECT_METHOD_CTX, "sampleCollectMethodCtx");
		BASE_ACTIVITY.put(KEY_SAMPLE_COLLECT_METHOD_NAME, "sampleCollectMethodName");
		BASE_ACTIVITY.put(KEY_ACT_SAM_COLLECT_METH_DESC, "actSamCollectMethDesc");
		BASE_ACTIVITY.put(KEY_SAMPLE_COLLECT_EQUIP_NAME, "sampleCollectEquipName");
		BASE_ACTIVITY.put(KEY_ACT_SAM_COLLECT_EQUIP_COMMENTS, "actSamCollectEquipComments");
		BASE_ACTIVITY.put(KEY_ACT_SAM_PREP_METH_ID, "actSamPrepMethId");
		BASE_ACTIVITY.put(KEY_ACT_SAM_PREP_METH_CONTEXT, "actSamPrepMethContext");
		BASE_ACTIVITY.put(KEY_ACT_SAM_PREP_METH_NAME, "actSamPrepMethName");
		BASE_ACTIVITY.put(KEY_ACT_SAM_PREP_METH_QUAL_TYPE, "actSamPrepMethQualType");
		BASE_ACTIVITY.put(KEY_ACT_SAM_PREP_METH_DESC, "actSamPrepMethDesc");
		BASE_ACTIVITY.put(KEY_SAMPLE_CONTAINER_TYPE, "sampleContainerType");
		BASE_ACTIVITY.put(KEY_SAMPLE_CONTAINER_COLOR, "sampleContainerColor");
		BASE_ACTIVITY.put(KEY_ACT_SAM_CHEMICAL_PRESERVATIVE, "actSamChemicalPreservative");
		BASE_ACTIVITY.put(KEY_THERMAL_PRESERVATIVE_NAME, "thermalPreservativeName");
		BASE_ACTIVITY.put(KEY_ACT_SAM_TRANSPORT_STORAGE_DESC, "actSamTransportStorageDesc");
	}

	public static final Map<String, Object> EXTENDED_ACTIVITY;
	static {
		EXTENDED_ACTIVITY = new LinkedHashMap<String, Object>();
		EXTENDED_ACTIVITY.putAll(BASE_ACTIVITY);
		EXTENDED_ACTIVITY.putAll(EXTENDED_LOCATION_COLUMNS);
		EXTENDED_ACTIVITY.put(KEY_ACT_HORIZONTAL_ACCURACY, "actHorizontalAccuracy");
		EXTENDED_ACTIVITY.put(KEY_ACT_HORIZONTAL_ACCURACY_UNIT, "actHorizontalAccuracyUnit");
		EXTENDED_ACTIVITY.put(KEY_ACT_HORIZONTAL_COLLECT_METHOD, "actHorizontalCollectMethod");
		EXTENDED_ACTIVITY.put(KEY_ACT_HORIZONTAL_DATUM_NAME, "actHorizontalDatumName");
		EXTENDED_ACTIVITY.put(KEY_ASSEMBLAGE_SAMPLED_NAME, "assemblageSampledName");
		EXTENDED_ACTIVITY.put(KEY_ACT_COLLECTION_DURATION, "actCollectionDuration");
		EXTENDED_ACTIVITY.put(KEY_ACT_COLLECTION_DURATION_UNIT, "actCollectionDurationUnit");
		EXTENDED_ACTIVITY.put(KEY_ACT_SAM_COMPNT_NAME, "actSamCompntName");
		EXTENDED_ACTIVITY.put(KEY_ACT_SAM_COMPNT_PLACE_IN_SERIES, BigDecimal.valueOf(12321));
		EXTENDED_ACTIVITY.put(KEY_ACT_REACH_LENGTH, "actReachLength");
		EXTENDED_ACTIVITY.put(KEY_ACT_REACH_LENGTH_UNIT, "actReachLengthUnit");
		EXTENDED_ACTIVITY.put(KEY_ACT_REACH_WIDTH, "actReachWidth");
		EXTENDED_ACTIVITY.put(KEY_ACT_REACH_WIDTH_UNIT, "actReachWidthUnit");
		EXTENDED_ACTIVITY.put(KEY_ACT_PASS_COUNT, BigDecimal.valueOf(4));
		EXTENDED_ACTIVITY.put(KEY_NET_TYPE_NAME, "netTypeName");
		EXTENDED_ACTIVITY.put(KEY_ACT_NET_SURFACE_AREA, "actNetSurfaceArea");
		EXTENDED_ACTIVITY.put(KEY_ACT_NET_SURFACE_AREA_UNIT, "actNetSurfaceAreaUnit");
		EXTENDED_ACTIVITY.put(KEY_ACT_NET_MESH_SIZE, "actNetMeshSize");
		EXTENDED_ACTIVITY.put(KEY_ACT_NET_MESH_SIZE_UNIT, "actNetMeshSizeUnit");
		EXTENDED_ACTIVITY.put(KEY_ACT_BOAT_SPEED, "actBoatSpeed");
		EXTENDED_ACTIVITY.put(KEY_ACT_BOAT_SPEED_UNIT, "actBoatSpeedUnit");
		EXTENDED_ACTIVITY.put(KEY_ACT_CURRENT_SPEED, "actCurrentSpeed");
		EXTENDED_ACTIVITY.put(KEY_ACT_CURRENT_SPEED_UNIT, "actCurrentSpeedUnit");
		EXTENDED_ACTIVITY.put(KEY_TOXICITY_TEST_TYPE_NAME, "toxicityTestTypeName");
		EXTENDED_ACTIVITY.put(KEY_ACT_SAM_COLLECT_METH_QUAL_TYPE, "actSamCollectMethQualType");
	}

	public static final Map<String, Object> ACTIVITY;
	static {
		ACTIVITY = new LinkedHashMap<String, Object>();
		ACTIVITY.putAll(EXTENDED_ACTIVITY);
		ACTIVITY.put(KEY_ACTIVITY_METRIC_URL, "/activities/organization-11/activitymetrics");
	}

	public static final Map<String, Object> ACTIVITY_ALL;
	static {
		ACTIVITY_ALL = new LinkedHashMap<String, Object>();
		ACTIVITY_ALL.putAll(ACTIVITY);
		ACTIVITY_ALL.put(KEY_ACTIVITY_OBJECT_NAME, "actObjectName2");
		ACTIVITY_ALL.put(KEY_ACTIVITY_OBJECT_TYPE, "actObjectType2");
		ACTIVITY_ALL.put(KEY_ACTIVITY_FILE_URL, "/organizations/organization/activities/organization-11/files");
		ACTIVITY_ALL.put(KEY_ACTIVITY_GROUP_URL, null);
	}

	private TestActivityMap() {
	}
}
