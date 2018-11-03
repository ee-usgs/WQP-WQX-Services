package gov.usgs.cida.wqp.dao.summary;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.BIODATA_61233184;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.NWIS_1353690;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.NWIS_1360035;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STEWARDS_36;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STEWARDS_46;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_1043441;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_1383;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_504707;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_777;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_888;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.STORET_999;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.assertRow;
import gov.usgs.cida.wqp.mapping.TestSummaryMonitoringLocationMap;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/clearAll.xml")
@DatabaseSetup("classpath:/testData/station.xml")
@DatabaseSetup("classpath:/testData/ml_grouping.xml")
@DbUnitConfiguration(dataSetLoader = ColumnSensingFlatXMLDataSetLoader.class)
public class PeriodOfRecordStreamingIT extends BaseStationStreamingTest {

	protected NameSpace nameSpace = NameSpace.PERIOD_OF_RECORD;
	protected Map<String, Object> expectedMapOneYear = TestSummaryMonitoringLocationMap.SUMMARY_MONITORING_LOCATION_ONE_YEAR;
	protected Map<String, Object> expectedMapFiveYears = TestSummaryMonitoringLocationMap.SUMMARY_MONITORING_LOCATION_FIVE_YEARS;
	protected Map<String, Object> expectedMapAllYears = TestSummaryMonitoringLocationMap.SUMMARY_MONITORING_LOCATION_ALL_YEARS;
	public static final String SUMMARY_YEARS_12_MONTHS = "1";
	public static final String SUMMARY_YEARS_60_MONTHS = "5";
	public static final String SUMMARY_YEARS_ALL_MONTHS = "all";
	
	public static final String TOTAL_SITE_SUMMARY_COUNT = "29";

	@Test
	public void testHarness() {
//		bboxTest(nameSpace);
//		countryTest(nameSpace);
//		emptyParameterTest(nameSpace);
//		huc8Test(nameSpace);
//		mimeTypeSummaryTest(nameSpace);
//		nullParameterTest(nameSpace);
//		organizationTest(nameSpace);
//		providersTest(nameSpace);
//		siteIdTest(nameSpace);
//		siteTypeTest(nameSpace);
//		stateTest(nameSpace);
//		withinTest(nameSpace);
//		zipTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
//		multipleParameterStationSumTest(nameSpace);

//		sortedAllSummaryTest(nameSpace);
//		sortedFiveYearsSummaryTest(nameSpace);
//		sortedOneYearSummaryTest(nameSpace);
	}
	
	@Override
	public void bboxTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = bboxTest(nameSpace, 20);
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1,
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1,
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}
	
	@Override
	public void countryTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = countryTest(nameSpace, 20);
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1,
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1,
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}
	
	@Override
	public void emptyParameterTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, STORET_504707_1910_CT1, STORET_504707_1911_CT1,
				STORET_504707_1912_CT1, STORET_504707_1923_CT1, STORET_504707_1934_CT1,
				STORET_504707_1945_CT1, STORET_504707_1956_CT1, STORET_504707_1977_CT1,
				STORET_504707_1998_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}
	
	@Override
	public void huc8Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc8Test(nameSpace, 0);
		assertContainsStation(results);
	}
	
	@Override
	public void nullParameterTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, STORET_504707_1910_CT1, STORET_504707_1911_CT1,
				STORET_504707_1912_CT1, STORET_504707_1923_CT1, STORET_504707_1934_CT1,
				STORET_504707_1945_CT1, STORET_504707_1956_CT1, STORET_504707_1977_CT1,
				STORET_504707_1998_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}
	
	@Override
	public void organizationTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = organizationTest(nameSpace, 20);
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}
	
	@Override
	public void providersTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = providersTest(nameSpace, 29);
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, STORET_504707_1910_CT1, STORET_504707_1911_CT1,
				STORET_504707_1912_CT1, STORET_504707_1923_CT1, STORET_504707_1934_CT1,
				STORET_504707_1945_CT1, STORET_504707_1956_CT1, STORET_504707_1977_CT1,
				STORET_504707_1998_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}
	
	@Override
	public void siteIdTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = siteIdTest(nameSpace, 20);
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}
	
	@Override
	public void siteTypeTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = siteTypeTest(nameSpace, 27);
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, STORET_504707_1910_CT1, STORET_504707_1911_CT1,
				STORET_504707_1912_CT1, STORET_504707_1923_CT1, STORET_504707_1934_CT1,
				STORET_504707_1945_CT1, STORET_504707_1956_CT1, STORET_504707_1977_CT1,
				STORET_504707_1998_CT1
		);
	}
	
	@Override
	public void stateTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = stateTest(nameSpace, 20);
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}
	
	@Override
	public void withinTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = withinTest(nameSpace, 29);
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, STORET_504707_1910_CT1, STORET_504707_1911_CT1,
				STORET_504707_1912_CT1, STORET_504707_1923_CT1, STORET_504707_1934_CT1,
				STORET_504707_1945_CT1, STORET_504707_1956_CT1, STORET_504707_1977_CT1,
				STORET_504707_1998_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}

	
	@Override
	public void multipleParameterStationSumTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 0);
		assertContainsStation(results);
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
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, STORET_504707_1910_CT1, STORET_504707_1911_CT1,
				STORET_504707_1912_CT1, STORET_504707_1923_CT1, STORET_504707_1934_CT1,
				STORET_504707_1945_CT1, STORET_504707_1956_CT1, STORET_504707_1977_CT1,
				STORET_504707_1998_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);

		results = mimeTypeGeoJsonTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
		assertContainsStation(results, STEWARDS_36_2000_CT1, STEWARDS_36_2001_CT1,
				STEWARDS_36_2002_CT1, STEWARDS_36_2003_CT1, STEWARDS_36_2004_CT1,
				STEWARDS_36_2005_CT1, STEWARDS_36_2006_CT1, STEWARDS_36_2007_CT1,
				STEWARDS_36_2008_CT1, NWIS_1353690_2010_CT1, NWIS_1353690_2011_CT1, 
				NWIS_1353690_2012_CT1, NWIS_1353690_2013_CT1, NWIS_1353690_2014_CT1, 
				NWIS_1353690_2015_CT1, NWIS_1353690_2016_CT1, NWIS_1353690_2017_CT1,
				NWIS_1353690_2018_CT1, STORET_504707_1910_CT1, STORET_504707_1911_CT1,
				STORET_504707_1912_CT1, STORET_504707_1923_CT1, STORET_504707_1934_CT1,
				STORET_504707_1945_CT1, STORET_504707_1956_CT1, STORET_504707_1977_CT1,
				STORET_504707_1998_CT1, NWIS_1360035_2017_CT1, NWIS_1360035_2018_CT1
		);
	}
}
