package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.ActivityColumn.*;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.*;
import static gov.usgs.cida.wqp.mapping.ResultColumn.*;
import static gov.usgs.cida.wqp.mapping.StationColumn.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestResultPhysChemMap {

	public static final Map<String, Object> PHYS_CHEM;
	static {
		PHYS_CHEM = new LinkedHashMap<String, Object>();
		PHYS_CHEM.putAll(TestPcResultMap.PC_RESULT);
		PHYS_CHEM.put(KEY_ACTIVITY_RELATIVE_DEPTH, "activityRelativeDepthName");
		PHYS_CHEM.put(KEY_PROJECT_NAME, "Some Project");
		PHYS_CHEM.put(KEY_STATION_NAME, "stationName");
		PHYS_CHEM.put(KEY_ACTIVITY_LATITUDE, BigDecimal.valueOf(43.333));
		PHYS_CHEM.put(KEY_ACTIVITY_LONGITUDE, BigDecimal.valueOf(-89.8989));
		PHYS_CHEM.put(KEY_EXTERNAL_RESULT_ID, "STORET-42");
		PHYS_CHEM.put(KEY_METHOD_SPECIATION_NAME, "methodSpecificationName");
		PHYS_CHEM.put(KEY_RES_MEASURE_BIAS, "resMeasureBias");
		PHYS_CHEM.put(KEY_RES_MEASURE_CONF_INTERVAL, "resMeasureConfInterval");
		PHYS_CHEM.put(KEY_RES_MEASURE_UPPER_CONF_LIMIT, "resMeasureUpperConfLimit");
		PHYS_CHEM.put(KEY_RES_MEASURE_LOWER_CONF_LIMIT, "resMeasureLowerConfLimit");
		PHYS_CHEM.put(KEY_METHOD_URL, "https://analyticalMethod");
		PHYS_CHEM.put(KEY_RES_DETECT_QNT_LMT_URL, "/activities/activity/results/STORET-42/resdetectqntlmts");
		PHYS_CHEM.put(KEY_LAB_SAMPLE_PREP_URL, "/labSamplePrepUrl");
		PHYS_CHEM.put(KEY_RESULT_OBJECT_NAME, "resultObjectName");
		PHYS_CHEM.put(KEY_RESULT_OBJECT_TYPE, "resultObjectType");
		PHYS_CHEM.put(KEY_RESULT_FILE_URL, "/organizations/organization/activities/activity/results/STORET-42/files");
		PHYS_CHEM.put(KEY_LAST_UPDATED, Timestamp.valueOf("2018-08-20 15:22:33.0"));
	}

	private TestResultPhysChemMap() {
	}
}
