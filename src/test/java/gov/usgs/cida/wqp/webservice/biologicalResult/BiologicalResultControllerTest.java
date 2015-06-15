package gov.usgs.cida.wqp.webservice.biologicalResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import gov.usgs.cida.wqp.util.MybatisConstants;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
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
	@DatabaseSetup("classpath:/testData/result.xml")
})
public class BiologicalResultControllerTest extends BaseSpringTest implements HttpConstants {

	protected String endpoint = "/" + HttpConstants.RESULT_SEARCH_ENPOINT + "?" 
			+ Parameters.DATA_PROFILE + "=" + ValidationConstants.REGEX_DATA_PROFILE + "&mimeType=";
	
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
    public void getAsCsvHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "csv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.csv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.csv"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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
 
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsCsvGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "csv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.csv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.csv"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertEquals(getCompareFile("bioResult.csv"), rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsCsvZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "csv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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
 
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsCsvZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "csv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertEquals(getCompareFile("bioResult.csv"), extractZipContent(rtn.getResponse().getContentAsByteArray(), "biologicalresult.csv"));
    }


    @Test
    public void getAsTsvHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "tsv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.tsv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.tsv"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "tsv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.tsv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.tsv"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertEquals(getCompareFile("bioResult.tsv"), rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "tsv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "tsv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertEquals(getCompareFile("bioResult.tsv"), extractZipContent(rtn.getResponse().getContentAsByteArray(), "biologicalresult.tsv"));
    }

    
    @Test
    public void getAsXlsxHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "xlsx"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(MimeType.xlsx.getMimeType()))
    		.andExpect(content().encoding(DEFAULT_ENCODING))
    		.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.xlsx"))
    		.andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
    		.andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
    		.andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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
	
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
	}
  
    @Test
    public void getAsXlsxGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "xlsx"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MimeType.xlsx.getMimeType()))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.xlsx"))
			.andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
			.andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
			.andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
//    	assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }

    @Test
    public void getAsXlsxZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "xlsx&zip=yes"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(MIME_TYPE_ZIP))
    		.andExpect(content().encoding(DEFAULT_ENCODING))
    		.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
    		.andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
    		.andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
    		.andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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
	
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
	}
  
    @Test
    public void getAsXlsxZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "xlsx&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MIME_TYPE_ZIP))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
			.andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
			.andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
			.andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
//    	assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }

    

    @Test
    public void getAsXmlHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.xml.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.xml"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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
 
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsXmlGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.xml.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.xml"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertEquals(harmonizeXml(getCompareFile("bioResult.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }
    

    @Test
    public void getAsXmlZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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
 
    	assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsXmlZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
        assertEquals(harmonizeXml(getCompareFile("bioResult.xml")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), "biologicalresult.xml")));
    }
    
    @Test
    public void kitchenSinkTest() throws Exception {
        when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

    	MvcResult rtn = mockMvc.perform(
    		get(endpoint + "csv" +
    			"&analyticalmethod=https://www.nemi.gov/methods/method_summary/4665/;https://www.nemi.gov/methods/method_summary/8896/" + 
    			"bBox=-89;43;-88;44" +
    			"&assemblage=Fish/Nekton;Benthic Macroinvertebrates" +
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.csv"))
            .andExpect(header().string(HEADER_CORS_METHODS, HEADER_CORS_METHODS_VALUE))
            .andExpect(header().string(HEADER_CORS_MAX_AGE, HEADER_CORS_MAX_AGE_VALUE))
		    .andExpect(header().string(HEADER_CORS_ALLOW_HEADERS, HEADER_CORS_ALLOW_HEADERS_VALUE))
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

        assertEquals(acceptHeaders,	rtn.getResponse().getHeaderValues(HEADER_CORS_EXPOSE_HEADERS));
//        assertEquals(harmonizeXml(getCompareFile("simpleStation.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
	
    }

	
	private void addEntryStation(String ds, Integer en, List<Map<String, Object>>  list) {
		Map<String, Object> count = new HashMap<String, Object>();
		count.put(MybatisConstants.DATA_SOURCE,ds);
		count.put(MybatisConstants.STATION_COUNT,en);
		list.add(count);
	}
	
	private void addEntryResult(String ds, Integer en, List<Map<String, Object>>  list) {
		Map<String, Object> count = new HashMap<String, Object>();
		count.put(MybatisConstants.DATA_SOURCE,ds);
		count.put(MybatisConstants.STATION_COUNT,en);
		count.put(MybatisConstants.RESULT_COUNT,en*3);
		list.add(count);
	}

	@Test
	public void test_addResultHeaders_proper() {
		BiologicalResultController testController = new BiologicalResultController(null, null, null, null, 12, null);
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryStation("NWIS", 7, counts);
		addEntryStation("STEW", 5, counts);
		addEntryStation(null, 12, counts);
		addEntryResult("NWIS", 7, counts);
		addEntryResult("STEW", 5, counts);
		addEntryResult("Stor", 5, counts);
		addEntryResult(null, 12, counts);
		assertEquals(HEADER_TOTAL_RESULT_COUNT, testController.addCountHeaders(response, counts));
		assertEquals(8, response.getHeaderNames().size());
		String nwis = "NWIS"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(nwis));
		String stew = "STEW"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(stew));
		assertTrue(response.containsHeader(HEADER_TOTAL_SITE_COUNT));
		assertEquals("7", response.getHeader(nwis));
		assertEquals("5", response.getHeader(stew));
		assertEquals("12", response.getHeader(HEADER_TOTAL_SITE_COUNT));
	}

	@Test
	public void test_addResultHeaders_noTotal() {
		BiologicalResultController testController = new BiologicalResultController(null, null, null, null, 12, null);
		MockHttpServletResponse response = new MockHttpServletResponse();
		List<Map<String, Object>> counts = new ArrayList<Map<String, Object>>();
		addEntryResult("NWIS", 7, counts);
		addEntryResult("STEW", 5, counts);
		addEntryStation("NWIS", 7, counts);
		addEntryStation("STEW", 5, counts);
		assertEquals(HEADER_TOTAL_RESULT_COUNT, testController.addCountHeaders(response, counts));
		assertEquals(6, response.getHeaderNames().size());
		String nwis = "NWIS"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(nwis));
		String stew = "STEW"+HEADER_DELIMITER+HEADER_SITE_COUNT;
		assertTrue(response.containsHeader(stew));
		assertTrue(response.containsHeader(HEADER_TOTAL_SITE_COUNT));
		assertEquals("7", response.getHeader(nwis));
		assertEquals("5", response.getHeader(stew));
		assertEquals("0", response.getHeader(HEADER_TOTAL_SITE_COUNT));
	}

}
