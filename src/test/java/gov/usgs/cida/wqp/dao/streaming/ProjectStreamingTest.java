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

	public static final String [] PROJECT_LAKE_BASELINE_21FLVEMD = new String[] {STORET, "Lake-BaselineMonitoringDNR", "21FLVEMD"};
	public static final String [] PROJECT_SAM_MDNR = new String[] {STORET, "SAM", "MDNR"};
	public static final String [] PROJECT_WR047_21LABCH = new String[] {STORET, "WR047", "21LABCH"};
	public static final String [] PROJECT_PROJECTID_ORGANIZATION = new String[] {STORET, "projectId", "organization"};
	public static final String [] PROJECT_EPABEACH_BLMRW = new String[] {STORET, "EPABEACH", "BLMRW"};
	public static final String [] PROJECT_CEAP_ARS = new String[] {STEWARDS, "CEAP", "ARS"};
	public static final String [] PROJECT_NAWQA_USGS_SC = new String[] {NWIS, "NAWQA", "USGS-SC"};
	public static final String [] PROJECT_SOMETHINGELSE_UMC = new String[] {STORET, "SOMETHINGELSE", "UMC"};
	public static final String [] PROJECT_SACR_BIOTDB_USGS = new String[] {BIODATA, "SACR BioTDB", "USGS"};
	public static final String [] PROJECT_NORESULTSPROJECT_BLAH = new String[] {STORET, "NoResultsProject", "BLAH"};
	public static final String [] PROJECT_NORESULTSPROJECT2_BLAH2 = new String[] {NWIS, "NoResultsProject2", "BLAH2"};
	public static final String [] PROJECT_NAWQA_USGS_WI = new String[] {NWIS, "NAWQA", "USGS-WI"};
	public static final String [] PROJECT_EPABEACH_ORGANIZATION = new String[] {STORET, "EPABEACH", "organization"};
	public static final String [] PROJECT_WR047_WIDNR_WQX = new String[] {STORET, "WR047", "WIDNR_WQX"};
	public static final String [] PROJECT_LAKE_BASELINE_WIDNR_WQX = new String[] {STORET, "Lake-BaselineMonitoringDNR", "WIDNR_WQX"};
	public static final String [] PROJECT_PROJECTIDNWIS_USGS_WI = new String[] {NWIS, "projectIdNwis", "USGS-WI"};
	public static final String [] PROJECT_PROJECTIDSTEWARDS_ARS = new String[] {STEWARDS, "projectIdStewards", "ARS"};
	

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
		assertContainsProject(results, PROJECT_LAKE_BASELINE_21FLVEMD, PROJECT_WR047_21LABCH, PROJECT_EPABEACH_BLMRW,
				PROJECT_PROJECTID_ORGANIZATION, PROJECT_SAM_MDNR, PROJECT_SOMETHINGELSE_UMC, PROJECT_NORESULTSPROJECT_BLAH,
				PROJECT_EPABEACH_ORGANIZATION, PROJECT_WR047_WIDNR_WQX, PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	@Test
	public void bboxTest() {
		filter.setBBox(getBBox());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI,
				PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	@Test
	public void countryTest() {
		filter.setCountrycode(getCountry());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	@Test
	public void countyTest() {
		filter.setCountycode(getCounty());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	@Test
	public void huc2Test() {
		filter.setHuc(getHuc2());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_CEAP_ARS, PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	@Test
	public void huc3Test() {
		filter.setHuc(getHuc3());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_CEAP_ARS, PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	@Test
	public void huc4Test() {
		filter.setHuc(getHuc4());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	@Test
	public void huc5Test() {
		filter.setHuc(getHuc5());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	@Test
	public void huc6Test() {
		filter.setHuc(getHuc6());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	@Test
	public void huc7Test() {
		filter.setHuc(getHuc7());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	@Test
	public void huc8Test() {
		filter.setHuc(getHuc8());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	@Test
	public void huc10Test() {
		filter.setHuc(getHuc10());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	@Test
	public void huc12Test() {
		filter.setHuc(getHuc12());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
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
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION);
	}

	@Test
	public void organizationTest() {
		filter.setOrganization(getOrganization());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION,
				PROJECT_WR047_WIDNR_WQX, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_PROJECTIDNWIS_USGS_WI, PROJECT_PROJECTIDSTEWARDS_ARS);
	}

	@Test
	public void providersTest() {
		filter.setProviders(getProviders());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_21FLVEMD, PROJECT_SAM_MDNR, PROJECT_WR047_21LABCH, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_EPABEACH_BLMRW, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_SC, PROJECT_SOMETHINGELSE_UMC, PROJECT_NORESULTSPROJECT_BLAH,
				PROJECT_NORESULTSPROJECT2_BLAH2, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_PROJECTIDNWIS_USGS_WI, PROJECT_PROJECTIDSTEWARDS_ARS);
	}

	@Test
	public void siteIdTest() {
		filter.setSiteid(getSiteid());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
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
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION);
	}


	@Test
	public void siteTypeTest() {
		filter.setSiteType(getSiteType());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	@Test
	public void stateTest() {
		filter.setStatecode(getState());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}
	
	@Test
	public void withinTest() {
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProject(results, PROJECT_CEAP_ARS, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX,
				PROJECT_PROJECTID_ORGANIZATION, PROJECT_NAWQA_USGS_WI);
	}

	@Test
	public void projectTest() {
		filter.setProject(getProject());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(5, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_SC, PROJECT_SACR_BIOTDB_USGS,
				PROJECT_NAWQA_USGS_WI);
	}

	@Test
	public void sampleMediaTest() {
		filter.setSampleMedia(getSampleMedia());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	@Test
	public void startDateHiTest() {
		filter.setStartDateHi(getStartDateHi());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	@Test
	public void startDateLoTest() {
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI,
				PROJECT_SACR_BIOTDB_USGS);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Result_Sum

	@Test
	public void analyticalMethodTest() {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION);
	}

	@Test
	public void assemblageTest() {
		filter.setAssemblage(getAssemblage());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_EPABEACH_ORGANIZATION, PROJECT_SACR_BIOTDB_USGS);
	}

	@Test
	public void characteristicNameTest() {
		filter.setCharacteristicName(getCharacteristicName());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_EPABEACH_ORGANIZATION);
	}

	@Test
	public void characteristicTypeTest() {
		filter.setCharacteristicType(getCharacteristicType());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_EPABEACH_ORGANIZATION);
	}

	@Test
	public void pcodeTest() {
		filter.setPCode(getPcode());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(NameSpace.PROJECT, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_SACR_BIOTDB_USGS);
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
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
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
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
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
		assertEquals(1, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION);
	}

	public void assertContainsProject(LinkedList<Map<String, Object>> results, String[]... projects) {
		for (Map<String, Object> result: results) {
			LOG.debug(ProjectColumn.KEY_DATA_SOURCE_ID + ":" + result.get(ProjectColumn.KEY_DATA_SOURCE_ID) + '/' 
					+ ProjectColumn.KEY_PROJECT_ID + ":" + result.get(ProjectColumn.KEY_PROJECT_ID) + '/'
					+ ProjectColumn.KEY_ORGANIZATION + ":" + result.get(ProjectColumn.KEY_ORGANIZATION));
		}

		for (String[] i : projects) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(ProjectColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(ProjectColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(result.get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString())
						&& i[2].equalsIgnoreCase(result.get(ProjectColumn.KEY_ORGANIZATION).toString())) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(ProjectColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + ProjectColumn.KEY_SITE_ID + ":" + i[1] + 
						"/" + ProjectColumn.KEY_ORGANIZATION + ":" + i[2] + " was not in the result set.");
			}
		}
		assertEquals("Double check result set expected size", projects.length, results.size());
	}

}
