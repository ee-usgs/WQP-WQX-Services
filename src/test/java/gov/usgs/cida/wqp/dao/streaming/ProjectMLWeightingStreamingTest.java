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
public class ProjectMLWeightingStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(ProjectMLWeightingStreamingTest.class);

	@Autowired 
	IStreamingDao streamingDao;

	TestResultHandler handler;
	FilterParameters filter;
	NameSpace nameSpace = NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING;

	public static final String[] STEWARDS_PRJMLW = new String[]{STEWARDS, "CEAP", "ARS-IAWC-IAWC225"};
	public static final String[] NWIS_PRJMLW1 = new String[]{NWIS, "NAWQA", "USGS-05425700"};
	public static final String[] NWIS_PRJMLW2 = new String[]{NWIS, "NAWQA", "USGS-431925089002701"};
	public static final String[] STORET_PRJMLW1 = new String[]{STORET, "WR047", "WIDNR_WQX-113086"};
	public static final String[] STORET_PRJMLW2 = new String[]{STORET, "Lake-BaselineMonitoringDNR", "WIDNR_WQX-113086"};
	public static final String[] STORET_PRJMLW3 = new String[]{STORET, "SAM", "21NYDECA_WQX-ONTARIO-02"};
	public static final String[] BIODATA_PRJMLW = new String[]{BIODATA, "SACR BioTDB", "USGS-11421000"};

	public static final int PRJ_ML_WEIGHTING_COLUMN_COUNT = TestProjectMLWeightingMap.PROJECT_MONITORING_LOCATION_WEIGHTING.keySet().size();

	@Before
	public void init() {
		handler = new TestResultHandler();
		filter = new FilterParameters();
	}

	@After
	public void cleanup() {
		handler = null;
		filter = null;
	}

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
		init();
		streamingDao.stream(nameSpace, null, handler);
		assertEquals(TOTAL_PRJ_ML_WEIGHTING_COUNT, String.valueOf(handler.getResults().size()));
		cleanup();
	}

	public void emptyParameterTest() {
		init();
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(TOTAL_PRJ_ML_WEIGHTING_COUNT, String.valueOf(handler.getResults().size()));
		cleanup();
	}

	public void allDataSortedTest() {
		init();
		filter.setSorted("yes");
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		//Validate the number AND order of results.
		assertEquals(TOTAL_PRJ_ML_WEIGHTING_COUNT, String.valueOf(results.size()));

		assertProjectMLWeighting(results.get(0), STEWARDS_PRJMLW);
		assertProjectMLWeighting(results.get(1), NWIS_PRJMLW1);
		assertProjectMLWeighting(results.get(2), NWIS_PRJMLW2);
		assertProjectMLWeighting(results.get(3), STORET_PRJMLW3);
		assertProjectMLWeighting(results.get(4), STORET_PRJMLW2);
		assertProjectMLWeighting(results.get(5), STORET_PRJMLW1);
		assertProjectMLWeighting(results.get(6), BIODATA_PRJMLW);
		cleanup();
	}

	public void bboxTest() {
		init();
		filter.setBBox(getBBox());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void countryTest() {
		init();
		filter.setCountrycode(getCountry());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1,  STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
		cleanup();
	}

	public void countyTest() {
		init();
		filter.setCountycode(getCounty());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void huc2Test() {
		init();
		filter.setHuc(getHuc2());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void huc3Test() {
		init();
		filter.setHuc(getHuc3());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void huc4Test() {
		init();
		filter.setHuc(getHuc4());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void huc5Test() {
		init();
		filter.setHuc(getHuc5());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void huc6Test() {
		init();
		filter.setHuc(getHuc6());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void huc7Test() {
		init();
		filter.setHuc(getHuc7());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void huc8Test() {
		init();
		filter.setHuc(getHuc8());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
		cleanup();
	}

	public void huc10Test() {
		init();
		filter.setHuc(getHuc10());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
		cleanup();
	}

	public void huc12Test() {
		init();
		filter.setHuc(getHuc12());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
		cleanup();
	}

	public void nldiUrlTest() {
		init();
		try {
			filter.setNldiSites(getManySiteId());
			streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
		cleanup();
	}

	public void organizationTest() {
		init();
		filter.setOrganization(getOrganization());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void providersTest() {
		init();
		filter.setProviders(getProviders());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STORET_PRJMLW3, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
		cleanup();
	}

	public void siteIdTest() {
		init();
		filter.setSiteid(getSiteid());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void manySitesTest() {
		init();
		try {
			filter.setSiteid(getManySiteId());
			streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2);
		cleanup();
	}

	public void siteTypeTest() {
		init();
		filter.setSiteType(getSiteType());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
		cleanup();
	}

	public void stateTest() {
		init();
		filter.setStatecode(getState());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}
	
	public void withinTest() {
		init();
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, STORET_PRJMLW1, STORET_PRJMLW2, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void projectTest() {
		init();
		filter.setProject(getProject());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, BIODATA_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void sampleMediaTest() {
		init();
		filter.setSampleMedia(getSampleMedia());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
		cleanup();
	}

	public void startDateHiTest() {
		init();
		filter.setStartDateHi(getStartDateHi());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW1, STORET_PRJMLW2, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
		cleanup();
	}

	public void startDateLoTest() {
		init();
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2, BIODATA_PRJMLW);
		cleanup();
	}

	public void analyticalMethodTest() {
		init();
		filter.setAnalyticalmethod(getAnalyticalMethod());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void assemblageTest() {
		init();
		filter.setAssemblage(getAssemblage());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsProjectMLWeightings(results, BIODATA_PRJMLW);
		cleanup();
	}

	public void characteristicNameTest() {
		init();
		filter.setCharacteristicName(getCharacteristicName());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(0, results.size());
		cleanup();
	}

	public void characteristicTypeTest() {
		init();
		filter.setCharacteristicType(getCharacteristicType());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW);
		cleanup();
	}

	public void pcodeTest() {
		init();
		filter.setPCode(getPcode());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void subjectTaxonomicNameTest() {
		init();
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsProjectMLWeightings(results, BIODATA_PRJMLW);
		cleanup();
	}

	public void multipleParameterStationSumTest() {
		init();
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void multipleParameterActivitySumTest() {
		init();
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW1, NWIS_PRJMLW2);
		cleanup();
	}

	public void multipleParameterResultSumTest() {
		init();
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());

		filter.setPCode(getPcode());
		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setAssemblage(getAssemblage());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(0, results.size());
		assertContainsProjectMLWeightings(results);
		cleanup();
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
