package gov.usgs.cida.wqp.transform;

import static org.junit.Assert.assertEquals;
import gov.usgs.cida.wqp.service.ILogService;


import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BaseMapToJsonTransformerTest {
	private class BaseMapToJsonTransImpl extends BaseMapToJsonTransformer {
		public int writeHeaderCalled = 0;
		public int writeDataCalled = 0;
		public BaseMapToJsonTransImpl(OutputStream target, Map<String, String> mapping, ILogService logService, BigDecimal logId, String siteUrlBase) {
			super(target, mapping, logService, logId, siteUrlBase);	
		}
		
		@Override
		protected void writeHeader() {
			writeHeaderCalled = writeHeaderCalled + 1;
		}
		@Override
		protected void writeData(Map<String, Object> resultMap) {
			writeDataCalled = writeDataCalled + 1;
		}	
	}
	
	@Mock
	protected ILogService logService;
	protected BigDecimal logId = new BigDecimal(1);	
	protected ByteArrayOutputStream baos;
	protected String siteUrlBase = "http://test-url.usgs.gov";
	private BaseMapToJsonTransImpl transformer;
	
	@Before
	public void initTest() {
		MockitoAnnotations.initMocks(this);
		baos = new ByteArrayOutputStream();
		transformer = new BaseMapToJsonTransImpl(baos, null, logService, logId, siteUrlBase);
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
