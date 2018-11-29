package gov.usgs.cida.wqp.webservice.BiologicalMetric;

import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.mapping.delimited.BiologicalMetricDelimitedTest;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

public class BiologicalMetricTest {
	
	protected BiologicalMetricController controller;

	@Before
	public void setup() {
		controller = new BiologicalMetricController(null, null, null, null, null, null, null);
		BiologicalMetricController.remove();
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getRawCounts());
		assertEquals(HttpConstants.HEADER_TOTAL_BIOLOGICAL_METRIC_COUNT, countHeader);
		assertEquals(BaseControllerTest.TEST_NWIS_STATION_COUNT, response.getHeaderValue(HEADER_NWIS_SITE_COUNT));
		assertEquals(BaseControllerTest.TEST_TOTAL_STATION_COUNT, response.getHeaderValue(HttpConstants.HEADER_TOTAL_SITE_COUNT));
	}

	@Test
	public void getMappingTest() {
		BiologicalMetricDelimitedTest.assertBiologicalMetricProfile(controller.getMapping(Profile.BIOLOGICAL_METRIC));		
	}

	@Test
	public void determineProfileTest() {
		assertEquals(Profile.BIOLOGICAL_METRIC, controller.determineProfile(null));

		FilterParameters filter = new FilterParameters();
		filter.setDataProfile("biological");
		assertEquals(Profile.BIOLOGICAL, controller.determineProfile(filter));
	}
	
}
