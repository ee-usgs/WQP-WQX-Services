package gov.usgs.cida.wqp.dao.summary;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.cida.wqp.dao.FilteredDaoTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest;
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
	public static final String[] STORET_TEST = new String[]{"Storet", ORG_ID_TEST_1, "arsite"};
	public static final String SUMMARY_YEARS_12_MONTHS = "1";
	public static final String SUMMARY_YEARS_60_MONTHS = "5";
	public static final String SUMMARY_YEARS_ALL_MONTHS = "all";
	
	public static final String TOTAL_ORGANIZATION_SUMMARY_COUNT = "2";

	@Autowired 
	IStreamingDao streamingDao;
   
	@Test
	public void testHarness() {
	    containsOrganizationTest(nameSpace);
	    sortedAllSummaryTest(nameSpace);
	    sortedFiveYearsSummaryTest(nameSpace);
	    sortedOneYearSummaryTest(nameSpace);
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

	private void containsOrganizationTest(NameSpace nameSpace) {
		String[] testOrganizations = {DATA_SOURCE_TEST, ORG_ID_TEST_2};
		List<Map<String, Object>> results = organizationTest(nameSpace, 2);
		assertContainsOrganization(results, testOrganizations);
	}

	public void sortedAllSummaryTest(NameSpace nameSpace) {
		Integer expectedColumnCount = expectedMapOneYear.keySet().size();

		List<Map<String, Object>> results = 
				sortedSumTest(
						SUMMARY_YEARS_12_MONTHS, 
						nameSpace, 
						Integer.valueOf(TOTAL_ORGANIZATION_SUMMARY_COUNT));
		assertRow(results.get(0), STORET_TEST, expectedColumnCount);
	}

	private void sortedFiveYearsSummaryTest(NameSpace nameSpace) {
		Integer expectedColumnCount = expectedMapOneYear.keySet().size();

		List<Map<String, Object>> results = 
				sortedSumTest(
						SUMMARY_YEARS_60_MONTHS, 
						nameSpace, 
						Integer.valueOf(TOTAL_ORGANIZATION_SUMMARY_COUNT));
		assertRow(results.get(0), STORET_TEST, expectedColumnCount);
	}

	private void sortedOneYearSummaryTest(NameSpace nameSpace) {
			Integer expectedColumnCount = expectedMapOneYear.keySet().size();

		List<Map<String, Object>> results = 
				sortedSumTest(
						SUMMARY_YEARS_ALL_MONTHS, 
						nameSpace, 
						Integer.valueOf(TOTAL_ORGANIZATION_SUMMARY_COUNT));
		assertRow(results.get(0), STORET_TEST, expectedColumnCount);
	}

	public List<Map<String, Object>> sortedSumTest(String summaryYears, NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSorted("yes");
		filter.setSummaryYears(summaryYears);
		return callDao(nameSpace, expectedSize, filter);
	}

	public static void assertRow(Map<String, Object> row, String[] station, int expectedColumnCount) {	    
		assertEquals(expectedColumnCount, row.keySet().size());
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

    @Override
    protected void assertSiteUrlBase(Map<String, Object> row) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
