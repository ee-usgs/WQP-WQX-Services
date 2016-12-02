package gov.usgs.cida.wqp.dao.count;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.dao.CountDao;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.MybatisConstants;

public abstract class CountDaoTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(CountDaoTest.class);

	@Autowired 
	CountDao countDao;

	public void singleParameterTests(String namespace, boolean includeActivity, boolean includeResults) {
		Map<String, Object> parms = new HashMap<>();
		List<Map<String, Object>> counts = countDao.getCounts(namespace, null);
		assertResults(includeResults, counts, 5, "12", "2", "2", "7", "1", "103", "12", "4", "70", "17");
		if (includeActivity) {
			assertActivityResults(counts, 5, "301", "48", "40", "178", "35");
		}
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "12", "2", "2", "7", "1", "103", "12", "4", "70", "17");
		if (includeActivity) {
			assertActivityResults(counts, 5, "301", "48", "40", "178", "35");
		}

		//Single Parameter Counts against station_sum

		parms.clear();
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "7", null, null, "7", null, "70", null, null, "70", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "178", null, null, "178", null);
		}

		parms.clear();
		parms.put(Parameters.BBOX.toString(), getBBox());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "9", "2", "2", "5", null, "74", "12", "4", "58", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "236", "48", "40", "148", null);
		}

		parms.clear();
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "11", "2", "2", "6", "1", "103", "12", "4", "70", "17");
		if (includeActivity) {
			assertActivityResults(counts, 5, "301", "48", "40", "178", "35");
		}

		parms.clear();
		parms.put(Parameters.COUNTY.toString(), getCounty());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "10", "2", "2", "6", null, "86", "12", "4", "70", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "266", "48", "40", "178", null);
		}

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"07"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "7", "2", "2", "3", null, "55", "12", "4", "39", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "181", "48", "40", "93", null);
		}

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"0708"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "2", null, "1", "1", null, "14", null, "1", "13", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "50", null, "19", "31", null);
		}

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"070801"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "2", null, "1", "1", null, "14", null, "1", "13", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "50", null, "19", "31", null);
		}

		parms.clear();
		parms.put(Parameters.HUC.toString(), new String[]{"07090002"});
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", "1", null, null, null, "5", "5", null, null, null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "23", "23", null, null, null);
		}

		parms.clear();
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "10", "2", "1", "6", "1", "102", "12", "3", "70", "17");
		if (includeActivity) {
			assertActivityResults(counts, 5, "282", "48", "21", "178", "35");
		}

		try {
			parms.clear();
			parms.put(Parameters.NLDIURL.toString(), getSourceFile("manySites.txt").split(","));
			counts = countDao.getCounts(namespace, parms);
			assertResults(includeResults, counts, 2, "3", null, null, "3", null, "30", null, null, "30", null);
			if (includeActivity) {
				assertActivityResults(counts, 2, "84", null, null, "84", null);
			}
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		parms.clear();
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "10", "2", "2", "6", null, "86", "12", "4", "70", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "266", "48", "40", "178", null);
		}

		parms.clear();
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "11", "2", "2", "7", null, "86", "12", "4", "70", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "266", "48", "40", "178", null);
		}

		parms.clear();
		parms.put(Parameters.SITEID.toString(), getSiteid());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "9", "2", "2", "5", null, "75", "12", "4", "59", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "237", "48", "40", "149", null);
		}

		try {
			parms.clear();
			parms.put(Parameters.SITEID.toString(), getSourceFile("manySites.txt").split(","));
			counts = countDao.getCounts(namespace, parms);
			assertResults(includeResults, counts, 2, "3", null, null, "3", null, "30", null, null, "30", null);
			if (includeActivity) {
				assertActivityResults(counts, 2, "84", null, null, "84", null);
			}
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}

		parms.clear();
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "11", "1", "2", "7", "1", "96", "5", "4", "70", "17");
		if (includeActivity) {
			assertActivityResults(counts, 5, "276", "23", "40", "178", "35");
		}

		parms.clear();
		parms.put(Parameters.STATE.toString(), getState());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "10", "2", "2", "6", null, "86", "12", "4", "70", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "266", "48", "40", "178", null);
		}

		parms.clear();
		parms.put(Parameters.WITHIN.toString(), getWithin());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "10", "2", "2", "6", null, "73", "12", "4", "57", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "235", "48", "40", "147", null);
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Single Parameter Counts against activity_sum

		parms.clear();
		parms.put(Parameters.PROJECT.toString(), getProject());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "8", "2", "2", "3", "1", "469", "130", "118", "140", "81");
		if (includeActivity) {
			assertActivityResults(counts, 5, "8", "2", "2", "3", "1");
		}

		parms.clear();
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "11", "2", "2", "6", "1", "981", "130", "118", "652", "81");
		if (includeActivity) {
			assertActivityResults(counts, 5, "15", "2", "2", "10", "1");
		}

		parms.clear();
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "11", "2", "2", "6", "1", "981", "130", "118", "652", "81");
		if (includeActivity) {
			assertActivityResults(counts, 5, "15", "2", "2", "10", "1");
		}

		parms.clear();
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "9", "2", "2", "4", "1", "672", "195", "177", "219", "81");
		if (includeActivity) {
			assertActivityResults(counts, 5, "11", "3", "3", "4", "1");
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Single Parameter Counts against result_sum

		parms.clear();
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "4", "1", null, "3", null, "199", "101", null, "98", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "5", "2", null, "3", null);
		}

		parms.clear();
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "5", null, null, "4", "1", "312", null, null, "201", "111");
		if (includeActivity) {
			assertActivityResults(counts, 3, "5", null, null, "4", "1");
		}

		parms.clear();
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "4", null, null, "4", null, "201", null, null, "201", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "4", null, null, "4", null);
		}

		parms.clear();
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "4", null, "1", "3", null, "203", null, "105", "98", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "5", null, "2", "3", null);
		}

		parms.clear();
		parms.put(Parameters.PCODE.toString(), getPcode());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "3", "1", null, "2", null, "155", "107", null, "48", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "3", "1", null, "2", null);
		}

		parms.clear();
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "4", null, null, "3", "1", "209", null, null, "98", "111");
		if (includeActivity) {
			assertActivityResults(counts, 3, "4", null, null, "3", "1");
		}
	}

	public void multipleParameterTests(String namespace, boolean includeActivity, boolean includeResults) {
		Map<String, Object> parms = new HashMap<>();

		//Counts against station_sum

		parms.clear();
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.WITHIN.toString(), getWithin());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		List<Map<String, Object>> counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "3", "1", null, "2", null, "24", "5", null, "19", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "78", "23", null, "55", null);
		}

		//show that the various siteid lists are AND'd together
		parms.put(Parameters.NLDIURL.toString(), getNldiSites());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 3, "2", "1", null, "1", null, "14", "5", null, "9", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "50", "23", null, "27", null);
		}

		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", null, null, "1", null, "9", null, null, "9", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "27", null, null, "27", null);
		}


		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Counts against activity_sum

		parms.clear();
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 5, "7", "2", "2", "2", "1", "469", "130", "118", "140", "81");
		if (includeActivity) {
			assertActivityResults(counts, 5, "7", "2", "2", "2", "1");
		}

		parms.put(Parameters.WITHIN.toString(), getWithin());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "6", "2", "2", "2", null, "388", "130", "118", "140", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "6", "2", "2", "2", null);
		}

		parms.put(Parameters.BBOX.toString(), getBBox());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 4, "5", "2", "2", "1", null, "316", "130", "118", "68", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "5", "2", "2", "1", null);
		}

		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", null, null, "1", null, "68", null, null, "68", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "1", null, null, "1", null);
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Counts against result_sum

		parms.clear();
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.NLDIURL.toString(), getNldiSites());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PCODE.toString(), getPcode());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		parms.put(Parameters.WITHIN.toString(), getWithin());
		counts = countDao.getCounts(namespace, parms);
		counts = countDao.getCounts(namespace, parms);
		assertResults(includeResults, counts, 2, "1", null, null, "1", null, "48", null, null, "48", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "1", null, null, "1", null);
		}

	}

	private void assertResults(boolean includeResults, List<Map<String, Object>> counts, int expectedSize,
			String expectedTotalStation, String expectedNwisStation, String expectedStewardsStation, String expectedStoretStation,
			String expectedBiodataStation,
			String expectedTotalResult, String expectedNwisResult, String expectedStewardsResult,
			String expectedStoretResult, String expectedBiodataResult) {
		LOG.error(counts.toString());
		assertResults(counts, MybatisConstants.STATION_COUNT, expectedSize,
				expectedTotalStation, expectedNwisStation, expectedStewardsStation, expectedStoretStation,
				expectedBiodataStation);
		if (includeResults) {
			assertResults(counts, MybatisConstants.RESULT_COUNT, expectedSize,
					expectedTotalResult, expectedNwisResult, expectedStewardsResult, expectedStoretResult,
					expectedBiodataResult);
		}
	}

	private void assertActivityResults(List<Map<String, Object>> counts, int size,
			String total, String nwis, String stewards, String storet, String biodata) {
		assertResults(counts, MybatisConstants.ACTIVITY_COUNT, size, total, nwis, stewards, storet, biodata);
	}

	private void assertResults(List<Map<String, Object>> counts, String countType, int expectedSize,
			String expectedTotal, String expectedNwis, String expectedStewards, String expectedStoret,
			String expectedBiodata) {
		assertEquals("Number of counts", expectedSize, counts.size());
		boolean nwis = (null == expectedNwis);
		boolean stewards = (null == expectedStewards);
		boolean storet = (null == expectedStoret);
		boolean biodata = (null == expectedBiodata);
		boolean total = false;
		for (int i = 0 ; i < counts.size() ; i++) {
			if (null == counts.get(i).get(MybatisConstants.DATA_SOURCE)) {
				assertEquals("total " + countType + " count", expectedTotal, counts.get(i).get(countType).toString());
				total = true;
			} else {
				switch (counts.get(i).get(MybatisConstants.DATA_SOURCE).toString()) {
				case NWIS:
					assertEquals("NWIS " + countType + " count", expectedNwis, counts.get(i).get(countType).toString());
					nwis = true;
					break;
				case STEWARDS:
					assertEquals("STEWARDS " + countType + " count", expectedStewards, counts.get(i).get(countType).toString());
					stewards = true;
					break;
				case STORET:
					assertEquals("STORET " + countType + " count", expectedStoret, counts.get(i).get(countType).toString());
					storet = true;
					break;
				case BIODATA:
					assertEquals("BIODATA " + countType + " count", expectedBiodata, counts.get(i).get(countType).toString());
					biodata = true;
					break;
				default:
					break;
				}
			}
		}
		assertTrue("Did not get " + countType + " Total", total);
		assertTrue("Did not get " + countType + " NWIS", nwis);
		assertTrue("Did not get " + countType + " STEWARDS", stewards);
		assertTrue("Did not get " + countType + " STORET", storet);
		assertTrue("Did not get " + countType + " BIODATA", biodata);
	}

}
