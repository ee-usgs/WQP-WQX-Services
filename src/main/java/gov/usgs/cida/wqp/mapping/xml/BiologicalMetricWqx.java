package gov.usgs.cida.wqp.mapping.xml;

import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_SITE_ID;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION_NAME;
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
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_CALCULATED_DATE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_COMMENT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_QUALIFIER_CODE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_SCORE_NUMERIC;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_TYPE_CONTEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_TYPE_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_TYPE_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_INDEX_TYPE_SCALE_TEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_CREATOR_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_DATE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_PUBLISHER_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_SUBJECT_TEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.KEY_RESOURCE_TITLE_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_CREATOR_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_DATE;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_PUBLISHER_NAME;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_SUBJECT_TEXT;
import static gov.usgs.cida.wqp.mapping.BiologicalMetricColumn.RESOURCE_TITLE_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.ROOT_NODE;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_MONITORING_LOCATION_IDENTITY;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ORGANIZATION_DESCRIPTION;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ORGANIZATION_FORMAL_NAME;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_ORGANIZATION_IDENTIFIER;
import static gov.usgs.cida.wqp.mapping.xml.BaseWqx.WQX_PROVIDER;
import gov.usgs.cida.wqp.mapping.ColumnProfile;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class BiologicalMetricWqx extends BaseWqx implements IXmlMapping {
	
	public static final Map<String, String> HARD_BREAK = new LinkedHashMap<>();
	public static final Map<String, List<String>> COLUMN_POSITION = new LinkedHashMap<>();	
	public static final Map<String, List<ColumnProfile>> GROUPING = new LinkedHashMap<>();
	static {
		HARD_BREAK.put(KEY_ORGANIZATION, ROOT_NODE);
		HARD_BREAK.put(KEY_SITE_ID, WQX_ORGANIZATION);
	}

	static {
		COLUMN_POSITION.put(KEY_ORGANIZATION,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_ORGANIZATION_IDENTIFIER)));
		COLUMN_POSITION.put(KEY_ORGANIZATION_NAME,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_ORGANIZATION_FORMAL_NAME)));
		
		COLUMN_POSITION.put(KEY_SITE_ID,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_MONITORING_LOCATION,
						WQX_MONITORING_LOCATION_IDENTITY,
						WQX_MONITORING_LOCATION_IDENTIFIER)));
		
		COLUMN_POSITION.put(KEY_INDEX_IDENTIFIER,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,						
						WQX_INDEX_ID)));
				
		COLUMN_POSITION.put(KEY_INDEX_TYPE_IDENTIFIER,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,						
						WQX_INDEX_TYPE_IDENTIFER)));
		
		COLUMN_POSITION.put(KEY_INDEX_TYPE_CONTEXT,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,						
						WQX_INDEX_TYPE_IDENTIFER_CONTEXT)));
		
		COLUMN_POSITION.put(KEY_INDEX_TYPE_NAME,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,						
						WQX_INDEX_TYPE_NAME)));
		
		COLUMN_POSITION.put(KEY_RESOURCE_TITLE_NAME,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,	
						WQX_INDEX_CITATION,						
						WQX_RESOURCE_TITLE)));
		
		COLUMN_POSITION.put(KEY_RESOURCE_CREATOR_NAME,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,	
						WQX_INDEX_CITATION,						
						WQX_RESOURCE_CREATOR)));
		
		COLUMN_POSITION.put(KEY_RESOURCE_SUBJECT_TEXT,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,	
						WQX_INDEX_CITATION,						
						WQX_RESOURCE_SUBJECT)));
		
		COLUMN_POSITION.put(KEY_RESOURCE_PUBLISHER_NAME,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,							
						WQX_INDEX_CITATION,						
						WQX_RESOURCE_PUBLISHER)));
		
		COLUMN_POSITION.put(KEY_RESOURCE_DATE,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,							
						WQX_INDEX_CITATION,						
						WQX_RESOURCE_DATE)));
		
		COLUMN_POSITION.put(KEY_RESOURCE_IDENTIFIER,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,							
						WQX_INDEX_CITATION,						
						WQX_RESOURCE_ID)));
		
		COLUMN_POSITION.put(KEY_INDEX_TYPE_SCALE_TEXT,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,							
						WQX_INDEX_CITATION,						
						WQX_INDEX_TYPE_SCALE_TEXT)));
		
		COLUMN_POSITION.put(KEY_INDEX_SCORE_NUMERIC,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,							
						WQX_INDEX_CITATION,						
						WQX_INDEX_SCORE_NUMERIC)));
		
		COLUMN_POSITION.put(KEY_INDEX_QUALIFIER_CODE,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,							
						WQX_INDEX_CITATION,						
						WQX_INDEX_QUALIFIER_CODE)));
		
		COLUMN_POSITION.put(KEY_INDEX_COMMENT,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,	
						WQX_INDEX_CITATION,						
						WQX_INDEX_COMMENT_TEXT)));
		
		COLUMN_POSITION.put(KEY_INDEX_CALCULATED_DATE,
				new LinkedList<>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_BIOLOGICAL_HABITAT_INDEX,
						WQX_INDEX_TYPE,	
						WQX_INDEX_CITATION,						
						WQX_INDEX_CALCULATED_DATE)));		
	}	

	static {
		GROUPING.put(KEY_ORGANIZATION,
				new LinkedList<>(Arrays.asList(
						ORGANIZATION,
						ORGANIZATION_NAME)));
		
		GROUPING.put(KEY_SITE_ID,
				new LinkedList<>(Arrays.asList(
						SITE_ID,
						INDEX_IDENTIFIER,
						INDEX_TYPE_IDENTIFIER,
						INDEX_TYPE_CONTEXT,
						INDEX_TYPE_NAME,
						RESOURCE_TITLE_NAME,
						RESOURCE_CREATOR_NAME,
						RESOURCE_SUBJECT_TEXT,
						RESOURCE_PUBLISHER_NAME,
						RESOURCE_DATE,
						RESOURCE_IDENTIFIER,
						INDEX_TYPE_SCALE_TEXT,
						INDEX_SCORE_NUMERIC,
						INDEX_QUALIFIER_CODE,
						INDEX_COMMENT,
						INDEX_CALCULATED_DATE
				)));	
	}
	
	@Override
	public String getEntryNodeName() {
		return WQX_PROVIDER;
	}

	@Override
	public Map<String, List<String>> getStructure() {
		return COLUMN_POSITION;
	}

	@Override
	public Map<String, String> getHardBreak() {
		return HARD_BREAK;
	}

	@Override
	public Map<String, List<ColumnProfile>> getGrouping() {
		return GROUPING;
	}

}
