package gov.usgs.wma.wqp.webservice.BiologicalMetric;

import static gov.usgs.wma.wqp.openapi.model.StationCountJson.HEADER_NWIS_SITE_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.delimited.BiologicalMetricDelimitedTest;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.util.HttpConstants;
import gov.usgs.wma.wqp.webservice.BaseControllerTest;

public class BiologicalMetricTest {

	protected BiologicalMetricController controller;

	@BeforeEach
	public void setup() {
		controller = new BiologicalMetricController(null, null, null, null, null, null, null);
		BiologicalMetricController.remove();
	}

	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = controller.addCountHeaders(response, BaseControllerTest.getNwisRawCounts());
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
