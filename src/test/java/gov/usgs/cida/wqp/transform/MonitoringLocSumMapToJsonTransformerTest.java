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


public class MonitoringLocSumMapToJsonTransformerTest {

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
	    transformer = new MonitoringLocSumMapToJsonTransformer(baos, null, logService, logId, siteUrlBase);
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
	    Map<String, Object> map = new HashMap<>();
	    map.put(StationColumn.KEY_LONGITUDE, "long");
	    map.put(StationColumn.KEY_LATITUDE, "lat");
	    map.put(StationColumn.KEY_DATA_SOURCE, "ds");
	    map.put(StationColumn.KEY_ORGANIZATION, "org");
	    map.put(StationColumn.KEY_ORGANIZATION_NAME, "org/name");
	    map.put(StationColumn.KEY_SITE_ID, "site");
	    map.put(StationColumn.KEY_STATION_NAME, "station");
	    map.put(StationColumn.KEY_MONITORING_LOCATION_TYPE, "realType");
	    map.put(StationColumn.KEY_SITE_TYPE, "type");
	    map.put(StationColumn.KEY_HUC_8, "huceight");
	    map.put(StationColumn.KEY_ACTIVITY_COUNT, 57);
	    map.put(StationColumn.KEY_RESULT_COUNT, 857);
	    map.put(StationColumn.KEY_STATE_NAME, "Wisconsin");
	    map.put(StationColumn.KEY_COUNTY_NAME, "Dane"); 		
	    map.put(StationColumn.KEY_SUMMARY_PAST_12_MONTHS, "{\"testKey\":\"testValue\"}"); 


	    try {
		    transformer.writeData(map);
		    //need to flush the JsonGenerator to get at output. 
		    transformer.g.flush();
		    assertEquals(JSON_HEADER.length() + 562, baos.size());
		    assertEquals(JSON_HEADER + "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[long,lat]},\"properties\":{\"ProviderName\":\"ds\",\"OrganizationIdentifier\":\"org\",\"OrganizationFormalName\":\"org/name\",\"MonitoringLocationIdentifier\":\"site\",\"MonitoringLocationName\":\"station\",\"MonitoringLocationTypeName\":\"realType\",\"ResolvedMonitoringLocationTypeName\":\"type\",\"HUCEightDigitCode\":\"huceight\""
				    + ",\"siteUrl\":\"http://test-url.usgs.gov/provider/ds/org/site/\",\"activityCount\":\"57\",\"resultCount\":\"857\",\"StateName\":\"Wisconsin\",\"CountyName\":\"Dane\",\"characteristicGroupResultCount\":{\"testKey\":\"testValue\"}}}",
				    new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
	    } catch (IOException e) {
		    fail(e.getLocalizedMessage());
	    }

	    map.put(StationColumn.KEY_LONGITUDE, "long2");
	    map.put(StationColumn.KEY_LATITUDE, "lat2");
	    map.put(StationColumn.KEY_DATA_SOURCE, "ds2");
	    map.put(StationColumn.KEY_ORGANIZATION, "org2");
	    map.put(StationColumn.KEY_ORGANIZATION_NAME, "org/name2");
	    map.put(StationColumn.KEY_SITE_ID, "site2");
	    map.put(StationColumn.KEY_STATION_NAME, "station2");
	    map.put(StationColumn.KEY_MONITORING_LOCATION_TYPE, "realType2");
	    map.put(StationColumn.KEY_SITE_TYPE, "type2");
	    map.put(StationColumn.KEY_HUC_8, "huceigh2");
	    map.put(StationColumn.KEY_ACTIVITY_COUNT, 67);
	    map.put(StationColumn.KEY_RESULT_COUNT, 667);
	    map.put(StationColumn.KEY_STATE_NAME, "Wisconsin");
	    map.put(StationColumn.KEY_COUNTY_NAME, "Dane");
	    map.put(StationColumn.KEY_SUMMARY_PAST_12_MONTHS, "{\"testKey2\":\"testValue2\"}"); 

	    try {
		    transformer.writeData(map);
		    //need to flush the JsonGenerator to get at output. 
		    transformer.g.flush();
		    assertEquals(JSON_HEADER.length() + 1139, baos.size());
		    assertEquals(JSON_HEADER + "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[long,lat]},\"properties\":{\"ProviderName\":\"ds\",\"OrganizationIdentifier\":\"org\",\"OrganizationFormalName\":\"org/name\",\"MonitoringLocationIdentifier\":\"site\",\"MonitoringLocationName\":\"station\",\"MonitoringLocationTypeName\":\"realType\",\"ResolvedMonitoringLocationTypeName\":\"type\",\"HUCEightDigitCode\":\"huceight\""
				    + ",\"siteUrl\":\"http://test-url.usgs.gov/provider/ds/org/site/\",\"activityCount\":\"57\",\"resultCount\":\"857\",\"StateName\":\"Wisconsin\",\"CountyName\":\"Dane\",\"characteristicGroupResultCount\":{\"testKey\":\"testValue\"}}}"
				    + ",{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[long2,lat2]},\"properties\":{\"ProviderName\":\"ds2\",\"OrganizationIdentifier\":\"org2\",\"OrganizationFormalName\":\"org/name2\",\"MonitoringLocationIdentifier\":\"site2\",\"MonitoringLocationName\":\"station2\",\"MonitoringLocationTypeName\":\"realType2\",\"ResolvedMonitoringLocationTypeName\":\"type2\",\"HUCEightDigitCode\":\"huceigh2\""
				    + ",\"siteUrl\":\"http://test-url.usgs.gov/provider/ds2/org2/site2/\",\"activityCount\":\"67\",\"resultCount\":\"667\",\"StateName\":\"Wisconsin\",\"CountyName\":\"Dane\",\"characteristicGroupResultCount\":{\"testKey2\":\"testValue2\"}}}",
				    new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
	    } catch (IOException e) {
		    fail(e.getLocalizedMessage());
	    }
    }

    @Test public void getSummaryColumnNameTest() {             
	    Map<String, Object> map = new HashMap<>();
	    Map<String, Object> mapWithoutSummary = new HashMap<>();

	    map.put(StationColumn.KEY_STATE_NAME, "Wisconsin");
	    map.put(StationColumn.KEY_COUNTY_NAME, "Dane");
	    map.put(StationColumn.KEY_SUMMARY_PAST_12_MONTHS, "");

	    mapWithoutSummary.put(StationColumn.KEY_STATE_NAME, "Wisconsin");
	    mapWithoutSummary.put(StationColumn.KEY_COUNTY_NAME, "Dane"); 

	    assertEquals(transformer.getSummaryColumnName(map), "SUMMARY_PAST_12_MONTHS" );
	    assertEquals(transformer.getSummaryColumnName(mapWithoutSummary), null);   
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

