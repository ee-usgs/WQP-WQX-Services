package gov.usgs.cida.wqp.webservice.SimpleStation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.IntegrationTest;
import gov.usgs.cida.wqp.util.CORSFilter;
import gov.usgs.cida.wqp.util.HttpConstants;

import java.io.IOException;

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
import com.github.springtestdbunit.annotation.DatabaseTearDown;

@Category(IntegrationTest.class)
@WebAppConfiguration
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/simpleStation.xml")
})
@DatabaseTearDown("classpath:/testData/clearAll.xml")
public class SimpleStationControllerTest extends BaseSpringTest implements HttpConstants {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
	protected CORSFilter filter;

    private MockMvc mockMvc;
    
    @Before
    public void setup() throws IOException {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilters(filter).build();
    }

    @Test
    public void getAsJsonTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/simplestation/search?mimeType=json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.json"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
        
        rtn = mockMvc.perform(get("/simplestation/search?mimeType=json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().encoding(DEFAULT_ENCODING))
                .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.json"))
                .andExpect(header().string("Total-Site-Count", "6"))
                .andExpect(header().string("NWIS-Site-Count", "2"))
                .andExpect(header().string("STEWARDS-Site-Count", "2"))
                .andExpect(header().string("STORET-Site-Count", "2"))
                .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
        		sameJSONObjectAs(new JSONObject(getCompareFile("simpleStation.json"))));
    }

    @Test
    public void getAsXmlTest() throws Exception {
        MvcResult rtn = mockMvc.perform(head("/simplestation/search?mimeType=xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xml"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
        
        rtn = mockMvc.perform(get("/simplestation/search?mimeType=xml"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(content().encoding(DEFAULT_ENCODING))
                .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xml"))
                .andExpect(header().string("Total-Site-Count", "6"))
                .andExpect(header().string("NWIS-Site-Count", "2"))
                .andExpect(header().string("STEWARDS-Site-Count", "2"))
                .andExpect(header().string("STORET-Site-Count", "2"))
                .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }

}
