package gov.usgs.cida.wqp.transform;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import gov.usgs.cida.wqp.service.ILogService;

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

public class MapToDelimitedTransformerTest {

	@Mock
    protected ILogService logService;
	protected BigDecimal logId = new BigDecimal(1);
	protected MapToDelimitedTransformer csv;
	protected MapToDelimitedTransformer tsv;
	protected ByteArrayOutputStream baos;
	protected Map<String, String> mapping;

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
		baos = new ByteArrayOutputStream();
		mapping = new HashMap<>();
		mapping.put("A", "colA");
		mapping.put("B", "colB");
		csv = new MapToDelimitedTransformer(baos, mapping, logService, logId, ",");
		tsv = new MapToDelimitedTransformer(baos, mapping, logService, logId, "\t");
    }

    @After
    public void closeTest() throws IOException {
    	csv.close();
    	tsv.close();
    }

    @Test
	public void writeDataTest() {
		Map<String, Object> result = new HashMap<>();
    	result.put("A", "1");
    	result.put("B", "2");
    	result.put("C", "3");

		try {
			csv.write(result);
			assertEquals(13, baos.size());
			assertEquals("colA,colB\n1,2", new String(baos.toByteArray(), "UTF-8"));

	    	result.put("A", "11");
	    	result.put("B", "12");
			csv.write(result);
			assertEquals(19, baos.size());
			assertEquals("colA,colB\n1,2\n11,12", new String(baos.toByteArray(), "UTF-8"));
	    	
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}

	}

	@Test
	public void writeDataMapTest() {
		Map<String, Object> result = new HashMap<>();
    	result.put("A", "1\r\n\t");
    	result.put("B", "2");
    	result.put("C", "3");

		try {
			tsv.writeData(result);
			assertEquals(7, baos.size());
			assertEquals("\n1   \t2", new String(baos.toByteArray(), "UTF-8"));

			baos.reset();
			result.clear();
	    	result.put("C", "3");
	    	result.put("A", "1");
	    	result.put("B", "2");
			tsv.writeData(result);
			assertEquals(4, baos.size());
			assertEquals("\n1\t2", new String(baos.toByteArray(), "UTF-8"));
	    	
			baos.reset();
	    	result.put("A", null);
	    	tsv.writeData(result);
			assertEquals(3, baos.size());
			assertEquals("\n\t2", new String(baos.toByteArray(), "UTF-8"));
			tsv.close();
	    	
			
			tsv = new MapToDelimitedTransformer(baos, mapping, logService, logId, ",");
			baos.reset();
			result.clear();
	    	result.put("A", "b,1");
	    	result.put("B", "2\"x\td\"34\"");
	    	tsv.writeData(result);
			assertEquals(21, baos.size());
			assertEquals("\n\"b,1\",\"2\"\"x\td\"\"34\"\"\"", new String(baos.toByteArray(), "UTF-8"));

			baos.reset();
			result.clear();
	    	result.put("A", "\"sdjfhjks\"");
	    	result.put("B", "\"");
	    	tsv.writeData(result);
			assertEquals(20, baos.size());
			assertEquals("\n\"\"\"sdjfhjks\"\"\",\"\"\"\"", new String(baos.toByteArray(), "UTF-8"));

		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}

	}
	
	@Test
	public void writeHeaderTest() {
		Map<String, Object> result = new HashMap<>();
    	result.put("A", "1");
    	result.put("B", "2");
    	result.put("C", "3");

		try {
			csv.writeHeader();
			assertEquals(9, baos.size());
			assertEquals("colA,colB", new String(baos.toByteArray(), "UTF-8"));
	    	
			baos.reset();
			result.clear();
	    	result.put("C", "3");
	    	result.put("A", "1");
	    	result.put("B", "2");
	    	csv.writeHeader();
			assertEquals(9, baos.size());
			assertEquals("colA,colB", new String(baos.toByteArray(), "UTF-8"));

			csv.close();
		} catch (IOException e) {
			fail(e.getLocalizedMessage());
		}

	}
	
}
