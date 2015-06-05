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
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ANALYSIS_PREP_DATE_TX;
import static gov.usgs.cida.wqp.mapping.PcResultColumn.KEY_ANALYSIS_START_DATE;
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
public class BioResultWqx extends BaseWqx implements IXmlMapping {

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
		
		
//		public static final String KEY_ACTIVITY_RELATIVE_DEPTH = "ACTIVITY_RELATIVE_DEPTH_NAME";
		
		
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

		
		
//		public static final String KEY_ACTIVITY_LATITUDE = "ACTIVITY_LATITUDE";
//		public static final String KEY_ACTIVITY_LONGITUDE = "ACTIVITY_LONGITUDE";
//		public static final String KEY_ACTIVITY_SOURCE_MAP_SCALE = "ACTIVITY_SOURCE_MAP_SCALE";
//		public static final String KEY_ACT_HORIZONTAL_ACCURACY = "ACT_HORIZONTAL_ACCURACY";
//		public static final String KEY_ACT_HORIZONTAL_ACCURACY_UNIT = "ACT_HORIZONTAL_ACCURACY_UNIT";
//		public static final String KEY_ACT_HORIZONTAL_COLLECT_METHOD = "ACT_HORIZONTAL_COLLECT_METHOD";
//		public static final String KEY_ACT_HORIZONTAL_DATUM_NAME = "ACT_HORIZONTAL_DATUM_NAME";
//		public static final String KEY_ASSEMBLAGE_SAMPLED_NAME = "ASSEMBLAGE_SAMPLED_NAME";
//		public static final String KEY_ACT_COLLECTION_DURATION = "ACT_COLLECTION_DURATION";
//		public static final String KEY_ACT_COLLECTION_DURATION_UNIT = "ACT_COLLECTION_DURATION_UNIT";
//		public static final String KEY_ACT_SAM_COMPNT_NAME = "ACT_SAM_COMPNT_NAME";
//		public static final String KEY_ACT_SAM_COMPNT_PLACE_IN_SERIES = "ACT_SAM_COMPNT_PLACE_IN_SERIES";
//		public static final String KEY_ACT_REACH_LENGTH = "ACT_REACH_LENGTH";
//		public static final String KEY_ACT_REACH_LENGTH_UNIT = "ACT_REACH_LENGTH_UNIT";
//		public static final String KEY_ACT_REACH_WIDTH = "ACT_REACH_WIDTH";
//		public static final String KEY_ACT_REACH_WIDTH_UNIT = "ACT_REACH_WIDTH_UNIT";
//		public static final String KEY_ACT_PASS_COUNT = "ACT_PASS_COUNT";
//		public static final String KEY_NET_TYPE_NAME = "NET_TYPE_NAME";
//		public static final String KEY_ACT_NET_SURFACE_AREA = "ACT_NET_SURFACE_AREA";
//		public static final String KEY_ACT_NET_SURFACE_AREA_UNIT = "ACT_NET_SURFACE_AREA_UNIT";
//		public static final String KEY_ACT_NET_MESH_SIZE = "ACT_NET_MESH_SIZE";
//		public static final String KEY_ACT_NET_MESH_SIZE_UNIT = "ACT_NET_MESH_SIZE_UNIT";
//		public static final String KEY_ACT_BOAT_SPEED = "ACT_BOAT_SPEED";
//		public static final String KEY_ACT_BOAT_SPEED_UNIT = "ACT_BOAT_SPEED_UNIT";
//		public static final String KEY_ACT_CURRENT_SPEED = "ACT_CURRENT_SPEED";
//		public static final String KEY_ACT_CURRENT_SPEED_UNIT = "ACT_CURRENT_SPEED_UNIT";
//		public static final String KEY_TOXICITY_TEST_TYPE_NAME = "TOXICITY_TEST_TYPE_NAME";
		
		

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
		
		
//		public static final String KEY_ACT_SAM_COLLECT_METH_QUAL_TYPE = "ACT_SAM_COLLECT_METH_QUAL_TYPE";
//		public static final String KEY_ACT_SAM_COLLECT_METH_DESC = "ACT_SAM_COLLECT_METH_DESC";
		
		
		COLUMN_POSITION.put(KEY_SAMPLE_COLLECT_EQUIP_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_SAMPLE_DESCRIPTION,
						WQX_COLLECTION_EQUIPMENT)));
		
		
