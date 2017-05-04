package gov.usgs.cida.wqp.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.MediaType;

public class MimeTypeTest {

	@Test
	public void fromMediaTypeDefault() {
		assertEquals(MimeType.csv, MimeType.csv.fromMediaType(new MediaType("abc")));
	}

	@Test
	public void fromMediaTypeSame() {
		String[] parts = HttpConstants.MIME_TYPE_CSV.split("/");
		assertEquals(MimeType.csv, MimeType.csv.fromMediaType(new MediaType(parts[0],parts[1])));
	}

	@Test
	public void fromMediaTypeDifferent() {
		String[] parts = HttpConstants.MIME_TYPE_XLSX.split("/");
		assertEquals(MimeType.xlsx, MimeType.csv.fromMediaType(new MediaType(parts[0],parts[1])));
	}

}
