package gov.usgs.cida.wqp.parameter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CustomStringArrayToListConverterTest {

	CustomStringArrayToListConverter converter = new CustomStringArrayToListConverter();

	@Test
	public void emptyStringTest() {
		List<String> mt = new ArrayList<>();
		assertEquals(mt, converter.convert(new String[0]));
	}

	@Test
	public void hasNoDelimiterTest() {
		assertEquals(Arrays.asList("wow"), converter.convert(new String[]{"wow"}));
	}

	@Test
	public void hasDelimiterTest() {
		assertEquals(Arrays.asList("wow", "this", "is", "fun"), converter.convert(new String[]{"wow;this", "is", "fun"}));
	}

}
