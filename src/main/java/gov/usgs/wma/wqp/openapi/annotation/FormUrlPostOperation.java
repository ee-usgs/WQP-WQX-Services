package gov.usgs.wma.wqp.openapi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;

@Retention(RUNTIME)
@Target({ METHOD, PARAMETER })
@Operation(description=FormUrlPostOperation.DEFAULT_DESCRIPTION, hidden=true)
@RequestMapping(method=RequestMethod.POST,
	consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public @interface FormUrlPostOperation {
	public static final String DEFAULT_DESCRIPTION = "Same as the JSON consumer, but hidden from swagger";
}
