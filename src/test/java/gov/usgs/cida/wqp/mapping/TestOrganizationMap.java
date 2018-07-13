package gov.usgs.cida.wqp.mapping;

import static gov.usgs.cida.wqp.mapping.OrganizationColumn.*;
import static gov.usgs.cida.wqp.swagger.model.StationCountJson.STORET;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestOrganizationMap {

	public static final Map<String, Object> ORGANIZATION;
	static {
		ORGANIZATION = new LinkedHashMap<String, Object>();
		ORGANIZATION.put(KEY_DATA_SOURCE_ID, BigDecimal.valueOf(3));
		ORGANIZATION.put(KEY_DATA_SOURCE, STORET);
		ORGANIZATION.put(KEY_ORGANIZATION, "organization");
		ORGANIZATION.put(KEY_ORGANIZATION_NAME, "organization formal name");
		ORGANIZATION.put(KEY_ORGANIZATION_ID, BigDecimal.valueOf(1));
		ORGANIZATION.put(KEY_ORGANIZATION_DESCRIPTION, "organization description");
		ORGANIZATION.put(KEY_TRIBAL_CODE, "tribe name");
		ORGANIZATION.put(KEY_ELECTRONIC_ADDRESS, "org@epa.gov (Email); https://epa.gov/org (Internet)");
		ORGANIZATION.put(KEY_TELEPHONIC, "712-555-1234 x1234 (Office); 712-555-5432 (Fax)");
		ORGANIZATION.put(KEY_ADDRESS_TYPE_1, "Location");
		ORGANIZATION.put(KEY_ADDRESS_TEXT_1, "123 First Ave");
		ORGANIZATION.put(KEY_SUPPLEMENTAL_ADDRESS_TEXT_1, "Suite 1848");
		ORGANIZATION.put(KEY_LOCALITY_NAME_1, "Anytown");
		ORGANIZATION.put(KEY_POSTAL_CODE_1, "12345-1234");
		ORGANIZATION.put(KEY_COUNTRY_CODE_1, "US");
		ORGANIZATION.put(KEY_STATE_CODE_1, "WI");
		ORGANIZATION.put(KEY_COUNTY_CODE_1, "027");
		ORGANIZATION.put(KEY_ADDRESS_TYPE_2, "Mailing");
		ORGANIZATION.put(KEY_ADDRESS_TEXT_2, "P.O. Box 111");
		ORGANIZATION.put(KEY_SUPPLEMENTAL_ADDRESS_TEXT_2, "109 First Ave");
		ORGANIZATION.put(KEY_LOCALITY_NAME_2, "Nothere");
		ORGANIZATION.put(KEY_POSTAL_CODE_2, "54321-4321");
		ORGANIZATION.put(KEY_COUNTRY_CODE_2, "US");
		ORGANIZATION.put(KEY_STATE_CODE_2, "WI");
		ORGANIZATION.put(KEY_COUNTY_CODE_2, "025");
		ORGANIZATION.put(KEY_ADDRESS_TYPE_3, "Shipping");
		ORGANIZATION.put(KEY_ADDRESS_TEXT_3, "110 Second St");
		ORGANIZATION.put(KEY_SUPPLEMENTAL_ADDRESS_TEXT_3, "Dock 3B");
		ORGANIZATION.put(KEY_LOCALITY_NAME_3, "Truckville");
		ORGANIZATION.put(KEY_POSTAL_CODE_3, "43210-2314");
		ORGANIZATION.put(KEY_COUNTRY_CODE_3, "US");
		ORGANIZATION.put(KEY_STATE_CODE_3, "WI");
		ORGANIZATION.put(KEY_COUNTY_CODE_3, "015");
		ORGANIZATION.put(KEY_ORGANIZATION_TYPE, "organization type");
	}

	private TestOrganizationMap() {
	}
}
