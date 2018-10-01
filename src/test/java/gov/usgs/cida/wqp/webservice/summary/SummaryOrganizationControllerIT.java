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
import static gov.usgs.cida.wqp.BaseTest.JSON;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.cida.wqp.dao.summary.SummaryOrganizationStreamingIT;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import static gov.usgs.cida.wqp.swagger.model.OrganizationCountJson.HEADER_STORET_ORGANIZATION_COUNT;
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
@DatabaseSetup("classpath:/testData/organizationSum.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class SummaryOrganizationControllerIT extends BaseControllerIntegrationTest {

	protected static final Profile PROFILE = Profile.SUMMARY_ORGANIZATION;
	protected static final boolean POSTABLE = true;	
	protected static final String ENDPOINT = HttpConstants.SUMMARY_ORGANIZATION_ENDPOINT + "?summaryYears=" + SummaryOrganizationStreamingIT.SUMMARY_YEARS_ALL_MONTHS + "&mimeType=";
	protected static final String TOTAL_ORG_SUM_COUNT = "2";
	protected static final String BIODATA_ORG_SUM_COUNT = "1";
	protected static final String NWIS_ORG_SUM_COUNT = "2";
	protected static final String STEWARDS_ORG_SUM_COUNT = "2";
	protected static final String STORET_ORG_SUM_COUNT = "6";
	
	protected static final String TOTAL_ORG_SUM_ONE_YEAR_COUNT = "1";
	protected static final String STORET_ORG_SUM_ONE_YEAR_COUNT = "1";
	
	protected static final String TEST_MONITORING_LOCACTION_1 = "R10ELKHEADMINE";
	protected static final String TEST_MONITORING_LOCACTION_2 = "R9VOL";
		
	@Test
	public void testHarness() throws Exception {
		getAsJsonTest(ENDPOINT + JSON, HttpConstants.MIME_TYPE_JSON, JSON, PROFILE, POSTABLE);
		getAsJsonZipTest(ENDPOINT + JSON_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, JSON, PROFILE, POSTABLE);
		getAllParametersTest();		
	}

	public void getAllParametersTest() throws Exception {
		getAllParametersTest(ENDPOINT + JSON, HttpConstants.MIME_TYPE_JSON, JSON, PROFILE, POSTABLE);
	}

	@Override
	public void postGetCountTest(String urlPrefix, String compareObject, Profile profile) throws Exception {
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(getNldiSites());

		MvcResult rtn = filteredHeaderCheck(callMockPostJson(urlPrefix + JSON, getSourceFile("summaryPostParameters.json").replace("[DATA_PROFILE]", profile.toString()), HttpConstants.MIME_TYPE_JSON, null))
			.andReturn();

		assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
				sameJSONObjectAs(new JSONObject(compareObject)));

		callMockPostJsonBadRequest(urlPrefix+ JSON);
		callMockPostJsonBadRequest(urlPrefix+ JSON_AND_ZIP);
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
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT, TOTAL_ORG_SUM_COUNT));
	}

	@Override
	public ResultActions unFilteredGeomHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT, TOTAL_ORG_SUM_COUNT));
	}

	@Override
	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT, TOTAL_ORG_SUM_ONE_YEAR_COUNT))
				.andExpect(header().string(HEADER_STORET_ORGANIZATION_COUNT, STORET_ORG_SUM_ONE_YEAR_COUNT));
	}

	@Override
	public ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT, "0"));
	}

	@Override
	protected void getAllParametersTest(String url, String mimeType, String fileType, Profile profile, boolean isPostable) throws Exception {
				
		String filteredUrl = HttpConstants.SUMMARY_ORGANIZATION_ENDPOINT + "?summaryYears=" + SummaryOrganizationStreamingIT.SUMMARY_YEARS_12_MONTHS + "&mimeType=" + JSON + "&organization=" + TEST_MONITORING_LOCACTION_1;

		assertEquals("", filteredHeaderCheck(callMockHead(filteredUrl, mimeType, getContentDisposition(profile, fileType))).andReturn().getResponse().getContentAsString());

		MvcResult rtn = filteredHeaderCheck(callMockGet(filteredUrl, mimeType, getContentDisposition(profile, fileType))).andReturn();
		
		assertThat(new JSONObject(getCompareFile(profile, fileType, "Filtered")), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())).allowingAnyArrayOrdering());

		if (isPostable) {
			rtn = filteredHeaderCheck(callMockPostJson(url, getSourceFile("summaryPostParameters.json").replace("[DATA_PROFILE]", profile.toString()), mimeType, getContentDisposition(profile, fileType))).andReturn();
			assertThat(new JSONObject(getCompareFile(profile, fileType, "Filtered")), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())).allowingAnyArrayOrdering());

			rtn = unFilteredHeaderCheck(callMockPostEmptyForm(url, mimeType, getContentDisposition(profile, fileType))).andReturn();
			assertThat(new JSONObject(getCompareFile(profile, fileType, null)), sameJSONObjectAs(new JSONObject(rtn.getResponse().getContentAsString())).allowingAnyArrayOrdering());
		}
	}
	
	@Override
	protected String getNoResultParameters(String url) {
		return url + "&" + Parameters.ORGANIZATION.toString() + "=DS";
	}
}
