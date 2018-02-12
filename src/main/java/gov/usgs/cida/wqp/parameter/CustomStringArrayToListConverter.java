package gov.usgs.cida.wqp.parameter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StrTokenizer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomStringArrayToListConverter implements Converter<String[], List<String>> {

	@Override
	public List<String> convert(String[] source) {
		List<String> a = 
				Arrays.stream(source)
					.flatMap(x -> Arrays.stream(new StrTokenizer(x, ";").getTokenArray()))
					.filter(x -> StringUtils.isNotBlank(x))
					.collect(Collectors.toList());
		return a.isEmpty() ? null : a;
	}

}
