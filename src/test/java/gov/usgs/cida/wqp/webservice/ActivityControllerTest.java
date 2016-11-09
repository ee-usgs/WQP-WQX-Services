package gov.usgs.cida.wqp.webservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.ActivityDelimitedTest;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.util.HttpConstants;

public class ActivityControllerTest extends BaseSpringTest {

	protected ActivityController controller;

	@Before
	public void setup() {
		controller = new ActivityController(null, null, null, null, null, null, null);
	}

	@After
	public void teardown() {
		//Need to manually clear out this thread local
		ActivityController.setCounts(null);
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getRawCounts());
		assertEquals(HttpConstants.HEADER_TOTAL_ACTIVITY_COUNT, countHeader);
		assertEquals("12", response.getHeaderValue("NWIS-Site-Count"));
		assertEquals("121", response.getHeaderValue("Total-Site-Count"));
		assertEquals("113", response.getHeaderValue("NWIS-Activity-Count"));
		assertEquals("1131", response.getHeaderValue("Total-Activity-Count"));
		assertNull(response.getHeaderValue("NWIS-Result-Count"));
		assertNull(response.getHeaderValue("Total-Result-Count"));
	}

	@Test
	public void getMappingTest() {
		//TODO - this test passed when the ActivityController was incorrectly coded with ResultDelimited...
		ActivityDelimitedTest.assertActivityProfile(controller.getMapping(Profile.ACTIVITY));
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.ACTIVITY, controller.determineProfile(null));

		Map<String, Object> pm = new HashMap<>();
		pm.put(Parameters.DATA_PROFILE.toString(), new String[]{"biological"});
		assertEquals(Profile.BIOLOGICAL, controller.determineProfile(pm));
	}

}
