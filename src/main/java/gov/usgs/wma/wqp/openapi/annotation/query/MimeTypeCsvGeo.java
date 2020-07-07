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
		name = FilterParameters.MIMETYPE_CONST,
		description = MimeTypeCsvGeo.DESCRIPTION,
		schema = @Schema(type = "string"),
		examples = {
				@ExampleObject(name="Comma-separated", value="csv"),
				@ExampleObject(name="GEOJSON", value="geojson")
				}
		)
public @interface MimeTypeCsvGeo {

	public static final String DESCRIPTION = "Can be used in place of the 'Accept' header.";

}
