package gov.usgs.wma.wqp.webservice.summary;

import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_STEWARDS_SITE_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_STORET_SITE_COUNT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

import java.net.URL;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.wma.wqp.Application;
import gov.usgs.wma.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.springinit.DBTestConfig;
import gov.usgs.wma.wqp.util.HttpConstants;
import gov.usgs.wma.wqp.webservice.BaseControllerIntegrationTest;

@EnableWebMvc
@AutoConfigureMockMvc()
@SpringBootTest(webEnvironment=WebEnvironment.MOCK,
	classes={DBTestConfig.class, Application.class})
@DatabaseSetup("classpath:/testData/clearAll.xml")
@DatabaseSetup("classpath:/testData/station.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class SummaryMonitoringLocationControllerIT extends BaseControllerIntegrationTest {

	protected static final Profile PROFILE = Profile.SUMMARY_MONITORING_LOCATION;	
	protected static final boolean POSTABLE = true;
	protected static final String ENDPOINT = HttpConstants.SUMMARY_MONITORING_LOCATION_ENDPOINT
			+ "?summaryYears=" + FilterParameters.SUMMARY_YEARS_ALL_MONTHS + "&mimeType=";

	protected static final String TOTAL_SITE_SUM_COUNT = "11";
	protected static final String NWIS_SITE_SUM_COUNT = "3";
	protected static final String STEWARDS_SITE_SUM_COUNT = "2";
	protected static final String STORET_SITE_SUM_COUNT = "6";

	protected static final String TOTAL_SITE_SUM_ONE_YEAR_COUNT = "9";
	protected static final String STORET_SITE_SUM_ONE_YEAR_COUNT = "4";

	@Test
	public void testHarness() throws Exception {
		getAsGeoJsonTest();
		getAsGeoJsonZipTest();
		getAllParametersTest();
		postGetCountTest();
	}

	public void getAsGeoJsonTest() throws Exception {
		getAsJsonTest(ENDPOINT + GEOJSON,
				HttpConstants.MIME_TYPE_GEOJSON, GEOJSON, PROFILE, POSTABLE);
	}

	public void getAsGeoJsonZipTest() throws Exception {
		getAsJsonZipTest(ENDPOINT + GEOJSON_AND_ZIP,
				HttpConstants.MIME_TYPE_ZIP, GEOJSON, PROFILE, POSTABLE);
	}

	public void getAllParametersTest() throws Exception {
		getAllParametersTest(ENDPOINT + GEOJSON,
				HttpConstants.MIME_TYPE_GEOJSON, GEOJSON, PROFILE, POSTABLE);
	}

	public void postGetCountTest() throws Exception {
		String urlPrefix = HttpConstants.SUMMARY_MONITORING_LOCATION_ENDPOINT + "/count?mimeType=";
		String compareObject = "{\"" + HttpConstants.HEADER_TOTAL_SITE_COUNT + "\":\"" + TOTAL_SITE_SUM_ONE_YEAR_COUNT
				+ "\",\"" + HEADER_STORET_SITE_COUNT + "\":\"" + STORET_SITE_SUM_ONE_YEAR_COUNT
				+ "\",\"" + HEADER_NWIS_SITE_COUNT + "\":\"" + NWIS_SITE_SUM_COUNT
				+ "\",\"" + HEADER_STEWARDS_SITE_COUNT + "\":\"" + STEWARDS_SITE_SUM_COUNT
				+ "\"}";
		postGetCountTest(urlPrefix, compareObject, PROFILE);
	}

	@Override
	public void postGetCountTest(String urlPrefix, String compareObject, Profile profile) throws Exception {
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSites());

		MvcResult rtn = filteredHeaderCheck(callMockPostJson(urlPrefix + JSON,
				getSourceFile("summaryPostParameters.json")
						.replace("[DATA_PROFILE]", profile.toString()), HttpConstants.MIME_TYPE_JSON, null))
			.andReturn();

		assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
				sameJSONObjectAs(new JSONObject(compareObject)));

		callMockPostJsonBadRequest(urlPrefix+ GEOJSON);
		callMockPostJsonBadRequest(urlPrefix+ GEOJSON_AND_ZIP);
	}

	@Override
	public ResultActions callMockPostJsonBadRequest(String url) throws Exception {
		return mockMvc.perform(post(url)
				.content(getSourceFile("summaryPostParameters.json")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable());
	}

	@Override
	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_SUM_COUNT));
	}

	@Override
	public ResultActions unFilteredGeomHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_SUM_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_SUM_COUNT));
	}

	@Override
	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_SUM_ONE_YEAR_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_SUM_ONE_YEAR_COUNT));
	}

	@Override
	public ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, "0"));
	}

	@Override
	protected void getAllParametersTest(String url, String mimeType, String fileType, Profile profile, boolean isPostable) throws Exception {
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSites());

		String filteredUrl = HttpConstants.SUMMARY_MONITORING_LOCATION_ENDPOINT + "?summaryYears=" + FilterParameters.SUMMARY_YEARS_12_MONTHS + "&mimeType=" + GEOJSON;

		assertEquals("", filteredHeaderCheck(callMockHead(filteredUrl, mimeType, getContentDisposition(profile, fileType))).andReturn().getResponse().getContentAsString());

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
