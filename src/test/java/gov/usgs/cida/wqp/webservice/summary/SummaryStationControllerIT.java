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
import static gov.usgs.cida.wqp.BaseTest.BIODATA_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.BIODATA_SITE_COUNT_GEOM;
import static gov.usgs.cida.wqp.BaseTest.CSV;
import static gov.usgs.cida.wqp.BaseTest.FILTERED_STORET_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.FILTERED_TOTAL_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.GEOJSON;
import static gov.usgs.cida.wqp.BaseTest.GEOJSON_AND_ZIP;
import static gov.usgs.cida.wqp.BaseTest.NWIS_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.STEWARDS_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.STORET_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.TOTAL_SITE_COUNT;
import static gov.usgs.cida.wqp.BaseTest.TOTAL_SITE_COUNT_GEOM;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_BIODATA_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_STEWARDS_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_STORET_SITE_COUNT;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerIntegrationTest;
import java.net.URL;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@EnableWebMvc
@AutoConfigureMockMvc(secure=false)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK,
	classes={DBTestConfig.class, Application.class})
//@DatabaseSetup("classpath:/testData/csv/")
//@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@DatabaseSetup("classpath:/testData/clearAll.xml")
@DatabaseSetup("classpath:/testData/state.xml")
@DatabaseSetup("classpath:/testData/county.xml")
@DatabaseSetup("classpath:/testData/station.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class SummaryStationControllerIT extends BaseControllerIntegrationTest {
	
	protected static final Profile PROFILE = Profile.SUMMARY_STATION;
	protected static final boolean POSTABLE = true;
	protected static final String ENDPOINT = HttpConstants.SUMMARY_STATION_ENDPOINT + "?mimeType=";
	
	@Test
	public void testHarness() throws Exception {
		getAsGeoJsonTest();
		getAsGeoJsonZipTest();
		getAllParametersTest(); // TODO: Make new version of this
	}

	@Test
	public void getAsGeoJsonTest() throws Exception {
		getAsJsonTest(ENDPOINT + GEOJSON, HttpConstants.MIME_TYPE_GEOJSON, GEOJSON, PROFILE, POSTABLE);
	}

	@Test
	public void getAsGeoJsonZipTest() throws Exception {
		getAsJsonZipTest(ENDPOINT + GEOJSON_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, GEOJSON, PROFILE, POSTABLE);
	}

	@Test
	public void getAllParametersTest() throws Exception {
		getAllParametersTest(ENDPOINT + CSV, HttpConstants.MIME_TYPE_CSV, CSV, PROFILE, POSTABLE);
	}

	@Test
	public void postGetCountTest() throws Exception {
		String urlPrefix = HttpConstants.SUMMARY_STATION_ENDPOINT + "/count?mimeType=";
		String compareObject = "{\"" + HttpConstants.HEADER_TOTAL_SITE_COUNT + "\":\"" + FILTERED_TOTAL_SITE_COUNT
				+ "\",\"" + HEADER_STORET_SITE_COUNT + "\":\"" + FILTERED_STORET_SITE_COUNT
				+ "\"}";
		postGetCountTest(urlPrefix, compareObject, PROFILE);
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
	
	protected void getAllParametersTest(String url, String mimeType, String fileType, Profile profile, boolean isPostable) throws Exception {
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSites());

		assertEquals("", filteredHeaderCheck(callMockHead(url + getUrlParameters(), mimeType, getContentDisposition(profile, fileType))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = filteredHeaderCheck(callMockGet(url + getUrlParameters(), mimeType, getContentDisposition(profile, fileType))).andReturn();
		assertEquals(getCompareFile(profile, fileType, "Filtered"), rtn.getResponse().getContentAsString());

		if (isPostable) {
			rtn = filteredHeaderCheck(callMockPostJson(url, getSourceFile("postParameters.json").replace("[DATA_PROFILE]", profile.toString()), mimeType, getContentDisposition(profile, fileType))).andReturn();
			assertEquals(getCompareFile(profile, fileType, "Filtered"), rtn.getResponse().getContentAsString());

			rtn = filteredHeaderCheck(callMockPostFormFiltered(url, mimeType, getContentDisposition(profile, fileType))).andReturn();
			assertEquals(getCompareFile(profile, fileType, "Filtered"), rtn.getResponse().getContentAsString());

			rtn = unFilteredHeaderCheck(callMockPostEmptyForm(url, mimeType, getContentDisposition(profile, fileType))).andReturn();
			assertEquals(getCompareFile(profile, fileType, null), rtn.getResponse().getContentAsString());
		}
	}
	
}
