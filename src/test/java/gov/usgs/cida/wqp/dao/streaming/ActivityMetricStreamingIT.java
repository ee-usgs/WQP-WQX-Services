package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.dao.FilteredActivityMetricDaoTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.StreamingDao;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.ActivityMetricColumn;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.TestActivityMetricMap;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class ActivityMetricStreamingIT extends FilteredActivityMetricDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(ActivityMetricStreamingIT.class);

	@Autowired 
	IStreamingDao streamingDao;

	NameSpace nameSpace = NameSpace.ACTIVITY_METRIC;

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

	public static final int ACTIVITY_METRIC_COLUMN_COUNT = TestActivityMetricMap.ACTIVITY_METRIC.keySet().size();

	@Test
	public void testHarness() {
		activityTest();
		analyticalMethodTest();
		assemblageTest();
		avoidTest();
		bboxTest();
		characteristicNameTest();
		characteristicTypeTest();
		countryTest();
		countyTest();
		emptyParameterTest();
		huc2Test();
		huc3Test();
		huc4Test();
		huc5Test();
		huc6Test();
		huc7Test();
		huc8Test();
		huc10Test();
		huc12Test();
		mimeTypeTest();
		minActivitiesTest();
		minResultsTest();
		nldiSitesTest();
		nldiUrlTest();
		nullParameterTest();
		organizationTest();
		pcodeTest();
		projectTest();
		providersTest();
		restTest();
		resultTest();
		sampleMediaTest();
		siteIdTest();
		siteIdLargeListTest();
		siteTypeTest();
		siteUrlBaseTest();
		sortedTest();
		startDateHiTest();
		startDateLoTest();
		stateTest();
		subjectTaxonomicNameTest();
		withinTest();
		zipTest();
		multipleParameterStationSumTest();
		multipleParameterActivityTest();
		multipleParameterActivitySumTest();
		multipleParameterResultSumTest();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Only need Activity (and possibly a lookup table)

	public void activityTest() {
		List<Map<String, Object>> results = activityTest(nameSpace, 6);
		assertContainsActivityMetric(results, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	public void avoidTest() {
		List<Map<String, Object>> results = avoidTest(nameSpace, Integer.valueOf(STORET_ACTIVITY_METRIC_COUNT));
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F,
				STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15,
				STORET_16);
	}

	public void countryTest() {
		List<Map<String, Object>> results = countryTest(nameSpace, 24);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void countyTest() {
		List<Map<String, Object>> results = countyTest(nameSpace, 23);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16);
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
	}

	public void huc2Test() {
		List<Map<String, Object>> results = huc2Test(nameSpace, 16);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C,
				STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_14, STORET_15);
	}

	public void huc3Test() {
		List<Map<String, Object>> results = huc3Test(nameSpace, 16);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C,
				STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_14, STORET_15);
	}

	public void huc4Test() {
		List<Map<String, Object>> results = huc4Test(nameSpace, 10);
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	public void huc5Test() {
		List<Map<String, Object>> results = huc5Test(nameSpace, 10);
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, NWIS_3, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	public void huc6Test() {
		List<Map<String, Object>> results = huc6Test(nameSpace, 9);
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	public void huc7Test() {
		List<Map<String, Object>> results = huc7Test(nameSpace, 9);
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	public void huc8Test() {
		List<Map<String, Object>> results = huc8Test(nameSpace, 7);
		assertContainsActivityMetric(results, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	public void huc10Test() {
		List<Map<String, Object>> results = huc10Test(nameSpace, 7);
		assertContainsActivityMetric(results, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	public void huc12Test() {
		List<Map<String, Object>> results = huc12Test(nameSpace, 7);
		assertContainsActivityMetric(results, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	public void mimeTypeTest() {
		mimeTypeJsonTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
		mimeTypeGeoJsonTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
		mimeTypeKmlTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
		mimeTypeKmzTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
		mimeTypeCsvTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
		mimeTypeTsvTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
		mimeTypeXmlTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
		mimeTypeXlsxTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
	}

	public void nldiUrlTest() {
		nldiUrlTest(nameSpace,  Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
	}

	public void nldiSitesTest() {
		List<Map<String, Object>> results = nldiSitesTest(nameSpace, 13);
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_11,
				STORET_12, STORET_13, STORET_16);
	}

	public void nullParameterTest() {
		nullParameterTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
	}

	public void organizationTest() {
		List<Map<String, Object>> results = organizationTest(nameSpace, 23);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16);
	}

	public void projectTest() {
		List<Map<String, Object>> results = projectTest(nameSpace, 14);
		assertContainsActivityMetric(results, STEWARDS_1,  STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void providersTest() {
		List<Map<String, Object>> results = providersTest(nameSpace, 27);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void restTest() {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(getRestProviders());
		filter.setOrganization(getRestOrganizations());
		filter.setActivity(getActivity());
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		List<Map<String, Object>> results = callDao(nameSpace, 6, filter);
		assertContainsActivityMetric(results, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F);
	}

	public void resultTest() {
		resultTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
	}

	public void sampleMediaTest() {
		List<Map<String, Object>> results = sampleMediaTest(nameSpace, 26);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A, STORET_5B,
				STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void siteUrlBaseTest() {
		siteUrlBaseTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
	}

	public void sortedTest() {
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
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

	public void startDateHiTest() {
		List<Map<String, Object>> results = startDateHiTest(nameSpace, 26);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A, STORET_5B,
				STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11,
				STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void startDateLoTest() {
		List<Map<String, Object>> results = startDateLoTest(nameSpace, 17);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1, BIODATA_1);
	}

	public void siteIdTest() {
		List<Map<String, Object>> results = siteIdTest(nameSpace, 16);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void siteIdLargeListTest() {
		List<Map<String, Object>> results = siteIdLargeListTest(nameSpace, 13);
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_11,
				STORET_12, STORET_13, STORET_16);
	}

	public void siteTypeTest() {
		List<Map<String, Object>> results = siteTypeTest(nameSpace, 27);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_4, STORET_5A,
				STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10,
				STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_1, BIODATA_1);
	}

	public void stateTest() {
		List<Map<String, Object>> results = stateTest(nameSpace, 23);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13,
				STORET_14, STORET_15, STORET_1);
	}

	public void zipTest() {
		zipTest(nameSpace, Integer.valueOf(TOTAL_ACTIVITY_METRIC_COUNT));
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Activity_Sum

	public void minActivitiesTest() {
		List<Map<String, Object>> results = minActivitiesTest(nameSpace, 24);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C,
				STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12,
				STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void minResultsTest() {
		List<Map<String, Object>> results = minResultsTest(nameSpace, 22);
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_4, STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E,
				STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Station_Sum

	public void bboxTest() {
		List<Map<String, Object>> results = bboxTest(nameSpace, 22);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_4, STORET_5A,
				STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	public void withinTest() {
		List<Map<String, Object>> results = withinTest(nameSpace, 24);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STEWARDS_3, NWIS_1, NWIS_2, NWIS_3, STORET_1, STORET_2, STORET_3, STORET_4,
				STORET_5A, STORET_5B, STORET_5C, STORET_5D, STORET_5E, STORET_5F, STORET_6, STORET_7, STORET_8, STORET_9,
				STORET_11, STORET_12, STORET_13, STORET_16);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Activity + Result_Sum

	public void analyticalMethodTest() {
		List<Map<String, Object>> results = analyticalMethodTest(nameSpace, 12);
		assertContainsActivityMetric(results, NWIS_1, NWIS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	public void assemblageTest() {
		List<Map<String, Object>> results = assemblageTest(nameSpace, 11);
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void characteristicNameTest() {
		List<Map<String, Object>> results = characteristicNameTest(nameSpace, 10);
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void characteristicTypeTest() {
		List<Map<String, Object>> results = characteristicTypeTest(nameSpace, 12);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_2, STORET_1, STORET_2, STORET_3, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	public void pcodeTest() {
		List<Map<String, Object>> results = pcodeTest(nameSpace, 10);
		assertContainsActivityMetric(results, NWIS_3, STORET_1, STORET_2, STORET_10, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16);
	}

	public void subjectTaxonomicNameTest() {
		List<Map<String, Object>> results = subjectTaxonomicNameTest(nameSpace, 10);
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14, STORET_15, STORET_16, BIODATA_1);
	}

	public void multipleParameterActivityTest() {
		List<Map<String, Object>> results = multipleParameterActivityTest(nameSpace, 12);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, STORET_1, STORET_2, STORET_3, STORET_11, STORET_12, STORET_13, STORET_14,
				STORET_15, STORET_16);
	}

	public void multipleParameterStationSumTest() {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 9);
		assertContainsActivityMetric(results, STEWARDS_1, STEWARDS_3, NWIS_1, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	public void multipleParameterActivitySumTest() {
		List<Map<String, Object>> results = multipleParameterActivitySumTest(nameSpace, 6);
		assertContainsActivityMetric(results, STORET_1, STORET_2, STORET_11, STORET_12, STORET_13, STORET_16);
	}

	public void multipleParameterResultSumTest() {
		List<Map<String, Object>> results = multipleParameterResultSumTest(nameSpace, 2);
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

	public void assertStoret2(Map<String, Object> row) {
		assertMapIsAsExpected(TestActivityMetricMap.ACTIVITY_METRIC, row);
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

	public void assertContainsActivityMetric(List<Map<String, Object>> results, String[]...  activityMetrics) {
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
		assertEquals(activityMetrics.length, results.size(),"Double check expected size");
	}

	@Override
	public LinkedList<Map<String, Object>> callDao(NameSpace nameSpace, int expectedSize, FilterParameters filter) {
		TestResultHandler handler = new TestResultHandler();
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(expectedSize, handler.getResults().size());
		return handler.getResults();
	}

	@Override
	protected void assertSiteUrlBase(Map<String, Object> row) {
		//Nothing to do for this one.
	}

}
