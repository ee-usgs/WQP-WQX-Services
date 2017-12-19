package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.mapping.xml.StationKml;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public abstract class BaseStationStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(BaseStationStreamingTest.class);

	@Autowired 
	IStreamingDao streamingDao;

	TestResultHandler handler;
	FilterParameters filter;

	public static final String ARS_SITE = "ARSSite";
	public static final String NWIS_SITE = "NWISSite";
	public static final String STORET_SITE = "STORETSite";
	public static final String BIODATA_SITE = "Unknown";

	public static final String[] STEWARDS_36 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", ARS_SITE};
	public static final String[] STEWARDS_46 = new String[]{STEWARDS, "ARS-IAWC-IAWC410", ARS_SITE};
	public static final String[] NWIS_1353690 = new String[]{NWIS, "USGS-05425700", NWIS_SITE};
	public static final String[] NWIS_1360035 = new String[]{NWIS, "USGS-431925089002701", NWIS_SITE};
	public static final String[] STORET_777 = new String[]{STORET, "organization-siteId2", STORET_SITE};
	public static final String[] STORET_888 = new String[]{STORET, "organization-siteId", STORET_SITE};
	public static final String[] STORET_999 = new String[]{STORET, "organization-siteId3", STORET_SITE};
	public static final String[] STORET_1383 = new String[]{STORET, "WIDNR_WQX-113086", STORET_SITE};
	public static final String[] STORET_436723 = new String[]{STORET, "WIDNR_WQX-10030952", STORET_SITE};
	public static final String[] STORET_504707 = new String[]{STORET, "21NYDECA_WQX-ONTARIO-02", STORET_SITE};
	public static final String[] STORET_1043441 = new String[]{STORET, "11NPSWRD-BICA_MFG_B", STORET_SITE};
	public static final String[] BIODATA_61233184 = new String[]{BIODATA, "USGS-11421000", BIODATA_SITE};
	public static final String[] BIODATA_433830088977331 = new String[]{BIODATA, "USGS-433830088977331", BIODATA_SITE};

	@Before
	public void init() {
		handler = new TestResultHandler();
		filter = new FilterParameters();
	}

	@After
	public void cleanup() {
		handler = null;
		filter = null;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Station_Sum 

	public void nullParameterTest(NameSpace nameSpace) {
		streamingDao.stream(nameSpace, null, handler);
		assertEquals(TOTAL_SITE_COUNT, String.valueOf(handler.getResults().size()));
	}

	public void emptyParameterTest(NameSpace nameSpace) {
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(TOTAL_SITE_COUNT, String.valueOf(handler.getResults().size()));
	}

	protected void mimeTypeTest(NameSpace nameSpace) {
		filter.setMimeType(JSON);
		streamingDao.stream(nameSpace, filter, handler);
		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(TOTAL_SITE_COUNT_GEOM, String.valueOf(results.size()));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		handler = new TestResultHandler();
		filter.setMimeType(GEOJSON);
		streamingDao.stream(nameSpace, filter, handler);
		results = handler.getResults();
		assertEquals(TOTAL_SITE_COUNT_GEOM, String.valueOf(results.size()));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		handler = new TestResultHandler();
		filter.setMimeType(KML);
		streamingDao.stream(nameSpace, filter, handler);
		results = handler.getResults();
		assertEquals(TOTAL_SITE_COUNT_GEOM, String.valueOf(results.size()));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		handler = new TestResultHandler();
		filter.setMimeType(KMZ);
		streamingDao.stream(nameSpace, filter, handler);
		results = handler.getResults();
		assertEquals(TOTAL_SITE_COUNT_GEOM, String.valueOf(results.size()));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		handler = new TestResultHandler();
		filter.setMimeType(CSV);
		streamingDao.stream(nameSpace, filter, handler);
		results = handler.getResults();
		assertEquals(TOTAL_SITE_COUNT, String.valueOf(results.size()));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184, BIODATA_433830088977331);
	}

	public void allDataSortedTest(NameSpace nameSpace, Map<String, Object> expectedMap) {
		Integer expectedColumnCount = expectedMap.keySet().size();
		filter.setSorted("yes");
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		//Validate the number AND order of results.
		assertEquals(TOTAL_SITE_COUNT, String.valueOf(results.size()));
		assertRow(results.get(0), STEWARDS_36, expectedColumnCount);
		assertRow(results.get(1), STEWARDS_46, expectedColumnCount);
		assertRow(results.get(2), NWIS_1353690, expectedColumnCount);
		assertRow(results.get(3), NWIS_1360035, expectedColumnCount);
		assertRow(results.get(4), STORET_1043441, expectedColumnCount);
		assertRow(results.get(5), STORET_504707, expectedColumnCount);
		assertRow(results.get(6), STORET_436723, expectedColumnCount);
		assertRow(results.get(7), STORET_1383, expectedColumnCount);
		assertStoret888(expectedMap, results.get(8));
		assertRow(results.get(9), STORET_777, expectedColumnCount);
		assertRow(results.get(10), STORET_999, expectedColumnCount);
		assertRow(results.get(11), BIODATA_61233184, expectedColumnCount);
	}

	public void avoidTest(NameSpace nameSpace) {
		filter.setCommand(getCommand());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(STORET_SITE_COUNT, String.valueOf(results.size()));
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707, STORET_1043441);
	}

	public void bboxTest(NameSpace nameSpace) {
		filter.setBBox(getBBox());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void countryTest(NameSpace nameSpace) {
		filter.setCountrycode(getCountry());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441,
				BIODATA_61233184);
	}

	public void countyTest(NameSpace nameSpace) {
		filter.setCountycode(getCounty());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void huc2Test(NameSpace nameSpace) {
		filter.setHuc(getHuc2());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void huc3Test(NameSpace nameSpace) {
		filter.setHuc(getHuc3());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void huc4Test(NameSpace nameSpace) {
		filter.setHuc(getHuc4());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsStation(results, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723);
	}

	public void huc5Test(NameSpace nameSpace) {
		filter.setHuc(getHuc5());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsStation(results, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723);
	}

	public void huc6Test(NameSpace nameSpace) {
		filter.setHuc(getHuc6());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, NWIS_1353690, STORET_1383, STORET_436723);
	}

	public void huc7Test(NameSpace nameSpace) {
		filter.setHuc(getHuc7());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, NWIS_1353690, STORET_1383, STORET_436723);
	}

	public void huc8Test(NameSpace nameSpace) {
		filter.setHuc(getHuc8());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	public void huc10Test(NameSpace nameSpace) {
		filter.setHuc(getHuc10());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	public void huc12Test(NameSpace nameSpace) {
		filter.setHuc(getHuc12());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	public void nldiUrlTest(NameSpace nameSpace) {
		try {
			filter.setNldiSites(getManySiteId());
			streamingDao.stream(nameSpace, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_1383);
	}

	public void organizationTest(NameSpace nameSpace) {
		filter.setOrganization(getOrganization());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void providersTest(NameSpace nameSpace) {
		filter.setProviders(getProviders());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441);
	}

	public void siteIdTest(NameSpace nameSpace) {
		filter.setSiteid(getSiteid());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_436723, STORET_1043441);
	}

	public void manySitesTest(NameSpace nameSpace) {
		try {
			filter.setSiteid(getManySiteId());
			streamingDao.stream(nameSpace, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_1383);
	}

	public void minActivitiesTest(NameSpace nameSpace) {
		filter.setMinactivities(getMinActivities());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsStation(results, STEWARDS_36, NWIS_1353690, STORET_777, STORET_888, STORET_1383, STORET_504707, STORET_1043441);
	}

	public void minResultsTest(NameSpace nameSpace) {
		filter.setMinresults(getMinResults());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888, STORET_1383, STORET_504707, STORET_1043441);
	}

	public void siteTypeTest(NameSpace nameSpace) {
		filter.setSiteType(getSiteType());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707, STORET_1043441,
				BIODATA_61233184);
	}

	public void stateTest(NameSpace nameSpace) {
		filter.setStatecode(getState());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void withinTest(NameSpace nameSpace) {
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Activity_Sum 

	public void projectTest(NameSpace nameSpace) {
		filter.setProject(getProject());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	public void sampleMediaTest(NameSpace nameSpace) {
		filter.setSampleMedia(getSampleMedia());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_504707, STORET_1043441,
				BIODATA_61233184);
	}

	public void startDateHiTest(NameSpace nameSpace) {
		filter.setStartDateHi(getStartDateHi());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_504707, STORET_1043441,
				BIODATA_61233184);
	}

	public void startDateLoTest(NameSpace nameSpace) {
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Result_Sum

	public void analyticalMethodTest(NameSpace nameSpace) {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	public void assemblageTest(NameSpace nameSpace) {
		filter.setAssemblage(getAssemblage());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	public void characteristicNameTest(NameSpace nameSpace) {
		filter.setCharacteristicName(getCharacteristicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	public void characteristicTypeTest(NameSpace nameSpace) {
		filter.setCharacteristicType(getCharacteristicType());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsStation(results, STEWARDS_36, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	public void pcodeTest(NameSpace nameSpace) {
		filter.setPCode(getPcode());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsStation(results, NWIS_1360035, STORET_777, STORET_888, STORET_1043441);
	}

	public void subjectTaxonomicNameTest(NameSpace nameSpace) {
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	public void multipleParameterStationSumTests(NameSpace nameSpace) {
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888);
	}

	public void multipleParameterActivitySumTests(NameSpace nameSpace) {
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsStation(results, STORET_777, STORET_888);
	}

	public void multipleParameterResultSumTest(NameSpace nameSpace) {
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());

		filter.setPCode(getPcode());
		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setAssemblage(getAssemblage());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsStation(results, STORET_777, STORET_888);
	}

	public static void assertRow(Map<String, Object> row, String[] station, int expectedColumnCount) {
		//The KML does not include data_source, it has a style_url
		assertEquals(expectedColumnCount, row.keySet().size());
		if (row.containsKey(StationColumn.KEY_DATA_SOURCE)) {
			assertEquals(station[0], row.get(BaseColumn.KEY_DATA_SOURCE));
		}
		assertEquals(station[1], row.get(StationColumn.KEY_SITE_ID));
		if (row.containsKey(StationKml.KEY_STYLE_URL)) {
			assertEquals(station[2], row.get(StationKml.KEY_STYLE_URL));
		}
	}

	public void assertStoret888(Map<String, Object> expectedRow, Map<String, Object> actualRow) {
		assertMapIsAsExpected(expectedRow, actualRow);
	}

	public void assertContainsStation(LinkedList<Map<String, Object>> results, String[]...  stations) {
		for (Map<String, Object> result : results) {
			LOG.debug(StationColumn.KEY_DATA_SOURCE + ":" + result.get(StationColumn.KEY_DATA_SOURCE) + "/" + StationColumn.KEY_SITE_ID + ":" +  result.get(StationColumn.KEY_SITE_ID));
		}

		for (String[] i : stations) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(StationColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(StationColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(result.get(StationColumn.KEY_SITE_ID).toString())) {
					isFound = true;
					break;
				} else {
					//The KML does not include data_source, it has a style_url
					if (result.containsKey(StationKml.KEY_STYLE_URL)
							&& i[2].equalsIgnoreCase(((String) result.get(StationKml.KEY_STYLE_URL)))
							&& i[1].equalsIgnoreCase(result.get(StationColumn.KEY_SITE_ID).toString())) {
						isFound = true;
						break;
					}
				}
			}
			if (!isFound) {
				fail(StationColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + StationColumn.KEY_SITE_ID + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals("Double check result set expected size", stations.length, results.size());
	}

}
