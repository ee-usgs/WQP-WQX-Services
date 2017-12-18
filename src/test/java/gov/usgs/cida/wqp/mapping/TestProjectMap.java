package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_DATA_SOURCE_ID;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.KEY_MONITORING_LOCATION_WEIGHT_URL;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.KEY_PROJECT_DESCRIPTION;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.KEY_PROJECT_FILE_URL;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.KEY_PROJECT_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.KEY_PROJECT_NAME;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.KEY_QAPP_APPROVAL_AGENCY_NAME;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.KEY_QAPP_APPROVED_INDICATOR;
import static gov.usgs.cida.wqp.mapping.ProjectColumn.KEY_SAMPLING_DESIGN_TYPE_CODE;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestProjectMap {
	
	public static final Map<String, Object> PROJECT;
	static {
		PROJECT = new LinkedHashMap<String, Object>();
		PROJECT.put(KEY_DATA_SOURCE_ID, BigDecimal.valueOf(3));
		PROJECT.put(KEY_DATA_SOURCE, STORET);
		PROJECT.put(KEY_PROJECT_IDENTIFIER, "YZ123");
		PROJECT.put(KEY_PROJECT_NAME, "Random Statewide Project");
		PROJECT.put(KEY_PROJECT_DESCRIPTION, "A project description");
		PROJECT.put(KEY_SAMPLING_DESIGN_TYPE_CODE, "X");
		PROJECT.put(KEY_QAPP_APPROVED_INDICATOR, "Y");
		PROJECT.put(KEY_QAPP_APPROVAL_AGENCY_NAME, "Name");
		PROJECT.put(KEY_PROJECT_FILE_URL, "fake.gov");
		PROJECT.put(KEY_MONITORING_LOCATION_WEIGHT_URL, "fake2.gov");
	}
	
	private TestProjectMap() {
	}
}
