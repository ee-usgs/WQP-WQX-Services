package gov.usgs.cida.wqp.webservice.SimpleStation;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_BIODATA_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_STEWARDS_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_STORET_SITE_COUNT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

import java.net.URL;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.Application;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerIntegrationTest;

@EnableWebMvc
@AutoConfigureMockMvc(secure=false)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK,
	classes={DBTestConfig.class, Application.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class SimpleStationControllerDbIT extends BaseControllerIntegrationTest {

	protected static final Profile PROFILE = Profile.SIMPLE_STATION;
	protected static final String ENDPOINT = HttpConstants.SIMPLE_STATION_ENDPOINT + "?mimeType=";

	@Test
	public void testHarness() throws Exception {
		getAsXmlTest();
		getAsXmlZipTest();
		getAsGeoJsonTest();
		getAsGeoJsonZipTest();
		getAllParametersTest();
	}

	public void getAsXmlTest() throws Exception {
		String url = ENDPOINT + XML;
		String mimeType = HttpConstants.MIME_TYPE_XML;
		String fileType = XML;

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, getContentDisposition(PROFILE, fileType))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, getContentDisposition(PROFILE, fileType))).andReturn();
		assertEquals(harmonizeXml(getCompareFile(PROFILE, fileType, null)), harmonizeXml(rtn.getResponse().getContentAsString()));

		rtn = noResultHeaderCheck(callMockGet(getNoResultParameters(url), mimeType, getContentDisposition(PROFILE, fileType))).andReturn();
		assertEquals(harmonizeXml(getCompareFile(PROFILE, fileType, "NoResult")), harmonizeXml(rtn.getResponse().getContentAsString()));
	}

	public void getAsXmlZipTest() throws Exception {
		String url = ENDPOINT + XML_AND_ZIP;
		String mimeType = HttpConstants.MIME_TYPE_ZIP;
		String fileType = XML;

		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, getContentDisposition(PROFILE, "zip"))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, getContentDisposition(PROFILE, "zip"))).andReturn();
		assertEquals(harmonizeXml(getCompareFile(PROFILE, fileType, null)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), getFileName(PROFILE, fileType))));

		rtn = noResultHeaderCheck(callMockGet(getNoResultParameters(url), mimeType, getContentDisposition(PROFILE, "zip"))).andReturn();
		assertEquals(harmonizeXml(getCompareFile(PROFILE, fileType, "NoResult")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), getFileName(PROFILE, fileType))));
	}

	public void getAsGeoJsonTest() throws Exception {
		getAsJsonTest(ENDPOINT + GEOJSON, HttpConstants.MIME_TYPE_GEOJSON, GEOJSON);
	}

	public void getAsGeoJsonZipTest() throws Exception {
		getAsJsonZipTest(ENDPOINT + GEOJSON_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, GEOJSON);
	}

	public void getAsJsonTest() throws Exception {
		getAsJsonTest(ENDPOINT + JSON, HttpConstants.MIME_TYPE_JSON, JSON);
	}

	public void getAsJsonZipTest() throws Exception {
		getAsJsonZipTest(ENDPOINT + JSON_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, JSON);
	}

	public void getAllParametersTest() throws Exception {
		String url = ENDPOINT + JSON;
		String mimeType = HttpConstants.MIME_TYPE_JSON;
		String fileType = JSON;

		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSites());

		assertEquals("", filteredHeaderCheck(callMockHead(url + getUrlParameters(), mimeType, getContentDisposition(PROFILE, fileType))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = filteredHeaderCheck(callMockGet(url + getUrlParameters(), mimeType, getContentDisposition(PROFILE, fileType))).andReturn();
		assertThat(new JSONObject(getCompareFile(PROFILE, fileType, "Filtered")), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));
	}

	protected void getAsJsonTest(String url, String mimeType, String fileType) throws Exception {
		assertEquals("", unFilteredGeomHeaderCheck(callMockHead(url, mimeType, getContentDisposition(PROFILE, fileType))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredGeomHeaderCheck(callMockGet(url, mimeType, getContentDisposition(PROFILE, fileType))).andReturn();
		
		JSONObject debug_JSONObject_compare = new JSONObject(getCompareFile(PROFILE, fileType, null));
		JSONObject debug_JSONObject_actual = new JSONObject(rtn.getResponse().getContentAsString());
		
		
		assertThat(new JSONObject(getCompareFile(PROFILE, fileType, null)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));

		rtn = noResultHeaderCheck(callMockGet(getNoResultParameters(url), mimeType, getContentDisposition(PROFILE, fileType))).andReturn();
		assertThat(new JSONObject(getCompareFile(PROFILE, fileType, "NoResult")), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));
	}

	protected void getAsJsonZipTest(String url, String mimeType, String fileType) throws Exception {
		assertEquals("", unFilteredGeomHeaderCheck(callMockHead(url, mimeType, getContentDisposition(PROFILE, "zip"))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredGeomHeaderCheck(callMockGet(url, mimeType, getContentDisposition(PROFILE, "zip"))).andReturn();
		assertThat(new JSONObject(getCompareFile(PROFILE, fileType, null)), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), getFileName(PROFILE, fileType)))));

		rtn = noResultHeaderCheck(callMockGet(getNoResultParameters(url), mimeType, getContentDisposition(PROFILE, "zip"))).andReturn();
		assertThat(new JSONObject(getCompareFile(PROFILE, fileType, "NoResult")), sameJSONObjectAs(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), getFileName(PROFILE, fileType)))));
	}

	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_COUNT))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_COUNT))
				.andExpect(header().string(HEADER_BIODATA_SITE_COUNT, BIODATA_SITE_COUNT));
	}

	@Override
	public ResultActions unFilteredGeomHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_COUNT_GEOM))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_COUNT))
				.andExpect(header().string(HEADER_BIODATA_SITE_COUNT, BIODATA_SITE_COUNT_GEOM));
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
