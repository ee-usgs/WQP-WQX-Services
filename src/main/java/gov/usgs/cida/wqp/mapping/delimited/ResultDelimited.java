package gov.usgs.cida.wqp.mapping.delimited;

import static gov.usgs.cida.wqp.mapping.BaseColumn.DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYSIS_END_DATE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYSIS_END_TIME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYSIS_END_TIMEZONE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYSIS_PREP_DATE_TX;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYSIS_START_DATE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYSIS_START_TIME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYSIS_START_TIMEZONE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYTICAL_METHOD_CITATION;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYTICAL_METHOD_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYTICAL_PROCEDURE_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANALYTICAL_PROCEDURE_SOURCE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.ANLMTH_QUAL_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.BIOLOGICAL_INTENT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.CELL_FORM_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.CELL_SHAPE_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.DETECTION_LIMIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.DETECTION_LIMIT_DESC;
import static gov.usgs.cida.wqp.mapping.ResultColumn.DETECTION_LIMIT_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.DURATION_BASIS;
import static gov.usgs.cida.wqp.mapping.ResultColumn.FCDSC_LOWER_BOUND;
import static gov.usgs.cida.wqp.mapping.ResultColumn.FCDSC_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.FCDSC_UPPER_BOUND;
import static gov.usgs.cida.wqp.mapping.ResultColumn.FREQUENCY_CLASS_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.HABIT_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.LAB_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.LAB_REMARK;
import static gov.usgs.cida.wqp.mapping.ResultColumn.METHOD_SPECIFICATION_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PARTICLE_SIZE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PRECISION;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_DILUTION_FACTOR;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_END_DATE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_END_TIME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_END_TIMEZONE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_METHOD_CONTEXT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_METHOD_DESC;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_METHOD_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_METHOD_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_METHOD_QUAL_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_START_TIME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.PREP_START_TIMEZONE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.P_CODE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_COMMENT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_DEPTH_ALT_REF_PT_TXT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_DEPTH_MEAS_UNIT_CODE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_DEPTH_MEAS_VALUE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_DETECTION_CONDITION_TX;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_MEASURE_VALUE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_MEAS_QUAL_CODE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_VALUE_STATUS;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RESULT_VALUE_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_BIO_INDIVIDUAL_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_DATA_LOGGER_LINE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_GROUP_SUMMARY_CT_WT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_GROUP_SUMMARY_CT_WT_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_LAB_ACCRED_AUTHORITY;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_LAB_ACCRED_YN;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_MEASURE_BIAS;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_MEASURE_CONF_INTERVAL;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_MEASURE_LOWER_CONF_LIMIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_MEASURE_UPPER_CONF_LIMIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_SAMPLING_POINT_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_TAXONOMIST_ACCRED_AUTHORTY;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RES_TAXONOMIST_ACCRED_YN;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RLCOM_CD;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RTDET_POLLUTION_TOLERANCE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RTDET_POLLUTION_TOLERNCE_SCALE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RTDET_TROPHIC_LEVEL;
import static gov.usgs.cida.wqp.mapping.ResultColumn.RTFGRP_FUNCTIONAL_FEEDING_GRP;
import static gov.usgs.cida.wqp.mapping.ResultColumn.SAMPLE_FRACTION_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.SAMPLE_TISSUE_ANATOMY_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.SAMPLE_TISSUE_TAXONOMIC_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.STATISTIC_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.TAXON_CITATION_CREATOR;
import static gov.usgs.cida.wqp.mapping.ResultColumn.TAXON_CITATION_DATE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.TAXON_CITATION_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.TAXON_CITATION_PUBLISHER;
import static gov.usgs.cida.wqp.mapping.ResultColumn.TAXON_CITATION_SUBJECT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.TAXON_CITATION_TITLE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.TEMPERATURE_BASIS_LEVEL;
import static gov.usgs.cida.wqp.mapping.ResultColumn.UNIDENTIFIEDSPECIESIDENTIFIER;
import static gov.usgs.cida.wqp.mapping.ResultColumn.VOLT_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.WEIGHT_BASIS_TYPE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ANALYSIS_END_DATE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ANALYSIS_END_TIME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ANALYSIS_START_DATE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ANALYSIS_START_TIME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ANALYTICAL_METHOD;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_BIAS;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_BIOLOGICAL_INDIVIDUAL_ID;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_BIOLOGICAL_INTENT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_CELL_FORM;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_CELL_SHAPE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_CHAR_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_CONFIDENCE_INTERVAL;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_DATA_LOGGER_LINE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_DATA_QUALITY;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_DETECTION_CONDITION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_DETECTION_LIMIT_TYPE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_DETECTION_LIMIT_VALUE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_FREQUENCE_CLASS_DESCRIPTOR_UNIT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_FREQUENCY_CLASS_DESCRIPTOR;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_FUNCTIONAL_FEEDING_GROUP;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_GROUP_SUMMARY_COUNT_WEIGHT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_HABIT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_LAB_ACCREDITATION_AUTHORITY;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_LAB_ACCREDITATION_INDICATOR;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_LAB_COMMENT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_LAB_COMMENT_CODE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_LAB_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_LAB_SAMPLE_PREP_METHOD;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_LOWER_CLASS_BOUND;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_LOWER_CONFIDENCE_LIMIT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MEASURE_QUALIFIER;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MEASURE_UNIT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MEASURE_VALUE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_METHOD_CONTEXT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_METHOD_DESCRIPTION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_METHOD_ID;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_METHOD_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_METHOD_QUALIFIER_TYPE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_METHOD_SPECIFICATION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_PARTICLE_SIZE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_POLLUTION_TOLERANCE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_PRECISION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_PREP_END_DATE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_PREP_END_TIME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_PREP_START_DATE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_PREP_START_TIME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_P_CODE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_CREATOR;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_DATE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_ID;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_PUBLISHER;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_SUBJECT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_TITLE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESULT_COMMENT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESULT_DEPTH;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESULT_DETH_REFERENCE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESULT_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESULT_MEASURE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESULT_MEASURE_VALUE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESULT_SAMPLING_POINT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_SAMPLE_FRACTION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_STATISTICAL_BASE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_STATUS_ID;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_SUBSTITUTION_DILUTION_FACTOR;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TAXONIMC_DETAIL_CITATION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TAXONOMIST_ACCREDITATION_AUTHORTY;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TAXONOMIST_ACCREDITATION_INDICATOR;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TAXON_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TEMP_BASIS;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TIME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TIME_BASIS;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TIME_ZONE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TISSUE_ANATOMY;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TOLERANCE_SCALE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_TROPHIC_LEVEL;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_UNIDENTIFIED_SPECIES_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_UPPER_CLASS_BOUND;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_UPPER_CONFIDENCE_LIMIT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_VALUE_TYPE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_VOLTISM;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_WEIGHT_BASIS;

