package gov.usgs.wma.wqp.openapi.annotation.query;

import gov.usgs.wma.wqp.parameter.FilterParameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
@Parameter(
		name = FilterParameters.COUNTS_CONST,
		in = ParameterIn.QUERY,
		description = "Should counts of records be included in the header. Counting all records before sending them makes the response take significantly longer.  Default 'yes'.",
		schema = @Schema(type = "string"),
		examples = {
				@ExampleObject(name="yes", description="Include record counts in the response header.", value="yes"),
				@ExampleObject(name="no", description="Do not include record counts in the response header.", value="no")
				}
		)
public @interface Counts {

}
