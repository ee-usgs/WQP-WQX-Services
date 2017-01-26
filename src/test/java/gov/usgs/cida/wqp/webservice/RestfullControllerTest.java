package gov.usgs.cida.wqp.webservice;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
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
public class RestfullControllerTest extends BaseControllerIntegrationTest {

	protected String endpoint = HttpConstants.ACTIVITY_METRIC_REST_ENPOINT.replace("{activity}", getActivity()[0]) + "?mimeType=";

	@Test
	public void getAsCsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "csv", HttpConstants.MIME_TYPE_CSV, "attachment; filename=activitymetric.csv", "activityMetric/activityMetricRest.csv", "activityMetric/noResultActivityMetric.csv");
	}

	@Test
	public void getAsCsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "csv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=activitymetric.zip", "activityMetric/activityMetricRest.csv", "activitymetric.csv", "activityMetric/noResultActivityMetric.csv");
	}

	@Test
	public void getAsTsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "tsv", HttpConstants.MIME_TYPE_TSV, "attachment; filename=activitymetric.tsv", "activityMetric/activityMetricRest.tsv", "activityMetric/noResultActivityMetric.tsv");
	}

	@Test
	public void getAsTsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "tsv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=activitymetric.zip", "activityMetric/activityMetricRest.tsv", "activitymetric.tsv", "activityMetric/noResultActivityMetric.tsv");
	}

	@Test
	public void getAsXlsxTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx", HttpConstants.MIME_TYPE_XLSX, "attachment; filename=activitymetric.xlsx");
	}

	@Test
	public void getAsXlsxZipTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=activitymetric.zip");
	}

	@Test
	public void getAsXmlTest() throws Exception {
		getAsXmlTest(endpoint + "xml", HttpConstants.MIME_TYPE_XML, "attachment; filename=activitymetric.xml", "activityMetric/activityMetricRest.xml", "activityMetric/noResultActivityMetric.xml");
	}

	@Test
	public void getAsXmlZipGetTest() throws Exception {
		getAsXmlZipTest(endpoint + "xml&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=activitymetric.zip", "activityMetric/activityMetricRest.xml", "activitymetric.xml", "activityMetric/noResultActivityMetric.xml");
	}

	@Override
	protected void getAsDelimitedTest(String url, String mimeType, String contentDisposition, String compareFile, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), rtn.getResponse().getContentAsString());

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(noResultFile), rtn.getResponse().getContentAsString());
	}

	@Override
	protected void getAsDelimitedZipTest(String url, String mimeType, String contentDisposition, String compareFile, String zipEntry, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(compareFile), extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(getCompareFile(noResultFile), extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry));
	}

	@Override
	protected void getAsXlsxTest(String url, String mimeType, String contentDisposition) throws Exception {
		//TODO validate spreadsheet and split out zipped.
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition));
	}

	@Override
	protected void getAsXmlTest(String url, String mimeType, String contentDisposition, String compareFile, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(rtn.getResponse().getContentAsString()));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(noResultFile)), harmonizeXml(rtn.getResponse().getContentAsString()));
	}

	@Override
	protected void getAsXmlZipTest(String url, String mimeType, String contentDisposition, String compareFile, String zipEntry, String noResultFile) throws Exception {
		assertEquals("", unFilteredHeaderCheck(callMockHead(url, mimeType, contentDisposition)).andReturn().getResponse().getContentAsString());

		MvcResult rtn = unFilteredHeaderCheck(callMockGet(url, mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(compareFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));

		rtn = noResultHeaderCheck(callMockGet(url + getNoResultParameters(), mimeType, contentDisposition)).andReturn();
		assertEquals(harmonizeXml(getCompareFile(noResultFile)), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), zipEntry)));
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
