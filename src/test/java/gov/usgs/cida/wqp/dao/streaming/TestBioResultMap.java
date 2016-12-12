package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.mapping.ActivityColumn.*;
import static gov.usgs.cida.wqp.mapping.ResultColumn.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestBioResultMap {

	public static final int BIO_RESULT_COLUMN_COUNT = 164;

	public static final Map<String, Object> BIO_RESULT;
	static {
		BIO_RESULT = new LinkedHashMap<String, Object>();
		BIO_RESULT.putAll(TestPcResultMap.PC_RESULT);
		BIO_RESULT.put(KEY_ACTIVITY_RELATIVE_DEPTH, "activityRelativeDepthName");
		BIO_RESULT.put(KEY_ACTIVITY_LATITUDE, BigDecimal.valueOf(43.333));
		BIO_RESULT.put(KEY_ACTIVITY_LONGITUDE, BigDecimal.valueOf(-89.8989));
		BIO_RESULT.put(KEY_ACTIVITY_SOURCE_MAP_SCALE, BigDecimal.valueOf(24000));
		BIO_RESULT.put(KEY_ACT_HORIZONTAL_ACCURACY, "actHorizontalAccuracy");
		BIO_RESULT.put(KEY_ACT_HORIZONTAL_ACCURACY_UNIT, "actHorizontalAccuracyUnit");
		BIO_RESULT.put(KEY_ACT_HORIZONTAL_COLLECT_METHOD, "actHorizontalCollectMethod");
		BIO_RESULT.put(KEY_ACT_HORIZONTAL_DATUM_NAME, "actHorizontalDatumName");
		BIO_RESULT.put(KEY_ASSEMBLAGE_SAMPLED_NAME, "assemblageSampledName");
		BIO_RESULT.put(KEY_ACT_COLLECTION_DURATION, "actCollectionDuration");
		BIO_RESULT.put(KEY_ACT_COLLECTION_DURATION_UNIT, "actCollectionDurationUnit");
		BIO_RESULT.put(KEY_ACT_SAM_COMPNT_NAME, "actSamCompntName");
		BIO_RESULT.put(KEY_ACT_SAM_COMPNT_PLACE_IN_SERIES, BigDecimal.valueOf(44));
		BIO_RESULT.put(KEY_ACT_REACH_LENGTH, "actReachLength");
		BIO_RESULT.put(KEY_ACT_REACH_LENGTH_UNIT, "actReachLengthUnit");
		BIO_RESULT.put(KEY_ACT_REACH_WIDTH, "actReachWidth");
		BIO_RESULT.put(KEY_ACT_REACH_WIDTH_UNIT, "actReachWidthUnit");
		BIO_RESULT.put(KEY_ACT_PASS_COUNT, BigDecimal.valueOf(11));
		BIO_RESULT.put(KEY_NET_TYPE_NAME, "netTypeName");
		BIO_RESULT.put(KEY_ACT_NET_SURFACE_AREA, "actNetSurfaceArea");
		BIO_RESULT.put(KEY_ACT_NET_SURFACE_AREA_UNIT, "actNetSurfaceAreaUnit");
		BIO_RESULT.put(KEY_ACT_NET_MESH_SIZE, "actNetMeshSize");
		BIO_RESULT.put(KEY_ACT_NET_MESH_SIZE_UNIT, "actNetMeshSizeUnit");
		BIO_RESULT.put(KEY_ACT_BOAT_SPEED, "actBoatSpeed");
		BIO_RESULT.put(KEY_ACT_BOAT_SPEED_UNIT, "actBoatSpeedUnit");
		BIO_RESULT.put(KEY_ACT_CURRENT_SPEED, "actCurrentSpeed");
		BIO_RESULT.put(KEY_ACT_CURRENT_SPEED_UNIT, "actCurrentSpeedUnit");
		BIO_RESULT.put(KEY_TOXICITY_TEST_TYPE_NAME, "toxicityTestTypeName");
		BIO_RESULT.put(KEY_ACT_SAM_COLLECT_METH_QUAL_TYPE, "actSamCollectMethQualType");
		BIO_RESULT.put(KEY_ACT_SAM_COLLECT_METH_DESC, "actSamCollectMethDesc");
		BIO_RESULT.put(KEY_ACT_SAM_COLLECT_EQUIP_COMMENTS, "actSamCollectEquipComments");
		BIO_RESULT.put(KEY_ACT_SAM_PREP_METH_ID, "actSamPrepMethId");
		BIO_RESULT.put(KEY_ACT_SAM_PREP_METH_CONTEXT, "actSamPrepMethContext");
		BIO_RESULT.put(KEY_ACT_SAM_PREP_METH_NAME, "actSamPrepMethName");
		BIO_RESULT.put(KEY_ACT_SAM_PREP_METH_QUAL_TYPE, "actSamPrepMethQualType");
		BIO_RESULT.put(KEY_ACT_SAM_PREP_METH_DESC, "actSamPrepMethDesc");
		BIO_RESULT.put(KEY_SAMPLE_CONTAINER_TYPE, "sampleContainerType");
		BIO_RESULT.put(KEY_SAMPLE_CONTAINER_COLOR, "sampleContainerColor");
		BIO_RESULT.put(KEY_ACT_SAM_CHEMICAL_PRESERVATIVE, "actSamChemicalPreservative");
		BIO_RESULT.put(KEY_THERMAL_PRESERVATIVE_NAME, "thermalPreservativeName");
		BIO_RESULT.put(KEY_ACT_SAM_TRANSPORT_STORAGE_DESC, "actSamTransportStorageDesc");

		BIO_RESULT.put(KEY_RES_DATA_LOGGER_LINE, "resDataLoggerLine");
		BIO_RESULT.put(KEY_METHOD_SPECIFICATION_NAME, "methodSpecificationName");
		BIO_RESULT.put(KEY_RES_MEASURE_BIAS, "resMeasureBias");
		BIO_RESULT.put(KEY_RES_MEASURE_CONF_INTERVAL, "resMeasureConfInterval");
		BIO_RESULT.put(KEY_RES_MEASURE_UPPER_CONF_LIMIT, "resMeasureUpperConfLimit");
		BIO_RESULT.put(KEY_RES_MEASURE_LOWER_CONF_LIMIT, "resMeasureLowerConfLimit");
		BIO_RESULT.put(KEY_RES_SAMPLING_POINT_NAME, "resSamplingPointName");
		BIO_RESULT.put(KEY_BIOLOGICAL_INTENT, "biologicalIntent");
		BIO_RESULT.put(KEY_RES_BIO_INDIVIDUAL_ID, "resBioIndividualId");
		BIO_RESULT.put(KEY_UNIDENTIFIEDSPECIESIDENTIFIER, "unidentifiedSpeciesIdentifier");
		BIO_RESULT.put(KEY_RES_GROUP_SUMMARY_CT_WT, "resGroupSummaryCtWt");
		BIO_RESULT.put(KEY_RES_GROUP_SUMMARY_CT_WT_UNIT, "resGroupSummaryCtWtUnit");
		BIO_RESULT.put(KEY_CELL_FORM_NAME, "cellFormName");
		BIO_RESULT.put(KEY_CELL_SHAPE_NAME, "cellShapeName");
		BIO_RESULT.put(KEY_HABIT_NAME, "habitName");
		BIO_RESULT.put(KEY_VOLT_NAME, "voltName");
		BIO_RESULT.put(KEY_RTDET_POLLUTION_TOLERANCE, "rtdetPollutionTolerance");
		BIO_RESULT.put(KEY_RTDET_POLLUTION_TOLERNCE_SCALE, "rtdetPollutionTolernceScale");
		BIO_RESULT.put(KEY_RTDET_TROPHIC_LEVEL, "rtdetTrophicLevel");
		BIO_RESULT.put(KEY_RTFGRP_FUNCTIONAL_FEEDING_GRP, "rtfgrpFunctionalFeedingGrp");
		BIO_RESULT.put(KEY_TAXON_CITATION_TITLE, "taxonCitationTitle");
		BIO_RESULT.put(KEY_TAXON_CITATION_CREATOR, "taxonCitationCreator");
		BIO_RESULT.put(KEY_TAXON_CITATION_SUBJECT, "taxonCitationSubject");
		BIO_RESULT.put(KEY_TAXON_CITATION_PUBLISHER, "taxonCitationPublisher");
		BIO_RESULT.put(KEY_TAXON_CITATION_DATE, "taxonCitationDate");
		BIO_RESULT.put(KEY_TAXON_CITATION_ID, "taxonCitationId");
		BIO_RESULT.put(KEY_FCDSC_NAME, "fcdscName");
		BIO_RESULT.put(KEY_FREQUENCY_CLASS_UNIT, "frequencyClassUnit");
		BIO_RESULT.put(KEY_FCDSC_LOWER_BOUND, BigDecimal.valueOf(88));
		BIO_RESULT.put(KEY_FCDSC_UPPER_BOUND, BigDecimal.valueOf(99));
		BIO_RESULT.put(KEY_ANLMTH_QUAL_TYPE, "anlmthQualType");
		BIO_RESULT.put(KEY_ANALYSIS_START_TIME, "analysisStartTime");
		BIO_RESULT.put(KEY_ANALYSIS_START_TIMEZONE, "analysisStartTimezone");
		BIO_RESULT.put(KEY_ANALYSIS_END_DATE, "analysisEndDate");
		BIO_RESULT.put(KEY_ANALYSIS_END_TIME, "analysisEndTime");
		BIO_RESULT.put(KEY_ANALYSIS_END_TIMEZONE, "analysisEndTimezone");
		BIO_RESULT.put(KEY_RLCOM_CD, "rlcomCd");
		BIO_RESULT.put(KEY_RES_LAB_ACCRED_YN, "resLabAccredYN");
		BIO_RESULT.put(KEY_RES_LAB_ACCRED_AUTHORITY, "resLabAccredAuthority");
		BIO_RESULT.put(KEY_RES_TAXONOMIST_ACCRED_YN, "resTaxonomistAccredYN");
		BIO_RESULT.put(KEY_RES_TAXONOMIST_ACCRED_AUTHORTY, "resTaxonomistAccredAuthorty");
		BIO_RESULT.put(KEY_PREP_METHOD_ID, "prepMethodId");
		BIO_RESULT.put(KEY_PREP_METHOD_CONTEXT, "prepMethodContext");
		BIO_RESULT.put(KEY_PREP_METHOD_NAME, "prepMethodName");
		BIO_RESULT.put(KEY_PREP_METHOD_QUAL_TYPE, "prepMethodQualType");
		BIO_RESULT.put(KEY_PREP_METHOD_DESC, "prepMethodDesc");
		BIO_RESULT.put(KEY_PREP_START_TIME, "prepStartTime");
		BIO_RESULT.put(KEY_PREP_START_TIMEZONE, "prepStartTimezone");
		BIO_RESULT.put(KEY_PREP_END_DATE, "prepEndDate");
		BIO_RESULT.put(KEY_PREP_END_TIME, "prepEndTime");
		BIO_RESULT.put(KEY_PREP_END_TIMEZONE, "prepEndTimezone");
		BIO_RESULT.put(KEY_PREP_DILUTION_FACTOR, "prepDilutionFactor");
	}

	private TestBioResultMap() {
	}
}
