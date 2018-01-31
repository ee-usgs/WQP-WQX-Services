package gov.usgs.cida.wqp.dao.count;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.CountDao;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.CountColumn;
import gov.usgs.cida.wqp.parameter.FilterParameters;


@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoProjectMLWeightingTest extends BaseSpringTest {

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
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, null);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, TOTAL_PRJ_ML_WEIGHTING_COUNT, NWIS_PRJ_ML_WEIGHTING_COUNT, STEWARDS_PRJ_ML_WEIGHTING_COUNT, STORET_PRJ_ML_WEIGHTING_COUNT, BIODATA_PRJ_ML_WEIGHTING_COUNT);
	}

	@Test
	public void emptyParameterTest() {
		List<Map<String, Object>> counts = countDao.getCounts(NameSpace.PROJECT_MONITORING_LOCATION_WEIGHTING, filter);
		assertResults(counts, CountColumn.KEY_PROJECT_MONITORING_LOCATION_WEIGHTING_COUNT, 5, TOTAL_PRJ_ML_WEIGHTING_COUNT, NWIS_PRJ_ML_WEIGHTING_COUNT, STEWARDS_PRJ_ML_WEIGHTING_COUNT, STORET_PRJ_ML_WEIGHTING_COUNT, BIODATA_PRJ_ML_WEIGHTING_COUNT);
	}

	protected void assertResults(List<Map<String, Object>> counts, String countType, int expectedSize,
			String expectedTotal, String expectedNwis, String expectedStewards, String expectedStoret,
			String expectedBiodata) {
		assertEquals("Number of counts", expectedSize, counts.size());
		boolean nwis = (null == expectedNwis);
		boolean stewards = (null == expectedStewards);
		boolean storet = (null == expectedStoret);
		boolean biodata = (null == expectedBiodata);
		boolean total = (null == expectedTotal);
		for (int i = 0 ; i < counts.size() ; i++) {
			if (null == counts.get(i).get(BaseColumn.KEY_DATA_SOURCE)) {
				assertEquals("total " + countType + " count", expectedTotal, counts.get(i).get(countType).toString());
				total = true;
			} else {
				switch (counts.get(i).get(BaseColumn.KEY_DATA_SOURCE).toString()) {
				case NWIS:
					assertEquals("NWIS " + countType + " count", expectedNwis, counts.get(i).get(countType).toString());
					nwis = true;
					break;
				case STEWARDS:
					assertEquals("STEWARDS " + countType + " count", expectedStewards, counts.get(i).get(countType).toString());
					stewards = true;
					break;
				case STORET:
					assertEquals("STORET " + countType + " count", expectedStoret, counts.get(i).get(countType).toString());
					storet = true;
					break;
				case BIODATA:
					assertEquals("BIODATA " + countType + " count", expectedBiodata, counts.get(i).get(countType).toString());
					biodata = true;
					break;
				default:
					break;
				}
			}
		}
		assertTrue("Did not get " + countType + " Total", total);
		assertTrue("Did not get " + countType + " NWIS", nwis);
		assertTrue("Did not get " + countType + " STEWARDS", stewards);
		assertTrue("Did not get " + countType + " STORET", storet);
		assertTrue("Did not get " + countType + " BIODATA", biodata);
	}

}
