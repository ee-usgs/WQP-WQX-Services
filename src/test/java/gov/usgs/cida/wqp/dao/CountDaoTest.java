package gov.usgs.cida.wqp.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.MybatisConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class CountDaoTest extends BaseSpringTest {

	@Autowired 
	CountDao countDao;

	public void singleParameterTests(String namespace, boolean includeResults) {
		Map<String, Object> parms = new HashMap<>();
		List<Map<String, Object>> counts = countDao.getCounts(namespace, null);
		assertResults(includeResults, counts, 5, "7", "2", "2", "2", "1", "81", "12", "24", "4", "41");
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "7", "2", "2", "2", "1", "81", "12", "24", "4", "41");

		//Counts against station_sum

		//this uses the goespatial index - it is not updated unless a commit is issued - we probably don't want to do that as
		//it would require a manual delete of data and commit, so we are only checking syntax, not results...
		parms.clear();
		parms.put(Parameters.BBOX.toString(), new String[]{"-89", "43", "-88", "44"});
		counts = countDao.getCounts(namespace, parms);
//		assertResults(counts, 0, "0", "0", "0", "0");

		parms.clear();
		parms.put("commandavoid", new String[]{"STORET"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "2", "2", null, null, null, "12", "12", null, null, null);
		
		parms.clear();
		parms.put(Parameters.COUNTRY.toString(), new String[]{"US"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "7", "2", "2", "2", "1", "81", "12", "24", "4", "41");

		parms.clear();
		parms.put(Parameters.COUNTY.toString(), new String[]{"US:55:027"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", "1", null, null, null, "7", "7", null, null, null);

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"07"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "6", "2", "2", "2", null, "40", "12", "24", "4", null);

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"0708"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "2", null, "1", "1", null, "12", null, "11", "1", null);

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"070801"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "2", null, "1", "1", null, "12", null, "11", "1", null);

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"07090002"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "2", "2", null, null, null, "12", "12", null, null, null);

		parms.clear();
		parms.put(Parameters.MIN_RESULTS.toString(), "3");
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "6", "2", "2", "1", "1", "80", "12", "24", "3", "41");

		try {
			parms.clear();
			parms.put(Parameters.NLDI_SITEID, getSourceFile("manySites.txt").split(","));
			counts = countDao.getCounts(namespace, parms);
			assertTrue(counts.isEmpty());
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		parms.clear();
		parms.put(Parameters.ORGANIZATION.toString(), new String[]{"USGS-WI"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "2", "2", null, null, null, "12", "12", null, null, null);

		parms.clear();
		parms.put(Parameters.PROVIDERS.toString(), new String[]{"STEWARDS"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "2", null, "2", null, null, "24", null, "24", null, null);

		parms.clear();
		parms.put(Parameters.SITEID.toString(), new String[]{"11NPSWRD-BICA_MFG_B"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", null, null, "1", null, "1", null, null, "1", null);

		try {
			parms.clear();
			parms.put(Parameters.SITEID.toString(), getSourceFile("manySites.txt").split(","));
			counts = countDao.getCounts(namespace, parms);
			assertTrue(counts.isEmpty());
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		parms.clear();
		parms.put(Parameters.SITE_TYPE.toString(), new String[]{"Stream"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "4", "1", "1", "1", "1", "62", "5", "13", "3", "41");

		parms.clear();
		parms.put(Parameters.STATE.toString(), new String[]{"US:55"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "3", "2", null, "1", null, "15", "12", null, "3", null);

		//it looks like this does not use the goespatial index.
		parms.clear();
		parms.put(Parameters.WITHIN.toString(), new String[]{"10"});
		parms.put(Parameters.LATITUDE.toString(), new String[]{"43.3836014"});
		parms.put(Parameters.LONGITUDE.toString(), new String[]{"-88.9773314"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "3", "2", null, "1", null, "15", "12", null, "3", null);


		//Counts against pc_result_ct_sum
		
		parms.clear();
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), new String[]{"https://www.nemi.gov/methods/method_summary/8896/"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", "1", null, null, null, "17", "17", null, null, null);

		parms.clear();
		parms.put(Parameters.ASSEMBLAGE.toString(), new String[]{"Fish/Nekton"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "2", null, null, "1", "1", "62", null, null, "19", "43");

		parms.clear();
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), new String[]{"Nitrate"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", null, null, "1", null, "19", null, null, "19", null);

		parms.clear();
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), new String[]{"Nutrient"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", null, "1", null, null, "23", null, "23", null, null);

		parms.clear();
		parms.put(Parameters.PCODE.toString(), new String[]{"00004"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", "1", null, null, null, "29", "29", null, null, null);

		parms.clear();
		parms.put(Parameters.PROJECT.toString(), new String[]{"NAWQA"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "2", "2", null, null, null, "46", "46", null, null, null);

		parms.clear();
		parms.put(Parameters.PROJECT.toString(), new String[]{"EPABEACH"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", null, null, "1", null, "19", null, null, "19", null);

		parms.clear();
		parms.put(Parameters.SAMPLE_MEDIA.toString(), new String[]{"Water"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", null, "1", null, null, "31", null, "31", null, null);

		parms.clear();
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), new String[]{"Acipenser"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", null, null, null, "1", "43", null, null, null, "43");

		
		
		//Counts against pc_result_sum
		
		//TODO - Activity is not currently supported
		//parms.put(Parameters.ACTIVITY_ID.toString(), new String[]{"abc"});
		//counts = countDao.getCounts(namespace, parms);
		//assertResults(counts, 4, "6", "2", "2", "2");

		parms.clear();
		parms.put(Parameters.START_DATE_HI.toString(), new String[]{"10-11-2012"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "2", "1", null, null, "1", "84", "37", null, null, "47");

		parms.clear();
		parms.put(Parameters.START_DATE_LO.toString(), new String[]{"10-11-2012"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", "1", null, null, null, "37", "37", null, null, null);
		
	}
	
	public void multipleParameterTests(String namespace, boolean includeResults) {
		Map<String, Object> parms = new HashMap<>();

		//Counts against station_sum

		//see BBOX comment above
		parms.clear();
		parms.put(Parameters.BBOX.toString(), new String[]{"-89", "43", "-88", "44"});
		List<Map<String, Object>> counts = countDao.getCounts(namespace, parms);
		parms.clear();
//		assertResults(counts, 0, "0", "0", "0", "0");

		parms.put(Parameters.COUNTRY.toString(), new String[]{"MX", "US"});
		parms.put(Parameters.COUNTY.toString(), new String[]{"US:19:015", "US:30:003", "US:55:017", "US:55:021", "US:55:027", "US:06:115"});
		parms.put(Parameters.HUC.toString(), new String[]{"07","0708","070801","07090002", "07080105", "18020107"});
		parms.put(Parameters.MIN_RESULTS.toString(), "3");
		parms.put(Parameters.ORGANIZATION.toString(), new String[]{"ARS", "11NPSWRD", "USGS-WI", "WIDNR_WQX", "USGS"});
		parms.put(Parameters.PROVIDERS.toString(), new String[]{"NWIS", "STEWARDS", "STORET", "BIODATA"});
		parms.put(Parameters.SITEID.toString(), new String[]{"11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700",
			"USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410", "USGS-11421000"});
		parms.put(Parameters.NLDI_SITEID, new String[]{"11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700",
				"USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410", "USGS-11421000"});
		parms.put(Parameters.SITE_TYPE.toString(), new String[]{"Lake, Reservoir, Impoundment", "Land", "Stream", "Well"});
		parms.put(Parameters.STATE.toString(), new String[]{"US:19", "US:30", "US:55", "US:06"});
		parms.put(Parameters.WITHIN.toString(), new String[]{"2000"});
		parms.put(Parameters.LATITUDE.toString(), new String[]{"43.3836014"});
		parms.put(Parameters.LONGITUDE.toString(), new String[]{"-88.9773314"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "6", "2", "2", "1", "1", "80", "12", "24", "3", "41");

		//show that the various siteid lists are AND'd together
		parms.put(Parameters.NLDI_SITEID, new String[]{"USGS-00000000", "USGS-05425700"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", "1", null, null, null, "5", "5", null, null, null);

		parms.put("commandavoid", new String[]{"NWIS", "STORET"});
		counts = countDao.getCounts(namespace, parms);
		assertTrue(counts.isEmpty());


		//Counts against result_ct_sum
		parms.clear();
		parms.put("commandavoid", new String[]{"STORET"});
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), new String[]{"https://www.nemi.gov/methods/method_summary/4665/",
			"https://www.nemi.gov/methods/method_summary/8896/"});
		parms.put(Parameters.ASSEMBLAGE.toString(), new String[]{"Fish/Nekton", "Benthic Macroinvertebrate"});
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), new String[]{"Beryllium", "Nitrate", "Count"});
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), new String[]{"Inorganics, Minor, Metals", "Nutrient", "Biological"});
		parms.put(Parameters.PCODE.toString(), new String[]{"00032", "00004"});
		parms.put(Parameters.PROJECT.toString(), new String[]{"NAWQA", "CEAP", "SACR BioTDB", "EPABEACH"});
		parms.put(Parameters.SAMPLE_MEDIA.toString(), new String[]{"Other", "Sediment", "Water"});
		parms.put(Parameters.COUNTRY.toString(), new String[]{"MX", "US"});
		parms.put(Parameters.COUNTY.toString(), new String[]{"US:19:015", "US:30:003", "US:55:017", "US:55:021", "US:55:027", "US:06:115"});
		parms.put(Parameters.HUC.toString(), new String[]{"07","0708","070801","07090002", "07080105", "18020107"});
		parms.put(Parameters.MIN_RESULTS.toString(), "3");
		parms.put(Parameters.ORGANIZATION.toString(), new String[]{"ARS", "11NPSWRD", "USGS-WI", "WIDNR_WQX", "USGS"});
		parms.put(Parameters.PROVIDERS.toString(), new String[]{"NWIS", "STEWARDS", "STORET", "BIODATA"});
		parms.put(Parameters.SITEID.toString(), new String[]{"11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700",
			"USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410", "USGS-11421000"});
		parms.put(Parameters.NLDI_SITEID, new String[]{"11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700",
				"USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410", "USGS-11421000"});
		parms.put(Parameters.SITE_TYPE.toString(), new String[]{"Lake, Reservoir, Impoundment", "Land", "Stream", "Well"});
		parms.put(Parameters.STATE.toString(), new String[]{"US:19", "US:30", "US:55"});
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), new String[]{"Acipenser", "Lota lota"});
		counts = countDao.getCounts(namespace, parms);
		//TODO - test data
		//assertResults(counts, 2, "1", null, "1", null);


		//Counts against result_sum
		//TODO - Activity is not currently supported
		//parms.put(Parameters.ACTIVITY_ID.toString(), new String[]{"abc"});
		//counts = countDao.getCounts(namespace, parms);
		//assertResults(counts, 4, "6", "2", "2", "2");

		parms.put(Parameters.START_DATE_HI.toString(), new String[]{"10-11-2012"});
		parms.put(Parameters.START_DATE_LO.toString(), new String[]{"10-11-2012"});
		counts = countDao.getCounts(namespace, parms);
		//TODO - test data
		//assertResults(counts, 2, "1", null, "1", null);

		//Counts against result_nr_sum
		parms.put(Parameters.BBOX.toString(), new String[]{"-89", "43", "-88", "44"});
		parms.put(Parameters.WITHIN.toString(), new String[]{"1000"});
		parms.put(Parameters.LATITUDE.toString(), new String[]{"43.3836014"});
		parms.put(Parameters.LONGITUDE.toString(), new String[]{"-88.9773314"});
		parms.put(Parameters.MIN_RESULTS.toString(), "3");
		counts = countDao.getCounts(namespace, parms);
		//TODO - test data
		//assertResults(counts, 2, "1", null, "1", null);

	}

	private void assertResults(boolean includeResults, List<Map<String, Object>> counts, int expectedSize,
			String expectedTotalStation, String expectedNwisStation, String expectedStewardsStation, String expectedStoretStation,
			String expectedBiodataStation,
			String expectedTotalResult, String expectedNwisResult, String expectedStewardsResult,
			String expectedStoretResult, String expectedBiodataResult) {
		assertEquals(expectedSize, counts.size());
		boolean nwisStation = (null == expectedNwisStation);
		boolean stewardsStation = (null == expectedStewardsStation);
		boolean storetStation = (null == expectedStoretStation);
		boolean biodataStation = (null == expectedBiodataStation);
		boolean totalStation = false;
		boolean nwisResult = (null == expectedNwisResult);
		boolean stewardsResult = (null == expectedStewardsResult);
		boolean storetResult = (null == expectedStoretResult);
		boolean biodataResult = (null == expectedBiodataResult);
		boolean totalResult = false;
		for (int i = 0 ; i < counts.size() ; i++) {
			if (null == counts.get(i).get(MybatisConstants.DATA_SOURCE)) {
				assertEquals("total station count", expectedTotalStation, counts.get(i).get(MybatisConstants.STATION_COUNT).toString());
				totalStation = true;
				if (includeResults) {
					assertEquals("total pcResult count", expectedTotalResult, counts.get(i).get(MybatisConstants.RESULT_COUNT).toString());
					totalResult = true;
				}
			} else {
				switch (counts.get(i).get(MybatisConstants.DATA_SOURCE).toString()) {
				case "NWIS":
					assertEquals("NWIS station count", expectedNwisStation, counts.get(i).get(MybatisConstants.STATION_COUNT).toString());
					nwisStation = true;
					if (includeResults) {
						assertEquals("NWIS pcResult count", expectedNwisResult, counts.get(i).get(MybatisConstants.RESULT_COUNT).toString());
						nwisResult = true;
					}
					break;
				case "STEWARDS":
					assertEquals("STEWARDS station count", expectedStewardsStation, counts.get(i).get(MybatisConstants.STATION_COUNT).toString());
					stewardsStation = true;
					if (includeResults) {
						assertEquals("STEWARDS pcResult count", expectedStewardsResult, counts.get(i).get(MybatisConstants.RESULT_COUNT).toString());
						stewardsResult = true;
					}
					break;
				case "STORET":
					assertEquals("STORET station count", expectedStoretStation, counts.get(i).get(MybatisConstants.STATION_COUNT).toString());
					storetStation = true;
					if (includeResults) {
						assertEquals("STORET pcResult count", expectedStoretResult, counts.get(i).get(MybatisConstants.RESULT_COUNT).toString());
						storetResult = true;
					}
					break;
				case "BIODATA":
					assertEquals("BIODATA station count", expectedBiodataStation, counts.get(i).get(MybatisConstants.STATION_COUNT).toString());
					biodataStation = true;
					if (includeResults) {
						assertEquals("BIODATA pcResult count", expectedBiodataResult, counts.get(i).get(MybatisConstants.RESULT_COUNT).toString());
						biodataResult = true;
					}
					break;
				default:
					break;
				}
			}
		}
		assertTrue("Did not get station Total", totalStation);
		assertTrue("Did not get station NWIS", nwisStation);
		assertTrue("Did not get station STEWARDS", stewardsStation);
		assertTrue("Did not get station STORET", storetStation);
		assertTrue("Did not get station BIODATA", biodataStation);
		if (includeResults) {
			assertTrue("Did not get result Total", totalResult);
			assertTrue("Did not get result NWIS", nwisResult);
			assertTrue("Did not get result STEWARDS", stewardsResult);
			assertTrue("Did not get station STORET", storetResult);
			assertTrue("Did not get station BIODATA", biodataResult);
		}
	}

}
