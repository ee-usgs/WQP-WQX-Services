package gov.usgs.cida.wqp.webservice.result;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerIntegrationTest;

@Category(DBIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/result.xml")
})
public class BiologicalResultControllerIntTest extends BaseControllerIntegrationTest {

	protected String endpoint = "/" + HttpConstants.RESULT_SEARCH_ENPOINT + "?" 
			+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL + "&mimeType=";

	@Test
	public void getAsCsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "csv", HttpConstants.MIME_TYPE_CSV, "attachment; filename=biologicalresult.csv", "result/bioResult.csv");
	}

	@Test
	public void getAsCsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "csv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=biologicalresult.zip", "result/bioResult.csv", "biologicalresult.csv");
	}

	@Test
	public void getAsTsvTest() throws Exception {
		getAsDelimitedTest(endpoint + "tsv", HttpConstants.MIME_TYPE_TSV, "attachment; filename=biologicalresult.tsv", "result/bioResult.tsv");
	}

	@Test
	public void getAsTsvZipTest() throws Exception {
		getAsDelimitedZipTest(endpoint + "tsv&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=biologicalresult.zip", "result/bioResult.tsv", "biologicalresult.tsv");
	}

	@Test
	public void getAsXlsxTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx", HttpConstants.MIME_TYPE_XLSX, "attachment; filename=biologicalresult.xlsx");
	}

	@Test
	public void getAsXlsxZipTest() throws Exception {
		getAsXlsxTest(endpoint + "xlsx&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=biologicalresult.zip");
	}

	@Test
	public void getAsXmlTest() throws Exception {
		getAsXmlTest(endpoint + "xml", HttpConstants.MIME_TYPE_XML, "attachment; filename=biologicalresult.xml", "result/bioResult.xml");
	}

	@Test
	public void getAsXmlZipGetTest() throws Exception {
		getAsXmlZipTest(endpoint + "xml&zip=yes", HttpConstants.MIME_TYPE_ZIP, "attachment; filename=biologicalresult.zip", "result/bioResult.xml", "biologicalresult.xml");
	}

	@Test
	public void getAllParametersTest() throws Exception {
		//TODO A test case that has results
		getAllParametersTest(endpoint + "csv", HttpConstants.MIME_TYPE_CSV, "attachment; filename=biologicalresult.csv", "result/filteredBioResult.csv");
	}

	@Test
	public void postGetCountTest() throws Exception {
		//TODO A test case that has results
		String urlPrefix = "/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=";
		String compareObject = "{\"Total-Site-Count\":\"0\",\"Total-Activity-Count\":\"0\",\"Total-Result-Count\":\"0\"}";
		postGetCountTest(urlPrefix, compareObject);
	}

	public ResultActions unFilteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string("Total-Site-Count", "6"))
				.andExpect(header().string("NWIS-Site-Count", "2"))
				.andExpect(header().string("STEWARDS-Site-Count", "2"))
				.andExpect(header().string("STORET-Site-Count", "2"))
				.andExpect(header().string("Total-Result-Count", "40"))
				.andExpect(header().string("NWIS-Result-Count", "12"))
				.andExpect(header().string("STEWARDS-Result-Count", "24"))
				.andExpect(header().string("STORET-Result-Count", "4"));
	}

	public ResultActions filteredHeaderCheck(ResultActions resultActions) throws Exception {
		return resultActions
				.andExpect(header().string("Total-Site-Count", "0"))
				.andExpect(header().string("Total-Result-Count", "0"));
	}

}
