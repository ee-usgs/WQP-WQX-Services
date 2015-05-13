package gov.usgs.cida.wqp.webservice.result;

import gov.cida.cdat.io.TransformOutputStream;
import gov.cida.cdat.transform.ManyPatternTransformer;
import gov.cida.cdat.transform.TerminatingTransformer;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ResultColumnMapping extends TransformOutputStream {

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
		mappings.put("ORGANIZATION", "OrganizationIdentifier");
		mappings.put("ORGANIZATION_NAME", "OrganizationFormalName");
		mappings.put("ACTIVITY", "ActivityIdentifier");
		mappings.put("ACTIVITY_TYPE_CODE", "ActivityTypeCode");
		mappings.put("SAMPLE_MEDIA", "ActivityMediaName");
		mappings.put("ACTIVITY_MEDIA_SUBDIV_NAME", "ActivityMediaSubdivisionName");
		mappings.put("EVENT_DATE", "ActivityStartDate");
		mappings.put("ACTIVITY_START_TIME", "ActivityStartTime/Time");
		mappings.put("ACT_START_TIME_ZONE", "ActivityStartTime/TimeZoneCode");
		mappings.put("ACTIVITY_STOP_DATE", "ActivityEndDate");
		mappings.put("ACTIVITY_STOP_TIME", "ActivityEndTime/Time");
		mappings.put("ACT_STOP_TIME_ZONE", "ActivityEndTime/TimeZoneCode");
		mappings.put("ACTIVITY_DEPTH", "ActivityDepthHeightMeasure/MeasureValue");
		mappings.put("ACTIVITY_DEPTH_UNIT", "ActivityDepthHeightMeasure/MeasureUnitCode");
		mappings.put("ACTIVITY_DEPTH_REF_POINT", "ActivityDepthAltitudeReferencePointText");
		mappings.put("ACTIVITY_UPPER_DEPTH", "ActivityTopDepthHeightMeasure/MeasureValue");
		mappings.put("ACTIVITY_UPPER_DEPTH_UNIT", "ActivityTopDepthHeightMeasure/MeasureUnitCode");
		mappings.put("ACTIVITY_LOWER_DEPTH", "ActivityBottomDepthHeightMeasure/MeasureValue");
		mappings.put("ACTIVITY_LOWER_DEPTH_UNIT", "ActivityBottomDepthHeightMeasure/MeasureUnitCode");
		mappings.put("PROJECT_ID", "ProjectIdentifier");
		mappings.put("ACTIVITY_CONDUCTING_ORG", "ActivityConductingOrganizationText");
		mappings.put("SITE_ID", "MonitoringLocationIdentifier");
		mappings.put("ACTIVITY_COMMENT", "ActivityCommentText");
		mappings.put("SAMPLE_AQUIFER_NAME", "SampleAquifer");
		mappings.put("HYDROLOGIC_CONDITION_NAME", "HydrologicCondition");
		mappings.put("HYDROLOGIC_EVENT_NAME", "HydrologicEvent");
		mappings.put("SAMPLE_COLLECT_METHOD_ID", "SampleCollectionMethod/MethodIdentifier");
		mappings.put("SAMPLE_COLLECT_METHOD_CTX", "SampleCollectionMethod/MethodIdentifierContext");
		mappings.put("SAMPLE_COLLECT_METHOD_NAME", "SampleCollectionMethod/MethodName");
		mappings.put("SAMPLE_COLLECT_EQUIP_NAME", "SampleCollectionEquipmentName");
		mappings.put("RESULT_DETECTION_CONDITION_TX", "ResultDetectionConditionText");
		mappings.put("CHARACTERISTIC_NAME", "CharacteristicName");
		mappings.put("SAMPLE_FRACTION_TYPE", "ResultSampleFractionText");
		mappings.put("RESULT_MEASURE_VALUE", "ResultMeasureValue");
		mappings.put("RESULT_UNIT", "ResultMeasure/MeasureUnitCode");
		mappings.put("RESULT_MEAS_QUAL_CODE", "MeasureQualifierCode");
		mappings.put("RESULT_VALUE_STATUS", "ResultStatusIdentifier");
		mappings.put("STATISTIC_TYPE", "StatisticalBaseCode");
		mappings.put("RESULT_VALUE_TYPE", "ResultValueTypeName");
		mappings.put("WEIGHT_BASIS_TYPE", "ResultWeightBasisText");
		mappings.put("DURATION_BASIS", "ResultTimeBasisText");
		mappings.put("TEMPERATURE_BASIS_LEVEL", "ResultTemperatureBasisText");
		mappings.put("PARTICLE_SIZE", "ResultParticleSizeBasisText");
		mappings.put("PRECISION", "PrecisionValue");
		mappings.put("RESULT_COMMENT", "ResultCommentText");
		mappings.put("P_CODE", "USGSPCode");
		mappings.put("RESULT_DEPTH_MEAS_VALUE", "ResultDepthHeightMeasure/MeasureValue");
		mappings.put("RESULT_DEPTH_MEAS_UNIT_CODE", "ResultDepthHeightMeasure/MeasureUnitCode");
		mappings.put("RESULT_DEPTH_ALT_REF_PT_TXT", "ResultDepthAltitudeReferencePointText");
		mappings.put("SAMPLE_TISSUE_TAXONOMIC_NAME", "SubjectTaxonomicName");
		mappings.put("SAMPLE_TISSUE_ANATOMY_NAME", "SampleTissueAnatomyName");
		mappings.put("ANALYTICAL_PROCEDURE_ID", "ResultAnalyticalMethod/MethodIdentifier");
		mappings.put("ANALYTICAL_PROCEDURE_SOURCE", "ResultAnalyticalMethod/MethodIdentifierContext");
		mappings.put("ANALYTICAL_METHOD_NAME", "ResultAnalyticalMethod/MethodName");
		mappings.put("ANALYTICAL_METHOD_CITATION", "MethodDescriptionText");
		mappings.put("LAB_NAME", "LaboratoryName");
		mappings.put("ANALYSIS_DATE_TIME", "AnalysisStartDate");
		mappings.put("LAB_REMARK", "ResultLaboratoryCommentText");
		mappings.put("DETECTION_LIMIT_DESC", "DetectionQuantitationLimitTypeName");
		mappings.put("DETECTION_LIMIT", "DetectionQuantitationLimitMeasure/MeasureValue");
		mappings.put("DETECTION_LIMIT_UNIT", "DetectionQuantitationLimitMeasure/MeasureUnitCode");
		mappings.put("ANALYSIS_PREP_DATE_TX", "PreparationStartDate");
		
		patterns  = new ManyPatternTransformer(mappings);
		transform = new TerminatingTransformer("\n".getBytes(), patterns);
	}
	
	public ResultColumnMapping(OutputStream target) {
		super(target, transform);
	}

}
