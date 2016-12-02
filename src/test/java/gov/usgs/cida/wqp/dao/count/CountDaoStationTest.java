package gov.usgs.cida.wqp.dao.count;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.BaseDao;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class CountDaoStationTest extends CountDaoTest {

	@Test
	public void singleParameterTests() {
		singleParameterTests(BaseDao.STATION_NAMESPACE, false, false);
	}

	@Test
	public void multipleParameterTests() {
		multipleParameterTests(BaseDao.STATION_NAMESPACE, false, false);
	}

	@Test
	public void singleParameterKmlTests() {
		singleParameterTests(BaseDao.STATION_KML_NAMESPACE, false, false);
	}

	@Test
	public void multipleParameterKmlTests() {
		multipleParameterTests(BaseDao.STATION_KML_NAMESPACE, false, false);
	}

}
