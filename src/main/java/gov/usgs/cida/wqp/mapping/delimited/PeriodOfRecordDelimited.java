package gov.usgs.cida.wqp.mapping.delimited;

import gov.usgs.cida.wqp.mapping.ColumnProfile;
import gov.usgs.cida.wqp.mapping.Profile;
import static gov.usgs.cida.wqp.mapping.delimited.BaseDelimited.getMapping;
import java.util.LinkedHashMap;
import java.util.Map;

public class PeriodOfRecordDelimited extends BaseDelimited{
	//Column Headings for the Keys
	public static final String VALUE_01 = "";
	public static final String VALUE_02 = "";
	
	public static final Map<ColumnProfile, String> MAPPINGS;
	static {
		MAPPINGS = new LinkedHashMap<>();
// example		MAPPINGS.put(key, VAL_DEL);
		
	}
	
	private PeriodOfRecordDelimited() {
	}
	
	public static Map<String, String> getMapping(Profile profile) {
		return getMapping(MAPPINGS, profile);
	}
	
}
