package gov.usgs.cida.wqp.webservice.SimpleStation;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimpleStationXmlMapping implements IXmlMapping, IWqxOutbound, ISimpleStation {
	
	public static final Map<String, String> HARD_BREAK = new LinkedHashMap<>();
	
	static {
		HARD_BREAK.put(K_DATA_SOURCE, WQX_OUTBOUND);
		HARD_BREAK.put(K_ORGANIZATION, PROVIDER);
		HARD_BREAK.put(K_SITE_ID, ORGANIZATION);
	}
	

	public static final Map<String, List<String>> COLUMN_POSITION = new LinkedHashMap<>();
	
	static {
		COLUMN_POSITION.put(K_DATA_SOURCE,
				new LinkedList<String>(Arrays.asList(PROVIDER,
						PROVIDER_NAME)));
		COLUMN_POSITION.put(K_ORGANIZATION,
				new LinkedList<String>(Arrays.asList(PROVIDER,
						ORGANIZATION,
						ORGANIZATION_DESCRIPTION,
						ORGANIZATION_IDENTIFIER)));
		COLUMN_POSITION.put(K_ORGANIZATION_NAME,
				new LinkedList<String>(Arrays.asList(PROVIDER,
						ORGANIZATION,
						ORGANIZATION_DESCRIPTION,
						ORGANIZATION_FORMAL_NAME)));
		COLUMN_POSITION.put(K_SITE_ID,
				new LinkedList<String>(Arrays.asList(PROVIDER,
						ORGANIZATION,
						MONITORING_LOCATION,
						MONITORING_LOCATION_IDENTITY,
						MONITORING_LOCATION_IDENTIFIER)));
		COLUMN_POSITION.put(K_STATION_NAME,
				new LinkedList<String>(Arrays.asList(PROVIDER,
						ORGANIZATION,
						MONITORING_LOCATION,
						MONITORING_LOCATION_IDENTITY,
						MONITORING_LOCATION_NAME)));
		COLUMN_POSITION.put(K_SITE_TYPE,
				new LinkedList<String>(Arrays.asList(PROVIDER,
						ORGANIZATION,
						MONITORING_LOCATION,
						MONITORING_LOCATION_IDENTITY,
						RESOLVED_MONITORING_LOCATION_NAME)));
		COLUMN_POSITION.put(K_LATITUDE,
				new LinkedList<String>(Arrays.asList(PROVIDER,
						ORGANIZATION,
						MONITORING_LOCATION,
						MONITORING_LOCATION_GEOSPATIAL,
						LATITUDE_MEASURE)));
		COLUMN_POSITION.put(K_LONGITUDE,
				new LinkedList<String>(Arrays.asList(PROVIDER,
						ORGANIZATION,
						MONITORING_LOCATION,
						MONITORING_LOCATION_GEOSPATIAL,
						LONGITUDE_MEASURE)));
	}

	public static final Map<String, List<String>> GROUPING = new LinkedHashMap<>();
	
	static {
		GROUPING.put(K_DATA_SOURCE,
				new LinkedList<String>(Arrays.asList(K_DATA_SOURCE)));
		GROUPING.put(K_ORGANIZATION,
				new LinkedList<String>(Arrays.asList(K_ORGANIZATION, K_ORGANIZATION_NAME)));
		GROUPING.put(K_SITE_ID,
				new LinkedList<String>(Arrays.asList(K_SITE_ID,
						K_STATION_NAME,
						K_SITE_TYPE,
						K_LATITUDE,
						K_LONGITUDE)));
	}
	
	@Override
	public String getRoot() {
		return ROOT;
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
