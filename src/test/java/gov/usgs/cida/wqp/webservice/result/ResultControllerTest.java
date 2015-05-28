package gov.usgs.cida.wqp.webservice.result;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.IntegrationTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.util.CORSFilter;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.MimeType;

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

@Category(IntegrationTest.class)
@WebAppConfiguration
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/pcResult.xml")
})
public class ResultControllerTest extends BaseSpringTest implements HttpConstants {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
	protected CORSFilter filter;
    
    @Autowired
    private CodesService codesService;

    private MockMvc mockMvc;
    
    @Before
    public void setup() throws IOException {
         mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilters(filter).build();
    }

    @Test
    public void getAsCsvHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Result/search?mimeType=csv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.csv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.csv"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();
 
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsCsvGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/Result/search?mimeType=csv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.csv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.csv"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertEquals(getCompareFile("pcResult.csv"), rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsCsvZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Result/search?mimeType=csv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();
 
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsCsvZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/Result/search?mimeType=csv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertEquals(getCompareFile("pcResult.csv"), extractZipContent(rtn.getResponse().getContentAsByteArray()));
    }


    @Test
    public void getAsTsvHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Result/search?mimeType=tsv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.tsv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.tsv"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/Result/search?mimeType=tsv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.tsv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.tsv"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertEquals(getCompareFile("pcResult.tsv"), rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Result/search?mimeType=tsv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/Result/search?mimeType=tsv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertEquals(getCompareFile("pcResult.tsv"), extractZipContent(rtn.getResponse().getContentAsByteArray()));
    }

    
    @Test
    public void getAsXlsxHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Result/search?mimeType=xlsx"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(MimeType.xlsx.getMimeType()))
    		.andExpect(content().encoding(DEFAULT_ENCODING))
    		.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.xlsx"))
    		.andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
    		.andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
    		.andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
    		.andExpect(header().string("Total-Site-Count", "6"))
    		.andExpect(header().string("NWIS-Site-Count", "2"))
    		.andExpect(header().string("STEWARDS-Site-Count", "2"))
    		.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
    		.andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
    		.andReturn();
	
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
	}
  
    @Test
    public void getAsXlsxGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/Result/search?mimeType=xlsx"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MimeType.xlsx.getMimeType()))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.xlsx"))
			.andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
			.andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
			.andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
			.andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
			.andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
//    	assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }

    @Test
    public void getAsXlsxZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Result/search?mimeType=xlsx&zip=yes"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(MIME_TYPE_ZIP))
    		.andExpect(content().encoding(DEFAULT_ENCODING))
    		.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
    		.andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
    		.andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
    		.andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
    		.andExpect(header().string("Total-Site-Count", "6"))
    		.andExpect(header().string("NWIS-Site-Count", "2"))
    		.andExpect(header().string("STEWARDS-Site-Count", "2"))
    		.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
    		.andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
    		.andReturn();
	
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
	}
  
    @Test
    public void getAsXlsxZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/Result/search?mimeType=xlsx&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MIME_TYPE_ZIP))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
			.andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
			.andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
			.andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
			.andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
			.andReturn();

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
//    	assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }

    

    @Test
    public void getAsXmlHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Result/search?mimeType=xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.xml.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.xml"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();
 
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsXmlGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/Result/search?mimeType=xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.xml.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.xml"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertEquals(harmonizeXml(getCompareFile("pcResult.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }
    

    @Test
    public void getAsXmlZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head("/Result/search?mimeType=xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();
 
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsXmlZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get("/Result/search?mimeType=xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
            .andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
        assertEquals(harmonizeXml(getCompareFile("pcResult.xml")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray())));
    }
    
    @Test
    public void kitchenSinkTest() throws Exception {
        when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

    	MvcResult rtn = mockMvc.perform(
    		get("/Result/search?mimeType=csv" +
    			"&analyticalmethod=https://www.nemi.gov/methods/method_summary/4665/;https://www.nemi.gov/methods/method_summary/8896/" + 
    			"bBox=-89;43;-88;44" +
    			"&characteristicName=Beryllium;Nitrate" +
    			"&characteristicType=Inorganics, Minor, Metals;Nutrient;Population/Community" + 
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
			.andExpect(content().contentType(MimeType.csv.getMimeType()))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.csv"))
            .andExpect(header().string(HttpConstants.HEADER_CORS_METHODS, HttpConstants.HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HttpConstants.HEADER_CORS_MAX_AGE, HttpConstants.HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HttpConstants.HEADER_CORS_ALLOW_HEADERS, HttpConstants.HEADER_CORS_ALLOW_HEADERS_VALUE))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null))
			.andExpect(header().string("Total-Result-Count", "0"))
			.andExpect(header().string("NWIS-Result-Count", (String)null))
			.andExpect(header().string("STEWARDS-Result-Count", (String)null))
			.andExpect(header().string("STORET-Result-Count", (String)null))
            .andExpect(header().string(HEADER_CORS, HEADER_CORS_VALUE))
			.andReturn();

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues("Access-Control-Expose-Headers"));
//        assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
	
    }

}
