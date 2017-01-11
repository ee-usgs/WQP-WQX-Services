package gov.usgs.cida.wqp.mapping.xml;

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
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import gov.usgs.cida.wqp.mapping.ColumnProfile;

public class ActivityMetricWqx extends BaseWqx implements IXmlMapping {


	public static final Map<String, String> HARD_BREAK = new LinkedHashMap<>();

	public static final Map<String, List<String>> COLUMN_POSITION = new LinkedHashMap<>();
	
	public static final Map<String, List<ColumnProfile>> GROUPING = new LinkedHashMap<>();

	
	static {
		HARD_BREAK.put(KEY_ORGANIZATION, ROOT_NODE);
		HARD_BREAK.put(KEY_ACTIVITY, WQX_ORGANIZATION);
	}
	
	static {
		COLUMN_POSITION.put(KEY_METRIC_TYPE_IDENTIFIER,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE_ID)));
		COLUMN_POSITION.put(KEY_METRIC_TYPE_CONTEXT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE_ID_CONTEXT)));
		COLUMN_POSITION.put(KEY_METRIC_TYPE_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE)));
		COLUMN_POSITION.put(KEY_METRIC_CITATION_TITLE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE_CITATION,
						WQX_RESOURCE_TITLE)));
		COLUMN_POSITION.put(KEY_METRIC_CITATION_CREATOR,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE_CITATION,
						WQX_RESOURCE_CREATOR)));
		COLUMN_POSITION.put(KEY_METRIC_CITATION_SUBJECT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE_CITATION,
						WQX_RESOURCE_SUBJECT)));
		COLUMN_POSITION.put(KEY_METRIC_CITATION_PUBLISHER,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE_CITATION,
						WQX_RESOURCE_PUBLISHER)));
		COLUMN_POSITION.put(KEY_METRIC_CITATION_DATE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE_CITATION,
						WQX_RESOURCE_DATE)));
		COLUMN_POSITION.put(KEY_METRIC_CITATION_ID,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE_CITATION,
						WQX_RESOURCE_ID)));
		COLUMN_POSITION.put(KEY_METRIC_TYPE_SCALE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_METRIC_TYPE_SCALE)));
		COLUMN_POSITION.put(KEY_FORMULA_DESCRIPTION,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_ACTIVITY_METRIC_TYPE,
						WQX_FORMULA_DESCRIPTION)));
		COLUMN_POSITION.put(KEY_ACTIVITY_METRIC_VALUE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_METRIC_VALUE,
						WQX_MEASURE_VALUE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_METRIC_UNIT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_METRIC_VALUE,
						WQX_MEASURE_UNIT)));
		COLUMN_POSITION.put(KEY_ACTIVITY_METRIC_SCORE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_METRIC_SCORE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_METRIC_COMMENT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_METRIC_COMMENT)));
		COLUMN_POSITION.put(KEY_INDEX_IDENTIFIER,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACTIVITY_METRIC,
						WQX_INDEX_ID)));
	}

	@Override
	public Map<String, List<String>> getStructure() {
		return COLUMN_POSITION;
	}

	@Override
	public Map<String, String> getHardBreak() {
		return HARD_BREAK;
	}

	@Override
	public Map<String, List<ColumnProfile>> getGrouping() {
		return GROUPING;
	}

	@Override
	public String getEntryNodeName() {
		return WQX_ORGANIZATION;
	}
}
