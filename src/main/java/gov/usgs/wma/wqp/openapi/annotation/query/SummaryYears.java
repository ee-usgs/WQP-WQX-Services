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
		in = ParameterIn.QUERY,
		name = FilterParameters.SUMMARY_YEARS_CONST,
		description = "Select summary data for 1, 5, or all years. If no summary year is selected, all years is returned by default.",
		schema = @Schema(type = "string"),
		examples = {
				@ExampleObject(name="One Year", description="Return data for the past year.", value=FilterParameters.SUMMARY_YEARS_12_MONTHS),
				@ExampleObject(name="Five Years", description="Return data for the past five years.", value=FilterParameters.SUMMARY_YEARS_60_MONTHS),
				@ExampleObject(name="All Years", description="Return data for the all years.", value=FilterParameters.SUMMARY_YEARS_ALL_MONTHS)
				}
)
public @interface SummaryYears {

}
