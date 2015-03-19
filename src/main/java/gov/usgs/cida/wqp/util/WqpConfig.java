package gov.usgs.cida.wqp.util;


import java.util.HashMap;
import java.util.Map;

public abstract class WqpConfig {
	public static final Map<String,String> config = new HashMap<String, String>();
	
	public static <T> T get(String confName, Transformer<T> transformer) {
		String value = config.get(confName);
		T transformed = transformer.transform(value);
		return transformed;
	}
	public static <T> T get(String confName, Transformer<T> transformer, T defaultValue) {
		T value = get(confName, transformer);
		return value==null ?defaultValue :value;
	}

	public static String get(String confName) {
		return config.get(confName);
	}
	public static String get(String confName, String defaultValue) {
		String value = config.get(confName);
		return value==null ?defaultValue :value;
	}
	public static void set(String confName, String value) {
		config.put(confName,value);
	}
	
}
