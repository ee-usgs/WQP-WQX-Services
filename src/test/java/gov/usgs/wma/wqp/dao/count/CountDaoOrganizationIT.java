package gov.usgs.wma.wqp.dao.count;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.wma.wqp.CsvDataSetLoader;
import gov.usgs.wma.wqp.dao.CountDao;
import gov.usgs.wma.wqp.dao.NameSpace;
import gov.usgs.wma.wqp.mapping.CountColumn;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
classes={DBTestConfig.class, CountDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoOrganizationIT extends BaseCountDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(CountDaoOrganizationIT.class);

	protected NameSpace nameSpace = NameSpace.ORGANIZATION;

	@Test
	public void testHarness() {
		activityTest();
		analyticalMethodTest();
		assemblageTest();
		avoidTest();
		bboxTest();
		characteristicNameTest();
		characteristicTypeTest();
		countryTest();
		countyTest();
		emptyParameterTest();
		huc2Test();
		huc3Test();
		huc4Test();
		huc5Test();
		huc6Test();
		huc7Test();
		huc8Test();
		huc10Test();
		huc12Test();
		mimeTypeTest();
		minActivitiesTest();
		minResultsTest();
		nldiSitesTest();
		nldiUrlTest();
		nullParameterTest();
		organizationTest();
		pcodeTest();
		projectTest();
		providersTest();
		resultTest();
		sampleMediaTest();
		siteIdTest();
		siteIdLargeListTest();
		siteTypeTest();
		siteUrlBaseTest();
		sortedTest();
		startDateHiTest();
		startDateLoTest();
		stateTest();
		subjectTaxonomicNameTest();
		withinTest();
		zipTest();
		multipleParameterStationSumTest();
		multipleParameterActivitySumTest();
		multipleParameterResultSumTest();
	}

	protected void nullParameterTest() {
		List<Map<String, Object>> counts = nullParameterTest(nameSpace, 5);
		assertFullDbReturned(counts);
	}

	protected void emptyParameterTest() {
		List<Map<String, Object>> counts = emptyParameterTest(nameSpace, 5);
		assertFullDbReturned(counts);
	}

	protected void sortedTest() {
		List<Map<String, Object>> counts = sortedTest(nameSpace, 5);
		assertFullDbReturned(counts);
	}

	protected void zipTest() {
		List<Map<String, Object>> counts = zipTest(nameSpace, 5);
		assertFullDbReturned(counts);
	}

	protected void activityTest() {
		List<Map<String, Object>> counts = activityTest(nameSpace, 5);
		assertFullDbReturned(counts);
	}

	protected void nldiUrlTest() {
		List<Map<String, Object>> counts = nldiUrlTest(nameSpace, 5);
		assertFullDbReturned(counts);
	}

	protected void siteUrlBaseTest() {
		List<Map<String, Object>> counts = siteUrlBaseTest(nameSpace, 5);
		assertFullDbReturned(counts);
	}

	protected void resultTest() {
		List<Map<String, Object>> counts = resultTest(nameSpace, 5);
		assertFullDbReturned(counts);
	}

	protected void mimeTypeTest() {
		List<Map<String, Object>> counts = mimeTypeJsonTest(nameSpace, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeGeoJsonTest(nameSpace, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeKmlTest(nameSpace, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeKmzTest(nameSpace, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeCsvTest(nameSpace, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeTsvTest(nameSpace, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeXmlTest(nameSpace, 5);
		assertFullDbReturned(counts);

		counts = mimeTypeXlsxTest(nameSpace, 5);
		assertFullDbReturned(counts);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against station_sum

	protected void avoidTest() {
		List<Map<String, Object>> counts = avoidTest(nameSpace, 2);
		assertResults(counts, "4", null, null, "4", null);
	}

	protected void bboxTest() {
		List<Map<String, Object>> counts = bboxTest(nameSpace, 4);
		assertResults(counts, "5", "1", "1", "3", null);
	}

	protected void countryTest() {
		List<Map<String, Object>> counts = countryTest(nameSpace, 5);
		assertResults(counts, "6", "1", "1", "3", "1");
	}

	protected void countyTest() {
		List<Map<String, Object>> counts = countyTest(nameSpace, 4);
		assertResults(counts, "5", "1", "1", "3", null);
	}

	protected void huc2Test() {
		List<Map<String, Object>> counts = huc2Test(nameSpace, 4);
		assertResults(counts, "4", "1", "1", "2", null);
	}

	protected void huc3Test() {
		List<Map<String, Object>> counts = huc3Test(nameSpace, 4);
		assertResults(counts, "4", "1", "1", "2", null);
	}

	protected void huc4Test() {
		List<Map<String, Object>> counts = huc4Test(nameSpace, 3);
		assertResults(counts, "2", "1", null, "1", null);
	}

	protected void huc5Test() {
		List<Map<String, Object>> counts = huc5Test(nameSpace, 3);
		assertResults(counts, "2", "1", null, "1", null);
	}

	protected void huc6Test() {
		List<Map<String, Object>> counts = huc6Test(nameSpace, 3);
		assertResults(counts, "2", "1", null, "1", null);
	}

	protected void huc7Test() {
		List<Map<String, Object>> counts = huc7Test(nameSpace, 3);
		assertResults(counts, "2", "1", null, "1", null);
	}

	protected void huc8Test() {
		List<Map<String, Object>> counts = huc8Test(nameSpace, 2);
		assertResults(counts, "1", null, null, "1", null);
	}

	protected void huc10Test() {
		List<Map<String, Object>> counts = huc10Test(nameSpace, 2);
		assertResults(counts, "1", null, null, "1", null);
	}

	protected void huc12Test() {
		List<Map<String, Object>> counts = huc12Test(nameSpace, 2);
		assertResults(counts, "1", null, null, "1", null);
	}

	protected void minActivitiesTest() {
		List<Map<String, Object>> counts = minActivitiesTest(nameSpace, 4);
		assertResults(counts, "6", "1", "1", "4", null);
	}

	protected void minResultsTest() {
		List<Map<String, Object>> counts = minResultsTest(nameSpace, 4);
		assertResults(counts, "6", "1", "1", "4", null);
	}

	protected void nldiSitesTest() {
		List<Map<String, Object>> counts = nldiSitesTest(nameSpace, 2);
		assertResults(counts, "2", null, null, "2", null);
	}

	protected void organizationTest() {
		List<Map<String, Object>> counts = organizationTest(nameSpace, 4);
		assertResults(counts, "5", "1", "1", "3", null);
	}

	protected void providersTest() {
		List<Map<String, Object>> counts = providersTest(nameSpace, 4);
		assertResults(counts, "7", "1", "1", "5", null);
	}

	protected void siteIdTest() {
		List<Map<String, Object>> counts = siteIdTest(nameSpace, 4);
		assertResults(counts, "5", "1", "1", "3", null);
	}

	protected void siteIdLargeListTest() {
		List<Map<String, Object>> counts = siteIdLargeListTest(nameSpace, 2);
		assertResults(counts, "2", null, null, "2", null);
	}

	protected void siteTypeTest() {
		List<Map<String, Object>> counts = siteTypeTest(nameSpace, 5);
		assertResults(counts, "7", "1", "1", "4", "1");
	}

	protected void stateTest() {
		List<Map<String, Object>> counts = stateTest(nameSpace, 4);
		assertResults(counts, "5", "1", "1", "3", null);
	}

	protected void withinTest() {
		List<Map<String, Object>> counts = withinTest(nameSpace, 4);
		assertResults(counts, "5", "1", "1", "3", null);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against activity_sum

	protected void projectTest() {
		List<Map<String, Object>> counts = projectTest(nameSpace, 5);
		assertResults(counts, "5", "1", "1", "2", "1");
	}

	protected void sampleMediaTest() {
		List<Map<String, Object>> counts = sampleMediaTest(nameSpace, 5);
		assertResults(counts, "7", "1", "1", "4", "1");
	}

	protected void startDateHiTest() {
		List<Map<String, Object>> counts = startDateHiTest(nameSpace, 5);
		assertResults(counts, "7", "1", "1", "4", "1");
	}

	protected void startDateLoTest() {
		List<Map<String, Object>> counts = startDateLoTest(nameSpace, 5);
		assertResults(counts, "5", "1", "1", "2", "1");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against result_sum

	protected void analyticalMethodTest() {
		List<Map<String, Object>> counts = analyticalMethodTest(nameSpace, 3);
		assertResults(counts, "2", "1", null, "1", null);
	}

	protected void assemblageTest() {
		List<Map<String, Object>> counts = assemblageTest(nameSpace, 3);
		assertResults(counts, "2", null, null, "1", "1");
	}

	protected void characteristicNameTest() {
		List<Map<String, Object>> counts = characteristicNameTest(nameSpace, 2);
		assertResults(counts, "1", null, null, "1", null);
	}

	protected void characteristicTypeTest() {
		List<Map<String, Object>> counts = characteristicTypeTest(nameSpace, 3);
		assertResults(counts, "2", null, "1", "1", null);
	}

	protected void pcodeTest() {
		List<Map<String, Object>> counts = pcodeTest(nameSpace, 3);
		assertResults(counts, "2", "1", null, "1", null);
	}

	protected void subjectTaxonomicNameTest() {
		List<Map<String, Object>> counts = subjectTaxonomicNameTest(nameSpace, 3);
		assertResults(counts, "2", null, null, "1", "1");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	protected void multipleParameterStationSumTest() {
		List<Map<String, Object>> counts = multipleParameterStationSumTest(nameSpace, 4);
		assertResults(counts, "3", "1", "1", "1", null);
	}

	protected void multipleParameterActivitySumTest() {
		FilterParameters filter = getNoEffectParameters(nameSpace);

		filter.setCommand(getCommand());
		filter.setBBox(getBBox());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setWithin(getWithin());
		List<Map<String, Object>> counts = callDao(nameSpace, 2, filter);
		assertResults(counts, "1", null, null, "1", null);
	}

	protected void multipleParameterResultSumTest() {
		FilterParameters filter = getNoEffectParameters(nameSpace);

		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setCommand(getCommand());
		filter.setAssemblage(getAssemblage());
		filter.setBBox(getBBox());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setNldiSites(getNldiSites());
		filter.setOrganization(getOrganization());
		filter.setPCode(getPcode());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSampleMedia(getSampleMedia());
		filter.setSiteid(getSiteId());
		filter.setSiteType(getSiteType());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setStatecode(getState());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		filter.setWithin(getWithin());
		List<Map<String, Object>> counts = callDao(nameSpace, 2, filter);
		assertResults(counts, FILTERED_TOTAL_SITE_COUNT, null, null, FILTERED_STORET_SITE_COUNT, null);
	}

	protected void assertFullDbReturned(List<Map<String, Object>> counts) {
		assertResults(counts, TOTAL_ORGANIZATION_COUNT, NWIS_ORGANIZATION_COUNT, STEWARDS_ORGANIZATION_COUNT, STORET_ORGANIZATION_COUNT, BIODATA_ORGANIZATION_COUNT);
	}

	@Override
	public List<Map<String, Object>> callDao(NameSpace nameSpace, int expectedSize, FilterParameters filter) {
		List<Map<String, Object>> counts = countDao.getCounts(nameSpace, filter);
		assertEquals(expectedSize, counts.size());
		return counts;
	}

	protected void assertResults(List<Map<String, Object>> counts, String total, String nwis, String stewards,
			String storet, String biodata) {
		LOG.debug(counts.toString());
		assertResults(counts, CountColumn.KEY_ORGANIZATION_COUNT, total, nwis, stewards, storet, biodata);
	}

}
