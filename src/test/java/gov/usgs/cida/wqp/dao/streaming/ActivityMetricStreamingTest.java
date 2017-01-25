package gov.usgs.cida.wqp.dao.streaming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.dao.BaseDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ActivityMetricColumn;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.parameter.Parameters;

@Category(DBIntegrationTest.class)
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ActivityMetricStreamingTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(ActivityMetricStreamingTest.class);

	@Autowired 
	IStreamingDao streamingDao;

	TestResultHandler handler;
	Map<String, Object> parms;
	String nameSpace = BaseDao.ACTIVITY_METRIC_NAMESPACE;

	public static final String[] STEWARDS_1 = new String[]{STEWARDS, "1_1_type_identifier"};
	public static final String[] STEWARDS_2 = new String[]{STEWARDS, "1_2_type_identifier"};
	public static final String[] STEWARDS_3 = new String[]{STEWARDS, "1_3_type_identifier"};
	public static final String[] NWIS_1 = new String[]{NWIS, "2_1_type_identifier"};
	public static final String[] NWIS_2 = new String[]{NWIS, "2_2_type_identifier"};
	public static final String[] NWIS_3 = new String[]{NWIS, "2_3_type_identifier"};
	public static final String[] STORET_1 = new String[]{STORET, "3_1_type_identifier"};
	public static final String[] STORET_2 = new String[]{STORET, "3_2_type_identifier"};
	public static final String[] STORET_3 = new String[]{STORET, "3_3_type_identifier"};
	public static final String[] STORET_4 = new String[]{STORET, "GENERA RICHNESS"};
	public static final String[] STORET_5A = new String[]{STORET, "% EPT Individuals"};
	public static final String[] STORET_5B = new String[]{STORET, "% EPT Taxa"};
	public static final String[] STORET_5C = new String[]{STORET, "CHI %"};
	public static final String[] STORET_5D = new String[]{STORET, "GATHERER %"};
	public static final String[] STORET_5E = new String[]{STORET, "MPTV"};
	public static final String[] STORET_5F = new String[]{STORET, "SHREDDER %"};
	public static final String[] STORET_6 = new String[]{STORET, "3_6_type_identifier"};
	public static final String[] STORET_7 = new String[]{STORET, "3_7_type_identifier"};
	public static final String[] STORET_8 = new String[]{STORET, "3_8_type_identifier"};
	public static final String[] STORET_9 = new String[]{STORET, "3_9_type_identifier"};
	public static final String[] STORET_10 = new String[]{STORET, "3_10_type_identifier"};
	public static final String[] STORET_11 = new String[]{STORET, "3_11_type_identifier"};
	public static final String[] STORET_12 = new String[]{STORET, "3_12_type_identifier"};
	public static final String[] STORET_13 = new String[]{STORET, "3_13_type_identifier"};
	public static final String[] STORET_14 = new String[]{STORET, "3_14_type_identifier"};
	public static final String[] STORET_15 = new String[]{STORET, "3_15_type_identifier"};
	public static final String[] STORET_16 = new String[]{STORET, "3_16_type_identifier"};
	public static final String[] BIODATA_1 = new String[]{BIODATA, "4_1_type_identifier"};

	public static final int ACTIVITY_METRIC_COLUMN_COUNT = 21;

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
	//Only need Activity (and possibly a lookup table)

	@Test
	public void nullParameterTest() {
		streamingDao.stream(nameSpace, null, handler);
		assertEquals(TOTAL_ACTIVITY_METRIC_COUNT, String.valueOf(handler.getResults().size()));
	}

	@Test
	public void emptyParameterTest() {
		streamingDao.stream(nameSpace, parms, handler);
		assertEquals(TOTAL_ACTIVITY_METRIC_COUNT, String.valueOf(handler.getResults().size()));
	}

	@Test
	public void allDataSortedTest() {
		parms.put(Parameters.SORTED.toString(), "yes");
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		//Validate the number AND order of results.
		assertEquals(TOTAL_ACTIVITY_METRIC_COUNT, String.valueOf(results.size()));
		assertStewards1(results.get(0));
		assertStewards2(results.get(1));
		assertStewards3(results.get(2));
		assertNwis1(results.get(3));
		assertNwis2(results.get(4));
		assertNwis3(results.get(5));
		assertStoret14(results.get(6));
		assertStoret15(results.get(7));
		assertStoret10(results.get(8));
		assertStoret6(results.get(9));
		assertStoret7(results.get(10));
		assertStoret9(results.get(11));
		assertStoret8(results.get(12));
		assertStoret5A(results.get(13));
		assertStoret5B(results.get(14));
		assertStoret5C(results.get(15));
		assertStoret5D(results.get(16));
		assertStoret5E(results.get(17));
		assertStoret5F(results.get(18));
		assertStoret4(results.get(19));
		assertStoret11(results.get(20));
		assertStoret12(results.get(21));
		assertStoret13(results.get(22));
		assertStoret2(results.get(23));
		assertStoret16(results.get(24));
		assertStoret1(results.get(25));
		assertStoret3(results.get(26));
		assertBiodata1(results.get(27));
	}

	@Test
	public void avoidTest() {
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(21, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15,
				STORET_16);
	}

	@Test
	public void countryTest() {
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(24, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void countyTest() {
		parms.put(Parameters.COUNTY.toString(), getCounty());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(23, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void huc2Test() {
		parms.put(Parameters.HUC.toString(), getHuc2());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C,
				STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_14, STORET_15);
	}

	@Test
	public void huc4Test() {
		parms.put(Parameters.HUC.toString(), getHuc4());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	@Test
	public void huc6Test() {
		parms.put(Parameters.HUC.toString(), getHuc6());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(9, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	@Test
	public void huc8Test() {
		parms.put(Parameters.HUC.toString(), getHuc8());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(7, results.size());
		assertContainsActivityMetric(results, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	@Test
	public void nldiUrlTest() {
		try {
			parms.put(Parameters.NLDIURL.toString(), getManySiteId());
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(13, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_11,
				STORET_12, STORET_13, STORET_16);
	}

	@Test
	public void organizationTest() {
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(23, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void projectTest() {
		parms.put(Parameters.PROJECT.toString(), getProject());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(14, results.size());
		assertContainsActivityMetric(results, STEWARDS_1,  STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void providersTest() {
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(27, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void sampleMediaTest() {
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(26, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A, STORET_5B,
				STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void startDateHiTest() {
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(26, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A, STORET_5B,
				STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void startDateLoTest() {
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(17, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1, BIODATA_1);
	}

	@Test
	public void siteIdTest() {
		parms.put(Parameters.SITEID.toString(), getSiteid());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(16, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void manySitesTest() {
		try {
			parms.put(Parameters.SITEID.toString(), getManySiteId());
			streamingDao.stream(nameSpace, parms, handler);
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(13, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_11,
				STORET_12, STORET_13, STORET_16);
	}

	@Test
	public void siteTypeTest() {
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(27, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A,
				STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1, BIODATA_1);
	}

	@Test
	public void stateTest() {
		parms.put(Parameters.STATE.toString(), getState());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(23, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_1);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Activity_Sum

	@Test
	public void minActivitiesTest() {
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(24, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C,
				STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void minResultsTest() {
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(22, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E,
				STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Station_Sum

	@Test
	public void bboxTest() {
		parms.put(Parameters.BBOX.toString(), getBBox());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(22, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_4, STORET_5A,
				STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	@Test
	public void withinTest() {
		parms.put(Parameters.WITHIN.toString(), getWithin());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(24, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_11, STORET_12, STORET_13, STORET_16);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Result_Sum

	@Test
	public void analyticalMethodTest() {
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(12, results.size());
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	@Test
	public void assemblageTest() {
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void characteristicNameTest() {
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void characteristicTypeTest() {
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(12, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	@Test
	public void pcodeTest() {
		parms.put(Parameters.PCODE.toString(), getPcode());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivityMetric(results, NWIS_3, STORET_1, STORET_2, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void subjectTaxonomicNameTest() {
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(10, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	@Test
	public void multipleParameterActivityTests() {
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(11, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, NWIS_1, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15,
				STORET_16);
	}

	@Test
	public void multipleParameterStationSumTests() {
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		parms.put(Parameters.WITHIN.toString(), getWithin());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsActivityMetric(results, STEWARDS_1, NWIS_1, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	@Test
	public void multipleParameterActivitySumTests() {
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(8, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void multipleParameterActivitySumStationSumTests() {
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		parms.put(Parameters.WITHIN.toString(), getWithin());
		streamingDao.stream(nameSpace, parms, handler);
		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(6, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	@Test
	public void multipleParameterResultSumTests() {
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.NLDIURL.toString(), getNldiSites());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.PCODE.toString(), getPcode());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		streamingDao.stream(nameSpace, parms, handler);

		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(4, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_14, STORET_15, STORET_16);
	}

	@Test
	public void multipleParameterResultSumStationSumTests() {
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
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
		parms.put(Parameters.NLDIURL.toString(), getNldiSites());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PCODE.toString(), getPcode());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		parms.put(Parameters.WITHIN.toString(), getWithin());
		streamingDao.stream(nameSpace, parms, handler);
		LinkedList<Map<String, Object>> results = handler.getResults();
		assertEquals(2, results.size());
		assertContainsActivityMetric(results, STORET_1, STORET_16);
	}

	public static void assertStewards1(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STEWARDS_1[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStewards2(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STEWARDS_2[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStewards3(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STEWARDS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STEWARDS_3[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertNwis1(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(NWIS_1[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertNwis2(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(NWIS_2[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertNwis3(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(NWIS, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(NWIS_3[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret1(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_1[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret2(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		for (String i : TestActivityMetricMap.ACTIVITY_METRIC.keySet()) {
			assertEquals(i, TestActivityMetricMap.ACTIVITY_METRIC.get(i), row.get(i));
		}
	}

	public static void assertStoret3(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_3[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret4(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_4[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5A(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5A[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5B(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5B[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5C(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5C[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5D(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5D[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5E(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5E[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret5F(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_5F[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret6(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_6[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret7(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_7[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret8(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_8[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret9(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_9[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret10(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_10[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret11(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_11[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret12(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_12[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret13(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_13[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret14(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_14[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret15(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_15[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertStoret16(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(STORET, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(STORET_16[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public static void assertBiodata1(Map<String, Object> row) {
		assertEquals(ACTIVITY_METRIC_COLUMN_COUNT, row.keySet().size());
		assertEquals(BIODATA, row.get(BaseColumn.KEY_DATA_SOURCE));
		assertEquals(BIODATA_1[1], row.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
	}

	public void assertContainsActivityMetric(LinkedList<Map<String, Object>> results, String[]...  activityMetrics) {
		for (Map<String, Object> result : results) {
			LOG.debug(BaseColumn.KEY_DATA_SOURCE + ":" + result.get(BaseColumn.KEY_DATA_SOURCE) + "/" 
						+ ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER + ":" +  result.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER));
		}

		for (String[] i : activityMetrics) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(BaseColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(BaseColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(result.get(ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER).toString())) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(BaseColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + ActivityMetricColumn.KEY_METRIC_TYPE_IDENTIFIER + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals("Double check expected size", results.size(), activityMetrics.length);
	}

}
