package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.FilteredProjectDaoTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ProjectColumn;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ProjectStreamingTest extends FilteredProjectDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(ProjectStreamingTest.class);

	@Autowired 
	IStreamingDao streamingDao;

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
		activityTest();
		analyticalMethodTest();
		assemblageTest();
		avoidTest();
		bboxTest();
		characteristicNameTest();
		characteristicTypeTest();
		countryTest();
		countyTest();
		emptyParameterTest();
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
		nldiSitesTest();
		nldiUrlTest();
		nullParameterTest();
		organizationTest();
		pcodeTest();
		projectTest();
		providersTest();
		resultTest();
		sampleMediaTest();
		siteIdTest();
		siteIdLargeListTest();
		siteTypeTest();
		siteUrlBaseTest();
		sortedTest();
		startDateHiTest();
		startDateLoTest();
		stateTest();
		subjectTaxonomicNameTest();
		withinTest();
		zipTest();
		multipleParameterTest();
		multipleParameterActivitySumTest();
		multipleParameterResultSumTest();
	}

	public void activityTest() {
		activityTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	public void avoidTest() {
		List<Map<String, Object>> results = avoidTest(nameSpace, Integer.valueOf(STORET_PROJECT_COUNT));
		assertContainsProject(results, PROJECT_LAKE_BASELINE_21FLVEMD, PROJECT_WR047_21LABCH, PROJECT_EPABEACH_BLMRW,
				PROJECT_PROJECTID_ORGANIZATION, PROJECT_SAM_MDNR, PROJECT_SOMETHINGELSE_UMC, PROJECT_NORESULTSPROJECT_BLAH,
				PROJECT_EPABEACH_ORGANIZATION, PROJECT_WR047_WIDNR_WQX, PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	public void mimeTypeTest() {
		mimeTypeJsonTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
		mimeTypeGeoJsonTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
		mimeTypeKmlTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
		mimeTypeKmzTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
		mimeTypeCsvTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
		mimeTypeTsvTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
		mimeTypeXmlTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
		mimeTypeXlsxTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	public void nldiUrlTest() {
		nldiUrlTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	public void nullParameterTest() {
		nullParameterTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	public void organizationTest() {
		List<Map<String, Object>> results = organizationTest(nameSpace, 8);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION,
				PROJECT_WR047_WIDNR_WQX, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_PROJECTIDNWIS_USGS_WI, PROJECT_PROJECTIDSTEWARDS_ARS);
	}

	public void providersTest() {
		List<Map<String, Object>> results = providersTest(nameSpace, 16);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_21FLVEMD, PROJECT_SAM_MDNR, PROJECT_WR047_21LABCH, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_EPABEACH_BLMRW, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_SC, PROJECT_SOMETHINGELSE_UMC, PROJECT_NORESULTSPROJECT_BLAH,
				PROJECT_NORESULTSPROJECT2_BLAH2, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_PROJECTIDNWIS_USGS_WI, PROJECT_PROJECTIDSTEWARDS_ARS);
	}

	public void projectTest() {
		List<Map<String, Object>> results = projectTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_SC, PROJECT_SACR_BIOTDB_USGS,
				PROJECT_NAWQA_USGS_WI);
	}

	public void resultTest() {
		resultTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	public void siteUrlBaseTest() {
		siteUrlBaseTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	public void sortedTest() {
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
		assertEquals(PROJECT_LAKE_BASELINE_21FLVEMD[1], results.get(0).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_SAM_MDNR[1], results.get(1).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_WR047_21LABCH[1], results.get(2).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_PROJECTID_ORGANIZATION[1], results.get(3).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_EPABEACH_BLMRW[1], results.get(4).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_CEAP_ARS[1], results.get(5).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_NAWQA_USGS_SC[1], results.get(6).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_SOMETHINGELSE_UMC[1], results.get(7).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_SACR_BIOTDB_USGS[1], results.get(8).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_NORESULTSPROJECT_BLAH[1], results.get(9).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_NORESULTSPROJECT2_BLAH2[1], results.get(10).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_NAWQA_USGS_WI[1], results.get(11).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_EPABEACH_ORGANIZATION[1], results.get(12).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_WR047_WIDNR_WQX[1], results.get(13).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_LAKE_BASELINE_WIDNR_WQX[1], results.get(14).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_PROJECTIDNWIS_USGS_WI[1], results.get(15).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
		assertEquals(PROJECT_PROJECTIDSTEWARDS_ARS[1], results.get(16).get(ProjectColumn.KEY_PROJECT_IDENTIFIER).toString());
	}

	public void zipTest() {
		zipTest(nameSpace, Integer.valueOf(TOTAL_PROJECT_COUNT));
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Activity_Sum

	public void bboxTest() {
		List<Map<String, Object>> results = bboxTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI,
				PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	public void countryTest() {
		List<Map<String, Object>> results = countryTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	public void countyTest() {
		List<Map<String, Object>> results = countyTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void huc2Test() {
		List<Map<String, Object>> results = huc2Test(nameSpace, 4);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_CEAP_ARS, PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc3Test() {
		List<Map<String, Object>> results = huc3Test(nameSpace, 4);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_CEAP_ARS, PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc4Test() {
		List<Map<String, Object>> results = huc4Test(nameSpace, 3);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc5Test() {
		List<Map<String, Object>> results = huc5Test(nameSpace, 3);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc6Test() {
		List<Map<String, Object>> results = huc6Test(nameSpace, 3);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc7Test() {
		List<Map<String, Object>> results = huc7Test(nameSpace, 3);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX,
				PROJECT_LAKE_BASELINE_WIDNR_WQX);
	}

	public void huc8Test() {
		List<Map<String, Object>> results = huc8Test(nameSpace, 2);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	public void huc10Test() {
		List<Map<String, Object>> results = huc10Test(nameSpace, 2);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	public void huc12Test() {
		List<Map<String, Object>> results = huc12Test(nameSpace, 2);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX);
	}

	public void minActivitiesTest() {
		List<Map<String, Object>> results = minActivitiesTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS,
				PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_SACR_BIOTDB_USGS);
	}

	public void minResultsTest() {
		List<Map<String, Object>> results = minResultsTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_NAWQA_USGS_WI, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS,
				PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_SACR_BIOTDB_USGS);
	}

	public void nldiSitesTest() {
		List<Map<String, Object>> results = nldiSitesTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION);
	};

	public void sampleMediaTest() {
		List<Map<String, Object>> results = sampleMediaTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	public void siteIdTest() {
		List<Map<String, Object>> results = siteIdTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void siteIdLargeListTest() {
		List<Map<String, Object>> results = siteIdLargeListTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION);
	}

	public void siteTypeTest() {
		List<Map<String, Object>> results = siteTypeTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	public void startDateHiTest() {
		List<Map<String, Object>> results = startDateHiTest(nameSpace, 6);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI, PROJECT_SACR_BIOTDB_USGS);
	}

	public void startDateLoTest() {
		List<Map<String, Object>> results = startDateLoTest(nameSpace, 4);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI,
				PROJECT_SACR_BIOTDB_USGS);
	}

	public void stateTest() {
		List<Map<String, Object>> results = stateTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX, PROJECT_PROJECTID_ORGANIZATION,
				PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void withinTest() {
		List<Map<String, Object>> results = withinTest(nameSpace, 5);
		assertContainsProject(results, PROJECT_CEAP_ARS, PROJECT_LAKE_BASELINE_WIDNR_WQX, PROJECT_WR047_WIDNR_WQX,
				PROJECT_PROJECTID_ORGANIZATION, PROJECT_NAWQA_USGS_WI);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Result_Sum

	public void analyticalMethodTest() {
		List<Map<String, Object>> results = analyticalMethodTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION);
	}

	public void assemblageTest() {
		List<Map<String, Object>> results = assemblageTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_EPABEACH_ORGANIZATION, PROJECT_SACR_BIOTDB_USGS);
	}

	public void characteristicNameTest() {
		List<Map<String, Object>> results = characteristicNameTest(nameSpace, 2);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_EPABEACH_ORGANIZATION);
	}

	public void characteristicTypeTest() {
		List<Map<String, Object>> results = characteristicTypeTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_EPABEACH_ORGANIZATION);
	}

	public void pcodeTest() {
		List<Map<String, Object>> results = pcodeTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_NAWQA_USGS_WI, PROJECT_EPABEACH_ORGANIZATION);
	}

	public void subjectTaxonomicNameTest() {
		List<Map<String, Object>> results = subjectTaxonomicNameTest(nameSpace, 2);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_SACR_BIOTDB_USGS);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Multiples

	public void multipleParameterTest() {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void multipleParameterActivitySumTest() {
		List<Map<String, Object>> results = multipleParameterActivitySumTest(nameSpace, 3);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION, PROJECT_CEAP_ARS, PROJECT_NAWQA_USGS_WI);
	}

	public void multipleParameterResultSumTest() {
		List<Map<String, Object>> results = multipleParameterResultSumTest(nameSpace, 1);
		assertContainsProject(results, PROJECT_PROJECTID_ORGANIZATION);
	}

	public void assertContainsProject(List<Map<String, Object>> results, String[]... projects) {
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
				fail(ProjectColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + ProjectColumn.KEY_PROJECT_IDENTIFIER + ":" + i[1] + 
						"/" + ProjectColumn.KEY_ORGANIZATION + ":" + i[2] + " was not in the result set.");
			}
		}
		assertEquals("Double check result set expected size", projects.length, results.size());
	}

	@Override
	public LinkedList<Map<String, Object>> callDao(NameSpace nameSpace, int expectedSize, FilterParameters filter) {
		TestResultHandler handler = new TestResultHandler();
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(expectedSize, handler.getResults().size());
		return handler.getResults();
	}

	@Override
	protected void assertSiteUrlBase(Map<String, Object> row) {
		if (null != row.get(ProjectColumn.KEY_PROJECT_FILE_URL)) {
			assertTrue("ProjectFileUrl incorrect", row.get(ProjectColumn.KEY_PROJECT_FILE_URL).toString().startsWith("http://siteUrlBase/"));
		}
		if (null != row.get(ProjectColumn.KEY_MONITORING_LOCATION_WEIGHT_URL)) {
			assertTrue("ProjectMonitoringLocationWeightingURL incorrect", row.get(ProjectColumn.KEY_MONITORING_LOCATION_WEIGHT_URL).toString().startsWith("http://siteUrlBase/"));
		}
	}

}
