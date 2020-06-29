package gov.usgs.wma.wqp.dao.count;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.wma.wqp.CsvDataSetLoader;
import gov.usgs.wma.wqp.dao.CountDao;
import gov.usgs.wma.wqp.dao.NameSpace;
import gov.usgs.wma.wqp.mapping.CountColumn;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, CountDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoActivityMetricIT extends BaseCountDaoTest {

	protected NameSpace nameSpace = NameSpace.ACTIVITY_METRIC;
	protected boolean includeActivity = true;
	protected boolean includeResults = false;

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
		restTest();
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
		multipleParameterActivitySumTest();
		multipleParameterResultSumTest();
	}

	public void nullParameterTest() {
		List<Map<String, Object>> counts = nullParameterTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT);
	}

	public void emptyParameterTest() {
		List<Map<String, Object>> counts = emptyParameterTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT);
	}

	public void mimeTypeTest() {
		mimeTypeTest(nameSpace, includeActivity, includeResults);
	}

	public void activityTest() {
		List<Map<String, Object>> counts = activityTest(nameSpace, 2);
		assertStationResults(counts, "1", null, null, "1");
		assertActivityResults(counts, "1", null, null, "1");
		assertActivityMetricResults(counts, "6", null, null, "6");
	}

	public void analyticalMethodTest() {
		List<Map<String, Object>> counts = analyticalMethodTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "12", "2", null, "10");
	}

	public void assemblageTest() {
		List<Map<String, Object>> counts = assemblageTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "11", "1", null, "10");
	}

	public void avoidTest() {
		List<Map<String, Object>> counts = avoidTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, STORET_ACTIVITY_METRIC_COUNT, null, null, STORET_ACTIVITY_METRIC_COUNT);
	}

	public void bboxTest() {
		List<Map<String, Object>> counts = bboxTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "22", "3", "3", "16");
	}

	public void characteristicNameTest() {
		List<Map<String, Object>> counts = characteristicNameTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "10", null, null, "10");
	}

	public void characteristicTypeTest() {
		List<Map<String, Object>> counts = characteristicTypeTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "12", null, "2", "10");
	}

	public void countryTest() {
		List<Map<String, Object>> counts = countryTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "24", "4", "3", "17");
	}

	public void countyTest() {
		List<Map<String, Object>> counts = countyTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "23", "3", "3", "17");
	}

	public void huc2Test() {
		List<Map<String, Object>> counts = huc2Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "16", "3", "3", "10");
	}

	public void huc3Test() {
		List<Map<String, Object>> counts = huc3Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "16", "3", "3", "10");
	}

	public void huc4Test() {
		List<Map<String, Object>> counts = huc4Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "10", "3", null, "7");
	}

	public void huc5Test() {
		List<Map<String, Object>> counts = huc5Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "10", "3", null, "7");
	}

	public void huc6Test() {
		List<Map<String, Object>> counts = huc6Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "9", "2", null, "7");
	}

	public void huc7Test() {
		List<Map<String, Object>> counts = huc7Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "9", "2", null, "7");
	}

	public void huc8Test() {
		List<Map<String, Object>> counts = huc8Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "7", null, null, "7");
	}

	public void huc10Test() {
		List<Map<String, Object>> counts = huc10Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "7", null, null, "7");
	}

	public void huc12Test() {
		List<Map<String, Object>> counts = huc12Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "7", null, null, "7");
	}

	public void minActivitiesTest() {
		List<Map<String, Object>> counts = minActivitiesTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "24", "2", "2", "20");
	}

	public void minResultsTest() {
		List<Map<String, Object>> counts = minResultsTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "22", "2", null, "20");
	}

	public void nldiSitesTest() {
		List<Map<String, Object>> counts = nldiSitesTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "13", null, null, "13");
	}

	public void nldiUrlTest() {
		List<Map<String, Object>> counts = nldiUrlTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT);
	}

	public void organizationTest() {
		List<Map<String, Object>> counts = organizationTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "23", "3", "3", "17");
	}

	public void pcodeTest() {
		List<Map<String, Object>> counts = pcodeTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "10", "1", null, "9");
	}

	public void projectTest() {
		List<Map<String, Object>> counts = projectTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "14", "3", "2", "9");
	}

	public void providersTest() {
		List<Map<String, Object>> counts = providersTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "25", "4", null, "21");
	}

	public void restTest() {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(getRestProviders());
		filter.setOrganization(getRestOrganizations());
		filter.setActivity(getActivity());
		List<Map<String, Object>> counts = callDao(nameSpace, 2, filter);
		assertStationResults(counts, "1", null, null, "1");
		assertActivityResults(counts, "1", null, null, "1");
		assertActivityMetricResults(counts, "6", null, null, "6");
	}

	public void resultTest() {
		List<Map<String, Object>> counts = resultTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT);
	}

	public void sampleMediaTest() {
		List<Map<String, Object>> counts = sampleMediaTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "26", "3", "2", "21");
	}

	public void siteIdTest() {
		List<Map<String, Object>> counts = siteIdTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "16", "3", "3", "10");
	}

	public void siteIdLargeListTest() {
		List<Map<String, Object>> counts = siteIdLargeListTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "13", null, null, "13");
	}

	public void siteTypeTest() {
		List<Map<String, Object>> counts = siteTypeTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "27", "3", "3", "21");
	}

	public void siteUrlBaseTest() {
		List<Map<String, Object>> counts = siteUrlBaseTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT);
	}

	public void sortedTest() {
		List<Map<String, Object>> counts = sortedTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT);
	}

	public void startDateHiTest() {
		List<Map<String, Object>> counts = startDateHiTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "26", "3", "2", "21");
	}

	public void startDateLoTest() {
		List<Map<String, Object>> counts = startDateLoTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "17", "4", "3", "10");
	}

	public void stateTest() {
		List<Map<String, Object>> counts = stateTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "23", "3", "3", "17");
	}

	public void subjectTaxonomicNameTest() {
		List<Map<String, Object>> counts = subjectTaxonomicNameTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "10", "1", null, "9");
	}

	public void withinTest() {
		List<Map<String, Object>> counts = withinTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "24", "3", "3", "18");
	}

	public void zipTest() {
		List<Map<String, Object>> counts = zipTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT);
	}

	public void multipleParameterStationSumTest() {
		List<Map<String, Object>> counts = multipleParameterStationSumTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "8", "2", null, "6");
	}

	public void multipleParameterActivitySumTest() {
		List<Map<String, Object>> counts = multipleParameterActivitySumTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, "6", null, null, "6");
	}

	public void multipleParameterResultSumTest() {
		List<Map<String, Object>> counts = multipleParameterResultSumTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, FILTERED_TOTAL_ACTIVITY_COUNT, null, null, FILTERED_STORET_ACTIVITY_COUNT);
	}

	private void assertActivityMetricResults(List<Map<String, Object>> counts,
			String total, String nwis, String stewards, String storet) {
		assertResults(counts, CountColumn.KEY_ACTIVITY_METRIC_COUNT, total, nwis, stewards, storet);
	}

}
