package gov.usgs.cida.wqp.util;

import org.springframework.http.MediaType;


public enum MimeType {

	// lower case make toString produce the file extension

	csv(HttpConstants.MIME_TYPE_CSV),
	json(HttpConstants.MIME_TYPE_JSON),
	tsv(HttpConstants.MIME_TYPE_TSV),
	xlsx(HttpConstants.MIME_TYPE_XLSX),
	xml(HttpConstants.MIME_TYPE_XML),
	kml(HttpConstants.MIME_TYPE_KML),
	kmz(HttpConstants.MIME_TYPE_KMZ),
	geojson(HttpConstants.MIME_TYPE_GEOJSON);

	public final String mimeType;
	public final MediaType mediaType;

	private MimeType(String mimeType) {
		this.mimeType  = mimeType;
		String[] parts = mimeType.split("/");
		this.mediaType = new MediaType(parts[0],parts[1]);
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getExtension() {
		return this.toString();
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public MimeType fromString(String mimeType) {
		MimeType type = this;
		try {
			type = MimeType.valueOf(mimeType);
		} catch (Exception e) {
			for (MimeType aType : MimeType.values()) {
				if (aType.mimeType.equalsIgnoreCase(mimeType)) {
					return aType;
				}
			}
		}
		return type;
	}

}
