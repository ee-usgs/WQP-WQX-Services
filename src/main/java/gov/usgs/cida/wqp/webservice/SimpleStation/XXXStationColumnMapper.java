package gov.usgs.cida.wqp.webservice.SimpleStation;

import java.util.HashMap;
import java.util.Map;

public class XXXStationColumnMapper {

	private static Map<String, String> mappings;
	
	static {
		mappings = new HashMap<>();
		mappings.put("ORGANIZATION_NAME","OrganizationFormalName");
		mappings.put("ORGANIZATION","OrganizationIdentifier");
		mappings.put("SITE_ID","MonitoringLocationIdentifier");
		mappings.put("STATION_NAME","MonitoringLocationName");
		mappings.put("STATION_TYPE_NAME","MonitoringLocationTypeName");
		mappings.put("DESCRIPTION_TEXT","MonitoringLocationDescriptionText");
		mappings.put("HUC_8", "HUCEightDigitCode");
		mappings.put("HUC_12", "HUCTwelveDigitCode");
		mappings.put("CONTRIB_DRAIN_AREA_VALUE","ContributingDrainageAreaMeasure/MeasureValue");
		mappings.put("CONTRIB_DRAIN_AREA_UNIT","ContributingDrainageAreaMeasure/MeasureUnitCode");
		mappings.put("DRAIN_AREA_VALUE","DrainageAreaMeasure/MeasureValue");
		mappings.put("DRAIN_AREA_UNIT","DrainageAreaMeasure/MeasureUnitCode");
		mappings.put("LATITUDE","LatitudeMeasure");
		mappings.put("LONGITUDE","LongitudeMeasure");
		mappings.put("MAP_SCALE","SourceMapScaleNumeric");
		mappings.put("GEOPOSITION_ACCY_VALUE","HorizontalAccuracyMeasure/MeasureValue");
		mappings.put("GEOPOSITION_ACCY_UNIT","HorizontalAccuracyMeasure/MeasureUnitCode");
		mappings.put("GEOPOSITIONING_METHOD","HorizontalCollectionMethodName");
		mappings.put("HDATUM_ID_CODE","HorizontalCoordinateReferenceSystemDatumName");
		mappings.put("ELEVATION_VALUE","VerticalMeasure/MeasureValue");
		mappings.put("ELEVATION_UNIT","VerticalMeasure/MeasureUnitCode");
		mappings.put("VERTICAL_ACCURACY_VALUE","VerticalAccuracyMeasure/MeasureValue");
		mappings.put("VERTICAL_ACCURACY_UNIT","VerticalAccuracyMeasure/MeasureUnitCode");
		mappings.put("ELEVATION_METHOD","VerticalCollectionMethodName");
		mappings.put("VDATUM_ID_CODE","VerticalCoordinateReferenceSystemDatumName");
		mappings.put("COUNTRY_CODE","CountryCode");
		mappings.put("STATE_CODE","StateCode");
		mappings.put("COUNTY_CODE","CountyCode");
		mappings.put("NAT_AQFR_NAME","AquiferName");
		mappings.put("AQFR_NAME","FormationTypeText");
		mappings.put("AQFR_TYPE_NAME","AquiferTypeName");
		mappings.put("CONSTRUCTION_DATE","ConstructionDateText");
		mappings.put("WELL_DEPTH_VALUE","WellDepthMeasure/MeasureValue");
		mappings.put("WELL_DEPTH_UNIT","WellDepthMeasure/MeasureUnitCode");
		mappings.put("HOLE_DEPTH_VALUE","WellHoleDepthMeasure/MeasureValue");
		mappings.put("HOLE_DEPTH_UNIT","WellHoleDepthMeasure/MeasureUnitCode");
	}
	
	public static Map<String, String> getMappings() {
		return mappings;
	}
}
