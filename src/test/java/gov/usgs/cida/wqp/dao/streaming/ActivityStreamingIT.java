package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.dao.FilteredActivityDaoTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.TestActivityMap;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ActivityStreamingIT extends FilteredActivityDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(ActivityStreamingIT.class);

	@Autowired 
	IStreamingDao streamingDao;

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

	@Test
	public void testHarness() {
		activityTest();
		analyticalMethodTest();
		assemblageTest();
		avoidTest();
		bboxTest();
		characteristicNameTest();
		characteristicTypeTest();
		countryTest();
		countyTest();
		emptyParameterTest();
		huc2Test();
		huc3Test();
		huc4Test();
		huc5Test();
		huc6Test();
		huc7Test();
		huc8Test();
		huc10Test();
		huc12Test();
		mimeTypeTest();
		minActivitiesTest();
		minResultsTest();
		nldiSitesTest();
		nldiUrlTest();
		nullParameterTest();
		organizationTest();
		pcodeTest();
		projectTest();
		providersTest();
		resultTest();
		sampleMediaTest();
		siteIdTest();
		siteIdLargeListTest();
		siteTypeTest();
		siteUrlBaseTest();
		sortedTest();
		startDateHiTest();
		startDateLoTest();
		stateTest();
		subjectTaxonomicNameTest();
		withinTest();
		zipTest();
		multipleParameterStationSumTest();
		multipleParameterActivityTest();
		multipleParameterActivitySumTest();
		multipleParameterActivitySumStationSumTest();
		multipleParameterResultSumTest();
		multipleParameterResultSumStationSumTest();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Only need Activity (and possibly a lookup table)

	public void activityTest() {
		activityTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	public void avoidTest() {
		List<Map<String, Object>> results = avoidTest(nameSpace, Integer.valueOf(STORET_ACTIVITY_COUNT));
		assertContainsActivity(results, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void countryTest() {
		List<Map<String, Object>> results = countryTest(nameSpace, 19);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void countyTest() {
		List<Map<String, Object>> results = countyTest(nameSpace, 18);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	public void huc2Test() {
		List<Map<String, Object>> results = huc2Test(nameSpace, 11);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5, STORET_10, STORET_14,
				STORET_15);
	}

	public void huc3Test() {
		List<Map<String, Object>> results = huc3Test(nameSpace, 11);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5, STORET_10, STORET_14,
				STORET_15);
	}

	public void huc4Test() {
		List<Map<String, Object>> results = huc4Test(nameSpace, 5);
		assertContainsActivity(results, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5);
	}

	public void huc5Test() {
		List<Map<String, Object>> results = huc5Test(nameSpace, 5);
		assertContainsActivity(results, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5);
	}

	public void huc6Test() {
		List<Map<String, Object>> results = huc6Test(nameSpace, 4);
		assertContainsActivity(results, NWIS_1, NWIS_2, STORET_4, STORET_5);
	}

	public void huc7Test() {
		List<Map<String, Object>> results = huc7Test(nameSpace, 4);
		assertContainsActivity(results, NWIS_1, NWIS_2, STORET_4, STORET_5);
	}

	public void huc8Test() {
		List<Map<String, Object>> results = huc8Test(nameSpace, 2);
		assertContainsActivity(results, STORET_4, STORET_5);
	}

	public void huc10Test() {
		List<Map<String, Object>> results = huc10Test(nameSpace, 2);
		assertContainsActivity(results, STORET_4, STORET_5);
	}

	public void huc12Test() {
		List<Map<String, Object>> results = huc12Test(nameSpace, 2);
		assertContainsActivity(results, STORET_4, STORET_5);
	}

	public void mimeTypeTest() {
		mimeTypeJsonTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
		mimeTypeGeoJsonTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
		mimeTypeKmlTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
		mimeTypeKmzTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
		mimeTypeCsvTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
		mimeTypeTsvTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
		mimeTypeXmlTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
		mimeTypeXlsxTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	public void nldiSitesTest() {
		List<Map<String, Object>> results = nldiSitesTest(nameSpace, 8);
		assertContainsActivity(results, STORET_1, STORET_2, STORET_4, STORET_5, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	public void nldiUrlTest() {
		nldiUrlTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	public void nullParameterTest() {
		nullParameterTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	public void organizationTest() {
		List<Map<String, Object>> results = organizationTest(nameSpace, 18);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void projectTest() {
		List<Map<String, Object>> results = projectTest(nameSpace, 14);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void providersTest() {
		List<Map<String, Object>> results = providersTest(nameSpace, 22);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	public void resultTest() {
		resultTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	public void sampleMediaTest() {
		List<Map<String, Object>> results = sampleMediaTest(nameSpace, 21);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6,
				STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16,
				BIODATA_1);
	}

	public void siteIdTest() {
		List<Map<String, Object>> results = siteIdTest(nameSpace, 16);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void siteIdLargeListTest() {
		List<Map<String, Object>> results = siteIdLargeListTest(nameSpace, 8);
		assertContainsActivity(results, STORET_1, STORET_2, STORET_4, STORET_5, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	public void siteTypeTest() {
		List<Map<String, Object>> results = siteTypeTest(nameSpace, 22);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15,
				STORET_1, BIODATA_1);
	}

	public void siteUrlBaseTest() {
		siteUrlBaseTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	public void sortedTest() {
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
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
		assertStoret11(results.get(15));
		assertStoret12(results.get(16));
		assertStoret13(results.get(17));
		assertStoret2(results.get(18));
		assertStoret16(results.get(19));
		assertStoret1(results.get(20));
		assertStoret3(results.get(21));
		assertBiodata1(results.get(22));
	}

	public void startDateHiTest() {
		List<Map<String, Object>> results = startDateHiTest(nameSpace, 21);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6,
				STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16,
				BIODATA_1);
	}

	public void startDateLoTest() {
		List<Map<String, Object>> results = startDateLoTest(nameSpace, 17);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1, BIODATA_1);
	}

	public void stateTest() {
		List<Map<String, Object>> results = stateTest(nameSpace, 18);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1);
	}

	public void zipTest() {
		zipTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Activity_Sum

	public void minActivitiesTest() {
		List<Map<String, Object>> results = minActivitiesTest(nameSpace, 19);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7,
				STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void minResultsTest() {
		List<Map<String, Object>> results = minResultsTest(nameSpace, 17);
		assertContainsActivity(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Station_Sum

	public void bboxTest() {
		List<Map<String, Object>> results = bboxTest(nameSpace, 17);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_4, STORET_5,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void withinTest() {
		List<Map<String, Object>> results = withinTest(nameSpace, 19);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Result_Sum

	public void analyticalMethodTest() {
		List<Map<String, Object>> results = analyticalMethodTest(nameSpace, 12);
		assertContainsActivity(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	public void assemblageTest() {
		List<Map<String, Object>> results = assemblageTest(nameSpace, 11);
		assertContainsActivity(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void characteristicNameTest() {
		List<Map<String, Object>> results = characteristicNameTest(nameSpace, 10);
		assertContainsActivity(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void characteristicTypeTest() {
		List<Map<String, Object>> results = characteristicTypeTest(nameSpace, 12);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	public void pcodeTest() {
		List<Map<String, Object>> results = pcodeTest(nameSpace, 10);
		assertContainsActivity(results, NWIS_3, STORET_1, STORET_2, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void subjectTaxonomicNameTest() {
		List<Map<String, Object>> results = subjectTaxonomicNameTest(nameSpace, 10);
		assertContainsActivity(results, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void multipleParameterActivityTest() {
		List<Map<String, Object>> results = multipleParameterActivityTest(nameSpace, 12);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	public void multipleParameterStationSumTest() {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 9);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	public void multipleParameterActivitySumTest() {
		List<Map<String, Object>> results = multipleParameterActivitySumTest(nameSpace, 8);
		assertContainsActivity(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void multipleParameterActivitySumStationSumTest() {
		List<Map<String, Object>> results = multipleParameterActivitySumStationSumTest(nameSpace, 6);
		assertContainsActivity(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	public void multipleParameterResultSumTest() {
		List<Map<String, Object>> results = multipleParameterResultSumTest(nameSpace, 4);
		assertContainsActivity(results, STORET_1, STORET_14, STORET_15, STORET_16);
	}

	public void multipleParameterResultSumStationSumTest() {
		List<Map<String, Object>> results = multipleParameterResultSumStationSumTest(nameSpace, 2);
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

	public void assertContainsActivity(List<Map<String, Object>> results, BigDecimal[]...  activityIds) {
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

	@Override
	public List<Map<String, Object>> callDao(NameSpace nameSpace, int expectedSize, FilterParameters filter) {
		TestResultHandler handler = new TestResultHandler();
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(expectedSize, handler.getResults().size());
		return handler.getResults();
	}

	@Override
	protected void assertSiteUrlBase(Map<String, Object> row) {
		if (null != row.get(ActivityColumn.KEY_ACTIVITY_METRIC_URL)) {
			assertTrue("ActivityMetricURL incorrect", row.get(ActivityColumn.KEY_ACTIVITY_METRIC_URL).toString().startsWith("http://siteUrlBase/activities/"));
		}
	}

}
