package gov.usgs.cida.wqp.mapping.delimited;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.ResultColumn;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.mapping.TestBaseDelimited;

public class BaseDelimitedTest {

	@Test
	public void stationProfileTest() {
		Map<String, String> mapping = TestBaseDelimited.getMapping(TestBaseDelimited.MAPPINGS, Profile.STATION);
		assertEquals(3, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(StationColumn.KEY_STATION_NAME,keys[1]);
		assertEquals(StationDelimited.VALUE_MONITORING_LOCATION_NAME, mapping.get(keys[1]));
		assertEquals(StationColumn.KEY_HUC_8, keys[2]);
		assertEquals(StationDelimited.VALUE_HUC_8, mapping.get(keys[2]));
	}

	@Test
	public void simplestationProfileTest() {
		Map<String, String> mapping = TestBaseDelimited.getMapping(TestBaseDelimited.MAPPINGS, Profile.SIMPLE_STATION);
		assertEquals(2, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(StationColumn.KEY_STATION_NAME,keys[1]);
		assertEquals(StationDelimited.VALUE_MONITORING_LOCATION_NAME, mapping.get(keys[1]));
	}

	@Test
	public void pcresultProfileTest() {
		Map<String, String> mapping = TestBaseDelimited.getMapping(TestBaseDelimited.MAPPINGS, Profile.PC_RESULT);
		assertEquals(3, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(ActivityColumn.KEY_ACTIVITY, keys[1]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY, mapping.get(keys[1]));
		assertEquals(ResultColumn.KEY_RESULT_VALUE_TYPE, keys[2]);
		assertEquals(ResultDelimited.VALUE_RESULT_VALUE_TYPE, mapping.get(keys[2]));
	}

	@Test
	public void biologicalProfileTest() {
		Map<String, String> mapping = TestBaseDelimited.getMapping(TestBaseDelimited.MAPPINGS, Profile.BIOLOGICAL);
		assertEquals(5, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(ActivityColumn.KEY_ACTIVITY, keys[1]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY, mapping.get(keys[1]));
		assertEquals(ActivityColumn.KEY_ACTIVITY_LATITUDE, keys[2]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY_LATITUDE, mapping.get(keys[2]));
		assertEquals(ResultColumn.KEY_RESULT_VALUE_TYPE, keys[3]);
		assertEquals(ResultDelimited.VALUE_RESULT_VALUE_TYPE, mapping.get(keys[3]));
		assertEquals(ResultColumn.KEY_HABIT_NAME, keys[4]);
		assertEquals(ResultDelimited.VALUE_HABIT_NAME, mapping.get(keys[4]));
	}
}
