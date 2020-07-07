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
		name = FilterParameters.STATECODE_CONST,
		in = ParameterIn.QUERY,
		description = "One or more two-character Federal Information Processing Standard (FIPS) country code, followed by a URL-encoded colon (\"%3A\"), followed by a two-digit FIPS state code.",
		array = @ArraySchema(schema = @Schema(type = "string"))
		)
public @interface Statecode {

}
