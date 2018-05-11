package gov.usgs.cida.wqp.parameter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.TestConstraintViolation;
import gov.usgs.cida.wqp.mapping.Profile;

public class FilterParametersTest extends BaseTest {

	@Test
	public void emptyStuff() {
		FilterParameters filter = new FilterParameters();
		assertTrue(filter.isEmpty());
		assertTrue(filter.isValid());
		assertEquals("{}", filter.toJson());
	}

	@Test
	public void fullHouse() throws IOException, JSONException {
		FilterParameters filter = new FilterParameters();
		filter.setActivity(getActivity());
		filter.setAnalyticalmethod(getAnalyticalMethod());
		filter.setAssemblage(getAssemblage());
		filter.setBBox(getBBox());
		filter.setCharacteristicName(getCharacteristicName());
		filter.setCharacteristicType(getCharacteristicType());
		filter.setCommand(getCommand());
		filter.setCountrycode(getCountry());
		filter.setCountycode(getCounty());
		filter.setDataProfile(Profile.ACTIVITY.name());
		filter.setHuc(getHuc());
		filter.setLat(getLatitude());
		filter.setLong(getLongitude());
		filter.setMimeType("csv");
		filter.setMinactivities(getMinActivities());
		filter.setMinresults(getMinResults());
		filter.setNldiurl(getNldiurl());
		filter.setOrganization(getOrganization());
		filter.setPCode(getPcode());
		filter.setProject(getProject());
		filter.setProviders(getProviders());
		filter.setResult(getResult());
		filter.setSampleMedia(getSampleMedia());
		filter.setSiteid(getSiteId());
		filter.setSiteType(getSiteType());
		filter.setSorted("yes");
		filter.setStartDateHi(getStartDateHi());
		filter.setStartDateLo(getStartDateLo());
		filter.setStatecode(getState());
		filter.setSubjectTaxonomicName(getSubjectTaxonomicName());
		filter.setWithin(getWithin());
		filter.setZip("no");
		Set<ConstraintViolation<FilterParameters>> validationErrors = new HashSet<>();
		validationErrors.add(new TestConstraintViolation("warning1"));
		validationErrors.add(new TestConstraintViolation("warning2"));
		validationErrors.add(new TestConstraintViolation("warning3"));
		validationErrors.add(new TestConstraintViolation("warning4"));
		filter.setValidationErrors(validationErrors);
		assertFalse(filter.isEmpty());
		assertFalse(filter.isValid());
		assertThat(new JSONObject(getCompareFile("filterParameters.json")), sameJSONObjectAs(new JSONObject(filter.toJson())));
	}

}
