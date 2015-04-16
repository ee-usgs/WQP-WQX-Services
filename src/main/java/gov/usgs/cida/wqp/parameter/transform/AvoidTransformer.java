package gov.usgs.cida.wqp.parameter.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvoidTransformer extends SplitTransformer {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public AvoidTransformer(String delimiter) {
		super(delimiter);
		log.trace(getClass().getName());
	}
	
	@Override
	public String[] transform(String value) {
		String[] strings = split(value);
		return strings;
	}
	
}
