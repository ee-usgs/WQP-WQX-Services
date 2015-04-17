package gov.usgs.cida.wqp.util;

import org.springframework.http.MediaType;

import gov.usgs.cida.wqp.parameter.ParameterMap;
import gov.usgs.cida.wqp.parameter.Parameters;

public class PutSomeWhereElse implements HttpConstants {

	public static String getMimeType(ParameterMap parameters, String defaultMimeType) {
		String mimeType = (String) parameters.getQueryParameters().get(Parameters.MIMETYPE.toString());
		if (null == mimeType) {
			return defaultMimeType;
		} else {
			return mimeType;
		}
	}
	
	public static String getContentType(ParameterMap parameters, String defaultMimeType) {
		String contentType = MIME_TYPE_TEXT_CSV;
		String mimeType = getMimeType(parameters, defaultMimeType);
		if (null != mimeType) {
			switch (mimeType) {
			case MEDIA_TYPE_TSV:
				contentType = MIME_TYPE_TEXT_TSV;
				break;
			case MEDIA_TYPE_XML:
				contentType = MediaType.APPLICATION_XML_VALUE;
				break;
			case MEDIA_TYPE_JSON:
				contentType = MediaType.APPLICATION_JSON_VALUE;
				break;
			case MEDIA_TYPE_XLSX:
				contentType = MIME_TYPE_XLSX;
				break;
			default:
				contentType = MIME_TYPE_TEXT_CSV;
				break;
			}
		}
		return contentType;
	}

}
