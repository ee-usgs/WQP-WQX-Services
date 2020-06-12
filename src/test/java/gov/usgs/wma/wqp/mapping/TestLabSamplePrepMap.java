package gov.usgs.wma.wqp.mapping;

import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_ANALYSIS_PREP_DATE_TX;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_DILUTION_FACTOR;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_END_DATE;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_END_TIME;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_END_TIMEZONE;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_METHOD_CONTEXT;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_METHOD_DESC;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_METHOD_ID;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_METHOD_NAME;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_METHOD_QUAL_TYPE;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_START_TIME;
import static gov.usgs.wma.wqp.mapping.ResultColumn.KEY_PREP_START_TIMEZONE;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestLabSamplePrepMap {

	private TestLabSamplePrepMap() {}

	public static final Map<String, Object> BASE_LAB_SAMPLE_PREP;
	static {
		BASE_LAB_SAMPLE_PREP = new LinkedHashMap<String, Object>();
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_METHOD_ID, "prepMethodId");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_METHOD_CONTEXT, "prepMethodContext");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_METHOD_NAME, "prepMethodName");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_METHOD_QUAL_TYPE, "prepMethodQualType");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_METHOD_DESC, "prepMethodDesc");
		BASE_LAB_SAMPLE_PREP.put(KEY_ANALYSIS_PREP_DATE_TX, "analysisPrepDateTx");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_START_TIME, "prepStartTime");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_START_TIMEZONE, "prepStartTimezone");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_END_DATE, "prepEndDate");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_END_TIME, "prepEndTime");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_END_TIMEZONE, "prepEndTimezone");
		BASE_LAB_SAMPLE_PREP.put(KEY_PREP_DILUTION_FACTOR, "prepDilutionFactor");
	}
}
