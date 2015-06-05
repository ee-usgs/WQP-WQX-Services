package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_COMMENT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_CONDUCTING_ORG;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_DEPTH;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_DEPTH_REF_POINT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_DEPTH_UNIT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_LOWER_DEPTH;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_LOWER_DEPTH_UNIT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_MEDIA_SUBDIV_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_START_TIME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_STOP_DATE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_STOP_TIME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_TYPE_CODE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_UPPER_DEPTH;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACTIVITY_UPPER_DEPTH_UNIT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACT_START_TIME_ZONE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ACT_STOP_TIME_ZONE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ANALYSIS_START_DATE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ANALYSIS_PREP_DATE_TX;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ANALYTICAL_METHOD_CITATION;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ANALYTICAL_METHOD_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ANALYTICAL_PROCEDURE_ID;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ANALYTICAL_PROCEDURE_SOURCE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_DETECTION_LIMIT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_DETECTION_LIMIT_DESC;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_DETECTION_LIMIT_UNIT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_DURATION_BASIS;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_EVENT_DATE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_HYDROLOGIC_CONDITION_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_HYDROLOGIC_EVENT_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_LAB_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_LAB_REMARK;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_PARTICLE_SIZE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_PRECISION;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_PROJECT_ID;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_P_CODE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_COMMENT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_DEPTH_ALT_REF_PT_TXT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_DEPTH_MEAS_UNIT_CODE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_DEPTH_MEAS_VALUE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_DETECTION_CONDITION_TX;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_ID;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_MEASURE_VALUE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_MEAS_QUAL_CODE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_UNIT;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_VALUE_STATUS;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_RESULT_VALUE_TYPE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_SAMPLE_AQFR_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_SAMPLE_COLLECT_EQUIP_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_SAMPLE_COLLECT_METHOD_CTX;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_SAMPLE_COLLECT_METHOD_ID;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_SAMPLE_COLLECT_METHOD_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_SAMPLE_FRACTION_TYPE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_SAMPLE_MEDIA;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_SAMPLE_TISSUE_ANATOMY_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_SAMPLE_TISSUE_TAXONOMIC_NAME;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_STATISTIC_TYPE;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_TEMPERATURE_BASIS_LEVEL;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_WEIGHT_BASIS_TYPE;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PcResultWqx extends BaseWqx implements IXmlMapping {

	public static final Map<String, String> HARD_BREAK = new LinkedHashMap<>();

	public static final Map<String, List<String>> COLUMN_POSITION = new LinkedHashMap<>();
	
	public static final Map<String, List<String>> GROUPING = new LinkedHashMap<>();

	
	static {
		HARD_BREAK.put(KEY_ORGANIZATION, ROOT_NODE);
		HARD_BREAK.put(KEY_ACTIVITY, WQX_ORGANIZATION);
		HARD_BREAK.put(KEY_RESULT_ID, WQX_ACTIVITY);
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
		COLUMN_POSITION.put(KEY_ACTIVITY_TYPE_CODE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_TYPE)));
		COLUMN_POSITION.put(KEY_SAMPLE_MEDIA,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_MEDIA)));
		COLUMN_POSITION.put(KEY_ACTIVITY_MEDIA_SUBDIV_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_MEDIA_SUB)));
		COLUMN_POSITION.put(KEY_EVENT_DATE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_START_DATE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_START_TIME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_START_TIME,
						WQX_TIME)));
		COLUMN_POSITION.put(KEY_ACT_START_TIME_ZONE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_START_TIME,
						WQX_TIME_ZONE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_STOP_DATE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_END_DATE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_STOP_TIME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_END_TIME,
						WQX_TIME)));
		COLUMN_POSITION.put(KEY_ACT_STOP_TIME_ZONE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_END_TIME,
						WQX_TIME_ZONE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_DEPTH,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_DEPTH,
						WQX_MEASURE_VALUE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_DEPTH_UNIT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_DEPTH,
						WQX_MEASURE_UNIT)));
		COLUMN_POSITION.put(KEY_ACTIVITY_DEPTH_REF_POINT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_DEPTH_REFERENCE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_UPPER_DEPTH,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_TOP_DEPTH,
						WQX_MEASURE_VALUE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_UPPER_DEPTH_UNIT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_TOP_DEPTH,
						WQX_MEASURE_UNIT)));
		COLUMN_POSITION.put(KEY_ACTIVITY_LOWER_DEPTH,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_BOTTOM_DEPTH,
						WQX_MEASURE_VALUE)));
		COLUMN_POSITION.put(KEY_ACTIVITY_LOWER_DEPTH_UNIT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_BOTTOM_DEPTH,
						WQX_MEASURE_UNIT)));
		COLUMN_POSITION.put(KEY_PROJECT_ID,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_PROJECT)));
		COLUMN_POSITION.put(KEY_ACTIVITY_CONDUCTING_ORG,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_CONDUCTION_ORG)));
		COLUMN_POSITION.put(KEY_ACTIVITY_COMMENT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_ACT_COMMENT)));
		COLUMN_POSITION.put(KEY_SAMPLE_AQFR_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_SAMPLE_AQUIFER)));
		COLUMN_POSITION.put(KEY_HYDROLOGIC_CONDITION_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_HYDROLOGIC_CONDITION)));
		COLUMN_POSITION.put(KEY_HYDROLOGIC_EVENT_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_ACT_DESCRIPTION,
						WQX_HYDROLOGIC_EVENT)));
		
		COLUMN_POSITION.put(KEY_SAMPLE_COLLECT_METHOD_ID,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_SAMPLE_DESCRIPTION,
						WQX_COLLECTION_METHOD,
						WQX_METHOD_ID)));
		COLUMN_POSITION.put(KEY_SAMPLE_COLLECT_METHOD_CTX,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_SAMPLE_DESCRIPTION,
						WQX_COLLECTION_METHOD,
						WQX_METHOD_CONTEXT)));
		COLUMN_POSITION.put(KEY_SAMPLE_COLLECT_METHOD_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_SAMPLE_DESCRIPTION,
						WQX_COLLECTION_METHOD,
						WQX_METHOD_NAME)));
		COLUMN_POSITION.put(KEY_SAMPLE_COLLECT_EQUIP_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_SAMPLE_DESCRIPTION,
						WQX_COLLECTION_EQUIPMENT)));
		
		COLUMN_POSITION.put(KEY_RESULT_DETECTION_CONDITION_TX,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_DETECTION_CONDITION)));
		COLUMN_POSITION.put(KEY_CHARACTERISTIC_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_CHAR_NAME)));
		COLUMN_POSITION.put(KEY_SAMPLE_FRACTION_TYPE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_SAMPLE_FRACTION)));
		COLUMN_POSITION.put(KEY_RESULT_MEASURE_VALUE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_RESULT_MEASURE,
						WQX_RESULT_MEASURE_VALUE
						)));
		COLUMN_POSITION.put(KEY_RESULT_UNIT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_RESULT_MEASURE,
						WQX_MEASURE_UNIT)));
		COLUMN_POSITION.put(KEY_RESULT_MEAS_QUAL_CODE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_RESULT_MEASURE,
						WQX_MEASURE_QUALIFIER)));
		COLUMN_POSITION.put(KEY_RESULT_VALUE_STATUS,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_STATUS_ID)));
		COLUMN_POSITION.put(KEY_STATISTIC_TYPE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_STATISTICAL_BASE)));
		COLUMN_POSITION.put(KEY_RESULT_VALUE_TYPE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_VALUE_TYPE)));
		COLUMN_POSITION.put(KEY_WEIGHT_BASIS_TYPE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_WEIGHT_BASIS)));
		COLUMN_POSITION.put(KEY_DURATION_BASIS,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_TIME_BASIS)));
		COLUMN_POSITION.put(KEY_TEMPERATURE_BASIS_LEVEL,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_TEMP_BASIS)));
		COLUMN_POSITION.put(KEY_PARTICLE_SIZE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_PARTICLE_SIZE)));
		COLUMN_POSITION.put(KEY_PRECISION,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_DATA_QUALITY,
						WQX_PRECISION)));
		COLUMN_POSITION.put(KEY_RESULT_COMMENT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_RESULT_COMMENT)));
		COLUMN_POSITION.put(KEY_P_CODE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_P_CODE)));
		COLUMN_POSITION.put(KEY_RESULT_DEPTH_MEAS_VALUE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_RESULT_DEPTH,
						WQX_MEASURE_VALUE)));
		COLUMN_POSITION.put(KEY_RESULT_DEPTH_MEAS_UNIT_CODE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_RESULT_DEPTH,
						WQX_MEASURE_UNIT)));
		COLUMN_POSITION.put(KEY_RESULT_DEPTH_ALT_REF_PT_TXT,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_RESULT_DETH_REFERENCE)));

		COLUMN_POSITION.put(KEY_SAMPLE_TISSUE_TAXONOMIC_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_BIOLOGICAL_RESULT,
						WQX_TAXON_NAME)));
		COLUMN_POSITION.put(KEY_SAMPLE_TISSUE_ANATOMY_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_BIOLOGICAL_RESULT,
						WQX_TISSUE_ANATOMY)));

		COLUMN_POSITION.put(KEY_ANALYTICAL_PROCEDURE_ID,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_ANALYTICAL_METHOD,
						WQX_METHOD_ID)));
		COLUMN_POSITION.put(KEY_ANALYTICAL_PROCEDURE_SOURCE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_ANALYTICAL_METHOD,
						WQX_METHOD_CONTEXT)));
		COLUMN_POSITION.put(KEY_ANALYTICAL_METHOD_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_ANALYTICAL_METHOD,
						WQX_METHOD_NAME)));
		COLUMN_POSITION.put(KEY_ANALYTICAL_METHOD_CITATION,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_ANALYTICAL_METHOD,
						WQX_METHOD_DESCRIPTION)));

		COLUMN_POSITION.put(KEY_LAB_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_LAB_INFO,
						WQX_LAB_NAME)));
		COLUMN_POSITION.put(KEY_ANALYSIS_START_DATE,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_LAB_INFO,
						WQX_ANALYSIS_START_DATE)));
		COLUMN_POSITION.put(KEY_LAB_REMARK,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_LAB_INFO,
						WQX_LAB_COMMENT)));
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

		COLUMN_POSITION.put(KEY_ANALYSIS_PREP_DATE_TX,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_LAB_SAMPLE_PREP,
						WQX_PREP_START_DATE)));

	}

	static {
		GROUPING.put(KEY_ORGANIZATION,
				new LinkedList<String>(Arrays.asList(KEY_ORGANIZATION, KEY_ORGANIZATION_NAME)));
		GROUPING.put(KEY_ACTIVITY,
				new LinkedList<String>(Arrays.asList(
						KEY_ACTIVITY,
						KEY_ACTIVITY_TYPE_CODE,
						KEY_SAMPLE_MEDIA,
						KEY_ACTIVITY_MEDIA_SUBDIV_NAME,
						KEY_EVENT_DATE,
						KEY_ACTIVITY_START_TIME,
						KEY_ACT_START_TIME_ZONE,
						KEY_ACTIVITY_STOP_DATE,
						KEY_ACTIVITY_STOP_TIME,
						KEY_ACT_STOP_TIME_ZONE,
						KEY_ACTIVITY_DEPTH,
						KEY_ACTIVITY_DEPTH_UNIT,
						KEY_ACTIVITY_DEPTH_REF_POINT,
						KEY_ACTIVITY_UPPER_DEPTH,
						KEY_ACTIVITY_UPPER_DEPTH_UNIT,
						KEY_ACTIVITY_LOWER_DEPTH,
						KEY_ACTIVITY_LOWER_DEPTH_UNIT,
						KEY_PROJECT_ID,
						KEY_ACTIVITY_CONDUCTING_ORG,
						KEY_ACTIVITY_COMMENT,
						KEY_SAMPLE_AQFR_NAME,
						KEY_HYDROLOGIC_CONDITION_NAME,
						KEY_HYDROLOGIC_EVENT_NAME,
						KEY_SAMPLE_COLLECT_METHOD_ID,
						KEY_SAMPLE_COLLECT_METHOD_CTX,
						KEY_SAMPLE_COLLECT_METHOD_NAME,
						KEY_SAMPLE_COLLECT_EQUIP_NAME
						)));
		GROUPING.put(KEY_RESULT_ID,
				new LinkedList<String>(Arrays.asList(
						KEY_RESULT_DETECTION_CONDITION_TX,
						KEY_CHARACTERISTIC_NAME,
						KEY_SAMPLE_FRACTION_TYPE,
						KEY_RESULT_MEASURE_VALUE,
						KEY_RESULT_UNIT,
						KEY_RESULT_MEAS_QUAL_CODE,
						KEY_RESULT_VALUE_STATUS,
						KEY_STATISTIC_TYPE,
						KEY_RESULT_VALUE_TYPE,
						KEY_WEIGHT_BASIS_TYPE,
						KEY_DURATION_BASIS,
						KEY_TEMPERATURE_BASIS_LEVEL,
						KEY_PARTICLE_SIZE,
						KEY_PRECISION,
						KEY_RESULT_COMMENT,
						KEY_P_CODE,
						KEY_RESULT_DEPTH_MEAS_VALUE,
						KEY_RESULT_DEPTH_MEAS_UNIT_CODE,
						KEY_RESULT_DEPTH_ALT_REF_PT_TXT,
						KEY_SAMPLE_TISSUE_TAXONOMIC_NAME,
						KEY_SAMPLE_TISSUE_ANATOMY_NAME,
						KEY_ANALYTICAL_PROCEDURE_ID,
						KEY_ANALYTICAL_PROCEDURE_SOURCE,
						KEY_ANALYTICAL_METHOD_NAME,
						KEY_ANALYTICAL_METHOD_CITATION,
						KEY_LAB_NAME,
						KEY_ANALYSIS_START_DATE,
						KEY_LAB_REMARK,
						KEY_DETECTION_LIMIT_DESC,
						KEY_DETECTION_LIMIT,
						KEY_DETECTION_LIMIT_UNIT,
						KEY_ANALYSIS_PREP_DATE_TX
						)));
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

	public Map<String, List<String>> getGrouping() {
		return GROUPING;
	}

}
