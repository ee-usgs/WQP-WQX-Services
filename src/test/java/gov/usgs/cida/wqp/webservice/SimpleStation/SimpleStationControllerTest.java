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

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.FullIntegrationTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.util.HttpConstants;

@Category(FullIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/simpleStation.xml")
})
public class SimpleStationControllerTest extends BaseSpringTest {

	protected String endpoint = "/" + HttpConstants.SIMPLE_STATION_ENDPOINT + "?mimeType=";
	
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CodesService codesService;

    private MockMvc mockMvc;
    
    @Before
    public void setup() {
         mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getAsJsonTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.json"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

    	assertEquals("", rtn.getResponse().getContentAsString());
        
        rtn = mockMvc.perform(get(endpoint + "json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.json"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

        assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
        		sameJSONObjectAs(new JSONObject(getCompareFile("simpleStation.json"))));
    }

    @Test
    public void getAsJsonZipTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "json&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

    	assertEquals("", rtn.getResponse().getContentAsString());
        
        rtn = mockMvc.perform(get(endpoint + "json&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

        assertThat(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), "simplestation.json")),
        		sameJSONObjectAs(new JSONObject(getCompareFile("simpleStation.json"))));
    }

    @Test
    public void getAsXmlTest_HEAD() throws Exception {
        MvcResult rtn = mockMvc.perform(head(endpoint + "xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xml"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

    	assertEquals("", rtn.getResponse().getContentAsString());
    }
    
    @Test
    public void getAsXmlTest_GET() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xml"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

        assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }

    @Test
    public void getAsXmlZipTest_HEAD() throws Exception {
        MvcResult rtn = mockMvc.perform(head(endpoint + "xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

    	assertEquals("", rtn.getResponse().getContentAsString());
    }
    
    @Test
    public void getAsXmlZipTest_GET() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

        assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), "simplestation.xml")));
    }

    @Test
    public void kitchenSinkTest() throws Exception {
        when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

    	mockMvc.perform(
    		get(endpoint + "xml" +
    			"&analyticalmethod=https://www.nemi.gov/methods/method_summary/4665/;https://www.nemi.gov/methods/method_summary/8896/" + 
    			"bBox=-89;43;-88;44" +
    			"&assemblage=Fish/Nekton;Benthic Macroinvertebrates" +
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
    			"&subjectTaxonomicName=Acipenser;Lota lota" +
    			"&within=1000"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xml"))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null));
	
    }

}
