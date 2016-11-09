package gov.usgs.cida.wqp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.springinit.ParameterValidationConfig;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FetchService.class)
public class FetchServiceTest extends BaseSpringTest {

	protected FetchService service;

	public static final Set<String> NLDI_IDENTIFIERS;
	static {
		NLDI_IDENTIFIERS = new HashSet<String>();
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

	@Test
	public void parseTest() {
		service = new FetchService(1);
		try {
			assertSites(service.parse(ParameterValidationConfig.NLDI_WQP_FEATURE_IDENTIFIER, new ByteArrayInputStream(getSourceFile("navigation.geojson").getBytes())));
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void fetchTest() throws IOException {
		service = new FetchService(1);
		URL url = mock(URL.class);
		URLConnection conn = mock(URLConnection.class);
		
		when(url.openConnection()).thenReturn(conn);
		when(conn.getInputStream()).thenReturn(new ByteArrayInputStream(getSourceFile("navigation.geojson").getBytes()));
		
		try {
			assertSites(service.fetch(ParameterValidationConfig.NLDI_WQP_FEATURE_IDENTIFIER, url));
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	private void assertSites(Set<String> sites){
		assertFalse(sites.isEmpty());
		assertEquals(12, sites.size());
		assertEquals(NLDI_IDENTIFIERS, sites);
	}

}
