package gov.usgs.cida.wqp.webservice;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.DBIntegrationTest;
import gov.usgs.cida.wqp.util.HttpConstants;

@Category(DBIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetup("classpath:/testData/blob_content.xml")
public class BlobControllerIntTest extends BaseSpringTest{

	protected static final String ENDPOINT = "/organizations/WQP/projects/br549/files";

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	//UT Testing
	@Test
	public void getBlobTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(ENDPOINT))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=project.zip"))
				.andReturn();

		assertEquals(getCompareFile("project/projectTestBlob.txt"), extractZipContent(rtn.getResponse().getContentAsByteArray(), "projectTestBlob.txt"));
	}

}
