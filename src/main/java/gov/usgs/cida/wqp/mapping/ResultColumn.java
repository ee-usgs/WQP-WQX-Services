package gov.usgs.cida.wqp.mapping;

public class ResultColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_RESULT_ID = "RESULT_ID";
	public static final String KEY_EXTERNAL_RESULT_ID = "EXTERNAL_RESULT_ID";
	public static final String KEY_ANALYTICAL_METHOD = "ANALYTICAL_METHOD";
	public static final String KEY_RES_DATA_LOGGER_LINE = "RES_DATA_LOGGER_LINE";
	public static final String KEY_RESULT_DETECTION_CONDITION_TX = "RESULT_DETECTION_CONDITION_TX";

	//Note that "SPECIATION" is correct and "SPECIFICATION" is a typo.
	public static final String KEY_METHOD_SPECIATION_NAME = "METHOD_SPECIATION_NAME";
	public static final String KEY_METHOD_SPECIFICATION_NAME = "METHOD_SPECIFICATION_NAME";

	public static final String KEY_CHARACTERISTIC_NAME = "CHARACTERISTIC_NAME";
	public static final String KEY_CHARACTERISTIC_TYPE = "CHARACTERISTIC_TYPE";
	public static final String KEY_SAMPLE_FRACTION_TYPE = "SAMPLE_FRACTION_TYPE";
	public static final String KEY_RESULT_MEASURE_VALUE = "RESULT_MEASURE_VALUE";
	public static final String KEY_RESULT_UNIT = "RESULT_UNIT";
	public static final String KEY_RESULT_MEAS_QUAL_CODE = "RESULT_MEAS_QUAL_CODE";
	public static final String KEY_RESULT_VALUE_STATUS = "RESULT_VALUE_STATUS";
	public static final String KEY_STATISTIC_TYPE = "STATISTIC_TYPE";
	public static final String KEY_RESULT_VALUE_TYPE = "RESULT_VALUE_TYPE";
	public static final String KEY_WEIGHT_BASIS_TYPE = "WEIGHT_BASIS_TYPE";
	public static final String KEY_DURATION_BASIS = "DURATION_BASIS";
	public static final String KEY_TEMPERATURE_BASIS_LEVEL = "TEMPERATURE_BASIS_LEVEL";
	public static final String KEY_PARTICLE_SIZE = "PARTICLE_SIZE";
	public static final String KEY_PRECISION = "PRECISION";
	public static final String KEY_RES_MEASURE_BIAS = "RES_MEASURE_BIAS";
	public static final String KEY_RES_MEASURE_CONF_INTERVAL = "RES_MEASURE_CONF_INTERVAL";
	public static final String KEY_RES_MEASURE_UPPER_CONF_LIMIT = "RES_MEASURE_UPPER_CONF_LIMIT";
	public static final String KEY_RES_MEASURE_LOWER_CONF_LIMIT = "RES_MEASURE_LOWER_CONF_LIMIT";
	public static final String KEY_RESULT_COMMENT = "RESULT_COMMENT";
	public static final String KEY_P_CODE = "P_CODE";
	public static final String KEY_RESULT_DEPTH_MEAS_VALUE = "RESULT_DEPTH_MEAS_VALUE";
	public static final String KEY_RESULT_DEPTH_MEAS_UNIT_CODE = "RESULT_DEPTH_MEAS_UNIT_CODE";
	public static final String KEY_RESULT_DEPTH_ALT_REF_PT_TXT = "RESULT_DEPTH_ALT_REF_PT_TXT";
	public static final String KEY_RES_SAMPLING_POINT_NAME = "RES_SAMPLING_POINT_NAME";
	public static final String KEY_BIOLOGICAL_INTENT = "BIOLOGICAL_INTENT";
	public static final String KEY_RES_BIO_INDIVIDUAL_ID = "RES_BIO_INDIVIDUAL_ID";
	public static final String KEY_SAMPLE_TISSUE_TAXONOMIC_NAME = "SAMPLE_TISSUE_TAXONOMIC_NAME";
	public static final String KEY_UNIDENTIFIEDSPECIESIDENTIFIER = "UNIDENTIFIEDSPECIESIDENTIFIER";
	public static final String KEY_SAMPLE_TISSUE_ANATOMY_NAME = "SAMPLE_TISSUE_ANATOMY_NAME";
	public static final String KEY_RES_GROUP_SUMMARY_CT_WT = "RES_GROUP_SUMMARY_CT_WT";
	public static final String KEY_RES_GROUP_SUMMARY_CT_WT_UNIT = "RES_GROUP_SUMMARY_CT_WT_UNIT";
	public static final String KEY_CELL_FORM_NAME = "CELL_FORM_NAME";
	public static final String KEY_CELL_SHAPE_NAME = "CELL_SHAPE_NAME";
	public static final String KEY_HABIT_NAME = "HABIT_NAME";

	//Note that Voltism is a typo, real value is Voltinism
	public static final String KEY_VOLT_NAME = "VOLT_NAME";
	public static final String KEY_VOLTINISM_NAME = "VOLTINISM_NAME";

	public static final String KEY_RTDET_POLLUTION_TOLERANCE = "RTDET_POLLUTION_TOLERANCE";
	public static final String KEY_RTDET_POLLUTION_TOLERNCE_SCALE = "RTDET_POLLUTION_TOLERNCE_SCALE";
	public static final String KEY_RTDET_TROPHIC_LEVEL = "RTDET_TROPHIC_LEVEL";
	public static final String KEY_RTFGRP_FUNCTIONAL_FEEDING_GRP = "RTFGRP_FUNCTIONAL_FEEDING_GRP";
	public static final String KEY_TAXON_CITATION_TITLE = "TAXON_CITATION_TITLE";
	public static final String KEY_TAXON_CITATION_CREATOR = "TAXON_CITATION_CREATOR";
	public static final String KEY_TAXON_CITATION_SUBJECT = "TAXON_CITATION_SUBJECT";
	public static final String KEY_TAXON_CITATION_PUBLISHER = "TAXON_CITATION_PUBLISHER";
	public static final String KEY_TAXON_CITATION_DATE = "TAXON_CITATION_DATE";
	public static final String KEY_TAXON_CITATION_ID = "TAXON_CITATION_ID";

	//Split into 3 columns for each value in newer profiles.
	public static final String KEY_FCDSC_URL = "FCDSC_URL";
	public static final String KEY_FCDSC_NAME = "FCDSC_NAME";
	public static final String KEY_FREQUENCY_CLASS_CODE_1 = "FREQUENCY_CLASS_CODE_1";
	public static final String KEY_FREQUENCY_CLASS_CODE_2 = "FREQUENCY_CLASS_CODE_2";
	public static final String KEY_FREQUENCY_CLASS_CODE_3 = "FREQUENCY_CLASS_CODE_3";
	public static final String KEY_FREQUENCY_CLASS_UNIT = "FREQUENCY_CLASS_UNIT";
	public static final String KEY_FREQUENCY_CLASS_UNIT_1 = "FREQUENCY_CLASS_UNIT_1";
	public static final String KEY_FREQUENCY_CLASS_UNIT_2 = "FREQUENCY_CLASS_UNIT_2";
	public static final String KEY_FREQUENCY_CLASS_UNIT_3 = "FREQUENCY_CLASS_UNIT_3";
	public static final String KEY_FCDSC_LOWER_BOUND = "FCDSC_LOWER_BOUND";
	public static final String KEY_FREQUENCY_CLASS_LOWER_BOUND_1 = "FREQUENCY_CLASS_LOWER_BOUND_1";
	public static final String KEY_FREQUENCY_CLASS_LOWER_BOUND_2 = "FREQUENCY_CLASS_LOWER_BOUND_2";
	public static final String KEY_FREQUENCY_CLASS_LOWER_BOUND_3 = "FREQUENCY_CLASS_LOWER_BOUND_3";
	public static final String KEY_FCDSC_UPPER_BOUND = "FCDSC_UPPER_BOUND";
	public static final String KEY_FREQUENCY_CLASS_UPPER_BOUND_1 = "FREQUENCY_CLASS_UPPER_BOUND_1";
	public static final String KEY_FREQUENCY_CLASS_UPPER_BOUND_2 = "FREQUENCY_CLASS_UPPER_BOUND_2";
	public static final String KEY_FREQUENCY_CLASS_UPPER_BOUND_3 = "FREQUENCY_CLASS_UPPER_BOUND_3";

	public static final String KEY_METHOD_URL = "ANALYTICAL_METHOD";
	public static final String KEY_ANALYTICAL_PROCEDURE_ID = "ANALYTICAL_PROCEDURE_ID";
	public static final String KEY_ANALYTICAL_PROCEDURE_SOURCE = "ANALYTICAL_PROCEDURE_SOURCE";
	public static final String KEY_ANALYTICAL_METHOD_NAME = "ANALYTICAL_METHOD_NAME";
	public static final String KEY_ANLMTH_QUAL_TYPE = "ANLMTH_QUAL_TYPE";
	public static final String KEY_ANALYTICAL_METHOD_CITATION = "ANALYTICAL_METHOD_CITATION";
	public static final String KEY_LAB_NAME = "LAB_NAME";
	public static final String KEY_ANALYSIS_START_DATE = "ANALYSIS_START_DATE";
	public static final String KEY_ANALYSIS_START_TIME = "ANALYSIS_START_TIME";
	public static final String KEY_ANALYSIS_START_TIMEZONE = "ANALYSIS_START_TIMEZONE";
	public static final String KEY_ANALYSIS_END_DATE = "ANALYSIS_END_DATE";
	public static final String KEY_ANALYSIS_END_TIME = "ANALYSIS_END_TIME";
	public static final String KEY_ANALYSIS_END_TIMEZONE = "ANALYSIS_END_TIMEZONE";
	public static final String KEY_RLCOM_CD = "RLCOM_CD";
	public static final String KEY_LAB_REMARK = "LAB_REMARK";
	public static final String KEY_RES_DETECT_QNT_LMT_URL = "RES_DETECT_QNT_LMT_URL";
	public static final String KEY_DETECTION_LIMIT_ID = "DETECTION_LIMIT_ID";
	public static final String KEY_DETECTION_LIMIT_DESC = "DETECTION_LIMIT_DESC";
	public static final String KEY_DETECTION_LIMIT = "DETECTION_LIMIT";
	public static final String KEY_DETECTION_LIMIT_UNIT = "DETECTION_LIMIT_UNIT";
	public static final String KEY_RES_LAB_ACCRED_YN = "RES_LAB_ACCRED_YN";
	public static final String KEY_RES_LAB_ACCRED_AUTHORITY = "RES_LAB_ACCRED_AUTHORITY";
	public static final String KEY_RES_TAXONOMIST_ACCRED_YN = "RES_TAXONOMIST_ACCRED_YN";
	public static final String KEY_RES_TAXONOMIST_ACCRED_AUTHORTY = "RES_TAXONOMIST_ACCRED_AUTHORTY";
	public static final String KEY_PREP_METHOD_ID = "PREP_METHOD_ID";
	public static final String KEY_PREP_METHOD_CONTEXT = "PREP_METHOD_CONTEXT";
	public static final String KEY_PREP_METHOD_NAME = "PREP_METHOD_NAME";
	public static final String KEY_PREP_METHOD_QUAL_TYPE = "PREP_METHOD_QUAL_TYPE";
	public static final String KEY_PREP_METHOD_DESC = "PREP_METHOD_DESC";
	public static final String KEY_ANALYSIS_PREP_DATE_TX = "ANALYSIS_PREP_DATE_TX";
	public static final String KEY_PREP_START_TIME = "PREP_START_TIME";
	public static final String KEY_PREP_START_TIMEZONE = "PREP_START_TIMEZONE";
	public static final String KEY_PREP_END_DATE = "PREP_END_DATE";
	public static final String KEY_PREP_END_TIME = "PREP_END_TIME";
	public static final String KEY_PREP_END_TIMEZONE = "PREP_END_TIMEZONE";
	public static final String KEY_PREP_DILUTION_FACTOR = "PREP_DILUTION_FACTOR";
	public static final String KEY_LAB_SAMPLE_PREP_URL = "LAB_SAMPLE_PREP_URL";
	public static final String KEY_RESULT_OBJECT_NAME = "RESULT_OBJECT_NAME";
	public static final String KEY_RESULT_OBJECT_TYPE = "RESULT_OBJECT_TYPE";
	public static final String KEY_RESULT_FILE_URL = "RESULT_FILE_URL";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//Profile Mapping of the Keys
	public static final ColumnProfile RESULT_ID = new ColumnProfile(KEY_RESULT_ID);
	public static final ColumnProfile RES_DATA_LOGGER_LINE = new ColumnProfile(KEY_RES_DATA_LOGGER_LINE, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_DETECTION_CONDITION_TX = new ColumnProfile(KEY_RESULT_DETECTION_CONDITION_TX, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile CHARACTERISTIC_NAME = new ColumnProfile(KEY_CHARACTERISTIC_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.RES_DETECT_QNT_LMT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	//Note that "SPECIATION" is correct and "SPECIFICATION" is a typo.
	public static final ColumnProfile METHOD_SPECIATION_NAME = new ColumnProfile(KEY_METHOD_SPECIATION_NAME, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile METHOD_SPECIFICATION_NAME = new ColumnProfile(KEY_METHOD_SPECIFICATION_NAME, Profile.BIOLOGICAL, Profile.NARROW_RESULT);

	public static final ColumnProfile SAMPLE_FRACTION_TYPE = new ColumnProfile(KEY_SAMPLE_FRACTION_TYPE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_MEASURE_VALUE = new ColumnProfile(KEY_RESULT_MEASURE_VALUE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_UNIT = new ColumnProfile(KEY_RESULT_UNIT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_MEAS_QUAL_CODE = new ColumnProfile(KEY_RESULT_MEAS_QUAL_CODE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_VALUE_STATUS = new ColumnProfile(KEY_RESULT_VALUE_STATUS, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile STATISTIC_TYPE = new ColumnProfile(KEY_STATISTIC_TYPE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_VALUE_TYPE = new ColumnProfile(KEY_RESULT_VALUE_TYPE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile WEIGHT_BASIS_TYPE = new ColumnProfile(KEY_WEIGHT_BASIS_TYPE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile DURATION_BASIS = new ColumnProfile(KEY_DURATION_BASIS, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile TEMPERATURE_BASIS_LEVEL = new ColumnProfile(KEY_TEMPERATURE_BASIS_LEVEL, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile PARTICLE_SIZE = new ColumnProfile(KEY_PARTICLE_SIZE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	//Note that later profiles specify the "DataQuality" prefix (RES_MEASURE_BIAS always had it)
	public static final ColumnProfile PRECISION = new ColumnProfile(KEY_PRECISION, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT);
	public static final ColumnProfile DQ_PRECISION = new ColumnProfile(KEY_PRECISION, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_MEASURE_BIAS = new ColumnProfile(KEY_RES_MEASURE_BIAS, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_MEASURE_CONF_INTERVAL = new ColumnProfile(KEY_RES_MEASURE_CONF_INTERVAL, Profile.BIOLOGICAL, Profile.NARROW_RESULT);
	public static final ColumnProfile DQ_RES_MEASURE_CONF_INTERVAL = new ColumnProfile(KEY_RES_MEASURE_CONF_INTERVAL, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_MEASURE_UPPER_CONF_LIMIT = new ColumnProfile(KEY_RES_MEASURE_UPPER_CONF_LIMIT, Profile.BIOLOGICAL, Profile.NARROW_RESULT);
	public static final ColumnProfile DQ_RES_MEASURE_UPPER_CONF_LIMIT = new ColumnProfile(KEY_RES_MEASURE_UPPER_CONF_LIMIT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_MEASURE_LOWER_CONF_LIMIT = new ColumnProfile(KEY_RES_MEASURE_LOWER_CONF_LIMIT, Profile.BIOLOGICAL, Profile.NARROW_RESULT);
	public static final ColumnProfile DQ_RES_MEASURE_LOWER_CONF_LIMIT = new ColumnProfile(KEY_RES_MEASURE_LOWER_CONF_LIMIT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);


	public static final ColumnProfile RESULT_COMMENT = new ColumnProfile(KEY_RESULT_COMMENT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_DEPTH_MEAS_VALUE = new ColumnProfile(KEY_RESULT_DEPTH_MEAS_VALUE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_DEPTH_MEAS_UNIT_CODE = new ColumnProfile(KEY_RESULT_DEPTH_MEAS_UNIT_CODE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_DEPTH_ALT_REF_PT_TXT = new ColumnProfile(KEY_RESULT_DEPTH_ALT_REF_PT_TXT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_SAMPLING_POINT_NAME = new ColumnProfile(KEY_RES_SAMPLING_POINT_NAME, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile P_CODE = new ColumnProfile(KEY_P_CODE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile BIOLOGICAL_INTENT = new ColumnProfile(KEY_BIOLOGICAL_INTENT, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_BIO_INDIVIDUAL_ID = new ColumnProfile(KEY_RES_BIO_INDIVIDUAL_ID, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile SAMPLE_TISSUE_TAXONOMIC_NAME = new ColumnProfile(KEY_SAMPLE_TISSUE_TAXONOMIC_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile UNIDENTIFIEDSPECIESIDENTIFIER = new ColumnProfile(KEY_UNIDENTIFIEDSPECIESIDENTIFIER, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile SAMPLE_TISSUE_ANATOMY_NAME = new ColumnProfile(KEY_SAMPLE_TISSUE_ANATOMY_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_GROUP_SUMMARY_CT_WT = new ColumnProfile(KEY_RES_GROUP_SUMMARY_CT_WT, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_GROUP_SUMMARY_CT_WT_UNIT = new ColumnProfile(KEY_RES_GROUP_SUMMARY_CT_WT_UNIT, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile CELL_FORM_NAME = new ColumnProfile(KEY_CELL_FORM_NAME, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile CELL_SHAPE_NAME = new ColumnProfile(KEY_CELL_SHAPE_NAME, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile HABIT_NAME = new ColumnProfile(KEY_HABIT_NAME, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	//Note that Voltism is a typo, real value is Voltinism
	public static final ColumnProfile VOLT_NAME = new ColumnProfile(KEY_VOLT_NAME, Profile.BIOLOGICAL, Profile.NARROW_RESULT);
	public static final ColumnProfile VOLTINISM_NAME = new ColumnProfile(KEY_VOLTINISM_NAME, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	public static final ColumnProfile RTDET_POLLUTION_TOLERANCE = new ColumnProfile(KEY_RTDET_POLLUTION_TOLERANCE, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RTDET_POLLUTION_TOLERNCE_SCALE = new ColumnProfile(KEY_RTDET_POLLUTION_TOLERNCE_SCALE, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RTDET_TROPHIC_LEVEL = new ColumnProfile(KEY_RTDET_TROPHIC_LEVEL, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RTFGRP_FUNCTIONAL_FEEDING_GRP = new ColumnProfile(KEY_RTFGRP_FUNCTIONAL_FEEDING_GRP, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile TAXON_CITATION_TITLE = new ColumnProfile(KEY_TAXON_CITATION_TITLE, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile TAXON_CITATION_CREATOR = new ColumnProfile(KEY_TAXON_CITATION_CREATOR, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile TAXON_CITATION_SUBJECT = new ColumnProfile(KEY_TAXON_CITATION_SUBJECT, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile TAXON_CITATION_PUBLISHER = new ColumnProfile(KEY_TAXON_CITATION_PUBLISHER, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile TAXON_CITATION_DATE = new ColumnProfile(KEY_TAXON_CITATION_DATE, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile TAXON_CITATION_ID = new ColumnProfile(KEY_TAXON_CITATION_ID, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	//Split into 3 columns for each value in newer profiles.
	public static final ColumnProfile FCDSC_URL = new ColumnProfile(KEY_FCDSC_URL, Profile.NARROW_RESULT);
	public static final ColumnProfile FCDSC_NAME = new ColumnProfile(KEY_FCDSC_NAME, Profile.BIOLOGICAL);
	public static final ColumnProfile FREQUENCY_CLASS_CODE_1 = new ColumnProfile(KEY_FREQUENCY_CLASS_CODE_1, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FREQUENCY_CLASS_CODE_2 = new ColumnProfile(KEY_FREQUENCY_CLASS_CODE_2, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FREQUENCY_CLASS_CODE_3 = new ColumnProfile(KEY_FREQUENCY_CLASS_CODE_3, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FREQUENCY_CLASS_UNIT = new ColumnProfile(KEY_FREQUENCY_CLASS_UNIT, Profile.BIOLOGICAL);
	public static final ColumnProfile FREQUENCY_CLASS_UNIT_1 = new ColumnProfile(KEY_FREQUENCY_CLASS_UNIT_1, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FREQUENCY_CLASS_UNIT_2 = new ColumnProfile(KEY_FREQUENCY_CLASS_UNIT_2, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FREQUENCY_CLASS_UNIT_3 = new ColumnProfile(KEY_FREQUENCY_CLASS_UNIT_3, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FCDSC_LOWER_BOUND = new ColumnProfile(KEY_FCDSC_LOWER_BOUND, Profile.BIOLOGICAL);
	public static final ColumnProfile FREQUENCY_CLASS_LOWER_BOUND_1 = new ColumnProfile(KEY_FREQUENCY_CLASS_LOWER_BOUND_1, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FREQUENCY_CLASS_LOWER_BOUND_2 = new ColumnProfile(KEY_FREQUENCY_CLASS_LOWER_BOUND_2, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FREQUENCY_CLASS_LOWER_BOUND_3 = new ColumnProfile(KEY_FREQUENCY_CLASS_LOWER_BOUND_3, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FCDSC_UPPER_BOUND = new ColumnProfile(KEY_FCDSC_UPPER_BOUND, Profile.BIOLOGICAL);
	public static final ColumnProfile FREQUENCY_CLASS_UPPER_BOUND_1 = new ColumnProfile(KEY_FREQUENCY_CLASS_UPPER_BOUND_1, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FREQUENCY_CLASS_UPPER_BOUND_2 = new ColumnProfile(KEY_FREQUENCY_CLASS_UPPER_BOUND_2, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile FREQUENCY_CLASS_UPPER_BOUND_3 = new ColumnProfile(KEY_FREQUENCY_CLASS_UPPER_BOUND_3, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);


	public static final ColumnProfile RESULT_OBJECT_NAME = new ColumnProfile(KEY_RESULT_OBJECT_NAME, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_OBJECT_TYPE = new ColumnProfile(KEY_RESULT_OBJECT_TYPE, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RESULT_FILE_URL = new ColumnProfile(KEY_RESULT_FILE_URL, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	public static final ColumnProfile ANALYTICAL_PROCEDURE_ID = new ColumnProfile(KEY_ANALYTICAL_PROCEDURE_ID, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ANALYTICAL_PROCEDURE_SOURCE = new ColumnProfile(KEY_ANALYTICAL_PROCEDURE_SOURCE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ANALYTICAL_METHOD_NAME = new ColumnProfile(KEY_ANALYTICAL_METHOD_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ANLMTH_QUAL_TYPE = new ColumnProfile(KEY_ANLMTH_QUAL_TYPE, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	//Note that later profiles specify the "ResultAnalyticalMethod" prefix
	public static final ColumnProfile ANALYTICAL_METHOD_CITATION = new ColumnProfile(KEY_ANALYTICAL_METHOD_CITATION, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT);
	public static final ColumnProfile R_ANALYTICAL_METHOD_CITATION = new ColumnProfile(KEY_ANALYTICAL_METHOD_CITATION, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	public static final ColumnProfile METHOD_URL = new ColumnProfile(KEY_METHOD_URL, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	public static final ColumnProfile LAB_NAME = new ColumnProfile(KEY_LAB_NAME, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ANALYSIS_START_DATE = new ColumnProfile(KEY_ANALYSIS_START_DATE, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ANALYSIS_START_TIME = new ColumnProfile(KEY_ANALYSIS_START_TIME, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ANALYSIS_START_TIMEZONE = new ColumnProfile(KEY_ANALYSIS_START_TIMEZONE, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ANALYSIS_END_DATE = new ColumnProfile(KEY_ANALYSIS_END_DATE, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ANALYSIS_END_TIME = new ColumnProfile(KEY_ANALYSIS_END_TIME, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile ANALYSIS_END_TIMEZONE = new ColumnProfile(KEY_ANALYSIS_END_TIMEZONE, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RLCOM_CD = new ColumnProfile(KEY_RLCOM_CD, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile LAB_REMARK = new ColumnProfile(KEY_LAB_REMARK, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM,
			Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile DETECTION_LIMIT_DESC = new ColumnProfile(KEY_DETECTION_LIMIT_DESC, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.RES_DETECT_QNT_LMT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile DETECTION_LIMIT = new ColumnProfile(KEY_DETECTION_LIMIT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.RES_DETECT_QNT_LMT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile DETECTION_LIMIT_UNIT = new ColumnProfile(KEY_DETECTION_LIMIT_UNIT, Profile.BIOLOGICAL, Profile.PC_RESULT, Profile.RES_DETECT_QNT_LMT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_LAB_ACCRED_YN = new ColumnProfile(KEY_RES_LAB_ACCRED_YN, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_LAB_ACCRED_AUTHORITY = new ColumnProfile(KEY_RES_LAB_ACCRED_AUTHORITY, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_TAXONOMIST_ACCRED_YN = new ColumnProfile(KEY_RES_TAXONOMIST_ACCRED_YN, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_TAXONOMIST_ACCRED_AUTHORTY = new ColumnProfile(KEY_RES_TAXONOMIST_ACCRED_AUTHORTY, Profile.BIOLOGICAL, Profile.NARROW_RESULT, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile RES_DETECT_QNT_LMT_URL = new ColumnProfile(KEY_RES_DETECT_QNT_LMT_URL, Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);

	public static final ColumnProfile PREP_METHOD_ID = new ColumnProfile(KEY_PREP_METHOD_ID, Profile.BIOLOGICAL);
	public static final ColumnProfile PREP_METHOD_CONTEXT = new ColumnProfile(KEY_PREP_METHOD_CONTEXT, Profile.BIOLOGICAL);
	public static final ColumnProfile PREP_METHOD_NAME = new ColumnProfile(KEY_PREP_METHOD_NAME, Profile.BIOLOGICAL);
	public static final ColumnProfile PREP_METHOD_QUAL_TYPE = new ColumnProfile(KEY_PREP_METHOD_QUAL_TYPE, Profile.BIOLOGICAL);
	public static final ColumnProfile PREP_METHOD_DESC = new ColumnProfile(KEY_PREP_METHOD_DESC, Profile.BIOLOGICAL);
	public static final ColumnProfile ANALYSIS_PREP_DATE_TX = new ColumnProfile(KEY_ANALYSIS_PREP_DATE_TX, Profile.BIOLOGICAL, Profile.PC_RESULT);
	public static final ColumnProfile PREP_START_TIME = new ColumnProfile(KEY_PREP_START_TIME, Profile.BIOLOGICAL);
	public static final ColumnProfile PREP_START_TIMEZONE = new ColumnProfile(KEY_PREP_START_TIMEZONE, Profile.BIOLOGICAL);
	public static final ColumnProfile PREP_END_DATE = new ColumnProfile(KEY_PREP_END_DATE, Profile.BIOLOGICAL);
	public static final ColumnProfile PREP_END_TIME = new ColumnProfile(KEY_PREP_END_TIME, Profile.BIOLOGICAL);
	public static final ColumnProfile PREP_END_TIMEZONE = new ColumnProfile(KEY_PREP_END_TIMEZONE, Profile.BIOLOGICAL);
	public static final ColumnProfile PREP_DILUTION_FACTOR = new ColumnProfile(KEY_PREP_DILUTION_FACTOR, Profile.BIOLOGICAL);

	public static final ColumnProfile EXTERNAL_RESULT_ID = new ColumnProfile(KEY_EXTERNAL_RESULT_ID, Profile.NARROW_RESULT, Profile.RES_DETECT_QNT_LMT,
			Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);
	public static final ColumnProfile LAB_SAMPLE_PREP_URL = new ColumnProfile(KEY_LAB_SAMPLE_PREP_URL,Profile.NARROW_RESULT, Profile.RESULT_PHYS_CHEM, Profile.RESULT_PRIMARY, Profile.RESULT_BROAD);


	private ResultColumn() {
	}
}