//		public static final String KEY_ACT_SAM_COLLECT_EQUIP_COMMENTS = "ACT_SAM_COLLECT_EQUIP_COMMENTS";
//		public static final String KEY_ACT_SAM_PREP_METH_ID = "ACT_SAM_PREP_METH_ID";
//		public static final String KEY_ACT_SAM_PREP_METH_CONTEXT = "ACT_SAM_PREP_METH_CONTEXT";
//		public static final String KEY_ACT_SAM_PREP_METH_NAME = "ACT_SAM_PREP_METH_NAME";
//		public static final String KEY_ACT_SAM_PREP_METH_QUAL_TYPE = "ACT_SAM_PREP_METH_QUAL_TYPE";
//		public static final String KEY_ACT_SAM_PREP_METH_DESC = "ACT_SAM_PREP_METH_DESC";
//		public static final String KEY_SAMPLE_CONTAINER_TYPE = "SAMPLE_CONTAINER_TYPE";
//		public static final String KEY_SAMPLE_CONTAINER_COLOR = "SAMPLE_CONTAINER_COLOR";
//		public static final String KEY_ACT_SAM_CHEMICAL_PRESERVATIVE = "ACT_SAM_CHEMICAL_PRESERVATIVE";
//		public static final String KEY_THERMAL_PRESERVATIVE_NAME = "THERMAL_PRESERVATIVE_NAME";
//		public static final String KEY_ACT_SAM_TRANSPORT_STORAGE_DESC = "ACT_SAM_TRANSPORT_STORAGE_DESC";
//		public static final String KEY_METRIC_TYPE_IDENTIFIER = "METRIC_TYPE_IDENTIFIER";
//		public static final String KEY_METRIC_TYPE_CONTEXT = "METRIC_TYPE_CONTEXT";
//		public static final String KEY_METRIC_TYPE_NAME = "METRIC_TYPE_NAME";
//		public static final String KEY_METRIC_CITATION_TITLE = "METRIC_CITATION_TITLE";
//		public static final String KEY_METRIC_CITATION_CREATOR = "METRIC_CITATION_CREATOR";
//		public static final String KEY_METRIC_CITATION_SUBJECT = "METRIC_CITATION_SUBJECT";
//		public static final String KEY_METRIC_CITATION_PUBLISHER = "METRIC_CITATION_PUBLISHER";
//		public static final String KEY_METRIC_CITATION_DATE = "METRIC_CITATION_DATE";
//		public static final String KEY_METRIC_CITATION_ID = "METRIC_CITATION_ID";
//		public static final String KEY_METRIC_TYPE_SCALE = "METRIC_TYPE_SCALE";
//		public static final String KEY_FORMULA_DESCRIPTION = "FORMULA_DESCRIPTION";
//		public static final String KEY_ACTIVITY_METRIC_VALUE = "ACTIVITY_METRIC_VALUE";
//		public static final String KEY_ACTIVITY_METRIC_UNIT = "ACTIVITY_METRIC_UNIT";
//		public static final String KEY_ACTIVITY_METRIC_SCORE = "ACTIVITY_METRIC_SCORE";
//		public static final String KEY_ACTIVITY_METRIC_COMMENT = "ACTIVITY_METRIC_COMMENT";
//		public static final String KEY_INDEX_IDENTIFIER = "INDEX_IDENTIFIER";
		
		

		
//		public static final String KEY_RES_DATA_LOGGER_LINE = "RES_DATA_LOGGER_LINE";

		
		COLUMN_POSITION.put(KEY_RESULT_DETECTION_CONDITION_TX,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_RESULT_DESRIPTION,
						WQX_DETECTION_CONDITION)));
		
		
//		public static final String KEY_METHOD_SPECIFICATION_NAME = "METHOD_SPECIFICATION_NAME";
		
		
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
		
		
//		public static final String KEY_RES_MEASURE_BIAS = "RES_MEASURE_BIAS";
//		public static final String KEY_RES_MEASURE_CONF_INTERVAL = "RES_MEASURE_CONF_INTERVAL";
//		public static final String KEY_RES_MEASURE_UPPER_CONF_LIMIT = "RES_MEASURE_UPPER_CONF_LIMIT";
//		public static final String KEY_RES_MEASURE_LOWER_CONF_LIMIT = "RES_MEASURE_LOWER_CONF_LIMIT";
		
		
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

		
		
//		public static final String KEY_RES_SAMPLING_POINT_NAME = "RES_SAMPLING_POINT_NAME";
//		public static final String KEY_BIOLOGICAL_INTENT = "BIOLOGICAL_INTENT";
//		public static final String KEY_RES_BIO_INDIVIDUAL_ID = "RES_BIO_INDIVIDUAL_ID";
		
		
		COLUMN_POSITION.put(KEY_SAMPLE_TISSUE_TAXONOMIC_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_BIOLOGICAL_RESULT,
						WQX_TAXON_NAME)));
		
		
//		public static final String KEY_UNIDENTIFIEDSPECIESIDENTIFIER = "UNIDENTIFIEDSPECIESIDENTIFIER";
		
		
		COLUMN_POSITION.put(KEY_SAMPLE_TISSUE_ANATOMY_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_BIOLOGICAL_RESULT,
						WQX_TISSUE_ANATOMY)));
		
		
