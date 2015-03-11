package gov.usgs.cida.wqp.code;

import static gov.usgs.cida.wqp.util.MybatisConstants.CODES_MAPPER_NAMESPACE;

import org.apache.log4j.Logger;

public enum CodeType {

    ORGANIZATION ("codeOrganization"),
    SITETYPE     ("codeSiteType"),
    SAMPLETYPE   ("codeSampleType"),
    SAMPLEMEDIA  ("codeSampleMedia"),
    COUNTYCODE   ("codeCountyCode"),
    STATECODE    ("codeStateCode"),
    COUNTRYCODE  ("codeCountryCode"),
    CHARACTERISTICTYPE ("codeCharacteristicType"),
    CHARACTERISTICNAME ("codeCharacteristicName");

	private final Logger log = Logger.getLogger(getClass());
	
    private final String xmlMapperSelectID;
    private final String listMapperSelectID;

    private CodeType(String selectID) {
        log.trace(getClass());
        
        this.xmlMapperSelectID  = CODES_MAPPER_NAMESPACE + "." + selectID + "List";
        this.listMapperSelectID = CODES_MAPPER_NAMESPACE + "." + selectID + "List";
    }

    public String getXMLMapperSelectID() {
        return xmlMapperSelectID;
    }

    public String getListMapperSelectID() {
        return listMapperSelectID;
    }

}