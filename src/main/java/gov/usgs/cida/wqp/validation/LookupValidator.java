package gov.usgs.cida.wqp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.usgs.cida.wqp.exception.WqpException;
import gov.usgs.cida.wqp.parameter.Parameters;
import gov.usgs.cida.wqp.service.CodesService;

@Component
public class LookupValidator implements ConstraintValidator<Lookup, String> {

	@Autowired
	CodesService codesService;

	private Parameters parameter;

	@Override
	public void initialize(Lookup constraintAnnotation) {
		this.parameter = constraintAnnotation.parameter();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			if (codesService.validate(parameter, value)) {
				return true;
			}
		} catch (WqpException e) {
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate("Server error: We cannot access the list of enumerated values to validate your parameter({parameter}).")
				.addConstraintViolation();
		}
		return false;
	}

}
