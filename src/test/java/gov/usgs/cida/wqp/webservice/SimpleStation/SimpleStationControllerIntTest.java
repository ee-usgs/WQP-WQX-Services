package gov.usgs.cida.wqp.webservice.SimpleStation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

import java.net.URL;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerIntegrationTest;

@Category(DBIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class SimpleStationControllerIntTest extends BaseControllerIntegrationTest {

	public static final String NAME = "simpleStation";
	protected String endpoint = HttpConstants.SIMPLE_STATION_ENDPOINT + "?mimeType=";

	@Test
	public void getAsXmlTest() throws Exception {
		String url = endpoint + XML;
		String mimeType = HttpConstants.MIME_TYPE_XML;
		String fileType = XML;

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, getContentDisposition(NAME, fileType))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, getContentDisposition(NAME, fileType))).andReturn();
		assertEquals(harmonizeXml(getCompareFile(NAME, fileType, null)), harmonizeXml(rtn.getResponse().getContentAsString()));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, getContentDisposition(NAME, fileType))).andReturn();
		assertEquals(harmonizeXml(getCompareFile(NAME, fileType, "NoResult")), harmonizeXml(rtn.getResponse().getContentAsString()));
	}

	@Test
	public void getAsXmlZipTest() throws Exception {
		String url = endpoint + XML_AND_ZIP;
		String mimeType = HttpConstants.MIME_TYPE_ZIP;
		String fileType = XML;

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, getContentDisposition(NAME, "zip"))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, getContentDisposition(NAME, "zip"))).andReturn();
		assertEquals(harmonizeXml(getCompareFile(NAME, fileType, null)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), getFileName(NAME, fileType))));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, getContentDisposition(NAME, "zip"))).andReturn();
		assertEquals(harmonizeXml(getCompareFile(NAME, fileType, "NoResult")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), getFileName(NAME, fileType))));
	}

	@Test
	public void getAsGeoJsonTest() throws Exception {
		getAsJsonTest(endpoint + GEOJSON, HttpConstants.MIME_TYPE_GEOJSON, GEOJSON, NAME);
	}

	@Test
	public void getAsGeoJsonZipTest() throws Exception {
		getAsJsonZipTest(endpoint + GEOJSON_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, GEOJSON, NAME);
	}

	@Test
	public void getAsJsonTest() throws Exception {
		getAsJsonTest(endpoint + JSON, HttpConstants.MIME_TYPE_JSON, JSON, NAME);
	}

	@Test
	public void getAsJsonZipTest() throws Exception {
		getAsJsonZipTest(endpoint + JSON_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, JSON, NAME);
	}

	@Test
	public void getAllParametersTest() throws Exception {
		String url = endpoint + JSON;
		String mimeType = HttpConstants.MIME_TYPE_JSON;
		String fileType = JSON;

		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSitesAsSet());

		assertEquals("", filteredHeaderCheck(callMockHead(url + getUrlParameters(), mimeType, getContentDisposition(NAME, fileType))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = filteredHeaderCheck(callMockGet(url + getUrlParameters(), mimeType, getContentDisposition(NAME, fileType))).andReturn();
		assertThat(new JSONObject(getCompareFile(NAME, fileType, "Filtered")), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));
	}

	protected void getAsJsonTest(String url, String mimeType, String fileType, String name) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, getContentDisposition(name, fileType))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, getContentDisposition(name, fileType))).andReturn();
		assertThat(new JSONObject(getCompareFile(name, fileType, null)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, getContentDisposition(name, fileType))).andReturn();
		assertThat(new JSONObject(getCompareFile(name, fileType, "NoResult")), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));
	}

	protected void getAsJsonZipTest(String url, String mimeType, String fileType, String name) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, getContentDisposition(name, "zip"))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, getContentDisposition(name, "zip"))).andReturn();
		assertThat(new JSONObject(getCompareFile(name, fileType, null)), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), getFileName(name, fileType)))));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, getContentDisposition(name, "zip"))).andReturn();
		assertThat(new JSONObject(getCompareFile(name, fileType, "NoResult")), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), getFileName(name, fileType)))));
	}

	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_COUNT))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_COUNT))
				.andExpect(header().string(HEADER_BIODATA_SITE_COUNT, BIODATA_SITE_COUNT));
	}

	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, FILTERED_TOTAL_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, FILTERED_STORET_SITE_COUNT));
	}

	public ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, "0"));
	}

}
