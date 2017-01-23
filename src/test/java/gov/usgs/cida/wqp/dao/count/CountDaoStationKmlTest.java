package gov.usgs.cida.wqp.dao.count;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.BaseDao;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoStationKmlTest extends BaseCountDaoTest {

	protected String nameSpace = BaseDao.STATION_KML_NAMESPACE;
	protected boolean includeActivity = false;
	protected boolean includeActivityMetric = false;
	protected boolean includeResults = false;

	@Test
	public void nullParameterTest() {
		nullParameterTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void emptyParameterTest() {
		emptyParameterTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void analyticalMethodTest() {
		analyticalMethodTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void assemblageTest() {
		assemblageTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void avoidTest() {
		avoidTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void bboxTest() {
		bboxTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void characteristicNameTest() {
		characteristicNameTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void characteristicTypeTest() {
		characteristicTypeTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void countryTest() {
		countryTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void countyTest() {
		countyTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void huc2Test() {
		huc2Test(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void huc4Test() {
		huc4Test(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void huc6Test() {
		huc6Test(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void huc8Test() {
		huc8Test(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void minActivitiesTest() {
		minActivitiesTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void minResultsTest() {
		minResultsTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void nldiUrlTest() {
		nldiUrlTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void organizationTest() {
		organizationTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void pcodeTest() {
		pcodeTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void projectTest() {
		projectTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void providersTest() {
		providersTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void sampleMediaTest() {
		sampleMediaTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void siteIdTest() {
		siteIdTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void manySitesTest() {
		manySitesTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void siteTypeTest() {
		siteTypeTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void startDateHiTest() {
		startDateHiTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void startDateLoTest() {
		startDateLoTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void stateTest() {
		stateTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		subjectTaxonomicNameTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void withinTest() {
		withinTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void multipleParameterStationSumTest() {
		multipleParameterStationSumTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void multipleParameterActivitySumTest() {
		multipleParameterActivitySumTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void multipleParameterActivitySumStationSumTest() {
		multipleParameterActivitySumStationSumTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void multipleParameterResultSumTest() {
		multipleParameterResultSumTest(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

	@Test
	public void multipleParameterResultSumStationSumTests() {
		multipleParameterResultSumStationSumTests(nameSpace, includeActivity, includeActivityMetric, includeResults);
	}

}
