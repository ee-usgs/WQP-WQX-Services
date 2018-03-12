package gov.usgs.cida.wqp.dao.streaming;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.TestStationMap;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class StationKmlStreamingTest extends BaseStationStreamingTest {

	protected NameSpace nameSpace = NameSpace.STATION_KML;
	protected Integer expectedColumnCount = TestStationMap.STATION_KML.keySet().size();
	protected Map<String, Object> expectedMap = TestStationMap.STATION_KML;

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
		sortedTest();
		startDateHiTest(nameSpace);
		startDateLoTest(nameSpace);
		stateTest(nameSpace);
		subjectTaxonomicNameTest(nameSpace);
		withinTest(nameSpace);
		zipTest(nameSpace);
		multipleParameterStationSumTest(nameSpace);
		multipleParameterActivitySumTest(nameSpace);
		multipleParameterResultSumTest(nameSpace);
	}

	public void sortedTest() {
		//KML is sorted different than the rest.
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
		assertRow(results.get(0), STORET_1043441, expectedColumnCount);
		assertRow(results.get(1), STORET_504707, expectedColumnCount);
		assertRow(results.get(2), STEWARDS_36, expectedColumnCount);
		assertRow(results.get(3), STEWARDS_46, expectedColumnCount);
		assertRow(results.get(4), NWIS_1353690, expectedColumnCount);
		assertRow(results.get(5), BIODATA_61233184, expectedColumnCount);
		assertRow(results.get(6), NWIS_1360035, expectedColumnCount);
		assertRow(results.get(7), BIODATA_433830088977331, expectedColumnCount);
		assertRow(results.get(8), STORET_436723, expectedColumnCount);
		assertRow(results.get(9), STORET_1383, expectedColumnCount);
		assertStoret888(expectedMap, results.get(10));
		assertRow(results.get(11), STORET_777, expectedColumnCount);
		assertRow(results.get(12), STORET_999, expectedColumnCount);
	}

}
