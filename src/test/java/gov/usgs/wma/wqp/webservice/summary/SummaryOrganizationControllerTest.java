package gov.usgs.wma.wqp.webservice.summary;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.util.HttpConstants;
import gov.usgs.wma.wqp.webservice.BaseControllerTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;


public class SummaryOrganizationControllerTest {	
	private SummaryOrganizationController summaryOrganizationController; 
    
	@BeforeEach
	public void setup() {
		summaryOrganizationController = new SummaryOrganizationController(null, null, null, null, null, null);
		SummaryOrganizationController.remove();
	}	
	
	@Test
	public void addCountHeadersTest() {
		MockHttpServletResponse response = new MockHttpServletResponse();
		String countHeader = summaryOrganizationController.addCountHeaders(response, BaseControllerTest.getNwisRawCounts());		
		assertEquals(HttpConstants.HEADER_TOTAL_ORGANIZATION_COUNT, countHeader);		
	}	

	@Test
	public void determineProfileTest() {		   
		FilterParameters testFilter = new FilterParameters();
		testFilter.setDataProfile("nothing");
		assertEquals(Profile.SUMMARY_ORGANIZATION, summaryOrganizationController.determineProfile(testFilter));	
	}
}
