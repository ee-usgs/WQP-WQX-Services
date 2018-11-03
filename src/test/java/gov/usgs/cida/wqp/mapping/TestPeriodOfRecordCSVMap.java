package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.BaseColumn.DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.SITE_ID;
import static gov.usgs.cida.wqp.mapping.StationColumn.CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.CHARACTERISTIC_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.COUNTY_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.HUC_8;
import static gov.usgs.cida.wqp.mapping.StationColumn.LAST_RESULT_DATE;
import static gov.usgs.cida.wqp.mapping.StationColumn.LATITUDE;
import static gov.usgs.cida.wqp.mapping.StationColumn.LONGITUDE;
import static gov.usgs.cida.wqp.mapping.StationColumn.MONITORING_LOCATION_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.MONITORING_LOCATION_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.MONITORING_LOCATION_URL;
import static gov.usgs.cida.wqp.mapping.StationColumn.SITE_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.STATE_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.STATION_ID;
import static gov.usgs.cida.wqp.mapping.StationColumn.STATION_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.THE_YEAR;
import static gov.usgs.cida.wqp.mapping.StationColumn.TOTAL_ACTIVITIES;
import static gov.usgs.cida.wqp.mapping.StationColumn.TOTAL_RESULTS;
import static gov.usgs.cida.wqp.mapping.delimited.BaseDelimited.VALUE_MONITORING_LOCATION_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.delimited.BaseDelimited.VALUE_ORGANIZATION_FORMAL_NAME;
import static gov.usgs.cida.wqp.mapping.delimited.BaseDelimited.VALUE_ORGANIZATION_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.MAPPINGS;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_CHARACTERISTIC_TYPE;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_COUNTY_NAME;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_DATA_SOURCE_PROVIDER;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_HUC_8;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_LAST_RESULT_DATE;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_LATITUDE;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_LONGITUDE;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_NAME;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_TYPE;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_MONITORING_LOCATION_URL;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_RESOLVED_MONITORING_LOCATION;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_STATE_NAME;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_STATION_ID;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_THE_YEAR;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_TOTAL_ACTIVITIES;
import static gov.usgs.cida.wqp.mapping.delimited.PeriodOfRecordDelimited.VALUE_TOTAL_RESULTS;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestPeriodOfRecordCSVMap {
	public static final Map<String, Object> PERIOD_OF_RECORD;
	
	static {
		PERIOD_OF_RECORD = new LinkedHashMap<>();
/**	these are the mappings needed for each data record	
		MAPPINGS.put(DATA_SOURCE, VALUE_DATA_SOURCE_PROVIDER);
		MAPPINGS.put(STATION_ID, VALUE_STATION_ID);
		MAPPINGS.put(THE_YEAR, VALUE_THE_YEAR);
		MAPPINGS.put(CHARACTERISTIC_TYPE, VALUE_CHARACTERISTIC_TYPE);
		MAPPINGS.put(CHARACTERISTIC_NAME, VALUE_CHARACTERISTIC_NAME);
		MAPPINGS.put(TOTAL_ACTIVITIES, VALUE_TOTAL_ACTIVITIES);
		MAPPINGS.put(TOTAL_RESULTS, VALUE_TOTAL_RESULTS);
		MAPPINGS.put(LAST_RESULT_DATE, VALUE_LAST_RESULT_DATE);	
		MAPPINGS.put(ORGANIZATION, VALUE_ORGANIZATION_IDENTIFIER);
		MAPPINGS.put(ORGANIZATION_NAME, VALUE_ORGANIZATION_FORMAL_NAME);		
		MAPPINGS.put(SITE_ID, VALUE_MONITORING_LOCATION_IDENTIFIER);
		MAPPINGS.put(MONITORING_LOCATION_TYPE, VALUE_MONITORING_LOCATION_TYPE);
		MAPPINGS.put(STATION_NAME, VALUE_MONITORING_LOCATION_NAME);
		MAPPINGS.put(MONITORING_LOCATION_TYPE_NAME, VALUE_MONITORING_LOCATION_TYPE_NAME);
		MAPPINGS.put(SITE_TYPE, VALUE_RESOLVED_MONITORING_LOCATION);
		MAPPINGS.put(HUC_8, VALUE_HUC_8);
		MAPPINGS.put(MONITORING_LOCATION_URL, VALUE_MONITORING_LOCATION_URL);
		MAPPINGS.put(COUNTY_NAME, VALUE_COUNTY_NAME);
		MAPPINGS.put(STATE_NAME, VALUE_STATE_NAME);
		MAPPINGS.put(LATITUDE, VALUE_MONITORING_LOCATION_LATITUDE);
		MAPPINGS.put(LONGITUDE, VALUE_MONITORING_LOCATION_LONGITUDE);
**/		
	}
	
	
	
	private TestPeriodOfRecordCSVMap() {
	}
}
