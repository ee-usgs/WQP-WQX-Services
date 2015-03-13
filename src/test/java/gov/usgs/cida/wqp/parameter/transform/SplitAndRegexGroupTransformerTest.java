package gov.usgs.cida.wqp.parameter.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import org.junit.Test;

public class SplitAndRegexGroupTransformerTest extends BaseSpringTest implements ValidationConstants {

    @Test
    public void testFailure() {
        SplitAndRegexGroupTransformer transformer = new SplitAndRegexGroupTransformer("a", "a");
        try {
            transformer.transform(null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }
    
    @Test
    public void testSplitAndReplace() {
        SplitAndRegexGroupTransformer transformer = new SplitAndRegexGroupTransformer(DEFAULT_DELIMITER, REGEX_FIPS_STATE);
        String[][] split = (String[][]) transformer.transform("US:01;US:55;CN:04");
        assertEquals(3, split.length);
        assertEquals(2, split[0].length);
        assertEquals("US", split[0][0]);
        assertEquals("01", split[0][1]);
        assertEquals(2, split[1].length);
        assertEquals("US", split[1][0]);
        assertEquals("55", split[1][1]);
        assertEquals(2, split[2].length);
        assertEquals("CN", split[2][0]);
        assertEquals("04", split[2][1]);
    }
    
    @Test
    public void testState() {
    	SplitAndRegexGroupTransformer transformer = new SplitAndRegexGroupTransformer(DEFAULT_DELIMITER, REGEX_FIPS_STATE);
        assertNotNull(transformer);
        String[][] split = (String[][]) transformer.transform("US:01;US:55;CN:90");
        assertEquals(3, split.length);
        assertEquals(2, split[0].length);
        assertEquals("US", split[0][0]);
        assertEquals("01", split[0][1]);
        assertEquals(2, split[1].length);
        assertEquals("US", split[1][0]);
        assertEquals("55", split[1][1]);
        assertEquals(2, split[2].length);
        assertEquals("CN", split[2][0]);
        assertEquals("90", split[2][1]);
    }

    @Test
    public void testCounty() {
    	SplitAndRegexGroupTransformer transformer = new SplitAndRegexGroupTransformer(DEFAULT_DELIMITER, REGEX_FIPS_COUNTY);
        assertNotNull(transformer);
        String[][] split = (String[][]) transformer.transform("US:01:121;US:55:027;CN:90:000");
        assertEquals(3, split.length);
        assertEquals(3, split[0].length);
        assertEquals("US", split[0][0]);
        assertEquals("01", split[0][1]);
        assertEquals("121", split[0][2]);
        assertEquals(3, split[1].length);
        assertEquals("US", split[1][0]);
        assertEquals("55", split[1][1]);
        assertEquals("027", split[1][2]);
        assertEquals(3, split[2].length);
        assertEquals("CN", split[2][0]);
        assertEquals("90", split[2][1]);
        assertEquals("000", split[2][2]);
    }

}
