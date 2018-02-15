package gov.usgs.cida.wqp.dao.count;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoBioResultTest extends BaseCountDaoTest {

	protected NameSpace nameSpace = NameSpace.BIOLOGICAL_RESULT;
	protected boolean includeActivity = true;
	protected boolean includeResults = true;

	@Test
	public void testHarness() {
		nullParameterTest();
		emptyParameterTest();
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
		multipleParameterActivitySumTest();
		multipleParameterActivitySumStationSumTest();
		multipleParameterResultSumTest();
		multipleParameterResultSumStationSumTests();
	}

	public void nullParameterTest() {
		nullParameterTest(nameSpace, includeActivity, includeResults);
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace, includeActivity, includeResults);
	}

	public void analyticalMethodTest() {
		analyticalMethodTest(nameSpace, includeActivity, includeResults);
	}

	public void assemblageTest() {
		assemblageTest(nameSpace, includeActivity, includeResults);
	}

	public void avoidTest() {
		avoidTest(nameSpace, includeActivity, includeResults);
	}

	public void bboxTest() {
		bboxTest(nameSpace, includeActivity, includeResults);
	}

	public void characteristicNameTest() {
		characteristicNameTest(nameSpace, includeActivity, includeResults);
	}

	public void characteristicTypeTest() {
		characteristicTypeTest(nameSpace, includeActivity, includeResults);
	}

	public void countryTest() {
		countryTest(nameSpace, includeActivity, includeResults);
	}

	public void countyTest() {
		countyTest(nameSpace, includeActivity, includeResults);
	}

	public void huc2Test() {
		huc2Test(nameSpace, includeActivity, includeResults);
	}

	public void huc3Test() {
		huc3Test(nameSpace, includeActivity, includeResults);
	}

	public void huc4Test() {
		huc4Test(nameSpace, includeActivity, includeResults);
	}

	public void huc5Test() {
		huc5Test(nameSpace, includeActivity, includeResults);
	}

	public void huc6Test() {
		huc6Test(nameSpace, includeActivity, includeResults);
	}

	public void huc7Test() {
		huc7Test(nameSpace, includeActivity, includeResults);
	}

	public void huc8Test() {
		huc8Test(nameSpace, includeActivity, includeResults);
	}

	public void huc10Test() {
		huc10Test(nameSpace, includeActivity, includeResults);
	}

	public void huc12Test() {
		huc12Test(nameSpace, includeActivity, includeResults);
	}

	public void minActivitiesTest() {
		minActivitiesTest(nameSpace, includeActivity, includeResults);
	}

	public void minResultsTest() {
		minResultsTest(nameSpace, includeActivity, includeResults);
	}

	public void nldiUrlTest() {
		nldiUrlTest(nameSpace, includeActivity, includeResults);
	}

	public void organizationTest() {
		organizationTest(nameSpace, includeActivity, includeResults);
	}

	public void pcodeTest() {
		pcodeTest(nameSpace, includeActivity, includeResults);
	}

	public void projectTest() {
		projectTest(nameSpace, includeActivity, includeResults);
	}

	public void providersTest() {
		providersTest(nameSpace, includeActivity, includeResults);
	}

	public void sampleMediaTest() {
		sampleMediaTest(nameSpace, includeActivity, includeResults);
	}

	public void siteIdTest() {
		siteIdTest(nameSpace, includeActivity, includeResults);
	}

	public void manySitesTest() {
		manySitesTest(nameSpace, includeActivity, includeResults);
	}

	public void siteTypeTest() {
		siteTypeTest(nameSpace, includeActivity, includeResults);
	}

	public void startDateHiTest() {
		startDateHiTest(nameSpace, includeActivity, includeResults);
	}

	public void startDateLoTest() {
		startDateLoTest(nameSpace, includeActivity, includeResults);
	}

	public void stateTest() {
		stateTest(nameSpace, includeActivity, includeResults);
	}

	public void subjectTaxonomicNameTest() {
		subjectTaxonomicNameTest(nameSpace, includeActivity, includeResults);
	}

	public void withinTest() {
		withinTest(nameSpace, includeActivity, includeResults);
	}

	public void multipleParameterStationSumTest() {
		multipleParameterStationSumTest(nameSpace, includeActivity, includeResults);
	}

	public void multipleParameterActivitySumTest() {
		multipleParameterActivitySumTest(nameSpace, includeActivity, includeResults);
	}

	public void multipleParameterActivitySumStationSumTest() {
		multipleParameterActivitySumStationSumTest(nameSpace, includeActivity, includeResults);
	}

	public void multipleParameterResultSumTest() {
		multipleParameterResultSumTest(nameSpace, includeActivity, includeResults);
	}

	public void multipleParameterResultSumStationSumTests() {
		multipleParameterResultSumStationSumTests(nameSpace, includeActivity, includeResults);
	}}
