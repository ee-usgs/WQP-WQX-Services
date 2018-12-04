package gov.usgs.cida.wqp.springinit;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import gov.usgs.cida.wqp.PostgresqlDataTypeFactoryWithJson;

//import oracle.jdbc.pool.OracleDataSource;

@TestConfiguration
@Import(MybatisConfig.class)
public class DBTestConfig extends SpringTestConfig {

	@Value("${wqpUrl}")
	private String datasourceUrl;

	@Value("${wqpUserUsername}")
	private String datasourceUserUsername;

	@Value("${wqpUserPassword}")
	private String datasourceUserPassword;

	@Value("${wqpOwnerUsername}")
	private String datasourceUsername;

	@Value("${wqpOwnerPassword}")
	private String datasourcePassword;

//	@Bean
//	public OracleDataSource dataSource() throws SQLException {
//		OracleDataSource ds = new OracleDataSource();
//		ds.setURL(datasourceUrl);
//		ds.setUser(datasourceUserUsername);
//		ds.setPassword(datasourceUserPassword);
//		return ds;
//	}
//
//	@Bean
//	public OracleDataSource dbUnitDataSource() throws SQLException {
//		OracleDataSource ds = new OracleDataSource();
//		ds.setURL(datasourceUrl);
//		ds.setUser(datasourceUsername);
//		ds.setPassword(datasourcePassword);
//		return ds;
//	}

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

//	//Beans to support DBunit for unit testing with Oracle.
//	@Bean
//	public DatabaseConfigBean dbUnitDatabaseConfig() {
//		DatabaseConfigBean dbUnitDbConfig = new DatabaseConfigBean();
//		dbUnitDbConfig.setDatatypeFactory(new OracleDataTypeFactory());
//		dbUnitDbConfig.setSkipOracleRecyclebinTables(true);
//		dbUnitDbConfig.setQualifiedTableNames(false);
//		return dbUnitDbConfig;
//	}

	//Beans to support DBunit for unit testing with PostgreSQL.
	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfig() {
		DatabaseConfigBean dbUnitDbConfig = new DatabaseConfigBean();
		dbUnitDbConfig.setDatatypeFactory(new PostgresqlDataTypeFactoryWithJson());
		dbUnitDbConfig.setQualifiedTableNames(false);
		return dbUnitDbConfig;
	}

//	@Bean
//	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() throws SQLException {
//		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
//		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
//		dbUnitDatabaseConnection.setDataSource(dbUnitDataSource());
//		dbUnitDatabaseConnection.setSchema("WQP_CORE");
//		return dbUnitDatabaseConnection;
//	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(dbUnitDataSource());
		dbUnitDatabaseConnection.setSchema("wqp");
		return dbUnitDatabaseConnection;
	}

}
