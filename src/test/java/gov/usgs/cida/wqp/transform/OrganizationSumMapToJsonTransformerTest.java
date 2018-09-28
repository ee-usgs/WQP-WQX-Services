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
    public static final String YEARLY_SUMMARY_PROVIDER_1 = "{'testKey':'testValue'}";     
    public static final String YEARLY_SUMMARY_PROVIDER_2 = "{'testKey_2':'testValue_2'}";    
    public static final String EXPECTED_JSON_RETURNED_1 = "{\"organizationID\":\"WIDNR_WQX_test\",\"organizationFormalName\":\"Wisconsin DNR_test\",\"organizationWQPUrl\":\"https://www.waterqualitydata.us/provider/STORET/WIDNR_WQX/\",\"lastResultSubmittedDate\":\"2018-06-24\",\"totalMonitoringLocationsSampled\":\"500\",\"totalActivities\":\"25000\",\"yearlySummary\":{'testKey':'testValue'}}";    
    public static final String EXPECTED_JSON_RETURNED_2 = "{\"organizationID\":\"WIDNR_WQX_test_2\",\"organizationFormalName\":\"Wisconsin DNR_test_2\",\"organizationWQPUrl\":\"https://www.waterqualitydata.us/provider/STORET/WIDNR_WQX/two/\",\"lastResultSubmittedDate\":\"2018-06-28\",\"totalMonitoringLocationsSampled\":\"506\",\"totalActivities\":\"25006\",\"yearlySummary\":{'testKey_2':'testValue_2'}}";  
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
	map.put(OrganizationColumn.KEY_ORGANIZATION_NAME, "Wisconsin DNR_test"); 
	map.put(OrganizationColumn.KEY_ORGANIZATION_SUMMARY_WQP_URL, "https://www.waterqualitydata.us/provider/STORET/WIDNR_WQX/");  
	map.put(OrganizationColumn.KEY_LAST_RESULT,"2018-06-24"); 
	map.put(OrganizationColumn.KEY_SITE_COUNT, 500); 
	map.put(OrganizationColumn.KEY_ACTIVITY_COUNT, 25000); 
	map.put(OrganizationColumn.KEY_ORGANIZATION_SUMMARY, YEARLY_SUMMARY_PROVIDER_1);
	
	try {
		transformer.writeData(map);
		//need to flush the JsonGenerator to get at output. 
		transformer.g.flush();
		assertEquals(JSON_HEADER.length() + 308, baos.size()); 
		assertEquals(JSON_HEADER + EXPECTED_JSON_RETURNED_1,
				    new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING)); 
	} catch (IOException e) {
		fail(e.getLocalizedMessage());
	}
	    
	map.put(OrganizationColumn.KEY_ORGANIZATION, "WIDNR_WQX_test_2");
	map.put(OrganizationColumn.KEY_ORGANIZATION_NAME, "Wisconsin DNR_test_2"); 
	map.put(OrganizationColumn.KEY_ORGANIZATION_SUMMARY_WQP_URL, "https://www.waterqualitydata.us/provider/STORET/WIDNR_WQX/two/");  
	map.put(OrganizationColumn.KEY_LAST_RESULT,"2018-06-28"); 
	map.put(OrganizationColumn.KEY_SITE_COUNT, 506); 
	map.put(OrganizationColumn.KEY_ACTIVITY_COUNT, 25006); 
	map.put(OrganizationColumn.KEY_ORGANIZATION_SUMMARY, YEARLY_SUMMARY_PROVIDER_2);    
	
	try {
		transformer.writeData(map);
		//need to flush the JsonGenerator to get at output. 
		transformer.g.flush();
		assertEquals(JSON_HEADER.length() + 629, baos.size()); 
		assertEquals(JSON_HEADER + EXPECTED_JSON_RETURNED_1 + "," + EXPECTED_JSON_RETURNED_2,
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
}   
