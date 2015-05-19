package gov.usgs.cida.wqp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.usgs.cida.wqp.IntegrationTest;
import gov.usgs.cida.wqp.dao.intfc.IDao;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.MybatisConstants;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

@Category(IntegrationTest.class)
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/stationCount.xml")
})
public class CountDaoPcResultTest extends CountDaoTest {

	@Autowired 
	CountDao countDao;

	@Test
	public void singleParameterTests() {
		String namespace = IDao.RESULT_NAMESPACE;
		Map<String, Object> parms = new HashMap<>();
		List<Map<String, Object>> counts = countDao.getCounts(namespace, null);
		assertResults(counts, 4, "6", "2", "2", "2", "40", "12", "24", "4");
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 4, "6", "2", "2", "2", "40", "12", "24", "4");

		//Counts against station_sum

//		parms.clear();
//		parms.put(Parameters.BBOX.toString(), new String[]{"-89", "43", "-88", "44"});
//		counts = countDao.getCounts(namespace, parms);
//		assertResults(namespace, counts, "0", "0", "0", "0", "0", "0", "0");

		parms.clear();
		parms.put("commandavoid", new String[]{"STORET"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "2", "2", null, null, "12", "12", null, null);
		
		parms.clear();
		parms.put(Parameters.COUNTRY.toString(), new String[]{"US"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 4, "6", "2", "2", "2", "40", "12", "24", "4");

		parms.clear();
		parms.put(Parameters.COUNTY.toString(), new String[]{"US:55:027"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "1", "1", null, null, "7", "7", null, null);

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"07"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 4, "6", "2", "2", "2", "40", "12", "24", "4");

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"0708"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 3, "2", null, "1", "1", "12", null, "11", "1");

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"070801"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 3, "2", null, "1", "1", "12", null, "11", "1");

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"07090002"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "2", "2", null, null, "12", "12", null, null);

		parms.clear();
		parms.put(Parameters.ORGANIZATION.toString(), new String[]{"USGS-WI"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "2", "2", null, null, "12", "12", null, null);

		parms.clear();
		parms.put(Parameters.PROVIDERS.toString(), new String[]{"STEWARDS"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "2", null, "2", null, "24", null, "24", null);

		parms.clear();
		parms.put(Parameters.SITEID.toString(), new String[]{"11NPSWRD-BICA_MFG_B"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "1", null, null, "1", "1", null, null, "1");

		parms.clear();
		parms.put(Parameters.SITE_TYPE.toString(), new String[]{"Stream"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 4, "3", "1", "1", "1", "21", "5", "13", "3");

		parms.clear();
		parms.put(Parameters.STATE.toString(), new String[]{"US:55"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 3, "3", "2", null, "1", "15", "12", null, "3");

		parms.clear();
		parms.put(Parameters.WITHIN.toString(), new String[]{"10"});
		parms.put(Parameters.LATITUDE.toString(), new String[]{"43.3836014"});
		parms.put(Parameters.LONGITUDE.toString(), new String[]{"-88.9773314"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "2", "2", null, null, "12", "12", null, null);


		//Counts against pc_result_ct_sum
		
		parms.clear();
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), new String[]{"https://www.nemi.gov/methods/method_summary/8896/"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "1", "1", null, null, "17", "17", null, null);

		parms.clear();
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), new String[]{"Nitrate"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "1", null, null, "1", "19", null, null, "19");

		parms.clear();
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), new String[]{"Nutrient"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "1", null, "1", null, "23", null, "23", null);

		parms.clear();
		parms.put(Parameters.PCODE.toString(), new String[]{"00004"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "1", "1", null, null, "29", "29", null, null);

		parms.clear();
		parms.put(Parameters.PROJECT.toString(), new String[]{"NAWQA"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "2", "2", null, null, "46", "46", null, null);

		parms.clear();
		parms.put(Parameters.SAMPLE_MEDIA.toString(), new String[]{"Water"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "1", null, "1", null, "31", null, "31", null);

		
		
		//Counts against pc_result_sum
		
		//TODO - Activity is not currently supported
		//parms.put(Parameters.ACTIVITY_ID.toString(), new String[]{"abc"});
		//counts = countDao.getCounts(namespace, parms);
		//assertResults(namespace, counts, 4, "6", "2", "2", "2");

		parms.clear();
		parms.put(Parameters.START_DATE_HI.toString(), new String[]{"10-11-2012"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "1", "1", null, null, "37", "37", null, null);

		parms.clear();
		parms.put(Parameters.START_DATE_LO.toString(), new String[]{"10-11-2012"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 2, "1", "1", null, null, "37", "37", null, null);
		
	}
	
	@Test
	public void multipleParameterTests() {
		String namespace = IDao.RESULT_NAMESPACE;
		Map<String, Object> parms = new HashMap<>();

		//Counts against station_sum

		//TODO Why doesn't bBox work in test?
//		parms.clear();
//		parms.put(Parameters.BBOX.toString(), new String[]{"-89", "43", "-88", "44"});
//		counts = countDao.getCounts(namespace, parms);
//		assertResults(namespace, counts, "0", "0", "0", "0");

		parms.put(Parameters.COUNTRY.toString(), new String[]{"MX", "US"});
		parms.put(Parameters.COUNTY.toString(), new String[]{"US:19:015", "US:30:003", "US:55:017", "US:55:021", "US:55:027"});
		parms.put(Parameters.HUC.toString(), new String[]{"07","0708","070801","07090002", "07080105"});
		parms.put(Parameters.ORGANIZATION.toString(), new String[]{"ARS", "11NPSWRD", "USGS-WI", "WIDNR_WQX"});
		parms.put(Parameters.PROVIDERS.toString(), new String[]{"NWIS", "STEWARDS", "STORET"});
		parms.put(Parameters.SITEID.toString(), new String[]{"11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700",
			"USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410"});
		parms.put(Parameters.SITE_TYPE.toString(), new String[]{"Lake, Reservoir, Impoundment", "Land", "Stream", "Well"});
		parms.put(Parameters.STATE.toString(), new String[]{"US:19", "US:30", "US:55"});
		parms.put(Parameters.WITHIN.toString(), new String[]{"1000"});
		parms.put(Parameters.LATITUDE.toString(), new String[]{"43.3836014"});
		parms.put(Parameters.LONGITUDE.toString(), new String[]{"-88.9773314"});
		List<Map<String, Object>> counts = countDao.getCounts(namespace, parms);
		assertResults(counts, 4, "6", "2", "2", "2", "40", "12", "24", "4");

		parms.put("commandavoid", new String[]{"NWIS", "STORET"});
		counts = countDao.getCounts(namespace, parms);
		assertTrue(counts.isEmpty());
		
		
		//Counts against pc_result_ct_sum
		
		parms.clear();
		parms.put("commandavoid", new String[]{"STORET"});
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), new String[]{"https://www.nemi.gov/methods/method_summary/4665/",
			"https://www.nemi.gov/methods/method_summary/8896/"});
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), new String[]{"Beryllium", "Nitrate"});
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), new String[]{"Inorganics, Minor, Metals", "Nutrient"});
		parms.put(Parameters.PCODE.toString(), new String[]{"00032", "00004"});
		parms.put(Parameters.PROJECT.toString(), new String[]{"NAWQA", "CEAP"});
		parms.put(Parameters.SAMPLE_MEDIA.toString(), new String[]{"Other", "Sediment", "Water"});
		parms.put(Parameters.COUNTRY.toString(), new String[]{"MX", "US"});
		parms.put(Parameters.COUNTY.toString(), new String[]{"US:19:015", "US:30:003", "US:55:017", "US:55:021", "US:55:027"});
		parms.put(Parameters.HUC.toString(), new String[]{"07","0708","070801","07090002", "07080105"});
		parms.put(Parameters.ORGANIZATION.toString(), new String[]{"ARS", "11NPSWRD", "USGS-WI", "WIDNR_WQX"});
		parms.put(Parameters.PROVIDERS.toString(), new String[]{"NWIS", "STEWARDS", "STORET"});
		parms.put(Parameters.SITEID.toString(), new String[]{"11NPSWRD-BICA_MFG_B", "WIDNR_WQX-10030952", "USGS-05425700",
			"USGS-431925089002701", "ARS-IAWC-IAWC225", "ARS-IAWC-IAWC410"});
		parms.put(Parameters.SITE_TYPE.toString(), new String[]{"Lake, Reservoir, Impoundment", "Land", "Stream", "Well"});
		parms.put(Parameters.STATE.toString(), new String[]{"US:19", "US:30", "US:55"});
		counts = countDao.getCounts(namespace, parms);
		//TODO - test data
		//assertResults(namespace, counts, 2, "1", null, "1", null);

		
		
		//Counts against pc_result_sum
		
		//TODO - Activity is not currently supported
		//parms.put(Parameters.ACTIVITY_ID.toString(), new String[]{"abc"});
		//counts = countDao.getCounts(namespace, parms);
		//assertResults(namespace, counts, 4, "6", "2", "2", "2");

		parms.put(Parameters.START_DATE_HI.toString(), new String[]{"10-11-2012"});
		parms.put(Parameters.START_DATE_LO.toString(), new String[]{"10-11-2012"});
		counts = countDao.getCounts(namespace, parms);
		//TODO - test data
		//assertResults(namespace, counts, 2, "1", null, "1", null);

		//Counts against pc_result_nr_sum
		
		parms.put(Parameters.BBOX.toString(), new String[]{"-89", "43", "-88", "44"});
		parms.put(Parameters.WITHIN.toString(), new String[]{"1000"});
		parms.put(Parameters.LATITUDE.toString(), new String[]{"43.3836014"});
		parms.put(Parameters.LONGITUDE.toString(), new String[]{"-88.9773314"});
		counts = countDao.getCounts(namespace, parms);
		//TODO - test data
		//assertResults(namespace, counts, 2, "1", null, "1", null);

	}
	
	private void assertResults(List<Map<String, Object>> counts, int expectedSize, String expectedTotalStation,
			String expectedNwisStation, String expectedStewardsStation, String expectedStoretStation, String expectedTotalResult,
			String expectedNwisResult, String expectedStewardsResult, String expectedStoretResult) {
		assertEquals(expectedSize, counts.size());
		boolean nwisStation = (null == expectedNwisStation);
		boolean stewardsStation = (null == expectedStewardsStation);
		boolean storetStation = (null == expectedStoretStation);
		boolean totalStation = false;
		boolean nwisResult = (null == expectedNwisResult);
		boolean stewardsResult = (null == expectedStewardsResult);
		boolean storetResult = (null == expectedStoretResult);
		boolean totalResult = false;
		for (int i = 0 ; i < counts.size() ; i++) {
			if (null == counts.get(i).get(MybatisConstants.DATA_SOURCE)) {
				assertEquals("total station count", expectedTotalStation, counts.get(i).get(MybatisConstants.STATION_COUNT).toString());
				totalStation = true;
				assertEquals("total pcResult count", expectedTotalResult, counts.get(i).get(MybatisConstants.PC_RESULT_COUNT).toString());
				totalResult = true;
			} else {
				switch (counts.get(i).get(MybatisConstants.DATA_SOURCE).toString()) {
				case "NWIS":
					assertEquals("NWIS station count", expectedNwisStation, counts.get(i).get(MybatisConstants.STATION_COUNT).toString());
					nwisStation = true;
					assertEquals("NWIS pcResult count", expectedNwisResult, counts.get(i).get(MybatisConstants.PC_RESULT_COUNT).toString());
					nwisResult = true;
					break;
				case "STEWARDS":
					assertEquals("STEWARDS station count", expectedStewardsStation, counts.get(i).get(MybatisConstants.STATION_COUNT).toString());
					stewardsStation = true;
					assertEquals("STEWARDS pcResult count", expectedStewardsResult, counts.get(i).get(MybatisConstants.PC_RESULT_COUNT).toString());
					stewardsResult = true;
					break;
				case "STORET":
					assertEquals("STORET station count", expectedStoretStation, counts.get(i).get(MybatisConstants.STATION_COUNT).toString());
					storetStation = true;
					assertEquals("STORET pcResult count", expectedStoretResult, counts.get(i).get(MybatisConstants.PC_RESULT_COUNT).toString());
					storetResult = true;
					break;
				default:
					break;
				}
			}
		}
		assertTrue("got station Total", totalStation);
		assertTrue("got station NWIS", nwisStation);
		assertTrue("got station STEWARDS", stewardsStation);
		assertTrue("got station STORET", storetStation);
		assertTrue("got result Total", totalResult);
		assertTrue("got result NWIS", nwisResult);
		assertTrue("got result STEWARDS", stewardsResult);
		assertTrue("got result STORET", storetResult);

	}

}
