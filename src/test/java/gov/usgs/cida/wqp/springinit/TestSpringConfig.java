package gov.usgs.cida.wqp.springinit;

import static org.mockito.Mockito.mock;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.service.FetchService;
import gov.usgs.cida.wqp.util.WqpEnv;
import oracle.jdbc.pool.OracleDataSource;

@Configuration
@PropertySource(value = "classpath:wqpgateway.test.properties")
public class TestSpringConfig extends SpringConfig {

	private static final Logger LOG = LoggerFactory.getLogger(TestSpringConfig.class);

	@Override
	public void setEnvironment(Environment env) {
		WqpEnv.setEnv(env);
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean mybatis = new SqlSessionFactoryBean();
		Resource mybatisConfig = new ClassPathResource(MYBATIS_CONF);
		mybatis.setConfigLocation(mybatisConfig);
		try {
			mybatis.setDataSource(testDataSource());
		} catch (SQLException e) {
			LOG.info("Issue creating testDataSource" + e.getLocalizedMessage());
			throw new RuntimeException(e);
		}
		return mybatis;
	}

	@Bean
	public OracleDataSource testDataSource() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(WqpEnv.get("test.datasource.url"));
		ds.setUser(WqpEnv.get("test.datasource.username"));
		ds.setPassword(WqpEnv.get("test.datasource.password"));
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
		dbUnitDatabaseConnection.setDataSource(testDataSource());
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
