package gov.usgs.cida.wqp.dao.streaming;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.mapping.xml.StationKml;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public abstract class BaseStationStreamingTest extends BaseStreamingTest {
	private static final Logger LOG = LoggerFactory.getLogger(BaseStationStreamingTest.class);

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

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Station_Sum 

	public void nullParameterTest(NameSpace nameSpace) {
		nullParameterTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
	}

	public void emptyParameterTest(NameSpace nameSpace) {
		emptyParameterTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
	}

	protected void mimeTypeTest(NameSpace nameSpace) {
		FilterParameters filter = new FilterParameters();
		filter.setMimeType(JSON);
		LinkedList<Map<String, Object>> results = callDao(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM), filter);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		filter.setMimeType(GEOJSON);
		results = callDao(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM), filter);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		filter.setMimeType(KML);
		results = callDao(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM), filter);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		filter.setMimeType(KMZ);
		results = callDao(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT_GEOM), filter);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184);

		filter.setMimeType(CSV);
		results = callDao(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT), filter);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441, BIODATA_61233184, BIODATA_433830088977331);
	}

	public void allDataSortedTest(NameSpace nameSpace, Map<String, Object> expectedMap) {
		Integer expectedColumnCount = expectedMap.keySet().size();
		LinkedList<Map<String, Object>> results = allDataSortedTest(nameSpace, Integer.valueOf(TOTAL_SITE_COUNT));
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
		LinkedList<Map<String, Object>> results = avoidTest(nameSpace, Integer.valueOf(STORET_SITE_COUNT));
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707, STORET_1043441);
	}

	public void bboxTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = bboxTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void countryTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = countryTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441,
				BIODATA_61233184);
	}

	public void countyTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = countyTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void huc2Test(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = huc2Test(nameSpace, 7);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void huc3Test(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = huc3Test(nameSpace, 7);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void huc4Test(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = huc4Test(nameSpace, 4);
		assertContainsStation(results, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723);
	}

	public void huc5Test(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = huc5Test(nameSpace, 4);
		assertContainsStation(results, NWIS_1353690, NWIS_1360035, STORET_1383, STORET_436723);
	}

	public void huc6Test(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = huc6Test(nameSpace, 3);
		assertContainsStation(results, NWIS_1353690, STORET_1383, STORET_436723);
	}

	public void huc7Test(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = huc7Test(nameSpace, 3);
		assertContainsStation(results, NWIS_1353690, STORET_1383, STORET_436723);
	}

	public void huc8Test(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = huc8Test(nameSpace, 2);
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	public void huc10Test(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = huc10Test(nameSpace, 2);
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	public void huc12Test(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = huc12Test(nameSpace, 2);
		assertContainsStation(results, STORET_1383, STORET_436723);
	}

	public void nldiUrlTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = nldiUrlTest(nameSpace, 3);
		assertContainsStation(results, STORET_777, STORET_888, STORET_1383);
	}

	public void organizationTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = organizationTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void providersTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = providersTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707,
				STORET_1043441);
	}

	public void siteIdTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = siteIdTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_436723, STORET_1043441);
	}

	public void manySitesTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = manySitesTest(nameSpace, 3);
		assertContainsStation(results, STORET_777, STORET_888, STORET_1383);
	}

	public void minActivitiesTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = minActivitiesTest(nameSpace, 7);
		assertContainsStation(results, STEWARDS_36, NWIS_1353690, STORET_777, STORET_888, STORET_1383, STORET_504707, STORET_1043441);
	}

	public void minResultsTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = minResultsTest(nameSpace, 6);
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888, STORET_1383, STORET_504707, STORET_1043441);
	}

	public void siteTypeTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = siteTypeTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707, STORET_1043441,
				BIODATA_61233184);
	}

	public void stateTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = stateTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_1043441);
	}

	public void withinTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = withinTest(nameSpace, 10);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_436723, STORET_504707);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Activity_Sum 

	public void projectTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = projectTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	public void sampleMediaTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = sampleMediaTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_504707, STORET_1043441,
				BIODATA_61233184);
	}

	public void startDateHiTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = startDateHiTest(nameSpace, 11);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1383, STORET_504707, STORET_1043441,
				BIODATA_61233184);
	}

	public void startDateLoTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = startDateLoTest(nameSpace, 9);
		assertContainsStation(results, STEWARDS_36, STEWARDS_46, NWIS_1353690, NWIS_1360035, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Station + Result_Sum

	public void analyticalMethodTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = analyticalMethodTest(nameSpace, 5);
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	public void assemblageTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = assemblageTest(nameSpace, 5);
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	public void characteristicNameTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = characteristicNameTest(nameSpace, 4);
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	public void characteristicTypeTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = characteristicTypeTest(nameSpace, 5);
		assertContainsStation(results, STEWARDS_36, STORET_777, STORET_888, STORET_999, STORET_1043441);
	}

	public void pcodeTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = pcodeTest(nameSpace, 4);
		assertContainsStation(results, NWIS_1360035, STORET_777, STORET_888, STORET_1043441);
	}

	public void subjectTaxonomicNameTest(NameSpace nameSpace) {
		LinkedList<Map<String, Object>> results = subjectTaxonomicNameTest(nameSpace, 5);
		assertContainsStation(results, STORET_777, STORET_888, STORET_999, STORET_1043441, BIODATA_61233184);
	}

	public void multipleParameterStationSumTest(NameSpace nameSpace) {
		FilterParameters filter = new FilterParameters();
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		LinkedList<Map<String, Object>> results = callDao(nameSpace, 3, filter);
		assertContainsStation(results, NWIS_1353690, STORET_777, STORET_888);
	}

	public void multipleParameterActivitySumTest(NameSpace nameSpace) {
		FilterParameters filter = new FilterParameters();
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		LinkedList<Map<String, Object>> results = callDao(nameSpace, 2, filter);
		assertContainsStation(results, STORET_777, STORET_888);
	}

	public void multipleParameterResultSumTest(NameSpace nameSpace) {
		FilterParameters filter = new FilterParameters();
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setWithin(getWithin());

		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());

		filter.setPCode(getPcode());
		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setAssemblage(getAssemblage());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		LinkedList<Map<String, Object>> results = callDao(nameSpace, 2, filter);
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

	public void assertContainsStation(LinkedList<Map<String, Object>> results, String[]...  stations) {
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

}
