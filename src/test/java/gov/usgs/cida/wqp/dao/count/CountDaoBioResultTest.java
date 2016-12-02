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
public class CountDaoBioResultTest extends CountDaoTest {

	@Test
	public void singleParameterTests() {
		singleParameterTests(BaseDao.BIOLOGICAL_RESULT_NAMESPACE, true, true);
	}

	@Test
	public void multipleParameterTests() {
		multipleParameterTests(BaseDao.BIOLOGICAL_RESULT_NAMESPACE, true, true);
	}

}
