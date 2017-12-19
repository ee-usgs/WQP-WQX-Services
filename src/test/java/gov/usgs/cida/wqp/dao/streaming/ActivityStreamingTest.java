package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Arrays;
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
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.TestActivityMap;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ActivityStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(ActivityStreamingTest.class);

	@Autowired 
	IStreamingDao streamingDao;

	TestResultHandler handler;
	FilterParameters filter;
	NameSpace nameSpace = NameSpace.ACTIVITY;

	public static final BigDecimal[] STEWARDS_1 = new BigDecimal[]{BigDecimal.ONE, BigDecimal.ONE};
	public static final BigDecimal[] STEWARDS_2 = new BigDecimal[]{BigDecimal.ONE, BigDecimal.valueOf(2)};
	public static final BigDecimal[] STEWARDS_3 = new BigDecimal[]{BigDecimal.ONE, BigDecimal.valueOf(3)};
	public static final BigDecimal[] NWIS_1 = new BigDecimal[]{BigDecimal.valueOf(2), BigDecimal.ONE};
	public static final BigDecimal[] NWIS_2 = new BigDecimal[]{BigDecimal.valueOf(2), BigDecimal.valueOf(2)};
	public static final BigDecimal[] NWIS_3 = new BigDecimal[]{BigDecimal.valueOf(2), BigDecimal.valueOf(3)};
	public static final BigDecimal[] STORET_1 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.ONE};
	public static final BigDecimal[] STORET_2 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(2)};
	public static final BigDecimal[] STORET_3 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(3)};
	public static final BigDecimal[] STORET_4 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(4)};
	public static final BigDecimal[] STORET_5 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(5)};
	public static final BigDecimal[] STORET_6 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(6)};
	public static final BigDecimal[] STORET_7 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(7)};
	public static final BigDecimal[] STORET_8 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(8)};
	public static final BigDecimal[] STORET_9 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(9)};
	public static final BigDecimal[] STORET_10 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.TEN};
	public static final BigDecimal[] STORET_11 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(11)};
	public static final BigDecimal[] STORET_12 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(12)};
	public static final BigDecimal[] STORET_13 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(13)};
	public static final BigDecimal[] STORET_14 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(14)};
	public static final BigDecimal[] STORET_15 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(15)};
	public static final BigDecimal[] STORET_16 = new BigDecimal[]{BigDecimal.valueOf(3), BigDecimal.valueOf(16)};
	public static final BigDecimal[] BIODATA_1 = new BigDecimal[]{BigDecimal.valueOf(4), BigDecimal.ONE};

	public static final int ACTIVITY_COLUMN_COUNT = TestActivityMap.ACTIVITY.keySet().size();

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
	//Only need Activity (and possibly a lookup table)

	@Test
	public void nullParameterTest() {
		streamingDao.stream(nameSpace, null, handler);
		assertEquals(TOTAL_ACTIVITY_COUNT, String.valueOf(handler.getResults().size()));
	}

	@Test
	public void emptyParameterTest() {
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(TOTAL_ACTIVITY_COUNT, String.valueOf(handler.getResults().size()));
	}
	
	@Test
	public void testActivityMetricURL() {
		filter.setSiteUrlBase(getSiteUrlBase());
		filter.setProviders(Arrays.asList("BIODATA"));
		streamingDao.stream(nameSpace, filter, handler);
		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals("Size should be 1", 1, results.size());
		Map<String, Object> row = results.get(0);
		assertEquals("ActivityMetricURL incorrect", "http://siteUrlBase/activities/activityBiodata/activitymetrics", row.get(ActivityColumn.KEY_ACTIVITY_METRIC_URL));
	}

	@Test
	public void allDataSortedTest() {
		filter.setSorted("yes");
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertStoret11(results.get(15));

		//Validate the number AND order of results.
		assertEquals(TOTAL_ACTIVITY_COUNT, String.valueOf(results.size()));
		assertStewards1(results.get(0));
		assertStewards2(results.get(1));
		assertStewards3(results.get(2));
		assertNwis1(results.get(3));
		assertNwis2(results.get(4));
		assertNwis3(results.get(5));
		assertStoret14(results.get(6));
		assertStoret15(results.get(7));
		assertStoret10(results.get(8));
		assertStoret6(results.get(9));
		assertStoret7(results.get(10));
		assertStoret9(results.get(11));
		assertStoret8(results.get(12));
		assertStoret5(results.get(13));
		assertStoret4(results.get(14));
		assertStoret12(results.get(16));
		assertStoret13(results.get(17));
		assertStoret2(results.get(18));
		assertStoret16(results.get(19));
		assertStoret1(results.get(20));
		assertStoret3(results.get(21));
		assertBiodata1(results.get(22));
	}

	@Test
	public void avoidTest() {
		filter.setCommand(getCommand());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(STORET_ACTIVITY_COUNT, String.valueOf(results.size()));
		assertContainsActivity(results, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void countryTest() {
		filter.setCountrycode(getCountry());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(19, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void countyTest() {
		filter.setCountycode(getCounty());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(18, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void huc2Test() {
		filter.setHuc(getHuc2());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5, STORET_10, STORET_14,
				STORET_15);
	}

	@Test
	public void huc4Test() {
		filter.setHuc(getHuc4());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsActivity(results, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5);
	}

	@Test
	public void huc6Test() {
		filter.setHuc(getHuc6());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsActivity(results, NWIS_1, NWIS_2, STORET_4, STORET_5);
	}

	@Test
	public void huc8Test() {
		filter.setHuc(getHuc8());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, STORET_4, STORET_5);
	}

	@Test
	public void nldiUrlTest() {
		try {
			filter.setNldiSites(getManySiteId());
			streamingDao.stream(nameSpace, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsActivity(results, STORET_1, STORET_2, STORET_4, STORET_5, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	@Test
	public void organizationTest() {
		filter.setOrganization(getOrganization());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(18, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void projectTest() {
		filter.setProject(getProject());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(14, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void providersTest() {
		filter.setProviders(getProviders());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(22, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	@Test
	public void sampleMediaTest() {
		filter.setSampleMedia(getSampleMedia());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(21, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6,
				STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16,
				BIODATA_1);
	}

	@Test
	public void startDateHiTest() {
		filter.setStartDateHi(getStartDateHi());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(21, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6,
				STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16,
				BIODATA_1);
	}

	@Test
	public void startDateLoTest() {
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(17, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1, BIODATA_1);
	}

	@Test
	public void siteIdTest() {
		filter.setSiteid(getSiteid());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void manySitesTest() {
		try {
			filter.setSiteid(getManySiteId());
			streamingDao.stream(nameSpace, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsActivity(results, STORET_1, STORET_2, STORET_4, STORET_5, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	@Test
	public void siteTypeTest() {
		filter.setSiteType(getSiteType());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(22, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15,
				STORET_1, BIODATA_1);
	}

	@Test
	public void stateTest() {
		filter.setStatecode(getState());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(18, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Activity_Sum

	@Test
	public void minActivitiesTest() {
		filter.setMinactivities(getMinActivities());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(19, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7,
				STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void minResultsTest() {
		filter.setMinresults(getMinResults());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(17, results.size());
		assertContainsActivity(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Station_Sum

	@Test
	public void bboxTest() {
		filter.setBBox(getBBox());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(17, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_4, STORET_5,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void withinTest() {
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(19, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Result_Sum

	@Test
	public void analyticalMethodTest() {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(12, results.size());
		assertContainsActivity(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	@Test
	public void assemblageTest() {
		filter.setAssemblage(getAssemblage());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsActivity(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void characteristicNameTest() {
		filter.setCharacteristicName(getCharacteristicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivity(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void characteristicTypeTest() {
		filter.setCharacteristicType(getCharacteristicType());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(12, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	@Test
	public void pcodeTest() {
		filter.setPCode(getPcode());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivity(results, NWIS_3, STORET_1, STORET_2, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivity(results, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void multipleParameterActivityTests() {
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setOrganization(getOrganization());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(12, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	@Test
	public void multipleParameterStationSumTests() {
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setOrganization(getOrganization());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setWithin(getWithin());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	@Test
	public void multipleParameterActivitySumTests() {
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsActivity(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void multipleParameterActivitySumStationSumTests() {
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setWithin(getWithin());
		streamingDao.stream(nameSpace, filter, handler);
		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsActivity(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	@Test
	public void multipleParameterResultSumTests() {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setCommand(getCommand());
		filter.setAssemblage(getAssemblage());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setNldiSites(getNldiSites());
		filter.setOrganization(getOrganization());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setPCode(getPcode());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsActivity(results, STORET_1, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void multipleParameterResultSumStationSumTests() {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setCommand(getCommand());
		filter.setAssemblage(getAssemblage());
		filter.setBBox(getBBox());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setNldiSites(getNldiSites());
		filter.setOrganization(getOrganization());
		filter.setPCode(getPcode());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSampleMedia(getSampleMedia());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setStatecode(getState());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		filter.setWithin(getWithin());
		streamingDao.stream(nameSpace, filter, handler);
		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, STORET_1, STORET_16);
	}

	public static void assertStewards1(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.ONE, row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStewards", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStewards2(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(2), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStewards", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStewards3(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(3), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStewards", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertNwis1(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.ONE, row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityNwis", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertNwis2(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(2), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityNwis", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertNwis3(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(3), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityNwis", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret1(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.ONE, row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret2(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(2), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret3(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(3), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret4(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(4), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("WIDNR_WQX-7788480", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret5(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(5), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("WIDNR_WQX-7788475", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret6(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(6), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("21NYDECA_WQX-020002", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret7(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(7), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("21NYDECA_WQX-020206", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret8(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(8), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("21NYDECA_WQX-0210EN", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret9(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(9), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("21NYDECA_WQX-020610", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret10(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.TEN, row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStoret", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public void assertStoret11(Map<String, Object> row) {
		assertMapIsAsExpected(TestActivityMap.ACTIVITY, row);
	}

	public static void assertStoret12(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(12), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret13(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(13), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret14(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(14), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStoret", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret15(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(15), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStoret", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret16(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(16), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertBiodata1(Map<String, Object> row) {
		assertEquals(ACTIVITY_COLUMN_COUNT, row.keySet().size());
		assertEquals(BIODATA, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.ONE, row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityBiodata", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public void assertContainsActivity(LinkedList<Map<String, Object>> results, BigDecimal[]...  activityIds) {
		for (Map<String, Object> result : results) {
			LOG.debug(ActivityColumn.KEY_DATA_SOURCE_ID + ":" + result.get(ActivityColumn.KEY_DATA_SOURCE_ID) + "/" + ActivityColumn.KEY_ACTIVITY_ID + ":" +  result.get(ActivityColumn.KEY_ACTIVITY_ID));
		}

		for (BigDecimal[] i : activityIds) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (i[0].compareTo(((BigDecimal) result.get(ActivityColumn.KEY_DATA_SOURCE_ID))) == 0
						&& i[1].compareTo(((BigDecimal) result.get(ActivityColumn.KEY_ACTIVITY_ID))) == 0) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(ActivityColumn.KEY_DATA_SOURCE_ID + ":" + i[0] + "/" + ActivityColumn.KEY_ACTIVITY_ID + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals("Double check expected size", results.size(), activityIds.length);
	}

}
