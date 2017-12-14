package gov.usgs.cida.wqp.service;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.FullIntegrationTest;
import gov.usgs.cida.wqp.validation.NldiFetchValidator;

@Category(FullIntegrationTest.class)
public class FetchServiceIntTest extends BaseSpringTest {

	protected FetchService service;

	@Autowired
	@Qualifier("TEST_NLDI_URL")
	protected URL testNldiUrl;

	@Test
	public void fetchTimeoutTest() throws IOException {
		service = new FetchService(1);
		try {
			service.fetch(NldiFetchValidator.NLDI_WQP_FEATURE_IDENTIFIER, testNldiUrl);
			fail("Should have gotten a SocketTimeoutException");
		} catch (SocketTimeoutException e) {
			//This is the expected behavior
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

}
