
package gov.usgs.cida.wqp.mapping.delimited;


import static gov.usgs.cida.wqp.mapping.BaseColumn.DATA_SOURCE;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.SITE_ID;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.INDEX_CALCULATED_DATE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.INDEX_COMMENT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.INDEX_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.INDEX_QUALIFIER_CODE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.INDEX_SCORE_NUMERIC;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.INDEX_TYPE_CONTEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.INDEX_TYPE_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.INDEX_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.INDEX_TYPE_SCALE_TEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_CREATOR_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_DATE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_PUBLISHER_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_SUBJECT_TEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_TITLE_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_INDEX_CALCULATED_DATE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_INDEX_COMMENT_TEXT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_INDEX_ID;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_INDEX_QUALIFIER_CODE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_INDEX_SCORE_NUMERIC;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_INDEX_TYPE_IDENTIFER;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_INDEX_TYPE_IDENTIFER_CONTEXT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_INDEX_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_INDEX_TYPE_SCALE_TEXT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_PROVIDER_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_CREATOR;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_DATE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_ID;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_PUBLISHER;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_SUBJECT;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_RESOURCE_TITLE;
import gov.usgs.cida.wqp.mapping.ColumnProfile;
import gov.usgs.cida.wqp.mapping.Profile;
import java.util.LinkedHashMap;
import java.util.Map;


public class BiologicalMetricDelimited extends BaseDelimited {
	
	//Column Headings for the Keys
	public static final String VALUE_INDEX_IDENTIFIER = WQX_INDEX_ID;
	public static final String VALUE_INDEX_TYPE_IDENTIFIER = WQX_INDEX_TYPE_IDENTIFER;
	public static final String VALUE_INDEX_TYPE_IDENTIFER_CONTEXT = WQX_INDEX_TYPE_IDENTIFER_CONTEXT;		
	public static final String VALUE_INDEX_TYPE_NAME = WQX_INDEX_TYPE_NAME;
	public static final String VALUE_RESOURCE_TITLE = WQX_RESOURCE_TITLE;
	public static final String VALUE_RESOURCE_CREATOR = WQX_RESOURCE_CREATOR;
	public static final String VALUE_RESOURCE_SUBJECT = WQX_RESOURCE_SUBJECT;		
	public static final String VALUE_RESOURCE_PUBLISHER = WQX_RESOURCE_PUBLISHER;		
	public static final String VALUE_RESOURCE_DATE = WQX_RESOURCE_DATE;		
	public static final String VALUE_RESOURCE_ID = WQX_RESOURCE_ID;		
	public static final String VALUE_INDEX_TYPE_SCALE_TEXT = WQX_INDEX_TYPE_SCALE_TEXT;		
	public static final String VALUE_INDEX_SCORE_NUMERIC = WQX_INDEX_SCORE_NUMERIC;	
	public static final String VALUE_INDEX_QUALIFIER_CODE = WQX_INDEX_QUALIFIER_CODE;		
	public static final String VALUE_INDEX_COMMENT_TEXT = WQX_INDEX_COMMENT_TEXT;	
	public static final String VALUE_INDEX_CALCULATED_DATE = WQX_INDEX_CALCULATED_DATE;		
	public static final String VALUE_PROVIDER_NAME = WQX_PROVIDER_NAME;		
	
	
	
	public static final Map<ColumnProfile, String> MAPPINGS;
	static {
		MAPPINGS = new LinkedHashMap<>();
		MAPPINGS.put(ORGANIZATION, VALUE_ORGANIZATION_IDENTIFIER);
		MAPPINGS.put(SITE_ID, VALUE_MONITORING_LOCATION_IDENTIFIER);
		MAPPINGS.put(INDEX_IDENTIFIER, VALUE_INDEX_IDENTIFIER);
		MAPPINGS.put(INDEX_TYPE_IDENTIFIER, VALUE_INDEX_TYPE_IDENTIFIER);
		MAPPINGS.put(INDEX_TYPE_CONTEXT, VALUE_INDEX_TYPE_IDENTIFER_CONTEXT);
		MAPPINGS.put(INDEX_TYPE_NAME, VALUE_INDEX_TYPE_NAME);
		MAPPINGS.put(RESOURCE_TITLE_NAME, VALUE_RESOURCE_TITLE);
		MAPPINGS.put(RESOURCE_CREATOR_NAME, VALUE_RESOURCE_CREATOR);
		MAPPINGS.put(RESOURCE_SUBJECT_TEXT, VALUE_RESOURCE_SUBJECT);
		MAPPINGS.put(RESOURCE_PUBLISHER_NAME, VALUE_RESOURCE_PUBLISHER);
		MAPPINGS.put(RESOURCE_DATE, VALUE_RESOURCE_DATE);
		MAPPINGS.put(RESOURCE_IDENTIFIER, VALUE_RESOURCE_ID);
		MAPPINGS.put(INDEX_TYPE_SCALE_TEXT, VALUE_INDEX_TYPE_SCALE_TEXT);
		MAPPINGS.put(INDEX_SCORE_NUMERIC, VALUE_INDEX_SCORE_NUMERIC);
		MAPPINGS.put(INDEX_QUALIFIER_CODE, VALUE_INDEX_QUALIFIER_CODE);
		MAPPINGS.put(INDEX_COMMENT, VALUE_INDEX_COMMENT_TEXT);
		MAPPINGS.put(INDEX_CALCULATED_DATE, VALUE_INDEX_CALCULATED_DATE);
		MAPPINGS.put(DATA_SOURCE, VALUE_PROVIDER_NAME);
	}
	
	private BiologicalMetricDelimited() {
	}
	
	public static Map<String, String> getMapping(Profile profile) {
		return getMapping(MAPPINGS, profile);
	}
	
}
