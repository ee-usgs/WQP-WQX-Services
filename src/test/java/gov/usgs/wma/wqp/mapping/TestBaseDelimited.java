package gov.usgs.wma.wqp.mapping;

import static gov.usgs.wma.wqp.mapping.ActivityColumn.ACTIVITY;
import static gov.usgs.wma.wqp.mapping.ActivityColumn.ACTIVITY_LATITUDE;
import static gov.usgs.wma.wqp.mapping.BaseColumn.ORGANIZATION;
import static gov.usgs.wma.wqp.mapping.ResultColumn.HABIT_NAME;
import static gov.usgs.wma.wqp.mapping.ResultColumn.EXTERNAL_RESULT_ID;
import static gov.usgs.wma.wqp.mapping.ResultColumn.RESULT_VALUE_TYPE;
import static gov.usgs.wma.wqp.mapping.StationColumn.HUC_8;
import static gov.usgs.wma.wqp.mapping.StationColumn.STATION_NAME;
import static gov.usgs.wma.wqp.mapping.delimited.ActivityDelimited.VALUE_ACTIVITY;
import static gov.usgs.wma.wqp.mapping.delimited.ActivityDelimited.VALUE_ACTIVITY_LATITUDE;
import static gov.usgs.wma.wqp.mapping.delimited.ResultDelimited.VALUE_HABIT_NAME;
import static gov.usgs.wma.wqp.mapping.delimited.ResultDelimited.VALUE_RESULT_VALUE_TYPE;
import static gov.usgs.wma.wqp.mapping.delimited.StationDelimited.VALUE_HUC_8;
import static gov.usgs.wma.wqp.mapping.delimited.StationDelimited.VALUE_MONITORING_LOCATION_NAME;

import java.util.LinkedHashMap;
import java.util.Map;

import gov.usgs.wma.wqp.mapping.delimited.BaseDelimited;

public class TestBaseDelimited extends BaseDelimited {
	public static final Map<ColumnProfile, String> MAPPINGS;
	
	static {
		MAPPINGS = new LinkedHashMap<ColumnProfile,String>();
		MAPPINGS.put(ORGANIZATION, VALUE_ORGANIZATION_IDENTIFIER);
		MAPPINGS.put(STATION_NAME, VALUE_MONITORING_LOCATION_NAME);
		MAPPINGS.put(HUC_8, VALUE_HUC_8);
		MAPPINGS.put(ACTIVITY, VALUE_ACTIVITY);
		MAPPINGS.put(ACTIVITY_LATITUDE, VALUE_ACTIVITY_LATITUDE);
		MAPPINGS.put(EXTERNAL_RESULT_ID, "nothing");
		MAPPINGS.put(RESULT_VALUE_TYPE, VALUE_RESULT_VALUE_TYPE);
		MAPPINGS.put(HABIT_NAME, VALUE_HABIT_NAME);
	}

}
