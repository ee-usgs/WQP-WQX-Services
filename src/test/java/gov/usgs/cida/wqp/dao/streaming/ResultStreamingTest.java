package gov.usgs.cida.wqp.dao.streaming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.ResultColumn;
import gov.usgs.cida.wqp.parameter.Parameters;

public abstract class ResultStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(ResultStreamingTest.class);

	@Autowired 
	protected IStreamingDao streamingDao;

	protected TestResultHandler handler;
	protected Map<String, Object> parms;

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
		parms = new HashMap<>();
	}

	@After
	public void cleanup() {
		handler = null;
		parms = null;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Only need Result (and possibly a lookup table)

	public void nullParameterTest(String nameSpace) {
		streamingDao.stream(nameSpace, null, handler);
		assertEquals(TOTAL_RESULT_COUNT, String.valueOf(handler.getResults().size()));
	}

	public void emptyParameterTest(String nameSpace) {
		streamingDao.stream(nameSpace, parms, handler);
		assertEquals(TOTAL_RESULT_COUNT, String.valueOf(handler.getResults().size()));
	}

	public void allDataSortedTest(String nameSpace, Integer expectedColumnCount, Map<String, Object> expectedMap) {
		parms.put(Parameters.SORTED.toString(), "yes");
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
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
		assertRow(results.get(8), STORET_41, expectedColumnCount);
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
		assertRow(results.get(21), STORET_27, expectedColumnCount);
		assertRow(results.get(22), STORET_28, expectedColumnCount);
		assertRow(results.get(23), STORET_29, expectedColumnCount);
		assertRow(results.get(24), STORET_30, expectedColumnCount);
		assertRow(results.get(25), STORET_31, expectedColumnCount);
		assertRow(results.get(26), STORET_32, expectedColumnCount);
		assertRow(results.get(27), STORET_33, expectedColumnCount);
		assertRow(results.get(28), STORET_34, expectedColumnCount);
		assertRow(results.get(29), STORET_35, expectedColumnCount);
		assertRow(results.get(30), STORET_36, expectedColumnCount);
		assertRow(results.get(31), STORET_37, expectedColumnCount);
		assertRow(results.get(32), STORET_38, expectedColumnCount);
		assertRow(results.get(33), STORET_39, expectedColumnCount);
		assertRow(results.get(34), STORET_40, expectedColumnCount);
		assertRow(results.get(35), STORET_4, expectedColumnCount);
		assertRow(results.get(36), STORET_5, expectedColumnCount);
		assertRow(results.get(37), STORET_6, expectedColumnCount);
		assertRow(results.get(38), STORET_7, expectedColumnCount);
		assertRow(results.get(39), STORET_8, expectedColumnCount);
		assertRow(results.get(40), STORET_9, expectedColumnCount);
		assertRow(results.get(41), STORET_10, expectedColumnCount);
		assertRow(results.get(42), STORET_11, expectedColumnCount);
		assertRow(results.get(43), STORET_12, expectedColumnCount);
		assertRow(results.get(44), STORET_13, expectedColumnCount);
		assertRow(results.get(45), STORET_14, expectedColumnCount);
		assertRow(results.get(46), STORET_45, expectedColumnCount);
		assertRow(results.get(47), STORET_46, expectedColumnCount);
		assertRow(results.get(48), STORET_47, expectedColumnCount);
		assertStoret42(expectedMap, results.get(49), expectedColumnCount);
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

	public void analyticalMethodTest(String nameSpace) {
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsResult(results, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_3, STORET_42, STORET_43, STORET_44,
				STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void assemblageTest(String nameSpace) {
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(14, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_3, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47,
				STORET_48, STORET_49, STORET_50, BIODATA_42);
	}

	public void avoidTest(String nameSpace) {
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(50, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20,
				STORET_21, STORET_22, STORET_23, STORET_24, STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30,
				STORET_31, STORET_32, STORET_33, STORET_34, STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40,
				STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void characteristicNameTest(String nameSpace) {
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(13, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_3, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47,
				STORET_48, STORET_49, STORET_50);
	}

	public void characteristicTypeTest(String nameSpace) {
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(14, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STORET_1, STORET_2, STORET_3, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46,
				STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void countryTest(String nameSpace) {
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(33, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50, BIODATA_42);
	}

	public void countyTest(String nameSpace) {
		parms.put(Parameters.COUNTY.toString(), getCounty());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(32, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50);
	}

	public void huc2Test(String nameSpace) {
		parms.put(Parameters.HUC.toString(), getHuc2());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(23, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_4, STORET_5,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_41,
				STORET_45, STORET_46, STORET_47);
	}

	public void huc4Test(String nameSpace) {
		parms.put(Parameters.HUC.toString(), getHuc4());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsResult(results, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8,
				STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14);
	}

	public void huc6Test(String nameSpace) {
		parms.put(Parameters.HUC.toString(), getHuc6());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(15, results.size());
		assertContainsResult(results, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14);
	}

	public void huc8Test(String nameSpace) {
		parms.put(Parameters.HUC.toString(), getHuc8());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsResult(results, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14);
	}

	public void nldiUrlTest(String nameSpace) {
		try {
			parms.put(Parameters.NLDIURL.toString(), getManySiteId());
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(19, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_42, STORET_43, STORET_44, STORET_48, STORET_49, STORET_50);
	}

	public void organizationTest(String nameSpace) {
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(32, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50);
	}

	public void projectTest(String nameSpace) {
		parms.put(Parameters.PROJECT.toString(), getProject());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(21, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50,
				BIODATA_42);
	}

	public void providersTest(String nameSpace) {
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(58, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22,
				STORET_23, STORET_24, STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32,
				STORET_33, STORET_34, STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_41, STORET_42,
				STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void sampleMediaTest(String nameSpace) {
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(50, results.size());
		assertContainsResult(results, STEWARDS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, STORET_17, STORET_18, STORET_19,
				STORET_20, STORET_21, STORET_22, STORET_23, STORET_24, STORET_25, STORET_26, STORET_27, STORET_28, STORET_29,
				STORET_30, STORET_31, STORET_32, STORET_33, STORET_34, STORET_35, STORET_36, STORET_37, STORET_38, STORET_39,
				STORET_40, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void startDateHiTest(String nameSpace) {
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(56, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22, STORET_23, STORET_24,
				STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32, STORET_33, STORET_34,
				STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45,
				STORET_46, STORET_47, STORET_48, STORET_49, STORET_50, BIODATA_42);
	}

	public void startDateLoTest(String nameSpace) {
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(22, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49,
				STORET_50, BIODATA_42);
	}

	public void siteIdTest(String nameSpace) {
		parms.put(Parameters.SITEID.toString(), getSiteid());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(21, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49,
				STORET_50);
	}

	public void manySitesTest(String nameSpace) {
		try {
			parms.put(Parameters.SITEID.toString(), getManySiteId());
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(19, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_42, STORET_43, STORET_44, STORET_48, STORET_49, STORET_50);
	}

	public void minActivitiesTest(String nameSpace) {
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(55, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_4, STORET_5,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15,
				STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22, STORET_23, STORET_24, STORET_25,
				STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32, STORET_33, STORET_34, STORET_35,
				STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_41, STORET_42, STORET_43, STORET_44,
				STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50);
	}

	public void minResultsTest(String nameSpace) {
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(53, results.size());
		assertContainsResult(results, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_4, STORET_5, STORET_6, STORET_7,
				STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, STORET_17,
				STORET_18, STORET_19, STORET_20, STORET_21, STORET_22, STORET_23, STORET_24, STORET_25, STORET_26, STORET_27,
				STORET_28, STORET_29, STORET_30, STORET_31, STORET_32, STORET_33, STORET_34, STORET_35, STORET_36, STORET_37,
				STORET_38, STORET_39, STORET_40, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47,
				STORET_48, STORET_49, STORET_50);
	}

	public void pcodeTest(String nameSpace) {
		parms.put(Parameters.PCODE.toString(), getPcode());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(12, results.size());
		assertContainsResult(results, NWIS_3, STORET_1, STORET_2, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50);
	}

	public void siteTypeTest(String nameSpace) {
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(58, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_4, NWIS_5, STORET_1, STORET_2, STORET_3,
				STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22, STORET_23,
				STORET_24, STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32, STORET_33,
				STORET_34, STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_41, STORET_42, STORET_43,
				STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49, STORET_50, BIODATA_42);
	}

	public void stateTest(String nameSpace) {
		parms.put(Parameters.STATE.toString(), getState());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(32, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50);
	}

	public void subjectTaxonomicNameTest(String nameSpace) {
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(13, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_3, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48,
				STORET_49, STORET_50, BIODATA_42);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Result + Station_Sum

	public void bboxTest(String nameSpace) {
		parms.put(Parameters.BBOX.toString(), getBBox());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(31, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_41, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49,
				STORET_50);
	}

	public void withinTest(String nameSpace) {
		parms.put(Parameters.WITHIN.toString(), getWithin());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(54, results.size());
		assertContainsResult(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, NWIS_4, NWIS_5, STORET_1, STORET_2,
				STORET_3, STORET_4, STORET_5, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_15, STORET_16, STORET_17, STORET_18, STORET_19, STORET_20, STORET_21, STORET_22,
				STORET_23, STORET_24, STORET_25, STORET_26, STORET_27, STORET_28, STORET_29, STORET_30, STORET_31, STORET_32,
				STORET_33, STORET_34, STORET_35, STORET_36, STORET_37, STORET_38, STORET_39, STORET_40, STORET_42, STORET_43,
				STORET_44, STORET_48, STORET_49, STORET_50);
	}


	public void multipleParameterResultTests(String nameSpace) {
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PCODE.toString(), getPcode());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_42, STORET_43, STORET_44, STORET_45, STORET_46, STORET_47, STORET_48, STORET_49,
				STORET_50);
	}

	public void multipleParameterResultStationSumTests(String nameSpace) {
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PCODE.toString(), getPcode());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		parms.put(Parameters.WITHIN.toString(), getWithin());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsResult(results, STORET_1, STORET_2, STORET_42, STORET_43, STORET_44, STORET_48, STORET_49, STORET_50);
	}

	public static void assertRow(Map<String, Object> row, BigDecimal[] result, int expectedColumnCount) {
		assertEquals(expectedColumnCount, row.keySet().size());
		assertEquals(result[0], row.get(BaseColumn.KEY_DATA_SOURCE_ID));
		assertEquals(result[1], row.get(ResultColumn.KEY_RESULT_ID));
	}

	public static void assertStoret42(Map<String, Object> compareRow, Map<String, Object> resultRow, int expectedColumnCount) {
		assertEquals("Expected column count", expectedColumnCount, resultRow.keySet().size());
		assertEquals("Map size comparison", compareRow.keySet().size(), resultRow.keySet().size());
		for (String i : compareRow.keySet()) {
			assertEquals(i, compareRow.get(i), resultRow.get(i));
		}
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
