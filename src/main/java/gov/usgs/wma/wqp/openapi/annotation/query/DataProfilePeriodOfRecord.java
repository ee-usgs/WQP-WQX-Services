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
		name = FilterParameters.DATA_PROFILE_CONST,
		in = ParameterIn.QUERY,
		description = "Data Profile (column set) of the download. For use with the activities endpoints.",
		schema = @Schema(type = "string"),
		examples = {
				@ExampleObject(name="summaryMonitoringLocation",value="summaryMonitoringLocation"),
				@ExampleObject(name="periodOfRecord", value="periodOfRecord")
				}
		)
public @interface DataProfilePeriodOfRecord {

}
