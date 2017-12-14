package gov.usgs.cida.wqp.validation;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.parameter.Parameters;

public class PointLocationValidator implements ConstraintValidator<PointLocation, FilterParameters> {

	@Override
	public boolean isValid(FilterParameters value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		SortedSet<String> containsSet = new TreeSet<String>();
		SortedSet<String> missingSet = new TreeSet<String>();

		if (StringUtils.isBlank(value.getLat())) {
			missingSet.add(Parameters.LATITUDE.toString());
		} else {
			containsSet.add(Parameters.LATITUDE.toString());
		}

		if (StringUtils.isBlank(value.getLong())) {
			missingSet.add(Parameters.LONGITUDE.toString());
		} else {
			containsSet.add(Parameters.LONGITUDE.toString());
		}

		if (StringUtils.isBlank(value.getWithin())) {
			missingSet.add(Parameters.WITHIN.toString());
		} else {
			containsSet.add(Parameters.WITHIN.toString());
		}

		if (containsSet.size() > 0 && missingSet.size() > 0) {
			HibernateConstraintValidatorContext hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);

			hibernateContext.disableDefaultConstraintViolation();
			hibernateContext
				.addExpressionVariable("containsSet", containsSet)
				.addExpressionVariable("missingSet", missingSet)
				.buildConstraintViolationWithTemplate("{message}")
				.addConstraintViolation();

			return false;
		} else {
			return true;
		}
	}

}
