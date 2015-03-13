package gov.usgs.cida.wqp.parameter.transform;

import static org.junit.Assert.*;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.validation.ValidationConstants;

import org.junit.Test;

public class SplitTransformerTest extends BaseSpringTest implements ValidationConstants {

    @Test
    public void testNullsAndEmpties() {
        try {
            new SplitTransformer(null);
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("A Delimiter must be provided.", e.getMessage());
        }
        try {
            new SplitTransformer("");
            fail("Should have gotten an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("A Delimiter must be provided.", e.getMessage());
        }
    }
    
    @Test
    public void testSplit() {
        SplitTransformer transformer = new SplitTransformer(DEFAULT_DELIMITER);
        String[] split = (String[]) transformer.transform("a;b;c");
        assertEquals(3, split.length);
        assertEquals("a", split[0]);
        assertEquals("b", split[1]);
        assertEquals("c", split[2]);
        
        split = (String[]) transformer.transform(null);
        assertEquals(0, split.length);
        
        split = (String[]) transformer.transform("");
        assertEquals(0, split.length);
        
        transformer = new SplitTransformer("x");
        split = (String[]) transformer.transform("axbxc");
        assertEquals(3, split.length);
        assertEquals("a", split[0]);
        assertEquals("b", split[1]);
        assertEquals("c", split[2]);
    }

    @Test
    public void testSplitAgain() {
    	SplitTransformer trans = new SplitTransformer("-");
        assertArrayEquals("delimiter gives new String[1] matching value", new String[]{"abc"}, trans.transform("abc"));
        assertArrayEquals("delimiter gives new String[1] matching value - even with ;", new String[]{";;a;b;;c;;;"}, trans.transform(";;a;b;;c;;;"));

    	trans = new SplitTransformer();
        assertArrayEquals("null value gives new String[0]", new String[0], trans.transform(null));
        
        String[] result = new String[]{"A", "B", "C"};
        assertArrayEquals("Do a split", result, trans.transform("A;B;C"));
        assertArrayEquals("Do a split with extra delimiters", result, trans.transform(";;A;;;B;C;;"));

    	trans = new SplitTransformer("x");
        assertArrayEquals("Try a new delimiter", result, trans.transform("AxBxxCx"));
    }
    
    
    @Test
    public void testPruneDelimiters() {
        SplitTransformer rv = new SplitTransformer();
        assertEquals("No Delimiters", "011234", rv.pruneDelimiters("011234"));

        assertEquals("Removes Internal Delimiters", "011;234", rv.pruneDelimiters("011;;234"));

        assertEquals("Removes Trailing Delimiters", "011234", rv.pruneDelimiters("011234;;"));
        
        assertEquals("Removes Leading Delimiters", "011234", rv.pruneDelimiters(";;011234"));

        try {
        	rv = new SplitTransformer(null);
        	fail("Expect an exception, no null based separator");
        } catch (Exception e ) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
        try {
        	rv = new SplitTransformer("");
        	fail("Expect an exception, no empty string separator");
        } catch (Exception e ) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
    }
    
}
