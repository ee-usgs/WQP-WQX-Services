package gov.usgs.wma.wqp.openapi.annotation.query;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import gov.usgs.wma.wqp.parameter.FilterParameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
@Parameter(
		name = FilterParameters.SITEID_CONST,
		in = ParameterIn.QUERY,
		description = "One or more case-sensitive Site IDs.",
		array = @ArraySchema(schema = @Schema(type = "string"))
		)
public @interface Siteid {

}
