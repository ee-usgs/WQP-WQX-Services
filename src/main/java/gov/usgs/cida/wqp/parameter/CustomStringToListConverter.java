package gov.usgs.cida.wqp.parameter;

import java.util.List;

import org.apache.commons.text.StrTokenizer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomStringToListConverter implements Converter<String, List<String>> {

	@Override
	public List<String> convert(String source) {
		List<String> x = new StrTokenizer(source, ";").getTokenList();
		return x.isEmpty() ? null : x;
	}

}
