package gov.usgs.wma.wqp.transform;

import static gov.usgs.wma.wqp.mapping.xml.StationKml.KML_DOCUMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.mapping.xml.IXmlMapping;
import gov.usgs.wma.wqp.mapping.xml.StationKml;
import gov.usgs.wma.wqp.service.ConfigurationService;
import gov.usgs.wma.wqp.service.ILogService;
import gov.usgs.wma.wqp.util.HttpConstants;

public class MapToKmlTransformerTest {

	@Mock
	protected ILogService logService;
	protected Integer logId = 1;
	protected IXmlMapping fieldMapping;
	protected MapToKmlTransformer transformer;
	protected ByteArrayOutputStream baos;
	private ConfigurationService configurationService;

	@BeforeEach
	public void initTest() {
		MockitoAnnotations.initMocks(this);
		configurationService = new ConfigurationService();
		fieldMapping = new StationKml(configurationService);
		baos = new ByteArrayOutputStream();
		transformer = new MapToKmlTransformer(baos, fieldMapping, logService, logId, Profile.STATION);
	}

	@AfterEach
	public void closeTest() throws IOException {
		transformer.close();
	}

	@Test
	public void writeHeaderTest() {
		try {
			assertEquals(38 + fieldMapping.getHeader().length(), baos.size());
			assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + fieldMapping.getHeader(),
					new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));
			assertTrue(transformer.nodes.contains(fieldMapping.getRoot()));
			assertTrue(transformer.nodes.contains(KML_DOCUMENT));
			assertEquals(KML_DOCUMENT, transformer.nodes.pop());
			assertEquals(fieldMapping.getRoot(), transformer.nodes.pop());
			assertEquals(0, transformer.nodes.size());
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

}
