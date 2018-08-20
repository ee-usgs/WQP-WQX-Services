package gov.usgs.cida.wqp.webservice.summary;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.Application;
import static gov.usgs.cida.wqp.BaseTest.BIODATA_SITE_COUNT_GEOM;
import static gov.usgs.cida.wqp.BaseTest.GEOJSON;
import static gov.usgs.cida.wqp.BaseTest.GEOJSON_AND_ZIP;
import static gov.usgs.cida.wqp.BaseTest.NWIS_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.STEWARDS_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.STORET_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.TOTAL_SITE_COUNT_GEOM;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.cida.wqp.dao.summary.SummaryStationStreamingIT;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_BIODATA_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_STEWARDS_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_STORET_SITE_COUNT;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerIntegrationTest;
import java.net.URL;
import static org.hamcrest.MatcherAssert.assertThat;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

@EnableWebMvc
@AutoConfigureMockMvc(secure=false)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK,
	classes={DBTestConfig.class, Application.class})
@DatabaseSetup("classpath:/testData/clearAll.xml")
@DatabaseSetup("classpath:/testData/state.xml")
@DatabaseSetup("classpath:/testData/county.xml")
@DatabaseSetup("classpath:/testData/station.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class SummaryStationControllerIT extends BaseControllerIntegrationTest {

	protected static final Profile PROFILE = Profile.SUMMARY_STATION;
	protected static final boolean POSTABLE = true;
	protected static final String ENDPOINT = HttpConstants.SUMMARY_STATION_ENDPOINT + "?summaryYears=" + SummaryStationStreamingIT.SUMMARY_YEARS_ALL_MONTHS + "&mimeType=";

	@Test
	public void testHarness() throws Exception {
		getAsGeoJsonTest();
		getAsGeoJsonZipTest();
		getAllParametersTest();
		postGetCountTest();
	}

	public void getAsGeoJsonTest() throws Exception {
		getAsJsonTest(ENDPOINT + GEOJSON, HttpConstants.MIME_TYPE_GEOJSON, GEOJSON, PROFILE, POSTABLE);
	}

	public void getAsGeoJsonZipTest() throws Exception {
		getAsJsonZipTest(ENDPOINT + GEOJSON_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, GEOJSON, PROFILE, POSTABLE);
	}

	public void getAllParametersTest() throws Exception {
		getAllParametersTest(ENDPOINT + GEOJSON, HttpConstants.MIME_TYPE_GEOJSON, GEOJSON, PROFILE, POSTABLE);
	}

	public void postGetCountTest() throws Exception {
		String urlPrefix = HttpConstants.SUMMARY_STATION_ENDPOINT + "/count?mimeType=";
		String compareObject = "{\"" + HttpConstants.HEADER_TOTAL_SITE_COUNT + "\":\"" + TOTAL_SITE_COUNT_GEOM
				+ "\",\"" + HEADER_STORET_SITE_COUNT + "\":\"" + STORET_SITE_COUNT
				+ "\",\"" + HEADER_BIODATA_SITE_COUNT + "\":\"" + BIODATA_SITE_COUNT_GEOM
				+ "\",\"" + HEADER_NWIS_SITE_COUNT + "\":\"" + NWIS_SITE_COUNT
				+ "\",\"" + HEADER_STEWARDS_SITE_COUNT + "\":\"" + STEWARDS_SITE_COUNT
				+ "\"}";
		postGetCountTest(urlPrefix, compareObject, PROFILE);
	}

	public void postGetCountTest(String urlPrefix, String compareObject, Profile profile) throws Exception {
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSites());

		MvcResult rtn = filteredHeaderCheck(callMockPostJson(urlPrefix + JSON, getSourceFile("summaryPostParameters.json").replace("[DATA_PROFILE]", profile.toString()), HttpConstants.MIME_TYPE_JSON, null))
			.andReturn();

		assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
				sameJSONObjectAs(new JSONObject(compareObject)));

		callMockPostJsonBadRequest(urlPrefix+ GEOJSON);
		callMockPostJsonBadRequest(urlPrefix+ GEOJSON_AND_ZIP);
	}

	public ResultActions callMockPostJsonBadRequest(String url) throws Exception {
		return mockMvc.perform(post(url)
				.content(getSourceFile("summaryPostParameters.json")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable());
	}

	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_COUNT_GEOM))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_COUNT))
				.andExpect(header().string(HEADER_BIODATA_SITE_COUNT, BIODATA_SITE_COUNT_GEOM));
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
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_COUNT_GEOM))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_COUNT));
	}

	public ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, "0"));
	}

	protected void getAllParametersTest(String url, String mimeType, String fileType, Profile profile, boolean isPostable) throws Exception {
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSites());

		assertEquals("", filteredHeaderCheck(callMockHead(url, mimeType, getContentDisposition(profile, fileType))).andReturn().getResponse().getContentAsString());

		String filteredUrl = HttpConstants.SUMMARY_STATION_ENDPOINT + "?summaryYears=" + SummaryStationStreamingIT.SUMMARY_YEARS_12_MONTHS + "&mimeType=" + GEOJSON;

		MvcResult rtn = filteredHeaderCheck(callMockGet(filteredUrl, mimeType, getContentDisposition(profile, fileType))).andReturn();
		assertThat(new JSONObject(getCompareFile(profile, fileType, "Filtered")), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));

		if (isPostable) {
			rtn = filteredHeaderCheck(callMockPostJson(url, getSourceFile("summaryPostParameters.json").replace("[DATA_PROFILE]", profile.toString()), mimeType, getContentDisposition(profile, fileType))).andReturn();
			assertThat(new JSONObject(getCompareFile(profile, fileType, "Filtered")), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));

			rtn = unFilteredHeaderCheck(callMockPostEmptyForm(url, mimeType, getContentDisposition(profile, fileType))).andReturn();
			assertThat(new JSONObject(getCompareFile(profile, fileType, null)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())));
		}
	}

}
