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
@Operation(description=HeadOperation.DEFAULT_DESCRIPTION)
@MimeTypeStd
@Zip
@RequestMapping(method=RequestMethod.HEAD)
public @interface HeadOperation {
	public static final String DEFAULT_DESCRIPTION = "Return appropriate request headers (including anticipated record counts).";
}
