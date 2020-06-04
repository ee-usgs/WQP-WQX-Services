package gov.usgs.wma.wqp.parameter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class CustomBoundingBoxConverter implements Converter<String, BoundingBox> {

	@Override
	public BoundingBox convert(String source) {
		return StringUtils.isNotBlank(source) ? new BoundingBox(source) : null;
	}

}
