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
import gov.usgs.cida.wqp.mapping.TestStationMap;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import java.util.List;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class SummaryStationStreamingIT extends BaseStationStreamingTest {

	protected NameSpace nameSpace = NameSpace.STATION;
	protected Map<String, Object> expectedMap = TestStationMap.STATION;

	@Test
	public void testHarness() {
		// cut out the tests you don't need according to what's in the swagger spec
		activityTest(nameSpace);
		analyticalMethodTest(nameSpace);
		assemblageTest(nameSpace);
		avoidTest(nameSpace);
		bboxTest(nameSpace);
		characteristicNameTest(nameSpace);
		characteristicTypeTest(nameSpace);
		countryTest(nameSpace);
		countyTest(nameSpace);
		emptyParameterTest(nameSpace);
		huc2Test(nameSpace);
		huc3Test(nameSpace);
		huc4Test(nameSpace);
		huc5Test(nameSpace);
		huc6Test(nameSpace);
		huc7Test(nameSpace);
		huc8Test(nameSpace);
		huc10Test(nameSpace);
		huc12Test(nameSpace);
		mimeTypeTest(nameSpace);
		minActivitiesTest(nameSpace);
		minResultsTest(nameSpace);
		nldiSitesTest(nameSpace);
		nldiUrlTest(nameSpace);
		nullParameterTest(nameSpace);
		organizationTest(nameSpace);
		pcodeTest(nameSpace);
		projectTest(nameSpace);
		providersTest(nameSpace);
		resultTest(nameSpace);
		sampleMediaTest(nameSpace);
		siteIdTest(nameSpace);
		siteIdLargeListTest(nameSpace);
		siteTypeTest(nameSpace);
		siteUrlBaseTest(nameSpace);
		sortedTest(nameSpace, expectedMap);
		startDateHiTest(nameSpace);
		startDateLoTest(nameSpace);
		stateTest(nameSpace);
		subjectTaxonomicNameTest(nameSpace);
		withinTest(nameSpace);
		zipTest(nameSpace);
		multipleParameterStationSumTest(nameSpace);
	}
	
	public void multipleParameterStationSumTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 3);
		assertContainsStation(results, BaseStationStreamingTest.NWIS_1353690, BaseStationStreamingTest.STORET_777, BaseStationStreamingTest.STORET_888);
	}
	
	public List<Map<String, Object>> multipleParameterStationSumTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = getNoEffectParameters(nameSpace);

		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteId());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		return callDao(nameSpace, expectedSize, filter);
	}

}
