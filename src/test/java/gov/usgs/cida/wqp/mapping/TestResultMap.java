package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.ActivityColumn.*;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_LAST_UPDATED;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.KEY_PROJECT_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.*;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_STATION_NAME;
import static gov.usgs.cida.wqp.mapping.TestResDetectQntLmtMap.BASE_RES_DETECT_QNT_LMT;
import static gov.usgs.cida.wqp.mapping.TestStationMap.BASE_STATION;
import static gov.usgs.cida.wqp.mapping.TestActivityMap.*;
import static gov.usgs.cida.wqp.mapping.TestLabSamplePrepMap.BASE_LAB_SAMPLE_PREP;
import static gov.usgs.cida.wqp.mapping.TestFreqClassInfoMap.BASE_FREQ_CLASS_INFO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestResultMap {

	private TestResultMap() {}

	public static final Map<String, Object> BASE_RESULT;
	static {
		BASE_RESULT = new LinkedHashMap<String, Object>();
		BASE_RESULT.put(KEY_RESULT_ID, BigDecimal.valueOf(42));
		BASE_RESULT.put(KEY_RESULT_DETECTION_CONDITION_TX, "resultDetectionConditionTx");
		BASE_RESULT.put(KEY_CHARACTERISTIC_NAME, "characteristicName");
		BASE_RESULT.put(KEY_CHARACTERISTIC_TYPE, "characteristicType");
		BASE_RESULT.put(KEY_SAMPLE_FRACTION_TYPE, "sampleFractionType");
		BASE_RESULT.put(KEY_RESULT_MEASURE_VALUE, "resultMeasureValue");
		BASE_RESULT.put(KEY_RESULT_UNIT, "resultUnit");
		BASE_RESULT.put(KEY_RESULT_MEAS_QUAL_CODE, "resultMeasQualCode");
		BASE_RESULT.put(KEY_RESULT_VALUE_STATUS, "resultValueStatus");
		BASE_RESULT.put(KEY_STATISTIC_TYPE, "statisticType");
		BASE_RESULT.put(KEY_RESULT_VALUE_TYPE, "resultValueType");
		BASE_RESULT.put(KEY_WEIGHT_BASIS_TYPE, "weightBasisType");
		BASE_RESULT.put(KEY_DURATION_BASIS, "durationBasis");
		BASE_RESULT.put(KEY_TEMPERATURE_BASIS_LEVEL, "temperatureBasisLevel");
		BASE_RESULT.put(KEY_PARTICLE_SIZE, "particleSize");
		BASE_RESULT.put(KEY_PRECISION, "precision");
		BASE_RESULT.put(KEY_RESULT_COMMENT, "resultComment");
		BASE_RESULT.put(KEY_P_CODE, "99999");
		BASE_RESULT.put(KEY_RESULT_DEPTH_MEAS_VALUE, "resultDepthMeasValue");
		BASE_RESULT.put(KEY_RESULT_DEPTH_MEAS_UNIT_CODE, "resultDepthMeasUnitCode");
		BASE_RESULT.put(KEY_RESULT_DEPTH_ALT_REF_PT_TXT, "resultDepthAltRefPtTxt");
		BASE_RESULT.put(KEY_SAMPLE_TISSUE_TAXONOMIC_NAME, "sampleTissueTaxonomicName");
		BASE_RESULT.put(KEY_SAMPLE_TISSUE_ANATOMY_NAME, "sampleTissueAnatomyName");
		BASE_RESULT.put(KEY_ANALYTICAL_PROCEDURE_ID, "analyticalProcedureId");
		BASE_RESULT.put(KEY_ANALYTICAL_PROCEDURE_SOURCE, "analyticalProcedureSource");
		BASE_RESULT.put(KEY_ANALYTICAL_METHOD_NAME, "analyticalMethodName");
		BASE_RESULT.put(KEY_ANALYTICAL_METHOD_CITATION, "analyticalMethodCitation");
		BASE_RESULT.put(KEY_LAB_NAME, "labName");
		BASE_RESULT.put(KEY_ANALYSIS_START_DATE, "analysisStartDate");
		BASE_RESULT.put(KEY_LAB_REMARK, "labRemark");
	}

	public static final Map<String, Object> EXTENDED_RESULT;
	static {
		EXTENDED_RESULT = new LinkedHashMap<String, Object>();
		EXTENDED_RESULT.putAll(BASE_RESULT);
		EXTENDED_RESULT.put(KEY_EXTERNAL_RESULT_ID, "STORET-42");
		EXTENDED_RESULT.put(KEY_RES_DATA_LOGGER_LINE, "resDataLoggerLine");
		EXTENDED_RESULT.put(KEY_RES_MEASURE_BIAS, "resMeasureBias");
		EXTENDED_RESULT.put(KEY_RES_MEASURE_CONF_INTERVAL, "resMeasureConfInterval");
		EXTENDED_RESULT.put(KEY_RES_MEASURE_UPPER_CONF_LIMIT, "resMeasureUpperConfLimit");
		EXTENDED_RESULT.put(KEY_RES_MEASURE_LOWER_CONF_LIMIT, "resMeasureLowerConfLimit");
		EXTENDED_RESULT.put(KEY_RES_SAMPLING_POINT_NAME, "resSamplingPointName");
		EXTENDED_RESULT.put(KEY_BIOLOGICAL_INTENT, "biologicalIntent");
		EXTENDED_RESULT.put(KEY_RES_BIO_INDIVIDUAL_ID, "resBioIndividualId");
		EXTENDED_RESULT.put(KEY_UNIDENTIFIEDSPECIESIDENTIFIER, "unidentifiedSpeciesIdentifier");
		EXTENDED_RESULT.put(KEY_RES_GROUP_SUMMARY_CT_WT, "resGroupSummaryCtWt");
		EXTENDED_RESULT.put(KEY_RES_GROUP_SUMMARY_CT_WT_UNIT, "resGroupSummaryCtWtUnit");
		EXTENDED_RESULT.put(KEY_CELL_FORM_NAME, "cellFormName");
		EXTENDED_RESULT.put(KEY_CELL_SHAPE_NAME, "cellShapeName");
		EXTENDED_RESULT.put(KEY_HABIT_NAME, "habitName");
		EXTENDED_RESULT.put(KEY_RTDET_POLLUTION_TOLERANCE, "rtdetPollutionTolerance");
		EXTENDED_RESULT.put(KEY_RTDET_POLLUTION_TOLERNCE_SCALE, "rtdetPollutionTolernceScale");
		EXTENDED_RESULT.put(KEY_RTDET_TROPHIC_LEVEL, "rtdetTrophicLevel");
		EXTENDED_RESULT.put(KEY_RTFGRP_FUNCTIONAL_FEEDING_GRP, "rtfgrpFunctionalFeedingGrp");
		EXTENDED_RESULT.put(KEY_TAXON_CITATION_TITLE, "taxonCitationTitle");
		EXTENDED_RESULT.put(KEY_TAXON_CITATION_CREATOR, "taxonCitationCreator");
		EXTENDED_RESULT.put(KEY_TAXON_CITATION_SUBJECT, "taxonCitationSubject");
		EXTENDED_RESULT.put(KEY_TAXON_CITATION_PUBLISHER, "taxonCitationPublisher");
		EXTENDED_RESULT.put(KEY_TAXON_CITATION_DATE, "taxonCitationDate");
		EXTENDED_RESULT.put(KEY_TAXON_CITATION_ID, "taxonCitationId");
		EXTENDED_RESULT.put(KEY_ANLMTH_QUAL_TYPE, "anlmthQualType");
		EXTENDED_RESULT.put(KEY_ANALYSIS_START_TIME, "analysisStartTime");
		EXTENDED_RESULT.put(KEY_ANALYSIS_START_TIMEZONE, "analysisStartTimezone");
		EXTENDED_RESULT.put(KEY_ANALYSIS_END_DATE, "analysisEndDate");
		EXTENDED_RESULT.put(KEY_ANALYSIS_END_TIME, "analysisEndTime");
		EXTENDED_RESULT.put(KEY_ANALYSIS_END_TIMEZONE, "analysisEndTimezone");
		EXTENDED_RESULT.put(KEY_RLCOM_CD, "rlcomCd");
		EXTENDED_RESULT.put(KEY_RES_LAB_ACCRED_YN, "resLabAccredYN");
		EXTENDED_RESULT.put(KEY_RES_LAB_ACCRED_AUTHORITY, "resLabAccredAuthority");
		EXTENDED_RESULT.put(KEY_RES_TAXONOMIST_ACCRED_YN, "resTaxonomistAccredYN");
		EXTENDED_RESULT.put(KEY_RES_TAXONOMIST_ACCRED_AUTHORTY, "resTaxonomistAccredAuthorty");
	}

	public static final Map<String, Object> RESULT_OBJECT_COLUMNS;
	static {
		RESULT_OBJECT_COLUMNS = new LinkedHashMap<String, Object>();
		RESULT_OBJECT_COLUMNS.put(KEY_RESULT_OBJECT_NAME, "resultObjectName");
		RESULT_OBJECT_COLUMNS.put(KEY_RESULT_OBJECT_TYPE, "resultObjectType");
		RESULT_OBJECT_COLUMNS.put(KEY_RESULT_FILE_URL, "/organizations/organization/activities/activity/results/STORET-42/files");
	}

	public static final Map<String, Object> RESULT_FREQ_CLASS_COLUMNS;
	static {
		RESULT_FREQ_CLASS_COLUMNS = new LinkedHashMap<String, Object>();
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_CODE_1, "freqClassCode1");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_CODE_2, "freqClassCode2");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_CODE_3, "freqClassCode3");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_UNIT_1, "freqClassUnit1");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_UNIT_2, "freqClassUnit2");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_UNIT_3, "freqClassUnit3");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_LOWER_BOUND_1, "freqClassLowerBound1");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_LOWER_BOUND_2, "freqClassLowerBound2");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_LOWER_BOUND_3, "freqClassLowerBound3");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_UPPER_BOUND_1, "freqClassUpperBound1");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_UPPER_BOUND_2, "freqClassUpperBound2");
		RESULT_FREQ_CLASS_COLUMNS.put(KEY_FREQUENCY_CLASS_UPPER_BOUND_3, "freqClassUpperBound3");
	}

	public static final Map<String, Object> BASE_RESULT_BIO;
	static {
		BASE_RESULT_BIO = new LinkedHashMap<String, Object>();
		BASE_RESULT_BIO.putAll(EXTENDED_ACTIVITY);
		BASE_RESULT_BIO.putAll(EXTENDED_RESULT);
		BASE_RESULT_BIO.putAll(BASE_RES_DETECT_QNT_LMT);
		BASE_RESULT_BIO.putAll(BASE_LAB_SAMPLE_PREP);
	}

	public static final Map<String, Object> BIO_RESULT;
	static {
		BIO_RESULT = new LinkedHashMap<String, Object>();
		BIO_RESULT.putAll(BASE_RESULT_BIO);
		BIO_RESULT.put(KEY_VOLT_NAME, "voltName");
		BIO_RESULT.put(KEY_METHOD_SPECIFICATION_NAME, "methodSpecificationName");
		BIO_RESULT.put(KEY_FCDSC_NAME, "fcdscName");
		BIO_RESULT.put(KEY_FREQUENCY_CLASS_UNIT, "frequencyClassUnit");
		BIO_RESULT.put(KEY_FCDSC_LOWER_BOUND, BigDecimal.valueOf(88));
		BIO_RESULT.put(KEY_FCDSC_UPPER_BOUND, BigDecimal.valueOf(99));
	}

	public static final Map<String, Object> BASE_NARROW;
	static {
		BASE_NARROW = new LinkedHashMap<String, Object>();
		BASE_NARROW.putAll(BASE_STATION);
		BASE_NARROW.put(KEY_ACTIVITY, "activity");
		BASE_NARROW.put(KEY_EVENT_DATE, "1999-12-28");
		BASE_NARROW.put(KEY_ACTIVITY_START_TIME, "activityStartTime");
		BASE_NARROW.put(KEY_ACT_START_TIME_ZONE, "actStartTimeZone");
		BASE_NARROW.putAll(EXTENDED_RESULT);
		BASE_NARROW.put(KEY_ANALYTICAL_METHOD_NAME, "analyticalMethodName");
	}

	public static final Map<String, Object> EXTENDED_NARROW;
	static {
		EXTENDED_NARROW = new LinkedHashMap<String, Object>();
		EXTENDED_NARROW.putAll(BASE_NARROW);
		EXTENDED_NARROW.put(KEY_VOLT_NAME, "voltName");
		EXTENDED_NARROW.put(KEY_ANALYTICAL_METHOD, "https://analyticalMethod");
		EXTENDED_NARROW.put(KEY_RES_DETECT_QNT_LMT_URL, "/activities/activity/results/STORET-42/resdetectqntlmts");
		EXTENDED_NARROW.put(KEY_LAB_SAMPLE_PREP_URL, "/labSamplePrepUrl");
	}

	public static final Map<String, Object> NARROW_RESULT;
	static {
		NARROW_RESULT = new LinkedHashMap<String, Object>();
		NARROW_RESULT.putAll(EXTENDED_NARROW);
		NARROW_RESULT.put(KEY_VOLT_NAME, "voltName");
		NARROW_RESULT.put(KEY_METHOD_SPECIFICATION_NAME, "methodSpecificationName");
	}

	public static final Map<String, Object> PC_RESULT;
	static {
		PC_RESULT = new LinkedHashMap<String, Object>();
		PC_RESULT.putAll(BASE_ACTIVITY);
		PC_RESULT.putAll(BASE_RESULT);
		PC_RESULT.putAll(BASE_RES_DETECT_QNT_LMT);
		PC_RESULT.put(KEY_ANALYSIS_PREP_DATE_TX, "analysisPrepDateTx");
	}

	public static final Map<String, Object> RESULT_PHYS_CHEM;
	static {
		RESULT_PHYS_CHEM = new LinkedHashMap<String, Object>();
		RESULT_PHYS_CHEM.putAll(PC_RESULT);
		RESULT_PHYS_CHEM.put(KEY_METHOD_SPECIATION_NAME, "methodSpecificationName");
		RESULT_PHYS_CHEM.put(KEY_ACTIVITY_RELATIVE_DEPTH, "activityRelativeDepthName");
		RESULT_PHYS_CHEM.put(KEY_PROJECT_NAME, "Some Project");
		RESULT_PHYS_CHEM.put(KEY_STATION_NAME, "stationName");
		RESULT_PHYS_CHEM.putAll(BASE_LOCATION_COLUMNS);
		RESULT_PHYS_CHEM.put(KEY_EXTERNAL_RESULT_ID, "STORET-42");
		RESULT_PHYS_CHEM.put(KEY_RES_MEASURE_BIAS, "resMeasureBias");
		RESULT_PHYS_CHEM.put(KEY_RES_MEASURE_CONF_INTERVAL, "resMeasureConfInterval");
		RESULT_PHYS_CHEM.put(KEY_RES_MEASURE_UPPER_CONF_LIMIT, "resMeasureUpperConfLimit");
		RESULT_PHYS_CHEM.put(KEY_RES_MEASURE_LOWER_CONF_LIMIT, "resMeasureLowerConfLimit");
		RESULT_PHYS_CHEM.put(KEY_METHOD_URL, "https://analyticalMethod");
		RESULT_PHYS_CHEM.put(KEY_RES_DETECT_QNT_LMT_URL, "/activities/activity/results/STORET-42/resdetectqntlmts");
		RESULT_PHYS_CHEM.put(KEY_LAB_SAMPLE_PREP_URL, "/labSamplePrepUrl");
		RESULT_PHYS_CHEM.putAll(RESULT_OBJECT_COLUMNS);
		RESULT_PHYS_CHEM.put(KEY_METHOD_SPECIATION_NAME, "methodSpecificationName");
		RESULT_PHYS_CHEM.put(KEY_LAST_UPDATED, Timestamp.valueOf("2018-08-20 15:22:33.0"));
	}

	public static final Map<String, Object> RESULT_PRIMARY;
	static {
		RESULT_PRIMARY = new LinkedHashMap<String, Object>();
		RESULT_PRIMARY.putAll(EXTENDED_NARROW);
		RESULT_PRIMARY.put(KEY_METHOD_SPECIATION_NAME, "methodSpecificationName");
		RESULT_PRIMARY.put(KEY_VOLTINISM_NAME, "voltName");
		RESULT_PRIMARY.putAll(BASE_LOCATION_COLUMNS);
		RESULT_PRIMARY.put(KEY_SAMPLE_MEDIA, "sampleMedia");
		RESULT_PRIMARY.put(KEY_ACTIVITY_PROJECTS, "projectId");
		RESULT_PRIMARY.put(KEY_PROJECT_NAME, "Some Project");
		RESULT_PRIMARY.putAll(RESULT_OBJECT_COLUMNS);
		RESULT_PRIMARY.putAll(BASE_RES_DETECT_QNT_LMT);
		RESULT_PRIMARY.putAll(BASE_FREQ_CLASS_INFO);
		RESULT_PRIMARY.put(KEY_LAST_UPDATED, Timestamp.valueOf("2018-08-20 15:22:33.0"));
	}

	public static final Map<String, Object> RESULT_BROAD;
	static {
		RESULT_BROAD = new LinkedHashMap<String, Object>();
		RESULT_BROAD.putAll(BASE_RESULT_BIO);
		RESULT_BROAD.put(KEY_METHOD_SPECIATION_NAME, "methodSpecificationName");
		RESULT_BROAD.put(KEY_VOLTINISM_NAME, "voltName");
		RESULT_BROAD.put(KEY_PROJECT_NAME, "Some Project");
		RESULT_BROAD.put(KEY_STATION_NAME, "stationName");
		RESULT_BROAD.put(KEY_METHOD_URL, "https://analyticalMethod");
		RESULT_BROAD.put(KEY_LAB_SAMPLE_PREP_URL, "/labSamplePrepUrl");
		RESULT_BROAD.put(KEY_RES_DETECT_QNT_LMT_URL, "/activities/activity/results/STORET-42/resdetectqntlmts");
		RESULT_BROAD.putAll(RESULT_OBJECT_COLUMNS);
		RESULT_BROAD.putAll(RESULT_FREQ_CLASS_COLUMNS);
		RESULT_BROAD.put(KEY_LAST_UPDATED, Timestamp.valueOf("2018-08-20 15:22:33.0"));
	}
}
