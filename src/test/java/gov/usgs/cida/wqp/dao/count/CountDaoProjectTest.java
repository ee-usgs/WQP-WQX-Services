package gov.usgs.cida.wqp.dao.count;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.CountDao;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.CountColumn;
import gov.usgs.cida.wqp.parameter.FilterParameters;


@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoProjectTest extends BaseCountDaoTest {

	@Autowired
	CountDao countDao;

	FilterParameters filter;

	@Before
	public void init() {
		filter = new FilterParameters();
	}

	@After
	public void cleanup() {
		filter = null;
	}

	@Test
	public void nullParameterTest() {
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, null);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, TOTAL_PROJECT_COUNT, NWIS_PROJECT_COUNT, STEWARDS_PROJECT_COUNT, STORET_PROJECT_COUNT, BIODATA_PROJECT_COUNT);
	}

	@Test
	public void emptyParameterTest() {
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, TOTAL_PROJECT_COUNT, NWIS_PROJECT_COUNT, STEWARDS_PROJECT_COUNT, STORET_PROJECT_COUNT, BIODATA_PROJECT_COUNT);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against activity_sum

	@Test
	public void sampleMediaTest() {
		filter.setSampleMedia(getSampleMedia());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, "6", "1", "1", "3", "1");
	}

	@Test
	public void startDateHiTest() {
		filter.setStartDateHi(getStartDateHi());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, "6", "1", "1", "3", "1");
	}

	@Test
	public void startDateLoTest() {
		filter.setStartDateLo(getStartDateLo());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 5, "4", "1", "1", "1", "1");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against result_sum

	@Test
	public void analyticalMethodTest() {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "3", "1", null, "2", null);
	}

	@Test
	public void assemblageTest() {
		filter.setAssemblage(getAssemblage());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "3", null, null, "2", "1");
	}

	@Test
	public void characteristicNameTest() {
		filter.setCharacteristicName(getCharacteristicName());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 2, "2", null, null, "2", null);
	}

	@Test
	public void characteristicTypeTest() {
		filter.setCharacteristicType(getCharacteristicType());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "3", null, "1", "2", null);
	}

	@Test
	public void pcodeTest() {
		filter.setPCode(getPcode());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "3", "1", null, "2", null);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_COUNT, 3, "2", null, null, "1", "1");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void multipleParameterActivitySumTest() {
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
	}

	@Test
	public void multipleParameterActivitySumStationSumTest() {
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
	}

	@Test
	public void multipleParameterResultSumTest() {
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
	}

	@Test
	public void multipleParameterResultSumActivitySumTests() {
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
	}
}
