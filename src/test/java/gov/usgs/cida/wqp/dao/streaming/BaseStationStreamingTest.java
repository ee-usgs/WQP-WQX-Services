package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.dao.FilteredStationDaoTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.mapping.xml.StationKml;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public abstract class BaseStationStreamingTest extends FilteredStationDaoTest {
	private static final Logger LOG = LoggerFactory.getLogger(BaseStationStreamingTest.class);

	@Autowired 
	IStreamingDao streamingDao;

	public static final String ARS_SITE = "ARSSite";
	public static final String NWIS_SITE = "NWISSite";
	public static final String STORET_SITE = "STORETSite";
	public static final String BIODATA_SITE = "Unknown";

	public static final String[] STEWARDS_36 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", ARS_SITE};
	public static final String[] STEWARDS_46 = new String[]{STEWARDS, "ARS-IAWC-IAWC410", ARS_SITE};
	public static final String[] NWIS_1353690 = new String[]{NWIS, "USGS-05425700", NWIS_SITE};
	public static final String[] NWIS_1360035 = new String[]{NWIS, "USGS-431925089002701", NWIS_SITE};
	public static final String[] STORET_777 = new String[]{STORET, "organization-siteId2", STORET_SITE};
	public static final String[] STORET_888 = new String[]{STORET, "organization-siteId", STORET_SITE};
	public static final String[] STORET_999 = new String[]{STORET, "organization-siteId3", STORET_SITE};
	public static final String[] STORET_1383 = new String[]{STORET, "WIDNR_WQX-113086", STORET_SITE};
	public static final String[] STORET_436723 = new String[]{STORET, "WIDNR_WQX-10030952", STORET_SITE};
	public static final String[] STORET_504707 = new String[]{STORET, "21NYDECA_WQX-ONTARIO-02", STORET_SITE};
	public static final String[] STORET_1043441 = new String[]{STORET, "11NPSWRD-BICA_MFG_B", STORET_SITE};
	public static final String[] BIODATA_61233184 = new String[]{BIODATA, "USGS-11421000", BIODATA_SITE};
	public static final String[] BIODATA_433830088977331 = new String[]{BIODATA, "USGS-433830088977331", BIODATA_SITE};
	
	public static final String[] STEWARDS_36_2000_CT1 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", "2000", "ct_1", "cn_1"}; 
	public static final String[] STEWARDS_36_2001_CT1 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", "2001", "ct_1", "cn_1"}; 
	public static final String[] STEWARDS_36_2002_CT1 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", "2002", "ct_1", "cn_1"}; 
	public static final String[] STEWARDS_36_2003_CT1 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", "2003", "ct_1", "cn_1"}; 
	public static final String[] STEWARDS_36_2004_CT1 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", "2004", "ct_1", "cn_1"}; 
	public static final String[] STEWARDS_36_2005_CT1 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", "2005", "ct_1", "cn_1"}; 
	public static final String[] STEWARDS_36_2006_CT1 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", "2006", "ct_1", "cn_1"}; 
	public static final String[] STEWARDS_36_2007_CT1 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", "2007", "ct_1", "cn_1"}; 
	public static final String[] STEWARDS_36_2008_CT1 = new String[]{STEWARDS, "ARS-IAWC-IAWC225", "2008", "ct_1", "cn_1"}; 
	
	public static final String[] NWIS_1353690_2010_CT1 = new String[]{NWIS, "USGS-05425700", "2010", "ct_1", "cn_1"};
	public static final String[] NWIS_1353690_2011_CT1 = new String[]{NWIS, "USGS-05425700", "2011", "ct_1", "cn_1"};
	public static final String[] NWIS_1353690_2012_CT1 = new String[]{NWIS, "USGS-05425700", "2012", "ct_1", "cn_1"};
	public static final String[] NWIS_1353690_2013_CT1 = new String[]{NWIS, "USGS-05425700", "2013", "ct_1", "cn_1"};
	public static final String[] NWIS_1353690_2014_CT1 = new String[]{NWIS, "USGS-05425700", "2014", "ct_1", "cn_1"};
	public static final String[] NWIS_1353690_2015_CT1 = new String[]{NWIS, "USGS-05425700", "2015", "ct_1", "cn_1"};
	public static final String[] NWIS_1353690_2016_CT1 = new String[]{NWIS, "USGS-05425700", "2016", "ct_1", "cn_1"};
	public static final String[] NWIS_1353690_2017_CT1 = new String[]{NWIS, "USGS-05425700", "2017", "ct_1", "cn_1"};
	public static final String[] NWIS_1353690_2018_CT1 = new String[]{NWIS, "USGS-05425700", "2018", "ct_1", "cn_1"};

	public static final String[] STORET_504707_1910_CT1 = new String[]{"STORET", "21NYDECA_WQX-ONTARIO-02", "1910", "ct_1", "cn_1"};
	public static final String[] STORET_504707_1911_CT1 = new String[]{"STORET", "21NYDECA_WQX-ONTARIO-02", "1911", "ct_1", "cn_1"};
	public static final String[] STORET_504707_1912_CT1 = new String[]{"STORET", "21NYDECA_WQX-ONTARIO-02", "1912", "ct_1", "cn_1"};
	public static final String[] STORET_504707_1923_CT1 = new String[]{"STORET", "21NYDECA_WQX-ONTARIO-02", "1923", "ct_1", "cn_1"};
	public static final String[] STORET_504707_1934_CT1 = new String[]{"STORET", "21NYDECA_WQX-ONTARIO-02", "1934", "ct_1", "cn_1"};
	public static final String[] STORET_504707_1945_CT1 = new String[]{"STORET", "21NYDECA_WQX-ONTARIO-02", "1945", "ct_1", "cn_1"};
	public static final String[] STORET_504707_1956_CT1 = new String[]{"STORET", "21NYDECA_WQX-ONTARIO-02", "1956", "ct_1", "cn_1"};
	public static final String[] STORET_504707_1977_CT1 = new String[]{"STORET", "21NYDECA_WQX-ONTARIO-02", "1977", "ct_1", "cn_1"};
	public static final String[] STORET_504707_1998_CT1 = new String[]{"STORET", "21NYDECA_WQX-ONTARIO-02", "1998", "ct_1", "cn_1"};
	
	public static final String[] NWIS_1360035_2017_CT1 = new String[]{NWIS, "USGS-431925089002701", "2017", "ct_1", "cn_1"};
	public static final String[] NWIS_1360035_2018_CT1 = new String[]{NWIS, "USGS-431925089002701", "2018", "ct_1", "cn_1"};	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Station_Sum 

	public void nullParameterTest(NameSpace nameSpace) {
		nullParameterTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
	}

	public void emptyParameterTest(NameSpace nameSpace) {
		emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
	}

	public void activityTest(NameSpace nameSpace) {
		activityTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
	}

	public void nldiUrlTest(NameSpace nameSpace) {
		nldiUrlTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
	}

	public void resultTest(NameSpace nameSpace) {
		resultTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
	}

	public void siteUrlBaseTest(NameSpace nameSpace) {
		siteUrlBaseTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
	}

	public void zipTest(NameSpace nameSpace) {
		zipTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
	}

	protected void mimeTypeTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = mimeTypeJsonTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		results = mimeTypeGeoJsonTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		results = mimeTypeKmlTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		results = mimeTypeKmzTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		results = mimeTypeCsvTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184, BIODATA_433830088977331);

		results = mimeTypeTsvTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184, BIODATA_433830088977331);

		results = mimeTypeXmlTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184, BIODATA_433830088977331);

		results = mimeTypeXlsxTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184, BIODATA_433830088977331);
	}

	public void sortedTest(NameSpace nameSpace, Map<String, Object> expectedMap) {
		Integer expectedColumnCount = expectedMap.keySet().size();
		List<Map<String, Object>> results = sortedTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
		assertRow(results.get(0), STEWARDS_36, expectedColumnCount);
		assertRow(results.get(1), STEWARDS_46, expectedColumnCount);
		assertRow(results.get(2), NWIS_1353690, expectedColumnCount);
		assertRow(results.get(3), NWIS_1360035, expectedColumnCount);
		assertRow(results.get(4), STORET_1043441, expectedColumnCount);
		assertRow(results.get(5), STORET_504707, expectedColumnCount);
		assertRow(results.get(6), STORET_436723, expectedColumnCount);
		assertRow(results.get(7), STORET_1383, expectedColumnCount);
		assertStoret888(expectedMap, results.get(8));
		assertRow(results.get(9), STORET_777, expectedColumnCount);
		assertRow(results.get(10), STORET_999, expectedColumnCount);
		assertRow(results.get(11), BIODATA_61233184, expectedColumnCount);
	}

	public void avoidTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = avoidTest(nameSpace, Integer.valueOf(STORET_SITE_COUNT));
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707, STORET_1043441);
	}

	public void bboxTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = bboxTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void countryTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = countryTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441,
				BIODATA_61233184);
	}

	public void countyTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = countyTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void huc2Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc2Test(nameSpace, 7);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void huc3Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc3Test(nameSpace, 7);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void huc4Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc4Test(nameSpace, 4);
		assertContainsStation(results, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723);
	}

	public void huc5Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc5Test(nameSpace, 4);
		assertContainsStation(results, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723);
	}

	public void huc6Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc6Test(nameSpace, 3);
		assertContainsStation(results, NWIS_1353690, STORET_1383, STORET_436723);
	}

	public void huc7Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc7Test(nameSpace, 3);
		assertContainsStation(results, NWIS_1353690, STORET_1383, STORET_436723);
	}

	public void huc8Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc8Test(nameSpace, 2);
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	public void huc10Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc10Test(nameSpace, 2);
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	public void huc12Test(NameSpace nameSpace) {
		List<Map<String, Object>> results = huc12Test(nameSpace, 2);
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	public void nldiSitesTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = nldiSitesTest(nameSpace, 3);
		assertContainsStation(results, STORET_777, STORET_888, STORET_1383);
	}

	public void organizationTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = organizationTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void providersTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = providersTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441);
	}

	public void siteIdTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = siteIdTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_436723, STORET_1043441);
	}

	public void siteIdLargeListTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = siteIdLargeListTest(nameSpace, 3);
		assertContainsStation(results, STORET_777, STORET_888, STORET_1383);
	}

	public void minActivitiesTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = minActivitiesTest(nameSpace, 7);
		assertContainsStation(results, STEWARDS_36, NWIS_1353690, STORET_777, STORET_888, STORET_1383, STORET_504707, STORET_1043441);
	}

	public void minResultsTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = minResultsTest(nameSpace, 6);
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888, STORET_1383, STORET_504707, STORET_1043441);
	}

	public void siteTypeTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = siteTypeTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707, STORET_1043441,
				BIODATA_61233184);
	}

	public void stateTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = stateTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void withinTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = withinTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Activity_Sum 

	public void projectTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = projectTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	public void sampleMediaTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = sampleMediaTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_504707, STORET_1043441,
				BIODATA_61233184);
	}

	public void startDateHiTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = startDateHiTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_504707, STORET_1043441,
				BIODATA_61233184);
	}

	public void startDateLoTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = startDateLoTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Result_Sum

	public void analyticalMethodTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = analyticalMethodTest(nameSpace, 5);
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	public void assemblageTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = assemblageTest(nameSpace, 5);
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	public void characteristicNameTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = characteristicNameTest(nameSpace, 4);
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	public void characteristicTypeTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = characteristicTypeTest(nameSpace, 5);
		assertContainsStation(results, STEWARDS_36, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	public void pcodeTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = pcodeTest(nameSpace, 4);
		assertContainsStation(results, NWIS_1360035, STORET_777, STORET_888, STORET_1043441);
	}

	public void subjectTaxonomicNameTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = subjectTaxonomicNameTest(nameSpace, 5);
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	public void multipleParameterStationSumTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = multipleParameterStationSumTest(nameSpace, 3);
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888);
	}

	public void multipleParameterActivitySumTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = multipleParameterActivitySumTest(nameSpace, 2);
		assertContainsStation(results, STORET_777, STORET_888);
	}

	public void multipleParameterResultSumTest(NameSpace nameSpace) {
		List<Map<String, Object>> results = multipleParameterResultSumTest(nameSpace, 2);
		assertContainsStation(results, STORET_777, STORET_888);
	}

	public static void assertRow(Map<String, Object> row, String[] station, int expectedColumnCount) {
		//The KML does not include data_source, it has a style_url		
		assertEquals(expectedColumnCount, row.keySet().size());
		if (row.containsKey(StationColumn.KEY_DATA_SOURCE)) {
			assertEquals(station[0], row.get(BaseColumn.KEY_DATA_SOURCE));
		}
		assertEquals(station[1], row.get(StationColumn.KEY_SITE_ID));
		if (row.containsKey(StationKml.KEY_STYLE_URL)) {
			assertEquals(station[2], row.get(StationKml.KEY_STYLE_URL));
		}
	}

	public void assertStoret888(Map<String, Object> expectedRow, Map<String, Object> actualRow) {
		assertMapIsAsExpected(expectedRow, actualRow);
	}

	public void assertContainsStation(List<Map<String, Object>> results, String[]...  stations) {
		for (Map<String, Object> result : results) {
			LOG.debug(StationColumn.KEY_DATA_SOURCE + ":" + result.get(StationColumn.KEY_DATA_SOURCE) + "/" + StationColumn.KEY_SITE_ID + ":" +  result.get(StationColumn.KEY_SITE_ID));
		}

		for (String[] i : stations) {
			boolean isFound = false;
			for (Map<String, Object> result : results) {
				if (result.containsKey(StationColumn.KEY_DATA_SOURCE)
						&& i[0].equalsIgnoreCase(((String) result.get(StationColumn.KEY_DATA_SOURCE)))
						&& i[1].equalsIgnoreCase(result.get(StationColumn.KEY_SITE_ID).toString())) {
					isFound = true;
					break;
				} else {
					//The KML does not include data_source, it has a style_url
					if (result.containsKey(StationKml.KEY_STYLE_URL)
							&& i[2].equalsIgnoreCase(((String) result.get(StationKml.KEY_STYLE_URL)))
							&& i[1].equalsIgnoreCase(result.get(StationColumn.KEY_SITE_ID).toString())) {
						isFound = true;
						break;
					}
				}
			}
			if (!isFound) {
				fail(StationColumn.KEY_DATA_SOURCE + ":" + i[0] + "/" + StationColumn.KEY_SITE_ID + ":" + i[1] + " was not in the result set.");
			}
		}
		assertEquals("Double check result set expected size", stations.length, results.size());
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
		// Nothing to do here
	}

}
