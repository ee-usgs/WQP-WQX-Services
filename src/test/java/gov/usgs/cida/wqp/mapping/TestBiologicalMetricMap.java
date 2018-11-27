package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_INDEX_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE;
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
	
	public static final Map<String, Object> BIOLOGICAL_METRIC_1;
	static {
		BIOLOGICAL_METRIC_1 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_1.put(KEY_ORGANIZATION, "sample_organization");
		BIOLOGICAL_METRIC_1.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_DATE, "sample_resource_date");
		BIOLOGICAL_METRIC_1.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_COMMENT, "sample_index_comment");
		BIOLOGICAL_METRIC_1.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date");
		BIOLOGICAL_METRIC_1.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
	public static final Map<String, Object> BIOLOGICAL_METRIC_2;
	static {
		BIOLOGICAL_METRIC_2 = new LinkedHashMap<>();
		BIOLOGICAL_METRIC_2.put(KEY_ORGANIZATION, "sample_organization");
		BIOLOGICAL_METRIC_2.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC_2.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier");
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
		BIOLOGICAL_METRIC_2.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
	public static final Map<String, Object> BIOLOGICAL_METRIC_3;
	static {
		BIOLOGICAL_METRIC_3 = new LinkedHashMap<>();
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
	
	public static final List<Map<String, Object>> BIOLOGICAL_METRIC =
			Arrays.asList(BIOLOGICAL_METRIC_1, BIOLOGICAL_METRIC_2, BIOLOGICAL_METRIC_3);
	
	private TestBiologicalMetricMap() {
	}	
}
