
package gov.usgs.cida.wqp.mapping.delimited;

import gov.usgs.cida.wqp.mapping.ColumnProfile;
import gov.usgs.cida.wqp.mapping.Profile;
import java.util.LinkedHashMap;
import java.util.Map;

public class BiologicalMetricDelimited extends BaseDelimited {
	
	public static final Map<ColumnProfile, String> MAPPINGS;
	static {
// TODO add the csv mapping
		MAPPINGS = new LinkedHashMap<>();
	}
	
	private BiologicalMetricDelimited() {
	}
	
	public static Map<String, String> getMapping(Profile profile) {
		return getMapping(MAPPINGS, profile);
	}
	
}
