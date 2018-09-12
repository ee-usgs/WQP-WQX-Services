package gov.usgs.cida.wqp.transform;

import gov.usgs.cida.wqp.mapping.OrganizationColumn;
import gov.usgs.cida.wqp.service.ILogService;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;


public class OrganizationSumMapToJsonTransformer extends BaseMapToJsonTransformer {    
    
    public OrganizationSumMapToJsonTransformer(OutputStream target, Map<String, String> mapping, ILogService logService, BigDecimal logId, String siteUrlBase) {
	super(target, mapping, logService, logId, siteUrlBase);	
    }
    
    @Override
    protected void writeHeader() {
	    try {
		    g.writeStartObject();
		    g.writeFieldName("organization");
		    g.writeStartArray();
	    } catch (IOException e) {
		    throw new RuntimeException("Error starting json document", e);
	    }
    }
    
    @Override
    protected void writeData(Map<String, Object> resultMap) {
	    try {
		    g.writeStartObject();
		    g.writeStringField("organizationID", getValue(resultMap, OrganizationColumn.KEY_ORGANIZATION));
		    g.writeStringField("organizationFormalName", "need this");
		    g.writeStringField("organizationWQPUrl", "need this");
		    g.writeStringField("lastResultSubmittedDate", "need this");
		    g.writeStringField("totalMonitoringLocationsSampled", "need this");
		    g.writeStringField("totalActivities", "need this");	
		    
//		    g.writeFieldName("yearlySummary");
//		    g.writeStartArray();
//		    g.writeEndArray();		    

		    g.writeEndObject();
	    } catch (IOException e) {
		    throw new RuntimeException("Error writing station json", e);
	    }
    }

    /** output the closing tags and close stuff as appropriate. */
    @Override
    public void end() {
	    try {
		    g.writeEndArray();
		    g.writeEndObject();
		    g.close();
	    } catch (IOException e) {
		    throw new RuntimeException("Error ending json document", e);
	    }
    } 
    

}
