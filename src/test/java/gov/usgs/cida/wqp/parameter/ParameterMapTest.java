package gov.usgs.cida.wqp.parameter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.validation.ValidationResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
public class ParameterMapTest extends BaseSpringTest {
	@Test
	public void testMergeNameVR_null() {
		ParameterMap pm = new ParameterMap();
		List<String> vms = new ArrayList<String>();
		vms.add("xyz");
		vms.add("qrs");
		pm.merge("test", new ValidationResult<Object>(true, null, vms));
		assertTrue(pm.isValid());
		assertEquals(1, pm.getValidationMessages().size());
		assertTrue(pm.getValidationMessages().containsKey("test"));
		assertEquals(2, pm.getValidationMessages().get("test").size());
		assertTrue(pm.getValidationMessages().get("test").contains("xyz"));
		assertTrue(pm.getValidationMessages().get("test").contains("qrs"));
		assertNull(pm.getQueryParameters().get("test"));
	}
	@Test
	public void testMergeNameVR_string() {
		ParameterMap pm = new ParameterMap();
		List<String> vms = new ArrayList<String>();
		vms.add("xyz");
		vms.add("qrs");
		pm.merge("test", vms);
		List<String> vms2 = new ArrayList<String>();
		vms2.add("wow");
		vms2.add("allis");
		pm.merge("test", new ValidationResult<Object>(false, "abc", vms2));
		assertFalse(pm.isValid());
		assertEquals(1, pm.getValidationMessages().size());
		assertTrue(pm.getValidationMessages().containsKey("test"));
		assertEquals(4, pm.getValidationMessages().get("test").size());
		assertTrue(pm.getValidationMessages().get("test").contains("xyz"));
		assertTrue(pm.getValidationMessages().get("test").contains("qrs"));
		assertTrue(pm.getValidationMessages().get("test").contains("wow"));
		assertTrue(pm.getValidationMessages().get("test").contains("allis"));
		assertNotNull(pm.getQueryParameters().get("test"));
		assertEquals("abc", pm.getQueryParameters().get("test"));
	}
	@Test
	public void testMergeNameList_twoEntries() {
		ParameterMap pm = new ParameterMap();
		List<String> vms = new ArrayList<String>();
		vms.add("xyz");
		vms.add("qrs");
		pm.merge("test", vms);
		assertTrue(pm.isValid());
		assertEquals(1, pm.getValidationMessages().size());
		assertTrue(pm.getValidationMessages().containsKey("test"));
		assertEquals(2, pm.getValidationMessages().get("test").size());
		assertTrue(pm.getValidationMessages().get("test").contains("xyz"));
		assertTrue(pm.getValidationMessages().get("test").contains("qrs"));
		assertNull(pm.getQueryParameters().get("test"));
	}
	@Test
	public void testMergeNameList_fourEntries() {
		ParameterMap pm = new ParameterMap();
		List<String> vms = new ArrayList<String>();
		vms.add("xyz");
		vms.add("qrs");
		pm.merge("test", vms);
		List<String> vms2 = new ArrayList<String>();
		vms2.add("wow");
		vms2.add("allis");
		pm.merge("test", vms2);
		assertTrue(pm.isValid());
		assertEquals(1, pm.getValidationMessages().size());
		assertTrue(pm.getValidationMessages().containsKey("test"));
		assertEquals(4, pm.getValidationMessages().get("test").size());
		assertTrue(pm.getValidationMessages().get("test").contains("xyz"));
		assertTrue(pm.getValidationMessages().get("test").contains("qrs"));
		assertTrue(pm.getValidationMessages().get("test").contains("wow"));
		assertTrue(pm.getValidationMessages().get("test").contains("allis"));
		assertNull(pm.getQueryParameters().get("test"));
	}
	@Test
	public void testMergeNameObject_null() {
		ParameterMap pm = new ParameterMap();
		Object o = null;
		pm.merge("test", o);
		assertTrue(pm.isValid());
		assertEquals(0, pm.getValidationMessages().size());
		assertNull(pm.getQueryParameters().get("test"));
	}
	@Test
	public void testMergeNameObject_string() {
		ParameterMap pm = new ParameterMap();
		pm.merge("test", "sdfjlhg");
		assertTrue(pm.isValid());
		assertEquals(0, pm.getValidationMessages().size());
		assertNotNull(pm.getQueryParameters().get("test"));
		assertEquals("sdfjlhg", pm.getQueryParameters().get("test"));
	}
	@Test
	public void testMergeMap() {
		ParameterMap pm1 = new ParameterMap();
		List<String> vms = new ArrayList<String>();
		vms.add("xyz");
		vms.add("qrs");
		pm1.merge("test", new ValidationResult<Object>(true, null, vms));
		ParameterMap pm2 = new ParameterMap();
		List<String> vms2 = new ArrayList<String>();
		vms2.add("allis");
		vms2.add("chalmers");
		pm2.merge("test2", new ValidationResult<Object>(false, "wow", vms2));
		pm1.merge(pm2);
		assertFalse(pm1.isValid());
		assertEquals(2, pm1.getValidationMessages().size());
		assertTrue(pm1.getValidationMessages().containsKey("test"));
		assertEquals(2, pm1.getValidationMessages().get("test").size());
		assertTrue(pm1.getValidationMessages().get("test").contains("xyz"));
		assertTrue(pm1.getValidationMessages().get("test").contains("qrs"));
		assertNull(pm1.getQueryParameters().get("test"));
		assertTrue(pm1.getValidationMessages().containsKey("test2"));
		assertEquals(2, pm1.getValidationMessages().get("test2").size());
		assertTrue(pm1.getValidationMessages().get("test2").contains("allis"));
		assertTrue(pm1.getValidationMessages().get("test2").contains("chalmers"));
		assertNotNull(pm1.getQueryParameters().get("test2"));
		assertEquals("wow", pm1.getQueryParameters().get("test2"));
		ParameterMap pm3 = new ParameterMap();
		pm1.merge(pm3);
		assertFalse(pm1.isValid());
		assertEquals(2, pm1.getValidationMessages().size());
		assertTrue(pm1.getValidationMessages().containsKey("test"));
		assertEquals(2, pm1.getValidationMessages().get("test").size());
		assertTrue(pm1.getValidationMessages().get("test").contains("xyz"));
		assertTrue(pm1.getValidationMessages().get("test").contains("qrs"));
		assertNull(pm1.getQueryParameters().get("test"));
		assertTrue(pm1.getValidationMessages().containsKey("test2"));
		assertEquals(2, pm1.getValidationMessages().get("test2").size());
		assertTrue(pm1.getValidationMessages().get("test2").contains("allis"));
		assertTrue(pm1.getValidationMessages().get("test2").contains("chalmers"));
		assertNotNull(pm1.getQueryParameters().get("test2"));
		assertEquals("wow", pm1.getQueryParameters().get("test2"));
		ParameterMap pm4 = new ParameterMap();
		pm4.setValidationMessages(null);
		pm4.setQueryParameters(null);
		pm1.merge(pm4);
		assertFalse(pm1.isValid());
		assertEquals(2, pm1.getValidationMessages().size());
		assertTrue(pm1.getValidationMessages().containsKey("test"));
		assertEquals(2, pm1.getValidationMessages().get("test").size());
		assertTrue(pm1.getValidationMessages().get("test").contains("xyz"));
		assertTrue(pm1.getValidationMessages().get("test").contains("qrs"));
		assertNull(pm1.getQueryParameters().get("test"));
		assertTrue(pm1.getValidationMessages().containsKey("test2"));
		assertEquals(2, pm1.getValidationMessages().get("test2").size());
		assertTrue(pm1.getValidationMessages().get("test2").contains("allis"));
		assertTrue(pm1.getValidationMessages().get("test2").contains("chalmers"));
		assertNotNull(pm1.getQueryParameters().get("test2"));
		assertEquals("wow", pm1.getQueryParameters().get("test2"));
	}
}