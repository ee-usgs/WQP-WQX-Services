package gov.usgs.cida.wqp.dao.streaming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.BaseDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.parameter.Parameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/dao/activity/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ActivityStreamingTest extends BaseSpringTest {

	@Autowired 
	IStreamingDao streamingDao;

	TestResultHandler handler;
	Map<String, Object> parms;
	String nameSpace = BaseDao.ACTIVITY_NAMESPACE;

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

	@Test
	public void allDataTest() {
		parms.put(Parameters.SORTED.toString(), "yes");
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertActivityId7772(results.get(0));
		assertActivityId7773(results.get(1));
		assertActivityId31827020(results.get(2));
		assertActivityId31835262(results.get(3));
		assertActivityId31827018(results.get(4));
		assertActivityId31827017(results.get(5));
		assertActivityId199206(results.get(6));
		assertActivityId13199(results.get(7));
		assertActivityId777(results.get(8));
	}

	@Test
	public void bboxTest() {
		parms.put(Parameters.BBOX.toString(), new String[]{"-89", "43", "-89", "44"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void countryTest() {
		parms.put(Parameters.COUNTRY.toString(), new String[]{"US"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsActivity(results, 7772, 7773, 13199, 199206);
	}

	@Test
	public void countyTest() {
		parms.put(Parameters.COUNTY.toString(), new String[]{"US:55:027"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void huc2Test() {
		parms.put(Parameters.HUC.toString(), new String[]{"07"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsActivity(results, 7772, 7773, 13199, 199206);
	}

	@Test
	public void huc4Test() {
		parms.put(Parameters.HUC.toString(), new String[]{"0709"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsActivity(results, 7773, 13199, 199206);
	}

	@Test
	public void huc6Test() {
		parms.put(Parameters.HUC.toString(), new String[]{"070900"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsActivity(results, 7773, 13199, 199206);
	}

	@Test
	public void huc8Test() {
		parms.put(Parameters.HUC.toString(), new String[]{"07090001"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	//TODO	@Test
	public void minResultsTest() {
		parms.put(Parameters.MIN_RESULTS.toString(), "230");
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 31827020, 31835262);
	}

	@Test
	public void nldiUrlTest() {
		try {
			parms.put(Parameters.NLDIURL.toString(), getSourceFile("manySites.txt").split(","));
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void organizationTest() {
		parms.put(Parameters.ORGANIZATION.toString(), new String[]{"WIDNR_WQX"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void providersTest() {
		parms.put(Parameters.PROVIDERS.toString(), new String[]{"STEWARDS"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsActivity(results, 7772);

		parms.put(Parameters.PROVIDERS.toString(), new String[]{"STORET"});
		streamingDao.stream(nameSpace, parms, handler);

		results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsActivity(results, 777, 7772, 13199, 199206, 31827020, 31835262, 31827017, 31827018);
	}

	@Test
	public void siteIdTest() {
		parms.put(Parameters.SITEID.toString(), new String[]{"WIDNR_WQX-113086"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void manySitesTest() {
		try {
			parms.put(Parameters.SITEID.toString(), getSourceFile("manySites.txt").split(","));
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void siteTypeTest() {
		parms.put(Parameters.SITE_TYPE.toString(), new String[]{"Stream"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsActivity(results, 7773, 13199, 199206);
	}

	@Test
	public void stateTest() {
		parms.put(Parameters.STATE.toString(), new String[]{"US:55"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsActivity(results, 7773, 13199, 199206);
	}

	@Test
	public void withinTest() {
		parms.put(Parameters.WITHIN.toString(), new String[]{"10"});
		parms.put(Parameters.LATITUDE.toString(), new String[]{"43.3836014"});
		parms.put(Parameters.LONGITUDE.toString(), new String[]{"-88.9773314"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(3, results.size());
		assertContainsActivity(results, 7773, 13199, 199206);
	}

	//TODO	@Test
	public void analyticalMethodTest() {
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), new String[]{"https://www.nemi.gov/methods/method_summary/8896/"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	//TODO	@Test
	public void assemblageTest() {
		parms.put(Parameters.ASSEMBLAGE.toString(), new String[]{"Fish/Nekton"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	//TODO	@Test
	public void characteristicNameTest() {
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), new String[]{"Nitrate"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	//TODO	@Test
	public void characteristicTypeTest() {
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), new String[]{"Nutrient"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	//TODO	@Test
	public void pcodeTest() {
		parms.put(Parameters.PCODE.toString(), new String[]{"00004"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void projectTest() {
		parms.put(Parameters.PROJECT.toString(), new String[]{"Lake-BaselineMonitoringDNR"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsActivity(results, 199206);
	}

	@Test
	public void sampleMediaTest() {
		parms.put(Parameters.SAMPLE_MEDIA.toString(), new String[]{"Water"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void startDateHiTest() {
		parms.put(Parameters.START_DATE_HI.toString(), new String[]{"09-09-1995"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void startDateLoTest() {
		parms.put(Parameters.START_DATE_LO.toString(), new String[]{"10-10-1994"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsActivity(results, 777, 7772, 7773, 31827020, 31835262, 31827017, 31827018);
	}

	//TODO	@Test
	public void subjectTaxonomicNameTest() {
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), new String[]{"Acipenser"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivity(results, 13199, 199206);
	}

	@Test
	public void multipleParameterTests() {
		//TODO		parms.put(Parameters.ANALYTICAL_METHOD.toString(), new String[]{"https://www.nemi.gov/methods/method_summary/4665/",
		//TODO		"https://www.nemi.gov/methods/method_summary/8896/"});
		parms.put(Parameters.BBOX.toString(), new String[]{"-90", "43", "-88", "44"});
		//TODO		parms.put(Parameters.ASSEMBLAGE.toString(), new String[]{"Fish/Nekton", "Benthic Macroinvertebrate"});
		//TODO		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), new String[]{"Beryllium", "Nitrate"});
		//TODO		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), new String[]{"Inorganics, Minor, Metals", "Nutrient"});
		parms.put(Parameters.COUNTRY.toString(), new String[]{"MX", "US", "XX"});
		parms.put(Parameters.COUNTY.toString(), new String[]{"XX:44:555", "US:30:003", "US:55:017", "US:55:021", "US:55:027"});
		parms.put(Parameters.HUC.toString(), new String[]{"0000", "07","0708","070801","07090002", "07080105"});
		parms.put(Parameters.LATITUDE.toString(), new String[]{"43.3836014"});
		parms.put(Parameters.LONGITUDE.toString(), new String[]{"-88.9773314"});
		//TODO		parms.put(Parameters.MIN_RESULTS.toString(), "3");
		parms.put(Parameters.ORGANIZATION.toString(), new String[]{"organization", "ARS", "11NPSWRD", "USGS-WI", "WIDNR_WQX"});
		parms.put(Parameters.PROJECT.toString(), new String[]{"projectId", "NAWQA", "CEAP", "EPABEACH"});
		parms.put(Parameters.PROVIDERS.toString(), new String[]{"NWIS", "STEWARDS", "STORET"});
		parms.put(Parameters.SITEID.toString(), new String[]{"organization-siteId", "11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700",
			"USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410"});
		parms.put(Parameters.SITE_TYPE.toString(), new String[]{"siteType", "Lake, Reservoir, Impoundment", "Land", "Stream", "Well"});
		parms.put(Parameters.STATE.toString(), new String[]{"XX:44", "US:19", "US:30", "US:55"});
		//TODO		parms.put(Parameters.PCODE.toString(), new String[]{"00032", "00004"});
		parms.put(Parameters.SAMPLE_MEDIA.toString(), new String[]{"sampleMedia", "Other", "Sediment", "Water"});
		parms.put(Parameters.START_DATE_HI.toString(), new String[]{"10-11-2012"});
		parms.put(Parameters.START_DATE_LO.toString(), new String[]{"10-11-1998"});
		//TODO		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), new String[]{"Acipenser", "Lota lota"});
		parms.put(Parameters.WITHIN.toString(), new String[]{"1000"});
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		assertContainsActivity(results, 777);
	}

	public static void assertActivityId13199(Map<String, Object> row) {
		assertEquals(78, row.keySet().size());
		assertTrue(row.containsKey(ActivityColumn.KEY_ACTIVITY));
		assertEquals("WIDNR_WQX-7788480", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertActivityId199206(Map<String, Object> row) {
		assertEquals(78, row.keySet().size());
		assertTrue(row.containsKey(ActivityColumn.KEY_ACTIVITY));
		assertEquals("WIDNR_WQX-7788475", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertActivityId31827020(Map<String, Object> row) {
		assertEquals(78, row.keySet().size());
		assertTrue(row.containsKey(ActivityColumn.KEY_ACTIVITY));
		assertEquals("21NYDECA_WQX-020002", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertActivityId31835262(Map<String, Object> row) {
		assertEquals(78, row.keySet().size());
		assertTrue(row.containsKey(ActivityColumn.KEY_ACTIVITY));
		assertEquals("21NYDECA_WQX-020206", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertActivityId31827017(Map<String, Object> row) {
		assertEquals(78, row.keySet().size());
		assertTrue(row.containsKey(ActivityColumn.KEY_ACTIVITY));
		assertEquals("21NYDECA_WQX-0210EN", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertActivityId31827018(Map<String, Object> row) {
		assertEquals(78, row.keySet().size());
		assertTrue(row.containsKey(ActivityColumn.KEY_ACTIVITY));
		assertEquals("21NYDECA_WQX-020610", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertActivityId777(Map<String, Object> row) {
		assertEquals(78, row.keySet().size());
		for (String i : TestActivityMap.ACTIVITY.keySet()) {
			assertEquals(TestActivityMap.ACTIVITY.get(i), row.get(i));
		}
	}

	public static void assertActivityId7772(Map<String, Object> row) {
		assertEquals(78, row.keySet().size());
		assertTrue(row.containsKey(ActivityColumn.KEY_ACTIVITY));
		assertEquals("activityStewards", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public static void assertActivityId7773(Map<String, Object> row) {
		assertEquals(78, row.keySet().size());
		assertTrue(row.containsKey(ActivityColumn.KEY_ACTIVITY));
		assertEquals("activityNwis", row.get(ActivityColumn.KEY_ACTIVITY));
	}

	public void assertContainsActivity(LinkedList<Map<String, Object>> results, Integer...  activityIds) {
		for (Integer i : activityIds) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (i.compareTo(((BigDecimal) result.get(ActivityColumn.KEY_ACTIVITY_ID)).intValue()) == 0) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(ActivityColumn.KEY_ACTIVITY_ID + " " + i + " was not in the result set.");
			}
		}
	}

}
