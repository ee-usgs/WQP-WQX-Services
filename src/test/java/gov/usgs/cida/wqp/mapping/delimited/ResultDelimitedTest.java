package gov.usgs.cida.wqp.mapping.delimited;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import gov.usgs.cida.wqp.mapping.ActivityColumn;
import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.ResultColumn;

public class ResultDelimitedTest {

	@Test
	public void pcResultProfileTest() {
		assertPcResultProfile(ResultDelimited.getMapping(Profile.PC_RESULT));
	}

	@Test
	public void biologicalProfileTest() {
		assertBiologicalProfile(ResultDelimited.getMapping(Profile.BIOLOGICAL));
	}

	@Test
	public void narrowProfileTest() {
		assertNarrowProfile(ResultDelimited.getMapping(Profile.NARROW_RESULT));
	}

	@Test
	public void resultPhysChemProfileTest() {
		assertResultPhysChemProfile(ResultDelimited.getMapping(Profile.RESULT_PHYS_CHEM));
	}

	public static void assertPcResultProfile(Map<String, String> mapping) {
		assertEquals(63, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		//kind of large, so just random checks...
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
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
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
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

	public static void assertNarrowProfile(Map<String, String> mapping) {
		assertEquals(78, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		//kind of large, so just random checks...
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(BaseColumn.KEY_SITE_ID,keys[6]);
		assertEquals(BaseDelimited.VALUE_MONITORING_LOCATION_IDENTIFIER, mapping.get(keys[6]));
		assertEquals(ResultColumn.KEY_TEMPERATURE_BASIS_LEVEL, keys[21]);
		assertEquals(ResultDelimited.VALUE_TEMPERATURE_BASIS_LEVEL, mapping.get(keys[21]));
		assertEquals(ResultColumn.KEY_RESULT_COMMENT, keys[28]);
		assertEquals(ResultDelimited.VALUE_RESULT_COMMENT, mapping.get(keys[28]));
		assertEquals(ResultColumn.KEY_RES_BIO_INDIVIDUAL_ID, keys[35]);
		assertEquals(ResultDelimited.VALUE_RES_BIO_INDIVIDUAL_ID, mapping.get(keys[35]));
		assertEquals(ResultColumn.KEY_CELL_SHAPE_NAME, keys[42]);
		assertEquals(ResultDelimited.VALUE_CELL_SHAPE_NAME, mapping.get(keys[42]));
		assertEquals(ResultColumn.KEY_TAXON_CITATION_TITLE, keys[49]);
		assertEquals(ResultDelimited.VALUE_TAXON_CITATION_TITLE, mapping.get(keys[49]));
		assertEquals(ResultColumn.KEY_ANALYTICAL_PROCEDURE_ID, keys[56]);
		assertEquals(ResultDelimited.VALUE_ANALYTICAL_PROCEDURE_ID, mapping.get(keys[56]));
		assertEquals(ResultColumn.KEY_ANALYSIS_START_DATE, keys[63]);
		assertEquals(ResultDelimited.VALUE_ANALYSIS_START_DATE, mapping.get(keys[63]));
		assertEquals(ResultColumn.KEY_LAB_REMARK, keys[70]);
		assertEquals(ResultDelimited.VALUE_LAB_REMARK, mapping.get(keys[70]));
		assertEquals(ResultColumn.KEY_DATA_SOURCE, keys[77]);
		assertEquals(ResultDelimited.VALUE_DATA_SOURCE, mapping.get(keys[77]));
	}

	public static void assertResultPhysChemProfile(Map<String, String> mapping) {
		assertEquals(81, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		//kind of large, so just random checks...
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(ActivityColumn.KEY_ACTIVITY_START_TIME,keys[7]);
		assertEquals(ActivityDelimited.VALUE_ACTIVITY_START_TIME, mapping.get(keys[7]));
		assertEquals(ActivityColumn.KEY_SAMPLE_COLLECT_METHOD_ID, keys[31]);
		assertEquals(ActivityDelimited.VALUE_SAMPLE_COLLECT_METHOD_ID, mapping.get(keys[31]));
		assertEquals(ResultColumn.KEY_EXTERNAL_RESULT_ID, keys[36]);
		assertEquals(ResultDelimited.VALUE_RESULT_IDENTIFIER, mapping.get(keys[36]));
		assertEquals(ResultColumn.KEY_RESULT_MEAS_QUAL_CODE, keys[43]);
		assertEquals(ResultDelimited.VALUE_RESULT_MEAS_QUAL_CODE, mapping.get(keys[43]));
		assertEquals(ResultColumn.KEY_RESULT_COMMENT, keys[56]);
		assertEquals(ResultDelimited.VALUE_RESULT_COMMENT, mapping.get(keys[56]));
		assertEquals(ResultColumn.KEY_ANALYTICAL_PROCEDURE_ID, keys[66]);
		assertEquals(ResultDelimited.VALUE_ANALYTICAL_PROCEDURE_ID, mapping.get(keys[66]));
		assertEquals(ResultColumn.KEY_LAST_UPDATED, keys[79]);
		assertEquals(ResultDelimited.VALUE_LAST_UPDATED, mapping.get(keys[79]));
		assertEquals(ResultColumn.KEY_DATA_SOURCE, keys[80]);
		assertEquals(ResultDelimited.VALUE_DATA_SOURCE, mapping.get(keys[80]));
	}
	
}
