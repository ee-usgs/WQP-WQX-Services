package gov.usgs.wma.wqp.dao.count;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.wma.wqp.CsvDataSetLoader;
import gov.usgs.wma.wqp.dao.CountDao;
import gov.usgs.wma.wqp.dao.NameSpace;
import gov.usgs.wma.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, CountDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoStationKmlIT extends BaseStationCountDaoTest {

	protected NameSpace nameSpace = NameSpace.STATION_KML;

	@Test
	public void testHarness() {
		activityTest(nameSpace, includeActivity, includeResults);
		analyticalMethodTest(nameSpace, includeActivity, includeResults);
		assemblageTest(nameSpace, includeActivity, includeResults);
		avoidTest(nameSpace, includeActivity, includeResults);
		bboxTest(nameSpace, includeActivity, includeResults);
		characteristicNameTest(nameSpace, includeActivity, includeResults);
		characteristicTypeTest(nameSpace, includeActivity, includeResults);
		countryTest(nameSpace, includeActivity, includeResults);
		countyTest(nameSpace, includeActivity, includeResults);
		emptyParameterTest(nameSpace, includeActivity, includeResults);
		huc2Test(nameSpace, includeActivity, includeResults);
		huc3Test(nameSpace, includeActivity, includeResults);
		huc4Test(nameSpace, includeActivity, includeResults);
		huc5Test(nameSpace, includeActivity, includeResults);
		huc6Test(nameSpace, includeActivity, includeResults);
		huc7Test(nameSpace, includeActivity, includeResults);
		huc8Test(nameSpace, includeActivity, includeResults);
		huc10Test(nameSpace, includeActivity, includeResults);
		huc12Test(nameSpace, includeActivity, includeResults);
		mimeTypeTest(nameSpace, includeActivity, includeResults);
		minActivitiesTest(nameSpace, includeActivity, includeResults);
		minResultsTest(nameSpace, includeActivity, includeResults);
		nldiSitesTest(nameSpace, includeActivity, includeResults);
		nldiUrlTest(nameSpace, includeActivity, includeResults);
		nullParameterTest(nameSpace, includeActivity, includeResults);
		organizationTest(nameSpace, includeActivity, includeResults);
		pcodeTest(nameSpace, includeActivity, includeResults);
		projectTest(nameSpace, includeActivity, includeResults);
		providersTest(nameSpace, includeActivity, includeResults);
		resultTest(nameSpace, includeActivity, includeResults);
		sampleMediaTest(nameSpace, includeActivity, includeResults);
		siteIdTest(nameSpace, includeActivity, includeResults);
		siteIdLargeListTest(nameSpace, includeActivity, includeResults);
		siteTypeTest(nameSpace, includeActivity, includeResults);
		siteUrlBaseTest(nameSpace, includeActivity, includeResults);
		sortedTest(nameSpace, includeActivity, includeResults);
		startDateHiTest(nameSpace, includeActivity, includeResults);
		startDateLoTest(nameSpace, includeActivity, includeResults);
		stateTest(nameSpace, includeActivity, includeResults);
		subjectTaxonomicNameTest(nameSpace, includeActivity, includeResults);
		withinTest(nameSpace, includeActivity, includeResults);
		zipTest(nameSpace, includeActivity, includeResults);
		multipleParameterStationSumTest(nameSpace, includeActivity, includeResults);
		multipleParameterActivitySumTest(nameSpace, includeActivity, includeResults);
		multipleParameterResultSumTest(nameSpace, includeActivity, includeResults);
	}

}
