package gov.usgs.wma.wqp.openapi.annotation.query;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import gov.usgs.wma.wqp.parameter.FilterParameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
@Parameter(
		name = FilterParameters.SORTED_CONST,
		in = ParameterIn.QUERY,
		description = "Should the data be sorted. Response times may be improved by not sorting.",
		schema = @Schema(type = "string"),
		examples = {
				@ExampleObject(name="Yes", description="Sort the data.", value="yes"),
				@ExampleObject(name="No", description="Do not sort the data.", value="no")
				}
		)
public @interface Sorted {

}
