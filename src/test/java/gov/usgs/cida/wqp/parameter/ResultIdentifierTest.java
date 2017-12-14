package gov.usgs.cida.wqp.parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ResultIdentifierTest {

	@Test
	public void nullConstructor() {
		ResultIdentifier x = new ResultIdentifier(null);
		assertNull(x.getDataSource());
		assertNull(x.getResultId());
		assertNull(x.getSingle());
	}

	@Test
	public void emptyStringConstructor() {
		ResultIdentifier x = new ResultIdentifier("");
		assertNull(x.getDataSource());
		assertNull(x.getResultId());
		assertEquals("", x.getSingle());
	}

	@Test
	public void noSplitConstructor() {
		ResultIdentifier x = new ResultIdentifier("abc");
		assertNull(x.getDataSource());
		assertNull(x.getResultId());
		assertEquals("abc", x.getSingle());
	}

	@Test
	public void happyPathConstructor() {
		ResultIdentifier x = new ResultIdentifier("abc-123");
		assertEquals("abc", x.getDataSource());
		assertEquals("123", x.getResultId());
		assertEquals("abc-123", x.getSingle());
	}

	@Test
	public void tooManyConstructor() {
		ResultIdentifier x = new ResultIdentifier("abc-123-def-456");
		assertNull(x.getDataSource());
		assertNull(x.getResultId());
		assertEquals("abc-123-def-456", x.getSingle());
	}

}
