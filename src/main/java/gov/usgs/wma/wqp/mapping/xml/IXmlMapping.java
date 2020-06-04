package gov.usgs.wma.wqp.mapping.xml;

import java.util.List;
import java.util.Map;

import gov.usgs.wma.wqp.mapping.ColumnProfile;

public interface IXmlMapping {

	String getRoot();

	String getHeader();

	Map<String, List<String>> getStructure();

	Map<String, String> getHardBreak();

	Map<String, List<ColumnProfile>> getGrouping();

	String getEntryNodeName();

}
