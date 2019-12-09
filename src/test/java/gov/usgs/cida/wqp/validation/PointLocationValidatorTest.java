package gov.usgs.cida.wqp.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintViolationCreationContext;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.parameter.FilterParameters;

public class PointLocationValidatorTest extends BaseTest {

	protected ConstraintValidatorContext context;

	PointLocationValidator validator;

	@BeforeEach
	public void setup() {
		validator = new PointLocationValidator();
		context = new ConstraintValidatorContextImpl(null, null, PathImpl.createPathFromString("WOW"), null, null);
	}

	@Test
	public void testNullValue() throws IOException {
		assertTrue(validator.isValid(null, context));

		assertTrue(validator.isValid(new FilterParameters(), context));
	}

	@Test
	public void happyPathTest() throws IOException {
		FilterParameters fp = new FilterParameters();
		fp.setLat("43.383");
		fp.setLong("-88.977");
		fp.setWithin("10");
		assertTrue(validator.isValid(fp, context));
	}

	@Test
	public void missingLatTest() throws IOException {
		FilterParameters fp = new FilterParameters();
		fp.setLong("-88.977");
		fp.setWithin("10");
		assertFalse(validator.isValid(fp, context));
		assertNoLat(context);
	}

	@Test
	public void emptyLatTest() throws IOException {
		FilterParameters fp = new FilterParameters();
		fp.setLat("");
		fp.setLong("-88.977");
		fp.setWithin("10");
		assertFalse(validator.isValid(fp, context));
		assertNoLat(context);
	}

	@SuppressWarnings("unchecked")
	protected void assertNoLat(ConstraintValidatorContext context) {
		ConstraintViolationCreationContext x = ((ConstraintValidatorContextImpl) context.unwrap(HibernateConstraintValidatorContext.class)).getConstraintViolationCreationContexts().get(0);
		Map<String, Object> ev = x.getExpressionVariables();
		assertEquals(2, ev.size());
		Set<String> contains = (Set<String>) ev.get("containsSet");
		assertThat(contains, containsInAnyOrder("long", "within"));
		Set<String> missing = (Set<String>) ev.get("missingSet");
		assertThat(missing, containsInAnyOrder("lat"));
		assertEquals("{message}", x.getMessage());
	}

	@Test
	public void missingLongTest() throws IOException {
		FilterParameters fp = new FilterParameters();
		fp.setLat("43.383");
		fp.setWithin("10");
		assertFalse(validator.isValid(fp, context));
		assertNoLong(context);
	}

	@Test
	public void emptyLongTest() throws IOException {
		FilterParameters fp = new FilterParameters();
		fp.setLat("43.383");
		fp.setLong("");
		fp.setWithin("10");
		assertFalse(validator.isValid(fp, context));
		assertNoLong(context);
	}

	@SuppressWarnings("unchecked")
	protected void assertNoLong(ConstraintValidatorContext context) {
		ConstraintViolationCreationContext x = ((ConstraintValidatorContextImpl) context.unwrap(HibernateConstraintValidatorContext.class)).getConstraintViolationCreationContexts().get(0);
		Map<String, Object> ev = x.getExpressionVariables();
		assertEquals(2, ev.size());
		Set<String> contains = (Set<String>) ev.get("containsSet");
		assertThat(contains, containsInAnyOrder("lat", "within"));
		Set<String> missing = (Set<String>) ev.get("missingSet");
		assertThat(missing, containsInAnyOrder("long"));
		assertEquals("{message}", x.getMessage());
	}

	@Test
	public void missingWithinTest() throws IOException {
		FilterParameters fp = new FilterParameters();
		fp.setLat("43.383");
		fp.setLong("-88.977");
		assertFalse(validator.isValid(fp, context));
		assertNoWithin(context);
	}

	@Test
	public void emptyWithinTest() throws IOException {
		FilterParameters fp = new FilterParameters();
		fp.setLat("43.383");
		fp.setLong("-88.977");
		fp.setWithin("");
		assertFalse(validator.isValid(fp, context));
		assertNoWithin(context);
	}

	@SuppressWarnings("unchecked")
	protected void assertNoWithin(ConstraintValidatorContext context) {
		ConstraintViolationCreationContext x = ((ConstraintValidatorContextImpl) context.unwrap(HibernateConstraintValidatorContext.class)).getConstraintViolationCreationContexts().get(0);
		Map<String, Object> ev = x.getExpressionVariables();
		assertEquals(2, ev.size());
		Set<String> contains = (Set<String>) ev.get("containsSet");
		assertThat(contains, containsInAnyOrder("lat", "long"));
		Set<String> missing = (Set<String>) ev.get("missingSet");
		assertThat(missing, containsInAnyOrder("within"));
		assertEquals("{message}", x.getMessage());
	}

}
