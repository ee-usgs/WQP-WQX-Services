package gov.usgs.cida.wqp.springinit;

import java.sql.SQLException;

import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import oracle.jdbc.pool.OracleDataSource;

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

	@Bean
	public OracleDataSource dataSource() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(datasourceUrl);
		ds.setUser(datasourceUserUsername);
		ds.setPassword(datasourceUserPassword);
		return ds;
	}

	@Bean
	public OracleDataSource dbUnitDataSource() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(datasourceUrl);
		ds.setUser(datasourceUsername);
		ds.setPassword(datasourcePassword);
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

}
