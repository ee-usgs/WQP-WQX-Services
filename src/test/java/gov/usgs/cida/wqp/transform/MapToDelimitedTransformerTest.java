package gov.usgs.cida.wqp.transform;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.service.ILogService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MapToDelimitedTransformerTest {

	@Mock
    protected ILogService logService;
	protected BigDecimal logId = new BigDecimal(1);

    @Before
    public void initTest() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void writeDataTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Map<String, String> mapping = new HashMap<>();
		mapping.put("A", "colA");
		mapping.put("B", "colB");
		
		MapToDelimitedTransformer transformer = new MapToDelimitedTransformer(baos, mapping, logService, logId, ";");
		Map<String, Object> result = new HashMap<>();
    	result.put("A", "1");
    	result.put("B", "2");
    	result.put("C", "3");

		try {
			transformer.write(result);
			assertEquals(13, baos.size());
			assertEquals("colA;colB\n1;2", new String(baos.toByteArray(), "UTF-8"));

	    	result.put("A", "11");
	    	result.put("B", "12");
			transformer.write(result);
			assertEquals(19, baos.size());
			assertEquals("colA;colB\n1;2\n11;12", new String(baos.toByteArray(), "UTF-8"));
	    	
			transformer.close();
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}

	}

	@Test
	public void writeDataMapTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Map<String, String> mapping = new HashMap<>();
		mapping.put("A", "colA");
		mapping.put("B", "colB");
		
		MapToDelimitedTransformer transformer = new MapToDelimitedTransformer(baos, mapping, logService, logId, "\t");

		Map<String, Object> result = new HashMap<>();
    	result.put("A", "1\r\n\t");
    	result.put("B", "2");
    	result.put("C", "3");

		try {
			transformer.writeData(result);
			assertEquals(7, baos.size());
			assertEquals("\n1   \t2", new String(baos.toByteArray(), "UTF-8"));

			baos.reset();
			result.clear();
	    	result.put("C", "3");
	    	result.put("A", "1");
	    	result.put("B", "2");
			transformer.writeData(result);
			assertEquals(4, baos.size());
			assertEquals("\n1\t2", new String(baos.toByteArray(), "UTF-8"));
	    	
			baos.reset();
	    	result.put("A", null);
			transformer.writeData(result);
			assertEquals(3, baos.size());
			assertEquals("\n\t2", new String(baos.toByteArray(), "UTF-8"));
			transformer.close();
	    	
			
			transformer = new MapToDelimitedTransformer(baos, mapping, logService, logId, ",");
			baos.reset();
			result.clear();
	    	result.put("A", "b,1");
	    	result.put("B", "2\"x\td\"34\"");
			transformer.writeData(result);
			assertEquals(21, baos.size());
			assertEquals("\n\"b,1\",\"2\"\"x\td\"\"34\"\"\"", new String(baos.toByteArray(), "UTF-8"));

			baos.reset();
			result.clear();
	    	result.put("A", "\"sdjfhjks\"");
	    	result.put("B", "\"");
			transformer.writeData(result);
			assertEquals(20, baos.size());
			assertEquals("\n\"\"\"sdjfhjks\"\"\",\"\"\"\"", new String(baos.toByteArray(), "UTF-8"));

			transformer.close();
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}

	}
	
	@Test
	public void writeHeaderTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Map<String, String> mapping = new HashMap<>();
		mapping.put("A", "colA");
		mapping.put("B", "colB");
		
		MapToDelimitedTransformer transformer = new MapToDelimitedTransformer(baos, mapping, logService, logId, ";");

		Map<String, Object> result = new HashMap<>();
    	result.put("A", "1");
    	result.put("B", "2");
    	result.put("C", "3");

		try {
			transformer.writeHeader();
			assertEquals(9, baos.size());
			assertEquals("colA;colB", new String(baos.toByteArray(), "UTF-8"));
	    	
			baos.reset();
			result.clear();
	    	result.put("C", "3");
	    	result.put("A", "1");
	    	result.put("B", "2");
			transformer.writeHeader();
			assertEquals(9, baos.size());
			assertEquals("colA;colB", new String(baos.toByteArray(), "UTF-8"));

	    	transformer.close();
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}

	}
	
}
