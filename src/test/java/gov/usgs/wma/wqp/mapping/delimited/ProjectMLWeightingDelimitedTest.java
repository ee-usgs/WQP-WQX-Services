package gov.usgs.wma.wqp.mapping.delimited;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import gov.usgs.wma.wqp.mapping.BaseColumn;
import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.ProjectColumn;
import gov.usgs.wma.wqp.mapping.ProjectMonitoringLocationWeightingColumn;
import gov.usgs.wma.wqp.mapping.StationColumn;

public class ProjectMLWeightingDelimitedTest {

	@Test
	public void projectMLWeightingProfileTest() {
		assertProjectMLWeightingProfile(ProjectMonitoringLocationWeightingDelimited.getMapping(Profile.PROJECT_MONITORING_LOCATION_WEIGHTING));
	}

	public static void assertProjectMLWeightingProfile(Map<String, String> mapping) {
		assertEquals(19, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseColumn.KEY_ORGANIZATION_NAME, keys[1]);
		assertEquals(ProjectColumn.KEY_PROJECT_IDENTIFIER, keys[2]);
		assertEquals(StationColumn.KEY_SITE_ID, keys[3]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_PRJMLW_VALUE, keys[4]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_PRJMLW_UNIT, keys[5]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_PRJMLW_STATISTICAL_STRATUM, keys[6]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_PRJMLW_LOCATION_CATEGORY, keys[7]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_PRJMLW_LOCATION_STATUS, keys[8]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_PRJMLW_REFERENCE_LOCATION_TYPE_CODE, keys[9]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_PRJMLW_REFERENCE_LOCATION_START_DATE, keys[10]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_PRJMLW_REFERENCE_LOCATION_END_DATE, keys[11]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_REFERENCE_LOCATION_CITATION_TITLE, keys[12]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_REFERENCE_LOCATION_CITATION_CREATOR, keys[13]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_REFERENCE_LOCATION_CITATION_SUBJECT, keys[14]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_REFERENCE_LOCATION_CITATION_PUBLISHER, keys[15]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_REFERENCE_LOCATION_CITATION_DATE, keys[16]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_REFERENCE_LOCATION_CITATION_IDENTIFIER, keys[17]);
		assertEquals(ProjectMonitoringLocationWeightingColumn.KEY_PRJMLW_COMMENT, keys[18]);
	}
}
