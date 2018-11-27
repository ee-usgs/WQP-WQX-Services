package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_INDEX_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE_ID;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_SITE_ID;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_CALCULATED_DATE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_COMMENT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_QUALIFIER_CODE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_SCORE_NUMERIC;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_TYPE_CONTEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_TYPE_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_TYPE_SCALE_TEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_CREATOR_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_DATE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_PUBLISHER_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_SUBJECT_TEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_TITLE_NAME;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestBiologicalMetricMap {
	
	public static final Map<String, Object> BIOLOGICAL_METRIC_0;
	static {
		BIOLOGICAL_METRIC_0 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_0.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_0.put(KEY_ORGANIZATION, "sample_organization_0");
		BIOLOGICAL_METRIC_0.put(KEY_SITE_ID, "sample_siteId_0");
		BIOLOGICAL_METRIC_0.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_0");
		BIOLOGICAL_METRIC_0.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_0");
		BIOLOGICAL_METRIC_0.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_0");
		BIOLOGICAL_METRIC_0.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_0");
		BIOLOGICAL_METRIC_0.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_0");
		BIOLOGICAL_METRIC_0.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_0");
		BIOLOGICAL_METRIC_0.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_0");
		BIOLOGICAL_METRIC_0.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_0");
		BIOLOGICAL_METRIC_0.put(KEY_RESOURCE_DATE, "sample_resource_date_0");
		BIOLOGICAL_METRIC_0.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_0");
		BIOLOGICAL_METRIC_0.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_0");
		BIOLOGICAL_METRIC_0.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_0");
		BIOLOGICAL_METRIC_0.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_0");
		BIOLOGICAL_METRIC_0.put(KEY_INDEX_COMMENT, "sample_index_comment_0");
		BIOLOGICAL_METRIC_0.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_0");
		BIOLOGICAL_METRIC_0.put(KEY_DATA_SOURCE, "sample_data_source_0");
	}
	
	public static final Map<String, Object> BIOLOGICAL_METRIC_1;
	static {
		BIOLOGICAL_METRIC_1 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_1.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_1.put(KEY_ORGANIZATION, "sample_organization_1");
		BIOLOGICAL_METRIC_1.put(KEY_SITE_ID, "sample_siteId_1");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_1");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_1");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_1");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_1");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_1");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_1");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_1");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_1");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_DATE, "sample_resource_date_1");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_1");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_1");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_1");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_1");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_COMMENT, "sample_index_comment_1");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_1");
		BIOLOGICAL_METRIC_1.put(KEY_DATA_SOURCE, "sample_data_source_1");
	}
	
	public static final Map<String, Object> BIOLOGICAL_METRIC_2;
	static {
		BIOLOGICAL_METRIC_2 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_2.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_2.put(KEY_ORGANIZATION, "sample_organization_2");
		BIOLOGICAL_METRIC_2.put(KEY_SITE_ID, "sample_siteId_2");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_2");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_2");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_2");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_2");
		BIOLOGICAL_METRIC_2.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_2");
		BIOLOGICAL_METRIC_2.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_2");
		BIOLOGICAL_METRIC_2.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_2");
		BIOLOGICAL_METRIC_2.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_2");
		BIOLOGICAL_METRIC_2.put(KEY_RESOURCE_DATE, "sample_resource_date_2");
		BIOLOGICAL_METRIC_2.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_2");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_2");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_2");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_2");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_COMMENT, "sample_index_comment_2");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_2");
		BIOLOGICAL_METRIC_2.put(KEY_DATA_SOURCE, "sample_data_source_2");
	}
	
		public static final Map<String, Object> BIOLOGICAL_METRIC_3;
	static {
		BIOLOGICAL_METRIC_3 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_3.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_3.put(KEY_ORGANIZATION, "sample_organization");
		BIOLOGICAL_METRIC_3.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_3.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_3");
		BIOLOGICAL_METRIC_3.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_3");
		BIOLOGICAL_METRIC_3.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_3");
		BIOLOGICAL_METRIC_3.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_3");
		BIOLOGICAL_METRIC_3.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_3");
		BIOLOGICAL_METRIC_3.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_3");
		BIOLOGICAL_METRIC_3.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_3");
		BIOLOGICAL_METRIC_3.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_3");
		BIOLOGICAL_METRIC_3.put(KEY_RESOURCE_DATE, "sample_resource_date_3");
		BIOLOGICAL_METRIC_3.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_3");
		BIOLOGICAL_METRIC_3.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_3");
		BIOLOGICAL_METRIC_3.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_3");
		BIOLOGICAL_METRIC_3.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_3");
		BIOLOGICAL_METRIC_3.put(KEY_INDEX_COMMENT, "sample_index_comment_3");
		BIOLOGICAL_METRIC_3.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_3");
		BIOLOGICAL_METRIC_3.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
		public static final Map<String, Object> BIOLOGICAL_METRIC_4;
	static {
		BIOLOGICAL_METRIC_4 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_4.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_4.put(KEY_ORGANIZATION, "sample_organization");
		BIOLOGICAL_METRIC_4.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_4.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_3");
		BIOLOGICAL_METRIC_4.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_3");
		BIOLOGICAL_METRIC_4.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_3");
		BIOLOGICAL_METRIC_4.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_3");
		BIOLOGICAL_METRIC_4.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_3");
		BIOLOGICAL_METRIC_4.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_3");
		BIOLOGICAL_METRIC_4.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_3");
		BIOLOGICAL_METRIC_4.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_3");
		BIOLOGICAL_METRIC_4.put(KEY_RESOURCE_DATE, "sample_resource_date_3");
		BIOLOGICAL_METRIC_4.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_3");
		BIOLOGICAL_METRIC_4.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_3");
		BIOLOGICAL_METRIC_4.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_3");
		BIOLOGICAL_METRIC_4.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_3");
		BIOLOGICAL_METRIC_4.put(KEY_INDEX_COMMENT, "sample_index_comment_3");
		BIOLOGICAL_METRIC_4.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_3");
		BIOLOGICAL_METRIC_4.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
		public static final Map<String, Object> BIOLOGICAL_METRIC_5;
	static {
		BIOLOGICAL_METRIC_5 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_5.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_5.put(KEY_ORGANIZATION, "sample_organization");
		BIOLOGICAL_METRIC_5.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_5.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_3");
		BIOLOGICAL_METRIC_5.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_3");
		BIOLOGICAL_METRIC_5.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_3");
		BIOLOGICAL_METRIC_5.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_3");
		BIOLOGICAL_METRIC_5.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_3");
		BIOLOGICAL_METRIC_5.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_3");
		BIOLOGICAL_METRIC_5.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_3");
		BIOLOGICAL_METRIC_5.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_3");
		BIOLOGICAL_METRIC_5.put(KEY_RESOURCE_DATE, "sample_resource_date_3");
		BIOLOGICAL_METRIC_5.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_3");
		BIOLOGICAL_METRIC_5.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_3");
		BIOLOGICAL_METRIC_5.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_3");
		BIOLOGICAL_METRIC_5.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_3");
		BIOLOGICAL_METRIC_5.put(KEY_INDEX_COMMENT, "sample_index_comment_3");
		BIOLOGICAL_METRIC_5.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_3");
		BIOLOGICAL_METRIC_5.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
		public static final Map<String, Object> BIOLOGICAL_METRIC_6;
	static {
		BIOLOGICAL_METRIC_6 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_6.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_6.put(KEY_ORGANIZATION, "organization");
		BIOLOGICAL_METRIC_6.put(KEY_SITE_ID, "organization-siteId");
		BIOLOGICAL_METRIC_6.put(KEY_INDEX_IDENTIFIER, "RM:OWW04440-0298:040825:RR:SEDI_DEP");
		BIOLOGICAL_METRIC_6.put(KEY_INDEX_TYPE_IDENTIFIER, "BENT_MMI_COND");
		BIOLOGICAL_METRIC_6.put(KEY_INDEX_TYPE_CONTEXT, "Biological");
		BIOLOGICAL_METRIC_6.put(KEY_INDEX_TYPE_NAME, "Condition class based on benthic MMI score");
		BIOLOGICAL_METRIC_6.put(KEY_RESOURCE_TITLE_NAME, "Idaho Small Stream Ecological Assessment Framework");
		BIOLOGICAL_METRIC_6.put(KEY_RESOURCE_CREATOR_NAME, "Quality Assurance Section");
		BIOLOGICAL_METRIC_6.put(KEY_RESOURCE_SUBJECT_TEXT, "an ecoregional scale. It also discusses change in water quality conditions in the Wadeable Streams Assessment");
		BIOLOGICAL_METRIC_6.put(KEY_RESOURCE_PUBLISHER_NAME, "MDNR, Enviromental Services Program");
		BIOLOGICAL_METRIC_6.put(KEY_RESOURCE_DATE, "17-OCT-10");
		BIOLOGICAL_METRIC_6.put(KEY_RESOURCE_IDENTIFIER, "Habitat Score");
		BIOLOGICAL_METRIC_6.put(KEY_INDEX_TYPE_SCALE_TEXT, "0-12");
		BIOLOGICAL_METRIC_6.put(KEY_INDEX_SCORE_NUMERIC, null);
		BIOLOGICAL_METRIC_6.put(KEY_INDEX_QUALIFIER_CODE, "SOURCE=Benthics_Counts");
		BIOLOGICAL_METRIC_6.put(KEY_INDEX_COMMENT, "10-NOV-2018");
		BIOLOGICAL_METRIC_6.put(KEY_INDEX_CALCULATED_DATE, "10-NOV-2018");
		BIOLOGICAL_METRIC_6.put(KEY_DATA_SOURCE, "STORET");
	}
	
		public static final Map<String, Object> BIOLOGICAL_METRIC_7;
	static {
		BIOLOGICAL_METRIC_7 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_7.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_7.put(KEY_ORGANIZATION, "sample_organization");
		BIOLOGICAL_METRIC_7.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_7.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_3");
		BIOLOGICAL_METRIC_7.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_3");
		BIOLOGICAL_METRIC_7.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_3");
		BIOLOGICAL_METRIC_7.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_3");
		BIOLOGICAL_METRIC_7.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_3");
		BIOLOGICAL_METRIC_7.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_3");
		BIOLOGICAL_METRIC_7.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_3");
		BIOLOGICAL_METRIC_7.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_3");
		BIOLOGICAL_METRIC_7.put(KEY_RESOURCE_DATE, "sample_resource_date_3");
		BIOLOGICAL_METRIC_7.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_3");
		BIOLOGICAL_METRIC_7.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_3");
		BIOLOGICAL_METRIC_7.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_3");
		BIOLOGICAL_METRIC_7.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_3");
		BIOLOGICAL_METRIC_7.put(KEY_INDEX_COMMENT, "sample_index_comment_3");
		BIOLOGICAL_METRIC_7.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_3");
		BIOLOGICAL_METRIC_7.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
		public static final Map<String, Object> BIOLOGICAL_METRIC_8;
	static {
		BIOLOGICAL_METRIC_8 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_8.put(KEY_DATA_SOURCE_ID, "3");
		BIOLOGICAL_METRIC_8.put(KEY_ORGANIZATION, "organization");
		BIOLOGICAL_METRIC_8.put(KEY_SITE_ID, "organization-siteId");
		BIOLOGICAL_METRIC_8.put(KEY_INDEX_IDENTIFIER, "RM:OWW04440-0298:040825:RR:SEDI_DEP");
		BIOLOGICAL_METRIC_8.put(KEY_INDEX_TYPE_IDENTIFIER, "BENT_MMI_COND");
		BIOLOGICAL_METRIC_8.put(KEY_INDEX_TYPE_CONTEXT, "Biological");
		BIOLOGICAL_METRIC_8.put(KEY_INDEX_TYPE_NAME, "Condition class based on benthic MMI score");
		BIOLOGICAL_METRIC_8.put(KEY_RESOURCE_TITLE_NAME, "Idaho Small Stream Ecological Assessment Framework");
		BIOLOGICAL_METRIC_8.put(KEY_RESOURCE_CREATOR_NAME, "Quality Assurance Section");
		BIOLOGICAL_METRIC_8.put(KEY_RESOURCE_SUBJECT_TEXT, "an ecoregional scale. It also discusses change in water quality conditions in the Wadeable Streams Assessment");
		BIOLOGICAL_METRIC_8.put(KEY_RESOURCE_PUBLISHER_NAME, "MDNR, Enviromental Services Program");
		BIOLOGICAL_METRIC_8.put(KEY_RESOURCE_DATE, "17-OCT-10");
		BIOLOGICAL_METRIC_8.put(KEY_RESOURCE_IDENTIFIER, "Habitat Score");
		BIOLOGICAL_METRIC_8.put(KEY_INDEX_TYPE_SCALE_TEXT, "0-12");
		BIOLOGICAL_METRIC_8.put(KEY_INDEX_SCORE_NUMERIC, "Poor");
		BIOLOGICAL_METRIC_8.put(KEY_INDEX_QUALIFIER_CODE, null);
		BIOLOGICAL_METRIC_8.put(KEY_INDEX_COMMENT, "SOURCE=Benthics_Counts");
		BIOLOGICAL_METRIC_8.put(KEY_INDEX_CALCULATED_DATE, "10-NOV-2018");
		BIOLOGICAL_METRIC_8.put(KEY_DATA_SOURCE, "STORET");
	}
	
		public static final Map<String, Object> BIOLOGICAL_METRIC_9;
	static {
		BIOLOGICAL_METRIC_9 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_9.put(KEY_ORGANIZATION, "sample_organization");
		BIOLOGICAL_METRIC_9.put(KEY_DATA_SOURCE_ID, "data_source");		
		BIOLOGICAL_METRIC_9.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_9.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_3");
		BIOLOGICAL_METRIC_9.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_3");
		BIOLOGICAL_METRIC_9.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_3");
		BIOLOGICAL_METRIC_9.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_3");
		BIOLOGICAL_METRIC_9.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_3");
		BIOLOGICAL_METRIC_9.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_3");
		BIOLOGICAL_METRIC_9.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_3");
		BIOLOGICAL_METRIC_9.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_3");
		BIOLOGICAL_METRIC_9.put(KEY_RESOURCE_DATE, "sample_resource_date_3");
		BIOLOGICAL_METRIC_9.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_3");
		BIOLOGICAL_METRIC_9.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_3");
		BIOLOGICAL_METRIC_9.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_3");
		BIOLOGICAL_METRIC_9.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_3");
		BIOLOGICAL_METRIC_9.put(KEY_INDEX_COMMENT, "sample_index_comment_3");
		BIOLOGICAL_METRIC_9.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_3");
		BIOLOGICAL_METRIC_9.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
		public static final Map<String, Object> BIOLOGICAL_METRIC_10;
	static {
		BIOLOGICAL_METRIC_10 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_10.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_10.put(KEY_ORGANIZATION, "sample_organization");
		BIOLOGICAL_METRIC_10.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_10.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_3");
		BIOLOGICAL_METRIC_10.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_3");
		BIOLOGICAL_METRIC_10.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_3");
		BIOLOGICAL_METRIC_10.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_3");
		BIOLOGICAL_METRIC_10.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_3");
		BIOLOGICAL_METRIC_10.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_3");
		BIOLOGICAL_METRIC_10.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_3");
		BIOLOGICAL_METRIC_10.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_3");
		BIOLOGICAL_METRIC_10.put(KEY_RESOURCE_DATE, "sample_resource_date_3");
		BIOLOGICAL_METRIC_10.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_3");
		BIOLOGICAL_METRIC_10.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_3");
		BIOLOGICAL_METRIC_10.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_3");
		BIOLOGICAL_METRIC_10.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_3");
		BIOLOGICAL_METRIC_10.put(KEY_INDEX_COMMENT, "sample_index_comment_3");
		BIOLOGICAL_METRIC_10.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_3");
		BIOLOGICAL_METRIC_10.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
			public static final Map<String, Object> BIOLOGICAL_METRIC_11;
	static {
		BIOLOGICAL_METRIC_11 = new LinkedHashMap<>();
		
		BIOLOGICAL_METRIC_11.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_11.put(KEY_ORGANIZATION, "sample_organization");		
		BIOLOGICAL_METRIC_11.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_11.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_3");
		BIOLOGICAL_METRIC_11.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_3");
		BIOLOGICAL_METRIC_11.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_3");
		BIOLOGICAL_METRIC_11.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_3");
		BIOLOGICAL_METRIC_11.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_3");
		BIOLOGICAL_METRIC_11.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_3");
		BIOLOGICAL_METRIC_11.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_3");
		BIOLOGICAL_METRIC_11.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_3");
		BIOLOGICAL_METRIC_11.put(KEY_RESOURCE_DATE, "sample_resource_date_3");
		BIOLOGICAL_METRIC_11.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_3");
		BIOLOGICAL_METRIC_11.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_3");
		BIOLOGICAL_METRIC_11.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_3");
		BIOLOGICAL_METRIC_11.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_3");
		BIOLOGICAL_METRIC_11.put(KEY_INDEX_COMMENT, "sample_index_comment_3");
		BIOLOGICAL_METRIC_11.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_3");
		BIOLOGICAL_METRIC_11.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
			public static final Map<String, Object> BIOLOGICAL_METRIC_12;
	static {
		BIOLOGICAL_METRIC_12 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_12.put(KEY_DATA_SOURCE_ID, "data_source");
		BIOLOGICAL_METRIC_12.put(KEY_ORGANIZATION, "sample_organization");		
		BIOLOGICAL_METRIC_12.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_12.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier_3");
		BIOLOGICAL_METRIC_12.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier_3");
		BIOLOGICAL_METRIC_12.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context_3");
		BIOLOGICAL_METRIC_12.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name_3");
		BIOLOGICAL_METRIC_12.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name_3");
		BIOLOGICAL_METRIC_12.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name_3");
		BIOLOGICAL_METRIC_12.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name_3");
		BIOLOGICAL_METRIC_12.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name_3");
		BIOLOGICAL_METRIC_12.put(KEY_RESOURCE_DATE, "sample_resource_date_3");
		BIOLOGICAL_METRIC_12.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier_3");
		BIOLOGICAL_METRIC_12.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text_3");
		BIOLOGICAL_METRIC_12.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric_3");
		BIOLOGICAL_METRIC_12.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code_3");
		BIOLOGICAL_METRIC_12.put(KEY_INDEX_COMMENT, "sample_index_comment_3");
		BIOLOGICAL_METRIC_12.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date_3");
		BIOLOGICAL_METRIC_12.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
	public static final List<Map<String, Object>> BIOLOGICAL_METRIC =
			Arrays.asList(BIOLOGICAL_METRIC_0,
					BIOLOGICAL_METRIC_1,
					BIOLOGICAL_METRIC_2,
					BIOLOGICAL_METRIC_3,
					BIOLOGICAL_METRIC_4,
					BIOLOGICAL_METRIC_5,
					BIOLOGICAL_METRIC_6,
					BIOLOGICAL_METRIC_7,
					BIOLOGICAL_METRIC_8,
					BIOLOGICAL_METRIC_9,
					BIOLOGICAL_METRIC_10,
					BIOLOGICAL_METRIC_11,
					BIOLOGICAL_METRIC_12
					);
	
	private TestBiologicalMetricMap() {
	}	
}
