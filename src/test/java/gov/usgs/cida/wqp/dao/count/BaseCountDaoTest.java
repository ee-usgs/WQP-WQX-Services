package gov.usgs.cida.wqp.dao.count;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.dao.CountDao;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.MybatisConstants;

public abstract class BaseCountDaoTest extends BaseSpringTest {
	private static final Logger LOG = LoggerFactory.getLogger(BaseCountDaoTest.class);

	@Autowired 
	CountDao countDao;

	Map<String, Object> parms;
	List<Map<String, Object>> counts;

	@Before
	public void init() {
		parms = new HashMap<>();
	}

	@After
	public void cleanup() {
		parms = null;
	}

	public void nullParameterTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		counts = countDao.getCounts(namespace, null);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 5, TOTAL_SITE_COUNT_MINUS_1, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT_MINUS_1, BIODATA_SITE_COUNT);
		} else {
			assertStationResults(counts, 5, TOTAL_SITE_COUNT, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT);
		}
		if (includeActivity) {
			assertActivityResults(counts, 5, TOTAL_ACTIVITY_COUNT, NWIS_ACTIVITY_COUNT, STEWARDS_ACTIVITY_COUNT, STORET_ACTIVITY_COUNT, BIODATA_ACTIVITY_COUNT);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 5, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT, BIODATA_ACTIVITY_METRIC_COUNT);
		}
		if (includeResults) {
			assertResultResults(counts, 5, TOTAL_RESULT_COUNT, NWIS_RESULT_COUNT, STEWARDS_RESULT_COUNT, STORET_RESULT_COUNT, BIODATA_RESULT_COUNT);
		}
	}

	public void emptyParameterTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 5, TOTAL_SITE_COUNT_MINUS_1, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT_MINUS_1, BIODATA_SITE_COUNT);
		} else {
			assertStationResults(counts, 5, TOTAL_SITE_COUNT, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT);
		}
		if (includeActivity) {
			assertActivityResults(counts, 5, TOTAL_ACTIVITY_COUNT, NWIS_ACTIVITY_COUNT, STEWARDS_ACTIVITY_COUNT, STORET_ACTIVITY_COUNT, BIODATA_ACTIVITY_COUNT);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 5, TOTAL_ACTIVITY_METRIC_COUNT, NWIS_ACTIVITY_METRIC_COUNT, STEWARDS_ACTIVITY_METRIC_COUNT, STORET_ACTIVITY_METRIC_COUNT, BIODATA_ACTIVITY_METRIC_COUNT);
		}
		if (includeResults) {
			assertResultResults(counts, 5, TOTAL_RESULT_COUNT, NWIS_RESULT_COUNT, STEWARDS_RESULT_COUNT, STORET_RESULT_COUNT, BIODATA_RESULT_COUNT);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against station_sum

	public void avoidTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 2, STORET_SITE_COUNT_MINUS_1, null, null, STORET_SITE_COUNT_MINUS_1, null);
		} else {
			assertStationResults(counts, 2, STORET_SITE_COUNT, null, null, STORET_SITE_COUNT, null);
		}
		if (includeActivity) {
			assertActivityResults(counts, 2, STORET_ACTIVITY_COUNT, null, null, STORET_ACTIVITY_COUNT, null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 2, STORET_ACTIVITY_METRIC_COUNT, null, null, STORET_ACTIVITY_METRIC_COUNT, null);
		}
		if (includeResults) {
			assertResultResults(counts, 2, STORET_RESULT_COUNT, null, null, STORET_RESULT_COUNT, null);
		}
	}

	public void bboxTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.BBOX.toString(), getBBox());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 4, "8", "2", "2", "4", null);
		} else {
			assertStationResults(counts, 4, "9", "2", "2", "5", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, 4, "17", "3", "3", "11", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 4, "22", "3", "3", "16", null);
		}
		if (includeResults) {
			assertResultResults(counts, 4, "31", "5", "3", "23", null);
		}
	}

	public void countryTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 5, "10", "2", "2", "5", "1");
		} else {
			assertStationResults(counts, 5, "11", "2", "2", "6", "1");
		}
		if (includeActivity) {
			assertActivityResults(counts, 5, "19", "3", "3", "12", "1");
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 5, "24", "3", "3", "17", "1");
		}
		if (includeResults) {
			assertResultResults(counts, 5, "33", "5", "3", "24", "1");
		}
	}

	public void countyTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.COUNTY.toString(), getCounty());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 4, "9", "2", "2", "5", null);
		} else {
			assertStationResults(counts, 4, "10", "2", "2", "6", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, 4, "18", "3", "3", "12", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 4, "23", "3", "3", "17", null);
		}
		if (includeResults) {
			assertResultResults(counts, 4, "32", "5", "3", "24", null);
		}
	}

	public void huc2Test(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.HUC.toString(), new String[]{"07"});
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 4, "6", "2", "2", "2", null);
		} else {
			assertStationResults(counts, 4, "7", "2", "2", "3", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, 4, "11", "3", "3", "5", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 4, "16", "3", "3", "10", null);
		}
		if (includeResults) {
			assertResultResults(counts, 4, "23", "5", "3", "15", null);
		}
	}

	public void huc4Test(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.HUC.toString(), new String[]{"0708"});
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 3, "2", null, "1", "1", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "5", null, "2", "3", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 3, "5", null, "2", "3", null);
		}
		if (includeResults) {
			assertResultResults(counts, 3, "6", null, "2", "4", null);
		}
	}

	public void huc6Test(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.HUC.toString(), new String[]{"070801"});
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 3, "2", null, "1", "1", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "5", null, "2", "3", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 3, "5", null, "2", "3", null);
		}
		if (includeResults) {
			assertResultResults(counts, 3, "6", null, "2", "4", null);
		}
	}

	public void huc8Test(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.HUC.toString(), new String[]{"07090002"});
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 2, "1", "1", null, null, null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "2", "2", null, null, null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 2, "2", "2", null, null, null);
		}
		if (includeResults) {
			assertResultResults(counts, 2, "4", "4", null, null, null);
		}
	}

	public void minActivitiesTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 4, "7", "1", "1", "5", null);
		if (includeActivity) {
			assertActivityResults(counts, 4, "19", "2", "2", "15", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 4, "24", "2", "2", "20", null);
		}
		if (includeResults) {
			assertResultResults(counts, 4, "55", "4", "2", "49", null);
		}
	}

	public void minResultsTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 3, "6", "1", null, "5", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "17", "2", null, "15", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 3, "22", "2", null, "20", null);
		}
		if (includeResults) {
			assertResultResults(counts, 3, "53", "4", null, "49", null);
		}
	}

	public void nldiUrlTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		try {
			parms.put(Parameters.NLDIURL.toString(), getManySiteId());
			counts = countDao.getCounts(namespace, parms);
			assertStationResults(counts, 2, "3", null, null, "3", null);
			if (includeActivity) {
				assertActivityResults(counts, 2, "8", null, null, "8", null);
			}
			if (includeActivityMetric) {
				assertActivityMetricResults(counts, 2, "13", null, null, "13", null);
			}
			if (includeResults) {
				assertResultResults(counts, 2, "19", null, null, "19", null);
			}
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

	public void organizationTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 4, "9", "2", "2", "5", null);
		} else {
			assertStationResults(counts, 4, "10", "2", "2", "6", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, 4, "18", "3", "3", "12", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 4, "23", "3", "3", "17", null);
		}
		if (includeResults) {
			assertResultResults(counts, 4, "32", "5", "3", "24", null);
		}
	}

	public void providersTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 4, "10", "2", "2", "6", null);
		} else {
			assertStationResults(counts, 4, "11", "2", "2", "7", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, 4, "22", "3", "3", "16", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 4, "27", "3", "3", "21", null);
		}
		if (includeResults) {
			assertResultResults(counts, 4, "58", "5", "3", "50", null);
		}
	}

	public void siteIdTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.SITEID.toString(), getSiteid());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 4, "8", "2", "2", "4", null);
		} else {
			assertStationResults(counts, 4, "9", "2", "2", "5", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, 4, "16", "3", "3", "10", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 4, "16", "3", "3", "10", null);
		}
		if (includeResults) {
			assertResultResults(counts, 4, "21", "5", "3", "13", null);
		}
	}

	public void manySitesTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		try {
			parms.put(Parameters.SITEID.toString(), getManySiteId());
			counts = countDao.getCounts(namespace, parms);
			assertStationResults(counts, 2, "3", null, null, "3", null);
			if (includeActivity) {
				assertActivityResults(counts, 2, "8", null, null, "8", null);
			}
			if (includeActivityMetric) {
				assertActivityMetricResults(counts, 2, "13", null, null, "13", null);
			}
			if (includeResults) {
				assertResultResults(counts, 2, "19", null, null, "19", null);
			}
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
	}

	public void siteTypeTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 5, "10", "1", "2", "6", "1");
		} else {
			assertStationResults(counts, 5, "11", "1", "2", "7", "1");
		}
		if (includeActivity) {
			assertActivityResults(counts, 5, "22", "2", "3", "16", "1");
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 5, "27", "2", "3", "21", "1");
		}
		if (includeResults) {
			assertResultResults(counts, 5, "58", "4", "3", "50", "1");
		}
	}

	public void stateTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.STATE.toString(), getState());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 4, "9", "2", "2", "5", null);
		} else {
			assertStationResults(counts, 4, "10", "2", "2", "6", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, 4, "18", "3", "3", "12", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 4, "23", "3", "3", "17", null);
		}
		if (includeResults) {
			assertResultResults(counts, 4, "32", "5", "3", "24", null);
		}
	}

	public void withinTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.WITHIN.toString(), getWithin());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		counts = countDao.getCounts(namespace, parms);
		if (includeActivity || includeActivityMetric || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, 4, "9", "2", "2", "5", null);
		} else {
			assertStationResults(counts, 4, "10", "2", "2", "6", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, 4, "19", "3", "3", "13", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 4, "24", "3", "3", "18", null);
		}
		if (includeResults) {
			assertResultResults(counts, 4, "54", "5", "3", "46", null);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against activity_sum

	public void projectTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.PROJECT.toString(), getProject());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 5, "9", "2", "2", "4", "1");
		if (includeActivity) {
			assertActivityResults(counts, 5, "14", "2", "2", "9", "1");
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 5, "14", "2", "2", "9", "1");
		}
		if (includeResults) {
			assertResultResults(counts, 5, "19", "4", "2", "12", "1");
		}
	}

	public void sampleMediaTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 5, "11", "2", "2", "6", "1");
		if (includeActivity) {
			assertActivityResults(counts, 5, "21", "2", "2", "16", "1");
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 5, "26", "2", "2", "21", "1");
		}
		if (includeResults) {
			assertResultResults(counts, 5, "57", "4", "2", "50", "1");
		}
	}

	public void startDateHiTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 5, "11", "2", "2", "6", "1");
		if (includeActivity) {
			assertActivityResults(counts, 5, "21", "2", "2", "16", "1");
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 5, "26", "2", "2", "21", "1");
		}
		if (includeResults) {
			assertResultResults(counts, 5, "57", "4", "2", "50", "1");
		}
	}

	public void startDateLoTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 5, "9", "2", "2", "4", "1");
		if (includeActivity) {
			assertActivityResults(counts, 5, "17", "3", "3", "10", "1");
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 5, "17", "3", "3", "10", "1");
		}
		if (includeResults) {
			assertResultResults(counts, 5, "22", "5", "3", "13", "1");
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against result_sum

	public void analyticalMethodTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.ANALYTICAL_METHOD.toString(), getAnalyticalMethod());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 3, "5", "1", null, "4", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "12", "2", null, "10", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 3, "12", "2", null, "10", null);
		}
		if (includeResults) {
			assertResultResults(counts, 3, "17", "4", null, "13", null);
		}
	}

	public void assemblageTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.ASSEMBLAGE.toString(), getAssemblage());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 3, "5", null, null, "4", "1");
		if (includeActivity) {
			assertActivityResults(counts, 3, "11", null, null, "10", "1");
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 3, "11", null, null, "10", "1");
		}
		if (includeResults) {
			assertResultResults(counts, 3, "14", null, null, "13", "1");
		}
	}

	public void characteristicNameTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.CHARACTERISTIC_NAME.toString(), getCharacteristicName());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 2, "4", null, null, "4", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "10", null, null, "10", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 2, "10", null, null, "10", null);
		}
		if (includeResults) {
			assertResultResults(counts, 2, "13", null, null, "13", null);
		}
	}

	public void characteristicTypeTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.CHARACTERISTIC_TYPE.toString(), getCharacteristicType());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 3, "5", null, "1", "4", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "12", null, "2", "10", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 3, "12", null, "2", "10", null);
		}
		if (includeResults) {
			assertResultResults(counts, 3, "15", null, "2", "13", null);
		}
	}

	public void pcodeTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.PCODE.toString(), getPcode());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 3, "4", "1", null, "3", null);
		if (includeActivity) {
			assertActivityResults(counts, 3, "10", "1", null, "9", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 3, "10", "1", null, "9", null);
		}
		if (includeResults) {
			assertResultResults(counts, 3, "13", "1", null, "12", null);
		}
	}

	public void subjectTaxonomicNameTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.SUBJECT_TAXONOMIC_NAME.toString(), getSubjectTaxonomicName());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 3, "5", null, null, "4", "1");
		if (includeActivity) {
			assertActivityResults(counts, 3, "10", null, null, "9", "1");
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 3, "10", null, null, "9", "1");
		}
		if (includeResults) {
			assertResultResults(counts, 3, "13", null, null, "12", "1");
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void multipleParameterStationSumTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.COUNTRY.toString(), getCountry());
		parms.put(Parameters.COUNTY.toString(), getCounty());
		parms.put(Parameters.HUC.toString(), getHuc());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.NLDIURL.toString(), getNldiSites());
		parms.put(Parameters.ORGANIZATION.toString(), getOrganization());
		parms.put(Parameters.PROVIDERS.toString(), getProviders());
		parms.put(Parameters.SITEID.toString(), getSiteid());
		parms.put(Parameters.SITE_TYPE.toString(), getSiteType());
		parms.put(Parameters.STATE.toString(), getState());
		parms.put(Parameters.WITHIN.toString(), getWithin());
		List<Map<String, Object>> counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 2, "1", null, null, "1", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "2", null, null, "2", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 2, "2", null, null, "2", null);
		}
		if (includeResults) {
			assertResultResults(counts, 2, "4", null, null, "4", null);
		}
	}

	public void multipleParameterActivitySumTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 2, "3", null, null, "3", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "8", null, null, "8", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 2, "8", null, null, "8", null);
		}
		if (includeResults) {
			assertResultResults(counts, 2, "11", null, null, "11", null);
		}
	}

	public void multipleParameterActivitySumStationSumTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.put(Parameters.AVOID.toString().replace(".", ""), getAvoid());
		parms.put(Parameters.BBOX.toString(), getBBox());
		parms.put(Parameters.LATITUDE.toString(), getLatitude());
		parms.put(Parameters.LONGITUDE.toString(), getLongitude());
		parms.put(Parameters.MIN_ACTIVITIES.toString(), getMinActivities());
		parms.put(Parameters.MIN_RESULTS.toString(), getMinResults());
		parms.put(Parameters.PROJECT.toString(), getProject());
		parms.put(Parameters.SAMPLE_MEDIA.toString(), getSampleMedia());
		parms.put(Parameters.START_DATE_HI.toString(), getStartDateHi());
		parms.put(Parameters.START_DATE_LO.toString(), getStartDateLo());
		parms.put(Parameters.WITHIN.toString(), getWithin());
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 2, "2", null, null, "2", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "6", null, null, "6", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 2, "6", null, null, "6", null);
		}
		if (includeResults) {
			assertResultResults(counts, 2, "8", null, null, "8", null);
		}
	}

	public void multipleParameterResultSumTest(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
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
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 2, "2", null, null, "2", null);
		if (includeActivity) {
			assertActivityResults(counts, 2, "4", null, null, "4", null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 2, "4", null, null, "4", null);
		}
		if (includeResults) {
			assertResultResults(counts, 2, "7", null, null, "7", null);
		}
	}

	public void multipleParameterResultSumStationSumTests(String namespace, boolean includeActivity, boolean includeActivityMetric, boolean includeResults) {
		parms.clear();
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
		counts = countDao.getCounts(namespace, parms);
		assertStationResults(counts, 2, FILTERED_TOTAL_SITE_COUNT, null, null, FILTERED_STORET_SITE_COUNT, null);
		if (includeActivity) {
			assertActivityResults(counts, 2, FILTERED_TOTAL_ACTIVITY_COUNT, null, null, FILTERED_STORET_ACTIVITY_COUNT, null);
		}
		if (includeActivityMetric) {
			assertActivityMetricResults(counts, 2, FILTERED_TOTAL_ACTIVITY_COUNT, null, null, FILTERED_STORET_ACTIVITY_COUNT, null);
		}
		if (includeResults) {
			assertResultResults(counts, 2, FILTERED_TOTAL_RESULT_COUNT, null, null, FILTERED_STORET_RESULT_COUNT, null);
		}
	}

	private void assertStationResults(List<Map<String, Object>> counts, int size,
			String total, String nwis, String stewards, String storet, String biodata) {
		LOG.error(counts.toString());
		assertResults(counts, MybatisConstants.STATION_COUNT, size, total, nwis, stewards, storet, biodata);
	}

	private void assertResultResults(List<Map<String, Object>> counts, int size,
			String total, String nwis, String stewards, String storet, String biodata) {
		assertResults(counts, MybatisConstants.RESULT_COUNT, size, total, nwis, stewards, storet, biodata);
	}

	private void assertActivityResults(List<Map<String, Object>> counts, int size,
			String total, String nwis, String stewards, String storet, String biodata) {
		assertResults(counts, MybatisConstants.ACTIVITY_COUNT, size, total, nwis, stewards, storet, biodata);
	}

	private void assertActivityMetricResults(List<Map<String, Object>> counts, int size,
			String total, String nwis, String stewards, String storet, String biodata) {
		assertResults(counts, MybatisConstants.ACTIVITY_METRIC_COUNT, size, total, nwis, stewards, storet, biodata);
	}

	private void assertResults(List<Map<String, Object>> counts, String countType, int expectedSize,
			String expectedTotal, String expectedNwis, String expectedStewards, String expectedStoret,
			String expectedBiodata) {
		assertEquals("Number of counts", expectedSize, counts.size());
		boolean nwis = (null == expectedNwis);
		boolean stewards = (null == expectedStewards);
		boolean storet = (null == expectedStoret);
		boolean biodata = (null == expectedBiodata);
		boolean total = (null == expectedTotal);
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
