package gov.usgs.cida.wqp.dao.summary;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.cida.wqp.ColumnSensingFlatXMLDataSetLoader;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest;
import static gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest.assertRow;
import gov.usgs.cida.wqp.mapping.TestPeriodOfRecordCSVMap;

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
	protected List<Map<String, Object>> expectedMapOneYear = TestPeriodOfRecordCSVMap.PERIOD_OF_RECORD_ONE_YEAR;
	protected List<Map<String, Object>> expectedMapFiveYears = TestPeriodOfRecordCSVMap.PERIOD_OF_RECORD_FIVE_YEARS;
	protected List<Map<String, Object>> expectedMapAllYears = TestPeriodOfRecordCSVMap.PERIOD_OF_RECORD_ALL_YEARS;
	public static final String PERIOD_OF_RECORD_YEARS_12_MONTHS = "1";
	public static final String PERIOD_OF_RECORD_YEARS_60_MONTHS = "5";
	public static final String PERIOD_OF_RECORD_YEARS_ALL_MONTHS = "all";
	
	public static final String TOTAL_SITE_SUMMARY_COUNT = "29";
	public static final Integer EXPECTED_COLUMN_COUNT = 20;

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
		zipTest(nameSpace, Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
		multipleParameterStationSumTest(nameSpace);
		sortedAllPeriodOfRecordTest(nameSpace);
		sortedFiveYearsPeriodOfRecordTest(nameSpace);
		sortedOneYearPeriodOfRecordTest(nameSpace);
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
	

	public void sortedAllPeriodOfRecordTest(NameSpace nameSpace) {		
		List<Map<String, Object>> results = 
				sortedSumTest(
						PERIOD_OF_RECORD_YEARS_ALL_MONTHS, 
						nameSpace, 
						Integer.valueOf(TOTAL_SITE_SUMMARY_COUNT));
	
		assertRow(results.get(0), STEWARDS_36_2000_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(1), STEWARDS_36_2001_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(2), STEWARDS_36_2002_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(3), STEWARDS_36_2003_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(4), STEWARDS_36_2004_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(5), STEWARDS_36_2005_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(6), STEWARDS_36_2006_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(7), STEWARDS_36_2007_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(8), STEWARDS_36_2008_CT1, EXPECTED_COLUMN_COUNT);			
		assertRow(results.get(9), NWIS_1353690_2010_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(10), NWIS_1353690_2011_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(11), NWIS_1353690_2012_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(12), NWIS_1353690_2013_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(13), NWIS_1353690_2014_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(14), NWIS_1353690_2015_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(15), NWIS_1353690_2016_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(16), NWIS_1353690_2017_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(17), NWIS_1353690_2018_CT1, EXPECTED_COLUMN_COUNT);		
		assertRow(results.get(18), NWIS_1360035_2017_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(19), NWIS_1360035_2018_CT1, EXPECTED_COLUMN_COUNT);		
		assertRow(results.get(20), STORET_504707_1910_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(21), STORET_504707_1911_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(22), STORET_504707_1912_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(23), STORET_504707_1923_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(24), STORET_504707_1934_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(25), STORET_504707_1945_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(26), STORET_504707_1956_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(27), STORET_504707_1977_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(28), STORET_504707_1998_CT1, EXPECTED_COLUMN_COUNT);
		
Map<String, Object> debug = results.get(0);
		// test for a sample of records that should appear only in 'all' years
		// locations (STEWARDS_36_2000, STORET_504707_1910)
		assertStoret888(expectedMapAllYears.get(0), results.get(0));
		assertStoret888(expectedMapAllYears.get(6), results.get(20));

		// test for a sample of records that should appear in 5 and 'all' years
		// locations (NWIS_1353690_2017, NWIS_1360035_2017)
		assertStoret888(expectedMapAllYears.get(1), results.get(16));
		assertStoret888(expectedMapAllYears.get(4), results.get(18));
		
		// test for records that should appear in 1, 5, and 'all' years	
		assertStoret888(expectedMapAllYears.get(2), results.get(17));
		assertStoret888(expectedMapAllYears.get(5), results.get(19));
	}
	
	public void sortedFiveYearsPeriodOfRecordTest(NameSpace nameSpace) {		
		List<Map<String, Object>> results = 
				sortedSumTest(
						PERIOD_OF_RECORD_YEARS_60_MONTHS, 
						nameSpace, 
						7);
	
		assertRow(results.get(0), NWIS_1353690_2014_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(1), NWIS_1353690_2015_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(2), NWIS_1353690_2016_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(3), NWIS_1353690_2017_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(4), NWIS_1353690_2018_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(5), NWIS_1360035_2017_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(6), NWIS_1360035_2018_CT1, EXPECTED_COLUMN_COUNT);
Map<String, Object> debug = results.get(0);
		// test for a sample of records that should appear in 5 and 'all' years
		// locations (NWIS_1353690_2017, NWIS_1360035_2017)
		assertStoret888(expectedMapFiveYears.get(0), results.get(3));
		assertStoret888(expectedMapFiveYears.get(2), results.get(5));
		
		// test for records that should appear in 1, 5, and 'all' years
		// locations (NWIS_1353690_2018, NWIS_1360035_2018)
		assertStoret888(expectedMapFiveYears.get(1), results.get(4));
		assertStoret888(expectedMapFiveYears.get(3), results.get(6));
	}
	
	public void sortedOneYearPeriodOfRecordTest(NameSpace nameSpace) {		
		List<Map<String, Object>> results = 
				sortedSumTest(
						PERIOD_OF_RECORD_YEARS_12_MONTHS, 
						nameSpace, 
						2);
	
		assertRow(results.get(0), NWIS_1353690_2018_CT1, EXPECTED_COLUMN_COUNT);
		assertRow(results.get(1), NWIS_1360035_2018_CT1, EXPECTED_COLUMN_COUNT);
		
		// test for records that should appear in 1, 5, and 'all' years	
		// locations (NWIS_1353690_2018, NWIS_1360035_2018)
		assertStoret888(expectedMapOneYear.get(0), results.get(0));
		assertStoret888(expectedMapOneYear.get(1), results.get(1));
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
