package gov.usgs.cida.wqp.mapping.xml;

import static gov.usgs.cida.wqp.mapping.ActivityColumn.ACTIVITY;
import static gov.usgs.cida.wqp.mapping.ActivityColumn.KEY_ACTIVITY;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_SITE_ID;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.SITE_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.DETECTION_LIMIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.DETECTION_LIMIT_DESC;
import static gov.usgs.cida.wqp.mapping.ResultColumn.DETECTION_LIMIT_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT_DESC;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_EXTERNAL_RESULT_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.EXTERNAL_RESULT_ID;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import gov.usgs.cida.wqp.mapping.ColumnProfile;

@Component
public class ResDetectQntLmtWqx extends BaseWqx implements IXmlMapping {

	public static final Map<String, String> HARD_BREAK = new LinkedHashMap<>();

	public static final Map<String, List<String>> COLUMN_POSITION = new LinkedHashMap<>();

	public static final Map<String, List<ColumnProfile>> GROUPING = new LinkedHashMap<>();

	static {
		HARD_BREAK.put(KEY_ORGANIZATION, ROOT_NODE);
		HARD_BREAK.put(KEY_ACTIVITY, WQX_ORGANIZATION);
		HARD_BREAK.put(KEY_EXTERNAL_RESULT_ID, WQX_ACTIVITY);
		HARD_BREAK.put(KEY_DETECTION_LIMIT_ID, WQX_LAB_INFO);
	}

	static {
		COLUMN_POSITION.put(KEY_ORGANIZATION,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_ORGANIZATION_IDENTIFIER)));
		COLUMN_POSITION.put(KEY_ORGANIZATION_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_ORGANIZATION_FORMAL_NAME)));
		COLUMN_POSITION.put(KEY_ACTIVITY,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_ID)));
		COLUMN_POSITION.put(KEY_SITE_ID,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_MONITORING_LOCATION_IDENTIFIER)));
		COLUMN_POSITION.put(KEY_EXTERNAL_RESULT_ID,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_RESULT_IDENTIFIER)));
		COLUMN_POSITION.put(KEY_CHARACTERISTIC_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_CHAR_NAME)));
		COLUMN_POSITION.put(KEY_DETECTION_LIMIT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_LAB_INFO,
						WQX_DETECTION_LIMIT,
						WQX_DETECTION_LIMIT_VALUE,
						WQX_MEASURE_VALUE)));
		COLUMN_POSITION.put(KEY_DETECTION_LIMIT_UNIT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_LAB_INFO,
						WQX_DETECTION_LIMIT,
						WQX_DETECTION_LIMIT_VALUE,
						WQX_MEASURE_UNIT)));
		COLUMN_POSITION.put(KEY_DETECTION_LIMIT_DESC,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_LAB_INFO,
						WQX_DETECTION_LIMIT,
						WQX_DETECTION_LIMIT_TYPE)));
	}

	static {
		GROUPING.put(KEY_ORGANIZATION,
				new LinkedList<ColumnProfile>(Arrays.asList(ORGANIZATION, ORGANIZATION_NAME)));
		GROUPING.put(KEY_ACTIVITY,
				new LinkedList<ColumnProfile>(Arrays.asList(
						ACTIVITY,
						SITE_ID)));
		GROUPING.put(KEY_EXTERNAL_RESULT_ID,
				new LinkedList<ColumnProfile>(Arrays.asList(
						EXTERNAL_RESULT_ID,
						CHARACTERISTIC_NAME)));
		GROUPING.put(KEY_DETECTION_LIMIT_ID,
				new LinkedList<ColumnProfile>(Arrays.asList(
						DETECTION_LIMIT_DESC,
						DETECTION_LIMIT,
						DETECTION_LIMIT_UNIT)));
	}

	public String getEntryNodeName() {
		return WQX_ORGANIZATION;
	}

	public Map<String, List<String>> getStructure() {
		return COLUMN_POSITION;
	}

	public Map<String, String> getHardBreak() {
		return HARD_BREAK;
	}

	public Map<String, List<ColumnProfile>> getGrouping() {
		return GROUPING;
	}
}
