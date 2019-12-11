package gov.usgs.cida.wqp.transform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.service.ILogService;
import gov.usgs.cida.wqp.util.HttpConstants;

public class TransformerTest {

	@Mock
	protected ILogService logService;
	protected Integer logId = 1;

	@BeforeEach
	public void initTest() {
		MockitoAnnotations.initMocks(this);
	}

	private class TTransformer extends Transformer {
		public int initCalled = 0;
		public int writeHeaderCalled = 0;
		public int writeDataCalled = 0;
		public TTransformer(OutputStream target, Map<String, String> mapping, ILogService logService, Integer logId) {
			super(target, mapping, logService, logId);
		}
		@Override
		protected void init() {
			initCalled = initCalled + 1;
		}
		@Override
		protected void writeHeader() {
			writeHeaderCalled = writeHeaderCalled + 1;
		}
		@Override
		protected void writeData(Map<String, Object> resultMap) {
			writeDataCalled = writeDataCalled + 1;
		}
		@Override
		public String encode(String value) {
			return null;
		}
		@Override
		public void end() {
		}
	}

	@Test
	public void writeTest() {
		TTransformer transformer = new TTransformer(new ByteArrayOutputStream(), null, logService, logId);

		//Don't process null results
		try {
			transformer.write((Object) null);
			assertEquals(0, transformer.initCalled);
			assertEquals(0, transformer.writeHeaderCalled);
			assertEquals(0, transformer.writeDataCalled);
			verify(logService, never()).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(FilterParameters.class));
			verify(logService, never()).logHeadComplete(anyList(), anyString(), eq(logId));
			verify(logService, never()).logFirstRowComplete(logId);
			verify(logService, never()).logRequestComplete(eq(logId), anyString(), anyMap());
			transformer.close();
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}

		//Don't process results that aren't a map
		try {
			transformer.write((Object) "ABCDEFG");
			assertEquals(0, transformer.initCalled);
			assertEquals(0, transformer.writeHeaderCalled);
			assertEquals(0, transformer.writeDataCalled);
			verify(logService, never()).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(FilterParameters.class));
			verify(logService, never()).logHeadComplete(anyList(), anyString(), eq(logId));
			verify(logService, never()).logFirstRowComplete(logId);
			verify(logService, never()).logRequestComplete(eq(logId), anyString(), anyMap());
			transformer.close();
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}

		Map<String, Object> result = new HashMap<>();
		result.put("A", "1");
		result.put("B", "2");

		try {
			transformer.write((Object) result);
			transformer.write((Object) result);
			assertEquals(0, transformer.initCalled);
			assertEquals(0, transformer.writeHeaderCalled);
			assertEquals(2, transformer.writeDataCalled);
			verify(logService, never()).logRequest(any(HttpServletRequest.class), any(HttpServletResponse.class), any(FilterParameters.class));
			verify(logService, never()).logHeadComplete(anyList(), anyString(), eq(logId));
			verify(logService, times(1)).logFirstRowComplete(logId);
			verify(logService, never()).logRequestComplete(eq(logId), anyString(), anyMap());
			transformer.close();
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void copyStringTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MapToDelimitedTransformer transformer = new MapToDelimitedTransformer(baos, new HashMap<String, String>(), logService, logId, ",");
		try {
			transformer.writeToStream(null);
			assertEquals(0, baos.size());
			
			transformer.writeToStream("abc");
			assertEquals(3, baos.size());
			assertEquals("abc", new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));

			transformer.writeToStream("\u00A7\u00AEabcDE\u00A9\u00B1");
			assertEquals(16, baos.size());
			assertEquals("abc\u00A7\u00AEabcDE\u00A9\u00B1", new String(baos.toByteArray(), HttpConstants.DEFAULT_ENCODING));

			transformer.close();
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void getStringValueTest() throws SQLException {
		Object object = null;
		assertNull(Transformer.getStringValue(object));

		object = "abcdef";
		assertEquals("abcdef", Transformer.getStringValue(object));

		object = (BigDecimal) null;
		assertNull(Transformer.getStringValue(object));

		object = BigDecimal.ONE;
		assertEquals("1", Transformer.getStringValue(object));

		object = (Clob) null;
		assertNull(Transformer.getStringValue(object));
//
//		object = CLOB.createTemporary(null, false, CLOB.DURATION_CALL);
//		assertEquals("1", Transformer.getStringValue(object));
	}
}
