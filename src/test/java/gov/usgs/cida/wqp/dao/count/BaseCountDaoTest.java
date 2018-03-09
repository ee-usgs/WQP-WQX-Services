package gov.usgs.cida.wqp.dao.count;

import static gov.usgs.cida.wqp.swagger.model.StationCountJson.BIODATA;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.NWIS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STEWARDS;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.dao.CountDao;
import gov.usgs.cida.wqp.dao.FilteredStationDaoTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.CountColumn;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public abstract class BaseCountDaoTest extends FilteredStationDaoTest {

	@Autowired 
	CountDao countDao;

	protected List<Map<String, Object>> nullParameterTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = nullParameterTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> emptyParameterTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = emptyParameterTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> sortedTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = sortedTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> zipTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = zipTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> activityTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = activityTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> nldiUrlTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = nldiUrlTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> siteUrlBaseTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = siteUrlBaseTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> resultTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = resultTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected void mimeTypeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = mimeTypeJsonTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeGeoJsonTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeKmlTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeKmzTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeCsvTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeTsvTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeXmlTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeXlsxTest(nameSpace, 5);
		assertFullDbReturned(counts, includeActivity, includeResults);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against station_sum

	protected List<Map<String, Object>> avoidTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = avoidTest(nameSpace, 2);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, STORET_SITE_COUNT_MINUS_1, null, null, STORET_SITE_COUNT_MINUS_1, null);
		} else {
			assertStationResults(counts, STORET_SITE_COUNT, null, null, STORET_SITE_COUNT, null);
		}
		if (includeActivity) {
			assertActivityResults(counts, STORET_ACTIVITY_COUNT, null, null, STORET_ACTIVITY_COUNT, null);
		}
		if (includeResults) {
			assertResultResults(counts, STORET_RESULT_COUNT, null, null, STORET_RESULT_COUNT, null);
		}
		return counts;
	}

	protected List<Map<String, Object>> bboxTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = bboxTest(nameSpace, 4);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "8", "2", "2", "4", null);
		} else {
			assertStationResults(counts, "9", "2", "2", "5", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "17", "3", "3", "11", null);
		}
		if (includeResults) {
			assertResultResults(counts, "31", "5", "3", "23", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> countryTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = countryTest(nameSpace, 5);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "10", "2", "2", "5", "1");
		} else {
			assertStationResults(counts, "11", "2", "2", "6", "1");
		}
		if (includeActivity) {
			assertActivityResults(counts, "19", "3", "3", "12", "1");
		}
		if (includeResults) {
			assertResultResults(counts, "33", "5", "3", "24", "1");
		}
		return counts;
	}

	protected List<Map<String, Object>> countyTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = countyTest(nameSpace, 4);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "9", "2", "2", "5", null);
		} else {
			assertStationResults(counts, "10", "2", "2", "6", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "18", "3", "3", "12", null);
		}
		if (includeResults) {
			assertResultResults(counts, "32", "5", "3", "24", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> huc2Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc2Test(nameSpace, 4);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "6", "2", "2", "2", null);
		} else {
			assertStationResults(counts, "7", "2", "2", "3", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "11", "3", "3", "5", null);
		}
		if (includeResults) {
			assertResultResults(counts, "23", "5", "3", "15", null);
		}
		return counts;
	}


	protected List<Map<String, Object>> huc3Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc3Test(nameSpace, 4);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "6", "2", "2", "2", null);
		} else {
			assertStationResults(counts, "7", "2", "2", "3", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "11", "3", "3", "5", null);
		}
		if (includeResults) {
			assertResultResults(counts, "23", "5", "3", "15", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> huc4Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc4Test(nameSpace, 3);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "3", "2", null, "1", null);
		} else {
			assertStationResults(counts, "4", "2", null, "2", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "5", "3", null, "2", null);
		}
		if (includeResults) {
			assertResultResults(counts, "16", "5", null, "11", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> huc5Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc5Test(nameSpace, 3);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "3", "2", null, "1", null);
		} else {
			assertStationResults(counts, "4", "2", null, "2", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "5", "3", null, "2", null);
		}
		if (includeResults) {
			assertResultResults(counts, "16", "5", null, "11", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> huc6Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc6Test(nameSpace, 3);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "2", "1", null, "1", null);
		} else {
			assertStationResults(counts, "3", "1", null, "2", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "4", "2", null, "2", null);
		}
		if (includeResults) {
			assertResultResults(counts, "15", "4", null, "11", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> huc7Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc7Test(nameSpace, 3);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "2", "1", null, "1", null);
		} else {
			assertStationResults(counts, "3", "1", null, "2", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "4", "2", null, "2", null);
		}
		if (includeResults) {
			assertResultResults(counts, "15", "4", null, "11", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> huc8Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc8Test(nameSpace, 2);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "1", null, null, "1", null);
		} else {
			assertStationResults(counts, "2", null, null, "2", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "2", null, null, "2", null);
		}
		if (includeResults) {
			assertResultResults(counts, "11", null, null, "11", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> huc10Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc10Test(nameSpace, 2);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "1", null, null, "1", null);
		} else {
			assertStationResults(counts, "2", null, null, "2", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "2", null, null, "2", null);
		}
		if (includeResults) {
			assertResultResults(counts, "11", null, null, "11", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> huc12Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc12Test(nameSpace, 2);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "1", null, null, "1", null);
		} else {
			assertStationResults(counts, "2", null, null, "2", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "2", null, null, "2", null);
		}
		if (includeResults) {
			assertResultResults(counts, "11", null, null, "11", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> minActivitiesTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = minActivitiesTest(nameSpace, 4);
		assertStationResults(counts, "7", "1", "1", "5", null);
		if (includeActivity) {
			assertActivityResults(counts, "19", "2", "2", "15", null);
		}
		if (includeResults) {
			assertResultResults(counts, "55", "4", "2", "49", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> minResultsTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = minResultsTest(nameSpace, 3);
		assertStationResults(counts, "6", "1", null, "5", null);
		if (includeActivity) {
			assertActivityResults(counts, "17", "2", null, "15", null);
		}
		if (includeResults) {
			assertResultResults(counts, "53", "4", null, "49", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> nldiSitesTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = nldiSitesTest(nameSpace, 2);
		assertStationResults(counts, "3", null, null, "3", null);
		if (includeActivity) {
			assertActivityResults(counts, "8", null, null, "8", null);
		}
		if (includeResults) {
			assertResultResults(counts, "19", null, null, "19", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> organizationTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = organizationTest(nameSpace, 4);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "9", "2", "2", "5", null);
		} else {
			assertStationResults(counts, "10", "2", "2", "6", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "18", "3", "3", "12", null);
		}
		if (includeResults) {
			assertResultResults(counts, "32", "5", "3", "24", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> providersTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = providersTest(nameSpace, 4);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "10", "2", "2", "6", null);
		} else {
			assertStationResults(counts, "11", "2", "2", "7", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "22", "3", "3", "16", null);
		}
		if (includeResults) {
			assertResultResults(counts, "58", "5", "3", "50", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> siteIdTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = siteIdTest(nameSpace, 4);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "8", "2", "2", "4", null);
		} else {
			assertStationResults(counts, "9", "2", "2", "5", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "16", "3", "3", "10", null);
		}
		if (includeResults) {
			assertResultResults(counts, "21", "5", "3", "13", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> siteIdLargeListTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = siteIdLargeListTest(nameSpace, 2);
		assertStationResults(counts, "3", null, null, "3", null);
		if (includeActivity) {
			assertActivityResults(counts, "8", null, null, "8", null);
		}
		if (includeResults) {
			assertResultResults(counts, "19", null, null, "19", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> siteTypeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = siteTypeTest(nameSpace, 5);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "10", "1", "2", "6", "1");
		} else {
			assertStationResults(counts, "11", "1", "2", "7", "1");
		}
		if (includeActivity) {
			assertActivityResults(counts, "22", "2", "3", "16", "1");
		}
		if (includeResults) {
			assertResultResults(counts, "58", "4", "3", "50", "1");
		}
		return counts;
	}

	protected List<Map<String, Object>> stateTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = stateTest(nameSpace, 4);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "9", "2", "2", "5", null);
		} else {
			assertStationResults(counts, "10", "2", "2", "6", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "18", "3", "3", "12", null);
		}
		if (includeResults) {
			assertResultResults(counts, "32", "5", "3", "24", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> withinTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = withinTest(nameSpace, 4);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "9", "2", "2", "5", null);
		} else {
			assertStationResults(counts, "10", "2", "2", "6", null);
		}
		if (includeActivity) {
			assertActivityResults(counts, "19", "3", "3", "13", null);
		}
		if (includeResults) {
			assertResultResults(counts, "54", "5", "3", "46", null);
		}
		return counts;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against activity_sum

	protected List<Map<String, Object>> projectTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = projectTest(nameSpace, 5);
		assertStationResults(counts, "9", "2", "2", "4", "1");
		if (includeActivity) {
			assertActivityResults(counts, "14", "2", "2", "9", "1");
		}
		if (includeResults) {
			assertResultResults(counts, "19", "4", "2", "12", "1");
		}
		return counts;
	}

	protected List<Map<String, Object>> sampleMediaTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = sampleMediaTest(nameSpace, 5);
		assertStationResults(counts, "11", "2", "2", "6", "1");
		if (includeActivity) {
			assertActivityResults(counts, "21", "2", "2", "16", "1");
		}
		if (includeResults) {
			assertResultResults(counts, "57", "4", "2", "50", "1");
		}
		return counts;
	}

	protected List<Map<String, Object>> startDateHiTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = startDateHiTest(nameSpace, 5);
		assertStationResults(counts, "11", "2", "2", "6", "1");
		if (includeActivity) {
			assertActivityResults(counts, "21", "2", "2", "16", "1");
		}
		if (includeResults) {
			assertResultResults(counts, "57", "4", "2", "50", "1");
		}
		return counts;
	}

	protected List<Map<String, Object>> startDateLoTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = startDateLoTest(nameSpace, 5);
		assertStationResults(counts, "9", "2", "2", "4", "1");
		if (includeActivity) {
			assertActivityResults(counts, "17", "3", "3", "10", "1");
		}
		if (includeResults) {
			assertResultResults(counts, "22", "5", "3", "13", "1");
		}
		return counts;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against result_sum

	protected List<Map<String, Object>> analyticalMethodTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = analyticalMethodTest(nameSpace, 3);
		assertStationResults(counts, "5", "1", null, "4", null);
		if (includeActivity) {
			assertActivityResults(counts, "12", "2", null, "10", null);
		}
		if (includeResults) {
			assertResultResults(counts, "17", "4", null, "13", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> assemblageTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = assemblageTest(nameSpace, 3);
		assertStationResults(counts, "5", null, null, "4", "1");
		if (includeActivity) {
			assertActivityResults(counts, "11", null, null, "10", "1");
		}
		if (includeResults) {
			assertResultResults(counts, "14", null, null, "13", "1");
		}
		return counts;
	}

	protected List<Map<String, Object>> characteristicNameTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = characteristicNameTest(nameSpace, 2);
		assertStationResults(counts, "4", null, null, "4", null);
		if (includeActivity) {
			assertActivityResults(counts, "10", null, null, "10", null);
		}
		if (includeResults) {
			assertResultResults(counts, "13", null, null, "13", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> characteristicTypeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = characteristicTypeTest(nameSpace, 3);
		assertStationResults(counts, "5", null, "1", "4", null);
		if (includeActivity) {
			assertActivityResults(counts, "12", null, "2", "10", null);
		}
		if (includeResults) {
			assertResultResults(counts, "15", null, "2", "13", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> pcodeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = pcodeTest(nameSpace, 3);
		assertStationResults(counts, "4", "1", null, "3", null);
		if (includeActivity) {
			assertActivityResults(counts, "10", "1", null, "9", null);
		}
		if (includeResults) {
			assertResultResults(counts, "13", "1", null, "12", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> subjectTaxonomicNameTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = subjectTaxonomicNameTest(nameSpace, 3);
		assertStationResults(counts, "5", null, null, "4", "1");
		if (includeActivity) {
			assertActivityResults(counts, "10", null, null, "9", "1");
		}
		if (includeResults) {
			assertResultResults(counts, "13", null, null, "12", "1");
		}
		return counts;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	protected List<Map<String, Object>> multipleParameterStationSumTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = multipleParameterStationSumTest(nameSpace, 3);
		assertStationResults(counts, "3", "1", null, "2", null);
		if (includeActivity) {
			assertActivityResults(counts, "8", "2", null, "6", null);
		}
		if (includeResults) {
			assertResultResults(counts, "12", "4", null, "8", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> multipleParameterActivitySumTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		FilterParameters filter = getNoEffectParameters(nameSpace);

		filter.setCommand(getCommand());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		List<Map<String, Object>> counts = callDao(nameSpace, 2, filter);
		assertStationResults(counts, "3", null, null, "3", null);
		if (includeActivity) {
			assertActivityResults(counts, "8", null, null, "8", null);
		}
		if (includeResults) {
			assertResultResults(counts, "11", null, null, "11", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> multipleParameterActivitySumStationSumTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		FilterParameters filter = getNoEffectParameters(nameSpace);

		filter.setCommand(getCommand());
		filter.setBBox(getBBox());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setProject(getProject());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setWithin(getWithin());
		List<Map<String, Object>> counts = callDao(nameSpace, 2, filter);
		assertStationResults(counts, "2", null, null, "2", null);
		if (includeActivity) {
			assertActivityResults(counts, "6", null, null, "6", null);
		}
		if (includeResults) {
			assertResultResults(counts, "8", null, null, "8", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> multipleParameterResultSumTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		FilterParameters filter = getNoEffectParameters(nameSpace);

		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setCommand(getCommand());
		filter.setAssemblage(getAssemblage());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setNldiSites(getNldiSites());
		filter.setOrganization(getOrganization());
		filter.setPCode(getPcode());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSampleMedia(getSampleMedia());
		filter.setSiteid(getSiteId());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		List<Map<String, Object>> counts = callDao(nameSpace, 2, filter);
		assertStationResults(counts, "2", null, null, "2", null);
		if (includeActivity) {
			assertActivityResults(counts, "4", null, null, "4", null);
		}
		if (includeResults) {
			assertResultResults(counts, "7", null, null, "7", null);
		}
		return counts;
	}

	protected List<Map<String, Object>> multipleParameterResultSumStationSumTests(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		FilterParameters filter = getNoEffectParameters(nameSpace);

		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setCommand(getCommand());
		filter.setAssemblage(getAssemblage());
		filter.setBBox(getBBox());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setNldiSites(getNldiSites());
		filter.setOrganization(getOrganization());
		filter.setPCode(getPcode());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSampleMedia(getSampleMedia());
		filter.setSiteid(getSiteId());
		filter.setSiteType(getSiteType());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setStatecode(getState());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		filter.setWithin(getWithin());
		List<Map<String, Object>> counts = callDao(nameSpace, 2, filter);
		assertStationResults(counts, FILTERED_TOTAL_SITE_COUNT, null, null, FILTERED_STORET_SITE_COUNT, null);
		if (includeActivity) {
			assertActivityResults(counts, FILTERED_TOTAL_ACTIVITY_COUNT, null, null, FILTERED_STORET_ACTIVITY_COUNT, null);
		}
		if (includeResults) {
			assertResultResults(counts, FILTERED_TOTAL_RESULT_COUNT, null, null, FILTERED_STORET_RESULT_COUNT, null);
		}
		return counts;
	}

	protected void assertStationResults(List<Map<String, Object>> counts,
			String total, String nwis, String stewards, String storet, String biodata) {
		assertResults(counts, CountColumn.KEY_STATION_COUNT, total, nwis, stewards, storet, biodata);
	}

	protected void assertResultResults(List<Map<String, Object>> counts,
			String total, String nwis, String stewards, String storet, String biodata) {
		assertResults(counts, CountColumn.KEY_RESULT_COUNT, total, nwis, stewards, storet, biodata);
	}

	protected void assertActivityResults(List<Map<String, Object>> counts,
			String total, String nwis, String stewards, String storet, String biodata) {
		assertResults(counts, CountColumn.KEY_ACTIVITY_COUNT, total, nwis, stewards, storet, biodata);
	}

	protected void assertResults(List<Map<String, Object>> counts, String countType,
			String expectedTotal, String expectedNwis, String expectedStewards, String expectedStoret,
			String expectedBiodata) {
		boolean nwis = (null == expectedNwis);
		boolean stewards = (null == expectedStewards);
		boolean storet = (null == expectedStoret);
		boolean biodata = (null == expectedBiodata);
		boolean total = (null == expectedTotal);
		for (int i = 0 ; i < counts.size() ; i++) {
			if (null == counts.get(i).get(BaseColumn.KEY_DATA_SOURCE)) {
				assertEquals("total " + countType + " count", expectedTotal, counts.get(i).get(countType).toString());
				total = true;
			} else {
				switch (counts.get(i).get(BaseColumn.KEY_DATA_SOURCE).toString()) {
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

	protected void assertFullDbReturned(List<Map<String, Object>> counts, boolean includeActivity, boolean includeResults) {
		if (includeActivity || includeResults) {
			//Adjust site count for sites with no activities
			assertStationResults(counts, TOTAL_SITE_COUNT_MINUS_1, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT_MINUS_1, BIODATA_SITE_COUNT_MINUS_1);
		} else {
			assertStationResults(counts, TOTAL_SITE_COUNT, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT, BIODATA_SITE_COUNT);
		}
		if (includeActivity) {
			assertActivityResults(counts, TOTAL_ACTIVITY_COUNT, NWIS_ACTIVITY_COUNT, STEWARDS_ACTIVITY_COUNT, STORET_ACTIVITY_COUNT, BIODATA_ACTIVITY_COUNT);
		}
		if (includeResults) {
			assertResultResults(counts, TOTAL_RESULT_COUNT, NWIS_RESULT_COUNT, STEWARDS_RESULT_COUNT, STORET_RESULT_COUNT, BIODATA_RESULT_COUNT);
		}	
	}

	@Override
	public List<Map<String, Object>> callDao(NameSpace nameSpace, int expectedSize, FilterParameters filter) {
		List<Map<String, Object>> counts = countDao.getCounts(nameSpace, filter);
		assertEquals(expectedSize, counts.size());
		return counts;
	}

	@Override
	protected void assertSiteUrlBase(Map<String, Object> row) {
		// Nothing to do here
	}

}
