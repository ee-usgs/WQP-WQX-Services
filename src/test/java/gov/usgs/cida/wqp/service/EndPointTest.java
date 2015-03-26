package gov.usgs.cida.wqp.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class EndPointTest {

	@Test
	public void testGetEnumByCode_null() {
		assertEquals(null, EndPoint.getEnumByCode(null));
		assertEquals(null, EndPoint.getEnumByCode(""));
	}

	@Test
	public void testGetEnumByCode() {
		assertEquals(EndPoint.STATION, EndPoint.getEnumByCode("wqp/"+EndPoint.STATION.URI));
	}

	@Test
	public void testGetEnumByCode_slashStringBoundsTest() {
		assertEquals(null, EndPoint.getEnumByCode("tooFewSlashesBoundsTestString"));
		assertEquals(null, EndPoint.getEnumByCode("tooFewSlashesBounds/TestString"));
		assertEquals(null, EndPoint.getEnumByCode("tooFewSlashesBoundsTestString/"));
		assertEquals(null, EndPoint.getEnumByCode("http://sdfsdf/sdfs/sdfsdfsdf"));
		assertEquals(null, EndPoint.getEnumByCode(""));
		assertEquals(null, EndPoint.getEnumByCode(" "));
	}

}
