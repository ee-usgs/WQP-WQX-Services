package gov.usgs.cida.wqp.webservice;

import gov.cida.cdat.io.TransformOutputStream;
import gov.cida.cdat.transform.ManyPatternTransformer;
import gov.cida.cdat.transform.TerminatingTransformer;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class StationColumnMapper extends TransformOutputStream {

	// Station Keys
	public static final String KEY_DATA_SOURCE       = "DATA_SOURCE";
	public static final String KEY_ORGANIZATION      = "ORGANIZATION";
	public static final String KEY_ORGANIZATION_NAME = "ORGANIZATION_NAME";
	public static final String KEY_SITE_ID           = "SITE_ID";
	public static final String KEY_STATION_NAME      = "STATION_NAME";
	public static final String KEY_LATITUDE          = "LATITUDE";
	public static final String KEY_LONGITUDE         = "LONGITUDE";
	
	// Simple Station Keys
	public static final String KEY_SITE_TYPE         = "SITE_TYPE";
	
	// Station Values
	public static final String VALUE_LATITUDE_MEASURE   = "LatitudeMeasure";
	public static final String VALUE_LONGITUDE_MEASURE  = "LongitudeMeasure";
	public static final String VALUE_ORGANIZATION_IDENTIFIER  = "OrganizationIdentifier";
	public static final String VALUE_ORGANIZATION_FORMAL_NAME = "OrganizationFormalName";
	public static final String VALUE_MONITORING_LOCATION_NAME = "MonitoringLocationName";
	public static final String VALUE_MONITORING_LOCATION_IDENTIFIER = "MonitoringLocationIdentifier";

	// Station WQX values
	public static final String VALUE_PROVIDER           = "Provider";
	public static final String VALUE_PROVIDER_NAME      = "ProviderName"; 
	public static final String VALUE_ORGANIZATION       = "Organization";
	public static final String VALUE_ORGANIZATION_DESCRIPTION = "OrganizationDescription";
	public static final String VALUE_MONITORING_LOCATION      = "MonitoringLocation";
	public static final String VALUE_MONITORING_LOCATION_IDENTITY   = "MonitoringLocationIdentity";
	public static final String VALUE_RESOLVED_MONITORING_LOCATION   = "ResolvedMonitoringLocationTypeName";
	public static final String VALUE_MONITORING_LOCATION_GEOSPATIAL = "MonitoringLocationGeospatial";
	
	
	
	public static final TerminatingTransformer transform;
	public static final ManyPatternTransformer patterns;
	public static final Map<String, String> mappings;
	
	
	static {
		mappings = new HashMap<String,String>();
		mappings.put(KEY_ORGANIZATION_NAME,VALUE_ORGANIZATION_FORMAL_NAME);
		mappings.put(KEY_ORGANIZATION,VALUE_ORGANIZATION_IDENTIFIER);
		mappings.put(KEY_SITE_ID,VALUE_MONITORING_LOCATION_IDENTIFIER);
		mappings.put(KEY_STATION_NAME,VALUE_MONITORING_LOCATION_NAME);
		mappings.put("STATION_TYPE_NAME","MonitoringLocationTypeName");
		mappings.put("DESCRIPTION_TEXT","MonitoringLocationDescriptionText");
		mappings.put("HUC_8", "HUCEightDigitCode");
		mappings.put("HUC_12", "HUCTwelveDigitCode");
		mappings.put("CONTRIB_DRAIN_AREA_VALUE","ContributingDrainageAreaMeasure/MeasureValue");
		mappings.put("CONTRIB_DRAIN_AREA_UNIT","ContributingDrainageAreaMeasure/MeasureUnitCode");
		mappings.put("DRAIN_AREA_VALUE","DrainageAreaMeasure/MeasureValue");
		mappings.put("DRAIN_AREA_UNIT","DrainageAreaMeasure/MeasureUnitCode");
		mappings.put(KEY_LATITUDE,VALUE_LATITUDE_MEASURE);
		mappings.put(KEY_LONGITUDE,VALUE_LONGITUDE_MEASURE);
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
		mappings.put("STATE_FIPS_CODE","StateCode");
		mappings.put("COUNTY_FIPS_CODE","CountyCode");
		mappings.put("NAT_AQFR_NAME","AquiferName");
		mappings.put("AQFR_NAME","FormationTypeText");
		mappings.put("AQFR_TYPE_NAME","AquiferTypeName");
		mappings.put("CONSTRUCTION_DATE","ConstructionDateText");
		mappings.put("WELL_DEPTH_VALUE","WellDepthMeasure/MeasureValue");
		mappings.put("WELL_DEPTH_UNIT","WellDepthMeasure/MeasureUnitCode");
		mappings.put("HOLE_DEPTH_VALUE","WellHoleDepthMeasure/MeasureValue");
		mappings.put("HOLE_DEPTH_UNIT","WellHoleDepthMeasure/MeasureUnitCode");
		mappings.put("DATA_SOURCE","DataSource");
		
		patterns  = new ManyPatternTransformer(mappings);
		transform = new TerminatingTransformer("\n".getBytes(), patterns);
	}
	
	public StationColumnMapper(OutputStream target) {
		super(target, transform);
	}

}
