package gov.usgs.cida.wqp.parameter;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomStringToListConverter implements Converter<String, List<String>> {

	@Override
	public List<String> convert(String source) {
		return Arrays.asList(source.split(";"));
	}

}
