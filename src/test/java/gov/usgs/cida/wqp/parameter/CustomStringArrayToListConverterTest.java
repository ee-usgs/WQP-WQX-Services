package gov.usgs.cida.wqp.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class CustomStringArrayToListConverterTest {

	CustomStringArrayToListConverter converter = new CustomStringArrayToListConverter();

	@Test
	public void emptyStringTest() {
		assertNull(converter.convert(new String[0]));
		assertNull(converter.convert(new String[]{}));
		assertNull(converter.convert(new String[]{null}));
		assertNull(converter.convert(new String[]{""}));
		assertNull(converter.convert(new String[] {"","",";"}));
	}

	@Test
	public void hasNoDelimiterTest() {
		assertEquals(Arrays.asList("wow"), converter.convert(new String[]{"wow"}));
	}

	@Test
	public void hasDelimiterTest() {
		assertEquals(Arrays.asList("wow", "this", "is", "fun"), converter.convert(new String[]{"", " ;;wow;this", "is", "fun;; ", "", " "}));
	}

}
