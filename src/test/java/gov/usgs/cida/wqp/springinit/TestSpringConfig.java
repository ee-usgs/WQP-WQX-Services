package gov.usgs.cida.wqp.springinit;

import static org.mockito.Mockito.mock;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.service.FetchService;
import gov.usgs.cida.wqp.util.WqpEnv;
import oracle.jdbc.pool.OracleDataSource;

@Configuration
@PropertySource(value = "classpath:wqpgateway.test.properties")
public class TestSpringConfig extends SpringConfig {

	@Override
	public void setEnvironment(Environment env) {
		WqpEnv.setEnv(env);
	}

	@Bean
	public OracleDataSource dataSource() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(WqpEnv.get("test.datasource.url"));
		ds.setUser(WqpEnv.get("test.datasource.username"));
		ds.setPassword(WqpEnv.get("test.datasource.password"));
		return ds;
	}

	@Bean
	public OracleDataSource dbUnitDataSource() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(WqpEnv.get("test.datasource.url"));
		ds.setUser(WqpEnv.get("test.dbunit.username"));
		ds.setPassword(WqpEnv.get("test.dbunit.password"));
		return ds;
	}

	//Beans to support DBunit for unit testing with Oracle.
	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfig() {
		DatabaseConfigBean dbUnitDbConfig = new DatabaseConfigBean();
		dbUnitDbConfig.setDatatypeFactory(new OracleDataTypeFactory());
		dbUnitDbConfig.setSkipOracleRecyclebinTables(true);
		dbUnitDbConfig.setQualifiedTableNames(false);
		return dbUnitDbConfig;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(dbUnitDataSource());
		dbUnitDatabaseConnection.setSchema("WQP_CORE");
		return dbUnitDatabaseConnection;
	}

	@Bean
	public URL TEST_NLDI_URL() throws MalformedURLException {
		return new URL(WqpEnv.get("test.nldi.url"));
	}

	@Bean
	public CodesService codesService() {
		return mock(CodesService.class);
	};

	@Bean
	public FetchService fetchService() {
		return mock(FetchService.class);
	};

}
