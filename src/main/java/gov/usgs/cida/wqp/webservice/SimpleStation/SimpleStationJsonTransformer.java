package gov.usgs.cida.wqp.webservice.SimpleStation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

public class SimpleStationJsonTransformer extends TransformOutputStream implements ISimpleStation {

	protected Map<String, String> groupings;
	
	/** Is this the first write to the stream. */
	private boolean first = true;
	
	public SimpleStationJsonTransformer(OutputStream target) {
		super(target, null);
		groupings = new HashMap<>();
	}

	/**
	 * Passes to parent to extract object. If object is available, process.
	 * 
	 *  {@inheritDoc}
	 */
	public void write(final byte[] b, final int off, final int len) throws IOException {
		super.write(b, off, len);
		if (null == result) {
			return;
		}
		if (first) {
			prepareHeader();
		}
		prepareData();
	}
	
	private void prepareHeader() {
		writeToStream("{\"type\":\"FeatureCollection\",\"features\":[");
	}
	
	/**
	 * Extract the data and write to the stream.
	 */
	private void prepareData() {
		if (!first) {
			writeToStream(",");
		}
		writeToStream("{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[");
		writeToStream(getValue(K_LONGITUDE));
		writeToStream(",");
		writeToStream(getValue(K_LATITUDE));
		writeToStream("]},\"properties\":{\"ProviderName\":\"");
		writeToStream(getValue(K_DATA_SOURCE));
		writeToStream("\",\"OrganizationIdentifier\":\"");
		writeToStream(getValue(K_ORGANIZATION));
		writeToStream("\",\"OrganizationFormalName\":\"");
		writeToStream(getValue(K_ORGANIZATION_NAME));
		writeToStream("\",\"MonitoringLocationIdentifier\":\"");
		writeToStream(getValue(K_SITE_ID));
		writeToStream("\",\"MonitoringLocationName\":\"");
		writeToStream(getValue(K_STATION_NAME));
		writeToStream("\",\"ResolvedMonitoringLocationTypeName\":\"");
		writeToStream(getValue(K_SITE_TYPE));
		writeToStream("\"}}");
		first = false;
	}
	
	/** output the closing tag. */
	public void end() {
   		writeToStream("]}");
	}

	private String getValue(String key) {
		return StringEscapeUtils.escapeJson(result.get(key).toString());
	}

	/**
	 * Write the results to the underlying outputStream.
	 * 
	 * @param str string to write.
	 */
	private void writeToStream(final String str) {
		try {
			target.write(str.getBytes("UTF-8"));
		} catch (Exception e) {
//			LOG.error("Problem writing '" + str + "' to underlying OutputStream.", e);
			throw new RuntimeException(e);
		}
	}

}
