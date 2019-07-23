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
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, CountDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoProjectMLWeightingIT extends BaseCountDaoTest {

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
//		multipleParameterStationSumTest();
//		multipleParameterActivitySumTest();
//		multipleParameterActivitySumStationSumTest();
//		multipleParameterResultSumTest();
//		multipleParameterResultSumStationSumTests();
	}

	public void activityTest() {
		List<Map<String, Object>> counts = activityTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	public void analyticalMethodTest() {
		List<Map<String, Object>> counts = analyticalMethodTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "2", "2", null, null, null);
	}

	public void assemblageTest() {
		List<Map<String, Object>> counts = assemblageTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "1", null, null, null, "1");
	}

	public void avoidTest() {
		List<Map<String, Object>> counts = avoidTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	public void bboxTest() {
		List<Map<String, Object>> counts = bboxTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "5", "2", "1", "2", null);
	}

	public void characteristicNameTest() {
		List<Map<String, Object>> counts = characteristicNameTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 1);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "0", null, null, null, null);
	}

	public void characteristicTypeTest() {
		List<Map<String, Object>> counts = characteristicTypeTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "1", null, "1", null, null);
	}

	public void countryTest() {
		List<Map<String, Object>> counts = countryTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "6", "2", "1", "2", "1");
	}

	public void countyTest() {
		List<Map<String, Object>> counts = countyTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "5", "2", "1", "2", null);
	}

	public void emptyParameterTest() {
		List<Map<String, Object>> counts = emptyParameterTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	public void huc2Test() {
		List<Map<String, Object>> counts = huc2Test(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "5", "2", "1", "2", null);
	}

	public void huc3Test() {
		List<Map<String, Object>> counts = huc3Test(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "5", "2", "1", "2", null);
	}

	public void huc4Test() {
		List<Map<String, Object>> counts = huc4Test(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 3);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "4", "2", null, "2", null);
	}

	public void huc5Test() {
		List<Map<String, Object>> counts = huc5Test(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 3);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "4", "2", null, "2", null);
	}

	public void huc6Test() {
		List<Map<String, Object>> counts = huc6Test(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING,  3);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "4", "2", null, "2", null);
	}

	public void huc7Test() {
		List<Map<String, Object>> counts = huc7Test(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING,  3);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "4", "2", null, "2", null);
	}

	public void huc8Test() {
		List<Map<String, Object>> counts = huc8Test(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "2", null, null, "2", null);
	}

	public void huc10Test() {
		List<Map<String, Object>> counts = huc10Test(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "2", null, null, "2", null);
	}

	public void huc12Test() {
		List<Map<String, Object>> counts = huc12Test(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "2", null, null, "2", null);
	}

	public void mimeTypeTest() {
		List<Map<String, Object>> counts = mimeTypeJsonTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeGeoJsonTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeKmlTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeKmzTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeCsvTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeTsvTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeXmlTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeXlsxTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	public void minActivitiesTest() {
		List<Map<String, Object>> counts = minActivitiesTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		//TODO implement the correct SQL
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "6", "2", "1", "2", "1");
	}

	public void minResultsTest() {
		List<Map<String, Object>> counts = minResultsTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		//TODO implement the correct SQL
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "6", "2", "1", "2", "1");
	}

	public void nldiSitesTest() {
		List<Map<String, Object>> counts = nldiSitesTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "2", null, null, "2", null);
	}

	public void nldiUrlTest() {
		List<Map<String, Object>> counts = nldiUrlTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	public void nullParameterTest() {
		List<Map<String, Object>> counts = nullParameterTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	public void organizationTest() {
		List<Map<String, Object>> counts = organizationTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "5", "2", "1", "2", null);
	}

	public void pcodeTest() {
		List<Map<String, Object>> counts = pcodeTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "2", "2", null, null, null);
	}

	public void projectTest() {
		List<Map<String, Object>> counts = projectTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "4", "2", "1", null, "1");
	}

	public void providersTest() {
		List<Map<String, Object>> counts = providersTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "6", "2", "1", "3", null);
	}

	public void restTest() {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(getRestProviders());
		filter.setOrganization(getRestOrganizations());
		filter.setProject(getRestProjects());
		List<Map<String, Object>> counts = callDao(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "1", null, null, "1", null);
	}

	public void resultTest() {
		List<Map<String, Object>> counts = resultTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	public void sampleMediaTest() {
		List<Map<String, Object>> counts = sampleMediaTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "6", "2", "1", "2", "1");
	}

	public void siteIdTest() {
		List<Map<String, Object>> counts = siteIdTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 3);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "3", "2", "1", null, null);
	}

	public void siteIdLargeListTest() {
		List<Map<String, Object>> counts = siteIdLargeListTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "2", null, null, "2", null);
	}

	public void siteTypeTest() {
		List<Map<String, Object>> counts = siteTypeTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "6", "2", "1", "2", "1");
	}

	public void siteUrlBaseTest() {
		List<Map<String, Object>> counts = siteUrlBaseTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	public void sortedTest() {
		List<Map<String, Object>> counts = sortedTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	public void startDateHiTest() {
		List<Map<String, Object>> counts = startDateHiTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "6", "2", "1", "2", "1");
	}

	public void startDateLoTest() {
		List<Map<String, Object>> counts = startDateLoTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "4", "2", "1", null, "1");
	}

	public void stateTest() {
		List<Map<String, Object>> counts = stateTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "5", "2", "1", "2", null);
	}

	public void subjectTaxonomicNameTest() {
		List<Map<String, Object>> counts = subjectTaxonomicNameTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 2);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "1", null, null, null, "1");
	}

	public void withinTest() {
		List<Map<String, Object>> counts = withinTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 4);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, "5", "2", "1", "2", null);
	}

	public void zipTest() {
		List<Map<String, Object>> counts = zipTest(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, 5);
		assertFullDbReturned(counts);
	}

	protected void assertFullDbReturned(List<Map<String, Object>> counts) {
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, TOTAL_PRJ_ML_WEIGHTING_COUNT, NWIS_PRJ_ML_WEIGHTING_COUNT, STEWARDS_PRJ_ML_WEIGHTING_COUNT, STORET_PRJ_ML_WEIGHTING_COUNT, BIODATA_PRJ_ML_WEIGHTING_COUNT);
	}

}
