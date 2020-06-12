package gov.usgs.wma.wqp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import gov.usgs.wma.wqp.BaseIT;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, CountDao.class})
public class CountDaoIT extends BaseIT {

	@Autowired
	private CountDao countDao;

	private FilterParameters filter;
	private String one = "1";
	private String five = "5";
	private String twelve = "12";

	@BeforeEach
	public void setUp() throws Exception {
		filter = new FilterParameters();
	}

	@Test
	public void adjustMinCountNullTest() {
		FilterParameters resultFilter = countDao.adjustMinCount(null, null);
		assertNull(resultFilter);

		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, null);
		assertNotNull(resultFilter);
		assertEquals(one, resultFilter.getMinactivities());
	}

	@Test
	public void adjustMinCountNoChangeTest() {
		//Nothing changed with Station Namespace
		FilterParameters resultFilter = countDao.adjustMinCount(NameSpace.STATION, filter);
		assertEquals(filter, resultFilter);

		//Don't change existing minimum count values
		filter.setMinactivities(five);
		filter.setMinresults(twelve);
		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());

		resultFilter = countDao.adjustMinCount(NameSpace.BIOLOGICAL_RESULT, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());
	}

	@Test
	public void adjustMinCountActivityTest() {
		//Add the minimum activity count
		FilterParameters resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(one, resultFilter.getMinactivities());
		assertTrue(StringUtils.isBlank(resultFilter.getMinresults()));

		//Don't change existing minimum count values
		filter.setMinactivities(five);
		filter.setMinresults(twelve);
		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());
	}

	@Test
	public void adjustMinCountBiologicalTest() {
		//Add the minimum result count - Biological
		FilterParameters resultFilter = countDao.adjustMinCount(NameSpace.BIOLOGICAL_RESULT, filter);
		assertTrue(StringUtils.isBlank(resultFilter.getMinactivities()));
		assertEquals(one, resultFilter.getMinresults());

		//Don't change existing minimum count values
		filter.setMinactivities(five);
		filter.setMinresults(twelve);
		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());
	}

	@Test
	public void adjustMinCountPCTest() {
		//Add the minimum result count - Result
		FilterParameters resultFilter = countDao.adjustMinCount(NameSpace.RESULT, filter);
		assertTrue(StringUtils.isBlank(resultFilter.getMinactivities()));
		assertEquals(one, resultFilter.getMinresults());

		//Don't change existing minimum count values
		filter.setMinactivities(five);
		filter.setMinresults(twelve);
		resultFilter = countDao.adjustMinCount(NameSpace.ACTIVITY, filter);
		assertEquals(five, resultFilter.getMinactivities());
		assertEquals(twelve, resultFilter.getMinresults());
	}

}
