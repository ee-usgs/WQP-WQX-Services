package gov.usgs.cida.wqp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterSeparatedValue {
	
	public static final Pattern QUOTE = Pattern.compile("\"");
	public static final String  COMMA = ",";
	public static final String  TAB = "\t";
	public static final String  NEW_LINE = "\n";
	public static final String  NEW_LINE_CR = "\r\n";
	
	public static final CharacterSeparatedValue CSV = new CharacterSeparatedValue(COMMA);
	public static final CharacterSeparatedValue TSV = new CharacterSeparatedValue(TAB);

	private final String valueSeparator;
	private final String entrySeparator;
	
	public CharacterSeparatedValue() {
		this(COMMA, NEW_LINE);
	}
	
	public CharacterSeparatedValue(String valueSeparator) {
		this(valueSeparator, NEW_LINE);
	}
	
	public CharacterSeparatedValue(String valueSeparator, String entrySeparator) {
		if (valueSeparator == null || entrySeparator == null) {
			throw new RuntimeException("Separators must provided for " + getClass());
		}
		if (valueSeparator == null || entrySeparator == null) { // TODO this should check for blank
			throw new RuntimeException("Separators must provided for " + getClass());
		}
		this.valueSeparator = valueSeparator;
		this.entrySeparator = entrySeparator;
	}
	
	public String encode(final String value) {
		if (value == null) {
			return "";
		}
		String processing = value;
		processing = XmlUtils.unEscapeXMLEntities(processing);
		processing = processing.replaceAll("[\n\r\t]", ""); // Handles newlines and carriage returns, and tabs
		if (COMMA.equals(valueSeparator)) { // Currently handles commas and quotes.
			boolean hasQuotes = processing.indexOf('"') >= 0;
			if (hasQuotes) {
				Matcher matcher = QUOTE.matcher(processing);
				processing = matcher.replaceAll("\"\""); // escape quotes by doubling them
			}
			boolean encloseInQuotes = hasQuotes || processing.indexOf(',')>=0;
			processing = (encloseInQuotes)  ?'"'+processing+'"'  :processing;
		}
		return processing;
	}
	
	public String getValueSeparator() {
		return valueSeparator;
	}
	public String getEntrySeparator() {
		return entrySeparator;
	}
}