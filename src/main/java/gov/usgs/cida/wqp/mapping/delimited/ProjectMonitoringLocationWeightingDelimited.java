package gov.usgs.cida.wqp.mapping.delimited;

import static gov.usgs.cida.wqp.mapping.BaseColumn.*;
import static gov.usgs.cida.wqp.mapping.ProjectMonitoringLocationWeightingColumn.*;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.*;

import java.util.LinkedHashMap;
import java.util.Map;

import gov.usgs.cida.wqp.mapping.ColumnProfile;
import gov.usgs.cida.wqp.mapping.Profile;

public class ProjectMonitoringLocationWeightingDelimited extends BaseDelimited {
	
	//Column Headings for the Keys
	
	public static final Map<ColumnProfile, String> MAPPINGS;
	static {
		MAPPINGS = new LinkedHashMap<ColumnProfile,String>();
		
	}
	
	private ProjectMonitoringLocationWeightingDelimited() {
	}
	
	public static Map<String, String> getMapping(Profile profile) {
		return getMapping(MAPPINGS, profile);
	}
}
