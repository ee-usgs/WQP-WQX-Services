package gov.usgs.cida.wqp.dao.count;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CountDaoProjectMLWeightingTest extends BaseCountDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(CountDaoProjectMLWeightingTest.class);

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
//		multipleParameterActivitySumTest();
//		multipleParameterActivitySumStationSumTest();
//		multipleParameterResultSumTest();
//		multipleParameterResultSumStationSumTests();
//		multipleParameterResultSumActivitySumTests();
	}

	public void nullParameterTest() {
		init();
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, null);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, TOTAL_PRJ_ML_WEIGHTING_COUNT, NWIS_PRJ_ML_WEIGHTING_COUNT, STEWARDS_PRJ_ML_WEIGHTING_COUNT, STORET_PRJ_ML_WEIGHTING_COUNT, BIODATA_PRJ_ML_WEIGHTING_COUNT);
		cleanup();
	}

	public void emptyParameterTest() {
		init();
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, TOTAL_PRJ_ML_WEIGHTING_COUNT, NWIS_PRJ_ML_WEIGHTING_COUNT, STEWARDS_PRJ_ML_WEIGHTING_COUNT, STORET_PRJ_ML_WEIGHTING_COUNT, BIODATA_PRJ_ML_WEIGHTING_COUNT);
		cleanup();
	}

	public void sampleMediaTest() {
		init();
		filter.setSampleMedia(getSampleMedia());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, "6", "2", "1", "2", "1");
		cleanup();
	}

	public void startDateHiTest() {
		init();
		filter.setStartDateHi(getStartDateHi());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, "6", "2", "1", "2", "1");
		cleanup();
	}

	public void startDateLoTest() {
		init();
		filter.setStartDateLo(getStartDateLo());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 4, "4", "2", "1", null, "1");
		cleanup();
	}

	public void analyticalMethodTest() {
		init();
		filter.setAnalyticalmethod(getAnalyticalMethod());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "2", "2", null, null, null);
		cleanup();
	}

	public void assemblageTest() {
		init();
		filter.setAssemblage(getAssemblage());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "1", null, null, null, "1");
		cleanup();
	}

	public void characteristicNameTest() {
		init();
		filter.setCharacteristicName(getCharacteristicName());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 0, null, null, null, null, null);
		cleanup();
	}

	public void characteristicTypeTest() {
		init();
		filter.setCharacteristicType(getCharacteristicType());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "1", null, "1", null, null);
		cleanup();
	}

	public void pcodeTest() {
		init();
		filter.setPCode(getPcode());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "2", "2", null, null, null);
		cleanup();
	}

	public void subjectTaxonomicNameTest() {
		init();
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 2, "1", null, null, null, "1");
		cleanup();
	}
}
