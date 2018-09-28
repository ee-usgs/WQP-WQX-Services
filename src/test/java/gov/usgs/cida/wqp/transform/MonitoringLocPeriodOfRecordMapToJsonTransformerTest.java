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


public class MonitoringLocPeriodOfRecordMapToJsonTransformerTest {

    public static final String JSON_HEADER = "{\"type\":\"FeatureCollection\",\"features\":[";
    private static final transient Logger LOG = LoggerFactory.getLogger(MonitoringLocSumMapToJsonTransformer.class);

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
	    transformer = new MonitoringLocPeriodOfRecordMapToJsonTransformer(baos, null, logService, logId, siteUrlBase);
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
		// create a map to simulate json data returned, this data has one monitoring location
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
	    testMap.put(StationColumn.KEY_PERIOD_OF_RECORD_SUMMARY, "{\"testKey\":\"testValue\"}"); 
	    
	    try {
		// feed the test map to the json transformer	
	    transformer.writeData(testMap);
	    // need to flush the JsonGenerator to get at output. 
	    transformer.g.flush();
		
		// check that the standard beginning (json header) of the json returned from the transformer and test map is the expected size
//	    assertEquals(JSON_HEADER.length() + 557, baos.size());
		// check that the header and the rest of the json data returned from the transformer exactly match what is expected
//	    assertEquals(JSON_HEADER + "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[,]},\"properties\":{\"ProviderName\":\"ds\",\"OrganizationIdentifier\":\"org\",\"OrganizationFormalName\":\"org/name\",\"MonitoringLocationIdentifier\":\"site\",\"MonitoringLocationName\":\"station\",\"MonitoringLocationTypeName\":\"realType\",\"ResolvedMonitoringLocationTypeName\":\"ResolvedMonitoringLocationTypeName\",\"HUCEightDigitCode\":\"huceight\",\"siteUrl\":\"http://test-url.usgs.gov/provider/ds/org/site/\",\"activityCount\":\"\",\"resultCount\":\"\",\"StateName\":\"Wisconsin\",\"CountyName\":\"Dane\",\"characteristicGroupResultCount\":}}",
//				    new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
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
	    testMap.put(StationColumn.KEY_PERIOD_OF_RECORD_SUMMARY, "{\"testKey\":\"testValue\"}"); 
		
		try {
			// repeat the process with an additional monitoring location added to the test map
		    transformer.writeData(testMap);
		    //need to flush the JsonGenerator to get at output. 
		    transformer.g.flush();
		
//		    assertEquals(JSON_HEADER.length() + 1128, baos.size());
//		    assertEquals(JSON_HEADER + "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[,]},\"properties\":{\"ProviderName\":\"ds\",\"OrganizationIdentifier\":\"org\",\"OrganizationFormalName\":\"org/name\",\"MonitoringLocationIdentifier\":\"site\",\"MonitoringLocationName\":\"station\",\"MonitoringLocationTypeName\":\"realType\",\"ResolvedMonitoringLocationTypeName\":\"ResolvedMonitoringLocationTypeName\",\"HUCEightDigitCode\":\"huceight\",\"siteUrl\":\"http://test-url.usgs.gov/provider/ds/org/site/\",\"activityCount\":\"\",\"resultCount\":\"\",\"StateName\":\"Wisconsin\",\"CountyName\":\"Dane\",\"characteristicGroupResultCount\":}},{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[,]},\"properties\":{\"ProviderName\":\"ds2\",\"OrganizationIdentifier\":\"org2\",\"OrganizationFormalName\":\"org/name2\",\"MonitoringLocationIdentifier\":\"site2\",\"MonitoringLocationName\":\"station2\",\"MonitoringLocationTypeName\":\"realType2\",\"ResolvedMonitoringLocationTypeName\":\"ResolvedMonitoringLocationTypeName2\",\"HUCEightDigitCode\":\"huceight2\",\"siteUrl\":\"http://test-url.usgs.gov/provider/ds2/org2/site2/\",\"activityCount\":\"\",\"resultCount\":\"\",\"StateName\":\"Wisconsin2\",\"CountyName\":\"Dane2\",\"characteristicGroupResultCount\":}}",
//				    new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
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
