package gov.usgs.cida.wqp.webservice;

import gov.cida.cdat.io.TransformOutputStream;
import gov.cida.cdat.transform.ManyPatternTransformer;
import gov.cida.cdat.transform.TerminatingTransformer;

import java.io.OutputStream;

public class StationColumnMapper extends TransformOutputStream {

	static TerminatingTransformer transform;
	static ManyPatternTransformer patterns;
	
	static {
		patterns = new ManyPatternTransformer();
		
		patterns.addMapping("ORGANIZATION_NAME","OrganizationFormalName");
		patterns.addMapping("ORGANIZATION","OrganizationIdentifier");
		patterns.addMapping("SITE_ID","MonitoringLocationIdentifier");
		patterns.addMapping("STATION_NAME","MonitoringLocationName");
		patterns.addMapping("STATION_TYPE_NAME","MonitoringLocationTypeName");
		patterns.addMapping("DESCRIPTION_TEXT","MonitoringLocationDescriptionText");
		patterns.addMapping("HUC_8", "HUCEightDigitCode");
		patterns.addMapping("HUC_12", "HUCTwelveDigitCode");
		patterns.addMapping("CONTRIB_DRAIN_AREA_VALUE","ContributingDrainageAreaMeasure/MeasureValue");
		patterns.addMapping("CONTRIB_DRAIN_AREA_UNIT","ContributingDrainageAreaMeasure/MeasureUnitCode");
		patterns.addMapping("DRAIN_AREA_VALUE","DrainageAreaMeasure/MeasureValue");
		patterns.addMapping("DRAIN_AREA_UNIT","DrainageAreaMeasure/MeasureUnitCode");
		patterns.addMapping("LATITUDE","LatitudeMeasure");
		patterns.addMapping("LONGITUDE","LongitudeMeasure");
		patterns.addMapping("MAP_SCALE","SourceMapScaleNumeric");
		patterns.addMapping("GEOPOSITION_ACCY_VALUE","HorizontalAccuracyMeasure/MeasureValue");
		patterns.addMapping("GEOPOSITION_ACCY_UNIT","HorizontalAccuracyMeasure/MeasureUnitCode");
		patterns.addMapping("GEOPOSITIONING_METHOD","HorizontalCollectionMethodName");
		patterns.addMapping("HDATUM_ID_CODE","HorizontalCoordinateReferenceSystemDatumName");
		patterns.addMapping("ELEVATION_VALUE","VerticalMeasure/MeasureValue");
		patterns.addMapping("ELEVATION_UNIT","VerticalMeasure/MeasureUnitCode");
		patterns.addMapping("VERTICAL_ACCURACY_VALUE","VerticalAccuracyMeasure/MeasureValue");
		patterns.addMapping("VERTICAL_ACCURACY_UNIT","VerticalAccuracyMeasure/MeasureUnitCode");
		patterns.addMapping("ELEVATION_METHOD","VerticalCollectionMethodName");
		patterns.addMapping("VDATUM_ID_CODE","VerticalCoordinateReferenceSystemDatumName");
		patterns.addMapping("COUNTRY_CODE","CountryCode");
		patterns.addMapping("STATE_CODE","StateCode");
		patterns.addMapping("COUNTY_CODE","CountyCode");
		patterns.addMapping("NAT_AQFR_NAME","AquiferName");
		patterns.addMapping("AQFR_NAME","FormationTypeText");
		patterns.addMapping("AQFR_TYPE_NAME","AquiferTypeName");
		patterns.addMapping("CONSTRUCTION_DATE","ConstructionDateText");
		patterns.addMapping("WELL_DEPTH_VALUE","WellDepthMeasure/MeasureValue");
		patterns.addMapping("WELL_DEPTH_UNIT","WellDepthMeasure/MeasureUnitCode");
		patterns.addMapping("HOLE_DEPTH_VALUE","WellHoleDepthMeasure/MeasureValue");
		patterns.addMapping("HOLE_DEPTH_UNIT","WellHoleDepthMeasure/MeasureUnitCode");
//		STATION_ID,
//		SITE_TYPE,
//		GOVERNMENTAL_UNIT_CODE	
//		DATA_SOURCE,
		
		transform = new TerminatingTransformer("\n".getBytes(), patterns);
	}
	
	public StationColumnMapper(OutputStream target) {
		super(target, transform);
	}

}
