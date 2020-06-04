package gov.usgs.wma.wqp.parameter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CustomStringToArrayConverterTest {

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
