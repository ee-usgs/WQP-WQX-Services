package gov.usgs.cida.wqp.mapping.delimited;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.ActivityMetricColumn;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.ResultColumn;

public class ResDetectQntLmtDelimitedTest {

	@Test
	public void resDetectQntLmtDelimitedTest() {
		assertResDetectQntLmtProfile(ResultDelimited.getMapping(Profile.RES_DETECT_QNT_LMT));
	}

	public static void assertResDetectQntLmtProfile(Map<String, String> mapping) {
		assertEquals(10, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(ResultColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(StationDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));

		assertEquals(ResultColumn.KEY_ORGANIZATION_NAME,keys[1]);
		assertEquals(ActivityDelimited.VALUE_ORGANIZATION_FORMAL_NAME, mapping.get(keys[1]));

		assertEquals(ActivityColumn.KEY_ACTIVITY, keys[2]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY, mapping.get(keys[2]));

		assertEquals(ResultColumn.KEY_SITE_ID, keys[3]);
		assertEquals(ActivityDelimited.VALUE_MONITORING_LOCATION_IDENTIFIER, mapping.get(keys[3]));

		assertEquals(ResultColumn.KEY_CHARACTERISTIC_NAME, keys[4]);
		assertEquals(ResultDelimited.VALUE_CHARACTERISTIC_NAME, mapping.get(keys[4]));

		assertEquals(ResultColumn.KEY_RESULT_ID, keys[5]);
		assertEquals(ResultDelimited.VALUE_RESULT_IDENTIFIER, mapping.get(keys[5]));

		assertEquals(ResultColumn.KEY_DETECTION_LIMIT_DESC, keys[6]);
		assertEquals(ResultDelimited.VALUE_DETECTION_LIMIT_DESC, mapping.get(keys[6]));

		assertEquals(ResultColumn.KEY_DETECTION_LIMIT, keys[7]);
		assertEquals(ResultDelimited.VALUE_DETECTION_LIMIT, mapping.get(keys[7]));

		assertEquals(ResultColumn.KEY_DETECTION_LIMIT_UNIT, keys[8]);
		assertEquals(ResultDelimited.VALUE_DETECTION_LIMIT_UNIT, mapping.get(keys[8]));

		assertEquals(ActivityMetricColumn.KEY_DATA_SOURCE, keys[9]);
		assertEquals(ActivityMetricDelimited.VALUE_DATA_SOURCE, mapping.get(keys[9]));
	}
}
