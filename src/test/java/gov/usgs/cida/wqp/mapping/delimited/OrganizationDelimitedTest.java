package gov.usgs.cida.wqp.mapping.delimited;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import gov.usgs.cida.wqp.mapping.OrganizationColumn;
import gov.usgs.cida.wqp.mapping.Profile;

public class OrganizationDelimitedTest {

	@Test
	public void organizationProfileTest() {
		assertOrganizationProfile(OrganizationDelimited.getMapping(Profile.ORGANIZATION));
	}

	public static void assertOrganizationProfile(Map<String, String> mapping) {
		assertEquals(32, mapping.size());
		Object[] keys = mapping.keySet().toArray();
		assertEquals(OrganizationColumn.KEY_ORGANIZATION, keys[0]);
		assertEquals(OrganizationDelimited.VALUE_ORGANIZATION_IDENTIFIER, mapping.get(keys[0]));
		assertEquals(OrganizationColumn.KEY_ORGANIZATION_NAME, keys[1]);
		assertEquals(OrganizationDelimited.VALUE_ORGANIZATION_FORMAL_NAME, mapping.get(keys[1]));
		assertEquals(OrganizationColumn.KEY_ORGANIZATION_DESCRIPTION, keys[2]);
		assertEquals(OrganizationDelimited.VALUE_ORGANIZATION_DESCRIPTION_TEXT, mapping.get(keys[2]));
		assertEquals(OrganizationColumn.KEY_ORGANIZATION_TYPE, keys[3]);
		assertEquals(OrganizationDelimited.VALUE_ORGANIZATION_TYPE, mapping.get(keys[3]));
		assertEquals(OrganizationColumn.KEY_TRIBAL_CODE, keys[4]);
		assertEquals(OrganizationDelimited.VALUE_TRIBAL_CODE, mapping.get(keys[4]));
		assertEquals(OrganizationColumn.KEY_ELECTRONIC_ADDRESS, keys[5]);
		assertEquals(OrganizationDelimited.VALUE_ELECTRONIC_ADDRESS, mapping.get(keys[5]));
		assertEquals(OrganizationColumn.KEY_TELEPHONIC, keys[6]);
		assertEquals(OrganizationDelimited.VALUE_TELEPHONIC, mapping.get(keys[6]));

		assertEquals(OrganizationColumn.KEY_ADDRESS_TYPE_1, keys[7]);
		assertEquals(OrganizationDelimited.VALUE_ADDRESS_TYPE_NAME_1, mapping.get(keys[7]));
		assertEquals(OrganizationColumn.KEY_ADDRESS_TEXT_1, keys[8]);
		assertEquals(OrganizationDelimited.VALUE_ADDRESS_TEXT_1, mapping.get(keys[8]));
		assertEquals(OrganizationColumn.KEY_SUPPLEMENTAL_ADDRESS_TEXT_1, keys[9]);
		assertEquals(OrganizationDelimited.VALUE_SUPPLEMENTAL_ADDRESS_TEXT_1, mapping.get(keys[9]));
		assertEquals(OrganizationColumn.KEY_LOCALITY_NAME_1, keys[10]);
		assertEquals(OrganizationDelimited.VALUE_LOCALITY_NAME_1, mapping.get(keys[10]));
		assertEquals(OrganizationColumn.KEY_STATE_CODE_1, keys[11]);
		assertEquals(OrganizationDelimited.VALUE_STATE_CODE_1, mapping.get(keys[11]));
		assertEquals(OrganizationColumn.KEY_POSTAL_CODE_1, keys[12]);
		assertEquals(OrganizationDelimited.VALUE_POSTAL_CODE_1, mapping.get(keys[12]));
		assertEquals(OrganizationColumn.KEY_COUNTRY_CODE_1, keys[13]);
		assertEquals(OrganizationDelimited.VALUE_COUNTRY_CODE_1, mapping.get(keys[13]));
		assertEquals(OrganizationColumn.KEY_COUNTY_CODE_1, keys[14]);
		assertEquals(OrganizationDelimited.VALUE_COUNTY_CODE_1, mapping.get(keys[14]));

		assertEquals(OrganizationColumn.KEY_ADDRESS_TYPE_2, keys[15]);
		assertEquals(OrganizationDelimited.VALUE_ADDRESS_TYPE_NAME_2, mapping.get(keys[15]));
		assertEquals(OrganizationColumn.KEY_ADDRESS_TEXT_2, keys[16]);
		assertEquals(OrganizationDelimited.VALUE_ADDRESS_TEXT_2, mapping.get(keys[16]));
		assertEquals(OrganizationColumn.KEY_SUPPLEMENTAL_ADDRESS_TEXT_2, keys[17]);
		assertEquals(OrganizationDelimited.VALUE_SUPPLEMENTAL_ADDRESS_TEXT_2, mapping.get(keys[17]));
		assertEquals(OrganizationColumn.KEY_LOCALITY_NAME_2, keys[18]);
		assertEquals(OrganizationDelimited.VALUE_LOCALITY_NAME_2, mapping.get(keys[18]));
		assertEquals(OrganizationColumn.KEY_STATE_CODE_2, keys[19]);
		assertEquals(OrganizationDelimited.VALUE_STATE_CODE_2, mapping.get(keys[19]));
		assertEquals(OrganizationColumn.KEY_POSTAL_CODE_2, keys[20]);
		assertEquals(OrganizationDelimited.VALUE_POSTAL_CODE_2, mapping.get(keys[20]));
		assertEquals(OrganizationColumn.KEY_COUNTRY_CODE_2, keys[21]);
		assertEquals(OrganizationDelimited.VALUE_COUNTRY_CODE_2, mapping.get(keys[21]));
		assertEquals(OrganizationColumn.KEY_COUNTY_CODE_2, keys[22]);
		assertEquals(OrganizationDelimited.VALUE_COUNTY_CODE_2, mapping.get(keys[22]));

		assertEquals(OrganizationColumn.KEY_ADDRESS_TYPE_3, keys[23]);
		assertEquals(OrganizationDelimited.VALUE_ADDRESS_TYPE_NAME_3, mapping.get(keys[23]));
		assertEquals(OrganizationColumn.KEY_ADDRESS_TEXT_3, keys[24]);
		assertEquals(OrganizationDelimited.VALUE_ADDRESS_TEXT_3, mapping.get(keys[24]));
		assertEquals(OrganizationColumn.KEY_SUPPLEMENTAL_ADDRESS_TEXT_3, keys[25]);
		assertEquals(OrganizationDelimited.VALUE_SUPPLEMENTAL_ADDRESS_TEXT_3, mapping.get(keys[25]));
		assertEquals(OrganizationColumn.KEY_LOCALITY_NAME_3, keys[26]);
		assertEquals(OrganizationDelimited.VALUE_LOCALITY_NAME_3, mapping.get(keys[26]));
		assertEquals(OrganizationColumn.KEY_STATE_CODE_3, keys[27]);
		assertEquals(OrganizationDelimited.VALUE_STATE_CODE_3, mapping.get(keys[27]));
		assertEquals(OrganizationColumn.KEY_POSTAL_CODE_3, keys[28]);
		assertEquals(OrganizationDelimited.VALUE_POSTAL_CODE_3, mapping.get(keys[28]));
		assertEquals(OrganizationColumn.KEY_COUNTRY_CODE_3, keys[29]);
		assertEquals(OrganizationDelimited.VALUE_COUNTRY_CODE_3, mapping.get(keys[29]));
		assertEquals(OrganizationColumn.KEY_COUNTY_CODE_3, keys[30]);
		assertEquals(OrganizationDelimited.VALUE_COUNTY_CODE_3, mapping.get(keys[30]));

		assertEquals(OrganizationColumn.KEY_DATA_SOURCE, keys[31]);
		assertEquals(OrganizationDelimited.VALUE_DATA_SOURCE, mapping.get(keys[31]));
	}

}
