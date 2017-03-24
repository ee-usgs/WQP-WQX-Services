package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_ANALYSIS_PREP_DATE_TX;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT_DESC;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_DETECTION_LIMIT_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_FCDSC_LOWER_BOUND;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_FCDSC_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_FCDSC_UPPER_BOUND;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_FREQUENCY_CLASS_UNIT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_DILUTION_FACTOR;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_END_DATE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_END_TIME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_END_TIMEZONE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_METHOD_CONTEXT;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_METHOD_DESC;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_METHOD_ID;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_METHOD_NAME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_METHOD_QUAL_TYPE;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_START_TIME;
import static gov.usgs.cida.wqp.mapping.ResultColumn.KEY_PREP_START_TIMEZONE;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestBioResultMap {

	public static final Map<String, Object> BIO_RESULT;
	static {
		BIO_RESULT = new LinkedHashMap<String, Object>();
		BIO_RESULT.putAll(TestActivityMap.BASE_ACTIVITY);
		BIO_RESULT.putAll(TestNarrowResultMap.BASE_NARROW);

		BIO_RESULT.put(KEY_FCDSC_NAME, "fcdscName");
		BIO_RESULT.put(KEY_FREQUENCY_CLASS_UNIT, "frequencyClassUnit");
		BIO_RESULT.put(KEY_FCDSC_LOWER_BOUND, BigDecimal.valueOf(88));
		BIO_RESULT.put(KEY_FCDSC_UPPER_BOUND, BigDecimal.valueOf(99));

		BIO_RESULT.put(KEY_DETECTION_LIMIT, "detectionLimit");
		BIO_RESULT.put(KEY_DETECTION_LIMIT_UNIT, "detectionLimitUnit");
		BIO_RESULT.put(KEY_DETECTION_LIMIT_DESC, "detectionLimitDesc");

		BIO_RESULT.put(KEY_PREP_METHOD_ID, "prepMethodId");
		BIO_RESULT.put(KEY_PREP_METHOD_CONTEXT, "prepMethodContext");
		BIO_RESULT.put(KEY_PREP_METHOD_NAME, "prepMethodName");
		BIO_RESULT.put(KEY_PREP_METHOD_QUAL_TYPE, "prepMethodQualType");
		BIO_RESULT.put(KEY_PREP_METHOD_DESC, "prepMethodDesc");
		BIO_RESULT.put(KEY_ANALYSIS_PREP_DATE_TX, "analysisPrepDateTx");
		BIO_RESULT.put(KEY_PREP_START_TIME, "prepStartTime");
		BIO_RESULT.put(KEY_PREP_START_TIMEZONE, "prepStartTimezone");
		BIO_RESULT.put(KEY_PREP_END_DATE, "prepEndDate");
		BIO_RESULT.put(KEY_PREP_END_TIME, "prepEndTime");
		BIO_RESULT.put(KEY_PREP_END_TIMEZONE, "prepEndTimezone");
		BIO_RESULT.put(KEY_PREP_DILUTION_FACTOR, "prepDilutionFactor");
	}

	private TestBioResultMap() {
	}
}
