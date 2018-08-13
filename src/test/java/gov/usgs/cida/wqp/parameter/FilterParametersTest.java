package gov.usgs.cida.wqp.parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.mapping.Profile;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

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
		
		return filter;
	}

	
	
	@Autowired
	private JacksonTester<FilterParameters> json;
	
	@Test
	public void testSerialize() throws Exception {
		assertThat(this.json.write(getTestFilterParameters())).isEqualToJson(new ClassPathResource("testResult/filterParameters.json").getInputStream());
	}
	
	
	@Test
	public void testDeserialize() throws Exception {
		assertThat(this.json.parse(getCompareFile("filterParameters.json"))).isEqualToComparingFieldByFieldRecursively(getTestFilterParameters());
	}

}
