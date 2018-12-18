package gov.usgs.cida.wqp.dao.streaming.result;

import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.mapping.TestResultMap;
import gov.usgs.cida.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class BioResultStreamingIT extends BaseResultStreamingTest {

	protected NameSpace nameSpace = NameSpace.BIOLOGICAL_RESULT;
	protected Map<String, Object> expectedMap = TestResultMap.BIO_RESULT;

	@Test
	public void testHarness() {
		activityTest(nameSpace);
		analyticalMethodTest(nameSpace);
		assemblageTest(nameSpace);
		avoidTest(nameSpace);
		bboxTest(nameSpace);
		characteristicNameTest(nameSpace);
		characteristicTypeTest(nameSpace);
		countryTest(nameSpace);
		countyTest(nameSpace);
		emptyParameterTest(nameSpace);
		huc2Test(nameSpace);
		huc3Test(nameSpace);
		huc4Test(nameSpace);
		huc5Test(nameSpace);
		huc6Test(nameSpace);
		huc7Test(nameSpace);
		huc8Test(nameSpace);
		huc10Test(nameSpace);
		huc12Test(nameSpace);
		mimeTypeTest(nameSpace);
		minActivitiesTest(nameSpace);
		minResultsTest(nameSpace);
		nldiSitesTest(nameSpace);
		nldiUrlTest(nameSpace);
		nullParameterTest(nameSpace);
		organizationTest(nameSpace);
		pcodeTest(nameSpace);
		projectTest(nameSpace);
		providersTest(nameSpace);
		resultTest(nameSpace);
		sampleMediaTest(nameSpace);
		siteIdTest(nameSpace);
		siteIdLargeListTest(nameSpace);
		siteTypeTest(nameSpace);
		siteUrlBaseTest(nameSpace);
		sortedTest(nameSpace, expectedMap);
		startDateHiTest(nameSpace);
		startDateLoTest(nameSpace);
		stateTest(nameSpace);
		subjectTaxonomicNameTest(nameSpace);
		withinTest(nameSpace);
		zipTest(nameSpace);
		multipleParameterResultTest(nameSpace);
	}

	@Override
	protected void assertSiteUrlBase(Map<String, Object> row) {
		// Nothing to do here
	}

}
