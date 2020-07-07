package gov.usgs.wma.wqp.openapi.annotation.query;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import gov.usgs.wma.wqp.parameter.FilterParameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
@Parameter(
		name = FilterParameters.BBOX_CONST,
		in = ParameterIn.QUERY,
		description = "Western-most longitude, Southern-most latitude, Eastern-most longitude, and Northern-most longitude separated by commas,"
				+ "expressed in decimal degrees, WGS84, and longitudes west of Greenwich are negative. (Example: bBox=-92.8,44.2,-88.9,46.0)",
		schema = @Schema(type = "string")
		)
public @interface BBox {

}
