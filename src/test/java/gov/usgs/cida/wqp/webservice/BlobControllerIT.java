package gov.usgs.cida.wqp.webservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayInputStream;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.Application;
import gov.usgs.cida.wqp.BaseIT;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.dao.BlobDaoIT;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import gov.usgs.cida.wqp.util.HttpConstants;

@EnableWebMvc
@AutoConfigureMockMvc()
@SpringBootTest(webEnvironment=WebEnvironment.MOCK,
	classes={DBTestConfig.class, Application.class})
@DatabaseSetup("classpath:/testData/blob/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class BlobControllerIT extends BaseIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHarness() throws Exception {
		singleFileStationTest();
		threeFileStationTest();
		singleFileProjectTest();
		threeFileProjectTest();
		singleFileResultTest();
		threeFileResultTest();
		singleFileActivityTest();
		threeFileActivityTest();
	}

	public void singleFileStationTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildMonitoringLocationUrl(BlobDaoIT.SINGLE_FILE_STATION_ORG, BlobDaoIT.SINGLE_FILE_STATION_STATION)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.MONITORING_LOCATION_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoIT.assertSingleFileStation(stream);
	}

	public void threeFileStationTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildMonitoringLocationUrl(BlobDaoIT.THREE_FILE_STATION_ORG, BlobDaoIT.THREE_FILE_STATION_STATION)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.MONITORING_LOCATION_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoIT.assertThreeFileStation(stream);
	}


	public void singleFileProjectTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildProjectUrl(BlobDaoIT.SINGLE_FILE_PROJECT_ORG, BlobDaoIT.SINGLE_FILE_PROJECT_PROJECT)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.PROJECT_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoIT.assertSingleFileProject(stream);
	}

	public void threeFileProjectTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildProjectUrl(BlobDaoIT.THREE_FILE_PROJECT_ORG, BlobDaoIT.THREE_FILE_PROJECT_PROJECT)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.PROJECT_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoIT.assertThreeFileProject(stream);
	}


	public void singleFileResultTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildResultUrl(BlobDaoIT.SINGLE_FILE_RESULT_ORG, BlobDaoIT.SINGLE_FILE_RESULT_ACTIVITY,
				BlobDaoIT.SINGLE_FILE_RESULT_RESULT)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.RESULT_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoIT.assertSingleFileResult(stream);
	}

	public void threeFileResultTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildResultUrl(BlobDaoIT.THREE_FILE_RESULT_ORG, BlobDaoIT.THREE_FILE_RESULT_ACTIVITY,
				BlobDaoIT.THREE_FILE_RESULT_RESULT)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.RESULT_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoIT.assertThreeFileResult(stream);
	}


	public void singleFileActivityTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildActivityUrl(BlobDaoIT.SINGLE_FILE_ACTIVITY_ORG, BlobDaoIT.SINGLE_FILE_ACTIVITY_ACTIVITY)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.ACTIVITY_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoIT.assertSingleFileActivity(stream);
	}

	public void threeFileActivityTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildActivityUrl(BlobDaoIT.THREE_FILE_ACTIVITY_ORG, BlobDaoIT.THREE_FILE_ACTIVITY_ACTIVITY)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.ACTIVITY_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoIT.assertThreeFileActivity(stream);
	}

	protected String buildBaseUrl(String template, String organization) {
		return template.replace("{organization}", organization);
	}

	protected String buildMonitoringLocationUrl(String organization, String monitoringLocation) {
		return buildBaseUrl(HttpConstants.MONITORING_LOCATION_FILE_REST_ENDPOINT, organization)
				.replace("{provider}", getRestProvider()).replace("{organization}", getRestOrganization())
				.replace("{monitoringLocation}", monitoringLocation);
	}

	protected String buildProjectUrl(String organization, String project) {
		return buildBaseUrl(HttpConstants.PROJECT_FILE_REST_ENDPOINT, organization)
				.replace("{provider}", getRestProvider()).replace("{organization}", getRestOrganization())
				.replace("{project}", project);
	}

	protected String buildActivityUrl(String organization, String activity) {
		return buildBaseUrl(HttpConstants.ACTIVITY_FILE_REST_ENDPOINT, organization)
				.replace("{provider}", getRestProvider()).replace("{organization}", getRestOrganization())
				.replace("{activity}", activity);
	}

	protected String buildResultUrl(String organization, String activity,  String resultIdentifier) {
		return buildBaseUrl(HttpConstants.RESULT_FILE_REST_ENDPOINT, organization)
				.replace("{provider}", getRestProvider()).replace("{organization}", getRestOrganization())
				.replace("{activity}", activity)
				.replace("{result}", resultIdentifier);
	}

	protected ZipInputStream getStream(MvcResult rtn) {
		return new ZipInputStream(new ByteArrayInputStream(rtn.getResponse().getContentAsByteArray()));
	}

}
