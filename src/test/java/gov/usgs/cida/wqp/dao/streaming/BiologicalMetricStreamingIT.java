package gov.usgs.cida.wqp.dao.streaming;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.mapping.TestBiologicalMetricMap;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import java.util.Map;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class BiologicalMetricStreamingIT extends BaseStationStreamingTest {
public static final int BIOLOGICAL_METRIC_COLUMN_COUNT = TestBiologicalMetricMap.BIOLOGICAL_METRIC.keySet().size();

	protected NameSpace nameSpace = NameSpace.BIOLOGICAL_METRIC;
	protected Map<String, Object> expectedMap = TestBiologicalMetricMap.BIOLOGICAL_METRIC;
	@Test
	public void testHarness() {
//		activityTest(nameSpace);
//		analyticalMethodTest(nameSpace);
//		assemblageTest(nameSpace);
//		avoidTest(nameSpace);
//		bboxTest(nameSpace);		
//		characteristicNameTest(nameSpace);
// fix this		characteristicTypeTest(nameSpace);
//		countryTest(nameSpace);
//		countyTest(nameSpace);
//		emptyParameterTest(nameSpace);
//		huc2Test(nameSpace);
//		huc3Test(nameSpace);
//		huc4Test(nameSpace);
//		huc5Test(nameSpace);
//		huc6Test(nameSpace);
//		huc7Test(nameSpace);
//		huc8Test(nameSpace);
//		huc10Test(nameSpace);
//		huc12Test(nameSpace);
// fix this		mimeTypeTest(nameSpace);
//	fix this	minActivitiesTest(nameSpace);
//		minResultsTest(nameSpace);
//		nldiSitesTest(nameSpace);
//		nldiUrlTest(nameSpace);
//		nullParameterTest(nameSpace);
//		organizationTest(nameSpace);
//		pcodeTest(nameSpace);
//		projectTest(nameSpace);
//		providersTest(nameSpace);
//		resultTest(nameSpace);
//		sampleMediaTest(nameSpace);
//		siteIdTest(nameSpace);
//		siteIdLargeListTest(nameSpace);
//		siteTypeTest(nameSpace);
//		siteUrlBaseTest(nameSpace);
		sortedTest(nameSpace, expectedMap);
//		startDateHiTest(nameSpace);
//		startDateLoTest(nameSpace);
//		stateTest(nameSpace);
//		subjectTaxonomicNameTest(nameSpace);
//		withinTest(nameSpace);
//		zipTest(nameSpace);
//		multipleParameterStationSumTest(nameSpace);
//		multipleParameterActivitySumTest(nameSpace);
//		multipleParameterResultSumTest(nameSpace);
	}	
}
