package gov.usgs.cida.wqp.mapping.delimited;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.ResultColumn;
import gov.usgs.cida.wqp.mapping.StationColumn;

public class ResultDelimitedTest {

	@Test
	public void pcResultProfileTest() {
		assertPcResultProfile(ResultDelimited.getMapping(Profile.PC_RESULT));
	}

	@Test
	public void biologicalProfileTest() {
		assertBiologicalProfile(ResultDelimited.getMapping(Profile.BIOLOGICAL));
	}

	public static void assertPcResultProfile(Map<String, String> mapping) {
		assertEquals(63, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		//kind of large, so just random checks...
		assertEquals(StationColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(StationDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(ActivityColumn.KEY_ACTIVITY_START_TIME,keys[7]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY_START_TIME, mapping.get(keys[7]));
		assertEquals(ActivityColumn.KEY_SAMPLE_COLLECT_METHOD_ID, keys[26]);
		assertEquals(ActivityDelimited.VALUE_SAMPLE_COLLECT_METHOD_ID, mapping.get(keys[26]));
		assertEquals(ResultColumn.KEY_RESULT_MEAS_QUAL_CODE, keys[35]);
		assertEquals(ResultDelimited.VALUE_RESULT_MEAS_QUAL_CODE, mapping.get(keys[35]));
		assertEquals(ResultColumn.KEY_RESULT_COMMENT, keys[44]);
		assertEquals(ResultDelimited.VALUE_RESULT_COMMENT, mapping.get(keys[44]));
		assertEquals(ResultColumn.KEY_ANALYTICAL_PROCEDURE_ID, keys[51]);
		assertEquals(ResultDelimited.VALUE_ANALYTICAL_PROCEDURE_ID, mapping.get(keys[51]));
		assertEquals(ResultColumn.KEY_DATA_SOURCE, keys[62]);
		assertEquals(ResultDelimited.VALUE_DATA_SOURCE, mapping.get(keys[62]));
	}

	public static void assertBiologicalProfile(Map<String, String> mapping) {
		assertEquals(156, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		//kind of large, so just random checks...
		assertEquals(StationColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(StationDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(ActivityColumn.KEY_ACTIVITY_START_TIME,keys[7]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY_START_TIME, mapping.get(keys[7]));
		assertEquals(ActivityColumn.KEY_HYDROLOGIC_EVENT_NAME, keys[26]);
		assertEquals(ActivityDelimited.VALUE_HYDROLOGIC_EVENT_NAME, mapping.get(keys[26]));
		assertEquals(ActivityColumn.KEY_ACT_COLLECTION_DURATION, keys[35]);
		assertEquals(ActivityDelimited.VALUE_ACT_COLLECTION_DURATION, mapping.get(keys[35]));
		assertEquals(ActivityColumn.KEY_NET_TYPE_NAME, keys[44]);
		assertEquals(ActivityDelimited.VALUE_NET_TYPE_NAME, mapping.get(keys[44]));
		assertEquals(ActivityColumn.KEY_ACT_CURRENT_SPEED, keys[51]);
		assertEquals(ActivityDelimited.VALUE_ACT_CURRENT_SPEED, mapping.get(keys[51]));
		assertEquals(ActivityColumn.KEY_ACT_SAM_PREP_METH_CONTEXT, keys[62]);
		assertEquals(ActivityDelimited.VALUE_ACT_SAM_PREP_METH_CONTEXT, mapping.get(keys[62]));
		assertEquals(ResultColumn.KEY_DURATION_BASIS, keys[83]);
		assertEquals(ResultDelimited.VALUE_DURATION_BASIS, mapping.get(keys[83]));
		assertEquals(ResultColumn.KEY_TAXON_CITATION_ID, keys[117]);
		assertEquals(ResultDelimited.VALUE_TAXON_CITATION_ID, mapping.get(keys[117]));
		assertEquals(ResultColumn.KEY_RLCOM_CD, keys[134]);
		assertEquals(ResultDelimited.VALUE_RLCOM_CD, mapping.get(keys[134]));
		assertEquals(ResultColumn.KEY_DATA_SOURCE, keys[155]);
		assertEquals(ResultDelimited.VALUE_DATA_SOURCE, mapping.get(keys[155]));
	}

}
