package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.Map;

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
import gov.usgs.cida.wqp.mapping.ActivityMetricColumn;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.TestActivityMetricMap;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ActivityMetricStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(ActivityMetricStreamingTest.class);

	@Autowired 
	IStreamingDao streamingDao;

	TestResultHandler handler;
	FilterParameters filter;
	NameSpace nameSpace = NameSpace.ACTIVITY_METRIC;

	public static final String[] STEWARDS_1 = new String[]{STEWARDS, "1_1_type_identifier"};
	public static final String[] STEWARDS_2 = new String[]{STEWARDS, "1_2_type_identifier"};
	public static final String[] STEWARDS_3 = new String[]{STEWARDS, "1_3_type_identifier"};
	public static final String[] NWIS_1 = new String[]{NWIS, "2_1_type_identifier"};
	public static final String[] NWIS_2 = new String[]{NWIS, "2_2_type_identifier"};
	public static final String[] NWIS_3 = new String[]{NWIS, "2_3_type_identifier"};
	public static final String[] STORET_1 = new String[]{STORET, "3_1_type_identifier"};
	public static final String[] STORET_2 = new String[]{STORET, "3_2_type_identifier"};
	public static final String[] STORET_3 = new String[]{STORET, "3_3_type_identifier"};
	public static final String[] STORET_4 = new String[]{STORET, "GENERA RICHNESS"};
	public static final String[] STORET_5A = new String[]{STORET, "% EPT Individuals"};
	public static final String[] STORET_5B = new String[]{STORET, "% EPT Taxa"};
	public static final String[] STORET_5C = new String[]{STORET, "CHI %"};
	public static final String[] STORET_5D = new String[]{STORET, "GATHERER %"};
	public static final String[] STORET_5E = new String[]{STORET, "MPTV"};
	public static final String[] STORET_5F = new String[]{STORET, "SHREDDER %"};
	public static final String[] STORET_6 = new String[]{STORET, "3_6_type_identifier"};
	public static final String[] STORET_7 = new String[]{STORET, "3_7_type_identifier"};
	public static final String[] STORET_8 = new String[]{STORET, "3_8_type_identifier"};
	public static final String[] STORET_9 = new String[]{STORET, "3_9_type_identifier"};
	public static final String[] STORET_10 = new String[]{STORET, "3_10_type_identifier"};
	public static final String[] STORET_11 = new String[]{STORET, "3_11_type_identifier"};
	public static final String[] STORET_12 = new String[]{STORET, "3_12_type_identifier"};
	public static final String[] STORET_13 = new String[]{STORET, "3_13_type_identifier"};
	public static final String[] STORET_14 = new String[]{STORET, "3_14_type_identifier"};
	public static final String[] STORET_15 = new String[]{STORET, "3_15_type_identifier"};
	public static final String[] STORET_16 = new String[]{STORET, "3_16_type_identifier"};
	public static final String[] BIODATA_1 = new String[]{BIODATA, "4_1_type_identifier"};

	public static final int ACTIVITY_METRIC_COLUMN_COUNT = TestActivityMetricMap.ACTIVITY_METRIC.keySet().size();

	public void init() {
		handler = new TestResultHandler();
		filter = new FilterParameters();
	}

	public void cleanup() {
		handler = null;
		filter = null;
	}

	@Test
	public void testHarness() {
		nullParameterTest();
		emptyParameterTest();
		allDataSortedTest();
		activityTest();
		analyticalMethodTest();
		assemblageTest();
		avoidTest();
		bboxTest();
		characteristicNameTest();
		characteristicTypeTest();
		countryTest();
		countyTest();
		huc2Test();
		huc3Test();
		huc4Test();
		huc5Test();
		huc6Test();
		huc7Test();
		huc8Test();
		huc10Test();
		huc12Test();
		minActivitiesTest();
		minResultsTest();
		nldiUrlTest();
		organizationTest();
		pcodeTest();
		projectTest();
		providersTest();
		sampleMediaTest();
		siteIdTest();
		manySitesTest();
		siteTypeTest();
		startDateHiTest();
		startDateLoTest();
		stateTest();
		subjectTaxonomicNameTest();
		withinTest();
		multipleParameterStationSumTest();
		multipleParameterActivityTest();
		multipleParameterActivitySumTest();
		multipleParameterActivitySumStationSumTest();
		multipleParameterResultSumTest();
		multipleParameterResultSumStationSumTest();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Only need Activity (and possibly a lookup table)

	public void nullParameterTest() {
		init();
		streamingDao.stream(nameSpace, null, handler);
		assertEquals(TOTAL_ACTIVITY_METRIC_COUNT, String.valueOf(handler.getResults().size()));
		cleanup();
	}

	public void emptyParameterTest() {
		init();
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(TOTAL_ACTIVITY_METRIC_COUNT, String.valueOf(handler.getResults().size()));
		cleanup();
	}

	public void allDataSortedTest() {
		init();
		filter.setSorted("yes");
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		//Validate the number AND order of results.
		assertEquals(TOTAL_ACTIVITY_METRIC_COUNT, String.valueOf(results.size()));
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
		assertStoret5A(results.get(13));
		assertStoret5B(results.get(14));
		assertStoret5C(results.get(15));
		assertStoret5D(results.get(16));
		assertStoret5E(results.get(17));
		assertStoret5F(results.get(18));
		assertStoret4(results.get(19));
		assertStoret11(results.get(20));
		assertStoret12(results.get(21));
		assertStoret13(results.get(22));
		assertStoret2(results.get(23));
		assertStoret16(results.get(24));
		assertStoret1(results.get(25));
		assertStoret3(results.get(26));
		assertBiodata1(results.get(27));
		cleanup();
	}

	public void activityTest() {
		init();
		filter.setActivity(getActivity());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsActivityMetric(results, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
		cleanup();
	}

	public void avoidTest() {
		init();
		filter.setCommand(getCommand());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(STORET_ACTIVITY_METRIC_COUNT, String.valueOf(results.size()));
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15,
				STORET_16);
		cleanup();
	}

	public void countryTest() {
		init();
		filter.setCountrycode(getCountry());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(24, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, BIODATA_1);
		cleanup();
	}

	public void countyTest() {
		init();
		filter.setCountycode(getCounty());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(23, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16);
		cleanup();
	}

	public void huc2Test() {
		init();
		filter.setHuc(getHuc2());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C,
				STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_14, STORET_15);
		cleanup();
	}

	public void huc3Test() {
		init();
		filter.setHuc(getHuc3());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C,
				STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_14, STORET_15);
		cleanup();
	}

	public void huc4Test() {
		init();
		filter.setHuc(getHuc4());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
		cleanup();
	}

	public void huc5Test() {
		init();
		filter.setHuc(getHuc5());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
		cleanup();
	}

	public void huc6Test() {
		init();
		filter.setHuc(getHuc6());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
		cleanup();
	}

	public void huc7Test() {
		init();
		filter.setHuc(getHuc7());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
		cleanup();
	}

	public void huc8Test() {
		init();
		filter.setHuc(getHuc8());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsActivityMetric(results, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
		cleanup();
	}

	public void huc10Test() {
		init();
		filter.setHuc(getHuc10());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsActivityMetric(results, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
		cleanup();
	}

	public void huc12Test() {
		init();
		filter.setHuc(getHuc12());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsActivityMetric(results, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
		cleanup();
	}

	public void nldiUrlTest() {
		init();
		try {
			filter.setNldiSites(getManySiteId());
			streamingDao.stream(nameSpace, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(13, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_11,
				STORET_12, STORET_13, STORET_16);
		cleanup();
	}

	public void organizationTest() {
		init();
		filter.setOrganization(getOrganization());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(23, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16);
		cleanup();
	}

	public void projectTest() {
		init();
		filter.setProject(getProject());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(14, results.size());
		assertContainsActivityMetric(results, STEWARDS_1,  STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, BIODATA_1);
		cleanup();
	}

	public void providersTest() {
		init();
		filter.setProviders(getProviders());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(27, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
		cleanup();
	}

	public void sampleMediaTest() {
		init();
		filter.setSampleMedia(getSampleMedia());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(26, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A, STORET_5B,
				STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
		cleanup();
	}

	public void startDateHiTest() {
		init();
		filter.setStartDateHi(getStartDateHi());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(26, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A, STORET_5B,
				STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
		cleanup();
	}

	public void startDateLoTest() {
		init();
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(17, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1, BIODATA_1);
		cleanup();
	}

	public void siteIdTest() {
		init();
		filter.setSiteid(getSiteid());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
		cleanup();
	}

	public void manySitesTest() {
		init();
		try {
			filter.setSiteid(getManySiteId());
			streamingDao.stream(nameSpace, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(13, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_11,
				STORET_12, STORET_13, STORET_16);
		cleanup();
	}

	public void siteTypeTest() {
		init();
		filter.setSiteType(getSiteType());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(27, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A,
				STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1, BIODATA_1);
		cleanup();
	}

	public void stateTest() {
		init();
		filter.setStatecode(getState());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(23, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_1);
		cleanup();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Activity_Sum

	public void minActivitiesTest() {
		init();
		filter.setMinactivities(getMinActivities());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(24, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C,
				STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_15, STORET_16);
		cleanup();
	}

	public void minResultsTest() {
		init();
		filter.setMinresults(getMinResults());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(22, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E,
				STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
		cleanup();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Station_Sum

	public void bboxTest() {
		init();
		filter.setBBox(getBBox());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(22, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_4, STORET_5A,
				STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
		cleanup();
	}

	public void withinTest() {
		init();
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(24, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_11, STORET_12, STORET_13, STORET_16);
		cleanup();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Result_Sum

	public void analyticalMethodTest() {
		init();
		filter.setAnalyticalmethod(getAnalyticalMethod());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(12, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
		cleanup();
	}

	public void assemblageTest() {
		init();
		filter.setAssemblage(getAssemblage());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
		cleanup();
	}

	public void characteristicNameTest() {
		init();
		filter.setCharacteristicName(getCharacteristicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
		cleanup();
	}

	public void characteristicTypeTest() {
		init();
		filter.setCharacteristicType(getCharacteristicType());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(12, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
		cleanup();
	}

	public void pcodeTest() {
		init();
		filter.setPCode(getPcode());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivityMetric(results, NWIS_3, STORET_1, STORET_2, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
		cleanup();
	}

	public void subjectTaxonomicNameTest() {
		init();
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
		cleanup();
	}

	public void multipleParameterActivityTest() {
		init();
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
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
		cleanup();
	}

	public void multipleParameterStationSumTest() {
		init();
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
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
		cleanup();
	}

	public void multipleParameterActivitySumTest() {
		init();
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
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
		cleanup();
	}

	public void multipleParameterActivitySumStationSumTest() {
		init();
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
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
		cleanup();
	}

	public void multipleParameterResultSumTest() {
		init();
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
		assertContainsActivityMetric(results, STORET_1, STORET_14, STORET_15, STORET_16);
		cleanup();
	}

	public void multipleParameterResultSumStationSumTest() {
		init();
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
		assertContainsActivityMetric(results, STORET_1, STORET_16);
		cleanup();
	}

	public static void assertStewards1(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STEWARDS_1[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStewards2(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STEWARDS_2[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStewards3(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STEWARDS_3[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertNwis1(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(NWIS_1[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertNwis2(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(NWIS_2[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertNwis3(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(NWIS_3[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret1(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_1[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public void assertStoret2(Map<String, Object> row) {
		assertMapIsAsExpected(TestActivityMetricMap.ACTIVITY_METRIC, row);
	}

	public static void assertStoret3(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_3[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret4(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_4[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5A(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5A[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5B(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5B[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5C(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5C[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5D(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5D[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5E(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5E[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5F(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5F[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret6(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_6[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret7(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_7[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret8(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_8[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret9(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_9[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret10(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_10[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret11(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_11[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret12(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_12[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret13(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_13[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret14(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_14[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret15(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_15[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret16(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_16[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertBiodata1(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(BIODATA, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BIODATA_1[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public void assertContainsActivityMetric(LinkedList<Map<String, Object>> results, String[]...  activityMetrics) {
		for (Map<String, Object> result : results) {
			LOG.debug(BaseColumn.KEY_DATA_SOURCE + ":" + result.get(BaseColumn.KEY_DATA_SOURCE) + "/" 
						+ ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER + ":" +  result.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
		}

		for (String[] i : activityMetrics) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(BaseColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(BaseColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(result.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER).toString())) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(BaseColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals("Double check expected size", activityMetrics.length, results.size());
	}

}
