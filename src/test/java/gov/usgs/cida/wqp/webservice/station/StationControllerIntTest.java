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

	protected String endpoint = "/" + HttpConstants.STATION_SEARCH_ENPOINT + "?mimeType=";

	@Test
	public void getAsCsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "csv", HttpConstants.MIME_TYPE_CSV, "attachment; filename=station.csv", "station/station.csv");
	}

	@Test
	public void getAsCsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "csv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=station.zip", "station/station.csv", "station.csv");
	}

	@Test
	public void getAsTsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "tsv", HttpConstants.MIME_TYPE_TSV, "attachment; filename=station.tsv", "station/station.tsv");
	}

	@Test
	public void getAsTsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "tsv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=station.zip", "station/station.tsv", "station.tsv");
	}

	@Test
	public void getAsXlsxTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx", HttpConstants.MIME_TYPE_XLSX, "attachment; filename=station.xlsx");
	}

	@Test
	public void getAsXlsxZipTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=station.zip");
	}

	@Test
	public void getAsXmlTest() throws Exception {
		getAsXmlTest(endpoint + "xml", HttpConstants.MIME_TYPE_XML, "attachment; filename=station.xml", "station/station.xml");
	}

	@Test
	public void getAsXmlZipTest() throws Exception {
		getAsXmlZipTest(endpoint + "xml&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=station.zip", "station/station.xml", "station.xml");
	}

	@Test
	public void getAsKmlTest() throws Exception {
		getAsXmlTest(endpoint + "kml", HttpConstants.MIME_TYPE_KML, "attachment; filename=station.kml", "station/station.kml");
	}

	@Test
	public void getAsKmlZipTest() throws Exception {
		getAsXmlZipTest(endpoint + "kml&zip=yes", HttpConstants.MIME_TYPE_KMZ, "attachment; filename=station.kmz", "station/station.kml", "station.kml");
	}

	@Test
	public void getAsKmzTest() throws Exception {
		getAsXmlZipTest(endpoint + "kmz", HttpConstants.MIME_TYPE_KMZ, "attachment; filename=station.kmz", "station/station.kml", "station.kml");
	}

	@Test
	public void getAsGeoJsonTest() throws Exception {
		getAsJsonTest(endpoint + "geojson", HttpConstants.MIME_TYPE_GEOJSON, "attachment; filename=station.geojson", "station/station.json");
	}

	@Test
	public void getAsGeoJsonZipTest() throws Exception {
		getAsJsonZipTest(endpoint + "geojson&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=station.zip", "station/station.json", "station.geojson");
	}

	@Test
	public void getAllParametersTest() throws Exception {
		getAllParametersTest(endpoint + "csv", HttpConstants.MIME_TYPE_CSV, "attachment; filename=station.csv", "station/filteredStation.csv");
	}

	@Test
	public void postGetCountTest() throws Exception {
		String urlPrefix = "/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=";
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

}
