package gov.usgs.cida.wqp.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;

public abstract class WqpEnv {
	public static final String BASE_PACKAGE = "gov.usgs.cida.wqp";
	public static final String PROPERTIES_FILE = "wqpgateway.properties";
	public static final String CONTAINER_PROPERTIES_FILE = "file:${catalina.base}/conf/" + PROPERTIES_FILE;
	
	public static Environment env;
	public static final Map<String,String> runtime = new HashMap<String, String>();
	
	public static String get(String property) {
		String value = runtime.get(property);
		if (value == null) {
			value = env.getProperty(property);
		}
		return value;
	}
	
	public static void setEnv(Environment env) {
		WqpEnv.env = env;
	}

}
