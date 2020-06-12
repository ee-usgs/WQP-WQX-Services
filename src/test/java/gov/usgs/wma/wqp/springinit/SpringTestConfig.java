package gov.usgs.wma.wqp.springinit;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import gov.usgs.wma.wqp.service.CodesService;
import gov.usgs.wma.wqp.service.FetchService;

@TestConfiguration
public class SpringTestConfig {

	@MockBean
	public CodesService codesService;

	@MockBean
	public FetchService fetchService;

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		return validator;
	}
}
