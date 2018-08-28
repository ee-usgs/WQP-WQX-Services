package gov.usgs.cida.wqp.mapping;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static gov.usgs.cida.wqp.mapping.ActivityColumn.*;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_STATION_ID;
import static gov.usgs.cida.wqp.mapping.StationColumn.*;
import static gov.usgs.cida.wqp.mapping.xml.StationKml.*;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.*;
import static gov.usgs.cida.wqp.mapping.TestOrganizationMap.BASE_ORGANIZATION;

public class TestStationMap {

	public static final Map<String, Object> BASE_STATION;
	static {
		BASE_STATION = new LinkedHashMap<String, Object>();
		BASE_STATION.putAll(BASE_ORGANIZATION);
		BASE_STATION.put(KEY_STATION_ID, BigDecimal.valueOf(888));
		BASE_STATION.put(KEY_SITE_ID, "organization-siteId");
	}

	public static final Map<String, Object> STATION;
	static {
		STATION = new LinkedHashMap<String, Object>();
		STATION.putAll(BASE_STATION);

		STATION.put(KEY_STATION_NAME, "stationName");
		STATION.put(KEY_MONITORING_LOCATION_TYPE, "stationTypeName");
		STATION.put(KEY_MONITORING_LOCATION_DESCRIPTION, "descriptionText");
		STATION.put(KEY_HUC_8, "00000000");
		STATION.put(KEY_HUC_12, "000000000000");

		STATION.put(KEY_CONTRIB_DRAIN_AREA_VALUE, BigDecimal.valueOf(567893));
		STATION.put(KEY_CONTRIB_DRAIN_AREA_UNIT, "contribDrainAreaUnit");
		STATION.put(KEY_DRAIN_AREA_VALUE, BigDecimal.valueOf(4569872));
		STATION.put(KEY_DRAIN_AREA_UNIT, "drainAreaUnit");
		STATION.put(KEY_LATITUDE,BigDecimal.valueOf( 43.333));
		STATION.put(KEY_LONGITUDE, BigDecimal.valueOf(-89.8989));
		STATION.put(KEY_SOURCE_MAP_SCALE, "mapScale");
		STATION.put(KEY_HORIZONTAL_ACCY_VALUE, "geopositionAccyValue");
		STATION.put(KEY_HORIZONTAL_ACCY_UNIT, "geopositionAccyUnit");
		STATION.put(KEY_HORIZONTAL_COLLECTION_METHOD, "geopositioningMethod");
		STATION.put(KEY_HORIZONTAL_DATUM, "hdatumIdCode");
		STATION.put(KEY_VERTICAL_MEASURE_VALUE, "elevationValue");
		STATION.put(KEY_VERTICAL_MEASURE_UNIT, "elevationUnit");
		STATION.put(KEY_VERTICAL_ACCY_VALUE, "verticalAccuracyValue");
		STATION.put(KEY_VERTICAL_ACCY_UNIT, "verticalAccuracyUnit");
		STATION.put(KEY_VERTICAL_COLLECTION_METHOD, "elevationMethod");
		STATION.put(KEY_VERTICAL_DATUM, "vdatumIdCode");
		STATION.put(KEY_COUNTRY_CODE, "XX");
		STATION.put(KEY_STATE_CODE, "44");
		STATION.put(KEY_COUNTY_CODE, "555");
		STATION.put(KEY_NAT_AQFR_NAME, "natAqfrName");
		STATION.put(KEY_AQFR_NAME, "aqfrName");
		STATION.put(KEY_AQFR_TYPE_NAME, "aqfrTypeName");
		STATION.put(KEY_CONSTRUCTION_DATE, "constructionDate");
		STATION.put(KEY_WELL_DEPTH_VALUE, BigDecimal.valueOf(2333));
		STATION.put(KEY_WELL_DEPTH_UNIT, "wellDepthUnit");
		STATION.put(KEY_HOLE_DEPTH_VALUE, BigDecimal.valueOf(2345));
		STATION.put(KEY_HOLE_DEPTH_UNIT, "holeDepthUnit");

		STATION.put(KEY_DATA_SOURCE, STORET);
		STATION.put(KEY_RESULT_COUNT, BigDecimal.valueOf(4));
	}

	public static final Map<String, Object> STATION_JSON;
	static {
		STATION_JSON = new LinkedHashMap<String, Object>();
		STATION_JSON.put(KEY_DATA_SOURCE, "STORET");
		STATION_JSON.put(KEY_ORGANIZATION, "organization");
		STATION_JSON.put(KEY_ORGANIZATION_NAME, "organizationName");
		STATION_JSON.put(KEY_SITE_ID, "organization-siteId");
		STATION_JSON.put(KEY_STATION_NAME, "stationName");
		STATION_JSON.put(KEY_SITE_TYPE, "siteType");
		STATION_JSON.put(KEY_LATITUDE, "43.3330000");
		STATION_JSON.put(KEY_LONGITUDE, "-89.8989000");
		STATION_JSON.put(KEY_MONITORING_LOCATION_TYPE, "stationTypeName");
		STATION_JSON.put(KEY_HUC_8, "00000000");
		STATION_JSON.put(KEY_ACTIVITY_COUNT, BigDecimal.valueOf(4));
		STATION_JSON.put(KEY_RESULT_COUNT, BigDecimal.valueOf(4));
		STATION_JSON.put(KEY_STATE_NAME, null);
		STATION_JSON.put(KEY_COUNTY_NAME, null);
	}

	public static final Map<String, Object> STATION_KML;
	static {
		STATION_KML = new LinkedHashMap<String, Object>();
		STATION_KML.put(KEY_STATION_NAME, "stationName");
		STATION_KML.put(KEY_STYLE_URL, "STORETSite");
		STATION_KML.put(KEY_ORGANIZATION_NAME, "organizationName");
		STATION_KML.put(KEY_ORGANIZATION, "organization");
		STATION_KML.put(KEY_SITE_ID, "organization-siteId");
		STATION_KML.put(KEY_STATION_NAME2, "stationName");
		STATION_KML.put(KEY_MONITORING_LOCATION_TYPE, "stationTypeName");
		STATION_KML.put(KEY_MONITORING_LOCATION_DESCRIPTION, "descriptionText");
		STATION_KML.put(KEY_HUC_8, "00000000");
		STATION_KML.put(KEY_CONTRIB_DRAIN_AREA_VALUE, BigDecimal.valueOf(567893));
		STATION_KML.put(KEY_CONTRIB_DRAIN_AREA_UNIT, "contribDrainAreaUnit");
		STATION_KML.put(KEY_AQFR_TYPE_NAME, "aqfrTypeName");
		STATION_KML.put(KEY_AQFR_NAME, "aqfrName");
		STATION_KML.put(KEY_WELL_DEPTH_VALUE, BigDecimal.valueOf(2333));
		STATION_KML.put(KEY_WELL_DEPTH_UNIT, "wellDepthUnit");
		STATION_KML.put(KEY_COORDINATES, "-89.8989000,43.3330000");
	}

	private TestStationMap() {
	}
}