//		public static final String KEY_RES_GROUP_SUMMARY_CT_WT = "RES_GROUP_SUMMARY_CT_WT";
//		public static final String KEY_RES_GROUP_SUMMARY_CT_WT_UNIT = "RES_GROUP_SUMMARY_CT_WT_UNIT";
//		public static final String KEY_CELL_FORM_NAME = "CELL_FORM_NAME";
//		public static final String KEY_CELL_SHAPE_NAME = "CELL_SHAPE_NAME";
//		public static final String KEY_HABIT_NAME = "HABIT_NAME";
//		public static final String KEY_VOLT_NAME = "VOLT_NAME";
//		public static final String KEY_RTDET_POLLUTION_TOLERANCE = "RTDET_POLLUTION_TOLERANCE";
//		public static final String KEY_RTDET_POLLUTION_TOLERNCE_SCALE = "RTDET_POLLUTION_TOLERNCE_SCALE";
//		public static final String KEY_RTDET_TROPHIC_LEVEL = "RTDET_TROPHIC_LEVEL";
//		public static final String KEY_RTFGRP_FUNCTIONAL_FEEDING_GRP = "RTFGRP_FUNCTIONAL_FEEDING_GRP";
//		public static final String KEY_TAXON_CITATION_TITLE = "TAXON_CITATION_TITLE";
//		public static final String KEY_TAXON_CITATION_CREATOR = "TAXON_CITATION_CREATOR";
//		public static final String KEY_TAXON_CITATION_SUBJECT = "TAXON_CITATION_SUBJECT";
//		public static final String KEY_TAXON_CITATION_PUBLISHER = "TAXON_CITATION_PUBLISHER";
//		public static final String KEY_TAXON_CITATION_DATE = "TAXON_CITATION_DATE";
//		public static final String KEY_TAXON_CITATION_ID = "TAXON_CITATION_ID";
//		public static final String KEY_FCDSC_NAME = "FCDSC_NAME";
//		public static final String KEY_FREQUENCY_CLASS_UNIT = "FREQUENCY_CLASS_UNIT";
//		public static final String KEY_FCDSC_LOWER_BOUND = "FCDSC_LOWER_BOUND";
//		public static final String KEY_FCDSC_UPPER_BOUND = "FCDSC_UPPER_BOUND";
		
		

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
		
		
//		public static final String KEY_ANLMTH_QUAL_TYPE = "ANLMTH_QUAL_TYPE";
		
		
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
		
		
//		public static final String KEY_ANALYSIS_START_TIME = "ANALYSIS_START_TIME";
//		public static final String KEY_ANALYSIS_START_TIMEZONE = "ANALYSIS_START_TIMEZONE";
//		public static final String KEY_ANALYSIS_END_DATE = "ANALYSIS_END_DATE";
//		public static final String KEY_ANALYSIS_END_TIME = "ANALYSIS_END_TIME";
//		public static final String KEY_ANALYSIS_END_TIMEZONE = "ANALYSIS_END_TIMEZONE";
//		public static final String KEY_RLCOM_CD = "RLCOM_CD";
		
		
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
		
		
//		public static final String KEY_RES_LAB_ACCRED_YN = "RES_LAB_ACCRED_YN";
//		public static final String KEY_RES_LAB_ACCRED_AUTHORITY = "RES_LAB_ACCRED_AUTHORITY";
//		public static final String KEY_RES_TAXONOMIST_ACCRED_YN = "RES_TAXONOMIST_ACCRED_YN";
//		public static final String KEY_RES_TAXONOMIST_ACCRED_AUTHORTY = "RES_TAXONOMIST_ACCRED_AUTHORTY";
//		public static final String KEY_PREP_METHOD_ID = "PREP_METHOD_ID";
//		public static final String KEY_PREP_METHOD_CONTEXT = "PREP_METHOD_CONTEXT";
//		public static final String KEY_PREP_METHOD_NAME = "PREP_METHOD_NAME";
//		public static final String KEY_PREP_METHOD_QUAL_TYPE = "PREP_METHOD_QUAL_TYPE";
//		public static final String KEY_PREP_METHOD_DESC = "PREP_METHOD_DESC";
		
		

		COLUMN_POSITION.put(KEY_ANALYSIS_PREP_DATE_TX,
				new LinkedList<String>(Arrays.asList(
						WQX_ACTIVITY,
						WQX_RESULT,
						WQX_LAB_SAMPLE_PREP,
						WQX_PREP_START_DATE)));

		
		
//		public static final String KEY_PREP_START_TIME = "PREP_START_TIME";
//		public static final String KEY_PREP_START_TIMEZONE = "PREP_START_TIMEZONE";
//		public static final String KEY_PREP_END_DATE = "PREP_END_DATE";
//		public static final String KEY_PREP_END_TIME = "PREP_END_TIME";
//		public static final String KEY_PREP_END_TIMEZONE = "PREP_END_TIMEZONE";
//		public static final String KEY_PREP_DILUTION_FACTOR = "PREP_DILUTION_FACTOR";

		
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
