package gov.usgs.wma.wqp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import gov.usgs.wma.wqp.mapping.Profile;
import gov.usgs.wma.wqp.parameter.Parameters;

@Component
public class EnumLookupValidator implements ConstraintValidator<EnumLookup, String> {

	private Parameters parameter;

	@Override
	public void initialize(EnumLookup constraintAnnotation) {
		this.parameter = constraintAnnotation.parameter();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
		if (StringUtils.isBlank(value)) {
			return true;
		} else {
			switch (parameter) {
			case DATA_PROFILE:
				return Profile.fromString(value) != null;
			default:
				return false;
			}
		}
	}

}
