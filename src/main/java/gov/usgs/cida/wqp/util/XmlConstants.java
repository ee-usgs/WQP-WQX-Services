package gov.usgs.cida.wqp.util;

public interface XmlConstants {
    public static final String BEGIN_OPEN_TAG = "<";
    public static final String CLOSE_TAG = ">";
    public static final String BEGIN_END_TAG = "</";
    public static final String CLOSE_EMPTY_TAG = "/>";

    public static final String WQX_OUTBOUND = "<WQX-Outbound></WQX-Outbound>";
    public static final String WQX_EMPTY_DOC = "<WQX xmlns='http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/ http://qwwebservices.usgs.gov/schemas/WQX-Outbound/2_0/index.xsd'></WQX>";
    public static final String KML_EMPTY_DOC = "<kml><Document></Document></kml>";
}
