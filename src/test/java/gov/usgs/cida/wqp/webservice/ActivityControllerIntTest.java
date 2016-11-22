package gov.usgs.cida.wqp.webservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.util.HttpConstants;

@Category(DBIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetup("classpath:/testData/dao/count/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ActivityControllerIntTest extends BaseControllerIntegrationTest {

	protected String endpoint = "/" + HttpConstants.ACTIVITY_SEARCH_ENPOINT + "?mimeType=";

	@Test
	public void getAsCsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "csv", HttpConstants.MIME_TYPE_CSV, "attachment; filename=activity.csv", "activity/activity.csv");
	}

	@Test
	public void getAsCsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "csv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=activity.zip", "activity/activity.csv", "activity.csv");
	}

	@Test
	public void getAsTsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "tsv", HttpConstants.MIME_TYPE_TSV, "attachment; filename=activity.tsv", "activity/activity.tsv");
	}

	@Test
	public void getAsTsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "tsv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=activity.zip", "activity/activity.tsv", "activity.tsv");
	}

	@Test
	public void getAsXlsxTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx", HttpConstants.MIME_TYPE_XLSX, "attachment; filename=activity.xlsx");
	}

	@Test
	public void getAsXlsxZipTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=activity.zip");
	}

	@Test
	public void getAsXmlTest() throws Exception {
		getAsXmlTest(endpoint + "xml", HttpConstants.MIME_TYPE_XML, "attachment; filename=activity.xml", "activity/activity.xml");
	}

	@Test
	public void getAsXmlZipGetTest() throws Exception {
		getAsXmlZipTest(endpoint + "xml&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=activity.zip", "activity/activity.xml", "activity.xml");
	}

	@Test
	public void getAllParametersTest() throws Exception {
		getAllParametersTest(endpoint + "csv", HttpConstants.MIME_TYPE_CSV, "attachment; filename=activity.csv", "activity/filteredActivity.csv");
	}

	@Test
	public void postGetCountTest() throws Exception {
		String urlPrefix = "/" + HttpConstants.ACTIVITY_SEARCH_ENPOINT + "/count?mimeType=";
		String compareObject = "{\"STORET-Site-Count\":\"1\",\"Total-Site-Count\":\"1\",\"STORET-Activity-Count\":\"1\",\"Total-Activity-Count\":\"1\"}";
		postGetCountTest(urlPrefix, compareObject);
	}

	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string("Total-Site-Count", "12"))
				.andExpect(header().string("NWIS-Site-Count", "2"))
				.andExpect(header().string("STEWARDS-Site-Count", "2"))
				.andExpect(header().string("STORET-Site-Count", "7"))
				.andExpect(header().string("BIODATA-Site-Count", "1"))

				.andExpect(header().string("Total-Activity-Count", "301"))
				.andExpect(header().string("NWIS-Activity-Count", "48"))
				.andExpect(header().string("STEWARDS-Activity-Count", "40"))
				.andExpect(header().string("STORET-Activity-Count", "178"))
				.andExpect(header().string("BIODATA-Activity-Count", "35"));
	}

	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string("Total-Site-Count", "1"))
				.andExpect(header().string("STORET-Site-Count", "1"));
	}

}
