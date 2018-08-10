package gov.usgs.cida.wqp.dao.summary;

import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.streaming.BaseStationStreamingTest;
import gov.usgs.cida.wqp.mapping.TestSummaryStationMap;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import java.util.List;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class SummaryStationStreamingIT extends BaseStationStreamingTest {

	protected NameSpace nameSpace = NameSpace.SUMMARY_STATION;
	protected Map<String, Object> expectedMap = TestSummaryStationMap.SUMMARY_STATION;

	@Test
	public void testHarness() {
		// cut out the tests you don't need according to what's in the swagger spec
//		bboxTest(nameSpace);
//		countryTest(nameSpace);
//		emptyParameterTest(nameSpace);
//		huc8Test(nameSpace);
		// mimeTypeTest(nameSpace); // fails
//		nullParameterTest(nameSpace);
//		organizationTest(nameSpace);
//		providersTest(nameSpace);
//		siteIdTest(nameSpace);
//		siteTypeTest(nameSpace);
		sortedTest(nameSpace, expectedMap); // fails
//		stateTest(nameSpace);
//		withinTest(nameSpace);
//		zipTest(nameSpace);
//		multipleParameterStationSumTest(nameSpace);
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

}
