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
				g.writeStringField("organizationFormalName", getValue(resultMap, OrganizationColumn.KEY_ORGANIZATION_NAME));
				g.writeStringField("organizationWQPUrl", siteUrlBase + getValue(resultMap, OrganizationColumn.KEY_ORGANIZATION_SUMMARY_WQP_URL));
				g.writeStringField("lastResultSubmittedDate", getValue(resultMap, OrganizationColumn.KEY_ORGANIZATION_SUMMARY_LAST_RESULT));
				g.writeStringField("totalMonitoringLocationsSampled", getValue(resultMap, OrganizationColumn.KEY_ORGANIZATION_SUMMARY_SITE_COUNT));
				g.writeStringField("totalActivities", getValue(resultMap, OrganizationColumn.KEY_ORGANIZATION_SUMMARY_ACTIVITY_COUNT));	

				g.writeFieldName("yearlySummary");
				g.writeRawValue(getValue(resultMap, OrganizationColumn.KEY_ORGANIZATION_SUMMARY));		
				
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
