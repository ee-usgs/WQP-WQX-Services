package gov.usgs.cida.wqp.parameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.TestConstraintViolation;
import gov.usgs.cida.wqp.mapping.Profile;

@AutoConfigureJsonTesters
@RunWith(SpringRunner.class)
@JsonTest
public class FilterParametersTest extends BaseTest {

	@Test
	public void emptyStuff() {
		FilterParameters filter = new FilterParameters();
		assertTrue(filter.isEmpty());
		assertTrue(filter.isValid());
		assertEquals("{}", filter.toJson());
	}

	private FilterParameters getTestFilterParameters() {
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
		filter.setResultId(getResult());
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

		return filter;
	}

	@Autowired
	private JacksonTester<FilterParameters> json;
	
	@Test
	public void testSerialize() throws Exception {
		FilterParameters filter = getTestFilterParameters();
		Set<ConstraintViolation<FilterParameters>> validationErrors = new HashSet<>();
		validationErrors.add(new TestConstraintViolation("warning1"));
		validationErrors.add(new TestConstraintViolation("warning2"));
		validationErrors.add(new TestConstraintViolation("warning3"));
		validationErrors.add(new TestConstraintViolation("warning4"));
		filter.setValidationErrors(validationErrors);

		assertFalse(filter.isEmpty());
		assertFalse(filter.isValid());
		assertThat(this.json.write(filter)).isEqualToJson(getCompareFile("filterParameters.json"));
	}

	@Test
	public void testDeserialize() throws Exception {
		assertThat(this.json.parse(getCompareFile("filterParameters.json"))).isEqualToComparingFieldByFieldRecursively(getTestFilterParameters());
	}

}
