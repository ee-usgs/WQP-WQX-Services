package gov.usgs.wma.wqp.openapi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.usgs.wma.wqp.openapi.annotation.query.MimeTypeStd;
import gov.usgs.wma.wqp.openapi.annotation.query.Zip;
import io.swagger.v3.oas.annotations.Operation;

@Retention(RUNTIME)
@Target({ METHOD, PARAMETER })
@Operation(description=GetOperation.DEFAULT_DESCRIPTION)
@MimeTypeStd
@Zip
@RequestMapping(method=RequestMethod.GET)
public @interface GetOperation {
	public static final String DEFAULT_DESCRIPTION = "Return requested data.";
}
