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
@Parameter(
		name = FilterParameters.DATA_PROFILE_CONST,
		in = ParameterIn.QUERY,
		description = "Data Profile (column set) of the download. For use with the activities endpoints.",
		schema = @Schema(type = "string"),
		examples = {
				@ExampleObject(name="activity",value="activity"),
				@ExampleObject(name="activityAll", value="activityAll")
				}
		)
public @interface DataProfileActivity {

}
