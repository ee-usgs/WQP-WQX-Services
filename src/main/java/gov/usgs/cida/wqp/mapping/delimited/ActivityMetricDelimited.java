package gov.usgs.cida.wqp.mapping.delimited;

import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.*;

public class ActivityMetricDelimited extends BaseDelimited {

	//Column Headings for the Keys
	public static final String VALUE_METRIC_TYPE_IDENTIFIER = WQX_METRIC_TYPE_ID;
	public static final String VALUE_METRIC_TYPE_CONTEXT = WQX_METRIC_TYPE_ID_CONTEXT;
	public static final String VALUE_METRIC_TYPE_NAME = WQX_METRIC_TYPE;
	public static final String VALUE_METRIC_CITATION_TITLE = WQX_METRIC_TYPE_CITATION + VAL_DEL + WQX_RESOURCE_TITLE;
	public static final String VALUE_METRIC_CITATION_CREATOR = WQX_METRIC_TYPE_CITATION + VAL_DEL + WQX_RESOURCE_CREATOR;
	public static final String VALUE_METRIC_CITATION_SUBJECT = WQX_METRIC_TYPE_CITATION + VAL_DEL + WQX_RESOURCE_SUBJECT;
	public static final String VALUE_METRIC_CITATION_PUBLISHER = WQX_METRIC_TYPE_CITATION + VAL_DEL + WQX_RESOURCE_PUBLISHER;
	public static final String VALUE_METRIC_CITATION_DATE = WQX_METRIC_TYPE_CITATION + VAL_DEL + WQX_RESOURCE_DATE;
	public static final String VALUE_METRIC_CITATION_ID = WQX_METRIC_TYPE_CITATION + VAL_DEL + WQX_RESOURCE_ID;
	public static final String VALUE_METRIC_TYPE_SCALE = WQX_METRIC_TYPE_SCALE;
	public static final String VALUE_FORMULA_DESCRIPTION = WQX_FORMULA_DESCRIPTION;
	public static final String VALUE_ACTIVITY_METRIC_VALUE = WQX_METRIC_VALUE + VAL_DEL + WQX_MEASURE_VALUE;
	public static final String VALUE_ACTIVITY_METRIC_UNIT = WQX_METRIC_VALUE + VAL_DEL + WQX_MEASURE_UNIT;
	public static final String VALUE_ACTIVITY_METRIC_SCORE = WQX_METRIC_SCORE;
	public static final String VALUE_ACTIVITY_METRIC_COMMENT = WQX_METRIC_COMMENT;
	public static final String VALUE_INDEX_IDENTIFIER = WQX_INDEX_ID;

	private ActivityMetricDelimited() {
	}

}
