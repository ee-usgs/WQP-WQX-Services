package gov.usgs.cida.wqp.webservice.summary;

import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.webservice.BaseControllerTest;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;


public class SummaryOrganizationControllerTest {	
	private SummaryOrganizationController summaryOrganizationController; 
    
	@Before
	public void setup() {
		summaryOrganizationController = new SummaryOrganizationController(null, null, null, null, null, null);
		SummaryOrganizationController.remove();
	}	
	
	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = summaryOrganizationController.addCountHeaders(response, BaseControllerTest.getRawCounts());		
		assertEquals(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT, countHeader);		
	}	

	@Test
	public void determineProfileTest() {		   
		FilterParameters testFilter = new FilterParameters();
		testFilter.setDataProfile("nothing");
		assertEquals(Profile.SUMMARY_ORGANIZATION, summaryOrganizationController.determineProfile(testFilter));	
	}
}
