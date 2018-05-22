package gov.usgs.cida.wqp.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.FetchService;
import gov.usgs.cida.wqp.service.FetchServiceTest;

public class NldiFetchValidatorTest extends BaseTest {

	@Mock
	protected ConstraintValidatorContext context;
	@Mock
	protected FetchService fetchService;
	@Mock
	protected ConstraintViolationBuilder cvb;

	NldiFetchValidator validator;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		validator = new NldiFetchValidator();
		Whitebox.setInternalState(validator, "fetchService", fetchService);
	}

	@Test
	public void testNullValue() throws IOException {
		when(fetchService.fetch(anyString(), any(URL.class))).thenReturn(new ArrayList<String>());
		assertTrue(validator.isValid(null, context));
		verify(fetchService, never()).fetch(anyString(), any(URL.class));

		assertTrue(validator.isValid(new FilterParameters(), context));
		verify(fetchService, never()).fetch(anyString(), any(URL.class));
	}

	@Test
	public void testEmptyStringValue() throws IOException {
		when(fetchService.fetch(anyString(), any(URL.class))).thenReturn(new ArrayList<String>());
		FilterParameters fp = new FilterParameters();
		fp.setNldiurl("");
		assertTrue(validator.isValid(new FilterParameters(), context));
		verify(fetchService, never()).fetch(anyString(), any(URL.class));
	}

	@Test
	public void cannotContactUrlTest() throws IOException {
		when(fetchService.fetch(anyString(), any(URL.class))).thenThrow(new IOException());
		when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(cvb);
		FilterParameters fp = new FilterParameters();
		fp.setNldiurl("https://usgs.gov");
		assertFalse(validator.isValid(fp, context));
		verify(fetchService).fetch(anyString(), any(URL.class));
		verify(cvb).addConstraintViolation();
	}

	@Test
	public void noValuesTest() throws IOException {
		when(fetchService.fetch(anyString(), any(URL.class))).thenReturn(new ArrayList<String>());
		FilterParameters fp = new FilterParameters();
		fp.setNldiurl("https://usgs.gov");
		assertFalse(validator.isValid(fp, context));
		verify(fetchService).fetch(anyString(), any(URL.class));
	}

	@Test
	public void happyPathTest() throws IOException {
		when(fetchService.fetch(anyString(), any(URL.class))).thenReturn(FetchServiceTest.NLDI_IDENTIFIERS);
		FilterParameters fp = new FilterParameters();
		fp.setNldiurl("https://usgs.gov");
		assertTrue(validator.isValid(fp, context));
		verify(fetchService).fetch(anyString(), any(URL.class));
		assertEquals(12, fp.getNldiSites().size());
		assertThat(fp.getNldiSites(), containsInAnyOrder(FetchServiceTest.NLDI_IDENTIFIERS.toArray()));
	}

}
