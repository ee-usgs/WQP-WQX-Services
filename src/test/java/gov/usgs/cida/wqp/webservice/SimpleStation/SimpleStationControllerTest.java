package gov.usgs.cida.wqp.webservice.SimpleStation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.IntegrationTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.util.CORSFilter;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

@Category(IntegrationTest.class)
@WebAppConfiguration
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/simpleStation.xml")
})
public class SimpleStationControllerTest extends BaseSpringTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
	protected CORSFilter filter;
    
    @Autowired
    private CodesService codesService;

    private MockMvc mockMvc;
    
    @Before
    public void setup() {
         mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilters(filter).build();
    }

    @Test
    public void getAsJsonTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/simplestation/search?mimeType=json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.json"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
        
        rtn = mockMvc.perform(get("/simplestation/search?mimeType=json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.json"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
        		sameJSONObjectAs(new JSONObject(getCompareFile("simpleStation.json"))));
    }

    @Test
    public void getAsJsonZipTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/simplestation/search?mimeType=json&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
        
        rtn = mockMvc.perform(get("/simplestation/search?mimeType=json&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertThat(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), "simplestation.json")),
        		sameJSONObjectAs(new JSONObject(getCompareFile("simpleStation.json"))));
    }

    @Test
    public void getAsXmlTest_HEAD() throws Exception {
        MvcResult rtn = mockMvc.perform(head("/simplestation/search?mimeType=xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xml"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }
    
    @Test
    public void getAsXmlTest_GET() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/simplestation/search?mimeType=xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xml"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }

    @Test
    public void getAsXmlZipTest_HEAD() throws Exception {
        MvcResult rtn = mockMvc.perform(head("/simplestation/search?mimeType=xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }
    
    @Test
    public void getAsXmlZipTest_GET() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/simplestation/search?mimeType=xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), "simplestation.xml")));
    }

    @Test
    public void kitchenSinkTest() throws Exception {
        when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

    	MvcResult rtn = mockMvc.perform(
    		get("/simplestation/search?mimeType=xml" +
    			"&analyticalmethod=https://www.nemi.gov/methods/method_summary/4665/;https://www.nemi.gov/methods/method_summary/8896/" + 
    			"bBox=-89;43;-88;44" +
    			"&characteristicName=Beryllium;Nitrate" +
    			"&characteristicType=Inorganics, Minor, Metals;Nutrient" + //TODO ;Population/Community" + 
    			"&command.avoid=STORET;NWIS" + 
    			"&countrycode=MX;US" + 
    			"&countycode=US:19:015;US:30:003;US:55:017;US:55:021;US:55:027" +
    			"&huc=07*;0708*;070801*;07090002;07080105" + 
    			"&lat=43.3836014" +
    			"&long=-88.9773314" + 
    			"&organization=ARS;11NPSWRD;USGS-WI;WIDNR_WQX" + 
    			"&pCode=00032;00004" +
    			"&project=CEAP;NAWQA" +
    			"&sampleMedia=Other;Sediment;Water" +
    			"&providers=NWIS;STEWARDS;STORET" +
    			"&siteid=11NPSWRD-BICA_MFG_B;WIDNR_WQX-10030952;USGS-05425700;USGS-431925089002701;ARS-IAWC-IAWC225;ARS-IAWC-IAWC410" +
    			"&siteType=Lake, Reservoir, Impoundment;Land;Stream;Well" + 
    			"&statecode=US:19;US:30;US:55" + 
    			"&startDateHi=10-11-2012" +
    			"&startDateLo=10-11-2012" +
    			"&within=1000"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xml"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
			.andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
//        assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
	
    }

}
