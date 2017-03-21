package gov.usgs.cida.wqp.webservice.station;

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
import gov.usgs.cida.wqp.webservice.BaseControllerIntegrationTest;

@Category(DBIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class StationControllerIntTest extends BaseControllerIntegrationTest {

	public static final String NAME = "station";
	protected static final boolean POSTABLE = true;
	protected String endpoint = HttpConstants.STATION_SEARCH_ENPOINT + "?mimeType=";

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
	public void getAsXmlZipTest() throws Exception {
		getAsXmlZipTest(endpoint + XML_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, XML, NAME, POSTABLE);
	}

	@Test
	public void getAsKmlTest() throws Exception {
		getAsXmlTest(endpoint + KML, HttpConstants.MIME_TYPE_KML, KML, NAME, POSTABLE);
	}

	@Test
	public void getAsKmlZipTest() throws Exception {
		getAsKmzTest(endpoint + KML_AND_ZIP, HttpConstants.MIME_TYPE_KMZ, KMZ, NAME, POSTABLE);
	}

	@Test
	public void getAsKmzTest() throws Exception {
		getAsKmzTest(endpoint + KMZ, HttpConstants.MIME_TYPE_KMZ, KMZ, NAME, POSTABLE);
	}

	@Test
	public void getAsGeoJsonTest() throws Exception {
		getAsJsonTest(endpoint + GEOJSON, HttpConstants.MIME_TYPE_GEOJSON, GEOJSON, NAME, POSTABLE);
	}

	@Test
	public void getAsGeoJsonZipTest() throws Exception {
		getAsJsonZipTest(endpoint + GEOJSON_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, GEOJSON, NAME, POSTABLE);
	}

	@Test
	public void getAllParametersTest() throws Exception {
		getAllParametersTest(endpoint + CSV, HttpConstants.MIME_TYPE_CSV, CSV, NAME, POSTABLE);
	}

	@Test
	public void postGetCountTest() throws Exception {
		String urlPrefix = HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=";
		String compareObject = "{\"" + HttpConstants.HEADER_TOTAL_SITE_COUNT + "\":\"" + FILTERED_TOTAL_SITE_COUNT
				+ "\",\"" + HEADER_STORET_SITE_COUNT + "\":\"" + FILTERED_STORET_SITE_COUNT
				+ "\"}";
		postGetCountTest(urlPrefix, compareObject);
	}

	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string(HttpConstants.HEADER_TOTAL_SITE_COUNT, TOTAL_SITE_COUNT))
				.andExpect(header().string(HEADER_NWIS_SITE_COUNT, NWIS_SITE_COUNT))
				.andExpect(header().string(HEADER_STEWARDS_SITE_COUNT, STEWARDS_SITE_COUNT))
				.andExpect(header().string(HEADER_STORET_SITE_COUNT, STORET_SITE_COUNT))
				.andExpect(header().string(HEADER_BIODATA_SITE_COUNT, BIODATA_SITE_COUNT));
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

}
