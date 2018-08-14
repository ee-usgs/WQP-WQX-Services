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
	protected Map<String, Object> expectedMap = TestSummaryStationMap.SUMMARY_STATION;
	public static final String SUMMARY_YEARS_12_MONTHS = "1";

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
		sortedSummaryTest(nameSpace, expectedMap);
		stateTest(nameSpace);
		withinTest(nameSpace);
		zipTest(nameSpace);
		multipleParameterStationSumTest(nameSpace);
	}
	
	public void multipleParameterStationSumTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 6);
		assertContainsStation(results, 
			BaseStationStreamingTest.NWIS_1353690, 
			BaseStationStreamingTest.NWIS_1353690,
			BaseStationStreamingTest.STORET_777, 
			BaseStationStreamingTest.STORET_888,
			BaseStationStreamingTest.STEWARDS_36,
			BaseStationStreamingTest.STEWARDS_46
		);
	}
	
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
	
	
	public void sortedSummaryTest(NameSpace nameSpace, Map<String, Object> expectedMap) {
		Integer expectedColumnCount = expectedMap.keySet().size();
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
		assertRow(results.get(0), STEWARDS_36, expectedColumnCount);
		assertRow(results.get(1), STEWARDS_46, expectedColumnCount);
		assertRow(results.get(2), NWIS_1353690, expectedColumnCount);
		assertRow(results.get(3), NWIS_1360035, expectedColumnCount);
		assertRow(results.get(4), STORET_1043441, expectedColumnCount);
		assertRow(results.get(5), STORET_504707, expectedColumnCount);
		assertRow(results.get(6), STORET_436723, expectedColumnCount);
		assertRow(results.get(7), STORET_1383, expectedColumnCount);
		assertStoret888(expectedMap, results.get(8));
		assertRow(results.get(9), STORET_777, expectedColumnCount);
		assertRow(results.get(10), STORET_999, expectedColumnCount);
		assertRow(results.get(11), BIODATA_61233184, expectedColumnCount);
	}
	
	public List<Map<String, Object>> sortedTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSorted("yes");
		filter.setSummaryYears(SUMMARY_YEARS_12_MONTHS);
		return callDao(nameSpace, expectedSize, filter);
	}
	
	protected void mimeTypeSummaryTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = mimeTypeJsonSummaryTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM));
		assertContainsStation(results, 
			STEWARDS_36, 
			STEWARDS_46, 
			NWIS_1353690, 
			NWIS_1360035, 
			STORET_777, 
			STORET_888, 
			STORET_999, 
			STORET_1383, 
			STORET_436723, 
			STORET_504707,
			STORET_1043441, 
			BIODATA_61233184
		);

		results = mimeTypeGeoJsonSummaryTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM));
		assertContainsStation(results, 
			STEWARDS_36, 
			STEWARDS_46, 
			NWIS_1353690, 
			NWIS_1360035, 
			STORET_777, 
			STORET_888, 
			STORET_999, 
			STORET_1383, 
			STORET_436723, 
			STORET_504707,
			STORET_1043441, 
			BIODATA_61233184
		);
	}
	
	public List<Map<String, Object>> mimeTypeJsonSummaryTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(JSON);
		filter.setSummaryYears(SUMMARY_YEARS_12_MONTHS);
		return callDao(nameSpace, expectedSize, filter);
	}
	
	public List<Map<String, Object>> mimeTypeGeoJsonSummaryTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(GEOJSON);
		filter.setSummaryYears(SUMMARY_YEARS_12_MONTHS);
		return callDao(nameSpace, expectedSize, filter);
	}

}
