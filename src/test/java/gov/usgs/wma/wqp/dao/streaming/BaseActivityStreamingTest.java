package gov.usgs.wma.wqp.dao.streaming;

import static gov.usgs.wma.wqp.openapi.model.StationCountJson.NWIS;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.STEWARDS;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.STORET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.wma.wqp.dao.FilteredActivityDaoTest;
import gov.usgs.wma.wqp.dao.NameSpace;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.ActivityColumn;
import gov.usgs.wma.wqp.mapping.BaseColumn;

public abstract class BaseActivityStreamingTest extends FilteredActivityDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(BaseActivityStreamingTest.class);

	@Autowired 
	public IStreamingDao streamingDao;

	public NameSpace nameSpace = NameSpace.ACTIVITY;

	public static final Object[] STEWARDS_1 = new Object[]{STEWARDS_ID, BigDecimal.ONE};
	public static final Object[] STEWARDS_2 = new Object[]{STEWARDS_ID, BigDecimal.valueOf(2)};
	public static final Object[] STEWARDS_3 = new Object[]{STEWARDS_ID, BigDecimal.valueOf(3)};
	public static final Object[] NWIS_1 = new Object[]{NWIS_ID, BigDecimal.ONE};
	public static final Object[] NWIS_2 = new Object[]{NWIS_ID, BigDecimal.valueOf(2)};
	public static final Object[] NWIS_3 = new Object[]{NWIS_ID, BigDecimal.valueOf(3)};
	public static final Object[] NWIS_4 = new Object[]{NWIS_ID, BigDecimal.valueOf(4)};
	public static final Object[] STORET_1 = new Object[]{STORET_ID, BigDecimal.ONE};
	public static final Object[] STORET_2 = new Object[]{STORET_ID, BigDecimal.valueOf(2)};
	public static final Object[] STORET_3 = new Object[]{STORET_ID, BigDecimal.valueOf(3)};
	public static final Object[] STORET_4 = new Object[]{STORET_ID, BigDecimal.valueOf(4)};
	public static final Object[] STORET_5 = new Object[]{STORET_ID, BigDecimal.valueOf(5)};
	public static final Object[] STORET_6 = new Object[]{STORET_ID, BigDecimal.valueOf(6)};
	public static final Object[] STORET_7 = new Object[]{STORET_ID, BigDecimal.valueOf(7)};
	public static final Object[] STORET_8 = new Object[]{STORET_ID, BigDecimal.valueOf(8)};
	public static final Object[] STORET_9 = new Object[]{STORET_ID, BigDecimal.valueOf(9)};
	public static final Object[] STORET_10 = new Object[]{STORET_ID, BigDecimal.TEN};
	public static final Object[] STORET_11 = new Object[]{STORET_ID, BigDecimal.valueOf(11)};
	public static final Object[] STORET_12 = new Object[]{STORET_ID, BigDecimal.valueOf(12)};
	public static final Object[] STORET_13 = new Object[]{STORET_ID, BigDecimal.valueOf(13)};
	public static final Object[] STORET_14 = new Object[]{STORET_ID, BigDecimal.valueOf(14)};
	public static final Object[] STORET_15 = new Object[]{STORET_ID, BigDecimal.valueOf(15)};
	public static final Object[] STORET_16 = new Object[]{STORET_ID, BigDecimal.valueOf(16)};

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
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
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
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, NWIS_4, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16);
	}

	public void providersTest() {
		List<Map<String, Object>> results = providersTest(nameSpace, 20);
		assertContainsActivity(results, NWIS_1, NWIS_2, NWIS_3, NWIS_4, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	public void resultTest() {
		resultTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	public void sampleMediaTest() {
		List<Map<String, Object>> results = sampleMediaTest(nameSpace, 21);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, NWIS_4, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6,
				STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
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
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_4, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15,
				STORET_1);
	}

	public void siteUrlBaseTest() {
		siteUrlBaseTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
	}

	public void sortedTest(int columnCount) {
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_COUNT));
		assertStewards1(columnCount, results.get(0));
		assertStewards2(columnCount, results.get(1));
		assertStewards3(columnCount, results.get(2));
		assertNwis4(columnCount, results.get(3));
		assertNwis1(columnCount, results.get(4));
		assertNwis2(columnCount, results.get(5));
		assertNwis3(columnCount, results.get(6));
		assertStoret14(columnCount, results.get(7));
		assertStoret15(columnCount, results.get(8));
		assertStoret10(columnCount, results.get(9));
		assertStoret6(columnCount, results.get(10));
		assertStoret7(columnCount, results.get(11));
		assertStoret9(columnCount, results.get(12));
		assertStoret8(columnCount, results.get(13));
		assertStoret5(columnCount, results.get(14));
		assertStoret4(columnCount, results.get(15));
		assertStoret11(columnCount, results.get(16));
		assertStoret12(columnCount, results.get(17));
		assertStoret13(columnCount, results.get(18));
		assertStoret2(columnCount, results.get(19));
		assertStoret16(columnCount, results.get(20));
		assertStoret1(columnCount, results.get(21));
		assertStoret3(columnCount, results.get(22));
	}

	public void startDateHiTest() {
		List<Map<String, Object>> results = startDateHiTest(nameSpace, 21);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, NWIS_4, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6,
				STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void startDateLoTest() {
		List<Map<String, Object>> results = startDateLoTest(nameSpace, 17);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1);
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
		assertContainsActivity(results, NWIS_4, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
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
		assertContainsActivity(results, NWIS_4, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void multipleParameterActivityTest() {
		List<Map<String, Object>> results = multipleParameterActivityTest(nameSpace, 9);
		assertContainsActivity(results, STEWARDS_1, STEWARDS_3, NWIS_1, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	public void multipleParameterActivitySumTest() {
		List<Map<String, Object>> results = multipleParameterActivitySumTest(nameSpace, 6);
		assertContainsActivity(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	public void multipleParameterResultSumTest() {
		List<Map<String, Object>> results = multipleParameterResultSumTest(nameSpace, 2);
		assertContainsActivity(results, STORET_1, STORET_16);
	}

	public static void assertStewards1(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.ONE, row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStewards-1", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStewards2(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(2), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStewards-2", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStewards3(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(3), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStewards-3", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertNwis1(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.ONE, row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityNwis-1", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertNwis2(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(2), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityNwis-2", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertNwis3(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(3), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityNwis-3", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertNwis4(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(4), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityNwis-4", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret1(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.ONE, row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity-1", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret2(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(2), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity-2", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret3(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(3), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity-3", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret4(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(4), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("WIDNR_WQX-7788480-4", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret5(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(5), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("WIDNR_WQX-7788475-5", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret6(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(6), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("21NYDECA_WQX-020002-6", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret7(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(7), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("21NYDECA_WQX-020206-7", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret8(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(8), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("21NYDECA_WQX-0210EN-8", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret9(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(9), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("21NYDECA_WQX-020610-9", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret10(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.TEN, row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStoret-10", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public abstract void assertStoret11(int columnCount, Map<String, Object> row);

	public static void assertStoret12(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(12), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity-12", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret13(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(13), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity-13", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret14(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(14), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStoret-14", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret15(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(15), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activityStoret-15", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertStoret16(int columnCount, Map<String, Object> row) {
		assertEquals(columnCount, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BigDecimal.valueOf(16), row.get(ActivityColumn.KEY_ACTIVITY_ID));
		assertEquals("activity-16", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public void assertContainsActivity(List<Map<String, Object>> results, Object[]...  activityIds) {
		for (Map<String, Object> result : results) {
			LOG.debug(ActivityColumn.KEY_DATA_SOURCE_ID + ":" + result.get(ActivityColumn.KEY_DATA_SOURCE_ID) + "/" + ActivityColumn.KEY_ACTIVITY_ID + ":" +  result.get(ActivityColumn.KEY_ACTIVITY_ID));
		}

		for (Object[] i : activityIds) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (((Integer) i[0]).compareTo(((Integer) result.get(ActivityColumn.KEY_DATA_SOURCE_ID))) == 0
						&& ((BigDecimal) i[1]).compareTo(((BigDecimal) result.get(ActivityColumn.KEY_ACTIVITY_ID))) == 0) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(ActivityColumn.KEY_DATA_SOURCE_ID + ":" + i[0] + "/" + ActivityColumn.KEY_ACTIVITY_ID + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals(results.size(), activityIds.length, "Double check expected size");
	}

	protected void assertActivityMetricURL(Map<String, Object> row) {
		assertUrl(ActivityColumn.KEY_ACTIVITY_METRIC_URL, row);
	}

	protected void assertActivityFileURL(Map<String, Object> row) {
		assertUrl(ActivityColumn.KEY_ACTIVITY_FILE_URL, row);
	}

	protected void assertActivityGroupURL(Map<String, Object> row) {
		assertUrl(ActivityColumn.KEY_ACTIVITY_GROUP_URL, row);
	}
}
