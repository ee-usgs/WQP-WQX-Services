package gov.usgs.cida.wqp.parameter;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public enum Parameters {
    ANALYTICAL_METHOD("analyticalmethod"),
    SITEID("siteid"),
    START_DATE_HI("startDateHi"),
    START_DATE_LO("startDateLo"),
    //activityId and pCode are nwis only
    ACTIVITY_ID("activityId"),
    PCODE("pCode"),
    CHARACTERISTIC_NAME("characteristicName"),
    CHARACTERISTIC_TYPE("characteristicType"),
    SAMPLE_MEDIA("sampleMedia"),
    SAMPLE_TYPE("sampleType"),
    ORGANIZATION("organization"),
    SITE_TYPE("siteType"),
    HUC("huc"),
    COUNTRY("countrycode"),
    STATE("statecode"),
    COUNTY("countycode"),
    LONGITUDE("long"),
    LATITUDE("lat"),
    WITHIN("within"),
    BBOX("bBox"),
    MIMETYPE("mimeType"),
    ZIP("zip"),
    LAYOUT("layout"),
    SCHEMA("schema"),
    PROVIDERS("providers"),
    AVOID("command.avoid"),
    TIMEZONE_TYPE("timezoneType");

	private final Logger log = Logger.getLogger(getClass());
	
    private final String parameterName;
    private static Map<String, Parameters> validParameterNames;
    
    private Parameters(String value) {
        log.trace(getClass());
        parameterName = value;
        putParameterName(value, this);        
    }
    
    private static void putParameterName(String valueIn, Parameters param) {
        if (valueIn == null || getValidParameterNames().containsKey(valueIn)) {
            //This is a configuration error and can only happen if you duplicate values above...
            throw new IllegalStateException("overloaded parameter value: " +  valueIn +", please verify the enum definition for Parameters");
        } else {
            getValidParameterNames().put(valueIn, param);
        }
    }
    
    private static Map<String, Parameters> getValidParameterNames() {
        if (validParameterNames == null) {
            validParameterNames = new HashMap<String, Parameters>();
        }
        return validParameterNames;
    }

    @Override
    public String toString() {
        return parameterName;
    }
    
    public static boolean isValid(String paramToCheck) {
        return validParameterNames.containsKey(paramToCheck);
    }
    
    public static Parameters fromName(String value) {
        return isValid(value) ? validParameterNames.get(value) : null;
    }
}