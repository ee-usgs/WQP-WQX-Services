package gov.usgs.cida.wqp.transform;

import gov.usgs.cida.wqp.mapping.OrganizationColumn;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrganizationSumMapToJsonTransformerTest {
    
    public static final String JSON_HEADER = "{\"organization\":[";
    private static final transient Logger LOG = LoggerFactory.getLogger(OrganizationSumMapToJsonTransformer.class);
    
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
	    transformer = new OrganizationSumMapToJsonTransformer(baos, null, logService, logId, siteUrlBase);
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
	map.put(OrganizationColumn.KEY_ORGANIZATION, "WIDNR_WQX_test");
	map.put("organizationFormalName", "Wisconsin DNR_test"); // need the constant for this when it is created
	map.put("organizationWQPUrl", "https://www.waterqualitydata.us/provider/STORET/WIDNR_WQX/"); // need the constant for this when it is created
	map.put("lastResultSubmittedDate", "2018-06-24"); // need the constant for this when it is created
	map.put("totalMonitoringLocationsSampled", 500); // need the constant for this when it is created 
	map.put("totalActivities", 25000); // need the constant for this when it is created
	map.put("yearlySummary", YEARLY_SUMMARY); // need the constant for this when it is created
	
	    try {
		    transformer.writeData(map);
		    //need to flush the JsonGenerator to get at output. 
		    transformer.g.flush();
		    assertEquals(JSON_HEADER.length() + 562, baos.size());    
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
    
    public static final String YEARLY_SUMMARY = "[\n" +
"        {\n" +
"            \"year\": 2018,\n" +
"            \"start\": \"01-01-2018\",\n" +
"            \"end\": \"08-21-2018\",\n" +
"            \"activityCount\": 50,\n" +
"            \"resultCount\": 5400,\n" +
"            \"monitoringLocationsSampled\": 25,\n" +
"            \"characteristicGroupResultCount\": {\n" +
"                \"Inorganics, Minor, Non-metals\": 1,\n" +
"                \"Microbiological\": 6,\n" +
"                \"Not Assigned\": 3,\n" +
"                \"Nutrient\": 28,\n" +
"                \"Organics, Other\": 7,\n" +
"                \"Physical\": 18\n" +
"            },\n" +
"            \"characteristicNameSummary\": [\n" +
"                {\n" +
"                    \"characteristicName\": \"Nitrate\",\n" +
"                    \"characteristicType\": \"Nutrient\",\n" +
"                    \"activityCount\": 12,\n" +
"                    \"resultCount\": 23,\n" +
"                    \"monitoringLocationCount\": 5\n" +
"                },\n" +
"                {\n" +
"                    \"characteristicName\": \"Phosphate\",\n" +
"                    \"characteristicType\": \"Nutrient\",\n" +
"                    \"activityCount\": 12,\n" +
"                    \"resultCount\": 15,\n" +
"                    \"monitoringLocationCount\": 5\n" +
"                }\n" +
"            ]\n" +
"        },\n" +
"        {\n" +
"            \"year\": 2017,\n" +
"            \"start\": \"2017-01-01\",\n" +
"            \"end\": \"2017-12-31\",\n" +
"            \"activityCount\": 150,\n" +
"            \"resultCount\": 7400,\n" +
"            \"monitoringLocationsSampled\": 125,\n" +
"            \"characteristicGroupResultCount\": {\n" +
"                \"Inorganics, Minor, Non-metals\": 34,\n" +
"                \"Microbiological\": 12,\n" +
"                \"Not Assigned\": 6,\n" +
"                \"Nutrient\": 5500,\n" +
"                \"Organics, Other\": 12,\n" +
"                \"Physical\": 18\n" +
"            },\n" +
"            \"characteristicNameSummary\": [\n" +
"                {\n" +
"                    \"characteristicName\": \"Nitrate\",\n" +
"                    \"characteristicType\": \"Nutrient\",\n" +
"                    \"activityCount\": 30,\n" +
"                    \"resultCount\": 12,\n" +
"                    \"monitoringLocationCount\": 4\n" +
"                },\n" +
"                {\n" +
"                    \"characteristicName\": \"Phosphate\",\n" +
"                    \"characteristicType\": \"Nutrient\",\n" +
"                    \"activityCount\": 15,\n" +
"                    \"resultCount\": 34,\n" +
"                    \"monitoringLocationCount\": 15\n" +
"                }\n" +
"            ]\n" +
"        }\n" +
"    ]";    
}


