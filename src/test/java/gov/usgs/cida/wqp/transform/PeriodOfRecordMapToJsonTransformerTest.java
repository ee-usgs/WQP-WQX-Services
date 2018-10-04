package gov.usgs.cida.wqp.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PeriodOfRecordMapToJsonTransformerTest {
	private static final transient Logger LOG = LoggerFactory.getLogger(StationMapToJsonTransformer.class);
	
    public static final String JSON_HEADER = "{\"type\":\"FeatureCollection\",\"features\":[";    
	public static final String MOCK_JSON_PERIOD_OF_RECORD_1 = "[{\"year\":2018,\"start\":\"01-01-2018\",\"end\":\"08-21-2018\",\"activityCount\":50,\"resultCount\":5400,\"monitoringLocationsSampled\":25,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":1,\"Microbiological\":6,\"NotAssigned\":3,\"Nutrient\":28,\"Organics,Other\":7,\"Physical\":18},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":12,\"resultCount\":23,\"monitoringLocationCount\":5},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":12,\"resultCount\":15,\"monitoringLocationCount\":5}]},{\"year\":2017,\"start\":\"2017-01-01\",\"end\":\"2017-12-31\",\"activityCount\":150,\"resultCount\":7400,\"monitoringLocationsSampled\":125,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":34,\"Microbiological\":12,\"NotAssigned\":6,\"Nutrient\":5500,\"Organics,Other\":12,\"Physical\":18},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":30,\"resultCount\":12,\"monitoringLocationCount\":4},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":15,\"resultCount\":34,\"monitoringLocationCount\":15}]}]";
    public static final String MOCK_JSON_PERIOD_OF_RECORD_2 = "[{\"year\":2017,\"start\":\"01-01-2017\",\"end\":\"08-21-2017\",\"activityCount\":57,\"resultCount\":5700,\"monitoringLocationsSampled\":25,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":1,\"Microbiological\":2,\"NotAssigned\":2,\"Nutrient\":2,\"Organics,Other\":2,\"Physical\":2},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":2,\"resultCount\":2,\"monitoringLocationCount\":2},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":12,\"resultCount\":15,\"monitoringLocationCount\":5}]},{\"year\":2017,\"start\":\"2017-01-01\",\"end\":\"2017-12-31\",\"activityCount\":150,\"resultCount\":7400,\"monitoringLocationsSampled\":125,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":34,\"Microbiological\":12,\"NotAssigned\":6,\"Nutrient\":5500,\"Organics,Other\":12,\"Physical\":18},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":30,\"resultCount\":12,\"monitoringLocationCount\":4},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":15,\"resultCount\":34,\"monitoringLocationCount\":15}]}]";
	public static final String MOCK_JSON_WITHOUT_HEADER_1 = "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[,]},\"properties\":{\"ProviderName\":\"ds\",\"OrganizationIdentifier\":\"org\",\"OrganizationFormalName\":\"org/name\",\"MonitoringLocationIdentifier\":\"site\",\"MonitoringLocationName\":\"station\",\"MonitoringLocationTypeName\":\"realType\",\"ResolvedMonitoringLocationTypeName\":\"ResolvedMonitoringLocationTypeName\",\"HUCEightDigitCode\":\"huceight\",\"siteUrl\":\"http://test-url.usgs.gov/provider/ds/org/site/\",\"StateName\":\"Wisconsin\",\"CountyName\":\"Dane\",\"lastResultSubmittedDate\":\"1981\",\"totalActivities\":\"\",\"yearlySummary\":[{\"year\":2018,\"start\":\"01-01-2018\",\"end\":\"08-21-2018\",\"activityCount\":50,\"resultCount\":5400,\"monitoringLocationsSampled\":25,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":1,\"Microbiological\":6,\"NotAssigned\":3,\"Nutrient\":28,\"Organics,Other\":7,\"Physical\":18},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":12,\"resultCount\":23,\"monitoringLocationCount\":5},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":12,\"resultCount\":15,\"monitoringLocationCount\":5}]},{\"year\":2017,\"start\":\"2017-01-01\",\"end\":\"2017-12-31\",\"activityCount\":150,\"resultCount\":7400,\"monitoringLocationsSampled\":125,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":34,\"Microbiological\":12,\"NotAssigned\":6,\"Nutrient\":5500,\"Organics,Other\":12,\"Physical\":18},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":30,\"resultCount\":12,\"monitoringLocationCount\":4},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":15,\"resultCount\":34,\"monitoringLocationCount\":15}]}]}}";
	public static final String MOCK_JSON_WITHOUT_HEADER_2 = "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[,]},\"properties\":{\"ProviderName\":\"ds\",\"OrganizationIdentifier\":\"org\",\"OrganizationFormalName\":\"org/name\",\"MonitoringLocationIdentifier\":\"site\",\"MonitoringLocationName\":\"station\",\"MonitoringLocationTypeName\":\"realType\",\"ResolvedMonitoringLocationTypeName\":\"ResolvedMonitoringLocationTypeName\",\"HUCEightDigitCode\":\"huceight\",\"siteUrl\":\"http://test-url.usgs.gov/provider/ds/org/site/\",\"StateName\":\"Wisconsin\",\"CountyName\":\"Dane\",\"lastResultSubmittedDate\":\"1981\",\"totalActivities\":\"\",\"yearlySummary\":[{\"year\":2018,\"start\":\"01-01-2018\",\"end\":\"08-21-2018\",\"activityCount\":50,\"resultCount\":5400,\"monitoringLocationsSampled\":25,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":1,\"Microbiological\":6,\"NotAssigned\":3,\"Nutrient\":28,\"Organics,Other\":7,\"Physical\":18},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":12,\"resultCount\":23,\"monitoringLocationCount\":5},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":12,\"resultCount\":15,\"monitoringLocationCount\":5}]},{\"year\":2017,\"start\":\"2017-01-01\",\"end\":\"2017-12-31\",\"activityCount\":150,\"resultCount\":7400,\"monitoringLocationsSampled\":125,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":34,\"Microbiological\":12,\"NotAssigned\":6,\"Nutrient\":5500,\"Organics,Other\":12,\"Physical\":18},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":30,\"resultCount\":12,\"monitoringLocationCount\":4},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":15,\"resultCount\":34,\"monitoringLocationCount\":15}]}]}},{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[,]},\"properties\":{\"ProviderName\":\"ds2\",\"OrganizationIdentifier\":\"org2\",\"OrganizationFormalName\":\"org/name2\",\"MonitoringLocationIdentifier\":\"site2\",\"MonitoringLocationName\":\"station2\",\"MonitoringLocationTypeName\":\"realType2\",\"ResolvedMonitoringLocationTypeName\":\"ResolvedMonitoringLocationTypeName2\",\"HUCEightDigitCode\":\"huceight2\",\"siteUrl\":\"http://test-url.usgs.gov/provider/ds2/org2/site2/\",\"StateName\":\"Wisconsin2\",\"CountyName\":\"Dane2\",\"lastResultSubmittedDate\":\"21980\",\"totalActivities\":\"\",\"yearlySummary\":[{\"year\":2017,\"start\":\"01-01-2017\",\"end\":\"08-21-2017\",\"activityCount\":57,\"resultCount\":5700,\"monitoringLocationsSampled\":25,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":1,\"Microbiological\":2,\"NotAssigned\":2,\"Nutrient\":2,\"Organics,Other\":2,\"Physical\":2},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":2,\"resultCount\":2,\"monitoringLocationCount\":2},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":12,\"resultCount\":15,\"monitoringLocationCount\":5}]},{\"year\":2017,\"start\":\"2017-01-01\",\"end\":\"2017-12-31\",\"activityCount\":150,\"resultCount\":7400,\"monitoringLocationsSampled\":125,\"characteristicGroupResultCount\":{\"Inorganics,Minor,Non-metals\":34,\"Microbiological\":12,\"NotAssigned\":6,\"Nutrient\":5500,\"Organics,Other\":12,\"Physical\":18},\"characteristicNameSummary\":[{\"characteristicName\":\"Nitrate\",\"characteristicType\":\"Nutrient\",\"activityCount\":30,\"resultCount\":12,\"monitoringLocationCount\":4},{\"characteristicName\":\"Phosphate\",\"characteristicType\":\"Nutrient\",\"activityCount\":15,\"resultCount\":34,\"monitoringLocationCount\":15}]}]}}";
	
	@Mock
    protected ILogService logService;
    protected BigDecimal logId = new BigDecimal(1);
    protected BaseMapToJsonTransformer transformer;
    protected ByteArrayOutputStream baos;
    protected String siteUrlBase = "http://test-url.usgs.gov";   

    @Before
    public void initTest() {
	    MockitoAnnotations.initMocks(this);
	    baos = new ByteArrayOutputStream();
	    transformer = new PeriodOfRecordMapToJsonTransformer(baos, null, logService, logId, siteUrlBase);
    }

    @After
    public void closeTest() throws IOException {
	    transformer.close();
    }

    @Test
    public void writeHeaderTest() {
	    try {
		    //need to flush the JsonGenerator to get an output. 
		    transformer.g.flush();
		    assertEquals(JSON_HEADER.length(), baos.size());
		    assertEquals(JSON_HEADER, new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
	    } catch (IOException e) {
		    fail(e.getLocalizedMessage());
	    }
    }

    @Test
    public void writeDataTest() {
		// create a map to simulate json data returned; this data has one monitoring location
	    Map<String, Object> testMap = new HashMap<>();
	    testMap.put(StationColumn.KEY_DATA_SOURCE, "ds");
	    testMap.put(StationColumn.KEY_ORGANIZATION, "org");
	    testMap.put(StationColumn.KEY_ORGANIZATION_NAME, "org/name");
	    testMap.put(StationColumn.KEY_SITE_ID, "site");
	    testMap.put(StationColumn.KEY_STATION_NAME, "station");
	    testMap.put(StationColumn.KEY_MONITORING_LOCATION_TYPE, "realType");
		testMap.put(StationColumn.KEY_SITE_TYPE, "ResolvedMonitoringLocationTypeName");
	    testMap.put(StationColumn.KEY_HUC_8, "huceight");
	    testMap.put(StationColumn.KEY_STATE_NAME, "Wisconsin");
	    testMap.put(StationColumn.KEY_COUNTY_NAME, "Dane"); 
	    testMap.put(StationColumn.KEY_LAST_SUBMITTED_DATE, 2018-12-25);
	    testMap.put(StationColumn.KEY_PERIOD_OF_RECORD_SUMMARY, MOCK_JSON_PERIOD_OF_RECORD_1); 
	    
	    try {
		// feed the test map to the json transformer	
	    transformer.writeData(testMap);
	    // need to flush the JsonGenerator to get at output. 
	    transformer.g.flush();
		
		// check that the standard beginning (json header) of the json returned from the transformer and test map is the expected size
	    assertEquals(JSON_HEADER.length() + 1695, baos.size());
		// check that the header and the rest of the json data returned from the transformer exactly match what is expected
	    assertEquals(JSON_HEADER + MOCK_JSON_WITHOUT_HEADER_1,
				    new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
	    } catch (IOException e) {
		    fail(e.getLocalizedMessage());
	    }
		// add a second monitoring location to the map that simulates the json data returned
		testMap.put(StationColumn.KEY_DATA_SOURCE, "ds2");
	    testMap.put(StationColumn.KEY_ORGANIZATION, "org2");
	    testMap.put(StationColumn.KEY_ORGANIZATION_NAME, "org/name2");
	    testMap.put(StationColumn.KEY_SITE_ID, "site2");
	    testMap.put(StationColumn.KEY_STATION_NAME, "station2");
	    testMap.put(StationColumn.KEY_MONITORING_LOCATION_TYPE, "realType2");
		testMap.put(StationColumn.KEY_SITE_TYPE, "ResolvedMonitoringLocationTypeName2");
	    testMap.put(StationColumn.KEY_HUC_8, "huceight2");
	    testMap.put(StationColumn.KEY_STATE_NAME, "Wisconsin2");
	    testMap.put(StationColumn.KEY_COUNTY_NAME, "Dane2"); 
	    testMap.put(StationColumn.KEY_LAST_SUBMITTED_DATE, 22018-12-26);
	    testMap.put(StationColumn.KEY_PERIOD_OF_RECORD_SUMMARY, MOCK_JSON_PERIOD_OF_RECORD_2); 
		
		try {
			// repeat the process with an additional monitoring location added to the test map
		    transformer.writeData(testMap);
		    //need to flush the JsonGenerator to get at output. 
		    transformer.g.flush();
	String debugString =  new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING);	
		    assertEquals(JSON_HEADER.length() + 3401, baos.size());
		    assertEquals(JSON_HEADER + MOCK_JSON_WITHOUT_HEADER_2,
				    new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
	    } catch (IOException e) {
		    fail(e.getLocalizedMessage());
	    }		
    } 

    @Test
    public void endTestData() {
	    try {
		    transformer.first = false;
		    transformer.g.writeStartObject();
		    transformer.g.writeFieldName("abc");
		    transformer.g.writeStartArray();

		    transformer.end();
		    assertEquals(JSON_HEADER.length() + 12, baos.size());
		    assertEquals(JSON_HEADER + "{\"abc\":[]}]}",
				    new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
	    } catch (IOException e) {
		    fail(e.getLocalizedMessage());
	    }
    }

    @Test
    public void endTestNoData() {
	    try {
		    transformer.end();
		    assertEquals(JSON_HEADER.length() + 2, baos.size());
		    assertEquals(JSON_HEADER + "]}",
				    new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
	    } catch (IOException e) {
		    fail(e.getLocalizedMessage());
	    }
    }
}
