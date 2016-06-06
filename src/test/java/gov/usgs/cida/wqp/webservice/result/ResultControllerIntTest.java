package gov.usgs.cida.wqp.webservice.result;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
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
import gov.usgs.cida.wqp.util.MimeType;

@Category(FullIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/result.xml")
})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class ResultControllerIntTest extends BaseSpringTest implements HttpConstants {

	protected String endpoint = "/" + HttpConstants.RESULT_SEARCH_ENPOINT + "?mimeType=";
	
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CodesService codesService;

	@Mock
	HttpServletResponse response;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

    @Test
    public void getAsCsvHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "csv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.csv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.csv"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();
 
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsCsvGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "csv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.csv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.csv"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();

        assertEquals(getCompareFile("pcResult.csv"), rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsCsvZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "csv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();
 
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsCsvZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "csv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();

        assertEquals(getCompareFile("pcResult.csv"), extractZipContent(rtn.getResponse().getContentAsByteArray(), "result.csv"));
    }


    @Test
    public void getAsTsvHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "tsv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.tsv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.tsv"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();

    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "tsv"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.tsv.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.tsv"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();

        assertEquals(getCompareFile("pcResult.tsv"), rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "tsv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();

    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsTsvZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "tsv&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();

        assertEquals(getCompareFile("pcResult.tsv"), extractZipContent(rtn.getResponse().getContentAsByteArray(), "result.tsv"));
    }

    
    @Test
    public void getAsXlsxHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "xlsx"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(MimeType.xlsx.getMimeType()))
    		.andExpect(content().encoding(DEFAULT_ENCODING))
    		.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.xlsx"))
    		.andExpect(header().string("Total-Site-Count", "6"))
    		.andExpect(header().string("NWIS-Site-Count", "2"))
    		.andExpect(header().string("STEWARDS-Site-Count", "2"))
    		.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
    		.andReturn();
	
    	assertEquals("", rtn.getResponse().getContentAsString());
	}
  
    @Test
    public void getAsXlsxGetTest() throws Exception {
    	mockMvc.perform(get(endpoint + "xlsx"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MimeType.xlsx.getMimeType()))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.xlsx"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"));
    }

    @Test
    public void getAsXlsxZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "xlsx&zip=yes"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType(MIME_TYPE_ZIP))
    		.andExpect(content().encoding(DEFAULT_ENCODING))
    		.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
    		.andExpect(header().string("Total-Site-Count", "6"))
    		.andExpect(header().string("NWIS-Site-Count", "2"))
    		.andExpect(header().string("STEWARDS-Site-Count", "2"))
    		.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
    		.andReturn();
	
    	assertEquals("", rtn.getResponse().getContentAsString());
	}
  
    @Test
    public void getAsXlsxZipGetTest() throws Exception {
    	mockMvc.perform(get(endpoint + "xlsx&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MIME_TYPE_ZIP))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"));
    }

    

    @Test
    public void getAsXmlHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.xml.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.xml"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();
 
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsXmlGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "xml"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.xml.getMimeType()))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.xml"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();

        assertEquals(harmonizeXml(getCompareFile("pcResult.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
    }
    

    @Test
    public void getAsXmlZipHeadTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(head(endpoint + "xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();
 
    	assertEquals("", rtn.getResponse().getContentAsString());
    }

    @Test
    public void getAsXmlZipGetTest() throws Exception {
    	MvcResult rtn = mockMvc.perform(get(endpoint + "xml&zip=yes"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MIME_TYPE_ZIP))
            .andExpect(content().encoding(DEFAULT_ENCODING))
            .andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.zip"))
            .andExpect(header().string("Total-Site-Count", "6"))
            .andExpect(header().string("NWIS-Site-Count", "2"))
            .andExpect(header().string("STEWARDS-Site-Count", "2"))
            .andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
            .andReturn();

        assertEquals(harmonizeXml(getCompareFile("pcResult.xml")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), "result.xml")));
    }
    
    @Test
    public void kitchenSinkTest() throws Exception {
        when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

    	mockMvc.perform(
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
    			"&subjectTaxonomicName=Acipenser;Lota lota" +
    			"&within=1000"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MimeType.csv.getMimeType()))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.csv"))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null))
			.andExpect(header().string("Total-Result-Count", "0"))
			.andExpect(header().string("NWIS-Result-Count", (String)null))
			.andExpect(header().string("STEWARDS-Result-Count", (String)null))
			.andExpect(header().string("STORET-Result-Count", (String)null))
			.andReturn();
	
    }

    @Test
    public void postJsonGetAsCsvTest() throws Exception {
        when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

    	mockMvc.perform(post(endpoint + "csv").content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType(MimeType.csv.getMimeType()))
    			.andExpect(content().encoding(DEFAULT_ENCODING))
    			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.csv"))
    			.andExpect(header().string("Total-Site-Count", "0"))
    			.andExpect(header().string("NWIS-Site-Count", (String)null))
    			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
    			.andExpect(header().string("STORET-Site-Count", (String)null))
    			.andExpect(header().string("Total-Result-Count", "0"))
    			.andExpect(header().string("NWIS-Result-Count", (String)null))
    			.andExpect(header().string("STEWARDS-Result-Count", (String)null))
    			.andExpect(header().string("STORET-Result-Count", (String)null))
    			.andReturn();
    }

	@Test
	public void postTonOSitesTest() throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

		mockMvc.perform(post(endpoint + "csv").content(getSourceFile("manySites.json")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MimeType.csv.getMimeType()))
				.andExpect(content().encoding(DEFAULT_ENCODING))
				.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.csv"))
				.andExpect(header().string("Total-Site-Count", "0"))
				.andExpect(header().string("NWIS-Site-Count", (String)null))
				.andExpect(header().string("STEWARDS-Site-Count", (String)null))
				.andExpect(header().string("STORET-Site-Count", (String)null))
				.andExpect(header().string("Total-Result-Count", "0"))
				.andExpect(header().string("NWIS-Result-Count", (String)null))
    			.andExpect(header().string("STEWARDS-Result-Count", (String)null))
    			.andExpect(header().string("STORET-Result-Count", (String)null));
	}

    @Test
    public void postFormUrlencodedGetAsCsvTest() throws Exception {
        when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

        //Note that in real life the values are urlencoded - This mock does not un-encode them, so we must use real values.
    	mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED)
    			.param("mimeType", "csv")
    			.param("countrycode", "US")
    			.param("statecode", "US:05")
    			.param("countycode", "US:05:007")
    			.param("countycode", "US:05:008"))
    		.andExpect(status().isOk())
			.andExpect(content().contentType(MimeType.csv.getMimeType()))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=result.csv"))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null))
			.andExpect(header().string("Total-Result-Count", "0"))
			.andExpect(header().string("NWIS-Result-Count", (String)null))
			.andExpect(header().string("STEWARDS-Result-Count", (String)null))
			.andExpect(header().string("STORET-Result-Count", (String)null));
    }

    @Test
    public void postGetCountTest() throws Exception {
        when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

		MvcResult rtn = mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=json").content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MIME_TYPE_JSON))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, (String)null))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null))
			.andReturn();

        assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
        		sameJSONObjectAs(new JSONObject("{\"Total-Site-Count\":\"0\", \"Total-Result-Count\":\"0\"}")));
        
        
    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=csv")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=csv&zip=yes")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=tsv")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=tsv&zip=yes")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=xlsx")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=xlsx&zip=yes")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=xml")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=xml&zip=yes")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=kml")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=kml&zip=yes")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=kmz")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=geojson")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    	mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=geojson&zip=yes")
    			.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

    }

	@Test
	public void postGetCountTonOSitesTest() throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

		MvcResult rtn = mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=json")
				.content(getSourceFile("manySites.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MIME_TYPE_JSON))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, (String)null))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null))
			.andReturn();

		assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
				sameJSONObjectAs(new JSONObject("{\"Total-Site-Count\":\"0\", \"Total-Result-Count\":\"0\"}")));
		
	}

}
