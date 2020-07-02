package gov.usgs.wma.wqp.openapi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.usgs.wma.wqp.openapi.annotation.post.FilterParameters;
import gov.usgs.wma.wqp.openapi.annotation.query.MimeTypeJson;
import gov.usgs.wma.wqp.openapi.annotation.query.Zip;
import io.swagger.v3.oas.annotations.Operation;

@Retention(RUNTIME)
@Target({ METHOD, PARAMETER })
@Operation(description=PostCountOperation.DEFAULT_DESCRIPTION)
@FilterParameters
@MimeTypeJson
@Zip
@RequestMapping(method=RequestMethod.POST,
	value="count",
	consumes=MediaType.APPLICATION_JSON_VALUE,
	produces=MediaType.APPLICATION_JSON_VALUE
	)
public @interface PostCountOperation {
	public static final String DEFAULT_DESCRIPTION = "Return anticipated record counts. Use when the list of parameter values is too long for a query string."
	+ " See the HEAD endpoint for valid values.";
}
