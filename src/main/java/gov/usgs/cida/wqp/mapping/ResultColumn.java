package gov.usgs.cida.wqp.mapping;

public class ResultColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_RESULT_ID = "result_id";
	public static final String KEY_EXTERNAL_RESULT_ID = "external_result_id";
	public static final String KEY_ANALYTICAL_METHOD = "analytical_method";
	public static final String KEY_RES_DATA_LOGGER_LINE = "res_data_logger_line";
	public static final String KEY_RESULT_DETECTION_CONDITION_TX = "result_detection_condition_tx";

	//Note that "SPECIATION" is correct and "SPECIFICATION" is a typo.
	public static final String KEY_METHOD_SPECIATION_NAME = "method_speciation_name";
	public static final String KEY_METHOD_SPECIFICATION_NAME = "method_specification_name";

	public static final String KEY_CHARACTERISTIC_NAME = "characteristic_name";
	public static final String KEY_CHARACTERISTIC_TYPE = "characteristic_type";
	public static final String KEY_SAMPLE_FRACTION_TYPE = "sample_fraction_type";
	public static final String KEY_RESULT_MEASURE_VALUE = "result_measure_value";
	public static final String KEY_RESULT_UNIT = "result_unit";
	public static final String KEY_RESULT_MEAS_QUAL_CODE = "result_meas_qual_code";
	public static final String KEY_RESULT_VALUE_STATUS = "result_value_status";
	public static final String KEY_STATISTIC_TYPE = "statistic_type";
	public static final String KEY_RESULT_VALUE_TYPE = "result_value_type";
	public static final String KEY_WEIGHT_BASIS_TYPE = "weight_basis_type";
	public static final String KEY_DURATION_BASIS = "duration_basis";
	public static final String KEY_TEMPERATURE_BASIS_LEVEL = "temperature_basis_level";
	public static final String KEY_PARTICLE_SIZE = "particle_size";
	public static final String KEY_PRECISION = "precision";
	public static final String KEY_RES_MEASURE_BIAS = "res_measure_bias";
	public static final String KEY_RES_MEASURE_CONF_INTERVAL = "res_measure_conf_interval";
	public static final String KEY_RES_MEASURE_UPPER_CONF_LIMIT = "res_measure_upper_conf_limit";
	public static final String KEY_RES_MEASURE_LOWER_CONF_LIMIT = "res_measure_lower_conf_limit";
	public static final String KEY_RESULT_COMMENT = "result_comment";
	public static final String KEY_P_CODE = "p_code";
	public static final String KEY_RESULT_DEPTH_MEAS_VALUE = "result_depth_meas_value";
	public static final String KEY_RESULT_DEPTH_MEAS_UNIT_CODE = "result_depth_meas_unit_code";
	public static final String KEY_RESULT_DEPTH_ALT_REF_PT_TXT = "result_depth_alt_ref_pt_txt";
	public static final String KEY_RES_SAMPLING_POINT_NAME = "res_sampling_point_name";
	public static final String KEY_BIOLOGICAL_INTENT = "biological_intent";
	public static final String KEY_RES_BIO_INDIVIDUAL_ID = "res_bio_individual_id";
	public static final String KEY_SAMPLE_TISSUE_TAXONOMIC_NAME = "sample_tissue_taxonomic_name";
	public static final String KEY_UNIDENTIFIEDSPECIESIDENTIFIER = "unidentifiedspeciesidentifier";
	public static final String KEY_SAMPLE_TISSUE_ANATOMY_NAME = "sample_tissue_anatomy_name";
	public static final String KEY_RES_GROUP_SUMMARY_CT_WT = "res_group_summary_ct_wt";
	public static final String KEY_RES_GROUP_SUMMARY_CT_WT_UNIT = "res_group_summary_ct_wt_unit";
	public static final String KEY_CELL_FORM_NAME = "cell_form_name";
	public static final String KEY_CELL_SHAPE_NAME = "cell_shape_name";
	public static final String KEY_HABIT_NAME = "habit_name";

	//Note that Voltism is a typo, real value is Voltinism
	public static final String KEY_VOLT_NAME = "volt_name";
	public static final String KEY_VOLTINISM_NAME = "voltinism_name";

	public static final String KEY_RTDET_POLLUTION_TOLERANCE = "rtdet_pollution_tolerance";
	public static final String KEY_RTDET_POLLUTION_TOLERNCE_SCALE = "rtdet_pollution_tolernce_scale";
	public static final String KEY_RTDET_TROPHIC_LEVEL = "rtdet_trophic_level";
	public static final String KEY_RTFGRP_FUNCTIONAL_FEEDING_GRP = "rtfgrp_functional_feeding_grp";
	public static final String KEY_TAXON_CITATION_TITLE = "taxon_citation_title";
	public static final String KEY_TAXON_CITATION_CREATOR = "taxon_citation_creator";
	public static final String KEY_TAXON_CITATION_SUBJECT = "taxon_citation_subject";
	public static final String KEY_TAXON_CITATION_PUBLISHER = "taxon_citation_publisher";
	public static final String KEY_TAXON_CITATION_DATE = "taxon_citation_date";
	public static final String KEY_TAXON_CITATION_ID = "taxon_citation_id";

	//Split into 3 columns for each value in newer profiles.
	public static final String KEY_FCDSC_URL = "fcdsc_url";
	public static final String KEY_FCDSC_NAME = "fcdsc_name";
	public static final String KEY_FREQUENCY_CLASS_CODE_1 = "frequency_class_code_1";
	public static final String KEY_FREQUENCY_CLASS_CODE_2 = "frequency_class_code_2";
	public static final String KEY_FREQUENCY_CLASS_CODE_3 = "frequency_class_code_3";
	public static final String KEY_FREQUENCY_CLASS_UNIT = "frequency_class_unit";
	public static final String KEY_FREQUENCY_CLASS_UNIT_1 = "frequency_class_unit_1";
	public static final String KEY_FREQUENCY_CLASS_UNIT_2 = "frequency_class_unit_2";
	public static final String KEY_FREQUENCY_CLASS_UNIT_3 = "frequency_class_unit_3";
	public static final String KEY_FCDSC_LOWER_BOUND = "fcdsc_lower_bound";
	public static final String KEY_FREQUENCY_CLASS_LOWER_BOUND_1 = "frequency_class_lower_bound_1";
	public static final String KEY_FREQUENCY_CLASS_LOWER_BOUND_2 = "frequency_class_lower_bound_2";
	public static final String KEY_FREQUENCY_CLASS_LOWER_BOUND_3 = "frequency_class_lower_bound_3";
	public static final String KEY_FCDSC_UPPER_BOUND = "fcdsc_upper_bound";
	public static final String KEY_FREQUENCY_CLASS_UPPER_BOUND_1 = "frequency_class_upper_bound_1";
	public static final String KEY_FREQUENCY_CLASS_UPPER_BOUND_2 = "frequency_class_upper_bound_2";
	public static final String KEY_FREQUENCY_CLASS_UPPER_BOUND_3 = "frequency_class_upper_bound_3";

	public static final String KEY_METHOD_URL = "analytical_method";
	public static final String KEY_ANALYTICAL_PROCEDURE_ID = "analytical_procedure_id";
	public static final String KEY_ANALYTICAL_PROCEDURE_SOURCE = "analytical_procedure_source";
	public static final String KEY_ANALYTICAL_METHOD_NAME = "analytical_method_name";
	public static final String KEY_ANLMTH_QUAL_TYPE = "anlmth_qual_type";
	public static final String KEY_ANALYTICAL_METHOD_CITATION = "analytical_method_citation";
	public static final String KEY_LAB_NAME = "lab_name";
	public static final String KEY_ANALYSIS_START_DATE = "analysis_start_date";
	public static final String KEY_ANALYSIS_START_TIME = "analysis_start_time";
	public static final String KEY_ANALYSIS_START_TIMEZONE = "analysis_start_timezone";
	public static final String KEY_ANALYSIS_END_DATE = "analysis_end_date";
	public static final String KEY_ANALYSIS_END_TIME = "analysis_end_time";
	public static final String KEY_ANALYSIS_END_TIMEZONE = "analysis_end_timezone";
	public static final String KEY_RLCOM_CD = "rlcom_cd";
	public static final String KEY_LAB_REMARK = "lab_remark";
	public static final String KEY_RES_DETECT_QNT_LMT_URL = "res_detect_qnt_lmt_url";
	public static final String KEY_DETECTION_LIMIT_ID = "detection_limit_id";
	public static final String KEY_DETECTION_LIMIT_DESC = "detection_limit_desc";
	public static final String KEY_DETECTION_LIMIT = "detection_limit";
	public static final String KEY_DETECTION_LIMIT_UNIT = "detection_limit_unit";
	public static final String KEY_RES_LAB_ACCRED_YN = "res_lab_accred_yn";
	public static final String KEY_RES_LAB_ACCRED_AUTHORITY = "res_lab_accred_authority";
	public static final String KEY_RES_TAXONOMIST_ACCRED_YN = "res_taxonomist_accred_yn";
	public static final String KEY_RES_TAXONOMIST_ACCRED_AUTHORTY = "res_taxonomist_accred_authorty";
	public static final String KEY_PREP_METHOD_ID = "prep_method_id";
	public static final String KEY_PREP_METHOD_CONTEXT = "prep_method_context";
	public static final String KEY_PREP_METHOD_NAME = "prep_method_name";
	public static final String KEY_PREP_METHOD_QUAL_TYPE = "prep_method_qual_type";
	public static final String KEY_PREP_METHOD_DESC = "prep_method_desc";
	public static final String KEY_ANALYSIS_PREP_DATE_TX = "analysis_prep_date_tx";
	public static final String KEY_PREP_START_TIME = "prep_start_time";
	public static final String KEY_PREP_START_TIMEZONE = "prep_start_timezone";
	public static final String KEY_PREP_END_DATE = "prep_end_date";
	public static final String KEY_PREP_END_TIME = "prep_end_time";
	public static final String KEY_PREP_END_TIMEZONE = "prep_end_timezone";
	public static final String KEY_PREP_DILUTION_FACTOR = "prep_dilution_factor";
	public static final String KEY_LAB_SAMPLE_PREP_URL = "lab_sample_prep_url";
	public static final String KEY_RESULT_OBJECT_NAME = "result_object_name";
	public static final String KEY_RESULT_OBJECT_TYPE = "result_object_type";
	public static final String KEY_RESULT_FILE_URL = "result_file_url";

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
