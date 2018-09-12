package gov.usgs.cida.wqp.mapping;

public abstract class OrganizationColumn extends BaseColumn {

	//ResultSet Keys
	public static final String KEY_ORGANIZATION_ID = "ORGANIZATION_ID";
	public static final String KEY_ORGANIZATION_DESCRIPTION = "ORGANIZATION_DESCRIPTION";
	public static final String KEY_ORGANIZATION_TYPE = "ORGANIZATION_TYPE";
	public static final String KEY_TRIBAL_CODE = "TRIBAL_CODE";
	public static final String KEY_ELECTRONIC_ADDRESS = "ELECTRONIC_ADDRESS";
	public static final String KEY_TELEPHONIC = "TELEPHONIC";
	public static final String KEY_ADDRESS_TYPE_1 = "ADDRESS_TYPE_1";
	public static final String KEY_ADDRESS_TEXT_1 = "ADDRESS_TEXT_1";
	public static final String KEY_SUPPLEMENTAL_ADDRESS_TEXT_1 = "SUPPLEMENTAL_ADDRESS_TEXT_1";
	public static final String KEY_LOCALITY_NAME_1 = "LOCALITY_NAME_1";
	public static final String KEY_POSTAL_CODE_1 = "POSTAL_CODE_1";
	public static final String KEY_COUNTRY_CODE_1 = "COUNTRY_CODE_1";
	public static final String KEY_STATE_CODE_1 = "STATE_CODE_1";
	public static final String KEY_COUNTY_CODE_1 = "COUNTY_CODE_1";
	public static final String KEY_ADDRESS_TYPE_2 = "ADDRESS_TYPE_2";
	public static final String KEY_ADDRESS_TEXT_2 = "ADDRESS_TEXT_2";
	public static final String KEY_SUPPLEMENTAL_ADDRESS_TEXT_2 = "SUPPLEMENTAL_ADDRESS_TEXT_2";
	public static final String KEY_LOCALITY_NAME_2 = "LOCALITY_NAME_2";
	public static final String KEY_POSTAL_CODE_2 = "POSTAL_CODE_2";
	public static final String KEY_COUNTRY_CODE_2 = "COUNTRY_CODE_2";
	public static final String KEY_STATE_CODE_2 = "STATE_CODE_2";
	public static final String KEY_COUNTY_CODE_2 = "COUNTY_CODE_2";
	public static final String KEY_ADDRESS_TYPE_3 = "ADDRESS_TYPE_3";
	public static final String KEY_ADDRESS_TEXT_3 = "ADDRESS_TEXT_3";
	public static final String KEY_SUPPLEMENTAL_ADDRESS_TEXT_3 = "SUPPLEMENTAL_ADDRESS_TEXT_3";
	public static final String KEY_LOCALITY_NAME_3 = "LOCALITY_NAME_3";
	public static final String KEY_POSTAL_CODE_3 = "POSTAL_CODE_3";
	public static final String KEY_COUNTRY_CODE_3 = "COUNTRY_CODE_3";
	public static final String KEY_STATE_CODE_3 = "STATE_CODE_3";
	public static final String KEY_COUNTY_CODE_3 = "COUNTY_CODE_3";
    public static final String KEY_ORGANIZATION_SUMMARY_WQP_URL = "ORGANIZATION_WQP_URL";
    public static final String KEY_ORGANIZATION_SUMMARY = "ORGANIZATION_SUMMARY";
    public static final String KEY_ORGANIZATION_SUMMARY_LAST_RESULT = "LAST RESULT";
    public static final String KEY_ORGANIZATION_SUMMARY_SITE_COUNT = "SITE_COUNT";
    public static final String KEY_ORGANIZATION_SUMMARY_ACTIVITY_COUNT = "ACTIVITY_COUNT";
    
    


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
    
    public static final ColumnProfile ORGANIZATION_SUMMARY_WQP_URL = new ColumnProfile(KEY_COUNTY_CODE_3, Profile.ORGANIZATION);

}
