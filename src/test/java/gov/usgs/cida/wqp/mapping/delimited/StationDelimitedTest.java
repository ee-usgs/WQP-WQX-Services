package gov.usgs.cida.wqp.mapping.delimited;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.StationColumn;

public class StationDelimitedTest {

	@Test
	public void stationProfileTest() {
		assertStationProfile(StationDelimited.getMapping(Profile.STATION));
	}

	@Test
	public void simpleStationProfileTest() {
		assertSimpleStationProfile(StationDelimited.getMapping(Profile.SIMPLE_STATION));
	}

	public static void assertStationProfile(Map<String, String> mapping) {
		assertEquals(36, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		//kind of large, so just random checks...
		assertEquals(StationColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(StationDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(StationColumn.KEY_DRAIN_AREA_VALUE,keys[7]);
		assertEquals(StationDelimited.VALUE_DRAIN_AREA_VALUE, mapping.get(keys[7]));
		assertEquals(StationColumn.KEY_COUNTY_CODE, keys[26]);
		assertEquals(StationDelimited.VALUE_COUNTY_CODE, mapping.get(keys[26]));
		assertEquals(StationColumn.KEY_DATA_SOURCE, keys[35]);
		assertEquals(StationDelimited.VALUE_DATA_SOURCE, mapping.get(keys[35]));
	}

	public static void assertSimpleStationProfile(Map<String, String> mapping) {
		assertEquals(8, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(StationColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(StationDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(StationColumn.KEY_ORGANIZATION_NAME, keys[1]);
		assertEquals(StationDelimited.VALUE_ORGANIZATION_FORMAL_NAME, mapping.get(keys[1]));
		assertEquals(StationColumn.KEY_SITE_ID,keys[2]);
		assertEquals(StationDelimited.VALUE_MONITORING_LOCATION_IDENTIFIER, mapping.get(keys[2]));
		assertEquals(StationColumn.KEY_STATION_NAME,keys[3]);
		assertEquals(StationDelimited.VALUE_MONITORING_LOCATION_NAME, mapping.get(keys[3]));
		assertEquals(StationColumn.KEY_SITE_TYPE,keys[4]);
		assertEquals(StationDelimited.VALUE_RESOLVED_MONITORING_LOCATION, mapping.get(keys[4]));
		assertEquals(StationColumn.KEY_LATITUDE,keys[5]);
		assertEquals(StationDelimited.VALUE_LATITUDE_MEASURE, mapping.get(keys[5]));
		assertEquals(StationColumn.KEY_LONGITUDE,keys[6]);
		assertEquals(StationDelimited.VALUE_LONGITUDE_MEASURE, mapping.get(keys[6]));
		assertEquals(StationColumn.KEY_DATA_SOURCE, keys[7]);
		assertEquals(StationDelimited.VALUE_DATA_SOURCE, mapping.get(keys[7]));
	}

}
