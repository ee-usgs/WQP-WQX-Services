package gov.usgs.cida.wqp.station;



public class SimpleStation {

    private String id;


    private String primarySiteType;

    private String latitude;

    private String longitude;

    public String getId() {
        return id;
    }

    public void setId(final String inId) {
        id = inId;
    }

//    public Organization getOrganization() {
//        return organization;
//    }
//
//    public void setOrganization(final Organization inOrganization) {
//        organization = inOrganization;
//    }

    public String getPrimarySiteType() {
        return primarySiteType;
    }

    public void setPrimarySiteType(final String inPrimarySiteType) {
        primarySiteType = inPrimarySiteType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(final String inLatitude) {
        latitude = inLatitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(final String inLongitude) {
        longitude = inLongitude;
    }

//    public synchronized Organization marshal(Organization organization, XMLStreamWriter xmlStreamWriter) throws XMLStreamException {
//        Organization rtn = organization;
//        if (null == organization) {
//            //If we ever put out actual organization info this will need to change so it begins/ends the organizations within the provider.
//            xmlStreamWriter.writeStartElement("Provider");
//            xmlStreamWriter.writeStartElement("ProviderName");
//            xmlStreamWriter.writeCharacters(QWHelper.cleanseString(ProviderName.getName()));
//            xmlStreamWriter.writeEndElement();
//            xmlStreamWriter.writeStartElement(Organization.ORGANIZATION_NODE_NAME);
//            rtn = getOrganization();
//        }
//        xmlStreamWriter.writeStartElement("MonitoringLocation");
//        xmlStreamWriter.writeStartElement("MonitoringLocationIdentity");
//        xmlStreamWriter.writeStartElement("MonitoringLocationIdentifier");
//        xmlStreamWriter.writeCharacters(null == getId() ? "" : QWHelper.cleanseString(getId()));
//        xmlStreamWriter.writeEndElement();
//        xmlStreamWriter.writeStartElement("ResolvedMonitoringLocationTypeName");
//        xmlStreamWriter.writeCharacters(null == getPrimarySiteType() ? "" : QWHelper.cleanseString(getPrimarySiteType()));
//        xmlStreamWriter.writeEndElement();
//        xmlStreamWriter.writeEndElement();
//        xmlStreamWriter.writeStartElement("MonitoringLocationGeospatial");
//        xmlStreamWriter.writeStartElement("LatitudeMeasure");
//        xmlStreamWriter.writeCharacters(null == getLatitude() ? "" : QWHelper.cleanseString(getLatitude()));
//        xmlStreamWriter.writeEndElement();
//        xmlStreamWriter.writeStartElement("LongitudeMeasure");
//        xmlStreamWriter.writeCharacters(null == getLongitude() ? "" : QWHelper.cleanseString(getLongitude()));
//        xmlStreamWriter.writeEndElement();
//        xmlStreamWriter.writeEndElement();
//        xmlStreamWriter.writeEndElement();
//        return rtn;
//    }

}
