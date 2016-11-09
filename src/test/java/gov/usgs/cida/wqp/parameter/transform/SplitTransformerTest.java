package gov.usgs.cida.wqp.parameter.transform;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.validation.AbstractValidator;
public class SplitTransformerTest extends BaseSpringTest {
	@Test
	public void testNullsAndEmpties_null() {
		try {
			new SplitTransformer(null);
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("A Delimiter must be provided.", e.getMessage());
		}
	}
	@Test
	public void testNullsAndEmpties_emptyString() {
		try {
			new SplitTransformer("");
			fail("Should have gotten an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("A Delimiter must be provided.", e.getMessage());
		}
	}
	@Test
	public void testSplit_multiValue() {
		SplitTransformer transformer = new SplitTransformer(AbstractValidator.DEFAULT_DELIMITER);
		String[] split = transformer.transform("a;b;c");
		assertEquals(3, split.length);
		assertEquals("a", split[0]);
		assertEquals("b", split[1]);
		assertEquals("c", split[2]);
	}
	@Test
	public void testSplit_null() {
		SplitTransformer transformer = new SplitTransformer(AbstractValidator.DEFAULT_DELIMITER);
		String[] split = transformer.transform(null);
		assertEquals(0, split.length);
		assertArrayEquals("null value gives new String[0]", new String[0], transformer.transform(null));
	}
	@Test
	public void testSplit_emptyString() {
		SplitTransformer transformer = new SplitTransformer(AbstractValidator.DEFAULT_DELIMITER);
		String[] split = transformer.transform("");
		assertEquals(0, split.length);
	}
	@Test
	public void testSplit_custom() {
		SplitTransformer transformer = new SplitTransformer("x");
		String[] split = transformer.transform("axbxc");
		assertEquals(3, split.length);
		assertEquals("a", split[0]);
		assertEquals("b", split[1]);
		assertEquals("c", split[2]);
	}
	@Test
	public void testSplit_noSplit() {
		SplitTransformer trans = new SplitTransformer("-");
		assertArrayEquals("delimiter gives new String[1] matching value", new String[]{"abc"}, trans.transform("abc"));
		assertArrayEquals("delimiter gives new String[1] matching value - even with ;", new String[]{";;a;b;;c;;;"}, trans.transform(";;a;b;;c;;;"));
	}
	@Test
	public void testSplit_extraDelimiters() {
		SplitTransformer trans = new SplitTransformer();
		String[] result = new String[]{"A", "B", "C"};
		assertArrayEquals("Do a split", result, trans.transform("A;B;C"));
		assertArrayEquals("Do a split with extra delimiters", result, trans.transform(";;A;;;B;C;;"));
	}
	@Test
	public void testSplit_extraCustomDelimiters() {
		SplitTransformer trans = new SplitTransformer("x");
		String[] result = new String[]{"A", "B", "C"};
		assertArrayEquals("Try a new delimiter", result, trans.transform("AxBxxCx"));
	}
	@Test
	public void testPruneDelimiters() {
		SplitTransformer rv = new SplitTransformer();
		assertEquals("No Delimiters", "011234", rv.pruneDelimiters("011234"));
		assertEquals("Removes Internal Delimiters", "011;234", rv.pruneDelimiters("011;;234"));
		assertEquals("Removes Trailing Delimiters", "011234", rv.pruneDelimiters("011234;;"));
		assertEquals("Removes Leading Delimiters", "011234", rv.pruneDelimiters(";;011234"));
	}
	@Test
	public void testPruneDelimiters_nullDelimiter() {
		try {
			new SplitTransformer(null);
			fail("Expect an exception, no null based separator");
		} catch (Exception e ) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	@Test
	public void testPruneDelimiters_emptyStringDelimiter() {
		try {
			new SplitTransformer("");
			fail("Expect an exception, no empty string separator");
		} catch (Exception e ) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
}