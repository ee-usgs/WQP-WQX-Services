package gov.usgs.wma.wqp.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gov.usgs.wma.wqp.BaseTest;
import gov.usgs.wma.wqp.validation.NldiFetchValidator;

public class FetchServiceTest extends BaseTest {

	@Mock
	protected ConfigurationService configurationService;
	protected FetchService service;

	public static final List<String> NLDI_IDENTIFIERS;
	static {
		NLDI_IDENTIFIERS = new ArrayList<String>();
		NLDI_IDENTIFIERS.add("USGS-05427880");
		NLDI_IDENTIFIERS.add("WIDNR_WQX-10001222");
		NLDI_IDENTIFIERS.add("WIDNR_WQX-134002");
		NLDI_IDENTIFIERS.add("WIDNR_WQX-10016974");
		NLDI_IDENTIFIERS.add("WIDNR_WQX-10001221");
		NLDI_IDENTIFIERS.add("WIDNR_WQX-10001223");
		NLDI_IDENTIFIERS.add("WIDNR_WQX-10001227");
		NLDI_IDENTIFIERS.add("WIDNR_WQX-10017024");
		NLDI_IDENTIFIERS.add("USGS-431212089314801");
		NLDI_IDENTIFIERS.add("USGS-431208089314901");
		NLDI_IDENTIFIERS.add("WIDNR_WQX-10043697");
		NLDI_IDENTIFIERS.add("USGS-431212089314802");
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(configurationService.getNldiTimeoutMilli()).thenReturn(1);
		service = new FetchService(configurationService);
	}

	@Test
	public void fetchTimeoutTest() throws IOException {
		try {
			service.fetch(NldiFetchValidator.NLDI_WQP_FEATURE_IDENTIFIER, TEST_NLDI_URL);
			fail("Should have gotten a SocketTimeoutException");
		} catch (SocketTimeoutException e) {
			//This is the expected behavior
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void parseTest() {
		try {
			assertSites(service.parse(NldiFetchValidator.NLDI_WQP_FEATURE_IDENTIFIER, new ByteArrayInputStream(getSourceFile("navigation.geojson").getBytes())));
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void fetchTest() throws IOException {
		URL url = mock(URL.class);
		URLConnection conn = mock(URLConnection.class);
		
		when(url.openConnection()).thenReturn(conn);
		when(conn.getInputStream()).thenReturn(new ByteArrayInputStream(getSourceFile("navigation.geojson").getBytes()));
		
		try {
			assertSites(service.fetch(NldiFetchValidator.NLDI_WQP_FEATURE_IDENTIFIER, url));
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	private void assertSites(List<String> sites){
		assertFalse(sites.isEmpty());
		assertEquals(12, sites.size());
		assertThat(sites, containsInAnyOrder(NLDI_IDENTIFIERS.toArray()));
	}

}
