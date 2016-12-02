package gov.usgs.cida.wqp.dao.streaming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.BaseDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.parameter.Parameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class StationStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(StationStreamingTest.class);

	@Autowired 
	IStreamingDao streamingDao;

	TestResultHandler handler;
	Map<String, Object> parms;
	String nameSpace = BaseDao.STATION_NAMESPACE;

	public static final String[] STEWARDS_36 = new String[]{STEWARDS, "ARS-IAWC-IAWC225"};
	public static final String[] STEWARDS_46 = new String[]{STEWARDS, "ARS-IAWC-IAWC410"};
	public static final String[] NWIS_1353690 = new String[]{NWIS, "USGS-05425700"};
	public static final String[] NWIS_1360035 = new String[]{NWIS, "USGS-431925089002701"};
	public static final String[] STORET_777 = new String[]{STORET, "organization-siteId2"};
	public static final String[] STORET_888 = new String[]{STORET, "organization-siteId"};
	public static final String[] STORET_999 = new String[]{STORET, "organization-siteId3"};
	public static final String[] STORET_1383 = new String[]{STORET, "WIDNR_WQX-113086"};
	public static final String[] STORET_436723 = new String[]{STORET, "WIDNR_WQX-10030952"};
	public static final String[] STORET_504707 = new String[]{STORET, "21NYDECA_WQX-ONTARIO-02"};
	public static final String[] STORET_1043441 = new String[]{STORET, "11NPSWRD-BICA_MFG_B"};
	public static final String[] BIODATA_61233184 = new String[]{BIODATA, "USGS-11421000"};

	public static final int STATION_COLUMN_COUNT = 38;

	@Before
	public void init() {
		handler = new TestResultHandler();
		parms = new HashMap<>();
	}

	@After
	public void cleanup() {
		handler = null;
		parms = null;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Only need Station

	@Test
	public void allDataSortedTest() {
		parms.put(Parameters.SORTED.toString(), "yes");
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		//Validate the number AND order of results.
		assertEquals(12, results.size());
		assertStewards36(results.get(0));
		assertStewards46(results.get(1));
		assertNwis1353690(results.get(2));
		assertNwis1360035(results.get(3));
		assertStoret1043441(results.get(4));
		assertStoret504707(results.get(5));
		assertStoret436723(results.get(6));
		assertStoret1383(results.get(7));
		assertStoret888(results.get(8));
		assertStoret777(results.get(9));
		assertStoret999(results.get(10));
		assertBiodata61233184(results.get(11));
	}

	@Test
	public void bboxTest() {
		parms.put(Parameters.BBOX.toString(), getBBox());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_1383, STORET_436723, STORET_1043441);
	}

	@Test
	public void countryTest() {
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441, BIODATA_61233184);
	}

	@Test
	public void countyTest() {
		parms.put(Parameters.COUNTY.toString(), getCounty());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	@Test
	public void huc2Test() {
		parms.put(Parameters.HUC.toString(), new String[]{"07"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723, STORET_1043441);
	}

	@Test
	public void huc4Test() {
		parms.put(Parameters.HUC.toString(), new String[]{"0709"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsStation(results, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723);
	}

	@Test
	public void huc6Test() {
		parms.put(Parameters.HUC.toString(), new String[]{"070900"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, NWIS_1353690, STORET_1383, STORET_436723);
	}

	@Test
	public void huc8Test() {
		parms.put(Parameters.HUC.toString(), new String[]{"07090001"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	@Test
	public void nldiUrlTest() {
		try {
			parms.put(Parameters.NLDIURL.toString(), getSourceFile("manySites.txt").split(","));
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_1383);
	}

	@Test
	public void organizationTest() {
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	@Test
	public void providersTest() {
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707, STORET_1043441);
	}

	@Test
	public void siteIdTest() {
		parms.put(Parameters.SITEID.toString(), getSiteid());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_436723, STORET_1043441);
	}

	@Test
	public void manySitesTest() {
		try {
			parms.put(Parameters.SITEID.toString(), getSourceFile("manySites.txt").split(","));
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_1383);
	}

	@Test
	public void siteTypeTest() {
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707, STORET_1043441, BIODATA_61233184);
	}

	@Test
	public void stateTest() {
		parms.put(Parameters.STATE.toString(), getState());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	@Test
	public void withinTest() {
		parms.put(Parameters.WITHIN.toString(), getWithin());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Station_Sum 

	@Test
	public void minResultsTest() {
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsStation(results, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441, BIODATA_61233184);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Activity_Sum 

	@Test
	public void projectTest() {
		parms.put(Parameters.PROJECT.toString(), getProject());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, BIODATA_61233184);
	}

	@Test
	public void sampleMediaTest() {
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_504707, STORET_1043441, BIODATA_61233184);
	}

	@Test
	public void startDateHiTest() {
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_504707, STORET_1043441, BIODATA_61233184);
	}

	@Test
	public void startDateLoTest() {
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Result_Sum

	@Test
	public void analyticalMethodTest() {
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888, STORET_999);
	}

	@Test
	public void assemblageTest() {
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	@Test
	public void characteristicNameTest() {
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	@Test
	public void characteristicTypeTest() {
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsStation(results, STEWARDS_36, STORET_777, STORET_888, STORET_999);
	}

	@Test
	public void pcodeTest() {
		parms.put(Parameters.PCODE.toString(), getPcode());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, NWIS_1360035, STORET_777, STORET_888);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, BIODATA_61233184);
	}

	@Test
	public void multipleParameterStationSumTests() {
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.WITHIN.toString(), getWithin());

		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888);
	}

	@Test
	public void multipleParameterActivitySumTests() {
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.WITHIN.toString(), getWithin());

		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, STEWARDS_36, NWIS_1353690, STORET_777);
	}

	@Test
	public void multipleParameterResultSumTest() {
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.WITHIN.toString(), getWithin());

		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());

		parms.put(Parameters.PCODE.toString(), getPcode());
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsStation(results, STORET_777);
	}

	public static void assertStewards36(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STEWARDS_36[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertStewards46(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STEWARDS_46[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertNwis1353690(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(NWIS_1353690[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertNwis1360035(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(NWIS_1360035[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertStoret777(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_777[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertStoret888(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		for (String i : TestStationMap.STATION.keySet()) {
			assertEquals(i, TestStationMap.STATION.get(i), row.get(i));
		}
	}

	public static void assertStoret999(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_999[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertStoret1383(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_1383[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertStoret436723(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_436723[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertStoret504707(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_504707[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertStoret1043441(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_1043441[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public static void assertBiodata61233184(Map<String, Object> row) {
		assertEquals(STATION_COLUMN_COUNT, row.keySet().size());
		assertEquals(BIODATA, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BIODATA_61233184[1], row.get(StationColumn.KEY_SITE_ID));
	}

	public void assertContainsStation(LinkedList<Map<String, Object>> results, String[]...  stations) {
		for (Map<String, Object> result : results) {
			LOG.debug(StationColumn.KEY_DATA_SOURCE + ":" + result.get(StationColumn.KEY_DATA_SOURCE) + "/" + StationColumn.KEY_SITE_ID + ":" +  result.get(StationColumn.KEY_SITE_ID));
		}

		for (String[] i : stations) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (i[0].equalsIgnoreCase(((String) result.get(StationColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(result.get(StationColumn.KEY_SITE_ID).toString())) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(StationColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + StationColumn.KEY_SITE_ID + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals("Double check result set expected size", stations.length, results.size());
	}

}
