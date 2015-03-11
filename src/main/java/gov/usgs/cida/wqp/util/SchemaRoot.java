package gov.usgs.cida.wqp.util;

import org.apache.log4j.Logger;

public enum SchemaRoot {

    WQX("WQX", "WQX", "<WQX xmlns='http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/ http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/index.xsd'></WQX>"),
    KML("kml", "Document", "<kml><Document></Document></kml>"),
    CODES("Codes", "Codes", "<Codes></Codes>"),
    WQX_OUTBOUND("WQX-Outbound", "WQX", "<WQX-Outbound></WQX-Outbound>");

	private final Logger log = Logger.getLogger(getClass());
	
    private String rootText;
    private String emptyDocument;
    private String aggregationNode;

    
    private SchemaRoot(final String inRootText, final String inAggregationNode, final String inEmptyDocument) {
        log.trace(getClass());
        
        rootText = inRootText;
        aggregationNode = inAggregationNode;
        emptyDocument = inEmptyDocument;
    }

    public String getRootText() {
        return rootText;
    }

    public String getAggregationNode() {
        return aggregationNode;
    }

    public String getEmptyDocument() {
        return emptyDocument;
    }
}
