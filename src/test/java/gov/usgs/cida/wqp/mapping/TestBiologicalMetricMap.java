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
import java.util.LinkedHashMap;
import java.util.Map;

public class TestBiologicalMetricMap {
	
	public static final Map<String, Object> BIOLOGICAL_METRIC;
	static {
		BIOLOGICAL_METRIC = new LinkedHashMap<>();
		BIOLOGICAL_METRIC.put(KEY_ORGANIZATION, "sample_organization");
		BIOLOGICAL_METRIC.put(KEY_SITE_ID, "sample_siteId");
		BIOLOGICAL_METRIC.put(KEY_INDEX_IDENTIFIER, "sample_index_identifier");
		BIOLOGICAL_METRIC.put(KEY_INDEX_TYPE_IDENTIFIER, "sample_index_type_identifier");
		BIOLOGICAL_METRIC.put(KEY_INDEX_TYPE_CONTEXT, "sample_index_type_context");
		BIOLOGICAL_METRIC.put(KEY_INDEX_TYPE_NAME, "sample_index_type_name");
		BIOLOGICAL_METRIC.put(KEY_RESOURCE_TITLE_NAME, "sample_resource_title_name");
		BIOLOGICAL_METRIC.put(KEY_RESOURCE_CREATOR_NAME, "sample_resource_creator_name");
		BIOLOGICAL_METRIC.put(KEY_RESOURCE_SUBJECT_TEXT, "sample_resource_subject_name");
		BIOLOGICAL_METRIC.put(KEY_RESOURCE_PUBLISHER_NAME, "sample_resource_publisher_name");
		BIOLOGICAL_METRIC.put(KEY_RESOURCE_DATE, "sample_resource_date");
		BIOLOGICAL_METRIC.put(KEY_RESOURCE_IDENTIFIER, "sample_resource_identifier");
		BIOLOGICAL_METRIC.put(KEY_INDEX_TYPE_SCALE_TEXT, "sample_index_type_scale_text");
		BIOLOGICAL_METRIC.put(KEY_INDEX_SCORE_NUMERIC, "sample_index_score_numeric");
		BIOLOGICAL_METRIC.put(KEY_INDEX_QUALIFIER_CODE, "sample_index_qualifier_code");
		BIOLOGICAL_METRIC.put(KEY_INDEX_COMMENT, "sample_index_comment");
		BIOLOGICAL_METRIC.put(KEY_INDEX_CALCULATED_DATE, "sample_calculated_date");
		BIOLOGICAL_METRIC.put(KEY_DATA_SOURCE, "sample_data_source");
	}
	
	private TestBiologicalMetricMap() {
	}	
}
