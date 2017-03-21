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
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ActivityMetricRestfullTest extends BaseControllerIntegrationTest {

	public static String NAME = ActivityMetricControllerIntTest.NAME;
	protected static final boolean POSTABLE = false;
	protected String endpoint = HttpConstants.ACTIVITY_METRIC_REST_ENPOINT.replace("{activity}", getActivity()[0]) + "?mimeType=";

	@Test
	public void getAsCsvTest() throws Exception {
		getAsDelimitedTest(endpoint + CSV, HttpConstants.MIME_TYPE_CSV, CSV, NAME, POSTABLE);
	}

	@Test
	public void getAsCsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + CSV_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, CSV, NAME, POSTABLE);
	}

	@Test
	public void getAsTsvTest() throws Exception {
		getAsDelimitedTest(endpoint + TSV, HttpConstants.MIME_TYPE_TSV, TSV, NAME, POSTABLE);
	}

	@Test
	public void getAsTsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + TSV_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, TSV, NAME, POSTABLE);
	}

	@Test
	public void getAsXlsxTest() throws Exception {
		getAsXlsxTest(endpoint + XLSX, HttpConstants.MIME_TYPE_XLSX, XLSX, NAME, POSTABLE);
	}

	@Test
	public void getAsXlsxZipTest() throws Exception {
		getAsXlsxZipTest(endpoint + XLSX_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, XLSX, NAME, POSTABLE);
	}

	@Test
	public void getAsXmlTest() throws Exception {
		getAsXmlTest(endpoint + XML, HttpConstants.MIME_TYPE_XML, XML, NAME, POSTABLE);
	}

	@Test
	public void getAsXmlZipGetTest() throws Exception {
		getAsXmlZipTest(endpoint + XML_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, XML, NAME, POSTABLE);
	}

	@Override
	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, "1"))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, "1"))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT, "1"))
				.andExpect(header().string(HEADER_STORET_ACTIVITY_COUNT, "1"))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_METRIC_COUNT, "6"))
				.andExpect(header().string(HEADER_STORET_ACTIVITY_METRIC_COUNT, "6"));
	}

	@Override
	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return null;
	}

	@Override
	public ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, "0"))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT, "0"))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_METRIC_COUNT, "0"));
	}

}
