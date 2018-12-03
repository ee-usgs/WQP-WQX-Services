package gov.usgs.cida.wqp.mapping.delimited;

import gov.usgs.cida.wqp.mapping.BaseColumn;
import gov.usgs.cida.wqp.mapping.BiologicalMetricColumn;
import gov.usgs.cida.wqp.mapping.Profile;

import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BiologicalMetricDelimitedTest {
	
	@Test
	public void BiologicalMetricProfileTest() {
		assertBiologicalMetricProfile(BiologicalMetricDelimited.getMapping(Profile.BIOLOGICAL_METRIC));
	}
	
	public static void assertBiologicalMetricProfile(Map<String, String> mapping) {
		assertEquals(18, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(BaseColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(BaseColumn.KEY_SITE_ID, keys[1]);
		assertEquals(BiologicalMetricColumn.KEY_INDEX_IDENTIFIER, keys[2]);
		assertEquals(BiologicalMetricColumn.KEY_INDEX_TYPE_IDENTIFIER, keys[3]);
		assertEquals(BiologicalMetricColumn.KEY_INDEX_TYPE_CONTEXT , keys[4]);
		assertEquals(BiologicalMetricColumn.KEY_INDEX_TYPE_NAME , keys[5]);
		assertEquals(BiologicalMetricColumn.KEY_RESOURCE_TITLE_NAME , keys[6]);
		assertEquals(BiologicalMetricColumn.KEY_RESOURCE_CREATOR_NAME , keys[7]);
		assertEquals(BiologicalMetricColumn.KEY_RESOURCE_SUBJECT_TEXT , keys[8]);
		assertEquals(BiologicalMetricColumn.KEY_RESOURCE_PUBLISHER_NAME , keys[9]);
		assertEquals(BiologicalMetricColumn.KEY_RESOURCE_DATE , keys[10]);
		assertEquals(BiologicalMetricColumn.KEY_RESOURCE_IDENTIFIER , keys[11]);
		assertEquals(BiologicalMetricColumn.KEY_INDEX_TYPE_SCALE_TEXT , keys[12]);
		assertEquals(BiologicalMetricColumn.KEY_INDEX_SCORE_NUMERIC , keys[13]);
		assertEquals(BiologicalMetricColumn.KEY_INDEX_QUALIFIER_CODE , keys[14]);
		assertEquals(BiologicalMetricColumn.KEY_INDEX_COMMENT , keys[15]);
		assertEquals(BiologicalMetricColumn.KEY_INDEX_CALCULATED_DATE , keys[16]);
		assertEquals(BiologicalMetricColumn.KEY_DATA_SOURCE , keys[17]);
	}
	
}
