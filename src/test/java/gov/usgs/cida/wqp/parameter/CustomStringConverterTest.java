package gov.usgs.cida.wqp.parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

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
