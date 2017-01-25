package gov.usgs.cida.wqp.mapping.delimited;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.ActivityMetricColumn;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.StationColumn;

public class ActivityMetricDelimitedTest {

	@Test
	public void activityMetricProfileTest() {
		assertActivityMetricProfile(ActivityMetricDelimited.getMapping(Profile.ACTIVITY_METRIC));
	}

	public static void assertActivityMetricProfile(Map<String, String> mapping) {
		assertEquals(21, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(StationColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(StationDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));

		assertEquals(ActivityColumn.KEY_ORGANIZATION_NAME,keys[1]);
		assertEquals(ActivityDelimited.VALUE_ORGANIZATION_FORMAL_NAME, mapping.get(keys[1]));

		assertEquals(ActivityColumn.KEY_SITE_ID, keys[2]);
		assertEquals(ActivityDelimited.VALUE_MONITORING_LOCATION_IDENTIFIER, mapping.get(keys[2]));

		assertEquals(ActivityColumn.KEY_ACTIVITY, keys[3]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY, mapping.get(keys[3]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER, keys[4]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_TYPE_IDENTIFIER, mapping.get(keys[4]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_TYPE_CONTEXT, keys[5]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_TYPE_CONTEXT, mapping.get(keys[5]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_TYPE_NAME, keys[6]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_TYPE_NAME, mapping.get(keys[6]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_CITATION_TITLE, keys[7]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_CITATION_TITLE, mapping.get(keys[7]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_CITATION_CREATOR, keys[8]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_CITATION_CREATOR, mapping.get(keys[8]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_CITATION_SUBJECT, keys[9]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_CITATION_SUBJECT, mapping.get(keys[9]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_CITATION_PUBLISHER, keys[10]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_CITATION_PUBLISHER, mapping.get(keys[10]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_CITATION_DATE, keys[11]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_CITATION_DATE, mapping.get(keys[11]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_CITATION_ID, keys[12]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_CITATION_ID, mapping.get(keys[12]));

		assertEquals(ActivityMetricColumn.KEY_METRIC_TYPE_SCALE, keys[13]);
		assertEquals(ActivityMetricDelimited.VALUE_METRIC_TYPE_SCALE, mapping.get(keys[13]));

		assertEquals(ActivityMetricColumn.KEY_FORMULA_DESCRIPTION, keys[14]);
		assertEquals(ActivityMetricDelimited.VALUE_FORMULA_DESCRIPTION, mapping.get(keys[14]));

		assertEquals(ActivityMetricColumn.KEY_ACTIVITY_METRIC_VALUE, keys[15]);
		assertEquals(ActivityMetricDelimited.VALUE_ACTIVITY_METRIC_VALUE, mapping.get(keys[15]));

		assertEquals(ActivityMetricColumn.KEY_ACTIVITY_METRIC_UNIT, keys[16]);
		assertEquals(ActivityMetricDelimited.VALUE_ACTIVITY_METRIC_UNIT, mapping.get(keys[16]));

		assertEquals(ActivityMetricColumn.KEY_ACTIVITY_METRIC_SCORE, keys[17]);
		assertEquals(ActivityMetricDelimited.VALUE_ACTIVITY_METRIC_SCORE, mapping.get(keys[17]));

		assertEquals(ActivityMetricColumn.KEY_ACTIVITY_METRIC_COMMENT, keys[18]);
		assertEquals(ActivityMetricDelimited.VALUE_ACTIVITY_METRIC_COMMENT, mapping.get(keys[18]));

		assertEquals(ActivityMetricColumn.KEY_INDEX_IDENTIFIER, keys[19]);
		assertEquals(ActivityMetricDelimited.VALUE_INDEX_IDENTIFIER, mapping.get(keys[19]));

		assertEquals(ActivityMetricColumn.KEY_DATA_SOURCE, keys[20]);
		assertEquals(ActivityMetricDelimited.VALUE_DATA_SOURCE, mapping.get(keys[20]));
	}
}
