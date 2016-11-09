package gov.usgs.cida.wqp.webservice;

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

import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.service.FetchService;
import gov.usgs.cida.wqp.util.HttpConstants;

public abstract class BaseControllerIntegrationTest extends BaseSpringTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	protected CodesService codesService;
	@Autowired
	protected FetchService fetchService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Autowired
	@Qualifier("TEST_NLDI_URL")
	protected URL testNldiUrl;

	public String getUrlParameters() {
		return
			//TODO				"&analyticalmethod=https://www.nemi.gov/methods/method_summary/4665/;https://www.nemi.gov/methods/method_summary/8896/" + 
			"&bBox=-90,43,-88,44" +
			//TODO				"&assemblage=Fish/Nekton;Benthic Macroinvertebrates" +
			//TODO				"&characteristicName=Beryllium;Nitrate" +
			//TODO				"&characteristicType=Inorganics, Minor, Metals;Nutrient;Population/Community" + 
			"&command.avoid=NWIS" + 
			"&countrycode=MX;US;XX" + 
			"&countycode=US:19:015;US:30:003;US:55:017;US:55:021;US:55:027;XX:44:555" +
			"&huc=07*;0708*;070801*;07090002;07080105;0000" + 
			"&lat=43.3836014" +
			"&long=-88.9773314" + 
			//TODO				"&minresults=10000" +
			"&nldiurl=" + testNldiUrl +
			"&organization=ARS;11NPSWRD;USGS-WI;WIDNR_WQX;organization" + 
			//TODO				"&pCode=00032;00004" +
			"&project=projectId;CEAP;NAWQA" +
			"&sampleMedia=sampleMedia;Other;Sediment;Water" +
			"&providers=NWIS;STEWARDS;STORET" +
			"&siteid=organization-siteId;11NPSWRD-BICA_MFG_B;WIDNR_WQX-10030952;USGS-05425700;USGS-431925089002701;ARS-IAWC-IAWC225;ARS-IAWC-IAWC410" +
			"&siteType=siteType;Lake, Reservoir, Impoundment;Land;Stream;Well" + 
			"&statecode=XX:44;US:19;US:30;US:55" + 
			"&startDateHi=10-11-2012" +
			"&startDateLo=10-11-1998" +
			//TODO				"&subjectTaxonomicName=Acipenser;Lota lota" +
			"&within=1000"
			;
	}

	protected void getAsDelimitedTest(String url, String mimeType, String contentDisposition, String compareFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());
	}

	protected void getAsDelimitedZipTest(String url, String mimeType, String contentDisposition, String compareFile, String zipEntry) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry));

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry));

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry));
	}

	protected void getAsXlsxTest(String url, String mimeType, String contentDisposition) throws Exception {
		//TODO validate spreadsheet and split out zipped.
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition));

		unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition));

		unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition));
	}

	protected void getAsXmlTest(String url, String mimeType, String contentDisposition, String compareFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(rtn.getResponse().getContentAsString()));

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(rtn.getResponse().getContentAsString()));

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(rtn.getResponse().getContentAsString()));
	}

	protected void getAsXmlZipTest(String url, String mimeType, String contentDisposition, String compareFile, String zipEntry) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));
	}

	protected void getAllParametersTest(String url, String mimeType, String contentDisposition, String compareFile) throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(Stream.of("a", "b", "organization-siteId").collect(Collectors.toSet()));

		assertEquals("", filteredHeaderCheck(callMockHead(url + getUrlParameters(), mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = filteredHeaderCheck(callMockGet(url + getUrlParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = filteredHeaderCheck(callMockPostJson(url, getSourceFile("postParameters.json"), mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = filteredHeaderCheck(callMockPostFormFiltered(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());
	}

	public void postGetCountTest(String urlPrefix, String compareObject) throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(Stream.of("a", "b", "organization-siteId").collect(Collectors.toSet()));

		MvcResult rtn = filteredHeaderCheck(callMockPostJson(urlPrefix + "json", getSourceFile("postParameters.json"), HttpConstants.MIME_TYPE_JSON, null))
			.andReturn();
	
		assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
				sameJSONObjectAs(new JSONObject(compareObject)));
	
		callMockPostJsonBadRequest(urlPrefix+ "csv");
		callMockPostJsonBadRequest(urlPrefix+ "csv&zip=yes");
		callMockPostJsonBadRequest(urlPrefix+ "tsv");
		callMockPostJsonBadRequest(urlPrefix+ "tsv&zip=yes");
		callMockPostJsonBadRequest(urlPrefix+ "xlsx");
		callMockPostJsonBadRequest(urlPrefix+ "xlsx&zip=yes");
		callMockPostJsonBadRequest(urlPrefix+ "xml");
		callMockPostJsonBadRequest(urlPrefix+ "xml&zip=yes");
		callMockPostJsonBadRequest(urlPrefix+ "kml");
		callMockPostJsonBadRequest(urlPrefix+ "kml&zip=yes");
		callMockPostJsonBadRequest(urlPrefix+ "kmz");
		callMockPostJsonBadRequest(urlPrefix+ "geojson");
		callMockPostJsonBadRequest(urlPrefix+ "geojson&zip=yes");
	}

	public ResultActions callMockHead(String url, String mimeType, String contentDisposition) throws Exception {
		return mockMvc.perform(head(url))
				.andExpect(status().isOk())
				.andExpect(content().contentType(mimeType))
				.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, contentDisposition));
	}

	public ResultActions callMockGet(String url, String mimeType, String contentDisposition) throws Exception {
		return mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().contentType(mimeType))
				.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, contentDisposition));
	}

	public ResultActions callMockPostJson(String url, String json, String mimeType, String contentDisposition) throws Exception {
		return mockMvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(mimeType))
				.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, contentDisposition));
	}

	public ResultActions callMockPostForm(String url, String mimeType, String contentDisposition) throws Exception {
		return mockMvc.perform(post(url).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk())
				.andExpect(content().contentType(mimeType))
				.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, contentDisposition));
	}

	public ResultActions callMockPostFormFiltered(String url, String mimeType, String contentDisposition) throws Exception {
		return mockMvc.perform(addFilters(post(url).contentType(MediaType.APPLICATION_FORM_URLENCODED)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(mimeType))
				.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, contentDisposition));
	}

	public ResultActions callMockPostJsonBadRequest(String url) throws Exception {
		return mockMvc.perform(post(url)
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable());
	}

	public MockHttpServletRequestBuilder addFilters(MockHttpServletRequestBuilder requestBase) {
		return requestBase
				.param("analyticalmethod", "https://www.nemi.gov/methods/method_summary/4665/", "https://www.nemi.gov/methods/method_summary/8896/")
				.param("bBox", "-90,43,-88,44")
				.param("assemblage", "Fish/Nekton", "Benthic Macroinvertebrates")
				.param("characteristicName", "Beryllium", "Nitrate")
				.param("characteristicType", "Inorganics, Minor, Metals", "Nutrient", "Population/Community")
				.param("command.avoid", "NWIS")
				.param("countrycode", "MX", "US", "XX")
				.param("countycode", "US:19:015", "US:30:003", "US:55:017", "US:55:021", "US:55:027", "XX:44:555")
				.param("huc", "07*", "0708*", "070801*", "07090002", "07080105", "0000")
				.param("lat", "43.3836014")
				.param("long", "-88.9773314")
				.param("minresults", "10000")
				.param("nldiurl", "" + testNldiUrl)
				.param("organization", "ARS", "11NPSWRD", "USGS-WI", "WIDNR_WQX", "organization")
				.param("pCode", "00032", "00004")
				.param("project", "projectId", "CEAP", "NAWQA")
				.param("sampleMedia", "sampleMedia", "Other", "Sediment", "Water")
				.param("providers", "NWIS", "STEWARDS", "STORET")
				.param("siteid", "organization-siteId", "11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700", "USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410")
				.param("siteType", "siteType", "Lake, Reservoir, Impoundment", "Land", "Stream", "Well")
				.param("statecode", "XX:44", "US:19", "US:30", "US:55")
				.param("startDateHi", "10-11-2012")
				.param("startDateLo", "10-11-1998")
				.param("subjectTaxonomicName", "Acipenser", "Lota lota")
				.param("within", "1000");
	}

	public abstract ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception;

	public abstract ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception;
}
