package gov.usgs.wma.wqp.parameter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class CustomStringConverter implements Converter<String, String> {

	@Override
	public String convert(String source) {
		return StringUtils.isNotBlank(source) ? source : null;
	}

}
