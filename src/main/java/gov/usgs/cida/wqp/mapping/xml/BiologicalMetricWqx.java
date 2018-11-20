package gov.usgs.cida.wqp.mapping.xml;

import gov.usgs.cida.wqp.mapping.ColumnProfile;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class BiologicalMetricWqx extends BaseWqx implements IXmlMapping {
	
	public static final Map<String, String> HARD_BREAK = new LinkedHashMap<>();

	public static final Map<String, List<String>> COLUMN_POSITION = new LinkedHashMap<>();
	
	public static final Map<String, List<ColumnProfile>> GROUPING = new LinkedHashMap<>();



	@Override
	public Map<String, List<String>> getStructure() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Map<String, String> getHardBreak() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Map<String, List<ColumnProfile>> getGrouping() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getEntryNodeName() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
