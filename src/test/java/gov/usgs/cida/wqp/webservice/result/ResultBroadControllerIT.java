package gov.usgs.cida.wqp.webservice.result;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.cida.wqp.Application;
import gov.usgs.cida.wqp.CsvDataSetLoader;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.springinit.DBTestConfig;
import gov.usgs.cida.wqp.util.HttpConstants;

@EnableWebMvc
@AutoConfigureMockMvc()
@SpringBootTest(webEnvironment=WebEnvironment.MOCK,
	classes={DBTestConfig.class, Application.class})
@DatabaseSetup("classpath:/testData/csv/")
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public class ResultBroadControllerIT extends BaseResultControllerIT {

	protected static final Profile PROFILE = Profile.RESULT_BROAD;
	protected static final boolean POSTABLE = true;
	protected static final String ENDPOINT = HttpConstants.RESULT_SEARCH_ENDPOINT + "?"
			+ Parameters.DATA_PROFILE + "=" + PROFILE + "&mimeType=";

	@Test
	public void testHarness() throws Exception {
		getAsCsvTest(ENDPOINT, PROFILE, POSTABLE);
		getAsCsvZipTest(ENDPOINT, PROFILE, POSTABLE);
		getAsTsvTest(ENDPOINT, PROFILE, POSTABLE);
		getAsTsvZipTest(ENDPOINT, PROFILE, POSTABLE);
		getAsXlsxTest(ENDPOINT, PROFILE, POSTABLE);
		getAsXlsxZipTest(ENDPOINT, PROFILE, POSTABLE);
		getAsXmlTest(ENDPOINT, PROFILE, POSTABLE);
		getAsXmlZipTest(ENDPOINT, PROFILE, POSTABLE);
		getAllParametersTest(ENDPOINT, PROFILE, POSTABLE);
		postGetCountTest(PROFILE);
	}

}
