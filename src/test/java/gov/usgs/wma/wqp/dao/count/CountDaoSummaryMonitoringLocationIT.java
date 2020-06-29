package gov.usgs.wma.wqp.dao.count;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.wma.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.wma.wqp.dao.CountDao;
import gov.usgs.wma.wqp.dao.NameSpace;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.springinit.DBTestConfig;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE,
	classes={DBTestConfig.class, CountDao.class})
@DatabaseSetup("classpath:/testData/clearAll.xml")
@DatabaseSetup("classpath:/testData/station.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public class CountDaoSummaryMonitoringLocationIT extends BaseStationCountDaoTest {

	protected NameSpace nameSpace = NameSpace.SUMMARY_MONITORING_LOCATION;

	public static final String TOTAL_SITE_SUMMARY_COUNT = "11";

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
		siteIdTest(nameSpace);
		siteTypeTest(nameSpace);
		stateTest(nameSpace);
		withinTest(nameSpace);
		multipleParameterStationSumTest(nameSpace);		
		allYearsSummaryTest(nameSpace);
		oneYearSummaryTest(nameSpace);
		fiveYearsSummaryTest(nameSpace);
	}

	public void bboxTest(NameSpace nameSpace) { 
		List<Map<String, Object>> counts = bboxTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "8", "2", "2", "4");
	}

	public void countryTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = countryTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "10", "3", "2", "5");
	}

	public void emptyParameterTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = emptyParameterTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "3", "2", "6");
	}

	public void huc8Test(NameSpace nameSpace) {
		List<Map<String, Object>> counts = huc8Test(nameSpace, 2);
		assertStationResults(counts, "1", null, null, "1");
	}

	public void mimeTypeSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = mimeTypeJsonTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "3", "2", "6");

		counts = mimeTypeGeoJsonTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "3", "2", "6");
	}

	public void nullParameterTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = nullParameterTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "3", "2", "6");
	}

	public void organizationTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = organizationTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "9", "2", "2", "5");
	}

	public void providersTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = providersTest(nameSpace, 3);
		assertStationResults(counts, "9", "3", null, "6");
	}

	public void siteIdTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = siteIdTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "8", "2", "2", "4");
	}

	public void siteTypeTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = siteTypeTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "10", "2", "2", "6");
	}

	public void stateTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = stateTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "9", "2", "2", "5");
	}

	public void withinTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = withinTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "9", "2", "2", "5");
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
		List<Map<String, Object>> counts = multipleParameterStationSumTest(nameSpace, 3);
		assertStationResults(counts, "3", null, "2", "1");
	}

	public List<Map<String, Object>> summaryYearsTest(NameSpace nameSpace, String summaryYears, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSummaryYears(summaryYears);
		return callDao(nameSpace, expectedSize, filter);
	}

	public void allYearsSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = summaryYearsTest(nameSpace, FilterParameters.SUMMARY_YEARS_ALL_MONTHS, BASE_HEADER_COUNT);
		assertStationResults(counts, TOTAL_SITE_SUMMARY_COUNT, "3", "2", "6");
	}

	public void oneYearSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = summaryYearsTest(nameSpace, FilterParameters.SUMMARY_YEARS_12_MONTHS, BASE_HEADER_COUNT);
		assertStationResults(counts, "9", "3", "2", "4");
	}

	public void fiveYearsSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> counts = summaryYearsTest(nameSpace, FilterParameters.SUMMARY_YEARS_60_MONTHS, BASE_HEADER_COUNT);
		assertStationResults(counts, "10", "3", "2", "5");
	}
}
