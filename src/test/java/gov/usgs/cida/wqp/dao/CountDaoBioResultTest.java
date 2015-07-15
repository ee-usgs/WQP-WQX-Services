package gov.usgs.cida.wqp.dao;

import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.intfc.ICountDao;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

@Category(DBIntegrationTest.class)
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/stationCount.xml")
})
public class CountDaoBioResultTest extends CountDaoTest {

	@Autowired 
	CountDao countDao;

	@Test
	public void singleParameterTests() {
		super.singleParameterTests(ICountDao.BIOLOGICAL_RESULT_NAMESPACE, true);
	}
	
	@Test
	public void multipleParameterTests() {
		super.multipleParameterTests(ICountDao.BIOLOGICAL_RESULT_NAMESPACE, true);
	}

}
