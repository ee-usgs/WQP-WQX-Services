package gov.usgs.cida.wqp.webservice.result;

import static gov.usgs.cida.wqp.swagger.model.ActivityCountJson.HEADER_BIODATA_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ActivityCountJson.HEADER_NWIS_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ActivityCountJson.HEADER_STEWARDS_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ActivityCountJson.HEADER_STORET_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResultCountJson.HEADER_BIODATA_RESULT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResultCountJson.HEADER_NWIS_RESULT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResultCountJson.HEADER_STEWARDS_RESULT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.ResultCountJson.HEADER_STORET_RESULT_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_BIODATA_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_STEWARDS_SITE_COUNT;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_STORET_SITE_COUNT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

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
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.mapping.Profile;
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
public class ResultControllerIT extends BaseControllerIntegrationTest {

	protected static final Profile PROFILE = Profile.PC_RESULT;
	protected static final boolean POSTABLE = true;
	protected static final String ENDPOINT = HttpConstants.RESULT_SEARCH_ENDPOINT + "?mimeType=";

	@Test
	public void testHarness() throws Exception {
		getAsCsvTest();
		getAsCsvZipTest();
		getAsTsvTest();
		getAsTsvZipTest();
		getAsXlsxTest();
		getAsXlsxZipTest();
		getAsXmlTest();
		getAsXmlZipTest();
		getAllParametersTest();
		postGetCountTest();
	}

	public void getAsCsvTest() throws Exception {
		getAsDelimitedTest(ENDPOINT + CSV, HttpConstants.MIME_TYPE_CSV, CSV, PROFILE, POSTABLE);
	}

	public void getAsCsvZipTest() throws Exception {
		getAsDelimitedZipTest(ENDPOINT + CSV_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, CSV, PROFILE, POSTABLE);
	}

	public void getAsTsvTest() throws Exception {
		getAsDelimitedTest(ENDPOINT + TSV, HttpConstants.MIME_TYPE_TSV, TSV, PROFILE, POSTABLE);
	}

	public void getAsTsvZipTest() throws Exception {
		getAsDelimitedZipTest(ENDPOINT + TSV_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, TSV, PROFILE, POSTABLE);
	}

	public void getAsXlsxTest() throws Exception {
		getAsXlsxTest(ENDPOINT + XLSX, HttpConstants.MIME_TYPE_XLSX, XLSX, PROFILE, POSTABLE);
	}

	public void getAsXlsxZipTest() throws Exception {
		getAsXlsxZipTest(ENDPOINT + XLSX_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, XLSX, PROFILE, POSTABLE);
	}

	public void getAsXmlTest() throws Exception {
		getAsXmlTest(ENDPOINT + XML, HttpConstants.MIME_TYPE_XML, XML, PROFILE, POSTABLE);
	}

	public void getAsXmlZipTest() throws Exception {
		getAsXmlZipTest(ENDPOINT + XML_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, XML, PROFILE, POSTABLE);
	}

	public void getAllParametersTest() throws Exception {
		getAllParametersTest(ENDPOINT + CSV, HttpConstants.MIME_TYPE_CSV, CSV, PROFILE, POSTABLE);
	}

	public void postGetCountTest() throws Exception {
		String urlPrefix = HttpConstants.RESULT_SEARCH_ENDPOINT + "/count?mimeType=";
		String compareObject = "{\"" + HttpConstants.HEADER_TOTAL_SITE_COUNT + "\":\"" + FILTERED_TOTAL_SITE_COUNT
				+ "\",\"" + HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT + "\":\"" + FILTERED_TOTAL_ACTIVITY_COUNT
				+ "\",\"" + HttpConstants.HEADER_TOTAL_RESULT_COUNT + "\":\"" + FILTERED_TOTAL_RESULT_COUNT
				+ "\",\"" + HEADER_STORET_SITE_COUNT + "\":\"" + FILTERED_STORET_SITE_COUNT
				+ "\",\"" + HEADER_STORET_ACTIVITY_COUNT + "\":\"" + FILTERED_STORET_ACTIVITY_COUNT
				+ "\",\"" + HEADER_STORET_RESULT_COUNT + "\":\"" + FILTERED_STORET_RESULT_COUNT
				+ "\"}";
		postGetCountTest(urlPrefix, compareObject, PROFILE);
	}

	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_COUNT_MINUS_1))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_COUNT_MINUS_1))
				.andExpect(header().string(HEADER_BIODATA_SITE_COUNT, BIODATA_SITE_COUNT_MINUS_1))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT, TOTAL_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_NWIS_ACTIVITY_COUNT, NWIS_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_ACTIVITY_COUNT, STEWARDS_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_STORET_ACTIVITY_COUNT, STORET_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_BIODATA_ACTIVITY_COUNT, BIODATA_ACTIVITY_COUNT))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_RESULT_COUNT, TOTAL_RESULT_COUNT))
				.andExpect(header().string(HEADER_NWIS_RESULT_COUNT, NWIS_RESULT_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_RESULT_COUNT, STEWARDS_RESULT_COUNT))
				.andExpect(header().string(HEADER_STORET_RESULT_COUNT, STORET_RESULT_COUNT))
				.andExpect(header().string(HEADER_BIODATA_RESULT_COUNT, BIODATA_RESULT_COUNT));
	}

	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, FILTERED_TOTAL_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, FILTERED_STORET_SITE_COUNT))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT, FILTERED_TOTAL_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_STORET_ACTIVITY_COUNT, FILTERED_STORET_ACTIVITY_COUNT))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_RESULT_COUNT, FILTERED_TOTAL_RESULT_COUNT))
				.andExpect(header().string(HEADER_STORET_RESULT_COUNT, FILTERED_STORET_RESULT_COUNT));
	}

	public ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, "0"))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT, "0"))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_RESULT_COUNT, "0"));
	}

}
