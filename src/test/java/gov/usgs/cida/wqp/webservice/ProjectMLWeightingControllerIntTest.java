package gov.usgs.cida.wqp.webservice;

import static gov.usgs.cida.wqp.swagger.model.ProjectMonitoringLocationWeightingCountJson.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.util.HttpConstants;


@Category(DBIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ProjectMLWeightingControllerIntTest extends BaseControllerIntegrationTest {
	
	protected static final Profile PROFILE = Profile.PROJECT_MONITORING_LOCATION_WEIGHTING;
	protected static final boolean POSTABLE = true;
	protected static final String ENDPOINT = HttpConstants.PROJECT_MONITORING_LOCATION_WEIGHTING_SEARCH_ENDPOINT + "?mimeType=";
	
	@Test
	public void getAsCsvTest() throws Exception {
		getAsDelimitedTest(ENDPOINT + CSV, HttpConstants.MIME_TYPE_CSV, CSV, PROFILE, POSTABLE);
	}
	
	@Test
	public void getAsCsvZipTest() throws Exception {
		getAsDelimitedZipTest(ENDPOINT + CSV_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, CSV, PROFILE, POSTABLE);
	}
	
	@Test
	public void getAsTsvTest() throws Exception {
		getAsDelimitedTest(ENDPOINT + TSV, HttpConstants.MIME_TYPE_TSV, TSV, PROFILE, POSTABLE);
	}

	@Test
	public void getAsTsvZipTest() throws Exception {
		getAsDelimitedZipTest(ENDPOINT + TSV_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, TSV, PROFILE, POSTABLE);
	}
	
	@Test
	public void getAsXlsxTest() throws Exception {
		getAsXlsxTest(ENDPOINT + XLSX, HttpConstants.MIME_TYPE_XLSX, XLSX, PROFILE, POSTABLE);
	}

	@Test
	public void getAsXlsxZipTest() throws Exception {
		getAsXlsxZipTest(ENDPOINT + XLSX_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, XLSX, PROFILE, POSTABLE);
	}
	
	@Test
	public void getAsXmlTest() throws Exception {
		getAsXmlTest(ENDPOINT + XML, HttpConstants.MIME_TYPE_XML, XML, PROFILE, POSTABLE);
	}

	@Test
	public void getAsXmlZipGetTest() throws Exception {
		getAsXmlZipTest(ENDPOINT + XML_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, XML, PROFILE, POSTABLE);
	}
	
	@Test
	public void getAllParametersTest() throws Exception {
		getAllParametersTest(ENDPOINT + CSV, HttpConstants.MIME_TYPE_CSV, CSV, PROFILE, POSTABLE);
	}
	
	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions				
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, TOTAL_PROJECT_COUNT))
				.andExpect(header().string(HEADER_NWIS_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, NWIS_PRJ_ML_WEIGHTING_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, STEWARDS_PRJ_ML_WEIGHTING_COUNT))
				.andExpect(header().string(HEADER_STORET_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, STORET_PRJ_ML_WEIGHTING_COUNT))
				.andExpect(header().string(HEADER_BIODATA_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, BIODATA_PRJ_ML_WEIGHTING_COUNT));
	}
	
	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, FILTERED_TOTAL_PRJ_ML_WEIGHTING_COUNT));
	}
	
	public ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "0"));
	}
	
}
