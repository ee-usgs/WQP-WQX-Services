package gov.usgs.cida.wqp.dao;

import gov.usgs.cida.wqp.IntegrationTest;
import gov.usgs.cida.wqp.dao.intfc.ICountDao;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

@Category(IntegrationTest.class)
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/stationCount.xml")
})
public class CountDaoStationSimpleTest extends CountDaoTest {

	@Test
	public void singleParameterTests() {
		super.singleParameterTests(ICountDao.SIMPLE_STATION_NAMESPACE);
	}
	
	@Test
	public void multipleParameterTests() {
		super.multipleParameterTests(ICountDao.SIMPLE_STATION_NAMESPACE);
	}

}
