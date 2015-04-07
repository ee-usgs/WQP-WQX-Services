package gov.usgs.cida.wqp.webservice.SimpleStation;


import static org.junit.Assert.assertTrue;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.IntegrationTest;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.annotation.DatabaseSetup;

@Category(IntegrationTest.class)
@DatabaseSetup("classpath:/testData/simpleStation.xml")
public class SimpleStationDaoTest extends BaseSpringTest {

	@Test
	public void myTest() {
		assertTrue(true);
	}
}
