package gov.usgs.cida.wqp.mapping;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static gov.usgs.cida.wqp.mapping.ActivityColumn.*;
import static gov.usgs.cida.wqp.mapping.StationColumn.*;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.*;

public class TestSummaryStationMap {

	public static final Map<String, Object> SUMMARY_STATION;
	static {
		SUMMARY_STATION = new LinkedHashMap<String, Object>();
		//SUMMARY_STATION.put(KEY_DATA_SOURCE_ID, 3);
		SUMMARY_STATION.put(KEY_DATA_SOURCE, STORET);
		//SUMMARY_STATION.put(KEY_STATION_ID, 888);
		SUMMARY_STATION.put(KEY_SITE_ID, "organization-siteId");
		SUMMARY_STATION.put(KEY_ORGANIZATION, "organization");
		SUMMARY_STATION.put(KEY_ORGANIZATION_NAME, "organizationName");
		SUMMARY_STATION.put(KEY_SITE_TYPE, "siteType");
		SUMMARY_STATION.put(KEY_HUC_8, "00000000");
		SUMMARY_STATION.put(KEY_MONITORING_LOCATION_TYPE, "stationTypeName");
		//SUMMARY_STATION.put(KEY_GOVERNMENTAL_UNIT_CODE, "XX:44:555");
		SUMMARY_STATION.put(KEY_LATITUDE, "43.3330000");
		SUMMARY_STATION.put(KEY_LONGITUDE, "-89.8989000");
		SUMMARY_STATION.put(KEY_ACTIVITY_COUNT, BigDecimal.valueOf(4));
		SUMMARY_STATION.put(KEY_RESULT_COUNT, BigDecimal.valueOf(4));
		SUMMARY_STATION.put(KEY_SUMMARY_PAST_12_MONTHS, "{\"characteristicType\": 3}");	
		SUMMARY_STATION.put(KEY_STATE_NAME, null);
		SUMMARY_STATION.put(KEY_COUNTY_NAME, null);
		SUMMARY_STATION.put(KEY_STATION_NAME, "stationName");
	}

	public static final Map<String, Object> SUMMARY_STATION_JSON;
	static {
		SUMMARY_STATION_JSON = new LinkedHashMap<String, Object>();
		SUMMARY_STATION_JSON.put(KEY_DATA_SOURCE, "STORET");
		SUMMARY_STATION_JSON.put(KEY_ORGANIZATION, "organization");
		SUMMARY_STATION_JSON.put(KEY_ORGANIZATION_NAME, "organizationName");
		SUMMARY_STATION_JSON.put(KEY_SITE_ID, "organization-siteId");
		SUMMARY_STATION_JSON.put(KEY_STATION_NAME, "stationName");
		SUMMARY_STATION_JSON.put(KEY_SITE_TYPE, "siteType");
		SUMMARY_STATION_JSON.put(KEY_LATITUDE, "43.3330000");
		SUMMARY_STATION_JSON.put(KEY_LONGITUDE, "-89.8989000");
		SUMMARY_STATION_JSON.put(KEY_MONITORING_LOCATION_TYPE, "stationTypeName");
		SUMMARY_STATION_JSON.put(KEY_HUC_8, "00000000");
		SUMMARY_STATION_JSON.put(KEY_ACTIVITY_COUNT, BigDecimal.valueOf(4));
		SUMMARY_STATION_JSON.put(KEY_RESULT_COUNT, BigDecimal.valueOf(4));
		SUMMARY_STATION_JSON.put(KEY_STATE_NAME, null);
		SUMMARY_STATION_JSON.put(KEY_COUNTY_NAME, null);
		SUMMARY_STATION_JSON.put(KEY_SUMMARY_PAST_12_MONTHS, "{\"characteristicType\": 3}");
	}

	private TestSummaryStationMap() {
	}
}
