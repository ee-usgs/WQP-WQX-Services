package gov.usgs.cida.wqp.dao.count;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.CountColumn;


@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoProjectTest extends BaseCountDaoTest {

	@Test
	public void testHarness() {
		nullParameterTest();
		emptyParameterTest();
		analyticalMethodTest();
		assemblageTest();
//		bboxTest();
		characteristicNameTest();
		characteristicTypeTest();
//		countryTest();
//		countyTest();
//		huc2Test();
//		huc3Test();
//		huc4Test();
//		huc5Test();
//		huc6Test();
//		huc7Test();
//		huc8Test();
//		huc10Test();
//		huc12Test();
//		minActivitiesTest();
//		minResultsTest();
//		nldiUrlTest();
//		organizationTest();
		pcodeTest();
//		projectTest();
//		providersTest();
		sampleMediaTest();
//		siteIdTest();
//		manySitesTest();
//		siteTypeTest();
		startDateHiTest();
		startDateLoTest();
//		stateTest();
		subjectTaxonomicNameTest();
//		withinTest();
//		multipleParameterStationSumTest();
		multipleParameterActivitySumTest();
		multipleParameterActivitySumStationSumTest();
		multipleParameterResultSumTest();
//		multipleParameterResultSumStationSumTests();
		multipleParameterResultSumActivitySumTests();
	}

	public void nullParameterTest() {
		init();
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, null);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, TOTAL_PROJECT_COUNT, NWIS_PROJECT_COUNT, STEWARDS_PROJECT_COUNT, STORET_PROJECT_COUNT, BIODATA_PROJECT_COUNT);
		cleanup();
	}

	public void emptyParameterTest() {
		init();
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, TOTAL_PROJECT_COUNT, NWIS_PROJECT_COUNT, STEWARDS_PROJECT_COUNT, STORET_PROJECT_COUNT, BIODATA_PROJECT_COUNT);
		cleanup();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against activity_sum

	public void sampleMediaTest() {
		init();
		filter.setSampleMedia(getSampleMedia());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, "6", "1", "1", "3", "1");
		cleanup();
	}

	public void startDateHiTest() {
		init();
		filter.setStartDateHi(getStartDateHi());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, "6", "1", "1", "3", "1");
		cleanup();
	}

	public void startDateLoTest() {
		init();
		filter.setStartDateLo(getStartDateLo());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, "4", "1", "1", "1", "1");
		cleanup();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against result_sum

	public void analyticalMethodTest() {
		init();
		filter.setAnalyticalmethod(getAnalyticalMethod());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "3", "1", null, "2", null);
		cleanup();
	}

	public void assemblageTest() {
		init();
		filter.setAssemblage(getAssemblage());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "3", null, null, "2", "1");
		cleanup();
	}

	public void characteristicNameTest() {
		init();
		filter.setCharacteristicName(getCharacteristicName());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 2, "2", null, null, "2", null);
		cleanup();
	}

	public void characteristicTypeTest() {
		init();
		filter.setCharacteristicType(getCharacteristicType());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "3", null, "1", "2", null);
		cleanup();
	}

	public void pcodeTest() {
		init();
		filter.setPCode(getPcode());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "3", "1", null, "2", null);
		cleanup();
	}

	public void subjectTaxonomicNameTest() {
		init();
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "2", null, null, "1", "1");
		cleanup();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void multipleParameterActivitySumTest() {
		init();
		filter.setCommand(getCommand());
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setNldiSites(getNldiSites());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());
		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 2, "1", null, null, "1", null);
		cleanup();
	}

	public void multipleParameterActivitySumStationSumTest() {
		init();
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
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 2, "1", null, null, "1", null);
		cleanup();
	}

	public void multipleParameterResultSumTest() {
		init();
		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setCommand(getCommand());
		filter.setAssemblage(getAssemblage());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setNldiSites(getNldiSites());
		filter.setOrganization(getOrganization());
		filter.setPCode(getPcode());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSampleMedia(getSampleMedia());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 2, "1", null, null, "1", null);
		cleanup();
	}

	public void multipleParameterResultSumActivitySumTests() {
		init();
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
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setStatecode(getState());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		filter.setWithin(getWithin());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 2, "1", null, null, "1", null);
		cleanup();
	}

}
