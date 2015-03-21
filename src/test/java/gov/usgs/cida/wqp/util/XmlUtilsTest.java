package gov.usgs.cida.wqp.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class XmlUtilsTest implements XmlConstants {

	@Test
	public void testXmlQuickSanitize() {
		assertEquals("legitimate", XmlUtils.xmlQuickSanitize("legitimate"));
		assertEquals("quickSanitize fails to catch (","legit-imat_(e.ly", XmlUtils.xmlQuickSanitize("legit-imat_(e.ly"));
		assertEquals("legitimate", XmlUtils.xmlQuickSanitize("legitimate"));
		assertEquals("quickSanitize fails to catch (", "count(*)", XmlUtils.xmlQuickSanitize("count(*)"));
		assertEquals("remove slash /", "removed_slash_from_path", XmlUtils.xmlQuickSanitize("removed/slash/from/path"));
		assertEquals("remove slash @", "Removed_At_", XmlUtils.xmlQuickSanitize("Removed@At@"));
	}

	@Test
	public void testXmlFullSanitize() {
		assertEquals("legitimate", XmlUtils.xmlFullSanitize("legitimate"));
		assertEquals("legit-imat_e.ly", XmlUtils.xmlFullSanitize("legit-imat(e.ly"));
		assertEquals("legitimate", XmlUtils.xmlFullSanitize("legitimate"));
		assertEquals("count__", XmlUtils.xmlFullSanitize("count(*)"));
	}

	@Test
	public void testUnescape() {
		String original = "123&lt;&gt;&quot;&apos;&amp;";
		assertEquals("123<>\"'&", XmlUtils.unEscapeXMLEntities(original));
	}
	
	@Test
	public void testUnescape_not() {
		String original = "123";
		assertEquals(original, XmlUtils.unEscapeXMLEntities(original));
	}
	
	
	@Test
	public void testEscapeBrackets() {
		assertEquals("excape xml tag brackets", "&lt;tag&gt;text&lt;/tag&gt;", XmlUtils.escapeAngleBrackets("<tag>text</tag>"));
	}

	
	@Test
	public void testQuickTagContentEscape() {
		assertEquals("?this=that&amp;that=this&amp;other",XmlUtils.quickTagContentEscape("?this=that&that=this&other"));
	}
	@Test
	public void testQuickTagContentEscape_not() {
		String xml = "?this=that&that=this&other&amp;";
		assertEquals(xml,XmlUtils.quickTagContentEscape(xml));
		
		xml = "?this=that&that=this&other&quot;";
		assertEquals(xml,XmlUtils.quickTagContentEscape(xml));
		
		xml = "?this=that&that=this&other&lt;";
		assertEquals(xml,XmlUtils.quickTagContentEscape(xml));
		
		xml = "?this=that&that=this&other&gt;";
		assertEquals(xml,XmlUtils.quickTagContentEscape(xml));
		
		xml = "?this=that&that=this&other&apos;";
		assertEquals(xml,XmlUtils.quickTagContentEscape(xml));
		
		xml = "?this=that=this";
		assertEquals(xml,XmlUtils.quickTagContentEscape(xml));
	}

}
