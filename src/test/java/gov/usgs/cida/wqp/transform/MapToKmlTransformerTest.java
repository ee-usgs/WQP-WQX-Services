package gov.usgs.cida.wqp.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static gov.usgs.cida.wqp.mapping.StationKml.*;
import gov.usgs.cida.wqp.mapping.IXmlMapping;
import gov.usgs.cida.wqp.mapping.StationKml;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MapToKmlTransformerTest {

	@Mock
    protected ILogService logService;
	protected BigDecimal logId = new BigDecimal(1);
	protected IXmlMapping fieldMapping = new StationKml("abc");
	protected MapToKmlTransformer transformer;
	protected ByteArrayOutputStream baos;

    @Before
    public void initTest() throws Exception {
        MockitoAnnotations.initMocks(this);
		baos = new ByteArrayOutputStream();
        transformer = new MapToKmlTransformer(baos, fieldMapping, logService, logId);
    }
    
    @After
    public void closeTest() throws IOException {
    	transformer.close();
    }

	@Test
	public void writeHeaderTest() {
		try {
			transformer.writeHeader();
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
