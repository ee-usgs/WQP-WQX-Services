package gov.usgs.cida.wqp.parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class BoundingBoxTest {

	@Test
	public void nullConstructor() {
		BoundingBox x = new BoundingBox(null);
		assertNull(x.getNorth());
		assertNull(x.getSouth());
		assertNull(x.getSingle());
		assertNull(x.toString());
	}

	@Test
	public void emptyStringConstructor() {
		BoundingBox x = new BoundingBox("");
		assertNull(x.getNorth());
		assertNull(x.getSouth());
		assertNull(x.getEast());
		assertNull(x.getWest());
		assertEquals("", x.getSingle());
		assertEquals("", x.toString());
	}

	@Test
	public void tooFewConstructor() {
		BoundingBox x = new BoundingBox("abc");
		assertNull(x.getNorth());
		assertNull(x.getSouth());
		assertNull(x.getEast());
		assertNull(x.getWest());
		assertEquals("abc", x.getSingle());
		assertEquals("abc", x.toString());
	}

	@Test
	public void happyPathConstructor() {
		BoundingBox x = new BoundingBox("4,3,2,1");
		assertEquals("1", x.getNorth());
		assertEquals("3", x.getSouth());
		assertEquals("2", x.getEast());
		assertEquals("4", x.getWest());
		assertEquals("4,3,2,1", x.getSingle());
		assertEquals("4,3,2,1", x.toString());
	}

	@Test
	public void tooManyConstructor() {
		BoundingBox x = new BoundingBox("4,3,2,1,5");
		assertNull(x.getNorth());
		assertNull(x.getSouth());
		assertNull(x.getEast());
		assertNull(x.getWest());
		assertEquals("4,3,2,1,5", x.getSingle());
		assertEquals("4,3,2,1,5", x.toString());
	}

}
