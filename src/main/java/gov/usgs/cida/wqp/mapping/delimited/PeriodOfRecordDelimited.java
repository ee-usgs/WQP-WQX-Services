package gov.usgs.cida.wqp.mapping.delimited;

import static gov.usgs.cida.wqp.mapping.BaseColumn.DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.SITE_ID;
import gov.usgs.cida.wqp.mapping.ColumnProfile;
import gov.usgs.cida.wqp.mapping.Profile;
import static gov.usgs.cida.wqp.mapping.StationColumn.CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.CHARACTERISTIC_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.COUNTY_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.HUC_8;
import static gov.usgs.cida.wqp.mapping.StationColumn.LAST_RESULT_DATE;
import static gov.usgs.cida.wqp.mapping.StationColumn.LATITUDE;
import static gov.usgs.cida.wqp.mapping.StationColumn.LONGITUDE;
import static gov.usgs.cida.wqp.mapping.StationColumn.MONITORING_LOCATION_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.MONITORING_LOCATION_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.SITE_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.STATE_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.STATION_ID;
import static gov.usgs.cida.wqp.mapping.StationColumn.STATION_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.THE_YEAR;
import static gov.usgs.cida.wqp.mapping.StationColumn.TOTAL_ACTIVITIES;
import static gov.usgs.cida.wqp.mapping.StationColumn.TOTAL_RESULTS;
import static gov.usgs.cida.wqp.mapping.delimited.BaseDelimited.getMapping;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_CHARATERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_CHARATERISTIC_TYPE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_COUNTY_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_HUC_8;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_LAST_SUBMITTED_DATE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION_LATITUDE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION_LONGITUDE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION_TYPE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION_URL;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATON_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOLVED_MONITORING_LOCATION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESULT_COUNT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_STATE_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_YEAR_SUMMARIZED;
import java.util.LinkedHashMap;
import java.util.Map;

public class PeriodOfRecordDelimited extends BaseDelimited {
	//Column Headings for the Keys
	
// from ml_grouping	
	public static final String VALUE_STATION_ID = WQX_MONITORING_LOCATION_IDENTIFIER;
	public static final String VALUE_THE_YEAR = WQX_YEAR_SUMMARIZED;
	public static final String VALUE_CHARACTERISTIC_TYPE = WQX_CHARATERISTIC_TYPE;
	public static final String VALUE_CHARACTERISTIC_NAME = WQX_CHARATERISTIC_NAME; 
	public static final String VALUE_TOTAL_ACTIVITIES = WQX_ACTIVITY_COUNT;
	public static final String VALUE_TOTAL_RESULTS = WQX_RESULT_COUNT;
	public static final String VALUE_LAST_RESULT_DATE = WQX_LAST_SUBMITTED_DATE;
		
// from station_sum	
	public static final String VALUE_MONITORING_LOCATION_TYPE = WQX_MONITORING_LOCATION_TYPE;
	public static final String VALUE_MONITORING_LOCATION_NAME = WQX_MONITORING_LOCATION_NAME;
	public static final String VALUE_HUC_8 = WQX_HUC_8;
	public static final String VALUE_RESOLVED_MONITORING_LOCATION = WQX_RESOLVED_MONITORING_LOCATION;
	public static final String VALUE_MONITORING_LOCATION_TYPE_NAME = WQX_MONITORING_LOCATON_TYPE_NAME;
	public static final String VALUE_MONITORING_LOCATION_URL = WQX_MONITORING_LOCATION_URL;
	public static final String VALUE_STATE_NAME = WQX_STATE_NAME;
	public static final String VALUE_COUNTY_NAME = WQX_COUNTY_NAME;
	public static final String VALUE_MONITORING_LOCATION_LATITUDE = WQX_MONITORING_LOCATION_LATITUDE;
	public static final String VALUE_MONITORING_LOCATION_LONGITUDE = WQX_MONITORING_LOCATION_LONGITUDE;

	public static final Map<ColumnProfile, String> MAPPINGS;
	static {
		MAPPINGS = new LinkedHashMap<>();
		
		MAPPINGS.put(ORGANIZATION, VALUE_ORGANIZATION_IDENTIFIER);
		MAPPINGS.put(ORGANIZATION_NAME, VALUE_ORGANIZATION_FORMAL_NAME);
		MAPPINGS.put(SITE_ID, VALUE_MONITORING_LOCATION_IDENTIFIER);
		MAPPINGS.put(DATA_SOURCE, VALUE_DATA_SOURCE);
		MAPPINGS.put(MONITORING_LOCATION_TYPE, VALUE_MONITORING_LOCATION_TYPE);
		MAPPINGS.put(STATION_NAME, VALUE_MONITORING_LOCATION_NAME);
		
		MAPPINGS.put(SITE_TYPE, VALUE_RESOLVED_MONITORING_LOCATION);
		MAPPINGS.put(HUC_8, VALUE_HUC_8);
		MAPPINGS.put(COUNTY_NAME, VALUE_MONITORING_LOCATION_URL);
		MAPPINGS.put(STATE_NAME, VALUE_STATE_NAME);
		MAPPINGS.put(LATITUDE, VALUE_MONITORING_LOCATION_LATITUDE);
		MAPPINGS.put(LONGITUDE, VALUE_MONITORING_LOCATION_LONGITUDE);
		MAPPINGS.put(TOTAL_ACTIVITIES, VALUE_TOTAL_ACTIVITIES);
		
		MAPPINGS.put(MONITORING_LOCATION_TYPE_NAME, VALUE_MONITORING_LOCATION_TYPE_NAME);
		MAPPINGS.put(STATION_ID, VALUE_STATION_ID);
		MAPPINGS.put(THE_YEAR, VALUE_THE_YEAR);
		MAPPINGS.put(CHARACTERISTIC_TYPE, VALUE_CHARACTERISTIC_TYPE);
		MAPPINGS.put(CHARACTERISTIC_NAME, VALUE_CHARACTERISTIC_NAME);		
		MAPPINGS.put(TOTAL_RESULTS, VALUE_TOTAL_RESULTS);
		MAPPINGS.put(LAST_RESULT_DATE, VALUE_LAST_RESULT_DATE);
	}
	
	private PeriodOfRecordDelimited() {
	}
	
	public static Map<String, String> getMapping(Profile profile) {
		return getMapping(MAPPINGS, profile);
	}
	
}
