package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ProjectColumn;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ProjectStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(ProjectStreamingTest.class);

	@Autowired
	IStreamingDao streamingDao;

	TestResultHandler handler;
	FilterParameters filter;

	public static final String [] PROJECT_LAKE_BASELINE = new String[] {STORET, "Lake-BaselineMonitoringDNR"};
	public static final String [] PROJECT_SAM = new String[] {STORET, "SAM"};
	public static final String [] PROJECT_WR047 = new String[] {STORET, "WR047"};
	public static final String [] PROJECT_PROJECTID = new String[] {STORET, "projectId"};
	public static final String [] PROJECT_EPABEACH = new String[] {STORET, "EPABEACH"};
	public static final String [] PROJECT_CEAP = new String[] {STEWARDS, "CEAP"};
	public static final String [] PROJECT_NAWQA = new String[] {NWIS, "NAWQA"};
	public static final String [] PROJECT_SOMETHINGELSE = new String[] {STORET, "SOMETHINGELSE"};
	public static final String [] PROJECT_SACR_BIOTDB = new String[] {BIODATA, "SACR BioTDB"};

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

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Activity_Sum

	@Test
	public void nullParameterTest() {
		streamingDao.stream(NameSpace.PROJECT, null, handler);
		assertEquals(TOTAL_PROJECT_COUNT, String.valueOf(handler.getResults().size()));
	}

	@Test
	public void emptyParameterTest() {
		streamingDao.stream(NameSpace.PROJECT, filter, handler);
		assertEquals(TOTAL_PROJECT_COUNT, String.valueOf(handler.getResults().size()));
	}

	@Test
	public void avoidTest() {
		filter.setCommand(getCommand());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(STORET_PROJECT_COUNT, String.valueOf(results.size()));
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_EPABEACH, PROJECT_PROJECTID, PROJECT_SAM, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void bboxTest() {
		filter.setBBox(getBBox());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsProject(results, PROJECT_NAWQA, PROJECT_CEAP, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_PROJECTID, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void countryTest() {
		filter.setCountrycode(getCountry());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		System.out.println(results);
		assertContainsProject(results, PROJECT_SACR_BIOTDB, PROJECT_NAWQA, PROJECT_CEAP, PROJECT_LAKE_BASELINE, PROJECT_WR047,
				PROJECT_EPABEACH, PROJECT_PROJECTID, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void countyTest() {
		filter.setCountycode(getCounty());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_PROJECTID, PROJECT_CEAP,
				PROJECT_NAWQA, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void huc2Test() {
		filter.setHuc(getHuc2());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_PROJECTID, PROJECT_CEAP, PROJECT_NAWQA,
				PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void huc3Test() {
		filter.setHuc(getHuc3());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_PROJECTID, PROJECT_CEAP, PROJECT_NAWQA,
				PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void huc4Test() {
		filter.setHuc(getHuc4());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_NAWQA);
	}

	@Test
	public void huc5Test() {
		filter.setHuc(getHuc5());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_NAWQA);
	}

	@Test
	public void huc6Test() {
		filter.setHuc(getHuc6());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_NAWQA);
	}

	@Test
	public void huc7Test() {
		filter.setHuc(getHuc7());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_NAWQA);
	}

	@Test
	public void huc8Test() {
		filter.setHuc(getHuc8());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047);
	}

	@Test
	public void huc10Test() {
		filter.setHuc(getHuc10());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047);
	}

	@Test
	public void huc12Test() {
		filter.setHuc(getHuc12());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047);
	}

	@Test
	public void nldiUrlTest() {
		try {
			filter.setNldiSites(getManySiteId());
			streamingDao.stream(NameSpace.PROJECT, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		System.out.println(results);
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_PROJECTID);
	}

	@Test
	public void organizationTest() {
		filter.setOrganization(getOrganization());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_PROJECTID, PROJECT_CEAP,
				PROJECT_NAWQA, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void providersTest() {
		filter.setProviders(getProviders());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_SAM, PROJECT_WR047, PROJECT_PROJECTID, PROJECT_CEAP,
				PROJECT_NAWQA, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void siteIdTest() {
		filter.setSiteid(getSiteid());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_CEAP, PROJECT_NAWQA, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void manySitesTest() {
		try {
			filter.setSiteid(getManySiteId());
			streamingDao.stream(NameSpace.PROJECT, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_PROJECTID);
	}

	@Test
	public void minActivitiesTest() {
		filter.setMinactivities(getMinActivities());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_SAM, PROJECT_NAWQA, PROJECT_CEAP);
	}

	@Test
	public void minResultsTest() {
		filter.setMinresults(getMinResults());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_NAWQA, PROJECT_SAM, PROJECT_WR047, PROJECT_PROJECTID);
	}

	@Test
	public void siteTypeTest() {
		filter.setSiteType(getSiteType());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_SAM, PROJECT_WR047, PROJECT_PROJECTID, PROJECT_CEAP,
				PROJECT_NAWQA, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE, PROJECT_SACR_BIOTDB);
	}

	@Test
	public void stateTest() {
		filter.setStatecode(getState());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_WR047, PROJECT_PROJECTID, PROJECT_CEAP,
				PROJECT_NAWQA, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}
	
	@Test
	public void withinTest() {
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProject(results, PROJECT_NAWQA, PROJECT_CEAP, PROJECT_LAKE_BASELINE, PROJECT_WR047,
				PROJECT_PROJECTID, PROJECT_SAM);
	}

	@Test
	public void projectTest() {
		filter.setProject(getProject());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_CEAP, PROJECT_NAWQA, PROJECT_SACR_BIOTDB);
	}

	@Test
	public void sampleMediaTest() {
		filter.setSampleMedia(getSampleMedia());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE, PROJECT_SAM, PROJECT_WR047, PROJECT_PROJECTID,
				PROJECT_CEAP, PROJECT_NAWQA, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE, PROJECT_SACR_BIOTDB);
	}

	@Test
	public void startDateHiTest() {
		filter.setStartDateHi(getStartDateHi());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsProject(results, PROJECT_SACR_BIOTDB, PROJECT_NAWQA, PROJECT_CEAP, PROJECT_LAKE_BASELINE,
				PROJECT_WR047, PROJECT_EPABEACH, PROJECT_PROJECTID, PROJECT_SAM, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void startDateLoTest() {
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_CEAP, PROJECT_NAWQA, PROJECT_EPABEACH,
				PROJECT_SOMETHINGELSE, PROJECT_SACR_BIOTDB);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Result_Sum

	@Test
	public void analyticalMethodTest() {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_NAWQA, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void assemblageTest() {
		filter.setAssemblage(getAssemblage());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE, PROJECT_SACR_BIOTDB);
	}

	@Test
	public void characteristicNameTest() {
		filter.setCharacteristicName(getCharacteristicName());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void characteristicTypeTest() {
		filter.setCharacteristicType(getCharacteristicType());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_CEAP, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void pcodeTest() {
		filter.setPCode(getPcode());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_NAWQA, PROJECT_EPABEACH, PROJECT_SOMETHINGELSE);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProject(results, PROJECT_PROJECTID, PROJECT_SACR_BIOTDB);
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
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsProject(results, PROJECT_PROJECTID);
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
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsProject(results, PROJECT_PROJECTID);
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
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		System.out.println(results);
		assertEquals(1, results.size());
		assertContainsProject(results, PROJECT_PROJECTID);
	}

	public void assertContainsProject(LinkedList<Map<String, Object>> results, String[]... projects) {
		for (Map<String, Object> result: results) {
			LOG.debug(ProjectColumn.KEY_DATA_SOURCE_ID + ":" + result.get(ProjectColumn.KEY_DATA_SOURCE_ID) + '/' + ProjectColumn.KEY_PROJECT_ID + ":" + result.get(ProjectColumn.KEY_PROJECT_ID));
		}

		for (String[] i : projects) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(ProjectColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(ProjectColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(result.get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString())) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(ProjectColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + ProjectColumn.KEY_SITE_ID + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals("Double check result set expected size", projects.length, results.size());
	}

}
