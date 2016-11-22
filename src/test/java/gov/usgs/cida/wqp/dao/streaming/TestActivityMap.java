package gov.usgs.cida.wqp.dao.streaming;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import static gov.usgs.cida.wqp.mapping.ActivityColumn.*;
import static gov.usgs.cida.wqp.mapping.StationColumn.*;

public class TestActivityMap {

	public static final Map<String, Object> ACTIVITY;
	static {
		ACTIVITY = new LinkedHashMap<String, Object>();
		ACTIVITY.put(KEY_DATA_SOURCE_ID, BigDecimal.valueOf(3));
		ACTIVITY.put(KEY_DATA_SOURCE, "STORET");
		ACTIVITY.put(KEY_STATION_ID, BigDecimal.valueOf(888));
		ACTIVITY.put(KEY_SITE_ID, "organization-siteId");
		ACTIVITY.put(KEY_EVENT_DATE, "1999-12-31");
		ACTIVITY.put(KEY_ACTIVITY, "activity");
		ACTIVITY.put(KEY_SAMPLE_MEDIA, "sampleMedia");
		ACTIVITY.put(KEY_ORGANIZATION, "organization");
		ACTIVITY.put(KEY_SITE_TYPE, "siteType");
		ACTIVITY.put(KEY_HUC, "0000");
		ACTIVITY.put(KEY_GOVERNMENTAL_UNIT_CODE, "XX:44:555");
		ACTIVITY.put(KEY_ORGANIZATION_NAME, "organizationName");
		ACTIVITY.put(KEY_ACTIVITY_ID, BigDecimal.valueOf(2));
		ACTIVITY.put(KEY_ACTIVITY_TYPE_CODE, "activityTypeCode");
		ACTIVITY.put(KEY_ACTIVITY_MEDIA_SUBDIV_NAME, "activityMediaSubdivName");
		ACTIVITY.put(KEY_ACTIVITY_START_TIME, "activityStartTime");
		ACTIVITY.put(KEY_ACT_START_TIME_ZONE, "actStartTimeZone");
		ACTIVITY.put(KEY_ACTIVITY_STOP_DATE, "activityStopDate");
		ACTIVITY.put(KEY_ACTIVITY_STOP_TIME, "activityStopTime");
		ACTIVITY.put(KEY_ACT_STOP_TIME_ZONE, "actStopTimeZone");
		ACTIVITY.put(KEY_ACTIVITY_RELATIVE_DEPTH, "activityRelativeDepthName");
		ACTIVITY.put(KEY_ACTIVITY_DEPTH, "activityDepth");
		ACTIVITY.put(KEY_ACTIVITY_DEPTH_UNIT, "activityDepthUnit");
		ACTIVITY.put(KEY_ACTIVITY_DEPTH_REF_POINT, "activityDepthRefPoint");
		ACTIVITY.put(KEY_ACTIVITY_UPPER_DEPTH, "activityUpperDepth");
		ACTIVITY.put(KEY_ACTIVITY_UPPER_DEPTH_UNIT, "activityUpperDepthUnit");
		ACTIVITY.put(KEY_ACTIVITY_LOWER_DEPTH, "activityLowerDepth");
		ACTIVITY.put(KEY_ACTIVITY_LOWER_DEPTH_UNIT, "activityLowerDepthUnit");
		ACTIVITY.put(KEY_PROJECT_ID, "projectId");
		ACTIVITY.put(KEY_ACTIVITY_CONDUCTING_ORG, "activityConductingOrg");
		ACTIVITY.put(KEY_ACTIVITY_COMMENT, "activityComment");
		ACTIVITY.put(KEY_SAMPLE_AQFR_NAME, "sampleAqfrName");
		ACTIVITY.put(KEY_HYDROLOGIC_CONDITION_NAME, "hydrologicConditionName");
		ACTIVITY.put(KEY_HYDROLOGIC_EVENT_NAME, "hydrologicEventName");
		ACTIVITY.put(KEY_ACTIVITY_LATITUDE, BigDecimal.valueOf(43.333));
		ACTIVITY.put(KEY_ACTIVITY_LONGITUDE, BigDecimal.valueOf(-89.8989));
		ACTIVITY.put(KEY_ACTIVITY_SOURCE_MAP_SCALE, BigDecimal.valueOf(2222222));
		ACTIVITY.put(KEY_ACT_HORIZONTAL_ACCURACY, "actHorizontalAccuracy");
		ACTIVITY.put(KEY_ACT_HORIZONTAL_ACCURACY_UNIT, "actHorizontalAccuracyUnit");
		ACTIVITY.put(KEY_ACT_HORIZONTAL_COLLECT_METHOD, "actHorizontalCollectMethod");
		ACTIVITY.put(KEY_ACT_HORIZONTAL_DATUM_NAME, "actHorizontalDatumName");
		ACTIVITY.put(KEY_ASSEMBLAGE_SAMPLED_NAME, "assemblageSampledName");
		ACTIVITY.put(KEY_ACT_COLLECTION_DURATION, "actCollectionDuration");
		ACTIVITY.put(KEY_ACT_COLLECTION_DURATION_UNIT, "actCollectionDurationUnit");
		ACTIVITY.put(KEY_ACT_SAM_COMPNT_NAME, "actSamCompntName");
		ACTIVITY.put(KEY_ACT_SAM_COMPNT_PLACE_IN_SERIES, BigDecimal.valueOf(12321));
		ACTIVITY.put(KEY_ACT_REACH_LENGTH, "actReachLength");
		ACTIVITY.put(KEY_ACT_REACH_LENGTH_UNIT, "actReachLengthUnit");
		ACTIVITY.put(KEY_ACT_REACH_WIDTH, "actReachWidth");
		ACTIVITY.put(KEY_ACT_REACH_WIDTH_UNIT, "actReachWidthUnit");
		ACTIVITY.put(KEY_ACT_PASS_COUNT, BigDecimal.valueOf(4));
		ACTIVITY.put(KEY_NET_TYPE_NAME, "netTypeName");
		ACTIVITY.put(KEY_ACT_NET_SURFACE_AREA, "actNetSurfaceArea");
		ACTIVITY.put(KEY_ACT_NET_SURFACE_AREA_UNIT, "actNetSurfaceAreaUnit");
		ACTIVITY.put(KEY_ACT_NET_MESH_SIZE, "actNetMeshSize");
		ACTIVITY.put(KEY_ACT_NET_MESH_SIZE_UNIT, "actNetMeshSizeUnit");
		ACTIVITY.put(KEY_ACT_BOAT_SPEED, "act_boatSpeed");
		ACTIVITY.put(KEY_ACT_BOAT_SPEED_UNIT, "act_boatSpeedUnit");
		ACTIVITY.put(KEY_ACT_CURRENT_SPEED, "actCurrentSpeed");
		ACTIVITY.put(KEY_ACT_CURRENT_SPEED_UNIT, "actCurrentSpeedUnit");
		ACTIVITY.put(KEY_TOXICITY_TEST_TYPE_NAME, "toxicityTestTypeName");
		ACTIVITY.put(KEY_SAMPLE_COLLECT_METHOD_ID, "sampleCollectMethodId");
		ACTIVITY.put(KEY_SAMPLE_COLLECT_METHOD_CTX, "sampleCollectMethodCtx");
		ACTIVITY.put(KEY_SAMPLE_COLLECT_METHOD_NAME, "sampleCollectMethodName");
		ACTIVITY.put(KEY_ACT_SAM_COLLECT_METH_QUAL_TYPE, "actSamCollectMethQualType");
		ACTIVITY.put(KEY_ACT_SAM_COLLECT_METH_DESC, "actSamCollectMethDesc");
		ACTIVITY.put(KEY_SAMPLE_COLLECT_EQUIP_NAME, "sampleCollectEquipName");
		ACTIVITY.put(KEY_ACT_SAM_COLLECT_EQUIP_COMMENTS, "actSamCollectEquipComments");
		ACTIVITY.put(KEY_ACT_SAM_PREP_METH_ID, "actSamPrepMethId");
		ACTIVITY.put(KEY_ACT_SAM_PREP_METH_CONTEXT, "actSamPrepMethContext");
		ACTIVITY.put(KEY_ACT_SAM_PREP_METH_NAME, "actSamPrepMethName");
		ACTIVITY.put(KEY_ACT_SAM_PREP_METH_QUAL_TYPE, "actSamPrepMethQualType");
		ACTIVITY.put(KEY_ACT_SAM_PREP_METH_DESC, "actSamPrepMethDesc");
		ACTIVITY.put(KEY_SAMPLE_CONTAINER_TYPE, "sampleContainerType");
		ACTIVITY.put(KEY_SAMPLE_CONTAINER_COLOR, "sampleContainerColor");
		ACTIVITY.put(KEY_ACT_SAM_CHEMICAL_PRESERVATIVE, "actSamChemicalPreservative");
		ACTIVITY.put(KEY_THERMAL_PRESERVATIVE_NAME, "thermalPreservativeName");
		ACTIVITY.put(KEY_ACT_SAM_TRANSPORT_STORAGE_DESC, "actSamTransportStorageDesc");
	}

	private TestActivityMap() {
	}
}
