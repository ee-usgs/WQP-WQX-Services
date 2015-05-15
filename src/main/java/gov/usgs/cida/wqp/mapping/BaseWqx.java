package gov.usgs.cida.wqp.mapping;

import gov.cida.cdat.transform.IXmlMapping;

public abstract class BaseWqx implements IXmlMapping {

	public static final String ROOT_NODE = "WQX";
	public static final String ROOT_NAMESPACE = "xmlns='http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/ http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/index.xsd'";

	public static final String WQX_MEASURE_VALUE = "MeasureValue";
	public static final String WQX_MEASURE_UNIT = "MeasureUnitCode";

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
	
	@Override
	public String getRoot() {
		return ROOT_NODE;
	}

	@Override
	public String getHeader() {
		return "<" + ROOT_NODE + " " + ROOT_NAMESPACE + ">";
	}

}
