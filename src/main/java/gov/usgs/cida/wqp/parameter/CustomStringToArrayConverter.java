package gov.usgs.cida.wqp.parameter;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringTokenizer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomStringToArrayConverter implements Converter<String, String[]> {

	@Override
	public String[] convert(String source) {
		String[] a = Arrays.stream(new StringTokenizer(source, ";").getTokenArray())
				.filter(x -> StringUtils.isNotBlank(x))
				.toArray(String[]::new);
		return 0 == a.length ? null : a;
	}

}
