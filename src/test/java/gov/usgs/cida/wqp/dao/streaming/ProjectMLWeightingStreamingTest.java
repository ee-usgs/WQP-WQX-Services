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
import gov.usgs.cida.wqp.mapping.ProjectMonitoringLocationWeightingColumn;
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

	public static final String[] STEWARDS_PRJMLW = new String[]{STEWARDS, "ARS-IAWC-IAWC225"};
	public static final String[] NWIS_PRJMLW = new String[]{NWIS, "USGS-05425700"};
	public static final String[] STORET_PRJMLW = new String[]{STORET, "WIDNR_WQX-113086"};
	public static final String[] BIODATA_PRJMLW = new String[]{BIODATA, "USGS-11421000"};

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
	public void nullParameterTest() {
		streamingDao.stream(nameSpace, null, handler);
		assertEquals(TOTAL_PRJ_ML_WEIGHTING_COUNT, String.valueOf(handler.getResults().size()));
	}

	@Test
	public void emptyParameterTest() {
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(TOTAL_PRJ_ML_WEIGHTING_COUNT, String.valueOf(handler.getResults().size()));
	}

	@Test
	public void allDataSortedTest() {
		filter.setSorted("yes");
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		//Validate the number AND order of results.
		assertEquals(TOTAL_PRJ_ML_WEIGHTING_COUNT, String.valueOf(results.size()));

		assertProjectMLWeighting(results.get(0), STEWARDS_PRJMLW);
		assertProjectMLWeighting(results.get(1), NWIS_PRJMLW);
		assertProjectMLWeighting(results.get(2), STORET_PRJMLW);
		assertProjectMLWeighting(results.get(3), BIODATA_PRJMLW);
	}

	@Test
	public void bboxTest() {
		filter.setBBox(getBBox());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void countryTest() {
		filter.setCountrycode(getCountry());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW, BIODATA_PRJMLW);
	}

	@Test
	public void countyTest() {
		filter.setCountycode(getCounty());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void huc2Test() {
		filter.setHuc(getHuc2());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void huc3Test() {
		filter.setHuc(getHuc3());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void huc4Test() {
		filter.setHuc(getHuc4());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void huc5Test() {
		filter.setHuc(getHuc5());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void huc6Test() {
		filter.setHuc(getHuc6());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void huc7Test() {
		filter.setHuc(getHuc7());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void huc8Test() {
		filter.setHuc(getHuc8());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW);
	}

	@Test
	public void huc10Test() {
		filter.setHuc(getHuc10());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW);
	}

	@Test
	public void huc12Test() {
		filter.setHuc(getHuc12());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW);
	}

	@Test
	public void nldiUrlTest() {
		try {
			filter.setNldiSites(getManySiteId());
			streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW);
	}

	@Test
	public void organizationTest() {
		filter.setOrganization(getOrganization());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void providersTest() {
		filter.setProviders(getProviders());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW, BIODATA_PRJMLW);
	}

	@Test
	public void siteIdTest() {
		filter.setSiteid(getSiteid());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void manySitesTest() {
		try {
			filter.setSiteid(getManySiteId());
			streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW);
	}


	@Test
	public void siteTypeTest() {
		filter.setSiteType(getSiteType());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW, BIODATA_PRJMLW);
	}

	@Test
	public void stateTest() {
		filter.setStatecode(getState());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW);
	}
	
	@Test
	public void withinTest() {
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, STORET_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void projectTest() {
		filter.setProject(getProject());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, BIODATA_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void sampleMediaTest() {
		filter.setSampleMedia(getSampleMedia());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW, BIODATA_PRJMLW);
	}

	@Test
	public void startDateHiTest() {
		filter.setStartDateHi(getStartDateHi());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProjectMLWeightings(results, STORET_PRJMLW, STEWARDS_PRJMLW, NWIS_PRJMLW, BIODATA_PRJMLW);
	}

	@Test
	public void startDateLoTest() {
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW, BIODATA_PRJMLW);
	}

	@Test
	public void analyticalMethodTest() {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, NWIS_PRJMLW);
	}

	@Test
	public void assemblageTest() {
		filter.setAssemblage(getAssemblage());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsProjectMLWeightings(results, BIODATA_PRJMLW);
	}

	@Test
	public void characteristicNameTest() {
		filter.setCharacteristicName(getCharacteristicName());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(0, results.size());
	}

	@Test
	public void characteristicTypeTest() {
		filter.setCharacteristicType(getCharacteristicType());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW);
	}

	@Test
	public void pcodeTest() {
		filter.setPCode(getPcode());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProjectMLWeightings(results, NWIS_PRJMLW);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsProjectMLWeightings(results, BIODATA_PRJMLW);
	}

	@Test
	public void multipleParameterStationSumTests() {
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
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void multipleParameterActivitySumTests() {
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
		assertContainsProjectMLWeightings(results, STEWARDS_PRJMLW, NWIS_PRJMLW);
	}

	@Test
	public void multipleParameterResultSumTest() {
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
	}

	private void assertContainsProjectMLWeightings(LinkedList<Map<String, Object>> results, String[]... prjmlws) {
		for (String[] i : prjmlws) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(BaseColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(BaseColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(((String) result.get(StationColumn.KEY_SITE_ID)))) {
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
		assertEquals(row.get(StationColumn.KEY_SITE_ID), comparison[1]);
	}
}
