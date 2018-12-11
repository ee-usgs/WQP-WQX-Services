package gov.usgs.cida.wqp.dao.count;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.cida.wqp.dao.CountDao;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE,
	classes={DBTestConfig.class, CountDao.class})
@DatabaseSetup("classpath:/testData/clearAll.xml")
@DatabaseSetup("classpath:/testData/station.xml")
@DatabaseSetup("classpath:/testData/ml_grouping.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public class CountDaoPeriodOfRecordIT extends BaseStationCountDaoTest{
	protected NameSpace nameSpace = NameSpace.PERIOD_OF_RECORD;
	
	public static final String TOTAL_SITE_SUMMARY_COUNT = "4";
	public static final String SUMMARY_YEARS_12_MONTHS = "1";
	public static final String SUMMARY_YEARS_60_MONTHS = "5";
	public static final String SUMMARY_YEARS_ALL_MONTHS = "all";

	@Test
	public void testHarness() {
		bboxTest(nameSpace);
		countryTest(nameSpace);
		emptyParameterTest(nameSpace);
		huc8Test(nameSpace);
		mimeTypeSummaryTest(nameSpace);
		nullParameterTest(nameSpace);
		organizationTest(nameSpace);
		providersTest(nameSpace);
		//TODO		siteIdTest(nameSpace);
		siteTypeTest(nameSpace);
		stateTest(nameSpace);
		withinTest(nameSpace);
		//TODO		multipleParameterStationSumTest(nameSpace);		
		//TODO		allYearsPeriodOfRecordTest(nameSpace);
		//TODO		oneYearPeriodOfRecordTest(nameSpace);
		//TODO		fiveYearsPeriodOfRecordTest(nameSpace);
	}
	
	public void bboxTest(NameSpace nameSpace) { 
		List<Map<String, Object>> counts = bboxTest(nameSpace, 3);
		assertStationResults(counts, "3", "2", "1", null, null);
	}
	
	public void countryTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = countryTest(nameSpace, 3);
		assertStationResults(counts, "3", "2", "1", null, null);
	}
	
	public void emptyParameterTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = emptyParameterTest(nameSpace, 4);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "2", "1", "1", null);
	}
	
	public void huc8Test(NameSpace nameSpace) {
		List<Map<String, Object>> counts = huc8Test(nameSpace, 1);
		assertStationResults(counts, "0", null, null, null, null);
	}
	
	public void mimeTypeSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = mimeTypeJsonTest(nameSpace, 4);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "2", "1", "1", null);
		
		counts = mimeTypeGeoJsonTest(nameSpace, 4);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "2", "1", "1", null);
	}
	
	public void nullParameterTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = nullParameterTest(nameSpace, 4);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "2", "1", "1", null);
	}
	
	public void organizationTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = organizationTest(nameSpace, 3);
		assertStationResults(counts, "3", "2", "1", null, null);
	}
	
	public void providersTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = providersTest(nameSpace, 4);
		assertStationResults(counts, "4", "2", "1", "1", null);
	}
	
	public void siteIdTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = siteIdTest(nameSpace, 3);
		assertStationResults(counts, "3", "2", "1", null, null);
	}
	
	public void siteTypeTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = siteTypeTest(nameSpace, 4);
		assertStationResults(counts, "3", "1", "1", "1", null);
	}
	
	public void stateTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = stateTest(nameSpace, 3);
		assertStationResults(counts, "3", "2", "1", null, null);
	}
	
	public void withinTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = withinTest(nameSpace, 4);
		assertStationResults(counts, "4", "2", "1", "1", null);
	}
	
	@Override
	public List<Map<String, Object>> multipleParameterStationSumTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = getNoEffectParameters(nameSpace);
		
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setNldiurl(getNldiurl());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteId());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setSummaryYears(getSummaryYears());
		filter.setWithin(getWithin());

		return callDao(nameSpace, expectedSize, filter);
	}
	
	public void multipleParameterStationSumTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = multipleParameterStationSumTest(nameSpace, 0);
		assertStationResults(counts, null, null, null, null, null);
	}
	
	public List<Map<String, Object>> summaryYearsTest(NameSpace nameSpace, String summaryYears, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSummaryYears(summaryYears);
		return callDao(nameSpace, expectedSize, filter);
	}
	
	public void allYearsPeriodOfRecordTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = summaryYearsTest(nameSpace, SUMMARY_YEARS_ALL_MONTHS, 4);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "2", "1", "1", null);
	}
	
	public void oneYearPeriodOfRecordTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = summaryYearsTest(nameSpace, SUMMARY_YEARS_12_MONTHS, 2);
		assertStationResults(counts, "2", "2", null, null, null);
	}
	
	public void fiveYearsPeriodOfRecordTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = summaryYearsTest(nameSpace, SUMMARY_YEARS_60_MONTHS, 2);
		assertStationResults(counts, "2", "2", null, null, null);
	}
}

