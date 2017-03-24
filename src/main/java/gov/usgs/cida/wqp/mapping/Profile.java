package gov.usgs.cida.wqp.mapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Profile {

	STATION ("station"),
	SIMPLE_STATION ("simplestation"),
	BIOLOGICAL ("biological"),
	PC_RESULT ("pcresult"),
	ACTIVITY ("activity"),
	ACTIVITY_METRIC ("activityMetric"),
	RES_DETECT_QNT_LMT ("resDetectQntLmt"),
	NARROW ("narrow");

	private final String name;
	private static Map<String, Profile> profileMap = new HashMap<>();
	
	static {
		for (Profile profile : Profile.values()) {
			profileMap.put(profile.name, profile);
		}
	}

	private Profile(String value) {
		name = value;
	}

	@Override
	public String toString() {
		return name;
	}

	public static Profile fromString(String profile) {
		if (profile == null || profile.isEmpty()) {
			return null;
		}
		try {
			return profileMap.get(profile);
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static Set<String> getValues() {
		return profileMap.keySet();
	}
}
