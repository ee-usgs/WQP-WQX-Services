package gov.usgs.cida.wqp.mapping.xml;

import static gov.usgs.cida.wqp.mapping.BaseColumn.KEY_ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION;
import static gov.usgs.cida.wqp.mapping.BaseColumn.ORGANIZATION_NAME;
import static gov.usgs.cida.wqp.mapping.OrganizationColumn.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import gov.usgs.cida.wqp.mapping.ColumnProfile;

@Component
public class OrganizationWqx extends BaseWqx implements IXmlMapping {

	public static final Map<String, String> HARD_BREAK = new LinkedHashMap<>();
	public static final Map<String, List<String>> COLUMN_POSITION = new LinkedHashMap<>();
	public static final Map<String, List<ColumnProfile>> GROUPING = new LinkedHashMap<>();

	static {
		HARD_BREAK.put(KEY_ORGANIZATION, ROOT_NODE);
		HARD_BREAK.put(KEY_ADDRESS_TEXT_1, WQX_ORGANIZATION);
		HARD_BREAK.put(KEY_ADDRESS_TEXT_2, WQX_ORGANIZATION);
		HARD_BREAK.put(KEY_ADDRESS_TEXT_3, WQX_ORGANIZATION);
	}

	static {
		COLUMN_POSITION.put(KEY_ORGANIZATION, 
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_ORGANIZATION_IDENTIFIER)));
		COLUMN_POSITION.put(KEY_ORGANIZATION_NAME,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_ORGANIZATION_FORMAL_NAME)));
		COLUMN_POSITION.put(KEY_ORGANIZATION_DESCRIPTION,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_ORGANIZATION_DESCRIPTION_TEXT)));
		COLUMN_POSITION.put(KEY_TRIBAL_CODE,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_DESCRIPTION,
						WQX_TRIBAL_CODE)));
		COLUMN_POSITION.put(KEY_ELECTRONIC_ADDRESS,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ELECTRONIC_ADDRESS)));
		COLUMN_POSITION.put(KEY_TELEPHONIC,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_TELEPHONIC)));

		COLUMN_POSITION.put(KEY_ADDRESS_TYPE_1,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_ADDRESS_TYPE_NAME)));
		COLUMN_POSITION.put(KEY_ADDRESS_TEXT_1,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_ADDRESS_TEXT)));
		COLUMN_POSITION.put(KEY_SUPPLEMENTAL_ADDRESS_TEXT_1,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_SUPPLEMENTAL_ADDRESS_TEXT)));
		COLUMN_POSITION.put(KEY_LOCALITY_NAME_1,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_LOCALITY_NAME)));
		COLUMN_POSITION.put(KEY_STATE_CODE_1,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_STATE_CODE)));
		COLUMN_POSITION.put(KEY_POSTAL_CODE_1,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_POSTAL_CODE)));
		COLUMN_POSITION.put(KEY_COUNTRY_CODE_1,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_COUNTRY_CODE)));
		COLUMN_POSITION.put(KEY_COUNTY_CODE_1,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_COUNTY_CODE)));

		COLUMN_POSITION.put(KEY_ADDRESS_TYPE_2,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_ADDRESS_TYPE_NAME)));
		COLUMN_POSITION.put(KEY_ADDRESS_TEXT_2,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_ADDRESS_TEXT)));
		COLUMN_POSITION.put(KEY_SUPPLEMENTAL_ADDRESS_TEXT_2,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_SUPPLEMENTAL_ADDRESS_TEXT)));
		COLUMN_POSITION.put(KEY_LOCALITY_NAME_2,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_LOCALITY_NAME)));
		COLUMN_POSITION.put(KEY_STATE_CODE_2,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_STATE_CODE)));
		COLUMN_POSITION.put(KEY_POSTAL_CODE_2,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_POSTAL_CODE)));
		COLUMN_POSITION.put(KEY_COUNTRY_CODE_2,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_COUNTRY_CODE)));
		COLUMN_POSITION.put(KEY_COUNTY_CODE_2,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_COUNTY_CODE)));

		COLUMN_POSITION.put(KEY_ADDRESS_TYPE_3,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_ADDRESS_TYPE_NAME)));
		COLUMN_POSITION.put(KEY_ADDRESS_TEXT_3,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_ADDRESS_TEXT)));
		COLUMN_POSITION.put(KEY_SUPPLEMENTAL_ADDRESS_TEXT_3,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_SUPPLEMENTAL_ADDRESS_TEXT)));
		COLUMN_POSITION.put(KEY_LOCALITY_NAME_3,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_LOCALITY_NAME)));
		COLUMN_POSITION.put(KEY_STATE_CODE_3,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_STATE_CODE)));
		COLUMN_POSITION.put(KEY_POSTAL_CODE_3,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_POSTAL_CODE)));
		COLUMN_POSITION.put(KEY_COUNTRY_CODE_3,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_COUNTRY_CODE)));
		COLUMN_POSITION.put(KEY_COUNTY_CODE_3,
				new LinkedList<String>(Arrays.asList(
						WQX_ORGANIZATION,
						WQX_ORGANIZATION_ADDRESS,
						WQX_COUNTY_CODE)));
	}

	static {
		GROUPING.put(KEY_ORGANIZATION,
				new LinkedList<ColumnProfile>(Arrays.asList(
						ORGANIZATION,
						ORGANIZATION_NAME,
						ORGANIZATION_DESCRIPTION,
						TRIBAL_CODE,
						ELECTRONIC_ADDRESS,
						TELEPHONIC
						)));
		GROUPING.put(KEY_ADDRESS_TEXT_1,
				new LinkedList<ColumnProfile>(Arrays.asList(
						ADDRESS_TYPE_1,
						ADDRESS_TEXT_1,
						SUPPLEMENTAL_ADDRESS_TEXT_1,
						LOCALITY_NAME_1,
						STATE_CODE_1,
						POSTAL_CODE_1,
						COUNTRY_CODE_1,
						COUNTY_CODE_1
						)));
		GROUPING.put(KEY_ADDRESS_TEXT_2,
				new LinkedList<ColumnProfile>(Arrays.asList(
						ADDRESS_TYPE_2,
						ADDRESS_TEXT_2,
						SUPPLEMENTAL_ADDRESS_TEXT_2,
						LOCALITY_NAME_2,
						STATE_CODE_2,
						POSTAL_CODE_2,
						COUNTRY_CODE_2,
						COUNTY_CODE_2
						)));
		GROUPING.put(KEY_ADDRESS_TEXT_3,
				new LinkedList<ColumnProfile>(Arrays.asList(
						ADDRESS_TYPE_3,
						ADDRESS_TEXT_3,
						SUPPLEMENTAL_ADDRESS_TEXT_3,
						LOCALITY_NAME_3,
						STATE_CODE_3,
						POSTAL_CODE_3,
						COUNTRY_CODE_3,
						COUNTY_CODE_3
						)));
	}

	public Map<String, List<String>> getStructure() {
		return COLUMN_POSITION;
	}

	public Map<String, String> getHardBreak() {
		return HARD_BREAK;
	}

	public Map<String, List<ColumnProfile>> getGrouping() {
		return GROUPING;
	}

	public String getEntryNodeName() {
		return WQX_PROVIDER;
	}
}
