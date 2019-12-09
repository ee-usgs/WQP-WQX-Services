package gov.usgs.cida.wqp.springinit;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import gov.usgs.cida.wqp.PostgresqlDataTypeFactoryWithJson;

@TestConfiguration
@Import(MybatisConfig.class)
public class DBTestConfig extends SpringTestConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return dataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource-dbunit")
	public DataSourceProperties dataSourcePropertiesWqpCore() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource-dbunit")
	public DataSource dataSourceWqpCore() {
		return dataSourcePropertiesWqpCore().initializeDataSourceBuilder().build();
	}

	@Value("${WQP_SCHEMA_NAME}")
	private String schemaName;

	//Beans to support DBunit for unit testing with PostgreSQL.
	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfig() {
		DatabaseConfigBean dbUnitDbConfig = new DatabaseConfigBean();
		dbUnitDbConfig.setDatatypeFactory(new PostgresqlDataTypeFactoryWithJson());
		dbUnitDbConfig.setQualifiedTableNames(false);
		return dbUnitDbConfig;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(dataSourceWqpCore());
		dbUnitDatabaseConnection.setSchema(schemaName);
		return dbUnitDatabaseConnection;
	}

}
