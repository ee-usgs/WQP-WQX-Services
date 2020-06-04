package gov.usgs.wma.wqp.webservice.result;

import static gov.usgs.wma.wqp.openapi.model.ActivityCountJson.HEADER_BIODATA_ACTIVITY_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ActivityCountJson.HEADER_NWIS_ACTIVITY_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ActivityCountJson.HEADER_STEWARDS_ACTIVITY_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ActivityCountJson.HEADER_STORET_ACTIVITY_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ResultCountJson.HEADER_BIODATA_RESULT_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ResultCountJson.HEADER_NWIS_RESULT_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ResultCountJson.HEADER_STEWARDS_RESULT_COUNT;
import static gov.usgs.wma.wqp.openapi.model.ResultCountJson.HEADER_STORET_RESULT_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_BIODATA_SITE_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_STEWARDS_SITE_COUNT;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_STORET_SITE_COUNT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.springframework.test.web.servlet.ResultActions;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.util.HttpConstants;
import gov.usgs.wma.wqp.webservice.BaseControllerIntegrationTest;

public abstract class BaseResultControllerIT extends BaseControllerIntegrationTest {

	public void getAsCsvTest(String endpoint, Profile profile, boolean postable) throws Exception {
		getAsDelimitedTest(endpoint + CSV, HttpConstants.MIME_TYPE_CSV, CSV, profile, postable);
	}

	public void getAsCsvZipTest(String endpoint, Profile profile, boolean postable) throws Exception {
		getAsDelimitedZipTest(endpoint + CSV_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, CSV, profile, postable);
	}

	public void getAsTsvTest(String endpoint, Profile profile, boolean postable) throws Exception {
		getAsDelimitedTest(endpoint + TSV, HttpConstants.MIME_TYPE_TSV, TSV, profile, postable);
	}

	public void getAsTsvZipTest(String endpoint, Profile profile, boolean postable) throws Exception {
		getAsDelimitedZipTest(endpoint + TSV_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, TSV, profile, postable);
	}

	public void getAsXlsxTest(String endpoint, Profile profile, boolean postable) throws Exception {
		getAsXlsxTest(endpoint + XLSX, HttpConstants.MIME_TYPE_XLSX, XLSX, profile, postable);
	}

	public void getAsXlsxZipTest(String endpoint, Profile profile, boolean postable) throws Exception {
		getAsXlsxZipTest(endpoint + XLSX_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, XLSX, profile, postable);
	}

	public void getAsXmlTest(String endpoint, Profile profile, boolean postable) throws Exception {
		getAsXmlTest(endpoint + XML, HttpConstants.MIME_TYPE_XML, XML, profile, postable);
	}

	public void getAsXmlZipTest(String endpoint, Profile profile, boolean postable) throws Exception {
		getAsXmlZipTest(endpoint + XML_AND_ZIP, HttpConstants.MIME_TYPE_ZIP, XML, profile, postable);
	}

	public void getAllParametersTest(String endpoint, Profile profile, boolean postable) throws Exception {
		getAllParametersTest(endpoint + CSV, HttpConstants.MIME_TYPE_CSV, CSV, profile, postable);
	}

	public void postGetCountTest(Profile profile) throws Exception {
		String urlPrefix = HttpConstants.RESULT_SEARCH_ENDPOINT + "/count?mimeType=";
		String compareObject = "{\"" + HttpConstants.HEADER_TOTAL_SITE_COUNT + "\":\"" + FILTERED_TOTAL_SITE_COUNT
				+ "\",\"" + HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT + "\":\"" + FILTERED_TOTAL_ACTIVITY_COUNT
				+ "\",\"" + HttpConstants.HEADER_TOTAL_RESULT_COUNT + "\":\"" + FILTERED_TOTAL_RESULT_COUNT
				+ "\",\"" + HEADER_STORET_SITE_COUNT + "\":\"" + FILTERED_STORET_SITE_COUNT
				+ "\",\"" + HEADER_STORET_ACTIVITY_COUNT + "\":\"" + FILTERED_STORET_ACTIVITY_COUNT
				+ "\",\"" + HEADER_STORET_RESULT_COUNT + "\":\"" + FILTERED_STORET_RESULT_COUNT
				+ "\"}";
		postGetCountTest(urlPrefix, compareObject, profile);
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
