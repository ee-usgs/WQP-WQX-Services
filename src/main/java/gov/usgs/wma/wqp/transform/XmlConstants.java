package gov.usgs.wma.wqp.transform;

import java.util.regex.Pattern;

public class XmlConstants {

	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	public static final String BEGIN_OPEN_TAG = "<";
	public static final String CLOSE_TAG = ">";
	public static final String BEGIN_END_TAG = "</";
	public static final String CLOSE_EMPTY_TAG = "/>";

	public static final String  REGEX_NON_QNAME_SEPARATORS = "[()/@]";
	public static final String  REGEX_NON_QNAME_CHARS = "[^-_.:0-9A-Za-z]";
	public static final String  REGEX_ALL_NON_QNAME_CHARS = REGEX_NON_QNAME_SEPARATORS + REGEX_NON_QNAME_CHARS;
	public static final Pattern NON_QNAME_SEPARATORS = Pattern.compile(REGEX_NON_QNAME_SEPARATORS);
	public static final Pattern NON_QNAME_CHARS = Pattern.compile(REGEX_NON_QNAME_CHARS);
	public static final Pattern LEFT_ANGLE_BRACKET = Pattern.compile("<");
	public static final Pattern RIGHT_ANGLE_BRACKET = Pattern.compile(">");
	public static final Pattern AMPERSAND = Pattern.compile("&");

	public static final Pattern LESS_THAN = Pattern.compile("&lt;");
	public static final Pattern GREATER_THAN = Pattern.compile("&gt;");
	public static final Pattern QUOTE = Pattern.compile("&quot;");
	public static final Pattern APOS = Pattern.compile("&apos;");
	public static final Pattern AMP = Pattern.compile("&amp;");

	private XmlConstants() {
	}

}