package gov.usgs.cida.wqp.parameter;

import org.apache.commons.text.StrTokenizer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomStringToArrayConverter implements Converter<String, String[]>{

	@Override
	public String[] convert(String source) {
		String[] x = new StrTokenizer(source, ";").getTokenArray();
		return 0 == x.length ? null : x;
	}

}
