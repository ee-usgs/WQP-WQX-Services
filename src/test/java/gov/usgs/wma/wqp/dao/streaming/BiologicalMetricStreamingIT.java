package gov.usgs.wma.wqp.dao.streaming;

import static gov.usgs.wma.wqp.openapi.model.StationCountJson.BIODATA;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.NWIS;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.STEWARDS;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.STORET;
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

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.wma.wqp.CsvDataSetLoader;
import gov.usgs.wma.wqp.dao.FilteredProjectDaoTest;
import gov.usgs.wma.wqp.dao.NameSpace;
import gov.usgs.wma.wqp.dao.StreamingDao;
import gov.usgs.wma.wqp.dao.intfc.IStreamingDao;
import gov.usgs.wma.wqp.mapping.BiologicalMetricColumn;
import gov.usgs.wma.wqp.mapping.ProjectColumn;
import gov.usgs.wma.wqp.mapping.TestResultHandler;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.springinit.DBTestConfig;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.NONE,
	classes={DBTestConfig.class, StreamingDao.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class BiologicalMetricStreamingIT extends FilteredProjectDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(ProjectStreamingIT.class);

	@Autowired
	IStreamingDao streamingDao;

	protected NameSpace nameSpace = NameSpace.BIOLOGICAL_METRIC;

	public static final String [] BIODATA_USGS_11421000 = new String[] {BIODATA, "PHB:OWW04440-0359:1:PCT_SLOW", "USGS"};
	public static final String [] BIODATA_USGS_433830088977331 = new String[] {BIODATA, "PHB:OWW04440-0359:1:PCT_FAST", "USGS"};
	public static final String [] STORET_ORGANIZATION_SITE_ID3 = new String[] {STORET, "RM:OWW04440-0298:040825:RR:EPIF_SUB", "organization"};
	public static final String [] STORET_WIDNR_WQX_113086 = new String[] {STORET, "RM:OWW04440-0298:040825:RR:FLOWTYPE", "WIDNR_WQX"};
	public static final String [] STORET_WIDNR_WQX_10030952 = new String[] {STORET, "PHB:OWW04440-0359:1:PCT_SIDE", "WIDNR_WQX"};
	public static final String [] STORET_21NYDECA_WQX_ONTARIO_02 = new String[] {STORET, "PHB:OWW04440-0359:1:PCT_DRS", "21NYDECA_WQX"};
	public static final String [] STORET_11NPSWRD_BICA_MFG_B = new String[] {STORET, "PHB:OWW04440-0359:1:PCT_POOL", "11NPSWRD"};
	public static final String [] STORET_ORGANIZATION_SITE_ID2 = new String[] {STORET, "RM:OWW04440-0298:040825:RR:CHAN_FLS", "organization"};
	public static final String [] STORET_ORGANIZATION_SITE_ID = new String[] {STORET, "RM:OWW04440-0298:040825:RR:SEDI_DEP", "organization"};
	public static final String [] NWIS_USGS_05425700 = new String[] {NWIS, "RM:OWW04440-0298:040825:RR:BANK_STL", "USGS-WI"};
	public static final String [] NWIS_USGS_431925089002701 = new String[] {NWIS, "RM:OWW04440-0298:040825:RR:CHAN_ALT", "USGS-WI"};
	public static final String [] STEWARDS_ARS_IAWC_IAWC410 = new String[] {STEWARDS, "RM:OWW04440-0298:040825:RR:VEG_PROL", "ARS"};
	public static final String [] STEWARDS_ARS_IAWC_IAWC225 = new String[] {STEWARDS, "RM:OWW04440-0298:040825:RR:BANK_STR", "ARS"};

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
		multipleParameterTest();
		multipleParameterActivitySumTest();
		multipleParameterResultSumTest();
	}

	public void activityTest() {
		activityTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
	}

	public void avoidTest() {
		List<Map<String, Object>> results = avoidTest(nameSpace, Integer.valueOf(STORET_BIOLOGICAL_METRIC_COUNT));
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_21NYDECA_WQX_ONTARIO_02,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID
		);
	}

	public void emptyParameterTest() {
		emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
	}

	public void mimeTypeTest() {
		mimeTypeJsonTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
		mimeTypeGeoJsonTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
		mimeTypeKmlTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
		mimeTypeKmzTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
		mimeTypeCsvTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
		mimeTypeTsvTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
		mimeTypeXmlTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
		mimeTypeXlsxTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
	}

	public void nldiUrlTest() {
		nldiUrlTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
	}

	public void nullParameterTest() {
		nullParameterTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
	}

	public void organizationTest() {
		List<Map<String, Object>> results = organizationTest(nameSpace, 10);
		assertContainsBiologicalMetric(results,
				STEWARDS_ARS_IAWC_IAWC410,
				STEWARDS_ARS_IAWC_IAWC225,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_11NPSWRD_BICA_MFG_B
		);
	}

	public void providersTest() {
		List<Map<String, Object>> results = providersTest(nameSpace, 11);
		assertContainsBiologicalMetric(results,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701,
				STEWARDS_ARS_IAWC_IAWC410,
				STEWARDS_ARS_IAWC_IAWC225,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_21NYDECA_WQX_ONTARIO_02,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID
		);
	}

	public void projectTest() {
		List<Map<String, Object>> results = projectTest(nameSpace, 9);
		assertContainsBiologicalMetric(results,
				NWIS_USGS_431925089002701,
				STORET_ORGANIZATION_SITE_ID2,
				STEWARDS_ARS_IAWC_IAWC225,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_11NPSWRD_BICA_MFG_B,
				BIODATA_USGS_11421000,
				STEWARDS_ARS_IAWC_IAWC410,
				NWIS_USGS_05425700,
				STORET_ORGANIZATION_SITE_ID
		);
	}

	public void resultTest() {
		resultTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
	}

	public void siteUrlBaseTest() {
		siteUrlBaseTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
	}

	public void sortedTest() {
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
		assertEquals(STEWARDS_ARS_IAWC_IAWC225[1], results.get(0).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(STEWARDS_ARS_IAWC_IAWC410[1], results.get(1).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(NWIS_USGS_05425700[1], results.get(2).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(NWIS_USGS_431925089002701[1], results.get(3).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(STORET_11NPSWRD_BICA_MFG_B[1], results.get(4).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(STORET_21NYDECA_WQX_ONTARIO_02[1], results.get(5).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(STORET_WIDNR_WQX_10030952[1], results.get(6).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(STORET_WIDNR_WQX_113086[1], results.get(7).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(STORET_ORGANIZATION_SITE_ID[1], results.get(8).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(STORET_ORGANIZATION_SITE_ID2[1], results.get(9).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(STORET_ORGANIZATION_SITE_ID3[1], results.get(10).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(BIODATA_USGS_11421000[1], results.get(11).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
		assertEquals(BIODATA_USGS_433830088977331[1], results.get(12).get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString());
	}

	public void zipTest() {
		zipTest(nameSpace, Integer.valueOf(TOTAL_BIOLOGICAL_METRIC_COUNT));
	}

	public void bboxTest() {
		List<Map<String, Object>> results = bboxTest(nameSpace, 9);
		assertContainsBiologicalMetric(results,
				STEWARDS_ARS_IAWC_IAWC225,
				STEWARDS_ARS_IAWC_IAWC410,
				NWIS_USGS_431925089002701,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_11NPSWRD_BICA_MFG_B,
				NWIS_USGS_05425700
		);
	}

	public void countryTest() {
		List<Map<String, Object>> results = countryTest(nameSpace, 11);
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID,
				STEWARDS_ARS_IAWC_IAWC410,
				STEWARDS_ARS_IAWC_IAWC225,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701,
				BIODATA_USGS_11421000 
		);
	}

	public void countyTest() {
		List<Map<String, Object>> results = countyTest(nameSpace, 10);
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701,
				STEWARDS_ARS_IAWC_IAWC410,
				STEWARDS_ARS_IAWC_IAWC225
		);
	}

	public void huc2Test() {
		List<Map<String, Object>> results = huc2Test(nameSpace, 7);
		assertContainsBiologicalMetric(results,
				STEWARDS_ARS_IAWC_IAWC410,
				STEWARDS_ARS_IAWC_IAWC225,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_11NPSWRD_BICA_MFG_B
		);
	}

	public void huc3Test() {
		List<Map<String, Object>> results = huc3Test(nameSpace, 7);
		assertContainsBiologicalMetric(results,
				STEWARDS_ARS_IAWC_IAWC410,
				STEWARDS_ARS_IAWC_IAWC225,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_11NPSWRD_BICA_MFG_B
		);
	}

	public void huc4Test() {
		List<Map<String, Object>> results = huc4Test(nameSpace, 4);
		assertContainsBiologicalMetric(results,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701
		);
	}

	public void huc5Test() {
		List<Map<String, Object>> results = huc5Test(nameSpace, 4);
		assertContainsBiologicalMetric(results,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701
		);
	}

	public void huc6Test() {
		List<Map<String, Object>> results = huc6Test(nameSpace, 3);
		assertContainsBiologicalMetric(results,
				NWIS_USGS_05425700,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952
		);
	}

	public void huc7Test() {
		List<Map<String, Object>> results = huc7Test(nameSpace, 3);
		assertContainsBiologicalMetric(results,
				NWIS_USGS_05425700,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952
		);
	}

	public void huc8Test() {
		List<Map<String, Object>> results = huc8Test(nameSpace, 2);
		assertContainsBiologicalMetric(results,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952
		);
	}

	public void huc10Test() {
		List<Map<String, Object>> results = huc10Test(nameSpace, 2);
		assertContainsBiologicalMetric(results,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952
		);
	}

	public void huc12Test() {
		List<Map<String, Object>> results = huc12Test(nameSpace, 2);
		assertContainsBiologicalMetric(results,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952
		);
	}

	public void minActivitiesTest() {
		List<Map<String, Object>> results = minActivitiesTest(nameSpace, 7);
		assertContainsBiologicalMetric(results,
				NWIS_USGS_05425700,
				STEWARDS_ARS_IAWC_IAWC225,
				STORET_WIDNR_WQX_113086,
				STORET_21NYDECA_WQX_ONTARIO_02,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID
		);
	}

	public void minResultsTest() {
		List<Map<String, Object>> results = minResultsTest(nameSpace, 6);
		assertContainsBiologicalMetric(results,
				NWIS_USGS_05425700,
				STORET_WIDNR_WQX_113086,
				STORET_21NYDECA_WQX_ONTARIO_02,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID
		);
	}

	public void nldiSitesTest() {
		List<Map<String, Object>> results = nldiSitesTest(nameSpace, 3);
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_WIDNR_WQX_113086
		);
	};

	public void sampleMediaTest() {
		List<Map<String, Object>> results = sampleMediaTest(nameSpace, 11);
		assertContainsBiologicalMetric(results,
				BIODATA_USGS_11421000,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701,
				STEWARDS_ARS_IAWC_IAWC410,
				STEWARDS_ARS_IAWC_IAWC225,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_WIDNR_WQX_113086,
				STORET_21NYDECA_WQX_ONTARIO_02,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID
		);
	}

	public void siteIdTest() {
		List<Map<String, Object>> results = siteIdTest(nameSpace, 9);
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_WIDNR_WQX_10030952,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701,
				STEWARDS_ARS_IAWC_IAWC225,
				STEWARDS_ARS_IAWC_IAWC410
		);
	}

	public void siteIdLargeListTest() {
		List<Map<String, Object>> results = siteIdLargeListTest(nameSpace, 3);
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_WIDNR_WQX_113086
		);
	}

	public void siteTypeTest() {
		List<Map<String, Object>> results = siteTypeTest(nameSpace, 11);
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_21NYDECA_WQX_ONTARIO_02,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID,
				BIODATA_USGS_11421000,
				NWIS_USGS_05425700,
				STEWARDS_ARS_IAWC_IAWC410,
				STEWARDS_ARS_IAWC_IAWC225
		);
	}

	public void startDateHiTest() {
		List<Map<String, Object>> results = startDateHiTest(nameSpace, 11);
		assertContainsBiologicalMetric(results,
				NWIS_USGS_431925089002701,
				BIODATA_USGS_11421000,
				NWIS_USGS_05425700,
				STEWARDS_ARS_IAWC_IAWC225,
				STORET_WIDNR_WQX_113086,
				STEWARDS_ARS_IAWC_IAWC410,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_21NYDECA_WQX_ONTARIO_02,
				STORET_11NPSWRD_BICA_MFG_B
		);
	}

	public void startDateLoTest() {
		List<Map<String, Object>> results = startDateLoTest(nameSpace, 9);
		assertContainsBiologicalMetric(results,
				NWIS_USGS_05425700,
				STEWARDS_ARS_IAWC_IAWC410,
				NWIS_USGS_431925089002701,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID3,
				BIODATA_USGS_11421000,
				STEWARDS_ARS_IAWC_IAWC225,
				STORET_11NPSWRD_BICA_MFG_B
		);
	}

	public void stateTest() {
		List<Map<String, Object>> results = stateTest(nameSpace, 10);
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID,
				STEWARDS_ARS_IAWC_IAWC410,
				STEWARDS_ARS_IAWC_IAWC225,
				NWIS_USGS_05425700,
				NWIS_USGS_431925089002701
		);
	}

	public void withinTest() {
		List<Map<String, Object>> results = withinTest(nameSpace, 10);
		assertContainsBiologicalMetric(results,
				STEWARDS_ARS_IAWC_IAWC225,
				STEWARDS_ARS_IAWC_IAWC410,
				NWIS_USGS_431925089002701,
				NWIS_USGS_05425700,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_WIDNR_WQX_113086,
				STORET_WIDNR_WQX_10030952,
				STORET_21NYDECA_WQX_ONTARIO_02
		);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Result_Sum

	public void analyticalMethodTest() {
		List<Map<String, Object>> results = analyticalMethodTest(nameSpace, 5);
		assertContainsBiologicalMetric(results,
				NWIS_USGS_05425700,
				STORET_11NPSWRD_BICA_MFG_B,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID3);
	}

	public void assemblageTest() {
		List<Map<String, Object>> results = assemblageTest(nameSpace, 5);
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_11NPSWRD_BICA_MFG_B,
				BIODATA_USGS_11421000,
				STORET_ORGANIZATION_SITE_ID
		);
	}

	public void characteristicNameTest() {
		List<Map<String, Object>> results = characteristicNameTest(nameSpace, 4);
		assertContainsBiologicalMetric(results,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_11NPSWRD_BICA_MFG_B
		);
	}

	public void characteristicTypeTest() {
		List<Map<String, Object>> results = characteristicTypeTest(nameSpace, 5);
		assertContainsBiologicalMetric(results, 
				STEWARDS_ARS_IAWC_IAWC225,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_11NPSWRD_BICA_MFG_B
		);
	}

	public void pcodeTest() {
		List<Map<String, Object>> results = pcodeTest(nameSpace, 4);
		assertContainsBiologicalMetric(results, 
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2,
				NWIS_USGS_431925089002701,
				STORET_11NPSWRD_BICA_MFG_B
		);
	}

	public void subjectTaxonomicNameTest() {
		List<Map<String, Object>> results = subjectTaxonomicNameTest(nameSpace, 5);
		assertContainsBiologicalMetric(results, 
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2,
				STORET_ORGANIZATION_SITE_ID3,
				STORET_11NPSWRD_BICA_MFG_B,
				BIODATA_USGS_11421000
		);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Multiples

	public void multipleParameterTest() {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 3);
		assertContainsBiologicalMetric(results, 
				NWIS_USGS_05425700,
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2
		);
	}

	public void multipleParameterActivitySumTest() {
		List<Map<String, Object>> results = multipleParameterActivitySumTest(nameSpace, 2);
		assertContainsBiologicalMetric(results, 
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2
		);
	}

	public void multipleParameterResultSumTest() {
		List<Map<String, Object>> results = multipleParameterResultSumTest(nameSpace, 2);
		assertContainsBiologicalMetric(results, 
				STORET_ORGANIZATION_SITE_ID,
				STORET_ORGANIZATION_SITE_ID2
		);
	}

	public void assertContainsBiologicalMetric(List<Map<String, Object>> results, String[]... projects) {
		for (Map<String, Object> result: results) {
			LOG.debug(ProjectColumn.KEY_DATA_SOURCE_ID + ":" + result.get(ProjectColumn.KEY_DATA_SOURCE_ID) + '/' 
					+ ProjectColumn.KEY_PROJECT_ID + ":" + result.get(ProjectColumn.KEY_PROJECT_ID) + '/'
					+ ProjectColumn.KEY_ORGANIZATION + ":" + result.get(ProjectColumn.KEY_ORGANIZATION));
		}

		for (String[] i : projects) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(ProjectColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(ProjectColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(result.get(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER).toString())
						&& i[2].equalsIgnoreCase(result.get(ProjectColumn.KEY_ORGANIZATION).toString())) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				fail(ProjectColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + BiologicalMetricColumn.KEY_INDEX_IDENTIFIER + ":" + i[1] + 
						"/" + ProjectColumn.KEY_ORGANIZATION + ":" + i[2] + " was not in the result set.");
			}
		}
		assertEquals(projects.length, results.size(), "Double check result set expected size");
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
		assertUrl(ProjectColumn.KEY_PROJECT_FILE_URL, row);
		assertUrl(ProjectColumn.KEY_MONITORING_LOCATION_WEIGHT_URL, row);
	}

}
