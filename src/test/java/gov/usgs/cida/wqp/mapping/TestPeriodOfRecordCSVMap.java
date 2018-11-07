package gov.usgs.cida.wqp.mapping;


import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE_ID;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_SITE_ID;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_STATION_ID;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_CHARACTERISTIC_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_CHARACTERISTIC_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_COUNTY_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_HUC_8;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_LAST_RESULT_DATE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_LATITUDE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_LONGITUDE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_MONITORING_LOCATION_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_MONITORING_LOCATION_URL;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_SITE_TYPE;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_STATE_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_STATION_NAME;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_THE_YEAR;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_TOTAL_ACTIVITIES;
import static gov.usgs.cida.wqp.mapping.StationColumn.KEY_TOTAL_RESULTS;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestPeriodOfRecordCSVMap {	
	
	// next section is for monitoring location STEWARDS_36
	// for sorted tests-should only appear in 'all' year summary
	public static final Map<String, Object> STEWARDS_36_2000;	
	static {
		STEWARDS_36_2000 = new LinkedHashMap<>();
		STEWARDS_36_2000.put(KEY_DATA_SOURCE_ID, "1");
		STEWARDS_36_2000.put(KEY_DATA_SOURCE, "STEWARDS");
		STEWARDS_36_2000.put(KEY_STATION_ID, "36");
		STEWARDS_36_2000.put(KEY_THE_YEAR, "2000");
		STEWARDS_36_2000.put(KEY_CHARACTERISTIC_TYPE, "ct_1");
		STEWARDS_36_2000.put(KEY_CHARACTERISTIC_NAME, "cn_1");
		STEWARDS_36_2000.put(KEY_TOTAL_ACTIVITIES, "1");
		STEWARDS_36_2000.put(KEY_TOTAL_RESULTS, "2");
		STEWARDS_36_2000.put(KEY_LAST_RESULT_DATE, "2000-12-31");	
		STEWARDS_36_2000.put(KEY_ORGANIZATION, "ARS");
		STEWARDS_36_2000.put(KEY_ORGANIZATION_NAME, "Agricultural Research Services");		
		STEWARDS_36_2000.put(KEY_SITE_ID, "ARS-IAWC-IAWC225");
		STEWARDS_36_2000.put(KEY_STATION_NAME, "Buckeye Rd.");
		STEWARDS_36_2000.put(KEY_MONITORING_LOCATION_TYPE_NAME, "River/Stream");
		STEWARDS_36_2000.put(KEY_SITE_TYPE, "Land");
		STEWARDS_36_2000.put(KEY_HUC_8, "07080105");
		STEWARDS_36_2000.put(KEY_MONITORING_LOCATION_URL, "https://www.waterqualitydata.us/provider/STEWARDS/ARS/ARS-IAWC-IAWC225");
		STEWARDS_36_2000.put(KEY_COUNTY_NAME, "Jefferson County");
		STEWARDS_36_2000.put(KEY_STATE_NAME, "Delaware");
		STEWARDS_36_2000.put(KEY_LATITUDE, "41.9607224");
		STEWARDS_36_2000.put(KEY_LONGITUDE, "-93.6982205");		
	}
	
	// next section is for monitoring location STORET_504707
	// for sorted tests-should only appear in 'all' year summary
	public static final Map<String, Object> STORET_504707_1910;	
	static {
		STORET_504707_1910 = new LinkedHashMap<>();	
		STORET_504707_1910.put(KEY_DATA_SOURCE_ID, "3");
		STORET_504707_1910.put(KEY_DATA_SOURCE, "STORET");
		STORET_504707_1910.put(KEY_STATION_ID, "504707");
		STORET_504707_1910.put(KEY_THE_YEAR, "1910");
		STORET_504707_1910.put(KEY_CHARACTERISTIC_TYPE, "ct_1");
		STORET_504707_1910.put(KEY_CHARACTERISTIC_NAME, "cn_1");
		STORET_504707_1910.put(KEY_TOTAL_ACTIVITIES, "11");
		STORET_504707_1910.put(KEY_TOTAL_RESULTS, "20");
		STORET_504707_1910.put(KEY_LAST_RESULT_DATE, "1910-08-31");	
		STORET_504707_1910.put(KEY_ORGANIZATION, "21NYDECA_WQX");
		STORET_504707_1910.put(KEY_ORGANIZATION_NAME, "Agricultural Research Services");		
		STORET_504707_1910.put(KEY_SITE_ID, "21NYDECA_WQX-ONTARIO-02");
		STORET_504707_1910.put(KEY_STATION_NAME, "Lake rd.");
		STORET_504707_1910.put(KEY_MONITORING_LOCATION_TYPE_NAME, "River/Stream");
		STORET_504707_1910.put(KEY_SITE_TYPE, "Lake, Reservoir, Impoundment");
		STORET_504707_1910.put(KEY_HUC_8, "04150200");
		STORET_504707_1910.put(KEY_MONITORING_LOCATION_URL, "https://www.waterqualitydata.us/provider/STORET/21NYDECA_WQX/21NYDECA_WQX-ONTARIO-02");
		STORET_504707_1910.put(KEY_COUNTY_NAME, "Lincoln County");
		STORET_504707_1910.put(KEY_STATE_NAME, "Indiana");
		STORET_504707_1910.put(KEY_LATITUDE, "44.0167000");
		STORET_504707_1910.put(KEY_LONGITUDE, "-76.6700000");
	}		
	
	// next section is for monitoring location NWIS_1353690
	// for sorted tests-should only appear in 'all' year summary
	public static final Map<String, Object> NWIS_1353690_2010;	
	static {
		NWIS_1353690_2010 = new LinkedHashMap<>();
		NWIS_1353690_2010.put(KEY_DATA_SOURCE_ID, "2");
		NWIS_1353690_2010.put(KEY_DATA_SOURCE, "NWIS");
		NWIS_1353690_2010.put(KEY_STATION_ID, "1353690");
		NWIS_1353690_2010.put(KEY_THE_YEAR, "2010");
		NWIS_1353690_2010.put(KEY_CHARACTERISTIC_TYPE, "ct_1");
		NWIS_1353690_2010.put(KEY_CHARACTERISTIC_NAME, "cn_1");
		NWIS_1353690_2010.put(KEY_TOTAL_ACTIVITIES, "");
		NWIS_1353690_2010.put(KEY_TOTAL_RESULTS, "");
		NWIS_1353690_2010.put(KEY_LAST_RESULT_DATE, "");	
		NWIS_1353690_2010.put(KEY_ORGANIZATION, "");
		NWIS_1353690_2010.put(KEY_ORGANIZATION_NAME, "");		
		NWIS_1353690_2010.put(KEY_SITE_ID, "");
		NWIS_1353690_2010.put(KEY_STATION_NAME, "");
		NWIS_1353690_2010.put(KEY_MONITORING_LOCATION_TYPE_NAME, "");
		NWIS_1353690_2010.put(KEY_SITE_TYPE, "");
		NWIS_1353690_2010.put(KEY_HUC_8, "");
		NWIS_1353690_2010.put(KEY_MONITORING_LOCATION_URL, "");
		NWIS_1353690_2010.put(KEY_COUNTY_NAME, "");
		NWIS_1353690_2010.put(KEY_STATE_NAME, "");
		NWIS_1353690_2010.put(KEY_LATITUDE, "");
		NWIS_1353690_2010.put(KEY_LONGITUDE, "");		
	}
	
	// for sorted tests, monitoring location 1353690 should only appear in 5, and all year summary
	public static final Map<String, Object> NWIS_1353690_2017;	
	static {
		NWIS_1353690_2017 = new LinkedHashMap<>();
		NWIS_1353690_2017.put(KEY_DATA_SOURCE_ID, "2");
		NWIS_1353690_2017.put(KEY_DATA_SOURCE, "NWIS");
		NWIS_1353690_2017.put(KEY_STATION_ID, "1353690");
		NWIS_1353690_2017.put(KEY_THE_YEAR, String.valueOf(LocalDate.now().getYear() - 1));
		NWIS_1353690_2017.put(KEY_CHARACTERISTIC_TYPE, "ct_1");
		NWIS_1353690_2017.put(KEY_CHARACTERISTIC_NAME, "cn_1");
		NWIS_1353690_2017.put(KEY_TOTAL_ACTIVITIES, "10");
		NWIS_1353690_2017.put(KEY_TOTAL_RESULTS, "10");
		NWIS_1353690_2017.put(KEY_LAST_RESULT_DATE, "2007-03-03");	
		NWIS_1353690_2017.put(KEY_ORGANIZATION, "USGS-WI");
		NWIS_1353690_2017.put(KEY_ORGANIZATION_NAME, "Random Org 1");		
		NWIS_1353690_2017.put(KEY_SITE_ID, "USGS-05425700");
		NWIS_1353690_2017.put(KEY_STATION_NAME, "Day lake Rd.");
		NWIS_1353690_2017.put(KEY_MONITORING_LOCATION_TYPE_NAME, "River/Stream");
		NWIS_1353690_2017.put(KEY_SITE_TYPE, "Stream");
		NWIS_1353690_2017.put(KEY_HUC_8, "07090002");
		NWIS_1353690_2017.put(KEY_MONITORING_LOCATION_URL, "https://www.waterqualitydata.us/provider/NWIS/USGS-WI/USGS-05425700");
		NWIS_1353690_2017.put(KEY_COUNTY_NAME, "Jackson County");
		NWIS_1353690_2017.put(KEY_STATE_NAME, "California");
		NWIS_1353690_2017.put(KEY_LATITUDE, "43.3466571");
		NWIS_1353690_2017.put(KEY_LONGITUDE, "-89.0320546");		
	}
	
	// for sorted tests-monitoring location 1353690 should appear every summary
	public static final Map<String, Object> NWIS_1353690_2018;	
	static {
		NWIS_1353690_2018 = new LinkedHashMap<>();
		NWIS_1353690_2018.put(KEY_DATA_SOURCE_ID, "2");	
		NWIS_1353690_2018.put(KEY_DATA_SOURCE, "NWIS");
		NWIS_1353690_2018.put(KEY_STATION_ID, "1353690");
		NWIS_1353690_2018.put(KEY_THE_YEAR, String.valueOf(LocalDate.now().getYear()));
		NWIS_1353690_2018.put(KEY_CHARACTERISTIC_TYPE, "ct_1");
		NWIS_1353690_2018.put(KEY_CHARACTERISTIC_NAME, "cn_1");
		NWIS_1353690_2018.put(KEY_TOTAL_ACTIVITIES, "10");
		NWIS_1353690_2018.put(KEY_TOTAL_RESULTS, "10");
		NWIS_1353690_2018.put(KEY_LAST_RESULT_DATE, "2018-03-03");	
		NWIS_1353690_2018.put(KEY_ORGANIZATION, "USGS-WI");
		NWIS_1353690_2018.put(KEY_ORGANIZATION_NAME, "Random Org 1");		
		NWIS_1353690_2018.put(KEY_SITE_ID, "USGS-05425700");
		NWIS_1353690_2018.put(KEY_STATION_NAME, "Day lake Rd.");
		NWIS_1353690_2018.put(KEY_MONITORING_LOCATION_TYPE_NAME, "River/Stream");
		NWIS_1353690_2018.put(KEY_SITE_TYPE, "Stream");
		NWIS_1353690_2018.put(KEY_HUC_8, "07090002");
		NWIS_1353690_2018.put(KEY_MONITORING_LOCATION_URL, "https://www.waterqualitydata.us/provider/NWIS/USGS-WI/USGS-05425700");
		NWIS_1353690_2018.put(KEY_COUNTY_NAME, "Jackson County");
		NWIS_1353690_2018.put(KEY_STATE_NAME, "California");
		NWIS_1353690_2018.put(KEY_LATITUDE, "43.3466571");
		NWIS_1353690_2018.put(KEY_LONGITUDE, "-89.0320546");		
	}
	
	// next section is for monitoring location NWIS_1360035 (different from NWIS_1353690)
	// for sorted tests-monitoring location 1360035 should only appear in 5, and all year summary
	public static final Map<String, Object> NWIS_1360035_2017;	
	static {
		NWIS_1360035_2017 = new LinkedHashMap<>();
		NWIS_1360035_2017.put(KEY_DATA_SOURCE_ID, "2");
		NWIS_1360035_2017.put(KEY_DATA_SOURCE, "NWIS");
		NWIS_1360035_2017.put(KEY_STATION_ID, "1360035");
		NWIS_1360035_2017.put(KEY_THE_YEAR, String.valueOf(LocalDate.now().getYear() - 1));
		NWIS_1360035_2017.put(KEY_CHARACTERISTIC_TYPE, "ct_1");
		NWIS_1360035_2017.put(KEY_CHARACTERISTIC_NAME, "cn_1");
		NWIS_1360035_2017.put(KEY_TOTAL_ACTIVITIES, "1");
		NWIS_1360035_2017.put(KEY_TOTAL_RESULTS, "5");
		NWIS_1360035_2017.put(KEY_LAST_RESULT_DATE, "2017-08-31");	
		NWIS_1360035_2017.put(KEY_ORGANIZATION, "USGS-WI");
		NWIS_1360035_2017.put(KEY_ORGANIZATION_NAME, "Random Org 2");		
		NWIS_1360035_2017.put(KEY_SITE_ID, "USGS-431925089002701");
		NWIS_1360035_2017.put(KEY_STATION_NAME, "Forest St.");
		NWIS_1360035_2017.put(KEY_MONITORING_LOCATION_TYPE_NAME, "River/Stream");
		NWIS_1360035_2017.put(KEY_SITE_TYPE, "Well");
		NWIS_1360035_2017.put(KEY_HUC_8, "07090002");
		NWIS_1360035_2017.put(KEY_MONITORING_LOCATION_URL, "https://www.waterqualitydata.us/provider/NWIS/USGS-WI/USGS-431925089002701");
		NWIS_1360035_2017.put(KEY_COUNTY_NAME, "Franklin County");
		NWIS_1360035_2017.put(KEY_STATE_NAME, "California");
		NWIS_1360035_2017.put(KEY_LATITUDE, "43.3236024");
		NWIS_1360035_2017.put(KEY_LONGITUDE, "-89.0076094");		
	}
	
	// for sorted tests-monitoring location 1360035 should appear every summary
	public static final Map<String, Object> NWIS_1360035_2018;	
	static {
		NWIS_1360035_2018 = new LinkedHashMap<>();
		NWIS_1360035_2018.put(KEY_DATA_SOURCE_ID, "2");
		NWIS_1360035_2018.put(KEY_DATA_SOURCE, "NWIS");
		NWIS_1360035_2018.put(KEY_STATION_ID, "1360035");
		NWIS_1360035_2018.put(KEY_THE_YEAR, String.valueOf(LocalDate.now().getYear()));
		NWIS_1360035_2018.put(KEY_CHARACTERISTIC_TYPE, "ct_1");
		NWIS_1360035_2018.put(KEY_CHARACTERISTIC_NAME, "cn_1");
		NWIS_1360035_2018.put(KEY_TOTAL_ACTIVITIES, "2");
		NWIS_1360035_2018.put(KEY_TOTAL_RESULTS, "4");
		NWIS_1360035_2018.put(KEY_LAST_RESULT_DATE, "2018-08-31");	
		NWIS_1360035_2018.put(KEY_ORGANIZATION, "USGS-WI");
		NWIS_1360035_2018.put(KEY_ORGANIZATION_NAME, "Random Org 2");		
		NWIS_1360035_2018.put(KEY_SITE_ID, "USGS-431925089002701");
		NWIS_1360035_2018.put(KEY_STATION_NAME, "Forest St.");
		NWIS_1360035_2018.put(KEY_MONITORING_LOCATION_TYPE_NAME, "River/Stream");
		NWIS_1360035_2018.put(KEY_SITE_TYPE, "Well");
		NWIS_1360035_2018.put(KEY_HUC_8, "07090002");
		NWIS_1360035_2018.put(KEY_MONITORING_LOCATION_URL, "https://www.waterqualitydata.us/provider/NWIS/USGS-WI/USGS-431925089002701");
		NWIS_1360035_2018.put(KEY_COUNTY_NAME, "Franklin County");
		NWIS_1360035_2018.put(KEY_STATE_NAME, "California");
		NWIS_1360035_2018.put(KEY_LATITUDE, "43.3236024");
		NWIS_1360035_2018.put(KEY_LONGITUDE, "-89.0076094");		
	}		
	
	public static final List<Map<String, Object>> PERIOD_OF_RECORD_ONE_YEAR =
			Arrays.asList(NWIS_1353690_2018, NWIS_1360035_2018);	

	// not a complete map of result data; just a representative sample
	public static final List<Map<String, Object>> PERIOD_OF_RECORD_FIVE_YEARS =
			Arrays.asList(NWIS_1353690_2017, NWIS_1353690_2018,
					NWIS_1360035_2017, NWIS_1360035_2018);

	// not a complete map of result data; just a representative sample
	public static final List<Map<String, Object>> PERIOD_OF_RECORD_ALL_YEARS = 
			Arrays.asList(STEWARDS_36_2000, NWIS_1353690_2017, NWIS_1353690_2018,
					NWIS_1353690_2010, NWIS_1360035_2017, NWIS_1360035_2018,
					STORET_504707_1910
					);	
	
	private TestPeriodOfRecordCSVMap() {
	}
}
