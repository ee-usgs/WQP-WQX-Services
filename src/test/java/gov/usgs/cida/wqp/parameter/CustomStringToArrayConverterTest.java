package gov.usgs.cida.wqp.parameter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import gov.usgs.cida.wqp.BaseSpringTest;

public class CustomStringToArrayConverterTest extends BaseSpringTest {

	CustomStringToArrayConverter converter = new CustomStringToArrayConverter();

	@Test
	public void emptyStringTest() {
		assertNull(converter.convert(null));
		assertNull(converter.convert(""));
		assertNull(converter.convert(";;;;;"));
	}

	@Test
	public void hasNoDelimiterTest() {
		assertArrayEquals(new String[]{"wow"}, converter.convert("wow"));
	}

	@Test
	public void hasDelimiterTest() {
		assertArrayEquals(new String[]{"wow", "this", "is", "fun"}, converter.convert(" ;wow;;this;is;fun;; "));
	}

}
