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

	protected String endpoint = "/" + HttpConstants.SIMPLE_STATION_ENDPOINT + "?mimeType=";

	@Test
	public void getAsXmlTest() throws Exception {
		String url = endpoint + "xml";
		String mimeType =  HttpConstants.MIME_TYPE_XML;
		String contentDisposition = "attachment; filename=simplestation.xml";
		String compareFile = "station/simplestation.xml";

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(rtn.getResponse().getContentAsString()));
	}

	@Test
	public void getAsXmlZipTest() throws Exception {
		String url = endpoint + "xml&zip=yes";
		String mimeType =  HttpConstants.MIME_TYPE_ZIP;
		String contentDisposition = "attachment; filename=simplestation.zip";
		String compareFile = "station/simplestation.xml";
		String zipEntry = "simplestation.xml";

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));
	}

	@Test
	public void getAsGeoJsonTest() throws Exception {
		String url = endpoint + "geojson";
		String mimeType =  HttpConstants.MIME_TYPE_GEOJSON;
		String contentDisposition = "attachment; filename=simplestation.geojson";
		String compareFile = "station/station.json";

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));

	}

	@Test
	public void getAsGeoJsonZipTest() throws Exception {
		String url = endpoint + "geojson&zip=yes";
		String mimeType =  HttpConstants.MIME_TYPE_ZIP;
		String contentDisposition = "attachment; filename=simplestation.zip";
		String compareFile = "station/station.json";
		String zipEntry = "simplestation.geojson";

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry))));
	}

	@Test
	public void getAsJsonTest() throws Exception {
		String url = endpoint + "json";
		String mimeType =  HttpConstants.MIME_TYPE_JSON;
		String contentDisposition = "attachment; filename=simplestation.json";
		String compareFile = "station/station.json";

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));
	}

	@Test
	public void getAsJsonZipTest() throws Exception {
		String url = endpoint + "json&zip=yes";
		String mimeType =  HttpConstants.MIME_TYPE_ZIP;
		String contentDisposition = "attachment; filename=simplestation.zip";
		String compareFile = "station/station.json";
		String zipEntry = "simplestation.json";

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry))));
	}

	@Test
	public void getAllParametersTest() throws Exception {
		String url = endpoint + "json";
		String mimeType =  HttpConstants.MIME_TYPE_JSON;
		String contentDisposition = "attachment; filename=simplestation.json";
		String compareFile = "station/filteredStation.json";

		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSitesAsSet());

		assertEquals("", filteredHeaderCheck(callMockHead(url + getUrlParameters(), mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = filteredHeaderCheck(callMockGet(url + getUrlParameters(), mimeType, contentDisposition)).andReturn();
		assertThat(new JSONObject(getCompareFile(compareFile)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));
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

}
