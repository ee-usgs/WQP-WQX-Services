package gov.usgs.cida.wqp.transform;

import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.service.ILogService;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

public class MapToJsonTransformer extends Transformer {

	private boolean endPrevious = false;

	public MapToJsonTransformer(OutputStream target, Map<String, String> mapping, ILogService logService, BigDecimal logId) {
		super(target, mapping, logService, logId);
	}

	@Override
	protected void writeHeader() {
		writeToStream("{\"type\":\"FeatureCollection\",\"features\":[");
	}

	@Override
	protected void writeData(Map<String, Object> resultMap) {
		if (endPrevious) {
			writeToStream(",");
		}
		writeToStream("{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[");
		writeToStream(getValue(resultMap, StationColumn.KEY_LONGITUDE));
		writeToStream(",");
		writeToStream(getValue(resultMap, StationColumn.KEY_LATITUDE));
		writeToStream("]},\"properties\":{\"ProviderName\":\"");
		writeToStream(getValue(resultMap, StationColumn.KEY_DATA_SOURCE));
		writeToStream("\",\"OrganizationIdentifier\":\"");
		writeToStream(getValue(resultMap, StationColumn.KEY_ORGANIZATION));
		writeToStream("\",\"OrganizationFormalName\":\"");
		writeToStream(getValue(resultMap, StationColumn.KEY_ORGANIZATION_NAME));
		writeToStream("\",\"MonitoringLocationIdentifier\":\"");
		writeToStream(getValue(resultMap, StationColumn.KEY_SITE_ID));
		writeToStream("\",\"MonitoringLocationName\":\"");
		writeToStream(getValue(resultMap, StationColumn.KEY_STATION_NAME));
		writeToStream("\",\"MonitoringLocationTypeName\":\"");
		writeToStream(getValue(resultMap, StationColumn.KEY_MONITORING_LOCATION_TYPE));
		writeToStream("\",\"ResolvedMonitoringLocationTypeName\":\"");
		writeToStream(getValue(resultMap, StationColumn.KEY_SITE_TYPE));
		writeToStream("\",\"HUCEightDigitCode\":\"");
		writeToStream(getValue(resultMap, StationColumn.KEY_HUC_8));
		writeToStream("\"}}");
		endPrevious = true;
	}
	
	/** output the closing tag. */
	@Override
	public void end() {
   		writeToStream("]}");
   		super.end();
	}

	protected String getValue(Map<String, Object> resultMap, String key) {
		if (resultMap.containsKey(key) && null != resultMap.get(key)) {
			return encode(resultMap.get(key).toString());
		} else {
			return "";
		}
	}

	@Override
	public String encode(String value) {
		return StringEscapeUtils.escapeJson(value);
	}

	@Override
	protected void init() {
		//Nothing to do here
	}

}
