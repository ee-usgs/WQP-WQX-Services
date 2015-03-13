package gov.usgs.cida.wqp.parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.usgs.cida.wqp.BaseSpringTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class HashMapParameterHandlerTest extends BaseSpringTest {
    
    HashMapParameterHandler handler = new HashMapParameterHandler();
    
    @Test
    public void testValidateParameterGroups_null() {
        ParameterMap pm = handler.validateParameterGroups(null);
        assertTrue(pm.isValid());
    }
    @Test
    public void testValidateParameterGroups_emptySet() {
    	ParameterMap pm = handler.validateParameterGroups(Collections.<Parameters> emptySet());
        assertTrue(pm.isValid());
    }       
    @Test
    public void testValidateParameterGroups_userSet() {
        Set<Parameters> userParameterSet = new HashSet<Parameters>();
        userParameterSet.add(Parameters.COUNTY);
        userParameterSet.add(Parameters.MIMETYPE);

        ParameterMap pm = handler.validateParameterGroups(userParameterSet);
        assertTrue(pm.isValid());
    }
    @Test
    public void testValidateParameterGroups_withinParam() {
        Set<Parameters> userParameterSet = new HashSet<Parameters>();    
        userParameterSet.add(Parameters.WITHIN);
        ParameterMap pm = handler.validateParameterGroups(userParameterSet);
        assertFalse(pm.isValid());
        assertEquals(1, pm.getValidationMessages().size());
        assertTrue(pm.getValidationMessages().containsKey("Group"));
        List<String> vm = pm.getValidationMessages().get("Group");
        assertEquals(1, vm.size());
        assertEquals("Parameter(s) 'within' require(s) 'lat' and 'long'", vm.get(0));
    }
    @Test
    public void testValidateParameterGroups_withinAndlatitudeParams() {
        Set<Parameters> userParameterSet = new HashSet<Parameters>();
        userParameterSet.add(Parameters.WITHIN);
        userParameterSet.add(Parameters.LATITUDE);
        ParameterMap pm = handler.validateParameterGroups(userParameterSet);
        assertFalse(pm.isValid());
        assertEquals(1, pm.getValidationMessages().size());
        assertTrue(pm.getValidationMessages().containsKey("Group"));
        List<String> vm = pm.getValidationMessages().get("Group");
        assertEquals(1, vm.size());
        assertEquals("Parameter(s) 'lat' and 'within' require(s) 'long'", vm.get(0));
    }
    @Test
    public void testValidateParameterGroups_allRequiredParams() {
        Set<Parameters> userParameterSet = new HashSet<Parameters>();
        userParameterSet.add(Parameters.WITHIN);
        userParameterSet.add(Parameters.LATITUDE);
        userParameterSet.add(Parameters.LONGITUDE);
        ParameterMap pm = handler.validateParameterGroups(userParameterSet);
        assertTrue(pm.isValid());
    }
    
    @Test
    public void testValidateParameterNames_null() {
        //These tests skip the groups.
        ParameterMap pm = handler.validateParameterNamesAndGroups(null);
        assertTrue(pm.isValid());
    }
    @Test
    public void testValidateParameterNames_emptySet() {
        ParameterMap pm = handler.validateParameterNamesAndGroups(Collections.<String> emptySet());
        assertTrue(pm.isValid());
    }        
    @Test
    public void testValidateParameterNames_validSet() {
        ParameterMap pm = handler.validateParameterNamesAndGroups(new HashSet<String>(Arrays.asList(Parameters.COUNTY.toString(), Parameters.MIMETYPE.toString())));
        assertTrue(pm.isValid());
        assertEquals(0, pm.getValidationMessages().size());
    }
    @Test
    public void testValidateParameterNames_invalidSet() {
        ParameterMap pm = handler.validateParameterNamesAndGroups(new HashSet<String>(Arrays.asList("IamNotHere")));
        assertFalse(pm.isValid());
        assertEquals(1, pm.getValidationMessages().size());
        assertTrue(pm.getValidationMessages().containsKey("IamNotHere"));
        List<String> vm = pm.getValidationMessages().get("IamNotHere");
        assertEquals(1, vm.size());
        assertEquals("Parameter Name 'IamNotHere' is not valid.  This service will NOT return ANY results until this parameter is removed from the query.", vm.get(0));
    }
    @Test
    public void testValidateParameterNames_invalidParam() {
        ParameterMap pm = handler.validateParameterNamesAndGroups(new HashSet<String>(Arrays.asList(Parameters.COUNTY.toString(), "asdf")));
        assertFalse(pm.isValid());
        assertEquals(1, pm.getValidationMessages().size());
        assertTrue(pm.getValidationMessages().containsKey("asdf"));
        List<String> vm = pm.getValidationMessages().get("asdf");
        assertEquals(1, vm.size());
        assertEquals("Parameter Name 'asdf' is not valid.  This service will NOT return ANY results until this parameter is removed from the query.", vm.get(0));
    }
    
    @Test
    public void testValidateParameterNamesAndGroups_countyAndMimeTypeAndWithin() {
        //These tests also hit groups.
        Set<String> userParameterSet = new HashSet<String>();
        userParameterSet.add(Parameters.COUNTY.toString());
        userParameterSet.add(Parameters.MIMETYPE.toString());
        userParameterSet.add(Parameters.WITHIN.toString());
        ParameterMap pm = handler.validateParameterNamesAndGroups(userParameterSet);
        assertFalse(pm.isValid());
        assertEquals(1, pm.getValidationMessages().size());
        assertTrue(pm.getValidationMessages().containsKey("Group"));
        List<String> vm = pm.getValidationMessages().get("Group");
        assertEquals(1, vm.size());
        assertEquals("Parameter(s) 'within' require(s) 'lat' and 'long'", vm.get(0));
    }
    @Test
    public void testValidateParameterNamesAndGroups_countyAndMimeTypeAndWithinAndLatLong() {
        Set<String> userParameterSet = new HashSet<String>();
        userParameterSet.add(Parameters.COUNTY.toString());
        userParameterSet.add(Parameters.MIMETYPE.toString());
        userParameterSet.add(Parameters.WITHIN.toString());
        userParameterSet.add(Parameters.LATITUDE.toString());
        userParameterSet.add(Parameters.LONGITUDE.toString());
        assertTrue(handler.validateParameterNamesAndGroups(userParameterSet).isValid());
    }
    
    
    @Test
    public void testValidateParameterNamesAndGroups_withInvalidParam() {
        Set<String> userParameterSet = new HashSet<String>();
        userParameterSet.add(Parameters.COUNTY.toString());
        userParameterSet.add(Parameters.MIMETYPE.toString());
        userParameterSet.add(Parameters.WITHIN.toString());
        userParameterSet.add(Parameters.LATITUDE.toString());
        userParameterSet.add(Parameters.LONGITUDE.toString());
        userParameterSet.add("IamNotHere");
        ParameterMap pm = handler.validateParameterNamesAndGroups(new HashSet<String>(Arrays.asList("IamNotHere")));
        assertFalse(pm.isValid());
        assertEquals(1, pm.getValidationMessages().size());
        assertTrue(pm.getValidationMessages().containsKey("IamNotHere"));
        List<String> vm = pm.getValidationMessages().get("IamNotHere");
        assertEquals(1, vm.size());
        assertEquals("Parameter Name 'IamNotHere' is not valid.  This service will NOT return ANY results until this parameter is removed from the query.", vm.get(0));
    }
    
    @Test
    public void testValidateParameterNamesAndGroups_withInvalidParamAndInvalidGroup() {
        Set<String> userParameterSet = new HashSet<String>();
        userParameterSet.add(Parameters.COUNTY.toString());
        userParameterSet.add(Parameters.MIMETYPE.toString());
        userParameterSet.add(Parameters.WITHIN.toString());
        userParameterSet.add(Parameters.LATITUDE.toString());
        userParameterSet.add("IamNotHere");
        ParameterMap pm = handler.validateParameterNamesAndGroups(userParameterSet);
        assertFalse(pm.isValid());
        assertEquals(2, pm.getValidationMessages().size());
        assertTrue(pm.getValidationMessages().containsKey("Group"));
        assertTrue(pm.getValidationMessages().containsKey("IamNotHere"));
        
        List<String> vm = pm.getValidationMessages().get("Group");
        assertEquals(1, vm.size());
        assertEquals("Parameter(s) 'lat' and 'within' require(s) 'long'", vm.get(0));
        
        vm = pm.getValidationMessages().get("IamNotHere");
        assertEquals(1, vm.size());
        assertEquals("Parameter Name 'IamNotHere' is not valid.  This service will NOT return ANY results until this parameter is removed from the query.", vm.get(0));
    }
    
    @Test
    public void testPrune_emptySet() {
        HashMap<String, String[]> parameters = new HashMap<String, String[]>();
        assertEquals("Empty gives back empty", new HashMap<String, String[]>(), handler.pruneParameters(parameters));
    }
    public void testPrune_nullValue() {
        HashMap<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put("countrycode", null);
        assertEquals("null value gives back empty", new HashMap<String, String[]>(), handler.pruneParameters(parameters));
    }
    public void testPrune_emptyStringValue() {
        HashMap<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put("countrycode", new String[]{""});
        assertEquals("Empty String gives back empty", new HashMap<String, String[]>(), handler.pruneParameters(parameters));
    }
    public void testPrune_emptyStringAndValidStringAndNullValue() {
        HashMap<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put("countrycode", new String[]{"", "US", null});
        Map<String, String[]> pruned = handler.pruneParameters(parameters);
        assertEquals(1, pruned.keySet().size());
        assertNotNull(pruned.get("countrycode"));
        assertEquals(1, ((String[]) pruned.get("countrycode")).length);
        assertEquals("US", ((String[]) pruned.get("countrycode"))[0]);
    }
    public void testPrune_emptyStringAndNullValue() {
        HashMap<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put("othercode", new String[]{"", null});
        Map<String, String[]> pruned = handler.pruneParameters(parameters);
        assertEquals(1, pruned.keySet().size());
        assertNotNull(pruned.get("countrycode"));
        assertNull(pruned.get("othercode"));
        assertEquals(1, ((String[]) pruned.get("countrycode")).length);
        assertEquals("US", ((String[]) pruned.get("countrycode"))[0]);
        assertEquals(2, parameters.keySet().size());
    }
    
    @Test
    public void testValidateAndTransformParameterValues() {
        //TODO more of an entire process test
        HashMap<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put("countrycode", new String[]{"", "US", null});
        ParameterMap pm = handler.validateAndTransformParameterValues(parameters);
        assertFalse(pm.isValid());
        assertEquals(1, pm.getValidationMessages().size());
        assertTrue(pm.getValidationMessages().containsKey("countrycode"));
        List<String> vm = pm.getValidationMessages().get("countrycode");
        assertEquals(1, vm.size());
        assertEquals("expected parameter value String[] of length == 1", vm.get(0));
    }
    
    @Test
    public void testValidateAndTransform() {
        //TODO more of an entire process test
        HashMap<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put("countrycode", new String[]{"", "US", null});
        ParameterMap pm = handler.validateAndTransform(parameters);
        assertEquals(1, pm.getValidationMessages().size());
        assertEquals(1, ((String[]) pm.getQueryParameters().get("countrycode")).length);
        
    }

}
