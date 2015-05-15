package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_SITE_ID;
import static gov.usgs.cida.wqp.webservice.StationColumnMapping.KEY_DATA_SOURCE;
import static gov.usgs.cida.wqp.webservice.StationColumnMapping.KEY_LATITUDE;
import static gov.usgs.cida.wqp.webservice.StationColumnMapping.KEY_LONGITUDE;
import static gov.usgs.cida.wqp.webservice.StationColumnMapping.KEY_SITE_TYPE;
import static gov.usgs.cida.wqp.webservice.StationColumnMapping.KEY_STATION_NAME;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimpleStationWqxOutbound extends BaseWqx {
	
	public static final String ROOT_NODE = "WQX-Outbound";

	static {
		HARD_BREAK.put(KEY_DATA_SOURCE, ROOT_NODE);
		HARD_BREAK.put(KEY_ORGANIZATION, WQX_PROVIDER);
		HARD_BREAK.put(KEY_SITE_ID, WQX_ORGANIZATION);
	}
	

	static {
		COLUMN_POSITION.put(KEY_DATA_SOURCE,
				new LinkedList<String>(Arrays.asList(WQX_PROVIDER,
						WQX_PROVIDER_NAME)));
		COLUMN_POSITION.put(KEY_ORGANIZATION,
				new LinkedList<String>(Arrays.asList(WQX_PROVIDER,
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_ORGANIZATION_IDENTIFIER)));
		COLUMN_POSITION.put(KEY_ORGANIZATION_NAME,
				new LinkedList<String>(Arrays.asList(WQX_PROVIDER,
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_ORGANIZATION_FORMAL_NAME)));
		COLUMN_POSITION.put(KEY_SITE_ID,
				new LinkedList<String>(Arrays.asList(WQX_PROVIDER,
						WQX_ORGANIZATION,
						WQX_MONITORING_LOCATION,
						WQX_MONITORING_LOCATION_IDENTITY,
						WQX_MONITORING_LOCATION_IDENTIFIER)));
		COLUMN_POSITION.put(KEY_STATION_NAME,
				new LinkedList<String>(Arrays.asList(WQX_PROVIDER,
						WQX_ORGANIZATION,
						WQX_MONITORING_LOCATION,
						WQX_MONITORING_LOCATION_IDENTITY,
						WQX_MONITORING_LOCATION_NAME)));
		COLUMN_POSITION.put(KEY_SITE_TYPE,
				new LinkedList<String>(Arrays.asList(WQX_PROVIDER,
						WQX_ORGANIZATION,
						WQX_MONITORING_LOCATION,
						WQX_MONITORING_LOCATION_IDENTITY,
						WQX_RESOLVED_MONITORING_LOCATION)));
		COLUMN_POSITION.put(KEY_LATITUDE,
				new LinkedList<String>(Arrays.asList(WQX_PROVIDER,
						WQX_ORGANIZATION,
						WQX_MONITORING_LOCATION,
						WQX_MONITORING_LOCATION_GEOSPATIAL,
						WQX_LATITUDE_MEASURE)));
		COLUMN_POSITION.put(KEY_LONGITUDE,
				new LinkedList<String>(Arrays.asList(WQX_PROVIDER,
						WQX_ORGANIZATION,
						WQX_MONITORING_LOCATION,
						WQX_MONITORING_LOCATION_GEOSPATIAL,
						WQX_LONGITUDE_MEASURE)));
	}
	

	static {
		GROUPING.put(KEY_DATA_SOURCE,
				new LinkedList<String>(Arrays.asList(KEY_DATA_SOURCE)));
		GROUPING.put(KEY_ORGANIZATION,
				new LinkedList<String>(Arrays.asList(KEY_ORGANIZATION, KEY_ORGANIZATION_NAME)));
		GROUPING.put(KEY_SITE_ID,
				new LinkedList<String>(Arrays.asList(KEY_SITE_ID,
						KEY_STATION_NAME,
						KEY_SITE_TYPE,
						KEY_LATITUDE,
						KEY_LONGITUDE)));
	}
	
	
	@Override
	public String getRoot() {
		return ROOT_NODE;
	}

	@Override
	public String getHeader() {
		return "<" + ROOT_NODE + ">";
	}

}
