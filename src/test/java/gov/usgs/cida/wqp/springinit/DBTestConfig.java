package gov.usgs.cida.wqp.springinit;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import gov.usgs.cida.wqp.PostgresqlDataTypeFactoryWithJson;

@TestConfiguration
@Import(MybatisConfig.class)
public class DBTestConfig extends SpringTestConfig {

	@Value("${WQP_DB_URL}")
	private String datasourceUrl;

	@Value("${WQP_READ_ONLY_USERNAME}")
	private String datasourceUserUsername;

	@Value("${WQP_READ_ONLY_PASSWORD}")
	private String datasourceUserPassword;

	@Value("${WQP_SCHEMA_OWNER_USERNAME}")
	private String datasourceUsername;

	@Value("${WQP_SCHEMA_OWNER_PASSWORD}")
	private String datasourcePassword;
	
	@Value("${WQP_SCHEMA_NAME}")
	private String schemaName;

	@Bean
	public DataSource dataSource() throws SQLException {
		PGSimpleDataSource ds = new PGSimpleDataSource();
		ds.setURL(datasourceUrl);
		ds.setUser(datasourceUserUsername);
		ds.setPassword(datasourceUserPassword);
		return ds;
	}

	@Bean
	public DataSource dbUnitDataSource() throws SQLException {
		PGSimpleDataSource ds = new PGSimpleDataSource();
		ds.setURL(datasourceUrl);
		ds.setUser(datasourceUsername);
		ds.setPassword(datasourcePassword);
		return ds;
	}

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
		dbUnitDatabaseConnection.setDataSource(dbUnitDataSource());
		dbUnitDatabaseConnection.setSchema(schemaName);
		return dbUnitDatabaseConnection;
	}

}
