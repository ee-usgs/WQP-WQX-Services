package gov.usgs.cida.wqp.webservice.station;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONObjectAs;

import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

import gov.usgs.cida.wqp.BaseSpringTest;
import gov.usgs.cida.wqp.FullIntegrationTest;
import gov.usgs.cida.wqp.mapping.Profile;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.service.FetchService;
import gov.usgs.cida.wqp.util.HttpConstants;
import gov.usgs.cida.wqp.util.MimeType;

@Category(FullIntegrationTest.class)
@WebAppConfiguration
@DatabaseSetups({
	@DatabaseSetup("classpath:/testData/clearAll.xml"),
	@DatabaseSetup("classpath:/testData/station.xml")
})
public class StationControllerTest extends BaseSpringTest {

	protected String endpoint = "/" + HttpConstants.STATION_SEARCH_ENPOINT + "?mimeType=";

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private CodesService codesService;

	@Autowired
	private FetchService fetchService;

	@Autowired
	@Qualifier("TEST_NLDI_URL")
	protected URL testNldiUrl;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getAsCsvTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "csv"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_CSV))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.csv"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());

		rtn = mockMvc.perform(get(endpoint + "csv"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_CSV))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.csv"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals(getCompareFile("station.csv"), rtn.getResponse().getContentAsString());
	}

	@Test
	public void getAsCsvZipTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "csv&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());

		rtn = mockMvc.perform(get(endpoint + "csv&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals(getCompareFile("station.csv"), extractZipContent(rtn.getResponse().getContentAsByteArray(), "station.csv"));
	}

	@Test
	public void getAsTsvTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "tsv"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_TSV))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.tsv"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());

		rtn = mockMvc.perform(get(endpoint + "tsv"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_TSV))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.tsv"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals(getCompareFile("station.tsv"), rtn.getResponse().getContentAsString());
	}

	@Test
	public void getAsTsvZipTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "tsv&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());

		rtn = mockMvc.perform(get(endpoint + "tsv&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals(getCompareFile("station.tsv"), extractZipContent(rtn.getResponse().getContentAsByteArray(), "station.tsv"));
	}

	@Test
	public void getAsXlsxTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "xlsx"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_XLSX))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.xlsx"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());

		rtn = mockMvc.perform(get(endpoint + "xlsx"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_XLSX))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.xlsx"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();
	}

	@Test
	public void getAsXlsxZipTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "xlsx&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());

		rtn = mockMvc.perform(get(endpoint + "xlsx&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();
	}

	@Test
	public void getAsXmlHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "xml"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_XML))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.xml"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());
	}

	@Test
	public void getAsXmlZipHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "xml&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());
	}

	@Test
	public void getAsXmlGetTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(endpoint + "xml"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_XML))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.xml"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals(harmonizeXml(getCompareFile("station.xml")), harmonizeXml(rtn.getResponse().getContentAsString()));
	}

	@Test
	public void getAsXmlZipGetTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(endpoint + "xml&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals(harmonizeXml(getCompareFile("station.xml")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), "station.xml")));
	}

	@Test
	public void getAsKmlHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "kml"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_KML))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.kml"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());
	}

	@Test
	public void getAsKmlZipHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "kml&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_KMZ))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.kmz"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());
	}

	@Test
	public void getAsKmzHeadTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "kmz"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_KMZ))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.kmz"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());
	}

	@Test
	public void getAsKmlGetTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(endpoint + "kml"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_KML))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.kml"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals(harmonizeXml(getCompareFile("station.kml")), harmonizeXml(rtn.getResponse().getContentAsString()));
	}

	@Test
	public void getAsKmlZipGetTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(endpoint + "kml&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_KMZ))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.kmz"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals(harmonizeXml(getCompareFile("station.kml")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), "station.kml")));
	}

	@Test
	public void getAsKmzGetTest() throws Exception {
		MvcResult rtn = mockMvc.perform(get(endpoint + "kmz"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_KMZ))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.kmz"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals(harmonizeXml(getCompareFile("station.kml")), harmonizeXml(extractZipContent(rtn.getResponse().getContentAsByteArray(), "station.kml")));
	}

	@Test
	public void kitchenSinkTest() throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);
		when(fetchService.fetch(any(String.class), any(URL.class))).thenReturn(Stream.of("a", "b").collect(Collectors.toSet()));

		mockMvc.perform(
			get(endpoint + "xml" +
				"&analyticalmethod=https://www.nemi.gov/methods/method_summary/4665/;https://www.nemi.gov/methods/method_summary/8896/" + 
				"bBox=-89;43;-88;44" +
				"&assemblage=Fish/Nekton;Benthic Macroinvertebrates" +
				"&characteristicName=Beryllium;Nitrate" +
				"&characteristicType=Inorganics, Minor, Metals;Nutrient;Population/Community" + 
				"&command.avoid=STORET;NWIS" + 
				"&countrycode=MX;US" + 
				"&countycode=US:19:015;US:30:003;US:55:017;US:55:021;US:55:027" +
				"&huc=07*;0708*;070801*;07090002;07080105" + 
				"&lat=43.3836014" +
				"&long=-88.9773314" + 
				"&minresults=10000" +
				"&nldiurl=" + testNldiUrl +
				"&organization=ARS;11NPSWRD;USGS-WI;WIDNR_WQX" + 
				"&pCode=00032;00004" +
				"&project=CEAP;NAWQA" +
				"&sampleMedia=Other;Sediment;Water" +
				"&providers=NWIS;STEWARDS;STORET" +
				"&siteid=11NPSWRD-BICA_MFG_B;WIDNR_WQX-10030952;USGS-05425700;USGS-431925089002701;ARS-IAWC-IAWC225;ARS-IAWC-IAWC410" +
				"&siteType=Lake, Reservoir, Impoundment;Land;Stream;Well" + 
				"&statecode=US:19;US:30;US:55" + 
				"&startDateHi=10-11-2012" +
				"&startDateLo=10-11-2012" +
				"&subjectTaxonomicName=Acipenser;Lota lota" +
				"&within=1000"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.xml"))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null));
	}

	@Test
	public void getAsGeojsonTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "geojson"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_GEOJSON))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.geojson"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());

		rtn = mockMvc.perform(get(endpoint + "geojson"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_GEOJSON))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.geojson"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
				sameJSONObjectAs(new JSONObject(getCompareFile("simpleStation.json"))));
	}

	@Test
	public void getAsGeojsonZipTest() throws Exception {
		MvcResult rtn = mockMvc.perform(head(endpoint + "geojson&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertEquals("", rtn.getResponse().getContentAsString());

		rtn = mockMvc.perform(get(endpoint + "geojson&zip=yes"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_ZIP))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.zip"))
			.andExpect(header().string("Total-Site-Count", "6"))
			.andExpect(header().string("NWIS-Site-Count", "2"))
			.andExpect(header().string("STEWARDS-Site-Count", "2"))
			.andExpect(header().string("STORET-Site-Count", "2"))
			.andReturn();

		assertThat(new JSONObject(extractZipContent(rtn.getResponse().getContentAsByteArray(), "station.geojson")),
				sameJSONObjectAs(new JSONObject(getCompareFile("simpleStation.json"))));
	}

	@Test
	public void postJsonGetAsGeojsonTest() throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

		mockMvc.perform(post(endpoint + "geojson").content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_GEOJSON))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.geojson"))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null));
	}

	@Test
	public void postTonOSitesTest() throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

		mockMvc.perform(post(endpoint + "csv").content(getSourceFile("manySites.json")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MimeType.csv.getMimeType()))
				.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
				.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.csv"))
				.andExpect(header().string("Total-Site-Count", "0"))
				.andExpect(header().string("NWIS-Site-Count", (String)null))
				.andExpect(header().string("STEWARDS-Site-Count", (String)null))
				.andExpect(header().string("STORET-Site-Count", (String)null));
	}

	@Test
	public void postFormUrlencodedGetAsGeojsonTest() throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

		//Note that in real life the values are urlencoded - This mock does not un-encode them, so we must use real values.
		mockMvc.perform(post(endpoint).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("mimeType", "geojson")
				.param("countrycode", "US")
				.param("statecode", "US:05")
				.param("countycode", "US:05:007")
				.param("countycode", "US:05:008"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_GEOJSON))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, "attachment; filename=station.geojson"))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null));
	}

	@Test
	public void postGetCountTest() throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

		MvcResult rtn = mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=json").content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_JSON))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, (String)null))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null))
			.andReturn();

		assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
				sameJSONObjectAs(new JSONObject("{\"Total-Site-Count\":\"0\"}")));

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=csv")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=csv&zip=yes")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=tsv")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=tsv&zip=yes")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=xlsx")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=xlsx&zip=yes")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=xml")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=xml&zip=yes")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=kml")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=kml&zip=yes")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=kmz")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=geojson")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());

		mockMvc.perform(post("/" + HttpConstants.STATION_SEARCH_ENPOINT + "/count?mimeType=geojson&zip=yes")
				.content(getSourceFile("postParameters.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotAcceptable());
	}

	@Test
	public void postGetCountTonOSitesTest() throws Exception {
		when(codesService.validate(any(Parameters.class), anyString())).thenReturn(true);

		MvcResult rtn = mockMvc.perform(post("/" + HttpConstants.RESULT_SEARCH_ENPOINT + "/count?mimeType=json&" 
				+ Parameters.DATA_PROFILE + "=" + Profile.BIOLOGICAL)
				.content(getSourceFile("manySites.json")).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(HttpConstants.MIME_TYPE_JSON))
			.andExpect(content().encoding(HttpConstants.DEFAULT_ENCODING))
			.andExpect(header().string(HttpConstants.HEADER_CONTENT_DISPOSITION, (String)null))
			.andExpect(header().string("Total-Site-Count", "0"))
			.andExpect(header().string("NWIS-Site-Count", (String)null))
			.andExpect(header().string("STEWARDS-Site-Count", (String)null))
			.andExpect(header().string("STORET-Site-Count", (String)null))
			.andReturn();

		assertThat(new JSONObject(rtn.getResponse().getContentAsString()),
				sameJSONObjectAs(new JSONObject("{\"Total-Site-Count\":\"0\",\"Total-Activity-Count\":\"0\",\"Total-Result-Count\":\"0\"}")));
	}

}
