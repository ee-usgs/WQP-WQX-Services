package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import gov.usgs.cida.wqp.BaseTest;
import gov.usgs.cida.wqp.parameter.BoundingBox;

public class BBoxValidatorTest extends BaseTest {

	@Mock
	protected ConstraintValidatorContext context;

	protected BBoxValidator validator;

	@Before
	public void setup() {
		validator = new BBoxValidator();
	}

	@Test
	public void testBbox_allValuesWithinBounds() {
		BoundingBox bbox = new BoundingBox("-88,43,-86,45");
		assertTrue(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_NaN() {
		BoundingBox bbox = new BoundingBox("a,b,c,d");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_firstLatTooLarge() {
		BoundingBox bbox = new BoundingBox("200,43,-86,45");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_firstLatTooSmall() {
		BoundingBox bbox = new BoundingBox("-200,43,-86,45");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_firstLongTooLarge() {
		BoundingBox bbox = new BoundingBox("-88,143,-86,45");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_firstLongTooSmall() {
		BoundingBox bbox = new BoundingBox("-88,-143,-86,45");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_secondLatTooLarge() {
		BoundingBox bbox = new BoundingBox("-88,43,200,45");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_secondLarTooSmall() {
		BoundingBox bbox = new BoundingBox("-88,43,-200,45");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_secondLongTooLarge() {
		BoundingBox bbox = new BoundingBox("-88,43,-86,145");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_secondLongTooSmall() {
		BoundingBox bbox = new BoundingBox("-88,43,-86,-145");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_minLatLargerThanMax() {
		BoundingBox bbox = new BoundingBox("88,43,86,45");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_minLongLagerThanMax() {
		BoundingBox bbox = new BoundingBox("-88,45,-86,43");
		assertFalse(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_null() {
		BoundingBox bbox = new BoundingBox(null);
		assertTrue(validator.isValid(bbox, context));
	}

	@Test
	public void testBbox_emptyString() {
		BoundingBox bbox = new BoundingBox("");
		assertTrue(validator.isValid(bbox, context));
	}

}