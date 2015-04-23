package gov.usgs.cida.wqp.parameter;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Parameters {
	//activityId and pCode are NWIS only
	//TODO - Activity is not currently supported
	//ACTIVITY_ID("activityId"),
	ANALYTICAL_METHOD("analyticalmethod"),
	//AVOID is transformed in filter XXX to PROVIDERS 
	AVOID("command.avoid"),
	BBOX("bBox"),
	CHARACTERISTIC_NAME("characteristicName"),
	CHARACTERISTIC_TYPE("characteristicType"),
	COUNTRY("countrycode"),
	COUNTY("countycode"),
	HUC("huc"),
	LATITUDE("lat"),
//	LAYOUT("layout"), //TODO?????????????????????
	LONGITUDE("long"),
	MIMETYPE("mimeType"),
	ORGANIZATION("organization"),
	PCODE("pCode"),
	PROJECT("project"),
	PROVIDERS("providers"),
	SAMPLE_MEDIA("sampleMedia"),
	SAMPLE_TYPE("sampleType"),
//	SCHEMA("schema"), //TODO?????????????????????
	SITEID("siteid"),
	SITE_TYPE("siteType"),
	START_DATE_HI("startDateHi"),
	START_DATE_LO("startDateLo"),
	STATE("statecode"),
//	TIMEZONE_TYPE("timezoneType"), //TODO?????????????????????
	WITHIN("within"),
	ZIP("zip");
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final String parameterName;
	private static Map<String, Parameters> validParameterNames;
	
	private Parameters(String value) {
		log.trace(getClass().getName());
		parameterName = value;
		putParameterName(value, this);
	}
	
	private static void putParameterName(String value, Parameters param) {
		if (value == null || getValidParameterNames().containsKey(value)) {
			//This is a configuration error and can only happen if you duplicate values above...not unit testable
			throw new IllegalStateException("overloaded parameter value: " +  value +", please verify the enum definition for Parameters");
		} else {
			getValidParameterNames().put(value, param);
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