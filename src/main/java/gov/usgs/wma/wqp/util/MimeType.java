package gov.usgs.wma.wqp.util;

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
	geojson(HttpConstants.MIME_TYPE_GEOJSON),
	text(HttpConstants.MIME_TYPE_TEXT);

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

	public MimeType fromMediaType(MediaType inMediaType) {
		MimeType type = this;
		for (MimeType aType : MimeType.values()) {
			if (aType.mediaType.equals(inMediaType)) {
				return aType;
			}
		}
		return type;
	}

}
