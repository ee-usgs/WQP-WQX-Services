package gov.usgs.wma.wqp.springinit;

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

import gov.usgs.wma.wqp.PostgresqlDataTypeFactoryWithJson;

@TestConfiguration
@Import(MybatisConfig.class)
public class DBTestConfig extends SpringTestConfig {

	public static final String DBUNIT_CONNECTION = "dbUnitDatabaseConnection";
	public static final String DBUNIT_CONNECTION_WSL = "dbUnitDatabaseConnectionWSL";

	@Value("${spring.datasource-dbunit.schemaName}")
	private String schemaNameDbunit;

	@Value("${spring.datasource-dbunit-wsl.schemaName}")
	private String schemaNameDbunitWSL;

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
	public DataSourceProperties dataSourcePropertiesDbunit() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource-dbunit")
	public DataSource dataSourceDbunit() {
		return dataSourcePropertiesDbunit().initializeDataSourceBuilder().build();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource-dbunit-wsl")
	public DataSourceProperties dataSourcePropertiesDbunitWSL() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource-dbunit-wsl")
	public DataSource dataSourceDbunitWSL() {
		return dataSourcePropertiesDbunitWSL().initializeDataSourceBuilder().build();
	}

	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfig() {
		DatabaseConfigBean dbUnitDbConfig = new DatabaseConfigBean();
		dbUnitDbConfig.setDatatypeFactory(new PostgresqlDataTypeFactoryWithJson());
		dbUnitDbConfig.setQualifiedTableNames(false);
		dbUnitDbConfig.setTableType(new String[] {"PARTITIONED TABLE", "TABLE"});
		return dbUnitDbConfig;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(dataSourceDbunit());
		dbUnitDatabaseConnection.setSchema(schemaNameDbunit);
		return dbUnitDatabaseConnection;
	}

	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfigWSL() {
		DatabaseConfigBean dbUnitDbConfig = new DatabaseConfigBean();
		dbUnitDbConfig.setDatatypeFactory(new PostgresqlDataTypeFactoryWithJson());
		dbUnitDbConfig.setQualifiedTableNames(false);
		dbUnitDbConfig.setTableType(new String[] {"TABLE"});
		return dbUnitDbConfig;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnectionWSL() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfigWSL());
		dbUnitDatabaseConnection.setDataSource(dataSourceDbunitWSL());
		dbUnitDatabaseConnection.setSchema(schemaNameDbunitWSL);
		return dbUnitDatabaseConnection;
	}
}