import java.util.LinkedHashMap;
import java.util.Map;

import gov.usgs.cida.wqp.mapping.ColumnProfile;

public class ResultDelimited extends BaseDelimited {

	//Column Headings for the Keys
	public static final String VALUE_RES_DATA_LOGGER_LINE = WQX_DATA_LOGGER_LINE;


	public static final String VALUE_RESULT_DETECTION_CONDITION_TX = WQX_DETECTION_CONDITION;


	public static final String VALUE_METHOD_SPECIFICATION_NAME = WQX_METHOD_SPECIFICATION;


	public static final String VALUE_RESULT_IDENTIFIER = WQX_RESULT_IDENTIFIER;
	public static final String VALUE_CHARACTERISTIC_NAME = WQX_CHAR_NAME;
	public static final String VALUE_SAMPLE_FRACTION_TYPE = WQX_SAMPLE_FRACTION;
	public static final String VALUE_RESULT_MEASURE_VALUE = WQX_RESULT_MEASURE_VALUE;
	public static final String VALUE_RESULT_UNIT = WQX_RESULT_MEASURE + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_RESULT_MEAS_QUAL_CODE = WQX_MEASURE_QUALIFIER;
	public static final String VALUE_RESULT_VALUE_STATUS = WQX_STATUS_ID;
	public static final String VALUE_STATISTIC_TYPE = WQX_STATISTICAL_BASE;
	public static final String VALUE_RESULT_VALUE_TYPE = WQX_VALUE_TYPE;
	public static final String VALUE_WEIGHT_BASIS_TYPE = WQX_WEIGHT_BASIS;
	public static final String VALUE_DURATION_BASIS = WQX_TIME_BASIS;
	public static final String VALUE_TEMPERATURE_BASIS_LEVEL = WQX_TEMP_BASIS;
	public static final String VALUE_PARTICLE_SIZE = WQX_PARTICLE_SIZE;
	public static final String VALUE_PRECISION = WQX_PRECISION;


