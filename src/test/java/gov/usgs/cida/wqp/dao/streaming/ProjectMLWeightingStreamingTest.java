package gov.usgs.cida.wqp.dao.streaming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.ProjectColumn;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.mapping.TestProjectMLWeightingMap;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ProjectMLWeightingStreamingTest extends BaseStreamingTest {
	private static final Logger LOG = LoggerFactory.getLogger(ProjectMLWeightingStreamingTest.class);

	NameSpace nameSpace = NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING;

	public static final String[] STEWARDS_PRJMLW = new String[]{STEWARDS, "CEAP", "ARS-IAWC-IAWC225"};
	public static final String[] NWIS_PRJMLW1 = new String[]{NWIS, "NAWQA", "USGS-05425700"};
	public static final String[] NWIS_PRJMLW2 = new String[]{NWIS, "NAWQA", "USGS-431925089002701"};
	public static final String[] STORET_PRJMLW1 = new String[]{STORET, "WR047", "WIDNR_WQX-113086"};
	public static final String[] STORET_PRJMLW2 = new String[]{STORET, "Lake-BaselineMonitoringDNR", "WIDNR_WQX-113086"};
	public static final String[] STORET_PRJMLW3 = new String[]{STORET, "SAM", "21NYDECA_WQX-ONTARIO-02"};
	public static final String[] BIODATA_PRJMLW = new String[]{BIODATA, "SACR BioTDB", "USGS-11421000"};

	public static final int PRJ_ML_WEIGHTING_COLUMN_COUNT = TestProjectMLWeightingMap.PROJECT_MONITORING_LOCATION_WEIGHTING.keySet().size();

	@Test
	public void testHarness() {
		nullParameterTest();
		emptyParameterTest();
		allDataSortedTest();
		analyticalMethodTest();
		assemblageTest();
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

	public void nullParameterTest() {
		nullParameterTest(nameSpace, Integer.valueOf(TOTAL_PRJ_ML_WEIGHTING_COUNT));
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_PRJ_ML_WEIGHTING_COUNT));
	}

	public void allDataSortedTest() {
		LinkedList<Map<String, Object>> results = allDataSortedTest(nameSpace, Integer.valueOf(TOTAL_PRJ_ML_WEIGHTING_COUNT));

		//Validate order of results.
		assertProjectMLWeighting(results.get(0), STEWARDS_PRJMLW);
		assertProjectMLWeighting(results.get(1), NWIS_PRJMLW1);
		assertProjectMLWeighting(results.get(2), NWIS_PRJMLW2);
		assertProjectMLWeighting(results.get(3), STORET_PRJMLW3);
		assertProjectMLWeighting(results.get(4), STORET_PRJMLW2);
		assertProjectMLWeighting(results.get(5), STORET_PRJMLW1);
		assertProjectMLWeighting(results.get(6), BIODATA_PRJMLW);
	}

	public void bboxTest() {
		LinkedList<Map<String, Object>> results = bboxTest(nameSpace, 5);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void countryTest() {
		LinkedList<Map<String, Object>> results = countryTest(nameSpace, 6);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1,  STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
	}

	public void countyTest() {
		LinkedList<Map<String, Object>> results = countyTest(nameSpace, 5);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void huc2Test() {
		LinkedList<Map<String, Object>> results = huc2Test(nameSpace, 5);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void huc3Test() {
		LinkedList<Map<String, Object>> results = huc3Test(nameSpace, 5);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void huc4Test() {
		LinkedList<Map<String, Object>> results = huc4Test(nameSpace, 4);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void huc5Test() {
		LinkedList<Map<String, Object>> results = huc5Test(nameSpace, 4);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void huc6Test() {
		LinkedList<Map<String, Object>> results = huc6Test(nameSpace, 4);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void huc7Test() {
		LinkedList<Map<String, Object>> results = huc7Test(nameSpace, 4);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void huc8Test() {
		LinkedList<Map<String, Object>> results = huc8Test(nameSpace, 2);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
	}

	public void huc10Test() {
		LinkedList<Map<String, Object>> results = huc10Test(nameSpace, 2);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
	}

	public void huc12Test() {
		LinkedList<Map<String, Object>> results = huc12Test(nameSpace, 2);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
	}

	public void nldiUrlTest() {
		LinkedList<Map<String, Object>> results = nldiUrlTest(nameSpace, 2);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
	}

	public void organizationTest() {
		LinkedList<Map<String, Object>> results = organizationTest(nameSpace, 5);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void providersTest() {
		LinkedList<Map<String, Object>> results = providersTest(nameSpace, 7);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STORET_PRJMLW3, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
	}

	public void siteIdTest() {
		LinkedList<Map<String, Object>> results = siteIdTest(nameSpace, 3);
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void manySitesTest() {
		LinkedList<Map<String, Object>> results = manySitesTest(nameSpace, 2);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
	}

	public void siteTypeTest() {
		LinkedList<Map<String, Object>> results = siteTypeTest(nameSpace, 6);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
	}

	public void stateTest() {
		LinkedList<Map<String, Object>> results = stateTest(nameSpace, 5);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}
	
	public void withinTest() {
		LinkedList<Map<String, Object>> results = withinTest(nameSpace, 5);
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void projectTest() {
		LinkedList<Map<String, Object>> results = projectTest(nameSpace, 4);
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, BIODATA_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void sampleMediaTest() {
		LinkedList<Map<String, Object>> results = sampleMediaTest(nameSpace, 6);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
	}

	public void startDateHiTest() {
		LinkedList<Map<String, Object>> results = startDateHiTest(nameSpace, 6);
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
	}

	public void startDateLoTest() {
		LinkedList<Map<String, Object>> results = startDateLoTest(nameSpace, 4);
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
	}

	public void analyticalMethodTest() {
		LinkedList<Map<String, Object>> results = analyticalMethodTest(nameSpace, 2);
		assertContainsProjectMLWeightings(results, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void assemblageTest() {
		LinkedList<Map<String, Object>> results = assemblageTest(nameSpace, 1);
		assertContainsProjectMLWeightings(results, BIODATA_PRJMLW);
	}

	public void characteristicNameTest() {
		LinkedList<Map<String, Object>> results = characteristicNameTest(nameSpace, 0);
	}

	public void characteristicTypeTest() {
		LinkedList<Map<String, Object>> results = characteristicTypeTest(nameSpace, 1);
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW);
	}

	public void pcodeTest() {
		LinkedList<Map<String, Object>> results = pcodeTest(nameSpace, 2);
		assertContainsProjectMLWeightings(results, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void subjectTaxonomicNameTest() {
		LinkedList<Map<String, Object>> results = subjectTaxonomicNameTest(nameSpace, 1);
		assertContainsProjectMLWeightings(results, BIODATA_PRJMLW);
	}

	public void multipleParameterStationSumTest() {
		LinkedList<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 3);
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void multipleParameterActivitySumTest() {
		LinkedList<Map<String, Object>> results = multipleParameterActivitySumTest(nameSpace, 3);
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
	}

	public void multipleParameterResultSumTest() {
		LinkedList<Map<String, Object>> results = multipleParameterResultSumTest(nameSpace, 0);
		assertContainsProjectMLWeightings(results);
	}

	private void assertContainsProjectMLWeightings(LinkedList<Map<String, Object>> results, String[]... prjmlws) {
		for (String[] i : prjmlws) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(BaseColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(BaseColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(((String) result.get(ProjectColumn.KEY_PROJECT_IDENTIFIER)))
						&& i[2].equalsIgnoreCase(((String) result.get(StationColumn.KEY_SITE_ID)))) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				String setString = "[\n";

				for(Map<String,Object> result : results) {
					setString += "\t{\n";
					for(Entry<String,Object> entry : result.entrySet()) {
						setString += "\t\t" + entry.getKey() + " : " + entry.getValue().toString() + "\n";
					}
					setString += "\t},\n";
				}
				setString += "]\n\n";
				fail(BaseColumn.KEY_DATA_SOURCE_ID + ":" + i[0] + "/" + StationColumn.KEY_SITE_ID + ":" + i[1] + 
				" was not in the result set. Set: \n\n" + setString);
			}
		}
		assertEquals("Double check result set expected size", prjmlws.length, results.size());
	}

	private void assertProjectMLWeighting(Map<String,Object> row, String[] comparison) {
		assertEquals(PRJ_ML_WEIGHTING_COLUMN_COUNT, row.keySet().size());
		assertEquals(row.get(BaseColumn.KEY_DATA_SOURCE), comparison[0]);
		assertEquals(row.get(ProjectColumn.KEY_PROJECT_IDENTIFIER), comparison[1]);
		assertEquals(row.get(StationColumn.KEY_SITE_ID), comparison[2]);
	}
}
