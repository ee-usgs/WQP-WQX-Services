package gov.usgs.cida.wqp.mapping.delimited;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.StationColumn;

public class ActivityDelimitedTest {

	@Test
	public void activityProfileTest() {
		assertActivityProfile(ActivityDelimited.getMapping(Profile.ACTIVITY));
	}

	public static void assertActivityProfile(Map<String, String> mapping) {
		assertEquals(72, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		//kind of large, so just random checks...
		assertEquals(StationColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(StationDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(ActivityColumn.KEY_ACTIVITY_START_TIME,keys[7]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY_START_TIME, mapping.get(keys[7]));
		assertEquals(ActivityColumn.KEY_ACT_STOP_TIME_ZONE, keys[11]);
		assertEquals(ActivityDelimited.VALUE_ACT_STOP_TIME_ZONE, mapping.get(keys[11]));
		assertEquals(ActivityColumn.KEY_ACTIVITY_COMMENT, keys[23]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY_COMMENT, mapping.get(keys[23]));
		assertEquals(ActivityColumn.KEY_ACT_SAM_COMPNT_NAME, keys[37]);
		assertEquals(ActivityDelimited.VALUE_ACT_SAM_COMPNT_NAME, mapping.get(keys[37]));
		assertEquals(ActivityColumn.KEY_ACT_REACH_WIDTH, keys[41]);
		assertEquals(ActivityDelimited.VALUE_ACT_REACH_WIDTH, mapping.get(keys[41]));
		assertEquals(ActivityColumn.KEY_TOXICITY_TEST_TYPE_NAME, keys[53]);
		assertEquals(ActivityDelimited.VALUE_TOXICITY_TEST_TYPE_NAME, mapping.get(keys[53]));
		assertEquals(ActivityColumn.KEY_SAMPLE_CONTAINER_TYPE, keys[66]);
		assertEquals(ActivityDelimited.VALUE_SAMPLE_CONTAINER_TYPE, mapping.get(keys[66]));
		assertEquals(ActivityColumn.KEY_DATA_SOURCE, keys[71]);
		assertEquals(ActivityDelimited.VALUE_DATA_SOURCE, mapping.get(keys[71]));
	}

}