	public static final String VALUE_RES_MEASURE_BIAS = WQX_DATA_QUALITY + VAL_DEL + WQX_BIAS;
	public static final String VALUE_RES_MEASURE_CONF_INTERVAL = WQX_CONFIDENCE_INTERVAL;
	public static final String VALUE_RES_MEASURE_UPPER_CONF_LIMIT = WQX_UPPER_CONFIDENCE_LIMIT;
	public static final String VALUE_RES_MEASURE_LOWER_CONF_LIMIT = WQX_LOWER_CONFIDENCE_LIMIT;


	public static final String VALUE_RESULT_COMMENT = WQX_RESULT_COMMENT;
	public static final String VALUE_P_CODE = WQX_P_CODE;
	public static final String VALUE_RESULT_DEPTH_MEAS_VALUE = WQX_RESULT_DEPTH + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_RESULT_DEPTH_MEAS_UNIT_CODE = WQX_RESULT_DEPTH + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_RESULT_DEPTH_ALT_REF_PT_TXT = WQX_RESULT_DETH_REFERENCE;


	public static final String VALUE_RES_SAMPLING_POINT_NAME = WQX_RESULT_SAMPLING_POINT;
	public static final String VALUE_BIOLOGICAL_INTENT = WQX_BIOLOGICAL_INTENT;
	public static final String VALUE_RES_BIO_INDIVIDUAL_ID = WQX_BIOLOGICAL_INDIVIDUAL_ID;


	public static final String VALUE_SAMPLE_TISSUE_TAXONOMIC_NAME = WQX_TAXON_NAME;


	public static final String VALUE_UNIDENTIFIEDSPECIESIDENTIFIER = WQX_UNIDENTIFIED_SPECIES_IDENTIFIER;


	public static final String VALUE_SAMPLE_TISSUE_ANATOMY_NAME = WQX_TISSUE_ANATOMY;


	public static final String VALUE_RES_GROUP_SUMMARY_CT_WT = WQX_GROUP_SUMMARY_COUNT_WEIGHT + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_RES_GROUP_SUMMARY_CT_WT_UNIT = WQX_GROUP_SUMMARY_COUNT_WEIGHT + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_CELL_FORM_NAME = WQX_CELL_FORM;
	public static final String VALUE_CELL_SHAPE_NAME = WQX_CELL_SHAPE;
	public static final String VALUE_HABIT_NAME = WQX_HABIT;
	public static final String VALUE_VOLT_NAME = WQX_VOLTISM;
	public static final String VALUE_RTDET_POLLUTION_TOLERANCE = WQX_POLLUTION_TOLERANCE;
	public static final String VALUE_RTDET_POLLUTION_TOLERNCE_SCALE = WQX_TOLERANCE_SCALE;
	public static final String VALUE_RTDET_TROPHIC_LEVEL = WQX_TROPHIC_LEVEL;
	public static final String VALUE_RTFGRP_FUNCTIONAL_FEEDING_GRP = WQX_FUNCTIONAL_FEEDING_GROUP;
	public static final String VALUE_TAXON_CITATION_TITLE = WQX_TAXONIMC_DETAIL_CITATION + VAL_DEL + WQX_RESOURCE_TITLE;
	public static final String VALUE_TAXON_CITATION_CREATOR = WQX_TAXONIMC_DETAIL_CITATION + VAL_DEL + WQX_RESOURCE_CREATOR;
	public static final String VALUE_TAXON_CITATION_SUBJECT = WQX_TAXONIMC_DETAIL_CITATION + VAL_DEL + WQX_RESOURCE_SUBJECT;
	public static final String VALUE_TAXON_CITATION_PUBLISHER = WQX_TAXONIMC_DETAIL_CITATION + VAL_DEL + WQX_RESOURCE_PUBLISHER;
	public static final String VALUE_TAXON_CITATION_DATE = WQX_TAXONIMC_DETAIL_CITATION + VAL_DEL + WQX_RESOURCE_DATE;
	public static final String VALUE_TAXON_CITATION_ID = WQX_TAXONIMC_DETAIL_CITATION + VAL_DEL + WQX_RESOURCE_ID;
	public static final String VALUE_FCDSC_NAME = WQX_FREQUENCY_CLASS_DESCRIPTOR;
	public static final String VALUE_FREQUENCY_CLASS_UNIT = WQX_FREQUENCE_CLASS_DESCRIPTOR_UNIT;
	public static final String VALUE_FCDSC_LOWER_BOUND = WQX_LOWER_CLASS_BOUND;
	public static final String VALUE_FCDSC_UPPER_BOUND = WQX_UPPER_CLASS_BOUND;


