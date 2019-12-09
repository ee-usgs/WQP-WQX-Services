package gov.usgs.cida.wqp.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.FetchService;
import gov.usgs.cida.wqp.service.FetchServiceTest;

@SpringBootTest(classes={NldiFetchValidator.class})
public class NldiFetchValidatorTest extends BaseTest {

	@MockBean
	protected ConstraintValidatorContext context;
	@MockBean
	protected FetchService fetchService;
	@MockBean
	protected ConstraintViolationBuilder cvb;

	@Autowired
	private NldiFetchValidator validator;

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
