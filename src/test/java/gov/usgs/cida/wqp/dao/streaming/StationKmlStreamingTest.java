package gov.usgs.cida.wqp.dao.streaming;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.TestStationMap;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class StationKmlStreamingTest extends BaseStationStreamingTest {

	protected NameSpace nameSpace = NameSpace.STATION_KML;
	protected Integer expectedColumnCount = TestStationMap.STATION_KML.keySet().size();
	protected Map<String, Object> expectedMap = TestStationMap.STATION_KML;

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
		mimeTypeTest();
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
		multipleParameterResultSumTest();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Station_Sum 

	public void nullParameterTest() {
		nullParameterTest(nameSpace);
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace);
	}

	public void mimeTypeTest() {
		mimeTypeTest(nameSpace);
	}

	public void allDataSortedTest() {
		//KML is sorted different than the rest.
		FilterParameters filter = new FilterParameters();
		filter.setSorted("yes");
		LinkedList<Map<String, Object>> results = callDao(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT), filter);
		assertEquals(TOTAL_SITE_COUNT, String.valueOf(results.size()));
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

	public void avoidTest() {
		avoidTest(nameSpace);
	}

	public void bboxTest() {
		bboxTest(nameSpace);
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

	public void nldiUrlTest() {
		nldiUrlTest(nameSpace);
	}

	public void organizationTest() {
		organizationTest(nameSpace);
	}

	public void providersTest() {
		providersTest(nameSpace);
	}

	public void siteIdTest() {
		siteIdTest(nameSpace);
	}

	public void manySitesTest() {
		manySitesTest(nameSpace);
	}

	public void minActivitiesTest() {
		minActivitiesTest(nameSpace);
	}

	public void minResultsTest() {
		minResultsTest(nameSpace);
	}

	public void siteTypeTest() {
		siteTypeTest(nameSpace);
	}

	public void stateTest() {
		stateTest(nameSpace);
	}

	public void withinTest() {
		withinTest(nameSpace);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Activity_Sum 

	public void projectTest() {
		projectTest(nameSpace);
	}

	public void sampleMediaTest() {
		sampleMediaTest(nameSpace);
	}

	public void startDateHiTest() {
		startDateHiTest(nameSpace);
	}

	public void startDateLoTest() {
		startDateLoTest(nameSpace);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Result_Sum

	public void analyticalMethodTest() {
		analyticalMethodTest(nameSpace);
	}

	public void assemblageTest() {
		assemblageTest(nameSpace);
	}

	public void characteristicNameTest() {
		characteristicNameTest(nameSpace);
	}

	public void characteristicTypeTest() {
		characteristicTypeTest(nameSpace);
	}

	public void pcodeTest() {
		pcodeTest(nameSpace);
	}

	public void subjectTaxonomicNameTest() {
		subjectTaxonomicNameTest(nameSpace);
	}

	public void multipleParameterStationSumTest() {
		multipleParameterStationSumTest(nameSpace);
	}

	public void multipleParameterActivitySumTest() {
		multipleParameterActivitySumTest(nameSpace);
	}

	public void multipleParameterResultSumTest() {
		multipleParameterResultSumTest(nameSpace);
	}

}
