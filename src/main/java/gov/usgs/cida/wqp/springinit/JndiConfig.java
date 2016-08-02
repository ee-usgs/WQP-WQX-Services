package gov.usgs.cida.wqp.springinit;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.usgs.cida.wqp.util.JndiUtils;
import gov.usgs.cida.wqp.util.WqpEnv;
import gov.usgs.cida.wqp.util.WqpEnvProperties;

@Configuration
public class JndiConfig {

	@Bean
	public DataSource dataSource() throws Exception {
		return JndiUtils.jndiDataSource(WqpEnv.get(WqpEnvProperties.JNDI_DATASOURCE));
	}

}
