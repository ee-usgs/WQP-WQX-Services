package gov.usgs.cida.wqp.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gov.usgs.cida.wqp.BaseSpringTest;

public class MimeTypeTest {

	@Test
	public void fromStringTest() {
		assertEquals(MimeType.csv, MimeType.csv.fromString("abc"));
		assertEquals(MimeType.csv, MimeType.csv.fromString(BaseSpringTest.CSV));
		assertEquals(MimeType.xlsx, MimeType.csv.fromString(BaseSpringTest.XLSX));
		assertEquals(MimeType.xml, MimeType.csv.fromString("AppliCation/XML"));
		assertEquals(MimeType.tsv, MimeType.csv.fromString("text/tab-separated-values"));
	}
}
