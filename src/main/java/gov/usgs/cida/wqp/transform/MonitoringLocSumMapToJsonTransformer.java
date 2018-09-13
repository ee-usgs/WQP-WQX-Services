package gov.usgs.cida.wqp.transform;

import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.service.ILogService;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;

public class MonitoringLocSumMapToJsonTransformer extends BaseMapToJsonTransformer {    
    
    public MonitoringLocSumMapToJsonTransformer(OutputStream target, Map<String, String> mapping, ILogService logService, BigDecimal logId, String siteUrlBase) {
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
	
    @Override
    protected void writeData(Map<String, Object> resultMap) {
	    try {
		    g.writeStartObject();

				g.writeStringField("type", "Feature");

				g.writeObjectFieldStart("geometry");
					g.writeStringField("type", "Point");

					g.writeArrayFieldStart("coordinates");
						g.writeNumber(getValue(resultMap, StationColumn.KEY_LONGITUDE));
						g.writeNumber(getValue(resultMap, StationColumn.KEY_LATITUDE));
					g.writeEndArray();

				g.writeEndObject();

				g.writeObjectFieldStart("properties");
					g.writeStringField("ProviderName", getValue(resultMap, StationColumn.KEY_DATA_SOURCE));
					g.writeStringField("OrganizationIdentifier", getValue(resultMap, StationColumn.KEY_ORGANIZATION));
					g.writeStringField("OrganizationFormalName", getValue(resultMap, StationColumn.KEY_ORGANIZATION_NAME));
					g.writeStringField("MonitoringLocationIdentifier", getValue(resultMap, StationColumn.KEY_SITE_ID));
					g.writeStringField("MonitoringLocationName", getValue(resultMap, StationColumn.KEY_STATION_NAME));
					g.writeStringField("MonitoringLocationTypeName", getValue(resultMap, StationColumn.KEY_MONITORING_LOCATION_TYPE));
					g.writeStringField("ResolvedMonitoringLocationTypeName", getValue(resultMap, StationColumn.KEY_SITE_TYPE));
					g.writeStringField("HUCEightDigitCode", getValue(resultMap, StationColumn.KEY_HUC_8));
					g.writeStringField("siteUrl", siteUrlBase + "/provider/" + getValue(resultMap, StationColumn.KEY_DATA_SOURCE)
							+ "/" + getValue(resultMap, StationColumn.KEY_ORGANIZATION) + "/" + getValue(resultMap, StationColumn.KEY_SITE_ID) + "/");
					g.writeStringField("activityCount", getValue(resultMap, StationColumn.KEY_ACTIVITY_COUNT));
					g.writeStringField("resultCount", getValue(resultMap, StationColumn.KEY_RESULT_COUNT));
					g.writeStringField("StateName", getValue(resultMap, StationColumn.KEY_STATE_NAME));
					g.writeStringField("CountyName", getValue(resultMap, StationColumn.KEY_COUNTY_NAME));                        

					g.writeFieldName("characteristicGroupResultCount");
					g.writeRawValue(getValue(resultMap, getSummaryColumnName(resultMap)));
				g.writeEndObject();
			    
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


// TODO refactor mapper to work with alias so that this method can be removed
    @Override
    protected String getSummaryColumnName(Map<String, Object> resultMap) {                
	String summaryColumnName = null;
	if (resultMap.containsKey(StationColumn.KEY_SUMMARY_PAST_12_MONTHS)) { 
	    summaryColumnName = StationColumn.KEY_SUMMARY_PAST_12_MONTHS;		
	} else if (resultMap.containsKey(StationColumn.KEY_SUMMARY_PAST_60_MONTHS)) {
	    summaryColumnName = StationColumn.KEY_SUMMARY_PAST_60_MONTHS;				
	} else if (resultMap.containsKey(StationColumn.KEY_SUMMARY_ALL_MONTHS)) {
	    summaryColumnName = StationColumn.KEY_SUMMARY_ALL_MONTHS;				
	}
	return summaryColumnName;
    } 
    
}
