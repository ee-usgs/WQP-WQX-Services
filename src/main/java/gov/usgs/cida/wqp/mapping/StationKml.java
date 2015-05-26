package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_SITE_ID;
import static gov.usgs.cida.wqp.mapping.StationColumn.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StationKml implements IXmlMapping {

	public static final String KEY_STYLE_URL = "STYLE_URL";
	public static final String KEY_COORDINATES = "COORDINATES";
	public static final String KEY_STATION_NAME2 = KEY_STATION_NAME + "2";

	public static final String ROOT_NODE = "kml";

	public static final String KML_DOCUMENT = "Document";
	public static final String KML_STYLE_MAP = "StyleMap";
	public static final String KML_PAIR = "Pair";
	public static final String KML_KEY = "key";
	public static final String KML_STYLE_URL = "styleUrl";
	public static final String KML_PLACEMARK = "Placemark";
	public static final String KML_NAME = "name";
	public static final String KML_EXTENDED_DATA = "ExtendedData";
	
//	public static final String KML_DATA = "Data";
	public static final String KML_DATA_ORGANIZATION_FORMAL_NAME = "Data name=\"Organization Formal Name\"";
	public static final String KML_DATA_ORGANIZATION_IDENTIFIER = "Data name=\"Organization Identifier\"";
	public static final String KML_DATA_MONITORING_LOCATION_IDENTIFIER = "Data name=\"Monitoring Location Identifier\"";
	public static final String KML_DATA_MONITORING_LOCATION_NAME = "Data name=\"Monitoring Location Name\"";
	public static final String KML_DATA_MONITORING_LOCATION_TYPE = "Data name=\"Monitoring Location Type Name\"";
	public static final String KML_DATA_MONITORING_LOCATION_DESCRIPTION = "Data name=\"Monitoring Location Description Text\"";
	public static final String KML_DATA_HUC_8 = "Data name=\"HUC Eight Digit Code\"";
	public static final String KML_DATA_CONTRIB_DRAIN_AREA_VALUE = "Data name=\"Drainage Area Measure/Measure Value\"";
	public static final String KML_DATA_CONTRIB_DRAIN_AREA_UNIT = "Data name=\"Drainage Area Measure/Measure Unit Code\"";
	public static final String KML_DATA_AQFR_TYPE_NAME = "Data name=\"AquiferTypeName\"";
	public static final String KML_DATA_AQFR_NAME = "Data name=\"AquiferName\"";
	public static final String KML_DATA_WELL_DEPTH_VALUE = "Data name=\"Well Depth Measure/Measure Value\"";
	public static final String KML_DATA_WELL_DEPTH_UNIT = "Data name=\"Well Depth Measure/Measure Unit Code\"";
	
	public static final String KML_VALUE = "value";
	public static final String KML_POINT = "Point";
	public static final String KML_COORDINATES = "coordinates";

	public static final Map<String, String> HARD_BREAK = new LinkedHashMap<>();

	public static final Map<String, List<String>> COLUMN_POSITION = new LinkedHashMap<>();
	
	public static final Map<String, List<String>> GROUPING = new LinkedHashMap<>();

	static {
		HARD_BREAK.put(KEY_SITE_ID, KML_DOCUMENT);
	}
	
	static {
		COLUMN_POSITION.put(KEY_STATION_NAME,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_NAME)));
		COLUMN_POSITION.put(KEY_STYLE_URL,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_STYLE_MAP)));
		COLUMN_POSITION.put(KEY_ORGANIZATION_NAME,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_ORGANIZATION_FORMAL_NAME,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_ORGANIZATION,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_ORGANIZATION_IDENTIFIER,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_SITE_ID,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_MONITORING_LOCATION_IDENTIFIER,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_STATION_NAME2,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_MONITORING_LOCATION_NAME,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_MONITORING_LOCATION_TYPE,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_MONITORING_LOCATION_TYPE,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_MONITORING_LOCATION_DESCRIPTION,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_MONITORING_LOCATION_DESCRIPTION,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_HUC_8,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_HUC_8,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_CONTRIB_DRAIN_AREA_VALUE,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_CONTRIB_DRAIN_AREA_VALUE,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_CONTRIB_DRAIN_AREA_UNIT,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_CONTRIB_DRAIN_AREA_UNIT,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_AQFR_TYPE_NAME,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_AQFR_TYPE_NAME,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_AQFR_NAME,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_AQFR_NAME,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_WELL_DEPTH_VALUE,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_WELL_DEPTH_VALUE,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_WELL_DEPTH_UNIT,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_EXTENDED_DATA,
						KML_DATA_WELL_DEPTH_UNIT,
						KML_VALUE)));
		COLUMN_POSITION.put(KEY_COORDINATES,
				new LinkedList<String>(Arrays.asList(
						KML_DOCUMENT,
						KML_PLACEMARK,
						KML_POINT,
						KML_COORDINATES)));
	}

	static {
		GROUPING.put(KEY_SITE_ID,
				new LinkedList<String>(Arrays.asList(KEY_STATION_NAME,
						                             KEY_STYLE_URL,
						                             KEY_ORGANIZATION_NAME,
						                             KEY_ORGANIZATION,
						                             KEY_SITE_ID,
						                             KEY_STATION_NAME2,
						                             KEY_MONITORING_LOCATION_TYPE,
						                             KEY_MONITORING_LOCATION_DESCRIPTION,
						                             KEY_HUC_8,
						                             KEY_CONTRIB_DRAIN_AREA_VALUE,
						                             KEY_CONTRIB_DRAIN_AREA_UNIT,
						                             KEY_AQFR_TYPE_NAME,
						                             KEY_AQFR_NAME,
						                             KEY_WELL_DEPTH_VALUE,
						                             KEY_WELL_DEPTH_UNIT,
						                             KEY_COORDINATES)));
	}
		
	public String getRoot() {
		return ROOT_NODE;
	}

	public String getHeader() {
		return "<kml><Document>" +
			   "<StyleMap id='ARSSite'><Pair><key>normal</key><styleUrl>http://www.waterqualitydata.us/kml/wqp_styles.kml#normalARSSite</styleUrl></Pair>" +
			   "<Pair><key>highlight</key><styleUrl>http://www.waterqualitydata.us/kml/wqp_styles.kml#highlightARSSite</styleUrl></Pair></StyleMap>" +
			   "<StyleMap id='NWISSite'><Pair><key>normal</key><styleUrl>http://www.waterqualitydata.us/kml/wqp_styles.kml#normalNWISSite</styleUrl></Pair>" +
			   "<Pair><key>highlight</key><styleUrl>http://www.waterqualitydata.us/kml/wqp_styles.kml#highlightNWISSite</styleUrl></Pair></StyleMap>" +
			   "<StyleMap id='STORETSite'><Pair><key>normal</key><styleUrl>http://www.waterqualitydata.us/kml/wqp_styles.kml#normalSTORETSite</styleUrl></Pair>" +
			   "<Pair><key>highlight</key><styleUrl>http://www.waterqualitydata.us/kml/wqp_styles.kml#highlightSTORETSite</styleUrl></Pair></StyleMap>";
	}

	public String getEntryNodeName() {
		return KML_PLACEMARK;
	}

	public Map<String, List<String>> getStructure() {
		return COLUMN_POSITION;
	}

	public Map<String, String> getHardBreak() {
		return HARD_BREAK;
	}

	public Map<String, List<String>> getGrouping() {
		return GROUPING;
	}

}
