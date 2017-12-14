package gov.usgs.cida.wqp.parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomStringArrayToListConverter implements Converter<String[], List<String>> {

	@Override
	public List<String> convert(String[] source) {
		List<String> a = new ArrayList<>();
		Arrays.stream(source).forEach(x -> a.addAll(Arrays.asList(x.split(";"))));
		return a;
	}

}
