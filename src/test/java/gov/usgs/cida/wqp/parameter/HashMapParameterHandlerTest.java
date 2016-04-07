package gov.usgs.cida.wqp.parameter;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import gov.usgs.cida.wqp.BaseSpringTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;

public class HashMapParameterHandlerTest extends BaseSpringTest {

	@Resource
	HashMapParameterHandler handler;
	
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
	public void testValidateParameterGroups_userSetOf4_valid() {
		Set<Parameters> userParameterSet = new HashSet<Parameters>();
		userParameterSet.add(Parameters.COUNTY);
		userParameterSet.add(Parameters.MIMETYPE);
		userParameterSet.add(Parameters.COUNTRY);
		userParameterSet.add(Parameters.STATE);
		try {
			HashMapParameterHandler.GROUP_LIST.add(userParameterSet);
			ParameterMap pm = handler.validateParameterGroups(userParameterSet);
			assertTrue(pm.isValid());
		} finally {
			HashMapParameterHandler.GROUP_LIST.remove(userParameterSet);
		}
	}
	
	@Test
	public void testValidateParameterGroups_userSetOf4_invalid() {
		Set<Parameters> parameterGroup = new HashSet<Parameters>();
		parameterGroup.add(Parameters.COUNTY);
		parameterGroup.add(Parameters.MIMETYPE);
		parameterGroup.add(Parameters.COUNTRY);
		parameterGroup.add(Parameters.STATE);
		Set<Parameters> userParameterSet = new HashSet<Parameters>();
		userParameterSet.add(Parameters.COUNTY);
		userParameterSet.add(Parameters.COUNTRY);
		userParameterSet.add(Parameters.STATE);
		try {
			HashMapParameterHandler.GROUP_LIST.add(parameterGroup);
			ParameterMap pm = handler.validateParameterGroups(userParameterSet);
			assertFalse(pm.isValid());
			assertEquals(1, pm.getValidationMessages().size());
			assertTrue(pm.getValidationMessages().containsKey("Group"));
			List<String> vm = pm.getValidationMessages().get("Group");
			assertEquals(1, vm.size());
			assertEquals("Parameter(s) 'countrycode', 'countycode' and 'statecode' require(s) 'mimeType'", vm.get(0));
		} finally {
			HashMapParameterHandler.GROUP_LIST.remove(parameterGroup);
		}
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
	
	@Test
	public void testPrune_nullValue() {
		HashMap<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("countrycode", null);
		assertEquals("null value gives back empty", new HashMap<String, String[]>(), handler.pruneParameters(parameters));
	}
	
	@Test
	public void testPrune_emptyStringValue() {
		HashMap<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("countrycode", new String[]{""});
		assertEquals("Empty String gives back empty", new HashMap<String, String[]>(), handler.pruneParameters(parameters));
	}
	
	@Test
	public void testPrune_emptyStringAndValidStringAndNullValue() {
		HashMap<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("countrycode", new String[]{"", "US", null});
		Map<String, String[]> pruned = handler.pruneParameters(parameters);
		assertEquals(1, pruned.keySet().size());
		assertNotNull(pruned.get("countrycode"));
		assertEquals(1, ((String[]) pruned.get("countrycode")).length);
		assertEquals("US", ((String[]) pruned.get("countrycode"))[0]);
	}
	
	@Test
	public void testPrune_emptyStringAndNullValue() {
		HashMap<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("countrycode", new String[]{"", null});
		Map<String, String[]> pruned = handler.pruneParameters(parameters);
		assertEquals(0, pruned.keySet().size());
	}
	
	@Test
	public void testPrune_undocumentedParameter() {
		HashMap<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("othercode", new String[]{"abc", null});
		Map<String, String[]> pruned = handler.pruneParameters(parameters);
		assertEquals(0, pruned.keySet().size());
	}
	
	@Test
	public void testPrune_multipleParameter() {
		HashMap<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put("countrycode", new String[]{"US", null, "MX", ""});
		Map<String, String[]> pruned = handler.pruneParameters(parameters);
		assertEquals(1, pruned.keySet().size());
		assertNotNull(pruned.get("countrycode"));
		assertEquals(1, ((String[]) pruned.get("countrycode")).length);
		assertEquals("US;MX", ((String[]) pruned.get("countrycode"))[0]);
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
		Map<String, String[]> parameters = new HashMap<>();
		parameters.put("countrycode", new String[]{"", "US", null});
		Map<String, Object> postParameters = new HashMap<>();
		postParameters.put("countrycode", Arrays.asList("MX"));
		ParameterMap pm = handler.validateAndTransform(parameters, postParameters);
		assertEquals(1, pm.getValidationMessages().size());
		assertEquals(2, ((String[]) pm.getQueryParameters().get("countrycode")).length);
	}
	
	@Test
	public void mergeParametersTest() {
		Map<String, String[]> requestParms = new HashMap<>();
		Map<String, Object> postParms = new HashMap<>();

		assertTrue(handler.mergeParameters(null, null).isEmpty());
		assertTrue(handler.mergeParameters(requestParms, null).isEmpty());
		assertTrue(handler.mergeParameters(null, postParms).isEmpty());
		assertTrue(handler.mergeParameters(requestParms, postParms).isEmpty());

		requestParms.put("noMatch", new String[]{"requestOnly", "ro"});
		requestParms.put("string", new String[]{"rString1", "rString2"});
		requestParms.put("bbox", new String[]{"1,2,3,4"});
		
		postParms.put("string", Arrays.asList("pString1", "pString2"));
		postParms.put("bbox", "1,2,3,4");
		postParms.put("list", Arrays.asList("item1", "item2"));

		Map<String, String[]> result = handler.mergeParameters(requestParms, postParms);
		assertEquals(4, result.size());
		assertTrue(result.containsKey("noMatch"));
		assertArrayEquals(new String[]{"requestOnly", "ro"}, result.get("noMatch"));
		assertTrue(result.containsKey("string"));
        assertThat(Arrays.copyOf(result.get("string"), result.get("string").length), arrayContainingInAnyOrder(new String[]{"rString1", "rString2", "pString1", "pString2"}));
		assertTrue(result.containsKey("bbox"));
		assertArrayEquals(new String[]{"1,2,3,4", "1,2,3,4"}, result.get("bbox"));
		assertTrue(result.containsKey("list"));
		assertArrayEquals(new String[]{"item1", "item2"}, result.get("list"));
		
	}

	@Test
	public void convertPostValueToListTest() {
		assertTrue(handler.convertPostValueToList(null).isEmpty());
		
		List<String> result = handler.convertPostValueToList("string");
		assertEquals(1, result.size());
		assertEquals("string", result.get(0));
		
		result = handler.convertPostValueToList("1,2,3,4");
		assertEquals(1, result.size());
		assertEquals("1,2,3,4", result.get(0));

		result = handler.convertPostValueToList(Arrays.asList("item1", "item2"));
		assertEquals(2, result.size());
		assertEquals(Arrays.asList("item1", "item2"), result);
	}

}