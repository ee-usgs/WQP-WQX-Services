package gov.usgs.cida.wqp.dao.streaming;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STEWARDS_36;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_1043441;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_777;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_888;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_999;
import gov.usgs.cida.wqp.mapping.TestBiologicalMetricMap;

import gov.usgs.cida.wqp.springinit.DBTestConfig;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class BiologicalMetricStreamingIT extends BaseStationStreamingTest {	

	protected NameSpace nameSpace = NameSpace.BIOLOGICAL_METRIC;
	protected List<Map<String, Object>> expectedMap = TestBiologicalMetricMap.BIOLOGICAL_METRIC;
//		public static final int BIOLOGICAL_METRIC_COLUMN_COUNT =
//			TestBiologicalMetricMap.BIOLOGICAL_METRIC.keySet().size();
	@Test
	public void testHarness() {
//		activityTest(nameSpace);
//		analyticalMethodTest(nameSpace);
//		assemblageTest(nameSpace);
//		avoidTest(nameSpace);
//		bboxTest(nameSpace);		
//		characteristicNameTest(nameSpace);
//		characteristicTypeTest(nameSpace);
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
		mimeTypeTest(nameSpace);
//		minActivitiesTest(nameSpace);
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
// fix		sortedTest(nameSpace);
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
	
	@Override
	protected void mimeTypeTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = mimeTypeJsonTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690,
				NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383,
				STORET_436723, STORET_504707, STORET_1043441, BIODATA_61233184);
	}
	
	public void sortedTest(NameSpace nameSpace) {

		Integer expectedColumnCount;
		expectedColumnCount = expectedMap.size();
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
		assertRow(results.get(0), STEWARDS_36, expectedColumnCount);
		assertRow(results.get(1), STEWARDS_46, expectedColumnCount);
		assertRow(results.get(2), NWIS_1353690, expectedColumnCount);
		assertRow(results.get(3), NWIS_1360035, expectedColumnCount);
		assertRow(results.get(4), STORET_1043441, expectedColumnCount);
		assertRow(results.get(5), STORET_504707, expectedColumnCount);
		assertRow(results.get(6), STORET_436723, expectedColumnCount);
		assertRow(results.get(7), STORET_1383, expectedColumnCount);
//		assertStoret888(expectedMap, results.get(8));
		assertRow(results.get(9), STORET_777, expectedColumnCount);
		assertRow(results.get(10), STORET_999, expectedColumnCount);
		assertRow(results.get(11), BIODATA_61233184, expectedColumnCount);
	}
}
