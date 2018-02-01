package gov.usgs.cida.wqp.dao.streaming;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
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

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.mapping.TestProjectMLWeightingMap;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ProjectMLWeightingStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(ProjectMLWeightingStreamingTest.class);

	@Autowired 
	IStreamingDao streamingDao;

	TestResultHandler handler;
	FilterParameters filter;
	NameSpace nameSpace = NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING;

	public static final String[] STORET_FW08OK051 = new String[]{"3", "NARSTEST-FW08OK051"};
	public static final String[] STEWARDS_FW08OR074 = new String[]{"1", "NARSTEST-FW08OR074"};
	public static final String[] STORET_FW08CA054 = new String[]{"3", "NARSTEST-FW08CA054"};
	public static final String[] STORET_OWW04440_0185 = new String[]{"3", "NARS_WQX-OWW04440-0185"};
	public static final String[] NWIS_FW08MS010 = new String[]{"2", "NARSTEST-FW08MS010"};
	public static final String[] STORET_MS03_0005 = new String[]{"3", "NARS_WQX-MS03-0005"};
	public static final String[] NWIS_FW08LA029 = new String[]{"2", "NARSTEST-FW08LA029"};
	public static final String[] STORET_OWW04440_0387 = new String[]{"3", "NARS_WQX-OWW04440-0387"};
	public static final String[] STORET_SC01_0009 = new String[]{"3", "NARS_WQX-SC01-0009"};
	public static final String[] BIODATA_FW08MI026 = new String[]{"4", "NARSTEST-FW08MI026"};

	public static final int PRJ_ML_WEIGHTING_COLUMN_COUNT = TestProjectMLWeightingMap.PROJECT_MONITORING_LOCATION_WEIGHTING.keySet().size();

	@Before
	public void init() {
		handler = new TestResultHandler();
		filter = new FilterParameters();
	}

	@After
	public void cleanup() {
		handler = null;
		filter = null;
	}

	@Test
	public void nullParameterTest() {
		streamingDao.stream(nameSpace, null, handler);
		assertEquals(TOTAL_PRJ_ML_WEIGHTING_COUNT, String.valueOf(handler.getResults().size()));
	}

	@Test
	public void emptyParameterTest() {
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(TOTAL_PRJ_ML_WEIGHTING_COUNT, String.valueOf(handler.getResults().size()));
	}

	@Test
	public void allDataSortedTest() {
		filter.setSorted("yes");
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		//Validate the number AND order of results.
		assertEquals(TOTAL_PRJ_ML_WEIGHTING_COUNT, String.valueOf(results.size()));

		assertProjectMLWeighting(results.get(0), STORET_FW08OK051);
		assertProjectMLWeighting(results.get(1), STEWARDS_FW08OR074);
		assertProjectMLWeighting(results.get(2), STORET_FW08CA054);
		assertProjectMLWeighting(results.get(3), STORET_OWW04440_0185);
		assertProjectMLWeighting(results.get(4), NWIS_FW08MS010);
		assertProjectMLWeighting(results.get(5), STORET_MS03_0005);
		assertProjectMLWeighting(results.get(6), NWIS_FW08LA029);
		assertProjectMLWeighting(results.get(7), STORET_OWW04440_0387);
		assertProjectMLWeighting(results.get(8), STORET_SC01_0009);
		assertProjectMLWeighting(results.get(9), BIODATA_FW08MI026);
	}

	private void assertProjectMLWeighting(Map<String,Object> row, String[] comparison) {
		assertEquals(PRJ_ML_WEIGHTING_COLUMN_COUNT, row.keySet().size());
		assertEquals(row.get(BaseColumn.KEY_DATA_SOURCE_ID), Integer.parseInt(comparison[0]));
		assertEquals(row.get(StationColumn.KEY_SITE_ID), comparison[1]);
	}
}
