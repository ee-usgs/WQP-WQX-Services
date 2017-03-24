package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.ActivityColumn.KEY_ACTIVITY;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_ACTIVITY_METRIC_COMMENT;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_ACTIVITY_METRIC_SCORE;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_ACTIVITY_METRIC_UNIT;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_ACTIVITY_METRIC_VALUE;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_FORMULA_DESCRIPTION;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_INDEX_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_CITATION_CREATOR;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_CITATION_DATE;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_CITATION_ID;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_CITATION_PUBLISHER;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_CITATION_SUBJECT;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_CITATION_TITLE;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_TYPE_CONTEXT;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.ActivityMetricColumn.KEY_METRIC_TYPE_SCALE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_SITE_ID;

import java.util.LinkedHashMap;
import java.util.Map;

import gov.usgs.cida.wqp.BaseSpringTest;

public class TestActivityMetricMap {

	public static final Map<String, Object> ACTIVITY_METRIC;
	static {
		ACTIVITY_METRIC = new LinkedHashMap<String, Object>();
		ACTIVITY_METRIC.put(KEY_DATA_SOURCE, BaseSpringTest.STORET);
		ACTIVITY_METRIC.put(KEY_SITE_ID, "organization-siteId");
		ACTIVITY_METRIC.put(KEY_ACTIVITY, "activity");
		ACTIVITY_METRIC.put(KEY_ORGANIZATION, "organization");
		ACTIVITY_METRIC.put(KEY_ORGANIZATION_NAME, "organizationName");
		ACTIVITY_METRIC.put(KEY_METRIC_TYPE_IDENTIFIER, "3_2_type_identifier");
		ACTIVITY_METRIC.put(KEY_METRIC_TYPE_CONTEXT, "3_2_identifier_context");
		ACTIVITY_METRIC.put(KEY_METRIC_TYPE_NAME, "3_2_type_name");
		ACTIVITY_METRIC.put(KEY_METRIC_CITATION_TITLE, "3_2_resource_title");
		ACTIVITY_METRIC.put(KEY_METRIC_CITATION_CREATOR, "3_2_resource_creator");
		ACTIVITY_METRIC.put(KEY_METRIC_CITATION_SUBJECT, "3_2_resource_subject");
		ACTIVITY_METRIC.put(KEY_METRIC_CITATION_PUBLISHER, "3_2_resource_publisher");
		ACTIVITY_METRIC.put(KEY_METRIC_CITATION_DATE, "3_2_resource_data");
		ACTIVITY_METRIC.put(KEY_METRIC_CITATION_ID, "3_2_resource_identifier");
		ACTIVITY_METRIC.put(KEY_METRIC_TYPE_SCALE, "3_2_type_scale");
		ACTIVITY_METRIC.put(KEY_FORMULA_DESCRIPTION, "3_2_formula_description");
		ACTIVITY_METRIC.put(KEY_ACTIVITY_METRIC_VALUE, "3_2_measure_value");
		ACTIVITY_METRIC.put(KEY_ACTIVITY_METRIC_UNIT, "3_2_unit_code");
		ACTIVITY_METRIC.put(KEY_ACTIVITY_METRIC_SCORE, "3_2_score");
		ACTIVITY_METRIC.put(KEY_ACTIVITY_METRIC_COMMENT, "3_2_comment_text");
		ACTIVITY_METRIC.put(KEY_INDEX_IDENTIFIER, "3_2_index_identifier");
	}

	private TestActivityMetricMap() {
	}
}
