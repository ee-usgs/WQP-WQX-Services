package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.ProjectColumn;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ProjectStreamingTest extends BaseStreamingTest {
	private static final Logger LOG = LoggerFactory.getLogger(ProjectStreamingTest.class);

	protected NameSpace nameSpace = NameSpace.PROJECT;

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

	@Test
	public void testHarness() {
		nullParameterTest();
		emptyParameterTest();
//		allDataSortedTest();
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
//		minActivitiesTest();
//		minResultsTest();
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
	// Activity_Sum

	public void nullParameterTest() {
		nullParameterTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	public void avoidTest() {
		LinkedList<Map<String, Object>> results = avoidTest(nameSpace, Integer.valueOf(STORET_PROJECT_COUNT));
		assertContainsProject(results, PROJECT_LAKE_BASELINE_21FLVEMD, PROJECT_WR047_21LABCH, PROJECT_EPABEACH_BLMRW,
				PROJECT_PROJECTID_ORGANIZATION, PROJECT_SAM_MDNR, PROJECT_SOMETHINGELSE_UMC, PROJECT_NORESULTSPROJECT_BLAH,
				PROJECT_EPABEACH_ORGANIZATION, PROJECT_WR047_WIDNR_WQX, PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void bboxTest() {
		LinkedList<Map<String, Object>> results = bboxTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI,
				PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	public void countryTest() {
		LinkedList<Map<String, Object>> results = countryTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	public void countyTest() {
		LinkedList<Map<String, Object>> results = countyTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void huc2Test() {
		LinkedList<Map<String, Object>> results = huc2Test(nameSpace, 4);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_CEAP_ARS, PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc3Test() {
		LinkedList<Map<String, Object>> results = huc3Test(nameSpace, 4);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_CEAP_ARS, PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc4Test() {
		LinkedList<Map<String, Object>> results = huc4Test(nameSpace, 3);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc5Test() {
		LinkedList<Map<String, Object>> results = huc5Test(nameSpace, 3);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc6Test() {
		LinkedList<Map<String, Object>> results = huc6Test(nameSpace, 3);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc7Test() {
		LinkedList<Map<String, Object>> results = huc7Test(nameSpace, 3);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc8Test() {
		LinkedList<Map<String, Object>> results = huc8Test(nameSpace, 2);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	public void huc10Test() {
		LinkedList<Map<String, Object>> results = huc10Test(nameSpace, 2);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	public void huc12Test() {
		LinkedList<Map<String, Object>> results = huc12Test(nameSpace, 2);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	public void nldiUrlTest() {
		LinkedList<Map<String, Object>> results = nldiUrlTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION);
	}

	public void organizationTest() {
		LinkedList<Map<String, Object>> results = organizationTest(nameSpace, 8);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION,
				PROJECT_WR047_WIDNR_WQX, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_PROJECTIDNWIS_USGS_WI, PROJECT_PROJECTIDSTEWARDS_ARS);
	}

	public void providersTest() {
		LinkedList<Map<String, Object>> results = providersTest(nameSpace, 16);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_21FLVEMD, PROJECT_SAM_MDNR, PROJECT_WR047_21LABCH, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_EPABEACH_BLMRW, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_SC, PROJECT_SOMETHINGELSE_UMC, PROJECT_NORESULTSPROJECT_BLAH,
				PROJECT_NORESULTSPROJECT2_BLAH2, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_PROJECTIDNWIS_USGS_WI, PROJECT_PROJECTIDSTEWARDS_ARS);
	}

	public void siteIdTest() {
		LinkedList<Map<String, Object>> results = siteIdTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void manySitesTest() {
		LinkedList<Map<String, Object>> results = manySitesTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION);
	}

	public void siteTypeTest() {
		LinkedList<Map<String, Object>> results = siteTypeTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	public void stateTest() {
		LinkedList<Map<String, Object>> results = stateTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void withinTest() {
		LinkedList<Map<String, Object>> results = withinTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_CEAP_ARS, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX,
				PROJECT_PROJECTID_ORGANIZATION, PROJECT_NAWQA_USGS_WI);
	}

	public void projectTest() {
		LinkedList<Map<String, Object>> results = projectTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_SC, PROJECT_SACR_BIOTDB_USGS,
				PROJECT_NAWQA_USGS_WI);
	}

	public void sampleMediaTest() {
		LinkedList<Map<String, Object>> results = sampleMediaTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	public void startDateHiTest() {
		LinkedList<Map<String, Object>> results = startDateHiTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	public void startDateLoTest() {
		LinkedList<Map<String, Object>> results = startDateLoTest(nameSpace, 4);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI,
				PROJECT_SACR_BIOTDB_USGS);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Result_Sum

	public void analyticalMethodTest() {
		LinkedList<Map<String, Object>> results = analyticalMethodTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION);
	}

	public void assemblageTest() {
		LinkedList<Map<String, Object>> results = assemblageTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_EPABEACH_ORGANIZATION, PROJECT_SACR_BIOTDB_USGS);
	}

	public void characteristicNameTest() {
		LinkedList<Map<String, Object>> results = characteristicNameTest(nameSpace, 2);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_EPABEACH_ORGANIZATION);
	}

	public void characteristicTypeTest() {
		LinkedList<Map<String, Object>> results = characteristicTypeTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_EPABEACH_ORGANIZATION);
	}

	public void pcodeTest() {
		LinkedList<Map<String, Object>> results = pcodeTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION);
	}

	public void subjectTaxonomicNameTest() {
		LinkedList<Map<String, Object>> results = subjectTaxonomicNameTest(nameSpace, 2);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_SACR_BIOTDB_USGS);
	}

	public void multipleParameterStationSumTest() {
		FilterParameters filter = new FilterParameters();
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
		LinkedList<Map<String, Object>> results = callDao(nameSpace, 3, filter);
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void multipleParameterActivitySumTest() {
		FilterParameters filter = new FilterParameters();
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
		LinkedList<Map<String, Object>> results = callDao(nameSpace, 3, filter);
		assertEquals(3, results.size());
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void multipleParameterResultSumTest() {
		FilterParameters filter = new FilterParameters();
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
		LinkedList<Map<String, Object>> results = callDao(nameSpace, 1, filter);
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