	public static final String VALUE_ANALYTICAL_PROCEDURE_ID = WQX_ANALYTICAL_METHOD + VAL_DEL + WQX_METHOD_ID;
	public static final String VALUE_ANALYTICAL_PROCEDURE_SOURCE = WQX_ANALYTICAL_METHOD + VAL_DEL + WQX_METHOD_CONTEXT;
	public static final String VALUE_ANALYTICAL_METHOD_NAME = WQX_ANALYTICAL_METHOD + VAL_DEL + WQX_METHOD_NAME;


	public static final String VALUE_ANLMTH_QUAL_TYPE = WQX_ANALYTICAL_METHOD + VAL_DEL + WQX_METHOD_QUALIFIER_TYPE;


	public static final String VALUE_ANALYTICAL_METHOD_CITATION = WQX_METHOD_DESCRIPTION;
	public static final String VALUE_LAB_NAME = WQX_LAB_NAME;
	public static final String VALUE_ANALYSIS_START_DATE = WQX_ANALYSIS_START_DATE;


	public static final String VALUE_ANALYSIS_START_TIME = WQX_ANALYSIS_START_TIME + VAL_DEL + WQX_TIME;
	public static final String VALUE_ANALYSIS_START_TIMEZONE = WQX_ANALYSIS_START_TIME + VAL_DEL + WQX_TIME_ZONE;
	public static final String VALUE_ANALYSIS_END_DATE = WQX_ANALYSIS_END_DATE;
	public static final String VALUE_ANALYSIS_END_TIME = WQX_ANALYSIS_END_TIME + VAL_DEL + WQX_TIME;
	public static final String VALUE_ANALYSIS_END_TIMEZONE = WQX_ANALYSIS_END_TIME + VAL_DEL + WQX_TIME_ZONE;
	public static final String VALUE_RLCOM_CD = WQX_LAB_COMMENT_CODE;


	public static final String VALUE_LAB_REMARK = WQX_LAB_COMMENT;
	public static final String VALUE_DETECTION_LIMIT_DESC = WQX_DETECTION_LIMIT_TYPE;
	public static final String VALUE_DETECTION_LIMIT = WQX_DETECTION_LIMIT_VALUE + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_DETECTION_LIMIT_UNIT = WQX_DETECTION_LIMIT_VALUE + VAL_DEL + WQX_MEASURE_UNIT;


	public static final String VALUE_RES_LAB_ACCRED_YN = WQX_LAB_ACCREDITATION_INDICATOR;
	public static final String VALUE_RES_LAB_ACCRED_AUTHORITY = WQX_LAB_ACCREDITATION_AUTHORITY;
	public static final String VALUE_RES_TAXONOMIST_ACCRED_YN = WQX_TAXONOMIST_ACCREDITATION_INDICATOR;
	public static final String VALUE_RES_TAXONOMIST_ACCRED_AUTHORTY = WQX_TAXONOMIST_ACCREDITATION_AUTHORTY;
	public static final String VALUE_PREP_METHOD_ID = WQX_LAB_SAMPLE_PREP_METHOD + VAL_DEL + WQX_METHOD_ID;
	public static final String VALUE_PREP_METHOD_CONTEXT = WQX_LAB_SAMPLE_PREP_METHOD + VAL_DEL + WQX_METHOD_CONTEXT;
	public static final String VALUE_PREP_METHOD_NAME = WQX_LAB_SAMPLE_PREP_METHOD + VAL_DEL + WQX_METHOD_NAME;
	public static final String VALUE_PREP_METHOD_QUAL_TYPE = WQX_LAB_SAMPLE_PREP_METHOD + VAL_DEL + WQX_METHOD_QUALIFIER_TYPE;
	public static final String VALUE_PREP_METHOD_DESC = WQX_LAB_SAMPLE_PREP_METHOD + VAL_DEL + WQX_METHOD_DESCRIPTION;


