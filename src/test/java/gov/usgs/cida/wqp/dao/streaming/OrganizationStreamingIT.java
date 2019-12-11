package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.dao.FilteredProjectDaoTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.OrganizationColumn;
import gov.usgs.cida.wqp.mapping.ProjectColumn;
import gov.usgs.cida.wqp.mapping.TestOrganizationMap;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class OrganizationStreamingIT extends FilteredProjectDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationStreamingIT.class);

	@Autowired 
	IStreamingDao streamingDao;

	protected NameSpace nameSpace = NameSpace.ORGANIZATION;

	public static final String [] STEWARDS_ARS = new String[] {STEWARDS, "ARS"};
	public static final String [] NWIS_USGS_WI = new String[] {NWIS, "USGS-WI"};
	public static final String [] STORET_ORGANIZATION = new String[] {STORET, "organization"};
	public static final String [] STORET_WIDNR_WQX = new String[] {STORET, "WIDNR_WQX"};
	public static final String [] STORET_21NYDECA_WQX = new String[] {STORET, "21NYDECA_WQX"};
	public static final String [] STORET_11NPSWRD = new String[] {STORET, "11NPSWRD"};
	public static final String [] STORET_NODATA = new String[] {STORET, "NoData"};
	public static final String [] BIODATA_USGS = new String[] {BIODATA, "USGS"};

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
		activityTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
	}

	public void avoidTest() {
		List<Map<String, Object>> results = avoidTest(nameSpace, 4);
		assertContainsOrganization(results, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_21NYDECA_WQX, STORET_11NPSWRD);
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
	}

	public void mimeTypeTest() {
		mimeTypeJsonTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
		mimeTypeGeoJsonTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
		mimeTypeKmlTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
		mimeTypeKmzTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
		mimeTypeCsvTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
		mimeTypeTsvTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
		mimeTypeXmlTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
		mimeTypeXlsxTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
	}

	public void nldiUrlTest() {
		nldiUrlTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
	}

	public void nullParameterTest() {
		nullParameterTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
	}

	public void organizationTest() {
		List<Map<String, Object>> results = organizationTest(nameSpace, 5);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_11NPSWRD);
	}

	public void providersTest() {
		List<Map<String, Object>> results = providersTest(nameSpace, 7);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_21NYDECA_WQX, STORET_11NPSWRD, STORET_NODATA);
	}

	public void projectTest() {
		List<Map<String, Object>> results = projectTest(nameSpace, 5);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_11NPSWRD, BIODATA_USGS);
	}

	public void resultTest() {
		resultTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
	}

	public void siteUrlBaseTest() {
		siteUrlBaseTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
	}

	public void sortedTest() {
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
		assertEquals(STEWARDS_ARS[1], results.get(0).get(OrganizationColumn.KEY_ORGANIZATION).toString());
		assertEquals(NWIS_USGS_WI[1], results.get(1).get(OrganizationColumn.KEY_ORGANIZATION).toString());
		assertEquals(STORET_11NPSWRD[1], results.get(2).get(OrganizationColumn.KEY_ORGANIZATION).toString());
		assertEquals(STORET_21NYDECA_WQX[1], results.get(3).get(OrganizationColumn.KEY_ORGANIZATION).toString());
		assertEquals(STORET_NODATA[1], results.get(4).get(OrganizationColumn.KEY_ORGANIZATION).toString());
		assertEquals(STORET_WIDNR_WQX[1], results.get(5).get(OrganizationColumn.KEY_ORGANIZATION).toString());
		assertMapIsAsExpected(TestOrganizationMap.ORGANIZATION, results.get(6));
		assertEquals(BIODATA_USGS[1], results.get(7).get(OrganizationColumn.KEY_ORGANIZATION).toString());
	}

	public void zipTest() {
		zipTest(nameSpace, Integer.valueOf(TOTAL_ORGANIZATION_COUNT));
	}

	public void bboxTest() {
		List<Map<String, Object>> results = bboxTest(nameSpace, 5);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_11NPSWRD);
	}

	public void countryTest() {
		List<Map<String, Object>> results = countryTest(nameSpace, 6);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_11NPSWRD, BIODATA_USGS);
	}

	public void countyTest() {
		List<Map<String, Object>> results = countyTest(nameSpace, 5);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_11NPSWRD);
	}

	public void huc2Test() {
		List<Map<String, Object>> results = huc2Test(nameSpace, 4);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_WIDNR_WQX, STORET_11NPSWRD);
	}

	public void huc3Test() {
		List<Map<String, Object>> results = huc3Test(nameSpace, 4);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_WIDNR_WQX, STORET_11NPSWRD);
	}

	public void huc4Test() {
		List<Map<String, Object>> results = huc4Test(nameSpace, 2);
		assertContainsOrganization(results, NWIS_USGS_WI, STORET_WIDNR_WQX);
	}

	public void huc5Test() {
		List<Map<String, Object>> results = huc5Test(nameSpace, 2);
		assertContainsOrganization(results, NWIS_USGS_WI, STORET_WIDNR_WQX);
	}

	public void huc6Test() {
		List<Map<String, Object>> results = huc6Test(nameSpace, 2);
		assertContainsOrganization(results, NWIS_USGS_WI, STORET_WIDNR_WQX);
	}

	public void huc7Test() {
		List<Map<String, Object>> results = huc7Test(nameSpace, 2);
		assertContainsOrganization(results, NWIS_USGS_WI, STORET_WIDNR_WQX);
	}

	public void huc8Test() {
		List<Map<String, Object>> results = huc8Test(nameSpace, 1);
		assertContainsOrganization(results, STORET_WIDNR_WQX);
	}

	public void huc10Test() {
		List<Map<String, Object>> results = huc10Test(nameSpace, 1);
		assertContainsOrganization(results, STORET_WIDNR_WQX);
	}

	public void huc12Test() {
		List<Map<String, Object>> results = huc12Test(nameSpace, 1);
		assertContainsOrganization(results, STORET_WIDNR_WQX);
	}

	public void minActivitiesTest() {
		List<Map<String, Object>> results = minActivitiesTest(nameSpace, 6);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_21NYDECA_WQX, STORET_11NPSWRD);
	}

	public void minResultsTest() {
		List<Map<String, Object>> results = minResultsTest(nameSpace, 6);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_21NYDECA_WQX, STORET_11NPSWRD);
	}

	public void nldiSitesTest() {
		List<Map<String, Object>> results = nldiSitesTest(nameSpace, 2);
		assertContainsOrganization(results, STORET_ORGANIZATION, STORET_WIDNR_WQX);
	};

	public void sampleMediaTest() {
		List<Map<String, Object>> results = sampleMediaTest(nameSpace, 7);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_21NYDECA_WQX, STORET_11NPSWRD, BIODATA_USGS);
	}

	public void siteIdTest() {
		List<Map<String, Object>> results = siteIdTest(nameSpace, 5);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_11NPSWRD);
	}

	public void siteIdLargeListTest() {
		List<Map<String, Object>> results = siteIdLargeListTest(nameSpace, 2);
		assertContainsOrganization(results, STORET_ORGANIZATION, STORET_WIDNR_WQX);
	}

	public void siteTypeTest() {
		List<Map<String, Object>> results = siteTypeTest(nameSpace, 7);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_21NYDECA_WQX, STORET_11NPSWRD, BIODATA_USGS);
	}

	public void startDateHiTest() {
		List<Map<String, Object>> results = startDateHiTest(nameSpace, 7);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_21NYDECA_WQX, STORET_11NPSWRD, BIODATA_USGS);
	}

	public void startDateLoTest() {
		List<Map<String, Object>> results = startDateLoTest(nameSpace, 5);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_11NPSWRD, BIODATA_USGS);
	}

	public void stateTest() {
		List<Map<String, Object>> results = stateTest(nameSpace, 5);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_11NPSWRD);
	}

	public void withinTest() {
		List<Map<String, Object>> results = withinTest(nameSpace, 5);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION, STORET_WIDNR_WQX, STORET_21NYDECA_WQX);
	}

	public void analyticalMethodTest() {
		List<Map<String, Object>> results = analyticalMethodTest(nameSpace, 2);
		assertContainsOrganization(results, NWIS_USGS_WI, STORET_ORGANIZATION);
	}

	public void assemblageTest() {
		List<Map<String, Object>> results = assemblageTest(nameSpace, 2);
		assertContainsOrganization(results, STORET_ORGANIZATION, BIODATA_USGS);
	}

	public void characteristicNameTest() {
		List<Map<String, Object>> results = characteristicNameTest(nameSpace, 1);
		assertContainsOrganization(results, STORET_ORGANIZATION);
	}

	public void characteristicTypeTest() {
		List<Map<String, Object>> results = characteristicTypeTest(nameSpace, 2);
		assertContainsOrganization(results, STEWARDS_ARS, STORET_ORGANIZATION);
	}

	public void pcodeTest() {
		List<Map<String, Object>> results = pcodeTest(nameSpace, 2);
		assertContainsOrganization(results, NWIS_USGS_WI, STORET_ORGANIZATION);
	}

	public void subjectTaxonomicNameTest() {
		List<Map<String, Object>> results = subjectTaxonomicNameTest(nameSpace, 2);
		assertContainsOrganization(results, STORET_ORGANIZATION, BIODATA_USGS);
	}

	public void multipleParameterTest() {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 3);
		assertContainsOrganization(results, STEWARDS_ARS, NWIS_USGS_WI, STORET_ORGANIZATION);
	}

	public void multipleParameterActivitySumTest() {
		List<Map<String, Object>> results = multipleParameterActivitySumTest(nameSpace, 1);
		assertContainsOrganization(results, STORET_ORGANIZATION);
	}

	public void multipleParameterResultSumTest() {
		List<Map<String, Object>> results = multipleParameterResultSumTest(nameSpace, 1);
		assertContainsOrganization(results, STORET_ORGANIZATION);
	}

	public void assertContainsOrganization(List<Map<String, Object>> results, String[]... organizations) {
		LOG.debug("Result Keys:");
		for (Map<String, Object> result: results) {
			LOG.debug(ProjectColumn.KEY_DATA_SOURCE_ID + ":" + result.get(ProjectColumn.KEY_DATA_SOURCE_ID) + '/' 
					+ ProjectColumn.KEY_ORGANIZATION + ":" + result.get(ProjectColumn.KEY_ORGANIZATION));
		}

		for (String[] i : organizations) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(ProjectColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(ProjectColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(result.get(ProjectColumn.KEY_ORGANIZATION).toString())) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(ProjectColumn.KEY_DATA_SOURCE + ":" + i[0] +
						"/" + ProjectColumn.KEY_ORGANIZATION + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals(organizations.length, results.size(), "Double check result set expected size");
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
		// Nothing to do here
	}

}
