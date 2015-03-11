package gov.usgs.cida.wqp.station;



public class Station {

    private String id;

    private String details;

//    private Organization organization;

    public String getId() {
        return id;
    }

    public void setId(String inId) {
        id = inId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String inDetails) {
        details = inDetails;
    }

//    public Organization getOrganization() {
//        return organization;
//    }
//
//    public void setOrganization(Organization inOrganization) {
//        organization = inOrganization;
//    }

//    public synchronized Organization marshal(Organization organization, XMLStreamWriter xmlStreamWriter) throws XMLStreamException {
//        QWHelper.marshalOrganization(organization, getOrganization(), xmlStreamWriter);
//
//        StringBuilder xml = new StringBuilder();
//        if (null != getDetails()) {
//            xml.append(getDetails());
//        }
//        if (0 < xml.length()) {
//            XmlStreamUtils.handleString(xml.toString(), xmlStreamWriter);
//        }
//        return getOrganization();
//    }

}
