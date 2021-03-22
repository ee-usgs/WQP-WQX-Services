package gov.usgs.wma.wqp.openapi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import gov.usgs.wma.wqp.openapi.annotation.query.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.usgs.wma.wqp.openapi.annotation.post.FilterParameters;
import io.swagger.v3.oas.annotations.Operation;

@Retention(RUNTIME)
@Target({ METHOD, PARAMETER })
@Operation(description=PostOperation.DEFAULT_DESCRIPTION)
@FilterParameters
@MimeTypeStd
@Zip
@RequestMapping(method=RequestMethod.POST,
	consumes=MediaType.APPLICATION_JSON_VALUE)
public @interface PostOperation {
	public static final String DEFAULT_DESCRIPTION = "Return requested data. Use when the list of parameter values is too long for a query string."
			+ " See the GET endpoint for valid values.";
}
