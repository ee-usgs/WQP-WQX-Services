package gov.usgs.cida.wqp.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.exception.WqpExceptionId;
import gov.usgs.cida.wqp.parameter.BoundingBox;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.service.FetchService;
import gov.usgs.cida.wqp.service.FetchServiceTest;

public class FilterParametersValidationTest extends BaseSpringTest {

	@Autowired
	protected Validator validator;
	@Autowired
	protected CodesService codesService;
	@Autowired
	protected FetchService fetchService;

	protected FilterParameters filter;

	@Before
	public void setup() {
		filter = new FilterParameters();
	}

	@Test
	public void maxLengthTest() throws WqpException {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

		filter.setAnalyticalmethod(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "https://usgs.gov"));
		filter.setAssemblage(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "a"));
		filter.setCharacteristicName(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "a"));
		filter.setCharacteristicType(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "a"));
		filter.setCommandavoid(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "NWIS"));
		filter.setCountrycode(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "US"));
		filter.setCountycode(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "US:55:019"));
		filter.setHuc(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "07"));
		filter.setOrganization(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "a"));
		filter.setPCode(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "12345"));
		filter.setProject(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "a"));
		filter.setProviders(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "a"));
		filter.setSampleMedia(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "a"));
		filter.setSiteid(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "USGS-12345678"));
		filter.setSiteType(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "a"));
		filter.setStatecode(Collections.nCopies(FilterParameters.IN_CLAUSE_LIMIT + 1, "US:55"));

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(15, validationErrors.size());
		assertValidationResults(validationErrors,
				"The analyticalmethod is not between 0 and 1000 occurances",
				"The assemblage is not between 0 and 1000 occurances",
				"The characteristicName is not between 0 and 1000 occurances",
				"The characteristicType is not between 0 and 1000 occurances",
				"The commandavoid is not between 0 and 1000 occurances",
				"The countrycode is not between 0 and 1000 occurances",
				"The countycode is not between 0 and 1000 occurances",
				"The huc is not between 0 and 1000 occurances",
				"The organization is not between 0 and 1000 occurances",
				"The pCode is not between 0 and 1000 occurances",
				"The project is not between 0 and 1000 occurances",
				"The providers is not between 0 and 1000 occurances",
				"The sampleMedia is not between 0 and 1000 occurances",
				"The siteType is not between 0 and 1000 occurances",
				"The statecode is not between 0 and 1000 occurances");
	}

	@Test
	public void urlTests() throws IOException {
		when(fetchService.fetch(anyString(), any(URL.class))).thenReturn(FetchServiceTest.NLDI_IDENTIFIERS);

		filter.setAnalyticalmethod(Arrays.asList("http://usgs.gov", "just.crud"));
		filter.setNldiurl("http://usgs.gov");

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(3, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of analyticalmethod=http://usgs.gov must be a valid HTTPS URL.",
				"The value of analyticalmethod=just.crud must be a valid HTTPS URL.",
				"The value of nldiurl=http://usgs.gov must be a valid HTTPS URL.");
	}

	@Test
	public void lookupTests() throws WqpException {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(false);

		filter.setAssemblage(Arrays.asList("a"));
		filter.setCharacteristicName(Arrays.asList("a"));
		filter.setCharacteristicType(Arrays.asList("a"));
		filter.setOrganization(Arrays.asList("a"));
		filter.setProject(Arrays.asList("a"));
		filter.setProviders(Arrays.asList("a"));
		filter.setSampleMedia(Arrays.asList("a"));
		filter.setSiteType(Arrays.asList("a"));
		filter.setSubjectTaxonomicName(Arrays.asList("a"));

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(9, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of assemblage=a is not in the list of enumerated values.",
				"The value of characteristicName=a is not in the list of enumerated values.",
				"The value of characteristicType=a is not in the list of enumerated values.",
				"The value of organization=a is not in the list of enumerated values.",
				"The value of project=a is not in the list of enumerated values.",
				"The value of providers=a is not in the list of enumerated values.",
				"The value of sampleMedia=a is not in the list of enumerated values.",
				"The value of siteType=a is not in the list of enumerated values.",
				"The value of subjectTaxonomicName=a is not in the list of enumerated values.");
	}

	@Test
	public void lookupErrorTests() throws WqpException {
		when(codesService.validate(Parameters.ASSEMBLAGE, "b")).thenThrow(new WqpException(WqpExceptionId.URL_PARSING_EXCEPTION, null, null, "oops", null));

		filter.setAssemblage(Arrays.asList("b"));

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"Server error: We cannot access the list of enumerated values to validate your parameter(assemblage).");
	}

	@Test
	public void patternTests() throws IOException {
		filter.setCommandavoid(Arrays.asList("a"));
		filter.setCountrycode(Arrays.asList("a"));
		filter.setCountycode(Arrays.asList("a"));
		filter.setHuc(Arrays.asList("a"));
		filter.setMimeType("a");
		filter.setMinactivities("a");
		filter.setMinresults("a");
		filter.setPCode(Arrays.asList("a"));
		filter.setSiteid(Arrays.asList("a"));
		filter.setSorted("a");
		filter.setStatecode(Arrays.asList("a"));
		filter.setZip("a");

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(12, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of command.avoid=a must match the format " + FilterParameters.REGEX_AVOID,
				"The value of countrycode=a must match the format " + FilterParameters.REGEX_FIPS_COUNTRY,
				"The value of countycode=a must match the format " + FilterParameters.REGEX_FIPS_COUNTY,
				"The value of huc=a must match the format " + FilterParameters.REGEX_HUC,
				"The value of mimeType=a must match the format " + FilterParameters.REGEX_MIMETYPES,
				"The value of minactivities=a must match the format " + FilterParameters.REGEX_POSITIVE_INT,
				"The value of minresults=a must match the format " + FilterParameters.REGEX_POSITIVE_INT,
				"The value of pCode=a must match the format " + FilterParameters.REGEX_PCODE,
				"The value of siteid=a must match the format " + FilterParameters.REGEX_SITEID,
				"The value of sorted=a must match the format " + FilterParameters.REGEX_YES_NO,
				"The value of statecode=a must match the format " + FilterParameters.REGEX_FIPS_STATE,
				"The value of zip=a must match the format " + FilterParameters.REGEX_YES_NO);
	}


	@Test
	public void pointLocationTests() throws IOException {
		filter.setLat("43");
		filter.setLong(null);
		filter.setWithin(null);
		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"Parameter(s) [lat] require(s) [long, within].");

		filter.setLat("43");
		filter.setLong("-88");
		filter.setWithin(null);
		validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"Parameter(s) [lat, long] require(s) [within].");

		filter.setLat(null);
		filter.setLong("-88");
		filter.setWithin(null);
		validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"Parameter(s) [long] require(s) [lat, within].");

		filter.setLat(null);
		filter.setLong("-88");
		filter.setWithin("10");
		validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"Parameter(s) [long, within] require(s) [lat].");

		filter.setLat(null);
		filter.setLong(null);
		filter.setWithin("10");
		validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"Parameter(s) [within] require(s) [lat, long].");

		filter.setLat("43");
		filter.setLong(null);
		filter.setWithin("10");
		validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"Parameter(s) [lat, within] require(s) [long].");
	}

	@Test
	public void fetchNoValueTests() throws IOException {
		when(fetchService.fetch(anyString(), any(URL.class))).thenReturn(new ArrayList<String>());

		filter.setNldiurl("https://usgs.gov");

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of nldiurl=https://usgs.gov had no values were found.");
	}

	@Test
	public void fetchErrorTests() throws IOException {
		when(fetchService.fetch(anyString(), any(URL.class))).thenThrow(new IOException());

		filter.setNldiurl("https://usgs.gov");

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of nldiurl=https://usgs.gov cannot be accessed to validate your request.");
	}

	@Test
	public void bBoxTests() throws IOException {
		BoundingBox bBox = new BoundingBox("4,3,2,1");
		filter.setBBox(bBox);

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of bBox=4,3,2,1 is not a valid bounding box.");
	}

	@Test
	public void enumLookupTests() throws IOException {
		filter.setDataProfile("a");

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of dataProfile=a is not in the list of enumerated values.");
	}

	@Test
	public void rangeTests() throws IOException {
		filter.setLat("a");
		filter.setLong("a");
		filter.setWithin("1");

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(2, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of lat=a is not between -90 and 90.",
				"The value of long=a is not between -180 and 180.");
	}

	@Test
	public void wqpDateTests() throws IOException {
		filter.setStartDateHi("a");
		filter.setStartDateLo("a");

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(2, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of startDateHi=a must be a valid date in the format MM-dd-yyyy.",
				"The value of startDateLo=a must be a valid date in the format MM-dd-yyyy.");
	}

	@Test
	public void minTests() throws IOException {
		filter.setWithin("a");
		filter.setLat("43");
		filter.setLong("-88");

		Set<ConstraintViolation<FilterParameters>> validationErrors = validator.validate(filter);
		assertEquals(1, validationErrors.size());
		assertValidationResults(validationErrors,
				"The value of within=a must be a number greater than or equal to 0.");
	}

	public void assertValidationResults(Set<ConstraintViolation<FilterParameters>> actual, String... expected) {
		List<String> actualStrings = new ArrayList<>();
		for (ConstraintViolation<FilterParameters> x : actual) {
			actualStrings.add(x.getMessage());
		}
		assertThat(actualStrings, containsInAnyOrder(expected));
	}

}
