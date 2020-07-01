package gov.usgs.wma.wqp.openapi.annotation.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import gov.usgs.wma.wqp.parameter.FilterParameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Organizations
@SummaryYears
@Parameter(
		in = ParameterIn.QUERY,
		name = FilterParameters.MIMETYPE_CONST,
		description = "Can be used in place of the 'Accept' header.",
		schema = @Schema(type = "string"),
		examples = {
				@ExampleObject(name="JSON", value="json")
				}
		)
@Zip
public @interface SummaryParameterListOrganization {

}
