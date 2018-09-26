package gov.usgs.cida.wqp.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import gov.usgs.cida.wqp.BaseIT;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public abstract class FilteredDaoTest extends BaseIT {

	public List<Map<String, Object>> nullParameterTest(NameSpace nameSpace, int expectedSize) {
		return callDao(nameSpace, expectedSize, null);
	}

	public List<Map<String, Object>> emptyParameterTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> activityTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setActivity(getActivity());
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> analyticalMethodTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setAnalyticalmethod(getAnalyticalMethod());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> assemblageTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setAssemblage(getAssemblage());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> avoidTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCommand(getCommand());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> bboxTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setBBox(getBBox());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> characteristicNameTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCharacteristicName(getCharacteristicName());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> characteristicTypeTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCharacteristicType(getCharacteristicType());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> countryTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCountrycode(getCountry());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> countyTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setCountycode(getCounty());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> huc2Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc2());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> huc3Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc3());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> huc4Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc4());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> huc5Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc5());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> huc6Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc6());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> huc7Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc7());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> huc8Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc8());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> huc10Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc10());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> huc12Test(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setHuc(getHuc12());
		return callDao(nameSpace, expectedSize, filter);
	}

	protected String getDataProfileFromNameSpace(NameSpace nameSpace) {
		switch (nameSpace) {
		case STATION: 
			return Profile.STATION.toString();
		case SIMPLE_STATION:
		case STATION_KML:
			return Profile.SIMPLE_STATION.toString();
		case SUMMARY_STATION:
			return Profile.SUMMARY_STATION.toString();
		case SUMMARY_ORGANIZATION:
			return Profile.SUMMARY_ORGANIZATION.toString();
		case PROJECT:
			return Profile.PROJECT.toString();
		case PROJECT_MONITORING_LOCATION_WEIGHTING:
			return Profile.PROJECT_MONITORING_LOCATION_WEIGHTING.toString();
		case ACTIVITY:
			return Profile.ACTIVITY.toString();
		case ACTIVITY_METRIC:
			return Profile.ACTIVITY_METRIC.toString();
		case RESULT:
			return Profile.PC_RESULT.toString();
		case BIOLOGICAL_RESULT:
			return Profile.BIOLOGICAL.toString();
		case NARROW_RESULT:
			return Profile.NARROW_RESULT.toString();
		case RES_DETECT_QNT_LMT:
			return Profile.RES_DETECT_QNT_LMT.toString();
		default:
			return null;
		}
	}

	public List<Map<String, Object>> mimeTypeJsonTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(JSON);
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> mimeTypeGeoJsonTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(GEOJSON);
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> mimeTypeKmlTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(KML);
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> mimeTypeKmzTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(KMZ);
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> mimeTypeCsvTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(CSV);
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> mimeTypeTsvTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(TSV);
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> mimeTypeXmlTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(XML);
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> mimeTypeXlsxTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(XLSX);
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> minActivitiesTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setMinactivities(getMinActivities());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> minResultsTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setMinresults(getMinResults());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> nldiSitesTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		try {
			filter.setNldiSites(getSiteIdLargeList());
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> nldiUrlTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setNldiurl(getNldiurl());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> organizationTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setOrganization(getOrganization());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> pcodeTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setPCode(getPcode());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> projectTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setProject(getProject());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> resultTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setResult(getResult());
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> providersTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setProviders(getProviders());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> sampleMediaTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSampleMedia(getSampleMedia());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> siteIdTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSiteid(getSiteId());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> siteIdLargeListTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		try {
			filter.setSiteid(getSiteIdLargeList());
		} catch (Exception e) {
			fail(e.getLocalizedMessage());
		}
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> siteTypeTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSiteType(getSiteType());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> siteUrlBaseTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSiteUrlBase(getSiteUrlBase());
		List<Map<String, Object>> results = callDao(nameSpace, expectedSize, filter);
		results.forEach(
				row -> assertSiteUrlBase(row)
			);
		return results;
	}

	public List<Map<String, Object>> sortedTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSorted("yes");
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> startDateHiTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setStartDateHi(getStartDateHi());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> startDateLoTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setStartDateLo(getStartDateLo());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> stateTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setStatecode(getState());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> subjectTaxonomicNameTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> withinTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setWithin(getWithin());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		return callDao(nameSpace, expectedSize, filter);
	}

	public List<Map<String, Object>> zipTest(NameSpace nameSpace, int expectedSize) {
		FilterParameters filter = new FilterParameters();
		filter.setZip(getZip());
		return callDao(nameSpace, expectedSize, filter);
	}

	protected FilterParameters getNoEffectParameters(NameSpace nameSpace) {
		FilterParameters filter = new FilterParameters();
		if (!NameSpace.ACTIVITY_METRIC.equals(nameSpace) && !NameSpace.RES_DETECT_QNT_LMT.equals(nameSpace)) {
			filter.setActivity(getActivity());
		}
		filter.setDataProfile(getDataProfileFromNameSpace(nameSpace));
		filter.setMimeType(JSON);
		filter.setNldiurl(getNldiurl());
		if (!NameSpace.RES_DETECT_QNT_LMT.equals(nameSpace)) {
			filter.setResult(getResult());
		}
		filter.setSiteUrlBase(getSiteUrlBase());
		filter.setSorted("yes");
		filter.setZip(getZip());
		return filter;
	}

	public abstract List<Map<String, Object>> callDao(NameSpace nameSpace, int expectedSize, FilterParameters filter);

	protected abstract void assertSiteUrlBase(Map<String, Object> row);

	protected void assertUrl(String columnName, Map<String, Object> row) {
		Object url = row.get(columnName);
		if (null != url) {
			assertTrue(columnName + " is incorrect: " + url, url.toString().startsWith(getSiteUrlBase()));
		}
	}
}
