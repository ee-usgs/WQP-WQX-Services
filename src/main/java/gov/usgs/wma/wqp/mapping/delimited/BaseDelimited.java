package gov.usgs.wma.wqp.mapping.delimited;

import static gov.usgs.wma.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION_IDENTIFIER;
import static gov.usgs.wma.wqp.mapping.xml.BaseWqx.WQX_ORGANIZATION_FORMAL_NAME;
import static gov.usgs.wma.wqp.mapping.xml.BaseWqx.WQX_ORGANIZATION_IDENTIFIER;
import static gov.usgs.wma.wqp.mapping.xml.BaseWqx.WQX_PROVIDER_NAME;
import static gov.usgs.wma.wqp.mapping.xml.BaseWqx.WQX_LAST_UPDATED;

import java.util.LinkedHashMap;
import java.util.Map;

import gov.usgs.wma.wqp.mapping.ColumnProfile;
import gov.usgs.wma.wqp.mapping.Profile;

public abstract class BaseDelimited {

	public static final String VAL_DEL = "/";

	//Column Headings for the Keys
	public static final String VALUE_DATA_SOURCE = WQX_PROVIDER_NAME;
	public static final String VALUE_ORGANIZATION_IDENTIFIER  = WQX_ORGANIZATION_IDENTIFIER;
	public static final String VALUE_ORGANIZATION_FORMAL_NAME = WQX_ORGANIZATION_FORMAL_NAME;
	public static final String VALUE_MONITORING_LOCATION_IDENTIFIER = WQX_MONITORING_LOCATION_IDENTIFIER;
	public static final String VALUE_LAST_UPDATED = WQX_LAST_UPDATED;

	protected BaseDelimited() {
	}

	public static Map<String, String> getMapping(Map<ColumnProfile, String> mappings, Profile profile) {
		Map<String, String> profileMapping = new LinkedHashMap<>();
		for (ColumnProfile p : mappings.keySet()) {
			if (p.getProfiles().contains(profile)) {
				profileMapping.put(p.getKey(), mappings.get(p));
			}
		}
		return profileMapping;
	}

}
