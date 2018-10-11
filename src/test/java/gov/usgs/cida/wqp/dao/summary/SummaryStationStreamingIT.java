package gov.usgs.cida.wqp.dao.summary;

import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;

import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest;
import gov.usgs.cida.wqp.mapping.TestSummaryStationMap;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import java.util.List;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/clearAll.xml")
@DatabaseSetup("classpath:/testData/station.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public class SummaryStationStreamingIT extends BaseStationStreamingTest {

	protected NameSpace nameSpace = NameSpace.SUMMARY_STATION;
	protected Map<String, Object> expectedMapOneYear = TestSummaryStationMap.SUMMARY_STATION_ONE_YEAR;
	protected Map<String, Object> expectedMapFiveYears = TestSummaryStationMap.SUMMARY_STATION_FIVE_YEARS;
	protected Map<String, Object> expectedMapAllYears = TestSummaryStationMap.SUMMARY_STATION_ALL_YEARS;
	public static final String SUMMARY_YEARS_12_MONTHS = "1";
	public static final String SUMMARY_YEARS_60_MONTHS = "5";
	public static final String SUMMARY_YEARS_ALL_MONTHS = "all";
	
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
		sortedAllSummaryTest(nameSpace);
		sortedFiveYearsSummaryTest(nameSpace);
		sortedOneYearSummaryTest(nameSpace);
		stateTest(nameSpace);
		withinTest(nameSpace);
		zipTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
		multipleParameterStationSumTest(nameSpace);
	}
	
	@Override
	public void bboxTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = bboxTest(nameSpace, 8);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_1383, STORET_1043441);
	}
	
	@Override
	public void countryTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = countryTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_1043441,
				BIODATA_61233184);
	}
	
	@Override
	public void emptyParameterTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, 
				STORET_504707, STORET_1043441, BIODATA_61233184);
	}
	
	@Override
	public void huc8Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc8Test(nameSpace, 1);
		assertContainsStation(results, STORET_1383);
	}
	
	@Override
	public void nullParameterTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, 
				STORET_504707, STORET_1043441, BIODATA_61233184);
	}
	
	@Override
	public void organizationTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = organizationTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, 
				NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383,
				STORET_1043441);
	}
	
	@Override
	public void providersTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = providersTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, 
				NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, 
				STORET_504707, STORET_1043441);
	}
	
	@Override
	public void siteIdTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = siteIdTest(nameSpace, 8);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, 
				NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}
	
	@Override
	public void siteTypeTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = siteTypeTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, 
				STORET_777, STORET_888, STORET_999, STORET_1383, STORET_504707, 
				STORET_1043441, BIODATA_61233184);
	}
	
	@Override
	public void stateTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = stateTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, 
				NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, 
				STORET_1043441);
	}
	
	@Override
	public void withinTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = withinTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, 
				NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, 
				STORET_504707);
	}

	
	@Override
	public void multipleParameterStationSumTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 3);
		assertContainsStation(results,			 
			BaseStationStreamingTest.STORET_888,
			BaseStationStreamingTest.STEWARDS_36,
			BaseStationStreamingTest.STEWARDS_46
		);
	}
	
	@Override
	public List<Map<String, Object>> multipleParameterStationSumTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = getNoEffectParameters(nameSpace);
		
		filter.setZip(getZip());
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
	

	public void sortedAllSummaryTest(NameSpace nameSpace) {
		Integer expectedColumnCount = expectedMapAllYears.keySet().size();
		
		List<Map<String, Object>> results = 
				sortedSumTest(
						SUMMARY_YEARS_ALL_MONTHS, 
						nameSpace, 
						Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
	
		assertRow(results.get(0), STEWARDS_36, expectedColumnCount);
		assertRow(results.get(1), STEWARDS_46, expectedColumnCount);
		assertRow(results.get(2), NWIS_1353690, expectedColumnCount);
		assertRow(results.get(3), NWIS_1360035, expectedColumnCount);
		assertRow(results.get(4), STORET_1043441, expectedColumnCount);
		assertRow(results.get(5), STORET_504707, expectedColumnCount);
		assertRow(results.get(6), STORET_1383, expectedColumnCount);
		assertStoret888(expectedMapAllYears, results.get(7));
		assertRow(results.get(8), STORET_777, expectedColumnCount);
		assertRow(results.get(9), STORET_999, expectedColumnCount);
		assertRow(results.get(10), BIODATA_61233184, expectedColumnCount);
	}
	
	public void sortedFiveYearsSummaryTest(NameSpace nameSpace) {
		Integer expectedColumnCount = expectedMapFiveYears.keySet().size();
		
		List<Map<String, Object>> results = 
				sortedSumTest(
						SUMMARY_YEARS_60_MONTHS, 
						nameSpace, 
						10);
	
		assertRow(results.get(0), STEWARDS_36, expectedColumnCount);
		assertRow(results.get(1), STEWARDS_46, expectedColumnCount);
		assertRow(results.get(2), NWIS_1353690, expectedColumnCount);
		assertRow(results.get(3), NWIS_1360035, expectedColumnCount);
		assertRow(results.get(4), STORET_1043441, expectedColumnCount);
		assertRow(results.get(5), STORET_504707, expectedColumnCount);
		assertRow(results.get(6), STORET_1383, expectedColumnCount);
		assertStoret888(expectedMapFiveYears, results.get(7));
		assertRow(results.get(8), STORET_999, expectedColumnCount);
		assertRow(results.get(9), BIODATA_61233184, expectedColumnCount);
	}
	
	public void sortedOneYearSummaryTest(NameSpace nameSpace) {
		Integer expectedColumnCount = expectedMapOneYear.keySet().size();
		
		List<Map<String, Object>> results = 
				sortedSumTest(
						SUMMARY_YEARS_12_MONTHS, 
						nameSpace, 
						9);
	
		assertRow(results.get(0), STEWARDS_36, expectedColumnCount);
		assertRow(results.get(1), STEWARDS_46, expectedColumnCount);
		assertRow(results.get(2), NWIS_1353690, expectedColumnCount);
		assertRow(results.get(3), NWIS_1360035, expectedColumnCount);
		assertRow(results.get(4), STORET_1043441, expectedColumnCount);
		assertRow(results.get(5), STORET_504707, expectedColumnCount);
		assertRow(results.get(6), STORET_1383, expectedColumnCount);
		assertStoret888(expectedMapOneYear, results.get(7));
		assertRow(results.get(8), BIODATA_61233184, expectedColumnCount);
	}

	public List<Map<String, Object>> sortedSumTest(String summaryYears, NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSorted("yes");
		filter.setSummaryYears(summaryYears);
		return callDao(nameSpace, expectedSize, filter);
	}
	
	protected void mimeTypeSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = mimeTypeJsonTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
		assertContainsStation(results, 
			STEWARDS_36, 
			STEWARDS_46, 
			NWIS_1353690, 
			NWIS_1360035, 
			STORET_777, 
			STORET_888, 
			STORET_999, 
			STORET_1383, 
			STORET_504707,
			STORET_1043441, 
			BIODATA_61233184
		);

		results = mimeTypeGeoJsonTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
		assertContainsStation(results, 
			STEWARDS_36, 
			STEWARDS_46, 
			NWIS_1353690, 
			NWIS_1360035, 
			STORET_777, 
			STORET_888, 
			STORET_999, 
			STORET_1383, 
			STORET_504707,
			STORET_1043441, 
			BIODATA_61233184
		);
	}
}
