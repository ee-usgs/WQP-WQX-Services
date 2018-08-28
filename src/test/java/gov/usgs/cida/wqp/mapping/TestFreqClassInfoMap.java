package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.ResultColumn.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestFreqClassInfoMap {

	private TestFreqClassInfoMap() {}

	public static final Map<String, Object> BASE_FREQ_CLASS_INFO;
	static {
		BASE_FREQ_CLASS_INFO = new LinkedHashMap<String, Object>();
		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_CODE_1, "freqClassCode1");
		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_CODE_2, "freqClassCode2");
		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_CODE_3, "freqClassCode3");

		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_UNIT_1, "freqClassUnit1");
		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_UNIT_2, "freqClassUnit2");
		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_UNIT_3, "freqClassUnit3");

		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_LOWER_BOUND_1, "freqClassLowerBound1");
		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_LOWER_BOUND_2, "freqClassLowerBound2");
		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_LOWER_BOUND_3, "freqClassLowerBound3");

		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_UPPER_BOUND_1, "freqClassUpperBound1");
		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_UPPER_BOUND_2, "freqClassUpperBound2");
		BASE_FREQ_CLASS_INFO.put(KEY_FREQUENCY_CLASS_UPPER_BOUND_3, "freqClassUpperBound3");
	}
}
