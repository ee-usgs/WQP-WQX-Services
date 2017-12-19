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

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class StationKmlStreamingTest extends BaseStationStreamingTest {

	protected NameSpace nameSpace = NameSpace.STATION_KML;
	protected Integer expectedColumnCount = TestStationMap.STATION_KML.keySet().size();
	protected Map<String, Object> expectedMap = TestStationMap.STATION_KML;

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Station_Sum 

	@Test
	public void nullParameterTest() {
		nullParameterTest(nameSpace);
	}

	@Test
	public void emptyParameterTest() {
		emptyParameterTest(nameSpace);
	}

	@Test
	public void mimeTypeTest() {
		mimeTypeTest(nameSpace);
	}

	@Test
	public void allDataSortedTest() {
		//KML is sorted different than the rest.
		filter.setSorted("yes");
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		//Validate the number AND order of results.
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

	@Test
	public void avoidTest() {
		avoidTest(nameSpace);
	}

	@Test
	public void bboxTest() {
		bboxTest(nameSpace);
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
	public void nldiUrlTest() {
		nldiUrlTest(nameSpace);
	}

	@Test
	public void organizationTest() {
		organizationTest(nameSpace);
	}

	@Test
	public void providersTest() {
		providersTest(nameSpace);
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
	public void minActivitiesTest() {
		minActivitiesTest(nameSpace);
	}

	@Test
	public void minResultsTest() {
		minResultsTest(nameSpace);
	}

	@Test
	public void siteTypeTest() {
		siteTypeTest(nameSpace);
	}

	@Test
	public void stateTest() {
		stateTest(nameSpace);
	}

	@Test
	public void withinTest() {
		withinTest(nameSpace);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Activity_Sum 

	@Test
	public void projectTest() {
		projectTest(nameSpace);
	}

	@Test
	public void sampleMediaTest() {
		sampleMediaTest(nameSpace);
	}

	@Test
	public void startDateHiTest() {
		startDateHiTest(nameSpace);
	}

	@Test
	public void startDateLoTest() {
		startDateLoTest(nameSpace);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Result_Sum

	@Test
	public void analyticalMethodTest() {
		analyticalMethodTest(nameSpace);
	}

	@Test
	public void assemblageTest() {
		assemblageTest(nameSpace);
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
	public void pcodeTest() {
		pcodeTest(nameSpace);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		subjectTaxonomicNameTest(nameSpace);
	}

	@Test
	public void multipleParameterStationSumTests() {
		multipleParameterStationSumTests(nameSpace);
	}

	@Test
	public void multipleParameterActivitySumTests() {
		multipleParameterActivitySumTests(nameSpace);
	}

	@Test
	public void multipleParameterResultSumTest() {
		multipleParameterResultSumTest(nameSpace);
	}

}
