package gov.usgs.cida.wqp.webservice.SimpleStation;

import static gov.usgs.cida.wqp.webservice.StationColumnMapper.*;
import gov.cida.cdat.transform.IXmlMapping;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimpleStationXmlMapping implements IXmlMapping, IWqxOutbound {
	
	public static final String VALUE_WQX_OUTBOUND = "WQX-Outbound"; // root node
	public static final String ROOT_NAMESPACE     = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
	
	
	public static final Map<String, String> HARD_BREAK = new LinkedHashMap<>();
	
	static {
		HARD_BREAK.put(KEY_DATA_SOURCE, VALUE_WQX_OUTBOUND);
		HARD_BREAK.put(KEY_ORGANIZATION, VALUE_PROVIDER);
		HARD_BREAK.put(KEY_SITE_ID, VALUE_ORGANIZATION);
	}
	

	public static final Map<String, List<String>> COLUMN_POSITION = new LinkedHashMap<>();
	
	static {
		COLUMN_POSITION.put(KEY_DATA_SOURCE,
				new LinkedList<String>(Arrays.asList(VALUE_PROVIDER,
						VALUE_PROVIDER_NAME)));
		COLUMN_POSITION.put(KEY_ORGANIZATION,
				new LinkedList<String>(Arrays.asList(VALUE_PROVIDER,
						VALUE_ORGANIZATION,
						VALUE_ORGANIZATION_DESCRIPTION,
						VALUE_ORGANIZATION_IDENTIFIER)));
		COLUMN_POSITION.put(KEY_ORGANIZATION_NAME,
				new LinkedList<String>(Arrays.asList(VALUE_PROVIDER,
						VALUE_ORGANIZATION,
						VALUE_ORGANIZATION_DESCRIPTION,
						VALUE_ORGANIZATION_FORMAL_NAME)));
		COLUMN_POSITION.put(KEY_SITE_ID,
				new LinkedList<String>(Arrays.asList(VALUE_PROVIDER,
						VALUE_ORGANIZATION,
						VALUE_MONITORING_LOCATION,
						VALUE_MONITORING_LOCATION_IDENTITY,
						VALUE_MONITORING_LOCATION_IDENTIFIER)));
		COLUMN_POSITION.put(KEY_STATION_NAME,
				new LinkedList<String>(Arrays.asList(VALUE_PROVIDER,
						VALUE_ORGANIZATION,
						VALUE_MONITORING_LOCATION,
						VALUE_MONITORING_LOCATION_IDENTITY,
						VALUE_MONITORING_LOCATION_NAME)));
		COLUMN_POSITION.put(KEY_SITE_TYPE,
				new LinkedList<String>(Arrays.asList(VALUE_PROVIDER,
						VALUE_ORGANIZATION,
						VALUE_MONITORING_LOCATION,
						VALUE_MONITORING_LOCATION_IDENTITY,
						VALUE_RESOLVED_MONITORING_LOCATION)));
		COLUMN_POSITION.put(KEY_LATITUDE,
				new LinkedList<String>(Arrays.asList(VALUE_PROVIDER,
						VALUE_ORGANIZATION,
						VALUE_MONITORING_LOCATION,
						VALUE_MONITORING_LOCATION_GEOSPATIAL,
						VALUE_LATITUDE_MEASURE)));
		COLUMN_POSITION.put(KEY_LONGITUDE,
				new LinkedList<String>(Arrays.asList(VALUE_PROVIDER,
						VALUE_ORGANIZATION,
						VALUE_MONITORING_LOCATION,
						VALUE_MONITORING_LOCATION_GEOSPATIAL,
						VALUE_LONGITUDE_MEASURE)));
	}
	

	public static final Map<String, List<String>> GROUPING = new LinkedHashMap<>();
	
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
		return VALUE_WQX_OUTBOUND;
	}

	@Override
	public String getRootNamespace() {
		return ROOT_NAMESPACE;
	}

	@Override
	public Map<String, List<String>> getStructure() {
		return COLUMN_POSITION;
	}

	@Override
	public Map<String, String> getHardBreak() {
		return HARD_BREAK;
	}

	@Override
	public Map<String, List<String>> getGrouping() {
		return GROUPING;
	}

}
