package gov.usgs.cida.wqp.webservice.SimpleStation;

public interface IWqxOutbound {

	String WQX_OUTBOUND = "WQX-Outbound";
	String PROVIDER = "Provider";
	String PROVIDER_NAME = "ProviderName"; 
	String ORGANIZATION = "Organization";
	String ORGANIZATION_DESCRIPTION = "OrganizationDescription";
	String ORGANIZATION_IDENTIFIER = "OrganizationIdentifier";
	String ORGANIZATION_FORMAL_NAME = "OrganizationFormalName";
	String MONITORING_LOCATION = "MonitoringLocation";
	String MONITORING_LOCATION_IDENTITY = "MonitoringLocationIdentity";
	String MONITORING_LOCATION_IDENTIFIER = "MonitoringLocationIdentifier";
	String MONITORING_LOCATION_NAME = "MonitoringLocationName";
	String RESOLVED_MONITORING_LOCATION_NAME = "ResolvedMonitoringLocationTypeName";
	String MONITORING_LOCATION_GEOSPATIAL = "MonitoringLocationGeospatial";
	String LATITUDE_MEASURE = "LatitudeMeasure";
	String LONGITUDE_MEASURE = "LongitudeMeasure";

	String ROOT = WQX_OUTBOUND;
	String ROOT_NAMESPACE = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
	
}
