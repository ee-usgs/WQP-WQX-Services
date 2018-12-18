package gov.usgs.cida.wqp.mapping;

public abstract class OrganizationColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_ORGANIZATION_ID = "organization_id";
	public static final String KEY_ORGANIZATION_DESCRIPTION = "organization_description";
	public static final String KEY_ORGANIZATION_TYPE = "organization_type";
	public static final String KEY_TRIBAL_CODE = "tribal_code";
	public static final String KEY_ELECTRONIC_ADDRESS = "electronic_address";
	public static final String KEY_TELEPHONIC = "telephonic";
	public static final String KEY_ADDRESS_TYPE_1 = "address_type_1";
	public static final String KEY_ADDRESS_TEXT_1 = "address_text_1";
	public static final String KEY_SUPPLEMENTAL_ADDRESS_TEXT_1 = "supplemental_address_text_1";
	public static final String KEY_LOCALITY_NAME_1 = "locality_name_1";
	public static final String KEY_POSTAL_CODE_1 = "postal_code_1";
	public static final String KEY_COUNTRY_CODE_1 = "country_code_1";
	public static final String KEY_STATE_CODE_1 = "state_code_1";
	public static final String KEY_COUNTY_CODE_1 = "county_code_1";
	public static final String KEY_ADDRESS_TYPE_2 = "address_type_2";
	public static final String KEY_ADDRESS_TEXT_2 = "address_text_2";
	public static final String KEY_SUPPLEMENTAL_ADDRESS_TEXT_2 = "supplemental_address_text_2";
	public static final String KEY_LOCALITY_NAME_2 = "locality_name_2";
	public static final String KEY_POSTAL_CODE_2 = "postal_code_2";
	public static final String KEY_COUNTRY_CODE_2 = "country_code_2";
	public static final String KEY_STATE_CODE_2 = "state_code_2";
	public static final String KEY_COUNTY_CODE_2 = "county_code_2";
	public static final String KEY_ADDRESS_TYPE_3 = "address_type_3";
	public static final String KEY_ADDRESS_TEXT_3 = "address_text_3";
	public static final String KEY_SUPPLEMENTAL_ADDRESS_TEXT_3 = "supplemental_address_text_3";
	public static final String KEY_LOCALITY_NAME_3 = "locality_name_3";
	public static final String KEY_POSTAL_CODE_3 = "postal_code_3";
	public static final String KEY_COUNTRY_CODE_3 = "country_code_3";
	public static final String KEY_STATE_CODE_3 = "state_code_3";
	public static final String KEY_COUNTY_CODE_3 = "county_code_3";
	public static final String KEY_ORGANIZATION_SUMMARY_WQP_URL = "organization_wqp_url";
	public static final String KEY_ORGANIZATION_SUMMARY = "organization_summary";

	//Profile Mapping of the Keys
	public static final ColumnProfile ORGANIZATION_DESCRIPTION = new ColumnProfile(KEY_ORGANIZATION_DESCRIPTION, Profile.ORGANIZATION);

	public static final ColumnProfile ORGANIZATION_TYPE = new ColumnProfile(KEY_ORGANIZATION_TYPE, Profile.ORGANIZATION);

	public static final ColumnProfile TRIBAL_CODE = new ColumnProfile(KEY_TRIBAL_CODE, Profile.ORGANIZATION);

	public static final ColumnProfile ELECTRONIC_ADDRESS = new ColumnProfile(KEY_ELECTRONIC_ADDRESS, Profile.ORGANIZATION);

	public static final ColumnProfile TELEPHONIC = new ColumnProfile(KEY_TELEPHONIC, Profile.ORGANIZATION);

	public static final ColumnProfile ADDRESS_TYPE_1 = new ColumnProfile(KEY_ADDRESS_TYPE_1, Profile.ORGANIZATION);

	public static final ColumnProfile ADDRESS_TEXT_1 = new ColumnProfile(KEY_ADDRESS_TEXT_1, Profile.ORGANIZATION);

	public static final ColumnProfile SUPPLEMENTAL_ADDRESS_TEXT_1 = new ColumnProfile(KEY_SUPPLEMENTAL_ADDRESS_TEXT_1, Profile.ORGANIZATION);

	public static final ColumnProfile LOCALITY_NAME_1 = new ColumnProfile(KEY_LOCALITY_NAME_1, Profile.ORGANIZATION);

	public static final ColumnProfile POSTAL_CODE_1 = new ColumnProfile(KEY_POSTAL_CODE_1, Profile.ORGANIZATION);

	public static final ColumnProfile COUNTRY_CODE_1 = new ColumnProfile(KEY_COUNTRY_CODE_1, Profile.ORGANIZATION);

	public static final ColumnProfile STATE_CODE_1 = new ColumnProfile(KEY_STATE_CODE_1, Profile.ORGANIZATION);

	public static final ColumnProfile COUNTY_CODE_1 = new ColumnProfile(KEY_COUNTY_CODE_1, Profile.ORGANIZATION);

	public static final ColumnProfile ADDRESS_TYPE_2 = new ColumnProfile(KEY_ADDRESS_TYPE_2, Profile.ORGANIZATION);

	public static final ColumnProfile ADDRESS_TEXT_2 = new ColumnProfile(KEY_ADDRESS_TEXT_2, Profile.ORGANIZATION);

	public static final ColumnProfile SUPPLEMENTAL_ADDRESS_TEXT_2 = new ColumnProfile(KEY_SUPPLEMENTAL_ADDRESS_TEXT_2, Profile.ORGANIZATION);

	public static final ColumnProfile LOCALITY_NAME_2 = new ColumnProfile(KEY_LOCALITY_NAME_2, Profile.ORGANIZATION);

	public static final ColumnProfile POSTAL_CODE_2 = new ColumnProfile(KEY_POSTAL_CODE_2, Profile.ORGANIZATION);

	public static final ColumnProfile COUNTRY_CODE_2 = new ColumnProfile(KEY_COUNTRY_CODE_2, Profile.ORGANIZATION);

	public static final ColumnProfile STATE_CODE_2 = new ColumnProfile(KEY_STATE_CODE_2, Profile.ORGANIZATION);

	public static final ColumnProfile COUNTY_CODE_2 = new ColumnProfile(KEY_COUNTY_CODE_2, Profile.ORGANIZATION);

	public static final ColumnProfile ADDRESS_TYPE_3 = new ColumnProfile(KEY_ADDRESS_TYPE_3, Profile.ORGANIZATION);

	public static final ColumnProfile ADDRESS_TEXT_3 = new ColumnProfile(KEY_ADDRESS_TEXT_3, Profile.ORGANIZATION);

	public static final ColumnProfile SUPPLEMENTAL_ADDRESS_TEXT_3 = new ColumnProfile(KEY_SUPPLEMENTAL_ADDRESS_TEXT_3, Profile.ORGANIZATION);

	public static final ColumnProfile LOCALITY_NAME_3 = new ColumnProfile(KEY_LOCALITY_NAME_3, Profile.ORGANIZATION);

	public static final ColumnProfile POSTAL_CODE_3 = new ColumnProfile(KEY_POSTAL_CODE_3, Profile.ORGANIZATION);

	public static final ColumnProfile COUNTRY_CODE_3 = new ColumnProfile(KEY_COUNTRY_CODE_3, Profile.ORGANIZATION);

	public static final ColumnProfile STATE_CODE_3 = new ColumnProfile(KEY_STATE_CODE_3, Profile.ORGANIZATION);

	public static final ColumnProfile COUNTY_CODE_3 = new ColumnProfile(KEY_COUNTY_CODE_3, Profile.ORGANIZATION);

	public static final ColumnProfile ORGANIZATION_SUMMARY_WQP_URL = new ColumnProfile(KEY_ORGANIZATION_SUMMARY_WQP_URL, Profile.SUMMARY_ORGANIZATION);

	public static final ColumnProfile ORGANIZATION_SUMMARY = new ColumnProfile(KEY_ORGANIZATION_SUMMARY, Profile.SUMMARY_ORGANIZATION);

	public static final ColumnProfile ORGANIZATION_SUMMARY_LAST_RESULT = new ColumnProfile(KEY_LAST_RESULT, Profile.SUMMARY_ORGANIZATION);

	public static final ColumnProfile ORGANIZATION_SUMMARY_SITE_COUNT = new ColumnProfile(KEY_SITE_COUNT, Profile.SUMMARY_ORGANIZATION);

	public static final ColumnProfile ORGANIZATION_SUMMARY_ACTIVITY_COUNT = new ColumnProfile(KEY_ACTIVITY_COUNT, Profile.SUMMARY_ORGANIZATION);
}
