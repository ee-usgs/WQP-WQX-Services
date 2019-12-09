package gov.usgs.cida.wqp.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CustomBoundingBoxConverterTest {

	private CustomBoundingBoxConverter converter = new CustomBoundingBoxConverter();

	@Test
	public void emptyStringTest() {
		assertNull(converter.convert(null));
		assertNull(converter.convert(""));
		assertNull(converter.convert(" "));
	}

	@Test
	public void hasNoDelimiterTest() {
		BoundingBox bbox = converter.convert("wow");
		assertEquals("wow", bbox.toString());
	}

	@Test
	public void hasDelimiterTest() {
		BoundingBox bbox = converter.convert(" ;;wow;this is fun;;  ");
		assertEquals(" ;;wow;this is fun;;  ", bbox.toString());
	}

	@Test
	public void realTest() {
		BoundingBox bbox = converter.convert("1,2,3,4");
		assertEquals("3", bbox.getEast());
		assertEquals("1", bbox.getWest());
		assertEquals("4", bbox.getNorth());
		assertEquals("2", bbox.getSouth());
	}


}
