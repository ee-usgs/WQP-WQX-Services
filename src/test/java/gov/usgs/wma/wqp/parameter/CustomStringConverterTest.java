package gov.usgs.wma.wqp.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CustomStringConverterTest {

	CustomStringConverter converter = new CustomStringConverter();

	@Test
	public void emptyStringTest() {
		assertNull(converter.convert(null));
		assertNull(converter.convert(""));
		assertNull(converter.convert(" "));
	}

	@Test
	public void hasNoDelimiterTest() {
		assertEquals("wow", converter.convert("wow"));
	}

	@Test
	public void hasDelimiterTest() {
		assertEquals(" ;;wow;this is fun;;  ", converter.convert(" ;;wow;this is fun;;  "));
	}

}
