package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ACTIVITY_COUNT;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_RESULT_COUNT;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_SITE_ID;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_COUNTY_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_HUC_8;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_LAST_SUBMITTED_DATE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_LATITUDE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_LONGITUDE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_MONITORING_LOCATION_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_SITE_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_STATE_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_STATION_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_TOTAL_ACTIVITIES;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_PERIOD_OF_RECORD;

public class TestPeriodOfRecordMap {
		public static final Map<String, Object> PERIOD_OF_RECORD_ONE_YEAR;
	static {
		PERIOD_OF_RECORD_ONE_YEAR = new LinkedHashMap<>();
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_DATA_SOURCE, STORET);
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_SITE_ID, "organization-siteId");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_ORGANIZATION, "organization");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_ORGANIZATION_NAME, "Random Org 2");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_SITE_TYPE, "siteType");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_HUC_8, "00000000");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_MONITORING_LOCATION_TYPE, "River/Stream");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_LATITUDE, "43.3330000");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_LONGITUDE, "-89.8989000");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_ACTIVITY_COUNT, BigDecimal.valueOf(1));
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_RESULT_COUNT, BigDecimal.valueOf(1));
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_STATE_NAME, "Hawaii");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_COUNTY_NAME, "Clay County");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_STATION_NAME, "Wintergreen ln");		
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_LAST_SUBMITTED_DATE, "2018-12-31");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_TOTAL_ACTIVITIES, "1");
		PERIOD_OF_RECORD_ONE_YEAR.put(KEY_PERIOD_OF_RECORD, "[{\"summary_period_of_record\": \"One Year\"}]");	
		
	}
	
	public static final Map<String, Object> PERIOD_OF_RECORD_FIVE_YEARS;
	static {
		PERIOD_OF_RECORD_FIVE_YEARS = new LinkedHashMap<>();
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_DATA_SOURCE, STORET);
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_SITE_ID, "organization-siteId");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_ORGANIZATION, "organization");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_ORGANIZATION_NAME, "Random Org 2");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_SITE_TYPE, "siteType");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_HUC_8, "00000000");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_MONITORING_LOCATION_TYPE, "River/Stream");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_LATITUDE, "43.3330000");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_LONGITUDE, "-89.8989000");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_ACTIVITY_COUNT, BigDecimal.valueOf(3));
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_RESULT_COUNT, BigDecimal.valueOf(3));//		
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_STATE_NAME, "Hawaii");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_COUNTY_NAME, "Clay County");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_STATION_NAME, "Wintergreen ln");		
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_LAST_SUBMITTED_DATE, "2018-12-31");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_TOTAL_ACTIVITIES, "3");
		PERIOD_OF_RECORD_FIVE_YEARS.put(KEY_PERIOD_OF_RECORD, "[{\"summary_period_of_record\": \"Five Years\"}]");
	}
	
	public static final Map<String, Object> PERIOD_OF_RECORD_ALL_YEARS;
	static {
		PERIOD_OF_RECORD_ALL_YEARS = new LinkedHashMap<>();
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_DATA_SOURCE, STORET);
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_SITE_ID, "organization-siteId");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_ORGANIZATION, "organization");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_ORGANIZATION_NAME, "Random Org 2");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_SITE_TYPE, "siteType");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_HUC_8, "00000000");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_MONITORING_LOCATION_TYPE, "River/Stream");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_LATITUDE, "43.3330000");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_LONGITUDE, "-89.8989000");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_ACTIVITY_COUNT, BigDecimal.valueOf(4));
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_RESULT_COUNT, BigDecimal.valueOf(4));
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_STATE_NAME, "Hawaii");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_COUNTY_NAME, "Clay County");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_STATION_NAME, "Wintergreen ln");		
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_LAST_SUBMITTED_DATE, "2018-12-31");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_TOTAL_ACTIVITIES, "4");
		PERIOD_OF_RECORD_ALL_YEARS.put(KEY_PERIOD_OF_RECORD, "[{\"summary_period_of_record\": \"All Years\"}]");
	}

	private TestPeriodOfRecordMap() {
	}
}
