package gov.usgs.cida.wqp.webservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
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
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class ResDetectQntLmtControllerIntTest extends BaseControllerIntegrationTest {

	protected String endpoint = HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENPOINT + "?mimeType=";

	@Test
	public void getAsCsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "csv", HttpConstants.MIME_TYPE_CSV, "attachment; filename=resdetectqntlmt.csv", "resDetectQntLmt/resdetectqntlmt.csv", "resDetectQntLmt/noResult.csv");
	}

	@Test
	public void getAsCsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "csv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=resdetectqntlmt.zip", "resDetectQntLmt/resdetectqntlmt.csv", "resdetectqntlmt.csv", "resDetectQntLmt/noResult.csv");
	}

	@Test
	public void getAsTsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "tsv", HttpConstants.MIME_TYPE_TSV, "attachment; filename=resdetectqntlmt.tsv", "resDetectQntLmt/resdetectqntlmt.tsv", "resDetectQntLmt/noResult.tsv");
	}

	@Test
	public void getAsTsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "tsv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=resdetectqntlmt.zip", "resDetectQntLmt/resdetectqntlmt.tsv", "resdetectqntlmt.tsv", "resDetectQntLmt/noResult.tsv");
	}

	@Test
	public void getAsXlsxTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx", HttpConstants.MIME_TYPE_XLSX, "attachment; filename=resdetectqntlmt.xlsx");
	}

	@Test
	public void getAsXlsxZipTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=resdetectqntlmt.zip");
	}

	@Test
	public void getAsXmlTest() throws Exception {
		getAsXmlTest(endpoint + "xml", HttpConstants.MIME_TYPE_XML, "attachment; filename=resdetectqntlmt.xml", "resDetectQntLmt/resdetectqntlmt.xml", "resDetectQntLmt/noResult.xml");
	}

	@Test
	public void getAsXmlZipGetTest() throws Exception {
		getAsXmlZipTest(endpoint + "xml&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=resdetectqntlmt.zip", "resDetectQntLmt/resdetectqntlmt.xml", "resdetectqntlmt.xml", "resDetectQntLmt/noResult.xml");
	}

	@Test
	public void getAllParametersTest() throws Exception {
		getAllParametersTest(endpoint + "csv", HttpConstants.MIME_TYPE_CSV, "attachment; filename=resdetectqntlmt.csv", "resDetectQntLmt/filtered.csv");
	}

	@Test
	public void postGetCountTest() throws Exception {
		String urlPrefix = HttpConstants.RES_DETECT_QNT_LMT_SEARCH_ENPOINT + "/count?mimeType=";
		String compareObject = "{\"" + HttpConstants.HEADER_TOTAL_SITE_COUNT + "\":\"" + FILTERED_TOTAL_SITE_COUNT
				+ "\",\"" + HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT + "\":\"" + FILTERED_TOTAL_ACTIVITY_COUNT
				+ "\",\"" + HttpConstants.HEADER_TOTAL_RESULT_COUNT + "\":\"" + FILTERED_TOTAL_RESULT_COUNT
				+ "\",\"" + HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT + "\":\"" + FILTERED_TOTAL_RES_DETECT_QNT_LMT_COUNT
				+ "\",\"" + HEADER_STORET_SITE_COUNT + "\":\"" + FILTERED_STORET_SITE_COUNT
				+ "\",\"" + HEADER_STORET_ACTIVITY_COUNT + "\":\"" + FILTERED_STORET_ACTIVITY_COUNT
				+ "\",\"" + HEADER_STORET_RESULT_COUNT + "\":\"" + FILTERED_STORET_RESULT_COUNT
				+ "\",\"" + HEADER_STORET_RES_DETECT_QNT_LMT_COUNT + "\":\"" + FILTERED_STORET_RES_DETECT_QNT_LMT_COUNT
				+ "\"}";
		postGetCountTest(urlPrefix, compareObject);
	}

	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_COUNT_MINUS_1))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_COUNT_MINUS_1))
				.andExpect(header().string(HEADER_BIODATA_SITE_COUNT, BIODATA_SITE_COUNT))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT, TOTAL_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_NWIS_ACTIVITY_COUNT, NWIS_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_ACTIVITY_COUNT, STEWARDS_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_STORET_ACTIVITY_COUNT, STORET_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_BIODATA_ACTIVITY_COUNT, BIODATA_ACTIVITY_COUNT))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_RESULT_COUNT, TOTAL_RESULT_COUNT))
				.andExpect(header().string(HEADER_NWIS_RESULT_COUNT, NWIS_RESULT_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_RESULT_COUNT, STEWARDS_RESULT_COUNT))
				.andExpect(header().string(HEADER_STORET_RESULT_COUNT, STORET_RESULT_COUNT))
				.andExpect(header().string(HEADER_BIODATA_RESULT_COUNT, BIODATA_RESULT_COUNT))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT, TOTAL_RES_DETECT_QNT_LMT_COUNT))
				.andExpect(header().string(HEADER_NWIS_RES_DETECT_QNT_LMT_COUNT, NWIS_RES_DETECT_QNT_LMT_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_RES_DETECT_QNT_LMT_COUNT, STEWARDS_RES_DETECT_QNT_LMT_COUNT))
				.andExpect(header().string(HEADER_STORET_RES_DETECT_QNT_LMT_COUNT, STORET_RES_DETECT_QNT_LMT_COUNT))
				.andExpect(header().string(HEADER_BIODATA_RES_DETECT_QNT_LMT_COUNT, BIODATA_RES_DETECT_QNT_LMT_COUNT));
	}

	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, FILTERED_TOTAL_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, FILTERED_STORET_SITE_COUNT))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT, FILTERED_TOTAL_ACTIVITY_COUNT))
				.andExpect(header().string(HEADER_STORET_ACTIVITY_COUNT, FILTERED_STORET_ACTIVITY_COUNT))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_RESULT_COUNT, FILTERED_TOTAL_RESULT_COUNT))
				.andExpect(header().string(HEADER_STORET_RESULT_COUNT, FILTERED_STORET_RESULT_COUNT))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT, FILTERED_TOTAL_RES_DETECT_QNT_LMT_COUNT))
				.andExpect(header().string(HEADER_STORET_RES_DETECT_QNT_LMT_COUNT, FILTERED_STORET_RES_DETECT_QNT_LMT_COUNT));
	}

	public ResultActions noResultHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, "0"))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT, "0"))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_RESULT_COUNT, "0"))

				.andExpect(header().string(HttpConstants.HEADER_TOTAL_RES_DETECT_QNT_LMT_COUNT, "0"));
	}


}
