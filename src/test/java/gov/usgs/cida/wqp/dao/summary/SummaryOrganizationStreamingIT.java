package gov.usgs.cida.wqp.dao.summary;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.cida.wqp.dao.FilteredDaoTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.OrganizationColumn;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.mapping.TestSummaryOrganizationMap;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/organizationSum.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public class SummaryOrganizationStreamingIT extends FilteredDaoTest {
 	private static final Logger LOG = LoggerFactory.getLogger(BaseStationStreamingTest.class);	
	
	protected NameSpace nameSpace = NameSpace.SUMMARY_ORGANIZATION;
	
	protected Map<String, Object> expectedMapOneYear = TestSummaryOrganizationMap.SUMMARY_ORGANIZATION_CURRENT_YEAR;
	protected Map<String, Object> expectedMapFiveYears = TestSummaryOrganizationMap.SUMMARY_ORGANIZATION_FIVE_YEAR;
	protected Map<String, Object> expectedMapAllYears = TestSummaryOrganizationMap.SUMMARY_ORGANIZATION_ALL_YEAR;
	
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
		return callDao(nameSpace, expectedSize, filter);
	}

	private void containsOrganizationTest(NameSpace nameSpace) {
		String[] testOrganizations = {DATA_SOURCE_TEST, ORG_ID_TEST_2};
		List<Map<String, Object>> results = organizationTest(nameSpace, 2);
		assertContainsOrganization(results, testOrganizations);
	}

	public static void assertRow(Map<String, Object> row, String[] station, int expectedColumnCount) {	    
		assertEquals(expectedColumnCount, row.keySet().size());
		if (row.containsKey(BaseColumn.KEY_DATA_SOURCE)) {
			assertEquals(station[0], row.get(BaseColumn.KEY_DATA_SOURCE));
		}
		assertEquals(station[1], row.get(OrganizationColumn.KEY_ORGANIZATION));
	}

	public void assertContainsOrganization(List<Map<String, Object>> results, String[]...  organizations) {
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
		// calculate the number of columns in the test map
		Integer expectedColumnCount = expectedMapAllYears.keySet().size();
		
		// grabs the rows from the CI database, sumTest asserts that number of rows returned from the db matches the expected number of rows (last parameter in method)
		List<Map<String, Object>> results = 
				sumTest(
						SUMMARY_YEARS_ALL_MONTHS, 
						nameSpace, 
						Integer.valueOf(TOTAL_ORGANIZATION_SUMMARY_COUNT));
	
		assertRow(results.get(0), STORET_TEST, expectedColumnCount);
		assertRow(results.get(1), STORET_TEST_2, expectedColumnCount);
	}
	
	public void fiveYearsSummaryTest(NameSpace nameSpace) {
		Integer expectedColumnCount = expectedMapFiveYears.keySet().size();
		
		List<Map<String, Object>> results = 
				sumTest(
						SUMMARY_YEARS_60_MONTHS, 
						nameSpace, 
						Integer.valueOf(TOTAL_ORGANIZATION_SUMMARY_COUNT));
	
		assertRow(results.get(0), STORET_TEST, expectedColumnCount);
		assertRow(results.get(1), STORET_TEST_2, expectedColumnCount);
	}
	
	public void oneYearSummaryTest(NameSpace nameSpace) {
		Integer expectedColumnCount = expectedMapOneYear.keySet().size();
		
		List<Map<String, Object>> results = 
				sumTest(
						SUMMARY_YEARS_12_MONTHS, 
						nameSpace, 
						Integer.valueOf(TOTAL_ORGANIZATION_SUMMARY_COUNT));
	
		assertRow(results.get(0), STORET_TEST, expectedColumnCount);
		assertRow(results.get(1), STORET_TEST_2, expectedColumnCount);
	}
}
