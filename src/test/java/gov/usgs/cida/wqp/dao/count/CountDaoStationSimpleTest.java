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
public class CountDaoStationSimpleTest extends CountDaoTest {

	@Test
	public void singleParameterTests() {
		super.singleParameterTests(BaseDao.SIMPLE_STATION_NAMESPACE, false);
	}
	
	@Test
	public void multipleParameterTests() {
		super.multipleParameterTests(BaseDao.SIMPLE_STATION_NAMESPACE, false);
	}

}
