package gov.usgs.wma.wqp.dao.count;

import static gov.usgs.wma.wqp.openapi.model.StationCountJson.NWIS;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.STEWARDS;
import static gov.usgs.wma.wqp.openapi.model.StationCountJson.STORET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.wma.wqp.dao.CountDao;
import gov.usgs.wma.wqp.dao.FilteredStationDaoTest;
import gov.usgs.wma.wqp.dao.NameSpace;
import gov.usgs.wma.wqp.mapping.BaseColumn;
import gov.usgs.wma.wqp.mapping.CountColumn;
import gov.usgs.wma.wqp.parameter.FilterParameters;

public abstract class BaseCountDaoTest extends FilteredStationDaoTest {

	@Autowired 
	CountDao countDao;

	protected List<Map<String, Object>> nullParameterTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = nullParameterTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> emptyParameterTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = emptyParameterTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> sortedTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = sortedTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> zipTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = zipTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> activityTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = activityTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> nldiUrlTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = nldiUrlTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> siteUrlBaseTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = siteUrlBaseTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected List<Map<String, Object>> resultTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = resultTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
		return counts;
	}

	protected void mimeTypeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = mimeTypeJsonTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeGeoJsonTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeKmlTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeKmzTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeCsvTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeTsvTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeXmlTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);

		counts = mimeTypeXlsxTest(nameSpace, BASE_HEADER_COUNT);
		assertFullDbReturned(counts, includeActivity, includeResults);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against station_sum

	protected List<Map<String, Object>> avoidTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = avoidTest(nameSpace, 2);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, STORET_SITE_COUNT_MINUS_1, null, null, STORET_SITE_COUNT_MINUS_1);
		} else {
			assertStationResults(counts, STORET_SITE_COUNT, null, null, STORET_SITE_COUNT);
		}
		if (includeActivity) {
			assertActivityResults(counts, STORET_ACTIVITY_COUNT, null, null, STORET_ACTIVITY_COUNT);
		}
		if (includeResults) {
			assertResultResults(counts, STORET_RESULT_COUNT, null, null, STORET_RESULT_COUNT);
		}
		return counts;
	}

	protected List<Map<String, Object>> bboxTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = bboxTest(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "8", "2", "2", "4");
		} else {
			assertStationResults(counts, "9", "2", "2", "5");
		}
		if (includeActivity) {
			assertActivityResults(counts, "17", "3", "3", "11");
		}
		if (includeResults) {
			assertResultResults(counts, "27", "5", "3", "19");
		}
		return counts;
	}

	protected List<Map<String, Object>> countryTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = countryTest(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "10", "3", "2", "5");
		} else {
			assertStationResults(counts, "11", "3", "2", "6");
		}
		if (includeActivity) {
			assertActivityResults(counts, "19", "4", "3", "12");
		}
		if (includeResults) {
			assertResultResults(counts, "29", "6", "3", "20");
		}
		return counts;
	}

	protected List<Map<String, Object>> countyTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = countyTest(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "9", "2", "2", "5");
		} else {
			assertStationResults(counts, "10", "2", "2", "6");
		}
		if (includeActivity) {
			assertActivityResults(counts, "18", "3", "3", "12");
		}
		if (includeResults) {
			assertResultResults(counts, "28", "5", "3", "20");
		}
		return counts;
	}

	protected List<Map<String, Object>> huc2Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc2Test(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "6", "2", "2", "2");
		} else {
			assertStationResults(counts, "7", "2", "2", "3");
		}
		if (includeActivity) {
			assertActivityResults(counts, "11", "3", "3", "5");
		}
		if (includeResults) {
			assertResultResults(counts, "19", "5", "3", "11");
		}
		return counts;
	}


	protected List<Map<String, Object>> huc3Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc3Test(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "6", "2", "2", "2");
		} else {
			assertStationResults(counts, "7", "2", "2", "3");
		}
		if (includeActivity) {
			assertActivityResults(counts, "11", "3", "3", "5");
		}
		if (includeResults) {
			assertResultResults(counts, "19", "5", "3", "11");
		}
		return counts;
	}

	protected List<Map<String, Object>> huc4Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc4Test(nameSpace, 3);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "3", "2", null, "1");
		} else {
			assertStationResults(counts, "4", "2", null, "2");
		}
		if (includeActivity) {
			assertActivityResults(counts, "5", "3", null, "2");
		}
		if (includeResults) {
			assertResultResults(counts, "12", "5", null, "7");
		}
		return counts;
	}

	protected List<Map<String, Object>> huc5Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc5Test(nameSpace, 3);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "3", "2", null, "1");
		} else {
			assertStationResults(counts, "4", "2", null, "2");
		}
		if (includeActivity) {
			assertActivityResults(counts, "5", "3", null, "2");
		}
		if (includeResults) {
			assertResultResults(counts, "12", "5", null, "7");
		}
		return counts;
	}

	protected List<Map<String, Object>> huc6Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc6Test(nameSpace, 3);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "2", "1", null, "1");
		} else {
			assertStationResults(counts, "3", "1", null, "2");
		}
		if (includeActivity) {
			assertActivityResults(counts, "4", "2", null, "2");
		}
		if (includeResults) {
			assertResultResults(counts, "11", "4", null, "7");
		}
		return counts;
	}

	protected List<Map<String, Object>> huc7Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc7Test(nameSpace, 3);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "2", "1", null, "1");
		} else {
			assertStationResults(counts, "3", "1", null, "2");
		}
		if (includeActivity) {
			assertActivityResults(counts, "4", "2", null, "2");
		}
		if (includeResults) {
			assertResultResults(counts, "11", "4", null, "7");
		}
		return counts;
	}

	protected List<Map<String, Object>> huc8Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc8Test(nameSpace, 2);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "1", null, null, "1");
		} else {
			assertStationResults(counts, "2", null, null, "2");
		}
		if (includeActivity) {
			assertActivityResults(counts, "2", null, null, "2");
		}
		if (includeResults) {
			assertResultResults(counts, "7", null, null, "7");
		}
		return counts;
	}

	protected List<Map<String, Object>> huc10Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc10Test(nameSpace, 2);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "1", null, null, "1");
		} else {
			assertStationResults(counts, "2", null, null, "2");
		}
		if (includeActivity) {
			assertActivityResults(counts, "2", null, null, "2");
		}
		if (includeResults) {
			assertResultResults(counts, "7", null, null, "7");
		}
		return counts;
	}

	protected List<Map<String, Object>> huc12Test(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = huc12Test(nameSpace, 2);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "1", null, null, "1");
		} else {
			assertStationResults(counts, "2", null, null, "2");
		}
		if (includeActivity) {
			assertActivityResults(counts, "2", null, null, "2");
		}
		if (includeResults) {
			assertResultResults(counts, "7", null, null, "7");
		}
		return counts;
	}

	protected List<Map<String, Object>> minActivitiesTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = minActivitiesTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "7", "1", "1", "5");
		if (includeActivity) {
			assertActivityResults(counts, "19", "2", "2", "15");
		}
		if (includeResults) {
			assertResultResults(counts, "51", "4", "2", "45");
		}
		return counts;
	}

	protected List<Map<String, Object>> minResultsTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = minResultsTest(nameSpace, 3);
		assertStationResults(counts, "6", "1", null, "5");
		if (includeActivity) {
			assertActivityResults(counts, "17", "2", null, "15");
		}
		if (includeResults) {
			assertResultResults(counts, "49", "4", null, "45");
		}
		return counts;
	}

	protected List<Map<String, Object>> nldiSitesTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = nldiSitesTest(nameSpace, 2);
		assertStationResults(counts, "3", null, null, "3");
		if (includeActivity) {
			assertActivityResults(counts, "8", null, null, "8");
		}
		if (includeResults) {
			assertResultResults(counts, "15", null, null, "15");
		}
		return counts;
	}

	protected List<Map<String, Object>> organizationTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = organizationTest(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "9", "2", "2", "5");
		} else {
			assertStationResults(counts, "10", "2", "2", "6");
		}
		if (includeActivity) {
			assertActivityResults(counts, "18", "3", "3", "12");
		}
		if (includeResults) {
			assertResultResults(counts, "28", "5", "3", "20");
		}
		return counts;
	}

	protected List<Map<String, Object>> providersTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = providersTest(nameSpace, 3);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "9", "3", null, "6");
		} else {
			assertStationResults(counts, "11", "4", null, "7");
		}
		if (includeActivity) {
			assertActivityResults(counts, "20", "4", null, "16");
		}
		if (includeResults) {
			assertResultResults(counts, "52", "6", null, "46");
		}
		return counts;
	}

	protected List<Map<String, Object>> siteIdTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = siteIdTest(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "8", "2", "2", "4");
		} else {
			assertStationResults(counts, "9", "2", "2", "5");
		}
		if (includeActivity) {
			assertActivityResults(counts, "16", "3", "3", "10");
		}
		if (includeResults) {
			assertResultResults(counts, "21", "5", "3", "13");
		}
		return counts;
	}

	protected List<Map<String, Object>> siteIdLargeListTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = siteIdLargeListTest(nameSpace, 2);
		assertStationResults(counts, "3", null, null, "3");
		if (includeActivity) {
			assertActivityResults(counts, "8", null, null, "8");
		}
		if (includeResults) {
			assertResultResults(counts, "15", null, null, "15");
		}
		return counts;
	}

	protected List<Map<String, Object>> siteTypeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = siteTypeTest(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "10", "2", "2", "6");
		} else {
			assertStationResults(counts, "11", "2", "2", "7");
		}
		if (includeActivity) {
			assertActivityResults(counts, "22", "3", "3", "16");
		}
		if (includeResults) {
			assertResultResults(counts, "54", "5", "3", "46");
		}
		return counts;
	}

	protected List<Map<String, Object>> stateTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = stateTest(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "9", "2", "2", "5");
		} else {
			assertStationResults(counts, "10", "2", "2", "6");
		}
		if (includeActivity) {
			assertActivityResults(counts, "18", "3", "3", "12");
		}
		if (includeResults) {
			assertResultResults(counts, "28", "5", "3", "20");
		}
		return counts;
	}

	protected List<Map<String, Object>> withinTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = withinTest(nameSpace, BASE_HEADER_COUNT);
		if (includeActivity || includeResults) {
			//Adjust site count for STORET site with no activities
			assertStationResults(counts, "9", "2", "2", "5");
		} else {
			assertStationResults(counts, "10", "2", "2", "6");
		}
		if (includeActivity) {
			assertActivityResults(counts, "19", "3", "3", "13");
		}
		if (includeResults) {
			assertResultResults(counts, "50", "5", "3", "42");
		}
		return counts;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against activity_sum

	protected List<Map<String, Object>> projectTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = projectTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "9", "3", "2", "4");
		if (includeActivity) {
			assertActivityResults(counts, "14", "3", "2", "9");
		}
		if (includeResults) {
			assertResultResults(counts, "19", "5", "2", "12");
		}
		return counts;
	}

	protected List<Map<String, Object>> sampleMediaTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = sampleMediaTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "11", "3", "2", "6");
		if (includeActivity) {
			assertActivityResults(counts, "21", "3", "2", "16");
		}
		if (includeResults) {
			assertResultResults(counts, "53", "5", "2", "46");
		}
		return counts;
	}

	protected List<Map<String, Object>> startDateHiTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = startDateHiTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "11", "3", "2", "6");
		if (includeActivity) {
			assertActivityResults(counts, "21", "3", "2", "16");
		}
		if (includeResults) {
			assertResultResults(counts, "53", "5", "2", "46");
		}
		return counts;
	}

	protected List<Map<String, Object>> startDateLoTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = startDateLoTest(nameSpace, BASE_HEADER_COUNT);
		assertStationResults(counts, "9", "3", "2", "4");
		if (includeActivity) {
			assertActivityResults(counts, "17", "4", "3", "10");
		}
		if (includeResults) {
			assertResultResults(counts, "22", "6", "3", "13");
		}
		return counts;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Single Parameter Counts against result_sum

	protected List<Map<String, Object>> analyticalMethodTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = analyticalMethodTest(nameSpace, 3);
		assertStationResults(counts, "5", "1", null, "4");
		if (includeActivity) {
			assertActivityResults(counts, "12", "2", null, "10");
		}
		if (includeResults) {
			assertResultResults(counts, "17", "4", null, "13");
		}
		return counts;
	}

	protected List<Map<String, Object>> assemblageTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = assemblageTest(nameSpace, 3);
		assertStationResults(counts, "5", "1", null, "4");
		if (includeActivity) {
			assertActivityResults(counts, "11", "1", null, "10");
		}
		if (includeResults) {
			assertResultResults(counts, "14", "1", null, "13");
		}
		return counts;
	}

	protected List<Map<String, Object>> characteristicNameTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = characteristicNameTest(nameSpace, 2);
		assertStationResults(counts, "4", null, null, "4");
		if (includeActivity) {
			assertActivityResults(counts, "10", null, null, "10");
		}
		if (includeResults) {
			assertResultResults(counts, "13", null, null, "13");
		}
		return counts;
	}

	protected List<Map<String, Object>> characteristicTypeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = characteristicTypeTest(nameSpace, 3);
		assertStationResults(counts, "5", null, "1", "4");
		if (includeActivity) {
			assertActivityResults(counts, "12", null, "2", "10");
		}
		if (includeResults) {
			assertResultResults(counts, "15", null, "2", "13");
		}
		return counts;
	}

	protected List<Map<String, Object>> pcodeTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = pcodeTest(nameSpace, 3);
		assertStationResults(counts, "4", "1", null, "3");
		if (includeActivity) {
			assertActivityResults(counts, "10", "1", null, "9");
		}
		if (includeResults) {
			assertResultResults(counts, "13", "1", null, "12");
		}
		return counts;
	}

	protected List<Map<String, Object>> subjectTaxonomicNameTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = subjectTaxonomicNameTest(nameSpace, 3);
		assertStationResults(counts, "5", "1", null, "4");
		if (includeActivity) {
			assertActivityResults(counts, "10", "1", null, "9");
		}
		if (includeResults) {
			assertResultResults(counts, "13", "1", null, "12");
		}
		return counts;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	protected List<Map<String, Object>> multipleParameterStationSumTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
		List<Map<String, Object>> counts = multipleParameterStationSumTest(nameSpace, 3);
		assertStationResults(counts, "3", "1", null, "2");
		if (includeActivity) {
			assertActivityResults(counts, "8", "2", null, "6");
		}
		if (includeResults) {
			assertResultResults(counts, "12", "4", null, "8");
		}
		return counts;
	}

	protected List<Map<String, Object>> multipleParameterActivitySumTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
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
		assertStationResults(counts, "2", null, null, "2");
		if (includeActivity) {
			assertActivityResults(counts, "6", null, null, "6");
		}
		if (includeResults) {
			assertResultResults(counts, "8", null, null, "8");
		}
		return counts;
	}

	protected List<Map<String, Object>> multipleParameterResultSumTest(NameSpace nameSpace, boolean includeActivity, boolean includeResults) {
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
		assertStationResults(counts, FILTERED_TOTAL_SITE_COUNT, null, null, FILTERED_STORET_SITE_COUNT);
		if (includeActivity) {
			assertActivityResults(counts, FILTERED_TOTAL_ACTIVITY_COUNT, null, null, FILTERED_STORET_ACTIVITY_COUNT);
		}
		if (includeResults) {
			assertResultResults(counts, FILTERED_TOTAL_RESULT_COUNT, null, null, FILTERED_STORET_RESULT_COUNT);
		}
		return counts;
	}

	protected void assertStationResults(List<Map<String, Object>> counts,
			String total, String nwis, String stewards, String storet) {
		assertResults(counts, CountColumn.KEY_STATION_COUNT, total, nwis, stewards, storet);
	}

	protected void assertResultResults(List<Map<String, Object>> counts,
			String total, String nwis, String stewards, String storet) {
		assertResults(counts, CountColumn.KEY_RESULT_COUNT, total, nwis, stewards, storet);
	}

	protected void assertActivityResults(List<Map<String, Object>> counts,
			String total, String nwis, String stewards, String storet) {
		assertResults(counts, CountColumn.KEY_ACTIVITY_COUNT, total, nwis, stewards, storet);
	}

	protected void assertResults(List<Map<String, Object>> counts, String countType,
			String expectedTotal, String expectedNwis, String expectedStewards, String expectedStoret) {
		boolean nwis = (null == expectedNwis);
		boolean stewards = (null == expectedStewards);
		boolean storet = (null == expectedStoret);
		boolean total = (null == expectedTotal);
		for (int i = 0 ; i < counts.size() ; i++) {
			if (null == counts.get(i).get(BaseColumn.KEY_DATA_SOURCE)) {
				assertEquals(expectedTotal, counts.get(i).get(countType).toString(), "total " + countType + " count");
				total = true;
			} else {
				switch (counts.get(i).get(BaseColumn.KEY_DATA_SOURCE).toString()) {
				case NWIS:
					assertEquals(expectedNwis, counts.get(i).get(countType).toString(), "NWIS " + countType + " count");
					nwis = true;
					break;
				case STEWARDS:
					assertEquals(expectedStewards, counts.get(i).get(countType).toString(), "STEWARDS " + countType + " count");
					stewards = true;
					break;
				case STORET:
					assertEquals(expectedStoret, counts.get(i).get(countType).toString(), "STORET " + countType + " count");
					storet = true;
					break;
				default:
					break;
				}
			}
		}
		assertTrue(total, "Did not get " + countType + " Total");
		assertTrue(nwis, "Did not get " + countType + " NWIS");
		assertTrue(stewards, "Did not get " + countType + " STEWARDS");
		assertTrue(storet, "Did not get " + countType + " STORET");
	}

	protected void assertFullDbReturned(List<Map<String, Object>> counts, boolean includeActivity, boolean includeResults) {
		if (includeActivity || includeResults) {
			//Adjust site count for sites with no activities
			assertStationResults(counts, TOTAL_SITE_COUNT_MINUS_1, NWIS_SITE_COUNT_MINUS_1, STEWARDS_SITE_COUNT, STORET_SITE_COUNT_MINUS_1);
		} else {
			assertStationResults(counts, TOTAL_SITE_COUNT, NWIS_SITE_COUNT, STEWARDS_SITE_COUNT, STORET_SITE_COUNT);
		}
		if (includeActivity) {
			assertActivityResults(counts, TOTAL_ACTIVITY_COUNT, NWIS_ACTIVITY_COUNT, STEWARDS_ACTIVITY_COUNT, STORET_ACTIVITY_COUNT);
		}
		if (includeResults) {
			assertResultResults(counts, TOTAL_RESULT_COUNT, NWIS_RESULT_COUNT, STEWARDS_RESULT_COUNT, STORET_RESULT_COUNT);
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
		// Nothing to do here - The site.url.base is never used on a count.
	}

}
