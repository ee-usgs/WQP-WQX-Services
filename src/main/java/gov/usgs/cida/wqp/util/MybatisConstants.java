package gov.usgs.cida.wqp.util;

public class MybatisConstants {
	public static final String MYBATIS_MAPPERS = "mybatis/*.xml";
	public static final String CODES_MAPPER_NAMESPACE = "dataMapper";
	public static final String STATIONS_MAPPER_NAMESPACE = "dataMapper";
	public static final String RESULTS_MAPPER_NAMESPACE = STATIONS_MAPPER_NAMESPACE;
	public static final String STATIONS_KML_SELECTID = STATIONS_MAPPER_NAMESPACE + ".stationsKMLSelect";
	public static final String STATIONS_WQX_SELECTID = STATIONS_MAPPER_NAMESPACE + ".stationsWQXSelect";
	public static final String STATIONS_WQX_COUNTID = STATIONS_MAPPER_NAMESPACE + ".stationsWQXCount";
	public static final String SIMPLE_STATIONS_SELECTID = STATIONS_MAPPER_NAMESPACE + ".simpleStationsSelect";
	public static final String BIOLOGICAL_STATIONS_WQX_COUNTID = RESULTS_MAPPER_NAMESPACE + ".biologicalstationsWQXCount";
	public static final String BIOLOGICAL_RESULTS_WQX_COUNTID = RESULTS_MAPPER_NAMESPACE + ".biologicalResultsWQXCount";
	public static final String ACTIVITY_COUNT = "ACTIVITY_COUNT";
	public static final String ACTIVITY_METRIC_COUNT = "ACTIVITY_METRIC_COUNT";
	public static final String STATION_COUNT = "STATION_COUNT";
	public static final String RESULT_COUNT = "RESULT_COUNT";
	public static final String RES_DETECT_QNT_LMT = "RES_DETECT_QNT_LMT_COUNT";
	public static final String DATA_SOURCE = "DATA_SOURCE";

	private MybatisConstants() {
	}

}