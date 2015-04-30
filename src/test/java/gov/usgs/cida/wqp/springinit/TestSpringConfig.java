package gov.usgs.cida.wqp.springinit;

import static org.mockito.Mockito.*;
import gov.usgs.cida.wqp.service.CodesService;
import gov.usgs.cida.wqp.util.WqpEnv;

import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

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

@Configuration
@PropertySource(value = "classpath:wqpgateway.test.properties")
public class TestSpringConfig extends SpringConfig {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void setEnvironment(Environment env) {
		WqpEnv.setEnv(env);
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean mybatis = new SqlSessionFactoryBean();
		Resource mybatisConfig = new ClassPathResource(WqpEnv.get(MYBATIS_CONF));
		mybatis.setConfigLocation(mybatisConfig);
		try {
			mybatis.setDataSource(testDataSource());
		} catch (SQLException e) {
			log.info("Issue creating testDataSource" + e.getLocalizedMessage());
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
	public CodesService codesService() {
		return mock(CodesService.class);
	};

}
