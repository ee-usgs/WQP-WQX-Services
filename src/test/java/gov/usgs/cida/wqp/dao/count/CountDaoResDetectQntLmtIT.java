package gov.usgs.cida.wqp.dao.count;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.dao.CountDao;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.CountColumn;
import gov.usgs.cida.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, CountDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoResDetectQntLmtIT extends BaseCountDaoTest {

	protected NameSpace nameSpace = NameSpace.RES_DETECT_QNT_LMT;
	protected boolean includeActivity = true;
	protected boolean includeResults = true;

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
		multipleParameterActivitySumTest();
		multipleParameterResultSumTest();
	}

	public void activityTest() {
		List<Map<String, Object>> counts = activityTest(nameSpace, 2);
		assertStationResults(counts, "1", null, null, "1", null);
		assertActivityResults(counts, "1", null, null, "1", null);
		assertResDetectQntLmtResults(counts, "3", null, null, "3", null);
	}

	public void analyticalMethodTest() {
		List<Map<String, Object>> counts = analyticalMethodTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "22", "6", null, "16", null);
	}

	public void assemblageTest() {
		List<Map<String, Object>> counts = assemblageTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "17", null, null, "16", "1");
	}

	public void avoidTest() {
		List<Map<String, Object>> counts = avoidTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, STORET_RES_DETECT_QNT_LMT_COUNT, null, null, STORET_RES_DETECT_QNT_LMT_COUNT, null);
	}

	public void bboxTest() {
		List<Map<String, Object>> counts = bboxTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "38", "7", "9", "22", null);
	}

	public void characteristicNameTest() {
		List<Map<String, Object>> counts = characteristicNameTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "16", null, null, "16", null);
	}

	public void characteristicTypeTest() {
		List<Map<String, Object>> counts = characteristicTypeTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "21", null, "5", "16", null);
	}

	public void countryTest() {
		List<Map<String, Object>> counts = countryTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "40", "7", "9", "23", "1");
	}

	public void countyTest() {
		List<Map<String, Object>> counts = countyTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "39", "7", "9", "23", null);
	}

	public void emptyParameterTest() {
		List<Map<String, Object>> counts = emptyParameterTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, TOTAL_RES_DETECT_QNT_LMT_COUNT, NWIS_RES_DETECT_QNT_LMT_COUNT, STEWARDS_RES_DETECT_QNT_LMT_COUNT, STORET_RES_DETECT_QNT_LMT_COUNT, BIODATA_RES_DETECT_QNT_LMT_COUNT);
	}

	public void huc2Test() {
		List<Map<String, Object>> counts = huc2Test(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "27", "7", "9", "11", null);
	}

	public void huc3Test() {
		List<Map<String, Object>> counts = huc3Test(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "27", "7", "9", "11", null);
	}

	public void huc4Test() {
		List<Map<String, Object>> counts = huc4Test(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "14", "7", null, "7", null);
	}

	public void huc5Test() {
		List<Map<String, Object>> counts = huc5Test(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "14", "7", null, "7", null);
	}

	public void huc6Test() {
		List<Map<String, Object>> counts = huc6Test(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "13", "6", null, "7", null);
	}

	public void huc7Test() {
		List<Map<String, Object>> counts = huc7Test(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "13", "6", null, "7", null);
	}

	public void huc8Test() {
		List<Map<String, Object>> counts = huc8Test(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "7", null, null, "7", null);
	}

	public void huc10Test() {
		List<Map<String, Object>> counts = huc10Test(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "7", null, null, "7", null);
	}

	public void huc12Test() {
		List<Map<String, Object>> counts = huc12Test(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "7", null, null, "7", null);
	}

	public void mimeTypeTest() {
		mimeTypeTest(nameSpace, includeActivity, includeResults);
	}

	public void minActivitiesTest() {
		List<Map<String, Object>> counts = minActivitiesTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "63", "6", "5", "52", null);
	}

	public void minResultsTest() {
		List<Map<String, Object>> counts = minResultsTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "58", "6", null, "52", null);
	}

	public void nldiSitesTest() {
		List<Map<String, Object>> counts = nldiSitesTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "18", null, null, "18", null);
	}

	public void nldiUrlTest() {
		List<Map<String, Object>> counts = nldiUrlTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, TOTAL_RES_DETECT_QNT_LMT_COUNT, NWIS_RES_DETECT_QNT_LMT_COUNT, STEWARDS_RES_DETECT_QNT_LMT_COUNT, STORET_RES_DETECT_QNT_LMT_COUNT, BIODATA_RES_DETECT_QNT_LMT_COUNT);
	}

	public void nullParameterTest() {
		List<Map<String, Object>> counts = nullParameterTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, TOTAL_RES_DETECT_QNT_LMT_COUNT, NWIS_RES_DETECT_QNT_LMT_COUNT, STEWARDS_RES_DETECT_QNT_LMT_COUNT, STORET_RES_DETECT_QNT_LMT_COUNT, BIODATA_RES_DETECT_QNT_LMT_COUNT);
	}

	public void organizationTest() {
		List<Map<String, Object>> counts = organizationTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "39", "7", "9", "23", null);
	}

	public void pcodeTest() {
		List<Map<String, Object>> counts = pcodeTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "16", "1", null, "15", null);
	}

	public void projectTest() {
		List<Map<String, Object>> counts = projectTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "28", "6", "6", "15", "1");
	}

	public void providersTest() {
		List<Map<String, Object>> counts = providersTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "69", "7", "9", "53", null);
	}

	public void resultTest() {
		List<Map<String, Object>> counts = resultTest(nameSpace, 2);
		assertStationResults(counts, "1", null, null, "1", null);
		assertActivityResults(counts, "1", null, null, "1", null);
		assertResultResults(counts, "1", null, null, "1", null);
		assertResDetectQntLmtResults(counts, "1", null, null, "1", null);
	}

	public void sampleMediaTest() {
		List<Map<String, Object>> counts = sampleMediaTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "66", "6", "6", "53", "1");
	}

	public void siteIdTest() {
		List<Map<String, Object>> counts = siteIdTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "32", "7", "9", "16", null);
	}

	public void siteIdLargeListTest() {
		List<Map<String, Object>> counts = siteIdLargeListTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "18", null, null, "18", null);
	}

	public void siteTypeTest() {
		List<Map<String, Object>> counts = siteTypeTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "69", "6", "9", "53", "1");
	}

	public void siteUrlBaseTest() {
		List<Map<String, Object>> counts = siteUrlBaseTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, TOTAL_RES_DETECT_QNT_LMT_COUNT, NWIS_RES_DETECT_QNT_LMT_COUNT, STEWARDS_RES_DETECT_QNT_LMT_COUNT, STORET_RES_DETECT_QNT_LMT_COUNT, BIODATA_RES_DETECT_QNT_LMT_COUNT);
	}

	public void sortedTest() {
		List<Map<String, Object>> counts = sortedTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, TOTAL_RES_DETECT_QNT_LMT_COUNT, NWIS_RES_DETECT_QNT_LMT_COUNT, STEWARDS_RES_DETECT_QNT_LMT_COUNT, STORET_RES_DETECT_QNT_LMT_COUNT, BIODATA_RES_DETECT_QNT_LMT_COUNT);
	}

	public void startDateHiTest() {
		List<Map<String, Object>> counts = startDateHiTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "66", "6", "6", "53", "1");
	}

	public void startDateLoTest() {
		List<Map<String, Object>> counts = startDateLoTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "33", "7", "9", "16", "1");
	}

	public void stateTest() {
		List<Map<String, Object>> counts = stateTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "39", "7", "9", "23", null);
	}

	public void subjectTaxonomicNameTest() {
		List<Map<String, Object>> counts = subjectTaxonomicNameTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "16", null, null, "15", "1");
	}

	public void withinTest() {
		List<Map<String, Object>> counts = withinTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "65", "7", "9", "49", null);
	}

	public void zipTest() {
		List<Map<String, Object>> counts = zipTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, TOTAL_RES_DETECT_QNT_LMT_COUNT, NWIS_RES_DETECT_QNT_LMT_COUNT, STEWARDS_RES_DETECT_QNT_LMT_COUNT, STORET_RES_DETECT_QNT_LMT_COUNT, BIODATA_RES_DETECT_QNT_LMT_COUNT);
	}

	public void multipleParameterStationSumTest() {
		List<Map<String, Object>> counts = multipleParameterStationSumTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "17", "6", null, "11", null);
	}

	public void multipleParameterActivitySumTest() {
		List<Map<String, Object>> counts = multipleParameterActivitySumTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "11", null, null, "11", null);
	}

	public void multipleParameterResultSumTest() {
		List<Map<String, Object>> counts = multipleParameterResultSumTest(nameSpace, includeActivity, includeResults);
		assertResDetectQntLmtResults(counts, "7", null, null, "7", null);
	}

	private void assertResDetectQntLmtResults(List<Map<String, Object>> counts,
			String total, String nwis, String stewards, String storet, String biodata) {
		assertResults(counts, CountColumn.KEY_RES_DETECT_QNT_LMT_COUNT, total, nwis, stewards, storet, biodata);
	}

}
