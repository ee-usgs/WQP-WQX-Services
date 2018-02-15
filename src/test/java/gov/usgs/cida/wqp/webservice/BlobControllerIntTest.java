package gov.usgs.cida.wqp.webservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayInputStream;
import java.util.zip.ZipInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.BlobDaoTest;
import gov.usgs.cida.wqp.util.HttpConstants;

@Category(DBIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetup("classpath:/testData/blob/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class BlobControllerIntTest extends BaseSpringTest{

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

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
		MvcResult rtn = mockMvc.perform(get(buildMonitoringLocationUrl(BlobDaoTest.SINGLE_FILE_STATION_ORG, BlobDaoTest.SINGLE_FILE_STATION_STATION)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.MONITORING_LOCATION_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoTest.assertSingleFileStation(stream);
	}

	public void threeFileStationTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildMonitoringLocationUrl(BlobDaoTest.THREE_FILE_STATION_ORG, BlobDaoTest.THREE_FILE_STATION_STATION)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.MONITORING_LOCATION_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoTest.assertThreeFileStation(stream);
	}


	public void singleFileProjectTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildProjectUrl(BlobDaoTest.SINGLE_FILE_PROJECT_ORG, BlobDaoTest.SINGLE_FILE_PROJECT_PROJECT)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.PROJECT_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoTest.assertSingleFileProject(stream);
	}

	public void threeFileProjectTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildProjectUrl(BlobDaoTest.THREE_FILE_PROJECT_ORG, BlobDaoTest.THREE_FILE_PROJECT_PROJECT)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.PROJECT_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoTest.assertThreeFileProject(stream);
	}


	public void singleFileResultTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildResultUrl(BlobDaoTest.SINGLE_FILE_RESULT_ORG, BlobDaoTest.SINGLE_FILE_RESULT_ACTIVITY,
				BlobDaoTest.SINGLE_FILE_RESULT_RESULT)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.RESULT_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoTest.assertSingleFileResult(stream);
	}

	public void threeFileResultTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildResultUrl(BlobDaoTest.THREE_FILE_RESULT_ORG, BlobDaoTest.THREE_FILE_RESULT_ACTIVITY,
				BlobDaoTest.THREE_FILE_RESULT_RESULT)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.RESULT_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoTest.assertThreeFileResult(stream);
	}


	public void singleFileActivityTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildActivityUrl(BlobDaoTest.SINGLE_FILE_ACTIVITY_ORG, BlobDaoTest.SINGLE_FILE_ACTIVITY_ACTIVITY)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.ACTIVITY_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoTest.assertSingleFileActivity(stream);
	}

	public void threeFileActivityTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(buildActivityUrl(BlobDaoTest.THREE_FILE_ACTIVITY_ORG, BlobDaoTest.THREE_FILE_ACTIVITY_ACTIVITY)))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=" + BlobController.ACTIVITY_FILE))
				.andReturn();

			ZipInputStream stream = getStream(rtn);
			BlobDaoTest.assertThreeFileActivity(stream);
	}

	protected String buildBaseUrl(String template, String organization) {
		return template.replace("{organization}", organization);
	}

	protected String buildMonitoringLocationUrl(String organization, String monitoringLocation) {
		return buildBaseUrl(HttpConstants.MONITORING_LOCATION_FILE_REST_ENDPOINT, organization).replace("{monitoringLocation}", monitoringLocation);
	}

	protected String buildProjectUrl(String organization, String project) {
		return buildBaseUrl(HttpConstants.PROJECT_FILE_REST_ENDPOINT, organization).replace("{project}", project);
	}

	protected String buildActivityUrl(String organization, String activity) {
		return buildBaseUrl(HttpConstants.ACTIVITY_FILE_REST_ENDPOINT, organization).replace("{activity}", activity);
	}

	protected String buildResultUrl(String organization, String activity,  String resultIdentifier) {
		return buildBaseUrl(HttpConstants.RESULT_FILE_REST_ENDPOINT, organization).replace("{activity}", activity)
				.replace("{result}", resultIdentifier);
	}

	protected ZipInputStream getStream(MvcResult rtn) {
		return new ZipInputStream(new ByteArrayInputStream(rtn.getResponse().getContentAsByteArray()));
	}

}
