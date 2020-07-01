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
		description = MimeType.DESCRIPTION,
		schema = @Schema(type = "string"),
		examples = {
				@ExampleObject(name="Comma-separated", value="csv"),
				@ExampleObject(name="Tab-separated", value="tsv"),
				@ExampleObject(name="JSON", value="json"),
				@ExampleObject(name="MS Excel 2007+", value="xlsx"),
				@ExampleObject(name="KML", description="(Keyhole Markup Language - for Sites only)", value="kml"),
				@ExampleObject(name="KMZ", description="(Keyhole Markup Language Compressed - for Sites only)", value="kmz"),
				@ExampleObject(name="GEOJSON", value="geojson"),
				@ExampleObject(name="TXT", value="text"),
				@ExampleObject(name="WQX", value="xml")
				}
		)
public @interface MimeType {

	public static final String DESCRIPTION = "Can be used in place of the 'Accept' header.";

}
