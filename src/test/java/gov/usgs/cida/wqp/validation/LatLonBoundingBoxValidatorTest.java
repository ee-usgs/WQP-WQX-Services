package gov.usgs.cida.wqp.validation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.parameter.HashMapParameterHandler;
import gov.usgs.cida.wqp.parameter.Parameters;

import org.junit.Test;

public class LatLonBoundingBoxValidatorTest extends BaseSpringTest implements ValidationConstants {

    @Test
    public void testBbox() {
    	AbstractValidator<?> validator = HashMapParameterHandler.getValidator(Parameters.BBOX);
        
        assertNotNull(validator);
        
        assertEquals(DEFAULT_MIN_OCCURS, validator.getMinOccurs());
        
        assertEquals(DEFAULT_MAX_OCCURS, validator.getMaxOccurs());
        
        assertEquals(",", validator.getDelimiter());
        
        ValidationResult<?> vr = validator.validate("-88,43,-86,45");
        assertTrue(vr.isValid());
        assertEquals(0, vr.getValidationMessages().size());
        assertArrayEquals(new String[]{"-88","43","-86","45"}, (String[])vr.getTransformedValue());


        vr = validator.validate("-88,43,-86,45;-88,43,-86,45");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-88,43,-86,45;-88,43,-86,45 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-88","43","-86","45;-88","43","-86","45"}, (String[])vr.getTransformedValue());

        vr = validator.validate("a,b,c,d");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=a,b,c,d is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"a","b","c","d"}, (String[])vr.getTransformedValue());

        vr = validator.validate("200,43,-86,45");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=200,43,-86,45 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"200","43","-86","45"}, (String[])vr.getTransformedValue());

        vr = validator.validate("-200,43,-86,45");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-200,43,-86,45 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-200","43","-86","45"}, (String[])vr.getTransformedValue());

        vr = validator.validate("-88,143,-86,45");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-88,143,-86,45 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-88","143","-86","45"}, (String[])vr.getTransformedValue());

        vr = validator.validate("-88,-143,-86,45");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-88,-143,-86,45 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-88","-143","-86","45"}, (String[])vr.getTransformedValue());

        vr = validator.validate("-88,43,200,45");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-88,43,200,45 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-88","43","200","45"}, (String[])vr.getTransformedValue());

        vr = validator.validate("-88,43,-200,45");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-88,43,-200,45 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-88","43","-200","45"}, (String[])vr.getTransformedValue());

        vr = validator.validate("-88,43,-86,145");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-88,43,-86,145 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-88","43","-86","145"}, (String[])vr.getTransformedValue());

        vr = validator.validate("-88,43,-86,-145");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-88,43,-86,-145 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-88","43","-86","-145"}, (String[])vr.getTransformedValue());

        vr = validator.validate("88,43,86,45");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=88,43,86,45 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"88","43","86","45"}, (String[])vr.getTransformedValue());

        vr = validator.validate("-88,45,-86,43");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-88,45,-86,43 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-88","45","-86","43"}, (String[])vr.getTransformedValue());
        
        vr = validator.validate(null);
        assertTrue(vr.isValid());
        
        vr = validator.validate("-88 ,45,-86 ,43");
        assertFalse(vr.isValid());
        assertEquals(1, vr.getValidationMessages().size());
        assertEquals("The value of bBox=-88 ,45,-86 ,43 is not a valid bounding box.", vr.getValidationMessages().get(0));
        assertArrayEquals(new String[]{"-88 ","45","-86 ","43"}, (String[])vr.getTransformedValue());
    }

}
