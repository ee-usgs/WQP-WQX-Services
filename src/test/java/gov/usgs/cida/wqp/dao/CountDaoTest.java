package gov.usgs.cida.wqp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.Parameters;

public class CountDaoTest extends BaseSpringTest {

	@Autowired 
	CountDao countDao;

	Map<String, Object> parameterMap;
	String[] one = new String[]{"1"};
	String[] five = new String[]{"5"};
	String[] twelve = new String[]{"12"};

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		parameterMap = new HashMap<>();
		parameterMap.put("test", "test");
	}

	@Test
	public void adjustMinCountNoChangeTest() {
		//Nothing changed with Station Namespace
		Map<String, Object> resultMap = countDao.adjustMinCount(NameSpace.STATION, parameterMap);
		assertEquals(1, resultMap.size());
		assertEquals(parameterMap, resultMap);

		//Don't change existing minimum count values
		parameterMap.put(Parameters.MIN_ACTIVITIES.toString(), five);
		parameterMap.put(Parameters.MIN_RESULTS.toString(), twelve);
		resultMap = countDao.adjustMinCount(NameSpace.ACTIVITY, parameterMap);
		assertEquals(3, resultMap.size());
		assertTrue(resultMap.containsKey("test"));
		assertEquals("test", resultMap.get("test"));
		assertTrue(resultMap.containsKey(Parameters.MIN_ACTIVITIES.toString()));
		assertEquals(five, resultMap.get(Parameters.MIN_ACTIVITIES.toString()));
		assertTrue(resultMap.containsKey(Parameters.MIN_RESULTS.toString()));
		assertEquals(twelve, resultMap.get(Parameters.MIN_RESULTS.toString()));
	}

	@Test
	public void adjustMinCountActivityTest() {
		//Add the minimum activity count
		Map<String, Object> resultMap = countDao.adjustMinCount(NameSpace.ACTIVITY, parameterMap);
		assertEquals(2, resultMap.size());
		assertTrue(resultMap.containsKey("test"));
		assertEquals("test", resultMap.get("test"));
		assertTrue(resultMap.containsKey(Parameters.MIN_ACTIVITIES.toString()));
		assertArrayEquals(one, (String[]) resultMap.get(Parameters.MIN_ACTIVITIES.toString()));

		//Don't change existing minimum count values
		parameterMap.put(Parameters.MIN_ACTIVITIES.toString(), five);
		parameterMap.put(Parameters.MIN_RESULTS.toString(), twelve);
		resultMap = countDao.adjustMinCount(NameSpace.ACTIVITY, parameterMap);
		assertEquals(3, resultMap.size());
		assertTrue(resultMap.containsKey("test"));
		assertEquals("test", resultMap.get("test"));
		assertTrue(resultMap.containsKey(Parameters.MIN_ACTIVITIES.toString()));
		assertEquals(five, resultMap.get(Parameters.MIN_ACTIVITIES.toString()));
		assertTrue(resultMap.containsKey(Parameters.MIN_RESULTS.toString()));
		assertEquals(twelve, resultMap.get(Parameters.MIN_RESULTS.toString()));
	}

	@Test
	public void adjustMinCountBiologicalTest() {
		//Add the minimum result count - Biological
		Map<String, Object> resultMap = countDao.adjustMinCount(NameSpace.BIOLOGICAL_RESULT, parameterMap);
		assertEquals(2, resultMap.size());
		assertTrue(resultMap.containsKey("test"));
		assertEquals("test", resultMap.get("test"));
		assertTrue(resultMap.containsKey(Parameters.MIN_RESULTS.toString()));
		assertArrayEquals(one, (String[]) resultMap.get(Parameters.MIN_RESULTS.toString()));

		//Don't change existing minimum count values
		parameterMap.put(Parameters.MIN_ACTIVITIES.toString(), five);
		parameterMap.put(Parameters.MIN_RESULTS.toString(), twelve);
		resultMap = countDao.adjustMinCount(NameSpace.ACTIVITY, parameterMap);
		assertEquals(3, resultMap.size());
		assertTrue(resultMap.containsKey("test"));
		assertEquals("test", resultMap.get("test"));
		assertTrue(resultMap.containsKey(Parameters.MIN_ACTIVITIES.toString()));
		assertEquals(five, resultMap.get(Parameters.MIN_ACTIVITIES.toString()));
		assertTrue(resultMap.containsKey(Parameters.MIN_RESULTS.toString()));
		assertEquals(twelve, resultMap.get(Parameters.MIN_RESULTS.toString()));
	}

	@Test
	public void adjustMinCountPCTest() {
		//Add the minimum result count - Result
		Map<String, Object> resultMap = countDao.adjustMinCount(NameSpace.RESULT, parameterMap);
		assertEquals(2, resultMap.size());
		assertTrue(resultMap.containsKey("test"));
		assertEquals("test", resultMap.get("test"));
		assertTrue(resultMap.containsKey(Parameters.MIN_RESULTS.toString()));
		assertArrayEquals(one, (String[]) resultMap.get(Parameters.MIN_RESULTS.toString()));

		//Don't change existing minimum count values
		parameterMap.put(Parameters.MIN_ACTIVITIES.toString(), five);
		parameterMap.put(Parameters.MIN_RESULTS.toString(), twelve);
		resultMap = countDao.adjustMinCount(NameSpace.ACTIVITY, parameterMap);
		assertEquals(3, resultMap.size());
		assertTrue(resultMap.containsKey("test"));
		assertEquals("test", resultMap.get("test"));
		assertTrue(resultMap.containsKey(Parameters.MIN_ACTIVITIES.toString()));
		assertEquals(five, resultMap.get(Parameters.MIN_ACTIVITIES.toString()));
		assertTrue(resultMap.containsKey(Parameters.MIN_RESULTS.toString()));
		assertEquals(twelve, resultMap.get(Parameters.MIN_RESULTS.toString()));
	}

}
