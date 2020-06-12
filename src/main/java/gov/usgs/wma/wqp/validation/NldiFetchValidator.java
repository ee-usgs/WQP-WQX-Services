package gov.usgs.wma.wqp.validation;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.usgs.wma.wqp.parameter.FilterParameters;
import gov.usgs.wma.wqp.service.FetchService;

@Component
public class NldiFetchValidator implements ConstraintValidator<Fetch, FilterParameters> {

	@Autowired
	FetchService fetchService;

	public static final String NLDI_WQP_FEATURE_IDENTIFIER = "identifier";

	@Override
	public boolean isValid(FilterParameters value, ConstraintValidatorContext context) {
		if (null == value || StringUtils.isBlank(value.getNldiurl())) {
			return true;
		}
		try {
			List<String> codeValues = fetchService.fetch(NLDI_WQP_FEATURE_IDENTIFIER, new URL(value.getNldiurl()));
			if (codeValues.isEmpty()) {
				return false;
			} else {
				value.setNldiSites(codeValues);
				return true;
			}
		} catch (IOException e) {
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate("The value of {parameter}=${validatedValue.nldiurl} cannot be accessed to validate your request.")
				.addConstraintViolation();
		}
		return false;
	}

}
