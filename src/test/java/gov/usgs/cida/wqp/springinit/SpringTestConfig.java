package gov.usgs.cida.wqp.springinit;

import static org.mockito.Mockito.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.service.FetchService;

@TestConfiguration
@PropertySource(value = "classpath:application.yml")
public class SpringTestConfig {

	@Bean
	public CodesService codesService() {
		return mock(CodesService.class);
	};

	@Bean
	public FetchService fetchService() {
		return mock(FetchService.class);
	};

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		return validator;
	}
}
