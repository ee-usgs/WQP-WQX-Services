package gov.usgs.cida.wqp.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.mapping.StationColumn;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseMapToJsonTransformerTest {

//	public static final String JSON_HEADER = "{\"type\":\"FeatureCollection\",\"features\":[";
        private static final transient Logger LOG = LoggerFactory.getLogger(BaseMapToJsonTransformer.class);

	@Mock
	protected ILogService logService;
	protected BigDecimal logId = new BigDecimal(1);
	protected BaseMapToJsonTransformer transformer;
	protected ByteArrayOutputStream baos;
	protected String siteUrlBase = "http://test-url.usgs.gov";   

	@Before
	public void initTest() {
		MockitoAnnotations.initMocks(this);
		baos = new ByteArrayOutputStream();
		transformer = new BaseMapToJsonTransformer(baos, null, logService, logId, siteUrlBase);
        }

	@After
	public void closeTest() throws IOException {
		transformer.close();
	}       

	@Test
	public void getValueTest() {
		Map<String, Object> map = new HashMap<>();
		map.put("NotNull", "abc/");
		map.put("Null", null);
		assertEquals("abc/", transformer.getValue(map, "NotNull"));
		assertEquals("", transformer.getValue(map, "Null"));
		assertEquals("", transformer.getValue(map, "NoWay"));
	}

	@Test
	public void encodeTest() {
		assertEquals("abc", transformer.encode("abc"));
		assertEquals("<d/ae>", transformer.encode("<d/ae>"));
	}
}
