package gov.usgs.cida.wqp.webservice.SimpleStation;

import static gov.usgs.cida.wqp.webservice.StationColumnMapping.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import gov.cida.cdat.transform.Transformer;


public class SimpleStationMapReformater extends Transformer {

	private final Transformer transformFurther;
	
	
	public SimpleStationMapReformater(Transformer transformFurther) {
		this.transformFurther = transformFurther;
	}
	
	
	@Override
	public byte[] transform(final byte[] bytes, final int off, final int len) {
		Object obj = extractObject(bytes,off,len);
		if (null == obj) {
			return new byte[0];
		}
		return transform(obj);
	}

	
	@Override
	@SuppressWarnings("unchecked") // cast of Map<String,String>
	public <T> byte[] transform(T obj) {
		Map<String,String> map = null;
		
		if (obj instanceof Map) {
			map = (Map<String,String>) obj;
		} else {
			throw new IllegalArgumentException(" write(Object) only handles Map<String, String>");
		}
		
		LinkedHashMap<String,Object> rowMap = new LinkedHashMap<>();
		LinkedList<BigDecimal> coords = new LinkedList<>();
		LinkedHashMap<String,String> properties = new LinkedHashMap<>();
		LinkedHashMap<String, Object> geom = new LinkedHashMap<>();
		
		rowMap.put("type", "Feature");
		rowMap.put("geometry", geom);
		geom.put("type","Point");
		geom.put("coordinates",coords);				
		coords.add(new BigDecimal(map.get(KEY_LONGITUDE)));
		coords.add(new BigDecimal(map.get(KEY_LATITUDE)));
		rowMap.put("properties", properties);
		properties.put("ProviderName",escapeValue(map,KEY_DATA_SOURCE));
		properties.put("OrganizationIdentifier",escapeValue(map,KEY_ORGANIZATION));
		properties.put("OrganizationFormalName",escapeValue(map,KEY_ORGANIZATION_NAME));
		properties.put("MonitoringLocationIdentifier",escapeValue(map,KEY_SITE_ID));
		properties.put("MonitoringLocationName",escapeValue(map,KEY_STATION_NAME));
		properties.put("ResolvedMonitoringLocationTypeName",escapeValue(map,KEY_SITE_TYPE));
		
		return transformFurther.transform(rowMap);
		
		// TODO keep this for a bit while we trouble shoot but then it can be deleted
//		writeToStream("{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[");
//		writeToStream(getValue(K_LONGITUDE));
//		writeToStream(",");
//		writeToStream(getValue(K_LATITUDE));
//		writeToStream("]},\"properties\":{\"ProviderName\":\"");
//		writeToStream(getValue(K_DATA_SOURCE));
//		writeToStream("\",\"OrganizationIdentifier\":\"");
//		writeToStream(getValue(K_ORGANIZATION));
//		writeToStream("\",\"OrganizationFormalName\":\"");
//		writeToStream(getValue(K_ORGANIZATION_NAME));
//		writeToStream("\",\"MonitoringLocationIdentifier\":\"");
//		writeToStream(getValue(K_SITE_ID));
//		writeToStream("\",\"MonitoringLocationName\":\"");
//		writeToStream(getValue(K_STATION_NAME));
//		writeToStream("\",\"ResolvedMonitoringLocationTypeName\":\"");
//		writeToStream(getValue(K_SITE_TYPE));
//		writeToStream("\"}}");
	}

	
	String escapeValue(Map<String,String> map, String key) {
		Object value = map.get(key);
		return transform.encode( value==null ?"" :value.toString() );
	}
	
	@Override
	public byte[] getRemaining() {
		return transformFurther.getRemaining();
	}
}
