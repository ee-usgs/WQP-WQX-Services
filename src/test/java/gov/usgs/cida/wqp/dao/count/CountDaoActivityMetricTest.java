package gov.usgs.cida.wqp.dao.count;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.CountColumn;
import gov.usgs.cida.wqp.parameter.Parameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoActivityMetricTest extends BaseCountDaoTest {

	protected NameSpace nameSpace = NameSpace.ACTIVITY_METRIC;
	protected boolean includeActivity = true;
	protected boolean includeResults = false;

	@Test
	public void nullParameterTest() {
		List<Map<String, Object>> counts = nullParameterTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 5, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT, BIODATA_ACTIVITY_METRIC_COUNT);
	}

	@Test
	public void emptyParameterTest() {
		List<Map<String, Object>> counts = emptyParameterTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 5, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT, BIODATA_ACTIVITY_METRIC_COUNT);
	}

	@Test
	public void activityTest() {
		parms.put(Parameters.ACTIVITY.toString(), getActivity());
		List<Map<String, Object>> counts = countDao.getCounts(nameSpace, parms);
		assertStationResults(counts, 2, "1", null, null, "1", null);
		assertActivityResults(counts, 2, "1", null, null, "1", null);
		assertActivityMetricResults(counts, 2, "6", null, null, "6", null);
	}

	@Test
	public void analyticalMethodTest() {
		List<Map<String, Object>> counts = analyticalMethodTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 3, "12", "2", null, "10", null);
	}

	@Test
	public void assemblageTest() {
		List<Map<String, Object>> counts = assemblageTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 3, "11", null, null, "10", "1");
	}

	@Test
	public void avoidTest() {
		List<Map<String, Object>> counts = avoidTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, STORET_ACTIVITY_METRIC_COUNT, null, null, STORET_ACTIVITY_METRIC_COUNT, null);
	}

	@Test
	public void bboxTest() {
		List<Map<String, Object>> counts = bboxTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 4, "22", "3", "3", "16", null);
	}

	@Test
	public void characteristicNameTest() {
		List<Map<String, Object>> counts = characteristicNameTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, "10", null, null, "10", null);
	}

	@Test
	public void characteristicTypeTest() {
		List<Map<String, Object>> counts = characteristicTypeTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 3, "12", null, "2", "10", null);
	}

	@Test
	public void countryTest() {
		List<Map<String, Object>> counts = countryTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 5, "24", "3", "3", "17", "1");
	}

	@Test
	public void countyTest() {
		List<Map<String, Object>> counts = countyTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 4, "23", "3", "3", "17", null);
	}

	@Test
	public void huc2Test() {
		List<Map<String, Object>> counts = huc2Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 4, "16", "3", "3", "10", null);
	}

	@Test
	public void huc4Test() {
		List<Map<String, Object>> counts = huc4Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 3, "10", "3", null, "7", null);
	}

	@Test
	public void huc6Test() {
		List<Map<String, Object>> counts = huc6Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 3, "9", "2", null, "7", null);
	}

	@Test
	public void huc8Test() {
		List<Map<String, Object>> counts = huc8Test(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, "7", null, null, "7", null);
	}

	@Test
	public void minActivitiesTest() {
		List<Map<String, Object>> counts = minActivitiesTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 4, "24", "2", "2", "20", null);
	}

	@Test
	public void minResultsTest() {
		List<Map<String, Object>> counts = minResultsTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 3, "22", "2", null, "20", null);
	}

	@Test
	public void nldiUrlTest() {
		List<Map<String, Object>> counts = nldiUrlTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, "13", null, null, "13", null);
	}

	@Test
	public void organizationTest() {
		List<Map<String, Object>> counts = organizationTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 4, "23", "3", "3", "17", null);
	}

	@Test
	public void pcodeTest() {
		List<Map<String, Object>> counts = pcodeTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 3, "10", "1", null, "9", null);
	}

	@Test
	public void projectTest() {
		List<Map<String, Object>> counts = projectTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 5, "14", "2", "2", "9", "1");
	}

	@Test
	public void providersTest() {
		List<Map<String, Object>> counts = providersTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 4, "27", "3", "3", "21", null);
	}

	@Test
	public void sampleMediaTest() {
		List<Map<String, Object>> counts = sampleMediaTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 5, "26", "2", "2", "21", "1");
	}

	@Test
	public void siteIdTest() {
		List<Map<String, Object>> counts = siteIdTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 4, "16", "3", "3", "10", null);
	}

	@Test
	public void manySitesTest() {
		List<Map<String, Object>> counts = manySitesTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, "13", null, null, "13", null);
	}

	@Test
	public void siteTypeTest() {
		List<Map<String, Object>> counts = siteTypeTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 5, "27", "2", "3", "21", "1");
	}

	@Test
	public void startDateHiTest() {
		List<Map<String, Object>> counts = startDateHiTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 5, "26", "2", "2", "21", "1");
	}

	@Test
	public void startDateLoTest() {
		List<Map<String, Object>> counts = startDateLoTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 5, "17", "3", "3", "10", "1");
	}

	@Test
	public void stateTest() {
		List<Map<String, Object>> counts = stateTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 4, "23", "3", "3", "17", null);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		List<Map<String, Object>> counts = subjectTaxonomicNameTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 3, "10", null, null, "9", "1");
	}

	@Test
	public void withinTest() {
		List<Map<String, Object>> counts = withinTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 4, "24", "3", "3", "18", null);
	}

	@Test
	public void multipleParameterStationSumTest() {
		List<Map<String, Object>> counts = multipleParameterStationSumTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, "2", null, null, "2", null);
	}

	@Test
	public void multipleParameterActivitySumTest() {
		List<Map<String, Object>> counts = multipleParameterActivitySumTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, "8", null, null, "8", null);
	}

	@Test
	public void multipleParameterActivitySumStationSumTest() {
		List<Map<String, Object>> counts = multipleParameterActivitySumStationSumTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, "6", null, null, "6", null);
	}

	@Test
	public void multipleParameterResultSumTest() {
		List<Map<String, Object>> counts = multipleParameterResultSumTest(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, "4", null, null, "4", null);
	}

	@Test
	public void multipleParameterResultSumStationSumTests() {
		List<Map<String, Object>> counts = multipleParameterResultSumStationSumTests(nameSpace, includeActivity, includeResults);
		assertActivityMetricResults(counts, 2, FILTERED_TOTAL_ACTIVITY_COUNT, null, null, FILTERED_STORET_ACTIVITY_COUNT, null);
	}

	private void assertActivityMetricResults(List<Map<String, Object>> counts, int size,
			String total, String nwis, String stewards, String storet, String biodata) {
		assertResults(counts, CountColumn.KEY_ACTIVITY_METRIC_COUNT, size, total, nwis, stewards, storet, biodata);
	}

}
