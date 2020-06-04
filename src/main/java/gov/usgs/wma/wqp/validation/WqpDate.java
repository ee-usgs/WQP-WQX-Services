package gov.usgs.wma.wqp.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import gov.usgs.wma.wqp.parameter.Parameters;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = WqpDateValidator.class)
@Documented
public @interface WqpDate {

	String message() default "The value of {parameter}=${validatedValue} must be a valid date in the format {formatString}.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	Parameters parameter();

	String formatString();

}
