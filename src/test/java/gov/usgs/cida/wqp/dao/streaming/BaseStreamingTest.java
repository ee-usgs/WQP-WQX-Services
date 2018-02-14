package gov.usgs.cida.wqp.dao.streaming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.dao.NameSpace;
import gov.usgs.cida.wqp.dao.intfc.IStreamingDao;
import gov.usgs.cida.wqp.mapping.TestResultHandler;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public abstract class BaseStreamingTest extends BaseSpringTest {

	@Autowired 
	IStreamingDao streamingDao;

	public LinkedList<Map<String, Object>> nullParameterTest(NameSpace nameSpace, int expectedSize) {
		return callDao(nameSpace, expectedSize, null);
	}

	public LinkedList<Map<String, Object>> emptyParameterTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> activityMetricURLTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSiteUrlBase(getSiteUrlBase());
		filter.setProviders(Arrays.asList("BIODATA"));
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> allDataSortedTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSorted("yes");
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> analyticalMethodTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setAnalyticalmethod(getAnalyticalMethod());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> assemblageTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setAssemblage(getAssemblage());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> avoidTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCommand(getCommand());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> bboxTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setBBox(getBBox());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> characteristicNameTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCharacteristicName(getCharacteristicName());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> characteristicTypeTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCharacteristicType(getCharacteristicType());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> countryTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCountrycode(getCountry());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> countyTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCountycode(getCounty());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> huc2Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc2());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> huc3Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc3());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> huc4Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc4());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> huc5Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc5());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> huc6Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc6());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> huc7Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc7());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> huc8Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc8());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> huc10Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc10());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> huc12Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc12());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> minActivitiesTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setMinactivities(getMinActivities());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> minResultsTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setMinresults(getMinResults());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> nldiUrlTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		try {
			filter.setNldiSites(getManySiteId());
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> organizationTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setOrganization(getOrganization());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> pcodeTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setPCode(getPcode());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> projectTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setProject(getProject());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> providersTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(getProviders());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>>  resultTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setResult(getResult());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> sampleMediaTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSampleMedia(getSampleMedia());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> siteIdTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSiteid(getSiteid());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> manySitesTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		try {
			filter.setSiteid(getManySiteId());
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> siteTypeTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSiteType(getSiteType());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> startDateHiTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setStartDateHi(getStartDateHi());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> startDateLoTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setStartDateLo(getStartDateLo());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> stateTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setStatecode(getState());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> subjectTaxonomicNameTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> withinTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> multipleParameterStationSumTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setBBox(getBBox());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setOrganization(getOrganization());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setWithin(getWithin());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> multipleParameterActivityTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setOrganization(getOrganization());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> multipleParameterActivitySumTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setHuc(getHuc());
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setOrganization(getOrganization());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> multipleParameterActivitySumStationSumTest(NameSpace nameSpace, int expectedSize) {
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
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setWithin(getWithin());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> multipleParameterResultSumTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
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
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStatecode(getState());
		filter.setPCode(getPcode());
		filter.setSampleMedia(getSampleMedia());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> multipleParameterResultSumStationSumTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
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
		filter.setSiteid(getSiteid());
		filter.setSiteType(getSiteType());
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setStatecode(getState());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		filter.setWithin(getWithin());
		return callDao(nameSpace, expectedSize, filter);
	}

	public LinkedList<Map<String, Object>> callDao(NameSpace nameSpace, int expectedSize, FilterParameters filter) {
		TestResultHandler handler = new TestResultHandler();
		streamingDao.stream(nameSpace, filter, handler);
		assertEquals(expectedSize, handler.getResults().size());
		return handler.getResults();
	}

}
