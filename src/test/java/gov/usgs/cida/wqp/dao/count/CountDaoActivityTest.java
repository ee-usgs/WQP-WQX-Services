package gov.usgs.cida.wqp.dao.count;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.BaseDao;
import gov.usgs.cida.wqp.dao.CountDao;

@Category(DBIntegrationTest.class)
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/dao/activity.xml")
})
@Ignore
//TODO
public class CountDaoActivityTest extends CountDaoTest {

	@Autowired 
	CountDao countDao;

	@Test
	public void singleParameterTests() {
		super.singleParameterTests(BaseDao.ACTIVITY_NAMESPACE, true);
	}

	@Test
	public void multipleParameterTests() {
		super.multipleParameterTests(BaseDao.ACTIVITY_NAMESPACE, true);
	}

}
