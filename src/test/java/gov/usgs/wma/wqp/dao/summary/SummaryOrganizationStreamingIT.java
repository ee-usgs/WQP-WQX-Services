package gov.usgs.wma.wqp.dao.summary;

import static gov.usgs.wma.wqp.mapping.TestSummaryOrganizationMap.SUMMARY_ORGANIZATION_ALL_YEAR;
import static gov.usgs.wma.wqp.mapping.TestSummaryOrganizationMap.SUMMARY_ORGANIZATION_CURRENT_YEAR;
import static gov.usgs.wma.wqp.mapping.TestSummaryOrganizationMap.SUMMARY_ORGANIZATION_FIVE_YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.wma.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.wma.wqp.dao.FilteredDaoTest;
import gov.usgs.wma.wqp.dao.NameSpace;
import gov.usgs.wma.wqp.dao.StreamingDao;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.OrganizationColumn;
import gov.usgs.wma.wqp.mapping.TestResultHandler;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/organizationSum.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public class SummaryOrganizationStreamingIT extends FilteredDaoTest {

	protected NameSpace nameSpace = NameSpace.SUMMARY_ORGANIZATION;

	public static final String ORG_ID_TEST_1 = "R10ELKHEADMINE";
	public static final String ORG_ID_TEST_2 = "R9VOL";
	public static final String DATA_SOURCE_TEST = "STORET";
	public static final String[] STORET_TEST = new String[]{"Storet", ORG_ID_TEST_1};
	public static final String[] STORET_TEST_2 = new String[]{"Storet", ORG_ID_TEST_2};
	public static final String SUMMARY_YEARS_12_MONTHS = "1";
	public static final String SUMMARY_YEARS_60_MONTHS = "5";
	public static final String SUMMARY_YEARS_ALL_MONTHS = "all";

	public static final String TOTAL_ORGANIZATION_SUMMARY_COUNT = "2";
	public static final int EXPECTED_SIZE = 2;	

	@Autowired 
	IStreamingDao streamingDao;

	@Test
	public void testHarness() {
		containsOrganizationTest(nameSpace);
		siteUrlBaseTest(nameSpace, EXPECTED_SIZE);
		allYearsSummaryTest(nameSpace);
		fiveYearsSummaryTest(nameSpace);
		oneYearSummaryTest(nameSpace);
	}

	@Override
	public List<Map<String, Object>> callDao(NameSpace nameSpace, int expectedSize, FilterParameters filter) {
		TestResultHandler handler = new TestResultHandler();
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(expectedSize, handler.getResults().size());
		return handler.getResults();
	}

	@Override
	public List<Map<String, Object>> organizationTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setOrganization(getOrganization());
		filter.setSiteUrlBase(getSiteUrlBase());
		return callDao(nameSpace, expectedSize, filter);
	}

	@Override
	public List<String> getOrganization() {
		return Arrays.asList(ORG_ID_TEST_1, ORG_ID_TEST_2);
	}
	
	@Override
	protected void assertSiteUrlBase(Map<String, Object> row) {
		assertUrl(OrganizationColumn.KEY_ORGANIZATION_SUMMARY_WQP_URL, row);
	}

	public List<Map<String, Object>> sumTest(String summaryYears, NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSummaryYears(summaryYears);
		filter.setSiteUrlBase(getSiteUrlBase());
		return callDao(nameSpace, expectedSize, filter);
	}

	private void containsOrganizationTest(NameSpace nameSpace) {
		String[] testOrganizations = {DATA_SOURCE_TEST, ORG_ID_TEST_2};
		List<Map<String, Object>> results = organizationTest(nameSpace, 2);
		assertContainsOrganization(results, testOrganizations);
	}

	public void assertRow(List<Map<String, Object>> results, Map<String, Object> expectedRow) {
		if (results.get(0).containsKey(OrganizationColumn.KEY_ORGANIZATION)&&results.get(0).get(OrganizationColumn.KEY_ORGANIZATION).toString().equalsIgnoreCase(ORG_ID_TEST_1)) {
			assertMapIsAsExpected(expectedRow, results.get(0));
			assertEquals(expectedRow.keySet().size(), results.get(0).keySet().size());
		}
		if (results.get(1).containsKey(OrganizationColumn.KEY_ORGANIZATION)&&results.get(1).get(OrganizationColumn.KEY_ORGANIZATION).toString().equalsIgnoreCase(ORG_ID_TEST_1)) {
			assertMapIsAsExpected(expectedRow, results.get(1));
			assertEquals(expectedRow.keySet().size(), results.get(1).keySet().size());
		}
	}

	public void assertContainsOrganization(List<Map<String, Object>> results, String[]... organizations) {
		for (String[] i : organizations) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(OrganizationColumn.KEY_ORGANIZATION))
				{
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(OrganizationColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + OrganizationColumn.KEY_ORGANIZATION_ID + ":" + i[1] + " was not in the result set.");
			}
		}
	}

	public void allYearsSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> results =
				sumTest(
						SUMMARY_YEARS_ALL_MONTHS,
						nameSpace,
						Integer.valueOf(TOTAL_ORGANIZATION_SUMMARY_COUNT));
		assertRow(results, SUMMARY_ORGANIZATION_ALL_YEAR);
	}

	public void fiveYearsSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> results =
				sumTest(
						SUMMARY_YEARS_60_MONTHS,
						nameSpace,
						Integer.valueOf(TOTAL_ORGANIZATION_SUMMARY_COUNT));
		assertRow(results, SUMMARY_ORGANIZATION_FIVE_YEAR);
	}

	public void oneYearSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> results =
				sumTest(
						SUMMARY_YEARS_12_MONTHS,
						nameSpace,
						Integer.valueOf(TOTAL_ORGANIZATION_SUMMARY_COUNT));
		assertRow(results, SUMMARY_ORGANIZATION_CURRENT_YEAR);
	}
}
