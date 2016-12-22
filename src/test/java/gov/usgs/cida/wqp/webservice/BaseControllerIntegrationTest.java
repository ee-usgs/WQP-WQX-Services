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
import java.util.Arrays;
import java.util.HashSet;

import org.json.JSONObject;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.exception.WqpException;
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
		try {
			when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		} catch (WqpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUrlParameters() {
		return
			"&" + Parameters.ANALYTICAL_METHOD.toString() + "=" + String.join(";", getAnalyticalMethod()) +
			"&" + Parameters.AVOID.toString() + "=" + String.join(";", getAvoid()) + 
			"&" + Parameters.ASSEMBLAGE.toString() + "=" + String.join(";", getAssemblage()) +
			"&" + Parameters.BBOX.toString() + "=" + String.join(",", getBBox()) +
			"&" + Parameters.CHARACTERISTIC_NAME.toString() + "=" + String.join(";", getCharacteristicName()) +
			"&" + Parameters.CHARACTERISTIC_TYPE.toString() + "=" + String.join(";", getCharacteristicType()) +
			"&" + Parameters.COUNTRY.toString() + "=" + String.join(";", getCountry()) +
			"&" + Parameters.COUNTY.toString() + "=" + String.join(";", getCounty()) +
			"&" + Parameters.HUC.toString() + "=" + String.join(";", getHuc()) +
			"&" + Parameters.LATITUDE.toString() + "=" + String.join(";", getLatitude()) +
			"&" + Parameters.LONGITUDE.toString() + "=" + String.join(";", getLongitude()) +
			"&" + Parameters.MIN_ACTIVITIES.toString() + "=" + String.join(";", getMinActivities()) +
			"&" + Parameters.MIN_RESULTS.toString() + "=" + String.join(";", getMinResults()) +
			"&" + Parameters.NLDIURL.toString() + "=" + String.join(";", getNldiurl()) +
			"&" + Parameters.ORGANIZATION.toString() + "=" + String.join(";", getOrganization()) +
			"&" + Parameters.PCODE.toString() + "=" + String.join(";", getPcode()) +
			"&" + Parameters.PROJECT.toString() + "=" + String.join(";", getProject()) +
			"&" + Parameters.PROVIDERS.toString() + "=" + String.join(";", getProviders()) +
			"&" + Parameters.SAMPLE_MEDIA.toString() + "=" + String.join(";", getSampleMedia()) +
			"&" + Parameters.SITEID.toString() + "=" + String.join(";", getSiteid()) +
			"&" + Parameters.SITE_TYPE.toString() + "=" + String.join(";", getSiteType()) +
			"&" + Parameters.START_DATE_HI.toString() + "=" + String.join(";", getStartDateHi()) +
			"&" + Parameters.START_DATE_LO.toString() + "=" + String.join(";", getStartDateLo()) +
			"&" + Parameters.STATE.toString() + "=" + String.join(";", getState()) +
			"&" + Parameters.SUBJECT_TAXONOMIC_NAME.toString() + "=" + String.join(";", getSubjectTaxonomicName()) +
			"&" + Parameters.WITHIN.toString() + "=" + String.join(";", getWithin())
			;
	}

	public String getNoResultParameters() {
		return "&" + Parameters.COUNTRY.toString() + "=DS";
	}

	protected void getAsDelimitedTest(String url, String mimeType, String contentDisposition, String compareFile, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(noResultFile), rtn.getResponse().getContentAsString());
	}

	protected void getAsDelimitedZipTest(String url, String mimeType, String contentDisposition, String compareFile, String zipEntry, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry));

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry));

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(noResultFile), extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry));
	}

	protected void getAsXlsxTest(String url, String mimeType, String contentDisposition) throws Exception {
		//TODO validate spreadsheet and split out zipped.
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition));

		unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition));

		unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition));
	}

	protected void getAsXmlTest(String url, String mimeType, String contentDisposition, String compareFile, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(rtn.getResponse().getContentAsString()));

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(rtn.getResponse().getContentAsString()));

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(rtn.getResponse().getContentAsString()));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(noResultFile)), harmonizeXml(rtn.getResponse().getContentAsString()));
	}

	protected void getAsXmlZipTest(String url, String mimeType, String contentDisposition, String compareFile, String zipEntry, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(noResultFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));
	}

	protected void getAsJsonTest(String url, String mimeType, String contentDisposition, String compareFile, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(noResultFile)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));
	}

	protected void getAsJsonZipTest(String url, String mimeType, String contentDisposition, String compareFile, String zipEntry, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry))));

		rtn = unFilteredHeaderCheck(callMockPostJson(url, "{}", mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry))));

		rtn = unFilteredHeaderCheck(callMockPostForm(url, mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry))));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(noResultFile)), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry))));
	}

	protected void getAllParametersTest(String url, String mimeType, String contentDisposition, String compareFile) throws Exception {
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSitesAsSet());

		assertEquals("", filteredHeaderCheck(callMockHead(url + getUrlParameters(), mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = filteredHeaderCheck(callMockGet(url + getUrlParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = filteredHeaderCheck(callMockPostJson(url, getSourceFile("postParameters.json"), mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = filteredHeaderCheck(callMockPostFormFiltered(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());
	}

	public void postGetCountTest(String urlPrefix, String compareObject) throws Exception {
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(new HashSet<String>(Arrays.asList(getNldiSites())));

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
				.param(Parameters.ANALYTICAL_METHOD.toString(), String.join(";", getAnalyticalMethod()))
				.param(Parameters.BBOX.toString(), String.join(",", getBBox()))
				.param(Parameters.ASSEMBLAGE.toString(), String.join(";", getAssemblage()))
				.param(Parameters.CHARACTERISTIC_NAME.toString(), String.join(";", getCharacteristicName()))
				.param(Parameters.CHARACTERISTIC_TYPE.toString(), String.join(";", getCharacteristicType()))
				.param(Parameters.AVOID.toString(), String.join(";", getAvoid()))
				.param(Parameters.COUNTRY.toString(), String.join(";", getCountry()))
				.param(Parameters.COUNTY.toString(), String.join(";", getCounty()))
				.param(Parameters.HUC.toString(), String.join(";", getHuc()))
				.param(Parameters.LATITUDE.toString(), String.join(";", getLatitude()))
				.param(Parameters.LONGITUDE.toString(), String.join(";", getLongitude()))
				.param(Parameters.MIN_ACTIVITIES.toString(), String.join(";", getMinActivities()))
				.param(Parameters.MIN_RESULTS.toString(), String.join(";", getMinResults()))
				.param(Parameters.NLDIURL.toString(), String.join(";", getNldiurl()))
				.param(Parameters.ORGANIZATION.toString(), String.join(";", getOrganization()))
				.param(Parameters.PCODE.toString(), String.join(";", getPcode()))
				.param(Parameters.PROJECT.toString(), String.join(";", getProject()))
				.param(Parameters.SAMPLE_MEDIA.toString(), String.join(";", getSampleMedia()))
				.param(Parameters.PROVIDERS.toString(), String.join(";", getProviders()))
				.param(Parameters.SITEID.toString(), String.join(";", getSiteid()))
				.param(Parameters.SITE_TYPE.toString(), String.join(";", getSiteType()))
				.param(Parameters.STATE.toString(), String.join(";", getState()))
				.param(Parameters.START_DATE_HI.toString(), String.join(";", getStartDateHi()))
				.param(Parameters.START_DATE_LO.toString(), String.join(";", getStartDateLo()))
				.param(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), String.join(";", getSubjectTaxonomicName()))
				.param(Parameters.WITHIN.toString(), String.join(";", getWithin()))
				;
	}

	public abstract ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception;

	public abstract ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception;

	public abstract ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception;
}
