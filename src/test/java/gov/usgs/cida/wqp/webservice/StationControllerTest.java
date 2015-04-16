package gov.usgs.cida.wqp.webservice;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.IntegrationTest;
import gov.usgs.cida.wqp.util.CORSFilter;
import gov.usgs.cida.wqp.util.HttpConstants;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
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
	@DatabaseSetup("classpath:/testData/station.xml")
})
@DatabaseTearDown("classpath:/testData/clearAll.xml")
public class StationControllerTest extends BaseSpringTest implements HttpConstants {

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
    public void getAsCsvTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Station/search?mimeType=csv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_TEXT_CSV))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            //TODO - this is wrong - should be station.csv
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.csv"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
        
        rtn = mockMvc.perform(get("/Station/search?mimeType=csv"))
        	.andExpect(status().isOk())
        	.andExpect(content().contentType(MIME_TYPE_TEXT_CSV))
        	.andExpect(content().encoding(DEFAULT_ENCODING))
            //TODO - this is wrong - should be station.csv
        	.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.csv"))
        	.andExpect(header().string("Total-Site-Count", "6"))
        	.andExpect(header().string("NWIS-Site-Count", "2"))
        	.andExpect(header().string("STEWARDS-Site-Count", "2"))
        	.andExpect(header().string("STORET-Site-Count", "2"))
        	.andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertEquals(getCompareFile("station.csv"), rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvTest() throws Exception {
        MvcResult rtn = mockMvc.perform(head("/Station/search?mimeType=tsv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_TEXT_TSV))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            //TODO - this is wrong - should be station.tsv
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.tsv"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
        
        rtn = mockMvc.perform(get("/Station/search?mimeType=tsv"))
        	.andExpect(status().isOk())
        	.andExpect(content().contentType(MIME_TYPE_TEXT_TSV))
        	.andExpect(content().encoding(DEFAULT_ENCODING))
            //TODO - this is wrong - should be station.tsv
        	.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.tsv"))
        	.andExpect(header().string("Total-Site-Count", "6"))
        	.andExpect(header().string("NWIS-Site-Count", "2"))
        	.andExpect(header().string("STEWARDS-Site-Count", "2"))
        	.andExpect(header().string("STORET-Site-Count", "2"))
        	.andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertEquals(getCompareFile("station.tsv"), rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsXlsxTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Station/search?mimeType=xlsx"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(MIME_TYPE_XLSX))
    		.andExpect(content().encoding(DEFAULT_ENCODING))
    		//TODO - this is wrong - should be station.tsv
    		.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xlsx"))
    		.andExpect(header().string("Total-Site-Count", "6"))
    		.andExpect(header().string("NWIS-Site-Count", "2"))
    		.andExpect(header().string("STEWARDS-Site-Count", "2"))
    		.andExpect(header().string("STORET-Site-Count", "2"))
    		.andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
    	
    	rtn = mockMvc.perform(get("/Station/search?mimeType=xlsx"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(MIME_TYPE_XLSX))
    		.andExpect(content().encoding(DEFAULT_ENCODING))
    		//TODO - this is wrong - should be station.tsv
    		.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=simplestation.xlsx"))
    		.andExpect(header().string("Total-Site-Count", "6"))
    		.andExpect(header().string("NWIS-Site-Count", "2"))
    		.andExpect(header().string("STEWARDS-Site-Count", "2"))
    		.andExpect(header().string("STORET-Site-Count", "2"))
    		.andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	//TODO an actual compare - perhaps using     https://github.com/tobyweston/simple-excel
//    	assertEquals(getCompareFile("station.tsv"), rtn.getResponse().getContentAsString());
    }
}
