package gov.usgs.cida.wqp.mapping;


public abstract class BaseWqx {

	public static final String ROOT_NODE = "WQX";
	public static final String ROOT_NAMESPACE = "xmlns='http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/ http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/index.xsd'";

	public static final String WQX_MEASURE_VALUE = "MeasureValue";
	public static final String WQX_MEASURE_UNIT = "MeasureUnitCode";
	public static final String WQX_TIME = "Time";
	public static final String WQX_TIME_ZONE = "TimeZoneCode";
	public static final String WQX_METHOD_ID = "MethodIdentifier";
	public static final String WQX_METHOD_CONTEXT = "MethodIdentifierContext";
	public static final String WQX_METHOD_NAME = "MethodName";

	public static final String WQX_PROVIDER = "Provider";
	public static final String WQX_PROVIDER_NAME = "ProviderName"; 

	public static final String WQX_ORGANIZATION = "Organization";
	public static final String WQX_ORGANIZATION_DESCRIPTION = "OrganizationDescription";
	public static final String WQX_ORGANIZATION_IDENTIFIER = "OrganizationIdentifier";
	public static final String WQX_ORGANIZATION_FORMAL_NAME = "OrganizationFormalName";

	public static final String WQX_MONITORING_LOCATION = "MonitoringLocation";
	public static final String WQX_MONITORING_LOCATION_IDENTITY = "MonitoringLocationIdentity";
	public static final String WQX_MONITORING_LOCATION_IDENTIFIER = "MonitoringLocationIdentifier";
	public static final String WQX_MONITORING_LOCATION_NAME = "MonitoringLocationName";
	public static final String WQX_MONITORING_LOCATION_TYPE = "MonitoringLocationTypeName";
	public static final String WQX_MONITORING_LOCATION_DESCRIPTION = "MonitoringLocationDescriptionText";
	public static final String WQX_HUC_8 = "HUCEightDigitCode";
	public static final String WQX_RESOLVED_MONITORING_LOCATION = "ResolvedMonitoringLocationTypeName";
	public static final String WQX_MONITORING_LOCATION_GEOSPATIAL = "MonitoringLocationGeospatial";
	public static final String WQX_LATITUDE_MEASURE = "LatitudeMeasure";
	public static final String WQX_LONGITUDE_MEASURE = "LongitudeMeasure";
	public static final String WQX_SOURCE_MAP_SCALE = "SourceMapScaleNumeric";
	public static final String WQX_HORIZONTAL_COLLECTION_METHOD = "HorizontalCollectionMethodName";
	public static final String WQX_HORIZONTAL_DATUM = "HorizontalCoordinateReferenceSystemDatumName";
	public static final String WQX_VERTICAL_MEASURE = "VerticalMeasure";
	public static final String WQX_VERTICAL_COLLECTION_METHOD = "VerticalCollectionMethodName";
	public static final String WQX_VERTICAL_DATUM = "VerticalCoordinateReferenceSystemDatumName";
	public static final String WQX_COUNTRY_CODE = "CountryCode";
	public static final String WQX_STATE_CODE = "StateCode";
	public static final String WQX_COUNTY_CODE = "CountyCode";
	
	
	public static final String WQX_ACTIVITY = "Activity";
	
	public static final String WQX_ACT_DESCRIPTION = "ActivityDescription";
	public static final String WQX_ACT_ID = "ActivityIdentifier";
	public static final String WQX_ACT_TYPE = "ActivityTypeCode";
	public static final String WQX_ACT_MEDIA = "ActivityMediaName";
	public static final String WQX_ACT_MEDIA_SUB = "ActivityMediaSubdivisionName";
	public static final String WQX_ACT_START_DATE = "ActivityStartDate";
	public static final String WQX_ACT_START_TIME = "ActivityStartTime";
	public static final String WQX_ACT_END_DATE = "ActivityEndDate";
	public static final String WQX_ACT_END_TIME = "ActivityEndTime";
	public static final String WQX_ACT_DEPTH = "ActivityDepthHeightMeasure";
	public static final String WQX_ACT_DEPTH_REFERENCE = "ActivityDepthAltitudeReferencePointText";
	public static final String WQX_ACT_TOP_DEPTH = "ActivityTopDepthHeightMeasure";
	public static final String WQX_ACT_BOTTOM_DEPTH = "ActivityBottomDepthHeightMeasure";
	public static final String WQX_PROJECT = "ProjectIdentifier";
	public static final String WQX_ACT_CONDUCTION_ORG = "ActivityConductingOrganizationText";
	public static final String WQX_ACT_COMMENT = "ActivityCommentText";
	public static final String WQX_SAMPLE_AQUIFER = "SampleAquifer";
	public static final String WQX_HYDROLOGIC_CONDITION = "HydrologicCondition";
	public static final String WQX_HYDROLOGIC_EVENT = "HydrologicEvent";

