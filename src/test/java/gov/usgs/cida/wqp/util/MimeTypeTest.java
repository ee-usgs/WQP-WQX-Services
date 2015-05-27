package gov.usgs.cida.wqp.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MimeTypeTest {

	@Test
	public void fromStringTest() {
		assertEquals(MimeType.csv, MimeType.csv.fromString("abc"));
		assertEquals(MimeType.csv, MimeType.csv.fromString("csv"));
		assertEquals(MimeType.xlsx, MimeType.csv.fromString("xlsx"));
		assertEquals(MimeType.xml, MimeType.csv.fromString("AppliCation/XML"));
		assertEquals(MimeType.tsv, MimeType.csv.fromString("text/tab-separated-values"));
	}
}
