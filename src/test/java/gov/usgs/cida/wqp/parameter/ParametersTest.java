package gov.usgs.cida.wqp.parameter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.usgs.cida.wqp.BaseSpringTest;
import org.junit.Test;
public class ParametersTest extends BaseSpringTest {
	@Test
	public void toStringTest() {
		assertEquals("bBox", Parameters.BBOX.toString());
	}
	@Test
	public void isValidTest() {
		assertTrue(Parameters.isValid(Parameters.BBOX.toString()));
		assertFalse(Parameters.isValid("abc"));
	}
	@Test
	public void fromNameTest() {
		assertEquals(Parameters.BBOX, Parameters.fromName("bBox"));
		assertNull(Parameters.fromName("abc"));
	}
}