	public static final String WQX_SAMPLE_DESCRIPTION = "SampleDescription";
	public static final String WQX_COLLECTION_METHOD = "SampleCollectionMethod";
	public static final String WQX_COLLECTION_EQUIPMENT = "SampleCollectionEquipmentName";
	
	public static final String WQX_RESULT = "Result";
	public static final String WQX_RESULT_DESRIPTION = "ResultDescription";
	public static final String WQX_CHAR_NAME = "CharacteristicName";
	public static final String WQX_DETECTION_CONDITION = "ResultDetectionConditionText";
	public static final String WQX_SAMPLE_FRACTION = "ResultSampleFractionText";
	public static final String WQX_RESULT_MEASURE = "ResultMeasure";
	public static final String WQX_RESULT_MEASURE_VALUE = "ResultMeasureValue";
	public static final String WQX_MEASURE_QUALIFIER = "MeasureQualifierCode";
	public static final String WQX_STATUS_ID = "ResultStatusIdentifier";
	public static final String WQX_STATISTICAL_BASE = "StatisticalBaseCode";
	public static final String WQX_VALUE_TYPE = "ResultValueTypeName";
	public static final String WQX_WEIGHT_BASIS = "ResultWeightBasisText";
	public static final String WQX_TIME_BASIS = "ResultTimeBasisText";
	public static final String WQX_TEMP_BASIS = "ResultTemperatureBasisText";
	public static final String WQX_PARTICLE_SIZE = "ResultParticleSizeBasisText";
	public static final String WQX_DATA_QUALITY = "DataQuality";
	public static final String WQX_PRECISION = "PrecisionValue";
	public static final String WQX_RESULT_COMMENT = "ResultCommentText";
	public static final String WQX_P_CODE = "USGSPCode";
	public static final String WQX_RESULT_DEPTH = "ResultDepthHeightMeasure";
	public static final String WQX_RESULT_DETH_REFERENCE = "ResultDepthAltitudeReferencePointText";
	
	public static final String WQX_BIOLOGICAL_RESULT = "BiologicalResultDescription";
	public static final String WQX_TAXON_NAME = "SubjectTaxonomicName";
	public static final String WQX_TISSUE_ANATOMY = "SampleTissueAnatomyName";
	
	public static final String WQX_ANALYTICAL_METHOD = "ResultAnalyticalMethod";
	public static final String WQX_METHOD_DESCRIPTION = "MethodDescriptionText";

	public static final String WQX_LAB_INFO = "ResultLabInformation";
	public static final String WQX_LAB_NAME = "LaboratoryName";
	public static final String WQX_ANALYSIS_START_DATE = "AnalysisStartDate";
	public static final String WQX_LAB_COMMENT = "ResultLaboratoryCommentText";
	public static final String WQX_DETECTION_LIMIT = "ResultDetectionQuantitationLimit";
	public static final String WQX_DETECTION_LIMIT_VALUE = "DetectionQuantitationLimitMeasure";
	public static final String WQX_DETECTION_LIMIT_TYPE = "DetectionQuantitationLimitTypeName";
	
	public static final String WQX_LAB_SAMPLE_PREP = "LabSamplePreparation";
	public static final String WQX_PREP_START_DATE = "PreparationStartDate";

	public String getRoot() {
		return ROOT_NODE;
	}

	public String getHeader() {
		return "<" + ROOT_NODE + " " + ROOT_NAMESPACE + ">";
	}

}