	public static final String VALUE_ANALYSIS_PREP_DATE_TX = WQX_PREP_START_DATE;


	public static final String VALUE_PREP_START_TIME = WQX_PREP_START_TIME + VAL_DEL + WQX_TIME;
	public static final String VALUE_PREP_START_TIMEZONE = WQX_PREP_START_TIME + VAL_DEL + WQX_TIME_ZONE;
	public static final String VALUE_PREP_END_DATE = WQX_PREP_END_DATE;
	public static final String VALUE_PREP_END_TIME = WQX_PREP_END_TIME + VAL_DEL + WQX_TIME;
	public static final String VALUE_PREP_END_TIMEZONE = WQX_PREP_END_TIME + VAL_DEL + WQX_TIME_ZONE;
	public static final String VALUE_PREP_DILUTION_FACTOR = WQX_SUBSTITUTION_DILUTION_FACTOR;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final Map<ColumnProfile, String> MAPPINGS;
	
	static {
		MAPPINGS = new LinkedHashMap<ColumnProfile,String>();
		MAPPINGS.putAll(ActivityDelimited.MAPPINGS);
		MAPPINGS.remove(DATA_SOURCE);

		MAPPINGS.put(RES_DATA_LOGGER_LINE, VALUE_RES_DATA_LOGGER_LINE);


		MAPPINGS.put(RESULT_DETECTION_CONDITION_TX, VALUE_RESULT_DETECTION_CONDITION_TX);


		MAPPINGS.put(METHOD_SPECIFICATION_NAME, VALUE_METHOD_SPECIFICATION_NAME);


		MAPPINGS.put(CHARACTERISTIC_NAME, VALUE_CHARACTERISTIC_NAME);
		MAPPINGS.put(SAMPLE_FRACTION_TYPE, VALUE_SAMPLE_FRACTION_TYPE);
		MAPPINGS.put(RESULT_MEASURE_VALUE, VALUE_RESULT_MEASURE_VALUE);
		MAPPINGS.put(RESULT_UNIT, VALUE_RESULT_UNIT);
		MAPPINGS.put(RESULT_MEAS_QUAL_CODE, VALUE_RESULT_MEAS_QUAL_CODE);
		MAPPINGS.put(RESULT_VALUE_STATUS, VALUE_RESULT_VALUE_STATUS);
		MAPPINGS.put(STATISTIC_TYPE, VALUE_STATISTIC_TYPE);
		MAPPINGS.put(RESULT_VALUE_TYPE, VALUE_RESULT_VALUE_TYPE);
		MAPPINGS.put(WEIGHT_BASIS_TYPE, VALUE_WEIGHT_BASIS_TYPE);
		MAPPINGS.put(DURATION_BASIS, VALUE_DURATION_BASIS);
		MAPPINGS.put(TEMPERATURE_BASIS_LEVEL, VALUE_TEMPERATURE_BASIS_LEVEL);
		MAPPINGS.put(PARTICLE_SIZE, VALUE_PARTICLE_SIZE);
		MAPPINGS.put(PRECISION, VALUE_PRECISION);


		MAPPINGS.put(RES_MEASURE_BIAS, VALUE_RES_MEASURE_BIAS);
		MAPPINGS.put(RES_MEASURE_CONF_INTERVAL, VALUE_RES_MEASURE_CONF_INTERVAL);
		MAPPINGS.put(RES_MEASURE_UPPER_CONF_LIMIT, VALUE_RES_MEASURE_UPPER_CONF_LIMIT);
		MAPPINGS.put(RES_MEASURE_LOWER_CONF_LIMIT, VALUE_RES_MEASURE_LOWER_CONF_LIMIT);


		MAPPINGS.put(RESULT_COMMENT, VALUE_RESULT_COMMENT);
		MAPPINGS.put(RESULT_ID, VALUE_RESULT_IDENTIFIER);
		MAPPINGS.put(P_CODE, VALUE_P_CODE);
		MAPPINGS.put(RESULT_DEPTH_MEAS_VALUE, VALUE_RESULT_DEPTH_MEAS_VALUE);
		MAPPINGS.put(RESULT_DEPTH_MEAS_UNIT_CODE, VALUE_RESULT_DEPTH_MEAS_UNIT_CODE);
		MAPPINGS.put(RESULT_DEPTH_ALT_REF_PT_TXT, VALUE_RESULT_DEPTH_ALT_REF_PT_TXT);


		MAPPINGS.put(RES_SAMPLING_POINT_NAME, VALUE_RES_SAMPLING_POINT_NAME);
		MAPPINGS.put(BIOLOGICAL_INTENT, VALUE_BIOLOGICAL_INTENT);
		MAPPINGS.put(RES_BIO_INDIVIDUAL_ID, VALUE_RES_BIO_INDIVIDUAL_ID);


		MAPPINGS.put(SAMPLE_TISSUE_TAXONOMIC_NAME, VALUE_SAMPLE_TISSUE_TAXONOMIC_NAME);


		MAPPINGS.put(UNIDENTIFIEDSPECIESIDENTIFIER, VALUE_UNIDENTIFIEDSPECIESIDENTIFIER);


		MAPPINGS.put(SAMPLE_TISSUE_ANATOMY_NAME, VALUE_SAMPLE_TISSUE_ANATOMY_NAME);


		MAPPINGS.put(RES_GROUP_SUMMARY_CT_WT, VALUE_RES_GROUP_SUMMARY_CT_WT);
		MAPPINGS.put(RES_GROUP_SUMMARY_CT_WT_UNIT, VALUE_RES_GROUP_SUMMARY_CT_WT_UNIT);
		MAPPINGS.put(CELL_FORM_NAME, VALUE_CELL_FORM_NAME);
		MAPPINGS.put(CELL_SHAPE_NAME, VALUE_CELL_SHAPE_NAME);
		MAPPINGS.put(HABIT_NAME, VALUE_HABIT_NAME);
		MAPPINGS.put(VOLT_NAME, VALUE_VOLT_NAME);
		MAPPINGS.put(RTDET_POLLUTION_TOLERANCE, VALUE_RTDET_POLLUTION_TOLERANCE);
		MAPPINGS.put(RTDET_POLLUTION_TOLERNCE_SCALE, VALUE_RTDET_POLLUTION_TOLERNCE_SCALE);
		MAPPINGS.put(RTDET_TROPHIC_LEVEL, VALUE_RTDET_TROPHIC_LEVEL);
		MAPPINGS.put(RTFGRP_FUNCTIONAL_FEEDING_GRP, VALUE_RTFGRP_FUNCTIONAL_FEEDING_GRP);
		MAPPINGS.put(TAXON_CITATION_TITLE, VALUE_TAXON_CITATION_TITLE);
		MAPPINGS.put(TAXON_CITATION_CREATOR, VALUE_TAXON_CITATION_CREATOR);
		MAPPINGS.put(TAXON_CITATION_SUBJECT, VALUE_TAXON_CITATION_SUBJECT);
		MAPPINGS.put(TAXON_CITATION_PUBLISHER, VALUE_TAXON_CITATION_PUBLISHER);
		MAPPINGS.put(TAXON_CITATION_DATE, VALUE_TAXON_CITATION_DATE);
		MAPPINGS.put(TAXON_CITATION_ID, VALUE_TAXON_CITATION_ID);
		MAPPINGS.put(FCDSC_NAME, VALUE_FCDSC_NAME);
		MAPPINGS.put(FREQUENCY_CLASS_UNIT, VALUE_FREQUENCY_CLASS_UNIT);
		MAPPINGS.put(FCDSC_LOWER_BOUND, VALUE_FCDSC_LOWER_BOUND);
		MAPPINGS.put(FCDSC_UPPER_BOUND, VALUE_FCDSC_UPPER_BOUND);


		MAPPINGS.put(ANALYTICAL_PROCEDURE_ID, VALUE_ANALYTICAL_PROCEDURE_ID);
		MAPPINGS.put(ANALYTICAL_PROCEDURE_SOURCE, VALUE_ANALYTICAL_PROCEDURE_SOURCE);
		MAPPINGS.put(ANALYTICAL_METHOD_NAME, VALUE_ANALYTICAL_METHOD_NAME);


		MAPPINGS.put(ANLMTH_QUAL_TYPE, VALUE_ANLMTH_QUAL_TYPE);


		MAPPINGS.put(ANALYTICAL_METHOD_CITATION, VALUE_ANALYTICAL_METHOD_CITATION);
		MAPPINGS.put(LAB_NAME, VALUE_LAB_NAME);
		MAPPINGS.put(ANALYSIS_START_DATE, VALUE_ANALYSIS_START_DATE);


		MAPPINGS.put(ANALYSIS_START_TIME, VALUE_ANALYSIS_START_TIME);
		MAPPINGS.put(ANALYSIS_START_TIMEZONE, VALUE_ANALYSIS_START_TIMEZONE);
		MAPPINGS.put(ANALYSIS_END_DATE, VALUE_ANALYSIS_END_DATE);
		MAPPINGS.put(ANALYSIS_END_TIME, VALUE_ANALYSIS_END_TIME);
		MAPPINGS.put(ANALYSIS_END_TIMEZONE, VALUE_ANALYSIS_END_TIMEZONE);
		MAPPINGS.put(RLCOM_CD, VALUE_RLCOM_CD);


		MAPPINGS.put(LAB_REMARK, VALUE_LAB_REMARK);
		MAPPINGS.put(DETECTION_LIMIT_DESC, VALUE_DETECTION_LIMIT_DESC);
		MAPPINGS.put(DETECTION_LIMIT, VALUE_DETECTION_LIMIT);
		MAPPINGS.put(DETECTION_LIMIT_UNIT, VALUE_DETECTION_LIMIT_UNIT);


		MAPPINGS.put(RES_LAB_ACCRED_YN, VALUE_RES_LAB_ACCRED_YN);
		MAPPINGS.put(RES_LAB_ACCRED_AUTHORITY, VALUE_RES_LAB_ACCRED_AUTHORITY);
		MAPPINGS.put(RES_TAXONOMIST_ACCRED_YN, VALUE_RES_TAXONOMIST_ACCRED_YN);
		MAPPINGS.put(RES_TAXONOMIST_ACCRED_AUTHORTY, VALUE_RES_TAXONOMIST_ACCRED_AUTHORTY);
		MAPPINGS.put(PREP_METHOD_ID, VALUE_PREP_METHOD_ID);
		MAPPINGS.put(PREP_METHOD_CONTEXT, VALUE_PREP_METHOD_CONTEXT);
		MAPPINGS.put(PREP_METHOD_NAME, VALUE_PREP_METHOD_NAME);
		MAPPINGS.put(PREP_METHOD_QUAL_TYPE, VALUE_PREP_METHOD_QUAL_TYPE);
		MAPPINGS.put(PREP_METHOD_DESC, VALUE_PREP_METHOD_DESC);


		MAPPINGS.put(ANALYSIS_PREP_DATE_TX, VALUE_ANALYSIS_PREP_DATE_TX);


		MAPPINGS.put(PREP_START_TIME, VALUE_PREP_START_TIME);
		MAPPINGS.put(PREP_START_TIMEZONE, VALUE_PREP_START_TIMEZONE);
		MAPPINGS.put(PREP_END_DATE, VALUE_PREP_END_DATE);
		MAPPINGS.put(PREP_END_TIME, VALUE_PREP_END_TIME);
		MAPPINGS.put(PREP_END_TIMEZONE, VALUE_PREP_END_TIMEZONE);
		MAPPINGS.put(PREP_DILUTION_FACTOR, VALUE_PREP_DILUTION_FACTOR);


		MAPPINGS.put(DATA_SOURCE, VALUE_DATA_SOURCE);

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private ResultDelimited() {
	}

	public static Map<String, String> getMapping(String profile) {
		return getMapping(MAPPINGS, profile);
	}


}
