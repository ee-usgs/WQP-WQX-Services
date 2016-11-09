package gov.usgs.cida.wqp.dao.count;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.BaseDao;

@Category(DBIntegrationTest.class)
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/stationCount.xml")
})
public class CountDaoStationTest extends CountDaoTest {

	@Test
	public void singleParameterTests() {
		super.singleParameterTests(BaseDao.STATION_NAMESPACE, false);
	}
	
	@Test
	public void multipleParameterTests() {
		super.multipleParameterTests(BaseDao.STATION_NAMESPACE, false);
	}

	@Test
	public void singleParameterKmlTests() {
		super.singleParameterTests(BaseDao.STATION_KML_NAMESPACE, false);
	}
	
	@Test
	public void multipleParameterKmlTests() {
		super.multipleParameterTests(BaseDao.STATION_KML_NAMESPACE, false);
	}

}
