package gov.usgs.cida.wqp.mapping;

public abstract class BaseColumn {

	//ResultSet Keys
	public static final String KEY_DATA_SOURCE = "DATA_SOURCE";
	public static final String KEY_ORGANIZATION = "ORGANIZATION";
	public static final String KEY_ORGANIZATION_NAME = "ORGANIZATION_NAME";
	public static final String KEY_SITE_ID = "SITE_ID";

	//Profile Mapping of the Keys
	public static final ColumnProfile DATA_SOURCE = new ColumnProfile(KEY_DATA_SOURCE, Profile.STATION, Profile.PC_RESULT, Profile.BIOLOGICAL, Profile.SIMPLE_STATION);
	public static final ColumnProfile ORGANIZATION = new ColumnProfile(KEY_ORGANIZATION, Profile.STATION, Profile.PC_RESULT, Profile.BIOLOGICAL, Profile.SIMPLE_STATION);
	public static final ColumnProfile ORGANIZATION_NAME = new ColumnProfile(KEY_ORGANIZATION_NAME, Profile.STATION, Profile.PC_RESULT, Profile.BIOLOGICAL, Profile.SIMPLE_STATION);
	public static final ColumnProfile SITE_ID = new ColumnProfile(KEY_SITE_ID, Profile.STATION, Profile.PC_RESULT, Profile.BIOLOGICAL, Profile.SIMPLE_STATION);

}
