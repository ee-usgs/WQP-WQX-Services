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

import java.io.IOException;
import java.net.URL;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.service.FetchService;
import gov.usgs.cida.wqp.service.FetchServiceTest;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.MimeType;

@Category(FullIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/result.xml")
})
public class BiologicalResultControllerIntTest extends BaseSpringTest implements HttpConstants {

	protected String endpoint = "/" + HttpConstants.RESULT_SEARCH_ENPOINT + "?" 
			+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL + "&mimeType=";

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private CodesService codesService;
	@Autowired
	private FetchService fetchService;

	@Autowired
	@Qualifier("TEST_NLDI_URL")
	protected URL testNldiUrl;

	private MockMvc mockMvc;
	
	@Before
	public void setup() throws WqpException, IOException {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		when(fetchService.fetch(anyString(), any(URL.class))).thenReturn(FetchServiceTest.NLDI_IDENTIFIERS);
	}

	@Test
	public void getAsCsvHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "csv"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MimeType.csv.getMimeType()))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.csv"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.csv"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
			.andReturn();

		assertEquals(getCompareFile("bioResult.csv"), rtn.getResponse().getContentAsString());
	}

	@Test
	public void getAsCsvZipHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "csv&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MIME_TYPE_ZIP))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
			.andReturn();

		assertEquals(getCompareFile("bioResult.csv"), extractZipContent(rtn.getResponse().getContentAsByteArray(), "biologicalresult.csv"));
	}

	@Test
	public void getAsTsvHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "tsv"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MimeType.tsv.getMimeType()))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.tsv"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.tsv"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
			.andReturn();

		assertEquals(getCompareFile("bioResult.tsv"), rtn.getResponse().getContentAsString());
	}

	@Test
	public void getAsTsvZipHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "tsv&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MIME_TYPE_ZIP))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
			.andReturn();

		assertEquals(getCompareFile("bioResult.tsv"), extractZipContent(rtn.getResponse().getContentAsByteArray(), "biologicalresult.tsv"));
	}

	
	@Test
	public void getAsXlsxHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "xlsx"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MimeType.xlsx.getMimeType()))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.xlsx"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.xlsx"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.xml"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.xml"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
			.andReturn();

		assertEquals(harmonizeXml(getCompareFile("bioResult.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
	}

	@Test
	public void getAsXmlZipHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "xml&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MIME_TYPE_ZIP))
			.andExpect(content().encoding(DEFAULT_ENCODING))
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andExpect(header().string("Total-Result-Count", "40"))
			.andExpect(header().string("NWIS-Result-Count", "12"))
			.andExpect(header().string("STEWARDS-Result-Count", "24"))
			.andExpect(header().string("STORET-Result-Count", "4"))
			.andReturn();

		assertEquals(harmonizeXml(getCompareFile("bioResult.xml")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), "biologicalresult.xml")));
	}

	@Test
	public void kitchenSinkTest() throws Exception {
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
				"&minresults=10000" +
				"&nldiurl=" + testNldiUrl +
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.csv"))
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
	public void postJsonGetAsCsvTest() throws Exception {
		mockMvc.perform(post(endpoint + "csv").content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MimeType.csv.getMimeType()))
				.andExpect(content().encoding(DEFAULT_ENCODING))
				.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.csv"))
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
	public void postTonOSitesTest() throws Exception {
		mockMvc.perform(post(endpoint + "csv").content(getSourceFile("manySites.json")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MimeType.csv.getMimeType()))
				.andExpect(content().encoding(DEFAULT_ENCODING))
				.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.csv"))
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
			.andExpect(header().string(HEADER_CONTENT_DISPOSITION, "attachment; filename=biologicalresult.csv"))
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
		MvcResult rtn = mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=json&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
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

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=csv&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=csv&zip=yes&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=tsv&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=tsv&zip=yes&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=xlsx&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=xlsx&zip=yes&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=xml&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=xml&zip=yes&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=kml&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=kml&zip=yes&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=kmz&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=geojson&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=geojson&zip=yes&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());
	}

	@Test
	public void postGetCountTonOSitesTest() throws Exception {
		MvcResult rtn = mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=json&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
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
