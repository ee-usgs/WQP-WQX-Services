package gov.usgs.cida.wqp.dao.streaming;

import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.TestBioResultMap;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class BioResultStreamingTest extends ResultStreamingTest {

	protected NameSpace nameSpace = NameSpace.BIOLOGICAL_RESULT;
	protected Map<String, Object> expectedMap = TestBioResultMap.BIO_RESULT;

	@Test
	public void nullParameterTest() {
		nullParameterTest(nameSpace);
	}

	@Test
	public void emptyParameterTest() {
		emptyParameterTest(nameSpace);
	}

	@Test
	public void allDataSortedTest() {
		allDataSortedTest(nameSpace, expectedMap);
	}

	@Test
	public void analyticalMethodTest() {
		analyticalMethodTest(nameSpace);
	}

	@Test
	public void assemblageTest() {
		assemblageTest(nameSpace);
	}

	@Test
	public void avoidTest() {
		avoidTest(nameSpace);
	}

	@Test
	public void bboxTest() {
		bboxTest(nameSpace);
	}

	@Test
	public void characteristicNameTest() {
		characteristicNameTest(nameSpace);
	}

	@Test
	public void characteristicTypeTest() {
		characteristicTypeTest(nameSpace);
	}

	@Test
	public void countryTest() {
		countryTest(nameSpace);
	}

	@Test
	public void countyTest() {
		countyTest(nameSpace);
	}

	@Test
	public void huc2Test() {
		huc2Test(nameSpace);
	}

	@Test
	public void huc4Test() {
		huc4Test(nameSpace);
	}

	@Test
	public void huc6Test() {
		huc6Test(nameSpace);
	}

	@Test
	public void huc8Test() {
		huc8Test(nameSpace);
	}

	@Test
	public void minActivitiesTest() {
		minActivitiesTest(nameSpace);
	}

	@Test
	public void minResultsTest() {
		minResultsTest(nameSpace);
	}

	@Test
	public void nldiUrlTest() {
		nldiUrlTest(nameSpace);
	}

	@Test
	public void organizationTest() {
		organizationTest(nameSpace);
	}

	@Test
	public void pcodeTest() {
		pcodeTest(nameSpace);
	}

	@Test
	public void projectTest() {
		projectTest(nameSpace);
	}

	@Test
	public void providersTest() {
		providersTest(nameSpace);
	}

	@Test
	public void sampleMediaTest() {
		sampleMediaTest(nameSpace);
	}

	@Test
	public void siteIdTest() {
		siteIdTest(nameSpace);
	}

	@Test
	public void manySitesTest() {
		manySitesTest(nameSpace);
	}

	@Test
	public void siteTypeTest() {
		siteTypeTest(nameSpace);
	}

	@Test
	public void startDateHiTest() {
		startDateHiTest(nameSpace);
	}

	@Test
	public void startDateLoTest() {
		startDateLoTest(nameSpace);
	}

	@Test
	public void stateTest() {
		stateTest(nameSpace);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		subjectTaxonomicNameTest(nameSpace);
	}

	@Test
	public void withinTest() {
		withinTest(nameSpace);
	}

	@Test
	public void multipleParameterResultTests() {
		multipleParameterResultTests(nameSpace);
	}

	@Test
	public void multipleParameterResultStationSumTests() {
		multipleParameterResultStationSumTests(nameSpace);
	}
}
