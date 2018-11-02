package gov.usgs.cida.wqp.mapping.delimited;

import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.xml.BaseWqx;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PeriodOfRecordDelimitedTest {

	@Test
	public void periodOfRecordProfileTest() {
		assertPeriodOfRecordProfile(PeriodOfRecordDelimited.getMapping(Profile.PERIOD_OF_RECORD));
	}
	
	public static void assertPeriodOfRecordProfile(Map<String, String> mapping) {
		Object[] keys = mapping.keySet().toArray();
		assertEquals(20, mapping.size());
		assertEquals(BaseWqx.WQX_PROVIDER, mapping.get(keys[0]));
		assertEquals(PeriodOfRecordDelimited.VALUE_DATA_SOURCE_PROVIDER, mapping.get(keys[0]));
		assertEquals(BaseWqx.WQX_MONITORING_LOCATION_IDENTIFIER, mapping.get(keys[1]));
		assertEquals(PeriodOfRecordDelimited.VALUE_STATION_ID, mapping.get(keys[1]));
		assertEquals(BaseWqx.WQX_YEAR_SUMMARIZED, mapping.get(keys[2]));
		assertEquals(PeriodOfRecordDelimited.VALUE_THE_YEAR, mapping.get(keys[2]));		
		assertEquals(BaseWqx.WQX_CHARATERISTIC_TYPE, mapping.get(keys[3]));				
		assertEquals(PeriodOfRecordDelimited.VALUE_CHARACTERISTIC_TYPE, mapping.get(keys[3]));
		assertEquals(BaseWqx.WQX_CHARATERISTIC_NAME, mapping.get(keys[4]));
		assertEquals(PeriodOfRecordDelimited.VALUE_CHARACTERISTIC_NAME, mapping.get(keys[4]));
		assertEquals(BaseWqx.WQX_ACTIVITY_COUNT, mapping.get(keys[5]));
		assertEquals(PeriodOfRecordDelimited.VALUE_TOTAL_ACTIVITIES, mapping.get(keys[5]));
		assertEquals(BaseWqx.WQX_RESULT_COUNT, mapping.get(keys[6]));
		assertEquals(PeriodOfRecordDelimited.VALUE_TOTAL_RESULTS, mapping.get(keys[6]));
		assertEquals(BaseWqx.WQX_LAST_SUBMITTED_DATE, mapping.get(keys[7]));
		assertEquals(PeriodOfRecordDelimited.VALUE_LAST_RESULT_DATE, mapping.get(keys[7]));
		assertEquals(BaseWqx.WQX_ORGANIZATION_IDENTIFIER, mapping.get(keys[8]));
		assertEquals(PeriodOfRecordDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[8]));
		assertEquals(BaseWqx.WQX_ORGANIZATION_FORMAL_NAME, mapping.get(keys[9]));
		assertEquals(PeriodOfRecordDelimited.VALUE_ORGANIZATION_FORMAL_NAME, mapping.get(keys[9]));		
		assertEquals(BaseWqx.WQX_MONITORING_LOCATION_IDENTIFIER, mapping.get(keys[10]));
		assertEquals(PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_IDENTIFIER, mapping.get(keys[10]));		
		assertEquals(BaseWqx.WQX_MONITORING_LOCATION_NAME, mapping.get(keys[11]));
		assertEquals(PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_NAME, mapping.get(keys[11]));		
		assertEquals(BaseWqx.WQX_MONITORING_LOCATION_TYPE, mapping.get(keys[12]));
		assertEquals(PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_TYPE_NAME, mapping.get(keys[12]));
		assertEquals(BaseWqx.WQX_RESOLVED_MONITORING_LOCATION, mapping.get(keys[13]));
		assertEquals(PeriodOfRecordDelimited.VALUE_RESOLVED_MONITORING_LOCATION, mapping.get(keys[13]));
		assertEquals(BaseWqx.WQX_HUC_8, mapping.get(keys[14]));
		assertEquals(PeriodOfRecordDelimited.VALUE_HUC_8, mapping.get(keys[14]));
		assertEquals(BaseWqx.WQX_MONITORING_LOCATION_URL, mapping.get(keys[15]));
		assertEquals(PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_URL, mapping.get(keys[15]));
		assertEquals(BaseWqx.WQX_COUNTY_NAME, mapping.get(keys[16]));
		assertEquals(PeriodOfRecordDelimited.VALUE_COUNTY_NAME, mapping.get(keys[16]));
		assertEquals(BaseWqx.WQX_STATE_NAME, mapping.get(keys[17]));
		assertEquals(PeriodOfRecordDelimited.VALUE_STATE_NAME, mapping.get(keys[17]));
		assertEquals(BaseWqx.WQX_MONITORING_LOCATION_LATITUDE, mapping.get(keys[18]));
		assertEquals(PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_LATITUDE, mapping.get(keys[18]));
		assertEquals(BaseWqx.WQX_MONITORING_LOCATION_LONGITUDE, mapping.get(keys[19]));
		assertEquals(PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_LONGITUDE, mapping.get(keys[19]));		
	}		
}
