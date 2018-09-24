package gov.usgs.cida.wqp.transform;
 
import gov.usgs.cida.wqp.service.ILogService;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;

public class MonitoringLocPeriodOfRecordMapToJsonTransformer extends BaseMapToJsonTransformer {
    
    public MonitoringLocPeriodOfRecordMapToJsonTransformer(OutputStream target, Map<String, String> mapping, ILogService logService, BigDecimal logId, String siteUrlBase) {
	super(target, mapping, logService, logId, siteUrlBase);
    }
    
    @Override
    protected void writeHeader() {
	    try {
		    g.writeStartObject();
		    g.writeStringField("type", "FeatureCollection");
		    g.writeFieldName("features");
		    g.writeStartArray();
	    } catch (IOException e) {
		    throw new RuntimeException("Error starting json document", e);
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
