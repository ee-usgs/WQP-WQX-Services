package gov.usgs.cida.wqp.dao.streaming;

import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.TestPcResultMap;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class PCResultStreamingTest extends ResultStreamingTest {

	protected NameSpace nameSpace = NameSpace.RESULT;
	protected Map<String, Object> expectedMap = TestPcResultMap.PC_RESULT;

	@Test
	public void testHarness() {
		nullParameterTest();
		emptyParameterTest();
		allDataSortedTest();
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
		multipleParameterResultTest();
		multipleParameterResultStationSumTest();
	}

	public void nullParameterTest() {
		nullParameterTest(nameSpace);
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace);
	}

	public void allDataSortedTest() {
		allDataSortedTest(nameSpace, expectedMap);
	}

	public void analyticalMethodTest() {
		analyticalMethodTest(nameSpace);
	}

	public void assemblageTest() {
		assemblageTest(nameSpace);
	}

	public void avoidTest() {
		avoidTest(nameSpace);
	}

	public void bboxTest() {
		bboxTest(nameSpace);
	}

	public void characteristicNameTest() {
		characteristicNameTest(nameSpace);
	}

	public void characteristicTypeTest() {
		characteristicTypeTest(nameSpace);
	}

	public void countryTest() {
		countryTest(nameSpace);
	}

	public void countyTest() {
		countyTest(nameSpace);
	}

	public void huc2Test() {
		huc2Test(nameSpace);
	}

	public void huc3Test() {
		huc3Test(nameSpace);
	}

	public void huc4Test() {
		huc4Test(nameSpace);
	}

	public void huc5Test() {
		huc5Test(nameSpace);
	}

	public void huc6Test() {
		huc6Test(nameSpace);
	}

	public void huc7Test() {
		huc7Test(nameSpace);
	}

	public void huc8Test() {
		huc8Test(nameSpace);
	}

	public void huc10Test() {
		huc10Test(nameSpace);
	}

	public void huc12Test() {
		huc12Test(nameSpace);
	}

	public void minActivitiesTest() {
		minActivitiesTest(nameSpace);
	}

	public void minResultsTest() {
		minResultsTest(nameSpace);
	}

	public void nldiUrlTest() {
		nldiUrlTest(nameSpace);
	}

	public void organizationTest() {
		organizationTest(nameSpace);
	}

	public void pcodeTest() {
		pcodeTest(nameSpace);
	}

	public void projectTest() {
		projectTest(nameSpace);
	}

	public void providersTest() {
		providersTest(nameSpace);
	}

	public void sampleMediaTest() {
		sampleMediaTest(nameSpace);
	}

	public void siteIdTest() {
		siteIdTest(nameSpace);
	}

	public void manySitesTest() {
		manySitesTest(nameSpace);
	}

	public void siteTypeTest() {
		siteTypeTest(nameSpace);
	}

	public void startDateHiTest() {
		startDateHiTest(nameSpace);
	}

	public void startDateLoTest() {
		startDateLoTest(nameSpace);
	}

	public void stateTest() {
		stateTest(nameSpace);
	}

	public void subjectTaxonomicNameTest() {
		subjectTaxonomicNameTest(nameSpace);
	}

	public void withinTest() {
		withinTest(nameSpace);
	}

	public void multipleParameterResultTest() {
		multipleParameterResultTest(nameSpace);
	}

	public void multipleParameterResultStationSumTest() {
		multipleParameterResultStationSumTest(nameSpace);
	}

}
