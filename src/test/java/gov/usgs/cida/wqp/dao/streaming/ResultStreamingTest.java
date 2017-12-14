package gov.usgs.cida.wqp.dao.streaming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.ResultColumn;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public abstract class ResultStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(ResultStreamingTest.class);

	@Autowired 
	protected IStreamingDao streamingDao;

	protected TestResultHandler handler;
	protected FilterParameters filter;

	public static final BigDecimal[] STEWARDS_1 = new BigDecimal[]{STEWARDS_ID, BigDecimal.ONE};
	public static final BigDecimal[] STEWARDS_2 = new BigDecimal[]{STEWARDS_ID, BigDecimal.valueOf(2)};
	public static final BigDecimal[] STEWARDS_3 = new BigDecimal[]{STEWARDS_ID, BigDecimal.valueOf(3)};

	public static final BigDecimal[] NWIS_1 = new BigDecimal[]{NWIS_ID, BigDecimal.ONE};
	public static final BigDecimal[] NWIS_2 = new BigDecimal[]{NWIS_ID, BigDecimal.valueOf(2)};
	public static final BigDecimal[] NWIS_3 = new BigDecimal[]{NWIS_ID, BigDecimal.valueOf(3)};
	public static final BigDecimal[] NWIS_4 = new BigDecimal[]{NWIS_ID, BigDecimal.valueOf(4)};
	public static final BigDecimal[] NWIS_5 = new BigDecimal[]{NWIS_ID, BigDecimal.valueOf(5)};

	public static final BigDecimal[] STORET_1 = new BigDecimal[]{STORET_ID, BigDecimal.ONE};
	public static final BigDecimal[] STORET_2 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(2)};
	public static final BigDecimal[] STORET_3 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(3)};
	public static final BigDecimal[] STORET_4 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(4)};
	public static final BigDecimal[] STORET_5 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(5)};
	public static final BigDecimal[] STORET_6 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(6)};
	public static final BigDecimal[] STORET_7 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(7)};
	public static final BigDecimal[] STORET_8 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(8)};
	public static final BigDecimal[] STORET_9 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(9)};
	public static final BigDecimal[] STORET_10 = new BigDecimal[]{STORET_ID, BigDecimal.TEN};
	public static final BigDecimal[] STORET_11 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(11)};
	public static final BigDecimal[] STORET_12 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(12)};
	public static final BigDecimal[] STORET_13 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(13)};
	public static final BigDecimal[] STORET_14 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(14)};
	public static final BigDecimal[] STORET_15 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(15)};
	public static final BigDecimal[] STORET_16 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(16)};
	public static final BigDecimal[] STORET_17 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(17)};
	public static final BigDecimal[] STORET_18 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(18)};
	public static final BigDecimal[] STORET_19 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(19)};
	public static final BigDecimal[] STORET_20 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(20)};
	public static final BigDecimal[] STORET_21 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(21)};
	public static final BigDecimal[] STORET_22 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(22)};
	public static final BigDecimal[] STORET_23 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(23)};
	public static final BigDecimal[] STORET_24 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(24)};
	public static final BigDecimal[] STORET_25 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(25)};
	public static final BigDecimal[] STORET_26 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(26)};
	public static final BigDecimal[] STORET_27 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(27)};
	public static final BigDecimal[] STORET_28 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(28)};
	public static final BigDecimal[] STORET_29 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(29)};
	public static final BigDecimal[] STORET_30 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(30)};
	public static final BigDecimal[] STORET_31 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(31)};
	public static final BigDecimal[] STORET_32 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(32)};
	public static final BigDecimal[] STORET_33 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(33)};
	public static final BigDecimal[] STORET_34 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(34)};
	public static final BigDecimal[] STORET_35 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(35)};
	public static final BigDecimal[] STORET_36 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(36)};
	public static final BigDecimal[] STORET_37 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(37)};
	public static final BigDecimal[] STORET_38 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(38)};
	public static final BigDecimal[] STORET_39 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(39)};
	public static final BigDecimal[] STORET_40 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(40)};
	public static final BigDecimal[] STORET_41 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(41)};
	public static final BigDecimal[] STORET_42 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(42)};
	public static final BigDecimal[] STORET_43 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(43)};
	public static final BigDecimal[] STORET_44 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(44)};
	public static final BigDecimal[] STORET_45 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(45)};
	public static final BigDecimal[] STORET_46 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(46)};
	public static final BigDecimal[] STORET_47 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(47)};
	public static final BigDecimal[] STORET_48 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(48)};
	public static final BigDecimal[] STORET_49 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(49)};
	public static final BigDecimal[] STORET_50 = new BigDecimal[]{STORET_ID, BigDecimal.valueOf(50)};

	public static final BigDecimal[] BIODATA_42 = new BigDecimal[]{BIODATA_ID, BigDecimal.valueOf(42)};

	@Before
	public void init() {
		handler = new TestResultHandler();
		filter = new FilterParameters();
	}

	@After
	public void cleanup() {
		handler = null;
		filter = null;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Only need Result (and possibly a lookup table)

	public void nullParameterTest(NameSpace nameSpace) {
		streamingDao.stream(nameSpace, null, handler);
		assertEquals(TOTAL_RESULT_COUNT, String.valueOf(handler.getResults().size()));
	}

	public void emptyParameterTest(NameSpace nameSpace) {
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(TOTAL_RESULT_COUNT, String.valueOf(handler.getResults().size()));
	}

	public void allDataSortedTest(NameSpace nameSpace, Map<String, Object> expectedMap) {
		Integer expectedColumnCount = expectedMap.keySet().size();
		filter.setSorted("yes");
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertStoret42(expectedMap, results.get(49));

		//Validate the number AND order of results.
		assertEquals(TOTAL_RESULT_COUNT, String.valueOf(results.size()));
		assertRow(results.get(0), STEWARDS_1, expectedColumnCount);
		assertRow(results.get(1), STEWARDS_2, expectedColumnCount);
		assertRow(results.get(2), STEWARDS_3, expectedColumnCount);
		assertRow(results.get(3), NWIS_1, expectedColumnCount);
		assertRow(results.get(4), NWIS_4, expectedColumnCount);
		assertRow(results.get(5), NWIS_5, expectedColumnCount);
		assertRow(results.get(6), NWIS_2, expectedColumnCount);
		assertRow(results.get(7), NWIS_3, expectedColumnCount);
		assertRow(results.get(8), STORET_27, expectedColumnCount);
		assertRow(results.get(9), STORET_15, expectedColumnCount);
		assertRow(results.get(10), STORET_16, expectedColumnCount);
		assertRow(results.get(11), STORET_17, expectedColumnCount);
		assertRow(results.get(12), STORET_18, expectedColumnCount);
		assertRow(results.get(13), STORET_19, expectedColumnCount);
		assertRow(results.get(14), STORET_20, expectedColumnCount);
		assertRow(results.get(15), STORET_21, expectedColumnCount);
		assertRow(results.get(16), STORET_22, expectedColumnCount);
		assertRow(results.get(17), STORET_23, expectedColumnCount);
		assertRow(results.get(18), STORET_24, expectedColumnCount);
		assertRow(results.get(19), STORET_25, expectedColumnCount);
		assertRow(results.get(20), STORET_26, expectedColumnCount);
		assertRow(results.get(21), STORET_28, expectedColumnCount);
		assertRow(results.get(22), STORET_29, expectedColumnCount);
		assertRow(results.get(23), STORET_30, expectedColumnCount);
		assertRow(results.get(24), STORET_31, expectedColumnCount);
		assertRow(results.get(25), STORET_33, expectedColumnCount);
		assertRow(results.get(26), STORET_34, expectedColumnCount);
		assertRow(results.get(27), STORET_35, expectedColumnCount);
		assertRow(results.get(28), STORET_36, expectedColumnCount);
		assertRow(results.get(29), STORET_37, expectedColumnCount);
		assertRow(results.get(30), STORET_39, expectedColumnCount);
		assertRow(results.get(31), STORET_40, expectedColumnCount);
		assertRow(results.get(32), STORET_32, expectedColumnCount);
		assertRow(results.get(33), STORET_38, expectedColumnCount);
		assertRow(results.get(34), STORET_5, expectedColumnCount);
		assertRow(results.get(35), STORET_6, expectedColumnCount);
		assertRow(results.get(36), STORET_7, expectedColumnCount);
		assertRow(results.get(37), STORET_8, expectedColumnCount);
		assertRow(results.get(38), STORET_10, expectedColumnCount);
		assertRow(results.get(39), STORET_14, expectedColumnCount);
		assertRow(results.get(40), STORET_4, expectedColumnCount);
		assertRow(results.get(41), STORET_9, expectedColumnCount);
		assertRow(results.get(42), STORET_11, expectedColumnCount);
		assertRow(results.get(43), STORET_12, expectedColumnCount);
		assertRow(results.get(44), STORET_13, expectedColumnCount);
		assertRow(results.get(45), STORET_45, expectedColumnCount);
		assertRow(results.get(46), STORET_46, expectedColumnCount);
		assertRow(results.get(47), STORET_47, expectedColumnCount);
		assertRow(results.get(48), STORET_41, expectedColumnCount);
		assertRow(results.get(49), STORET_42, expectedColumnCount);
		assertRow(results.get(50), STORET_43, expectedColumnCount);
		assertRow(results.get(51), STORET_44, expectedColumnCount);
		assertRow(results.get(52), STORET_2, expectedColumnCount);
		assertRow(results.get(53), STORET_48, expectedColumnCount);
		assertRow(results.get(54), STORET_49, expectedColumnCount);
		assertRow(results.get(55), STORET_50, expectedColumnCount);
		assertRow(results.get(56), STORET_1, expectedColumnCount);
		assertRow(results.get(57), STORET_3, expectedColumnCount);
		assertRow(results.get(58), BIODATA_42, expectedColumnCount);
	}

	public void analyticalMethodTest(NameSpace nameSpace) {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(17, results.size());
		assertContainsResult(results, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_3, STORET_41, STORET_42, STORET_43,
				STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void assemblageTest(NameSpace nameSpace) {
		filter.setAssemblage(getAssemblage());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(14, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_3, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47,
				STORET_48, STORET_49, STORET_50, BIODATA_42);
	}

	public void avoidTest(NameSpace nameSpace) {
		filter.setCommandavoid(getAvoid());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(STORET_RESULT_COUNT, String.valueOf(results.size()));
		assertContainsResult(results, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20,
				STORET_21, STORET_22, STORET_23, STORET_24, STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30,
				STORET_31, STORET_32, STORET_33, STORET_34, STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40,
				STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void characteristicNameTest(NameSpace nameSpace) {
		filter.setCharacteristicName(getCharacteristicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(13, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_3, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47,
				STORET_48, STORET_49, STORET_50);
	}

	public void characteristicTypeTest(NameSpace nameSpace) {
		filter.setCharacteristicType(getCharacteristicType());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(15, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STORET_1, STORET_2, STORET_3, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45,
				STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void countryTest(NameSpace nameSpace) {
		filter.setCountrycode(getCountry());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(33, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50, BIODATA_42);
	}

	public void countyTest(NameSpace nameSpace) {
		filter.setCountycode(getCounty());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(32, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50);
	}

	public void huc2Test(NameSpace nameSpace) {
		filter.setHuc(getHuc2());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(23, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_4, STORET_5,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_41,
				STORET_45, STORET_46, STORET_47);
	}

	public void huc4Test(NameSpace nameSpace) {
		filter.setHuc(getHuc4());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsResult(results, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8,
				STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14);
	}

	public void huc6Test(NameSpace nameSpace) {
		filter.setHuc(getHuc6());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(15, results.size());
		assertContainsResult(results, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14);
	}

	public void huc8Test(NameSpace nameSpace) {
		filter.setHuc(getHuc8());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsResult(results, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14);
	}

	public void nldiUrlTest(NameSpace nameSpace) {
		try {
			filter.setNldiSites(getManySiteId());
			streamingDao.stream(nameSpace, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(19, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_42, STORET_43, STORET_44, STORET_48, STORET_49, STORET_50);
	}

	public void organizationTest(NameSpace nameSpace) {
		filter.setOrganization(getOrganization());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(32, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50);
	}

	public void projectTest(NameSpace nameSpace) {
		filter.setProject(getProject());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(19, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_3, STORET_42,
				STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50, BIODATA_42);
	}

	public void providersTest(NameSpace nameSpace) {
		filter.setProviders(getProviders());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(58, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22,
				STORET_23, STORET_24, STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32,
				STORET_33, STORET_34, STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_41, STORET_42,
				STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void sampleMediaTest(NameSpace nameSpace) {
		filter.setSampleMedia(getSampleMedia());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(57, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22, STORET_23, STORET_24,
				STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32, STORET_33, STORET_34,
				STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_41, STORET_42, STORET_43, STORET_44,
				STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50, BIODATA_42);
	}

	public void startDateHiTest(NameSpace nameSpace) {
		filter.setStartDateHi(getStartDateHi());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(57, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22, STORET_23, STORET_24,
				STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32, STORET_33, STORET_34,
				STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_41, STORET_42, STORET_43, STORET_44,
				STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50, BIODATA_42);
	}

	public void startDateLoTest(NameSpace nameSpace) {
		filter.setStartDateLo(getStartDateLo());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(22, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49,
				STORET_50, BIODATA_42);
	}

	public void siteIdTest(NameSpace nameSpace) {
		filter.setSiteid(getSiteid());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(21, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49,
				STORET_50);
	}

	public void manySitesTest(NameSpace nameSpace) {
		try {
			filter.setSiteid(getManySiteId());
			streamingDao.stream(nameSpace, filter, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(19, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_42, STORET_43, STORET_44, STORET_48, STORET_49, STORET_50);
	}

	public void minActivitiesTest(NameSpace nameSpace) {
		filter.setMinactivities(getMinActivities());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(55, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_4, STORET_5,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15,
				STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22, STORET_23, STORET_24, STORET_25,
				STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32, STORET_33, STORET_34, STORET_35,
				STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_41, STORET_42, STORET_43, STORET_44,
				STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void minResultsTest(NameSpace nameSpace) {
		filter.setMinresults(getMinResults());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(53, results.size());
		assertContainsResult(results, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7,
				STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, STORET_17,
				STORET_18, STORET_19, STORET_20, STORET_21, STORET_22, STORET_23, STORET_24, STORET_25, STORET_26, STORET_27,
				STORET_28, STORET_29, STORET_30, STORET_31, STORET_32, STORET_33, STORET_34, STORET_35, STORET_36, STORET_37,
				STORET_38, STORET_39, STORET_40, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47,
				STORET_48, STORET_49, STORET_50);
	}

	public void pcodeTest(NameSpace nameSpace) {
		filter.setPCode(getPcode());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(13, results.size());
		assertContainsResult(results, NWIS_3, STORET_1, STORET_2, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47,
				STORET_48, STORET_49, STORET_50);
	}

	public void siteTypeTest(NameSpace nameSpace) {
		filter.setSiteType(getSiteType());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(58, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_3,
				STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22, STORET_23,
				STORET_24, STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32, STORET_33,
				STORET_34, STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_41, STORET_42, STORET_43,
				STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50, BIODATA_42);
	}

	public void stateTest(NameSpace nameSpace) {
		filter.setStatecode(getState());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(32, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50);
	}

	public void subjectTaxonomicNameTest(NameSpace nameSpace) {
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(13, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_3, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50, BIODATA_42);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Result + Station_Sum

	public void bboxTest(NameSpace nameSpace) {
		filter.setBBox(getBBox());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(31, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49,
				STORET_50);
	}

	public void withinTest(NameSpace nameSpace) {
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(54, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22,
				STORET_23, STORET_24, STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32,
				STORET_33, STORET_34, STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_42, STORET_43,
				STORET_44, STORET_48, STORET_49, STORET_50);
	}


	public void multipleParameterResultTests(NameSpace nameSpace) {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setAssemblage(getAssemblage());
		filter.setCommandavoid(getAvoid());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setPCode(getPcode());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSampleMedia(getSampleMedia());
		filter.setSiteType(getSiteType());
		filter.setSiteid(getSiteid());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setStatecode(getState());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49,
				STORET_50);
	}

	public void multipleParameterResultStationSumTests(NameSpace nameSpace) {
		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setAssemblage(getAssemblage());
		filter.setCommandavoid(getAvoid());
		filter.setBBox(getBBox());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setNldiSites(getNldiSites());
		filter.setOrganization(getOrganization());
		filter.setPCode(getPcode());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSampleMedia(getSampleMedia());
		filter.setSiteType(getSiteType());
		filter.setSiteid(getSiteid());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setStatecode(getState());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		filter.setWithin(getWithin());
		streamingDao.stream(nameSpace, filter, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsResult(results, STORET_1, STORET_48, STORET_49, STORET_50);
	}

	public static void assertRow(Map<String, Object> row, BigDecimal[] result, int expectedColumnCount) {
		assertEquals(expectedColumnCount, row.keySet().size());
		assertEquals(result[0], row.get(BaseColumn.KEY_DATA_SOURCE_ID));
		assertEquals(result[1], row.get(ResultColumn.KEY_RESULT_ID));
	}

	public void assertStoret42(Map<String, Object> compareRow, Map<String, Object> resultRow) {
		assertMapIsAsExpected(compareRow, resultRow);
	}

	public void assertContainsResult(LinkedList<Map<String, Object>> results, BigDecimal[]...  resultIds) {
		for (Map<String, Object> result : results) {
			LOG.debug(ActivityColumn.KEY_DATA_SOURCE_ID + ":" + result.get(ActivityColumn.KEY_DATA_SOURCE_ID) + "/" + ResultColumn.KEY_RESULT_ID + ":" +  result.get(ResultColumn.KEY_RESULT_ID));
		}

		for (BigDecimal[] i : resultIds) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (i[0].compareTo(((BigDecimal) result.get(ActivityColumn.KEY_DATA_SOURCE_ID))) == 0
						&& i[1].compareTo(((BigDecimal) result.get(ResultColumn.KEY_RESULT_ID))) == 0) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(ActivityColumn.KEY_DATA_SOURCE_ID + ":" + i[0] + "/" + ResultColumn.KEY_RESULT_ID + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals("Double check expected size", results.size(), resultIds.length);
	}

}
