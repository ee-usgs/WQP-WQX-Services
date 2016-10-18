package gov.usgs.cida.wqp.mapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ColumnProfile {

	private final String key;

	private final Set<String> profiles;

	public ColumnProfile(String key, String... profiles) {
		this.key = key;
		this.profiles = new HashSet<>(Arrays.asList(profiles));
	}

	public String getKey() {
		return key;
	}

	public Set<String> getProfiles() {
		return profiles;
	}

}
