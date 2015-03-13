package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.usgs.cida.wqp.BaseSpringTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ValidationResultTest extends BaseSpringTest implements ValidationConstants {

    List<String> msgs;
    ValidationResult<String> vr;
    
    @Before
    public void setup() {
        msgs = new ArrayList<String>();
        msgs.add("wow");
        msgs.add("cool");
        vr = new ValidationResult<String>(true, "abc", msgs);
	}
	
    @Test
    public void mergeTest_empty() {
        vr.merge(new ValidationResult<String>());
        assertTrue(vr.isValid());
        assertEquals(2, vr.getValidationMessages().size());
        assertTrue(vr.getValidationMessages().contains("wow"));
        assertTrue(vr.getValidationMessages().contains("cool"));
        assertNotNull(vr.getTransformedValue());
        assertEquals("abc", vr.getTransformedValue());
    }
    @Test
    public void mergeTest_nullList() {
        vr.merge(new ValidationResult<String>(true, null, null));
        assertTrue(vr.isValid());
        assertEquals(2, vr.getValidationMessages().size());
        assertTrue(vr.getValidationMessages().contains("wow"));
        assertTrue(vr.getValidationMessages().contains("cool"));
        assertNotNull(vr.getTransformedValue());
        assertEquals("abc", vr.getTransformedValue());
    }        
    @Test
    public void mergeTest() {
        List<String> moreMsgs = new ArrayList<String>();
        msgs.add("awsome");
        msgs.add("totaly rad");
        msgs.add("way");
        vr.merge(new ValidationResult<String>(false, "zzz", moreMsgs));
        assertFalse(vr.isValid());
        assertEquals(5, vr.getValidationMessages().size());
        assertTrue(vr.getValidationMessages().contains("wow"));
        assertTrue(vr.getValidationMessages().contains("cool"));
        assertTrue(vr.getValidationMessages().contains("awsome"));
        assertTrue(vr.getValidationMessages().contains("totaly rad"));
        assertTrue(vr.getValidationMessages().contains("way"));
        assertNotNull(vr.getTransformedValue());
        assertEquals("zzz", vr.getTransformedValue());

    }
}
